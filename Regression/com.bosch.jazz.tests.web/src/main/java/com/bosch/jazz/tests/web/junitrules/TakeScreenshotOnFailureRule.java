package com.bosch.jazz.tests.web.junitrules;

import java.io.File;
import java.io.IOException;

import org.junit.runner.Description;
import org.openqa.selenium.TakesScreenshot;

import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.junitrules.AbstractWebDriverRule;
import com.bosch.jazz.automation.web.reporter.Reporter;

/**
 * Using Reporter logs when a test failed the exception message.
 */
public class TakeScreenshotOnFailureRule extends AbstractWebDriverRule {

  @Override
  protected void failed(final Throwable e, final Description description) {
    if ((getBrowser() == null) || (getBrowser().getWebDriver() == null)) {
      // if there is no web driver only log a failure without a screenshot
      Reporter.logFailure(e);
      return;
    }

    String testContext = description.getTestClass().getSimpleName() + "-" + description.getMethodName();
    System.out.println(testContext);
    testContext = testContext.replaceAll("[^\\w.-]", "_"); // make sure the testContext is a valid file name by
                                                           // replacing certain characters
    String fileName = ScreenShotUtil.makeScreenShot(Reporter.getResultsReportDirLocation(), testContext,
        (TakesScreenshot) getBrowser().getWebDriver());
    System.out.println(fileName);
    try {
      Reporter.logFailureWithScreenshot(e, new File(fileName).getName());
    }
    catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}