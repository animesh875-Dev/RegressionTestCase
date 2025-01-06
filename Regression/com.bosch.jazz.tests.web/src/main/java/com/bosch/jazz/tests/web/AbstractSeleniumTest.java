/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.tests.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.bosch.jazz.automation.web.common.DriverSetup;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.JazzPageFactory;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.jazz.tests.web.junitrules.ReportStartAndEndTestRule;
import com.bosch.jazz.tests.web.junitrules.TakeScreenshotOnFailureRule;
import com.bosch.jazz.tests.web.junitrules.TestClass;
import com.bosch.jazz.utils.tests.AbstractUserCredentialsRequiringTest;


/**
 * Base test class that opens a new browser window per test class execution (that means one new window for all tests of
 * a single test class that inherits from this one). After all tests closes the window. If {@link #getStartURL(String)}
 * provides a URL this URL is opened in the browser before each single test is being executed. If no URL is given then
 * nothing will be done before each test.
 */
public abstract class AbstractSeleniumTest extends AbstractUserCredentialsRequiringTest {

  /**
   * Rule holding the name of test method that is is going to be or was executed at last.
   */
  @Rule
  public TestName testNameRule = new TestName();

  /**
   * Rule holding the name of the test class that is is going to be or was executed at last.
   */
  @ClassRule
  public static TestClass testClassRule = new TestClass();

  /**
   * The selenium web driver to use for the tests
   */
  protected static WebDriver driver;

  /**
   * A custom utility class containing a lot convenience methods for using the {@link #driver} in an efficient way.
   */
  protected static WebDriverCustom browser;
  private static JazzPageFactory jazzPageFactory;

  private static DriverSetup driverSetup;

  private static File dlFolder;

  /**
   * For logging the failed tests to {@link Reporter} and making a screenshot on test failure.
   */
  private final TakeScreenshotOnFailureRule takeScreenshotOnFailureRule = new TakeScreenshotOnFailureRule();

  /**
   * Rule to make sure that in case of test failures a screenshot is taken and after that all other required actions are
   * been done.
   */
  @Rule
  public TestRule takeScreenshotOnFailureAndLaterActionsRule = getTakeScreenshotOnFailureAndLaterActionsRule();


  /**
   * @return a rule that makes sure that in case a test is failing first a screenshot is being taken and then the other
   *         required actions (e.g. like logging out) are done. In case no test case is failing then still all rules are
   *         executed in the specified order.
   *         <p>
   *         In case more than just e.g. logging out must be done then method
   *         {@link #getRuleToCallAfterScreenshotTakingRule()} must be overwritten and the required rules be added.
   */
  private RuleChain getTakeScreenshotOnFailureAndLaterActionsRule() {
    return RuleChain.outerRule(getRuleToCallAfterScreenshotTakingRule()).around(this.takeScreenshotOnFailureRule)
        .around(new ReportStartAndEndTestRule());
  }

  /**
   * @return a {@link TestWatcher} (can also be a {@link RuleChain}) that will be called after
   *         {@link TakeScreenshotOnFailureRule} by defining a {@link RuleChain} where the screenshot taking rule is
   *         before the one returned by this method.
   */
  protected TestRule getRuleToCallAfterScreenshotTakingRule() {
    return new TestWatcher() {
      // just a dummy to not be forced to deal with null return values
    };
  }

  /**
   * Configured the selenium driver and finally opens the browser
   *
   * @throws java.lang.Exception in case of problems
   */
  @BeforeClass
  public static void openBrowser() throws Exception {
    try {
      driverSetup = new DriverSetup(testClassRule.getTestClass());
      Reporter.initializeReporter(driverSetup.getBrowserToRun().toString());
      Reporter.startTestClass(testClassRule.getTestClassName());
      Reporter.startTest("Open browser", null);
      dlFolder = java.nio.file.Files.createTempDirectory("automatedBrowserDownloads").toFile();
      driver = driverSetup.initializeWebDriver(dlFolder);
      if (driverSetup.getBrowserMobProxy() != null) {
        driverSetup.getBrowserMobProxy().newHar();
      }
      browser = new WebDriverCustom(driver, driverSetup);
      jazzPageFactory = new JazzPageFactory(browser);
      Reporter.logPass(driverSetup + " browser opened successfully.");
    }
    catch (Exception e) {
      Reporter.logError(e.getMessage());
      throw e;
    }
  }

  /**
   * Before each test opens in the browser the page defined by {@link #getStartURL(String)}. If this method returns
   * null, then nothing wil be done here.
   *
   * @throws Exception in case of exceptions
   */
  @Before
  public void beforeEachTest() throws Exception {
    this.takeScreenshotOnFailureRule.setBrowser(browser);
    InputStream additionalSeleniumLocatorProperties = getAdditionalSeleniumLocatorProperties();
    if (additionalSeleniumLocatorProperties != null) {
      AbstractSeleniumTest.browser.addSeleniumLocatorProperties(additionalSeleniumLocatorProperties);
    }
    browser.closeAllTabsExceptOne();
    browser.waitForPageLoaded();

    if (!shallOpenStartUrlBeforeEachTest()) {
      return;
    }
    openStartUrl();
  }

