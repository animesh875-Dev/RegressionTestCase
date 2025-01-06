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
public class TS_14651_createTestSuiteExecutionRecordOutSideTestPlan extends AbstractFrameworkTest {

  /**
   * The Test Suite should test the creation of Test Suite Execution Records as well as the creation of Test Suite
   * Execution Records. With this we should also see if the selection of Iterations and Test Environments is working
   * fine.
   *
   * @throws IOException , on any exception occour
   * @throws InvalidKeyException , on any exception occour
   */
  @Test
  public void ts_14651_createTestSuiteExecutionRecordOutSideTestPlan() throws IOException, InvalidKeyException {
    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String suiteId = this.testDataRule.getConfigData("SUITE_ID");
    String testPlanName = this.testDataRule.getConfigData("TEST_PLAN_NAME");
    String testEnvName = this.testDataRule.getConfigData("TEST_ENV_NAME");
    String dialogName = this.testDataRule.getConfigData("DIALOG_NAME");
    String testSuiteExeRecName = this.testDataRule.getConfigData("TEST_SUITE_EXE_REC_NAME");
    String iteration = this.testDataRule.getConfigData("ITERATION");
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // click on Construction tab
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.CONSTRUCTION.toString());
    Reporter.logPass("Clicked on planing tab");
    // click on browse test 'test suites'
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Suites");
    Reporter.logPass("clicked on browse test plans");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    // click on slidedown
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectSlideDown();
    // click on existing test suite
    additionalParams.put("testArtifactName", "Test Suites");
    additionalParams.put("artifactID", suiteId);
    getJazzPageFactory().getRQMConstructionPage().filterRqmArtifacts(additionalParams);
    Reporter.logPass("Browse Test Plan Done Successfully");
    // click on "Test Suite Execution Record" under section menu
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString());
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    // click on "Generate Execution Record", select the 'Test Plan', 'Iteration','select Reuse Existing Test
    // Environments'.
    getJazzPageFactory().getRQMConstructionPage().generateNewTestSuiteExecutionRecord(testPlanName);
    Reporter.logPass("Record Successfully created");
    // search existing test env.
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecificationsForTestEnvironment(testEnvName, dialogName);
    // generate the Test Suite Execution Record
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    additionalParams.put("dialogName", "Generate Test Suite Execution Records");
    additionalParams.put("artifactName", testEnvName);
    additionalParams.put("finish and save", "Finish and Save Test Suite");
    getJazzPageFactory().getRQMPlanningPage().setIterationAndTestEnvironment(additionalParams);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // click on "Test Suite execution record" under section menu
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString());
    // Verify newly created Test Case Execution Record is listed there
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(testSuiteExeRecName));
    // Verify attribute "Iteration" is set to the selected Iteration
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(iteration));
    // Verify attribute "Test Environment" is set to the selected Test Environment
    assertTrue(getJazzPageFactory().getRQMPlanningPage().getExecutionRecordDetails(testEnvName));
    // search the created Test Case Execution Record
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecifications(testSuiteExeRecName);
    // Deleting the created Test Suite Execution Record.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMPlanningPage().deleteTestExecutionRecord(testSuiteExeRecName,"'");
  }
}

