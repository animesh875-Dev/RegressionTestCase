/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.constants.UserCredentialsHelper;
import com.bosch.jazz.utils.misc.encryption.EncryptionHelper;

/**
 * @author HCM6KOR This class reads the environment.properties file and stores the data as key value pair in a map. also
 *         merges the globalEnv map and suiteConfig map and returns the combined map.
 */
public class EnvironmentProvider {

  private EnvironmentProvider() {}

  /**
   * Constant to represent label passwod.
   */
  public static final String TEXT_PASSWOD = "PASSWORD";

  /**
   * Constant to represent label username.
   */
  public static final String TEXT_USERNAME = "USERNAME";

  /**
   * Constant used for identifying the user credentials in u drive location
   */
  public static final String U_DRIVE_LOCATION = "D:/com.bosch.rtc.utils/";
  
  /**
   * Constant used for identifying the user credentials in u drive location
   */
  public static final String USERNAME_PASSWORD_LOCATION = "C:/Users/gho6hc/AppData/Local/Temp/";
  
  /**
   * Constant used for identifying the user credentials in u drive location
   */
  public static final String DEFAULT_KEY_FILE_NAME = "key.txt";
  /**
   * Constant used for identifying the user credentials in u drive location
   */
  public static final String DEFAULT_CREDENTIAL_FILE_NAME = "user_credentials.txt";
  /**
   * Constant to represent label username.
   */
  public static final String PROPERTY_USERID = "userid";
  /**
   * Constant to represent label username.
   */
  public static final String PROPERTY_USERPASSWORD = "userpassword";
  

  /**
   * This method reads the environment.properties file and stores the data as key value pair in a map.
   *
   * @return global environment map.
   * @throws IOException if the file cannot be opened.
   */
  public static Map<String, String> loadGlobalEnv() throws IOException {
    HashMap<String, String> globalEnv = new HashMap<>();
    String filePath = "src/main/resources/excel_webtest/environment.properties";
    try (FileInputStream i =
        new FileInputStream(new File(System.getProperty("environment", filePath)).getAbsolutePath())) {

      Properties properties = new Properties();
      properties.load(i);
      Set<Object> keys = properties.keySet();

      if ((System.getenv(TEXT_PASSWOD) != null) && (System.getenv(TEXT_PASSWOD).trim().length() > 0)) {
        globalEnv.put(TEXT_PASSWOD, System.getenv(TEXT_PASSWOD));
      }
      else if (new File(System.getProperty("java.io.tmpdir")+"//temp.txt").exists())
      {
        FileInputStream inputStream = new FileInputStream(System.getProperty("java.io.tmpdir")+"//temp.txt");
        String pwd=null;
        try {
            pwd = IOUtils.toString(inputStream);
        } finally {
            inputStream.close();
        }
        globalEnv.put(TEXT_PASSWOD, pwd);
      }
      else if ((properties.get(TEXT_USERNAME) != null) &&
          (properties.get(TEXT_USERNAME).toString().trim().length() > 0)) {
        String decryptedPassword = getPasswordFromDrive(properties);
        globalEnv.put(TEXT_PASSWOD, decryptedPassword);
      }
      else {
        throw new WebAutomationException("Please enter username in the environment.properties file.");
      }

      for (Object obj : keys) {
        String key = obj.toString().trim();
        String value = properties.getProperty(key).trim();
        globalEnv.put(key, value);
      }
    }
    catch (IOException e) {
      throw new IOException("File \"" + filePath + "\" was not found.");
    }
    return globalEnv;
  }

  /**
   * This method merges both the input maps and returns a combined map. In case of duplicate key, the value in
   * suiteConfig override the value in env.
   *
   * @param globalEnv global environment map.
   * @param suiteConfig map from the suite config.
   * @return merged map.
   */
  public static Map<String, String> mergeSuiteConfig(final Map<String, String> globalEnv,
      final Map<String, String> suiteConfig) {
    HashMap<String, String> env = new HashMap<>();
    env.putAll(globalEnv);
    env.putAll(suiteConfig);
    return env;
  }

  private static String getPasswordFromDrive(final Properties properties) {
    String decryptedPassword = null;
    String username = null;

    File f = new File(USERNAME_PASSWORD_LOCATION);
    if (f.exists() && f.isDirectory()) {
      UserCredentialsHelper credentialsHelper = new UserCredentialsHelper();
      username = getUserNameFromFile();
      if ((username != null) && username.equalsIgnoreCase(properties.get(TEXT_USERNAME).toString())) {
        decryptedPassword = getDecryptedPassword(getEncryptedPasswordFromFile());
        return decryptedPassword;
      }
      throw new WebAutomationException("Username are not same in the environment properties and in U drive.");
    }
    throw new WebAutomationException("Password is not present either in the environment properties or in U drive.");
  }
  
  private static String getUserNameFromFile() {
    UserCredentialsHelper helper = new UserCredentialsHelper();
    String username = null;
    try {
      String fileLoc = USERNAME_PASSWORD_LOCATION + DEFAULT_CREDENTIAL_FILE_NAME;
      username = helper.getPropertyFromCredentialFile(PROPERTY_USERID, fileLoc);
      return username;
    }

    catch (SecurityException | IllegalArgumentException e) {
      throw new WebAutomationException("Exception while fetching the userid from U drive.");
    }
  }
  /**
   * this method reads the usercredential file and fetches the encrypted password.
   * 
   * @return encrypted value
   */
  private static String getEncryptedPasswordFromFile() {
    UserCredentialsHelper helper = new UserCredentialsHelper();
    String encryptedPassword = null;
    try {
      String fileLoc = USERNAME_PASSWORD_LOCATION + DEFAULT_CREDENTIAL_FILE_NAME;
      encryptedPassword = helper.getPropertyFromCredentialFile(PROPERTY_USERPASSWORD, fileLoc);
      return encryptedPassword;
    }

    catch (SecurityException | IllegalArgumentException e) {
      throw new WebAutomationException("Exception while fetching the encyrpted password from U drive.");
    }
  }
  /**
   * Method to decrypt the encyrpted password.
   * 
   * @param password pasword added in the environment variable
   * @return decrypted value
   */
  private static String getDecryptedPassword(String password) {
    try {
      UserCredentialsHelper helper = new UserCredentialsHelper();
      String fileLoc = USERNAME_PASSWORD_LOCATION + DEFAULT_KEY_FILE_NAME;
      File credentialsKeyFile = new File(fileLoc);
      EncryptionHelper encryptionHelper = EncryptionHelper.getInstance();
      char[] decryptedPassword = encryptionHelper.decrypt(password, credentialsKeyFile);
      return new String(decryptedPassword);
    }
    catch (Exception e) {
      throw new WebAutomationException("Exception while decrypting the password");
    }
  }
}
//private static String getPasswordFromDrive(final Properties properties) {
//  return "RCM_Test2025";
//}

//}
