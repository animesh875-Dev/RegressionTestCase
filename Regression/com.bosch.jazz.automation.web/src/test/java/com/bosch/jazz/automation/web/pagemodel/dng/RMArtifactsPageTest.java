/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;


/**
 * Unit test coverage for the methods of RMArtifactsPage.
 */
public class RMArtifactsPageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#loadArtifactsInRootFolder(String)}.
   */
  @Test
  public void testLoadArtifactsInProjectArea() {
    loadPage("dng/LoadArtifactFolder.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.loadArtifactsInRootFolder("ALM_System");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getSectionText(String)}.
   */
  @Test
  public void testGetSectionText() {
    loadPage("dng/get_section_text.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/single-requirement.html");
    clickNumberToPagePath.put(2, "dng/single-requirement-expanded.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.getSectionText("Comments");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#createArtifact(Map)}.
   */
  @Test
  public void testCreateArtifact() {
    loadPage("dng/createArtifact.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/createArtifact_ID.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    String name = "Test Artifact " + DateUtil.getCurrentDateAndTime();
    additionalParams.put("ARTIFACT_NAME", name);
    additionalParams.put("ARTIFACT_TYPE", "Assumption");
    additionalParams.put("ARTIFACT_FORMAT", "Text");
    rm.createArtifact(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#addArtifactAttributeInToArtifactTable(String)}.
   */
  @Test
  public void testAddArtifactAttributeInToArtifactTable() {
    loadPage("dng/configPageSettings_first.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/configPageSettings.html");
    clickNumberToPagePath.put(2, "dng/configPageSettings_window.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addArtifactAttributeInToArtifactTable("Validated By");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#editArtifact(Map)}.
   */
  @Test
  public void testEditArtifact() {
    loadPage("dng/editArtifact_Search.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/editArtifact_Editbutton.html");
    clickNumberToPagePath.put(3, "dng/editArtifact_Section.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/editArtifact_Commentwindow.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "dng/editArtifact_CommentUser.html");
    clickNumberToPagePath.put(8, null);
    clickNumberToPagePath.put(9, null);
    clickNumberToPagePath.put(10, "dng/editArtifact_Commentwindow.html");
    clickNumberToPagePath.put(11, null);
    //clickNumberToPagePath.put(11, "dng/editArtifact_Section.html");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Select Type Section", "Comments");
    additionalParams.put("Select Item", "Comments");
    additionalParams.put("Sub Item Selection", "Create comment for Artifact...");
    additionalParams.put("User ID", "KDO1KOR");
    additionalParams.put("Artifact ID", "130470");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.editArtifact(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#editArtifact(Map)}.
   */
  @Test
  public void testEditArtifactOverView() {
    loadPage("dng/editArtifact_Search.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/editArtifact_Editbutton.html");
    clickNumberToPagePath.put(3, "dng/editArtifact_Section.html");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Artifact ID", "130470");
    additionalParams.put("Select Type Section", "Overview");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.editArtifact(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#deleteArtifact(String)}.
   */
  @Test
  public void testDeleteArtifact() {
    loadPage("dng/test_edit_artifact_attribute_artifact_all_page_1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/deleteArtifact_window.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    try {
      rm.deleteArtifact("153162");
    }
    catch (Exception e) {
      assertNotNull(rm);
    }
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#exportArtifact(String, String)}.
   */
  @Test
  public void testExportArtifact() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/exportArtifact_Export.html");
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.exportArtifact("148476", "CSV");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#pasteSpecial()}.
   */
  @Test
  public void testPasteSpecial() {
    loadPage("dng/paste_special_show.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/paste_special_checkbox_show.html");
    clickNumberToPagePath.put(2, "dng/paste_special_checkbox_hidden.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.pasteSpecial());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#pasteSpecial()}.
   */
  @Test
  public void testPasteSpecialElseCond() {
    loadPage("dng/paste_special_show.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/paste_special_checkbox_show.html");
    clickNumberToPagePath.put(2, "dng/paste_special_enable.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertFalse(rm.pasteSpecial());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#pasteSpecial()}.
   */
  @Test
  public void testPasteSpecialElseifCond() {
    loadPage("dng/paste_special_enable.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/paste_special_enable.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertFalse(rm.pasteSpecial());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#pasteSpecial()}.
   */
  @Test
  public void testPasteSpecialTwo() {
    loadPage("dng/paste_special_checkbox_show.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/paste_special_select_the_link_type.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.pasteSpecial());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#pasteSpecial()}.
   */
  @Test
  public void testPasteSpecialElseifConditions() {
    loadPage("dng/paste_special_checkbox_show.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/paste_special_dropdown_link.html");
    clickNumberToPagePath.put(2, "dng/paste_special_enable.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.pasteSpecial());
  }


  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#changeColumnDisplaySettings(String)}.
   */
  @Test
  public void testChangeColumnDisplaySettings() {
    loadPage("dng/change_column_display_settings.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/change_column_display_settings_window.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.changeColumnDisplaySettings("Validated By");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)}.
   */
  @Test
  public void testOpenContextMenuForSelectedArtifact() {
    loadPage("dng/opencontest_menufor_selected_artifact_00.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/opencontest_menufor_selected_artifact.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.openContextMenuForSelectedArtifact("1188105", "Generate Report for Artifact...");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#editRMAttributesFromOverviewSection(Map)}.
   */
  @Test
  public void testEditRMAttributesFromOverviewSection() {
    loadPage("dng/edit_artifact_attributes_editbutton.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/edit_artifact_attributes_savebutton.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.TYPE.toString(), "SW Fbl Information");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.editRMAttributesFromOverviewSection(additionalParams);
  }

  /**
   * <P>
   * Unit test to verify {@link RMArtifactsPage#editArtifactAttributes(Map)}.
   */
  @Test(expected = Exception.class)
  public void testEditArtifactAttributes() {
    loadPage("dng/test_edit_artifact_attribute_artifact_all_page_1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);

    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    clickNumberToPagePath.put(1, null);

    additionalParams.put("ARTIFACTS_TYPE", "Heading");
    additionalParams.put("ATTRIBUTE_NAME", "testing for junit");
    additionalParams.put("ARTIFACT_ID", "153162");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Heading", rm.editArtifactAttributes(additionalParams));
  }

  /**
   * <P>
   * Unit test to verify {@link RMArtifactsPage#editArtifactAttributes(Map)}.
   */
  @Test
  public void testEditArtifactAttributeElseCond() {
    loadPage("dng/edit_artifact_attributes_textfield.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/edit_artifact_attributes.window.html");
    clickNumberToPagePath.put(2, "dng/test_edit_artifact_attribute_artifact_all_page.html");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ARTIFACTS_TYPE", "Requirement");
    additionalParams.put("ATTRIBUTE_NAME", "Test Module 23_04_2019_13_04_358");
    additionalParams.put("ARTIFACT_ID", rm.getArtifactID());
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNull(rm.editArtifactAttributes(additionalParams));
  }

  /**
   * C:\BBMALMWorkspace\com.bosch.jazz.automation.web\src\test\resources\dng\migiz_package_1.migiz Loads requirement
   * management compare configuration page, checks if it returns ture then compare properties pages is visible/enable
   */
  @Test
  public void testCompareConfigurations() {
    loadPage("dng/compare_configurations.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/compare_configurations_folders.html");
    clickNumberToPagePath.put(2, "dng/compare_configurations_close_button.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.compareConfigurations());
  }

  /**
   * Loads requirement management compare configuration page, checks if it returns false then compare properties pages
   * is not visible/enable
   */
  @Test
  public void testCompareConfigurationsIsNotEnable() {
    loadPage("dng/edit_artifact_attributes.clear_button.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertFalse(rm.compareConfigurations());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#addArtifactAttributeInToArtifactTable(String)}.
   */
  @Test
  public void testAddArtifactAttributeInToArtifactTableExistColumn() {
    loadPage("dng/configPageSettings_first.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/configPageSettings.html");
    clickNumberToPagePath.put(2, "dng/configPageSettings_exist_column.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    try {
      rm.addArtifactAttributeInToArtifactTable("Validated By ");
    }
    catch (Exception e) {
      e.getMessage();
    }

  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#copyFromAComponent(Map)}.
   */
  @Test
  public void testCopyFromAComponent() {
    loadPage("dng/copy_from_component.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("FOLDER_NAME", "Software Requirements");
    additionalParams.put("DROP_DOWN_MENU", "Baselines");
    additionalParams.put("COMPONENT_NAME", "Com_for _test");
    additionalParams.put("SELECT_ARTIFACT_TYPE", "Add Artifact");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/copy_from_component_config.html");
    clickNumberToPagePath.put(2, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(3, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(4, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(5, "dng/copy_from_component_comp.html");
    clickNumberToPagePath.put(6, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(9, "dng/copy_from_component_config.html");
    clickNumberToPagePath.put(10, "dng/copy_from_component_artifacts_window.html");
    clickNumberToPagePath.put(11, "dng/copy_from_component_artifacts_window.html");
    clickNumberToPagePath.put(12, null);
    clickNumberToPagePath.put(13, null);
    clickNumberToPagePath.put(14, "dng/copy_from_component_finish.html");
    clickNumberToPagePath.put(15, "dng/copy_from_component_msg.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.copyFromAComponent(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#copyFromAComponent(Map)}.
   */
  @Test
  public void testCopyFromAComponentNotCopied() {
    loadPage("dng/copy_from_component.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("FOLDER_NAME", "Software Requirements");
    additionalParams.put("DROP_DOWN_MENU", "Baselines");
    additionalParams.put("COMPONENT_NAME", "Com_for _test");
    additionalParams.put("SELECT_ARTIFACT_TYPE", "Add Artifact");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/copy_from_component_config.html");
    clickNumberToPagePath.put(2, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(3, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(4, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(5, "dng/copy_from_component_comp.html");
    clickNumberToPagePath.put(6, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, "dng/copy_from_component_window.html");
    clickNumberToPagePath.put(9, "dng/copy_from_component_config.html");
    clickNumberToPagePath.put(10, "dng/copy_from_component_artifacts_window.html");
    clickNumberToPagePath.put(11, "dng/copy_from_component_artifacts_window.html");
    clickNumberToPagePath.put(12, null);
    clickNumberToPagePath.put(13, null);
    clickNumberToPagePath.put(14, "dng/copy_from_component_finish.html");
    clickNumberToPagePath.put(15, "dng/copy_from_component_warning.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertFalse(rm.copyFromAComponent(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#exportCSVfile(Map)}.
   */
  @Test
  public void testExportCSVFile() {
    loadPage("dng/addArtifactTest5.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/export_csvfile_window.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    additionalParams.put("ARTIFACT_TYPE", "ARTIFACTS");
    additionalParams.put("EXPORT_ARTIFACTS", "132242");
    assertTrue(rm.exportCSVfile(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#exportCSVfile(Map)}.
   */
  @Test
  public void testExportCSVfileNotExport() {
    loadPage("dng/addArtifactTest5.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/export_csvfile_cancel.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    additionalParams.put("ARTIFACT_TYPE", "ARTIFACTS");
    additionalParams.put("EXPORT_ARTIFACTS", "132242");
    assertFalse(rm.exportCSVfile(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isArtifactAttributeAvailable(String)}.
   */
  @Test
  public void testIsArtifactAttributeAvailable() {
    loadPage("dng/check_for_artifact_attributes.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    assertTrue(rm.isArtifactAttributeAvailable("ForeignCreatedBy"));
  }


  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#exportCSVfile(Map)}.
   */
  @Test
  public void testExportCSVFileModules() {
    loadPage("dng/addArtifactModule1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/export_csvfile_window.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    additionalParams.put("ARTIFACT_TYPE", "MODULES");
    additionalParams.put("EXPORT_MODULE", "132243");
    assertTrue(rm.exportCSVfile(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#exportCSVfile(Map)}.
   */
  @Test
  public void testExportCSVFileCollections() {
    loadPage("dng/addArtifactModule1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/export_csvfile_window.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    additionalParams.put("ARTIFACT_TYPE", "COLLECTIONS");
    additionalParams.put("EXPORT_COLLECTION", "132243");
    assertTrue(rm.exportCSVfile(additionalParams));
  }

  /**
   * Loads an requirement artifact page ,checks if it returns false means child artifact not exist below partent
   * artifact *
   */
  @Test
  public void testSearchWordInCollapsedArtifact() {
    loadPage("dng/search_wordIn_collapsed_artifact.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/search_wordIn_collapsed_artifact.html");
    clickNumberToPagePath.put(2, "dng/search_wordIn_collapsed_artifact.html");
    clickNumberToPagePath.put(3, "dng/search_wordIn_collapsed_artifact.html");
    clickNumberToPagePath.put(4, "dng/search_wordIn_collapsed_artifact.html");
    clickNumberToPagePath.put(5, "dng/search_wordIn_collapsed_artifact.html");
    clickNumberToPagePath.put(6, "dng/search_wordIn_collapsed_artifact.html");
    clickNumberToPagePath.put(7, "dng/search_wordIn_collapsed_artifact.html");
    clickNumberToPagePath.put(5, "dng/search_wordIn_collapsed_artifact_goto.html");
    clickNumberToPagePath.put(6, "dng/search_wordIn_collapsed_artifact_goto.html");
    clickNumberToPagePath.put(7, "dng/search_wordIn_collapsed_artifact_goto.html");
    clickNumberToPagePath.put(8, "dng/search_wordIn_collapsed_artifact_goto.html");
    clickNumberToPagePath.put(9, "dng/search_wordIn_collapsed_artifact_goto.html");
    clickNumberToPagePath.put(10, "dng/search_wordIn_collapsed_artifact_goto.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    assertFalse(rm.searchWordInCollapsedArtifact());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#exportReqIF(String, String, String)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testExportReqIF() throws Throwable {
    loadPage("dng/export_reqif.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/export_reqif.html");
    clickNumberToPagePath.put(2, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(3, "dng/export_reqif_drop_down.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/export_reqif_download.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.exportReqIF("Test sample", "Test", "Add Artifact");
  }


  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#openComponentFromAllProjectsAreaPage(String)}.
   */
  @Test
  public void testOpenComponentFromAllProjectsAreaPage() {
    loadPage("dng/move_to_stream.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/move_to_stream_component.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.openComponentFromAllProjectsAreaPage("SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importArtifactTypes(Map)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactTypes() throws Throwable {
    loadPage("dng/import_artifact_text_docu.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_artifact_dropdownList.html");
    clickNumberToPagePath.put(2, "dng/import_artifact_text_dropdowns.html");
    clickNumberToPagePath.put(3, "dng/import_artifact_text_finish.html");
    clickNumberToPagePath.put(4, "dng/import_artifact_text_msg.html");
    String file = new File("src\\test\\resources\\dng\\Test.docx").getAbsolutePath();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORT_TYPE", "Import requirements from within a text document");
    additionalParams.put("FILE_PATH", file);
    additionalParams.put("SELECT_REQUIREMENT_TYPE", "Import Artifact...");
    additionalParams.put("HEADING_DROP_DOWN_VALUE", "CRS");
    additionalParams.put("IMAGE_DROP_DOWN_VALUE", "CRS");
    additionalParams.put("OTHER_TEXT_DROP_DOWN_VALUE", "CRS");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Import requirements from within a text document :: Success!",
        rm.importArtifactTypes(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importArtifactTypes(Map)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactTypesCsv() throws Throwable {
    loadPage("dng/import_artifact_csv.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_artifact_csv_msg.html");
    String file = new File("src\\test\\resources\\dng\\export.csv").getAbsolutePath();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORT_TYPE", "Import requirements from a CSV file or spreadsheet");
    additionalParams.put("FILE_PATH", file);
    additionalParams.put("SELECT_REQUIREMENT_TYPE", "Import Artifact...");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.importArtifactTypes(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importArtifactTypes(Map)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactTypesReqIF() throws Throwable {
    loadPage("dng/import_artifact_reqif_upload.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_artifact_reqif_all_attributes.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/import_artifact_reqif_msg.html");
    String file = new File("src\\test\\resources\\dng\\Test ReqIf export.reqifz").getAbsolutePath();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORT_TYPE", "Import requirements from a ReqIF file");
    additionalParams.put("FILE_PATH", file);
    additionalParams.put("SELECT_REQUIREMENT_TYPE", "Import Artifact...");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Import requirements from a ReqIF file :: The import is complete. Show Report",
        rm.importArtifactTypes(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importArtifactTypes(Map)}. <br>
   * Imports the "Import requirements from a ReqIF file in this component or
   * project"C:\BBMALMWorkspace\com.bosch.jazz.automation.web\src\test\resources\dng\Test.docx
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactTypesReqIFTwo() throws Throwable {
    loadPage("dng/import_artifact_par_reqif.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_artifact_par_reqif.html");
    clickNumberToPagePath.put(2, "dng/import_artifact_par_reqif.html");
    clickNumberToPagePath.put(3, "dng/import_artifact_reqif_msg.html");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORT_TYPE", "Import requirements from a ReqIF file in this component or project");
    additionalParams.put("FILE_PATH", "");
    additionalParams.put("SELECT_REQUIREMENT_TYPE", "Import Artifact...");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals(
        "Import requirements from a ReqIF file in this component or project :: The import is complete. Show Report",
        rm.importArtifactTypes(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importArtifactTypes(Map)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactTypesMigration() throws Throwable {
    loadPage("dng/import_artifact_migration_upload.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_artifact_migration_next.html");
    clickNumberToPagePath.put(2, "dng/import_artifact_migration_next.html");
    clickNumberToPagePath.put(3, "dng/import_artifact_migration_msg.html");
    String file = new File("src\\test\\resources\\dng\\migiz_package_1.migiz").getAbsolutePath();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORT_TYPE", "Import requirements from a migration package");
    additionalParams.put("FILE_PATH", file);
    additionalParams.put("SELECT_REQUIREMENT_TYPE", "Import Artifact...");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Import requirements from a migration package :: The import is complete. Show Report",
        rm.importArtifactTypes(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importArtifactTypes(Map)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactTypesTextArtifact() throws Throwable {
    loadPage("dng/import_artifact_text_artifact_choose.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_artifact_text_artifact_msg.html");
    String file = new File("src\\test\\resources\\dng\\Gc show history View.docx").getAbsolutePath();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORT_TYPE", "Import a text document and convert to a rich text artifact");
    additionalParams.put("FILE_PATH", file);
    additionalParams.put("SELECT_REQUIREMENT_TYPE", "Import Artifact...");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals(
        "Import a text document and convert to a rich text artifact :: The document was converted.ID CRRRW7575I  This message is for informational purposes only.",
        rm.importArtifactTypes(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importArtifactTypes(Map)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactError() throws Throwable {
    loadPage("dng/import_artifact_error.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_artifact_error.html");
    String file = new File("src\\test\\resources\\dng\\Test.docx").getAbsolutePath();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORT_TYPE", "Import requirements from within a text document");
    additionalParams.put("FILE_PATH", file);
    additionalParams.put("SELECT_REQUIREMENT_TYPE", "Import Artifact...");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.importArtifactTypes(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#setClipboardData(String)}.
   */
  @Test
  public void testSetClipboardData() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    String file = new File("src\\test\\resources\\dng\\Test.docx").getAbsolutePath();
    rm.setClipboardData(file);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#uploadFile(String)}
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testUploadFile() throws Throwable {
    loadPage("dng/import_artifact_error.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    String file = new File("src\\test\\resources\\dng\\Test.docx").getAbsolutePath();
    rm.uploadFile(file);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#assignTeamOwnership(String, String)}.
   */
  @Test
  public void testAssignTeamOwnership() {
    loadPage("dng/assign_team_wonership.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/assign_team_wonership_team.html");
    clickNumberToPagePath.put(2, "dng/assign_team_wonership_team.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.assignTeamOwnership("module1 artifacts", "Team A");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#renameStream(String, String)}.
   */
  @Test
  public void testRenameStream() {
    loadPage("dng/modifystream_page1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/modifystream_page1.html");
    clickNumberToPagePath.put(2, "dng/modifystream_page2.html");
    clickNumberToPagePath.put(3, "dng/modifystream_page3.html");
    clickNumberToPagePath.put(4, "dng/modifystream_page4.html");
    clickNumberToPagePath.put(5, "dng/modifystream_page4.html");
    clickNumberToPagePath.put(6, "dng/modifystream_page5.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.renameStream("SampleComponent Initial Stream", "Test_Stream_");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnArtifact(String)}.
   */
  @Test
  public void testClickOnArtifact() {
    loadPage("dng/selectArtifactId.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/selectedArtifactId.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnArtifact("154680");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectArtifact(String)}.
   */
  @Test
  public void testSelectArtifact() {
    loadPage("dng/selectMulitple.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectArtifact("156582");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectArtifact(String)}.
   */
  @Test
  public void testDeSelectArtifact() {
    loadPage("dng/selectMulitple.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deSelectArtifact("156582");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#openRMSpecification(String)}.
   */
  @Test
  public void testOpenRMSpecification() {
    loadPage("dng/OpenRMSpecifications.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.openRMSpecification("129494");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getRMAttributeValue(String)}.
   */
  @Test
  public void testGetRMAttributeValue() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Assert.assertEquals("Apr 8, 2019, 10:32:25 AM", rm.getRMAttributeValue("Created On"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getRMSpecificationsSummary()}.
   */
  @Test
  public void testGetRMSepecificationsSummary() {
    loadPage("dng/getRMSepecificationsSummary.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Assert.assertEquals("Copy of KGModule", rm.getRMSpecificationsSummary());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isSeachResultsNotFound()}.
   */
  @Test
  public void testIsSeachResultsNotFound() {
    loadPage("dng/Serarch_results_invalid.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Assert.assertTrue(rm.isSeachResultsNotFound());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.
   */
  @Test
  public void testFilterArtifactByTextOrId() {
    loadPage("dng/FilterArtifactByTextOrId.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.filterArtifactByTextOrId("1092374");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#addFilter()}.
   */
  @Test
  public void testAddFilter() {
    loadPage("dng/addFilter_newfilter.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.addFilter();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#expandFilters()}.
   */
  @Test
  public void testExpandFilters() {
    loadPage("dng/artifacts_expandFilters.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.expandFilters();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clearFilter()}.
   */
  @Test
  public void testClearFilter() {
    loadPage("dng/artifacts_clearFilter.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clearFilter();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#searchAttributeInFilter(String)}.
   */
  @Test
  public void testSearchAttributeTypeInFilter() {
    loadPage("dng/addFilter_newfilter.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.searchAttributeInFilter("Artifact Type");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#chooseAttributeValueForSelectedAttribute(String, String)}.
   */
  @Test
  public void testChooseAttributeValueForSelectedAttribute() {
    loadPage("dng/addFilter_newfilter.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.chooseAttributeValueForSelectedAttribute("Artifact Type", "Heading");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnSaveAsNewView()}.
   */
  @Test
  public void testClickOnSaveAsNewView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clickOnSaveAsNewView();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#setViewName(String)}.
   */
  @Test
  public void testSetViewName() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.setViewName("Test View_");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getViewName()}.
   */
  @Test
  public void testGetViewName() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.setViewName("Test View");
    Assert.assertTrue(rm.getViewName().contains("Test View"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#setViewType(String)}.
   */
  @Test
  public void testSetViewType() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.setViewType("Personal");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#setWhereToShowThisView(String)}.
   */
  @Test
  public void testSetWhereToShowThisView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.setWhereToShowThisView("Just this module");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#makePreferredView()}.
   */
  @Test
  public void testMakePreferredView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.makePreferredView();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#addDescriptionInView(String)}.
   */
  @Test
  public void testAddDescriptionInView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.addDescriptionInView("This is description of view");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#deleteView(String)}.
   */
  @Test
  public void testDeleteView() {
    loadPage("dng/view_deleteview.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/deleteView_popup.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteView("Test_Personal_View");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getColumnDatafromTable(String)}.
   */
  @Test
  public void testGetColumnDatafromTable() {
    loadPage("dng/view_getListOfContentForEachColumn.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    String res = rm.getColumnDatafromTable("Artifact Type");
    Assert.assertTrue(res.contains("Hardware Requirement"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#chooseRole(String)}.
   */
  @Test
  public void testChooseRole() {
    loadPage("dng/new_sharedview_chooserole.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.chooseRole("Author");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}.
   */
  @Test
  public void testSelectValueFromViewDropdown() {
    loadPage("dng/select_value_from_view_dropdown.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.selectOptionFromViewOptionsDropdown("test", "Edit Attributes from View...");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnEditAttributeCheckbox(String)}.
   */
  @Test
  public void testClickOnEditAttributeCheckbox() {
    loadPage("dng/add_dialog_values.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clickOnEditAttributeCheckbox("Description");
  }


  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#setAttributeValue(String, String)}.
   */
  @Test
  public void testSetAttributeValue() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.setAttributeValue("Description", "test");
  }


  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectView(String)}.
   */
  @Test
  public void testSelectView() {
    loadPage("dng/select_View.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.selectView("View_For_Automation_Testing");
  }

  /**
   *
   */
  @Test
  public void testGetAllArtifactIdFromSelectedView() {
    loadPage("dng/select_View.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getAllArtifactIdFromSelectedView();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectView(String)}.
   */
  @Test
  public void testSelectViewOne() {
    loadPage("dng/select_View.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.selectView("View_Emb");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectTags(String)}.
   */
  @Test
  public void testClickonSelectTagText() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.selectTags("testing");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#editArtifactAttribute(String, String)}.
   */
  @Test
  public void testEditArtifactAttribute() {
    loadPage("dng/rm_test_editattribute_artifact1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rm);
    rm.editArtifactAttribute("Description", "test");
  }


  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#verifyArtifactValuesInModuleView(String, String, String)}.
   */
  @Test
  public void testVerifyArtifactValuesInModuleView() {
    loadPage("dng/selectMulitple.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.verifyArtifactValuesInModuleView("155555", "Artifact Type", "Storyboard");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#uncheckAttributeInEditAttibutesDialog(String)}.
   */
  @Test
  public void testUncheckAttributeInEditAttibutesDialog() {
    loadPage("dng/uncheck_Attribute_In_Edit_Attibutes_Dialog.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.uncheckAttributeInEditAttibutesDialog("Artifact Type");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isArtifactDisplayed(String)}.
   */
  @Test
  public void testIsArtifactDisplayed() {
    loadPage("dng/is_Artifact_Displayed.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertTrue(rm.isArtifactDisplayed("425711"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#inputArtifactContent(String)}.
   */
  @Test
  public void testInputArtifactContent() {
    loadPage("dng/input_Artifact_Content.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.inputArtifactContent("Overview");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isApplyButtonEnabled()}.
   */
  @Test
  public void testIsApplyButtonEnabled() {
    loadPage("dng/testRqmenabled.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertTrue(rm.isApplyButtonEnabled());
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isApplyButtonEnabled()}.
   */
  @Test
  public void testIsApplyButtonEnabledOne() {
    loadPage("dng/testRqmApplybuttonIsEnabled.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertFalse(rm.isApplyButtonEnabled());
  }

  /**
   * <P>
   * Unit test to verify {@link RMArtifactsPage#importArtifactsWithMigizFileExtension(Map)}
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportArtifactsWithMigizFileExtension() throws Throwable {
    loadPage("dng/import_migration_migiz.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_migration_migiz_1.html");
    clickNumberToPagePath.put(2, "dng/import_migration_migiz_2.html");
    clickNumberToPagePath.put(3, "dng/import_migration_migiz_3.html");
    clickNumberToPagePath.put(4, "dng/import_migration_migiz_4.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/import_migration_migiz_5.html");
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, null);
    clickNumberToPagePath.put(9, "dng/import_migration_migiz_6.html");
    String file = "migiz_package_1.migiz";
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("FILE_PATH", file);
    additionalParams.put("IMPORTED_FOLDER", "Base Artifacts");
    additionalParams.put("ARTIFACT_ATTRIBUTE", "/Migiz Migration/Migration Module - Object");
    additionalParams.put("MODULE_ATTRIBUTE", "/Migiz Migration/Migration Module - Module");
    // additionalParams.put("migrationFolder", "Migration Module artifacts");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Import requirements from a migration package :: The import is complete. Show Report",
        rm.importArtifactsWithMigizFileExtension(additionalParams));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#deleteArtifactFolder(String, String)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testDeleteArtifactFolder() throws Throwable {
    loadPage("dng/delete_artifact_folder.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/delete_artifact_folder_2.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/delete_artifact_folder_3.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
//    rm.deleteArtifactFolder("TestABC", "FALSE");
    rm.deleteArtifactFolder("Module", "FALSE");
  }

  /**
   * Unit test selectMoreActionsForAttribute method.
   */
  @Test
  public void tesGetRMAttributeTagValue() {
    loadPage("dng/getTagValue.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getRMAttributeTagValue("213133");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnImageButton(String)}
   */
  @Test
  public void testClickOnImageButton() {
    loadPage("dng/clickOnImage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clickOnImageButton("Save changes to this view");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#manageTags(String, String, String)}.
   */
  @Test
  public void testManageTags() {
    loadPage("dng/manageTag2.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "dng/manageTag3.html");
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rm);
    rm.manageTags("DefaultTag", "Delete Tag", "Delete tag");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#deleteArtifactFromSearchedSpecification(String,String)}.
   */
  @Test
  public void testDeleteArtifactFromSearchedSpecification() {
    loadPage("dng/delete_artifact_from_searched_specification.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.deleteArtifactFromSearchedSpecification("1110078", "false");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importRequirementsFromAMigrationPackageWithError(String)}.
   *
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportRequirementsFromAMigrationPackageWithError() throws Throwable {
    loadPage("dng/import_migration_migiz_error.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_migration_migiz_error_1.html");
    clickNumberToPagePath.put(2, "dng/import_migration_migiz_error_2.html");
    clickNumberToPagePath.put(3, null);
    String fileName = "Artifact_Excel1.xlsx";
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.importRequirementsFromAMigrationPackageWithError(fileName));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#importRequirementsFromAMigrationPackageWithError(String)}.
   *
   * @author KYY1HC
   * @throws Throwable use to handle any kind of exception
   */
  @Test
  public void testImportRequirementsFromACSVFileOrSpreadsheetIntoAModule() throws Throwable {
    loadPage("dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule1.html");
    clickNumberToPagePath.put(2, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule2.html");
    clickNumberToPagePath.put(3, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule3.html");
    clickNumberToPagePath.put(4, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule4.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule5.html");
    clickNumberToPagePath.put(7, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule8.html");
    clickNumberToPagePath.put(8, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule9.html");
    clickNumberToPagePath.put(9, "dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule10.html");
    String fileName = "export_artifact_TS_25902.xlsx";
    String moduleId = "643387";
    String moduleName = "briBk1 Fbl SW RS";
    String importOption = "Update artifacts that match entries, and ignore new entries";
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.importRequirementsFromACSVFileOrSpreadsheetIntoAModule(fileName, moduleId, moduleName, importOption));
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectAvailableReportType(String)}.
   */
  @Test
  public void testSelectAvailableReportType() {
    loadPage("dng/selectAvailableReportType1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rm);
    rm.selectAvailableReportType("Traceability Report");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#addArtifactsInTraceabilityReport(String, String, String)}.
   */
  @Test
  public void testAddArtifactsInTraceabilityReport() {
    loadPage("dng/addAttributeToreport1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/addAttributeToreport4.html");
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, null);
    clickToPage.put(6, null);
    clickToPage.put(7, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rm);
    rm.addArtifactsInTraceabilityReport("Add...", "1110099", "Module created to automate pdf report");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getValidationMessageFromReportGenerationMessageArea(String)}.
   */
  @Test
  public void testgetValidationMessageFromReportGenerationMessageArea() {
    loadPage("dng/reportCompleteMessage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getValidationMessageFromReportGenerationMessageArea("Report generation complete");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isDataAvailableInPdfFile(String, String)}.
   *
   * @throws IOException throws {@link Exception}
   */
  @Test
  public void testIsDataAvailableInPdfFile() throws IOException {
    loadPage("dng/reportCompleteMessage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    new File("src/test/resources/dng/Artifsct_pdf_report_12_08_2020_19_08_169.pdf").getAbsolutePath();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isRmLinksAvailableInPdfFile(String, String)}.
   *
   * @throws IOException {@link Exception}
   */
  @Test
  public void testIsRmLinksAvailableInPdfFile() throws IOException {
    loadPage("dng/reportCompleteMessage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#isRmLinksAvailableInPdfFile(String, String)}.
   *
   * @throws IOException {@link Exception}
   */
  @Test
  public void testvalidatePdfContent() throws IOException {
    loadPage("dng/reportCompleteMessage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getArtifactIdByIndex(String)}.
   */
  @Test
  public void testGetArtifactIdByIndex() {
    loadPage("dng/RM_index.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getArtifactIdByIndex("1");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getArtifactIdByIndex(String)}.
   */
  @Test(expected = Exception.class)
  public void testGetArtifactIdByIndexOne() {
    loadPage("dng/RM_index.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getArtifactIdByIndex("100");
  }

  /**
   * *
   * <p>
   * Unit test to verify {@link RMArtifactsPage#searchView(String)}.
   */
  @Test
  public void testSearchView() {
    loadPage("dng/test_searchViewTwo.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.searchView("View_created_to_automate_pdf");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectOptionFromCreateDropDown(String)}.
   */
  @Test(expected = Exception.class)
  public void testSelectOptionFromCreateDropDown() {
    loadPage("dng/test_createDropdown.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.selectOptionFromCreateDropDown("Others...");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnCreateButton(String)}.
   */
  @Test
  public void testclickOnCreateButton() {
    loadPage("dng/test_createDropdown.htm");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/test_createDropdown_01.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnCreateButton("Other...");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#verifyEditAttributeSuccessMessage(String)}.
   */
  @Test
  public void testVerifyEditattributeSuccessMessage() {
    loadPage("dng/test_compleatedMessage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.verifyEditAttributeSuccessMessage("Artifact Update Completed");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#openAndSelectSubMenuInCurrentConfiguration(String)}.
   */
  @Test
  public void testOpenAndSelectSubMenuInCurrentConfiguration() {
    loadPage("dng/RM_index.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.openAndSelectSubMenuInCurrentConfiguration("invalid");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)}.
   */
  @Test(expected = Exception.class)
  public void testSelectMenuItemFromMoreActions() {
    loadPage("dng/RM_index.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/RM_index.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.selectMenuItemFromMoreActions("Manage Tags...");
  }

  /**
   * Loads RM Artifacts page and check Is Condition Set In Filter.
   */
  @Test
  public void testIsConditionSetInFilter() {
    loadPage("dng/test_filterCondition.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.isConditionSetInFilter("Full Text - - test");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#waitForHundredPercentLoad()}.
   */
  @Test
  public void testWaitForHundredPercentLoad() {
    loadPage("dng/test_compleatedMessage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.waitForHundredPercentLoad();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clearSelectedTags()}.
   */
  @Test
  public void testClearSelectedTags() {
    loadPage("dng/test_cleareTag.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clearSelectedTags();

  }

  /**
   * <p>
   * Unit test verify {@link RMArtifactsPage#clearAttributeValue(String)}.
   */
  @Test
  public void testclearAttributeValue() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clearAttributeValue("Description");

  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#createNewTag(String, String)}.
   */
  @Test
  public void testCreateNewTag() {
    loadPage("dng/create_new_tag.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.createNewTag("test_tag", "tag_description");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#addReportInformationWithDateandTime(Map)}.
   */
  @Test
  public void testAddReportInformationWithDateandTime() {
    loadPage("dng/add_report_information_with_date_and_time.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<String, String> params = new HashMap<>();
    params.put("reportName", "test");
    params.put("reportType", "Traceability Report");
    params.put("appendDateTime", "true");
    params.put("reportFormat", "Adobe PDF");
    params.put("companyName", "test");
    params.put("authorName", "Company Name");
    rm.addReportInformationWithDateandTime(params);
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#addReportInformationWithoutDateandTime(String, String, String, String)}.
   */
  @Test
  public void testAddReportInformationWithoutDateandTime() {
    loadPage("dng/add_report_information_with_date_and_time.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.addReportInformationWithoutDateandTime("test_", "Adobe PDF", "test", "Company Name");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectSaveReportToProjectCheckBox()}.
   */
  @Test
  public void testSelectSaveReportToProjectCheckBox() {
    loadPage("dng/unselect_save_report_to_project_checkbox.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.selectSaveReportToProjectCheckBox();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#unSelectSaveReportToProjectCheckBox()}.
   */
  @Test
  public void testUnSelectSaveReportToProjectCheckBox() {
    loadPage("dng/select_save_report_to_project_checkbox.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.unSelectSaveReportToProjectCheckBox();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getValidationMessageFromReportGenerationMessageArea(String)}.
   */
  @Test
  public void testGetValidationMessageFromReportGenerationMessageArea() {
    loadPage("dng/validation_generation_message_area.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getValidationMessageFromReportGenerationMessageArea("Report generation complete");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectOpenTheReportWhenTheWizardIsClosedCheckBox()}.
   */
  @Test
  public void testSelectOpenTheReportWhenTheWizardIsClosedCheckBox() {
    loadPage("dng/select_on_closed_checkbox.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.selectOpenTheReportWhenTheWizardIsClosedCheckBox();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnArtifactTypes(String)}.
   */
  @Test
  public void testClickOnArtifactTypes() {
    loadPage("dng/delete_artifact_folder_3.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clickOnArtifactTypes("All");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnArtifactTypes(String)}.
   */
  @Test(expected = Exception.class)
  public void testClickOnArtifactTypesTwo() {
    loadPage("dng/delete_artifact_folder_3.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clickOnArtifactTypes("test");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#selectMultipleArtifact(String)}.
   */
  @Test
  public void testSelectMultipleArtifact() {
    loadPage("dng/selectMulitple.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectMultipleArtifact("5");
  }

  /**
   * Search and select artifact in the Add to module... dialog
   */
  @Test
  public void testGetDownloadedPDFFileNameAndPath() {
    loadPage("dng/Add_Artifact_To_Module_2.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.getDownloadedPDFFileNameAndPath("");
  }


  /**
   * Add new filter for Folder attribute
   */
  @Test
  public void testAddAndCloseValueForFolderAttributeInFilter() {
    loadPage("dng/FilterFolder04.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addAndCloseValueForFolderAttributeInFilter("PPP 123 artifacts");
  }

  /**
   * Search and Go To Artifact
   */
  @Test
  public void testSearchGoToInModuleFind() {
    loadPage("dng/searchGoTo_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/searchGoTo_02.html");
    clickNumberToPagePath.put(3, "dng/searchGoTo_03.html");
    clickNumberToPagePath.put(4, "dng/searchGoTo_04.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.searchGoToInModuleFind("1107282");
  }


  /**
   * Get Column Header From CSV File By Index
   */
  @Test
  public void testGetColumnHeaderFromCSVFileByIndex() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.csv";
    String actual_output = rm.getColumnHeaderFromCSVFileByIndex(path, "5");
    String expect_result = "Link:Implemented By";
    assertEquals(actual_output, expect_result);
  }

  /**
   * Add Limit By Lifecycle Status Filter Case 1: All item are selected value
   */
  @Test
  public void testAddItemAndValueForLimitByLifecycleStatusFilter01() {
    loadPage("dng/LimitByLifecycleStatus01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/LimitByLifecycleStatus07.html");
    clickNumberToPagePath.put(3, "dng/LimitByLifecycleStatus08.html");
    clickNumberToPagePath.put(4, "dng/LimitByLifecycleStatus09.html");
    clickNumberToPagePath.put(5, "dng/LimitByLifecycleStatus10.html");
    clickNumberToPagePath.put(6, "dng/LimitByLifecycleStatus03.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("ATTRIBUTE_NAME", "Limit by lifecycle status");
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "Resolved");
    additionalParams.put("TRACKING_VALUE", "Open");
    additionalParams.put("AFFECTS_VALUE", "All");
    additionalParams.put("TEST_VALUE", "Unattempted");
    rm.addItemAndValueForLimitByLifecycleStatusFilter(additionalParams);
  }

  /**
   * Add Limit By Lifecycle Status Filter Case 2: One item is selected value. Others are not selected (case for "not
   * select")
   */
  @Test
  public void testAddItemAndValueForLimitByLifecycleStatusFilter02() {
    loadPage("dng/LimitByLifecycleStatus01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/LimitByLifecycleStatus02.html");
    clickNumberToPagePath.put(3, "dng/LimitByLifecycleStatus03.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("ATTRIBUTE_NAME", "Limit by lifecycle status");
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "not select");
    additionalParams.put("TRACKING_VALUE", "not select");
    additionalParams.put("AFFECTS_VALUE", "not select");
    additionalParams.put("TEST_VALUE", "All");
    rm.addItemAndValueForLimitByLifecycleStatusFilter(additionalParams);
  }


  /**
   * Unit test for {@link RMArtifactsPage#getAllValuesAtColumnFromTable(String,String)} Case 1: expectedValue is
   * "emptydata"
   */
  @Test
  public void testGetAllValuesAtColumnFromTable01() {
    loadPage("dng/getValueFromColumn02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.getAllValuesAtColumnFromTable("Validated By", "emptydata");
  }

  /**
   * Unit test for {@link RMArtifactsPage#getAllValuesAtColumnFromTable(String,String)} Case 2: expectedValue is single
   * value
   */
  @Test
  public void testGetAllValuesAtColumnFromTable02() {
    loadPage("dng/getValueFromColumn01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.getAllValuesAtColumnFromTable("Validated By", "96118: TC_SW_QM_PowerMonitoring");
  }


  /**
   * Unit test for {@link RMArtifactsPage#getAllValuesAtColumnFromTable(String,String)} Case 3: expectedValue is multi
   * value (ex: "32090: TC_CleanupECU", "9500: RSU_1162_KL30only", "emptydata")
   */
  @Test
  public void testGetAllValuesAtColumnFromTable03() {
    loadPage("dng/getValueFromColumn03.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.getAllValuesAtColumnFromTable("Validated By", "32090: TC_CleanupECU,9500: RSU_1162_KL30only,emptydata");
  }

  /**
   * Unit test for {@link RMArtifactsPage#getExcelSize(String)}
   *
   * @throws FileNotFoundException -
   */
  @Test
  public void testgetExcelSize() throws FileNotFoundException {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.csv";
    String actual_output = rm.getExcelSize(path);
    double actual = Double.parseDouble(actual_output);
    assertNotEquals(0, actual);

  }

  /**
   * Unit test for {@link RMArtifactsPage#clickOnRadioButtonInExportDialog(String)}
   */
  @Test
  public void testClickOnRadioButtonInExportDialog() {
    loadPage("dng/ClickOnRadioButton01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/ClickOnRadioButton02.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnRadioButtonInExportDialog("XLSX");

  }

  /**
   * Unit test for {@link RMArtifactsPage#getDataFromCellOfXLSX(String,String,String,String)}
   */
  @Test
  public void testGetDataFromCellOfXLSX() {
    RMArtifactsPage ravt = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(ravt);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    String result = ravt.getDataFromCellOfXLSX(path, "0", "0", "0");
    assertEquals(result, "id");

  }

  /**
   * Unit test for {@link RMArtifactsPage#openEditFilterDialogOfOneCondition(String)}
   */
  @Test(expected = Exception.class)
  public void testOpenEditFilterDialogOfOneCondition() {
    loadPage("dng/EditFilter01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/EditFilter02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.openEditFilterDialogOfOneCondition("Limit by lifecycle status");

  }

  /**
   * Edit Limit By Lifecycle Status Filter {@link RMArtifactsPage#editItemAndValueForLimitByLifecycleStatusFilter(Map)}
   * Case 1: All item are selected value - one item as TRACKING_ITEM is remove selected item by re-select this value
   * again. Other items change value from None to All/Passed
   */
  @Test
  public void testEditItemAndValueForLimitByLifecycleStatusFilter01() {
    loadPage("dng/EditItemAndValueForLimitByLifecycleStatusFilter01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/EditItemAndValueForLimitByLifecycleStatusFilter02.html");
    clickNumberToPagePath.put(3, "dng/EditItemAndValueForLimitByLifecycleStatusFilter03.html");
    clickNumberToPagePath.put(4, "dng/EditItemAndValueForLimitByLifecycleStatusFilter04.html");
    clickNumberToPagePath.put(5, "dng/EditItemAndValueForLimitByLifecycleStatusFilter05.html");
    clickNumberToPagePath.put(6, "dng/EditItemAndValueForLimitByLifecycleStatusFilter06.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "All");
    additionalParams.put("TRACKING_VALUE", "None");
    additionalParams.put("AFFECTS_VALUE", "All");
    additionalParams.put("TEST_VALUE", "Passed");
    rm.editItemAndValueForLimitByLifecycleStatusFilter(additionalParams);

  }

  /**
   * Edit Limit By Lifecycle Status Filter {@link RMArtifactsPage#editItemAndValueForLimitByLifecycleStatusFilter(Map)}
   * Case 2: One item is selected value. Others are not selected (case for "not select")
   */
  @Test
  public void testEditItemAndValueForLimitByLifecycleStatusFilter02() {
    loadPage("dng/EditItemAndValueForLimitByLifecycleStatusFilter01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/EditItemAndValueForLimitByLifecycleStatusFilter07.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "not select");
    additionalParams.put("TRACKING_VALUE", "not select");
    additionalParams.put("AFFECTS_VALUE", "not select");
    additionalParams.put("TEST_VALUE", "not select");
    rm.editItemAndValueForLimitByLifecycleStatusFilter(additionalParams);
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#cloneFromAComponent(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testCloneFromAComponent() {
    loadPage("dng/cloneFromAComponent.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/cloneFromAComponent_01.html");
    clickNumberToPagePath.put(2, "dng/cloneFromAComponent_02.html");
    clickNumberToPagePath.put(3, "dng/cloneFromAComponent_03.html");
    clickNumberToPagePath.put(4, "dng/cloneFromAComponent_05.html");
    clickNumberToPagePath.put(5, "dng/cloneFromAComponent_06.html");
    clickNumberToPagePath.put(6, "dng/cloneFromAComponent_07.html");
    clickNumberToPagePath.put(7, "dng/cloneFromAComponent_08.html");
    clickNumberToPagePath.put(8, "dng/cloneFromAComponent_08_1.html");
    clickNumberToPagePath.put(9, "dng/cloneFromAComponent_09.html");
    clickNumberToPagePath.put(10, "dng/cloneFromAComponent_10.html");
    clickNumberToPagePath.put(11, null);
    clickNumberToPagePath.put(12, "dng/cloneFromAComponent_11.html");
    clickNumberToPagePath.put(13, "dng/cloneFromAComponent_12.html");
    clickNumberToPagePath.put(14, "dng/cloneFromAComponent_13.html");
    clickNumberToPagePath.put(15, "dng/cloneFromAComponent_14.html");
    clickNumberToPagePath.put(16, null);
    clickNumberToPagePath.put(17, "dng/cloneFromAComponent_16.html");
    clickNumberToPagePath.put(18, "dng/cloneFromAComponent_12.html");
    clickNumberToPagePath.put(19, "dng/cloneFromAComponent_17.html");
    clickNumberToPagePath.put(20, "dng/cloneFromAComponent_18.html");

    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("FOLDER_NAME", "test112421");
    additionalParams.put("PROJECT_AREA", "Control Unit System Development (Requirements)");
    additionalParams.put("COMPONENT_NAME", "MO_EcuBasFct");
    additionalParams.put("STREAMS_DROPDOWN_OPTION", "Streams");
    additionalParams.put("STREAM_NAME", "MO_EcuBasFct Initial Stream");
    additionalParams.put("SELECT_ARTIFACT_TYPE", "Add Module");
    additionalParams.put("ARTIFACT_ID", "1020219");
    rm.cloneFromAComponent(additionalParams);
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPage#selectConditionWhenAddAndCloseListValueForAttributeInFilter(Map)}
   */
  @Test(expected = Exception.class)
  public void testSelectConditionWhenAddAndCloseListValueForAttributeInFilter() {
    loadPage("dng/selectCondition_02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    // Can not save as html file which has opening drop-down (expected = Exception.class)
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/selectCondition_03.html");
    clickNumberToPagePath.put(3, "dng/selectCondition_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("ARTIFACT_NAME", "Status SW SRS");
    additionalParams.put("DEFAULT_CONDITION", "is any of");
    additionalParams.put("EXPECT_CONDITION", "does not exist");
    rm.selectConditionWhenAddAndCloseListValueForAttributeInFilter(additionalParams);
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPage#addAndCloseListValueByTextFieldForAttributeInFilter(String,String)}
   */
  @Test
  public void testAddAndCloseListValueByTextFieldForAttributeInFilter() {
    loadPage("dng/FilterAddListValue_02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/FilterAddListValue_03.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/FilterAddListValue_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.addAndCloseListValueByTextFieldForAttributeInFilter("Artifact ID", "645625, 611268, 611566");
  }

  /**
   * Unit test for {@link RMArtifactsPage#clickOnArtifactByOpenNewTab(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testClickOnArtifactByOpenNewTab() {
    loadPage("dng/FilterAddListValue_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clickOnArtifactByOpenNewTab("611268");
  }

  /**
   * Unit test for {@link RMArtifactsPage#searchGoToInModuleFindWhat(String,String)}
   */
  @Test
  public void testSearchGoToInModuleFindWhat() {
    loadPage("dng/find_Goto.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> findwhatInModuledlg = new HashMap<>();
    findwhatInModuledlg.put(1, "dng/searchGoToInModuleFindWhat.html");
    findwhatInModuledlg.put(2, "dng/searchGoToInModuleFindWhat02.html");
    findwhatInModuledlg.put(3, "dng/searchGoToInModuleFindWhat03.html");
    findwhatInModuledlg.put(4, "dng/searchGoToInModuleFindWhat04.html");
    loadNewPageOnNthDriverClickElementCall(findwhatInModuledlg);
    rm.searchGoToInModuleFindWhat("Use Case Description:TEST", "173350");
  }

  /**
   * Unit test for {@link RMArtifactsPage#selectSearchedArtifactCheckBoxAndCopyContent(String, String)}
   */
  @Test
  public void testSelectSearchedArtifactCheckBoxAndCopyContent() {
    loadPage("dng/searchedArtifactCheckBoxAndCopyContent.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectSearchedArtifactCheckBoxAndCopyContent("Test Artfact 5", "2032453");
  }

  /**
   * Unit test for {@link RMArtifactsPage#chooseAllAttributeValuesForSelectedAttribute}
   */
  @Test
  public void testChooseAllAttributeValuesForSelectedAttribute() {
    loadPage("dng/chooseAllAttributeValuesForSelectedAttribute.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, "dng/chooseAllAttributeValuesForSelectedAttribute_04.html");
    chooseAllAttributeValuesForSelectedAttribute.put(2, null);
    chooseAllAttributeValuesForSelectedAttribute.put(3, null);
    chooseAllAttributeValuesForSelectedAttribute.put(4, null);
    chooseAllAttributeValuesForSelectedAttribute.put(5, null);
    loadNewPageOnNthDriverClickElementCall(chooseAllAttributeValuesForSelectedAttribute);
    rm.chooseAllAttributeValuesForSelectedAttribute("Category", "is any of",
        "functional, non functional, non technical, functional safety");
  }

  /**
   * Unit test for {@link RMArtifactsPage#isEmptyDataExistingFromArtifactTable(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testIsEmptyDataExistingFromArtifactTable() {
    loadPage("dng/ChecEmptyData01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, null);
    loadNewPageOnNthDriverClickElementCall(chooseAllAttributeValuesForSelectedAttribute);
    boolean check = false;
    check = rm.isEmptyDataExistingFromArtifactTable("Contents");
    assertTrue(check);
  }


  /**
   * Unit test for {@link RMArtifactsPage#verifyDataDisplayedInColumn(String,String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyDataDisplayedInColumn() {
    loadPage("dng/IsDataDisplayedInColumn_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, null);
    loadNewPageOnNthDriverClickElementCall(chooseAllAttributeValuesForSelectedAttribute);
    assertTrue(rm.verifyDataDisplayedInColumn("Artifact Type", "SYS HSI Heading"));
    assertFalse(rm.verifyDataDisplayedInColumn("Artifact Type", "Type not found"));
  }

  /**
   * Unit test for {@link RMArtifactsPage#isExistingIDAndURLInOneColumnFromExcelXLSXExport(String,String,String)}
   *
   * @author LPH1HC
   * @throws IOException -
   */
  @Test
  public void testIsExistingIDAndURLInOneColumnFromExcelXLSXExport() throws IOException {

    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    boolean check = false;
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    check = rm.isExistingIDAndURLInOneColumnFromExcelXLSXExport(path, "0", "5");
    assertTrue(check);
  }

  /**
   * Unit test for {@link RMArtifactsPage#openCreateTermDialogByRightClick()}
   *
   * @author LPH1HC
   */
  @Test
  public void testOpenCreateTermDialogByRightClick() {
    loadPage("dng/openCreateTermRightClick02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, "dng/openCreateTermRightClick02.html");
    loadNewPageOnNthDriverClickElementCall(chooseAllAttributeValuesForSelectedAttribute);
    rm.openCreateTermDialogByRightClick();
  }


  /**
   * Unit test for {@link RMArtifactsPage#createNewTerm(Map)}
   *
   * @author LPH1HC
   */
  @Test
  public void testCreateNewTerm() {
    loadPage("dng/createTerm01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, "dng/createTerm02.html");
    chooseAllAttributeValuesForSelectedAttribute.put(2, "dng/createTerm03.html");
    chooseAllAttributeValuesForSelectedAttribute.put(3, "dng/createTerm04.html");
    loadNewPageOnActionsCall(chooseAllAttributeValuesForSelectedAttribute);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("Initial_Content", "123");
    additionalParams.put("Artifact_Type", "Actor (Represents a role that a human, hardware device, or another ...)");
    rm.createNewTerm(additionalParams);
  }

  /**
   * Unit test for {@link RMArtifactsPage#editArtifactIDFilter(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testEditArtifactIDFilter() {
    loadPage("dng/EditArtifactIDFilter01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, "dng/EditArtifactIDFilter02.html");
    chooseAllAttributeValuesForSelectedAttribute.put(2, "dng/EditArtifactIDFilter03.html");
    chooseAllAttributeValuesForSelectedAttribute.put(3, "dng/EditArtifactIDFilter04.html");
    loadNewPageOnActionsCall(chooseAllAttributeValuesForSelectedAttribute);
    rm.editArtifactIDFilter("253823");
  }

  /**
   * Unit test for {@link RMArtifactsPage#openLookUpTermDialogByRightClick()}
   *
   * @author LPH1HC
   */
  @Test
  public void testOpenLookUpTermDialogByRightClick() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, "dng/LookUpTerm01.html");
    loadNewPageOnActionsCall(chooseAllAttributeValuesForSelectedAttribute);
    rm.openLookUpTermDialogByRightClick();
  }

  /**
   * Unit test for {@link RMArtifactsPage#isTermExistingLookUpTermDialog(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testIsTermExistingLookUpTermDialog() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> chooseAllAttributeValuesForSelectedAttribute = new HashMap<>();
    chooseAllAttributeValuesForSelectedAttribute.put(1, "dng/LookUpTerm01.html");
    loadNewPageOnActionsCall(chooseAllAttributeValuesForSelectedAttribute);
    rm.isTermExistingLookUpTermDialog("test_term_content");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#removeColumnFromTable(String)}.
   */
  @Test
  public void testRemoveColumnFromTable() {
    loadPage("dng/change_column_display_settings.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/change_column_display_settings_window.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.removeColumnFromTable("Validated By");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickSaveChangesToThisView()}.
   */
  @Test
  public void testClickSaveChangesToThisView() {
    loadPage("dng/clickSaveChangesToThisView.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickSaveChangesToThisView();
  }

  /**
   * <p>
   *
   * @author LPH1HC <br>
   *         Unit test for {@link RMArtifactsPage#isAllTheCountValuesMatchingWithTheOriginalValue(String)}.<br>
   */
  @Test
  public void testIsAllTheCountValuesMatchingWithTheOriginalValue01() {
    loadPage("dng/isAllTheCountValuesMatchingWithTheOriginalValue01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    boolean check = rm.isAllTheCountValuesMatchingWithTheOriginalValue("15");
    assertTrue(check);
  }

  /**
   * <p>
   *
   * @author LPH1HC <br>
   *         Unit test for {@link RMArtifactsPage#isAllTheCountValuesMatchingWithTheOriginalValue(String)}.<br>
   */
  @Test
  public void testIsAllTheCountValuesMatchingWithTheOriginalValue02() {
    loadPage("dng/isAllTheCountValuesMatchingWithTheOriginalValue01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    boolean check = rm.isAllTheCountValuesMatchingWithTheOriginalValue("10");
    assertFalse(check);
  }

  /**
   * <p>
   *
   * @author LPH1HC <br>
   *         Unit test for {@link RMArtifactsPage#isPassedStatusOfValidatedByLinkExistingInOneArtifactRow()}.<br>
   */
  @Test
  public void testIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow01() {
    loadPage("dng/IsPassedStatusOfValidatedByLinkExistingInOneArtifactRow_PASSED.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    boolean check = rm.isPassedStatusOfValidatedByLinkExistingInOneArtifactRow();
    System.out.println(check);
    assertTrue(check);
  }

  /**
   * <p>
   *
   * @author LPH1HC <br>
   *         Unit test for {@link RMArtifactsPage#isPassedStatusOfValidatedByLinkExistingInOneArtifactRow()}.<br>
   */
  @Test
  public void testIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow02() {
    loadPage("dng/IsPassedStatusOfValidatedByLinkExistingInOneArtifactRow_FAILED.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    boolean check = rm.isPassedStatusOfValidatedByLinkExistingInOneArtifactRow();
    assertFalse(check);
  }

  /**
   * <p>
   * Unit test for {@link RMArtifactsPage#removeFilterByCondition(String)}.<br>
   *
   * @author KYY1HC
   */
  @Test
  public void testRemoveFilterByCondition() {
    loadPage("dng/removeFilterByCondition_01_AddedFilter.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/removeFilterByCondition_02_RemoveFilterDisplayed.html");
    clickNumberToPagePath.put(2, "dng/removeFilterByCondition_03_RemovedFilter.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.removeFilterByCondition("Artifact Type - is any of - SW STIL Information, SW STIL Requirement");
  }

  /**
   * Unit test for {@link RMArtifactsPage#verifyNoErrorMessageDisplay()}.<br>
   */
  @Test
  public void testVerifyNoErrorMessageDisplay() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertTrue(rm.verifyNoErrorMessageDisplay());
  }

  /**
   * Unit test for {@link RMArtifactsPage#isButtonDisplayed(String)}.<br>
   *
   * @author KYY1HC
   */
  @Test
  public void testIsButtonDisplayed() {
    loadPage("dng/IsButtonDisplayWithTitle.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertTrue(rm.isButtonDisplayed("Undo all changes to this view"));
  }

  /**
   * Unit test for {@link RMArtifactsPage#overrideTheLockedArtifact()}.<br>
   *
   * @author KYY1HC
   */
  @Test
  public void testOverrideTheLockedArtifact() {
    loadPage("dng/ClickToOverrideTheLock_01_LockedArtifactIcon.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/ClickToOverrideTheLock_02_LockedArtifactDialog.html");
    clickNumberToPagePath.put(2, "dng/ClickToOverrideTheLock_03_UnLockedArtifactSuccessfully.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.overrideTheLockedArtifact());
  }

  /**
   * Unit test for {@link RMArtifactsPage#isColumnHeaderDisplayed(String)}.<br>
   * Column Header is displayed
   *
   * @author KYY1HC
   */
  @Test
  public void testIsColumnHeaderDisplayed() {
    loadPage("dng/GetColumnHeaderInView_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertTrue(rm.isColumnHeaderDisplayed("Modified By"));
  }

  /**
   * Unit test for {@link RMArtifactsPage#isColumnHeaderDisplayed(String)}.<br>
   * Column Header is not displayed
   *
   * @author KYY1HC
   */
  @Test
  public void testIsColumnHeaderNotDisplayed() {
    loadPage("dng/GetColumnHeaderInView_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    assertFalse(rm.isColumnHeaderDisplayed("Validated By"));
  }

  /**
   * Unit test for {@link RMArtifactsPage#setDataFromCellOfXLSX(String, String, String, String, String)}.<br>
   *
   * @author VDY1HC
   */
  @Test
  public void testSetDataFromCellOfXLSX() {
    RMArtifactsPage ravt = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(ravt);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    ravt.setDataFromCellOfXLSX(path, "0", "0", "0", "id");
  }

  /**
   * Unit test for {@link RMArtifactsPage#getCurrentURL()}.<br>
   *
   * @author VDY1HC
   */
  @Test
  public void testGetCurrentURL() {
    loadPage("dng/GetColumnHeaderInView_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getCurrentURL();
  }

  /**
   * Unit test for method {@link RMArtifactsPage#hoverOnArtifactLink(String, String, String, String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testHoverOnArtifactLink() {
    loadPage("dng/HoverOnArtifactLink01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/HoverOnArtifactLink02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.hoverOnArtifactLink("1132128", "Satisfied By", "1132127:Module For Automation Testing TS_25933", "4");
  }

  /**
   * Unit test for method {@link RMArtifactsPage#clickOnCloseButtonOnPopup()}
   *
   * @author VDY1HC
   */
  @Test
  public void testClickOnCloseButtonOnPopup() {
    loadPage("dng/HoverOnArtifactLink02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/HoverOnArtifactLink01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnCloseButtonOnPopup();
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#editVerificationCriteria(String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testEditVerificationCriteria() {
    loadPage("dng/editVerificationCriteria_02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/editVerificationCriteria_01.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.editVerificationCriteria("644849", "test Verification Criteria");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#clearVerificationCriteria()}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testClearVerificationCriteria() {
    loadPage("dng/clearVerificationCriteria.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/clearVerificationCriteria_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clearVerificationCriteria();
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#closeVerificationCriteriaPopupByClickingOutOfThePopup()}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testCloseVerificationCriteriaDialogWithoutClickingOkOrCancel() {
    loadPage("dng/clearVerificationCriteria_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/closeVerificationCriteriaDialogWithoutClickingOkOrCancel.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.closeVerificationCriteriaPopupByClickingOutOfThePopup();
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#clickOnApplyFilters}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testClickOnApplyFilters() {
    loadPage("dng/clickOnApplyFilters.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/clickOnApplyFilters1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnApplyFilters();
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#clickOnAddExistingArtifactOption}
   * <p>
   *
   * @author KCE1KOR
   */
  @Test
  public void testClickOnAddExistingArtifactOption() {
    loadPage("dng/addArtifact1.htm");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.clickOnAddExistingArtifactOption();
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#exportView(String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testExportView() {
    loadPage("dng/exportView2.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/exportView3.html");
    clickNumberToPagePath.put(2, "dng/exportView4.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(4, "dng/exportView1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.exportView("test", "XLS");
  }

  /**
   * <p>
   * Unit test for method
   * ${@link RMArtifactsPage#verifyAttributeValueOfArtifactDisplayedInXLSFile(String, String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyAttributeValueOfArtifactDisplayedInXLSFile() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    String fileNameAndPath =
        Paths.get(RMConstants.IMPORT_FILE_LOCATION + "Artifact_Excel2.xls").toAbsolutePath().toString();
    rm.verifyAttributeValueOfArtifactDisplayedInXLSFile("CAN communication interface", fileNameAndPath, "10",
        "Primary Text");
  }

  /**
   * <p>
   * Unit test for method
   * ${@link RMArtifactsPage#verifyTestCaseStatusUnderValidateByAttributeOfArtifact(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyTestCaseStatusUnderValidateByAttributeOfArtifact() {
    loadPage("rqm/clickOnLinkInRightSide2.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.verifyTestCaseStatusUnderValidateByAttributeOfArtifact("1051958", "47863: Test case 2 for automation testing",
        "Failed");
  }


  /**
   * Unit test for {@link RMArtifactsPage#openEditFilterDialogOfAttributeName(String)}
   *
   * @author PDU6HC
   */
  @Test
  public void testOpenEditFilterDialogOfAttributeName() {
    loadPage("dng/EditFilterConditionNumber.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/EditFilterConditionNumber01.html");
    clickNumberToPagePath.put(2, "dng/EditFilterConditionNumber02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.openEditFilterDialogOfAttributeName("Artifact Type");

  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#addFilterForAttributeName(String,String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testAddFilterForAttributeName() {
    loadPage("dng/EditFilterConditionNumber.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/EditFilterConditionNumber01.html");
    clickNumberToPagePath.put(2, "dng/EditFilterConditionNumber02.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);

    rm.addFilterForAttributeName("Artifact Type", "TypeC-System");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#getPercentageOfArtifactDisplayed()}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testGetPercentageOfArtifactDisplayed() {
    loadPage("dng/test.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getPercentageOfArtifactDisplayed();
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#collapseFilters()}.
   *
   * @author PDU6HC
   */
  @Test
  public void testCollapseFilter() {
    loadPage("dng/collapseFilters.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.collapseFilters();
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#hoverOnHyperLink(String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testHoverOnHyperLink() {
    loadPage("dng/hoverOnHyperLink1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/hoverOnHyperLink2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.hoverOnHyperLink("661554", "nT100A3655");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#verifyHyperlinkDisplayed(String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyHyperlinkDisplayed() {
    loadPage("dng/hoverOnHyperLink1.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.verifyHyperlinkDisplayed("661554", "nT100A3655");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#clickOnSelectAllModules}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testClickOnSelectAllItems() {
    loadPage("dng/testClickOnSelectAllItems.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testClickOnSelectAllItems_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnSelectAllModules();
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPage#removeLinkOnModuleWithId(String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testClickOnRemoveLinkOnModuleWithId() {
    loadPage("dng/testClickOnRemoveLinkOnModuleWithId_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/testRemoveLinkOfArtifactFromTable_04.html");
    clickNumberToPagePath.put(5, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.removeLinkOnModuleWithId("1439718", "Component: DefaultComponent"));
  }

  /**
   * Unit test covers for method ${@link RMArtifactsPage#navigateToAuditHistory(String)}
   *
   * @author PDU6HC
   */
  @Test
  public void testNavigateToAuditHistory() {
    loadPage("dng/testNavigateToAuditHistory.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testNavigateToAuditHistory_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.navigateToAuditHistory("2194457");
  }

  /**
   * Unit test covers for method ${@link RMArtifactsPage#hoverOnLinksByText(String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testHoverOnLinksByText() {
    loadPage("dng/HoverOnLinksByText_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/HoverOnLinksByText_02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.hoverOnLinksByText("6281");
  }

  /**
   * Unit test covers for method ${@link RMArtifactsPage#checkLinkWithTextOnQuickInfo(String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testCheckLinkWithTextOnQuickInfo() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.checkLinkWithTextOnQuickInfo(
        "678325: For classifying and prioritizing artefacts the attribute <Safety Classification> is used:"));
  }

  /**
   * Unit test covers for method ${@link RMArtifactsPage#isExistingTextInArtifactContent(String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testIsExistingTextInArtifactContent() {
    loadPage("dng/IsExistingTextInArtifactContent_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    String expectedText =
        "For having optimal memory ressource usage the VLE compiler option must be used for building the application SW.";
    assertTrue(rm.isExistingTextInArtifactContent(expectedText));
  }

  /**
   * Unit test covers for method ${@link RMArtifactsPage#deleteComment(String, String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testDeleteComment() {
    loadPage("dng/deleteComment.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/deleteComment_02.html");
    clickNumberToPagePath.put(3, "dng/deleteComment_03.html");
    clickNumberToPagePath.put(4, "dng/deleteComment.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteComment("824506", "subject");
  }

  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#clickOnFolder(String)}.
   */
  @Test
  public void testClickOnFolder() {
    loadPage("dng/ClickOnFolder_01.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/ClickOnFolder_02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnFolder("AE Doors Development (RM)");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#verifyFolderHierarchy(Map)}.
   * @author LTU7HC
   */
  @Test
  public void verifyFolderHierarchy() {
    loadPage("dng/rm_folder_hirarchy.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("rootFolderName", "AE Doors Development (RM)");
    additionalParams.put("childLv1", "AE_ENP_ALM");
    additionalParams.put("childLv2", "DNG");
    additionalParams.put("childLv3",
        "Acceptance Criteria on Requiremt Exchange artifacts," +
        " Copy of Acceptance Criteria on Requiremt Exchange artifacts");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.verifyFolderHierarchy(additionalParams);
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#createAndOpenAnArtifact(Map)}.
   */
  @Test
  public void testCreateAndOpenAnArtifact() {
    loadPage("dng/createArtifact.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/createArtifact_ID.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    String name = "Test Artifact " + DateUtil.getCurrentDateAndTime();
    additionalParams.put("ARTIFACT_NAME", name);
    additionalParams.put("ARTIFACT_TYPE", "Assumption");
    additionalParams.put("ARTIFACT_FORMAT", "Text");
    rm.createAndOpenAnArtifact(additionalParams);
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getArtifactName()}.
   */
  @Test
  public void testGetArtifactName() {
    loadPage("dng/get_artifact_id_name.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getArtifactName();
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getArtifactID()}.
   */
  @Test
  public void testGetArtifactID() {
    loadPage("dng/get_artifact_id_name.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getArtifactID();
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#navigateToArtifactsPage()}.
   */
  @Test
  public void testNavigateToArtifactsPage() {
    loadPage("dng/get_artifact_id_name.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.navigateToArtifactsPage();
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getArtifactQuickInfo()}.
   */
  @Test
  public void testGetArtifactQuickInfo() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getArtifactQuickInfo();
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getLocationOfAnArtifact()}.
   */
  @Test
  public void testGetLocationOfAnArtifact() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.getLocationOfAnArtifact();
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#insertArtifactNameInArtifactsPage(String)}.
   */
  @Test
  public void testInsertArtifactNameInArtifactsPage() {
    loadPage("dng/insert_artifact_name_in_artifactspage.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    rm.insertArtifactNameInArtifactsPage("test");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#countNumberOfArtifactDisplayed()}.
   */
  @Test
  public void testCountNumberOfArtifactDisplayed() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);    
    // Case single page
    loadPage("dng/countArtifact0.html");
    assertEquals(20, Integer.parseInt(rm.countNumberOfArtifactDisplayed()));    
    // More than 1 page (Next button displayed)
    loadPage("dng/countArtifact1.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "/dng/countArtifact2.html");
    clickNumberToPagePath.put(2, "/dng/countArtifact3.html");
    clickNumberToPagePath.put(3, "/dng/countArtifact4.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals(95, Integer.parseInt(rm.countNumberOfArtifactDisplayed()));
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getCurrentDisplayedArtifactsNumber()}.
   */
  @Test
  public void testGetCurrentDisplayedArtifactsNumber() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm); 
    loadPage("dng/countArtifact1.html");
    assertEquals(25, Integer.parseInt(rm.getCurrentDisplayedArtifactsNumber()));
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPage#getAllArtifactsNumber()}.
   */
  @Test
  public void testGetAllArtifactsNumber() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    loadPage("dng/countArtifact1.html");
    assertEquals(95, Integer.parseInt(rm.getAllArtifactsNumber()));
  }
}