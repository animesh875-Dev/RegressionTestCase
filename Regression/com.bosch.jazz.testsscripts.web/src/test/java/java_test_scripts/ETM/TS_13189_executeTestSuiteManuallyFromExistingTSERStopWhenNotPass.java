/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_13189_executeTestSuiteManuallyFromExistingTSERStopWhenNotPass extends AbstractFrameworkTest {

  /**
   * This test case Execute test suite from already existing TSER and verify whether execution is not continuing after
   * any of the test case execution status changes to other then pass status.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13189_executeTestSuiteManuallyFromExistingTSERStopWhenNotPass() throws Throwable {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String testSuiteExeRecId = this.testDataRule.getConfigData("TEST_SUITE_EXECUTION_RECORD_ID");
    String testCaseName = this.testDataRule.getConfigData("TEST_CASE_NAME");

    // Log in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Click on EXECUTION tab
    getJazzPageFactory().getRQMExecutionPage().openMainMenuByMenuTitle(RQMMainMenus.EXECUTION.toString());
    Reporter.logPass("Clicked on 'Execution' tab.");
    // Click on browse Test Suites.
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Suite Execution Records");
    Reporter.logPass("Clicked on 'Browse' Test Suite Execution Records.");
    // Search Test Suite Execution Record in 'Type to Filter Text Box'.
    getJazzPageFactory().getRQMConstructionPage().searchRqmArtifactsInFilterTextBox(testSuiteExeRecId);
    Reporter.logPass("Searched '" + testSuiteExeRecId + "' test suite id in 'Type filter text box'.");
    // Select the searched Test Suite Execution Record.
    getJazzPageFactory().getRQMConstructionPage().selectRqmArtifact(testSuiteExeRecId);
    Reporter.logPass("Selected '" + testSuiteExeRecId + "' from searched results.");
    // Click on 'Run Test Suite' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Run Test Suite (Ctrl+Shift+X)");
    Reporter.logPass("Clicked on 'Run Test Suite' button.");
    // Click on 'Run' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnCellButton("Run");
    Reporter.logPass("Clicked on 'Run' button.");
    // Choose the option 'Stop suite execution if any test case does not pass' from 'Run Test Suite' dialog.
    getJazzPageFactory().getRQMExecutionPage()
        .chooseExecutionCheckPoint("Stop suite execution if any test case does not pass");
    Reporter.logPass("Selected 'Stop suite execution if any test case does not pass' option.");
    // Click on 'Finish' button in 'Run Test Suite' dialog.
    getJazzPageFactory().getRQMExecutionPage().clickOnDialogButton("Run Test Suite", "Finish");
    Reporter.logPass("Run the test suite.");
    // Click on 'Start Manual Step' link.
    getJazzPageFactory().getRQMExecutionPage().clickOnStartManualStep();
    Reporter.logPass("Clicked on 'Start Manual Step' link.");
    // Make the first test case as 'Pass'.
    getJazzPageFactory().getRQMExecutionPage().applyAllStepResult("Pass");
    Reporter.logPass("Make the first test case as Pass.");
    // Click on 'Close' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzSpanButtons("Close");
    Reporter.logPass("Click on 'Close' button.");
    // Click on 'Start Manual Step' link.
    getJazzPageFactory().getRQMExecutionPage().clickOnStartManualStep();
    Reporter.logPass("Clicked on 'Start Manual Step' link.");
    // Make the second test case as 'Error'.
    getJazzPageFactory().getRQMExecutionPage().applyAllStepResult("Error");
    Reporter.logPass("Make the second test case as Error.");
    // Click on 'Close' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzSpanButtons("Close");
    Reporter.logPass("Click on 'Close' button.");
    // Verify first test case result is 'Pass'.
    Assert.assertTrue(
        getJazzPageFactory().getRQMExecutionPage().getTestCaseResultFromExecutionOrder("1").contains("Passed"));
    Reporter.logPass("Verified first test case result is 'Pass'.");
    // Verify second test case result is 'Error'.
    Assert.assertTrue(
        getJazzPageFactory().getRQMExecutionPage().getTestCaseResultFromExecutionOrder("2").contains("Error"));
    Reporter.logPass("Verified second test case result is 'Error'.");
    // Verify third test case is not executed.
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getNotExecutedTestCase(testCaseName));
    Reporter.logPass("Verified third test case is not executed.");
    // Click on 'Close and Show Results' button.
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Close and Show Results");
    Reporter.logPass("Clicked on 'Close and Show Results' button.");
    // Verify Test Suite Result is 'Error'.
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestSuiteExecutionResultStatus().equals("Error"));
    Reporter.logPass("Verified Test Suite Result is 'Error'.");
  }
}
