/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.SourceControlEnums;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.GlobalConfigurationsPageVerification;

/**
 * this page is related to GlobalConfigurationsPageVerification.
 */
public class GlobalConfigurationsPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickOnCreateBaselineButton(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyClickOnCreateBaselineButton() {
    loadPage("dng/input_Name_In_Create_Config_Dialog.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnCreateBaselineButton("");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickOnCreateBaselineButton(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfyClickOnCreateBaselineButton() {
    loadPage("ccm/login_with_given_password.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnCreateBaselineButton("");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link GlobalConfigurationsPageVerification#verifyCreateBaseline(String, String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyCreateBaseline() {
    loadPage("gc/verify_create_baseline.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyCreateBaseline("Test001 Initial Development test", "", "", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link GlobalConfigurationsPageVerification#verifyCreateBaseline(String, String, String, String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyCreateBaseline() {
    loadPage("gc/verify_create_baseline.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyCreateBaseline("invalid", "", "", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link GlobalConfigurationsPageVerification#verifySelectTabInConfigurationPage(String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifySelectTabInConfigurationPage() {
    loadPage("gc/verify_create_baseline.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTabInConfigurationPage("Attributes", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link GlobalConfigurationsPageVerification#verifySelectTabInConfigurationPage(String, String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfySelectTabInConfigurationPage() {
    loadPage("gc/verify_create_baseline.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectTabInConfigurationPage("Baseline Staging Streams ", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyArchive(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyArchive() {
    loadPage("gc/archiveTestT1.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyArchive("");
  }


  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyConfirmArchive(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyConfirmArchive() {
    loadPage("gc/verify_confirm_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyConfirmArchive("");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyConfirmArchive(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfyConfirmArchive() {
    loadPage("gc/verify_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyConfirmArchive("");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickOnButtonInConfigPage(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyClickOnButtonInConfigPage() {
    loadPage("gc/verify_confirm_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnButtonInConfigPage("Save", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickOnButtonInConfigPage(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfyClickOnButtonInConfigPage() {
    loadPage("gc/verify_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnButtonInConfigPage("Save", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickOnButtonInConfigPage(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfyClIckOnButtonInConfigPage() {
    loadPage("gc/verify_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnButtonInConfigPage("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickOnButtonInConfigPage(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfyClickOnButtonInConfIgPage() {
    loadPage("gc/verify_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnButtonInConfigPage("Cancel", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyOpenActionMenu(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyOpenActionMenu() {
    loadPage("gc/verify_open_action_menu.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("moreLinkValue", "More...");
    rqm.verifyOpenActionMenu(additionalParams, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyOpenActionMenu(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfyOpenActionMenu() {
    loadPage("gc/verify_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("moreLinkValue", "More...");
    rqm.verifyOpenActionMenu(additionalParams, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickFromActionMenu(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyClickFromActionMenu() {
    loadPage("gc/verify_open_action_menu.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickFromActionMenu("Expand All", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyClickFromActionMenu(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfyClickFromActionMenu() {
    loadPage("gc/verify_archive.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickFromActionMenu("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyAddConfiguration(Map,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyCreateComponent() {
    loadPage("gc/verify_click_on_create_component_button.html");
    GlobalConfigurationsPageVerification rqm = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(rqm);
    Map<String, String> params = new HashMap<String, String>();
    params.put(SourceControlEnums.CONFIG_NAME.toString(), "Component_Test_15_09_2020_20_09_909");
    rqm.verifyAddConfiguration(params, "");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyExpandOrCollapse(String, String, String, String)}.
   * <p>
   * @author LTU7HC
   */
  @Test
  public void testVerifyExpandOrCollapse() {
    loadPage("gc/Global Configuration Management - expandOrCollapse.html");
    GlobalConfigurationsPageVerification globalConfigurationsPageVerification = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(globalConfigurationsPageVerification);
    assertEquals("PASSED", globalConfigurationsPageVerification.verifyExpandOrCollapse("Component_Test_25_04_2022_18_04_147 Initial Development test", "null", "expand", "true").getState());
    assertEquals("FAILED", globalConfigurationsPageVerification.verifyExpandOrCollapse("Component_Test_25_04_2022_18_04_147 Initial Development test", "null", "collapse", "false").getState());
    assertEquals("PASSED", globalConfigurationsPageVerification.verifyExpandOrCollapse("AutomationTest Initial Development", "null", "collapse", "true").getState());
    assertEquals("FAILED", globalConfigurationsPageVerification.verifyExpandOrCollapse("AutomationTest Initial Development", "null", "expand", "false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyVerifyThatOpenNewAddedConfigurationSucessfully(Map, String)(Map, String)}.
   * <p>
   * @author LTU7HC
   */
  @Test
  public void testVerifySwitchToNewTabAndVerifyThatOpenConfigurationSucessfully() {
    loadPage("gc/Component ALM_System - RM.html");
    GlobalConfigurationsPageVerification globalConfigurationsPageVerification = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(globalConfigurationsPageVerification);
    Map<String, String> params = new LinkedHashMap<>();
    params.put("expectedProjectArea", "ALM Test (RM)");
    params.put("applicationName", "RM");
    params.put("expectedComponent", "ALM_System");
    params.put("expectedStream", "ALM_System_AcceptanceTest_Platform");
    assertEquals("PASSED", globalConfigurationsPageVerification.verifyVerifyThatOpenNewAddedConfigurationSucessfully(params, "true").getState());
    assertEquals("FAILED", globalConfigurationsPageVerification.verifyVerifyThatOpenNewAddedConfigurationSucessfully(params, "false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyAddConfiguration(Map, String)}.
   * <p>
   * @author LTU7HC
   */
  @Test
  public void testVerifyAddConfiguration() {
    loadPage("gc/ALM_System_AcceptanceTest_Platform - Global Configuration Management.html");
    GlobalConfigurationsPageVerification globalConfigurationsPageVerification = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(globalConfigurationsPageVerification);
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("Configuration", "Requirements Management");
    params.put("ProjectArea", "ALM Test (RM)");
    params.put("GC_Component", "ALM_System");
    params.put("ConfigType", "Streams");
    params.put("ConfigName", "ALM Test (RM) Initial Stream");
    assertEquals("PASSED", globalConfigurationsPageVerification.verifyAddConfiguration(params, "true").getState());
    params.put("ConfigName", "ALM Test (RM) Initial Streams");
    assertEquals("FAILED", globalConfigurationsPageVerification.verifyAddConfiguration(params, "false").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifyCloseCurrentWindowAndSwitchToAnother(String)}.
   * <p>
   * @author LTU7HC
   */
  @Test
  public void testVerifyCloseCurrentWindowAndSwitchToAnother() {
    loadPage("gc/ALM_System_AcceptanceTest_Platform - Global Configuration Management.html");
    GlobalConfigurationsPageVerification globalConfigurationsPageVerification = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(globalConfigurationsPageVerification);
    assertEquals("PASSED", globalConfigurationsPageVerification.verifyCloseCurrentWindowAndSwitchToAnother("true").getState());
  }
  
  /**
   * <p>
   * Unit test coverage for {@link GlobalConfigurationsPageVerification#verifySwitchToWindowWithTitleContains(String,String)}.
   * <p>
   * @author LTU7HC
   */
  @Test
  public void testVerifySwitchToWindowWithTitleContains() {
    loadPage("gc/ALM_System_AcceptanceTest_Platform - Global Configuration Management.html");
    GlobalConfigurationsPageVerification globalConfigurationsPageVerification = getJazzPageFactory().getGlobalConfigurationsPageVerification();
    assertNotNull(globalConfigurationsPageVerification);
    assertEquals("PASSED", globalConfigurationsPageVerification.verifySwitchToWindowWithTitleContains("Global Configuration Management", "true").getState());
    assertEquals("FAILED", globalConfigurationsPageVerification.verifySwitchToWindowWithTitleContains("Requirements Management (RM)", "false").getState());
  }
}
