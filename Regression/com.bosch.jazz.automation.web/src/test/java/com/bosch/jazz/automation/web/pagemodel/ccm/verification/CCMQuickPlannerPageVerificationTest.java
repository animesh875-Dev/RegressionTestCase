/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMQuickPlannerPageVerification;

/**
 * @author UUM4KOR
 */
public class CCMQuickPlannerPageVerificationTest extends AbstractFrameworkUnitTest {


  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyClickOnWorkItemMenu(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyClickOnWorkItemMenu() click On Work Item Menu.
   */
  @Test
  public void testVerifyClickOnWorkItemMenu() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnWorkItemMenu("Filed Against", "Filed Against");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyClickOnWorkItemMenu(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyClickOnWorkItemMenu() not click On Work Item Menu.
   */
  @Test
  public void testVerifyClickOnWorkItemMenuOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnWorkItemMenu("Filed Against", "Invalid");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyClickOnCreateButton(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyClickOnCreateButton() verify Click On Create Button.
   */
  @Test
  public void testVerifyClickOnCreateButton() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnCreateButton("As a review report user", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyClickOnCreateButton(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyClickOnCreateButton() verify Click On Create Button.
   */
  @Test
  public void testVerifyClickOnCreateButtonOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    // CCMQuickPlannerPage wi = getJazzPageFactory().getCCMQuickPlannerPage();
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnCreateButton("Review: Develop TCs: 19684_Create Baseline", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySelectWorkBoardFromMyBoard(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySelectWorkBoardFromMyBoard() verify Select Work
   * Board From My Board.
   */
  @Test
  public void testVerifySelectWorkBoardFromMyBoard() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySelectWorkBoardFromMyBoard("Test automation", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySelectWorkBoardFromMyBoard(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySelectWorkBoardFromMyBoard() verify Select Work
   * Board From My Board.
   */
  @Test
  public void testVerifySelectWorkBoardFromMyBoardOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySelectWorkBoardFromMyBoard("test defect", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySetSummary(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySetSummary() verify Set Summary.
   */
  @Test
  public void testVerifySetSummary() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySetSummary("test", "test");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySetSummary(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySetSummary() verify Set Summary.
   */
  @Test
  public void testVerifySetSummaryOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySetSummary("", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySelectWorkitemType(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySelectWorkitemeType() verify Select Work item Type.
   */
  @Test
  public void testVerifySelectWorkitemType() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_Type_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySelectWorkitemType("Task", "Task");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySelectWorkitemType(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySelectWorkitemeType() verify Select Work item Type.
   */
  @Test
  public void testVerifySelectWorkitemeTypeOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_Type_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySelectWorkitemType("", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySetTextInQuickPlannerCreateTextBox(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySetTextInQuickPlannerCreateTextBox() verify Set Text
   * In Quick Planner Create Text Box.
   */
  @Test
  public void testVerifySetTextInQuickPlannerCreateTextBox() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySetTextInQuickPlannerCreateTextBox("Task", "Task");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySetTextInQuickPlannerCreateTextBox(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifySetTextInQuickPlannerCreateTextBox() verify Set Text
   * In Quick Planner Create Text Box.
   */
  @Test
  public void testVerifySetTextInQuickPlannerCreateTextBoxOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySetTextInQuickPlannerCreateTextBox("", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyGetValidationMessageFromNotificationView(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyGetValidationMessageFromNotificationView() verify
   * Get Validation Message From Notification View.
   */
  @Test
  public void testVerifyGetValidationMessageFromNotificationView() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_validationMessage_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyGetValidationMessageFromNotificationView(
        "'Save Work Item' failed. Preconditions have not been met: The 'Filed Against' attribute needs to be set",
        "'Save Work Item' failed. Preconditions have not been met: The 'Filed Against' attribute needs to be set");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyClickOnExpandButtonIconArrowToExpandAll(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyGetValidationMessageFromNotificationView() verify
   * Get Validation Message From Notification View.
   */
  @Test
  public void testVerifyGetValidationMessageFromNotificationViewOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_validationMessage_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyGetValidationMessageFromNotificationView("", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyClickOnExpandButtonIconArrowToExpandAll(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyClickOnExpandButtonIconArrowToExpandAll() verify
   * Click On Expand Button Icon Arrow To Expand All.
   */
  @Test
  public void testVerifyClickOnExpandButtonIconArrowToExpandAll() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_getAttributeValue_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnExpandButtonIconArrowToExpandAll("", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyGetAttributeValue(String,String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyGetAttributeValue() verify Get Attribute Value.
   */
  @Test
  public void testVerifyGetAttributeValue() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_getAttributeValue_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyGetAttributeValue("Filed Against", "CATtest", "CATtest");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyGetAttributeValue(String,String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyGetAttributeValue() verify Get Attribute Value.
   */
  @Test
  public void testVerifyGetAttributeValueOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_getAttributeValue_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyGetAttributeValue("Filed Against", "", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyIsWorkItemCreated(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyIsWorkItemCreated() verify Is Work Item Created.
   */
  @Test
  public void testVerifyIsWorkItemCreated() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemCreated("", "true");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyIsWorkItemCreated(String,String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyIsWorkItemCreated() verify Is Work Item Created.
   */
  @Test
  public void testVerifyIsWorkItemCreatedOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlanner_setText_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemCreated("", "false");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifyCreateABoard(String, String, String, String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verifyCreateABoard() verify Create ABoard.
   */
  @Test
  public void testverifyCreateABoard() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/QuickPlannermenu1_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifyCreateABoard("Test automation", "", "", "");
  }


  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySelectDropdownInWorkItemRow(String, String, String, String, String, String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verify Select DropdownInWorkItemRow() verify Select Drop
   * Down In Work Item Row.
   */
  @Test
  public void testverifySelectDropdownInWorkItemRow() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();

    loadPage("ccm/test_select_dropdown_in_a_board_1_files/saved_resource.html");

    assertNotNull(cqpv);
    cqpv.verifySelectDropdownInWorkItemRow("In Progress", "Epic Automation For TS_19942", "Epic", "Story",
        "In Progress Planned For: Project Backlog", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMQuickPlannerPageVerification#verifySelectDropdownInWorkItemRow(String, String, String, String, String, String)}.
   * 
   * <p>Loads an Overview page of an quick planner and checks if verify Select DropdownInWorkItemRow() verify Select Drop
   * Down In Work Item Row.
   */
  @Test
  public void testverifySelectDropdownInWorkItemRowOne() {
    CCMQuickPlannerPageVerification cqpv = getJazzPageFactory().getCCMQuickPlannerPageVerification();
    loadPage("ccm/test_select_dropdown_in_a_board_1_files/saved_resource.html");
    assertNotNull(cqpv);
    cqpv.verifySelectDropdownInWorkItemRow("In Progress", "Epic Automation For TS_19942", "Epic", "Story", "zzzzzzz",
        "");
  }
}
