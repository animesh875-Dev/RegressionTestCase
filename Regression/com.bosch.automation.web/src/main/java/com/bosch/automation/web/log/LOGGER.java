/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.automation.web.log;


/**
 * @author hrt5kor Most logging operations, except configuration, are done through this class.
 */
public class LOGGER {

  private LOGGER() {}

  /**
   * Retrieve a logger named according to the value of the name parameter.
   */
  public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger("Log");
}
