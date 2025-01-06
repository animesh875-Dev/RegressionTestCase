/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMCreateQueryPageVerification;

/**
 * @author BBW1KOR
 */
public class CCMCreateQueryPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyOpenMenu(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyOpenMenu() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyOpenMenu("Work Items", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyOpenMenu(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyOpenMenus() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/projectarea_clickon_select_workitemtype.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyOpenMenu("Work Items", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetQueryName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetQueryName("sample shared query1", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetQueryName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifysSetQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetQueryName("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySaveButton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySaveCopyButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/newquery_button.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySaveButton("Save Copy", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySaveButton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyRunButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySaveButton("Run", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySaveButton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySaveButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySaveButton("Save", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySaveButton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyCancelButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySaveButton("Close", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyGetQueryName(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetQueryName("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyGetQueryName(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifysGetQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/newquery_button.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetQueryName("check");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnEditCurrentQuery(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnEditCurrentQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/edit_description.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnEditCurrentQuery("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnQuery(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/click_on_edit_current_query.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnQuery("New_Query_06_01_2020_18_01_165", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnQuery(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyclickOnQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/click_on_edit_current_query.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnQuery("New_Query_06_01_2020_18_01_165...", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySelectTab(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectTab() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectTab("Conditions", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySelectTab(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifYSelectTab() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectTab("Conditio", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySearchQueryName(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySearchQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySearchQueryName("test", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySearchQueryName(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifiySearchQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySearchQueryName("sreenivas", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyEditDescription(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyEditDescription() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/edit_description.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "ccm/description_box.html");
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    getJazzPageFactory().getCCMQueryPage().editDescription("New_Query_06_01_2020_13_01_534");
    cqpv.verifyEditDescription("New_Query_06_01_2020_13_01_534", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyEditDescription(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyEdItDescription() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/edit_description.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "ccm/description_box.html");
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    getJazzPageFactory().getCCMQueryPage().editDescription("New_Query_06_01_2020_13_01_534");
    cqpv.verifyEditDescription("sreenivas", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifySelectTeamAreaOrProjectArea(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectTeamAreaOrProjectArea() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_team_project_area_verify.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectTeamAreaOrProjectArea("SYS-TEST_com.bosch.rtc.tmpl.scrum_2019.2.0_ibm6.0.6.1_RC1", "GC Team", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyAddUser(String,String,String)}.
   * <p>
   * Passed case
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddUser() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/AddUser_04_AddedUserSuccessful.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyAddUser("DCG9SI", "CDG ALM Project Manager system-user-CC (XC-ECO/ESI1)", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyAddUser(String,String,String)}.
   * <p>
   * Failed case
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddUser() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/AddUser_04_AddedUserSuccessful.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyAddUser("", "(CAP-SST/ESM3)", "");
  }

   /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyDeleteDescription(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyDeleteDescription() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/edit_description.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "ccm/description_box.html");
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    getJazzPageFactory().getCCMQueryPage().editDescription("New_Query_06_01_2020_13_01_534");
    cqpv.verifyDeleteDescription("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyRemoveTeamArea(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyRemoveTeamArea() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/edit_description.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyRemoveTeamArea("BBM ALM (CCM)_System_Test_Testdata", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyRemoveUser(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyRemoveUser() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/edit_description.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyRemoveUser("CDG ALM Tester system-user-CC (CAP-SST/ESM3)", "");
  }

  
  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifylogOutWithUrl(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifylogOutWithUrl() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/edit_description.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifylogOutWithUrl(driver.getCurrentUrl(), "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifylogOutWithUrl(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfylogOutWithUrl() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/Logout.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifylogOutWithUrl("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyGetDetails(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetDetails() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/validate_details_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetDetails("Description", "", "testing");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyGetDetails(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetDetails() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/validate_details_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetDetails("Description", "", "sreenivas");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyOpenSubMenu(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyOpenSubMenu() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/validate_details_tab.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyOpenSubMenu("Description", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyOpenSubMenu(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyOpenSubMenu() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/projectarea_clickon_select_workitemtype.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyOpenSubMenu("Description", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyDeleteQuery(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyDeleteQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyDeleteQuery("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyDeleteQuery(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyDeleteQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyDeleteQuery("sreenivas", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickAddCondition(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickAddCondition() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/create_query_filter_text.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickAddCondition("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickAddCondition(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickAddCondition() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickAddCondition("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySelectItemInListBox(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectItemInListBox() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/save_query_enabled.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectItemInListBox("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySelectItemInListBox(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySelectItemInListBox() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_item_in_list_box_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectItemInListBox("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnAddAttributeCondition(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnAddAttributeCondition() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type_by_index1.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnAddAttributeCondition("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnAddAttributeCondition(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnAddAttributeCondition() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_item_in_list_box_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnAddAttributeCondition("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyFilterText(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyFilterText() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type_by_index1.html");
    assertNotNull(cqpv);
    cqpv.verifyFilterText("Id", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyFilterText(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyFilterText() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type_by_index1.html");
    assertNotNull(cqpv);
    cqpv.verifyFilterText("type", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnSelectType(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnSelectType() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/save_query_enabled.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnSelectType("Task", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnSelectType(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnSelectType() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/save_query_enabled.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnSelectType("Epic", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyGetListOfContentsForEachColumn(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetListOfContentsForEachColumn() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/save_query_enabled.html");
    assertNotNull(cqpv);
    cqpv.verifyGetListOfContentsForEachColumn("id", "", "Ascending", "1^2^3");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyGetListOfContentsForEachColumn(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetListOfContentsForEachColumn() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/save_query_enabled.html");
    assertNotNull(cqpv);
    cqpv.verifyGetListOfContentsForEachColumn("id", "", "Descending", "1^2^3");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyGetListOfContentsForEachColumn(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetLIstOfContentsForEachColumn() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/save_query_enabled.html");
    assertNotNull(cqpv);
    cqpv.verifyGetListOfContentsForEachColumn("id", "", "", "1^2^3");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetId(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetId() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifySetId("306026", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetId(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetId() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifySetId("1234577889", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyGetEnableInputQueryName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetEnableInputQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifyGetEnableInputQueryName("", "306026...");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyGetEnableInputQueryName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetEnableInputQueryName() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifyGetEnableInputQueryName("", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyEnableInputConditionValuesWhenQueryIsRun(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyEnableInputConditionValuesWhenQueryIsRun() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifyEnableInputConditionValuesWhenQueryIsRun("");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyEnableInputConditionValuesWhenQueryIsRun(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyEnableInputConditionValuesWhenQueryIsRun() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/verify_enable_input_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifyEnableInputConditionValuesWhenQueryIsRun("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetAttributeTextBox(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetAttributeTextBox() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifySetAttributeTextBox("Id", "306026", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetAttributeTextBox(String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetAttributeTextBox() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifySetAttributeTextBox("Id", "12345", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyEditQuery(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyEditQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifyEditQuery("Copy of New_Query_06_01_2020_18_01_165", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyEditQuery(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyEditQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifyEditQuery("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyAddColumn(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddColumn() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/add_column_show_link_ok_verify.html");
    assertNotNull(cqpv);
    cqpv.verifyAddColumn("Id", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyAddColumn(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddColumn() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/add_column_show_link_ok_verify.html");
    assertNotNull(cqpv);
    cqpv.verifyAddColumn("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetColumnOrder(String,String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetColumnOrder() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/set_column_order.html");
    assertNotNull(cqpv);
    cqpv.verifySetColumnOrder("", "Priority", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetColumnOrder(String,String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetColumnOrder() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/set_column_order.html");
    assertNotNull(cqpv);
    cqpv.verifySetColumnOrder("", "Id", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyAddSortColumn(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddSortColumn() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/set_column_order.html");
    assertNotNull(cqpv);
    cqpv.verifyAddSortColumn("Id", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyAddSortColumn(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddSortColumn() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/set_column_order.html");
    assertNotNull(cqpv);
    cqpv.verifyAddSortColumn("Priority", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetSortingOrderType(String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetSortingOrderType() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/verify_set_sorting_order_type.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetSortingOrderType("Id", "Descending", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySetSortingOrderType(String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetSortingOrderType() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/verify_set_sorting_order_type.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetSortingOrderType("Id", "test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyValidateResultColumns(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyValidateResultColumns() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/set_column_order.html");
    assertNotNull(cqpv);
    cqpv.verifyValidateResultColumns("true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyValidateResultColumns(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyValidateResultColumns() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/set_column_order.html");
    assertNotNull(cqpv);
    cqpv.verifyValidateResultColumns("not same as columns displayed in the table");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySelectConditionType(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectConditionType() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type_by_index1.html");
    assertNotNull(cqpv);
    cqpv.verifySelectConditionType("AND", "All must match", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySelectConditionType(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySelectConditionType() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type_by_index1.html");
    assertNotNull(cqpv);
    cqpv.verifySelectConditionType("AND", "test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySearchDeletedQuery(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySearchDeletedQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifySearchDeletedQuery("sreenivas", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifySearchDeletedQuery(String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySearchDeletedQuery() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifySearchDeletedQuery("test", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyValidateWildcardsTextBox(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyValidateWildcardsTextBox() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifyValidateWildcardsTextBox("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyValidateWildcardsTextBox(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyValidateWildcardsTextBox() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifyValidateWildcardsTextBox("", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyvalidateColumnContent(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyvalidateColumnContent() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifyvalidateColumnContent("", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyvalidateColumnContent(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyvalidateColumnContent() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifyvalidateColumnContent("", "", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyValidateNoWorkItemsFound(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyValidateNoWorkItemsFound() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifyValidateNoWorkItemsFound("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyValidateNoWorkItemsFound(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyValidateNoWorkItemsFound() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/search_query_name.html");
    assertNotNull(cqpv);
    cqpv.verifyValidateNoWorkItemsFound("", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnCloseButton(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnCloseButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnCloseButton("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyClickOnCloseButton(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnCloseButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/save_query_enabled.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnCloseButton("");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifySelectConditionTypeByIndex(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectConditionTypeByIndex() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type_by_index1.html");
    assertNotNull(cqpv);
    cqpv.verifySelectConditionTypeByIndex("", "is", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifySelectConditionTypeByIndex(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySelectConditionTypeByIndex() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/select_condition_type_by_index1.html");
    assertNotNull(cqpv);
    cqpv.verifySelectConditionTypeByIndex("", "test", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyselectValueFromCondition(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyselectValueFromCondition() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_inLineEditorAttributeData.html");
    assertNotNull(cqpv);
    cqpv.verifyselectValueFromCondition("", "Change Request", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyselectValueFromCondition(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyselectValueFromCondition() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_inLineEditorAttributeData.html");
    assertNotNull(cqpv);
    cqpv.verifyselectValueFromCondition("", "Need", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifySetAttributeTextBoxByIndex(String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetAttributeTextBoxByIndex() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    assertNotNull(cqpv);
    cqpv.verifySetAttributeTextBoxByIndex("Id", "306026", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyclickOnDailogButton(String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyclickOnDailogButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_inLineEditorAttributeData.html");
    assertNotNull(cqpv);
    cqpv.verifyclickOnDailogButton("", "Run", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyclickOnDailogButton(String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyclickOnDailogButton() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/query_inLineEditorAttributeData.html");
    assertNotNull(cqpv);
    cqpv.verifyclickOnDailogButton("", "Need", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyQueryResultValidation(String, String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   *
   * @throws ParseException Exception
   */
  @Test
  public void testVerifyGetListOfContentsForEachColumnDate() throws ParseException {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/verify_get_listof_contentsfor_each_column_date.html");
    assertNotNull(cqpv);
    try {
      cqpv.verifyQueryResultValidation("May 31, 2019", "May 31, 2019", "446", "",
          "May 31, 2019, 5:06:16 PM^May 10, 2019, 2:47:11 PM^Mar 21, 2019, 4:37:52 PM");
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Unit test coverage for {@link CCMCreateQueryPageVerification#verifyQueryResultValidation(String)}.
   */
  @Test
  public void testVerifyQueryResultValidation() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    cqpv.verifyQueryResultValidation("55");
  }


  /**
   * Unit test coverage for
   * {@link CCMCreateQueryPageVerification#verifyClickOnSelectTypeWithTitle(String, String, String)}.
   */
  @Test
  public void testVerifyClickOnSelectTypeWithTitle() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/clickOnSelectTypeWithTitle02.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnSelectTypeWithTitle("Type", "Change Request", "");
  }

  /**
   * @author UUM4KOR
   * @throws ParseException java Exception
   */
  @Test
  public void testVerifyQueryResultCount() throws ParseException {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/queryCountOne.html");
    assertNotNull(cqpv);
    cqpv.verifyQueryResultCount("63", "63");
  }

  /**
   * @author UUM4KOR
   * @throws ParseException java Exception
   */
  @Test
  public void testVerifyQueryResultCountOne() throws ParseException {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/queryCountOne.html");
    assertNotNull(cqpv);
    cqpv.verifyQueryResultCount("63", "0");
  }

  /**
   * @author UUM4KOR
   */
  @Test
  public void testVerifyGetSelectedColumnValue() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/queryCountOne.html");
    assertNotNull(cqpv);
    cqpv.verifyGetSelectedColumnValue("summary", "PCR", "PCR - Environmental tests,PCR - Environmental Test");
  }

  /**
   * @author UUM4KOR
   */
  @Test
  public void testVerifyGetSelectedColumnValueOne() {
    CCMCreateQueryPageVerification cqpv = getJazzPageFactory().getCCMCreateQueryPageVerification();
    loadPage("ccm/queryCountOne.html");
    assertNotNull(cqpv);
    cqpv.verifyGetSelectedColumnValue("summary", "", "");
  }
}
