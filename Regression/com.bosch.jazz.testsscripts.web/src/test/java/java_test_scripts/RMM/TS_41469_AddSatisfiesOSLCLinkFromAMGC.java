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
public class TS_41469_AddSatisfiesOSLCLinkFromAMGC extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   * @throws AWTException
   */
  @Test
  public void ts_41469_addSatisfiesOSLCLinkFromAMGC() throws InvalidKeyException, AWTException {

    boolean isLinkPresent = false;

    String amServerurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String gcServerurl = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("GC_STREAM_ID");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");
    String requirementID = this.testDataRule.getConfigData("REQUIREMENT_ID");
    String linksText = this.testDataRule.getConfigData("LINKS");

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


    isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(requirementID);

    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (!isLinkPresent) {
      // scroll to perticular Element
      getJazzPageFactory().getAbstractRMMPage().scrollViewToElement(RMMConstants.RMM_ADD_OSLC_LINK);

      // click on quick Navigation
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK);

      Reporter.logPass("Clicked on ADD OSLC LINK");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      // click on Add OSLC link Satisfies
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK_SATISFIES);
      Reporter.logPass("Clicked on ADD SATISFIES OSLC LINK");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      // click on Associate change request in the add oslc link popup window
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST);

      Reporter.logPass("Clicked on Associate Change Request");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      // click on Associate change request in the add oslc link popup window. Select Project area and click OK.
      getJazzPageFactory().getAbstractRMMPage()
          .clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST_SELECTPROJECTAREA_OK);

      Reporter.logPass("Associate Change Request: Project Area is Selected");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      WebElement iFrameDiv = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_DIV);

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewByWebElement(iFrameDiv);

      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      WebElement element = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_ENTER_WORKITEM_TEXTBOX);

      element.sendKeys(workItemID);
      element.sendKeys(Keys.ENTER);

      Reporter.logPass("Associate Change Request: Search for WorkItem ID");


      getJazzPageFactory().getAbstractRMMPage().doubleClick("//option[@value='" + workItemID + "']");

      Reporter.logPass("Associate Change Request: Selected the WorkItem");


      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewToActiveElement();
      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      WebElement iFrameReqFilterDiv = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_FILTER_REQUIREMENT_SWITCHTO_IFRAME_DIV);

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewByWebElement(iFrameReqFilterDiv);

      WebElement filterReq = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_SELECT_FILTER_ARTIFACTS_BY_NAME_ID);

      // Select perticular Requirement
      filterReq.sendKeys(requirementID);
      filterReq.sendKeys(Keys.ENTER);

      Reporter.logPass("ADD SATISFIES OSLC LINK: Filter Requirements by Name or ID");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage()
          .clickElementwithDynamicValue(RMMConstants.RMM_VERIFY_OSLC_LINK_AVAILABILITY, requirementID);

      Reporter.logPass("ADD SATISFIES OSLC LINK: Select the Requirement");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().zoomOutScreen();

      getJazzPageFactory().getAbstractRMMPage()
          .clickElement(RMMConstants.RMM_ADD_OSLC_LINK_SELECT_REQUIREMENT_OK_BUTTON);

      Reporter.logPass("ADD SATISFIES OSLC LINK: Clicked OK Button to create Link");


      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().zoomInScreen();

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewToActiveElement();

      isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(requirementID);

      if (isLinkPresent) {

        getJazzPageFactory().getAbstractRMMPage()
            .clickElementwithDynamicValue(RMMConstants.RMM_VERIFY_OSLC_LINK_AVAILABILITY, requirementID);

        Reporter.logPass("NAVIGATE SATISFIES OSLC LINK: click on Created OSLC link and Navigate to RM Application");


        getJazzPageFactory().getAbstractRMMPage()
            .clickElementwithDynamicValue(RMMConstants.RMM_SPAN_VERIFY_TEXT_AVAILABILITY, linksText);

        Reporter.logPass("NAVIGATE SATISFIES OSLC LINK: Expand LINKS Section");


        boolean isLinkVisible = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementId);

        assertTrue("Link is Visible", isLinkVisible);
        Reporter.logPass("IS OSLC LINK VISIBLE? " + isLinkVisible);

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
