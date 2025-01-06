/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.LoadNewPageOnNthAnswer;
import com.bosch.jazz.automation.web.common.DateUtil;

/**
 * Unit tests coverage for the RQMConstrutionPage.
 */
public class RQMConstrutionPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testGetTestcaseID() {
    Path file = Paths.get("src/test/resources/rqm/test_case_id_files.html");
    AbstractFrameworkUnitTest.driver.get(file.toUri().toString());
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    assertEquals("13233", rqm.getRqmArtifactID());
  }

  /**
   * Loads test data page in quality management and checks if getTestDataID() gets the proper test data id or not.
   */
  @Test
  public void testGetTestdataID() {
    Path file = Paths.get("src/test/resources/rqm/test_data_id.html");
    AbstractFrameworkUnitTest.driver.get(file.toUri().toString());
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    assertEquals("20980", rqm.getTestDataID());
  }

  /**
   * Loads test case execution record page in quality management and checks if getTestcaseExecRecordID() gets the proper
   * Test case execution record id or not.
   */
  @Test
  public void testGetTestcaseexecutionrecordID() {
    Path file = Paths.get("src/test/resources/rqm/test_case_execution_record.html");
    AbstractFrameworkUnitTest.driver.get(file.toUri().toString());
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    assertEquals("58505", rqm.getTestcaseExecRecordID());
  }

  /**
   * Loads copied test case page in quality management and checks if verifyRQMReqTitle() gets the proper copied test
   * case title or not.
   */
  @Test
  public void testVerifyCopiedtestcaseTitle() {
    Path file = Paths.get("src/test/resources/rqm/copied_test_case_title.html");
    AbstractFrameworkUnitTest.driver.get(file.toUri().toString());
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    assertEquals("Copy of TCS_AT_Test_Case_12_04_2019_17_04_566", rqm.verifyDuplictedRqmArtifactByTitle());
  }


  /**
   * Loads RQM test case page and keyTitle(additionalParams) clicks on test case summary and provide the summary data.
   */
  @Test
  public void testKeyTitle() {
    loadPage("rqm/key_title.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Place holder name", "Enter New Test Case Name");
    additionalParams.put("testArtifactTitleValue", "TCS_AT_Test_Case_" + DateUtil.getCurrentDateAndTime());
    assertNotNull(rqm);
    rqm.enterTestArtifactName(additionalParams);
  }

  /**
   * Loads RQM test case page and keyTitle(additionalParams) clicks on test case summary and provide the summary data.
   */
  @Test
  public void testNewKeyTitle() {
    loadPage("rqm/test_enter_new_artifact_name.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactTitleValue", "TCS_AT_Test_Case_" + DateUtil.getCurrentDateAndTime());
    assertNotNull(rqm);
    rqm.renameTestArtifactName(additionalParams);
  }

  /**
   * Loads RQM browse test data page and verifyRQMReqTitle() will get test data title and check name will return null if
   * : doesn't exist.
   */
  @Test
  public void testVerifyRqmReqTitles() {
    loadPage("rqm/delete_test_data.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    assertEquals(null, rqm.verifyDuplictedRqmArtifactByTitle());
  }

  /**
   * @throws Exception thrown by PowerMockito when it performs next operation. Loads RQM test case page and
   *           selectTestArtifactTemplate(additionalParams) clicks on Creating using template: and using PowerMockito it
   *           is selecting Classic test case template drop down value.
   */
  @Test
  public void testSelectTestArtifactTemplate() throws Exception {
    loadPage("rqm/select_test_artifact_template.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("creatingUsingTemplateName", "Creating using template:");
    additionalParams.put("testArtifactTemplateValue", "Classic test case template");
    loadNewPageOnFirstDriverClickElementCall("rqm/select_test_artifact_template_dropdown.html");
    assertNotNull(rqm);
    rqm.chooseTemplateFromSummaryOuterNodeSection(additionalParams);
  }

  /**
   * @throws Exception thrown by PowerMockito when it performs next operation. Loads RQM test case page and
   *           selectTestArtifactTemplate(additionalParams) clicks on priority: and using PowerMockito it is selecting
   *           Medium drop down value.
   */
  @Test
  public void testSelectPrority() throws Exception {
    loadPage("rqm/select_prority.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("priorityValue", "Medium");
    loadNewPageOnFirstDriverClickElementCall("rqm/select_prority_dropdown.html");
    assertNotNull(rqm);
    rqm.selectPrority(additionalParams);
  }


  /**
   * Loads RQM test case page and testArtifactDescription(final Map<String, String> additionalParams) clicks on
   * description text box and provide description.
   */
  @Test
  public void testArtifactDescription() {
    loadPage("rqm/click_on_button.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactDescriptionValue", "Test Case Description");
    assertNotNull(rqm);
    rqm.fillDescriptionInSummaryOuterNodeSection(additionalParams);
  }

  /**
   * Loads RQM test case page and artifactManageSections() use to add all sections to the test case.
   *
   * @throws Exception thrown by PowerMockito when it performs click operation on ok button.
   */
  @Test
  public void testAddAllSectionsInManageSections() throws Exception {
    loadPage("rqm/artifact_manage_sections.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    loadNewPageOnFirstDriverClickElementCall("rqm/artifact_manage_sections_button.html");
    assertNotNull(rqm);
    rqm.addAllSectionsInManageSections();
  }

  /**
   * Loads RQM keyword page and summaryTagsSection(final Map<String, String> additionalParams) clicks on tags text box
   * and provide tag name.
   */
  @Test
  public void testSummaryTagsSection() {
    loadPage("rqm/summary_tags_section.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("tagsValue", "system-test");
    assertNotNull(rqm);
    rqm.setTagName(additionalParams);
  }

  /**
   * Loads RQM test data page and uploadTestData(final Map<String, String> additionalParams) use to upload the csv file.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void testUploadTestData() throws Throwable {
    loadPage("rqm/upload_test_data.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testDataCSVFileValue", "src\\test\\resources\\rqm\\Formal PA Users.csv");
    assertNotNull(rqm);
    rqm.uploadTestData(additionalParams);
  }

  /**
   * Loads RQM browse test case page and selectSlideDown() use to click on show inline filter.
   */
  @Test
  public void testSelectSlideDown() {
    loadPage("rqm/select_slide_down.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectSlideDown();
  }


  /**
   * Loads RQM browse test data page and browseDeleteRQMTestData(final Map<String, String> additionalParams) will enter
   * the test data name and search.
   */
  @Test
  public void testBrowseDeleteRQMTestData() {
    loadPage("rqm/browse_delete_rqm_test_data.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("COLUMN_NAME", "Name");
    additionalParams.put("SEARCH_VALUE", "Test_Data_" + DateUtil.getCurrentDateAndTime());
    assertNotNull(rqm);
    rqm.filterRqmArtifactByInlineFilterColoumnName(additionalParams);
  }

  /**
   * Loads RQM test suite page and executionVariables(final Map<String, String> additionalParams) will create execution
   * variable by filling all given data.
   */
  @Test
  public void testExecutionVariables() {
    loadPage("rqm/execution_variables.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("varName", "ExeVarName");
    additionalParams.put("valName", "ExeVarVal");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/execution_variables_create.html");
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertTrue(rqm.createExecutionVariable(additionalParams));
  }

  /**
   * Loads RQM test suite page and executionVariables(final Map<String, String> additionalParams) will create execution
   * variable by filling all given data.
   */
  @Test
  public void testExecutionVariable() {
    loadPage("rqm/execution_variables.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("varName", "ExeVarName");
    additionalParams.put("valName", "ExeVarVal");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/execution_variables_create_disabled.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertTrue(!rqm.createExecutionVariable(additionalParams));
  }


  /**
   *
   */
  @Test
  public void testSelectCriteria() {
    loadPage("rqm/add_teste_envi_content.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("selectCriteriaName", "Select Criteria");
    additionalParams.put("selectOSValue", "Operating System");
    assertNotNull(rqm);
    rqm.selectCriteria("Physical Machine", "Machine");
  }


  /**
   * Loads RQM test suite execution record page and selectExecutionRecDropdown( String selectName, String selectValue)
   * select the test suite execution record value from the test suite execution record: dropdown.
   */
  @Test
  public void testSelectExecutionRecDropdown() {
    loadPage("rqm/select_execution_rec_dropdown.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Test Suite Execution Record value", "New Test Suite Execution Record");
    assertNotNull(rqm);
    rqm.chooseDropdownAndSetValueOnlyInExecutionRecordDialog("Test Suite Execution Record:",
        additionalParams.get("Test Suite Execution Record value"));
  }


  /**
   * Loads RQM browse test case page and verifyDelRqmReq(additionalParams) will check th deleted test case is deleted or
   * not.
   */
  @Test
  public void testVerifyDelRqmReq() {
    loadPage("rqm/verify_del_rqm_reqs.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("verifyMsg", "No items found.");
    additionalParams.put("testName", "id");
    additionalParams.put("testplanID", "13224");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/verify_del_rqm_req.html");
    loadNewPageOnthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals(additionalParams.get("verifyMsg"), rqm.verifyDeletedRqmArtifact(additionalParams));
  }


  /**
   * Loads RQM test case page and selectTypeAttributeselectTypeAttribute(final Map<String, String> additionalParams,
   * String selectName, String selectValue) select the domain value from domain name dropdown.
   */
  @Test
  public void testSelectTypeAttribute() {
    loadPage("rqm/select_type_attribute.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("attributeName", "Domain:");
    additionalParams.put("attributeValue", "RQM");
    assertNotNull(rqm);
    rqm.chooseCategoriesTypeAndSelectValues(additionalParams);
  }

  /**
   * Loads RQM test script page and testScriptStepsHandle(final Map<String, String> additionalParams, String
   * testScriptStep1Name, String testStep1Value) will write the test script description and expected result.
   */
  @Test
  public void testScriptStepsHandle() {
    loadPage("rqm/test_script_steps_handle.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testStep1Value", "Test Script Step1 Description");
    additionalParams.put("testScriptStep1Name", "Step  1");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_script_steps_handle.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.enterTextInTestSteps(additionalParams, "testScriptStep1Name", "testStep1Value");
  }

  /**
   * Loads RQM test plan page and selectCheckBoxAttribute(final Map<String, String> additionalParams, String selectName,
   * String selectValue) will select release dropdown value.
   */
  @Test
  public void testSelectCheckBoxAttribute() {
    loadPage("rqm/select_check_box_attribute.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_check_box_attribute_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectCheckBoxInDropDown("Release:", "USA 2017.1.0");
  }


  /**
   * Loads RQM test script page and formalReviewSection(final Map<String, String> additionalParams) will add test script
   * approver.
   */
  @Test
  public void testFormalReviewSection() {
    loadPage("rqm/formal_review_section.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("approvalName", "Approval");
    additionalParams.put("formalReviewDescriptionValue", "Approval Description added");
    additionalParams.put("addApproverName", "Add approver");
    additionalParams.put("reviewUserIdValue", "dgs9si");
    additionalParams.put("reviewUserValue", "‎CDG ALM Exchange system-user-CC (CAP-SST/ESM3) - ‎DGS9SI");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/formal_review_section_description.html");
    // clickToPage.put(2, null);
    // clickToPage.put(3, null);
    clickToPage.put(2, "rqm/formal_review_section_add_approver.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.formalReviewSection(additionalParams);
  }


  /**
   * Loads RQM keyword page and testKeywordModify(final Map<String, String> additionalParams) will add detailed
   * description by filling all given data.
   */
  @Test
  public void testKeywordModify() {
    loadPage("rqm/test_keyword_modify.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactDescriptionValue", "Test Keyword Description Updated");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_keyword_modify_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.editKeyowrdRqmArtifact(additionalParams);
  }

  /**
   * Loads RQM test plan page and deleteExportItem(final Map<String, String> additionalParams) will delete the exported
   * ram test plan by filling all given data.
   */
  @Test
  public void testDeleteExportItem() {
    loadPage("rqm/delete_export_item.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("exportInline", "100%");
    additionalParams.put("alertAccept", "Yes");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/delete_export_item_first.html");
    clickToPage.put(2, "rqm/delete_export_item_second.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.deleteExportedItem(additionalParams);
  }


  /**
   * Loads RQM test plan page and testEnvironments(final Map<String, String> additionalParams) will add test environment
   * to the test plan.
   */
  @Test
  public void testEnvironments() {
    loadPage("rqm/test_environments.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testEnvironment", "Test Environment");
    additionalParams.put("Add Environments", "Add Test Environments");
    additionalParams.put("testEnvironmentName",
        "TE_Acceptance_Test_Environment_IBM_CLM_Version_Test_Server_Eclipse_client");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_environments_first.html");
    clickToPage.put(2, "rqm/test_environments_second.html");
    clickToPage.put(3, null);
    clickToPage.put(4, "rqm/test_environments_third.html");
    clickToPage.put(5, "rqm/test_environments_fourth.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addTestEnvironmentsToRqmArtifacts(additionalParams);
  }

  /**
   * Loads RQM test case page and developmetItems(final Map<String, String> additionalParams) will add ccm work item to
   * the test case.
   */
  @Test
  public void testDevelopmetItems() {
    loadPage("rqm/developmet_items.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Artifact Container Selection", "BBM ALM Dev (CCM) [rb-alm-02-q.de.bosch.com:9443]");
    additionalParams.put("workitemID", "61707");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/developmet_items_first.html");
    clickToPage.put(2, "rqm/developmet_items_first.html");
    clickToPage.put(3, "rqm/developmet_items_third.html");
    clickToPage.put(4, null);
    clickToPage.put(5, "rqm/developmet_items_third_files/com.ibm.team.workitem.html");
    clickToPage.put(6, "rqm/developmet_items_fourth.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addDevelopmentItem(additionalParams);
  }

  /**
   * Loads RQM test case page and selectTestArtifactTemplate(final Map<String, String> additionalParams) clicks on
   * Creating using template: and it is selecting Classic test case template drop down value and handles alert.
   */
  @Test
  public void testSelectTestArtifactTemplates() {
    loadPage("rqm/select_test_artifact_template.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("creatingUsingTemplateName", "Creating using template:");
    additionalParams.put("testArtifactTemplateValue", "Classic test case template");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_test_artifact_template_dropdown.html");
    clickToPage.put(2, "rqm/select_test_artifact_template_alert.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.chooseTemplateFromSummaryOuterNodeSection(additionalParams);
  }


  /**
   * Loads RQM browse test case page and browseDeleteRQMReq(additionalParams) will browse and delete the test case.
   */
  @Test
  public void testBrowseDeleteRQMReq() {
    loadPage("rqm/browse_delete_rqm_req_first.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testplanID", "13468");
    additionalParams.put("deleteTestPlan", "Delete Test Case");
    additionalParams.put("webButton", "Delete");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/browse_delete_rqm_req_first.html");
    clickToPage.put(2, "rqm/browse_delete_rqm_req_first.html");
    clickToPage.put(3, "rqm/browse_delete_rqm_req_second.html");
    loadNewPageOnthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.filterRqmArtifactsAndDelete(additionalParams);
  }

  /**
   * @param pageForCallNumber mapping the call number to the path to the new page relative to SRC_TEST_RESOURCES and
   *          navigate to every page after every mouse action.
   */
  public void loadNewPageOnthDriverClickElementCall(final Map<Integer, String> pageForCallNumber) {
    Answer<Response> answer = new LoadNewPageOnNthAnswer(SRC_TEST_RESOURCES, pageForCallNumber);
    try {
      PowerMockito.doAnswer(answer)
      .when(AbstractFrameworkUnitTest.driver,
          MemberMatcher.method(RemoteWebDriver.class, "execute", String.class, Map.class))
      .withArguments(ArgumentMatchers.eq(DriverCommand.ACTIONS), ArgumentMatchers.anyMap());
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Loads RQM browse test data page and deleteTestData(final Map<String, String> additionalParams) will delete the test
   * data.
   */
  @Test
  public void testDeleteTestData() {
    loadPage("rqm/delete_test_data.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("alertAccept", "Delete");
    additionalParams.put("testplanName", "Test_Data_13_02_2019_14_02_127");
    assertNotNull(rqm);
    rqm.deleteTestData(additionalParams);
  }


  /**
   * Loads RQM browse test case page and verifyDelRqmReqs(additionalParams) will check th deleted test case is deleted
   * or not.
   */
  @Test
  public void testVerifyDelRqmReqs() {
    loadPage("rqm/verify_del_rqm_reqs.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("verifyMsg", "No items found.");
    additionalParams.put("testName", "name");
    additionalParams.put("testplanName", "Test_Data_26_02_2019_11_02_255");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals(additionalParams.get("verifyMsg"), rqm.verifyDeletedRqmArtifact(additionalParams));
  }

  /**
   * Loads RQM browse test case page and verifyDelRqmReq(additionalParams) will check th deleted test case is deleted or
   * not.
   */
  @Test
  public void testVerifyDelRqmReqsWithoutIdAndName() {
    loadPage("rqm/verify_del_rqm_reqs.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("verifyMsg", "No items found.");
    additionalParams.put("testName", "");
    additionalParams.put("testplanName", "Test_Data_26_02_2019_11_02_255");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/verify_del_rqm_reqs.html");
    loadNewPageOnthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals(additionalParams.get("verifyMsg"), rqm.verifyDeletedRqmArtifact(additionalParams));
  }

  /**
   * Loads RQM test case page and duplicateRQMReq(additionalParams) will create a duplicate of test case.
   */
  @Test
  public void testDuplicateRqmReq() {
    loadPage("rqm/duplicate_rqm_req.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    additionalParams.put("dupType",
        "Include links to duplicates of the test artifacts that the original artifacts link to");
    additionalParams.put("finishButton", "Finish");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/duplicate_rqm_req_first.html");
    clickToPage.put(2, "rqm/duplicate_rqm_req_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.duplicateRqmArtifacts(additionalParams);
  }

  /**
   * Loads RQM browse test case page and duplicateRQMReq(additionalParams) will create a duplicate of test plan.
   */
  @Test
  public void testDuplicateRqmReqs() {
    loadPage("rqm/duplicate_rqm_reqs.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Browse Test Plans");
    additionalParams.put("dupType", "Include links to the same artifacts that the original artifacts link to");
    additionalParams.put("finishButton", "Finish");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/duplicate_rqm_reqs_first.html");
    clickToPage.put(2, "rqm/duplicate_rqm_reqs_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.duplicateRqmArtifacts(additionalParams);
  }


  /**
   * Loads RQM test suite page and selectLifeCycleState(final Map<String, String> additionalParams) will change the
   * action drop down draft to ready for review.
   */
  @Test
  public void testSelectLifeCycleState() {
    loadPage("rqm/select_lifecyclestate.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Suite");
    additionalParams.put("selectActionValue", "Ready for review");
    loadNewPageOnFirstDriverClickElementCall("rqm/select_lifecyclestate_dropdown.html");
    assertNotNull(rqm);
    rqm.selectLifeCycleState(additionalParams);
  }

  /**
   * Loads RQM test script page and selectLifeCycleState(final Map<String, String> additionalParams) will change the
   * action drop down draft to ready for review.
   */
  @Test
  public void testSelectLifeCycleStates() {
    loadPage("rqm/select_lifecyclestate.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Script");
    additionalParams.put("selectActionValue", "Ready for review");
    loadNewPageOnFirstDriverClickElementCall("rqm/select_lifecyclestate_dropdown.html");
    assertNotNull(rqm);
    rqm.selectLifeCycleState(additionalParams);
  }

  /**
   * Loads RQM test script page and selectOwner(final Map<String, String> additionalParams) will change the owner from
   * unassigned to admin - admin.
   */
  @Test
  public void testSelectOwner() {
    loadPage("rqm/select_lifecyclestate.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("moreLinkValue", "More...");
    additionalParams.put("userIdValue", "hcm6kor");
    additionalParams.put("ownerValue", "ADMIN - ADMIN");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_owner_dropdown.html");
    clickToPage.put(2, "rqm/select_owner_list.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectOwner(additionalParams);
  }

  /**
   * Loads RQM test keyword page and selectTeamArea(final Map<String, String> additionalParams) will select the team
   * area as alm test team.
   */
  @Test
  public void testSelectTeamArea() {
    loadPage("rqm/select_teamarea.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Keyword");
    additionalParams.put("teamAreaValue", "ALM Test Team");
    loadNewPageOnFirstDriverClickElementCall("rqm/select_teamarea.html");
    assertNotNull(rqm);
    rqm.selectTeamArea(additionalParams);
  }

  /**
   * Loads RQM test case page and selectTeamArea(final Map<String, String> additionalParams) will select the team area
   * as alm test team.
   */
  @Test
  public void testSelectTeamAreas() {
    loadPage("rqm/select_teamareas.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Case");
    additionalParams.put("teamAreaValue", " ALM Test Team");
    loadNewPageOnFirstDriverClickElementCall("rqm/select_teamarea_dropdown.html");
    assertNotNull(rqm);
    rqm.selectTeamArea(additionalParams);
  }

  /**
   * Loads RQM test keyword page and browseRQMRequirement(final Map<String, String> additionalParams, final String
   * artifactID) will browse the test keyword based on id.
   */
  @Test
  public void testBrowseRQMRequirementKeywords() {
    loadPage("rqm/browse_rqm_req_keyword.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Keywords");
    additionalParams.put("artifactID", "123");

    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(4, "rqm/browse_rqm_req_keyword_link.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.filterRqmArtifacts(additionalParams);
  }

  /**
   * Loads RQM test suite page and browseRQMRequirement(final Map<String, String> additionalParams, final String
   * artifactID) will browse the test suite based on id.
   */
  @Test

  public void testBrowseRQMRequirementTestSuites() {
    loadPage("rqm/browse_rqm_req_testsuites.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Suites");
    additionalParams.put("artifactID", "125");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/browse_rqm_req_keyword_link.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.filterRqmArtifacts(additionalParams);
  }

  /**
   * Downloads the RQM test data and verifies if the downloaded file is existing in the given path. Takes
   * testArtifactName and CSVFileName
   */
  @Test
  public void testDownloadTestData() {
    loadPage("rqm/download_testdata.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Data");
    additionalParams.put("File name", "Formal PA Users.csv");
    additionalParams.put("CSVFileName", "Formal+PA+Users.csv");
    additionalParams.put("downloadpath",
        new File("com.bosch.jazz.automation.web\\src\\test\\resources\\rqm").toString());
    assertTrue(!rqm.downloadTestData(additionalParams));
  }

  /**
   * Loads RQM test case execution record page and testCaseExecutionRecordsRun() will execute the execution record and
   * verify the value after execution.
   */
  @Test
  public void testCaseExecutionRecordsRun() {
    loadPage("rqm/test_case_execution_records_run.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_case_execution_records_run_first.html");
    clickToPage.put(2, "rqm/test_case_execution_records_run_second.html");
    clickToPage.put(3, "rqm/test_case_execution_records_run_third.html");
    clickToPage.put(4, "rqm/test_case_execution_records_run_fourth.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals("Passed", rqm.generateTestCaseExecutionRecordAndGetStatus());
  }

  /**
   * Loads RQM test case execution record page and testCaseExecutionRecordsRun() will verify the value with out
   * execution.
   */
  @Test
  public void testCaseExecutionRecordsRuns() {
    loadPage("rqm/test_case_execution_records_run.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_case_execution_records_run_first.html");
    clickToPage.put(2, "rqm/test_case_execution_records_run_third.html");
    clickToPage.put(3, "rqm/test_case_execution_records_run_fourth.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals("Passed", rqm.generateTestCaseExecutionRecordAndGetStatus());
  }

  /**
   * Loads RQM test keyword page and addRQMRequirement(final Map<String, String> additionalParams) will add the
   * requirement artifact to keywords page.
   */
  @Test
  public void testAddRQMRequirement() {
    loadPage("rqm/add_rqm_requirement.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Keyword");
    additionalParams.put("rmreqIdValue", "4840");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/add_rqm_requirement_add_and_close.html");
    clickToPage.put(2, null);

    clickToPage.put(3, null);
    clickToPage.put(4, "rqm/add_rqm_requirement_save.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.associateTestScriptInToRqmArtifacts(additionalParams);
  }

  /**
   * Loads RQM test cases page and addRQMRequirement(final Map<String, String> additionalParams) will add the
   * requirement artifact to RQM test case.
   */
  @Test
  public void testAddRQMRequirements() {
    loadPage("rqm/add_rqm_requirement_testcase.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    additionalParams.put("rmreqIdValue", "3447");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/add_rqm_requirement_textbox.html");
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, "rqm/add_rqm_requirement_save_and_close.html");
    clickToPage.put(5, "rqm/add_rqm_requirement_save.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.associateTestScriptInToRqmArtifacts(additionalParams);
  }

  /**
   * Loads RQM test keyword page and exportTests(final Map<String, String> additionalParams) will export the test
   * keyword as pdf.
   */
  @Test
  public void testExportTests() {
    loadPage("rqm/export_tests_checkbox.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("exportInline", "100%");
    additionalParams.put("exportDetails", "Export Comprehensive");
    assertTrue(rqm.exportRqmArtifacts(additionalParams));
  }

  /**
   * Loads RQM test data page and browseRQMRequirement(final Map<String, String> additionalParams, final String
   * artifactID) will browse the requirement artifact.
   */
  @Test
  public void testBrowseRQMRequirement() {
    loadPage("rqm/browse_rqm_requirement_testdata.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Data");
    additionalParams.put("artifactID", "Formal PA Roles");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/browse_rqm_req_keyword_link.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.filterRqmArtifacts(additionalParams);
  }

  /**
   * Loads RQM test script page and testModifyScript(final Map<String, String> additionalParams) will add a new line
   * under test script and save the test script.
   */
  @Test
  public void testModifyScript() {
    loadPage("rqm/test_modify_script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("stepNumber", "Step  1 Description Press ENTER to edit");
    additionalParams.put("step", "Description Edited");
    assertNotNull(rqm);
    rqm.editTestScriptSteps(additionalParams);
  }

  /**
   * Loads RQM test plan page and testArtifactDesignSection(final Map<String, String> additionalParams) will add test
   * design.
   */
  @Test
  public void testAddContentToTestCaseDesignSection() {
    loadPage("rqm/test_artifact_design_section.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactDesignDescriptionValue", "Test Suite Design Description");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_artifact_design_section_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addContentToTextAreaSection(additionalParams);
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testExecutionRecord() {
    loadPage("rqm/execution_record_main_page.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Cases");
    additionalParams.put("Test Plan value", "Unassigned");
    additionalParams.put("Iteration value", "Unassigned");
    additionalParams.put("testEnvironmentNmae",
        "TE_SYSTEM_TEST_Environment_Windows 10_Firefox_6.0.4 GA_ALM USA 2017.2.0_USA 2017.2.0_USA 2017.2.0_1.8");
    additionalParams.put("Test Environment value", additionalParams.get("testEnvironmentNmae"));
    additionalParams.put("isGenerateANewTestCaseExecutionRecord", "false");
    additionalParams.put("Create a result without executing", "false");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/execution_record_main_page.html");
    clickToPage.put(2, "rqm/execution_record_run_test_case_page.html");
    clickToPage.put(3, "rqm/execution_record_run_test_case_page.html");
    clickToPage.put(4, "rqm/execution_record_script_pass_page.html");
    clickToPage.put(5, "rqm/execution_record_script_show_page.html");
    clickToPage.put(6, "rqm/execution_record_text_page.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals("Passed", rqm.generateExecutionRecordAndGetResult(additionalParams));
  }

  /**
   * Loads RQM test data page and selectViewAs() will select the value from 'view as' dropdown.
   */
  @Test
  public void testSelectViewAs() {
    loadPage("rqm/view_as.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectViewAs("Traceability");
  }

  /**
   * Loads RQM test data page and clickchangeColumnDisplay() will clicking on change column display.
   */
  @Test
  public void testClickChangeColumnDisplay() {
    loadPage("rqm/change_column_display.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickChangeColumnDisplay();
  }

  /**
   * Loads RQM test data page and removeAllColumns() will clicking on remove all column button.
   */
  @Test
  public void testRemoveAllColumns() {
    loadPage("rqm/remove_all.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.removeAllColumns();
  }

  /**
   * Loads RQM test data page and selectFilterText() will select the filter text field.
   */
  @Test
  public void testSelectFilterText() {
    loadPage("rqm/filter_text.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectFilterText();
  }

  /**
   * Loads RQM test data page and selectAvailableColumnsId() will select the column ID.
   */
  @Test
  public void testSelectAvailableColumnsId() {
    loadPage("rqm/select_available_columns_id.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectAvailableColumn("ID");
  }

  /**
   * Loads RQM test data page and clickOnAddSelect() will click on add select button.
   */
  @Test
  public void testClickOnAddSelect() {
    loadPage("rqm/click_on_add_select.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnAddSelect();
  }

  /**
   * Loads RQM test data page and clickOk() will click the ok button in change column display setting.
   */
  @Test
  public void testClickOk() {
    loadPage("rqm/click_ok.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOk();
  }

  /**
   * Loads RQM test data page and clickOnTestDevelopmentItemDropdown() will click test development item dropdown.
   */
  @Test
  public void testClickOnTestDevelopmentItemDropdown() {
    loadPage("rqm/test_development_item_dropdown.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnTestDevelopmentItemDropdown();
  }

  /**
   * Loads RQM test data page and selectHasDevelopmentItemFromDropdown() will select 'HasDevelopmentItem' test
   * development item dropdown.
   */
  @Test
  public void testSelectHasDevelopmentItemFromDropdown() {
    loadPage("rqm/test_development_item_dropdown.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectOptionFromTestDevelopmentItemDropdown("Has Development Item");
  }

  /**
   * Loads RQM test data page and clickFilterButton() will select 'HasDevelopmentItem' test development item dropdown.
   */
  @Test
  public void testClickFilterButton() {
    loadPage("rqm/test_development_item_dropdown.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnFilterButton();
  }

  /**
   * Loads RQM test data page and getActualTestcaseId() will get the test case ID.
   */
  @Test
  public void testGetActualTestcaseId() {
    loadPage("rqm/get_test_development_id_and_name.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getTestCaseIdFromResultsTable();
  }

  /**
   * Loads RQM test data page and getTestDevelopmentItemIdAndName() will get the test development id and name.
   */
  @Test
  public void testGetTestDevelopmentItemIdAndName() {
    loadPage("rqm/get_test_development_id_and_name.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getDevelopmentItemIdAndName();
  }

  /**
   * Loads RQM test data page and getTestDevelopmentItemId() will get the test development id.
   */
  @Test
  public void testGetTestDevelopmentItemId() {
    loadPage("rqm/get_test_development_id_and_name.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getDevelopmentItemId();
  }

  /**
   * Loads RQM test data page and getTestDevelopmentItemId() will get the test development name.
   */
  @Test
  public void testGetTestDevelopmentItemName() {
    loadPage("rqm/get_test_development_id_and_name.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getDevelopmentItemName();
  }

  /**
   * Loads RQM test case page and generateNewTestCaseExecutionRecords(final Map<String, String> additionalParams) will
   * add a test execution record to the test case. Returns true as the operation is complete.
   */
  @Test
  public void testTestSuiteExecutionRecordsRun() {
    loadPage("rqm/test_suite_execution_records_run.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_suite_execution_records_run_1.html");
    clickToPage.put(2, "rqm/test_suite_execution_records_run_1.html");
    clickToPage.put(3, "rqm/test_suite_execution_records_run_2.html");
    clickToPage.put(4, "rqm/test_suite_execution_records_run_3.html");
    clickToPage.put(5, "rqm/test_suite_execution_records_run_4.html");
    clickToPage.put(6, "rqm/test_suite_execution_records_run_5.html");
    clickToPage.put(7, "rqm/test_suite_execution_records_run_6.html");
    clickToPage.put(8, "rqm/test_suite_execution_records_run_7.html");
    clickToPage.put(9, "rqm/test_suite_execution_records_run_7.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals("Passed", rqm.generateTestSuiteExecutionRecordAndGetStatus());
  }

  /**
   * Loads RQM test case page and generateNewTestCaseExecutionRecords(final Map<String, String> additionalParams) will
   * add a test execution record to the test case. Returns true as the operation is complete.
   */
  @Test
  public void testTestSuiteExecutionsRecordsRun() {
    loadPage("rqm/test_suite_execution_records_run.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_suite_execution_records_run_1.html");
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/test_suite_execution_records_run_2.html");
    clickToPage.put(4, "rqm/test_suite_execution_records_run_3.html");
    clickToPage.put(5, "rqm/test_suite_execution_records_run_8.html");
    clickToPage.put(6, null);
    clickToPage.put(7, "rqm/test_suite_execution_records_run_5.html");
    clickToPage.put(8, "rqm/test_suite_execution_records_run_5.html");
    clickToPage.put(9, null);
    clickToPage.put(10, "rqm/test_suite_execution_records_run_6.html");
    clickToPage.put(11, "rqm/test_suite_execution_records_run_7.html");
    clickToPage.put(12, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals("Passed", rqm.generateTestSuiteExecutionRecordAndGetStatus());
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testExecutionsRecord() {
    loadPage("rqm/test_suite_execution_records_run_1.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactName", "Test Suite");
    additionalParams.put("Test Suite Execution Record value", "New Test Suite Execution Record");
    additionalParams.put("Test Plan value", "Unassigned");
    additionalParams.put("Iteration value", "Unassigned");
    additionalParams.put("testEnvironmentNmae",
        "TE_SYSTEM_TEST_Environment_Windows 10_Firefox_6.0.4 GA_ALM USA 2017.2.0_USA 2017.2.0_USA 2017.2.0_1.8");
    additionalParams.put("Test Environment value", additionalParams.get("testEnvironmentNmae"));
    additionalParams.put("selectall", "Select all items on all pages");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_executions_record_run_test_suite_with_plan_itteration.html");
    clickToPage.put(2, "rqm/test_executions_record_run_test_suite_with_plan_itteration.html");
    clickToPage.put(3, "rqm/test_executions_record_run_test_suite_with_plan_itteration.html");
    clickToPage.put(4, "rqm/test_executions_record_run_test_suite_with_plan_itteration.html");
    clickToPage.put(5, "rqm/test_suite_execution_records_run_3.html");
    clickToPage.put(6, "rqm/execution_record_script_pass_page.html");
    clickToPage.put(7, "rqm/execution_record_script_show_page.html");
    clickToPage.put(8, "rqm/test_suite_execution_records_run_5.html");
    clickToPage.put(9, "rqm/test_suite_execution_records_run_6.html");
    clickToPage.put(10, "rqm/test_suite_execution_records_run_7.html");
    clickToPage.put(11, "rqm/test_suite_execution_records_run_7.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertEquals("Passed", rqm.generateExecutionRecordAndGetResult(additionalParams));
  }

  /**
   * Loads RQM test case page and generateNewTestCaseExecutionRecords(final Map<String, String> additionalParams) will
   * add a test execution record to the test case. Returns false as the operation is incomplete.
   */
  @Test
  public void testGenerateNewTestCaseExecutionRecord() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_1.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testEnvironmentNmae", "test");
    additionalParams.put("Generate New Execution Records", "Generate New Test Case Execution Records");
    additionalParams.put("Finish and Save", "Finish and Save Test Case");
    additionalParams.put("searchTestEnvName", "AMD64");
    additionalParams.put("testPlanName", "SamplePlanForAutomation");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/GenerateNewTestCaseExecutionRecord_2.html");
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/GenerateNewTestCaseExecutionRecord_3.html");
    clickToPage.put(4, null);
    clickToPage.put(5, "rqm/GenerateNewTestCaseExecutionRecord_4.html");
    clickToPage.put(6, "rqm/GenerateNewTestCaseExecutionRecord_5.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.generateNewTestCaseExecutionRecord(additionalParams);
  }

  /**
   * Loads RQM test suite page and generateNewTestSuiteExecutionRecords(String testPlan) will add a test excecution
   * record to the test suite.
   */
  @Test
  public void testgenerateNewTestSuiteExecutionRecord() {
    loadPage("rqm/generate_new_testsuite_execution_record_button.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    String testPlan = "test plan created for suite report";
    rqm.generateNewTestSuiteExecutionRecord(testPlan);
  }

  /**
   * Load RQM test script page and Create a new step in the test script.
   */
  @Test
  public void testCreateNewStep() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.createNewStep("Insert New Step Before", "Create Empty Step");
  }

  /**
   * Load RQM test script page and add step values in the 'Description' and 'Expected Result'.
   */
  @Test
  public void testAddNewStepInTestScript() {
    loadPage("rqm/test_script_steps_handle.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("description", "Description is added for Test Script");
    additionalParams.put("expecResultValue", "Step  1 Expected Results");
    additionalParams.put("expectedResult", "Expected Result is added");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_script_steps_handle.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addStepValuesInTestScript(additionalParams);
  }

  /**
   * Load RQM test script page and add step values in the 'Description' and 'Expected Result'.
   */
  @Test
  public void testAddNewStepInNewTestScript() {
    loadPage("rqm/add_Step_Values_In_New_Test_Script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("description", "Description is added for Test Script");
    additionalParams.put("descValue", "Step  1");
    additionalParams.put("expecResultValue", "Step  1 Expected Results");
    additionalParams.put("expectedResult", "Expected Result is added");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_script_steps_handle.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addStepValuesInNewTestScript(additionalParams);
  }


  /**
   * Load RQM test script page and validate the content of the 'Description' and 'Expected Result'.
   */
  @Test
  public void testValidateAddedStep() {
    loadPage("rqm/addedsteps_testscript.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertEquals("Testing test scripts", rqm.getContentOfStep("1", "Description"));
  }

  /**
   * Load RQM test script page and delete the step.
   */
  @Test
  public void testDeleteStep() {
    loadPage("rqm/manualsteps_testscript.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.deleteStep("1");
  }

  /**
   * Load RQM keywords page and click on the test script id.
   */
  @Test
  public void testClickOnTestScriptId() {
    loadPage("rqm/click_testscript_id.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnTestScriptId("3637");
  }

  /**
   * Load Browse webpage for the artifacts and search for the artifacts in filter text box.
   */
  @Test
  public void testSearchRqmArtifactsInFilterTextBox() {
    loadPage("rqm/search_artifacts_filters_textbox.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.searchRqmArtifactsInFilterTextBox("Keyword for Testing");
  }

  /**
   * Load Browse web page for the artifacts and search for the artifacts in filter text box.
   */
  @Test
  public void testFilterAndDeleteRqmArtifact() {
    loadPage("rqm/filter_delete_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/filter_delete_artifact2.html");
    clickToPage.put(3, "rqm/filter_delete_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.filterAndDeleteRqmArtifact("Keyword For Automation Testing", "Delete Keyword", "Delete Keywords");
  }

  /**
   * Load Browse webpage for the test data and search for the test data in filter text box.
   */
  @Test
  public void testFilterAndDeleteTestData() {
    loadPage("rqm/filter_Delete_TestData.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/filter_Delete_TestData1.html");
    clickToPage.put(3, "rqm/filter_Delete_TestData2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.filterAndDeleteTestData("test11");
  }

  /**
   * Load Browse webpage for the artifacts and click on the searched artifacts.
   */
  @Test
  public void testSelectRqmArtifact() {
    loadPage("rqm/select_rqm_artifacts.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectRqmArtifact("1144");
  }

  /**
   * Load RQM construction page and verify Error message displayed.
   */
  @Test
  public void testIsErrorMessageDisplay() {
    loadPage("rqm/test_error_meassage.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isErrorMessageDisplay());
  }

  /**
   * Load RQM construction page and verify parent test case.
   */
  @Test
  public void testIsParentTestCases() {
    loadPage("rqm/test_related_test_case_in_test_script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_related_test_case_in_test_script1.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    Assert.assertTrue(rqm.isParentTestCases("Single File QAC"));
  }

  /**
   * Load RQM construction page and verify No Result in Dialog displayed.
   */
  @Test
  public void testIsNoFoundResultAfterSearchingInDialog() {
    loadPage("rqm/test_no_result_in_dialog.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isNoFoundResultAfterSearchingInDialog());
  }

  /**
   * Verify added Quality Objectives displayed
   */
  @Test
  public void testIsQualityObjectiveDisplayed() {
    loadPage("rqm/message_saved_successfully.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isQualityObjectiveDisplayed("Test Script"));
  }

  /**
   * Click on "Manage the platform to be covered" icon on the Test Environment section
   */
  @Test
  public void testSelectEnvironmentType() {
    loadPage("rqm/select_environment_option.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectEnvironmentType("Browsers");
  }

  /**
   * Select environment option in the "Available Environment Options" dialog
   */
  @Test
  public void testSelectEnvironmentOption() {
    loadPage("rqm/select_environment_option.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectEnvironmentOption("Chrome");
  }

  /**
   * Deselect environment option in the "Available Environment Options" dialog
   */
  @Test
  public void testDeSelectEnvironmentOption() {
    loadPage("rqm/deselect_environment_option.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.deSelectEnvironmentOption("Chrome");
  }

  /**
   * Verify environment type and option is diplayed
   */
  @Test
  public void testIsEnvironmentDisplayed() {
    loadPage("rqm/is_environment_displayed.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isEnvironmentDisplayed("Browsers", "Chrome");
  }

  /**
   * Click on "Add Test Suites" button
   */
  @Test
  public void testClickOnRqmAddTestSuitesButton() {
    loadPage("rqm/click_on_Rqm_Add_TestSuites_Button.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnRqmAddTestSuitesButton();
  }


  /**
   * Verify added approver/reviwer is diplayed
   */
  @Test
  public void testIsReviewerDisplayed() {
    loadPage("rqm/is_Reviewer_Displayed.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isReviewerDisplayed("CDG ALM Tester system-user-CC (CAP-SST/ESM3)");
  }

  /**
   * Delete all added approver/reviwer
   */
  @Test
  public void testDeleteAllReviewer() {
    loadPage("rqm/delete_All_Reviewer.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.deleteAllReviewer();
  }

  /**
   * Verify added Risk Assessment is displayed
   */
  @Test
  public void testIsRiskAssessmentDisplayed() {
    loadPage("rqm/is_Risk_Assessment_Displayed.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isRiskAssessmentDisplayed("Lack of skills", "Mitigation Action");
  }

  /**
   * Verify added Risk Assessment is displayed
   */
  @Test
  public void testIsRIskAssessmentDisplayed() {
    loadPage("rqm/is_Risk_Assessment_Displayed.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isRiskAssessmentDisplayed("invalid", "invalid");
  }

  /**
   * Verify Add Risk type function
   */
  @Test
  public void testAddRiskType() {
    loadPage("rqm/input_mitigation_action.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.addRiskType("Technical");
  }

  /**
   * Verify Add Risk function
   */
  @Test
  public void testAddRisk() {
    loadPage("rqm/input_mitigation_action.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.addRisk("Lack of skills");
  }

  /**
   * Verify Add Likelihood function
   */
  @Test
  public void testAddLikelihood() {
    loadPage("rqm/input_mitigation_action.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.addLikelihood("Medium");
  }

  /**
   * Verify Add Impact function
   */
  @Test
  public void testAddImpact() {
    loadPage("rqm/input_mitigation_action.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.addImpact("Medium");
  }

  /**
   * Verify input MitigationAction function
   */
  @Test
  public void testInputMitigationAction() {
    loadPage("rqm/input_mitigation_action.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.inputMitigationAction("Mitigation Action");
  }

  /**
   * Loads RQM Construction page and verify owner is added.
   */
  @Test
  public void testIsOwnerAdded() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isOwnerAdded("CDG ALM Tester system-user-CC (CAP-SST/ESM3)"));
  }

  /**
   * Loads RQM Construction page and add section in manage section.
   */
  @Test
  public void testAddSectionsOfArtifact() {
    loadPage("rqm/manage_sections_rqm_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.addSectionsOfArtifact("Edit Test Scripts Inline");
  }

  /**
   * Loads RQM Construction page and verify section is added in manage section.
   */
  @Test
  public void testIsSectionAdded() {
    loadPage("rqm/manage_sections_rqm_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isSectionAdded("Edit Test Scripts Inline"));
  }

  /**
   * Loads RQM Construction page and verify test artifact is added.
   */
  @Test
  public void testIsAssociateArtifactDisplayed() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isAssociateArtifactDisplayed("Test Script For Automation Testing08_04_2020_14_04_563"));
  }

  /**
   * Loads RQM Construction page and verify test artifact is added.
   */
  @Test
  public void testIsAssocIateArtifactDisplayed() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isAssociateArtifactDisplayed("invalid");
  }

  /**
   * Loads RQM Construction page and verify test script is added.
   */
  @Test
  public void testVerifyDetailedDescription() {
    loadPage("rqm/detailed_description_keyword.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.verifyDetailedDescription("Testing"));
  }

  /**
   * Loads RQM Construction page and verify test script is added.
   */
  @Test
  public void testIsKeywordDeleted() {
    loadPage("rqm/browse_keyword_delete_keyword.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.isRqmArtifactDisplayed("test_keyword"));
  }

  /**
   * Loads RQM Construction page and select test script in 'Test Scripts' section.
   */
  @Test
  public void testSelectTestScript() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectTestScript("Test Script For Automation Testing08_04_2020_14_04_563");
  }

  /**
   * Loads RQM Construction page and remove rqm artifacts.
   */
  @Test
  public void testRemoveAssociatedArtifactInRqmArtifact() {
    loadPage("rqm/set_owner_add_test_script.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.removeAssociatedArtifactInRqmArtifact("Test Script For Automation Testing08_04_2020_14_04_563",
        "Remove Test Script");
  }

  /**
   * Remove section for test plan, test case, test suite
   */
  @Test
  public void testRemoveSectionsOfArtifact() {
    loadPage("rqm/remove_Sections_Of_Artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.removeSectionsOfArtifact("Pre-Condition");
  }

  /**
   * Remove file for test plan, test case, test suite
   */
  @Test
  public void testRemoveUploadedFile() {
    loadPage("rqm/remove_Uploaded_File.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.removeUploadedFile("filename.png");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testIsUploadedFileDisplayed() {
    loadPage("rqm/remove_Uploaded_File.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isUploadedFileDisplayed("filename.png");
  }

  /**
   * Verify add associate artifact into a Rqm Artifact
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
  }

  /**
   * Verify associate test script with test case
   */
  @Test
  public void testAssociateTestScriptWithTestCase() {
    loadPage("rqm/associate_test_script_with_test_case.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.associateTestScriptWithTestCase("33012: Test Case For Automation Testing");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testIsAttachmentAdded() {
    loadPage("rqm/remove_Uploaded_File.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isAttachmentAdded("filename.png");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testIsAttachmentAddded() {
    loadPage("rqm/remove_Uploaded_File.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isAttachmentAdded("e.png");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testIsTestCaseSecionVisible() {
    loadPage("rqm/remove_Uploaded_File.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isTestCaseSecionVisible();
  }

  /**
   * Verify add associate artifact into a Rqm Artifact
   */
  @Test
  public void testAddCCMWorkitemToRQMArtifact() {
    loadPage("rqm/add_ccmworkitem_to_rqmartifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/add_ccmworkitem_to_rqmartifact.html");
    clickToPage.put(4, "rqm/add_ccmworkitem_to_rqmartifact.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addCCMWorkitemToRQMArtifact("Task for automation 2020");
  }

  /**
   * Verify add development plan into a test plan
   */
  @Test
  public void testAddDevelopmentPlanToTestPlan() {
    loadPage("rqm/add_Development_Plan_In_TP.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/add_Development_Plan_In_TP.html");
    clickToPage.put(4, "rqm/add_Development_Plan_In_TP.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addDevelopmentPlanToTestPlan("Automation Development Plan");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testVerifyPriority() {
    loadPage("rqm/verify_priority.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.verifyPriority("Low");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testVerifyOwner() {
    loadPage("rqm/verify_priority.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.verifyOwner("EXTERNAL Biswajit Behere (Datamatics, RBEI/EMT4)");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testVerifyTestCaseDesign() {
    loadPage("rqm/verify_test_case_design.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.verifyTextAreaAdded("test");
  }

  /**
   * Verify file in test plan, test case, test suite
   */
  @Test
  public void testVerifyMitigation() {
    loadPage("rqm/verify_mitigation.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.verifyMitigation("Lack of skills", "test");
  }

  /**
   * Load RQM construction page and verify Saved successfully message displayed.
   */
  @Test
  public void testIsSavedSuccessfully() {
    loadPage("rqm/verify_mitigation.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isSavedSuccessfully();
  }

  /**
   * Loads RQM test suite page and generateNewTestSuiteExecutionRecords(String testPlan) will add a test excecution
   * record to the test suite.
   */
  @Test
  public void testAddQualityObjectives() {
    loadPage("rqm/add_quality_objectives.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/add_quality_objectives2.html");
    clickToPage.put(2, null);
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addQualityObjectives("Percentage of passed test runs");
  }

  /**
   * Load RQM construction page and verify Saved successfully message displayed.
   */
  @Test
  public void testSwitchToDefaultContent() {
    loadPage("rqm/verify_mitigation.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.switchToDefaultContent();
  }

  /**
   * Verify added approver/reviewer is displayed
   */
  @Test
  public void testVerifyReview() {
    loadPage("rqm/is_Reviewer_Displayed.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.verifyReview("CDG ALM Tester system-user-CC (CAP-SST/ESM3)");
  }

  /**
   * Verify added approver/reviewer is displayed
   */
  @Test
  public void testVerifyTestDataRowDisplayed() {
    loadPage("rqm/rqm_create_TestData.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.verifyTestDataRowDisplayed("lisa.steuver@gmail.com", "12345x!X");
  }

  /**
   * Unit test coverage for {@link RQMConstructionPage#createNewTestScriptInDialog(Map)}.
   * No required categories
   *
   * @author KYY1HC
   */
  @Test
  public void testCreateNewTestScriptNoRequiredCategoriesInDialog() {
    loadPage("rqm/CreateNewTestScriptInDialogNoRequiredCategories_02_OKButtonEnabled.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, "rqm/CreateNewTestScriptInDialogNoRequiredCategories_03_SaveButtonEnabled.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("varName", "New Test Script");
    additionalParams.put("varDescription", "This test suite is created by automation testing");
    additionalParams.put("varCategories", "null");
    additionalParams.put("varDescriptionContent", "Input username and password");
    rqm.createNewTestScriptInDialog(additionalParams);
  }

  /**
   * Unit test coverage for {@link RQMConstructionPage#createNewTestScriptInDialog(Map)}.
   * Two required categories
   *
   * @author KYY1HC
   */
  @Test
  public void testCreateNewTestScriptWithRequiredCategoriesInDialog() {
    loadPage("rqm/CreateNewTestScriptInDialog_01_NewTestScriptDialogWithRequiredFields.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, null);
    clickToPage.put(5, "rqm/CreateNewTestScriptInDialog_02_OKButtonEnabled.html");
    clickToPage.put(6, "rqm/CreateNewTestScriptInDialog_03_SaveButtonEnabled.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("varName", "New Test Script");
    additionalParams.put("varDescription", "This test suite is created by automation testing");
    additionalParams.put("varCategories", "Function:=6.0.3 ifix 003,Test Phase:=Regression Test");
    additionalParams.put("varDescriptionContent", "Input username and password");
    rqm.createNewTestScriptInDialog(additionalParams);
  }

  /**
   * Unit test coverage for {@link RQMConstructionPage#createNewTestScriptWithSameNameInDialog(Map)}.
   */
  @Test
  public void testCreateNewTestScriptWithSameNameInDialog1() {
    loadPage("rqm/create_new_test_script_with_same_name_in_dialog.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("varName", "Test Script 1 For Automation Testing");
    additionalParams.put("varDescription", "Test1");
    additionalParams.put("varCategories", "null");
    additionalParams.put("varDescriptionContent", "Test2");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, "rqm/create_new_test_script_with_same_name_in_dialog_0.html");
    clickToPage.put(5, "rqm/create_new_test_script_with_same_name_in_dialog_1.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.createNewTestScriptWithSameNameInDialog(additionalParams);
  }

  /**
   * Unit test coverage for {@link RQMConstructionPage#createNewTestScriptWithSameNameInDialog(Map)}.
   */
  @Test
  public void testCreateNewTestScriptWithSameNameInDialog2() {
    loadPage("rqm/create_new_test_script_with_same_name_in_dialog_case2.html");
    RQMConstructionPage rqm1 = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams1 = new LinkedHashMap<String, String>();
    additionalParams1.put("varName", "Test Script For Automation Testing_08_12_2020_18_12_940_123");
    additionalParams1.put("varDescription", "Test1");
    additionalParams1.put("varCategories", "null");
    additionalParams1.put("varDescriptionContent", "Test2");
    Map<Integer, String> clickToPage1 = new LinkedHashMap<Integer, String>();
    clickToPage1.put(1, null);
    clickToPage1.put(2, null);
    clickToPage1.put(3, null);
    clickToPage1.put(4, "rqm/create_new_test_script_with_same_name_in_dialog_case2_error.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage1);
    assertNotNull(rqm1);
    rqm1.createNewTestScriptWithSameNameInDialog(additionalParams1);
  }

  /**
   * Verify click on Cancel button when Error shown during create new test script with same name.
   */
  @Test
  public void testClickOnCancelButtonWhenErrorMsgDisplayed() {
    loadPage("rqm/create_new_test_script_with_same_name_in_dialog_case2.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnCancelButtonWhenErrorMsgDisplayed();
    // Case 2
    loadPage("rqm/create_new_test_script_with_same_name_in_dialog_1.html");
    RQMConstructionPage rqm1 = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage1 = new LinkedHashMap<Integer, String>();
    clickToPage1.put(1, "rqm/create_new_test_script_with_same_name_in_dialog_2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage1);
    assertNotNull(rqm1);
    rqm1.clickOnCancelButtonWhenErrorMsgDisplayed();
  }

  /**
   * Loads RQM test case page and duplicateRQMReq(additionalParams) will create a duplicate of test case.
   */
  @Test
  public void testAssocIateArtifactToRqmArtifact() {
    loadPage("rqm/associate_artifact_to_rqm_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.associateArtifactToRqmArtifact("11409", "Add Test Scripts");
  }

  /**
   * Loads RQM test script page and testScriptStepsHandle(final Map<String, String> additionalParams, String
   * testScriptStep1Name, String testStep1Value) will write the test script description and expected result.
   */
  @Test
  public void testValIdateAddedStep() {
    loadPage("rqm/validate_added_step.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testStep1Value", "Test Script Step1 Description");
    additionalParams.put("testScriptStep1Name", "Step  1");
    assertNotNull(rqm);
    rqm.validateAddedStep("1", "Description");
  }

  /**
   * Loads RQM test script page and testScriptStepsHandle(final Map<String, String> additionalParams, String
   * testScriptStep1Name, String testStep1Value) will write the test script description and expected result.
   */
  @Test
  public void testValIDateAddedStep() {
    loadPage("rqm/validate_added_step.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testStep1Value", "Test Script Step1 Description");
    additionalParams.put("testScriptStep1Name", "Step  1");
    assertNotNull(rqm);
    rqm.validateAddedStep("2", "Description");
  }

  /**
   * Loads RQM test script page and testScriptStepsHandle(final Map<String, String> additionalParams, String
   * testScriptStep1Name, String testStep1Value) will write the test script description and expected result.
   */
  @Test
  public void testValIDAteAddedStep() {
    loadPage("rqm/validate_added_step.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testStep1Value", "Test Script Step1 Description");
    additionalParams.put("testScriptStep1Name", "Step  1");
    assertNotNull(rqm);
    rqm.validateAddedStep("2", "Expected Results");
  }

  /**
   * Loads RQM test script page and testScriptStepsHandle(final Map<String, String> additionalParams, String
   * testScriptStep1Name, String testStep1Value) will write the test script description and expected result.
   */
  @Test
  public void testOpen() {
    loadPage("rqm/validate_added_step.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testStep1Value", "Test Script Step1 Description");
    additionalParams.put("testScriptStep1Name", "Step  1");
    assertNotNull(rqm);
    rqm.open("", "", additionalParams);
  }

  /**
   * Loads RQM Construction page and Select the requirement for reconcile.
   */
  @Test
  public void testSelectTheRequirementForReconcile() {
    loadPage("rqm/select_the_searched_requirement.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectTheRequirementForReconcile("34567");
  }

  /**
   * Loads RQM Construction page and choose project area from artifact container.
   */
  @Test
  public void testChooseProjectAreaFromArtifactContainer() {
    loadPage("rqm/choose_project_area_from_Artifact_Container.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.chooseProjectAreaFromArtifactContainer("UBK ALM (RM) [rb-alm-02-q.de.bosch.com:9443]");
  }

  /**
   * Loads RQM Construction page and add requirement in the test case.
   */
  @Test
  public void testAddRequirementInTestCase() {
    loadPage("rqm/add_requirement_in_test_case.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addRequirementInTestCase("365965");
  }

  /**
   * Loads RQM Construction page and click on requirement link.
   */
  @Test
  public void testClickOnRequirementLink() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnRequirementLink("8428: Attribute Order");
  }

  /**
   * Loads RM Artifacts Page and click on manage suspicion option.
   */
  @Test
  public void testManageSuspicion() {
    loadPage("dng/input_Artifact_Content.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.manageSuspicion();
  }

  /**
   * Loads RM Artifacts Page and choose suspicion profile.
   */
  @Test
  public void testChooseSuspicionProfile() {
    loadPage("rqm/choose_suspicion_profile.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.chooseSuspicionProfile("Test_Automation2");
  }

  /**
   * Loads RQM Construction page and select the searched artifact.
   */
  @Test
  public void testSelectTheSearchedArtifact() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.selectTheSearchedArtifact("8428: Attribute Order");
  }

  /**
   * Loads RQM Construction page and create new requirement.
   */
  @Test
  public void testCreateNewRequirement() {
    loadPage("rqm/create_new_requirement.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Requirement Name", "Test_Requirement");
    additionalParams.put("Artifact type", "Test_Requirement");
    additionalParams.put("Artifact format", "Text");
    rqm.createNewRequirement(additionalParams);
  }

  /**
   * Loads RQM Construction page and get reconcile requirement.
   */
  @Test
  public void testGetReconcileRequirement() {
    loadPage("rqm/select_the_searched_requirement.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.getReconcileRequirement().contains("34567"));
  }

  /**
   * Loads RQM Construction page and get suspect status of requirement.
   */
  @Test
  public void testGetSuspectStatusOfRequirement() {
    loadPage("rqm/click_on_requirement_link.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Assert.assertTrue(rqm.getSuspectStatusOfRequirement("8428: Attribute Order","Yes"));
  }

  /**
   * Loads RQM Construction page and get the archived requirement.
   */
  @Test
  public void testGetRequirement() {
    loadPage("rqm/get_requirement.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getRequirement().contains("https://rb-alm-02-q.de.bosch.com:9443/rm");
  }

  /**
   * Loads RQM Construction page and select all the requirements for reconcile.
   */
  @Test
  public void testSelectAllRequirementsForReconcile() {
    loadPage("rqm/select_all_requirements_for_reconcile.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.selectAllRequirementsForReconcile("Select all items on this page");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#filterRequirementLinks(String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testFilterRequirementLinks() {
    loadPage("rqm/testFilterRequirementLinks.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/testFilterRequirementLinks_01.html");
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.filterRequirementLinks("1023997");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#getDescriptionOfRqmArtifact()}
   *
   * @author VDY1HC
   */
  @Test
  public void testGetDescriptionOfRqmArtifact() {
    loadPage("rqm/getDescriptionOfRqmArtifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getDescriptionOfRqmArtifact();
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#editDescriptionRqmArtifact(String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testEditDescriptionRqmArtifact() {
    loadPage("rqm/editDescriptionRqmArtifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.editDescriptionRqmArtifact("new");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#editDescriptionRqmArtifactWithoutSave(String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testEditDescriptionRqmArtifactWithoutSave() {
    loadPage("rqm/editDescriptionRqmArtifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.editDescriptionRqmArtifactWithoutSave("new");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#verifyAllCategoriesValue(String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testVerifyAllCategoriesValue() {
    loadPage("rqm/verifyAllCategoriesValue.htm");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    assertTrue(rqm.verifyAllCategoriesValue(
        "Product:,AE_BE PK Script;Release:,Unassigned;Component:,Unassigned;Test Phase:,Acceptance testing"));
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#clickOnAddNewLinksFromRequirementLinks()}
   *
   * @author KYY1HC
   */
  @Test
  public void testClickOnAddNewLinksFromRequirementLinks() {
    loadPage("rqm/VerifyClickOnContextMenuOrSubMenuForArtifact_01_CreateLinkDisplayed.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/VerifyClickOnContextMenuOrSubMenuForArtifact_02_ArtifactContainerSelectionDisplayed.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.clickOnAddNewLinksFromRequirementLinks();
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#getContentOfTextAreaSection()}
   * @author VDY1HC
   */
  @Test
  public void testGetContentOfTextAreaSection() {
    loadPage("rqm/getContentOfTextAreaSection.htm");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm.getContentOfTextAreaSection());
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#verifyUpdateAttributeHistoryByIndex(Map)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyUpdateAttributeHistoryByIndex() {
    loadPage("rqm/verifyUpdateAttributeHistoryByIndex.htm");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("HISTORY_INDEX", "1");
    additionalParams.put("ATTRIBUTE_NAME", "Test Preparation");
    additionalParams.put("UPDATE_TYPE", "Insert");
    additionalParams.put("ATTRIBUTE_NEW_VALUE", "ggg");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    rqm.verifyUpdateAttributeHistoryByIndex(additionalParams);
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#clickOnLinkInRightSide(String,String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testClickOnLinkInRightSide() {
    loadPage("rqm/clickOnLinkInRightSide1.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/clickOnLinkInRightSide2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.clickOnLinkInRightSide("Validates Requirements", "1051958: Information artifact");
  }

  /**
   * Unit test cover for ${@link RQMConstructionPage#isLinkDisplayed(String,String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testIsLinkDisplayed() {
    loadPage("rqm/clickOnLinkInRightSide1.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isLinkDisplayed("Validates Requirements", "1051958: Information artifact");
  }
  /**
   * Unit test cover ${@link RQMConstructionPage#clickOnTestCaseLinkFromTestExecutionResult(String)}.
   */
  @Test
  public void testClickOnTestCaseLinkFromTestExecutionResult() {
    loadPage("rqm/click_on_testcase_link_from_test_execution_result.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnTestCaseLinkFromTestExecutionResult("16754");
  }
  /**
   * Unit test cover ${@link RQMConstructionPage#verifyLinkValidityStatus(String, String)}.
   */
  @Test
  public void testVerifyLinkValidityStatus() {
    loadPage("rqm/verify_link_validity_status.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.verifyLinkValidityStatus("Link is suspect","365965");
  }
  /**
   * Unit test cover ${@link RQMConstructionPage#getValiditySummary(String, String)}.
   */
  @Test
  public void testGetValiditySummary() {
    loadPage("rqm/get_validity_summary.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getValiditySummary("Suspect","13303");
  }
  /**
   * Unit test cover ${@link RQMConstructionPage#getClearSuspicionStatus()}.
   */
  @Test
  public void testGetClearSuspicionStatus() {
    loadPage("rqm/get_clear_suspicion_status.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getClearSuspicionStatus();
  }
  
  /**
   * Unit test cover ${@link RQMConstructionPage#getTestCaseLinkFromTestSuitesTable(String)}.
   */
  @Test
  public void testGetTestCaseLinkFromTestSuitesTable() {
    loadPage("rqm/test_cases_from_test_suites_table.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.getTestCaseLinkFromTestSuitesTable("5570");
  }
  
  /**
   *  Unit test cover ${@link RQMConstructionPage#associateTestCaseWithTestSuite(String, String)}.
   */
  @Test
  public void testAssociateTestCaseWithTestSuite() {
    loadPage("rqm/associate_test_case_with_test_suite.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/associate_test_case_with_test_suite_Okbtn_enabled.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.associateTestCaseWithTestSuite("Test Suite for TC_24623","Associate this test case with new Test Suite");
  }
  
  /**
   *  Unit test cover ${@link RQMConstructionPage#associateTestCaseWithTestPlan(String, String)}.
   */
  @Test
  public void testAssociateTestCaseWithTestPlan() {
    loadPage("rqm/associate_test_case_with_test_plan_1.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/associate_test_case_with_test_plan_2.html");
    clickToPage.put(4, null);
    clickToPage.put(5, "rqm/associate_test_case_with_test_plan_3.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
//    rqm.associateTestCaseWithTestPlan("Associate with Existing Test Plan","361","Testability_Reviews");
    rqm.associateTestCaseWithTestPlan("Associate with Existing Test Plan","Testability_Reviews");
  }
  
  /**
   * Unit test cover ${@link RQMConstructionPage#clickOnAddorRemoveColumns(String)}
   */
  @Test
  public void testClickOnAddorRemoveColumns() {
    loadPage("rqm/click_on_add_or_remove_columns.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.clickOnAddorRemoveColumns("Add or Remove Columns");
  }
  /**
   * Unit test cover ${@link RQMConstructionPage#addColumns(String)}
   */
  @Test
  public void testAddColumns() {
    loadPage("rqm/add_Columns.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.addColumns("Tests Development Items");
  }
  
  /**
   * Unit test cover ${@link RQMConstructionPage#isLinkDisplayedInTable(String,String,String)}
   */
  @Test
  public void testIsLinkDisplayedInTable() {
    loadPage("rqm/is_Link_Displayed_In_Table.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.isLinkDisplayedInTable("Validates Architecture Elements","100495","Configuration: DefaultConfig");
  }
  /**
   * Unit test cover ${RQMConstructionPage#addOrRemoveLinkType(String,String)}
   */
  @Test
  public void testAddOrRemoveLinkType() {
    loadPage("rqm/clickOnAddOrRemoveLinkType.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.addOrRemoveLinkType("Validates Architecture Elements","Add Architecture Element Link");
  }
  
  /**
   * Unit test cover ${RQMConstructionPage#addValidatesArchitectureElementsLink(String,String)}
   */
  @Test
  public void testAddValidatesArchitectureElementsLink() {
    loadPage("rqm/add_ValidatesArchitectureElementsLink.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm);
    rqm.addValidatesArchitectureElementsLink("rbd.ca.wle.hmc.RS4.01_ApplLyr.Antipinch","AntiPinch");
  }
  /**
   * Unit test cover ${RQMConstructionPage#deleteRqmArtifact(String,String,String)}
   */
  @Test
  public void testDeleteRqmArtifact() {
    loadPage("rqm/filter_delete_artifact.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/filter_delete_artifact2.html");
    clickToPage.put(3, "rqm/filter_delete_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.deleteRqmArtifact("Keyword For Automation Testing", "Delete Keyword", "Delete Keywords");
  }
  /**
   * Unit test cover {@link RQMConstructionPage#getListSectionsMenuItem() }
   */
  @Test
  public void testGetListSectionsMenuItem() {
    loadPage ("rqm/test_get_left_menu_section.html");
    RQMConstructionPage rqm = getJazzPageFactory().getRQMConstructionPage();
    assertNotNull(rqm.getListSectionsMenuItem());
  }
  
}

