/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.util.List;

/**
 * POJO class for the Excel Test Script reader.
 */
public class ExcelScriptResult {

  private String returnValue;
  private String action;
  private String actionType;
  private String screenshotFileName;
  private String result;
  private String parameter;
  private String values;
  private TestAcceptanceMessage testAcceptanceMessage;
  private String failMessage = "";
  private List<TestAcceptanceMessage> verificationResultMsg;
  private String description;

  /**
   * @return the returnValue
   */
  public String getReturnValue() {
    return this.returnValue;
  }

  /**
   * @param returnValue the returnValue to set
   */
  public void setReturnValue(final String returnValue) {
    this.returnValue = returnValue;
  }

  /**
   * @return the action
   */
  public String getAction() {
    return this.action;
  }

  /**
   * @param action the action to set
   */
  public void setAction(final String action) {
    this.action = action;
  }


  /**
   * @return the screenshotFileName
   */
  public String getScreenshotFileName() {
    return this.screenshotFileName;
  }


  /**
   * @param screenshotFileName the screenshotFileName to set
   */
  public void setScreenshotFileName(final String screenshotFileName) {
    this.screenshotFileName = screenshotFileName;
  }


  /**
   * @return the result
   */
  public String getResult() {
    return result;
  }


  /**
   * @param result the result to set
   */
  public void setResult(String result) {
    this.result = result;
  }


  /**
   * @return the parameter
   */
  public String getParameter() {
    return parameter;
  }


  /**
   * @param parameter the parameter to set
   */
  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  /**
   * @param values to be set..
   */
  public void setValues(final String values) {
    this.values = values;
  }

  /**
   * @return values of the parameters as string.
   */
  public String getValues() {
    return values;
  }


  /**
   * @return the testAcceptanceMessage
   */
  public TestAcceptanceMessage getTestAcceptanceMessage() {
    return testAcceptanceMessage;
  }


  /**
   * @param testAcceptanceMessage the testAcceptanceMessage to set
   */
  public void setTestAcceptanceMessage(TestAcceptanceMessage testAcceptanceMessage) {
    this.testAcceptanceMessage = testAcceptanceMessage;
  }


  /**
   * @return the failMessage
   */
  public String getFailMessage() {
    return failMessage;
  }


  /**
   * @param failMessage the failMessage to set
   */
  public void setFailMessage(String failMessage) {
    this.failMessage = failMessage;
  }

  /**
   * Sets verification result message.
   * 
   * @param tamObj to hold verification result message.
   */
  public void setVerificationResult(final List<TestAcceptanceMessage> tamObj) {
    this.verificationResultMsg = tamObj;
  }

  /**
   * @return result of verification.
   */
  public List<TestAcceptanceMessage> getVerificationResult() {
    return this.verificationResultMsg;
  }
  /**
   * @param description value to be set for action.
   */
  public void setDescription(String description)
  {
    this.description=description;
  }
  /**
   * @return description of action.
   */
  public String getDescription()
  {
    return this.description;
  }
  /**
   * @return action type.
   */
  public String getActionType() {
    return this.actionType;
  }

  /**
   * @param actionType to set type of action.
   */
  public void setActionType(final String actionType) {
    this.actionType = actionType;
  }

}
