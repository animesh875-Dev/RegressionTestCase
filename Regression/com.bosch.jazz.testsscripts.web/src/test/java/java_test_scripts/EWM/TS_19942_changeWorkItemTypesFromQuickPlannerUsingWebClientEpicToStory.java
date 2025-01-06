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
public class TS_19942_changeWorkItemTypesFromQuickPlannerUsingWebClientEpicToStory extends AbstractFrameworkTest {

  /**
   * This test case creating new Work Items from Link section of a work item except Summary, verifying the error message
   * from notification area and Fill Summary then check created Work Item ID then Removing the linked work item.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_19942_changeWorkItemTypesFromQuickPlannerUsingWebClientEpicToStory() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String summary = this.testDataRule.getConfigData("WORK_ITEM_SUMMARY");
    String status = this.testDataRule.getConfigData("WORK_ITEM_STATUS");
    String workItemID = this.testDataRule.getConfigData("WORK_ITEM_ID");
    String filedAgainst = this.testDataRule.getConfigData("WORK_ITEM_FILED_AGAINST");
    String ownedby = this.testDataRule.getConfigData("WORK_ITEM_OWNED_BY");
    String plannedFor = this.testDataRule.getConfigData("WORK_ITEM_PLANNED_FOR");
    String boardName = this.testDataRule.getConfigData("BOARD_NAME");
    String boardType = this.testDataRule.getConfigData("BOARD_TYPE");
    String interationName = this.testDataRule.getConfigData("BOARD_INTERATION");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu("Plans");
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("Quick Planner");
    Reporter.logPass("Verify Quick Planner page opened");
    getJazzPageFactory().getCCMQuickPlannerPage().createABoard(boardName, boardType, interationName);
    Reporter.logPass("Create a new board: '" + boardName + "' successfully");
    getJazzPageFactory().getCCMQuickPlannerPage().selectDropdownInWorkItemRow("In Progress", summary, "Epic", "Story");
    Reporter.logPass("Verify change work item type from Epic to Story");
    Assert.assertTrue(getJazzPageFactory().getCCMQuickPlannerPage().isWorkItemInPanel("Open", summary));
    Reporter.logPass("Verify the status of Story is Open");
    getJazzPageFactory().getCCMQuickPlannerPage().selectWorkItemLinkInPannel(workItemID, summary);
    Reporter.logPass("Verify the work item selected");

    // Verify the work item after updating
    Assert.assertEquals("New", getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    Reporter.logPass("Verify Work Item - Status");
    Assert.assertEquals(summary, getJazzPageFactory().getCCMWorkItemEditorPage().getAttributeValueInTextBox("Summary"));
    Reporter.logPass("Verify Work Item - Summary");
    Assert.assertEquals(filedAgainst,
        getJazzPageFactory().getCCMWorkItemEditorPage().getDropDownValue("Filed Against"));
    Reporter.logPass("Verify Work Item - Filed Against");
    Assert.assertEquals(ownedby, getJazzPageFactory().getCCMWorkItemEditorPage().getDropDownValue("Owned By"));
    Reporter.logPass("Verify Work Item - Owned By");
    Assert.assertEquals(plannedFor, getJazzPageFactory().getCCMWorkItemEditorPage().getDropDownValue("Planned For"));
    Reporter.logPass("Verify Work Item - Planned For");

    // TearDown step
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Reporter.logPass("Perform teardown steps.");
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue("Type", "Epic");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus("start working");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Assert.assertEquals(status, getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
  }


}
