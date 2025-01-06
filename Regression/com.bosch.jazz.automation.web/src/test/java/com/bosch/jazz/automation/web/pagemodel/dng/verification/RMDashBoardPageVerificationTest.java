/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.ConfigurationManagementEnums.CurrentConfig;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMDashBoardPageVerification;

/**
 * Unit tests for the RMDashBoardPage.
 */
public class RMDashBoardPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifyOpenMenu(String,String)}.
   * <p>
   * Loads RM page and Verifies RM menu is clicked.
   */
  @Test
  public void testVerifyOpenMenu() {
    loadPage("dng/open_menu.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(rdbpv);
    rdbpv.verifyOpenMenu("Artifacts", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifyOpenMenu(String,String)}.
   * <p>
   * Loads RM page and Verifies RM menu is not clicked and throws false error message.
   */
  @Test
  public void testVerIfyOpenMenu() {
    loadPage("dng/open_menu.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyOpenMenu("Invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMDashBoardPageVerification#verifyClickOnCurrentConfigurationDropdownMenu(String,String)}.
   * <p>
   * Loads Requirement Management page and Verifies clicked on 'Create Change Set' option.
   */
  @Test
  public void testVerifyClickOnCurrentConfigurationDropdownMenu_1() {
    loadPage("dng/create_baseline_or_stream_change_set_review.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyClickOnCurrentConfigurationDropdownMenu("Create Change Set...", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMDashBoardPageVerification#verifyClickOnCurrentConfigurationDropdownMenu(String,String)}.
   * <p>
   * Loads Requirement Management page and Verifies clicked on 'Deliver Change Set' option.
   */
  @Test
  public void testVerifyClickOnCurrentConfigurationDropdownMenu_2() {
    loadPage("dng/choose_changeset_delivery_option_express.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyClickOnCurrentConfigurationDropdownMenu("Create Change Set...", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMDashBoardPageVerification#verifyClickOnCurrentConfigurationDropdownMenu(String,String)}.
   * <p>
   * Loads Requirement Management page and Verifies it's not clicked any of the option from 'Current Configuration' drop
   * down.
   */
  @Test
  public void testVerifyClickOnCurrentConfigurationDropdownMenu_3() {
    loadPage("dng/open_menu.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyClickOnCurrentConfigurationDropdownMenu("Create Change Set...", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifyCreateConfig(Map,String)}.
   * <p>
   * Loads Requirement Management page and Verifies change set is created.
   */
  @Test
  public void testVerifyCreateConfig() {
    loadPage("dng/clickonconfig.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "dng/verify_create_config.html");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ConfigName", "test_1234");
    additionalParams.put("ConfigOption", CurrentConfig.CREATECHANGESET.toString());
    rdbpv.verifyCreateConfig(additionalParams, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifyChooseChangeSetDeliveryOption(String,String)}.
   * <p>
   * Loads Requirement Management page and Verifies change set delivery option is selected.
   */
  @Test
  public void testVerifyChooseChangeSetDeliveryOption() {
    loadPage("dng/choose_changeset_delivery_option_express.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyChooseChangeSetDeliveryOption("Express", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifyChooseChangeSetDeliveryOption(String,String)}.
   * <p>
   * Loads Requirement Management page and Verifies change set delivery option is not selected.
   */
  @Test
  public void testverifyChooseChangeSetDeliveryOption() {
    loadPage("dng/choose_changeset_delivery_option_express.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyChooseChangeSetDeliveryOption("Standard", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifySelectTheConfigContext(Map,String)}.
   * <p>
   * Loads Requirement Management page and Verifies switched to 'Global Configuration'.
   */
  @Test
  public void testVerifySelectTheConfigContext() {
    loadPage("dng/verify_select_the_config_context.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("streamName", "rbd_briBk10_jmc_sw");
    rdbpv.verifySelectTheConfigContext(additionalParams, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifySelectTheConfigContext(Map,String)}.
   * <p>
   * Loads Requirement Management page and Verifies switched to 'Global Configuration'.
   */
  @Test
  public void testverifySelectTheConfigContext() {
    loadPage("dng/verify_select_the_config_context.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("streamName", "Invalid");
    rdbpv.verifySelectTheConfigContext(additionalParams, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMDashBoardPageVerification#verifyGetDeliveredArtifact(String,String)}.
   * <p>
   * Loads Requirement Management page and Verifies created artifact is delivered.
   */
  @Test
  public void testVerifyGetDeliveredArtifact() {
    loadPage("dng/get_delivered_change_set.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyGetDeliveredArtifact("213504", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMDashBoardPageVerification#verifyEnableChangeSetDeliveryModeForConflicts(String,String)}.
   * <p>
   * Loads Requirement Management page and Verifies change set delivery option is enabled.
   */
  @Test
  public void testVerifyEnableChangeSetDeliveryModeForConflicts() {
    loadPage("dng/verify_change_set_delivery_option_conflicts.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyEnableChangeSetDeliveryModeForConflicts("Preview changes and manually resolve any conflicts", "");
  }

  /**
   * <p>
   * Unit test coverage for ${@link RMDashBoardPageVerification#verifyArchiveComponent(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyArchiveComponent() {
    loadPage("dng/verifyArchiveComponent.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyArchiveComponent("true");
  }

  /**
   * <p>
   * Unit test coverage for ${@link RMDashBoardPageVerification#verifyArchiveComponent(String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyArchiveComponent() {
    loadPage("dng/verifyArchiveComponent_failed.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyArchiveComponent("true");
  }

  /**
   * <p>
   * Unit test coverage for
   * ${@link RMDashBoardPageVerification#verifyCreateComponentUsingTemplate(String, String, String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyCreateComponentUsingTemplate() {
    loadPage("dng/verifyCreateComponentUsingTemplate.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyCreateComponentUsingTemplate("test create component", "test", "PS_EC_MASTER_TEMPLATE_V8_1", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * ${@link RMDashBoardPageVerification#verifyCreateComponentUsingTemplate(String, String, String, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyCreateComponentUsingTemplate() {
    loadPage("dng/verifyCreateComponentUsingTemplate.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyCreateComponentUsingTemplate("test123", "test", "PS_EC_MASTER_TEMPLATE_V8_1", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * ${@link RMDashBoardPageVerification#verifyCheckCurrentConfigurationsEnabled(String, String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyCheckCurrentConfigurationsEnabled() {
    loadPage("dng/verifyCheckCurrentConfigurationsEnabled.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyCheckCurrentConfigurationsEnabled("Create Baseline...", "true", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * ${@link RMDashBoardPageVerification#verifyCheckCurrentConfigurationsEnabled(String, String, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyCheckCurrentConfigurationsEnabled() {
    loadPage("dng/verifyCheckCurrentConfigurationsEnabled.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyCheckCurrentConfigurationsEnabled("Create Baseline...", "true", "false");
  }

  /**
   * <p>
   * Unit test coverage for ${@link RMDashBoardPageVerification#verifyDiscardChangeset(String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyDiscardChangeset() {
    loadPage("dng/verifyCheckCurrentConfigurationsEnabled.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyDiscardChangeset("Test changeset", "true");
  }

  /**
   * <p>
   * Unit test coverage for ${@link RMDashBoardPageVerification#verifyDiscardChangeset(String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyDiscardChangeset() {
    loadPage("dng/verifyDiscardChangeset_failed.html");
    RMDashBoardPageVerification rdbpv = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rdbpv);
    rdbpv.verifyDiscardChangeset("Test changeset", "false");
  }

  /**
   * Unit test for method ${@link RMDashBoardPageVerification#verifyDeliveryChangesToAStream(String, String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testVerifyDeliveryChangesToAStream() {
    loadPage("dng/deliveryChangesToAStream_01.htm");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyDeliveryChangesToAStream("rbg.BEG_MFB_ShrRS_Legal Branch", "");
  }

  /**
   * Unit test for method
   * ${@link RMDashBoardPageVerification#verifyClickOnNextButtonToDeliverySpecificSection(String, String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testVerifyClickOnNextButtonToDeliverySpecificSection() {
    loadPage("dng/clickNextDeliverySpecificSection_02.htm");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyClickOnNextButtonToDeliverySpecificSection("Artifacts", "");
  }

  /**
   * Unit test for method
   * ${@link RMDashBoardPageVerification#verifySelectCheckboxInDropDownWhenDeliveryChanges(String, String, String, String)}
   *
   * @author VDY1HC
   */
  @Test
  public void testVerifySelectCheckboxInDropDownWhenDeliveryChanges() {
    loadPage("dng/selectCheckboxInDropDownWhenDeliveryChanges_01.htm");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/selectCheckboxInDropDownWhenDeliveryChanges.htm");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.verifySelectCheckboxInDropDownWhenDeliveryChanges("Filter Artifacts", "Show only modules", "true", "false");
  }


  /**
   * @author UUM4KOR Unit test for method
   *         ${@link RMDashBoardPageVerification#verifySelectViewValue(String, String,String)}
   */
  @Test
  public void testVerifySelectViewValue() {
    loadPage("dng/setView.html");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifySelectViewValue("View", "All briBk1 SW RS", "true");
  }

  /**
   * @author UUM4KOR Unit test for method
   *         ${@link RMDashBoardPageVerification#verifySaveProjectDashboard( String,String)}
   */
  @Test
  public void testVerifySaveProjectDashboard() {
    loadPage("dng/setView.html");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifySaveProjectDashboard("Dashboard successfully saved", "true");
  }

  /**
   * @author UUM4KOR Unit test for method
   *         ${@link RMDashBoardPageVerification#verifyIsViewDataDisplayed( String,String, String,String)}
   */
  @Test
  public void testVerifyIsViewDataDisplayed() {
    loadPage("dng/setView.html");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyIsViewDataDisplayed("All briBk1 SW RS", "briBk1 Fbl SW RS (643387)", "briBk1 Fbl SW RS (643387)", "true");
  }
  
  /**
   * Unit test for method ${@link RMDashBoardPageVerification#verifySelectValuesForRequirementsView(Map, String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifySelectValuesForRequirementsView() {
    loadPage("dng/select_values_successfully_Requirements_View.html");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("VIEW", "View_SYSRS_Review");
    rm.verifySelectValuesForRequirementsView(additionalParams, "true");
  }
  
  /**
   * Unit test for method ${@link RMDashBoardPageVerification#verifyRemoveWidgetWithContains(String, String)}
   * @author LTU7HC
   */
  @Test
  public void testVerifyRemoveWidgetWithContains() {
    loadPage("dng/remove_widget_contains_name.html");
    RMDashBoardPageVerification rm = getJazzPageFactory().getRMDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyRemoveWidgetWithContains("View_SYSRS_Review", "true");
  }
}
