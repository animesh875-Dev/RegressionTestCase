/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.tests.web;

import org.junit.After;
import org.junit.Before;
import org.junit.rules.TestRule;

import com.bosch.jazz.automation.web.pagemodel.JazzLoginPage;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.jazz.tests.web.junitrules.LogOutRule;
import com.bosch.jazz.utils.rest.JazzHttpUtils;


/**
 * Base test class that logs in and logs out to/from a defined repository before/after each test case. The repository is
 * defined by {@link #getRepositoryURL(String)} that must be implemented by each test class. After logging in the
 * repository URL is saved in field {@link #loggedInRepository}.<br>
 * The user id and password used for logging in is defined by {@link #getUserId()} and {@link #getUserPassword()}. If
 * some custom user credentials need to be used per test case these methods shall be overwritten.
 */
public abstract class AbstractLoginLogoutSeleniumTest extends AbstractSeleniumTest {

  private LogOutRule logOutRule;

  /**
   * The URL of the currently logged-in repository, without a trailing slash. Can never be null when invoked from code
   * inside a test.<br>
   * When accessed as part of a JUnit rule though it might be null before the first login.
   */
  protected String loggedInRepository;


  /**
   * Logs in to the repository specified by {@link #getRepositoryURL(String)}. The login credentials are taken from
   * {@link #getUserId()} and {@link #getUserPassword()}. If successfull opens the provided start URL.
   */
  @Before
  public void loginAndOpenStartUrl() {
    this.loggedInRepository = getRepositoryURL(getTestMethodName());
    this.loggedInRepository = JazzHttpUtils.removeTrailingSlash(this.loggedInRepository);
    this.logOutRule.setBrowser(browser);
    this.logOutRule.setRepositoryURL(this.loggedInRepository);
    JazzLoginPage jazzLoginPage = getJazzPageFactory().getLoginPage();
    jazzLoginPage.loginWithGivenPassword(getUserId(), getUserPassword(), this.loggedInRepository);
    String message = getUserId() + " user logged into " + this.loggedInRepository + " the repository sucessfully.";
    System.out.println(message);
    Reporter.logInfo(message);
    openStartUrl();
  }

  /**
   * Logs out from the repository specified by {@link #loggedInRepository}.
   */
  @After
  public void logout() {
    /**
     * the log out cannot be realized here. Reason is the following:<br>
     * In order to automatically make a screenshot when a test case fails the TestRule class TakeScreenshotOnFailureRule
     * is used. But this class is called by JUnit after all @After methods and that means the screenshot would be taken
     * after this method invocation and then most likely the error cause is no more visible. Therefore the logout
     * functionality is realized as another rule (LogOutRule) that is called after the screenshot taking rule.
     */
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected TestRule getRuleToCallAfterScreenshotTakingRule() {
    this.logOutRule = new LogOutRule();
    return this.logOutRule;
  }

  /**
   * Returns the jazz application repository url to use for login and logout. Since this method is called once before
   * each executed test, it can be overriden if required to use different repositories per test. If
   * {@link #getStartURL(String)} provides an URL it is opened automatically after login.
   * <p>
   * <b>ATTENTION:</b> Provide only the repository URL, everything else will lead to failure. E.g. only URLs like
   * https://server.com/ccm or https://server.com/rs are allowed. A trailing slash is ok but nothing in addition to
   * that.
   *
   * @param testName the name of the test method (never null) that will be invoked automatically some time after a call
   *          to this method. That means the repository URL returned here will be used for the given test and as such
   *          can be customized if required per test case.
   * @return the repository url to use for login,logout and all other web activities. Must not end with a slash. Will be
   *         called before each test case of this class is being executed.
   */
  protected abstract String getRepositoryURL(String testName);

  /**
   * Returns the URL that shall be opened automatically before each single test but after login.
   */
  @Override
  public String getStartURL(final String testName) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean shallOpenStartUrlBeforeEachTest() {
    // need to put a false here otherwise start URL will be opened even before the login attempt.
    return false;
  }
}
