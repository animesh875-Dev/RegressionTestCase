/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.RMM;

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
public class TS_41393_AddSatisfiesOSLCLinkFromRM extends AbstractFrameworkTest {

  /**
   *
   */
  public static int countOfWidjetReOpen = 0;

  /**
   * @throws InvalidKeyException
   * @throws AWTException
   */
  @Test
  public void ts_41393_addSatisfiesOSLCLinkFromRM() throws InvalidKeyException, AWTException {

    boolean isLinkPresent = false;

    String rmServerURL = this.testDataRule.getConfigData("RM_SERVER_URL");
    String amProjectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String projectArea = this.testDataRule.getConfigData("RMM_RM_PROJECT_AREA");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID");
    String viewname = this.testDataRule.getConfigData("VIEW_NAME");
    String requirementID = this.testDataRule.getConfigData("REQUIREMENT_ID");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");
    String amComponentName = this.testDataRule.getConfigData("AM_COMPONENT_NAME");
    String elementResourceId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");
    String linksText = this.testDataRule.getConfigData("LINKS");
    String closeText = this.testDataRule.getConfigData("CLOSE");


//Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().SSOCheckloginWithGivenPassword(getUserId(), getUserPassword(), rmServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + rmServerURL + " repository successfully.");

    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Quick search module
    getJazzPageFactory().getAbstractRMMPage().quickSearchInRM(moduleId);
    Reporter.logPass(moduleId + ": module opened successfully.");

    // Open searched artifact
    getJazzPageFactory().getAbstractRMMPage().doubleClickWithDynomicValue(RMMConstants.RM_QUICK_VIEW_OPEN_REQUIREMENT,
        moduleId);
    Reporter.logPass("Open '" + moduleId + "' module found in the project area.");
    // Select particular view.
    getJazzPageFactory().getRMArtifactPage().searchView(viewname);
    Reporter.logPass(viewname + ": view searched.");
    // From View Options drop down Select Edit Attribute from View… option.

    getJazzPageFactory().getAbstractRMMPage()
        .clickElementwithDynamicValue(RMMConstants.RMM_SPAN_VERIFY_TEXT_AVAILABILITY, viewname);
    Reporter.logPass(viewname + ": is Opened");

    // open Requirement
    getJazzPageFactory().getAbstractRMMPage()
        .clickElementwithDynamicValue(RMMConstants.RMM_VERIFY_OSLC_LINK_AVAILABILITY, requirementID);
    Reporter.logPass("Click on Requirement with ID: " + requirementID);

    // Expand Links Tab
    getJazzPageFactory().getAbstractRMMPage()
        .clickElementwithDynamicValue(RMMConstants.RMM_SPAN_VERIFY_TEXT_AVAILABILITY, linksText);
    Reporter.logPass("Expand Links Section");

    // close information popup in Links Section
//    getJazzPageFactory().getAbstractRMMPage()
//        .clickElementwithDynamicValue(RMMConstants.RM_CLOSE_INOFORMATION_IN_LINKS_SECTION, closeText);
//    Reporter.logPass("Close Information Popup in Links Section");


    isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementResourceId);
    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (!isLinkPresent) {

      getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.ADD_LINK_TO_ARTIFACT_RM);
      Reporter.logPass("Clicked on ADD LINK TO ARTIFACT");

      getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.RMM_TD_TEXT_AVAILABILITY,
          linkType);

      Reporter.logPass("Project Area is Selected");
      selectRMMProjectArea(workItemID, amProjectArea, linkType);

      Reporter.logPass("Select Model Element");
      getJazzPageFactory().getAbstractRMMPage().zoomOutScreen();

      getJazzPageFactory().getAbstractRMMPage().selectModelElement(
          RMMConstants.RM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME, amComponentName);
      Reporter.logPass("Link Created Succsessfully");

      boolean linkVisibility =
          getJazzPageFactory().getAbstractRMMPage().verifyLinkNavigationToAM(elementResourceId, requirementID);
      Reporter.logPass("IS OSLC LINK VISIBLE? " + linkVisibility);

      getJazzPageFactory().getAbstractRMMPage().zoomInScreen();

      assertTrue(linkVisibility);

    }
    else {
      Reporter.logPass("OSLC Link already Exists");
    }
  }

  private void associateChangeRequestInRM(final String workItemID) {

// click on Associate change request in the add oslc link popup window
    getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST);

    Reporter.logPass("Click Associate Change Request");

