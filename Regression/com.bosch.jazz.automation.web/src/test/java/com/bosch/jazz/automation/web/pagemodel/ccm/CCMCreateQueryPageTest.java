/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.WebAutomationException;

/**
 * Unit tests for the CCMCreateQueryPage
 */
public class CCMCreateQueryPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads an change and configuration management query page and test the buttons.
   */
  @Test
  public void testButton() {
    loadPage("ccm/newquery_button.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    List<String> buttons = Arrays.asList("Save", "Run", "Cancel");
    Iterator itr = buttons.iterator();
    while (itr.hasNext()) {
      query.button((String) itr.next());
    }
  }

  /**
   * Loads an change and configuration management query page and click on action in search query.
   */
  @Test
  public void testClickOnActionInSharedQuery() {
    loadPage("ccm/queries_click_on__actionIn_shared_query.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.clickOnActionInSharedQuery("test");
  }

  /**
   * Loads an change and configuration management query page and click on add attribute condition option
   */
  @Test
  public void testClickOnAddAttributeCondition() {
    loadPage("ccm/newquery_click_on_add_attribute_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.clickOnAddAttributeCondition();
  }

  /**
   * Loads an change and configuration management query page and click on add column option
   */
  @Test
  public void testClickOnAddColumn() {
    loadPage("ccm/newquery_click_on_add_column.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.clickOnAddColumn();
  }

  /**
   * Loads anchange and configuration management query page and click on add condition option
   */
  @Test
  public void testClickOnAddCondition() {
    loadPage("ccm/newquery_click_on_add_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.clickOnAddCondition();
  }

  /**
   * Loads an change and configuration management query page and click on create query Link in work item menu
   */
  @Test
  public void testClickOnCreateQuaryLink() {
    loadPage("ccm/newquery_click_on_create_quary_link.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.clickOnCreateQuaryLink();
  }

  /**
   * Loads an change and configuration management query page and click on select type.
   */
  @Test
  public void testClickOnSelectType() {
    loadPage("ccm/create_query_click_on_select_type.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.clickOnSelectType("Defect");
    query.selectOptionInDropDownBYIndex("Defect", "1");
  }


  /**
   * Loads an change and configuration management query page and click on shared query Link in work item menu
   */
  @Test
  public void testClickOnSharedQuaryLink() {
    loadPage("ccm/newquery_click_on_create_quary_link.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.clickOnSharedQuaryLink();
  }

  /**
   * Loads an change and configuration management query page and enter on ok button
   */
  @Test
  public void testEnterOkButton() {
    loadPage("ccm/newquery_enter_ok_button.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.enterOKButton();
  }

  /**
   * Loads an change and configuration management query page and click on tabs.
   */
  @Test
  public void testTab() {
    loadPage("ccm/query_tab.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    List<String> tabs = Arrays.asList("Conditions", "Details", "Column Display");
    Iterator itr = tabs.iterator();
    while (itr.hasNext()) {
      query.tab((String) itr.next());
    }
  }

  /**
   * Loads an change and configuration management query page and click on tabs.
   */
  @Test
  public void testTabTwo() {
    loadPage("ccm/query_tabs.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    List<String> tabs = Arrays.asList("Conditions", "Details", "Column Display");
    Iterator itr = tabs.iterator();
    while (itr.hasNext()) {
      query.tab((String) itr.next());
    }
  }

  /**
   * Loads an change and configuration management query page and click on run.
   */
  @Test
  public void testRunQuery() {
    loadPage("ccm/query_run_query.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.runQuery();
  }

  /**
   * Loads an change and configuration management query page and select AND or OR condition.
   */
  @Test
  public void testSelectCondition() {
    loadPage("ccm/newquery_select_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    List<String> conditions = Arrays.asList("AND", "OR");
    Iterator itr = conditions.iterator();
    while (itr.hasNext()) {
      query.selectCondition((String) itr.next());
    }
  }

  /**
   * Loads an change and configuration management query page and select null condition and validating the exception
   * message.
   */
  @Test
  public void testSelectConditionTwo() {
    loadPage("ccm/newquery_select_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String condition = null;
    try {
      query.selectCondition(condition);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid condition name.", e.getMessage());
    }
  }

  /**
   * Loads an change and configuration management query page and sets AND condition.
   */
  @Test
  public void testSelectConditionThree() {
    loadPage("ccm/newquery_select_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.selectCondition("AND");
  }

  /**
   * Loads an change and configuration management query page and sets OR condition.
   */
  @Test
  public void testSelectConditionFour() {
    loadPage("ccm/newquery_select_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.selectCondition("OR");
  }

  /**
   * Loads an change and configuration management query page and select NOR condition and validating the exception
   * message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSelectConditionFive() {
    loadPage("ccm/newquery_select_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.selectCondition("NOR");
  }

  /**
   * Loads an change and configuration management query page and click on recently created link.
   */
  @Test
  public void testClickOnRecentlyCreatedLink() {
    loadPage("ccm/query_click_on_recently_created_link.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.clickOnRecentlyCreatedLink();
  }

  /**
   * Loads an change and configuration management query page and get the column index.
   */
  @Test
  public void testGetColumnIndexd() {
    loadPage("ccm/query_get_column_index.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    List<String> columnNames = Arrays.asList("Type", "Id", "Summary", "Owned By", "Status", "Priority", "Severity",
        "Modified Date", "Actions");
    Iterator itr = columnNames.iterator();
    while (itr.hasNext()) {
      query.getColumnIndex((String) itr.next());
    }
  }

  /**
   * Loads an change and configuration management query page and set query name.
   */
  @Test
  public void testSetQueryName() {
    loadPage("ccm/newquery_set_query_name.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.setQueryName("Open assigned to me");
  }

  /**
   * Loads an change and configuration management query page and set query name as null and validateing the exception
   * message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetQueryNameTwo() {
    loadPage("ccm/newquery_set_query_name.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String queryname = null;
    query.setQueryName(queryname);
  }

  /**
   * Loads an change and configuration management query page and set query name as empty and validateing the exception
   * message
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetQueryNameThree() {
    loadPage("ccm/newquery_set_query_name.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String quryname = "";
    query.setQueryName(quryname);
  }

  /**
   * Loads an change and configuration management query page and filter the text.
   */
  @Test
  public void testFilterText() {
    loadPage("ccm/create_query_filter_text.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.filterText("task");
  }

  /**
   * Loads an change and configuration management query page and filter the text as empty and validate the exception
   * message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFilterTextTwo() {
    loadPage("ccm/query_filter_text.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String filtertext = "";
    query.filterText(filtertext);
  }

  /**
   * Loads an change and configuration management query page and filter the text as null and validate the exception
   * message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFilterTextThree() {
    loadPage("ccm/query_filter_text.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.filterText(null);
  }

  /**
   * Loads an change and configuration management query page and open the project area
   */

  @Test
  public void testOpen() {
    loadPage("ccm/query_open.html");
    CCMCreateQueryPage plan = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "SYS-TEST-com.bosch.rtc.tmpl.Formal_2017.2.0_IBM_6.0.5";
    Map<String, String> param = new HashMap<String, String>();
    param.put("", "");

    plan.open(repositoryURL, projectAreaName, param);
  }

  /**
   * Loads an change and configuration management query page and click on tabs.
   */

  @Test
  public void testSelectTab() {
    loadPage("ccm/query_tab.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String tab = "Conditions";
    query.selectTab(tab);
  }

  /**
   * Loads an change and configuration management query page and click on tabs.
   */

  @Test
  public void testSelectTabTwo() {
    loadPage("ccm/query_tabs.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String tab = "Conditions";
    query.selectTab(tab);
  }

  /**
   * Loads the change and configuration management query page and selects one of the items in the list box
   */
  @Test
  public void testSelectItemInListBox() {
    loadPage("ccm/select_item_in_list_box_approval.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String listBoxItem = "Approvals";
    query.selectItemInListBox(listBoxItem);
  }

  /**
   * Loads the change and configuration management query page and thorws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSelectItemInListBoxs() {
    loadPage("ccm/select_item_in_list_box_approval.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.selectItemInListBox(null);
  }

  /**
   * Loads the change and configuration management query page and selects one of the workitems from queried result.
   */
  @Test
  public void testClickOnSelectedWICheckbox() {
    loadPage("ccm/click_on_selected_wi_checkbox.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    loadNewPageOnFirstDriverClickElementCall("ccm/click_on_selected_wi_checkbox2.html");
    query.clickOnSelectedWICheckbox("USA 2019.1.0");
  }

  /**
   * Loads the change and configuration management query page and saves the created query.
   */
  @Test
  public void testSaveQuery() {
    loadPage("ccm/save_query_enabled.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.saveQuery();
  }

  /**
   * Loads the change and configuration management query page and produces WebAutomationException.
   */
  @Test(expected = WebAutomationException.class)
  public void testSaveQueries() {
    loadPage("ccm/save_query_disabled.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.saveQuery();
  }

  /**
   * Loads the change and configuration management query page and select the attribute.
   */
  @Test
  public void testSelectAttribute() {
    loadPage("ccm/select_type.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.selectAttribute("Type");

  }

  /**
   * Loads the change and configuration management query page and set the inline attribute value(Planned Estimate).
   */

  @Test
  public void testSetInLineAttributeTextBox() {
    loadPage("ccm/query_inline_text_box_planned_estimate.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.setInLineAttributeTextBox("Planned Estimate", "10");
  }

  /**
   * Loads the change and configuration management query page and get the column name.
   */

  @Test
  public void testGetColumnValue() {
    loadPage("ccm/query_inline_text_box_planned_estimate.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.getColumnValue(4);
  }

  /**
   * Loads the change and configuration management query page and checks is work item query saved.
   */
  @Test
  public void testIsWorkItemQuerySaved() {
    loadPage("ccm/save_query_enabled.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    assertFalse(query.isWorkItemQuerySaved());
  }

  /**
   * Loads the change and configuration management query page and checks in line editor attribute data.
   */
  @Test
  public void testInLineEditorAttributeData() {
    loadPage("ccm/query_inLineEditorAttributeData.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/query_inLineEditorAttributeData_one.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String wiName = "Change Request Work Item";
    String[] attribute = { "Summary" };
    query.inLineEditorAttributeData(wiName, attribute);
  }

  /**
   * Loads the change and configuration management query page and checks in line editor attribute data.
   */
  @Test
  public void testInLineEditorAttributeDataConditionOne() {
    loadPage("ccm/query_inLineEditorAttributeData.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/query_inLineEditorAttributeData_one.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String wiName = "Change Request Work Item";
    String[] attribute = { "PM Interface Change Log" };
    query.inLineEditorAttributeData(wiName, attribute);
  }

  /**
   * Loads the change and configuration management query page and checks in line editor attribute data.
   */
  @Test
  public void testInLineEditorAttributeDataConditionTwo() {
    loadPage("ccm/query_inLineEditorAttributeData.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/query_inLineEditorAttributeData_one.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String wiName = "Change Request Work Item";
    String[] attribute = { "W" };
    query.inLineEditorAttributeData(wiName, attribute);
  }


  /**
   * View and run the personal queries
   */
  @Test
  public void testRunPersonalQuery() {
    loadPage("ccm/runpersonalquery_workitem_dropdown_myqueries.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/runpersonalquery_personalquery.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.runPersonalQuery("Personal Query For Automation");
  }

  /**
   * View and run the personal queries
   */
  @Test
  public void testId() {
    loadPage("ccm/set_id_value.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.setId("12345");
  }

  /**
   * Loads anchange and configuration management query page and click on add condition option
   */
  @Test
  public void testClickAddCondition() {
    loadPage("ccm/newquery_click_on_add_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.clickAddCondition();
  }

  /**
   * Loads anchange and configuration management query page and click on add condition option
   */
  @Test
  public void testSearchQueryName() {
    loadPage("ccm/search_query_name.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(query.searchQueryName("test"));
  }

  /**
   * click on query as per the query name.
   */
  @Test
  public void testClickOnQuery() {
    loadPage("ccm/click_on_query.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.clickOnQuery("test");
  }

  /**
   * This method validates the list of values inside the column given by the user
   */
  @Test
  public void testGetListOfContentsForEachColumn() {
    loadPage("ccm/getListOfContentsForEachColumn.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String res = query.getListOfContentsForEachColumn("Owned By");
    Assert.assertTrue(res.contains("CDG ALM Exchange system-user-CC (CAP-SST/ESM3)"));
  }

  /**
   * Delete the query as per the query name.
   */
  @Test
  public void testdeleteQuery() {
    loadPage("ccm/delete_query.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "ccm/delete_query_ok_button.html");
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.deleteQuery("New_Query_06_01_2020_13_01_534");
  }

  /**
   * Delete the query as per the query name.
   */
  @Test
  public void testSelectConditionType() {
    loadPage("ccm/select_condition_type.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/select_condition_type1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.selectConditionType("AND", "Any can match");
  }

  /**
   * View and run the personal queries
   */
  @Test
  public void testSetAttributeTextBox() {
    loadPage("ccm/set_id_value.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.setAttributeTextBox("Id", "12345");
  }

  /**
   * This method validates the list of values inside the column given by the user
   */
  @Test
  public void testClickOnEditCurrentQuery() {
    loadPage("ccm/click_on_edit_current_query.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    getJazzPageFactory().getCCMQueryPage().clickOnEditCurrentQuery();
  }

  /**
   * This method validates the list of values inside the column given by the user
   */
  @Test
  public void testEnableInputConditionValuesWhenQueryIsRun() {
    loadPage("ccm/enable_input_of_condition_values_when_query_is_run.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    getJazzPageFactory().getCCMQueryPage().enableInputConditionValuesWhenQueryIsRun();
  }

  /**
   * This method validates the list of values inside the column given by the user
   */
  @Test
  public void testGetQueryName() {
    loadPage("ccm/get_query_name.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    getJazzPageFactory().getCCMQueryPage().setQueryName("test");
    System.out.println(getJazzPageFactory().getCCMQueryPage().getQueryname());
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getQueryname().contains("test"));
  }

  /**
   * Delete the query as per the query name.
   */
  @Test
  public void testEditQuery() {
    loadPage("ccm/delete_query.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.editQuery("New_Query_06_01_2020_13_01_534");
  }

  /**
   * Edit the description in description text box in Query Page.
   */
  @Test
  public void testAddDescription() {
    loadPage("ccm/edit_description.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/description_box.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.editDescription("New_Query_06_01_2020_13_01_534");
  }

  /**
   * Delete the query as per the query name.
   */
  @Test
  public void testAddColumn() {
    loadPage("ccm/add_column_button.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/add_column_show_link_ok.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/add_column_show_link_ok_verify.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.addColumn("Parent");
  }

  /**
   * Unit test to verify {@link CCMCreateQueryPage#addUser(String,String)}.
   */
  @Test
  public void testAddUser() {
    loadPage("ccm/AddUser_01_DetailsOfNewQuery.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/AddUser_03_SelectUserToAdd.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/AddUser_04_AddedUserSuccessful.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.addUser("DCG9SI", "CDG ALM Project Manager system-user-CC (XC-ECO/ESI1)");
  }

  /**
   * Delete the query as per the query name.
   */
  @Test
  public void testAddSortColumn() {
    loadPage("ccm/add_column_button.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/add_sort_column_attribute.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(4, "ccm/add_sort_column_attribute_verify.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.addSortColumn("Created By");
  }

  /**
   * This method validates project area and team area selected for the query.
   */
  @Test
  public void testSelectTeamAreaOrProjectArea() {
    loadPage("ccm/AddUser_01_DetailsOfNewQuery.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/select_team_project_area.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "ccm/select_team_project_area_verify.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.selectTeamAreaOrProjectArea("SYS-TEST_com.bosch.rtc.tmpl.scrum_2019.2.0_ibm6.0.6.1_RC1", "GC Team");
  }

  /**
   * This method verifies description is removed from query.
   */
  @Test
  public void testDeleteDescription() {
    loadPage("ccm/edit_description.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/description_box.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.deleteDescription();
  }

  /**
   * This method verifies team area removed from query.
   */
  @Test
  public void testRemoveTeamArea() {
    loadPage("ccm/remove_teamarea_user.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/description_box.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.removeTeamArea("GC Team");
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testRemoveUser() {
    loadPage("ccm/remove_teamarea_user.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/description_box.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.removeUser("CDG ALM Exchange system-user-CC (CAP-SST/ESM3)");
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testGetListOfDetails() {
    loadPage("ccm/validate_details_tab.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Assert.assertTrue(query.getDetails("Description").contains("testing"));
  }

  /**
   * This method verifies user removed from query.
   */
  @Test(expected = WebAutomationException.class)
  public void testGetListOfDetailsException() {
    loadPage("ccm/validate_details_tab.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.getDetails("Banglore");
  }

  /**
   * Delete the query as per the query name.
   */
  @Test
  public void testSetColumnOrder() {
    loadPage("ccm/set_column_order.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/set_column_order.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.setColumnOrder("Result Columns", "Id", "Up");
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testValidateSortingOrder() {
    loadPage("ccm/validate_sorting_order.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Assert.assertTrue(
        query.getListOfContentsForEachColumn("Id").contains(query.getSortedOrderValues("Id", "Descending")));
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testValidateSortingOrderAscending() {
    loadPage("ccm/validate_sorting_order_ascending.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Assert.assertTrue(query.getListOfContentsForEachColumn("Id").equals(query.getSortedOrderValues("Id", "Ascending")));
  }

  /**
   * Delete the query as per the query name.
   */
  @Test
  public void testSetSortingOrderType() {
    loadPage("ccm/set_order_type.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.setSortingOrderType("Id", "Ascending");
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testValidateResultColumns() {
    loadPage("ccm/validate_result_columns.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.validateResultColumns();
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testSelectConditionTypeByIndex() {
    loadPage("ccm/select_condition_type_by_index.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/select_condition_type_by_index1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.selectConditionTypeByIndex("Id", "is not", "2");
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testvalidateWildcards() {
    loadPage("ccm/select_condition_type_by_index.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Assert.assertTrue(query.validateWildcardsTextBox());
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testValidateSortingOrderNoResult() {
    loadPage("ccm/select_condition_type_by_index.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Assert.assertTrue(query.validateNoWorkItemsFound());
    Assert.assertFalse(query.isResultsFound());
  }

  /**
   * View and run the personal queries
   */
  @Test
  public void testSetAttributeTextBoxByIndex() {
    loadPage("ccm/select_condition_type_by_index.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.setAttributeTextBoxByIndex("Id", "12345", "2");
  }

  /**
   * View and run the personal queries
   */
  @Test
  public void testSelectValueFromCondition() {
    loadPage("ccm/select_value_from_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.selectValueFromCondition("Type", "Story", "2");
  }

  /**
   * This method selects project area.
   */
  @Test
  public void testSelectProjectArea() {
    loadPage("ccm/select_project_area.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.selectProjectArea("BBM ALM (CCM)_System_Test_Testdata");
  }

  /**
   * This method expect exception if invalid project area set..
   */
  @Test
  public void testSelectProjectAreaWithInvalidData() {
    loadPage("ccm/select_project_area.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.selectProjectArea("BBM ALM (CCM)_System_a.....");
  }

  /**
   * This method expect exception if invalid project area set..
   */
  @Test
  public void testValidateColumnContent() {
    loadPage("ccm/validate_column_content.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.validateColumnContent("Type", "Defect");
    query.validateColumnContent("Owned By", "Ankita Madhav Udyavar (RBEI/ETC3)");
  }

  /**
   * This method verifies user removed from query.
   */
  @Test
  public void testHandleAlert() {
    loadPage("jrs/delete_popup.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.handleAlert();
  }

  /**
   * Loads an change and configuration management query page and click on select type.
   */
  @Test
  public void testCloseAllCondition() {
    loadPage("ccm/newquery_click_on_add_condition.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/newquery_click_on_add_condition.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.closeAllCondition();
  }

  /**
   * Loads anchange and configuration management query page and click on add condition option
   */
  @Test
  public void testSearchDeletedQuery() {
    loadPage("ccm/search_query_name.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.searchDeletedQuery("test");
  }

  /**
   * Unit test to verify {@link CCMCreateQueryPage#clickOnSelectTypeWithTitle(String, String)}.
   */
  @Test
  public void testClickOnSelectTypeWithTitle() {
    loadPage("ccm/clickOnSelectTypeWithTitle01.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/clickOnSelectTypeWithTitle02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.clickOnSelectTypeWithTitle("Type", "Change Request");
  }

  /**
   * @author UUM4KOR unit test case to verify query result count.
   */
  @Test
  public void testQueryResultCount() {
    loadPage("ccm/queryCountOne.html");
    // loadPage("ccm/validate_column_content.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    query.queryResultCount("63");

  }

  /**
   * @author UUM4KOR unit test case to verify selected column value.
   */
  @Test
  public void testGetSelectedColumnValue() {
    loadPage("ccm/queryColumn1.html");
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    query.getSelectedColumnValue("summary");

  }
}