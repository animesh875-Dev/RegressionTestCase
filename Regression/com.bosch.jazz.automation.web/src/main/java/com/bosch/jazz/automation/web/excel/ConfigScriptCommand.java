/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

/**
 * @author CIK5KOR POJO class for Config reader.
 */
public class ConfigScriptCommand {

  private String sheetName;
  private int lineNumber;
  private String property;
  private String value;


  /**
   * @return the sheetName
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
   * @return the property
   */
  public String getProperty() {
    return property;
  }


  /**
   * @param property the property to set
   */
  public void setProperty(String property) {
    this.property = property;
  }


  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }


  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }


}
