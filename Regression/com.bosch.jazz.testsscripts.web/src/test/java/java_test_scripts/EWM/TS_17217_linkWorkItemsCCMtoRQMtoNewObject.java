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
public class TS_17217_linkWorkItemsCCMtoRQMtoNewObject extends AbstractFrameworkTest {

  /**
   * This Test Case checks the creation of links between objects of CCM and RQM with 'Add Tested By Test Case' and 'Add
   * Related Test Plan' link by creating new test case and test plan.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_17217_linkWorkItemsCCMtoRQMtoNewObject() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String defectWorkitemid = this.testDataRule.getConfigData("DEFECT_WORK_ITEM_ID");
    String rqmProjectArea = this.testDataRule.getConfigData("RQM_PROJECT_AREA");
    String testCaseName = this.testDataRule.getConfigData("TEST_CASE_NAME");
    String testCaseWeight = this.testDataRule.getConfigData("TEST_CASE_WEIGHT");
    String domainValue = this.testDataRule.getConfigData("TEST_CASE_DOMAIN");
    String testCaseTestType = this.testDataRule.getConfigData("TEST_CASE_TYPE");
    String testCaselinkType = this.testDataRule.getConfigData("TEST_CASE_LINK_TYPE");
    String epicWorkItemId = this.testDataRule.getConfigData("EPIC_WORK_ITEM");
    String testPlanName = this.testDataRule.getConfigData("TEST_PLAN_NAME");
    String testPlanProduct = this.testDataRule.getConfigData("TEST_PLAN_PRODUCT");
    String testPlanRelease = this.testDataRule.getConfigData("TEST_PLAN_RELEASE");
    String testPlanlinkType = this.testDataRule.getConfigData("TEST_PLAN_LINK_TYPE");
    String dropdownText = this.testDataRule.getConfigData("DROP_DOWN_TEXT");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(defectWorkitemid);
    Reporter.logPass(defectWorkitemid + ": Defect WorkItem opened successfully.");
    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");

    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("linkActions", "Add Tested By Test Case");
    additionalParams.put("rqmProjectArea", rqmProjectArea);
    additionalParams.put("testCaseName", testCaseName);
    additionalParams.put("testCaseWeight", testCaseWeight);
    additionalParams.put("testCaseDomainMoreLinkValue", "More");
    additionalParams.put("domainValue", domainValue);
    additionalParams.put("testCaseTestType", testCaseTestType);
    // Select 'Tested By Test Case' option from 'Links' section and create a link.
    String testCaseWithDate = getJazzPageFactory().getCCMWorkItemEditorPage().createLinkToExistingObject(additionalParams);
    Reporter.logPass("New test case " + testCaseName + " is created in Links section ");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the defect workitem.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    Reporter.logPass(defectWorkitemid + " saved successfully.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Verify test case is added in the work item.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testCaseName));
    Reporter.logPass(testCaseName + " is visible in the Links section of the work item");
    // Click on the test case added in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", testCaseName,
        testCaselinkType);
    Reporter.logPass("Click on the test case with " + testCaseName);
    
    
    // Verify task work item visible in the Development Items section in the test case.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemVisibleInTestCase(defectWorkitemid));
    Reporter.logPass(defectWorkitemid + " is visible in the Development Items section of the test case");
    // Click on the work item which is visible in Development Item section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnWorkItemFromTestCase(defectWorkitemid);
    Reporter.logPass(defectWorkitemid + " clicked on the work item from test case.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Select the Links tab in the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Task workitem.");
    // Remove the added test case in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(testCaseWithDate);
    Reporter.logPass("Removed " + testCaseName + " from the task work item.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the work item
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");
    Reporter.logPass("Task Work Item saved successfully");
    // Verify added test case is not visible in 'Links' section of the work item.
    Assert.assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testCaseName));
    Reporter.logPass(testCaseName + " is not visible in the Links section");
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();

    // Search for the existing workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(epicWorkItemId);
    Reporter.logPass(defectWorkitemid + ": Epic WorkItem opened successfully.");
    // Navigate to Links tab in epic work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Epic workitem.");
    additionalParams.put("linksSection", "Links");
    additionalParams.put("linkActions", "Add Related Test Plan");
    additionalParams.put("dropdownText", dropdownText);
    additionalParams.put("rqmProjectArea", rqmProjectArea);
    additionalParams.put("testPlanName", testPlanName);
    additionalParams.put("testPlanProduct", testPlanProduct);
    additionalParams.put("testPlanReleaseMoreLinkValue", "More");
    additionalParams.put("testPlanReleaseValue", testPlanRelease);
    // Select 'Add Related' option from 'Links' section and create a link.
    String testPlanWithDate = getJazzPageFactory().getCCMWorkItemEditorPage().createLinkToExistingObject(additionalParams);
    Reporter.logPass("New test case " + testCaseName + " is created in Links section ");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the defect workitem.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    Reporter.logPass(defectWorkitemid + " saved successfully.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Verify test case is added in the work item.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testPlanName));
    Reporter.logPass(testPlanName + " is visible in the Links section of the work item");
    // Click on the test case added in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", testPlanName,
        testPlanlinkType);
    Reporter.logPass("Click on the test plan with name " + testPlanName);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(10);
    // Verify story work item visible in the Related Change Request section in the test plan.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemVisibleInTestArtifact(epicWorkItemId));
    Reporter.logPass(epicWorkItemId + " is visible in Related Change Request in the test plan");
    // Click on the work item which is visible in Related Change Request section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnWorkItemFromTestArtifact(epicWorkItemId);
    Reporter.logPass(epicWorkItemId + " clicked on the work item from test pan.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Refresh the webpage.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Select the Links tab in the task work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Story workitem.");
    // Remove the added test case in the Links section of the work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(testPlanWithDate);
    Reporter.logPass("Removed " + testPlanlinkType + " from the epic work item.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the work item
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save");
    Reporter.logPass("Story Work Item saved successfully");
    // Verify added test plan is not visible in 'Links' section of the work item.
    Assert.assertFalse(getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testPlanName));
    Reporter.logPass(testPlanName + " is not visible in the Links section");

  }
}
