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
public class TS_41390_validateLinkValidityStatus extends AbstractFrameworkTest {

  /**
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_41390_validateLinkValidityStatus() throws Throwable {
    String amServerurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String gcServerurl = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("GC_STREAM_ID");


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), amServerurl);
    Reporter.logPass(getUserId() + " user logged in to the " + amServerurl + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);
    String[] attribute = { "Settings", RMMConstants.SHOW_LINK_VALIDITY_XPATH };
    boolean isEnabled = rmm.checkCMEnable(attribute);

    if (isEnabled) {
      Reporter.logPass(" Show Link Validity is enabled  for the project " + projectArea);
      String element_URI = rmm.getRepositoryURLForRMMGC(elementId, amServerurl, streamId, gcServerurl, projectArea);

      // Novigate to Architectecture element.
      JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
      assertNotNull(jazz);
      jazz.navigateToURL(element_URI);

      Reporter.logPass(getUserId() + " user logged in to the " + element_URI + " successfully.");

      getJazzPageFactory().getRMMModelElementPage().refresh();
      getJazzPageFactory().getRMMModelElementPage().waitForSecs(5);

      // validate Link validity option availability

      WebElement elelementFound =
          getJazzPageFactory().getRMMModelElementPage().getModelElementIfFoundByXpath(RMMConstants.LINK_VALIDITY_XPATH);
      Assert.assertTrue(elelementFound != null);
      Reporter.logPass(" Link validity status visible in architectecture element Links");

    }
    else {
      Reporter.logPass(" Show Link Validity option is not enabled  for the project " + projectArea);
      Assert.assertTrue(false);

    }


  }
}
