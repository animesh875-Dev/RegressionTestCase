/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.tests.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;


/**
 * Abstract base test class that loads properties from file {@link #JRS_SCONFIG_PROPERTIES} before each test executes.
 */
public abstract class AbstractJRSTestCase extends AbstractLoginLogoutSeleniumTest {

  /**
   * Predefined JRS specific property file.
   */
  public static final String JRS_SCONFIG_PROPERTIES = "JRSconfig.properties";

  /**
   * Creating a Properties object to load user defined property file
   */
  protected Properties prop = new Properties();

  /**
   * Creating a InputStrean object to get the property file from the defined location.
   */
  protected InputStream input = null;

  /**
   * Below method is used to load JRS specific property file {@link #JRS_SCONFIG_PROPERTIES}.
   *
   * @throws IOException
   */
  @SuppressWarnings("javadoc")
  @Before
  public void loadPropertyFile() throws IOException {
    this.input = getClass().getClassLoader().getResourceAsStream(JRS_SCONFIG_PROPERTIES);
    if (this.input != null) {
      this.prop.load(this.input);
    }

  }

}
