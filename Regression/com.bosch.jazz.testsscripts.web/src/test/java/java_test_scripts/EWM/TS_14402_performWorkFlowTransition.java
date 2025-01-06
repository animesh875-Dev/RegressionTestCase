/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author UUM4KOR
 */
public class TS_14402_performWorkFlowTransition extends AbstractFrameworkTest {


  /**
   * <p>
   * This test case test the State and resolution of existing work item in web client.<br>
   * With this we should also test that after each action/save of work item,the state should be correctly set and the
   * resolutions available for selected state working fine.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_14402_performWorkFlowTransition() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String storyWorkItemID = this.testDataRule.getConfigData("WORK_ITEM_STORY_ID");
    String ownedBy = this.testDataRule.getConfigData("OWNED_BY");
    String plannedFor = this.testDataRule.getConfigData("PLANNED_FOR");
    String setResolutionInvalid = "Invalid";
    String setResolutionSolved = "Solved";
    String setStateClose = "close";
    String setStateRework = "rework";

    // Login in to ALM application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // page refresh
    getJazzPageFactory().getCCMProjectAreaDashboardPage().refresh();
    // Select the project area.
    getJazzPageFactory().getCCMAllProjectsPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing work item.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(storyWorkItemID);
    // set attribute Owned By value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue("Owned By", ownedBy);
    Reporter.logPass("Owned By Drop Down Attribute Value set as : " + ownedBy);
    getJazzPageFactory().getCCMQueryPage().waitForSecs(3);
    // set attribute Planned For value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue("Planned For", plannedFor);
    Reporter.logPass("Planned For Drop Down Attribute Value set as : " + plannedFor);
    getJazzPageFactory().getCCMQueryPage().waitForSecs(3);
    // Check present sate is In Progress.
    Reporter.logPass("Initial state is : " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    String workItemStatus = getJazzPageFactory().getCCMWorkItemEditorPage().getStatus();
    assertTrue(
        workItemStatus + " is the current status but expected is 'In Progress', Please change work item status. ",
        workItemStatus.contains("In Progress"));
    // Set work item state.
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateClose);
    Reporter.logPass("Transition state set as : " + setStateClose);
    // Set work item resolution.
    getJazzPageFactory().getCCMWorkItemEditorPage().setResolution(setResolutionInvalid);
    Reporter.logPass("Resolution set as : " + setResolutionInvalid);
    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("Work item saved successfully");
    // page refresh
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Validate State and Resolution
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getStatus().contains("Closed"));
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getResolution().contains("Invalid"));
    Reporter.logPass("Target state is " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus() +
        " and Target Resolution is " + getJazzPageFactory().getCCMWorkItemEditorPage().getResolution());
    Reporter.logPass("Target State and Resolution validated successfully");
    // Set work item state.
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateRework);
    Reporter.logPass("Transition state set as : " + setStateRework);
    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("Work item saved successfully");
    // page refresh
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getStatus().contains("In Progress"));
    Reporter.logPass(
        "Initial state " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus() + " validated successfully");
    Reporter.logPass("Initial state is : " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    // Set work item state.
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateClose);
    Reporter.logPass("Transition state set as : " + setStateClose);
    // Set work item resolution.
    getJazzPageFactory().getCCMWorkItemEditorPage().setResolution(setResolutionSolved);
    Reporter.logPass("Resolution set as : " + setResolutionSolved);
    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("Work item saved successfully");
    // page refresh
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getStatus().contains("Closed"));
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getResolution().contains("Solved"));
    Reporter.logPass("Target state is " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus() +
        " and Target Resolution is " + getJazzPageFactory().getCCMWorkItemEditorPage().getResolution());
    Reporter.logPass("Target State and Resolution validated successfully");
    // Set work item state.
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateRework);
    Reporter.logPass("Transition state set as : " + setStateRework);
    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("Work item saved successfully");
    // page refresh
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    Reporter.logPass("Work item current state is : " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
  }
}
