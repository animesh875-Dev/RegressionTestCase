/*
 * Copyright (c) ETAS GmbH 2023. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.security.InvalidKeyException;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 */
public class TS_41471_AddValidatedByOSLCLinkFromAMGC extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   * @throws AWTException
   */
  @Test
  public void ts_41471_addValidatedByOSLCLinkFromAMGC() throws InvalidKeyException, AWTException {

    boolean isLinkPresent = false;

    String amServerurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String gcServerurl = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("GC_STREAM_ID");
    String testcaseID = this.testDataRule.getConfigData("TESTCASE_ID");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), amServerurl);
    Reporter.logPass(getUserId() + " user logged in to the " + amServerurl + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);

    String element_URI = rmm.getRepositoryURLForRMMGC(elementId, amServerurl, streamId, gcServerurl, projectArea);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().navigateToURL(element_URI);

    isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(testcaseID);

    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (!isLinkPresent) {
      // click on quick Navigation
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK);

      Reporter.logPass("Clicked on ADD OSLC LINK");
      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      // click on Add OSLC link validated by
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK_VALIDATED_BY_GC);
      Reporter.logPass("Clicked on ADD VALIDATED BY OSLC LINK");


      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage()
          .switchToFrameViewByNameOrID(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME);

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);

      WebElement filterTestcase = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_FIND_TESTCASE_GC);

      while (filterTestcase == null) {
        // Wait for 20s for PageLoad
        getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);

        filterTestcase = getJazzPageFactory().getAbstractRMMPage()
            .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_FIND_TESTCASE_GC);
      }

      filterTestcase.clear();
      filterTestcase.sendKeys(testcaseID);
      filterTestcase.sendKeys(Keys.ENTER);
      Reporter.logPass("Filtered Testcase" + testcaseID);

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_SELECT_TESTCASES).click();

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().zoomOutScreen();

      // click on ok BUTTON after selecting testcase.
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK_SELECT_TESTCASE_OK_BUTTON);

      Reporter.logPass("Selected Testcase and Created Link");


      getJazzPageFactory().getAbstractRMMPage().zoomInScreen();

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewToActiveElement();

      isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(testcaseID);

      Reporter.logPass("Created Link is Visible?: " + isLinkPresent);

      if (isLinkPresent) {

        getJazzPageFactory().getAbstractRMMPage()
            .clickElementwithDynamicValue(RMMConstants.RMM_VERIFY_OSLC_LINK_AVAILABILITY, testcaseID);

        Reporter.logPass("Click onCreated Link and check Navigation");

        getJazzPageFactory().getAbstractRMMPage()
            .clickElementwithDynamicValue(RMMConstants.RMM_SPAN_VERIFY_TEXT_AVAILABILITY, linkType);

        Reporter.logPass("Expand Architecture Element Links To see Link Visibility");


        isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementId);

        Reporter.logPass("Is Link Present under Architecture Element Links Section: " + isLinkPresent);
        assertTrue("Link is Visible", isLinkPresent);

      }
      else {
        Reporter.logPass("Link Creation is Successfull, But link is not Visible");
        assertFalse("Link is not Vsible", isLinkPresent);
      }
    }
    else {
      Reporter.logPass("Link already Exists, so skipping Link Creation");
      assertTrue("Link already Exists", isLinkPresent);
    }
  }

}
