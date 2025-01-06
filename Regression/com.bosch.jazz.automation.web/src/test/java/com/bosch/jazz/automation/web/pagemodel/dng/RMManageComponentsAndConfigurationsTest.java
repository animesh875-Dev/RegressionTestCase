/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit test coverage for the methods of RMManageComponentsAndConfigurationsPage.
 */
public class RMManageComponentsAndConfigurationsTest extends AbstractFrameworkUnitTest {

  /**
   * Loads an management component properties page, click on matching Type.
   */
  @Test
  public void testGetCurrentConfiguration() {
    loadPage("dng/get_Current_Configuration.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    String s = rm.getCurrentConfiguration();
    assertEquals(s, "BBM ALM Dev (RM)");
  }

  /**
   * Test method to input baseline/stream/changeset name
   */
  @Test
  public void testCreateConfiguration() {
    loadPage("dng/createConfiguration1.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    rm.createConfiguration("Baseline1", "Description of Baseline");
  }

  /**
   *
   */
  @Test(expected = Exception.class)
  public void testSelectConfigContext() {
    loadPage("dng/select_Config_Context_1.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/select_Config_Context_2.html");
    clickNumberToPagePath.put(2, "dng/select_Config_Context_3.html");
    clickNumberToPagePath.put(3, "dng/select_Config_Context_5.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/select_Config_Context_7.html");
    clickNumberToPagePath.put(6, "dng/select_Config_Context_8.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectConfigContext("Requirements Management Configuration", "Baseline", "Test_Baseline");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentsAndConfigurations#selectATemplateFromCreateComponentDialog(String)}.
   */
  @Test
  public void testSelectATemplateFromCreateComponentDialog() {
    loadPage("dng/click_Checkbox_From_Create_Component_Dialog.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/select_Template_From_Create_Component_Dialog.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectATemplateFromCreateComponentDialog("DNG Release USA 2016.1.0");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentsAndConfigurations#isComponentOrConfigurationLinkDisplayed(String)}.
   */
  @Test
  public void testIsComponentDisplayedInBrowseComponentPage() {
    loadPage("dng/is_Component_Displayed_In_Browse_Component_Page.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/is_Component_Displayed_In_Browse_Component_Page2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.isComponentOrConfigurationLinkDisplayed("aviation.SW-TC.LLT.Library_BasOp"));
  }

  /**
   * <p>
   * Unit test to verify
   * {@link RMManageComponentsAndConfigurations#clickOnContextMenuForAConfigurations(String, String)}.
   */
  @Test
  public void testClickOnContextMenuForAConfigurations() {
    loadPage("dng/click_On_Context_Menu_For_A_Configurations_1.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/click_On_Context_Menu_For_A_Configurations_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnContextMenuForAConfigurations("Component2 Initial Stream", "Create Baseline");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentsAndConfigurations#searchComponentInBrowseComponentPage(String)}.
   */
  @Test
  public void testSearchComponentInBrowseComponentPage() {
    loadPage("dng/search_Component_In_Browse_ComponentPage.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    assertTrue(rm.searchComponentInBrowseComponentPage("Comp_0ne"));
  }

  /**
   * <p>
   * Unit test to verify
   * {@link RMManageComponentsAndConfigurations#createComponentFromAdministrations(String, String, String)}.
   */
  @Test
  public void testCreateComponentFromAdministrations() {
    loadPage("dng/create_component_1.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/create_component_2.html");
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createComponentFromAdministrations("test_component", "component_description", "DNG Release USA 2016.1.0");
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentsAndConfigurations#isErrorMessageDisplayed(String)}.
   */
  @Test
  public void testIsErrorMessageDisplayed() {
    loadPage("dng/is_error_message_displayed.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    assertTrue(rm.isErrorMessageDisplayed("You are not allowed to restore this component."));
  }

  /**
   * <p>
   * Unit test to verify {@link RMManageComponentsAndConfigurations#isErrorMessageDisplayed(String)}.
   *
   */
  @Test
  public void testIsErrorMessageDisplayedError() {
    loadPage("dng/is_error_message_displayed.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    rm.isErrorMessageDisplayed("test");
  }

  /**
   * Unit test for method ${@link RMManageComponentsAndConfigurations#backToBrowseComponentPage()}
   *
   * @author NVV1HC
   */
  @Test
  public void testBackToBrowseComponentPage() {
    loadPage("dng/backToBrowseComponentPage.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/backToBrowseComponentPage_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.backToBrowseComponentPage();
  }

  /**
   * Unit test for method ${@link RMManageComponentsAndConfigurations#searchAndExploreComponent(String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testSearchAndExploreComponent() {
    loadPage("dng/searchAndExploreComponent.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/searchAndExploreComponent_01.html");
    clickNumberToPagePath.put(3, "dng/searchAndExploreComponent_02.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.searchAndExploreComponent("test112421");
  }

  /**
   * Unit test for method ${@link RMManageComponentsAndConfigurations#selectTheFirstArchivedComponentInBrowseComponentPage()}
   *
   * @author KYY1HC
   */
  @Test
  public void testSelectTheFirstArchivedComponentInBrowseComponentPage() {
    loadPage("dng/SelectTheFirstArchivedComponentInBrowseComponentPage_01_ListOfArchivedComponent.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/SelectTheFirstArchivedComponentInBrowseComponentPage_02_ArchivedComponentSelected.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertEquals("Component for automation testing TS_25886_11Q_GC_24_06_2021_21_06_784", rm.selectTheFirstArchivedComponentInBrowseComponentPage());
  }

  /**
   * Unit test for method ${@link RMManageComponentsAndConfigurations#restoreArchivedComponent()}
   *
   * @author KYY1HC
   */
  @Test
  public void testRestoreArchivedComponent() {
    loadPage("dng/ClickOnRestoreThisComponent_01_RestoreThisComponentButton.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/ClickOnRestoreThisComponent_02_ConfirmRestoreDialog.html");
    clickNumberToPagePath.put(2, "dng/ClickOnRestoreThisComponent_03_RestoreSuccessfullyDialog.html");
    clickNumberToPagePath.put(3, "dng/ClickOnRestoreThisComponent_04_ActiveComponent.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.restoreArchivedComponent();
  }

  /**
   * Unit test for method ${@link RMManageComponentsAndConfigurations#archiveActiveComponent()}
   *
   * @author KYY1HC
   */
  @Test
  public void testArchiveActiveComponent() {
    loadPage("dng/ArchiveActiveComponent_01_ArchiveThisComponentButton.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/ArchiveActiveComponent_02_ConfirmArchiveDialog.html");
    clickNumberToPagePath.put(2, "dng/ArchiveActiveComponent_03_ArchivedComponentSuccessfully.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.archiveActiveComponent();
  }

  /**
   * Unit test for method ${@link RMManageComponentsAndConfigurations#exploreActiveComponent(String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testExploreActiveComponent() {
    loadPage("dng/ExploreActiveComponent_01_ButtonDisplayed.html");
    RMManageComponentsAndConfigurations rm = getJazzPageFactory().getRMManageComponentsAndConfigurations();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/ExploreActiveComponent_02_NavigateToThisComponent.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.exploreActiveComponent("Component_To_Unarchived_TS_25861");
  }
}
