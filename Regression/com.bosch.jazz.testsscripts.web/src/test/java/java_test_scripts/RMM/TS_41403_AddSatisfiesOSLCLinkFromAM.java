/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.security.InvalidKeyException;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author PZP1KOR
 */
public class TS_41403_AddSatisfiesOSLCLinkFromAM extends AbstractFrameworkTest {


  /**
   * @throws InvalidKeyException
   * @throws AWTException
   * @throws InterruptedException
   */
  @Test
  public void ts_41403_addSatisfiesOSLCLinkFromAM() throws InvalidKeyException, AWTException, InterruptedException {

    boolean isLinkPresent = false;

    String amServerURL = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String modelElementID = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamID = this.testDataRule.getConfigData("AM_STREAM_ID");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");
    String requirementID = this.testDataRule.getConfigData("REQUIREMENT_ID");
    String linksText = this.testDataRule.getConfigData("LINKS");
    String closeText = this.testDataRule.getConfigData("CLOSE");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), amServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + amServerURL + " repository successfully.");

    // open Element in RMM
    String elementURL = getJazzPageFactory().getAbstractRMMPage().getRepositoryURLForRMMNonGC(modelElementID,
        amServerURL, streamID, projectArea);
    getJazzPageFactory().getAbstractRMMPage().open(elementURL, projectArea, RMMConstants.ADDITIONAL_PARAMS);
    Reporter.logPass("Navigated to Project Area Successfully");

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

      WebElement element = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_ENTER_WORKITEM_TEXTBOX);

      while (element == null) {
        // Wait for 20s for PageLoad
        getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);

        element = getJazzPageFactory().getAbstractRMMPage()
            .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_ENTER_WORKITEM_TEXTBOX);
      }
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

      while (filterReq == null) {
        // Wait for 20s for PageLoad
        getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);

        filterReq = getJazzPageFactory().getAbstractRMMPage()
            .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_SELECT_FILTER_ARTIFACTS_BY_NAME_ID);
      }

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


        boolean isLinkVisible = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(modelElementID);

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
