/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;

import org.junit.Test;

import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author PZP1KOR
 */
public class TS_41391_AddValidatedByOSLCLinkFromQM extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41391_addValidatedByOSLCLinkFromQM() throws InvalidKeyException {

    boolean isLinkPresent = false;

    String qmServerURL = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RMM_QM_PROJECT_AREA");
    String testcaseID = this.testDataRule.getConfigData("TESTCASE_ID");
    String amComponentName = this.testDataRule.getConfigData("AM_COMPONENT_NAME");
    String elementResourceId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String amProjectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");
    String addNewLinks = this.testDataRule.getConfigData("ADD_NEW_LINKS");
    String savebutton = this.testDataRule.getConfigData("SAVE_BUTTON");


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), qmServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + qmServerURL + " repository successfully.");

    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    getJazzPageFactory().getAbstractRMMPage().quickSearchInRM(testcaseID);
    Reporter.logPass(testcaseID + ": Testcase opened successfully.");

    // Open searched artifact
    getJazzPageFactory().getAbstractRMMPage()
        .clickElementwithDynamicValue(RMMConstants.QM_SELECT_ARCHITECTURE_ELEMENT_LINK, testcaseID);
    Reporter.logPass("Open '" + testcaseID + "' module found in the quick search");

    getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);

    Reporter.logPass("Click Architecture Element Links");
    getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.ELEMENT_WITH_ANCHOR_TAG_TITLE,
        linkType);

    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

    isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementResourceId);
    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (!isLinkPresent) {
      Reporter.logPass("Click on ADD NEW LINKS");

      getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.ELEMENT_WITH_ANCHOR_TAG_TITLE,
          addNewLinks);

      Reporter.logPass("Expand Project area Dropdown");
      getJazzPageFactory().getAbstractRMMPage().clickElement("//select[@id='selectServiceProvider']");

      Reporter.logPass("Select Project Area");

      getJazzPageFactory().getAbstractRMMPage()
          .clickElementwithDynamicValue(RMMConstants.RMM_OPTION_VERIFY_TEXT_AVAILIBILITY, amProjectArea);

      Reporter.logPass("Select Project Area: OK Button");
      getJazzPageFactory().getAbstractRMMPage().clickElement("//button[@tabindex=0 and @class='j-button-primary']");

      getJazzPageFactory().getAbstractRMMPage().waitForSecs(30);

      getJazzPageFactory().getAbstractRMMPage().selectModelElement(
          RMMConstants.QM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME, amComponentName);

      Reporter.logPass("Link Created Succsessfully");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewToActiveElement();

      Reporter.logPass("Click on SAVE Button");

      getJazzPageFactory().getAbstractRMMPage().clickOnButtons(savebutton);

      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      boolean linkVisibility = verifyLinkNavigationToAMFromQM(elementResourceId, testcaseID);
      Reporter.logPass("Check if OSLC Link already Present?: " + linkVisibility);

      assertTrue(linkVisibility);
    }
    else {
      Reporter.logPass("Link already exists for the Test Case");
    }


  }

  /**
   * @param elementResourceId
   * @param testcaseID
   * @return
   */
  private boolean verifyLinkNavigationToAMFromQM(final String elementResourceId, final String testcaseID) {
    getJazzPageFactory().getAbstractRMMPage().switchToFrameViewToActiveElement();

    Reporter.logPass("Click Created Link and Navigate");
    getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.QM_NAVIGATE_OSLC_LINK,
        elementResourceId);

    // Wait for 20s for PageLoad
    getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);

    return getJazzPageFactory().getAbstractRMMPage().isOSLCLinkVisibleinWeb(testcaseID);
  }

}
