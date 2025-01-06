/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests for the CCMWorkItemEditorPage
 *
 * @author taa6si
 */
public class CCMQuickPlannerPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads an Overview page of an Epic and checks if getWorkItemID() gets the proper id
   * ccm/create_a_board_config_lane_files/saved_resource.html
   */
  @Test
  public void testCreateABoard() {
    loadPage("ccm/create_a_board_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "ccm/test_create_a_board_files/saved_resource.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(wi);
    wi.createABoard("Testabc", "Work Board", "Internal Backlog");
  }

  /**
   * ccm/test_select_dropdown_in_a_board_files/saved_resource.html
   */
  @Test
  public void testSelectDropdownInWorkItemRow() {
    loadPage("ccm/test_select_dropdown_in_a_board_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "ccm/test_select_dropdown_in_a_board_1_files/saved_resource.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(wi);
    wi.selectDropdownInWorkItemRow("In Progress", "Epic Automation For TS_19942", "Epic", "Story");
  }

  /**
   * ccm/test_select_dropdown_in_a_board_files/saved_resource.html
   */
  @Test
  public void testIsWorkItemInPanel() {
    loadPage("ccm/test_select_dropdown_in_a_board_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    assertTrue(wi.isWorkItemInPanel("In Progress", "Epic Automation For TS_19942"));
  }

  /**
   * ccm/test_select_dropdown_in_a_board_files/saved_resource.html
   */
  @Test
  public void testSelectWorkItemLinkInPannel() {
    loadPage("ccm/test_select_dropdown_in_a_board_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.selectWorkItemLinkInPannel("494710", "Epic Automation For TS_19942");
  }


  /**
   * Loads an Overview page of an quick planner and checks if clickOnCreateWorkitemtextBox() click On Create Work item
   * text Box.
   */
  @Test
  public void testClickOnCreateWorkitemtextBox() {
    loadPage("ccm/test_select_dropdown_in_a_board_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.clickOnCreateWorkitemtextBox();

  }


  /**
   * Loads an Overview page of an quick planner and checks if click On Work Item Menu() click On Work Item Menu.
   */
  @Test
  public void testClickOnWorkItemMenu() {
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();

    assertNotNull(wi);
    wi.clickOnWorkItemMenu("Filed Against");

  }

  /**
   * Loads an Overview page of an quick planner and checks if clickOnWorkItemMenu() click On Work Item Menu.
   */
  @Test
  public void testClickOnWorkItemMenuOne() {
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.clickOnWorkItemMenu("Filed Against");

  }

  /**
   * Loads an Overview page of an quick planner and checks if setSummary() set Summary.
   */
  @Test
  public void testSetSummary() {
    loadPage("ccm/test_select_dropdown_in_a_board_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, "ccm/QuickPlannerSummary1_files/saved_resource.html");
    clickToPage.put(5, null);
    clickToPage.put(1, "ccm/QuickPlanner_setText_files/saved_resource.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(wi);
    wi.setSummary("Task");

  }

  /**
   * Loads an Overview page of an quick planner and checks if clickOnCreateWorkitemtextBox() click On Create Work item
   * text Box.
   */
  @Test
  public void testClickOnCreateWorkitemtextBoxOne() {
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.clickOnCreateWorkitemtextBox();

  }

  /**
   * Loads an Overview page of an quick planner and checks if selectWorkiemeType() select Work item Type.
   */
  @Test
  public void testSelectWorkiemeType() {
    loadPage("ccm/QuickPlanner_Type_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.selectWorkitemType("Task");

  }

  /**
   * Loads an Overview page of an quick planner and checks if setTextInQuickPlannerCreateTextBox() set Text In Quick
   * Planner Create Text Box.
   */
  @Test
  public void testSetTextInQuickPlannerCreateTextBox() {
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.setTextInQuickPlannerCreateTextBox("Task");


  }

  /**
   * Loads an Overview page of an quick planner and checks if clickOnCreateButton() click On Create Button.
   */
  @Test
  public void TestClickOnCreateButton() {
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.clickOnCreateButton();


  }

  /**
   * Loads an Overview page of an quick planner and checks if getValidationMessageFromNotificationView() get Validation
   * Message From Notification View.
   */
  @Test
  public void testGetValidationMessageFromNotificationView() {
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "ccm/QuickPlanner_validationMessage_files/saved_resource.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(wi);
    wi.getValidationMessageFromNotificationView();

  }

  /**
   * Loads an Overview page of an quick planner and checks if isWorkItemCreated() is Work Item Created.
   */
  @Test
  public void TestIsWorkItemCreated() {
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.isWorkItemCreated("As a review report user");


  }

  /**
   * Loads an Overview page of an quick planner and checks if clickOnExpandButtonIconArrowToExpandAll() click On Expand
   * Button Icon Arrow To Expand All.
   */
  @Test
  public void TestClickOnExpandButtonIconArrowToExpandAll() {
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.clickOnExpandButtonIconArrowToExpandAll("As a review report user");


  }

  /**
   * Loads an Overview page of an quick planner and checks if getAttributeValue() gets the Attribute Value.
   */
  @Test
  public void TestGetAttributeValue() {
    loadPage("ccm/QuickPlanner_getAttributeValue_files/saved_resource.html");
    CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    assertNotNull(wi);
    wi.getAttributeValue("Filed Against");


  }
}