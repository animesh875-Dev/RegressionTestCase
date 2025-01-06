/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;

/**
 * @author HCM6KOR POJO class for the Excel WebTest reader.
 */
public class ExcelScriptCommand {

  private String sheetName;
  private int lineNumber;
  private String action;
  private String actionType;
  private List<AbstractMap.SimpleImmutableEntry<String, String>> parameter;
  private List<AbstractMap.SimpleImmutableEntry<String, String>> verificationParameter;
  private String strParameter = "";
  private String description;

  /**
   * @return sheetName
   */
  public String getSheetName() {
    return this.sheetName;
  }

  /**
   * @param sheetName the sheetName to set
   */
  public void setSheetName(final String sheetName) {
    this.sheetName = sheetName;
  }

  /**
   * @return the lineNumber
   */
  public int getLineNumber() {
    return this.lineNumber;
  }

  /**
   * @param i the lineNumber to set
   */
  public void setLineNumber(final int i) {
    this.lineNumber = i;
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

  /**
   * @return parameter
   */
  public List<SimpleImmutableEntry<String, String>> getParameter() {
    return this.parameter;
  }

  /**
   * @param parameter the parameter to set
   */
  public void setParameter(final List<SimpleImmutableEntry<String, String>> parameter) {
    this.parameter = parameter;
  }


  /**
   * @return strParameter
   */
  public String getStrParameter() {
    return strParameter;
  }


  /**
   * @param strParameter the strParameter to set
   */
  public void setStrParameter(String strParameter) {
    this.strParameter = strParameter;
  }

  /**
   * @param description value to be set for action.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return description of action.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return the verificationParameter
   */
  public List<AbstractMap.SimpleImmutableEntry<String, String>> getVerificationParameter() {
    return verificationParameter;
  }

  /**
   * @param verificationParameter the verificationParameter to set
   */
  public void setVerificationParameter(List<AbstractMap.SimpleImmutableEntry<String, String>> verificationParameter) {
    this.verificationParameter = verificationParameter;
  }

}
