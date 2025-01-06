/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package common;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.bosch.jazz.automation.web.common.DriverSetup;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestDataRuleRMM;
import com.bosch.jazz.automation.web.junitrules.ReportStartAndEndTestRule;
import com.bosch.jazz.automation.web.junitrules.TakeScreenshotOnFailureRule;
import com.bosch.jazz.automation.web.junitrules.TestClass;
import com.bosch.jazz.automation.web.pagemodel.JazzPageFactory;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.jazz.utils.tests.AbstractUserCredentialsRequiringTest;

/**
 * @author PZP1KOR
 */
public class AbstractFrameworkTestRMM extends AbstractUserCredentialsRequiringTest {

  /**
   * Rule holding the configuration data required for the test method.
   */
  @Rule
  public TestDataRuleRMM testDataRule = new TestDataRuleRMM();

  /**
   * Rule holding the name of the test class that is is going to be or was executed at last.
   */
  @ClassRule
  public static TestClass testClassRule = new TestClass();
  /**
   *
   */
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

  private static DriverSetup driverSetup;
  private static JazzPageFactory jazzPageFactory;
  private static File dlFolder;
  /** The WebDriver used for testing */
  protected static RemoteWebDriver driver;

  /**
   * Runs at class level before executing all test cases and initialze reporter for the class.
   */
  @BeforeClass
  public static void initiateReporter() {
    driverSetup = new DriverSetup(AbstractFrameworkTest.class);
    Reporter.initializeReporter(driverSetup.getBrowserToRun().toString());
    Reporter.startTestClass(testClassRule.getTestClassName());
  }

  /**
   * Configures the selenium driver and opens the browser
   *
   * @throws java.lang.Exception in case of problems
   */
  @Before
  public void openBrowser() throws Exception {
    dlFolder = java.nio.file.Files.createTempDirectory("automatedBrowserDownloads").toFile();
    driver = (RemoteWebDriver) driverSetup.initializeWebDriver(dlFolder);
    if (driverSetup.getBrowserMobProxy() != null) {
      driverSetup.getBrowserMobProxy().newHar();
    }
    WebDriverCustom browser = new WebDriverCustom(driver, driverSetup);
    jazzPageFactory = new JazzPageFactory(browser);
  }

//  /**
//   * Closes the browser
//   *
//   * @throws java.lang.Exception in case of problems
//   */
//  @After
//  public void closeBrowser() throws Exception {
//    FileUtils.deleteQuietly(dlFolder); // clean the downloads folder
//    if (driver != null) {
//      driver.quit();
//    }
//    // driverSetup.closeBrowserTasksAndDeleteFolder();
//  }

  /**
   * Runs at class level after all the test cases execution completed, closes the reporter.
   *
   * @throws IOException
   */
  @AfterClass
  public static void closReporter() throws IOException {
    Reporter.close();
  }

  /**
   * @return the page factory instance used for creating the page objects that represent certain web pages.
   */
  protected final JazzPageFactory getJazzPageFactory() {
    return AbstractFrameworkTestRMM.jazzPageFactory;
  }


}
