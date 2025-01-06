/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.WorkItemEnums;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_14412_linkWorkItemsCCMtoRQMtoExistingObject extends AbstractFrameworkTest {

  /**
   * This Test Case checks the creation of links between objects of CCM and RQM with using 'Add Tested By Test Case' and
   * 'Add Related Test Plan' by using existing test case and test plan.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_14412_linkWorkItemsCCMtoRQMtoExistingObject() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String taskWorkitemid = this.testDataRule.getConfigData("TASK_WORK_ITEM_ID");
    String rqmProjectArea = this.testDataRule.getConfigData("RQM_PROJECT_AREA");
    String testCaseId = this.testDataRule.getConfigData("TEST_CASE_ID");
    String testedByTestCase = this.testDataRule.getConfigData("TESTED_BY_TEST_CASE");
    String storyWorkitemid = this.testDataRule.getConfigData("STORY_WORK_ITEM_ID");
    String testPlanId = this.testDataRule.getConfigData("TEST_PLAN_ID");
    String relatedTestPlan = this.testDataRule.getConfigData("RELATED_TEST_PLAN");
    String testCaselinkType = this.testDataRule.getConfigData("TEST_CASE_LINK_TYPE");
    String testPlanlinkType = this.testDataRule.getConfigData("TEST_PLAN_LINK_TYPE");
    String dropdownText = this.testDataRule.getConfigData("DROP_DOWN_TEXT");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing task workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(taskWorkitemid);
    Reporter.logPass(taskWorkitemid + ": Task WorkItem opened successfully.");
    // Navigate to Links tab in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");

    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", dropdownText);
    additionalParams.put("linkActions", "Add Tested By Test Case");
    additionalParams.put("rqmProjectArea", rqmProjectArea);
    additionalParams.put("linkTypeID", testCaseId);
    // Add Existing Tested By Test Case in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams);
    Reporter.logPass(testCaseId + " selected for work item from " + rqmProjectArea + " project area.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(10);
    // Save the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    Reporter.logPass(" Task Work Item saved successfully");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Verify test case is added in the work item.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testCaseId));
    Reporter.logPass(testCaseId + " is visible in the Links section of the work item");
    // Click on the test case added in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", testCaseId,
        testCaselinkType);
    Reporter.logPass("Click on the test case with id " + testCaseId);
    // Verify task work item visible in the Development Items section in the test case.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemVisibleInTestCase(taskWorkitemid));
    Reporter.logPass(taskWorkitemid + " is visible in the Development Items section of the test case");
    // Click on the work item which is visible in Development Item section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnWorkItemFromTestCase(taskWorkitemid);
    Reporter.logPass(taskWorkitemid + " clicked on the work item from test case.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Select the Links tab in the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");
    // Remove the added test case in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(testedByTestCase);
    Reporter.logPass("Removed " + testedByTestCase + " from the task work item.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the work item
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    Reporter.logPass("Task Work Item saved successfully");
    // Verify added test case is not visible in 'Links' section of the work item.
    Assert.assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testCaseId));
    Reporter.logPass(testCaseId + " is not visible in the Links section");
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();

    // Search for the existing story workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(storyWorkitemid);
    Reporter.logPass(storyWorkitemid + ": Story WorkItem opened successfully.");
    // Navigate to Links tab in story work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Story workitem.");
    // Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", dropdownText);
    additionalParams.put("linkActions", "Add Related Test Plan");
    additionalParams.put("rqmProjectArea", rqmProjectArea);
    additionalParams.put("linkTypeID", testPlanId);
    // Add Existing Tested By Test Case in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams);
    Reporter.logPass(testPlanId + " selected for work item from " + rqmProjectArea + " project area.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(10);
    // Save the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    Reporter.logPass(" Story Work Item saved successfully");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Verify test case is added in the work item.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testPlanId));
    Reporter.logPass(testPlanId + " is visible in the Links section of the work item");
    // Click on the test case added in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", testPlanId,
        testPlanlinkType);
    Reporter.logPass("Click on the test plan with id " + testPlanId);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(10);
    // Verify story work item visible in the Related Change Request section in the test plan.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemVisibleInTestArtifact(storyWorkitemid));
    Reporter.logPass(storyWorkitemid + " is visible in Related Change Request in the test plan");
    // Click on the work item which is visible in Related Change Request section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnWorkItemFromTestArtifact(storyWorkitemid);
    Reporter.logPass(storyWorkitemid + " clicked on the work item from test pan.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Select the Links tab in the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Story workitem.");
    // Remove the added test case in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(relatedTestPlan);
    Reporter.logPass("Removed " + testedByTestCase + " from the story work item.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the work item
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");
    Reporter.logPass("Story Work Item saved successfully");
    // Verify added test plan is not visible in 'Links' section of the work item.
    Assert.assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testPlanId));
    Reporter.logPass(testPlanId + " is not visible in the Links section");


  }

}
