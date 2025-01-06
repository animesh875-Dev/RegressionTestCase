/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class TS_41404_AddElobaratesOSLCLinkFromAM extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41404_addElobaratesOSLCLinkFromAM() throws InvalidKeyException {

    String amServerURL = this.testDataRule.getConfigData("AM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String modelElementID = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String streamID = this.testDataRule.getConfigData("AM_STREAM_ID");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), amServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + amServerURL + " repository successfully.");

    // open Element in RMM
    String elementURL = getJazzPageFactory().getAbstractRMMPage().getRepositoryURLForRMMNonGC(modelElementID,
        amServerURL, streamID, projectArea);
    getJazzPageFactory().getAbstractRMMPage().open(elementURL, projectArea, RMMConstants.ADDITIONAL_PARAMS);

    boolean isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(workItemID);
    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (!isLinkPresent) {
      // scroll to perticular Element
      getJazzPageFactory().getAbstractRMMPage().scrollViewToElement(RMMConstants.RMM_ADD_OSLC_LINK);

      // click on quick Navigation
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK);

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      // click on Add OSLC link Satisfies
      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ELOBARATES);

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

        boolean isLinkVisible = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(modelElementID);

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
