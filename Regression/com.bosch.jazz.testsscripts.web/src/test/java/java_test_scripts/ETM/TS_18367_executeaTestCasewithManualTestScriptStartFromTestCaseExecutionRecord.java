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
 * This test case represents 18367 Execute a Test Case with manual Test Script - start from Test Case Execution Record.
 */
public class TS_18367_executeaTestCasewithManualTestScriptStartFromTestCaseExecutionRecord
    extends AbstractFrameworkTest {

  /**
   * This test case Browse KeyWords,search for the test script inside Keywords. Create new manual step in the test
   * script, delete the added step and validate new step is added and also deleted from the test script.
   * 
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void ts_18367_executeaTestCasewithManualTestScriptStartFromTestCaseExecutionRecord() throws Throwable {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String testCaseExecutionRecordsID = this.testDataRule.getConfigData("Test_Case_Execution_Records_ID");

    // Log in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    // Click on CONSTRUCTION tab
    getJazzPageFactory().getRQMExecutionPage().handleAlert();
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.EXECUTION.toString());
    Reporter.logPass("Clicked on construction tab");
    // Click on browse Test Scripts.
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Case Execution Records");
    Reporter.logPass("Clicked on browse keywords");
    // Click on slide down.
    getJazzPageFactory().getRQMExecutionPage().handleAlert();
    // Click on existing test script.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecifications(testCaseExecutionRecordsID);
    Reporter.logPass(testCaseExecutionRecordsID + " opened successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMExecutionPage().handleAlert();
    getJazzPageFactory().getRQMExecutionPage().selectCategory(testCaseExecutionRecordsID);
    Reporter.logPass(testCaseExecutionRecordsID + "Clicked successfully.");
    getJazzPageFactory().getRQMExecutionPage().handleAlert();
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Run Test Case  (Ctrl+Shift+X)");
    getJazzPageFactory().getRQMExecutionPage().handleAlert();
    getJazzPageFactory().getRQMExecutionPage().clickOnCellButton("Run");
    Reporter.logPass(testCaseExecutionRecordsID + "Clicked on Run button.");
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Pass (Ctrl+Shift+P)");
    Reporter.logPass("Clicked on Pass button.");
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Fail (Ctrl+Shift+F)");
    Reporter.logPass("Clicked on Fail button.");
    getJazzPageFactory().getRQMExecutionPage().clickOnAllPassButton();
    Reporter.logPass("Clicked on Pass button.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    getJazzPageFactory().getRQMExecutionPage().handleAlert();
    getJazzPageFactory().getRQMExecutionPage().clickOnJazzImageButtons("Show Result");
    Reporter.logPass("Clicked on Show Result button.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestCaseExecutionResultStatus().equals("Failed"));
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestExecutonRecordStepResult("1").equals("Pass"));
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestExecutonRecordStepResult("2").equals("Fail"));
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestExecutonRecordStepResult("3").equals("Pass"));
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestExecutonRecordStepResult("4").equals("Pass"));
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestExecutonRecordStepResult("5").equals("Pass"));
    Assert.assertTrue(getJazzPageFactory().getRQMExecutionPage().getTestExecutonRecordStepResult("6").equals("Pass"));
  }
}
