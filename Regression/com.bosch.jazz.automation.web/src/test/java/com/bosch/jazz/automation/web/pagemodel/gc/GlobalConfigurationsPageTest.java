/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This page represents GlobalConfigurations Page.
 */
public class GlobalConfigurationsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads GC page and click on 'Create Baseline...' button.
   */
  @Test
  public void testClickOnCreateBaselineButton() {
    loadPage("gc/verify_create_baseline.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    rqm.clickOnCreateBaselineButton();
  }

  /**
   * Loads GC page and create Baseline from 'Create Baseline...' button.
   */
  @Test
  public void testCreateBaseline() {
    loadPage("gc/create_baseline.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.createBaseline("test", "data", "", "");
  }

  /**
   *
   */
  @Test
  public void testSelectTabInConfigurationPage() {
    loadPage("gc/verify_create_baseline.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.selectTabInConfigurationPage("Attributes");
  }

  /**
   *
   */
  @Test
  public void testArchive() {
    loadPage("gc/verify_archive.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.archive();
  }

  /**
   *
   */
  @Test
  public void testConfirmArchive() {
    loadPage("gc/verify_confirm_archive.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.confirmArchive();
  }

  /**
   *
   */
  @Test
  public void testClickOnButtonInConfigPage() {
    loadPage("gc/archiveTestT1.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.clickOnButtonInConfigPage("Save");
  }

  /**
   *
   */
  @Test
  public void testOpenActionMenu() {
    loadPage("gc/verify_open_action_menu.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    Map<String, String> clicKToPage = new LinkedHashMap<String, String>();
    clicKToPage.put("Streams", "Test001 Initial Development");
    try {
      rqm.openActionMenu(clicKToPage);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }

  /**
   *
   */
  @Test
  public void testClickFromActionMenu() {
    loadPage("gc/verify_open_action_menu.html");
    GlobalConfigurationsPage rqm = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(rqm);
    rqm.clickFromActionMenu("");
  }

  /**
   *
   */
  @Test
  public void testAddConfiguration() {
    loadPage("gc/AddConfiguration_1.html");
    GlobalConfigurationsPage gc = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(gc);
    Map<String, String> parms = new LinkedHashMap<>();
    parms.put("Configuration", "Requirements Management");
    parms.put("ConfigType", "Baselines");
    parms.put("ConfigName", "CAT Test Automation PA with CM (Requirements) Initial Stream");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "gc/configurationPicker.html");
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    gc.addConfiguration(parms);
  }
  
  /**
   * Unit test for searchAndSelectStream{@link GlobalConfigurationsPage#expandOrCollapse(String, String, String)}
   * @author LTU7HC
   */
 @Test
 public void testExpandOrCollapse() {
   loadPage("gc/Global Configuration Management - expandOrCollapse.html");
   GlobalConfigurationsPage gc = getJazzPageFactory().getGlobalConfigurationsPage();
   assertNotNull(gc);
   Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
   clickToPage.put(1, null);
   clickToPage.put(2, null);
   clickToPage.put(3, null);
   gc.expandOrCollapse("Component_Test_25_04_2022_18_04_147 Initial Development test", "null", "expand");
 }
 
 /**
  * Unit test for {@link GlobalConfigurationsPage#verifyThatOpenNewAddedConfigurationSucessfully(Map)}
  * @author LTU7HC
  */
  @Test
  public void testVerifyThatOpenConfigurationSucessfully() {
    loadPage("gc/Component ALM_System - RM.html");
    GlobalConfigurationsPage gc = getJazzPageFactory().getGlobalConfigurationsPage();
    assertNotNull(gc);
    Map<String, String> params = new LinkedHashMap<>();
    params.put("expectedProjectArea", "ALM Test (RM)");
    params.put("applicationName", "RM");
    params.put("expectedComponent", "ALM_System");
    params.put("expectedStream", "ALM_System_AcceptanceTest_Platform");
    assertTrue(gc.verifyThatOpenNewAddedConfigurationSucessfully(params));
  }
  
  /**
   * Unit test for {@link GlobalConfigurationsPage#closeCurrentWindowAndSwitchToAnother}
   * @author LTU7HC
   */
   @Test
   public void testCloseCurrentWindowAndSwitchToAnother() {
     loadPage("gc/Component ALM_System - RM.html");
     GlobalConfigurationsPage gc = getJazzPageFactory().getGlobalConfigurationsPage();
     assertNotNull(gc);
     gc.closeCurrentWindowAndSwitchToAnother();
   }
   
   /**
    * Unit test for {@link GlobalConfigurationsPage#switchToWindowWithTitleContains(String)}
    * @author LTU7HC
    */
    @Test
    public void testSwitchToWindowWithTitleContains() {
      loadPage("gc/Component ALM_System - RM.html");
      GlobalConfigurationsPage gc = getJazzPageFactory().getGlobalConfigurationsPage();
      assertNotNull(gc);
      gc.switchToWindowWithTitleContains("Requirements Management (RM)");
    }
}
