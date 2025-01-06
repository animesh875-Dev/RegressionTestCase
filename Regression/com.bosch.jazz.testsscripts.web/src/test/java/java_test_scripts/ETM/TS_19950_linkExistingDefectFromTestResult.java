/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_19950_linkExistingDefectFromTestResult extends AbstractFrameworkTest {

  /**
   * The Test Cases test whether we are able to execute test suite manually by creating TSER and verify whether
   * execution is continuing after failing any test case in the test suite.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_19950_linkExistingDefectFromTestResult() throws Throwable {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String globalArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");

    String ccmProjectAreaName = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String testCaseResultName = this.testDataRule.getConfigData("TEST_CASE_RESULT_NAME");
    String testCaseResultId = this.testDataRule.getConfigData("TEST_CASE_RESULT_ID");
    String existingDefectName = this.testDataRule.getConfigData("EXISTING_DEFECT_NAME");
    String existingDefectID = this.testDataRule.getConfigData("EXISTING_DEFECT_ID");

    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectProjectAreaAndGlobalConfiguration(projectArea, globalArea,
        componentName, streamName);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // click on Execution tab
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.EXECUTION.toString());
    Reporter.logPass("Clicked on Execution tab");

    // click on browse test 'plans'
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Case Results");
    Reporter.logPass("clicked on Browse Test Case Results");

    // click on existing Test Case Result
    getJazzPageFactory().getRQMConstructionPage().searchRqmArtifactsInFilterTextBox(testCaseResultId);
    getJazzPageFactory().getRQMConstructionPage().selectRqmArtifact(testCaseResultId);
    Reporter.logPass("Browse To Test Case Result Successfully");

    // Click on Defect tab
    getJazzPageFactory().getRQMExecutionPage().openRQMSection(RQMSectionMenus.DEFECTS.toString());
    Reporter.logPass(RQMSectionMenus.TEST_SCRIPTS.toString() + " opened successfully");
    getJazzPageFactory().getRQMExecutionPage().waitForSecs(5);

    // Link an Existing defect into TC Result
    getJazzPageFactory().getRQMExecutionPage().linkExistingDefectFromTestResult(ccmProjectAreaName, existingDefectID,
        existingDefectName);

    // Click Save button
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzButtons("Save");
    Reporter.logPass("Test Case Result is saved successfully");
    getJazzPageFactory().getRQMExecutionPage().waitForSecs(5);

    // Click on Defect link
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Refresh");
    getJazzPageFactory().getRQMExecutionPage().waitForSecs(5);
    getJazzPageFactory().getRQMExecutionPage().openMainMenuByMenuTitle(existingDefectID + ": " + existingDefectName);
    Reporter.logPass("Clicked on the existing defect - " + existingDefectName);

    // Click Link Tab
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab("Links");
    getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(testCaseResultName);
    Reporter.logPass("Open the Links Tab");

    // Remove defect link
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", testCaseResultName,
        "Affects Test Case Result");
    getJazzPageFactory().getRQMExecutionPage().openRQMSection(RQMSectionMenus.DEFECTS.toString());
    getJazzPageFactory().getRQMExecutionPage().waitForSecs(5);
    getJazzPageFactory().getRQMExecutionPage().removeLinkedDefect(existingDefectID, existingDefectName);
    Reporter.logPass(existingDefectName + " is removed successfully");
    getJazzPageFactory().getRQMExecutionPage().waitForSecs(5);

    // Click Save button
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzButtons("Save");
    Reporter.logPass("Test Case Result is saved successfully");
    getJazzPageFactory().getRQMExecutionPage().waitForSecs(5);

  }
}
