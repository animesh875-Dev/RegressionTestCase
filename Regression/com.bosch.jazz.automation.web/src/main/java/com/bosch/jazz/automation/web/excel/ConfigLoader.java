/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bosch.jazz.automation.web.common.WebAutomationException;

/**
 * @author hrt5kor ConfigLoader to read the Environment provider and Configuration data
 */
public class ConfigLoader {

  /**
   * Variable to store the main folder of the test data files.
   */
  private static final String RESOURCE_PATH = "configuration";


  private ConfigLoader() {}

  /**
   * If the environment properties contains "config.suffix" property then configuration specified by this value is
   * loaded otherwise only baseConfigName+"_Config" is loaded. Local configurations are stored in an excel file with
   * property and value with the same name of the test case file name and configuration prefix and suffix value.
   *
   * @param globalPropertiesMap globalProperties
   * @param testName Java or excel test file name
   * @return configuration specific for the suite
   * @throws IOException IOException
   */
  public static Map<String, String> loadConfig(final Map<String, String> globalPropertiesMap, final String testName) throws IOException
       {
    String prefix = "Config";
    String configSuffix = "config.suffix";
    configSuffix = globalPropertiesMap.containsKey(configSuffix) ? "-" + globalPropertiesMap.get(configSuffix) : "";
    String configName = testName + "_" + prefix + configSuffix;
    String filePath = getFilePathBySearchingAllDirectories(System.getProperty("ConfigDirectory",RESOURCE_PATH), configName);
    Map<String, String> configurations= new HashMap<>();
    try {
      configurations= ConfigReader.readExcelFile(filePath);
    }
    catch (Exception e) {
      throw new IOException("Configuration File \""+filePath+ "\" not found or seems to be corrupted.");
    }
    return configurations;
  }

  /**
   * Method to return the path of the configuration file by searching from the main directory {@resourcePath}, as the
   * test name will be same as the configuration data file name and with _ Environment properties file name.
   *
   * @param directoryName src/test/resources
   * @param configName name of the config for test class or excel test suite file
   * @return absolute file path if the text data i.eexcel sheet with the name of the test case name is found.
   */
  private static String getFilePathBySearchingAllDirectories(final String directoryName, final String configName) {
    try (Stream<Path> walk = Files.walk(Paths.get(new File(directoryName).getAbsolutePath()))) {
      List<String> result =
          walk.filter(path -> path.toFile().isFile()).map(Path::toString).collect(Collectors.toList());
      for (String filePath : result) {
        if (filePath.endsWith("\\" + configName + ".xlsx")) {
          return filePath;
        }
      }

    }
    catch (IOException e) {
      throw new WebAutomationException(e);
    }
    return null;
  }

}
