/*
 * Copyright (c) ETAS GmbH 2023. All rights reserved.
 */
package java_test_scripts.RMM;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.constants.WorkItemEnums;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMConstants;
import com.bosch.jazz.automation.web.pagemodel.rmm.RMMModelElementPage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 */
public class TS_41596_AddElobaratesOSLCLinkFromCCMGC extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41596_addElobaratesOSLCLinkFromCCMGC() throws InvalidKeyException {

    String ccmServerURL = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RMM_CCM_PROJECT_AREA");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");
    String elementResourceId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String amProjectArea = this.testDataRule.getConfigData("AM_PROJECT_AREA");
    String amComponentName = this.testDataRule.getConfigData("AM_COMPONENT_NAME");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");
    String savebutton = this.testDataRule.getConfigData("SAVE_BUTTON");


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), ccmServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + ccmServerURL + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");


    RMMModelElementPage rmm = getJazzPageFactory().getRMMModelElementPage();
    assertNotNull(rmm);

    String element_URI = rmm.getRepositoryURLForCCMGC(workItemID, ccmServerURL, projectArea);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().navigateToURL(element_URI);

    getJazzPageFactory().getAbstractRMMPage().waitForSecs(10);

    // Navigate to Links tab in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");

    boolean isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementResourceId);
    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (!isLinkPresent) {
      // scroll to div Elobarated by Architecture Elemnts
      WebElement addLink = getJazzPageFactory().getAbstractRMMPage().getModelElementIfFoundByXpath(
          "//div[@id='com_ibm_team_workitem_web_ui_internal_view_layout_SectionLayout_6']//div[@class='ViewCommands']/div[@class='com-ibm-team-foundation-web-ui-views-ActionDropdownView']/div[2]/span[2]");
      addLink.click();

      Reporter.logPass("Click on ADD Link");

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      // click on Add OSLC link Satisfies
      getJazzPageFactory().getAbstractRMMPage().clickElementwithDynamicValue(RMMConstants.RMM_TD_TEXT_AVAILABILITY,
          linkType);
      Reporter.logPass("ADD Link: Select Link Type");


      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      Reporter.logPass("ADD Link: Select AM Project Area");

      // select Project Area
      getJazzPageFactory().getAbstractRMMPage()
          .clickElementwithDynamicValue(RMMConstants.RMM_OPTION_VERIFY_TEXT_AVAILIBILITY, amProjectArea);


      getJazzPageFactory().getAbstractRMMPage().waitForSecs(10);

      Reporter.logPass("ADD Link: Select Project Area: Click OK");

      getJazzPageFactory().getAbstractRMMPage()
          .clickElement(RMMConstants.RMM_ADD_OSLC_LINK_ASSOCIATECHANGEREUEST_SELECTPROJECTAREA_OK);


      getJazzPageFactory().getAbstractRMMPage().waitForSecs(20);


      // Select ModelElement
      getJazzPageFactory().getAbstractRMMPage().selectModelElementGC(
          RMMConstants.CCM_ADD_OSLC_LINK_ASSOCIATECHANGEREQUEST_SWITCHTO_IFRAME_NAME, amComponentName);

      // Wait for 20s for PageLoad
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      getJazzPageFactory().getAbstractRMMPage().switchToFrameViewToActiveElement();
      Reporter.logPass("ADD Link: Click SAVE");

      getJazzPageFactory().getAbstractRMMPage().clickOnButtons(savebutton);
      Reporter.logPass("ADD Link: Link is Created");

      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

      boolean linkVisibility =
          getJazzPageFactory().getAbstractRMMPage().verifyLinkNavigationToAM(elementResourceId, workItemID);
      Reporter.logPass("Check if OSLC Link Present?: " + linkVisibility);
      assertTrue(linkVisibility);

    }
    else {
      Reporter.logFailure("OSLC Link already Exists");
      assertFalse(true);
    }
  }

}
