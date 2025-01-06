/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.tests.web.junitrules;

import org.junit.runner.Description;

import com.bosch.jazz.automation.web.junitrules.AbstractWebDriverRule;
import com.bosch.jazz.automation.web.reporter.Reporter;


/**
 * TestRule that logs from the specified repository when the method {@link #finished(Description)} is called.
 */
public class LogOutRule extends AbstractWebDriverRule {

  private String repositoryURL;

  @Override
  protected void finished(final Description description) {
    if ((getBrowser() == null) || (getBrowser().getWebDriver() == null)) {
      return;
    }
    Reporter.logInfo("Logging out from: " + getRepositoryURL());
    getBrowser().getWebDriver().get(getRepositoryURL() + "/auth/logout");
    getBrowser().waitForPageLoaded();
    Reporter.logPass("User successfully logged out from: " + getRepositoryURL());
  }

  private String getRepositoryURL() {
    return this.repositoryURL;
  }

  /**
   * @param repositoryURL the URL of the repository from which to log out
   */
  public void setRepositoryURL(final String repositoryURL) {
    this.repositoryURL = repositoryURL;
  }

}
