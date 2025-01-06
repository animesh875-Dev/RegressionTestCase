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
public class TS_17596_linkWorkItemCCMtoRQMLinkSameWIASecondTime extends AbstractFrameworkTest {

  /**
   * This test case describes linking a work item second time to the same destination RQM object should not be possible.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_17596_linkWorkItemCCMtoRQMLinkSameWIASecondTime() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String taskWorkitemid = this.testDataRule.getConfigData("TASK_WORK_ITEM_ID");
    String rqmProjectArea = this.testDataRule.getConfigData("RQM_PROJECT_AREA");
    String testCaseId = this.testDataRule.getConfigData("TEST_CASE_ID");
    String relatedTestCase = this.testDataRule.getConfigData("RELATED_TEST_CASE");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");
    String dropdownText = this.testDataRule.getConfigData("DROP_DOWN_TEXT");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(taskWorkitemid);
    Reporter.logPass(taskWorkitemid + ": Task WorkItem opened successfully.");
    // Navigate to Links tab in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");

    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", dropdownText);
    additionalParams.put("linkActions", "Add Related Test Case");
    additionalParams.put("rqmProjectArea", rqmProjectArea);
    additionalParams.put("linkTypeID", testCaseId);
    // Add Existing Tested By Test Case in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams);
    Reporter.logPass(testCaseId + " selected for work item from " + rqmProjectArea + " project area.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(10);
    // Save the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    Reporter.logPass("Task Work Item saved successfully");
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Navigate to Links tab in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");
    // Add Existing Tested By Test Case in task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams);
    Reporter.logPass(testCaseId + " selected for work item from " + rqmProjectArea + " project area.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Verify same test case is added as related test case.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isTestCaseDuplicated());
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Click on the test case added in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", testCaseId, linkType);
    Reporter.logPass("Click on the test case with id " + testCaseId);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(10);
    // Verify task work item visible in the Development Items section in the test case.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemVisibleInTestArtifact(taskWorkitemid));
    Reporter.logPass(taskWorkitemid + " is visible in the Development Items section of the test case");
    // Click on the work item which is visible in Development Item section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnWorkItemFromTestArtifact(taskWorkitemid);
    Reporter.logPass(taskWorkitemid + " clicked on the work item from test case.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Select the Links tab in the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");
    // Remove the added test case in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(relatedTestCase);
    Reporter.logPass("Removed " + relatedTestCase + " from the task work item.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the work item
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");
    Reporter.logPass("Task Work Item saved successfully");
    // Verify added test case is not visible in 'Links' section of the work item.
    Assert.assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testCaseId));
    Reporter.logPass(testCaseId + " is not visible in the Links section");

  }
}
