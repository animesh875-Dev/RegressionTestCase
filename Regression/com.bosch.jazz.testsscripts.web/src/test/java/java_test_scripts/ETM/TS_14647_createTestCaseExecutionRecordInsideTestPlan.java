/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NEE2KOR
 */
public class TS_14647_createTestCaseExecutionRecordInsideTestPlan extends AbstractFrameworkTest {

  /**
   * The Test Cases should test the creation of Test Case Execution Records as well as the creation of Test Suite
   * Execution Records. With this we should also see if the selection of Iterations and Test Environments is working
   * fine.
   *
   * @throws IOException , on any exception occur
   * @throws InvalidKeyException , on any exception occur
   */
  @Test
  public void tcs_14647_createTestCaseExecutionRecordInsideTestPlan() throws IOException, InvalidKeyException {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String testCaseId = this.testDataRule.getConfigData("TESTCASE_ID");
    String testEnvName = this.testDataRule.getConfigData("TEST_ENV_NAME");
    String testCaseExeRecName = this.testDataRule.getConfigData("TESTCASE_EXE_REC_NAME");
    String iteration = this.testDataRule.getConfigData("ITERATION");
    String testId = this.testDataRule.getConfigData("TEST_PLAN_ID");

    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // click on planning tab
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(8);
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.PLANNING.toString());
    Reporter.logPass("Clicked on planing tab");
    // click on browse test 'plans'
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Plans");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    Reporter.logPass("clicked on browse test plans");
    // click on slidedown
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectSlideDown();
    // click on existing test case
    additionalParams.put("testArtifactName", "Test Case");
    additionalParams.put("artifactID", testId);
    getJazzPageFactory().getRQMConstructionPage().filterRqmArtifacts(additionalParams);
    Reporter.logPass("Browse Test Plan Done Successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // click on "Test Cases" under section menu
    getJazzPageFactory().getRQMConstructionPage().openRQMSection(RQMSectionMenus.TEST_CASES.toString());
    // search existing test case ID
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecifications(testCaseId);
    // search any one of the test case by clicking on check box and click on "Generate Execution Record".
    getJazzPageFactory().getRQMPlanningPage().pageScrollBar();
    getJazzPageFactory().getRQMPlanningPage().clickOnGenerateNewExecutionRecordButton(testCaseId);
    // search existing test env.
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecifications(testEnvName);
    // select on existing test environment
    additionalParams.put("dialogName", "Generate Test Case Execution Records");
    additionalParams.put("artifactName", testEnvName);
    additionalParams.put("finish and save", "Finish and Save Test Plan");
    getJazzPageFactory().getRQMPlanningPage().setIterationAndTestEnvironment(additionalParams);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMPlanningPage().pageScrollBar();
    // click on "Test Case execution records" under section menu
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_CASE_EXECUTION_RECORDS.toString());
    // Verify newly created Test Case Execution Record is listed there
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(testCaseExeRecName));
    // Verify attribute "Iteration" is set to the selected Iteration
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(iteration));
    // Verify attribute "Test Environment" is set to the selected Test Environment
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(testEnvName));
    // search the created Test Case Execution Record
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecifications(testCaseExeRecName);
    // Deleting the created Test Case Execution Record
    getJazzPageFactory().getRQMPlanningPage().deleteTestExecutionRecord(testCaseExeRecName,"");
  }
}
