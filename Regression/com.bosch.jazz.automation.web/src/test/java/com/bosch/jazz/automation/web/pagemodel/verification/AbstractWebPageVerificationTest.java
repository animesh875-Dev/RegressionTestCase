/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Verifies whether data exists in server by searching in the search box.
 */
public class AbstractWebPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyQuickSearch(String,String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyQuickSearch() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/verify_negative_quick_search.html");
    assertNotNull(jlv);
    jlv.verifyQuickSearch("test", "False", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyQuickSearch(String,String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyQuickSearch() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/workItemQuickSearch2.html");
    assertNotNull(jlv);
    jlv.verifyQuickSearch("458225: Task Work Item Created for adding Approvals automation", "False", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyQuickSearch(String,String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyQuIckSearch() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/workItemQuickSearch2.html");
    assertNotNull(jlv);
    jlv.verifyQuickSearch("Task Work Item Created for adding Approvals automation", "False", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyQuickSearch(String,String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyQuIckSearCh() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workItemQuickSearch2.html");
    clickNumberToPagePath.put(2, "ccm/workItemQuickSearch2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(jlv);
    jlv.verifyQuickSearch("Task Work Item Created for adding Approvals automation", "False", "");
  }

  /**
   * <
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyQuickSearch(String,String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyQUIckSearCh() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workItemQuickSearch2.html");
    clickNumberToPagePath.put(2, "ccm/workItemQuickSearch2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(jlv);
    jlv.verifyQuickSearch("458225: Task Work Item Created for adding Approvals automation", "False", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyQuickSearch(String,String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVErIfyQUIckSearCh() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workItemQuickSearch2.html");
    clickNumberToPagePath.put(2, "ccm/workItemQuickSearch2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(jlv);
    jlv.verifyQuickSearch("invalid task", "False", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSearchedSpecification(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSearchedSpecification() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/workItemQuickSearch2.html");
    assertNotNull(jlv);
    jlv.verifyOpenSearchedSpecification("Change and Configuration Management", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSearchedSpecification(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSearchedSpecification() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/workItemQuickSearch2.html");
    assertNotNull(jlv);
    jlv.verifyOpenSearchedSpecification("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifySelectProjectArea(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifySelectPojectArea() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/workItemQuickSearch2.html");
    assertNotNull(jlv);
    jlv.verifySelectProjectArea("BBM ALM Dev (CCM)", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifySelectProjectArea(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfySelectPojectArea() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifySelectProjectArea("BBM ALM Dev (CCM)", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitle() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/verify_open_main_menu_by_menu_title.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Projects Dashboards", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitle() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Projects Dashboards", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitleRequirements() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_requirements.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Requirements", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleRequirements() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Requirements", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitlePlanning() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_planning.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Planning", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitlePlanning() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Planning", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitleConstruction() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_construction.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Construction", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleConstruction() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Construction", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitleLabManagement() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_labmanagement.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Lab Management", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleLabManagement() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Lab Management", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitleBuilds() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_builds.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Builds", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleBuilds() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Builds", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitleExecution() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_execution.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Execution", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleExecution() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Execution", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitleReports() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_reports.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Reports", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleReports() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Reports", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenMainMenuByMenuTitleChangeRequests() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_main_menu_by_menu_title_changerequests.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Change Requests", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleChangeRequests() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("Change Requests", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenMainMenuByMenuTitle(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenMainMenuByMenuTitleChangeRequest() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenMainMenuByMenuTitle("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSubMenuUnderSection() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/browse_rqm_req_testsuites.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Browse", "Test Suites", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSubMenuUnderSection() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Browse", "Test Suites", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSubMenuUnderSectionCreate() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/key_title.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Create", "Test Case", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSubMenuUnderSectionCreate() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Create", "Test Case", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSubMenuUnderSectionCreateImport() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/verify_open_sub_menu_under_section_import.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Import", "Test Case", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSubMenuUnderSectionCreateImport() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Import", "Test Case", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSubMenuUnderSectionCreateReport() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/set_report_name.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Create Report", "Report", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSubMenuUnderSectionCreateReport() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("Create Report", "Report", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSubMenuUnderSection(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSubMenuUnderSectionCreateRepor() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/workItem_quick_search_next.html");
    assertNotNull(jlv);
    jlv.verifyOpenSubMenuUnderSection("invalid", "Report", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenRQMSection(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenRQMSection() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/click_on_button.html");
    assertNotNull(jlv);
    jlv.verifyOpenRQMSection("Summary", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenRQMSection(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenRQMSection() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/click_on_button.html");
    assertNotNull(jlv);
    jlv.verifyOpenRQMSection("Related Test Suites", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenRQMSection(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOPenRQMSection() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/click_on_button.html");
    assertNotNull(jlv);
    jlv.verifyOpenRQMSection("Description:", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnJazzButtons(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyClickOnJazzButtons() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/click_on_button.html");
    assertNotNull(jlv);
    jlv.verifyClickOnJazzButtons("Save", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnJazzButtons(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyClIckOnJazzButtons() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyClickOnJazzButtons("Save Copy", "true", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnJazzButtons(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyClIckOnJazzButtons() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyClickOnJazzButtons("Run", "false", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnJazzImageButtons(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyClickOnJazzImageButtons() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyClickOnJazzImageButtons("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnJazzImageButtons(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyClickOnJazzImageButtons() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyClickOnJazzImageButtons("", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnDialogButton(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyClickOnDailogButton() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyClickOnDialogButton("", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnDialogButton(String, String,String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyClickOnDailogButton() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyClickOnDialogButton("", "", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsMenu() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsMenu("Manage This Project Area", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenu() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/selectconfigmenubaseline.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Baselines", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenuManageBaselines() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("ccm/newquery_button.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Baselines", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenuManageComponentsandConfigurations() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/selectconfigmenubaseline.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Components and Configurations", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSettingsSubMenuManageComponentsandConfigurations() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("jrs/delete_popup.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Components and Configurations", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenuManageComponentProperties() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Component Properties", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSettingsSubMenuManageComponentProperties() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("jrs/delete_popup.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Component Properties", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenuCreateComponent() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/create_component_dialog_window.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Create Component", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSettingsSubMenuCreateComponent() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("jrs/delete_popup.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Create Component", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenuManageThisProjectArea() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/manage_this_project_area.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage This Project Area", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSettingsSubMenuManageThisProjectArea() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("jrs/delete_popup.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage This Project Area", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenuManageProjectAreas() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/manage_project_areas.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Project Areas", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSettingsSubMenuManageProjectAreas() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("jrs/delete_popup.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Project Areas", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyOpenSettingsSubMenuManageTemplates() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/manage_templates.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Templates", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSettingsSubMenuManageTemplates() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("jrs/delete_popup.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("Manage Templates", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyOpenSettingsSubMenu(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyOpenSettingsSubMenuinvalid() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("jrs/delete_popup.html");
    assertNotNull(jlv);
    jlv.verifyOpenSettingsSubMenu("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnLink(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyClickOnLink() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/select_type_attribute.html");
    assertNotNull(jlv);
    jlv.verifyClickOnLink("", "True");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnLink(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyClickOnLink() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/select_type_attribute.html");
    assertNotNull(jlv);
    jlv.verifyClickOnLink("", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnLinkWithPartOfText(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyClickOnLinkWithPartOfText() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/select_type_attribute.html");
    assertNotNull(jlv);
    jlv.verifyClickOnLinkWithPartOfText("", "True");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyClickOnLinkWithPartOfText(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyClickOnLinkWithPartOfText() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("rqm/select_type_attribute.html");
    assertNotNull(jlv);
    jlv.verifyClickOnLinkWithPartOfText("", "True");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link AbstractWebPageVerification#verifySelectProjectAreaAndGlobalConfiguration(String, String, String, String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifySelectProjectAreaAndGlobalConfiguration() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifySelectProjectAreaAndGlobalConfiguration(
        "SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC", "Test sample Initial Stream",
        "Test sample", "Test sample Initial Stream", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link AbstractWebPageVerification#verifySelectProjectAreaAndGlobalConfiguration(String, String, String, String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfySelectProjectAreaAndGlobalConfiguration() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifySelectProjectAreaAndGlobalConfiguration(
        "SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC", "Test sample Initial Stream", "invalid",
        "Test sample Initial Stream", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link AbstractWebPageVerification#verifySelectProjectAreaAndGlobalConfiguration(String, String, String, String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifySelectGlobalConfiguration() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifySelectGlobalConfiguration("SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC",
        "Test sample Initial Stream", "Test sample", "Test sample Initial Stream", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link AbstractWebPageVerification#verifySelectProjectAreaAndGlobalConfiguration(String, String, String, String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfySelectGlobalConfiguration() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifySelectGlobalConfiguration("SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC",
        "Test sample Initial Stream", "invalid", "Test sample Initial Stream", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyGetRMAttributeValue(String, String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyGetRMAttributeValue() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifyGetRMAttributeValue("", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyGetRMAttributeValue(String, String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyGetRMAttributeValue() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifyGetRMAttributeValue("", "", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyPageNavigation(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerifyPageNavigation() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifyPageNavigation("Administration: Test sample - Requirements Management (RM)", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyPageNavigation(String, String)}.
   * <p>
   * Verifies whether data exists in server by searching in the search box.
   */
  @Test
  public void testVerIfyPageNavigation() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/export_reqif.html");
    assertNotNull(jlv);
    jlv.verifyPageNavigation("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyPageNavigation(String, String)}.
   * <p>
   * Verifies page is navigated successfully.
   */
  @Test
  public void testVerifyNavigateBack() {
    loadPage("dng/verify_input_artifact_content.html");
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(jlv);
    jlv.verifyNavigateBack("/rm/web", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link AbstractWebPageVerification#verifyIsDate01beforeDate02(String, String,String)}.
   * <p>
   * Verifies page is navigated successfully.
   */
  @Test
  public void testVerifyIsDate01beforeDate02() {
    loadPage("dng/verify_input_artifact_content.html");
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(jlv);
    jlv.verifyIsDate01beforeDate02("Mar 15, 2021, 6:47:50 PM", "Mar 15, 2021 6:49 PM", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link AbstractWebPageVerification#verifyGetCreationDateFromBaseline(String, String,String)}.
   * <p>
   * Verifies get Creation Date from baseline successfully.
   */
  @Test
  public void TestVerifyGetCreationDateFromBaseline() {
    loadPage("dng/verify_input_artifact_content.html");
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(jlv);
    jlv.verifyGetCreationDateFromBaseline("Baselines", "New Baseline PPP_17_03_2021_15_03_999", "Mar 17, 2021 3:11 PM");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link AbstractWebPageVerification#verifyCompareTwoInputAsNumber(String, String,String,String)}.
   * <p>
   * Verifies compare two input as number
   */
  @Test
  public void testVerifyCompareTwoInputAsNumber() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    jlv.verifyCompareTwoInputAsNumber("123", "124", "equal", "false");
    assertNotNull(jlv);
  }

  /**
   * Unit test coverage for
   * {@link AbstractWebPageVerification#verifyCompareTwoInputAsText(String, String,String,String)}.
   * <p>
   * Verifies compare two input as number
   */
  @Test
  public void testVerifyCompareTwoInputAsText() {
    AbstractWebPageVerification jlv = getJazzPageFactory().getAbstractWebPageVerification();
    jlv.verifyCompareTwoInputAsNumber("a", "a", "equal", "true");
    assertNotNull(jlv);
  }

  /**
   * @author LPH1HC Unit test for {@link AbstractWebPageVerification#verifySwitchToTheOtherTab(String,String)}
   */
  @Test
  public void testVerifySwitchToTheOtherTab() {
    AbstractWebPageVerification rm = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(rm);
    rm.verifySwitchToTheOtherTab("1", "true");
  }

  /**
   * @author LPH1HC Unit test for {@link AbstractWebPageVerification#verifySwitchToTheOtherTab(String,String)}
   */
  @Test
  public void testVerIfySwitchToTheOtherTab() {
    AbstractWebPageVerification rm = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(rm);
    rm.verifySwitchToTheOtherTab("1", "false");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyIsChangesetDisplayed(String, String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyIsChangesetDisplayed() {
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/isChangesetDisplayed.html");
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyIsChangesetDisplayed("Test change set", "true", "true");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyIsChangesetDisplayed(String, String, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyIsChangesetDisplayed() {
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    loadPage("dng/isChangesetDisplayed.html");
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyIsChangesetDisplayed("Test 123", "false", "false");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyOpenNewTabWithURL(String, String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyOpenNewTabWithURL() {
    loadPage("dng/verifySelectReview.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyOpenNewTabWithURL("https://rb-alm-23-q.de.bosch.com/rm/reviews/",
        "Review for automatic testing TS_25905 - Requirements Management (RM)", "true");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyOpenNewTabWithURL(String, String,String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyOpenNewTabWithURL() {
    loadPage("dng/blank_tab.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyOpenNewTabWithURL("https://rb-alm-23-q.de.bosch.com/rm/reviews/", "test", "true");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyAddDashboardWidget(String, String,String,String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyAddDashboardWidget() {
    loadPage("dng/verifyAddDashboardWidget.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyAddDashboardWidget("General", "All", "Module Explorer", "true");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyAddDashboardWidget(String, String,String,String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyAddDashboardWidget() {
    loadPage("dng/verifyAddDashboardWidget_failed.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyAddDashboardWidget("General", "All", "Module Explorer", "false");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyRemoveWidget(String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyRemoveWidget() {
    loadPage("dng/verifyAddDashboardWidget_failed.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyRemoveWidget("Module Explorer", "true");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyRemoveWidget(String, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyRemoveWidget() {
    loadPage("dng/verifyAddDashboardWidget.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyRemoveWidget("Module Explorer", "false");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifySaveWidget(String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testverifySaveWidget() {
    loadPage("dng/verifyAddDashboardWidget_failed.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifySaveWidget("true");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyEnableACheckBox(String, String, String)} <br>
   * <p>
   * 
   * @author YNT2HC
   */
  @Test
  public void testverifyEnableACheckBox() {
    loadPage("JRS/enableACheckbox1.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyEnableACheckBox("Enable multiple paths or add other source artifacts", "withLabel",
        "");
  }

  /**
   * <p>
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyClickOnLabel(String, String)} <br>
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyClickOnLabel() {
    loadPage("rqm/VerifyClickOnLabel_01.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyClickOnLabel("Baselines", "");
  }
  
  /**
   * Unit tet cover for ${@link AbstractWebPageVerification#verifyIsDateTimeAtTheMoment(String, String, String)} <br>
   * @author VDY1HC
   */
  @Test
  public void testVerifyIsDateTimeAtTheMoment() {
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyIsDateTimeAtTheMoment("Oct 18, 2021, 7:00:13 PM", "MMM d, yyyy, h:mm:ss aaa", "false");
  }

  /**
   * Unit test cover for ${@link AbstractWebPageVerification#verifyQuickSearchFilterByProject(String, String, String, String)} <br>
   * @author VDY1HC
   */
  @Test
  public void testVerifyQuickSearchFilterByProject() {
    loadPage("DNG/quickSearch_03.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyQuickSearchFilterByProject("Module for automation testing 2", "Current Component", "false", "");
  }
  /**
   * Unit test to cover ${@link AbstractWebPageVerification#verifyGetGlobalConfigItemsCount(String, String, String)} with true condition.
   */
  @Test
  public void testVerifyGetGlobalConfigItemsCount() {
    loadPage("rqm/select_project_area_and_global_configuration_stream.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyGetGlobalConfigItemsCount("BR-Inverter BK1 (GC)","Streams","true");
  }
  /**
   *  Unit test to cover ${@link AbstractWebPageVerification#verifyGetGlobalConfigItemsCount(String, String, String)} with false condition.
   */
  @Test
  public void testverifyGetGlobalConfigItemsCount() {
    loadPage("dng/quickSearch_03.html");
    AbstractWebPageVerification abstractVerificationPage = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractVerificationPage);
    abstractVerificationPage.verifyGetGlobalConfigItemsCount("BR-Inverter BK1 (GC)","Streams",null);
  }
  /**
   * Unit test for method ${@link AbstractWebPageVerification#verifyRemoveAllWidgets(String, String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifyRemoveAllWidgets() {
    loadPage("dng/remove_widget_contains_name.html");
    AbstractWebPageVerification rm = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(rm);
    rm.verifyRemoveAllWidgets("AE_TS_25842_Module (1126090) : View_SYSRS_Review", "true");
  }
  
  /**
   * Unit test for method ${@link AbstractWebPageVerification#verifyGetNumberOfRowsInTable(String, String, String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifyGetNumberOfRowsInTable() {
    loadPage("rqm/get_number_of_row_in_table.html");
    AbstractWebPageVerification abstractWebPageVerification = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractWebPageVerification);
    assertEquals("PASSED", abstractWebPageVerification.verifyGetNumberOfRowsInTable("Test Suites", "", "25").getState());
    assertEquals("FAILED", abstractWebPageVerification.verifyGetNumberOfRowsInTable("Test Suites", "10", "15").getState());
  }
  
  /**
   * Unit test for method ${@link AbstractWebPageVerification#verifyShowInlineFilters(String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifyShowInlineFilters() {
    loadPage("rqm/select_slide_down.html");
    AbstractWebPageVerification abstractWebPageVerification = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractWebPageVerification);
    assertEquals("PASSED", abstractWebPageVerification.verifyShowInlineFilters("true").getState());
  }
  
  /**
   * Unit test for method ${@link AbstractWebPageVerification#verifyShowFilters(String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifyShowFilters() {
    loadPage("rqm/show_filters.html");
    AbstractWebPageVerification abstractWebPageVerification = getJazzPageFactory().getAbstractWebPageVerification();
    assertNotNull(abstractWebPageVerification);
    assertEquals("PASSED", abstractWebPageVerification.verifyShowFilters("true").getState());
  }
}
