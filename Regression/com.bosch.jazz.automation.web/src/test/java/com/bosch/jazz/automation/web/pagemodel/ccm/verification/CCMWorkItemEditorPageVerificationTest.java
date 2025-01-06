/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMWorkItemEditorPageVerification;

/**
 * Loads a JazzLogin Page and verifies the methods.
 */
public class CCMWorkItemEditorPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetStatus(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetStatus() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetStatus("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetStatus(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetStatus() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetStatus("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetResolution(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetResolution() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetResolution("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetResolution(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetResolution() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyGetResolution("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyClickOnAddApproval(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnAddApproval() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_isapprovaraddedone.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnAddApproval("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyClickOnAddApproval(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnAddApproval() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnAddApproval("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddApprovers(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddApprovers() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_isapprovaraddedone.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyAddApprovers("QUERYTESTINGINVALID", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddApprovers(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddApprovers() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_isapprovaraddedone.html");
    assertNotNull(cqpv);
    cqpv.verifyAddApprovers("CDG ALM Exchange system-user-CC (CAP-SST/ESM3)",
        "CDG ALM Exchange system-user-CC (CAP-SST/ESM3): Approver added successfully.");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddApprovers(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddApproverS() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_isapprovaraddedone.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyAddApprovers("CDG ALM Exchange system-user-CC (CAP-SST/ESM3)",
        "CDG ALM Exchange system-user-CC (CAP-SST/ESM3): Approver can't be selected (Approver is disabled)");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetDueDate(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetDueDate() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_isapprovaraddedone.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    String date = "Dec 25, 2018, 12:00:00 PM";
    String approvalType = "Review";
    getJazzPageFactory().getCCMWorkItemEditorPage().setDueDate(approvalType, date);
    cqpv.verifySetDueDate(approvalType, date, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetDueDate(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetDueDate() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetDueDate("", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySelectTab(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectTab() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workirem_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectTab("Approvals", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySelectTab(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySelectTab() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workirem_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectTab("Attachments", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySaveWorkItem(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySaveWorkItem() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workirem_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySaveWorkItem("true", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySaveWorkItem(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySaveWorkItem() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySaveWorkItem("false", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySaveWorkItem(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySavEWorkItem() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySaveWorkItem("true", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyNotSaveWorkItem(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyNotSaveWorkItem() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyNotSaveWorkItem("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyNotSaveWorkItem(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyNotSaveWorkItem() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workirem_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyNotSaveWorkItem("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyDeleteApproval(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyDeleteApproval() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/verify_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyDeleteApproval("Review", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyDeleteApproval(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyDeleteApproval() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workirem_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyDeleteApproval("Review", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsApprovarUserAdded(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsApprovarUserAdded() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workirem_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsApprovarUserAdded("Approval", "EXTERNAL Kumar Suresh (Datamatics, RBEI/EMT4)", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsApprovarUserAdded(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsApprovarUserAdded() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workirem_delete_approval.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsApprovarUserAdded("Approval", "CDG ALM Tester system-user-CC (CAP-SST/ESM3)", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsDueDateAdded(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsDueDateAdded() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsDueDateAdded("", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsDueDateAdded(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsDueDateAdded() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsDueDateAdded("", "", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifySetDropDownAttributeValue(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetDropDownAttributeValue() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetDropDownAttributeValue("Type", "Task", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifySetDropDownAttributeValue(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetDropDownAttributeValue() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetDropDownAttributeValue("InvalidType", "InvalidAttribute", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetResolution(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetResolution() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_set_resolution.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetResolution("Confirmed", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetResolution(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetResolution() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetResolution("InvalidType", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetStatus(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetStatus() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_set_resolution.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetStatus("In Progress", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetStatus(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetStatus() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetStatus("InvalidType", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsAttributeMandatory(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsAttributeMandatory() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsAttributeMandatory("", "false", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsAttributeMandatory(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsAttributeMandatory() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsAttributeMandatory("", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsAttributeMandatory(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsAttrIbuteMandatory() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsAttributeMandatory("", "true", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsAttributeNotMandatory(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsAttributeNotMandatory() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsAttributeNotMandatory("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsAttributeNotMandatory(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsAttributeNotMandatory() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsAttributeNotMandatory("", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetDropDownValue(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetDropDownValue() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyGetDropDownValue("", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetDropDownValue(String,String,String)}.
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetDropDownValue() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyGetDropDownValue("", "", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyAddWorkItemFromNotificationAreaLink(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetValidationMessageFromNotificationArea() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyGetValidationMessageFromNotificationArea("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetStatus(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetValIdationMessageFromNotificationArea() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyGetValidationMessageFromNotificationArea("", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyAddWorkItemFromNotificationAreaLink(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddWorkItemFromNotificationAreaLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/click_On_Links_From_WorkItem_Links_Section.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyAddWorkItemFromNotificationAreaLink("17666", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddExistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Tested By Test Case");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67903");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddExistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Tested By Test Case");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyADdExistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related Test Plan");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67903");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAdDExistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related Test Plan");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyADdEXistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related Test Case");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67903");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyADDExistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related Test Case");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVErifyADdEXistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Contributes To");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67903");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVeRIfyADDExistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Contributes To");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVErifyADDEXistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Set Parent");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67903");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVERIfyADDExistingLink() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Set Parent");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddExistingLinkAddTracksRequirement() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Tracks Requirement");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67903");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddExistingLinkAddTracksRequirement() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Tracks Requirement");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddExistingLinkAddRelated() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67903");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddExistingLinkAddRelated() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddExistingLinkAddChildren() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/verify_add_existing_link_add_children.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Children");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "464376");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddExistingLinkAddChildren() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/verify_add_existing_link_add_children.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Children");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "464376invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObject(Map,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAddExistIngLinkAddChildren() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/verify_add_existing_link_add_children.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "invalid");
    clickToPage.put(CCMConstants.LINKTYPE_ID, "464376invalid");
    assertNotNull(cqpv);
    cqpv.verifyAddLinkToExistingObject(clickToPage, "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyIsTestArtifactAddedInLinksSection(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsTestArtifactAddedInLinksSection() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsTestArtifactAddedInLinksSection("", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyIsTestArtifactAddedInLinksSection(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsTestArtifactAddedInLinksSection() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsTestArtifactAddedInLinksSection("", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyClickOnLinkFromWorkItemLinksSection(String,String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnLinkFromWorkItemLinksSection() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("rqm/test_case_id_files.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnLinkFromWorkItemLinksSection("Links", "13233", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyClickOnLinkFromWorkItemLinksSection(String,String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnLinkFromWorkItemLinksSectionOne() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    System.out.println(driver.getTitle());
    cqpv.verifyClickOnLinkFromWorkItemLinksSection("Links", "Configuration Management", "", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemVisibleInTestCase(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsWorkItemVisibleInTestCase() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemVisibleInTestCase("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemVisibleInTestCase(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsWorkItemVisibleInTestCase() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemVisibleInTestCase("", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyClickOnWorkItemFromTestCase(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnWorkItemFromTestCase() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnWorkItemFromTestCase("296824", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyClickOnWorkItemFromTestCase(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnWorkItemFromTestCase() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnWorkItemFromTestCase("test", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyDeleteAllLinks(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyDeleteAllLinks() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_add_existing_link_list.html");
    assertNotNull(cqpv);
    cqpv.verifyDeleteAllLinks("243888: AutomationLink032318084212", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyDeleteAllLinks(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyDeleteAllLinks() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_add_existing_link_list.html");
    assertNotNull(cqpv);
    cqpv.verifyDeleteAllLinks("test", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemVisibleInTestArtifact(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsWorkItemVisibleInTestArtifact() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemVisibleInTestArtifact("296824", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemVisibleInTestArtifact(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsWorkItemVisibleInTestArtifact() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemVisibleInTestArtifact("test", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyClickOnWorkItemFromTestArtifact(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnWorkItemFromTestArtifact() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnWorkItemFromTestArtifact("296824", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyClickOnWorkItemFromTestArtifact(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnWorkItemFromTestArtifact() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnWorkItemFromTestArtifact("test", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyCreateLinkToExistingObject(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyCreateLinkAddTestedByTestCase() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Tested By Test Case");
    clickToPage.put("testCaseName", "67903");
    assertNotNull(cqpv);
    cqpv.verifyCreateLinkToExistingObject(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyCreateLinkToExistingObject(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyCreateLinkAddTestedByTestCase() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Tested By Test Case");
    clickToPage.put("testCaseName", "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyCreateLinkToExistingObject(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyCreateLinkToExistingObject(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyCreateLinkAddRelatedTestPlan() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related Test Plan");
    clickToPage.put("testPlanName", "67903");
    assertNotNull(cqpv);
    cqpv.verifyCreateLinkToExistingObject(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyCreateLinkToExistingObject(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyCreateLinkAddRelatedTestPlan() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related Test Plan");
    clickToPage.put("testPlanName", "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyCreateLinkToExistingObject(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyCreateLinkToExistingObject(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyCreateLinkAddImplementsRequirement() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Implements Requirement");
    clickToPage.put("RequirementName", "67903");
    assertNotNull(cqpv);
    cqpv.verifyCreateLinkToExistingObject(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyCreateLinkToExistingObject(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyCreateLinkAddImplementsRequirement() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Implements Requirement");
    clickToPage.put("RequirementName", "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyCreateLinkToExistingObject(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyCreateLinkToExistingObject(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyCreateLinkAddRelated() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/Epic_delete_pilot1.html");
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(CCMConstants.LINKACTIONS, "Add Related");
    clickToPage.put("RequirementName", "67904invalid");
    assertNotNull(cqpv);
    cqpv.verifyCreateLinkToExistingObject(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsTestCaseDuplicated(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsTestCaseDuplicated() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    assertNotNull(cqpv);
    cqpv.verifyIsTestCaseDuplicated("false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsTestCaseDuplicated(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsTestCaseDuplicated() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("rqm/test_case_id_files.html");
    assertNotNull(cqpv);
    cqpv.verifyIsTestCaseDuplicated("true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemDisabled(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsWorkItemDisabled() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("rqm/test_case_id_files.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemDisabled("", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemDisabled(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsWorkItemDisabled() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("rqm/test_case_id_files.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemDisabled("", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifySetAttributeValueInTextBox(String,String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetAttributeValueInTextBox() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetAttributeValueInTextBox("Summary:", "Electric Car Mid Level B2", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifySetAttributeValueInTextBox(String,String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetAttributeValueInTextBox() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetAttributeValueInTextBox("Invalid", "test", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyGetAttributeValueInTextBox(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyGetAttributeValueInTextBox() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    assertNotNull(cqpv);
    cqpv.verifyGetAttributeValueInTextBox("", "", "NotEmpty");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyGetAttributeValueInTextBox(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGetAttributeValueInTextBox() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    assertNotNull(cqpv);
    cqpv.verifyGetAttributeValueInTextBox("", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemAddedInProcessLinkSection(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsWorkItemAddedInProcessLinkSection() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemAddedInProcessLinkSection("", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyIsWorkItemAddedInProcessLinkSection(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsWorkItemAddedInProcessLinkSection() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemAddedInProcessLinkSection("", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsReadOnlyDropdown(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsReadOnlyDropdown() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/task_validation_message.html");
    assertNotNull(cqpv);
    cqpv.verifyIsReadOnlyDropdown("", "true", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyModifyDescriptionTextFont(String, String)}.
   */
  @Test
  public void testVerifyModifyDescriptionTextFont() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/modify_description_field_font.html");
    assertNotNull(cqpv);
    cqpv.verifyModifyDescriptionTextFont("Bold", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetTags(String, String)}.
   */
  @Test
  public void testVerifySetTags() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/remove_workitem_tag.html");
    assertNotNull(cqpv);
    cqpv.verifySetTags("automatic", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetTags(String, String)}.
   */
  @Test
  public void testVerifySetTagsTwo() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/remove_workitem_tag.html");
    assertNotNull(cqpv);
    cqpv.verifySetTags("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyRemoveDueDate(String)}.
   */
  @Test
  public void testVerifyRemoveDueDate() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/remove_workitem_tag.html");
    assertNotNull(cqpv);
    cqpv.verifyRemoveDueDate("");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyRemoveTags(String, String)}.
   */
  @Test
  public void testVerifyRemoveTags() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/remove_workitem_tag.html");
    assertNotNull(cqpv);
    cqpv.verifyRemoveTags("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifySetDate(String, String, String)}.
   */
  @Test
  public void testVerifySetDate() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/remove_workitem_tag.html");
    assertNotNull(cqpv);
    cqpv.verifySetDate("Due Date", "Dec 2, 2020, 12:00:00 PM", "");
  }

  /**
   * Unit test coverage for
   * {@link CCMWorkItemEditorPageVerification#verifyGetWarningMessageOnWorningSymbol(String, String)}
   */
  @Test
  public void testVerifyGetWarningMessageOnWorningSymbol() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/warningMessage.html");
    assertNotNull(cqpv);
    cqpv.verifyGetWarningMessageOnWorningSymbol("Owner does not belong to Team Area",
        "Owner does not belong to Team Area");
  }

  /**
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsWarningSymbolDisplayed(String, String)}
   */
  @Test
  public void testVerifyIsWarningSymbolDisplayed() {
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/warningMessage.html");
    assertNotNull(cqpv);
    cqpv.verifyIsWarningSymbolDisplayed("Owned By", "Owned By");
  }
  
  /**
   * Unit test for method {@link CCMWorkItemEditorPageVerification#verifyHoverOnArtifactLinkInLinkTab(String, String, String, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyHoverOnArtifactLinkInLinkTab() {
    loadPage("ccm/hoverOnArtifactLinkInLinkTab02.html");
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(cqpv);
    cqpv.verifyHoverOnArtifactLinkInLinkTab("Implements Requirement","1132128: Information For Automation Testing - Link 1","4","");
  }
  
  /**
   * Unit test for method {@link CCMWorkItemEditorPageVerification#verifyGenerateExpectedMessageDisplayed(Map, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyGenerateExpectedMessageDisplayed() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("1", "a&nbsp;");
    additionalParams.put("2", "b");
    additionalParams.put("3", "c");
    additionalParams.put("4", "d");
    additionalParams.put("5", "e");
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(cqpv);
    cqpv.verifyGenerateExpectedMessageDisplayed(additionalParams, "a bcde");
  }
  
  /**
   * Unit test for method {@link CCMWorkItemEditorPageVerification#verifyAddLinkToExistingObjectWithOutClose(Map, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyAddLinkToExistingObjectWithOutClose() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Implements Requirement");
    additionalParams.put("rmProjectArea", "BEG OX-004558 Modularer Feature Baukasten (RM)");
    additionalParams.put("componentName", "rbg.BEG_MFB_Modes");
    additionalParams.put("viewName", "test111");
    additionalParams.put("searchBy", "Module");
    additionalParams.put("moduleID", "1090341");
    additionalParams.put("expectedMsg", "Displaying 1-2444 of 2444 matches");
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(cqpv);
    loadPage("ccm/addLinkImplementRequirement_04.html");
    cqpv.verifyAddLinkToExistingObjectWithOutClose(additionalParams,"");
  }

  /**
   * Unit test for method {@link CCMWorkItemEditorPageVerification#verifyIsSummarySameInPrintPage(String,String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyIsSummarySameInPrintPage() {
    loadPage("ccm/IsSummarySameInPrintPage_03.html");
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(cqpv);
    assertEquals("PASSED", cqpv.verifyIsSummarySameInPrintPage("Polyspace Setup in New Windows PC - updated", "true").getState());
    assertEquals("FAILED", cqpv.verifyIsSummarySameInPrintPage("Polyspace Setup in New Windows PC", "false").getState());
  }

  /**
   * Unit test for method {@link CCMWorkItemEditorPageVerification#verifyIsSummarySameInPrintPage(String,String)}
   * @author KYY1HC
   */
  @Test
  public void testVerifyRemoveLinksFromLinksSectionById() {
    loadPage("ccm/RemoveLinksFromLinksSectionById_02.html");
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(cqpv);
    assertEquals("PASSED", cqpv.verifyIsSummarySameInPrintPage("123656", "true").getState());
    assertEquals("FAILED", cqpv.verifyIsSummarySameInPrintPage("123", "false").getState());
  }
  
  /**
   * Unit test for method {@link CCMWorkItemEditorPageVerification#verifyVerifyWorkItemIsSavedWithNewDropdownValue(Map, String)}
   * @author NCY3HC
   */
  @Test
  public void testVerifyVerifyWorkItemIsSavedWithNewDropdownValue() {
    loadPage("ccm/createWIwithNewIteration.html");
    CCMWorkItemEditorPageVerification cqpv = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(cqpv);
    Map<String, String> param = new HashMap<String, String>();
    param.put("DROPDOWN_VALUE", "New Iteration1");
    assertEquals("PASSED", cqpv.verifyVerifyWorkItemIsSavedWithNewDropdownValue(param,"true").getState());
    assertEquals("FAILED", cqpv.verifyVerifyWorkItemIsSavedWithNewDropdownValue(param, "false").getState());
  }
  
  /**
   * Unit test covers for method {@link CCMWorkItemEditorPageVerification#verifySetPlannedFor(String, String)}
   * @author NCY3HC
   * 
   */
  @Test
  public void testSetPlannedFor() {
    loadPage("ccm/setPlannedFor.html");
    CCMWorkItemEditorPageVerification workItems = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(workItems);
    workItems.verifySetPlannedFor("Automation Iteration_02_10_2022_18_10_604", "true");
    workItems.verifySetPlannedFor("Automation Iteration_02_10_2022_18_10_604", "false");
  }
  
  /**
   * Unit test for method {@link CCMWorkItemEditorPageVerification#verifyVerifyTextInDescriptionFont(String, String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifyVerifyTextInDescriptionFont() {
    loadPage("ccm/task_has_description_in_bold.html");
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    assertNotNull(page);
    assertEquals("PASSED", page.verifyVerifyTextInDescriptionFont("Bold","true").getState());
    assertEquals("FAILED", page.verifyVerifyTextInDescriptionFont("Italic","false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetTagsValues(String, String)}.
   * @author LTU7HC
   */
  @Test
  public void testVerifyGetTagsValues() {
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/get_Tags_Values.html");
    assertNotNull(page);
    assertEquals("PASSED", page.verifyGetTagsValues("automation_test", "true").getState());
    assertEquals("FAILED", page.verifyGetTagsValues("unitTest", "false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyMoveOrCopyWorkItemToAnotherProject(String,String,String,String)}.
   * @author NCY3HC
   */
  @Test
  public void testVerifyMoveOrCopyWorkItemToAnotherProject() {
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/copied_workitem.html");
    assertNotNull(page);
    assertEquals("PASSED", page.verifyMoveOrCopyWorkItemToAnotherProject("CATS MASTER PA_2018","Copy", "true","true").getState());
    assertEquals("FAILED",page.verifyMoveOrCopyWorkItemToAnotherProject("CATS MASTER","Move","true","false").getState());
  }
  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyHeaderValidationMessage(Map, String)}.
   * @author NCY3HC
   */
  @Test
  public void testVerifyHeaderValidationMessage() {
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/header_message.html");
    assertNotNull(page);
    Map<String, String> params = new HashMap<>();
    params.put("EXPECTED_MESSAGE","Work Item belongs to PS-XS Automation Test (Change Management) project. Click here to open the work item in that context> and click on notification to open work item in target project");
    params.put("PROJECT_NAME", "PS-XS Automation Test (Change Management)");
    assertEquals("PASSED",page.verifyHeaderValidationMessage(params,"Work Item belongs to PS-XS Automation Test (Change Management) project. Click here to open the work item in that context> and click on notification to open work item in target project").getState());
    assertEquals("FAILED",page.verifyHeaderValidationMessage(params,"false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsLinksDisplayedInsideWorkItem(Map,String,String)}.
   * @author NCY3HC
   */
  @Test
  public void testVerifyIsLinksDisplayedInsideWorkItem() {
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/verify_links_in_workItem.html");
    Map<String, String> params = new HashMap<>();
    params.put("LINK_SECTION", "Links");
    params.put("LINK_TYPE", "Parent");
    params.put("LINK_LABEL", "150541: test1");
    assertNotNull(page);
    assertEquals("PASSED",page.verifyIsLinksDisplayedInsideWorkItem(params,"PS-XS Automation Project","true").getState());
    assertEquals("FAILED",page.verifyIsLinksDisplayedInsideWorkItem(params,"PS-XS Automation Project","false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsFilesDisplayedInsideWorkItem(String,String, String)}.
   * @author NCY3HC
   */
  @Test
  public void testVerifyIsFilesDisplayedInsideWorkItem() {
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/attachments_inside_workItem.html");
    assertNotNull(page);
    assertEquals("PASSED",page.verifyIsFilesDisplayedInsideWorkItem("41708: LinkAfterDeleted.png;41709: Desert.jpg","PS-XS Automation Project","true").getState());
    assertEquals("FAILED",page.verifyIsFilesDisplayedInsideWorkItem("41708: LinkAfterDeleted.png","PS-XS Automation Project","false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyIsCommentDisplayedInsideWorkItem(String,String, String)}.
   * @author NCY3HC
   */
  @Test
  public void testVerifyIsCommentDisplayedInsideWorkItem() {
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/header_message.html");
    assertNotNull(page);
    assertEquals("PASSED",page.verifyIsFilesDisplayedInsideWorkItem("comment for automation test","PS-XS Automation Project","true").getState());
    assertEquals("FAILED",page.verifyIsFilesDisplayedInsideWorkItem("comment for automation test","PS-XS Automation Project","false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link CCMWorkItemEditorPageVerification#verifyGetTagValueByLabel(String,String,String)}.
   * @author NCY3HC
   */
  @Test
  public void testVerifyGetTagValueByLabel() {
    CCMWorkItemEditorPageVerification page = getJazzPageFactory().getCCMWorkItemEditorPageVerification();
    loadPage("ccm/workItem_details.html");
    assertNotNull(page);
    assertEquals("PASSED",page.verifyGetTagValueByLabel("Project Area","BEG OX-004558 Modularer Feature Baukasten (CCM)","BEG OX-004558 Modularer Feature Baukasten (CCM)").getState());
    assertEquals("FAILED",page.verifyGetTagValueByLabel("Project Area","BEG OX-004558 Feature Baukasten (CCM)","false").getState());
  }
}