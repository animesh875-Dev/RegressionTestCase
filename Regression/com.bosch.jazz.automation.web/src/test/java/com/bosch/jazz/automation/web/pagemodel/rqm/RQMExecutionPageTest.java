/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit test cases covers RQM Execution Page
 */
public class RQMExecutionPageTest extends AbstractFrameworkUnitTest {

  /**
   *
   */
  @Test
  public void testSelectOwner() {
    loadPage("rqm/select_lifecyclestate.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("moreLinkValue", "More...");
    additionalParams.put("userIdValue", "hcm6kor");
    additionalParams.put("ownerValue", "ADMIN - ADMIN");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_owner_dropdown.html");
    clickToPage.put(2, "rqm/select_owner_list.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    // rqm.selectOwner(additionalParams);
  }

  /**
   * Loads RQMExecution Page and click on start manual link.
   */
  @Test
  public void testClickOnStartManualStep() {
    loadPage("rqm/test_suite_execution_records_run_3.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.clickOnStartManualStep();
  }

  /**
   * Loads RQMExecution Page and get the test case result from Execution order.
   */
  @Test
  public void testGetTestCaseResultFromExecutionOrder() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.getTestCaseResultFromExecutionOrder("1").contains("Passed"));
  }

  /**
   * Loads RQMExecution Page and get the test suite result status.
   */
  @Test
  public void testGetTestSuiteExecutionResultStatus() {
    loadPage("rqm/test_suite_execution_records_run_7.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.getTestSuiteExecutionResultStatus().contains("Passed"));
  }

  /**
   * Loads RQMExecution Page and choose execution check point.
   */
  @Test
  public void testChooseExecutionCheckPoint() {
    loadPage("rqm/test_suite_execution_records_run_2.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.chooseExecutionCheckPoint("Schedule execution:");
  }

  /**
   * Loads RQMExecution Page and get not executed test case.
   */
  @Test
  public void testGetNotExecutedTestCase() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.getNotExecutedTestCase("test"));
  }

  /**
   * Loads RQMExecution Page and verify test case not run result.
   */
  @Test
  public void testGetTestCasesNotRunResult() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.getTestCasesNotRunResult());
  }

  /**
   * Loads RQMExecution Page and click on created record.
   */
  @Test
  public void testClickOnTestSuiteExecutionRecord() {
    loadPage("rqm/isTSER_Available.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.clickOnTestSuiteExecutionRecord("Expl Test suite_AMD64");
  }

  /**
   * Loads RQMExecution Page and apply all step verdict.
   */
  @Test
  public void testApplyAllStepResult() {
    loadPage("rqm/test_apply_step_result_1.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_apply_step_result_2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.applyAllStepResult("Pass");
  }

  /**
   * Loads RQMExecution Page and click on cell button.
   */
  @Test
  public void testClickOnCellButton() {
    loadPage("rqm/test_suite_execution_records_run_1.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.clickOnCellButton("Run");
  }

  /**
   * Loads RQMExecution Page and verify execution check point is selected.
   */
  @Test
  public void testIsExecutionCheckPointSelected() {
    loadPage("rqm/test_suite_execution_records_run_2.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isExecutionCheckPointSelected("Schedule execution:"));
  }

  /**
   * Loads RQMExecution Page and verify defect is visible.
   */
  @Test
  public void testIsDefectVisibleInDefectsSection() {
    loadPage("rqm/test_linkTestResultFromExistingDefect.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isDefectVisibleInDefectsSection("522257"));
  }

  /**
   * Loads RQMExecution Page and verify execution check point is selected.
   */
  @Test
  public void testGetTestCaseExecutionResultStatus() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.getTestCaseExecutionResultStatus();
  }

  /**
   * Loads RQMExecution Page and verify execution check point is selected.
   */
  @Test
  public void testGetTestExecutonRecordStepResult() {
    loadPage("rqm/get_test_case_execution_result_status.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.getTestExecutonRecordStepResult("1");
  }

  /**
   * Loads RQMExecution Page and verify execution check point is selected.
   */
  @Test
  public void testGetTestExecutonRecordStepResults() {
    loadPage("rqm/get_test_case_execution_result_status.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_suite_execution_records_run_2.html");
    clickToPage.put(2, "rqm/test_suite_execution_records_run_2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.getTestExecutonRecordStepResults();
  }

  /**
   * Loads RQMExecution Page and verify execution check point is selected.
   */
  @Test
  public void testClickOnAllPassButton() {
    loadPage("rqm/get_test_case_execution_result_status.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_suite_execution_records_run_2.html");
    clickToPage.put(2, "rqm/test_suite_execution_records_run_2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.clickOnAllPassButton();
  }

  /**
   * Loads RQMExecution Page and verify execution check point is selected.
   */
  @Test
  public void testGetTestCaseExecutionRecordsID() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_1.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.getTestCaseExecutionRecordsID();
  }

  /**
   * Loads RQMExecution Page and verify execution check point is selected.
   */
  @Test
  public void testClickOnLastResult() {
    loadPage("rqm/click_on_last_result.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.clickOnLastResult();
  }

  /**
   * linkToExistingDefect: link an existing defect to a test case result
   */
  @Test
  public void testLinkToExistingDefect() {
    loadPage("rqm/test_linkExistingDefectPage.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_linkExistingDefectPage_1.html");
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/test_linkExistingDefectPage_2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.linkExistingDefectFromTestResult("BBM ALM Dev (CCM)", "521748",
        "Exporting RMM data for GC with Component owned by Team area setting");
  }

  /**
   * removeLinkedDefect: removed the defect linked to test case result
   */
  @Test
  public void testRemoveLinkedDefect() {
    loadPage("rqm/test_removeExistingDefectPage.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/test_removeExistingDefectPage_1.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.removeLinkedDefect("521748", "Exporting RMM data for GC with Component owned by Team area setting");
  }


  /**
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testCreateANewDefectFromTheTestResult() throws Throwable {
    loadPage("rqm/create_A_New_Defect_From_The_Test_Result.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "rqm/create_A_New_Defect_From_The_Test_Result_1.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "rqm/create_A_New_Defect_From_The_Test_Result_2.html");
    clickNumberToPagePath.put(4, "rqm/create_A_New_Defect_From_The_Test_Result_3.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "rqm/create_A_New_Defect_From_The_Test_Result_5.html");
    clickNumberToPagePath.put(8, "rqm/create_A_New_Defect_From_The_Test_Result_6.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rqm.createANewDefectFromTheTestResult("BBM ALM Dev (CCM)", "TC_19946_createNewDefectFromTestResul", "BBM-ALM-Dev");
  }

  
  /**
   * Unit Test coverage method {@link RQMExecutionPage#clickOnTestArtifactFromExecutionResult(String)}
   */
  @Test
  public void testClickOnTestArtifactFromExecutionResult() {
    loadPage("rqm/clickOnLinkTSER.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.clickOnTestArtifactFromExecutionResult("Test Plan");
  }
  /**
   * Unit Test coverage method {@link RQMExecutionPage#getDropdownValue(String)}
   */
  @Test
  public void testGetDropdownValue() {
    loadPage("rqm/clickOnLinkTSER.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.getDropdownValue("Actual Result:");
  }
  /**
   * Unit Test coverage method {@link RQMExecutionPage#checkLastResultTestExecutionRecord(String, String, String,String)}
   */
  @Test
  public void testCheckLastResultTestExecutionRecord() {
    loadPage("rqm/tserLastResult.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    rqm.checkLastResultTestExecutionRecord("Passed", "iFix013 test suite", "23Q_NonGC","Test Iteration");
  }
  
  /**
   * Unit Test coverage method {@link RQMExecutionPage#setDefaultQuery(Map)}
   * @author LTU7HC
   */
  @Test
  public void testSetDefaultQuery() {
    loadPage("rqm/testcase_result_page.html");
    RQMExecutionPage rqm = getJazzPageFactory().getRQMExecutionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "rqm/set_default_query_testcase_results.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "rqm/set_default_query_successfully.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("option", "Select...");
    additionalParams.put("queryOption", "Shared Queries");
    additionalParams.put("folderName", "Predefined");
    additionalParams.put("defaultQueryForListView", "All Current Test Case Results");
    additionalParams.put("buttonName", "OK");
    rqm.setDefaultQuery(additionalParams);
  }
}
