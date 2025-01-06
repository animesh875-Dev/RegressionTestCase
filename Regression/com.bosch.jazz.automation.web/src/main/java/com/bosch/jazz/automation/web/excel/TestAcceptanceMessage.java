/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;


/**
 * @author CIK5KOR
 */
public class TestAcceptanceMessage {

  private boolean isTestPassed;
  private String message;

  /**
   * Default Constructor
   */
  public TestAcceptanceMessage() {
    this.isTestPassed = false;
  }

  /**
   * @param isTestPassed boolean if test is passed or failed
   * @param message optional message for more information to the end user
   */
  public TestAcceptanceMessage(boolean isTestPassed, String message) {
    this.isTestPassed = isTestPassed;
    this.message = message;
  }

  /**
   * @return the failMessage
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param failMessage the failMessage to set
   */
  public void setMessage(String failMessage) {
    this.message = failMessage;
  }

  /**
   * @return Returns the State as String
   */
  public String getState() {
    if (isTestPassed) {
      return "PASSED";
    }
    return "FAILED";
  }

}
