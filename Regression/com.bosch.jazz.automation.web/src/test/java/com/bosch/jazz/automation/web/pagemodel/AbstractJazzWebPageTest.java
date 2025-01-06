/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMManageComponentsAndConfigurationsPage;
// import com.bosch.jazz.automation.web.pagemodel.jrs.JazzDashboardPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMManageProjectPropertiesPage;

/**
 *
 *
 */
public class AbstractJazzWebPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads dashboard of user profile page and addNewTab(final String tabName, final int noOfColumn) will create a new
   * tab with the given name and given number of column layouts. In this case 3.
   */
  @Test
  public void navigateToURLTest() {
    loadPage("ccm/add_new_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.navigateToURL(driver.getCurrentUrl());
  }

  /**
   * Loads dashboard of user profile page and addNewTab(final String tabName, final int noOfColumn) will create a new
   * tab with the given name and given number of column layouts. In this case 3.
   */
  @Test
  public void logOutWithUrlTest() {
    loadPage("jrs");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.logOutWithUrl(driver.getCurrentUrl());
  }

  /**
   * Loads dashboard of user profile page and addNewTab(final String tabName, final int noOfColumn) will create a new
   * tab with the given name and given number of column layouts. In this case 3.
   */
  @Test
  public void addOpenSocialGadgetTest() {
    loadPage("jrs/addWidget1.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "jrs/add_social_gadget_add_button.html");
    clickToPage.put(2, "jrs/add_social_gadget.html");
    clickToPage.put(3, "jrs/add_social_gadget.html");
    clickToPage.put(4, "jrs/addWidget1.html");
    clickToPage.put(5, "jrs/addWidget1.html");
    clickToPage.put(6, "jrs/addWidget1.html");
    clickToPage.put(7, "jrs/addWidget1.html");
    clickToPage.put(8, "jrs/addWidget3.html");
    assertNotNull(jazz);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    jazz.addOpenSocialGadget(
        "https://www.intranet.bosch.com/doku/bbm_alm_opensocialgadgets/DNG/module_search/TEST_2018-10-05/module_search.xml");
  }

  /**
   * Loads dashboard of user profile page and addNewTab(final String tabName, final int noOfColumn) will create a new
   * tab with the given name and given number of column layouts. In this case 3.
   */
  @Test
  public void removeOpenSocialGadgetTest() {
    loadPage("jrs/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "jrs/add_social_gadget_add_button.html");
    clickToPage.put(2, "jrs/add_social_gadget_add_button.html");
    clickToPage.put(3, "jrs/removeWidget1.html");
    clickToPage.put(4, null);
    clickToPage.put(6, null);
  clickToPage.put(7, "jrs/delete_popup.html");
    assertNotNull(jazz);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    jazz.removeOpenSocialGadget("Customize Your Dashboard");
  }

  /**
   * Loads dashboard of user profile page and addNewTab(final String tabName, final int noOfColumn) will create a new
   * tab with the given name and given number of column layouts. In this case 3.
   */
  @Test
  public void verifyPageByTitleTest() {
    loadPage("jrs/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.verifyPageByTitle("Rational Jazz Team Server");
  }

  /**
   * Loads dashboard of user profile page and addNewTab(final String tabName, final int noOfColumn) will create a new
   * tab with the given name and given number of column layouts. In this case 3.
   */
  @Test
  public void titleNotEmptyAndContainsNotTest() {
    loadPage("jrs/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.titleNotEmptyAndContainsNot("Rational Jazz Team Server");
  }

  /**
   * navigateBack use to come to back page.
   */
  @Test
  public void navigateBackTest() {
    loadPage("jrs/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.navigateBack();
  }

  /**
   * navigateBack use to come to back page.
   */
  @Test
  public void getALLWindowsTest() {
    loadPage("jrs/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.getALLWindows();
  }

  /**
   * Open menu item in Requirement management by taking menu href value of it.
   */
  @Test
  public void openMainMenuBymenuTitle() {
    loadPage("rqm/open_Main_Menu_Bymenu_Title.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.openMainMenuByMenuTitle("Construction");
  }

  /**
   * Open menu item in Requirement management by taking menu href value of it.
   */
  @Test
  public void openRQMSection() {
    loadPage("rqm/open_RQM_Section.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.openRQMSection("Summary");
  }

  /**
   * open Artifact Types by taking text value of tabs.
   */
  @Test
  public void openManageComponentProperties() {

    loadPage("rqm/openManageComponentProperties.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.openManageComponentProperties("Artifact Attributes");
  }

  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void openSettingsSubMenuOne() {

    loadPage("dng/openSettingsSubMenu.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.openSettingsSubMenu("Manage Component Properties");
  }
  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void openSettingsSubMenuTwo() {

    loadPage("dng/openSettingsSubMenu.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.openSettingsSubMenu("Create Component");
  }

  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void testClickOnTab() {
    loadPage("rqm/click_on_properties_section.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnTab("Custom Attributes");
  }

  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void testClickOnDailogButton() {
    loadPage("rqm/add_values.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnDialogButton("Add Values", "OK");
  }

  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void testClickOnDailogToolTipButton() {
    loadPage("rqm/set_rqmartifact_template_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnDialogToolTipButton("Create Test Plan Template", "New");
  }

  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void testClickOnLabel() {
    loadPage("rqm/click_on_properties_section.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnLabel("Test Case Categories");
  }

  /**
   * Navigate to Manage Comoponents and Configurations Page of RQM project.
   * Select tab based on lable text: Components, Streams and Baselines.
   * @author KYY1HC
   */
  @Test
  public void testClickOnLabel01() {
    loadPage("rqm/SelectTabOnTheLeftMenu_01.html");
    RQMManageComponentsAndConfigurationsPage rqm = getJazzPageFactory().getRQMManageComponentsAndConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/SelectTabOnTheLeftMenu_02.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.clickOnLabel("Baselines");
  }

  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void testClickOnDialogLabel() {
    loadPage("rqm/click_on_dialog_label.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnDialogLabel("New Custom Section","Section Type:");
  }

  /**
   * Open menu item in change and configuration management by taking menu href value of it. Note: In order to open main
   * menu sub menu should be opened. Class CCMMainMenus, CCMSubMenus stores the href values as string constants with
   * menu item name.
   */
  @Test
  public void testOpenSubMenuUnderSection() {
    loadPage("rqm/open_submenu_under_section.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.openSubMenuUnderSection("Browse","Test Scripts");
  }

  /**
   * Click on a "Browse Components" link in the Manage Component And Configuration page* <br>
   * Unit test to verify {@link AbstractJazzWebPage#clickOnLink(String)}.
   */
  @Test
  public void testClickOnLink() {
    loadPage("dng/click_On_Link.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnLink("Browse Components");
  }

  /**
   * Click on a "Browse Components" link in the Manage Component And Configuration page with a part of text
   */
  @Test
  public void testClickOnLinkWithPartOfText() {
    loadPage("dng/click_On_Link.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnLinkWithPartOfText("Components");
  }
  /**
   * Load RQM test script page and add step values in the 'Description' and 'Expected Result'.
   */
  @Test
  public void testSelectPariticularComp() {
    loadPage("dng/select_pariticular_comp.html");
    AbstractJazzWebPage GC = getJazzPageFactory().getRMArtifactPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/choose_another_component_test.html");
    clickToPage.put(2, "dng/choose_component.html");
    clickToPage.put(3, "dng/choose_component_test.html");
    clickToPage.put(4, null);
    clickToPage.put(5, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(GC);
    GC.selectPariticularComp("clone test", "SYS-TEST-com.bosch.dng.tmpl.default.usa_2018.1.0_RC1_With_CM");
  }

  /**
   * Unit test to verify {@link AbstractJazzWebPage#clickOnJazzSpanButtons(String)}
   */
  @Test
  public void testClickOnJazzSpanButtons() {
    loadPage("dng/clickOnJazzSpanButtons.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnJazzSpanButtons("New Definition...");
  }

  /**
   * Unit test to verify {@link AbstractJazzWebPage#openSettingsSubMenu(String)}
   */
  @Test
  public void testOpenSettingsMenu() {
    loadPage("dng/openSettingsMenu.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.openSettingsSubMenu("Administration");
  }

  /**
   * <p>
   * Unit test covers for ${@link AbstractWebPage#openNewTabWithURL(String)}
   * <p>
   *
   * @author NVV1HC
   * @throws AWTException exception
   */
  @Test
  public void testOpenNewTabWithURL() throws AWTException {
    loadPage("dng/verifySelectReview.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.openNewTabWithURL("https://www.google.com");
  }

  /**
   * <p>
   * Unit test covers for ${@link AbstractJazzWebPage#enableACheckBox(String, String)}
   * <p>
   *
   * @author YNT2HC
   */
  @Test
  public void testEnableACheckBox(){
    loadPage("jrs/enableACheckbox.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.enableACheckBox("Enable multiple paths or add other source artifacts", "withLabel");
  }

  /**
   * <p>
   * Unit test covers for ${@link AbstractWebPage#addTwoNumbers(String,String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testAddTwoNumbers() {
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    int total = rm.addTwoNumbers("1", "2");
    boolean result = (total == 3);
    assertTrue(result);
  }
  
  /**
   * <p>
   * Unit test covers for ${@link AbstractJazzWebPage#removeAllWidgets(String)}
   * <p>
   *
   * @author LTU7HC
   */
  @Test
  public void testRemoveAllWidgets() {
    loadPage("dng/select_values_successfully_Requirements_View.html");
    AbstractJazzWebPage rm = getJazzPageFactory().getRMDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/click_remove_widget_contains_name.html");
    clickNumberToPagePath.put(2, "dng/remove_widget_contains_name.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.removeAllWidgets("AE_TS_25842_Module (1126090) : View_SYSRS_Review");
  }
}
