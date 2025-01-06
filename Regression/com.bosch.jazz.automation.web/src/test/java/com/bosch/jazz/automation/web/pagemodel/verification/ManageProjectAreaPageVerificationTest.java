/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Verifies whether data exists in server by searching in the search box.
 */
public class ManageProjectAreaPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test coverage for
   * {@link ManageProjectAreaPageVerification#verifyTeamMemberActions(String, String, String, String, String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyTeamMemberActions() {
    loadPage("dng/verifyTeamMemberActions.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(ravt);
    ravt.verifyTeamMemberActions("CDG ALM Tester system-user-CC (XC-ECO/ESI1)", "Add Process Roles...", "Administrator",
        "OK", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link ManageProjectAreaPageVerification#verifyTeamMemberActions(String, String, String, String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyTeamMemberActions() {
    loadPage("dng/verifyTeamMemberActions.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(ravt);
    ravt.verifyTeamMemberActions("CDG ALM Tester system-user-CC (XC-ECO/ESI1)", "Add Process Roles...",
        "Administrator admin", "OK", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifySelectTeamArea(String, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifySelectTeamArea() {
    loadPage("dng/verifySelectTeamArea.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectTeamArea("Team Area for automatic testing TS_25856", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifySelectTeamArea(String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfySelectTeamArea() {
    loadPage("dng/verifySelectTeamArea.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectTeamArea("Team Area for automatic testing", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifySelectTeamArea(String, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyExploreProjButton() {
    loadPage("dng/verifyExploreProjButton.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(ravt);
    ravt.verifyExploreProjButton("true");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifySelectTeamArea(String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyExploreProjButton() {
    loadPage("dng/verifyExploreProjButton_failed.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(ravt);
    ravt.verifyExploreProjButton("false");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyAddMemberWithAllRoles(String, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyAddMemberWithAllRoles() {
    loadPage("dng/verifyAddMemberWithAllRoles_02.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "dng/verifyAddMemberWithAllRoles_03.html");
    clickToPage.put(3, "dng/verifyAddMemberWithAllRoles_04.html");
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyAddMemberWithAllRoles("CDG ALM Tester system-user-CC (XC-ECO/ESI1)", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyAddMemberWithAllRoles(String, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyAddMemberWithAllRoles() {
    loadPage("dng/verifyAddMemberWithAllRoles_02.html");
    ManageProjectAreaPageVerification ravt = getJazzPageFactory().getManageProjectAreaPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "dng/verifyAddMemberWithAllRoles_03.html");
    clickToPage.put(3, "dng/verifyAddMemberWithAllRoles_failed.html");
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyAddMemberWithAllRoles("CDG ALM Tester system-user-CC (XC-ECO/ESI1)", "false");
  }
  
  /**
   * Unit test for {@link ManageProjectAreaPageVerification#verifyTabSection(String, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyTabSection() {
    loadPage("ccm/TabSection_02_Permissions.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyTabSection("Permissions", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyCreateRole(Map,String)}.<br>
   * Passed case
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyCreateRole() {
    loadPage("dng/VerifyCreateRole_01_SaveSuccessfully.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IDENTIFIER", "Override_Locks");
    additionalParams.put("NAME", "Test_Role_To_Override_Locks_18_07_2021_17_07_901");
    additionalParams.put("CARDINALITY", "many");
    additionalParams.put("DESCRIPTION", "Verify Defect IBM 356283");
    rm.verifyCreateRole(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyGrantOrRevokePermission(String, String, String, String)}.<br>
   * Revoke Permission successully
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyPermissionRevoke() {
    loadPage("dng/GrantOrRevokePermission_07_VerifyRevokeSuccessfully.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.verifyGrantOrRevokePermission("Test_Role_To_Override_Locks_18_07_2021_17_07_901", "Override locks", "Revoke", "Role");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyGrantOrRevokePermission(String, String, String, String)}.<br>
   * Grant Permisison successully
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyPermissionGrant() {
    loadPage("dng/GrantOrRevokePermission_07_VerifyGrantSuccessfully.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.verifyGrantOrRevokePermission("Test_Role_To_Override_Locks_18_07_2021_17_07_901", "Override locks", "Grant", "Role");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyGrantOrRevokePermission(String, String, String, String)}.<br>
   * Revoke Permission successully - Operation
   */
  @Test
  public void testVerifyPermissionRevoke_Operation() {
    loadPage("gc/Permission_Operation_Revoke_04.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyGrantOrRevokePermission("Commenter", "Modify CLM Link", "Revoke", "Operation");
  }
  
  /**
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyGrantOrRevokePermission(String, String, String, String)}.<br>
   * Grant Permisison successully - Operation
   */
  @Test
  public void testVerifyPermissionGrant_Operation() {
    loadPage("gc/Permission_Operation_Grant_04.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyGrantOrRevokePermission("Commenter", "Modify CLM Link", "Grant", "Operation");
  }

  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyDeleteRole(String, String)}.<br>
   * Deleted Role successfully
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyDeleteRole() {
    loadPage("dng/DeletedRole_04_SavedSuccessfully.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyDeleteRole("Role_Override_Locks__23_07_2021_12_07_625", "");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyDeleteRole(String, String)}.<br>
   * Role is still displayed
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerIfyDeleteRole() {
    loadPage("dng/DeletedRole_04_SavedSuccessfully.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyDeleteRole("Administrator", "");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifySelectDislayMembersPerPage(String, String)}
   * @author VDY1HC
   */
  @Test
  public void tesVerifytSelectDislayMembersPerPage() {
    loadPage("gc/selectDislayMembersPerPage_03.htm");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifySelectDislayMembersPerPage("100","");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifyAddMemberToProjectAreaWithRole(Map, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyAddMemberToProjectAreaWithRole() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("MEMBER_NAME","rtctest2 rtctester2 (CI/PLS2)");
    additionalParams.put("LIST_OF_MEMBER_ROLES","Contributor");
    additionalParams.put("MEMBER_ID","RTCTEST2");
    loadPage("gc/addMemberToProjectAreaWithRole_08.htm");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyAddMemberToProjectAreaWithRole(additionalParams,"");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifyClickOnSaveButton(String, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyClickOnSaveButton() {
    loadPage("gc/clickOnSaveButton_02.htm");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyClickOnSaveButton("Project area saved successfully.","");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifyClickOnRefreshButton(String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyClickOnRefreshButton() {
    loadPage("gc/clickOnSaveButton_02.htm");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyClickOnRefreshButton("");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifyIsExistingMemberInProjectArea(Map, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyIsExistingMemberInProjectArea() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("MEMBER_NAME","Aarthi Thirumalnesan (MS/PJ-ALM)");
    additionalParams.put("ROLE","Members");
    loadPage("gc/removeMemberFromProjectArea_01.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyIsExistingMemberInProjectArea(additionalParams,"");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifyRemoveMemberFromProjectArea(Map, String)}
   */
  @Test
  public void testVerifyRemoveMemberFromProjectArea() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("MEMBER_NAME","Aarthi Thirumalnesan (MS/PJ-ALM)");
    loadPage("gc/removeMemberFromProjectArea_04.html");
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyRemoveMemberFromProjectArea(additionalParams,"");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifyGetDisplayedMessage(String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyGetDisplayedMessage() {
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    rm.verifyGetDisplayedMessage("");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifySelectShowByInPermission(String, String)}
   * Show by Role
   * @author VDY1HC
   */
  @Test
  public void testVerifySelectShowByInPermission_01() {
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    loadPage("gc/selectShowByInPermission_01.html");
    rm.verifySelectShowByInPermission("Role","");
  }  
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifySelectShowByInPermission(String, String)}
   * Show by Operation
   * @author VDY1HC
   */
  @Test
  public void testVerifySelectShowByInPermission_02() {
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    loadPage("gc/selectShowByInPermission_02.html");
    rm.verifySelectShowByInPermission("Operation","");
  }  
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifySelectShowByInPermission(String, String)}
   * Show by Operation with select Permission
   * @author VDY1HC
   */
  @Test
  public void testVerifySelectShowByInPermission_03() {
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    loadPage("gc/selectShowByInPermission_03.html");
    rm.verifySelectShowByInPermission("Operation","");
  }
  
  /**
   * Unit test covers for method ${@link ManageProjectAreaPageVerification#verifyIsAssociationDisplayed(String, String,String)}
   * @author KYY1HC
   */
  @Test
  public void testVerifyIsAssociationDisplayed() {
    ManageProjectAreaPageVerification rm = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(rm);
    loadPage("gc/IsAssociationDisplayed_01.html");
    assertEquals("PASSED", rm.verifyIsAssociationDisplayed("", "", "true").getState().toString());
    assertEquals("FAILED", rm.verifyIsAssociationDisplayed("", "", "false").getState().toString());
  }  
  
  
  /**
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyVerifyIsPermissionShowByOperation(Map, String)}.<br>
   * @author NCY3HC
   */
  @Test
  public void testVerifyVerifyIsPermissionShowByOperation() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ROLE_NAME","Administrator");
    additionalParams.put("OPERATION_TYPE_SORT_IN_ORDER","Manage Baselines");
    additionalParams.put("PERMISSION_SORT_IN_ORDER","Not Permitted");
    
    loadPage("dng/AdministratorPermissionShowByOperartion_01.html");
    ManageProjectAreaPageVerification mpav =
        getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(mpav);
    assertEquals("PASSED", mpav.verifyVerifyIsPermissionShowByOperation(additionalParams, "true").getState());
  }
  
  /**
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyVerifyIsPermissionShowByRole(Map, String)}.<br>
   * @author NCY3HC
   */
  @Test
  public void testVerifyVerifyIsPermissionShowByRole() {
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ROLE_NAME","Administrator");
    additionalParams.put("OPERATION_TYPE_SORT_IN_ORDER","Manage Baselines");
    additionalParams.put("PERMISSION_SORT_IN_ORDER","Not Permitted");
    
    loadPage("dng/AdministrationPermissionShowByRole.html");
    ManageProjectAreaPageVerification mpav =
        getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(mpav);
    assertEquals("PASSED", mpav.verifyVerifyIsPermissionShowByOperation(additionalParams, "true").getState());
  }
  
  /**
   * Unit test coverage for {@link ManageProjectAreaPageVerification#verifyVerifyDefinedRolesInPA(String, String)}.<br>
   * @author NCY3HC
   */
  @Test
  public void testVerifyVerifyDefinedRolesInPA() {    
    loadPage("dng/totalRoleInPA.html");
    ManageProjectAreaPageVerification mpav =
        getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(mpav);
    assertEquals("PASSED", mpav.verifyVerifyDefinedRolesInPA("Administrator;Author;Commenter;Configuration Lead;Test_Role", "true").getState());
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPageVerification#verifyGetMemberNameList(String)}
   * 
   */
  @Test
  public void testVerifyGetMemberNameList() {
    loadPage("dng/isListMemberRoleCorrect.html");
    ManageProjectAreaPageVerification managerProjectAreaVerificationPage = getJazzPageFactory().getManageProjectAreaPageVerification();
    String testList = "Abt Thomas (ED/PMO-CA);Bahr Markus (AE/EED5);Bauer Tamas (ED-TS/EAC4);Ben Ammar Nessim (AE/EED11);Bender Stefan (ED/EDI31);Eckert Klaus (AE/EED11);ERDEI ANDRAS (AE/EEC4);Fischer Frank-Sven (AE/ENS2);Fkaier Haithem (AE/EED11);Gommenginger Charles (AE/EED-P2);Gramberg Ralf (AE/EQM-ED);Grass Ansgar (AE/EED5);Grassi Emmanuel (ED-CA/ENS1);Hannasky Jonas (AE/EED2);Higel Martin (AE/EED11);Hondmann Christian (ED/EDA);Hriczu Adam (ED-TS/EAC4);Hug Timo (ED-TS/EAC1);Jerger Armin (AE/EED11);Juhasz Balint (AE/EEC4);Kalchschmidt Peter (PS/QMM-EM1);Knoerr Malte (AE/EED1);Kuderer Alexander (ED/ENS1);Kuperberg Ilya (AE/EED3);Ludwig Victor (ED/EDI31);Lurk Volker (ED-TS/EAC4);Malich Thomas (AE/EED2);Misenheimer Charles Wesley (AE/EEC4);Montes Camacho Jose Daniel (ED/EEF1-TS-Tl);Pozsar Csaba (AE/ECF-EV-SD);Puntus Andreas (AE/EED3);Rau Marco (ED-TS/EAC1);Rehagen Johannes (AE/EED11);Reiss Klaus (AE/EED2);Rosner Thomas (AE/EED4);Sauter Christian (EDA-WS/PJ-WD);Schaller Simon (AE/EED1);Schermann Michael (ED-TS/ENP2);Schmidt Zoltan (AE/EEC4);Schreiber Jacques (ED-TS/EAC3);Schroeder Patrick (ED/EVW-C ED/EVW2-P);Soellner Michael (AE/EED5);Stubenbord Linh-Thao (ED/EDS1);Vollmer Dirk (ED/EDI31);Wassmer Bruno (AE/EED4);Weickenmeier Klaus (ED/EDS1);Wendl Michael (AE/EMC2)";
    assertNotNull(managerProjectAreaVerificationPage);    
    assertEquals("PASSED", managerProjectAreaVerificationPage.verifyGetMemberNameList(testList).getState());
    assertEquals("FAILED", managerProjectAreaVerificationPage.verifyGetMemberNameList("").getState());
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPageVerification#verifyGetMemberRoleList(String)}
   */
  @Test
  public void testVerifyGetMemberRoleList() {
    loadPage("dng/isListMemberRoleCorrect.html");
    ManageProjectAreaPageVerification managerProjectAreaVerificationPage = getJazzPageFactory().getManageProjectAreaPageVerification();
    String testList = ";Key User, Author, Commenter;Commenter, Author;;Author;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Project Responsible, Author, Commenter, Key User, Configuration Administrator;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter;Commenter, Author;;Author, Commenter, Configuration Administrator;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter";
    assertNotNull(managerProjectAreaVerificationPage);    
    assertEquals("PASSED", managerProjectAreaVerificationPage.verifyGetMemberRoleList(testList).getState());
    assertEquals("FAILED", managerProjectAreaVerificationPage.verifyGetMemberRoleList("").getState());
  }
  
  /**
   * Unit test covers for method {@link ManageProjectAreaPageVerification#verifyIsDisplayedMemberListWithRoles(String,String,String)}
   */
  @Test
  public void testVerifyIsDisplayedMemberListWithRoles() {
    loadPage("dng/isListMemberRoleCorrect.html");
    ManageProjectAreaPageVerification managerProjectAreaVerificationPage = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(managerProjectAreaVerificationPage);
    String nameList = "Abt Thomas (ED/PMO-CA);Bahr Markus (AE/EED5);Bauer Tamas (ED-TS/EAC4);Ben Ammar Nessim (AE/EED11);Bender Stefan (ED/EDI31);Eckert Klaus (AE/EED11);ERDEI ANDRAS (AE/EEC4);Fischer Frank-Sven (AE/ENS2);Fkaier Haithem (AE/EED11);Gommenginger Charles (AE/EED-P2);Gramberg Ralf (AE/EQM-ED);Grass Ansgar (AE/EED5);Grassi Emmanuel (ED-CA/ENS1);Hannasky Jonas (AE/EED2);Higel Martin (AE/EED11);Hondmann Christian (ED/EDA);Hriczu Adam (ED-TS/EAC4);Hug Timo (ED-TS/EAC1);Jerger Armin (AE/EED11);Juhasz Balint (AE/EEC4);Kalchschmidt Peter (PS/QMM-EM1);Knoerr Malte (AE/EED1);Kuderer Alexander (ED/ENS1);Kuperberg Ilya (AE/EED3);Ludwig Victor (ED/EDI31);Lurk Volker (ED-TS/EAC4);Malich Thomas (AE/EED2);Misenheimer Charles Wesley (AE/EEC4);Montes Camacho Jose Daniel (ED/EEF1-TS-Tl);Pozsar Csaba (AE/ECF-EV-SD);Puntus Andreas (AE/EED3);Rau Marco (ED-TS/EAC1);Rehagen Johannes (AE/EED11);Reiss Klaus (AE/EED2);Rosner Thomas (AE/EED4);Sauter Christian (EDA-WS/PJ-WD);Schaller Simon (AE/EED1);Schermann Michael (ED-TS/ENP2);Schmidt Zoltan (AE/EEC4);Schreiber Jacques (ED-TS/EAC3);Schroeder Patrick (ED/EVW-C ED/EVW2-P);Soellner Michael (AE/EED5);Stubenbord Linh-Thao (ED/EDS1);Vollmer Dirk (ED/EDI31);Wassmer Bruno (AE/EED4);Weickenmeier Klaus (ED/EDS1);Wendl Michael (AE/EMC2)";
    String roleList = ";Key User, Author, Commenter;Commenter, Author;;Author;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Project Responsible, Author, Commenter, Key User, Configuration Administrator;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;;Author, Commenter;Author, Commenter;;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter;Commenter, Author;;Author, Commenter, Configuration Administrator;Author, Commenter;Author, Commenter;Author, Commenter;Author, Commenter";
    assertEquals("PASSED", managerProjectAreaVerificationPage.verifyIsDisplayedMemberListWithRoles(nameList, roleList, "true").getState());    
    assertEquals("FAILED", managerProjectAreaVerificationPage.verifyIsDisplayedMemberListWithRoles(nameList, roleList ,"not true").getState());
  }
  /**
   * Unit test covers for method {@link ManageProjectAreaPageVerification#verifyEditPropertiesTimelineOrIteration(Map, String)}
   * 
   */
  @Test
  public void testVerifyEditPropertiesTimelineOrIteration() {
    loadPage("ccm/editProperties1.html");
    ManageProjectAreaPageVerification managerProjectAreaVerificationPage = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(managerProjectAreaVerificationPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();  
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String>param = new HashMap<String, String>();
    managerProjectAreaVerificationPage.verifyEditPropertiesTimelineOrIteration(param, "True");
    managerProjectAreaVerificationPage.verifyEditPropertiesTimelineOrIteration(param, "false");
  }
  
  /**
   * Unit test cover for method {@link ManageProjectAreaPageVerification#verifyCreateTimeLineOrIterations(Map, String)}
   */
  @Test
  public void testVerifyCreateTimeLineOrIterations() {
    loadPage("ccm/createTimeline0.html");
    ManageProjectAreaPageVerification mppv = getJazzPageFactory().getManageProjectAreaPageVerification();
    assertNotNull(mppv);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("BUTTON_OPTION","Create Timeline...");
    additionalParams.put("NAME","‪New Timeline1‬‎");
    mppv.verifyCreateTimeLineOrIterations(additionalParams, "true");
  }
}