  /**
   * Opens the start URL given by {@link #getStartURL(String)}. If no (empty or null String) start URL provided nothing
   * is done.
   */
  protected void openStartUrl() {
    if (!StringUtils.isEmpty(getStartURL(this.testNameRule.getMethodName()))) {
      String browserName = getBrowserNameFromDriver();
      Reporter
          .logInfo("Opening URL " + getStartURL(this.testNameRule.getMethodName()) + "in " + browserName + " browser.");
      AbstractSeleniumTest.driver.get(getStartURL(this.testNameRule.getMethodName()));
      AbstractSeleniumTest.browser.waitForPageLoaded();
      Reporter.logPass("URL loaded successfully.");
    }
  }

  private static String getBrowserNameFromDriver() {
    if (driver instanceof RemoteWebDriver) {
      Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
      String browserName = cap.getBrowserName();
      return browserName;
    }
    return "UNKNOWN_BROWSER";
  }


  /**
   * Returns the URL that shall be opened automatically before each single test case.
   * <p>
   * ATTENTION: If subclasses of {@link #AbstractSeleniumTest()} need to do something (e.g. login, data preparation)
   * before the start URL is opened then {@link #shallOpenStartUrlBeforeEachTest()} must return false and opening the
   * start URL must be invoked in the sub class manually via a call to {@link #openStartUrl()}.
   *
   * @param testName the name of the test method that will be executed after opening the url returned by this method,
   *          never null
   * @return the URL that is opened before each test case is executed, if null then no URL will be opened at all.
   */
  public abstract String getStartURL(String testName);

  /**
   * @return the current url of the browser.
   */
  public String getCurrentURL() {
    return driver.getCurrentUrl();
  }

  /**
   * Closes the browser and ends the report using the {@link Reporter} class.
   *
   * @throws java.lang.Exception in case of problems
   */
  @AfterClass
  public static void closeBrowser() throws Exception {
    try {
      Reporter.startTest("Close browser.", null);
      FileUtils.deleteQuietly(dlFolder); // clean the downloads folder
      if (driver == null) {
        Reporter.logError("ERROR: Selenium driver was not initialized properly, therefore cannot close any browser!");
      }
      else {
        File harFilesFolder = new File(FileUtils.getTempDirectoryPath(), "harFiles");
        harFilesFolder.mkdirs();
        File harFile = new File(harFilesFolder, testClassRule.getTestClassName());
        FileUtils.deleteQuietly(harFile);
        if (driverSetup.getBrowserMobProxy() != null) {
          System.out.println("Writing HAR file: " + harFile.getAbsolutePath());
          driverSetup.getBrowserMobProxy().getHar().writeTo(harFile);
          System.out.println("Stopping proxy on port: " + driverSetup.getBrowserMobProxy().getPort());
          driverSetup.getBrowserMobProxy().stop();
        }
        String browserNameFromDriver = getBrowserNameFromDriver();
        driver.quit();
        Reporter.logPass(browserNameFromDriver + " browser closed successfully.");
      }
    }
    catch (Exception e) {
      Reporter.logError(e.getMessage());
      throw e;
    }
    finally {
      Reporter.close();
    }
  }


  /**
   * @return the name of the test method that is about to run, is currently running or was running as last, returns
   *         "UNKNOWN" if this method is called too early and before any test method was executed or is known to be
   *         executed.
   */
  protected String getTestMethodName() {
    String methodName = this.testNameRule.getMethodName();
    if (methodName == null) {
      return "UNKNOWN";
    }
    return methodName;
  }

  /**
   * @return input stream to a properties file that contains selenium locator properties that shall be usable by the
   *         {@link WebDriverCustom} instance (@see {@link #browser}) of this test class.
   * @throws IOException in case of problems returning the input stream
   */
  protected InputStream getAdditionalSeleniumLocatorProperties() throws IOException {
    return null;
  }

  /**
   * @return the page factory instance used for creating the page objects that represent certain web pages.
   */
  protected final JazzPageFactory getJazzPageFactory() {
    return jazzPageFactory;
  }

  /**
   * @return true to open the URL provided by {@link #getStartURL(String)} before each test case is executed, false to
   *         not execute it.
   *         <p>
   *         ATTENTION: If subclasses of {@link #AbstractSeleniumTest()} need to do something before the start URL is
   *         opened then {@link #shallOpenStartUrlBeforeEachTest()} must return false and opening the start URL must be
   *         invoked in the sub class manually via a call to {@link #openStartUrl()}.
   */
  protected boolean shallOpenStartUrlBeforeEachTest() {
    return true;
  }

  /**
   * @return the {@link WebDriverCustom} instance that shall be used for this test.
   */
  protected WebDriverCustom getWebDriverCustom() {
    return AbstractSeleniumTest.browser;
  }
}
