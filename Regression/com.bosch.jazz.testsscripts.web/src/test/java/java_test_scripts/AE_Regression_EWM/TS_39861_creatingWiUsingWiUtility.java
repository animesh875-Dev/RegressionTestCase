/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author GUX2KOR
 *
 */
public class TS_39861_creatingWiUsingWiUtility extends AbstractFrameworkTest{

  /**
   * @throws Throwable
   */
  @Test
  public void tcs_39861_creatingWiUsingWiUtility() throws Throwable {
    
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");
    String storyPA = this.testDataRule.getConfigData("STORY_PA");
    String storyType = this.testDataRule.getConfigData("STORY_TYPE");
    String storySummary = this.testDataRule.getConfigData("STORY_SUMMARY");
    String storyFA = this.testDataRule.getConfigData("STORY_FA");
    String taskPA = this.testDataRule.getConfigData("TASK_PA");
    String taskType = this.testDataRule.getConfigData("TASK_TYPE");
    String taskSummary = this.testDataRule.getConfigData("TASK_SUMMARY");
    String taskFA = this.testDataRule.getConfigData("TASK_FA");
    String setResolutionInvalid = "Invalid";
    String setStateClose = "close";
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    // open minidashboard page
    getJazzPageFactory().getCCMProjectAreaDashboardPage().clickOnMiniDashbaord();
    getJazzPageFactory().getCCMProjectAreaDashboardPage().pinMiniDashboard();
    Reporter.logPass("Successfully opened minidashboard page.");
    
    // click on workitem utility widget to create workitem 1
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openWidgetFromWidgetManagementWidget("Workitem Utility");
    Reporter.logPass("Clicked on Workitem utility widget successfully.");
    
    // creating workitem 1
    Map<String, String> parameters = new LinkedHashMap<String, String>();
    parameters.put("projectArea", storyPA);
    parameters.put("type", storyType);
    parameters.put("summary", storySummary);
    parameters.put("filedAgainst", storyFA);
    String workItemId1 = getJazzPageFactory().getCCMProjectAreaDashboardPage().createWorkItemsUsingWorkitemUtilityWidget(parameters);
    Reporter.logPass("Created first workitem : " + workItemId1);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    // close the workitem utility widget
    getJazzPageFactory().getCCMProjectAreaDashboardPage().closeWorkitemUtilityWidget();
    Reporter.logPass("Closed Workitem utility widget successfully.");
    
    // click on workitem utility widget to create workitem 2
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openWidgetFromWidgetManagementWidget("Workitem Utility");
    Reporter.logPass("Clicked on Workitem utility widget successfully.");
    
    // creating workitem 2
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("projectArea", taskPA);
    params.put("type", taskType);
    params.put("summary", taskSummary);
    params.put("filedAgainst", taskFA);
    String workItemId2 = getJazzPageFactory().getCCMProjectAreaDashboardPage().createWorkItemsUsingWorkitemUtilityWidget(params);
    Reporter.logPass("Created second workitem : " + workItemId2);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    // close the workitem utility widget
    getJazzPageFactory().getCCMProjectAreaDashboardPage().closeWorkitemUtilityWidget();
    Reporter.logPass("Closed Workitem utility widget successfully.");
    
    // verifying and closing the created workitems
    //verifying and closing workitem 1
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(workItemId1);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSearchedSpecification(workItemId1);
    TestAcceptanceMessage Ta =  getJazzPageFactory().getCCMProjectAreaDashboardPageVerification().verifyOpenSearchedSpecification(parameters.get("type") + " " + workItemId1, null);
    if(Ta.getState().equals("FAILED")) {
      throw new WebAutomationException(Ta.getMessage());
    }
    String fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(),
        "verifyOpenSearchedSpecification1", driver);
    Reporter.logPassWithScreenshot(Ta.getMessage(), fileName, fileName);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
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
        " and Target Resolution is " + getJazzPageFactory().getCCMWorkItemEditorPage().getResolution() + " for " + workItemId1);
    fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(),
        "targetStateAndResolution1", driver);
    Reporter.logPassWithScreenshot("Target State and Resolution validated successfully", fileName, fileName);
    
    //verifying and closing workitem 2
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(workItemId2);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSearchedSpecification(workItemId2);
    Ta =  getJazzPageFactory().getCCMProjectAreaDashboardPageVerification().verifyOpenSearchedSpecification(params.get("type") + " " + workItemId2, null);
    if(Ta.getState().equals("FAILED")) {
      throw new WebAutomationException(Ta.getMessage());
    }
    fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(),
        "verifyOpenSearchedSpecification2", driver);
    Reporter.logPassWithScreenshot(Ta.getMessage(), fileName, fileName);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
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
        " and Target Resolution is " + getJazzPageFactory().getCCMWorkItemEditorPage().getResolution() + " for " + workItemId2);
    fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(),
        "targetStateAndResolution2", driver);
    Reporter.logPassWithScreenshot("Target State and Resolution validated successfully", fileName, fileName);
        
  }
}
