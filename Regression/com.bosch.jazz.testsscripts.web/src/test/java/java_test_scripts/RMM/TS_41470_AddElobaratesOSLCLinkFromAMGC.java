/*
 * Copyright (c) ETAS GmbH 2023. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
public class TS_41470_AddElobaratesOSLCLinkFromAMGC extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41470_addElobaratesOSLCLinkFromAMGC() throws InvalidKeyException {

    boolean isLinkPresent = false;

    String amServerurl = this.testDataRule.getConfigData("AM_SERVER_URL");
    String gcServerurl = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String elementId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamId = this.testDataRule.getConfigData("GC_STREAM_ID");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");

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


    isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(workItemID);

    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (!isLinkPresent) {
      // scroll to perticular Element
      getJazzPageFactory().getAbstractRMMPage().scrollViewToElement(RMMConstants.RMM_ADD_OSLC_LINK);

      // click on quick Navigation
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK);

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      // click on Add OSLC link Satisfies
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ELOBARATES_GC);

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage()
          .switchToFrameViewByNameOrID(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME);

      WebElement element = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath(RMMConstants.RMM_ADD_OSLC_LINK_ENTER_WORKITEM_TEXTBOX);
      element.sendKeys(workItemID);
      element.sendKeys(Keys.ENTER);

      getJazzPageFactory().getAbstractRMMPage().doubleClick("//option[@value='" + workItemID + "']");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewToActiveElement();

      isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(workItemID);

      if (isLinkPresent) {

        getJazzPageFactory().getAbstractRMMPage()
            .clickElementwithDynamicValue(RMMConstants.RMM_VERIFY_OSLC_LINK_AVAILABILITY, workItemID);


        getJazzPageFactory().getAbstractRMMPage()
            .clickElementwithDynamicValue(RMMConstants.RMM_VERIFY_OSLC_LINK_AVAILABILITY, "Links");

        getJazzPageFactory().getAbstractRMMPage()
            .scrollViewToElement(RMMConstants.CCM_LINKS_TAB_SCROLL_TO_ELOBARATES_BY_ARCHITECTURE_ELEMENT);

        boolean isLinkVisible = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementId);

        assertTrue("Link is Visible", isLinkVisible);
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
