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
public class TS_14648_createTestCaseExecutionRecordOutsideTestPlan extends AbstractFrameworkTest {


  /**
   * This is one of the Test Cases which are created to verify the Use Case (Requirement) 173336. The Test Cases should
   * test the creation of Test Case Execution Records as well as the creation of Test Suite Execution Records. With this
   * we should also see if the selection of Iterations and Test Environments is working fine.
   *
   * @throws IOException , on any exception occour
   * @throws InvalidKeyException , on any exception occour
   */
  @Test
  public void tcs_14648_createTestCaseExecutionRecordOutsideTestPlan() throws IOException, InvalidKeyException {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String browseTestCaseId = this.testDataRule.getConfigData("TESTCASE_ID");
    String testPlanName = this.testDataRule.getConfigData("TEST_PLAN_NAME");
    String searchTestEnvName = this.testDataRule.getConfigData("TEST_ENV_NAME");
    String testCaseExeRecName = this.testDataRule.getConfigData("TESTCASE_EXE_REC_NAME");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    // click on CONSTRUCTION tab
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.CONSTRUCTION.toString());
    Reporter.logPass("Clicked on construction tab");
    // click on browse test 'cases'
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Cases");
    Reporter.logPass("clicked on browse test cases");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Case");
    additionalParams.put("artifactID", browseTestCaseId);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    // click on slidedown
    getJazzPageFactory().getRQMConstructionPage().selectSlideDown();
    // click on required test case
    getJazzPageFactory().getRQMConstructionPage().filterRqmArtifacts(additionalParams);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    Reporter.logPass("Browse Test case Done Successfully");
    // click on "Test Case execution records" under section menu
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_CASE_EXECUTION_RECORDS.toString());
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    additionalParams.put("testEnvironmentNmae", "test");
    additionalParams.put("Generate New Execution Records", "Generate New Test Case Execution Records");
    additionalParams.put("Finish and Save", "Finish and Save Test Case");
    additionalParams.put("searchTestEnvName", searchTestEnvName);
    additionalParams.put("testPlanName", testPlanName);
    // click on "Generate Execution Record", select the 'Test Plan', 'Iteration','select Generate Test Env',Next,Next
    // and finish.
    getJazzPageFactory().getRQMConstructionPage().generateNewTestCaseExecutionRecord(additionalParams);
    Reporter.logPass("Record Successfully created");

    // Validation
    // The newly created Test Case Execution Record is listed there
    // The attribute "Iteration" is empty
    // The attribute "Test Environment" shows the new created Test Environment
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(testCaseExeRecName));
    // The attribute "Test Plan" shows a link to the selected Test Plan
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(searchTestEnvName));

    // teardown method
    // Delete the created Test Case Execution Record
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecifications(searchTestEnvName);
    // and also the created Test Environment
    getJazzPageFactory().getRQMPlanningPage().deleteTestExecutionRecord(searchTestEnvName,"");
  }
}
