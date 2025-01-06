/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;

/**
 * POJO class for the verification methods for each PageModel.
 */
public class VerificationConfig extends ExcelScriptCommand{

  private String pageModelMethod;
  private String verificationMethod;
  private int rating;
  private String hasParameter;
  private List<AbstractMap.SimpleImmutableEntry<String, String>> outputParameter;
  private String outputStrParameter = "";

  /**
   * @return the hasParameter
   */
  public String getHasParameter() {
    return this.hasParameter;
  }

  /**
   * @return the pageModelMethod
   */
  public String getPageModelMethod() {
    return this.pageModelMethod;
  }
 

  /**
   * @return the verificationMethod
   */
  public String getVerificationMethod() {
    return this.verificationMethod;
  }

  /**
   * @param hasParameter the hasParameter to set
   */
  public void setHasParameter(final String hasParameter) {
    this.hasParameter = hasParameter;
  }
 
  /**
   * @param pageModelMethod the pageModelMethod to set
   */
  public void setPageModelMethod(final String pageModelMethod) {
    this.pageModelMethod = pageModelMethod;
  }

  /**
   * @param verificationMethod the verificationMethod to set
   */
  public void setVerification(final String verificationMethod) {
    this.verificationMethod = verificationMethod;
  }

  
  /**
   * @return the rating
   */
  public int getRating() {
    return rating;
  }

  
  /**
   * @param rating the rating to set
   */
  public void setRating(int rating) {
    this.rating = rating;
  }

  /**
   * @return parameter
   */
  public List<SimpleImmutableEntry<String, String>> getOutputParameter() {
    return this.outputParameter;
  }

  /**
   * @param parameter the parameter to set
   */
  public void setOutputParameter(final List<SimpleImmutableEntry<String, String>> parameter) {
    this.outputParameter = parameter;
  }


  /**
   * @return strParameter
   */
  public String getOutputStrParameter() {
    return outputStrParameter;
  }


  /**
   * @param strParameter the strParameter to set
   */
  public void setOutputStrParameter(String strParameter) {
    this.outputStrParameter = strParameter;
  }

  
}
