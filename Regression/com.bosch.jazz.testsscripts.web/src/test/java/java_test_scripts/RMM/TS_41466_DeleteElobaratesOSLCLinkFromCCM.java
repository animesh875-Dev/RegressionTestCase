/*
 * Copyright (c) ETAS GmbH 2023. All rights reserved.
 */
package java_test_scripts.RMM;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.WorkItemEnums;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 */
public class TS_41466_DeleteElobaratesOSLCLinkFromCCM extends AbstractFrameworkTest {

  /**
   * @throws InvalidKeyException
   */
  @Test
  public void ts_41466_deleteElobaratesOSLCLinkFromCCM() throws InvalidKeyException {

    String ccmServerURL = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RMM_CCM_PROJECT_AREA");
    String workItemID = this.testDataRule.getConfigData("WORKITEM_ID");
    String elementResourceId = this.testDataRule.getConfigData("ELEMENT_RESOURCE_ID");
    String savebutton = this.testDataRule.getConfigData("SAVE_BUTTON");


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), ccmServerURL);
    Reporter.logPass(getUserId() + " user logged in to the " + ccmServerURL + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(workItemID);
    Reporter.logPass(workItemID + ": Task workitem opened successfully.");

    // Open searched workItem
    getJazzPageFactory().getAbstractRMMPage().clickElement("//div[@class='search-result']/div[@class='link']/a");
    Reporter.logPass("Open '" + workItemID + "' task found in the project area.");

    getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();

    // Navigate to Links tab in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");

    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(10);

    boolean isLinkPresent = getJazzPageFactory().getAbstractRMMPage().isLinkVisibleWithHref(elementResourceId);
    Reporter.logPass("Check if OSLC Link already Present?: " + isLinkPresent);

    if (isLinkPresent) {
      // getJazzPageFactory().getAbstractRMMPage().deleteAMLinkFromCCM(elementResourceId);
      getJazzPageFactory().getAbstractRMMPage().deleteAMLinkFromCCM(elementResourceId);

      Reporter.logPass("Click on Save Button");

      getJazzPageFactory().getAbstractRMMPage().clickOnButtons(savebutton);
      getJazzPageFactory().getAbstractRMMPage().waitForPageLoaded();
      Reporter.logPass("Refresh Page");

      // Refresh CCM page
      getJazzPageFactory().getAbstractRMMPage().refresh();

      // Verify CCM links are deleted or not
      isLinkPresent = getJazzPageFactory().getRMMModelElementPage().isLinkVisibleWithHref(elementResourceId);
      Reporter.logPass("Check if OSLC Link Present after Deletion: " + isLinkPresent);

      Assert.assertFalse(isLinkPresent);
      Reporter.logPass(elementResourceId + " : Link is deleted succesfully");
    }
    else {
      Reporter.logFailure(elementResourceId + " : Link does not present in the Architecture element");
      Assert.assertTrue(isLinkPresent);
    }
  }

}
