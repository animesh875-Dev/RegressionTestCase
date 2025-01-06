/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMPlanningPageVerification;

/**
 * Unit tests coverage for the RQMPlanningPageVerification.
 */
public class RQMPlanningPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySearchTestSpecifications(String, String)}.
   * 
   * <p>Verify search test specification with true acceptance message.
   */
  @Test
  public void testVerifySearchTestSpecifications() {
    loadPage("rqm/searchArtifact.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySearchTestSpecifications("1631", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySearchTestSpecifications(String, String)}.
   * 
   * <p>Verify search test specification with false acceptance message.
   */
  @Test
  public void testVerifySearchTestSpecificationsTwo() {
    loadPage("rqm/searchArtifact.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySearchTestSpecifications("1234", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyClickOnGenerateNewExecutionRecordButton(String, String)}.
   *
   * <p>Verify execution record generated with true acceptance message.
   */
  @Test
  public void testVerifyGenerateExecutionRecord() {
    loadPage("rqm/verify_click_on_generate_new_execution_record_button.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyClickOnGenerateNewExecutionRecordButton("56865", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyClickOnGenerateNewExecutionRecordButton(String, String)}.
   * 
   * <p>Verify execution record generated with false acceptance message.
   */
  @Test
  public void testVerifyGenerateExecutionRecordTwo() {
    loadPage("rqm/rqm_planning_generate_exe.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyClickOnGenerateNewExecutionRecordButton("4125r5", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySetIterationAndTestEnvironment(Map, String)}.
   * 
   * <p>Verify test environment generated with true acceptance message.
   */
  @Test
  public void testVerifyGenerateTestEnvironment() {
    loadPage("rqm/delete_testexe_record_button.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("searchTestEnvName", "AMD64");
    rqmpv.verifySetIterationAndTestEnvironment(addParam, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySetIterationAndTestEnvironment(Map, String)}.
   * 
   * <p>Verify test environment generated with false acceptance message.
   */
  @Test
  public void testVerifyGenerateTestEnvironmentTwo() {
    loadPage("rqm/rqm_planning_generate_exe.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("searchTestEnvName", "test123");
    rqmpv.verifySetIterationAndTestEnvironment(addParam, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyGetExecutionRecordDetails(String, String)}.
   * 
   * <p>Verify execution record is generated with true acceptance message.
   */
  @Test
  public void testVerifyIsExecutionRecordsGenerated() {
    loadPage("rqm/rqm_istestcase_exerec_attribute_visible.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyGetExecutionRecordDetails("AMD64", "true");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyGetExecutionRecordDetails(String, String)}.
   * 
   * <p>Verify execution record is generated with false acceptance message.
   */
  @Test
  public void testVerifyIsExecutionRecordsGeneratedTwo() {
    loadPage("rqm/rqm_istestcase_exerec_attribute_visible.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyGetExecutionRecordDetails("AMD_test", "false");
  }

  /**
    * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyDeleteTestExecutionRecord(String,String, String)}.
    * 
    * <p>Verify execution record is deleted with true acceptance message.
    */
  @Test
  public void testVerifyDeleteTestExecutionRecord() {
    loadPage("rqm/delete_testexe_record_button.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyDeleteTestExecutionRecord("AMD64", "Delete Test Case Execution Record","");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyDeleteTestExecutionRecord(String,String, String)}.
   * 
   * <p>Verify execution record is deleted with false acceptance message.
   */
  @Test
  public void testVerifyDeleteTestExecutionRecordTwo() {
    loadPage("rqm/rqm_istestcase_exerec_attribute_visible.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyDeleteTestExecutionRecord("AMD_test", "Delete Test Case Execution Record","");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyIsTestEnvironmentAdded(Map, String)}.
   * 
   * <p>Verify test environment is added with true acceptance message.
   */
  @Test
  public void testVerifyIsTestEnvironmentAdded() {
    loadPage("rqm/testEnvironment.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "AMD64");
    rqmpv.verifyIsTestEnvironmentAdded(addParam, "true");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyIsTestEnvironmentAdded(Map, String)}.
   * 
   * <p>Verify test environment is added with false acceptance message.
   */
  @Test
  public void testVerifyIsTestEnvironmentAddedTwo() {
    loadPage("rqm/testEnvironment.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "abcd");
    rqmpv.verifyIsTestEnvironmentAdded(addParam, "false");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySelectIterationAndGenerateTestEnvironment(Map, String)}.
   * 
   * <p>Verify iteration is added in the execution record with true acceptance message.
   */
  @Test
  public void testVerifySelectIterationAndGenerateTestEnvironment() {
    loadPage("rqm/delete_testexe_record_button.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "");
    rqmpv.verifySelectIterationAndGenerateTestEnvironment(addParam, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySelectIterationAndGenerateTestEnvironment(Map, String)}.
   * 
   * <p>Verify iteration is added in the execution record with false acceptance message.
   */
  @Test
  public void testVerifySelectIterationTwo() {
    loadPage("rqm/rqm_istestcase_exerec_attribute_visible.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "");
    rqmpv.verifySelectIterationAndGenerateTestEnvironment(addParam, "");
  }

  /**
    * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyIsTestSuiteExecutionRecordsVisible(Map, String)}.
    *
    * <p>Verify iteration is added in the execution record with true acceptance message.
    */
  @Test
  public void testVerifyIsTestSuiteExecutionRecordsVisible() {
    loadPage("rqm/delete_testexe_record_button.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "");
    rqmpv.verifyIsTestSuiteExecutionRecordsVisible(addParam, "true");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyIsTestSuiteExecutionRecordsVisible(Map, String)}.
   * 
   * <p>Verify iteration is added in the execution record with false acceptance message.
   */
  @Test
  public void testVerifyIsTestSuiteExecutionRecordsVisibleTwo() {
    loadPage("rqm/rqm_istestcase_exerec_attribute_visible.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "");
    rqmpv.verifyIsTestSuiteExecutionRecordsVisible(addParam, "false");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyClickOnTestPlanFromExecutionRecord(String, String)}.
   * 
   * <p>Verify clicked on test plan with true acceptance message.
   */
  @Test
  public void testVerifyClickOnTestPlanFromExecutionRecord() {
    loadPage("rqm/rqm_istestcase_exerec_attribute_visible.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyClickOnTestPlanFromExecutionRecord("3742", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyClickOnTestPlanFromExecutionRecord(String, String)}.
   * 
   * <p>Verify clicked on test plan with false acceptance message.
   */
  @Test
  public void testverifyClickOnTestPlanFromExecutionRecord() {
    loadPage("rqm/searchArtifact.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyClickOnTestPlanFromExecutionRecord("78965", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyDeleteTestEnvironment(Map, String)}.
   * 
   * <p>Verify test environment is deleted with true acceptance message.
   */
  @Test
  public void testVerifyDeleteTestEnvironmentAndRecord() {
    loadPage("rqm/testEnvironment.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "Alpha");
    rqmpv.verifyDeleteTestEnvironment(addParam, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyDeleteTestEnvironment(Map, String)}.
   * 
   * <p>Verify test environment is deleted with true acceptance message.
   */
  @Test
  public void testverifyDeleteTestEnvironmentAndRecord() {
    loadPage("rqm/searchArtifact.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    Map<String, String> addParam = new LinkedHashMap<String, String>();
    addParam.put("TestEnvironment", "test_env");
    rqmpv.verifyDeleteTestEnvironment(addParam, "false");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySearchTestSpecificationsFromTestEnvironment(String,String, String)}.
   * 
   * <p>Verify searched test environment is displayed with true acceptance message.
   */
  @Test
  public void testVerifySearchTestSpecificationsFromTestEnvironment() {
    loadPage("rqm/dailog_search_test_specification.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySearchTestSpecificationsFromTestEnvironment("11671", "Generate Test Case Execution Records", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySearchTestSpecificationsFromTestEnvironment(String,String, String)}.
   * 
   * <p>Verify searched test environment is displayed with false acceptance message.
   */
  @Test
  public void testVerifySearchTestSpecificationsFromTestEnvironmentTwo() {
    loadPage("rqm/dailog_search_test_specification.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySearchTestSpecificationsFromTestEnvironment("12345", "Generate Test Case Execution Records", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySelectIteration(String,String)}.
   * 
   * <p>Verify selected Iteration for the execution record.
   */
  @Test
  public void testverifySelectIteration() {
    loadPage("rqm/select_iteration_test_environment.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySelectIteration("Unassigned", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySelectIteration(String,String)}.
   * 
   * <p>Verify selected Iteration for the execution record with false acceptance message.
   */
  @Test
  public void testVerifySelectIteration() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_4.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySelectIteration("IBM CLM 6.0.6.1", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyGenerateTestEnvironment(String,String)}.
   * 
   * <p>Verify test environment is added for the execution record.
   */
  @Test
  public void testverifyGenerateTestEnvironment() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_4.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyGenerateTestEnvironment("Alpha", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyGenerateTestEnvironment(String,String)}.
   * 
   * <p>Verify test environment is added for the execution record.
   */
  @Test
  public void testVerifyGenerateTestenvironment() {
    loadPage("rqm/verify_generate_test_environment_finish.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyGenerateTestEnvironment("Alpha", "");
  }


  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySetIteration(String,String)}.
   * 
   * <p>Verify selected Iteration for the execution record.
   */
  @Test
  public void testVerifySetIteration() {
    loadPage("rqm/select_iteration_test_environment.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySetIteration("IBM CLM 6.0.6.1", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifySetIteration(String,String)}.
   * 
   * <p>Verify selected Iteration for the execution record with false acceptance message.
   */
  @Test
  public void testverifySetIteration() {
    loadPage("rqm/generate_testEnv_click_finish.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifySetIteration("test", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMPlanningPageVerification#verifyReuseExistingTestEnvironment(String,String)}.
   * 
   * <p>Verify existing test environment is added for the execution record.
   */
  @Test
  public void testVerifyReuseExistingTestEnvironment() {
    loadPage("rqm/generate_testEnv_click_finish.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyReuseExistingTestEnvironment("Alpha", "");
  }

  /**
   * Verify test environment is added for the execution record.
   */
  @Test
  public void testVerifyReuseExistingTestEnvironmentTwo() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_4.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyReuseExistingTestEnvironment("Alpha", "");
  }

  /**
   * Verify page is scrolled.
   */
  @Test
  public void testVerifyPageScrollBar() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_4.html");
    RQMPlanningPageVerification rqmpv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmpv);
    rqmpv.verifyPageScrollBar("");
  }
  
  /**
   * Unit test coverage for method {@link RQMPlanningPageVerification#verifyInputValueIntoNewTestCaseDlg(Map, String)}
   * 
   */
  @Test
  public void testVerifyInputValueIntoNewTestCaseDlg() {
    loadPage("rqm/CreateNewTC.html");
    RQMPlanningPageVerification rqmv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmv);
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("NAME_VALUE","test abc");
    params.put("TEST_TYPE_VALUE", "‪Inspection");
    rqmv.verifyInputValueIntoNewTestCaseDlg(params, "");
  }
  /**
   * Unit test coverage for method {@link RQMPlanningPageVerification#verifyIsNewCreatedTestCase(Map, String)}
   * 
   */
  @Test
  public void testVerifyIsNewCreatedTestCase() {
    loadPage("rqm/newTestCaseCreated.html");
    RQMPlanningPageVerification rqmv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmv);
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("NAME_VALUE","Test abc_19_10_2022_14_10_391");
    params.put("TEST_TYPE_VALUE", "Inspection");
    rqmv.verifyIsNewCreatedTestCase(params, "true");
    rqmv.verifyIsNewCreatedTestCase(params, "false");
  }
  /**
   * Unit test coverage for method {@link RQMPlanningPageVerification#verifyRunTestSuiteFromActionsMenu(String,String)}
   * 
   */
  @Test
  public void testVerifyRunTestSuiteFromActionsMenu() {
    loadPage("rqm/runtestSuite2.html");
    RQMPlanningPageVerification rqmv = getJazzPageFactory().getRQMPlanningPageVerification();
    assertNotNull(rqmv);
    rqmv.verifyRunTestSuiteFromActionsMenu("Run","");
  }
}
