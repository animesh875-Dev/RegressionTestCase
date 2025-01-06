/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;


/**
 * Unit tests for the RMManageComponentPropertiesPage.
 */
public class RMManageComponentPropertiesPageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.
   */
  @Test
  public void testSelectComponentPropertiesType() {
    loadPage("dng/selectType.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.selectComponentPropertiesType("ReqIF");
  }
  @Test
  public void testclickOnAddArtifactTypesFromComponentSetupDialogue() {
    loadPage("dng/Component_ setup1.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
   
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
   // additionalParams.put("applyAcomponentTemplate", "Apply a component template");
    additionalParams.put("IMPORT_COMPONENT_PROPERTIES_FROM_AN_EXISTING_COMPONENT", "Import component properties from an existing component");
  //  additionalParams.put("manuallyDefineArtifactTypes", "Manually define artifact types");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.clickOnAddArtifactTypesFromComponentSetupDialogue(additionalParams);
  }
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewAttributeDataType(Map)}.
   *
   * @throws AWTException thrown when an exceptional condition has occurred within AWT
   */
  @Test
  public void testAttributeDataTypeswithKindofValueSimple() throws AWTException {
    loadPage("dng/attribute_datatype.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/attribute_datatype.html");
    clickNumberToPagePath.put(2, "dng/attribute_datatype.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    additionalParams.put("KIND_OF_VALUE", "Simple");
    additionalParams.put("BASE_DATA_TYPE", "integer");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.createNewAttributeDataType(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewAttributeDataType(Map)}.
   *
   * @throws Exception handles Exception.
   */
  @Test
  public void testAttributeDataTypesWithKindofValueEnumerated() throws Exception {
    loadPage("dng/attribute_datatype_enum.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/attribute_datatype_enum_multiple.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/attribute_datatype_enum.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    additionalParams.put("KIND_OF_VALUE", "Enumerated list of values");
    additionalParams.put("MULTIPLE_VALUES", "low,medium,high");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.createNewAttributeDataType(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewAttributeDataType(Map)}.
   *
   * @throws Exception handles Exception.
   */
  @Test
  public void testAttributeDataTypeswithKindofValueBounded() throws Exception {
    loadPage("dng/attribute_datatype_boundary.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/attribute_datatype_boundary.html");
    clickNumberToPagePath.put(2, "dng/attribute_datatype_boundary.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    additionalParams.put("KIND_OF_VALUE", "Bounded range of values");
    additionalParams.put("BASE_DATA_TYPE", "integer");
    additionalParams.put("MAXIMUM_VALUE", "10");
    additionalParams.put("MINIMUM_VALUE", "2");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.createNewAttributeDataType(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.
   */
  @Test
  public void testInputNameForArtifactType() {
    loadPage("dng/defination_filed.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    String name = "Artifact Types* " + DateUtil.getCurrentDateAndTime();
    rm.inputNameForComponentProperties(name);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.
   */
  @Test
  public void testInputNameForAttributeType() {
    loadPage("dng/defination_filed_artifact_attribute.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    String name = "Artifact Attribute* " + DateUtil.getCurrentDateAndTime();
    rm.inputNameForComponentProperties(name);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewArtifactAttributeDataType(Map)}.
   */
  @Test
  public void testAddArtifactAttributeDataType() {
    loadPage("dng/artifact_attribute.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/artifact_attribute.html");
    clickNumberToPagePath.put(2, "dng/artifact_attribute.html");
    additionalParams.put("DATA_TYPE", "Boolean");
    additionalParams.put("NUMBER_OF_VALUES", "Multiple values allowed");
    additionalParams.put("INITIAL_VALUES", "accepted,new/changed,in clarification");
    additionalParams.put("URI", "http://jazz.net/ns/dm/linktypes#attribute");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
     rm.createNewArtifactAttributeDataType(additionalParams);
   // assertTrue(attributeName.contains("Test Attriubte"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewArtifactAttributeDataType(Map)}.
   */
  @Test
  public void testAddArtifactAttributeDataTypeOneValue() {
    loadPage("dng/artifact_attribute_one_value.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/artifact_attribute_one_value.html");
    clickNumberToPagePath.put(2, "dng/artifact_attribute_one_value.html");
    additionalParams.put("DATA_TYPE", "Boolean");
    additionalParams.put("NUMBER_OF_VALUES", "One value allowed");
    additionalParams.put("SINGLE_VALUE", "accepted");
    additionalParams.put("URI", "http://jazz.net/ns/dm/linktypes#attribute");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    //List<String> attributeName = rm.createNewArtifactAttributeDataType(additionalParams);
    rm.createNewArtifactAttributeDataType(additionalParams);
    //assertTrue(attributeName.contains("Test Attriubte"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewArtifactType(Map)}.
   */
  @Test
  public void testCreateNewArtifactType() {
    loadPage("dng/addattribute.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/artifact_types.html");
    additionalParams.put("DEFAULT_ARTIFACT_FORMAT", "Text");
    additionalParams.put("ATTRIBUTE_DATA_TYPE", "Channeling");
    additionalParams.put("URI", "http://jazz.net/ns/dm/linktypes#attribute");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
  //  List<String> attributeName = rm.createNewArtifactType(additionalParams);
    rm.createNewArtifactType(additionalParams);
    //assertTrue(attributeName.contains("B-Type"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewReqIFDefinition(Map)}.
   */
  @Test
  public void testCreateNewReqIFDefinition() {
    loadPage("dng/reqif_definition_save.html");
    Map<String, String> additionalParams = new HashMap<>();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/reqif_definition.html");
    clickNumberToPagePath.put(2, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(3, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(4, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(5, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(6, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(7, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(8, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(9, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(10, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(11, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(12, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(13, "dng/export_reqif_artifact_window.html");
    clickNumberToPagePath.put(14, "dng/reqif_definition_save.html");
    additionalParams.put("BUTTON_TYPE", "Add Artifact...");
    additionalParams.put("INCLUDE_TYPE", "Folders");
    additionalParams.put("URI", "http://jazz.net/ns/dm/linktypes#attribute");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    List<String> attributeName = rm.createNewReqIFDefinition(additionalParams);
    assertTrue(attributeName.contains("Test"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#exportReqIF(String, String)}.
   */
  @Test
  public void testExportReqIF() {
    loadPage("dng/export.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/export.html");
    clickNumberToPagePath.put(2, "dng/export.html");
    clickNumberToPagePath.put(3, "dng/export_download.html");
    clickNumberToPagePath.put(4, "dng/export_download.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    List<String> reqifexport = rm.exportReqIF("Test", "Export...");
    assertTrue(reqifexport.contains("Completed : Exported file passed validation."));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#exportReqIF(String, String)}.
   */
  @Test
  public void testExportDelete() {
    loadPage("dng/export_dropdown_menu.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/export_dropdown_menu.html");
    clickNumberToPagePath.put(2, "dng/export_dropdown_menu.html");
    clickNumberToPagePath.put(3, "dng/export_delete.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    List<String> reqifexport = rm.exportReqIF("Copy of Test", "Delete...");
    assertFalse(reqifexport.contains("Copy of Test"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#exportReqIF(String, String)}.
   */
  @Test
  public void testExportCopy() {
    loadPage("dng/export.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/export.html");
    clickNumberToPagePath.put(2, "dng/export.html");
    clickNumberToPagePath.put(3, "dng/export_copy.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    List<String> reqifexport = rm.exportReqIF("Test", "Create Copy");
    assertTrue(reqifexport.contains("Copy of Test"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#importComponentProperties(Map)}.
   */
  @Test
  public void testImportComponentProperties() {
    loadPage("dng/import_properties_browse.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/import_properties_project.html");
    clickNumberToPagePath.put(2, "dng/import_properties_component.html");
    clickNumberToPagePath.put(3, "dng/import_properties_config.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/import_properties_next.html");
    clickNumberToPagePath.put(7, "dng/import_properties_finish.html");
    clickNumberToPagePath.put(8, "dng/import_properties_close.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    additionalParams.put("PROJECT_AREA", "SYS_TEST_com.bosch.dng.tmpl.default.process.usa2018.2.0_RC1_With_GC");
    additionalParams.put("COMPONENT_NAME", "Component_11");
    additionalParams.put("CONFIG_TYPE", "Streams");
    additionalParams.put("CONFIG_NAME", "Comp1 Initial Stream");
    assertNotNull(rm);
    String ImportMsg = rm.importComponentProperties(additionalParams);
    assertTrue(ImportMsg.contains("The properties were imported successfully."));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#addAttributesToArtifactType(Map)}.
   */
  @Test
  public void testAddAttributesToArtifactType() {
    loadPage("dng/add_attributs_to_artifact_type.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    Map<String, String> additionalParams = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/add_attributs_to_artifact_type_window.html");
    clickNumberToPagePath.put(2, "dng/add_attributs_to_artifact_type_window.html");
    clickNumberToPagePath.put(3, "dng/add_attributs_to_artifact_type_window.html");
    clickNumberToPagePath.put(4, "dng/add_attributs_to_artifact_type_add.html");
    additionalParams.put("ARTIFACT_TYPE", "AT");
    additionalParams.put("ARTIFACT_ATTRIBUTE", "[ALM Eval] Comment (CC-AS)");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    List<String> reqifexport = rm.addAttributesToArtifactType(additionalParams);
    assertTrue(reqifexport.contains("[ALM Eval] Comment (CC-AS)"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#createNewArtifactTemplate(String)}.
   *
   * @throws Exception search the selected artifact.
   */
  @Test
  public void testCreateArtifactTemplate() throws Exception {
    loadPage("dng/create_artifact_template_first.html");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/create_artifact_template_first.html");
    clickNumberToPagePath.put(2, "dng/create_artifact_template.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/create_artifact_template_window.html");
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, "dng/create_artifact_template.html");
    clickNumberToPagePath.put(9, "dng/create_artifact_template_first.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    String artifactType = rm.createNewArtifactTemplate("Test");
    assertTrue(artifactType.equals("Test Plan Collections"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnButtons(String)}.
   */
  @Test
  public void testClickOnButtons() {
    loadPage("dng/showReport.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.clickOnButtons("Show Report");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#setReqIFDefinitionName(String)}.
   */
  @Test
  public void testSetReqIFDefinitionName() {
    loadPage("dng/reqIFName&Description.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    String name = "Export_Name* " + DateUtil.getCurrentDateAndTime();
    rm.setReqIFDefinitionName(name);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#setComponentPropertiesDescription(String)}.
   */
  @Test
  public void testSetComponentPropertiesDescription() {
    loadPage("dng/reqIFName&Description.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    String name = "Export_Desc* " + DateUtil.getCurrentDateAndTime();
    rm.setComponentPropertiesDescription(name);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnReqIFDropdownOption(String, String)}.
   */
  @Test
  public void testClickOnReqifDropdownOptionExport() {
    loadPage("dng/clickOnReqIFDropDown.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.clickOnReqIFDropdownOption("Export_Name_04_11_2020_18_11_468","Export...");
  }
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnReqIFDropdownOption(String, String)}.
   */
  @Test
  public void testClickOnReqifDropdownOptionDelete() {
    loadPage("dng/clickOnReqIFDropDown.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.clickOnReqIFDropdownOption("Export_Name_04_11_2020_18_11_468","Delete...");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#includeTypeForReqIF(String)}.
   */
  @Test
  public void testIncludeTypeForReqIF() {
    loadPage("dng/clickOnCheckboxIfNotChecked.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.includeTypeForReqIF("Links");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#includeTypeForReqIF(String)}.
   */
  @Test
  public void testclickOnCheckboxIfNotCheckedTwo() {
    loadPage("dng/clickOnCheckboxUnChecked.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.includeTypeForReqIF("Links");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#searchArtifact(String, String)}.
   */
  @Test
  public void testSearchArtifact() {
    loadPage("dng/searchArtifact.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.searchArtifact("Select Modules for Export", "173276");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#importComponentProperties(Map)}.
   */
  @Test
  public void testSelectArtifact() {
    loadPage("dng/searchArtifact.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.selectArtifact("Select Modules for Export", "173276");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnButtonFromCreatingReqIFFileWindow(String)}.
   */
  @Test
  public void testClickOnCreatingReqIFFileButon() {
    loadPage("dng/showReport.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.clickOnButtonFromCreatingReqIFFileWindow("Download");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#switchTowindowTab()}.
   */
  @Test
  public void testswitchTowindowTab() {
    loadPage("dng/showReport.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.switchTowindowTab();
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#switchToWindow()}.
   */
  @Test
  public void testswitchToWindow() {
    loadPage("dng/clickOnCheckboxIfNotChecked.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.switchToWindow();
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#getDownloadedReqIFFileNameAndPath(String)}.
   */
  @Test
  public void testgetDownededReqFileNameAndPath() {
    loadPage("dng/SelectViewNotSelected.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.getDownloadedReqIFFileNameAndPath("Export_Name_ 12_11_2020_18_11_119");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#selectModuleViewForReqIF(String)}.
   */
  @Test
  public void testSelectModuleViewForReqIFOne() {
    loadPage("dng/selectModuleViewForReqIF.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.selectModuleViewForReqIF("Only Features View");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#selectModuleViewForReqIF(String)}.
   */
  @Test
  public void testSelectModuleViewForReqIFTwo() {
    loadPage("dng/SelectViewNotSelected.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.selectModuleViewForReqIF("Only Features View");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#getSummaryOfExportedReqIF(String)}.
   */
  @Test
  public void testGetSummaryOfExportedReqIF() {
    loadPage("dng/exportReqIFReport.htm");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    String EXPORTED_SUMMARY =
        "Exported 1 Modules.Exported 32 Artifacts.Exported 7 Links.Exported 2 Artifact Types.Exported Attribute Definitions (13 total).Exported 1 Link Types.Exported 6 Data Types.Exported 0 Views.Exported 1 tagsExported 31 tag associationsExported 4 Folders.";
    String actual = rm.getSummaryOfExportedReqIF("Exported");
    assertEquals(EXPORTED_SUMMARY, actual);
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#isWidgetOpened(String)}.
   */
  @Test
  public void testIsWidgetOpened() {
    loadPage("dng/searchArtifact.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    assertEquals("Select Modules for Export", rm.isWidgetOpened("Select Modules for Export"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#isReqIFDefinitionSaved(String)}.
   */
  @Test
  public void testIsReqIFDefinitionSaved() {
    loadPage("dng/isReqifDefinitionSaved.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.isReqIFDefinitionSaved("Export_Name* 25_11_2020_18_11_98");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnButtons(String)}.
   */
  @Test
  public void testclickOnButtons() {
    loadPage("dng/showReport.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.clickOnButtons("Download");

  }
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnButtons(String)}.
   */
  @Test
  public void testIsProgressBarReached100Percent() {
    loadPage("dng/showReport.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.isProgressBarReached100Percent();

  }
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnButtons(String)}.
   */
  @Test
  public void testClickOnTab() {
    loadPage("dng/showReport.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.isProgressBarReached100Percent();

  }
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnButtons(String)}.
   */
  @Test
  public void testClickOnManageProjectPropertiesTab() {
    loadPage("dng/export.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.clickOnManageProjectPropertiesTab("ReqIF");

  }
  /**
   * Unit test for  {@link RMManageComponentPropertiesPage#selectViewForReqIF(String,String,String)}
   */
  @Test
  public void testSelectViewForReqIF() {
    loadPage("dng/selectViewForReqIF00.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    Map<Integer, String> findwhatInModuledlg = new HashMap<>();
    findwhatInModuledlg.put(1, "dng/selectViewForReqIF01.html");
    findwhatInModuledlg.put(2, null);
    findwhatInModuledlg.put(3, null);
    findwhatInModuledlg.put(4, null);
    findwhatInModuledlg.put(5, null);
    loadNewPageOnNthDriverClickElementCall(findwhatInModuledlg);
    rm.selectViewForReqIF("Add Views to the ReqIF Definition","Public Module Views","View For Export ReqIF");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#checkModuleNamesInReqIFDefinition(String,String,String,String)}.
   */
  @Test
  public void testCheckModuleNamesInReqIFDefinition() {
    loadPage("dng/isModulesNameAreSame.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.checkModuleNamesInReqIFDefinition("01_SysRS_AHC+R_Platform","00_SysRS_CA_NewMechatronics_Platform","SYS Requirement Specification","Edit_view");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#deleteArtifactType(String)}.
   * @author PDU6HC
   */
  @Test
  public void testDeleteArtifactType() {
    loadPage("dng/testDeleteArtifactType.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testDeleteArtifactType01.html");
    clickNumberToPagePath.put(2, "dng/testDeleteArtifactType02.html");
    clickNumberToPagePath.put(3, "dng/testDeleteArtifactType03.html");
    clickNumberToPagePath.put(4, "dng/testDeleteArtifactType04.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteArtifactType("B");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#clickOnDeleteAnArtifactType(String)}.
   * @author PDU6HC
   */
  @Test
  public void testClickOnDeleteAnArtifactType() {
    loadPage("dng/testDeleteArtifactType.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/testDeleteArtifactType01.html");
    clickNumberToPagePath.put(2, "dng/testDeleteArtifactType02.html");
    clickNumberToPagePath.put(3, "dng/testDeleteArtifactType03.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnDeleteAnArtifactType("B");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#isNoticeMessageDisplay()}.
   * @author PDU6HC
   */
  @Test
  public void testIsNoticeMessageDisplay() {
    loadPage("dng/testIsNoticeMessageDisplay.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    rm.isNoticeMessageDisplay();
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMManageComponentPropertiesPage#verifyPropertiesTabsOrder(String)}.
   * @author VDY1HC
   */
  @Test
  public void testVerifyPropertiesTabsOrder() {
    String listOfTabs = "Artifact Types;Artifact Attributes;Attribute Data Types;" +
        "Link Types;Link Constraints;Link Validity;Module Options;Templates;" +
        "Team Ownership Overview;Configuration Management;ReqIF;Migration;Mapping Contexts";
    loadPage("dng/verifyPropertiesTabsOrder_00.html");
    RMManageComponentPropertiesPage rm = getJazzPageFactory().getRMManageCompProperties();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/verifyPropertiesTabsOrder_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.verifyPropertiesTabsOrder(listOfTabs);
  }
  
}

