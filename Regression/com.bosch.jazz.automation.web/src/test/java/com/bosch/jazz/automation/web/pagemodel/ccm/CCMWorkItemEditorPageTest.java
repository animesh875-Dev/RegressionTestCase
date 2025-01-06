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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkSection;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkTypes;

import junit.framework.Assert;

/**
 * Unit tests for the CCMWorkItemEditorPage
 *
 * @author taa6si
 */
@SuppressWarnings("deprecation")
public class CCMWorkItemEditorPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads an Overview page of an Epic and checks if getWorkItemID() gets the proper id
   */
  @Test
  public void testGetWorkItemID() {
    loadPage("ccm/workitem_id.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertEquals("416984", wi.getWorkItemID());
  }

  /**
   * Loads an Overview page of an Epic and checks if getWorkItemID() gets the workiten id
   */
  @Test
  public void testGetWorkItemIDTwo() {
    loadPage("ccm/wi_id_null.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.getWorkItemID();

  }

  /**
   * Load an overview page of an Task and check if getStatus() gets the proper status or not.
   */
  @Test
  public void testGetStatus() {
    loadPage("ccm/task_status.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertEquals("In Progress", wi.getStatus());
  }

  /**
   * Load an overview page of an Task and check if GetResolution() gets the proper resolution or not.
   */
  @Test
  public void testGetResolution() {
    loadPage("ccm/task_resolution.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertEquals("Solved", wi.getResolution());
  }

  /**
   * Load an overview page of an Task and check if GetValidationMessage() gets error message or not.
   */
  @Test
  public void testGetValidationMessage() {
    loadPage("ccm/task_validation_message.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String errorMsg = "You don't have permission to perform the following actions:";
    assertTrue(wi.getValidationMessage().contains(errorMsg));
  }

  /**
   * 
   */
  @Test
  public void testgetNotificationMessage() {
    loadPage("ccm/testMessage.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String errorMsg = "Attribute 'Summary' not set.";
    assertTrue(wi.getValidationMessageFromNotificationArea().contains(errorMsg));

  }


  /**
   * Load an overview page of an Task and check if IsWorkItemSaved() saved or not.
   */
  @Test
  public void testIsWorkItemSaved() {
    loadPage("ccm/task_is_workitem_saved.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertTrue(wi.isWorkItemSaved());
  }

  /**
   * Load an overview page of an Task and check if GetTabNamesList() gets the list of tab names.
   */
  @Test
  public void testGetTabNamesList() {
    loadPage("ccm/task_tab_names_list.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertTrue(wi.getTabNamesList().size() > 0);

  }

  /**
   * Load an overview page of an Task and check if GetDropDownValue() gets value of the dropdown or not.
   */
  @Test
  public void testGetDropDownValue() {
    loadPage("ccm/task_dropdown_value.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertEquals("Category1", wi.getDropDownValue(WorkItemAttribute.FILED_AGAINST.toString()));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isAttributeExist() gets the proper Attribute is their or
   * not.
   */
  @Test
  public void testIsAttributeExist() {
    loadPage("ccm/workitem_is_attribute_exist.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Meeting Type";
    assertTrue(wi.isAttributeExist(attribute));
  }


  /**
   * Loads an Overview page of an workitem meeting and checks wheather isAttributeMandatory() returns true if the given
   * attribute is mandotary.
   */
  @Test
  public void testIsAttributeMandatory() {
    loadPage("ccm/workitem_is_attribute_mandatory.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Summary";
    assertTrue(wi.isAttributeMandatory(attribute));
  }

  /**
   * Loads an change and configuration management and checks if isAttributeMandatory() wether the proper Attribute is
   * mandatory or not.
   */

  @Test
  public void testIsAttributeMandatoryConditionTwo() {
    loadPage("ccm/testStar.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Tags";
    assertTrue(wi.isAttributeMandatory(attribute));
  }

  /**
   * Loads an change and configuration management and checks if isAttributeMandatory() wether the proper Attribute is
   * mandatory or not.
   */
  @Test
  public void testIsAttributeMandatoryThree() {
    loadPage("ccm/test_description.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Description";
    Assert.assertTrue(wi.isAttributeMandatory(attribute));
  }

  /**
   * Loads an change and configuration management and checks if isAttributeMandatory() wether the proper Attribute is
   * mandatory or not.
   */
  @Test
  public void testIsAttributeMandatoryFour() {
    loadPage("ccm/workitem_is_attribute_mandatory.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Description";
    Assert.assertFalse(wi.isAttributeMandatory(attribute));
  }

  /**
   * Loads an change and configuration management and checks if isAttributeMandatory() wether the proper Attribute is
   * mandatory or not.
   */
  @Test
  public void testIsAttributeMandatoryFive() {
    loadPage("ccm/workitem_is_attribute_mandatory.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Tags";
    Assert.assertFalse(wi.isAttributeMandatory(attribute));
  }

  /**
   * Loads an Overview page of an workitem meeting and checks if clickOnLink() gets clicking proper Link or not
   */
  @Test
  public void testClickOnLink() {
    loadPage("ccm/workitem_click_on_link.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.clickOnLink("Links");
  }

  /**
   * Loads an Overview page of an workitem task and checks if selectTab() gets select the proper Tab or not
   */
  @Test
  public void testSelectTab() {
    loadPage("ccm/workitem_select_tab.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.selectTab("Links");

  }


  /**
   * Loads an Overview page of an workitem task and checks if isAttributeWritable() checks wether attribute is writable
   * or not
   */
  @Test
  public void testSaveWorkitem() {
    loadPage("ccm/workitem_save_workitem.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.saveWorkItem();
  }

  /**
   * Loads an Overview page of an workitem task and checks if isAttributeWritable() checks wether attribute is writable
   * or not
   */
  @Test
  public void testNotSaveWorkitem() {
    loadPage("ccm/SaveWorkItemDisabled.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.isWorkItemSaved();
  }

  /**
   * Loads an Overview page of an workitem defect and checks if setResolution() set the proper Resolution
   */
  @Test
  public void testSetResolution() {
    loadPage("ccm/workitem_set_resolution.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setResolution("Duplicate");

  }

  /**
   * Loads an Overview page of an workitem defect and checks if setResolution() set the proper status
   */
  @Test
  public void testSetStatus() {
    loadPage("ccm/workitem_set_status.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setStatus("close");
  }


  /**
   * Loads an Overview page of an workitem Task and checks if setTags() set the proper Tag
   */
  @Test
  public void testSetTags() {
    loadPage("ccm/workitem_set_status.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setTags("systemtest_rcm_rc03_2018_2.0");
  }

  /**
   * Loads an overview page of an task workitem and checks if getLeftAttributeValue() gets the proper attribute value.
   */
  @Test
  public void testGetLeftAttributeValue() {
    loadPage("ccm/workitem_get_left_attribute_value.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String[] arr = { "Type", "Task Type", "Severity", "Priority", "Filed Against" };
    String[] value = {
        "Type : Task",
        "Task Type : 020 Requirement Specification",
        "Severity : Major",
        "Priority : High",
        "Filed Against : Category1" };
    List<String> list = Arrays.asList(value);
    Assert.assertTrue(wi.getLeftAttributeValue(arr).size() > 0);
    Assert.assertTrue(wi.getLeftAttributeValue(arr).containsAll(list));
  }

  /**
   * Loads an overview page of an task workitem and checks if getRightAttributeValue() gets the proper attribute value.
   */
  @Test
  public void testGetRightAttributeValue() {
    loadPage("ccm/workitem_get_right_attribute_value.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String[] arr = { "Tags", "Owned By", "Target Configuration", "Planned For", "Due Date", "Constraint Type" };
    Assert.assertTrue(wi.getRightAttributeValue(arr).size() > 0);
    String[] value = {
        "Tags : systemtest_rcm_rc03_2018_2.0",
        "Owned By : CDG ALM Exchange system-user-CC (CAP-SST/ESM3)",
        "Target Configuration : Unassigned",
        "Planned For : Release 1.0",
        "Due Date : ",
        "Constraint Type : Start No Earlier Than" };
    List<String> list = Arrays.asList(value);
    Assert.assertTrue(wi.getRightAttributeValue(arr).containsAll(list));
  }

  /**
   * Loads an overview page of an task workitem and checks if testOpen() gets open project area.
   */
  @Test
  public void testOpen() {
    loadPage("ccm/epic-overview.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    Map<String, String> params = new HashMap<String, String>();
    params.put("WORKITEM_ID", "_bq1MID5EEemjcY1k4_iJpw&planMode");
    wi.open(repositoryURL, projectAreaName, params);
  }

  /**
   * Loads an overview page of an task workitem and checks if testOpen() gets open project area.
   */
  @Test
  public void testOpenTwo() {
    loadPage("ccm/epic-overview.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    Map<String, String> params = new HashMap<String, String>();
    params.put("WORK_ITEM_TYPE", "_bq1MID5EEemjcY1k4_iJpw&planMode");
    wi.open(repositoryURL, projectAreaName, params);
  }

  /**
   * Loads an overview page of an task workitem and checks if testOpen() validate the exception message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOpenThree() {
    loadPage("ccm/epic-overview.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    wi.open(repositoryURL, projectAreaName, null);
  }

  /**
   * Loads an overview page of an task workitem and checks if setDate() set due date message.
   */
  @Test
  public void testSetDate() {
    loadPage("ccm/workitem_ste_due_date.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setDate("Due Date", "Jul 29, 2017, 12:00:00 PM");
  }

  /**
   * Loads an overview page of an task workitem and checks if setDate() set due date message.
   */
  @Test
  public void testAccountNumbers() {
    loadPage("ccm/accountNumber.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setAccountNumbers("Account Numbers", "999999999999");
  }

  /**
   * Loads an overview page of an task workitem and checks if setDate() set due date message.
   */
  @Test
  public void testAddContactPersons() {
    loadPage("ccm/accountNumber.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/contact_persone.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.addContactPersons("CDG ALM Tester system-user-CC (CAP-SST/ESM3)");
  }

  /**
   * Loads an overview page of an task workitem and checks if setDate() set due date message.
   */
  @Test
  public void testDeleteContactPersons() {
    loadPage("ccm/Epic_delete_pilot1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/Epic_pilot.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.deleteContactPersons("Story For Automation Testing 1");
  }

  /**
   * This method used to set RQ1 Data attributeName Enum for the attributes names as RQ1 ID,RQ1 Relationship Data,RQ1
   * Customer Data,RQ1-RTC-Sync Info. attributeValue text field value for RQ1 ID,RQ1 Relationship Data, RQ1 Customer
   * Data,RQ1-RTC-Sync Info
   */
  @Test
  public void testsetRQ1Data() {
    loadPage("ccm/accountNumber.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setAttributeValueInTextBox("RQ1 Customer Data", "Test data for automation");
  }

  /**
   * This method used to get RQ1 Data attributeName Enum for the attributes names as RQ1 ID,RQ1 Relationship Data,RQ1
   * Customer Data,RQ1-RTC-Sync Info.
   */
  @Test
  public void testgetRQ1Data() {
    loadPage("ccm/accountNumber.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.getAttributeValueInTextBox("RQ1 Customer Data");
  }


  /**
   * Loads the overview page of a task workitem and setAttributeValueInTextBox(final String attributeName, final String
   * attributeValue) sets the summary to new value attributeValue.
   */
  @Test
  public void testSetAttributeValueInTextBox() {
    loadPage("ccm/set_attribute_value_in_textbox.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setAttributeValueInTextBox("Summary", "Code coverage for CCMWorkItemEditorpage");
  }

  /**
   * Loads the misc page of a task workitem and setAttributeValueInTextArea(final String attributeName, final String
   * attributeValue) sets the Additional Information in TEXT value to attributeValue.
   */
  @Test
  public void testSetAttributeValueInTextArea() {
    loadPage("ccm/set_attribute_value_in_textarea.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setAttributeValueInTextArea("Additional Information in TEXT", "Test");
  }

  /**
   * Loads the validity page of a task workitem and checkOrUncheckValidity(final String attributeName) sets the checkbox
   * for the given attributeName.
   */
  @Test
  public void testCheckOrUncheckValidity() {
    loadPage("ccm/check_or_uncheck_validity.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    workItems.checkOrUncheckValidity("Unit Test:");
  }

  /**
   * Loads the validity page of a task workitem and checkOrUncheckValidity(final String attributeName) sets the checkbox
   * for the given attributeName and validate the exception message.
   */
  @Test(expected = WebAutomationException.class)
  public void testCheckOrUncheckValidityOne() {
    loadPage("ccm/check_or_uncheck_validity.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    workItems.checkOrUncheckValidity("Unit:");
  }

  /**
   * Loads an Links page of an workitem task and checks if getLinksList() gets list of workItem linkTypes for 'Process
   * Links' Section
   */
  @Test
  public void testGetLinksList() {
    loadPage("ccm/workItem_editor_get_links_list.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    workItems.getLinksList(WorkItemLinkSection.PROCESS_LINKS.toString(),
        WorkItemLinkTypes.CONTRIBUTES_TO_LINKS.toString());
  }

  /**
   * Loads an Links page of an workitem task and checks if getDropDownList() gets drop down list.
   */
  @Test
  public void testGetDropDownList() {
    loadPage("ccm/workitem_get_dropdown_list_two.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    List<String> dropdownlilt = Arrays.asList("Task", "Story", "Epic", "Defect", "Defect Fix", "Review", "Problem",
        "Meeting", "Delivery", "Release", "Defect Eval", "Relevant Stream", "Track Build Item", "Adoption Item",
        "Impediment", "Retrospective");
    assertEquals(dropdownlilt, wi.getDropDownList("Type"));
  }

  /**
   * Loads an Overview page of an workitem defect and checks if setResolution() set empty and validating the exception
   * message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetResolutionConditionOne() {
    loadPage("ccm/workitem_set_resolution.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setResolution("");
  }

  /**
   * Loads an Overview page of an workitem defect and checks if setResolution() set null and validating the exception
   * message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetResolutionConditionTwo() {
    loadPage("ccm/workitem_set_resolution.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setResolution(null);
  }

  /**
   * Loads an Overview page of an workitem defect and checks if setResolution() set empty Status and validating the
   * exception message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetStatusConditionOne() {
    loadPage("ccm/workitem_set_status.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setStatus("");
  }

  /**
   * Loads an Overview page of an workitem defect and checks if setResolution() set null Status and validating the
   * exception message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetStatusConditionTwo() {
    loadPage("ccm/workitem_set_status.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.setStatus(null);
  }


  /**
   * Loads an Overview page of an workitem and checks if setDropDownAttributeValue() opens the dropdown and sets
   * dropdown value.
   */

  @Test
  public void testSetDropDownAttributeValue() {
    // Case 1
    loadPage("ccm/workitem_editor_attribute_value.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/workitem_editor_attribute_value_0.html");
    clickNumberToPagePath.put(3, "ccm/workitem_editor_attribute_value_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String attributeName = "Planned For";
    String attributeValue = "System Testing";
    workItems.setDropDownAttributeValue(attributeName, attributeValue);
    // Case 2
    loadPage("ccm/workitem_editor_attribute_value_owner.html");
    CCMWorkItemEditorPage workItems1 = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems1);
    Map<Integer, String> clickNumberToPagePath1 = new HashMap<>();
    clickNumberToPagePath1.put(1, null);
    clickNumberToPagePath1.put(2, "ccm/workitem_editor_attribute_value_owner_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath1);
    String attributeName1 = "Owned By";
    String attributeValue1 = "Phung Trong Van (RBVH/EET2)";
    workItems.setDropDownAttributeValue(attributeName1, attributeValue1);
  }

  /**
   * Loads an Overview page of an workitem and checks if setDropDownAttributeValueByEngine() opens the dropdown and sets
   * dropdown value.
   */
  @Test
  public void testSetDropDownAttributeValueByEngine() {
    loadPage("ccm/workitem_editor_attribute_value.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String attributeName = "Type";
    String attributeValue = "Epic";
    workItems.setDropDownAttributeValueByEngine(attributeName, attributeValue);
  }

  /**
   * Loads an Overview page of an workitem and checks if setDropDownAttributeValue() click on clickForMoreValues button
   * and sets dropdown value.
   */

  // @Test
  // public void testSetDropDownAttributeValueOne() {
  // loadPage("ccm/workitem_editor_dropDownMoreValue_button.html");
  // CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
  // assertNotNull(workItems);
  // Map<Integer, String> clickNumberToPagePath = new HashMap<>();
  // clickNumberToPagePath.put(1, "ccm/workitem_editor_textFeild.html");
  // clickNumberToPagePath.put(2, "ccm/workitem_editor_textFeild.html");
  //
  // loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
  // String attributeName = "Planned For";
  // String attributeValue = "Release2";
  // workItems.setDropDownAttributeValue(attributeName, attributeValue);
  // }


  /**
   * Loads an Overview page of an workitem and checks if addExistingLink() add existing link and validating the
   * exception message.
   */

  @Test(expected = WebAutomationException.class)
  public void testAddExistingLink() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/workitem_add_existing_link_frame.html");
    clickNumberToPagePath.put(3, "ccm/workitem_add_existing_link_list.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Affected by Defect");
    additionalParams.put("value", "");
    wi.addLinkToExistingObject(additionalParams);
  }

  /**
   * Loads work item links page and add Tested By Test Case from links section.
   */
  @Test
  public void testAddExistingLinkTestedByTestCase() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_add_existing_link_tested_by_testcase.html");// LOC 1787 - 2015
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/workitem_add_existing_link_select_testcase.html");// LOC 2016
    clickNumberToPagePath.put(5, null);// LOC 2037 - 2040 (Fail)
    clickNumberToPagePath.put(6, "ccm/workitem_add_existing_select_testcase_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");// LOC 1787
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Tested By Test Case");// LOC 1787
    additionalParams.put("rqmProjectArea", "BBM ALM Dev (QM)");
    additionalParams.put("linkTypeID", "17666");
    wi.addLinkToExistingObject(additionalParams);

  }

  /**
   * Loads work item links page and Add Contributes To from links section.
   */
  @Test
  public void testAddExistingLinkAddContributesTo() {
    loadPage("ccm/addContributesTo1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/linksAddContributesTo2.html");
   // clickNumberToPagePath.put(2, "ccm/linkAddContributesTo3.html");
    clickNumberToPagePath.put(2, "ccm/addContributsTo.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/linksAddContributesTo5.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Contributes To");
    additionalParams.put("ccmProjectArea", "ALM Test (CCM)");
    additionalParams.put("linkTypeID", "269781");
    wi.addLinkToExistingObject(additionalParams);
  }
  /**
   * Loads work item links page and Add Tracks To from links section.
   */
  @Test
  public void testAddExistingLinkAddTracks() {
    loadPage("ccm/addContributesTo1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/linksAddContributesTo2.html");
   // clickNumberToPagePath.put(2, "ccm/linkAddContributesTo3.html");
    clickNumberToPagePath.put(2, "ccm/addContributsTo.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/linksAddContributesTo5.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(7, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Tracks");
    additionalParams.put("ccmProjectArea", "ALM Test (CCM)");
    additionalParams.put("linkTypeID", "269781");
    wi.addLinkToExistingObject(additionalParams);
  }
  /**
   * Loads work item links page and add Add Related Test Case from links section.
   */
  @Test
  public void testAddExistingLinkAddRelatedTestCase() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_add_existing_link_tested_by_testcase.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/workitem_add_existing_link_select_testcase.html");
    clickNumberToPagePath.put(5, "ccm/workitem_add_existing_link_select_testcase.html");
    clickNumberToPagePath.put(6, "ccm/workitem_add_existing_select_testcase_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Related Test Case");
    additionalParams.put("rqmProjectArea", "BBM ALM Dev (QM)");
    additionalParams.put("linkTypeID", "17666");
    wi.addLinkToExistingObject(additionalParams);
  }

  /**
   * Loads work item links page and add Add Related Test Plan from links section.
   */
  @Test
  public void testAddExistingLinkAddRelatedTestPlan() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_add_existing_link_tested_by_testcase.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/workitem_add_existing_link_select_testcase.html");
    clickNumberToPagePath.put(5, "ccm/workitem_add_existing_link_select_testcase.html");
    clickNumberToPagePath.put(6, "ccm/workitem_add_existing_select_testcase_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Related Test Plan");
    additionalParams.put("rqmProjectArea", "BBM ALM Dev (QM)");
    additionalParams.put("linkTypeID", "17666");
    wi.addLinkToExistingObject(additionalParams);
  }


  /**
   * Loads an Overview page of an workitem meeting and checks if isDropDownListBoxAttributeWritable() wether attribute
   * is writable or not.
   */

  @Test
  public void testIsDropDownListBoxAttributeWritable() {
    loadPage("ccm/workitem_is_attribute_writable.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Type";
    assertTrue(wi.isDropDownListBoxAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isDropDownListBoxAttributeWritable() wether attribute is
   * writable or not.
   */
  @Test
  public void testIsDropDownListBoxAttributeWritableOne() {
    loadPage("ccm/workitem_readonly_condition.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Type";
    assertFalse(workItems.isDropDownListBoxAttributeWritable(attribute));
  }

  /**
   * Loads an misc page of an workitem meeting and checks if isRichTextEditorWidgetTextBoxAttributeWritable() wether
   * attribute is writable or not.
   */
  @Test
  public void testIsRichTextEditorWidgetTextBoxAttributeWritable() {
    loadPage("ccm/workitem_is_rich_text_editor_attribute_writable.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Additional Information in HTML";
    assertTrue(wi.isRichTextEditorWidgetTextBoxAttributeWritable(attribute));
  }

  /**
   * Loads an misc page of an workitem meeting and checks if isRichTextEditorWidgetTextBoxAttributeWritable() wether
   * attribute is writable or not.
   */
  @Test
  public void testIsAttributesTextAreaWritable() {
    loadPage("ccm/workitem_is_rich_text_editor_attribute_writable.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Additional Information in TEXT";
    assertTrue(workItems.isAttributesTextAreaWritable(attribute));
  }

  /**
   * Loads an Links page of an workitem delivery and checks if isAttributesTextAreaWritable() wether attribute is
   * writable or not.
   */
  @Test
  public void testIsAttributesTextAreaWritableTwo() {
    loadPage("ccm/test_part_number.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Part Number";
    assertTrue(workItems.isAttributesTextAreaWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isDateCalenderAttributeWritable() wether attribute is
   * writable or not.
   */
  @Test
  public void testIsDateCalenderAttributeWritable() {
    loadPage("ccm/workitem_ste_due_date.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    String attribute = "Due Date";
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(wi.isDateCalenderAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isDateCalenderAttributeWritable() wether attribute is
   * writable or not.
   */
  @Test
  public void testIsDateCalenderAttributeWritableOne() {
    loadPage("ccm/workitem_readonly_condition.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Due Date";
    assertFalse(workItems.isDateCalenderAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem defect and checks if isDetectedInViewPickerAttributeWritable() wether
   * attribute is writable or not.
   */

  @Test
  public void testIsDetectedInViewPickerAttributeWritable() {
    loadPage("ccm/workitem_readonly_condition_detected_in.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Detected In";
    assertTrue(workItems.isDetectedInViewPickerAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem need and checks if isConstraintDateListBoxAttributeWritable() wether
   * attribute is writable or not.
   */
  @Test
  public void testIsConstraintDateListBoxAttributeWritable() {
    loadPage("ccm/workitem_readonly_condition_constraintdate.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Constraint Date";
    assertTrue(workItems.isConstraintDateListBoxAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem need and checks if isTagsTextBoxAttributeWritable() wether attribute is
   * writable or not.
   */
  @Test
  public void testIsTagsTextBoxAttributeWritable() {
    loadPage("ccm/workitem_readonly_condition_constraintdate.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Tags";
    assertTrue(workItems.isTagsTextBoxAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isEstimateTextFieldAttributeWritable() wether attribute is
   * writable or not.
   */
  @Test
  public void testIsEstimateTextFieldAttributeWritable() {
    loadPage("ccm/estimate_writable.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Correction";
    assertTrue(workItems.isEstimateTextFieldAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isTimeSpentTextFieldAttributeWritable() wether attribute
   * is writable or not.
   */
  @Test
  public void testIsTimeSpentTextFieldAttributeWritable() {
    loadPage("ccm/workitem_readonly_condition_timespent_two.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Time Spent";
    assertFalse(workItems.isTimeTextFieldAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem meeting and checks if isDetectedInViewPickerAttributeWritable() wether
   * attribute is writable or not.
   */
  @Test
  public void testIsDurationTextFieldAttributeWritable() {
    loadPage("ccm/workitem_is_attribute_writable.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Duration";
    assertTrue(workItems.isTimeTextFieldAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem meeting and checks if isAddButtonButtonAttributeWritable() wether attribute
   * is writable or not.
   */
  @Test
  public void testIsAddButtonButtonAttributeWritable() {
    loadPage("ccm/workitem_is_attribute_writable.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Internal Participants";
    assertTrue(workItems.isAddButtonButtonAttributeWritable(attribute));
  }

  /**
   * Loads an validity page of an workitem need and checks if isValidityCheckBoxAttributeWritable() wether attribute is
   * writable or not.
   */
  @Test
  public void testIsValidityCheckBoxAttributeWritable() {
    loadPage("ccm/workitem_readonly_condition_unit_test.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Unit Test";
    assertTrue(workItems.isValidityCheckBoxAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an work item task and checks if isValidityCheckBoxAttributeWritable() weather attribute
   * is writable or not.
   */
  @Test
  public void testIsValidityCheckBoxAttributeWritableOne() {
    loadPage("ccm/workitem_readonly_condition_unit_test_two.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Unit Test";
    assertTrue(workItems.isValidityCheckBoxAttributeWritable(attribute));
  }

  /**
   * Loads an planning page of an work item story and checks if isPlanningEstimateTextViewAttributeWritable() wether
   * attribute is writable or not.
   */
  @Test
  public void testIsPlanningEstimateTextViewAttributeWritable() {
    loadPage("ccm/workitem_readonly_condition_estimate.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Residual Estimate (hrs)";
    assertTrue(workItems.isPlanningEstimateTextViewAttributeWritable(attribute));
  }

  /**
   * Loads an planning page of an workitem story and checks if isPlanningEstimateTextViewAttributeWritable() wether
   * attribute is writable or not.
   */
  @Test
  public void testIsPmInterfaceIdAndExecutionProgressAttributeWritable() {
    loadPage("ccm/workitem_readonly_condition_estimate.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "PM Interface Element ID";
    assertTrue(workItems.isPmInterfaceIdAndExecutionProgressAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isAttributeReadOnly() checks wether attribute is ReadOnly
   * or not.
   */
  @Test
  public void testIsRichTextEditorWidgetTextBoxAttributeWritableOne() {
    loadPage("ccm/workitem_readonly_condition_delivery_two.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Description";
    assertFalse(workItems.isRichTextEditorWidgetTextBoxAttributeWritable(attribute));
  }

  /**
   * Loads an Overview page of an workitem task and checks if isAttributeReadOnly() checks wether attribute is ReadOnly
   * or not.
   */
  @Test
  public void testIsAttributesTextAreaWritableOne() {
    loadPage("ccm/workitem_readonly_condition_estimate_two.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attribute = "Planned Estimate (hrs)";
    assertFalse(workItems.isAttributesTextAreaWritable(attribute));
  }

  /**
   *
   */
  @Test(expected = WebAutomationException.class)
  public void testSetConstraintDate() {
    loadPage("ccm/constraint_date.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attributeName = "Constraint date";
    String attributeValue = "";
    workItems.setConstraintDate(attributeName, attributeValue);


  }

  /**
   *
   */
  @Test
  public void testSetConstraintDateOne() {
    loadPage("ccm/workitem_editor_constraint_date.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String attributeName = "Constraint Date";
    String attributeValue = "Jul 25, 2019";
    workItems.setConstraintDate(attributeName, attributeValue);

  }

  /**
   * Loads an Approvals page of an workitem and checks if isApprovarUserAdded() checks wether approval user added or
   * not.
   */
  @Test
  public void testisApprovarUserAdded() {
    loadPage("ccm/workitem_isapprovaraddedone.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String user = "CDG ALM Exchange system-user-CC (CAP-SST/ESM3)";
    String approvalType = "Review";
    workItems.isApprovarUserAdded(approvalType, user);

  }


  /**
   * Loads an Approvals page of an workitem and checks if isDueDateAdded() checks wether due date added or not.
   */
  @Test
  public void testisDueDateAdded() {
    loadPage("ccm/workitem_isapprovaraddedone.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String date = "Dec 25, 2018, 12:00:00 PM";
    String approvalType = "Review";
    workItems.isDueDateAdded(approvalType, date);

  }

  /**
   * Loads an Approvals page of an workitem and checks if dueDate() set due date .
   */
  @Test
  public void testdueDate() {
    loadPage("ccm/workitem_isapprovaraddedone.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String date = "Dec 25, 2018, 12:00:00 PM";
    String approvalType = "Review";
    workItems.setDueDate(approvalType, date);

  }

  /**
   * Loads an Approvals page of an workitem and checks if dueDate() set due date .
   */
  @Test
  public void testaddApprovers() {
    loadPage("ccm/workitem_isapprovaraddedone.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String user = "CDG ALM Exchange system-user-CC (CAP-SST/ESM3)";
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_isapprovaraddeddilog.html");
    clickNumberToPagePath.put(2, "ccm/workitem_isapprovaraddeddilog_select.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.addApprovers(user);

  }

  /**
   * Loads an Approvals page of an workitem and checks if dueDate() set due date .
   */
  @Test
  public void testaddApproversOne() {
    loadPage("ccm/workitem_isapprovaraddedone.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String user = "ALM Tester system-userr-XC (ETAS-VOS/XPC-ESY1)";
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_isapprovaraddeddilog_usernotselectable_close.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.addApprovers(user);

  }


  /**
   * Loads an Approvals page of an workitem and checks if clickOnAddApproval() select the approval type from dropdown.
   */
  @Test
  public void tesclickOnAddApproval() {
    loadPage("ccm/workitem_isapprovaraddedone.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String dropdown = "Add Review";
    workItems.clickOnAddApproval(dropdown);
  }

  /**
   * Loads an Approvals page of an workitem and checks if deleteApproval() select the approval user to delete.
   */
  @Test
  public void testdeleteApproval() {
    loadPage("ccm/workirem_delete_approval.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    workItems.deleteApproval("Approval");
  }


  /**
   * removing the all links under parent workitem.
   */
  @Test
  public void testremoveAllLinks() {
    loadPage("ccm/workitem_removeAllLinks.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_removeAllLinksone.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);

    workItems.removeAllLinks(WorkItemLinkSection.PROCESS_LINKS.toString(), WorkItemLinkTypes.TRACKS_LINKS.toString());
  }

  /**
   * Create a link task using click on "Create Linked Task..." link
   */
  @Test
  public void testCreateLinkedTask() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/click_on_create_linked_task.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Related");
    additionalParams.put("createLinkType", "Create Linked Task");
    workItems.createLinkToExistingObject(additionalParams);
  }

  /**
   * Loads CCM WorkItem Editor page and create tested by test case link.
   */
  @Test
  public void testCreateLinkAddImplementsRequirement() {
    loadPage("ccm/addImplements_requirement1.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/addImplements_requirement2.html");
    clickNumberToPagePath.put(2, "ccm/addImplements_requirement3.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/addImplements_requirement5.html");
    // clickNumberToPagePath.put(5, "ccm/componentLink.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Implements Requirement");
    additionalParams.put("rmProjectArea", "AE Workplace ALM (RM)");
    additionalParams.put("RequirementName", "Test Case For Testing_");
    additionalParams.put("componentName", "rbd_briBk1_crs_BMW");
    workItems.createLinkToExistingObject(additionalParams);
  }

  /**
   * Loads CCM WorkItem Editor page and create tested by test case link.
   */
  @Test
  public void testCreateLinkAddImplementsRequirementOne() {
    loadPage("ccm/addImplements_requirement1.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/addImplements_requirement2.html");
    clickNumberToPagePath.put(2, "ccm/addImplements_requirement3.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    // clickNumberToPagePath.put(5, "ccm/addImplements_requirement5.html");
    clickNumberToPagePath.put(5, "ccm/componentLink.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Implements Requirement");
    additionalParams.put("rmProjectArea", "AE Workplace ALM (RM)");
    additionalParams.put("RequirementName", "Test Case For Testing_");
    additionalParams.put("componentName", "rbd_briBk1_sys");
    workItems.createLinkToExistingObject(additionalParams);
  }

  /**
   * Loads CCM WorkItem Editor page and create tested by test case link.
   */
  @Test
  public void testCreateLinkAddTestedByTestCase() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_createlink_add_tested_by_testcase.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/workitem_createlink_add_testedby_test_case_2.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Tested By Test Case");
    additionalParams.put("rqmProjectArea", "BBM ALM Dev (QM)");
    additionalParams.put("testCaseName", "Test Case For Testing_");
    additionalParams.put("testCaseWeight", "100");
    additionalParams.put("testCaseDomainMoreLinkValue", "More");
    additionalParams.put("domainValue", "DNG");
    additionalParams.put("testCaseTestType", "Requirement Based");
    additionalParams.put("testCaseLevel", "Software Qualification");
    additionalParams.put("testDesignTechniques", "Boundary Value Analysis");
    additionalParams.put("featuremodul", "Acoustic Alert System");
    workItems.createLinkToExistingObject(additionalParams);
  }

  /**
   * Loads CCM WorkItem Editor page and create tested by test plan link.
   */
  @Test
  public void testCreateLinkAddTestedByTestPlan() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workitem_createlink_add_tested_by_testcase.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/workitem_createlink_add_related_testplan_.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Related Test Plan");
    additionalParams.put("rqmProjectArea", "BBM ALM Dev (QM)");
    additionalParams.put("testPlanName", "Test Plan For Testing_");
    additionalParams.put("testPlanReleaseMoreLinkValue", "More");
    additionalParams.put("testPlanReleaseValue", "6.0.3 iFix004");
    additionalParams.put("testPlanProduct", "IBM CLM");
    workItems.createLinkToExistingObject(additionalParams);
  }

  /**
   * Loads an Overview page of an work item and checks if addWorkItemFromNotificationAreaLink() adding user defined work
   * item by clicking link from notification area.
   */
  @Test
  public void testAddWorkItemFromNotificationAreaLink() {
    loadPage("ccm/addwotkitem_dialog.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.addWorkItemFromNotificationAreaLink("310010");
  }

  /**
   *
   */
  @Test(expected = Exception.class)
  public void testSetTshirtSize() {
    loadPage("ccm/story_tshirt_size.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setDropDownAttributeValue("TShirt Size", "S");
  }

  /**
   *
   */
  @Test
  public void tesSetPredictedImplementationDate() {
    loadPage("ccm/story_tshirt_size.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    // loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setAttributeValueInTextBox("Predicted Implementation Date", "Dec 1, 2019, 12:00:00 PM");
  }

  /**
   *
   */
  @Test
  public void tesSetPredictedImplementationDateRQ1() {
    loadPage("ccm/story_tshirt_size.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    // loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setAttributeValueInTextBox("RQ1 Relationship Data", "Test data for automation");
  }

  /**
   *
   */
  @Test(expected = Exception.class)
  public void testsetMiscAllocation() {
    loadPage("ccm/story_misc_allocation.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setDropDownAttributeValue("Development Method", " Change based Development");
  }

  /**
   *
   */
  @Test(expected = Exception.class)
  public void testsetMiscAllocation1() {
    loadPage("ccm/story_misc_allocation1.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setDropDownAttributeValue("Allocation", "Software");
  }

  /**
   *
   */
  @Test
  public void tesSetPredictedImplementationDateRQ2() {
    loadPage("ccm/story_misc_allocation1.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    // loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setAttributeValueInTextBox("Additional Information in HTML", "Test data for automation");
  }

  /**
   *
   */
  @Test
  public void tesSetplanningaccount() {
    loadPage("ccm/Story_planning_accountNumber.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    // loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setAttributeValueInTextBox("Account Numbers", "1234");
  }


  /**
   *
   */
  @Test(expected = Exception.class)
  public void testsetplanningStoryPoint() {
    loadPage("ccm/Story_planning_accountNumber2.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    workItems.setDropDownAttributeValue("Story Points", "13 pts");
  }

  /**
   *
   */
  @Test
  public void testAddPilotPlanningWorkItem() {
    loadPage("ccm/Epic_pilot1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    // clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(1, "ccm/Epic_pilot.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.addPilotPlanningWorkItem("Story For Automation Testing 1");
  }


  /**
  *
  */
  @Test
  public void testAddTestCaseInLinkSection() {
    loadPage("ccm/AddTestCaseInLinkSection_1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/AddTestCaseInLinkSection_2.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "ccm/AddTestCaseInLinkSection_3.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.addTestCaseInLinkSection("Add Work Items Included in Packages", "Add Related Test Case",
        "Test Case 1 For Automation Testing", "Control Unit System Development (Tests)");
  }

  /**
   * This method validate the pilot work item in epic planning page
   */
  @Test
  public void testisPilotPlanningWorkItemAdded() {
    loadPage("ccm/Epic_delete_pilot1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);

    wi.isPilotPlanningWorkItemAdded("Story For Automation Testing 1");
  }

  /**
   *
   */
  @Test
  public void testdeleteAllLinks() {
    loadPage("ccm/Epic_delete_pilot1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/Epic_pilot.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.deleteAllLinks("Story For Automation Testing 1");
  }

  /**
  *
  */
  @Test
  public void testdeletePilotPlanningWorkItem() {
    loadPage("ccm/Epic_delete_pilot1.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/Epic_pilot.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.deletePilotPlanningWorkItem("Story For Automation Testing 1", "false");
  }

  /**
   * Loads Work Item Editor page and Click on the Link from Links section.
   */
  @Test
  public void testClickOnLinksFromWorkItemLinksSection() {
    loadPage("ccm/click_On_Links_From_WorkItem_Links_Section.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/click_On_Links_From_WorkItem_Links_Section.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.clickOnLinkFromWorkItemLinksSection("Links", "17666", "Related Test Case");
  }

  /**
   * Loads RQM Construction page from Work Item and verify added work item is visible in Development Items section.
   */
  @Test
  public void testIsWorkItemVisibleInTestCase() {
    loadPage("ccm/is_workitem_visible_in_testcase.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.isWorkItemVisibleInTestCase("458247");
  }

  /**
   * Loads RQM Construction page from Work Item and click on the added work item.
   */
  @Test
  public void testClickOnWorkItemFromTestCase() {
    loadPage("ccm/is_workitem_visible_in_testcase.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.clickOnWorkItemFromTestCase("458247");
  }

  /**
   * Loads Work Item Editor page and verify Save button is disabled.
   */
  @Test
  public void testIsTestCaseDuplicated() {
    loadPage("ccm/click_On_Links_From_WorkItem_Links_Section.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertTrue(wi.isTestCaseDuplicated());
  }

  /**
   * Loads Work Item Editor page and verify workitem is disabled.
   */
  @Test
  public void testIsWorkItemDisabled() {
    loadPage("ccm/is_workitem_disabled.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertTrue(wi.isWorkItemDisabled("269292: Task"));
  }

  /**
   * Loads RQM Construction page from Work Item and click on the added work item.
   */
  @Test
  public void testClickOnWorkItemFromTestArtifact() {
    loadPage("ccm/click_on_workitem_from_testplan.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.clickOnWorkItemFromTestArtifact("458247: Sample Task Work Item");
  }

  /**
   * Loads RQM Construction page from Work Item and verify work item is visible in test plan.
   */
  @Test
  public void testIsWorkItemVisibleInTestArtifact() {
    loadPage("ccm/click_on_workitem_from_testplan.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertTrue(wi.isWorkItemVisibleInTestArtifact("458247: Sample Task Work Item"));
  }

  /**
   * Loads RQM Construction page from Work Item and verify test case is added in Links section.
   */
  @Test
  public void testIsTestCaseAddedInLinkSection() {
    loadPage("ccm/click_On_Links_From_WorkItem_Links_Section.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertTrue(wi.isTestArtifactAddedInLinksSection("17666"));
  }

  /**
   * Loads RQM Construction page from Work Item and verify dropdown is disabled.
   */
  @Test
  public void testIsReadOnlyDropdown() {
    loadPage("ccm/test_ReadOnly_Dropdown.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    assertTrue(wi.isReadOnlyDropdown("Type"));
  }

  /**
   * Loads CCM WorkItem Editor page and modify description font of the work item.
   */
  @Test
  public void testModifyDescriptionTextFont() {
    loadPage("ccm/modify_description_field_font.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.modifyDescriptionTextFont("Bold");
  }

  /**
   * Loads CCM WorkItem Editor page and verify the print page data
   */
  @Test
  public void testIsDescriptionSameInPrintPage() {
    loadPage("ccm/checkIsDescriptionSameInPrintPage01.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> loadWIPages = new HashMap<>();
    loadWIPages.put(1, "ccm/checkIsDescriptionSameInPrintPage02.html");
    loadWIPages.put(2, "ccm/checkIsDescriptionSameInPrintPage03.html");
    loadNewPageOnNthDriverClickElementCall(loadWIPages);
    workItems.isDescriptionSameInPrintPage("verify description text automation testing");
  }

  /**
   * Loads CCM WorkItem Editor page and verify the warning symbol.
   */
  @Test
  public void testIsWarningSymbolDisplayed() {
    loadPage("ccm/warningMessage.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertTrue(wi.isWarningSymbolDisplayed("Owned By"));
  }

  /**
   * Loads CCM WorkItem Editor page and verify the warning message.
   */
  @Test
  public void testGetWarningMessageOnWorningSymbol() {
    loadPage("ccm/warningMessage.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    wi.getWarningMessageOnWorningSymbol("Owner does not belong to Team Area");
  }

  /**
   * Unit test for method {@link CCMWorkItemEditorPage#hoverOnArtifactLinkInLinkTab(String, String, String)}
   * 
   * @author VDY1HC
   */
  @Test
  public void testHoverOnArtifactLinkInLinkTab() {
    loadPage("ccm/hoverOnArtifactLinkInLinkTab01.html");
  CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
  assertNotNull(workItems);
  Map<Integer, String> loadWIPages = new HashMap<>();
  loadWIPages.put(1, "ccm/hoverOnArtifactLinkInLinkTab02.html");
  loadNewPageOnNthDriverClickElementCall(loadWIPages);
    workItems.hoverOnArtifactLinkInLinkTab("Implements Requirement",
        "1132128: Information For Automation Testing - Link 1", "4");
  }
  
  /**
   * Loads work item links page and add Add Related Test Case from links section.
   */
  @Test
  public void testAddExistingLinkWithoutClose() {
    loadPage("ccm/workitem_add_existing_links.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/addLinkImplementRequirement_03.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/addLinkImplementRequirement_04.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Implements Requirement");
    additionalParams.put("rmProjectArea", "BEG OX-004558 Modularer Feature Baukasten (RM)");
    additionalParams.put("componentName", "rbg.BEG_MFB_Modes");
    additionalParams.put("viewName", "test111");
    additionalParams.put("searchBy", "Module");
    additionalParams.put("moduleID", "1090341");
    wi.addLinkToExistingObjectWithOutClose(additionalParams);
  }
  
  /**
   * Unit test for method {@link CCMWorkItemEditorPage#generateExpectedMessageDisplayed(Map)}
   * @author VDY1HC
   */
  @Test
  public void testGenerateExpectedMessageDisplayed() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("1", "a&nbsp;");
    additionalParams.put("2", "b");
    additionalParams.put("3", "c");
    additionalParams.put("4", "d");
    additionalParams.put("5", "e");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    String text = workItems.generateExpectedMessageDisplayed(additionalParams);
    assertTrue(text.equalsIgnoreCase("a bcde"));
  }

  /**
   * Unit test for method {CCMWorkItemEditorPage#isSummarySameInPrintPage(String)}
   * @author KYY1HC
   */
  @Test
  public void testIsSummarySameInPrintPage() {
    loadPage("ccm/IsSummarySameInPrintPage_01.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> loadWIPages = new HashMap<>();
    loadWIPages.put(1, "ccm/IsSummarySameInPrintPage_02.html");
    loadWIPages.put(2, "ccm/IsSummarySameInPrintPage_03.html");
    loadNewPageOnNthDriverClickElementCall(loadWIPages);
    assertTrue(workItems.isSummarySameInPrintPage("Polyspace Setup in New Windows PC - updated"));
  }

  /**
   * Unit test for method {CCMWorkItemEditorPage#removeLinksFromLinksSectionById(String)}
   * @author KYY1HC
   */
  @Test
  public void testRemoveLinksFromLinksSectionById() {
    loadPage("ccm/RemoveLinksFromLinksSectionById_01.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<Integer, String> loadWIPages = new HashMap<>();
    loadWIPages.put(1, "ccm/RemoveLinksFromLinksSectionById_02.html");
    loadNewPageOnNthDriverClickElementCall(loadWIPages);
    assertTrue(workItems.removeLinksFromLinksSectionById("123656"));
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#verifyWorkItemIsSavedWithNewDropdownValue(Map)}
   * @author NCY3HC
   * 
   */
  @Test
  public void testVerifyWorkIteamIsSavedWithNewDropdownValue() {
    loadPage("ccm/availableNewIteration.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getWorkItemPage();
    assertNotNull(workItems);
    Map<String, String> params = new HashMap<>();
    params.put("DROPDOWN_VALUE", "New Iteration1");
    params.put("DROPDOWN_NAME", "Planned For");
    assertTrue(workItems.verifyWorkItemIsSavedWithNewDropdownValue(params));
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#setPlannedFor(String)}
   * @author NCY3HC
   * 
   */
  @Test
  public void testSetPlannedFor() {
    loadPage("ccm/setPlannedFor.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    Map<Integer, String> clickToPath = new HashMap<>();
    clickToPath.put(1, "ccm/setPlannedFor2.html");
    loadNewPageOnNthDriverClickElementCall(clickToPath);
    workItems.setPlannedFor("Automation Iteration_02_10_2022_18_10_604");
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#verifyTextInDescriptionFont(String)}
   * @author LTU7HC
   * 
   */
  @Test
  public void testVerifyTextInDescriptionFont() {
    loadPage("ccm/task_has_description_in_bold.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    Map<Integer, String> clickToPath = new HashMap<>();
    clickToPath.put(1, null);
    workItems.verifyTextInDescriptionFont("Bold");
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#getTagsValues}
   * @author LTU7HC
   * 
   */
  @Test
  public void testGetTagsValues() {
    loadPage("ccm/get_Tags_Values.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    List<String> lstTagsValues = workItems.getTagsValues();
    assertTrue(lstTagsValues.size()>0);
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#moveOrCopyWorkItemToAnotherProject(String,String,String)}
   * @author NCY3HC
   */
  @Test
  public void testMoveOrCopyWorkItemToAnotherProject() {
    loadPage("ccm/workitem_overview.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    Map<Integer, String> clickToPath = new HashMap<>();
    clickToPath.put(1, "ccm/copy_move_workitem.html");
    clickToPath.put(2, "null");
    clickToPath.put(3, "null");
    clickToPath.put(4, "null");
    workItems.moveOrCopyWorkItemToAnotherProject("CATS MASTER PA_2018","Copy","true");
    
  }
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#getHeaderValidationMessage()}
   * @author NCY3HC
   */
  @Test
  public void testGetHeaderValidationMessage() {
    loadPage("ccm/header_message.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    String msg = "Work Item belongs to PS-XS ALM Testing PA with Formal Template project. Click here to open the work item in that context.";
    workItems.getHeaderValidationMessage().contains(msg);
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#isLinksDisplayedInsideWorkItem(Map)}
   * @author NCY3HC
   */
  @Test
  public void testIsLinksDisplayedInsideWorkItem() {
    loadPage("ccm/verify_links_in_workItem.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    Map<String, String> params = new HashMap<>();
    params.put("LINK_SECTION", "Links");
    params.put("LINK_TYPE", "Parent");
    params.put("LINK_LABEL", "150541: test1");
    workItems.isLinksDisplayedInsideWorkItem(params);
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#isFilesDisplayedInsideWorkItem(String)}
   * @author NCY3HC
   */
  @Test
  public void testIsFilesDisplayedInsideWorkItem() {
    loadPage("ccm/attachments_inside_workItem.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    workItems.isFilesDisplayedInsideWorkItem("41708: LinkAfterDeleted.png;41709: Desert.jpg");
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#isCommentDisplayedInsideWorkItem(String)}
   * @author NCY3HC
   */
  @Test
  public void testIsCommentDisplayedInsideWorkItem() {
    loadPage("ccm/header_message.html");
    CCMWorkItemEditorPage workItems = getJazzPageFactory().getCCMWorkItemEditorPage();
    assertNotNull(workItems);
    workItems.isCommentDisplayedInsideWorkItem("comment for automation test");
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPage#getTagValueByLabel(String)}
   * @author NCY3HC
   */
  @Test
  public void testGetTagValueByLabel() {
    loadPage("ccm/workItem_details.html");
    CCMWorkItemEditorPage wi = getJazzPageFactory().getWorkItemPage();
    assertNotNull(wi);
    wi.getTagValueByLabel("Project Area");
  }
}