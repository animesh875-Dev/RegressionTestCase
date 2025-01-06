/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMManageComponentsAndConfigurationsVerification;

/**
 * @author BBW1KOR
 */
public class RMManageComponentsAndConfigurationsVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationCreateStream() {
    loadPage("dng/verify_open_and_select_sub_menu_create_stream.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Create Stream...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenAndSelectSubMenuInCurrentConfigurationCreateStream() {
    loadPage("ccm/login_with_given_password.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Create Stream...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationCreateBaseline() {
    loadPage("dng/verify_open_and_select_sub_menu_create_stream.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Create Baseline...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationCompareConfiguration() {
    loadPage("dng/verify_open_and_select_sub_menu_compare_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Compare Configuration...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenAndSelectSubMenuInCurrentConfigurationCompareConfiguration() {
    loadPage("ccm/login_with_given_password.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Compare Configuration...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationCreateChangeSet() {
    loadPage("dng/verify_open_and_select_sub_menu_create_changeSet.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Create Change Set..", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenAndSelectSubMenuInCurrentConfigurationCreateChangeSet() {
    loadPage("ccm/login_with_given_password.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Create Change Set..", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationComparewithStream() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Compare with Stream...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationDeliverChangeSet() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Deliver Change Set", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationDiscardChangeSet() {
    loadPage("dng/verify_open_and_select_sub_menu_discard _change_set.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Discard Change Set...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenAndSelectSubMenuInCurrentConfigurationDiscardChangeSet() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Discard Change Set...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationDeliverChanges() {
    loadPage("dng/verify_open_and_select_sub_menu_deliver_changes.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Deliver Changes...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenAndSelectSubMenuInCurrentConfigurationDeliverChanges() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Deliver Changes...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyGetCurrentConfiguration(String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetCurrentConfiguration() {
    loadPage("dng/verify_open_and_select_sub_menu_deliver_changes.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyGetCurrentConfiguration("test");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyGetCurrentConfiguration(String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyGetCurrentConfiguration() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyGetCurrentConfiguration("");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenAndSelectSubMenuInCurrentConfigurationLinktoaWorkItem() {
    loadPage("dng/verify_open_and_select_sub_menu_link_to_a_workitem.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Link to a Work Item...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenAndSelectSubMenuInCurrentConfigurationLinktoaWorkItem() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("Link to a Work Item...", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyOpenAndSelectSubMenuInCurrentConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenAndSelectSubMenuInCurrentConfigurationInvalid() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyOpenAndSelectSubMenuInCurrentConfiguration("invalid", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyCreateConfiguration(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyCreateConfiguration() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyCreateConfiguration("", "", "test");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyCreateConfiguration(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyCreateConfiguration() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyCreateConfiguration("", "", "invalid");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyCreateConfiguration(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyCreateConfIguration() {
    loadPage("dng/compare_configurations_folders.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyCreateConfiguration("", "", "invalid");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifySelectConfigContext(String,String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectConfigContext() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifySelectConfigContext("", "",
        "SYS-TEST-com.bosch.dng.tmpl.default.usa_2018.1.0_RC1_With_CM Initial Stream", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifySelectConfigContext(String,String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectConfigContext() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifySelectConfigContext("", "", "invalid", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyArchiveConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyArchiveConfiguration() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyArchiveConfiguration("148573", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyArchiveConfiguration(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyArchiveConfiguration() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyArchiveConfiguration("148573148573148573invalid", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyIsComponentOrConfigurationLinkDisplayed(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyIsComponentOrConfigurationLinkDisplayed() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyIsComponentOrConfigurationLinkDisplayed("148573", "true");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyIsComponentOrConfigurationLinkDisplayed(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyIsComponentOrConfigurationLinkDisplayed() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyIsComponentOrConfigurationLinkDisplayed("148573", "false");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyCreateComponent(String,String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyCreateComponent() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyCreateComponent("", "", "", "SYS-TEST-com.bosch.dng.tmpl.default.usa_2018.1.0_RC1_With_CM");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnContextMenuForAConfigurations() {
    loadPage("dng/verify_click_on_context_menu_for_aconfigurations_archive.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Archive", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnContextMenuForAConfigurations() {
    loadPage("dng/verify_create_configuration.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Archive", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnContextMenuForAConfigurationsCreateStream() {
    loadPage("dng/verify_open_and_select_sub_menu_create_stream.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Create Stream", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClIckOnContextMenuForAConfigurationsCreateStream() {
    loadPage("ccm/login_with_given_password.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Create Stream", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnContextMenuForAConfigurationsCreateBaseline() {
    loadPage("dng/input_Name_In_Create_Config_Dialog.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Create Baseline", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClIckOnContextMenuForAConfigurationsCreateBaseline() {
    loadPage("ccm/login_with_given_password.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Create Baseline", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnContextMenuForAConfigurationsRename() {
    loadPage("dng/verify_click_on_context_menu_for_aconfigurations_rename.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Rename", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClIckOnContextMenuForAConfigurationsRename() {
    loadPage("ccm/login_with_given_password.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "Rename", "");
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentsAndConfigurationsVerification#verifyClickOnContextMenuForAConfigurations(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClIckOnContextMenuForAConfigurationsInvalid() {
    loadPage("ccm/login_with_given_password.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextMenuForAConfigurations("", "invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyBackToBrowseComponentPage(String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyBackToBrowseComponentPage() {
    loadPage("ccm/verifyBackToBrowseComponentPage.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyBackToBrowseComponentPage("true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyBackToBrowseComponentPage(String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyBackToBrowseComponentPage() {
    loadPage("ccm/verifyBackToBrowseComponentPage_failed.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifyBackToBrowseComponentPage("true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifySearchAndExploreComponent(String, String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifySearchAndExploreComponent() {
    loadPage("ccm/verifySearchAndExploreComponent.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifySearchAndExploreComponent("test 2132", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifySearchAndExploreComponent(String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfySearchAndExploreComponent() {
    loadPage("ccm/verifySearchAndExploreComponent.html");
    RMManageComponentsAndConfigurationsVerification ravt =
        getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(ravt);
    ravt.verifySearchAndExploreComponent("test2", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifySearchComponentInBrowseComponentPage(String, String)}. <br>
   * Cover for the passed case
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyTeamMemberActions() {
    loadPage("dng/search_Component_In_Browse_ComponentPage.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifySearchComponentInBrowseComponentPage("Comp_0ne", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifySearchComponentInBrowseComponentPage(String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyTeamMemberActions() {
    loadPage("dng/search_Component_In_Browse_ComponentPage.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifySearchComponentInBrowseComponentPage("Comp_0neNotExisted", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifySelectTheFirstArchivedComponentInBrowseComponentPage(String)}. <br>
   * Cover for the passed case
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifySelectTheFirstArchivedComponentInBrowseComponentPage() {
    loadPage("dng/SelectTheFirstArchivedComponentInBrowseComponentPage_02_ArchivedComponentSelected.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifySelectTheFirstArchivedComponentInBrowseComponentPage("Component for automation testing TS_25886_11Q_GC_24_06_2021_21_06_784");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifySelectTheFirstArchivedComponentInBrowseComponentPage(String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfySelectTheFirstArchivedComponentInBrowseComponentPage() {
    loadPage("dng/SelectTheFirstArchivedComponentInBrowseComponentPage_02_ArchivedComponentSelected.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifySelectTheFirstArchivedComponentInBrowseComponentPage("Component TS_25886_11Q_GC_24_06_2021_21_06_784");
  }
  
  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyRestoreArchivedComponent(String)}. <br>
   * Verify passed case<br>
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyRestoreArchivedComponent() {
    loadPage("dng/ClickOnRestoreThisComponent_04_ActiveComponent.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifyRestoreArchivedComponent("");
  }
  
  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyRestoreArchivedComponent(String)}. <br>
   * Verify failed case<br>
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyRestoreArchivedComponent() {
    loadPage("dng/ArchiveActiveComponent_03_ArchivedComponentSuccessfully.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifyRestoreArchivedComponent("");
  }
  
  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyArchiveActiveComponent(String)}. <br>
   * Verify passed case<br>
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyArchiveActiveComponent() {
    loadPage("dng/ArchiveActiveComponent_03_ArchivedComponentSuccessfully.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifyArchiveActiveComponent("");
  }
  
  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyArchiveActiveComponent(String)}. <br>
   * Verify failed case<br>
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyArchiveActiveComponent() {
    loadPage("dng/ClickOnRestoreThisComponent_04_ActiveComponent.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifyArchiveActiveComponent("");
  }
  
  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyExploreActiveComponent(String,String)}. <br>
   * Verify failed case<br>
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyExploreActiveComponent() {
    loadPage("dng/ClickOnRestoreThisComponent_04_ActiveComponent.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifyExploreActiveComponent("Component_To_Unarchived_TS_25861","");
  }
  
  /**
   * <p>
   * Unit test coverage for
   * {@link RMManageComponentsAndConfigurationsVerification#verifyExploreActiveComponent(String,String)}. <br>
   * Verify failed case<br>
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyExploreActiveComponent() {
    loadPage("dng/ClickOnRestoreThisComponent_04_ActiveComponent.html");
    RMManageComponentsAndConfigurationsVerification rmmcacv = getJazzPageFactory().getRMManageComponentsAndConfigurationsVerification();
    assertNotNull(rmmcacv);
    rmmcacv.verifyExploreActiveComponent("rbg.BEG_MFB_Modes","");
  }
}
