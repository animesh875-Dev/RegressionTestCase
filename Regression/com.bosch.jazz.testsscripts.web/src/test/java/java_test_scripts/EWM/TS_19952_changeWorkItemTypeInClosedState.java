/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author UUM4KOR
 */
public class TS_19952_changeWorkItemTypeInClosedState extends AbstractFrameworkTest {

  /**
   * This test case creating new Work Items from Link section of a work item except Summary, verifying the error message
   * from notification area and Fill Summary then check created Work Item ID then Removing the linked work item.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_19952_changeWorkItemTypeInClosedState() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String workItemID = this.testDataRule.getConfigData("WORK_ITEM_ID");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(workItemID);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSearchedSpecification(workItemID);
    Reporter.logPass("Task Work ID: '" + workItemID + "' is opened successfully");
    getJazzPageFactory().getCCMWorkItemEditorPage().getStatus();
    Assert.assertEquals("Closed", getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    Reporter.logPass("Verify the work item status is 'Closed'");
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isReadOnlyDropdown("Type"));
    Reporter.logPass("Verify that Type droddown is disabled");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    // Save the work item
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus("rework");
    Reporter.logPass("Task Work Item is set 'rework' status successfully");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Reporter.logPass("Task Work Item saved successfully");
    // Check save button is desabled.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Work item saved successfully ");
    // Verify Type dropdown is disable
    Assert.assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isReadOnlyDropdown("Type"));

    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus("close");
    Reporter.logPass("Set the state of task to closed");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Reporter.logPass("Task Work Item saved successfully");
  }


}
