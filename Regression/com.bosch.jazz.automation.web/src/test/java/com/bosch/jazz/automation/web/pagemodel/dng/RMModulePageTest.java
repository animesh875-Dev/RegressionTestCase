/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.opencsv.exceptions.CsvValidationException;


/**
 * Unit test coverage for the methods of RMModulePage.
 */
public class RMModulePageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#openHistory()}.
   */
  @Test
  public void testOpenHistory() {
    loadPage("dng/openHistory.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    rm.openHistory();
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#logout()}.
   */
  @Test
  public void testLogout() {
    loadPage("dng/openHistory.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/logout.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.logout();
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#openModule(String, int)}.
   */
  @Test
  public void testOpenModule() {
    loadPage("dng/move_inside_module_id.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    Path url = Paths.get(SRC_TEST_RESOURCES + "dng/move_inside_module_id.html");
    assertNotNull(rm);
    rm.openModule(url.toUri().toString(), Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#getArtifactIDListFromModule()}.
   */
  @Test
  public void testGetArtifactIDListFromModule() {
    loadPage("dng/artifactids.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    assertTrue(rm.getArtifactIDListFromModule().size() > 0);
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#moreAction(String)}.
   */
  @Test
  public void testMoreAction() {
    loadPage("dng/moreAction.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/moreAction.html");
    clickNumberToPagePath.put(2, "dng/moreAction.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.moreAction("Show Full Hierarchy");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#moreAction(String)}.
   */
  @Test
  public void testMoreActionMenu() {
    loadPage("dng/moreActionMenu.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/moreActionMenu.html");
    clickNumberToPagePath.put(2, "dng/moreActionMenu.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.moreAction("Open History");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#getValuesOfHeaders(String, String, String, String)}.
   */
  @SuppressWarnings("deprecation")
  @Test
  public void testGetValuesOfHeaders() {
    loadPage("dng/createArtifact_ID.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/getValuesOfHeaders_Search.html");
    clickNumberToPagePath.put(2, "dng/configPageSettings_first.html");
    clickNumberToPagePath.put(3, "dng/configPageSettings.html");
    clickNumberToPagePath.put(4, "dng/configPageSettings_window.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    clickNumberToPagePath.put(9, "dng/moreActionMenu.html");
    clickNumberToPagePath.put(10, null);
    clickNumberToPagePath.put(11, null);
    clickNumberToPagePath.put(12, "dng/getValuesOfHeaders_Des.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    try {
      List<String> lst = rm.getValuesOfHeaders("Created On", "descending", "Show Full Hierarchy", "128946");
      List<Date> str = new ArrayList<Date>();
      for (String lt1 : lst) {
        str.add(new Date(lt1));
      }
      boolean b = DateUtil.isDatesAreinDescendingOrder(str);
      assertTrue(
          "The Grid is sorted by Created On and is  not shows Level 1 artifacts and working fine (or) The Grid is sorted by Created On but additional level artifacts are shown",
          b);
    }
    catch (Exception e) {
      e.getMessage();
    }

  }


  /**
   * <p>
   * Unit test to verify {@link RMModulePage#moveArtifactToFolder(String)}.
   */
  @Test
  public void testMoveArtifactToFolder() {
    loadPage("dng/moveArtifact_TextField.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/moveArtifact_DropMenu.html");
    clickNumberToPagePath.put(4, "dng/moveArtifact_MoveFolder.html");
    clickNumberToPagePath.put(5, "dng/moveArtifact_MoveFolder.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.moveArtifactToFolder("130470");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#getModuleID()}.
   */
  @Test
  public void testGetModuleID() {
    loadPage("dng/get_module_id.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    assertEquals("192512", rm.getModuleID());
  }
  /**
   * <p>
   * Unit test to verify {@link RMModulePage#chooseTabInsideModule(String)}.
   */
  @Test
  public void testChooseTabInsideModule() {
    loadPage("dng/choose_Tab_Inside-Module.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    rm.chooseTabInsideModule("Module");

  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#createModule(Map)}.
   */
  @Test
  public void testCreateModule() {
    loadPage("dng/rm_create_artifact.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/rm_create_module_2.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("FOLDER_NAME", "Module1 artifacts");
    additionalParams.put("MODULE_NAME", "test1");
    additionalParams.put("MODULE_TYPE", "SYS Signal (System Signal Artifact)");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createModule(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#editModuleName(String, String)}.
   */
  @Test
  public void testEditAModule() {
    loadPage("dng/test18.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.editModuleName("test1", "new Module (Automation)-1909101211");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#deleteModule(String, String)}.
   */
  @Test
  public void testDeleteAModule() {
    loadPage("dng/rm_delete_artifact.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/rm_click_delete_artifact.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/rm_after_delete_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteModule("Module1 artifacts", "new Module (Automation)-1909101211");
  }


  /**
   * <p>
   * Unit test to verify {@link RMModulePage#addArtifactToModule(String)}.
   */
  @Test
  public void testAddArtifactToModule() {
    loadPage("dng/Add_Artifact_To_Module_2.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addArtifactToModule("425074","Add");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#getArtifactUrl(String)}.
   *
   * @throws FileNotFoundException Exception
   */
  @Test
  public void testGetArtifactUrl() throws FileNotFoundException {
    String filePath = new File("src/test/resources/Excel_WebTest/exportmain.xlsx").getAbsolutePath();
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    System.out.println(rm.getArtifactUrl(filePath));
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#navigateToArtifactURL(String)}.
   */
  @Test
  public void testNavigateToArtifactURL() {
    loadPage("dng/Add_Artifact_To_Module_2.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.navigateToArtifactURL(driver.getCurrentUrl());
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#verifyExcelID(String, String)}.
   *
   * @throws FileNotFoundException throws Exception when file not found.
   */
  @Test
  public void testVerifyExcelID() throws FileNotFoundException {
    String filePath = new File("src/test/resources/Excel_WebTest/exportmain.xlsx").getAbsolutePath();
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    rm.verifyExcelID(filePath,
        "173095,173086,173098,173094,173083,173067,173107,173088,173072,173064,173092,173082,173093,173062,173065,173099,173089,173071,173084,173077,173105,173090,173069,173087,173068,173075,173109,173091,173085,173078,173081,173108,173101,173076,173080,173070,173100,173066,173097,173106,173104,173079,173063,173103,173096,173074,173073,173102");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#clickOnDropdownOption(String)}.
   */
  @Test
  public void testClickOnDropdownOption() {
    loadPage("dng/remove_link_if_exists_1.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnDropdownOption("Remove");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#verifyCSVid(String, String)}.
   */
  @Test
  public void testVerifyCSVid() {
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    rm.verifyCSVid(filePath, "211565");
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#getCSVArtifactURL(String)}.
   */
  @Test
  public void testGetCSVArtifactURL() {
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    rm.getCSVArtifactURL(filePath);
  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#removeArtifact(String)}.
   */
  @Test
  public void testRemoveArtifact() {
    loadPage("dng/rm_delete_artifact.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/remove_artifact_1.html");
    clickNumberToPagePath.put(3, "dng/remove_artifact_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    try {
      rm.removeArtifact("149409");
    }
    catch (Exception e) {
      assertNotNull(rm);
    }

  }

  /**
   * <p>
   * Unit test to verify {@link RMModulePage#openCreateContextMenuAndSelectSubmenu(String)}.
   */
  @Test
  public void testOpenCreateContextMenuAndSelectSubmenu() {
    loadPage("dng/rm_create_artifact.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/rm_select_artifact.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.openCreateContextMenuAndSelectSubmenu("Module1 artifacts");
  }


  /**
   * Duplicated artifact to folder
   */
  @Test
  public void testDuplicateArtifactToFolder() {
    loadPage("dng/duplicatedArtifact_01.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/duplicatedArtifact_02.html");
    clickNumberToPagePath.put(3, "dng/duplicatedArtifact_03.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);

    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("newName","Copy of TEST_AUTOMATION_MODULE");
    additionalParams.put("destinationFolder","Platform");
    additionalParams.put("isCheckCopyLinks","true");
    additionalParams.put("isCheckCopyTags","true");
    additionalParams.put("duplicationPolicy","Duplicate no artifacts (reuse all artifacts)");
    rm.duplicateArtifactToFolder(additionalParams);
  }


  /**
   * Count number Artifact showing in table
   */
  @Test
  public void testCountNumberArtifactShowing() {
    loadPage("dng/countNumberArtifactShowing_01.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.getNumberOfArtifactShowInTable();
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#searchArtifact(String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testSearchArtifact() {
    loadPage("dng/searchArtifact_01.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/searchArtifact_02.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/searchArtifact_03.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.searchArtifact("1020220", "true");
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#editArtifact_insertContent(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testEditArtifact_insertContent() {
    loadPage("dng/editArtifactInsertContent_02.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/editArtifactInsertContent_03.html");
    clickNumberToPagePath.put(4, "dng/editArtifactInsertContent_04.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "dng/editArtifactInsertContent_06.html");
    clickNumberToPagePath.put(8, "dng/editArtifactInsertContent_07.html");
    clickNumberToPagePath.put(9, "dng/editArtifactInsertContent_08.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.editArtifact_insertContent("1021402", "Artifact", "291238");
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#createANewArtifact(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testCreateANewArtifact() {
    loadPage("dng/createANewArtifact.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/createANewArtifact_01.html");
    clickNumberToPagePath.put(2, "dng/createANewArtifact_07.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "dng/createANewArtifact_08.html");
    Map<String, String> params = new HashMap<>();
    params.put("ARTIFACT_NAME", "Test artifact");
    params.put("ARTIFACT_TYPE", "Information (Information artifact)");
    params.put("ARTIFACT_FORMAT", "Text");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createANewArtifact(params);
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#insertNewArtifact(Map)}
   * <p>
   *
   * @author LTU7HC
   */
  @Test
  public void testInsertNewArtifact() {
    loadPage("dng/insertNewArtifact.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/insertNewArtifact_04.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> params = new HashMap<>();
    params.put("targetArtifactID", "1021396");
    params.put("typeOfInsertion", "After");
    params.put("artifactContent", "Test artifact");
    params.put("artifactType", "SW Term Heading");
    rm.insertNewArtifact(params);
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#lookUpTerm(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testLookUpTerm() {
    loadPage("dng/lookUpTerm.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/lookUpTerm_02.html");
    clickNumberToPagePath.put(2, null);
    //    clickNumberToPagePath.put(3, "dng/lookUpTerm_03.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.lookUpTerm("1020209", "Term_01", "1");
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#editArtifactContent(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testEditArtifactContent() {
    loadPage("dng/editArtifactContent.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> params = new HashMap<>();
    params.put("ARTIFACT_ID", "1020220");
    params.put("NEW_CONTENT", "Information (Information artifact)");
    params.put("EDIT_OPTION", "Replace new artifact content");
    rm.editArtifactContent(params);
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#deleteArtifact(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testDeleteArtifact() {
    loadPage("dng/deleteArtifactInModule1.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/deleteArtifactInModule3.html");
    clickNumberToPagePath.put(2, "dng/deleteArtifactInModule4.html");
    clickNumberToPagePath.put(3, "dng/deleteArtifactInModule5.html");
    clickNumberToPagePath.put(4, "dng/deleteArtifactInModule6.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteArtifact("2153731");
  }

  /**
   * <p>
   * Unit test for method ${@link RMModulePage#clickOnEditContentOfArtifact(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testClickOnEditContentOfArtifact() {
    loadPage("dng/searchArtifact_03.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/editArtifactContent.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnEditContentOfArtifact("1020220");
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#isLinkDisplayedInSelectedArtifactTab(String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testIsLinkDisplayedInSelectedArtifactTab() {
    loadPage("dng/isLinkDisplayedInSelectedArtifactTab.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    rm.isLinkDisplayedInSelectedArtifactTab("Satisfied By Architecture Element", "SysML: SystemInterface");
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#collapseArtifactSection(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testCollapseArtifactSection() {
    loadPage("dng/collapseArtifactSection.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();

    clickNumberToPagePath.put(1, "dng/expandArtifactSection.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    rm.collapseArtifactSection("1");
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#expandArtifactSection(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testExpandArtifactSection() {
    loadPage("dng/expandArtifactSection.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/collapseArtifactSection.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    rm.expandArtifactSection("1");
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#exportModuleWithPDFOrWordDocument(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testExportModuleWithPDFOrWordDocument() {
    loadPage("dng/exportModuleWithPDFDocument.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    Map<String, String> params = new HashMap<>();
    params.put("includeComments", "false");
    params.put("includeAttributes", "false");
    params.put("includeTitlesForInsertedArtifacts", "true");
    params.put("moduleLayout", "Book");
    params.put("exportOption", "Create and Print PDF Document...");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/exportModuleWithPDFDocument_01.html");
    clickNumberToPagePath.put(2, "dng/exportModuleWithPDFDocument_02.html");
    //    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(3, "dng/exportModuleWithPDFDocument_03.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    rm.exportModuleWithPDFOrWordDocument(params);
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#openLinkInTheNewTab(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testOpenLinkInTheNewTab() {
    loadPage("dng/open_the_link_in_new_tab.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    Map<String, String> params = new HashMap<>();
    params.put("linkType", "Validated By");
    params.put("link", "16754: TCS_250323_AT_Custom expression is not working properly in LQE");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    rm.openLinkInTheNewTab(params);
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#clickOnAuditHistory()}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testClickOnAuditHistory() {
    loadPage("dng/clickOnAuditHistory.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    rm.clickOnAuditHistory();
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#verifyFirstMessageInAuditHistory(String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyFirstMessageInAuditHistory() {
    loadPage("dng/verifyFirstMessageInAuditHistory.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    assertTrue(rm.verifyFirstMessageInAuditHistory("'saved with no changes", "false"));
    assertTrue(rm.verifyFirstMessageInAuditHistory("New / Changed", "true"));
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#verifyNumberOfArtifactDisplayedInModule(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyNumberOfArtifactDisplayedInModule() {
    loadPage("dng/verifyNumberOfArtifactDisplayedInModule.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    assertTrue(rm.verifyNumberOfArtifactDisplayedInModule("2879"));
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#verifyNumberOfArtifactDisplayedInModule(String)}
   * Special case for number of artifact - 2 layout overlapped
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyNumberOfArtifactDisplayedInModule01() {
    loadPage("dng/VerifyNumberOfArtifacts.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    assertTrue(rm.verifyNumberOfArtifactDisplayedInModule("2"));
  }
  
  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#verifyPercentageDisplayed(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyPercentageDisplayed() {
    loadPage("dng/test.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    boolean result = rm.verifyPercentageDisplayed("99%");
    assertTrue(result);
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#inputNameForArtifactContent(String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testInputNameForArtifactContent() {
    loadPage("dng/testInputNameForArtifactContent.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testInputNameForArtifactContent_01.html");
    rm.inputNameForArtifactContent("testing");
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#clickOnSearchOtherComponents}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testClickOnSearchOtherComponents() {
    loadPage("dng/testClickOnSearchOtherComponents.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testClickOnSearchOtherComponents_01.html");
    rm.clickOnSearchOtherComponents();
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#verifyNumberOfMatchesInTermDialog(String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyNumberOfTermMatches() {
    loadPage("dng/testVerifyNumberOfTermMatches.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    rm.verifyNumberOfMatchesInTermDialog("3");
    assertEquals(true, rm.verifyNumberOfMatchesInTermDialog("3"));
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#addTermOfOtherComponentHyperLinkandSave(String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testAddTermOfOtherComponentHyperLinkandSave() {
    loadPage("dng/testAddTermOfOtherComponentHyperLinkandSave.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testAddTermOfOtherComponentHyperLinkandSave_01.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addTermOfOtherComponentHyperLinkandSave("2");
  }

  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#navigateToTermHyperLink(String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testNavigateToTermHyperLink() {
    loadPage("dng/testNavigateToTermHyperLink.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    rm.navigateToTermHyperLink("611315","BRS");
  }


  /**
   * <p>
   * Unit test covers for method ${@link RMModulePage#removeLinkInsideModule(String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testRemoveLinkInsideModule() {
    loadPage("dng/testRemoveLinkInsideModule.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/testRemoveLinkInsideModule_02.html");
    clickNumberToPagePath.put(3, "dng/testRemoveLinkInsideModule_03.html");
    clickNumberToPagePath.put(4, "dng/testRemoveLinkInsideModule_04.html");
    clickNumberToPagePath.put(5, "dng/testRemoveLinkInsideModule_05.html");
    clickNumberToPagePath.put(6, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.removeLinkInsideModule("Component: DefaultComponent","Traced By Architecture Element ");
  }

  /**
   * Unit test covers for method ${@link RMModulePage#getModuleAttribute(String)}
   * @author NVV1HC
   */
  @Test
  public void testGetModuleAttribute() {
    loadPage("dng/getModuleAttribute.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    String result = rm.getModuleAttribute("Team Ownership");
    assertTrue(result.equalsIgnoreCase("ALM Test (RM)"));
  }

  /**
   * Unit test covers for method ${@link RMModulePage#editArtifactTypeHSIFlagDropDownInModule(String, String, String)}
   * @author PDU6HC
   */
  @Test
  public void testEditArtifactTypeHSIFlagDropDownInModule() {
    loadPage("dng/testEditArtifactTypeHSIFlagInModule.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testEditArtifactTypeHSIFlagInModule_01.html");
    clickNumberToPagePath.put(2, "dng/testEditArtifactTypeHSIFlagInModule_02.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/testEditArtifactTypeHSIFlagInModule_04.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.editArtifactTypeHSIFlagDropDownInModule("2194448", "AEn_ThermoSystems_HSI-FlagSavedToLeanComErrorStruct", "yes"));
  }
  
  /**
   * Unit test covers for method ${@link RMModulePage#navigateToModuleFromAuditHistory()}
   * @author PDU6HC
   */
  @Test
  public void testNavigateToModuleFromAuditHistory() {
    loadPage("dng/testNavigateToModuleFromAuditHistory.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.navigateToModuleFromAuditHistory();
  }
  
  /**
   * Unit test covers for method ${@link RMModulePage#editMaxLb(String, String)}
   * @author PDU6HC
   */
  @Test
  public void testEditMaxLb() {
    loadPage("dng/testEditMaxLb_01.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/testEditMaxLb_01.html");
    clickNumberToPagePath.put(3, "dng/testEditMaxLb_02.html");
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.editMaxLb("1921130", "26.5");
  }
  /**
   * Unit test covers for method ${@link RMModulePage#getSelectedArtifacts()}
   */
  @Test
  public void testGetSelectedArtifacts() {
    loadPage("dng/get_selected_artifacts.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.getSelectedArtifacts();
  }
  /**
   * Unit test covers for method ${@link RMModulePage#getNumberOfArtifactsFromExcelFile(String)}
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testGetNumberOfArtifactsFromExcelFile() throws IOException {
    loadPage("dng/get_selected_artifacts.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    rm.getNumberOfArtifactsFromExcelFile(filePath);
  }
  /**
   * Unit test covers for method ${@link RMModulePage#getURLOfLinkedArtifactFromExcelFile(String, String, String)}.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testGetURLOfLinkedArtifactFromExcelFile() throws IOException {
    loadPage("dng/get_selected_artifacts.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    rm.getURLOfLinkedArtifactFromExcelFile(filePath,"Link:Validated By", "211565");
  }
  /**
   * Unit test to verify ${@link RMModulePage#getNumberOfArtifactsFromCSVFile(String)}.
   *
   * @throws IOException is thrown if file is not found.
   * @throws CsvValidationException if single line is invalid
   */
  @Test
  public void testGetNumberOfArtifactsFromCSVFile() throws IOException, CsvValidationException {
    loadPage("dng/get_selected_artifacts.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    rm.getNumberOfArtifactsFromCSVFile(filePath);
  }
  /**
   * Unit test to verify ${@link RMModulePage#getURLOfLinkedArtifactFromCSVFile(String, String, String)}.
   * @throws IOException is thrown if file is not found.
   * @throws CsvValidationException if single line is invalid.
   */
  @Test
  public void testGetURLOfLinkedArtifactFromCSVFile() throws IOException, CsvValidationException {
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    rm.getURLOfLinkedArtifactFromCSVFile(filePath,"Link:Validated By", "211565");
  }
  /**
   * Unit test covers for method ${@link RMModulePage#editMaxLb(String, String)}
   * @author PDU6HC
   */
  @Test
  public void testVerifyArtifactNotInModuleUsingModuleGoToFind() {
    loadPage("dng/testArtifactNotInModuleGoToFind.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testArtifactNotInModuleGoToFind1.html");
    clickNumberToPagePath.put(2, "dng/testArtifactNotInModuleGoToFind2.html");
    clickNumberToPagePath.put(3, "dng/testArtifactNotInModuleGoToFind3.html");
    clickNumberToPagePath.put(4, "dng/testArtifactNotInModuleGoToFind.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.verifyArtifactNotInModuleUsingModuleGoToFind("123456");
  }

  /**
   * Unit test covers for method ${@link RMModulePage#verifyModuleStructureDisplayedCorrectly(String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyModuleStructureDisplayed() {
    loadPage("dng/verifyModuleStructureDisplayed.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    String text = "1:General;1.1:Document Purpose;1.2:Document Scope;2:Terms and Items;" +
        "2.1:History;2.2:References;2.3:Definitions;2.4:Abbreviations;2.5:Symbols;" +
        "3:Modes;3.1:Example;3.2:ECU Modes;3.3:Vehicle States;3.4:Drive Modes;" +
        "3.5:Accelerator pedal characteristic Modes;3.6:Recuperation Modes (Coasting, One Pedal Mode);" +
        "3.7:Creeping Modes;3.8:Torque distribution Modes;3.9:Steering Modes;" +
        "3.10:Chassis height Modes;3.11:Damping Modes;3.12:Battery cooling Modes;" +
        "3.13:HVAC Modes;3.14:Speed limit Modes;3.15:Valet Mode;3.16:ESC/TCS Modes;3.17:Operational Modes";
    assertTrue(rm.verifyModuleStructureDisplayedCorrectly(text));
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMModulePage#getArtifactIDByArtifactContent(String)}.
   */
    @Test
    public void testGetArtifactIDByArtifactContent() {
      loadPage("dng/getArtifactIDByArtifactName.html");
      RMModulePage rm = getJazzPageFactory().getRMModulePage();
      assertNotNull(rm);
      assertEquals("1714831", rm.getArtifactIDByArtifactContent("What is automation test ?"));
    }
    /**
     * <p>
     * Unit test to verify {@link RMModulePage#verifyInsertExistingArtifactStructureDisplayCorrectly(Map)}
     */
    @Test
    public void testVerifyInsertExistingArtifactStructureDisplayCorrectly() {
      loadPage("dng/verifyInsertExistingArtifactStructureDisplayCorrectly.html");
      RMModulePage rm = getJazzPageFactory().getRMModulePage();
      assertNotNull(rm);
      Map<String, String> additionalParams = new LinkedHashMap<>();
      additionalParams.put("TARGET_ARTIFACT_ID", "1714831");
      additionalParams.put("INSERTED_ARTIFACT_ID", "1714828");
      additionalParams.put("TYPE_OF_INSERTION", "Before...");
      rm.verifyInsertExistingArtifactStructureDisplayCorrectly(additionalParams);
      
    }
    /**
     * <p>
     * Unit test to verify {@link RMModulePage#insertExistingArtifact(Map)}.
     */
      @Test
      public void testInsertExistingArtifact() {
        loadPage("dng/insertExistingArtifact_1.html");
        RMModulePage rm = getJazzPageFactory().getRMModulePage();
        assertNotNull(rm);
        Map<Integer, String> clickNumberToPagePath = new HashMap<>();
        clickNumberToPagePath.put(1, "dng/insertExistingArtifact_3.html");
        clickNumberToPagePath.put(2,null);
        clickNumberToPagePath.put(3,null);
        clickNumberToPagePath.put(4, "dng/insertExistingArtifact_4.html");
        Map<String, String> addtionalParams = new HashMap<>();
        addtionalParams.put("EXISTING_ARTIFACT_ID", "1714828");
        addtionalParams.put("TYPE_OF_INSERTION", "Before...");
        addtionalParams.put("TARGET_ARTIFACT_ID", "1714831");
        loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
        rm.insertExistingArtifact(addtionalParams);
        
      }
      /**
       * <p>
       * Unit test for method ${@link RMModulePage#clickOnAddExistingArtifactOption}
       * <p>
       *
       * @author NCY3HC
       */
      @Test
      public void testClickOnAddExistingArtifactOption() {
        loadPage("dng/addExistingArtifact.html");
        RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
        assertNotNull(rm);
        rm.clickOnAddExistingArtifactOption();
      }
      /**
       * <p>
       * Unit test to verify {@link RMModulePage#pasteArtifact(Map)}.
       */
        @Test
        public void testPasteArtifact() {
          loadPage("dng/pasteArtifact.html");
          RMModulePage rm = getJazzPageFactory().getRMModulePage();
          assertNotNull(rm);
          Map<Integer, String> clickNumberToPagePath = new HashMap<>();
          clickNumberToPagePath.put(1, "dng/pasteArtifact1.html");
          clickNumberToPagePath.put(2,"dng/pasteArtifact3.html");

          Map<String, String> addtionalParams = new HashMap<>();
          addtionalParams.put("targetArtifactID", "2409481");
          addtionalParams.put("typeOfInsertion", "After");

          loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
          rm.pasteArtifact(addtionalParams);
          
        }
        /**
         * <p>
         * Unit test to verify {@link RMModulePage#uploadNewArtifact(Map)}.
         */
          @Test
          public void testUploadNewArtifact() {
            loadPage("dng/uploadArtifact.html");
            RMModulePage rm = getJazzPageFactory().getRMModulePage();
            assertNotNull(rm);
            Map<Integer, String> clickNumberToPagePath = new HashMap<>();
            clickNumberToPagePath.put(1, "dng/uploadArtifact1.html");
            clickNumberToPagePath.put(2,"dng/uploadArtifact2.html");

            Map<String, String> addtionalParams = new HashMap<>();
            addtionalParams.put("FILE_PATH", "testImage.png");
            addtionalParams.put("targetArtifactID", "2409500");
            addtionalParams.put("typeOfInsertion", "After");
            loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
            rm.pasteArtifact(addtionalParams);
            
          }   
          /**
           * <p>
           * Unit test to verify {@link RMModulePage#createAndOpenModule(Map)}.
           */
          @Test
          public void testCreateAndOpenModule() {
            loadPage("dng/createArtifact.html");
            RMModulePage rm = getJazzPageFactory().getRMModulePage();
            assertNotNull(rm);
            Map<Integer, String> clickNumberToPagePath = new HashMap<>();
            clickNumberToPagePath.put(1, null);
            clickNumberToPagePath.put(2, null);
            clickNumberToPagePath.put(3, null);
            clickNumberToPagePath.put(4, null);
            clickNumberToPagePath.put(5, "dng/createArtifact_ID.html");
            loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
            Map<String, String> additionalParams = new LinkedHashMap<String, String>();
            String name = "Test Module " + DateUtil.getCurrentDateAndTime();
            additionalParams.put("MODULE_NAME", name);
            additionalParams.put("MODULE_TYPE", "Assumption");
            additionalParams.put("MODULE_FORMAT", "Text");
            additionalParams.put("OPEN_ARTIFACT","true");
            rm.createAndOpenModule(additionalParams);
          }
          
          /**
           * <P>
           * Unit test to verify {@link RMModulePage#getViewNameApplied()}.
           */
          @Test
          public void testGetViewNameApplied() {
            RMModulePage rmModulePage = getJazzPageFactory().getRMModulePage();
            assertNotNull(rmModulePage);
            loadPage("dng/view_applied.html");            
            rmModulePage.getViewNameApplied();
            loadPage("dng/no_view_applied.html");
            rmModulePage.getViewNameApplied();
           }
          
          /**
           * <P>
           * Unit test to verify {@link RMModulePage#isViewApplied(String)}.
           */
          @Test
          public void testIsViewApplied() {
            RMModulePage rmModulePage = getJazzPageFactory().getRMModulePage();
            assertNotNull(rmModulePage);
            loadPage("dng/view_applied.html");            
            assertTrue(rmModulePage.isViewApplied("SW_SRS_briBk1_All_Default"));
            assertFalse(rmModulePage.isViewApplied("wrong_view"));
            loadPage("dng/no_view_applied.html");
            assertFalse(rmModulePage.isViewApplied("SW_SRS_briBk1_All_Default"));
          }
    /**
     * Unit test to verify {@link RMModulePage#navigateToModulePage(String)}.
     */
    @Test
     public void testNavigateToModulePage() {
         loadPage("dng/navigate_to_module_page.html");
         RMModulePage rm = getJazzPageFactory().getRMModulePage();
         assertNotNull(rm);
         rm.navigateToModulePage("test_module_Ts_25886");
       }
}


