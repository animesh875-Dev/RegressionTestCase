/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.JazzSettingsEnums.TeamArea;

/**
 * Unit test for the ManageProjectAreaPage
 */
public class ManageProjectAreaPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads manage this project area page and click on add button
   */
  @Test
  public void testClikOnAddButton() {
    loadPage("ccm/clik_onadd_button.html");
    ManageProjectAreaPage wi = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(wi);
    wi.clikOnAddButton(TeamArea.MEMBERS.toString());
  }

  /**
   * Loads manage this project area page and add members in team area
   */
  @Test
  public void testAddMembers() {
    loadPage("ccm/add_members_window.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/add_members_window.html");
    clickNumberToPagePath.put(2, "ccm/add_members_window.html");
    clickNumberToPagePath.put(3, "ccm/add_members_window.html");
    clickNumberToPagePath.put(4, "ccm/add_members_roles.html");
    clickNumberToPagePath.put(5, "ccm/add_members_roles.html");
    clickNumberToPagePath.put(6, "ccm/add_members_roles.html");
    clickNumberToPagePath.put(7, "ccm/add_members_save.html");
    clickNumberToPagePath.put(8, "ccm/add_members_cancel.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addMembers("PPC4KOR", "Analyst");
  }

  /**
   * Loads manage this project area page and add memebers in Administration
   */
  @Test
  public void testAddMembersAdministration() {
    loadPage("ccm/add_members_administration.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    rm.addMembers("PPC4KOR", "");
  }

  /**
   * Loads manage this project area page and create team area
   */
  @Test
  public void testSetTeamAreaFields() {
    loadPage("ccm/set_team_area_fields_create.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/set_team_area_fields_team_area.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/set_team_area_fields_add.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "ccm/set_team_area_fields_team_area.html");
    clickNumberToPagePath.put(8, "ccm/set_team_area_fields_add.html");
    clickNumberToPagePath.put(9, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> tempList = new LinkedHashMap<>();
    tempList.put(TeamArea.NAME.toString(), "Test team " + DateUtil.getCurrentDateAndTime());
    tempList.put(TeamArea.SUMMARY.toString(), "Test team summary " + DateUtil.getCurrentDateAndTime());
    tempList.put(TeamArea.DESCRIPTION.toString(), "Test team drescription" + DateUtil.getCurrentDateAndTime());
    tempList.put(TeamArea.TIMELINE.toString(), "Main_Development_Timeline");
    tempList.put(TeamArea.MEMBERS.toString(), "Prachi Praveen Kumar (RBEI/EMT4)_Author");
    tempList.put(TeamArea.ADMINISTRATORS.toString(), "Prachi Praveen Kumar (RBEI/EMT4)_Author");
    rm.setTeamAreaFields(tempList);
  }

  /**
   * Loads manage this project area page and create team area without assinging role
   */
  @Test
  public void testSetTeamAreaFieldsRoleNull() {
    loadPage("ccm/set_team_area_fields_create.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/set_team_area_fields_team_area.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/set_team_area_fields_add.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "ccm/set_team_area_fields_team_area.html");
    clickNumberToPagePath.put(8, "ccm/set_team_area_fields_add.html");
    clickNumberToPagePath.put(9, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> tempList = new LinkedHashMap<>();
    tempList.put(TeamArea.NAME.toString(), "Test team " + DateUtil.getCurrentDateAndTime());
    tempList.put(TeamArea.SUMMARY.toString(), "Test team summary " + DateUtil.getCurrentDateAndTime());
    tempList.put(TeamArea.DESCRIPTION.toString(), "Test team drescription" + DateUtil.getCurrentDateAndTime());
    tempList.put(TeamArea.TIMELINE.toString(), "Main_Development_Timeline");
    tempList.put(TeamArea.MEMBERS.toString(), "Prachi Praveen Kumar (RBEI/EMT4)");
    tempList.put(TeamArea.ADMINISTRATORS.toString(), "Prachi Praveen Kumar (RBEI/EMT4)");
    rm.setTeamAreaFields(tempList);
  }

  /**
   * Loads manage this project area page and select iterations
   */
  @Test
  public void testSelectIterationInProjArea() {
    loadPage("ccm/select_iterationin_projarea.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/select_iterationin_projarea_iteration.html");
    clickNumberToPagePath.put(2, "ccm/add_members_window.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Name", "Release 1.0 [10/10/2018 - 10/23/2018]");
    additionalParams.put("SelectTimeLine", "Main_Development_Timeline");
    rm.selectIterationInProjArea(additionalParams);
  }

  /**
   * Loads manage this project area page and archive time line or iteration in selected project area
   */
  @Test
  public void testDeleteButtonInProjArea() {
    loadPage("ccm/select_iterationin_projarea.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    rm.deleteButtonInProjArea();
  }

  /**
   * Loads manage this project area page and modify attachement in process description
   */
  @Test
  public void testModifyProcessAttachment() {
    loadPage("ccm/modify_process_attachment.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/modify_process_attachment_new.html");
    clickNumberToPagePath.put(2, "ccm/modify_process_attachment_new.html");
    clickNumberToPagePath.put(3, "ccm/modify_process_attachment_add_file.html");
    clickNumberToPagePath.put(4, "ccm/modify_process_attachment_add_file.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "ccm/modify_process_attachment_ok.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String file = new File("src\\test\\resources\\dng\\export.csv").getAbsolutePath();
    rm.modifyProcessAttachment("Sample", file);
  }

  /**
   * Loads manage this project area page and add process roles to memember
   */
  @Test
  public void testTeamMemberActions() {
    loadPage("ccm/team_member_actions_0.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/team_member_actions_window.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.teamMemberActions("EXTERNAL Shravana Sainath Pasupala Venkata (Datamatics, RBEI/EMT5)", "Add Process Roles...",
        "Commenter", "Add");
  }

  /**
   * Loads manage this project area page and remove process roles to memember
   */
  @Test
  public void testTeamMemberActionsRemove() {
    loadPage("ccm/team_member_actions_0.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/team_member_actions_remove.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.teamMemberActions("EXTERNAL Shravana Sainath Pasupala Venkata (Datamatics, RBEI/EMT5)",
        "Remove Process Roles...", "Author", "Remove");
  }

  /**
   * Loads manage this project area page and edit process roles to memember
   */
  @Test
  public void testTeamMemberActionsEdit() {
    loadPage("ccm/team_member_actions_0.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/Team_member_actions_edit.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.teamMemberActions("EXTERNAL Shravana Sainath Pasupala Venkata (Datamatics, RBEI/EMT5)", "Edit Process Roles...",
        "Project Responsible", "OK");
  }

  /**
   * Loads manage this project area page and clicking on selected team area
   */
  @Test
  public void testSelectTeamArea() {
    loadPage("ccm/set_team_area_fields_create.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    rm.selectTeamArea("Team A");
  }

  /**
   * Loads manage this project area page and clicks on project area in team area page
   */
  @Test
  public void testTeamAreaToProjArea() {
    loadPage("ccm/set_team_area_fields_team_area.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    rm.teamAreaToProjArea("SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC");
  }

  /**
   * Loads manage this project area page and creates process description
   */
  @Test
  public void testCreateProcessDescription() {
    loadPage("ccm/create_process_description.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/create_process_description_save.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/create_process_description_success.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "ccm/create_process_description_success.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Project area saved successfully.", rm.createProcessDescription("Test Process Description"));
  }

  /**
   * Loads manage this project area page and creating project area
   */
  @Test
  public void testCreateProjectArea() {
    loadPage("ccm/create_project_area.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/create_project_area_new.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/create_project_area_add_members.html");
    clickNumberToPagePath.put(6, "ccm/create_project_area_add_members.html");
    clickNumberToPagePath.put(7, "ccm/create_project_area_add_members.html");
    clickNumberToPagePath.put(8, "ccm/create_project_area_admin.html");
    clickNumberToPagePath.put(9, "ccm/create_project_area_admin.html");
    clickNumberToPagePath.put(10, "ccm/create_project_area_admin.html");
    clickNumberToPagePath.put(11, "ccm/create_project_area_msg.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(TeamArea.NAME.toString(),
        "SYS-TEST-com.bosch.rqm.tmpl.default.usa_" + DateUtil.getCurrentDateAndTime() + "_IBM");
    additionalParams.put(TeamArea.SUMMARY.toString(),
        "SYS-TEST-com.bosch.rqm.tmpl.default.usa summary_" + DateUtil.getCurrentDateAndTime());
    additionalParams.put(TeamArea.DESCRIPTION.toString(),
        "SYS-TEST-com.bosch.rqm.tmpl.default.usa drescription_" + DateUtil.getCurrentDateAndTime());
    additionalParams.put(TeamArea.MEMBERS.toString(), "PPC4KOR" + "_Author");
    additionalParams.put(TeamArea.ADMINISTRATORS.toString(), "PPC4KOR" + "_Author");
    rm.createProjectArea(additionalParams, "BBM-ALM-DNG@USA2018.2.0");
  }

  /**
   * Loads manage this project area page and creating project area
   */
  @Test
  public void testCreateProjectAreaRoleNull() {
    loadPage("ccm/create_project_area.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/create_project_area_new.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/create_project_area_add_members.html");
    clickNumberToPagePath.put(6, "ccm/create_project_area_add_members.html");
    clickNumberToPagePath.put(7, "ccm/create_project_area_add_members.html");
    clickNumberToPagePath.put(8, "ccm/create_project_area_admin.html");
    clickNumberToPagePath.put(9, "ccm/create_project_area_admin.html");
    clickNumberToPagePath.put(10, "ccm/create_project_area_admin.html");
    clickNumberToPagePath.put(11, "ccm/create_project_area_msg.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(TeamArea.NAME.toString(),
        "SYS-TEST-com.bosch.rqm.tmpl.default.usa_" + DateUtil.getCurrentDateAndTime() + "_IBM");
    additionalParams.put(TeamArea.SUMMARY.toString(),
        "SYS-TEST-com.bosch.rqm.tmpl.default.usa summary_" + DateUtil.getCurrentDateAndTime());
    additionalParams.put(TeamArea.DESCRIPTION.toString(),
        "SYS-TEST-com.bosch.rqm.tmpl.default.usa drescription_" + DateUtil.getCurrentDateAndTime());
    additionalParams.put(TeamArea.MEMBERS.toString(), "PPC4KOR");
    additionalParams.put(TeamArea.ADMINISTRATORS.toString(), "PPC4KOR");
    rm.createProjectArea(additionalParams, "BBM-ALM-DNG@USA2018.2.0");
  }

  /**
   * Loads manage this project area page and create workflow transactions states and actions
   */
  @Test
  public void testWorkflowTransactions() {
    loadPage("ccm/workflow_transactions.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/workflow_transactions_states.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/workflow_transactions_action.html");
    clickNumberToPagePath.put(5, "ccm/workflow_transactions_action.html");
    clickNumberToPagePath.put(6, "ccm/workflow_transactions_action.html");
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    clickNumberToPagePath.put(9, null);
    clickNumberToPagePath.put(10, "ccm/workflow_transactions_add.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.workflowTransactions("Open", "open.gif", "open.gif", "Under Review", "Open", "Add...");
  }

  /**
   * Loads manage this project area page and it return successfull message if it is save
   */
  @Test
  public void testMsg() {
    loadPage("ccm/msg_success.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/msg_success.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Project area saved successfully.", rm.msg("Save"));
  }

  /**
   * Loads manage this project area page and it return failer message if it's not saved
   */
  @Test
  public void testMsgFailed() {
    loadPage("ccm/msg_error.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/msg_error.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.msg("Save").contains("Project area cannot be saved."));
  }

  /**
   * Loads manage this project area page and add new workflow
   */
  @Test
  public void testWorkFlows() {
    loadPage("ccm/work_flow.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/work_flow_create.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.workFlows("New", "Add...");
  }

  /**
   * Loads manage this project area page and click on project area link
   */
  @Test
  public void testExploreProjArea() {
    loadPage("ccm/explore_projarea.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    rm.exploreProjArea("SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC");
  }

  /**
   * Unit test for {@link ManageProjectAreaPage#tabSection(String)}
   */
  @Test
  public void testTabSection() {
    loadPage("ccm/TabSection_01_Overview.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/TabSection_02_Permissions.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.tabSection("Permissions"));
  }

  /**
   * Loads manage this project area page and click on explore button
   */
  @Test
  public void testExploreProjButton() {
    loadPage("ccm/workflow_transactions.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    rm.exploreProjButton();
  }

  /**
   * Loads "About the Application page" and returns alm release version.
   */
  @Test
  public void testAlmReleaseVersion() {
    loadPage("ccm/AboutTheApplication.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    assertEquals("6.0.6.1", rm.almReleaseVersion());
  }

  /**
   * Loads "About the Application page" and returns alm ifix release version.
   */
  @Test
  public void testGetAlmIFIXReleaseVersion() {
    loadPage("ccm/AboutTheApplication.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    assertEquals("iFix005", rm.getAlmIFIXReleaseVersion());
  }

  /**
   * Loads manage this project area page and it return failure message if it's not saved
   */
  @Test
  public void testArchiveButtonInProjArea() {
    loadPage("ccm/archive_button_in_projarea.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/archive_save.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.archiveButtonInProjArea();
  }

  /**
   * Loads manage this project area page and it return failure message if it's not saved
   */
  @Test
  public void testArchiveTeamArea() {
    loadPage("ccm/archive_teamarea.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/archive_teamarea.html");
    clickNumberToPagePath.put(2, "ccm/archive_save.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.archiveTeamArea("SYS-TEST-com.bosch.rtc.tmpl.Formal_2018.1.0_RC2_IBM_6.0.5");
  }

  /**
   * <p>
   * Unit test covers for method ${@link ManageProjectAreaPage#addMemberWithAllRoles(String)}
   * <p>
   * 
   * @author NVV1HC
   */
  @Test
  public void testAddMemberWithAllRoles() {
    loadPage("dng/addMemberWithAllRoles.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/addMemberWithAllRoles_02.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/addMemberWithAllRoles_03.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/addMemberWithAllRoles_04.html");
    clickNumberToPagePath.put(6, "dng/addMemberWithAllRoles_09.html");
    clickNumberToPagePath.put(7, "dng/addMemberWithAllRoles_08.html");
    clickNumberToPagePath.put(8, "dng/addMemberWithAllRoles_05.html");
    clickNumberToPagePath.put(9, null);

    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addMemberWithAllRoles("CDG ALM Tester system-user-CC (XC-ECO/ESI1)");
  }

  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#createRole(Map)}
   * 
   * @author KYY1HC
   */
  @Test
  public void testCreateRole() {
    loadPage("dng/CreateRole_01_ClickCreateRoleButton.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/CreateRole_02_ClearIdentifier.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/CreateRole_04_InputName.html");
    clickNumberToPagePath.put(4, "dng/CreateRole_05_SelectCardinality.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/CreateRole_07_ClickSave.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IDENTIFIER", "abc");
    additionalParams.put("NAME", "testing");
    additionalParams.put("CARDINALITY", "many");
    additionalParams.put("DESCRIPTION", "testing");
    rm.createRole(additionalParams);
  }

  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}
   * Grant Permission
   * 
   * @author KYY1HC
   */
  @Test
  public void testPermissionGranted() {
    loadPage("dng/GrantOrRevokePermission_01_PermissionsScreen.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/GrantOrRevokePermission_05_GrantPermission.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/GrantOrRevokePermission_06_SavedSuccessfully.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.grantOrRevokePermission("Test_Role_To_Override_Locks_18_07_2021_17_07_901", "Override locks", "Grant");
  }

  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}
   * Revoke Permission
   * 
   * @author KYY1HC
   */
  @Test
  public void testPermissionRevoked() {
    loadPage("dng/GrantOrRevokePermission_01_PermissionsScreen.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/GrantOrRevokePermission_05_RevokedPermission.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/GrantOrRevokePermission_06_SavedSuccessfully.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.grantOrRevokePermission("Test_Role_To_Override_Locks_18_07_2021_17_07_901", "Override locks", "Revoke");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}
   * Show By Operation - Revoke Permission
   * @author VDY1HC
   */
  @Test
  public void testPermissionRevokeOperation() {
    loadPage("gc/Permission_Operation_Revoke_03.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "gc/Permission_Operation_Revoke_04.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.grantOrRevokePermission("Commenter", "Modify CLM Link", "Revoke");
  }  
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#grantOrRevokePermission(String, String, String)}
   * Show By Operation - Grant Permission
   * @author VDY1HC
   */
  @Test
  public void testPermissionGrantOperation() {
    loadPage("gc/Permission_Operation_Grant_03.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "gc/Permission_Operation_Grant_04.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.grantOrRevokePermission("Commenter", "Modify CLM Link", "Grant");
  }

  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#deleteRole(String)}
   * 
   * @author KYY1HC
   */
  @Test
  public void testDeleteRole() {
    loadPage("dng/DeletedRole_01_RolesPage.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/DeletedRole_02_SelectedRole.html");
    clickNumberToPagePath.put(2, "dng/DeletedRole_03_SaveButtonEnabled.html");
    clickNumberToPagePath.put(3, "dng/DeletedRole_04_SavedSuccessfully.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteRole("Role_Override_Locks__23_07_2021_12_07_625");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#getDisplayedMessage()}
   * @author VDY1HC
   */
  @Test
  public void testGetDisplayedMessage() {
    loadPage("gc/getDisplayedMessage.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    assertTrue(rm.getDisplayedMessage()=="");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#getDisplayedMessage()}
   * @author VDY1HC
   */
  @Test
  public void testGetDisplayedMessageWithMessage() {
    loadPage("gc/getDisplayedMessage_WithMessage.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    assertTrue(rm.getDisplayedMessage().contentEquals("Project area saved successfully."));
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#removeMemberFromProjectArea(Map)}
   * @author VDY1HC
   */
  @Test
  public void testRemoveMemberFromProjectArea() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("MEMBER_NAME","Aarthi Thirumalnesan (MS/PJ-ALM)");
    additionalParams.put("ROLE","Members");
    loadPage("gc/removeMemberFromProjectArea_01.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "gc/removeMemberFromProjectArea_03.html");
    clickNumberToPagePath.put(3, "gc/removeMemberFromProjectArea_04.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.removeMemberFromProjectArea(additionalParams);
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#isExistingMemberInProjectArea(Map)}
   * @author VDY1HC
   */
  @Test
  public void testIsExistingMemberInProjectArea() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("MEMBER_NAME","Aarthi Thirumalnesan (MS/PJ-ALM)");
    additionalParams.put("ROLE","Members");
    loadPage("gc/removeMemberFromProjectArea_01.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    rm.isExistingMemberInProjectArea(additionalParams);
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#clickOnRefreshButton()}
   * @author VDY1HC
   */
  @Test
  public void testClickOnRefreshButton() {
    loadPage("gc/removeMemberFromProjectArea_01.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnRefreshButton();
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#clickOnSaveButton(String)}
   * @author VDY1HC
   */
  @Test
  public void testClickOnSaveButton() {
    loadPage("gc/clickOnSaveButton.htm");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "gc/clickOnSaveButton_02.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnSaveButton("Project area saved successfully.");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#addMemberToProjectAreaWithRole(Map)}
   * @author VDY1HC
   */
  @Test
  public void testAddMemberToProjectAreaWithRole() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("MEMBER_NAME","rtctest2 rtctester2 (CI/PLS2)");
    additionalParams.put("LIST_OF_MEMBER_ROLES","Contributor");
    additionalParams.put("MEMBER_ID","RTCTEST2");
    loadPage("gc/addMemberToProjectAreaWithRole_01.htm");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "gc/addMemberToProjectAreaWithRole_02.htm");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "gc/addMemberToProjectAreaWithRole_03.htm");
    clickNumberToPagePath.put(4, "gc/addMemberToProjectAreaWithRole_04.htm");
    clickNumberToPagePath.put(5, "gc/addMemberToProjectAreaWithRole_05.htm");
    clickNumberToPagePath.put(6, "gc/addMemberToProjectAreaWithRole_06.htm");
    clickNumberToPagePath.put(7, "gc/addMemberToProjectAreaWithRole_07.htm");
    clickNumberToPagePath.put(8, "gc/addMemberToProjectAreaWithRole_08.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addMemberToProjectAreaWithRole(additionalParams);
  }
  

  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#selectDislayMembersPerPage(String)}
   * @author VDY1HC
   */
  @Test
  public void testSelectDislayMembersPerPage() {
    loadPage("gc/selectDislayMembersPerPage_01.htm");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "gc/selectDislayMembersPerPage_02.htm");
    clickNumberToPagePath.put(2, "gc/selectDislayMembersPerPage_03.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectDislayMembersPerPage("100");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#selectShowByInPermission(String)}
   * Show by Operation
   * @author VDY1HC
   */
  @Test
  public void testSelectShowByInPermission_01() {
    loadPage("gc/selectShowByInPermission_01.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "gc/selectShowByInPermission_02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectShowByInPermission("Operation");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#selectShowByInPermission(String)}
   * Show by Role
   * @author VDY1HC
   */
  @Test
  public void testSelectShowByInPermission_02() {
    loadPage("gc/selectShowByInPermission_01.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "gc/selectShowByInPermission_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectShowByInPermission("Role");
  }

  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#isAssociationDisplayed(String, String)}
   * @author KYY1HC
   */
  @Test
  public void testIsAssociationDisplayed() {
    loadPage("gc/IsAssociationDisplayed_01.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    assertTrue(rm.isAssociationDisplayed("Implementation Requests", "AE Doors Development (CCM agile)"));
  }

  /**
   * Unit test covers for method ${@link ManageProjectAreaPage#verifyDefinedRolesInPA(String)}
   *@author NCY3HC
   */
  @Test
  public void testVerifyDefinedRolesInPA() {
    loadPage("dng/totalRoleInPA.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    assertTrue(rm.verifyDefinedRolesInPA("Administrator;Author;Commenter;Configuration Lead;Test_Role"));
  }
  
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPage#verifyIsPermissionShowByRole(Map)}
   * @author NCY3HC
   */
  @Test
  public void testVerifyIsPermissionShowByRole() {
    loadPage("dng/AdministrationPermissionShowByRole.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ROLE_NAME","Administrator");
    additionalParams.put("OPERATION_TYPE_SORT_IN_ORDER","Manage Baselines");
    additionalParams.put("PERMISSION_SORT_IN_ORDER","Not Permitted");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    assertTrue(rm.verifyIsPermissionShowByRole(additionalParams));
  }
  
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPage#verifyIsPermissionShowByOperation(Map)}
   * @author NCY3HC
   */
  @Test
  public void testVerifyIsPermissionShowByOperation() {
    loadPage("dng/AdministratorPermissionShowByOperartion_01.html");
    ManageProjectAreaPage rm = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ROLE_NAME","Administrator");
    additionalParams.put("OPERATION_TYPE_SORT_IN_ORDER","Manage Baselines");
    additionalParams.put("PERMISSION_SORT_IN_ORDER","Not Permitted");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    assertTrue(rm.verifyIsPermissionShowByOperation(additionalParams));
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPage#getMemberNameList(String)}
   * 
   */
  @Test
  public void testGetMemberNameList() {
    loadPage("dng/isListMemberRoleCorrect.html");
    ManageProjectAreaPage managerProjectAreaPage = getJazzPageFactory().getManageProjectAreaPage();
    String testList = "Abt Thomas (ED/PMO-CA);Bahr Markus (AE/EED5);Bauer Tamas (ED-TS/EAC4);Ben Ammar Nessim (AE/EED11);Bender Stefan (ED/EDI31);Eckert Klaus (AE/EED11);ERDEI ANDRAS (AE/EEC4);Fischer Frank-Sven (AE/ENS2);Fkaier Haithem (AE/EED11);Gommenginger Charles (AE/EED-P2);Gramberg Ralf (AE/EQM-ED);Grass Ansgar (AE/EED5);Grassi Emmanuel (ED-CA/ENS1);Hannasky Jonas (AE/EED2);Higel Martin (AE/EED11);Hondmann Christian (ED/EDA);Hriczu Adam (ED-TS/EAC4);Hug Timo (ED-TS/EAC1);Jerger Armin (AE/EED11);Juhasz Balint (AE/EEC4);Kalchschmidt Peter (PS/QMM-EM1);Knoerr Malte (AE/EED1);Kuderer Alexander (ED/ENS1);Kuperberg Ilya (AE/EED3);Ludwig Victor (ED/EDI31);Lurk Volker (ED-TS/EAC4);Malich Thomas (AE/EED2);Misenheimer Charles Wesley (AE/EEC4);Montes Camacho Jose Daniel (ED/EEF1-TS-Tl);Pozsar Csaba (AE/ECF-EV-SD);Puntus Andreas (AE/EED3);Rau Marco (ED-TS/EAC1);Rehagen Johannes (AE/EED11);Reiss Klaus (AE/EED2);Rosner Thomas (AE/EED4);Sauter Christian (EDA-WS/PJ-WD);Schaller Simon (AE/EED1);Schermann Michael (ED-TS/ENP2);Schmidt Zoltan (AE/EEC4);Schreiber Jacques (ED-TS/EAC3);Schroeder Patrick (ED/EVW-C ED/EVW2-P);Soellner Michael (AE/EED5);Stubenbord Linh-Thao (ED/EDS1);Vollmer Dirk (ED/EDI31);Wassmer Bruno (AE/EED4);Weickenmeier Klaus (ED/EDS1);Wendl Michael (AE/EMC2)";
    assertNotNull(managerProjectAreaPage);
    String nameList = managerProjectAreaPage.getMemberNameList("Members");
    assertEquals(testList, nameList);
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPage#getMemberRoleList(String)}
   */
  @Test
  public void testGetMemberRoleList() {
    loadPage("dng/isListMemberRoleCorrect.html");
    ManageProjectAreaPage managerProjectAreaPage = getJazzPageFactory().getManageProjectAreaPage();
    String testList = ";Key User, Author, Commenter;Commenter, Author;;Author;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Project Responsible, Author, Commenter, Key User, Configuration Administrator;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter;Commenter, Author;;Author, Commenter, Configuration Administrator;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter";
    assertNotNull(managerProjectAreaPage);    
    String roleList = managerProjectAreaPage.getMemberRoleList("Members");
    assertEquals(testList, roleList);
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPage#isDisplayedMemberListWithRoles(String,String)}
   */
  @Test
  public void testIsDisplayedMemberListWithRoles() {
    loadPage("dng/isListMemberRoleCorrect.html");
    ManageProjectAreaPage managerProjectAreaPage = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(managerProjectAreaPage);
    String nameList = "Abt Thomas (ED/PMO-CA);Bahr Markus (AE/EED5);Bauer Tamas (ED-TS/EAC4);Ben Ammar Nessim (AE/EED11);Bender Stefan (ED/EDI31);Eckert Klaus (AE/EED11);ERDEI ANDRAS (AE/EEC4);Fischer Frank-Sven (AE/ENS2);Fkaier Haithem (AE/EED11);Gommenginger Charles (AE/EED-P2);Gramberg Ralf (AE/EQM-ED);Grass Ansgar (AE/EED5);Grassi Emmanuel (ED-CA/ENS1);Hannasky Jonas (AE/EED2);Higel Martin (AE/EED11);Hondmann Christian (ED/EDA);Hriczu Adam (ED-TS/EAC4);Hug Timo (ED-TS/EAC1);Jerger Armin (AE/EED11);Juhasz Balint (AE/EEC4);Kalchschmidt Peter (PS/QMM-EM1);Knoerr Malte (AE/EED1);Kuderer Alexander (ED/ENS1);Kuperberg Ilya (AE/EED3);Ludwig Victor (ED/EDI31);Lurk Volker (ED-TS/EAC4);Malich Thomas (AE/EED2);Misenheimer Charles Wesley (AE/EEC4);Montes Camacho Jose Daniel (ED/EEF1-TS-Tl);Pozsar Csaba (AE/ECF-EV-SD);Puntus Andreas (AE/EED3);Rau Marco (ED-TS/EAC1);Rehagen Johannes (AE/EED11);Reiss Klaus (AE/EED2);Rosner Thomas (AE/EED4);Sauter Christian (EDA-WS/PJ-WD);Schaller Simon (AE/EED1);Schermann Michael (ED-TS/ENP2);Schmidt Zoltan (AE/EEC4);Schreiber Jacques (ED-TS/EAC3);Schroeder Patrick (ED/EVW-C ED/EVW2-P);Soellner Michael (AE/EED5);Stubenbord Linh-Thao (ED/EDS1);Vollmer Dirk (ED/EDI31);Wassmer Bruno (AE/EED4);Weickenmeier Klaus (ED/EDS1);Wendl Michael (AE/EMC2)";
    String roleList = ";Key User, Author, Commenter;Commenter, Author;;Author;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Project Responsible, Author, Commenter, Key User, Configuration Administrator;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter;Commenter, Author;;Author, Commenter, Configuration Administrator;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter";
    assertTrue(managerProjectAreaPage.isDisplayedMemberListWithRoles(nameList, roleList));
    nameList = "Abt Thomas (ED/PMO-CA);Bahr Markus (AE/EED5)";
    roleList = "Key User, Author, Commenter;Commenter, Author";
    assertFalse(managerProjectAreaPage.isDisplayedMemberListWithRoles(nameList, roleList));
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPage#createTimeLineOrIterations(Map)}
   */
  @Test
  public void testCreateTimeLineOrIterations() {
  loadPage("ccm/createTimeline0.html");
  ManageProjectAreaPage managerProjectAreaPage = getJazzPageFactory().getManageProjectAreaPage();
  assertNotNull(managerProjectAreaPage);
  Map<Integer, String> clickNumberToPagePath = new HashMap<>();
  clickNumberToPagePath.put(1, "ccm/createTimeline.html");
  clickNumberToPagePath.put(2, "ccm/createTimeline0.html");
  Map<String, String> additionalParams = new LinkedHashMap<String, String>();
  additionalParams.put("BUTTON_OPTION","Create Timeline...");
  additionalParams.put("NAME","‪New Timeline1‬‎");
  additionalParams.put("DURATION","4");
  managerProjectAreaPage.createTimeLineOrIterations(additionalParams);
  
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPage#editPropertiesTimelineOrIteration(Map)}
   * 
   */
  @Test
  public void testEditPropertiesTimelineOrIteration() {
    loadPage("ccm/createTimeline0.html");
    ManageProjectAreaPage managerProjectAreaPage = getJazzPageFactory().getManageProjectAreaPage();
    assertNotNull(managerProjectAreaPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();  
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/editProperties1.html");
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String,String> param = new HashMap<String, String>();
    param.put("NAME","New Timeline1");
    param.put("EDIT_DIALOG_NAME","Edit the Timeline");
    managerProjectAreaPage.editPropertiesTimelineOrIteration(param);
  }
}
