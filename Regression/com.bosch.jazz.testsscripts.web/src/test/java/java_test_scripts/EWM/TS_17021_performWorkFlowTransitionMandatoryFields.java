/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author UUM4KOR
 */
public class TS_17021_performWorkFlowTransitionMandatoryFields extends AbstractFrameworkTest {

  /**
   * <p>
   * This test case tests that without the presence of mandatory fields the Change of state of work Item should not be
   * allowed.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_17021_performWorkFlowTransitionMandatoryFields() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String storyWorkItemID = this.testDataRule.getConfigData("WORK_ITEM_TASK_ID");
    String ownedBy = this.testDataRule.getConfigData("OWNED_BY");
    String plannedFor = this.testDataRule.getConfigData("PLANNED_FOR");
    String errorMessage = this.testDataRule.getConfigData("ERROR_MESSAGE");
    String WorkItemID = this.testDataRule.getConfigData("WORKITEM_ID");
    String setResolutionDuplicate = "Duplicate";
    String setStateClose = "close";
    String setStateRework = "rework";
    String setStateInprogress = "start working";
    String setStateRenew = "renew";

    // Login in to ALM application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMAllProjectsPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing work item.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(storyWorkItemID);
    // Page Refresh
    getJazzPageFactory().getCCMProjectAreaDashboardPage().refresh();
    // Get work item present state and check for work item state New
    String workItemPresentStatus = getJazzPageFactory().getCCMWorkItemEditorPage().getStatus();
    assertTrue(workItemPresentStatus + " is the current status but expected is 'New', Please change work item status. ",
        workItemPresentStatus.contains("New"));
    Reporter.logInfo("Work item initial state is : " + workItemPresentStatus);
    // Get drop down attribute value and check attribute value is Unassigned
    String ownedByattributeValue =
        getJazzPageFactory().getCCMWorkItemEditorPage().getDropDownValue(WorkItemAttribute.OWNED_BY.toString());
    assertTrue(ownedByattributeValue +
        " is the current attribute value but expected is 'Unassigned', Please change work item dropdown attribute value. ",
        ownedByattributeValue.contains("Unassigned"));
    Reporter.logInfo("Work item initial value of 'Owned By' attribute is : " + ownedByattributeValue);
    // Get drop down attribute value and check attribute value is Unassigned
    String plannedForAttributeValue =
        getJazzPageFactory().getCCMWorkItemEditorPage().getDropDownValue(WorkItemAttribute.PLANNED_FOR.toString());
    assertTrue(plannedForAttributeValue +
        " is the current attribute value but expected is 'Unassigned', Please change work item dropdown attribute value. ",
        plannedForAttributeValue.contains("Unassigned"));
    Reporter.logInfo("Work item initial value of 'Planned For' attribute is : " + plannedForAttributeValue);
    // Check attribute is mandatory.
    assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isAttributeMandatory("Owned By"));
    Reporter.logInfo(WorkItemAttribute.OWNED_BY.toString() + " : attribute in not a mandatory ");
    // Check attribute is mandatory.
    assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isAttributeMandatory("Planned For"));
    Reporter.logInfo(WorkItemAttribute.PLANNED_FOR.toString() + " : attribute in not a mandatory ");
    // Set work item state.
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateInprogress);
    Reporter.logPass("Work item state set as : " + setStateInprogress);
    // Save work item
  //  getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
  //  getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // check is work item is saved.
    assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Is Work Item Saved : " + getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass(
        "Work item without mandatory fields 'Owned by' and 'Planned for' not allowed set to In progress and save, get below warning message in notification area ");
    // Check Validation massage from notification area.
    String validationMessageOwnedBy =
        getJazzPageFactory().getCCMWorkItemEditorPage().getValidationMessageFromNotificationArea();
    assertTrue(validationMessageOwnedBy +
        " is the current attribute value but expected is 'Unassigned', Please change work item dropdown attribute value. ",
        validationMessageOwnedBy.contains("Attribute 'Owned By' not set"));
    // Check Validation massage from notification area.
    String validationMessagePlannedFor =
        getJazzPageFactory().getCCMWorkItemEditorPage().getValidationMessageFromNotificationArea();
    assertTrue(validationMessagePlannedFor +
        " is the current attribute value but expected is 'Unassigned', Please change work item dropdown attribute value. ",
        validationMessagePlannedFor.contains("Attribute 'Planned For' not set."));
    Reporter.logError("Attribute 'Owned By' not set");
    Reporter.logError("Attribute 'Planned For' not set.");
    // Check is Attribute Mandatory
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isAttributeMandatory("Owned By"));
    Reporter.logPass("Is Owned By Attribute Mandatory : " +
        getJazzPageFactory().getCCMWorkItemEditorPage().isAttributeMandatory("Owned By"));
    // Check is Attribute Mandatory
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isAttributeMandatory("Planned For"));
    Reporter.logPass("Is Planned For Attribute Mandatory : " +
        getJazzPageFactory().getCCMWorkItemEditorPage().isAttributeMandatory("Planned For"));
    // set drop down attribute value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue("Owned By", ownedBy);
    Reporter.logPass("Owned By Drop Down Attribute Value set as : " + ownedBy);
    // set drop down attribute value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue("Planned For", plannedFor);
    Reporter.logPass("Planned For Drop Down Attribute Value set as : " + plannedFor);
    // Click save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    // page refresh
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // check is work item is saved.
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Is Work Item Saved : " + getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Work item saved successfully");
    // validate initial work item state
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getStatus().contains("In Progress"));
    Reporter.logInfo("Work item Target state : " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    // Set Work item state.
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateClose);
    Reporter.logInfo("Work item Transition state set as : " + setStateClose);
    // Set Work item resolution
    getJazzPageFactory().getCCMWorkItemEditorPage().setResolution(setResolutionDuplicate);
    Reporter.logInfo("Work item Resolution set as : " + setResolutionDuplicate);
    // Click save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);
    assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Is Work Item Saved : " + getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    // validate the error message from notification area
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getValidationMessageFromNotificationArea()
        .contains(errorMessage));
    Reporter.logError(errorMessage);
    Reporter.logPass(
        "Above Message displayed in Notification area when trying to save Work item with 'State' as 'Close' and 'Resolution' as 'Duplicate'");
    // Add work item from notifications area link
    Reporter.logPass("add WorkItem From Notification Area Link");
    getJazzPageFactory().getCCMWorkItemEditorPage().addWorkItemFromNotificationAreaLink(WorkItemID);
    Reporter.logPass("Work Item added From Notification Area Link successfully");
    // Click and save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    // Page Refresh
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Is Work Item Saved : " + getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    // Validate the target state
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getStatus().contains("Closed"));
    // Validate the target resolution
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getResolution().contains("Duplicate"));
    Reporter.logPass("Target state is " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus() +
        " and Target Resolution is " + getJazzPageFactory().getCCMWorkItemEditorPage().getResolution());
    Reporter.logPass("Target State and Resolution validated successfully");
    // Set work item state back to In Progress
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateRework);
    Reporter.logInfo("Work item Transition state set as : " + setStateRework);
    // Click and Save Button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Is Work Item Saved : " + getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logInfo("Work item saved successfully");
    Reporter.logInfo("Work item Target Sate is : " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    // Set work item state back to New
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateRenew);
    Reporter.logInfo("Work item Transition State set : " + setStateRenew);
    // Click and save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Is Work Item Saved : " + getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logInfo("Target Sate is : " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    // Select tab
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab("Overview");
    // set attribute value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue("Owned By", "Unassigned");
    // set attribute value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue("Planned For", "Unassigned");
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Is Work Item Saved : " + getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass("Work item saved successfully");
    String workItemTargetStatus = getJazzPageFactory().getCCMWorkItemEditorPage().getStatus();
    assertTrue(workItemTargetStatus + " is the current status but expected is 'New', Please change work item status. ",
        workItemTargetStatus.contains("New"));
    Reporter
        .logInfo("Finaly Work item present Sate is : " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus());
    Reporter.logPass("Finaly 'Owned By' Drop Down Attribute present Value is : " +
        getJazzPageFactory().getCCMWorkItemEditorPage().getDropDownValue(WorkItemAttribute.OWNED_BY.toString()));
    Reporter.logPass("Finaly 'Planned For' Drop Down Attribute present Value is : " +
        getJazzPageFactory().getCCMWorkItemEditorPage().getDropDownValue(WorkItemAttribute.PLANNED_FOR.toString()));
  }
}
