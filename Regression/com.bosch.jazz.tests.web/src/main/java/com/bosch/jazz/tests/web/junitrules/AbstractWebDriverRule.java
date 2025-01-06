/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.tests.web.junitrules;


import org.junit.rules.TestWatcher;

import com.bosch.jazz.automation.web.common.WebDriverCustom;

/**
 * Abstract base class for all JUnit rules that need the selenium WebDriver.
 */
public abstract class AbstractWebDriverRule extends TestWatcher {

  private WebDriverCustom browser;

  /**
   * @return the browser
   */
  public WebDriverCustom getBrowser() {
    return this.browser;
  }

  /**
   * @param browser the browser to set
   */
  public void setBrowser(final WebDriverCustom browser) {
    this.browser = browser;
  }
}