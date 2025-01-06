/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_13509_executeTestSuiteManuallyByCreatingTSERContinueWhenNotPass extends AbstractFrameworkTest {

  /**
   * The Test Cases test whether we are able to execute test suite manually by creating TSER and verify whether
   * execution is continuing after failing any test case in the test suite.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13509_executeTestSuiteManuallyByCreatingTSERContinueWhenNotPass() throws Throwable {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String testSuiteId = this.testDataRule.getConfigData("TEST_SUITE_ID");
    String testPlanName = this.testDataRule.getConfigData("TEST_PLAN_NAME");
    String testEnvironmentName = this.testDataRule.getConfigData("TEST_ENVIRONMENT_NAME");
    String iteration = this.testDataRule.getConfigData("ITERATION");
    String dialogName = this.testDataRule.getConfigData("DIALOG_NAME");
    String testSuiteExeRecName = this.testDataRule.getConfigData("TEST_SUITE_EXECUTION_RECORD_NAME");

    // Log in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Click on CONSTRUCTION tab
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.CONSTRUCTION.toString());
    Reporter.logPass("Clicked on 'Construction' tab.");
    // Click on browse Test Suites.
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Suites");
    Reporter.logPass("Clicked on 'Browse' Test Suites.");
    // Search Test Suite id in 'Type to Filter Text Box'.
    getJazzPageFactory().getRQMConstructionPage().searchRqmArtifactsInFilterTextBox(testSuiteId);
    Reporter.logPass("Searched '" + testSuiteId + "' test suite id in 'Type filter text box'.");
    // Select the searched test suite id.
    getJazzPageFactory().getRQMConstructionPage().selectRqmArtifact(testSuiteId);
    Reporter.logPass("Selected '" + testSuiteId + "' from searched results.");
    // Open Test Suite Execution Record section in Test Suite.
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString());
    Reporter.logPass(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString() + " opened successfully");
    // Generate New Test Suite Execution record.
    getJazzPageFactory().getRQMConstructionPage().generateNewTestSuiteExecutionRecord(testPlanName);
    Reporter.logPass("New Test Suite Execution record is generated using " + testPlanName);
    // Search 'Test Environment' name in 'Generate Test Suite Execution Records' dialog.
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecificationsForTestEnvironment(testEnvironmentName,
        dialogName);
    Reporter.logPass("Searched '" + testEnvironmentName + "' in diaolg " + dialogName);
    // Generate Test Environment for the Test Suite.
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("dialogName", "Generate Test Suite Execution Records");
    additionalParams.put("testEnvName", testEnvironmentName);
    additionalParams.put("Iteration", iteration);
    additionalParams.put("finish and save", "Finish and Save Test Suite");
    getJazzPageFactory().getRQMPlanningPage().setIterationAndTestEnvironment(additionalParams);
    Reporter.logPass("Test Environment is generated.");
    // Click on "Test Suite execution record" under section menu
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString());
    Reporter.logPass(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString() + " opened successfully");
    // Verify 'Test Suite Execution Record' created successfully.
    getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(testSuiteExeRecName);
    Reporter.logPass("Verified test suite execution record created " + testSuiteExeRecName);
    // Click on created test suite execution record.
    getJazzPageFactory().getRQMExecutionPage().clickOnTestSuiteExecutionRecord(testSuiteExeRecName);
    Reporter.logPass("Clicked on created test suite execution record " + testSuiteExeRecName);
    // Click on 'Run Test Suite' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Run Test Suite (Ctrl+Shift+X)");
    Reporter.logPass("Clicked on 'Run Test Suite' button.");
    // Click on 'Run' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnCellButton("Run");
    Reporter.logPass("Clicked on 'Run' button.");
    // Click on 'Finish' button in 'Run Test Suite' dialog.
    getJazzPageFactory().getRQMExecutionPage().clickOnDialogButton("Run Test Suite", "Finish");
    Reporter.logPass("Run the test suite.");
    // Click on 'Start Manual Step' link.
    getJazzPageFactory().getRQMExecutionPage().clickOnStartManualStep();
    Reporter.logPass("Clicked on 'Start Manual Step' link.");
    // Apply 'Pass' for all the steps of the test case to make the test case 'Passed'.
    getJazzPageFactory().getRQMExecutionPage().applyAllStepResult("Pass");
    Reporter.logPass("Make the first test case as Pass.");
    // Click on 'Close' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzSpanButtons("Close");
    Reporter.logPass("Click on 'Close' button.");
    // Click on 'Start Manual Step' link.
    getJazzPageFactory().getRQMExecutionPage().clickOnStartManualStep();
    Reporter.logPass("Clicked on 'Start Manual Step' link.");
    // Apply 'Fail' for all the steps of the test case to make the test case 'Failed'.
    getJazzPageFactory().getRQMExecutionPage().applyAllStepResult("Fail");
    Reporter.logPass("Make the second test case as Fail.");
    // Click on 'Close' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzSpanButtons("Close");
    Reporter.logPass("Click on 'Close' button.");
    // Click on 'Start Manual Step' link.
    getJazzPageFactory().getRQMExecutionPage().clickOnStartManualStep();
    Reporter.logPass("Clicked on 'Start Manual Step' link.");
    // Apply 'Pass' for all the steps of the test case to make the test case 'Passed'.
    getJazzPageFactory().getRQMExecutionPage().applyAllStepResult("Pass");
    Reporter.logPass("Make the third test case as Pass.");
    // Click on 'Close' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzSpanButtons("Close");
    Reporter.logPass("Click on 'Close' button.");
    // Verify first test case result is 'Pass'.
    Assert.assertTrue(
        getJazzPageFactory().getRQMExecutionPage().getTestCaseResultFromExecutionOrder("1").contains("Passed"));
    Reporter.logPass("Verified first test case result is 'Pass'.");
    // Verify second test case result is 'Fail'.
    Assert.assertTrue(
        getJazzPageFactory().getRQMExecutionPage().getTestCaseResultFromExecutionOrder("2").contains("Failed"));
    Reporter.logPass("Verified second test case result is 'Fail'.");
    // Verify third test case result is 'Pass'.
    Assert.assertTrue(
        getJazzPageFactory().getRQMExecutionPage().getTestCaseResultFromExecutionOrder("3").contains("Passed"));
    Reporter.logPass("Verified third test case result is 'Pass'.");
    // Click on 'Close and Show Results' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Close and Show Results");
    Reporter.logPass("Clicked on 'Close and Show Results' button.");
    // Verify Test Suite Result is 'Failed'.
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestSuiteExecutionResultStatus().equals("Failed"));
    Reporter.logPass("Verified Test Suite Result is 'Failed'.");
  }
}
