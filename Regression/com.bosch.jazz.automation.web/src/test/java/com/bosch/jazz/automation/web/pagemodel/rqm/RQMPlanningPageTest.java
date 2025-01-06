/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.InvalidArgumentException;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;

/**
 * Unit tests coverage for the RQMPlanningPage.
 */
public class RQMPlanningPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM test plan page and createSnapshot(final Map<String, String> additionalParams) will create a snapshot
   * return true if its able to create else return false.
   */
  @Test
  public void testCreateSnapshot() {
    loadPage("rqm/create_snapshot.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("SnapshotName", "Snapshot Name " + DateUtil.getCurrentDateAndTime());
    additionalParams.put("SnapshotDescription", "Snapshot Desc" + DateUtil.getCurrentDateAndTime());
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/create_snapshot_second.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertEquals(true, rqm.createSnapshot(additionalParams));
  }

  /**
   * Loads RQM test plan page and createSnapshot(final Map<String, String> additionalParams) will create a snapshot
   * return true if its able tocraete else return false.
   */
  @Test
  public void testCreateSnapshots() {
    loadPage("rqm/create_snapshot.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("SnapshotName", "");
    additionalParams.put("SnapshotDescription", "");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/create_snapshot_first.html");
    clickToPage.put(2, "rqm/create_snapshot_first.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertTrue(!rqm.createSnapshot(additionalParams));
  }

  /**
   * Loads RQM test plan page and setToLocked() use to lock the test plan.
   */
  @Test
  public void testSetToLocked() {
    loadPage("rqm/set_to_locked.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/set_to_locked_comment_with_ok.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    additionalParams.put("userid", "testUser");
    additionalParams.put("userpassword", "testPassword");
    additionalParams.put("lockComments", "comment");
    assertNotNull(rqm);
    rqm.setToLocked(additionalParams);
  }

  /**
   * Loads RQM test data page and selectTestTeam(final Map<String, String> additionalParams) use to select ALM Test Team
   * from Test Team: drop down.
   */
  @Test
  public void testSelectTestTeam() {
    loadPage("rqm/select_test_team_alm_test_team.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testTeam", "ALM Test Team");
    assertNotNull(rqm);
    rqm.selectTestTeam(additionalParams);
  }

  /**
   * Loads RQM test plan page and setToUNLocked() use to unlock the test plan.
   */
  @Test
  public void testSetToUnlocked() {
    loadPage("rqm/set_to_unlocked.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/set_to_locked_comment_with_ok.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    additionalParams.put("userid", "testUser");
    additionalParams.put("userpassword", "testPassword");
    additionalParams.put("unlockComments", "comment");
    rqm.setToUNLocked(additionalParams);
  }

  /**
   * Loads RQM test case page and clickonButton(final String button) clicks on provided button name.
   */
  @Test
  public void testClickOnButton() {
    loadPage("rqm/click_on_button.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.clickonButton("Save");
  }

  /**
   * Loads RQM test plan page and removeAllReqColln(final Map<String, String> additionalParams, String selectall) will
   * delete the requirement.
   */
  @Test
  public void testRemoveAllReqColln() {
    loadPage("rqm/remove_all_req_colln.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("selectall", "Select all items on all pages");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/remove_all_req_colln.html");
    clickToPage.put(2, "rqm/remove_all_req_colln_second.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.removeAllReqColln(additionalParams, "selectall");
  }

  /**
   * Loads RQM test plan page and resources(final Map<String, String> additionalParams) will add the resource.
   */
  @Test
  public void testResources() {
    loadPage("rqm/resources.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Select Script Type", "Rational Robot");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/resources_first.html");
    clickToPage.put(2, "rqm/resources_first.html");
    clickToPage.put(3, "rqm/resources_second.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.resources(additionalParams);
  }

  /**
   * Loads RQM test plan page and addTestEnvironment(final Map<String, String> additionalParams) will test environment
   * in test environments section.
   */
  @Test
  public void testaddTestEnvironment() {
    loadPage("rqm/add_test_environment.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testenvironmentName", "Test Environment");
    additionalParams.put("Add Environments", "Add Test Environments");
    additionalParams.put("testEnvironmentTitleName", "Test_Environment_13_02_2019_15_02_749");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/add_test_environments.html");
    clickToPage.put(2, "rqm/add_test_environment_select_test_environments.html");
    clickToPage.put(3, null);
    clickToPage.put(4, null);
    clickToPage.put(5, "rqm/add_test_environment_checkbox.html");
    clickToPage.put(6, "rqm/add_test_environment_save.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addTestEnvironment(additionalParams);
  }

  /**
   * Loads RQM test plan page and addTestSchedule(final Map<String, String> additionalParams) will add iteration in test
   * schedule section.
   */
  @Test
  public void testAddTestSchedule() {
    loadPage("rqm/add_test_schedule_browse.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("browseButtonName", "Browse");
    additionalParams.put("iterationTitle", "Test_Iteration_03_05_2019_12_05_809 ");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/add_test_schedule_select_iteration.html");
    clickToPage.put(2, "rqm/add_test_schedule_select_iteration_ok.html");
    clickToPage.put(3, "rqm/add_test_schedule_save.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addTestSchedule(additionalParams);
  }

  /**
   * Loads RQM test plan page and developmentPlanLinks(final Map<String, String> additionalParams) will add development
   * plan link and save the test plan.
   */
  @Test
  public void testDevelopmentPlanLinks() {
    loadPage("rqm/development_plan_links.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Artifact Container Selection", "BBM ALM Dev (CCM) [rb-alm-02-q.de.bosch.com:9443]");
    additionalParams.put("Plan Name", "ALM-Dev PM Project Backlog ");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/development_plan_links_first.html");
    clickToPage.put(2, "rqm/development_plan_links_first.html");
    clickToPage.put(3, "rqm/development_plan_links_third.html");
    /* Map<Integer, String> clickToPageInframe = new LinkedHashMap<Integer, String>(); */
    clickToPage.put(4, "rqm/development_plan_links_fourth_files/com.htm");
    clickToPage.put(5, "rqm/development_plan_links_second.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    /* loadNewPageOnNthCommandCall(DriverCommand.SWITCH_TO_FRAME, clickToPageInframe); */
    assertNotNull(rqm);
    rqm.developmentPlanLinks(additionalParams);
  }

  /**
   * Loads RQM test plan page and developmentPlanLinks(final Map<String, String> additionalParams) will add development
   * plan link and save the test plan.
   */
  @Test
  public void testDevelopmentPlanLink() {
    loadPage("rqm/development_plan_links.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Artifact Container Selection", "BBM ALM Dev (CCM) [rb-alm-02-q.de.bosch.com:9443]");
    additionalParams.put("Plan Name", "ALM-Dev PM Project Backlog ");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/development_plan_links_first.html");
    clickToPage.put(2, "rqm/development_plan_links_first.html");
    clickToPage.put(3, "rqm/development_plan_links_third.html");
    clickToPage.put(4, "rqm/development_plan_links_fourth_files/com.htm");
    clickToPage.put(5, "rqm/development_plan_links_second.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.developmentPlanLinks(additionalParams);
  }

  /**
   * Loads RQM test plan page and developmentPlanLinks(final Map<String, String> additionalParams) will add requirement
   * link to test plan.
   */
  @Test
  public void testAddRequirementCollectionLink() {
    loadPage("rqm/add_requirement_collection_link.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Artifact Container", "UBK ALM (RM) [rb-alm-02-q.de.bosch.com:9443]");
    additionalParams.put("Collection Selection ID", "192511");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/add_requirement_collection_link_first.html");
    clickToPage.put(2, "rqm/add_requirement_collection_link_ok_button.html");
    clickToPage.put(3, null);
    clickToPage.put(4, null);
    clickToPage.put(5, "rqm/add_requirement_collection_link_ok_button.html");

    clickToPage.put(6, "rqm/click_on_button.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    rqm.addRequirementcollectionlink(additionalParams);
  }

  /**
   * Loads RQM test script page and testPlanCriteria(final Map<String, String> additionalParams) will add test criteria
   * in the quality objective section.
   */
  @Test
  public void testTestPlanCriteria() {
    loadPage("rqm/test_plan_criteria.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("entryCriteria", "Requirements coverage");
    additionalParams.put("OKButton", "OK");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/test_plan_criteria_select_quality.html");
    clickToPage.put(2, "rqm/test_plan_criteria_ok_first.html");
    clickToPage.put(3, "rqm/test_plan_criteria_save.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    try {
      rqm.testPlanCriteria(additionalParams);
    }
    catch (Exception e) {
      org.junit.Assert.fail(e.getMessage());
    }
  }


  /**
   * Loads RQM test suite page and normInfoDocs(final Map<String, String> additionalParams) will add document
   * description and link.
   */
  @Test
  public void testNormInfoDoc() {
    loadPage("rqm/norm_info_doc.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("expResDes", "testdata");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/norm_info_doc_first.html");
    clickToPage.put(2, "rqm/norm_info_doc_second.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertTrue(!rqm.normInfoDocs(additionalParams));
  }


  /**
   * Loads RQM test plan page and selectParentCheckBoxReqLinks() will click on select drop down from requirement
   * collection link section.
   */
  @Test
  public void testSelectParentCheckBoxReqLinks() {
    loadPage("rqm/select_parent_check_box_req_links.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.selectParentCheckBoxReqLinks();
  }

  /**
   * Loads RQM test plan page and selectAllItemsAllPages(final Map<String, String> additionalParams, String selectall)
   * will select "Select all items on all pages" from the drop down.
   */
  @Test
  public void testSelectAllItemsAllPages() {
    loadPage("rqm/select_all_items_all_pages.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("selectall", "Select all items on all pages");
    assertNotNull(rqm);
    rqm.selectAllItemsAllPages(additionalParams, "selectall");
  }

  /**
   * Loads RQM test plan page and verifyRemoveReqColln(additionalParams) checks requirement exist or not in requirement
   * collection links section.
   */
  @Test
  public void testVerifyRemoveReqColln() {
    loadPage("rqm/verify_remove_req_colln.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("verifyMsg", "No items found.");
    assertEquals(additionalParams.get("verifyMsg"), rqm.verifyRemoveReqColln(additionalParams));
  }

  /**
   * Loads RQM test suite page and normInfoDocs(final Map<String, String> additionalParams) will add document
   * description and link.
   */
  @Test
  public void testNormInfoDocs() {
    loadPage("rqm/norm_info_doc.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("expResDes", "testdata");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/norm_info_doc_first.html");
    clickToPage.put(2, "rqm/norm_info_doc_second.html");
    clickToPage.put(2, "rqm/norm_info_doc_third.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rqm);
    assertTrue(rqm.normInfoDocs(additionalParams));
  }

  /**
   * test used to search the artifact id.
   */
  @Test
  public void testSearchTestSpecifications() {
    loadPage("rqm/searchArtifact.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.searchTestSpecifications("1631");
  }

  /**
   * test used to search the artifact id by using dailog box.
   */
  @Test
  public void testSearchTestSpecificationFromTestEnvironment() {
    loadPage("rqm/dailog_search_test_specification.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/searchArtifact.html");
    clickToPage.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.searchTestSpecificationsForTestEnvironment("11671", "Generate Test Case Execution Records");
  }


  /**
   * generate the execution record in 'test cases' sections.
   */
  @Test
  public void testGenerateExecutionRecord() {
    loadPage("rqm/rqm_planning_generate_exe.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.clickOnGenerateNewExecutionRecordButton("56865");
  }

  /**
   * execute the 'generateTestEnv' executor.
   */
  @Test
  public void testSetIterationAndTestEnvironment() {
    loadPage("rqm/generate_test_env.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("dialogName", "Generate Test Case Execution Records");
    additionalParams.put("testEnvName", "AMD64");
    additionalParams.put("Iteration", "Unassigned");
    additionalParams.put("finish and save", "Finish and Save Test Plan");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, "rqm/generate_testEnv_click_finish.html");
    clickToPage.put(5, "rqm/select_iteartion_test_environment_alert.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.setIterationAndTestEnvironment(additionalParams);
  }

  /**
   * Unit test case to add test environment and validate the exception.
   */
  @Test(expected = InvalidArgumentException.class)
  public void testGenerateTestEnvironmentTwo() {
    loadPage("rqm/generate_test_env.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("dialogName", "Generate Test Case Execution Records");
    additionalParams.put("testEnvName", "AMD");
    additionalParams.put("finish and save", "Finish and Save Test Plan");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/generate_testEnv_click_finish.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.setIterationAndTestEnvironment(additionalParams);
  }

  /**
   * check weather all attribute are visible in 'test exe record tab'.
   */
  @Test
  public void testIsTestCaseExeRecAttributeVisible() {
    loadPage("rqm/rqm_istestcase_exerec_attribute_visible.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.getExecutionRecordDetails("AMD64");
  }

  /**
   * delete the execution recored created.
   */
  @Test
  public void testDeleteTestExeRec() {
    loadPage("rqm/delete_testexe_record.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.pageScrollBar();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/delete_testexe_record_button.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.deleteTestExecutionRecord("AMD64","Delete Test Case Execution Record");
  }

  /**
   * Test TSER is visible
   */
  @Test
  public void testIsTSERecordVisible() {
    loadPage("rqm/isTSER_Available.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    // rqm.pageScrollBar();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("TSER_Name", "Expl Test suite_AMD64");
    additionalParams.put("TestEnvironment", "AMD64");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.isTestSuiteExecutionRecordsVisible(additionalParams);
  }

  /**
   * This method used to check whether particular test environment is visible
   */
  @Test
  public void testIsTestEnvVisible() {
    loadPage("rqm/testEnvironment.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("TestEnvironment", "AMD64");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.isTestEnvironmentAdded(additionalParams);
  }

  /**
   * This method used to delete test environment
   **/
  @Test
  public void testDeleteTestEnv() {
    loadPage("rqm/testEnvironment.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/testDeleteEnvironment.html");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("TestEnvironment", "AMD64");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.deleteTestEnvironment(additionalParams);
  }

  /**
   * Loads RQM Planning page and select iteration and generate new test environment.
   */
  @Test(expected = Exception.class)
  public void testSelectIterationAndGenerateTestEnvironment() {
    loadPage("rqm/select_iteration_test_environment.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("TestEnvironment", "AMD64");
    additionalParams.put("Iteration", "IBM CLM 6.0.6.1");
    additionalParams.put("finish and save", "Finish and Save Test Plan");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/GenerateNewTestCaseExecutionRecord_3.html");
    clickToPage.put(3, null);
    clickToPage.put(4, "rqm/GenerateNewTestCaseExecutionRecord_4.html");
    clickToPage.put(5, "rqm/generate_testEnv_click_finish.html");
    clickToPage.put(6, "rqm/select_iteartion_test_environment_alert.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.selectIterationAndGenerateTestEnvironment(additionalParams);
  }

  /**
   * Loads RQM Planning page and click on Test Plan from Execution Record.
   */
  @Test
  public void testClickOnTestPlanFromExecutionRecord() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_1.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.clickOnTestPlanFromExecutionRecord("TestCasesample1_AMD64");
  }

  /**
   * Loads RQM Planning page and select the Iteration as 'Unassigned'.
   */
  @Test
  public void testSelectIteration() {
    loadPage("rqm/select_iteration_test_environment.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.selectIteration("IBM CLM 6.0.6.1");
  }

  /**
   * Loads RQM Planning page and generate the environments.
   */
  @Test
  public void testGenerateTestEnvironments() {
    loadPage("rqm/GenerateNewTestCaseExecutionRecord_3.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/GenerateNewTestCaseExecutionRecord_4.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.generateTestEnvironment("AMD64");
  }

  /**
   * Loads RQM Planning page and select the Iteration as 'Unassigned'.
   */
  @Test
  public void testSetIteration() {
    loadPage("rqm/select_iteration_test_environment.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.setIteration("IBM CLM 6.0.6.1");
  }

  /**
   * Loads RQM Planning page and reuse the existing Test Environment .
   */
  @Test
  public void testReuseExistingTestEnvironment() {
    loadPage("rqm/generate_test_env_new.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/GenerateNewTestCaseExecutionRecord_4.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.reuseExistingTestEnvironment("AMD64");
  }
  /**
   * Unit test coverage for method {@link RQMPlanningPage#inputValueIntoNewTestCaseDlg(Map)}
   * 
   */
  @Test
  public void testCreateNewTestCaseInTestPlan() {
    loadPage("rqm/createNewTestCase.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("NAME_VALUE","test abc");
    params.put("TEST_TYPE_VALUE", "‪Inspection‬");
    rqm.inputValueIntoNewTestCaseDlg(params);
    
  }
  /**
   * Unit test coverage for method {@link RQMPlanningPage#isNewCreatedTestCase(Map)}
   * 
   */
  @Test
  public void testIsNewCreatedTestCase() {
    loadPage("rqm/newTestCaseCreated.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("NAME_VALUE","Test abc_19_10_2022_14_10_391");
    params.put("TEST_TYPE_VALUE", "Inspection");
    rqm.isNewCreatedTestCase(params);
    
  }
  /**
   * Unit test coverage for method {@link RQMPlanningPage#runTestSuiteFromActionsMenu(String)}
   */
  @Test
  public void testRunTestSuiteFromActionsMenu() {
    loadPage("rqm/runtestSuite2.html");
    RQMPlanningPage rqm = getJazzPageFactory().getRQMPlanningPage();
    assertNotNull(rqm);
    rqm.runTestSuiteFromActionsMenu("Run");
  }
}
