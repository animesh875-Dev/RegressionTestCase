/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.utils.misc.encryption.EncryptionHelper;
import com.bosch.jazz.utils.tests.AbstractUserCredentialsRequiringTest;

/**
 * THis class is used to invoke the methods of AbstractUserCredentialsRequiringTest
 */
public class UserCredentialsHelper extends AbstractUserCredentialsRequiringTest {



  /**
   * this method reads the usercredential file and fetches the userid.
   * 
   * @return encrypted value
   */
  public String getUserNameFromFile() {
    UserCredentialsHelper helper = new UserCredentialsHelper();
    String username = null;
    try {
      String fileLoc = helper.getUserCredentialsFile();
      username = helper.getPropertyFromCredentialFile(PROPERTY_USERID, fileLoc);
      return username;
    }

    catch (SecurityException | IllegalArgumentException e) {
      throw new WebAutomationException("Exception while fetching the userid from U drive.");
    }
  }

  public String getPropertyFromCredentialFile(final String key, final String userCredFile) {
    if (!org.apache.commons.lang.StringUtils.isEmpty(userCredFile)) {

      final File credentialsFile = new File(userCredFile);

      final Properties userProperties = new Properties(null);
      try (FileReader reader = new FileReader(credentialsFile)) {
        userProperties.load(reader);
        return userProperties.getProperty(key);
      }
      catch (final IOException e) {
        throw new WebAutomationException("IOException happened: " + e.getMessage());
      }
    }
    return null;
  }

  /**
   * Method to decrypt the encyrpted password.
   * 
   * @param password pasword added in the environment variable
   * @return decrypted value
   */
  public String getDecryptedPassword(String password) {
    try {
      UserCredentialsHelper helper = new UserCredentialsHelper();
      String fileLoc = helper.getKeyFile();
      File credentialsKeyFile = new File(fileLoc);
      EncryptionHelper encryptionHelper = getEncryptionHelper();
      char[] decryptedPassword = encryptionHelper.decrypt(password, credentialsKeyFile);
      return new String(decryptedPassword);
    }
    catch (Exception e) {
      throw new WebAutomationException("Exception while decrypting the password");
    }
  }
}
