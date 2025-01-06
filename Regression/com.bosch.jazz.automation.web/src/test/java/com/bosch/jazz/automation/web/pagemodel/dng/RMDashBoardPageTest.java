/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.ConfigurationManagementEnums.CurrentConfig;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;

/**
 * Unit test coverage for the methods of RMDashBoardPage.
 */
public class RMDashBoardPageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#getListOfAllPersonalDashBoardNames()}.
   */
  @Test
  public void testGetListOfAllPersonalDashBoardNames() {
    loadPage("dng/All Personal Dashboards.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.getListOfAllPersonalDashBoardNames().size() > 0);
  }


  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#getListOfWidgetsOnDashboard()}.
   */
  @Test
  public void testGetListOfWidgetsOnDashboard() {
    loadPage("dng/ListOfWidgetsOnDashboard.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.getListOfWidgetsOnDashboard().size() > 0);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#isDashboardSaved()}.
   */
  @Test
  public void testisDashboardSaved() {
    loadPage("dng/isDashboardSaved.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.isDashboardSaved());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#isPersonalDashBoardSaved()}.
   */
  @Test
  public void testisPersonalDashBoardSaved() {
    loadPage("dng/isPersonalDashBoardSaved.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.isPersonalDashBoardSaved());
  }

  /**
   * <p>
   * Unit test to verify {@link AbstractRMPage#selectTheConfigContext(Map)}.
   */
  @Test
  public void testSelectTheConfigContext() {
    loadPage("dng/select_theconfig_Context.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/select_theconfig_Context.html");
    clickNumberToPagePath.put(2, "dng/selectTheConfigContext_window.html");
    clickNumberToPagePath.put(3, "dng/selectTheConfigContext_window.html");
    clickNumberToPagePath.put(4, "dng/select_theconfig_Context_window_files/com.ibm.rdm.web.html");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Streams", "Streams");
    additionalParams.put("streamName", "component_13");
    additionalParams.put("type", "Switch");
    additionalParams.put("configuration", "Requirements Management Configuration");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectTheConfigContext(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#isSomeUserLoggedIn()}.
   */

  @Test
  public void testIsSomeUserLoggedIn() {
    loadPage("dng/dashboard_is_some_user_logged_in.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.isSomeUserLoggedIn());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#isPersonalDashBoardSaved()}.
   */
  @Test
  public void testIsPersonalDashBoardSaved() {
    loadPage("dng/isPersonalDashBoardSaved.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.isPersonalDashBoardSaved());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#openPersonalDashboard()}.
   */
  @Test
  public void testOpenPersonalDashboard() {
    loadPage("dng/openPersonalDashboard.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    rm.openPersonalDashboard();
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#clickOnAllPersonalDashboardLink(String)}.
   */

  @Test
  public void testClickOnAllPersonalDashboardLink() {
    loadPage("dng/clickOnAllPersonalDashboardLink.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    rm.clickOnAllPersonalDashboardLink("All Personal Dashboards");
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#createDashboard(String)}.
   */

  @Test
  public void testCreateDashboard() {
    loadPage("dng/create_dashboard.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/create_dashboard_window.html");
    clickNumberToPagePath.put(2, "dng/create_dashboard_window.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createDashboard("Default");
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#createConfig(Map)}.
   */
  @Test
  public void testCreateConfig() {
    loadPage("dng/create_baseline_or_stream_change_set_review.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_NAME", "Test_Change set " + DateUtil.getCurrentDateAndTime());
    additionalParams.put("CONFIG_OPTION", CurrentConfig.CREATECHANGESET.toString());
    rm.createConfig(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#createConfig(Map)}.
   */
  @Test
  public void testCreateConfigDiscardChangeSet() {
    loadPage("dng/create_config_discard.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_NAME", "Test_Baseline " + DateUtil.getCurrentDateAndTime());
    additionalParams.put("CONFIG_OPTION", "Discard Change Set...");
    rm.createConfig(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#createConfig(Map)}.
   */

  @Test
  public void testCreateConfigStream() {
    loadPage("dng/create_baseline_or_stream_stream.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_NAME", "Test_Stream " + DateUtil.getCurrentDateAndTime());
    additionalParams.put("CONFIG_OPTION", CurrentConfig.CREATESTREAM.toString());
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/create_baseline_or_stream_ok.html");
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createConfig(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#createComponent(String[])}.
   */

  @Test
  public void testCreateComponent() {
    loadPage("dng/create_component_admin.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    String name[] = {
        "Test_Stream " + DateUtil.getCurrentDateAndTime(),
        "Manage Components and Configurations",
        CurrentConfig.CREATESTREAM.toString() };
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/create_component_option.html");
    clickNumberToPagePath.put(2, "dng/create_component_window.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/create_component_finish.html");
    clickNumberToPagePath.put(5, "dng/create_component_close.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createComponent(name);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#archiveComponent()}.
   */

  @Test
  public void testArchiveComponent() {
    loadPage("dng/create_component_admin.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/create_component_option.html");
    clickNumberToPagePath.put(2, "dng/archive_component_button.html");
    clickNumberToPagePath.put(3, "dng/archive_component_dropdown.html");
    clickNumberToPagePath.put(4, "dng/archive_component_dropdown.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.archiveComponent();
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#modifyComponentNameAndDescription(String[])}.
   */
  @Test
  public void testModifyComponentNameAndDescription() {
    loadPage("dng/create_component_admin.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    String[] name = {
        "DNGTestAutomationComp" + DateUtil.getCurrentDateAndTime(),
        "Component Created By DNGAutoTeam",
        "SYS-TEST-com.bosch.dng_PA_6.0.3GA_With_CM",
        "Comp1",
        " Name Changed By DNGAutoTeam",
        " Desc Change By DNGAutoTeam" };
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/create_component_option.html");
    clickNumberToPagePath.put(2, "dng/archive_component_button.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/modify_component_name_and_description.html");
    clickNumberToPagePath.put(5, "dng/modify_component_name_and_description.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.modifyComponentNameAndDescription(name);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#selectConfigMenu(Map)}.
   */
  @Test
  public void testSelectConfigMenuChangeset() {
    loadPage("dng/selectconfigmenu.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/selectconfigmenu1.html");
    clickNumberToPagePath.put(2, "dng/selectconfigmenuchangeset.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_OPTION", CurrentConfig.CREATECHANGESET.toString());
    additionalParams.put("STREAM_NAME", "TestComponnetMay3 Initial Stream");
    rm.selectConfigMenu(additionalParams);

  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#selectConfigMenu(Map)}.
   */
  @Test
  public void testSelectConfigMenuStream() {
    loadPage("dng/selectconfigmenu.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/seselect_config_menu_stream.html");
    clickNumberToPagePath.put(2, "dng/select_config_menu.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_OPTION", CurrentConfig.CREATESTREAM.toString());
    additionalParams.put("STREAM_NAME", "TestComponnetMay3 Initial Stream");
    rm.selectConfigMenu(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#selectConfigMenu(Map)}.
   */
  @Test
  public void testSelectConfigMenuBaseline() {
    loadPage("dng/selectconfigmenu.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/selectconfigmenu1.html");
    clickNumberToPagePath.put(2, "dng/selectconfigmenubaseline.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_OPTION", CurrentConfig.CREATEBASELINE.toString());
    additionalParams.put("STREAM_NAME", "TestComponnetMay3 Initial Stream");
    rm.selectConfigMenu(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#clickOnCurrentConfigurationDropdownMenu(String)}.
   */
  @Test
  public void testClickOnCurrentConfigurationDropdownMenu() {
    loadPage("dng/clickonconfig.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/clickonconfig1.html");
    clickNumberToPagePath.put(2, "dng/clickonconfig2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnCurrentConfigurationDropdownMenu(CurrentConfig.CREATEBASELINE.toString());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#openMenu(String)}.
   */
  @Test
  public void testOpenMenu() {
    loadPage("dng/open_menu.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/open_menu_artifacts.html");
    clickNumberToPagePath.put(2, "dng/open_menu_artifacts.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.openMenu("Artifacts");

  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#configActionMenu(Map)}.
   */
  @Test
  public void testConfigActionMenuBaseline() {
    loadPage("dng/config_action_menu_baselines_modified.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_NAME", "Stream1 Initial Baseline");
    additionalParams.put("CONFIG_ACTION_OPTION", "Rename");
    additionalParams.put("MODIFIED_NAME", "Modified_BaseLine" + DateUtil.getCurrentDateAndTime());
    rm.configActionMenu(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#configActionMenu(Map)}.
   */
  @Test
  public void testConfigActionMenuArchive() {
    loadPage("dng/config_action_menu_stream.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/config_action_menu_stream.html");
    clickNumberToPagePath.put(2, "dng/config_action_stream_archive.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/config_action_stream_error.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("CONFIG_NAME", "Test Stream");
    additionalParams.put("CONFIG_ACTION_OPTION", "Archive");
    rm.configActionMenu(additionalParams);
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#quickSearch(String)}.
   */
  @Test
  public void testQuickSearch() {
    loadPage("dng/config_action_menu_stream.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.quickSearch("130686: sample"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#expandButtonHidden()}.
   */
  @Test
  public void testExpandButtonHidden() {
    loadPage("dng/expandButtonHidden.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.expandButtonHidden());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#compareConfigrationEnabled()}.
   */
  @Test
  public void testCompareConfigrationEnabled() {
    loadPage("dng/compareConfigrationEnabled.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertTrue(rm.compareConfigrationEnabled());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#checkNumberOfChangesets()}.
   */
  @Test
  public void testCheckNumberOfChangesets() {
    loadPage("dng/check_numberof_changesets_admin.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/check_numberof_changesets_menu.html");
    clickNumberToPagePath.put(2, "dng/check_numberof_changesets.html");
    clickNumberToPagePath.put(3, "dng/check_numberof_changesets_count.html");
    clickNumberToPagePath.put(4, "dng/check_numberof_changesets_count.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.checkNumberOfChangesets());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#checkNumberOfChangesets()}.
   */
  @Test
  public void testCheckNumberOfChangesetsNotEqual() {
    loadPage("dng/check_numberof_changesets_admin.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/check_numberof_changesets_menu.html");
    clickNumberToPagePath.put(2, "dng/check_numberof_changesets.html");
    clickNumberToPagePath.put(3, "dng/check_numberof_changesets_count.html");
    clickNumberToPagePath.put(4, "dng/check_numberof_changesets_count.html");
    clickNumberToPagePath.put(5, "dng/check_numberof_changesets_count.html");
    clickNumberToPagePath.put(6, "dng/check_numberof_changesets_count.html");
    clickNumberToPagePath.put(7, "dng/check_numberof_changesets_after_refresh.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertFalse(rm.checkNumberOfChangesets());
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#compareConfiguration()}.
   */
  @Test
  public void testCompareConfiguration() {
    loadPage("dng/compare_configuration_streams.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/compare_configuration_stream.html");
    clickNumberToPagePath.put(2, "dng/compare_configuration_stream.html");
    clickNumberToPagePath.put(3, "dng/compare_configuration_stream.html");
    clickNumberToPagePath.put(4, "dng/compare_configuration_stream.html");
    clickNumberToPagePath.put(5, "dng/compare_configuration_stream.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.compareConfiguration();
  }


  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#compareConfiguration()}.
   */
  @Test
  public void testCompareConfigurationElseCond() {
    loadPage("dng/compare_configuration_stream_name.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.compareConfiguration();
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#checkCMEnable(String[])}.
   */
  @Test
  public void testCheckCMEnable() {
    loadPage("dng/check_numberof_changesets_admin.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/check_numberof_changesets_menu.html");
    clickNumberToPagePath.put(2, "dng/check_cm_enable.html");
    String[] attribute = { "Configuration Management" };
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rm.checkCMEnable(attribute));
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#getDeliveredArtifact(String)}.
   */
  @Test
  public void testGetDeliveredArtifact() {
    loadPage("dng/get_delivered_change_set.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    assertFalse(rm.getDeliveredArtifact("12345"));
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#chooseChangeSetDeliveryOption(String)}.
   */
  @Test
  public void testChooseChangeSetDeliveryOption() {
    loadPage("dng/choose_changeset_delivery_option_express.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    rm.chooseChangeSetDeliveryOption("Express");
  }

  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#enableChangeSetDeliveryModeForConflicts(String)}.
   */
  @Test
  public void testEnableChangeSetDeliveryModeForConflicts() {
    loadPage("dng/choose_changeset_delivery_option_custom.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    rm.enableChangeSetDeliveryModeForConflicts("Preview changes and manually resolve any conflicts");
  }

  /**
   * Unit test for method ${@link RMDashBoardPage#createComponentUsingTemplate(String, String, String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testCreateComponentUsingTemplate() {
    loadPage("dng/createComponentUsingTemplate.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/createComponentUsingTemplate_07.html");
    clickNumberToPagePath.put(2, "dng/createComponentUsingTemplate_02.html");
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/createComponentUsingTemplate_03.html");
    clickNumberToPagePath.put(6, "dng/createComponentUsingTemplate_04.html");
    clickNumberToPagePath.put(7, "dng/createComponentUsingTemplate_05.html");
    clickNumberToPagePath.put(8, "dng/createComponentUsingTemplate_06.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createComponentUsingTemplate("Component for testing", "Description", "PS_EC_MASTER_TEMPLATE_V8_1");
  }

  /**
   * Unit test for method ${@link RMDashBoardPage#discardChangeset(String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testDiscardChangeset() {
    loadPage("dng/discardChangeset_01.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/discardChangeset_2.html");
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.discardChangeset("test change set");
  }

  /**
   * Unit test for method ${@link RMDashBoardPage#checkCurrentConfigurationsEnabled(String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testCheckCurrentConfigurationsEnabled() {
    loadPage("dng/checkCurrentConfigurationEnabled.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/checkCurrentConfigurationEnabled_01.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.checkCurrentConfigurationsEnabled("Create Baseline...");
  }

  /**
   * Unit test for method ${@link RMDashBoardPage#deliveryChangesToAStream(String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testDeliveryChangesToAStream() {
    loadPage("dng/deliveryChangesToAStream.htm");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/deliveryChangesToAStream_01.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deliveryChangesToAStream("rbg.BEG_MFB_ShrRS_Legal Branch");
  }

  /**
   * Unit test for method ${@link RMDashBoardPage#clickOnNextButtonToDeliverySpecificSection(String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testClickOnNextButtonToDeliverySpecificSection() {
    loadPage("dng/clickNextDeliverySpecificSection.htm");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/clickNextDeliverySpecificSection_01.htm");
    clickNumberToPagePath.put(2, "dng/clickNextDeliverySpecificSection_02.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.clickOnNextButtonToDeliverySpecificSection("Artifacts");
  }

  /**
   * Unit test for method ${@link RMDashBoardPage#selectCheckboxInDropDownWhenDeliveryChanges(String, String, String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testSelectCheckboxInDropDownWhenDeliveryChanges() {
    loadPage("dng/selectCheckboxInDropDownWhenDeliveryChanges.htm");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/selectCheckboxInDropDownWhenDeliveryChanges_01.htm");
    clickNumberToPagePath.put(2, "dng/selectCheckboxInDropDownWhenDeliveryChanges.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectCheckboxInDropDownWhenDeliveryChanges("Filter Artifacts", "Show only modules", "true");
  }

  /**
   * @author UUM4KOR Unit test for method ${@link RMDashBoardPage#clickOnSelectAViewLink()}
   */
  @Test
  public void testClickOnSelectAViewLink() {
    loadPage("dng/selectAview.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);

    rm.clickOnSelectAViewLink();
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         Unit test for method ${@link RMDashBoardPage#saveProjectDashboard()}
   */
  @Test
  public void testSaveProjectDashboard() {
    loadPage("dng/selectAview.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    rm.saveProjectDashboard();
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         Unit test for method ${@link RMDashBoardPage#isViewDataDisplayed(String, String)}
   */
  @Test
  public void testIsViewDataDisplayed() {
    loadPage("dng/setView.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);

    rm.isViewDataDisplayed("All briBk1 SW RS", "briBk1 Fbl SW RS (643387)");
  }


  /**
   * @author UUM4KOR
   *         <p>
   *         Unit test for method ${@link RMDashBoardPage#selectViewValue(String, String)}
   */
  @Test
  public void testSelectViewValue() {
    loadPage("dng/setView2.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/setView.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.selectViewValue("View", "All briBk1 SW RS");
  }

  /**
   * Unit test for method ${@link RMDashBoardPage#quickSearchFilterByProject(String, String)}
   * 
   * @author VDY1HC
   */
  @Test
  public void testQuickSearchFilterByProject() {
    loadPage("dng/quickSearch_00.htm");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/quickSearch_01.htm");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.quickSearchFilterByProject("test", "Current Project");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#selectValuesForRequirementsView(Map)}.
   * 
   * @author LTU7HC
   */
  @Test
  public void testSelectValuesForRequirementsView() {
    loadPage("dng/select_values_Requirements_View.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/select_project_area_Requirements_View.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/select_values_Requirements_View.html");
    clickNumberToPagePath.put(4, "dng/select_component_Requirements_View.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "dng/select_values_Requirements_View.html");
    clickNumberToPagePath.put(8, "dng/select_configuration_Requirements_View.html");
    clickNumberToPagePath.put(9, null);
    clickNumberToPagePath.put(10, null);
    clickNumberToPagePath.put(11, null);
    clickNumberToPagePath.put(12, "dng/select_values_Requirements_View.html");
    clickNumberToPagePath.put(13, "dng/select_module_Requirements_View.html");
    clickNumberToPagePath.put(14, null);
    clickNumberToPagePath.put(15, "dng/select_values_Requirements_View.html");
    clickNumberToPagePath.put(16, "dng/select_view_Requirements_View.html");
    clickNumberToPagePath.put(17, null);
    clickNumberToPagePath.put(18, null);
    clickNumberToPagePath.put(19, "dng/select_values_Requirements_View.html");
    clickNumberToPagePath.put(20, null);
    clickNumberToPagePath.put(21, "dng/click_ok_Requirements_View.html");
    
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("PROJECT", "Control Unit System Development (Requirements)");
    additionalParams.put("COMPONENT", "MO_EcuBasFct");
    additionalParams.put("CONFIGURATION", "System_Testing Initial Development");
    additionalParams.put("MODULE", "AE_TS_25842_Module");
    additionalParams.put("VIEW", "View_SYSRS_Review");
    additionalParams.put("ITEMS_TO_SHOW", "20");
    rm.selectValuesForRequirementsView(additionalParams);
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMDashBoardPage#removeWidgetWithContains(String)}.
   * 
   * @author LTU7HC
   */
  @Test
  public void testRemoveWidgetWithContains() {
    loadPage("dng/select_values_successfully_Requirements_View.html");
    RMDashBoardPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/click_remove_widget_contains_name.html");
    clickNumberToPagePath.put(2, "dng/remove_widget_contains_name.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.removeWidgetWithContains("View_SYSRS_Review");
  }

}