// Wait for 20s for PageLoad
    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

// click on Associate change request in the add oslc link popup window. Select Project area and click OK.
    getJazzPageFactory().getAbstractRMMPage()
        .clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST_SELECTPROJECTAREA_OK);
    Reporter.logPass("Associate Change Request: Project Area is Selected");


// Wait for 20s for PageLoad
    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

    getJazzPageFactory().getAbstractRMMPage()
        .switchToFrameViewByNameOrID(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME);

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
  }

  private void selectRMMProjectArea(final String workItemID, final String amProjectArea, final String linkType) {
    Reporter.logPass("Select RMM Project Area");

    String rmWidjetId = getRMWidgetId();
    WebElement projectAreaSelect = getJazzPageFactory().getAbstractRMMPage()
        .getModelElementIfFoundByXpath("// div[@class='projectArea']//child::div[@id='" + rmWidjetId +
            "']/div[@data-dojo-attach-point='_buttonNode']");
    Reporter.logPass("Select RMM Project Area: " + "IS RMM PROJECT AREA SELECTION VISIBLE? " + projectAreaSelect);

    if (projectAreaSelect != null) {
      projectAreaSelect.click();
      getJazzPageFactory().getAbstractRMMPage()
          .clickElementwithDynamicValue(RMMConstants.RM_DIV_VERIFY_TEXT_AVAILABILITY, amProjectArea);

      Reporter.logPass("Select RMM Project Area: " + amProjectArea + " is Selected");

      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage()
          .switchToFrameViewByNameOrID(RMMConstants.RM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME);

      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();
      WebElement associateCRDiv = getJazzPageFactory().getAbstractRMMPage()
          .getModelElementIfFoundByXpath("//div[@class='com-ibm-team-rmm-ExplorerPicker']");
      Reporter.logPass("Create Link " + "IS ASSOCIATE CHANGE REQUEST OPTION VISIBLE? " + (associateCRDiv != null));

      if (associateCRDiv != null) {
        Reporter.logPass("Associate WorkItem");
        associateChangeRequestInRM(workItemID);
      }
      else {
        Reporter.logPass("CREATE LINK: Popup is not loaded correctly, Please Close and re-open Again...!!!");
        closeAndReOpenAddLinkWindow(workItemID, amProjectArea, linkType);
      }
    }
    else {
      Reporter.logPass("CREATE LINK: Popup is not loaded correctly, Please Close and re-open Again...!!!");
      closeAndReOpenAddLinkWindow(workItemID, amProjectArea, linkType);
    }
  }

  /**
   * @return
   */
  private String getRMWidgetId() {
    String rmPASelectId = "widget_com_ibm_rdm_web_common_FilteringSelect_";
    rmPASelectId = rmPASelectId + countOfWidjetReOpen;
    return rmPASelectId;
  }

  private void closeAndReOpenAddLinkWindow(final String workItemID, final String amProjectArea, final String linkType) {

    countOfWidjetReOpen++;

    getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.RMM_CLOSE_RM_PA_SLECTION_WINDOW);

    getJazzPageFactory().getAbstractRMMPage().clickElement(RMMConstants.ADD_LINK_TO_ARTIFACT_RM);

    getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.RMM_TD_TEXT_AVAILABILITY,
        linkType);
    selectRMMProjectArea(workItemID, amProjectArea, linkType);
  }
}
