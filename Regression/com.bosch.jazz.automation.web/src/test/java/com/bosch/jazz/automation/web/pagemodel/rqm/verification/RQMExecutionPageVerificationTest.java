/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMExecutionPageVerification;

/**
 * This page represents RQMExecution Page Verification.
 */
public class RQMExecutionPageVerificationTest extends AbstractFrameworkUnitTest {


  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifySelectCategory(String,String)}.
   * 
   * <p>loads execution record page and verifies .
   */
  @Test
  public void testVerifySelectCategory() {
    loadPage("rqm/verify_select_category.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.verifySelectCategory("", "");
    rqm.verifySelectCategory("66653", "");
    rqm.verifySelectCategory("TCS_ PS-XS -  Export CSV/Excel is not working in ALM-23-P server_02T1_TestServer", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifySelectCategory(String,String)}.
   * 
   * <p>loads execution record page and verifies .
   */
  @Test
  public void tesVerifyClickOnJazzImageButtons() {
    loadPage("rqm/verify_select_category.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.verifySelectCategory("", "");
    rqm.verifySelectCategory("66653", "");
    rqm.verifySelectCategory("TCS_ PS-XS -  Export CSV/Excel is not working in ALM-23-P server_02T1_TestServer", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetExecutionRecordMessageSummary(String,String)}.
   * 
   * <p>loads execution record page and verifies .
   */
  @Test
  public void tesVerifyGetExecutionRecordMessageSummary() {
    loadPage("rqm/verify_get_execution_record_messageSummary.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.verifyGetExecutionRecordMessageSummary("Fail", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyclickOnJazzImageButtons(String, String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on 'Run Test Suite' button.
   */
  @Test
  public void testVerifyClickOnJazzImageButtonsRunTestSuite() {
    loadPage("rqm/test_suite_execution_records_run_1.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyclickOnJazzImageButtons("Run Test Suite (Ctrl+Shift+X)", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyclickOnJazzImageButtons(String, String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyClickOnJazzImageButtonsCloseAndShowResults() {
    loadPage("rqm/test_suite_execution_records_run_6.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyclickOnJazzImageButtons("Close and Show Results", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyclickOnJazzImageButtons(String, String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on 'Close And Show Results' button with false message.
   */
  @Test
  public void testVerifyClickOnJazzImageButtonsCloseAndShowResultstwo() {
    loadPage("rqm/test_suite_execution_records_run_1.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyclickOnJazzImageButtons("Close and Show Results", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnStartManualStep(String)}.
   * 
   * <p>Loads RQM Execution page and Verify 'Start Manual Step' link is clicked.
   */
  @Test
  public void testVerifyClickOnStartManualStep() {
    loadPage("rqm/test_suite_execution_records_run_4.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnStartManualStep("");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnStartManualStep(String)}.
   * 
   * <p>Loads RQM Execution page and Verify 'Start Manual Step' link is clicked with False message.
   */
  @Test
  public void testVerifyClickOnStartManualStepTwo() {
    loadPage("rqm/test_suite_execution_records_run_3.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnStartManualStep("");
  }

  /**
    * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyApplyAllStepResult(String, String)}.
    * 
    * <p>Loads RQM Execution page and Verify all step result is applied.
    */
  @Test
  public void testVerifyApplyAllStepResult() {
    loadPage("rqm/verify_apply_all_step_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyApplyAllStepResult("Pass", "");
  }

  /**
    * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyApplyAllStepResult(String, String)}.
    * 
    * <p>Loads RQM Execution page and Verify all step result is applied with False message.
    */
  @Test
  public void testVerifyApplyAllStepResultTwo() {
    loadPage("rqm/verify_apply_all_step_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyApplyAllStepResult("Fail", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnTestSuiteExecutionRecord(String, String)}.
   *
   * <p>Loads RQM Execution page and Verify clicked on 'Test Suite Execution Record'.
   */
  @Test
  public void testVerifyClickOnTestSuiteExecutionRecord() {
    loadPage("rqm/testScriptsID.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnTestSuiteExecutionRecord("3742", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnDialogButton(String, String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify 'Run Test Suite' dialog button is clicked.
   */
  @Test
  public void testVerifyClickOnDialogButton() {
    loadPage("rqm/delete_testexe_record_button.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnDialogButton("Run Test Suite", "Finish", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnJazzSpanButtons(String, String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on 'Close' button.
   */
  @Test
  public void testVerifyClickOnJazzSpanButtons() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnJazzSpanButtons("Close", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetTestCaseResultFromExecutionOrder(String, Map,String)}.
   * 
   * <p>Loads RQM Execution page and Verify Test Case Execution result.
   */
  @Test
  public void testVerifyGetTestCaseResultFromExecutionOrder() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("VerificationType", "TestCaseExecutionResult");
    clickToPage.put("1", "Passed");
    assertNotNull(rqm);
    rqm.verifyGetTestCaseResultFromExecutionOrder("1", clickToPage, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetNotExecutedTestCase(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify not executed test case.
   */
  @Test
  public void testVerifyGetNotExecutedTestCase() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetNotExecutedTestCase("abc", "true");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetNotExecutedTestCase(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify not executed test case with False message.
   */
  @Test
  public void testVerifyGetNotExecutedTestCaseTwo() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetNotExecutedTestCase("abc", "false");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetNotExecutedTestCase(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify check point is selected.
   */
  @Test
  public void testVerifyChooseExecutionCheckPoint() {
    loadPage("rqm/test_suite_execution_records_run_2.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyChooseExecutionCheckPoint("Schedule execution:", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetTestCasesNotRunResult(String)}.
   * 
   * <p>Loads RQM Execution page and Verify test case not run result.
   */
  @Test
  public void testVerifyGetTestCasesNotRunResult() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetTestCasesNotRunResult("true");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetTestCasesNotRunResult(String)}.
   *
   * <p>Loads RQM Execution page and Verify test case not run result with 'False' message.
   */
  @Test
  public void testVerifyGetTestCasesNotRunResultTwo() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetTestCasesNotRunResult("false");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetTestCasesNotRunResult(String)}.
   *
   * <p>Loads RQM Execution page and Verify clicked on Cell button 'Run'.
   */
  @Test
  public void testVerifyClickOnCellButton() {
    loadPage("rqm/test_suite_execution_records_run_5.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnCellButton("Run", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyIsExecutionCheckPointSelected(String,String)}.
   * 
   * <p>Loads RQM Execution page and verify check point is not selected.
   */
  @Test
  public void testVerifyIsExecutionCheckPointSelected() {
    loadPage("rqm/test_suite_execution_records_run_2.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsExecutionCheckPointSelected("Schedule execution:", "true");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyIsDefectVisibleInDefectsSection(String,String)}.
   * 
   * <p>Loads RQM Execution page and verify defect is visible
   */
  @Test
  public void testVerifyIsDefectVisibleInDefectsSection() {
    loadPage("rqm/test_linkTestResultFromExistingDefect.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsDefectVisibleInDefectsSection("522257", "true");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyIsDefectVisibleInDefectsSection(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyclickOnJazzImageButtons() {
    loadPage("rqm/test_case_execution_records_run_first.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyclickOnJazzImageButtons("Run Test Case  (Ctrl+Shift+X)", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyclickOnJazzImageButtons(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyClickOnJazzImageButtons() {
    loadPage("rqm/test_case_execution_records_run_first.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyclickOnJazzImageButtons("Show Result", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyclickOnJazzImageButtons(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyClIckOnJazzImageButtons() {
    loadPage("rqm/test_case_execution_records_run_fourth.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyclickOnJazzImageButtons("Pass (Ctrl+Shift+P)", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyclickOnJazzImageButtons(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerIfyClIckOnJazzImageButtons() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyclickOnJazzImageButtons("Fail (Ctrl+Shift+F)", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnAllPassButton(String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyClickOnAllPassButton() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnAllPassButton("");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetTestExecutonRecordStepResults(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyGetTestExecutonRecordStepResults() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetTestExecutonRecordStepResults("pass", "pass,pass");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifygetTestCaseExecutionResultStatus(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifygetTestCaseExecutionResultStatus() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifygetTestCaseExecutionResultStatus("Inconclusive", "Inconclusive");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifygetTestCaseExecutionResultStatus(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerIfygetTestCaseExecutionResultStatus() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifygetTestCaseExecutionResultStatus("Inconclusive", "pass");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifygetTestExecutonRecordStepResult(String,String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifygetTestExecutonRecordStepResult() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifygetTestExecutonRecordStepResult("", "true", "true");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifygetTestExecutonRecordStepResult(String,String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerIfygetTestExecutonRecordStepResult() {
    loadPage("rqm/actual_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifygetTestExecutonRecordStepResult("", "true", "false");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnLastResult(String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyClickOnLastResult() {
    loadPage("rqm/click_on_last_result.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnLastResult("");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetTestCaseExecutionRecordsID(String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyGetTestCaseExecutionRecordsID() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_1.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetTestCaseExecutionRecordsID("10739");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyOpenRQMSection(String, String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyOpenRQMSection() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_1.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyOpenRQMSection("Summary", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyChooseDropdownAndSetValueInExecutionRecordDialog(String, String, String, String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyChooseDropdownAndSetValueInExecutionRecordDialog() {
    loadPage("rqm/execution_record_run_test_case_page.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.verifyChooseDropdownAndSetValueInExecutionRecordDialog("", "Test Plan:", "Unassigned", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnTab(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on'Close And Show Results' button.
   */
  @Test
  public void testVerifyClickOnTab() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_1.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnTab("Generate a New Test Case Execution Record", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnDialogButton(String,String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on 'Run Test Suite' button.
   */
  @Test
  public void testverifyClickOnDialogButton() {
    loadPage("rqm/verify_click_on_dialog_button_1.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnDialogButton("Run Test Suite", "Pause", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnDialogButton(String,String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on 'Close' button.
   */
  @Test
  public void testVerifyClickOnDialogButtonTwo() {
    loadPage("rqm/verify_click_on_dialog_button_2.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnDialogButton("Confirmation", "Yes", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on 'Stop Run' button.
   */
  @Test
  public void testverifyClickOnJazzSpanButtons() {
    loadPage("rqm/verify_click_on_dialog_button_1.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnJazzSpanButtons("Stop Run", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyClickOnLastResult(String)}.
   * 
   * <p>Loads RQM Execution page and Verify clicked on 'Last Result'.
   */
  @Test
  public void testverifyClickOnLastResult() {
    loadPage("rqm/verify_click_on_cell_button_run_test_suite.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnLastResult("");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyGetExecutionRecordMessageSummary(String,String)}.
   * 
   * <p>Loads RQM Execution page and Verify execution record message summary.
   */
  @Test
  public void testVerifyGetExecutionRecordMessageSummary() {
    loadPage("rqm/verify_click_on_cell_button_run_test_suite.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetExecutionRecordMessageSummary("Fail", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyLinkExistingDefectFromTestResult(String, String, String, String)}.
   *
   * <p>Loads RQM Execution page and Verify adding existing defect into Test Result
   */
  @Test
  public void testVerifyLinkExistingDefectFromTestResult() {
    loadPage("rqm/test_removeExistingDefectPage.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    assertEquals("PASSED", rqm.verifyLinkExistingDefectFromTestResult("BBM ALM Dev (CCM)", "521748",
        "Exporting RMM data for GC with Component owned by Team area setting", "").getState());
  }

  /**
   * <p>Unit test coverage for {@link RQMExecutionPageVerification#verifyRemoveLinkedDefect(String, String, String)}.
   * 
   * <p>Loads RQM Execution page and Verify removing existing defect from Test Result
   */
  @Test
  public void testverifyRemoveLinkedDefect() {
    loadPage("rqm/test_linkExistingDefectPage.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    assertEquals("PASSED", rqm
        .verifyRemoveLinkedDefect("521748", "Exporting RMM data for GC with Component owned by Team area setting", "")
        .getState());
  }

  /**
   * Unit Test coverage method {@link RQMExecutionPageVerification#verifySelectedValue(String,String,String, String)}
   */
  @Test
  public void testVerifySelectedValue() {
    loadPage("rqm/clickOnLinkTSER.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectedValue("","Test Suite Execution Record","Passed","Passed");
  } 
  /**
   * Unit Test coverage method {@link RQMExecutionPageVerification#verifyClickOnTestArtifactFromExecutionResult(String,String)}
   */
  @Test
  public void testVerifyClickOnTestArtifactFromExecutionResult() {
    loadPage("rqm/verifyClickOnLinkTSER.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnTestArtifactFromExecutionResult("Test Suite Result", "");
  } 
  /**
   * Unit Test coverage method {@link RQMExecutionPageVerification#verifyCheckLastResultTestExecutionRecord(String, String, String, String,String, String)}
   */
  @Test
  public void testVerifyCheckLastResultTestExecutionRecord() {
    loadPage("rqm/tserLastResult.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    rqm.verifyCheckLastResultTestExecutionRecord("Passed", "", "","Test suite execution record","CAT Iteration","true");
  }
  
  /**
   * Unit Test coverage method {@link RQMExecutionPageVerification#verifySetDefaultQuery(Map, String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifySetDefaultQuery() {
    loadPage("rqm/set_default_query_successfully.html");
    RQMExecutionPageVerification rqm = getJazzPageFactory().getRQMExecutionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("option", "Select...");
    additionalParams.put("queryOption", "Shared Queries");
    additionalParams.put("folderName", "Predefined");
    additionalParams.put("defaultQueryForListView", "All Current Test Case Results");
    additionalParams.put("buttonName", "OK");
    rqm.verifySetDefaultQuery(additionalParams, "The default query was updated successfully.");
    assertEquals("PASSED", rqm.verifySetDefaultQuery(additionalParams, "The default query was updated successfully.").getState());
    assertEquals("FAILED", rqm.verifySetDefaultQuery(additionalParams, "FAILED!").getState());
  } 
}
