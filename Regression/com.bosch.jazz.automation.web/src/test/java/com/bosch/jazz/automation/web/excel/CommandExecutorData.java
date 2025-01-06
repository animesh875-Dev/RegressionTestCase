/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;

/**
 * @author HCM6KOR Test class for quality gates.
 */
public class CommandExecutorData {

  /**
   * @param driverCustom Parameterised constructor to instantiate the class.
   */
  public CommandExecutorData(final WebDriverCustom driverCustom) {
    this.driverCustom = driverCustom;
  }

  WebDriverCustom driverCustom;

  /**
   * @param subString the input String.
   * @return Substring of input String.
   */
  public String testCommandExcecutor(final String subString) {
    return subString.substring(3);
  }

  /**
   * @return true or false
   */
  public boolean testCommandExcecutor2() {
    System.out.println("subString + subsString");
    return true;
  }

  /**
   * @param additionalParams input map.
   * @param subString first input String.
   * @param subsString second input String.
   * @return concatenated String.
   */
  public String testCommandExcecutor3(final Map<String, String> additionalParams, final String subString,
      final String subsString) {
    return additionalParams.get("name") + additionalParams.get("company") + subString + subsString;
  }

  /**
   * @param additionalParams input map.
   * @return concatenated String.
   */
  public String testCommandExcecutor4(final Map<String, String> additionalParams) {
    return additionalParams.get("name") + additionalParams.get("company");
  }

  /**
   * @param firstString first input String.
   * @param secondString second input String.
   * @return String concatenated String.
   */
  public String testCommandExcecutor5(final String firstString, final String secondString) {
    return firstString + secondString;
  }
}
