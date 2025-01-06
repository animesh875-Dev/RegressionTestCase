/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.WorkItemEnums;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author UUM4KOR
 */
public class TS_14392_addingWorkItemApprovalsInExistingWorkitem extends AbstractFrameworkTest {

  /**
   * This test case Adding work item Approval in existing Task, Defect and Story work item and verifying the Approval
   * types, Approvers, and Due date in web client.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_14392_AddingWorkitemApprovalsInExistingWorkitem() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String taskWorkitemid = this.testDataRule.getConfigData("WORK_ITEM_TASK_ID");
    String defectWorkitemid = this.testDataRule.getConfigData("WORK_ITEM_DEFECT_ID");
    String storyWorkitemid = this.testDataRule.getConfigData("WORK_ITEM_STORY_ID");
    String user = this.testDataRule.getConfigData("APPROVER_USER");
    String dueDate = this.testDataRule.getConfigData("DUE_DATE");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(taskWorkitemid);
    Reporter.logPass(taskWorkitemid + ": Task workitem opened successfully.");
    // Navigate to Approvals tab in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.APPROVALS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.APPROVALS.toString() + ": Tab opened successfully in Task workitem.");
    // Click on Add Review attribute in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnAddApproval(WorkItemAttribute.ADD_REVIEW.toString());
    Reporter.logPass(WorkItemAttribute.ADD_REVIEW.toString() + " selected from Add Approval dropdown.");
    // Add approvers using select users window.
    getJazzPageFactory().getCCMWorkItemEditorPage().addApprovers(user);
    Reporter.logPass(user + " user selected for approval.");
    // Add Due Date for Review Approvers.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDueDate(WorkItemAttribute.REVIEW.toString(), dueDate);
    Reporter.logPass(dueDate + " due date added for" + WorkItemAttribute.REVIEW.toString());
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");
    // Verify approval user is added to the approval type.
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isApprovarUserAdded(WorkItemAttribute.REVIEW.toString(), user));
    // Verify date is added to the approval type.
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isDueDateAdded(WorkItemAttribute.REVIEW.toString(), dueDate));
    // Delete the review from approvals tab.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteApproval(WorkItemAttribute.REVIEW.toString());
    Reporter.logPass(WorkItemAttribute.REVIEW.toString() + " is removed from approvals tab.");
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");

    // Search the existing workitem defect.
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(defectWorkitemid);
    Reporter.logPass(defectWorkitemid + ": Defect workitem opened successfully.");
    // Navigate to Approvals tab in defect workitem.
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.APPROVALS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.APPROVALS.toString() + " tab opened successfully in Defect workitem.");
    // Add Verification for the defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnAddApproval(WorkItemAttribute.ADD_VERIFICATION.toString());
    Reporter.logPass(WorkItemAttribute.ADD_VERIFICATION.toString() + " selected from Add Approval dropdown.");
    // Add approvers from select users window.
    getJazzPageFactory().getCCMWorkItemEditorPage().addApprovers(user);
    Reporter.logPass(user + " user selected for approval.");
    // Add Due Date for Verification Approvers.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDueDate(WorkItemAttribute.VERIFICATION.toString(), dueDate);
    Reporter.logPass(dueDate + " due date added for " + WorkItemAttribute.VERIFICATION.toString());
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(3);
    // Verify approval user is added to the approval type.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage()
        .isApprovarUserAdded(WorkItemAttribute.VERIFICATION.toString(), user));
    // Verify date is added to the approval type.
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage()
        .isDueDateAdded(WorkItemAttribute.VERIFICATION.toString(), dueDate));
    // Delete the verification from approvals tab.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteApproval(WorkItemAttribute.VERIFICATION.toString());
    Reporter.logPass(WorkItemAttribute.VERIFICATION.toString() + " is removed from approvals tab.");
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");

    // Search the existing workitem Story.
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(storyWorkitemid);
    Reporter.logPass(storyWorkitemid + ": Story workitem opened successfully.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    // Navigate to Approvals tab in story work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.APPROVALS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.APPROVALS.toString() + ": Tab opened successfully in Story workitem.");
    // click on Add Approvel attribute in story work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnAddApproval(WorkItemAttribute.ADD_APPROVAL.toString());
    Reporter.logPass(WorkItemAttribute.ADD_APPROVAL.toString() + " selected from Add Approval dropdown.");
    // Add approvers using select users window.
    getJazzPageFactory().getCCMWorkItemEditorPage().addApprovers(user);
    Reporter.logPass(user + " user selected for approval.");
    // Add Due Date for Approval.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDueDate(WorkItemAttribute.APPROVAL.toString(), dueDate);
    Reporter.logPass(dueDate + " due date added for" + WorkItemAttribute.APPROVAL.toString());
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");
    // Verify approval user is added to the approval type.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage()
        .isApprovarUserAdded(WorkItemAttribute.APPROVAL.toString(), user));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);
    // Verify date is added to the approval type.
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isDueDateAdded(WorkItemAttribute.APPROVAL.toString(), dueDate));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Add again same approvers using select users window.
    getJazzPageFactory().getCCMWorkItemEditorPage().addApprovers(user);
    // Delete the approval from approvals tab.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteApproval(WorkItemAttribute.APPROVAL.toString());
    Reporter.logPass(WorkItemAttribute.APPROVAL.toString() + " is removed from approvals tab.");
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");

  }
}
