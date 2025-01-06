/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author PSV5KOR This class contains test cases used to create TSER using iterations and test environment selected
 *         inside a Test Plan.
 */
public class TS_14649_createTestSuiteExecutionRecordInsideTestPlan extends AbstractFrameworkTest {

  /**
   * The Test Cases should test the creation of TSER. And also test whether the selection of Iterations and Test
   * Environments is working fine.
   *
   * @throws InvalidKeyException , on any exception occur
   */
  @Test
  public void tcs_14649_Create_TSER_Inside_TPL() throws InvalidKeyException {
    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String testId = this.testDataRule.getConfigData("TEST_PLAN_ID");
    String testSuiteID = this.testDataRule.getConfigData("TEST_SUITE_ID");
    String testSuiteName = this.testDataRule.getConfigData("TEST_SUITE_NAME");

    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // click on planning tab
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.PLANNING.toString());
    Reporter.logPass("Clicked on planing tab");
    // click on browse test 'plans'
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Plans");

    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    // click on slidedown
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectSlideDown();
    // click on existing first test case
    additionalParams.put("testArtifactName", "Test Case");
    additionalParams.put("artifactID", testId);
    getJazzPageFactory().getRQMConstructionPage().filterRqmArtifacts(additionalParams);
    Reporter.logPass("Browse Test Plan Done Successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // click on "Test Cases" under section menu
    getJazzPageFactory().getRQMConstructionPage().openRQMSection(RQMSectionMenus.TEST_SUITES.toString());
    getJazzPageFactory().getRQMPlanningPage().searchTestSpecifications(testSuiteID);
    // select any one of the test case by clicking on check box and click on "Generate Execution Record".
    getJazzPageFactory().getRQMPlanningPage().pageScrollBar();
    getJazzPageFactory().getRQMPlanningPage().clickOnGenerateNewExecutionRecordButton(testSuiteID);
    Reporter.logPass("Clicked on Generate Execution Record");
    additionalParams.put("Iteration", this.testDataRule.getConfigData("ITERATION"));
    additionalParams.put("TestEnvironment", this.testDataRule.getConfigData("TEST_ENVIRONMENT"));
    getJazzPageFactory().getRQMPlanningPage().selectIterationAndGenerateTestEnvironment(additionalParams);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMPlanningPage().pageScrollBar();
    // click on "Test Case execution records" under section menu
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString());
    // Verify newly created Test Case Execution Record is listed there
    additionalParams.put("TSER_Name", testSuiteName);
    assertTrue(getJazzPageFactory().getRQMPlanningPage().isTestSuiteExecutionRecordsVisible(additionalParams));
    // click on "Test Environments" under section menu
    getJazzPageFactory().getRQMConstructionPage().openRQMSection(RQMSectionMenus.TEST_ENVIRONMENTS.toString());
    getJazzPageFactory().getRQMPlanningPage().pageScrollBar();
    boolean isEnvPresent = getJazzPageFactory().getRQMPlanningPage().isTestEnvironmentAdded(additionalParams);
    assertTrue(isEnvPresent);
    // Deleting the created Test Case Execution Record
    getJazzPageFactory().getRQMPlanningPage().deleteTestEnvironment(additionalParams);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMPlanningPage().pageScrollBar();
    getJazzPageFactory().getRQMConstructionPage()
        .openRQMSection(RQMSectionMenus.TEST_SUITE_EXECUTION_RECORDS.toString());
    getJazzPageFactory().getRQMPlanningPage().deleteTestExecutionRecord(testSuiteName,"");
    Reporter.logPass("Clicked on Generate Execution Record");
  }
}
