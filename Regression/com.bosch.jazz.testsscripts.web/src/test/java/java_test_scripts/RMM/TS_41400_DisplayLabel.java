/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.pagemodel.JazzDashboardPage;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author HLB1KOR
 */
public class TS_41400_DisplayLabel extends AbstractFrameworkTest {

  /**
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_41400_DisplayLabel() throws Throwable {
    String serverurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("AM_STREAM_ID");
    String elementLabel = this.testDataRule.getConfigData("ELEMENT_LABEL");

    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), serverurl);
    Reporter.logPass(getUserId() + " user logged in to the " + serverurl + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");


    String[] attribute = { "Settings", RMMConstants.SHOW_LABEL_MODE_XPATH };
    boolean isEnabled = rmm.checkCMEnable(attribute);
    // assertTrue(isEnabled);

    if (isEnabled) {
      Reporter.logPass(" Show LABLE MODE is enabled  for the project " + projectArea);
      String element_URI = rmm.getRepositoryURLForRMMNonGC(elementId, serverurl, streamId, projectArea);

      // Novigate to Architectecture element.
      JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
      assertNotNull(jazz);
      jazz.navigateToURL(element_URI);

      Reporter.logPass(getUserId() + " user logged in to the " + element_URI + " successfully.");

      getJazzPageFactory().getRMMModelElementPage().refresh();
      getJazzPageFactory().getRMMModelElementPage().waitForSecs(10);

      // validate element label option availability instead of name
      Assert.assertTrue(getJazzPageFactory().getRMMModelElementPage()
          .isProvidedFieldAvailableInWebElement(RMMConstants.DISPLAY_FIELD_AVAILABILITY, RMMConstants.FIELD_LABEL));
      Reporter.logPass("Lable option is Visible in RMM web");

      // Validate element label value
      WebElement value = getJazzPageFactory().getRMMModelElementPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ELEMENT_TYPE_XPATH);
      String element = value.getText();
      Reporter.logPass("element is : " + element);
      Assert.assertTrue(element.contains(elementLabel));

    }
    else {
      Reporter.logPass(" Show LABLE MODE is not enabled  for the project " + projectArea);
      Assert.assertTrue(false);

    }
  }
}
