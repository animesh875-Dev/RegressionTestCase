/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstructionPage;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMConstructionPageVerification;

/**
 * @author UUM4KOR
 */
public class RQMConstructionPageVerificationTest extends AbstractFrameworkUnitTest {


  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectOwner(Map, String)}.
   *
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifySelectOwner() {
    loadPage("rqm/select_lifecyclestate.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("moreLinkValue", "More...");
    additionalParams.put("userIdValue", "hcm6kor");
    additionalParams.put("ownerValue", "ADMIN - ADMIN");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_owner_dropdown.html");
    clickToPage.put(2, "rqm/select_owner_list.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.verifySelectOwner(additionalParams, "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectOwner(Map, String)}.
   *
   * <p>Verifies the methods with Boolean value return
   */
  @Test
  public void tesVerifySelectOwnerOne() {
    loadPage("rqm/verify_priority.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();

    additionalParams.put("ownerValue", "EXTERNAL Biswajit Behere (Datamatics, RBEI/EMT4");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();

    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.verifySelectOwner(additionalParams, "");

  }


  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyClickOnTestScriptId(String, String)}.
   *
   * <p>clicking Artifact Id name or id of artifact
   */
  @Test
  public void testVerifyClickOnTestScriptId() {
    loadPage("rqm/testScriptsID.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnTestScriptId("3742", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyClickOnTestScriptId(String, String)}.
   *
   * <p>Invalid Artifact Id name or id of artifact
   */
  @Test
  public void testVerifyClickOnTestScriptIdOne() {
    loadPage("rqm/testScriptsID.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnTestScriptId("909090", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyIsErrorMessageDisplay(String, String)}.
   *
   * <p>Null Artifact Id name or id of artifact
   */
  @Test
  public void testVerifyIsErrorMessageDisplay() {
    loadPage("rqm/test_error_meassage.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsErrorMessageDisplay("", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyIsAssociateArtifactDisplayed(String, String,String)}.
   *
   * <p>Last Result get the last result of {@link RQMConstructionPage#isAssociateArtifactDisplayed}}
   */
  @Test
  public void testVerifyIsAssociateArtifactDisplayed() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsAssociateArtifactDisplayed("Test Script For Automation Testing08_04_2020_14_04_563", "", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectTestScript(String, String)}.
   *
   * <p>Select Test Script name or id of artifact
   */
  @Test
  public void testVerifySelectTestScript() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTestScript("Test Script For Automation Testing08_04_2020_14_04_563", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySearchRqmArtifactsInFilterTextBox(String, String)}.
   *
   * <p>acceptance object which contains verification results.
   */
  @Test
  public void testverifySearchRqmArtifactsInFilterTextBox() {
    loadPage("rqm/search_artifacts_filters_textbox.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySearchRqmArtifactsInFilterTextBox("Keyword for Testing", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySearchRqmArtifactsInFilterTextBox(String, String)}.
   *
   * <p>Acceptance object which not contains verification results.
   */
  @Test
  public void testVerifySearchRqmArtifactsInFilterTextBoxOne() {
    loadPage("rqm/search_artifacts_filters_textbox.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySearchRqmArtifactsInFilterTextBox("zzzzzzzzz", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyFilterAndDeleteRqmArtifact(String, String, String, String)}.
   *
   * <p>Dialog Name the title of Delete test artifact dialog false
   */
  @Test
  public void testVerifyFilterAndDeleteRqmArtifact() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyFilterAndDeleteRqmArtifact("", "", "", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyFilterAndDeleteRqmArtifact(String, String, String, String)}.
   *
   * <p>Dialog Name the title of Delete test artifact dialog true
   */
  @Test
  public void testVerifyFilterAndDeleteRqmArtifactTrue() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyFilterAndDeleteRqmArtifact("Keyword For Automation Testing", "Delete Keyword", "Delete Keywords", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyDeleteStep(String, String)}.
   *
   * <p>Acceptance object which contains verification results.
   */
  @Test
  public void testVerifyDeleteStep() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyDeleteStep("1", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyCreateNewStep(String, String, String)}
   *
   * <p>Menu is Insert New Step Before/Insert New Step After
   */
  @Test
  public void testVerifyCreateNewStep() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyCreateNewStep("Insert New Step Before", "Create Empty Step", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyCreateNewStep(String, String, String)}.
   *
   * <p>Menu is Insert New Step After/Insert New Step After
   */
  @Test
  public void testVerifyCreateNewStepOne() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyCreateNewStep("Insert New Step After", "Create Empty Step", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyCreateNewStep(String, String, String)}.
   *
   * <p>Invalid data Menu is Insert New Step Before/Insert New Step After
   */
  @Test
  public void testVerifyCreateNewStepTwo() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyCreateNewStep("", "", "");

  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGenerateNewTestCaseExecutionRecord(Map, String)}.
   *
   * <p>Verify Test Case Execution Record is created.
   */
  @Test
  public void testVerifyGenerateNewTestCaseExecutionRecord() {
    loadPage("rqm/delete_testexe_record_button.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("searchTestEnvName", "AMD64");
    rqm.verifyGenerateNewTestCaseExecutionRecord(clickToPage, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGenerateNewTestCaseExecutionRecord(Map, String)}.
   *
   * <p>Invalid environment name provided to validate false message.
   */
  @Test
  public void testVerifyGenerateNewTestCaseExecutionRecordTwo() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_5.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("searchTestEnvName", "test");
    rqm.verifyGenerateNewTestCaseExecutionRecord(clickToPage, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGenerateNewTestSuiteExecutionRecord(String, String)}.
   *
   * <p>Verify Test Suite Execution Record is created.
   */
  @Test
  public void testVerifyGenerateNewTestSuiteExecutionRecord() {
    loadPage("rqm/delete_testexe_record_button.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGenerateNewTestSuiteExecutionRecord("test plan created for suite report", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGenerateNewTestSuiteExecutionRecord(String, String)}.
   *
   * <p>Verify false message with invalid data.
   */
  @Test
  public void testVerifyGenerateNewTestSuiteExecutionRecordTwo() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_3.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGenerateNewTestSuiteExecutionRecord("test plan", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectRqmArtifact(String, String)}.
   *
   * <p>Artifact Name name of the artifact.
   */
  @Test
  public void testVerifySelectRqmArtifact() {
    loadPage("rqm/testScriptsID.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectRqmArtifact("Retrieve one existing project", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectTestData(String, String)}.
   *
   * <p>Acceptance object which contains verification results.
   */
  @Test
  public void testverifySelectTestData() {
    loadPage("rqm/testScriptsID.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTestData("3742", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectTestData(String, String)}.
   *
   * <p>Acceptance object which contains verification results.
   */
  @Test
  public void testverifySelectTestDataOne() {
    loadPage("rqm/testScriptsID.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTestData("909090", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectTestData(String, String)}.
   *
   * <p>Acceptance object which contains verification results.
   */
  @Test
  public void testverifySelectTestDataTwo() {
    loadPage("rqm/open_Main_Menu_Bymenu_Title.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTestData("909090", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyCreateNewTestScriptInDialog(Map, String)}.
   *
   * <p>Additional Parameters the content of new test script
   */
  @Test
  public void testVerIfyCreateNewTestScriptInDialog() {
    loadPage("rqm/create_new_test_script_in_dialog.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    assertNotNull(rqm);
    rqm.verifyCreateNewTestScriptInDialog(additionalParams, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAddStringWithDateTime(String)}.
   *
   * <p>Added with date and time as a suffix.
   */
  @Test
  public void testverifyAddStringWithDateTime() {
    loadPage("rqm/create_new_test_script_in_dialog.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAddStringWithDateTime("");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAddStringWithDateTime(String)}.
   *
   * <p>Added with date and time as a suffix.
   */
  @Test
  public void testverifyAddStringWithDateTimeOne() {
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.verifyAddStringWithDateTime("16_06_2020_12_06_589");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAssociateTestScriptWithTestCase(String,String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testverifyAssociateTestScriptWithTestCase() {
    loadPage("rqm/AssociateTestScriptWithTestCase.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAssociateTestScriptWithTestCase("Test 1", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAssociateTestScriptWithTestCase(String,String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testverifyAssociateTestScriptWithTestCaseOne() {
    loadPage("rqm/AssociateTestScriptWithTestCaseOne1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();

    assertNotNull(rqm);
    rqm.verifyAssociateTestScriptWithTestCase("Test 1", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#waitForPageLoaded()}.
   */
  @Test
  public void testwaitForPageLoaded() {
    loadPage("rqm/AssociateTestScriptWithTestCaseOne1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.waitForPageLoaded();
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAddStepValuesInTestScript(Map, String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testverifyAddStepValuesInTestScript() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(RQMConstants.DESCRIPTION_VALUE, "");
    additionalParams.put(RQMConstants.DESCRIPTION, "");
    additionalParams.put("expecResultValue", "");
    additionalParams.put(RQMConstants.EXPECTED_RESULT, "");
    assertNotNull(rqm);
    rqm.verifyAddStepValuesInTestScript(additionalParams, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAddStepValuesInTestScript(Map, String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testverIfyAddStepValuesInTestScript() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(RQMConstants.DESCRIPTION_VALUE, "");
    additionalParams.put(RQMConstants.DESCRIPTION, "");
    additionalParams.put("expecResultValue", "");
    additionalParams.put(RQMConstants.EXPECTED_RESULT, "test");
    assertNotNull(rqm);
    rqm.verifyAddStepValuesInTestScript(additionalParams, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetContentOfAddedStep(String, String, String, String, String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testVerifyGetContentOfAddedStep() {
    loadPage("rqm/AssociateTestScriptWithTestCaseOne1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetContentOfAddedStep("", "", "test", "true", "test");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetContentOfAddedStep(String, String, String, String, String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testVerIfyGetContentOfAddedStep() {
    loadPage("rqm/AssociateTestScriptWithTestCaseOne1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetContentOfAddedStep("", "", "test", "false", "test");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetContentOfAddedStep(String, String, String, String, String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testVerIfYGetContentOfAddedStep() {
    loadPage("rqm/AssociateTestScriptWithTestCaseOne1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetContentOfAddedStep("", "", "", "true", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyEnterTestArtifactName(Map,String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testVerifyEnterTestArtifactName() {
    loadPage("rqm/test_case_id_files.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    rqm.verifyEnterTestArtifactName(additionalParams, "TCS_AT_Test_Case_31_03_2019_10_03_129");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyEnterTestArtifactName(Map,String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testVerIfyEnterTestArtifactName() {
    loadPage("rqm/test_case_id_files.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    rqm.verifyEnterTestArtifactName(additionalParams, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyFillDescriptionInSummaryOuterNodeSection(Map,String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testVerifyFillDescriptionInSummaryOuterNodeSection() {
    loadPage("rqm/test_case_id_files.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactDescriptionValue", "Test Case Description");
    rqm.verifyFillDescriptionInSummaryOuterNodeSection(additionalParams, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyFillDescriptionInSummaryOuterNodeSection(Map,String)}.
   *
   * <p>This method use to associate test script with test case in dialog: Associate this test script with test case
   */
  @Test
  public void testVerIfyFillDescriptionInSummaryOuterNodeSection() {
    loadPage("rqm/test_case_id_files.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactDescriptionValue", "Invalid");
    rqm.verifyFillDescriptionInSummaryOuterNodeSection(additionalParams, "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetRqmArtifactID(String)}.
   *
   * <p>Acceptance object which contains verification results.
   */
  @Test
  public void testVerifyGetRqmArtifactID() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetRqmArtifactID("True");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetRqmArtifactID(String)}.
   *
   * <p>Acceptance object which contains verification results.
   */
  @Test
  public void testVerIfyGetRqmArtifactID() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetRqmArtifactID("");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySetIdOrNameToAddRQMArtifact(String, String, String)}.
   *
   * <p>Verify add associate artifact into a Rqm Artifact
   */
  @Test
  public void testAssociateArtifactToRqmArtifact() {
    loadPage("rqm/associate_artifact_to_rqm_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/associate_artifact_to_rqm_artifact.html");
    clickToPage.put(4, "rqm/associate_artifact_to_rqm_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.setIdOrNameToAddRQMArtifact("Add Test Scripts", "Test Case 1 For Automation");
    RQMConstructionPageVerification rqms = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqms);
    rqms.verifySetIdOrNameToAddRQMArtifact("Add Test Scripts", "Test Case 1 For Automation", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySetIdOrNameToAddRQMArtifact(String, String, String)}.
   *
   * <p>Verify add associate artifact into a Rqm Artifact
   */
  @Test
  public void testAssocIateArtifactToRqmArtifact() {
    loadPage("rqm/associate_artifact_to_rqm_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/associate_artifact_to_rqm_artifact.html");
    clickToPage.put(4, "rqm/associate_artifact_to_rqm_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.setIdOrNameToAddRQMArtifact("Add Test Scripts", "Test Case 1 For Automation");
    RQMConstructionPageVerification rqms = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqms);
    rqms.verifySetIdOrNameToAddRQMArtifact("Add Test Scripts", "invalid", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySetIdOrNameToAddRQMArtifact(String,String,String)}.
   *
   * <p>Verify add associate artifact into a Rqm Artifact
   */
  @Test
  public void testAssocIateArtIfactToRqmArtifact() {
    loadPage("rqm/associate_artifact_to_rqm_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/associate_artifact_to_rqm_artifact.html");
    clickToPage.put(4, "rqm/associate_artifact_to_rqm_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.setIdOrNameToAddRQMArtifact("Add Test Scripts", "8224");
    RQMConstructionPageVerification rqms = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqms);
    rqms.verifySetIdOrNameToAddRQMArtifact("Add Test Scripts", "8224", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySetIdOrNameToAddRQMArtifact(String,String,String)}.
   *
   * <p>Verify add associate artifact into a Rqm Artifact
   */
  @Test
  public void testAssocIateArtIfactToRqmArtIfact() {
    loadPage("rqm/associate_artifact_to_rqm_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/associate_artifact_to_rqm_artifact.html");
    clickToPage.put(4, "rqm/associate_artifact_to_rqm_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.setIdOrNameToAddRQMArtifact("Add Test Scripts", "8224");
    RQMConstructionPageVerification rqms = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqms);
    rqms.verifySetIdOrNameToAddRQMArtifact("Add Test Scripts", "1234", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySearchRqmArtifactsInFilterTextBox(String,String,String)}.
   *
   * <p>Verify 'No items match the current filter criteria.' message displayed while searching not existing artifacts.
   */
  @Test
  public void testVerifySearchRqmArtifactsInFilterTextBox() {
    loadPage("rqm/verify_Search_RqmArtifacts_In_Filter_TextBox.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySearchRqmArtifactsInFilterTextBox("%%%", "false", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyDeleteStep(String, String, String, String)}.
   *
   * <p>Verify added step is deleted from the Test Script.
   */
  @Test
  public void testverifyDeleteStep() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyDeleteStep("3", "description", "expected result", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyCreateNewTestScriptInDialog(Map,String)}.
   *
   * <p>Verify created 'Test Script' is displayed in 'Test Scripts' section.
   */
  @Test
  public void testVerifyCreateNewTestScriptInDialog() {
    loadPage("rqm/verify_create_new_testscript_in_dialog.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("varName", "TS_Create New repository connection.");
    additionalParams.put("varDescription", "Test1");
    additionalParams.put("varDescriptionContent", "Test2");
    rqm.verifyCreateNewTestScriptInDialog(additionalParams, "");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyChooseProjectAreaFromArtifactContainer(String,String)}.
   *
   * <p>Verify project area chosen from artifact container.
   */
  @Test
  public void testVerifyChooseProjectAreaFromArtifactContainer() {
    loadPage("rqm/add_requirement_in_testcase.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyChooseProjectAreaFromArtifactContainer("Test New Req1", "");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyChooseProjectAreaFromArtifactContainer(String,String)}.
   *
   * <p>Verify project area chosen from artifact container with false message.
   */
  @Test
  public void testverifyChooseProjectAreaFromArtifactContainer() {
    loadPage("rqm/verify_create_new_testscript_in_dialog.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyChooseProjectAreaFromArtifactContainer("Test1234", "");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAddRequirementInTestCase(String,String)}.
   *
   * <p>Verify requirement is added in the test case.
   */
  @Test
  public void testVerifyAddRequirementInTestCase() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAddRequirementInTestCase("8428: Attribute Order", "");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAddRequirementInTestCase(String,String)}.
   *
   * <p>Verify requirement is added in the test case with false acceptance message.
   */
  @Test
  public void testverifyAddRequirementInTestCase() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAddRequirementInTestCase("Test", "");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyManageSuspicion(String)}.
   *
   * <p>Verifies manage suspicion option is selected.
   */
  @Test
  public void testVerifyManageSuspicion() {
    loadPage("rqm/choose_suspicion_profile.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyManageSuspicion("");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyManageSuspicion(String)}.
   *
   * <p>Verifies manage suspicion option is selected with false acceptance message.
   */
  @Test
  public void testverifyManageSuspicion() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyManageSuspicion("");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyChooseSuspicionProfile(String,String)}.
   *
   * <p>Verifies suspicion profile is enabled for the given profile name.
   */
  @Test
  public void testVerifyChooseSuspicionProfile() {
    loadPage("rqm/choose_suspicion_profile.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyChooseSuspicionProfile("Test_Automation2","");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyChooseSuspicionProfile(String,String)}.
   *
   * <p>Verifies suspicion profile is enabled with false acceptance message.
   */
  @Test
  public void testverifyChooseSuspicionProfile() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyChooseSuspicionProfile("test","");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectTheSearchedArtifact(String,String)}.
   *
   * <p>Verifies searched artifact is selected.
   */
  @Test
  public void testVerifySelectTheSearchedArtifact() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTheSearchedArtifact("8428: Attribute Order", "");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectTheRequirementForReconcile(String,String)}.
   *
   * <p>Verifies selected the requirement for reconcile.
   */
  @Test
  public void testVerifySelectTheRequirementForReconcile() {
    loadPage("rqm/verify_select_the_requirement_for_reconcile.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTheRequirementForReconcile("2207106", "true");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectTheRequirementForReconcile(String,String)}.
   *
   * <p>Verifies selected the requirement for reconcile.
   */
  @Test
  public void testVerifySelectTheRequirementForReconcileTwo() {
    loadPage("rqm/verify_select_the_requirement_for_reconcile.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTheRequirementForReconcile("2207106: test", "true");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetReconcileRequirement(String,String)}.
   *
   * <p>Verifies reconciled requirement.
   */
  @Test
  public void testVerifyGetReconcileRequirement() {
    loadPage("rqm/select_the_searched_requirement.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetReconcileRequirement("34567","");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetReconcileRequirement(String,String)}.
   *
   * <p>Verifies reconciled requirement.
   */
  @Test
  public void testVerifyGetReconcileRequirementTwo() {
    loadPage("rqm/select_the_searched_requirement.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetReconcileRequirement("34567: Backend_Update_Center2_Filtering","");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetSuspectStatusOfRequirement(String,String,String)}.
   *
   * <p>Verifies suspect status of the requirement.
   */
  @Test
  public void testVerifyGetSuspectStatusOfRequirement() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetSuspectStatusOfRequirement("8428: Attribute Order","Yes","true");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyGetSuspectStatusOfRequirement(String,String,String)}.
   *
   * <p>Verifies suspect status of the requirement.
   */
  @Test
  public void testverifyGetSuspectStatusOfRequirement() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetSuspectStatusOfRequirement("8428: Attribute Order","No","false");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyCreateRequirement(Map,String)}.
   *
   * <p>Verifies requirement is created.
   */
  @Test
  public void testVerifyCreateRequirement() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Requirement Name", "8428: Attribute Order");
    additionalParams.put("Artifact type", "Capability");
    additionalParams.put("Artifact format", "Text");
    rqm.verifyCreateRequirement(additionalParams,"");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyCreateRequirement(Map,String)}.
   *
   * <p>Verify requirement is created in the test case with false acceptance message.
   */
  @Test
  public void testverifyCreateRequirement() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Requirement Name", "Test");
    additionalParams.put("Artifact type", "Capability");
    additionalParams.put("Artifact format", "Text");
    rqm.verifyCreateRequirement(additionalParams,"");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectAllRequirements(String,String)}.
   *
   * <p>Verifies all the requirements selected from 'Requirement Links' section.
   */
  @Test
  public void testVerifySelectAllRequirements() {
    loadPage("rqm/select_all_requirements_for_reconcile.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectAllRequirements("Select all items on this page","");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectAllRequirements(String,String)}.
   *
   * <p>Verifies all the requirements selected from 'Requirement Links' section with false acceptance message.
   */
  @Test
  public void testverifySelectAllRequirements() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectAllRequirements("Select all items on this page","");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifySelectAllRequirementsForReconcile(String,String)}.
   *
   * <p>Verifies all the requirements selected from 'Requirements Reconcile' window.
   */
  @Test
  public void testVerifySelectAllRequirementsForReconcile() {
    loadPage("rqm/select_all_requirements_for_reconcile.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectAllRequirementsForReconcile("Select all items on this page","");
  }

  /**
   * <p>
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyFilterRequirementLinks(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testverifyFilterRequirementLinks() {
    loadPage("rqm/testFilterRequirementLinks.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyFilterRequirementLinks("1023997", "true", "true");
  }

  /**
   * <p>
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyFilterRequirementLinks(String, String, String)}
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testverIfyFilterRequirementLinks() {
    loadPage("rqm/testFilterRequirementLinks.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyFilterRequirementLinks("12345", "false", "false");
  }

  /**
   * <p>
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyIsLinkValidityVisibleInRequirementLinks(String, String, String)}
   * Cover for the failed case
   * <p>
   * @author LPH1HC
   */
  @Test
  public void testVerifyIsLinkValidityVisibleInRequirementLinks() {
    loadPage("rqm/testFilterRequirementLinks.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsLinkValidityVisibleInRequirementLinks("12345","true","true");
  }


  /**
   * <p>
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyIsLinkValidityVisibleInRequirementLinks(String, String, String)}
   * Cover for the failed case
   * <p>
   * @author LPH1HC
   */
  @Test
  public void testVerIfyIsLinkValidityVisibleInRequirementLinks() {
    loadPage("rqm/testFilterRequirementLinks.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsLinkValidityVisibleInRequirementLinks("12345","false","false");
  }

  /**
   * <p>
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyClickOnRequirementIDFromRequirementLinks(String, String)}
   * Cover for the failed case
   * <p>
   * @author LPH1HC
   */
  @Test
  public void testVerifyClickOnRequirementIDFromRequirementLinks() {
    loadPage("rqm/verifyClickOnRequirementIDFromRequirementLinks.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnRequirementIDFromRequirementLinks("1024525","false");
  }

  /**
   * <p>
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyClickOnRequirementIDFromRequirementLinks(String, String)}
   * Cover for the failed case
   * <p>
   * @author LPH1HC
   */
  @Test
  public void testVerIfyClickOnRequirementIDFromRequirementLinks() {
    loadPage("rqm/verifyClickOnRequirementIDFromRequirementLinks.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnRequirementIDFromRequirementLinks("1024525","true");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyGetDescriptionOfRqmArtifact(String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyGetDescriptionOfRqmArtifact() {
    loadPage("rqm/getDescriptionOfRqmArtifact.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetDescriptionOfRqmArtifact("Test script for TSC_PK_PF_SW_UT_SleepWakeBle_rbd_SleepWakeBle_rbd_SleepWakeBle_EvalSpeedRequest");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyEditDescriptionRqmArtifact(String, String)}
   * @author VDY1HC
   */
  @Test
  public void testVeriyEditDescriptionRqmArtifact() {
    loadPage("rqm/editDescriptionRqmArtifact.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyEditDescriptionRqmArtifact("Test script for TSC_PK_PF_SW_UT_SleepWakeBle_rbd_SleepWakeBle_rbd_SleepWakeBle_EvalSpeedRequest Test","Test script for TSC_PK_PF_SW_UT_SleepWakeBle_rbd_SleepWakeBle_rbd_SleepWakeBle_EvalSpeedRequest Test");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyEditDescriptionRqmArtifactWithoutSave(String, String)}
   * @author VDY1HC
   */
  @Test
  public void testVeriyEditDescriptionRqmArtifactWithoutSave() {
    loadPage("rqm/editDescriptionRqmArtifact.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyEditDescriptionRqmArtifactWithoutSave("Test script for TSC_PK_PF_SW_UT_SleepWakeBle_rbd_SleepWakeBle_rbd_SleepWakeBle_EvalSpeedRequest Test","Test script for TSC_PK_PF_SW_UT_SleepWakeBle_rbd_SleepWakeBle_rbd_SleepWakeBle_EvalSpeedRequest Test");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyVerifyAllCategoriesValue(String, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyVerifyAllCategoriesValue() {
    loadPage("rqm/verifyAllCategoriesValue.htm");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyVerifyAllCategoriesValue("Product:,AE_BE PK Script;Release:,Unassigned;Component:,Unassigned;Test Phase:,Acceptance testing","true");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyClickOnAddNewLinksFromRequirementLinks(String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyClickOnAddNewLinksFromRequirementLinks() {
    loadPage("rqm/VerifyClickOnContextMenuOrSubMenuForArtifact_02_ArtifactContainerSelectionDisplayed.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnAddNewLinksFromRequirementLinks("");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyGetContentOfTextAreaSection(String)}
   */
  @Test
  public void testVerifyGetContentOfTextAreaSection() {
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetContentOfTextAreaSection("a");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyVerifyUpdateAttributeHistoryByIndex(Map, String)}
   */
  @Test
  public void testVerifyVerifyUpdateAttributeHistoryByIndex() {
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("HISTORY_INDEX", "1");
    additionalParams.put("ATTRIBUTE_NAME", "Test Preparation");
    additionalParams.put("UPDATE_TYPE", "Insert");
    additionalParams.put("ATTRIBUTE_NEW_VALUE", "ggg");
    rqm.verifyVerifyUpdateAttributeHistoryByIndex(additionalParams,"true");
  }

  /**
   * Unit test cover for
   * ${@link RQMConstructionPageVerification#verifyClickOnLinkInRightSide(String,String,String,String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyClickOnLinkInRightSide() {
    loadPage("rqm/clickOnLinkInRightSide2.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnLinkInRightSide("Validates Requirements", "1051958: Information artifact",
        "1051956: Module for automation testing TS_25886", "");
  }

  /**
   * Unit test cover for
   * ${@link RQMConstructionPageVerification#verifyClickOnLinkInRightSide(String,String,String,String)}. <br>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyClickOnLinkInRightSide() {
    loadPage("rqm/clickOnLinkInRightSide2.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnLinkInRightSide("Validates Requirements", "1051958: Information artifact", "test123456", "");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyIsLinkDisplayed(String,String,String,String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyIsLinkDisplayed() {
    loadPage("rqm/clickOnLinkInRightSide1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsLinkDisplayed("Validates Requirements", "1051958: Information artifact", "TRUE", "");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPageVerification#verifyIsLinkDisplayed(String,String,String,String)}.
   * <br>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyIsLinkDisplayed() {
    loadPage("rqm/clickOnLinkInRightSide1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsLinkDisplayed("Validates Requirements", "1051958: Information artifact", "FALSE", "");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyClickOnTestCaseLinkFromTestExecutionResult(String,String)}.
   */
  @Test
  public void testVerifyClickOnTestCaseLinkFromTestExecutionResult() {
    loadPage("rqm/click_on_testcase_link_from_test_execution_result.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnTestCaseLinkFromTestExecutionResult("16754","false");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyClickOnTestCaseLinkFromTestExecutionResult(String,String)}.
   */
  @Test
  public void testVerifyClickOnTestCaseLinkFromTestExecutionResultOne() {
    loadPage("rqm/testScriptsID.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnTestCaseLinkFromTestExecutionResult("3742", "true");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifySetLinkValidityStatus(String, String, String)}.
   */
  @Test
  public void testVerifySetLinkValidityStatus() {
    loadPage("rqm/verify_link_validity_status.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifySetLinkValidityStatus("Suspect","365965", "");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyVerifyLinkValidityStatus(String, String, String)}.
   */
  @Test
  public void testVerifyVerifyLinkValidityStatus() {
    loadPage("rqm/verify_link_validity_status.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyVerifyLinkValidityStatus("Link is suspect","365965", "true");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyVerifyLinkValidityStatus(String, String, String)}.
   */
  @Test
  public void testverifyLinkValidityStatus() {
    loadPage("rqm/verify_link_validity_status.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyVerifyLinkValidityStatus("Link is suspect","365965", "");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyGetValiditySummary(String, String, String)}.
   */
  @Test
  public void testVerifyGetValiditySummary() {
    loadPage("rqm/get_validity_summary.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetValiditySummary("Suspect","13303", "true");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyGetValiditySummary(String, String, String)}.
   */
  @Test
  public void testverifyGetValiditySummary() {
    loadPage("rqm/get_validity_summary.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetValiditySummary("Suspect","13303", "");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyGetRequirement(String)}.
   */
  @Test
  public void testverifyGetRequirement() {
    loadPage("rqm/get_requirement.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetRequirement("true");
  }
  /**
   * Unit test to cover ${@link RQMConstructionPageVerification#verifyGetRequirement(String)}.
   */
  @Test
  public void testVerifyGetRequirement() {
    loadPage("rqm/get_requirement.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetRequirement(null);
  }
  /**
   * Unit test cover ${@link RQMConstructionPageVerification#verifyGetClearSuspicionStatus(String, String)}.
   */
  @Test
  public void testverifyGetClearSuspicionStatus() {
    loadPage("rqm/get_clear_suspicion_status.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetClearSuspicionStatus("","test");
  }
  /**
   * Unit test cover ${@link RQMConstructionPageVerification#verifyGetClearSuspicionStatus(String, String)}.
   */
  @Test
  public void testVerifyGetClearSuspicionStatus() {
    loadPage("rqm/get_clear_suspicion_status.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetClearSuspicionStatus("No requirements were changed or removed.","No requirements were changed or removed.");
  }
 
  /**
   * Unit test cover ${@link RQMConstructionPageVerification#verifyGetTestCaseLinkFromTestSuitesTable(String, String)}.
   */
  @Test
  public void testVerifyGetTestCaseLinkFromTestSuitesTables() {
    loadPage("rqm/test_cases_from_test_suites_table.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetTestCaseLinkFromTestSuitesTable("5570", "#action=com.ibm.rqm.planning.home.actionDispatcher&subAction=viewTestCase&id=5570");
  }
  
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAssociateTestCaseWithTestSuite(String,String,String)}.
   *
   * <p>This method use to associate test case with test suite in dialog: Associate this test case with test suite
   */
  @Test
  public void testVerifyAssociateTestCaseWithTestSuite() {
    loadPage("rqm/associate_test_case_with_test_suite.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAssociateTestCaseWithTestSuite("Test Suite for TC_24623","Associate this test case with new Test Suite","");
  }
  
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAssociateTestCaseWithTestPlan(String,String,String,String)}.
   *
   * <p>This method use to associate test case with test plan in dialog: Associate this test case with test plan
   */
  @Test
  public void testVerifyAssociateTestCaseWithTestPlan() {
    loadPage("rqm/associate_test_case_with_test_plan_1.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAssociateTestCaseWithTestPlan("Review_50440","Associate this test case with exist Test Plan","False","");
//    rqm.verifyAssociateTestCaseWithTestPlan("Review_50440","Associate this test case with exist Test Plan");
  }
  
  /**
   * Unit test coverage for {@link RQMConstructionPageVerification#verifyRemoveAssociatedArtifactInRqmArtifact(String, String, String)}
   */
  @Test
  public void testVerifyRemoveAssociatedArtifactInRqmArtifact() {
    loadPage("rqm/removeTestCase.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyRemoveAssociatedArtifactInRqmArtifact("22024", "", "");
  }
  /**
   * <p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAssociateArtifactToRqmArtifact(String,String, String)}.
   *
   */
  @Test
  public void testVerifyAssociateArtifactToRqmArtifact() {
    loadPage("rqm/addtestcase.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAssociateArtifactToRqmArtifact("13259","Add Test Cases","");
  }
  
  /**
   *<p>Unit test coverage for {@link RQMConstructionPageVerification#testverifyClickOnAddorRemoveColumns(String, String)}. 
   */
  @Test 
  public void testVerifyClickOnAddorRemoveColumns() {
    loadPage("rqm/click_on_add_or_remove_columns_verify.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnAddorRemoveColumns("Add or Remove Columns","");
  }
  
  /**
   *<p>Unit test coverage for {@link RQMConstructionPageVerification#verifyAddColumns(String, String)}. 
   */
  @Test 
  public void testVerifyAddColumns() {
    loadPage("rqm/add_Columns_verify.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAddColumns("Tests Development Items","");
  }
  
  /**
   *<p>Unit test coverage for {@link RQMConstructionPageVerification#verifyIsLinkDisplayedInTable(String, String, String)}. 
   */
  @Test 
  public void testVerifyIsLinkDisplayedInTable() {
    loadPage("rqm/is_Link_Displayed_In_Table.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyIsLinkDisplayedInTable("Validates Architecture Elements","100495","Configuration: DefaultConfig","","");
  }
  
  /*
   * <p> Unit test coverage for {@link RQMConstructionPageVerification#verifyAddOrRemoveLinkType (String, String, String, String)}
   */
  @Test
  public void testVerifyAddOrRemoveLinkType() {
    loadPage("rqm/clickOnAddTypeLinkButton.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAddOrRemoveLinkType("Validates Architecture Elements", "Add Architecture Element Link","","");
  }
  
  /*
   * Unit test coverage for {@link RQMConstructionPageVerification#verifyAddValidatesArchitectureElementsLink (String, String, String)}
   */
  @Test
  public void testAddValidatesArchitectureElementsLink() {
    loadPage("rqm/add_ValidatesArchitectureElementsLink.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyAddValidatesArchitectureElementsLink("rbd.ca.wle.hmc.RS4.01_ApplLyr.Antipinch","AntiPinch","");
  }
  /**
   * Unit test coverage for {@link RQMConstructionPageVerification#verifyGetListSectionsMenuItem(String)}
   */
  @Test
  public void testVerifyGetListSectionsMenuItem() {
    loadPage("rqm/test_get_left_menu_section.html");
    RQMConstructionPageVerification rqm = getJazzPageFactory().getRQMConstructionPageVerification();
    assertNotNull(rqm);
    rqm.verifyGetListSectionsMenuItem("a");
    
    
  }

}
