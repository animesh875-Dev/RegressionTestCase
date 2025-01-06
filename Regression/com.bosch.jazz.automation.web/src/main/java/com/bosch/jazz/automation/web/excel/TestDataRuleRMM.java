/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Map;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.bosch.jazz.automation.web.common.WebAutomationException;

/**
 * @author PZP1KOR
 */
public class TestDataRuleRMM extends TestWatcher {

  /**
   * Variable to store the main folder of the test data files.
   */
  String testClassName = null;
  private Map<String, String> testDataMap = null;

  /**
   * {@inheritDoc}
   */
  @Override
  protected void starting(final Description d) {
    loadConfigurationMBD(d.getTestClass().getSimpleName());
  }

  /**
   * Reads the environment proprties file and configuration properties from the file and merge both the properties. If
   * the environment properties contains "config.suffix" property then configuration value is returned otherwise only
   * "Config" is returned. Local configurations are stored in an excel file with property and value with the same name
   * of the test case file name and conguration prefix and suffix value.
   *
   * @param fileName name of the Configuration file.
   */
  private void loadConfiguration(final String fileName) {
    try {
      Map<String, String> globalPropertiesMap = EnvironmentProvider.loadGlobalEnv();
      setTestDataMap(EnvironmentProvider.mergeSuiteConfig(globalPropertiesMap,
          ConfigLoader.loadConfig(globalPropertiesMap, fileName)));
      setTestDataMap(globalPropertiesMap);
    }
    catch (IOException e) {
      throw new WebAutomationException(e);
    }
  }

  /**
   * Reads the environment proprties file and configuration properties from the file and merge both the properties.
   *
   * @param fileName name of the Configuration file.
   */
  private void loadConfigurationMBD(final String fileName) {
    try {
      Map<String, String> globalPropertiesMap = EnvironmentProvider.loadGlobalEnv();
      setTestDataMap(globalPropertiesMap);
    }
    catch (IOException e) {
      throw new WebAutomationException(e);
    }
  }

  /**
   * Method returns the value of the given property.
   *
   * @param property configuration name.
   * @return value for the given configuration.
   * @throws InvalidKeyException if the property doesn't exist in the configuration.
   */
  public String getConfigData(final String property) throws InvalidKeyException {
    if (this.testDataMap.get(property) != null) {
      return this.testDataMap.get(property);
    }
    throw new InvalidKeyException(property + " property doesn't exist in the configuration.");
  }

  /**
   * Sets the map.
   *
   * @param testDataMap the testDataMap to set
   */
  public void setTestDataMap(final Map<String, String> testDataMap) {
    this.testDataMap = testDataMap;
  }


}
