/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.JazzDashboardPage;

/**
 * Unit tests coverage for the JazzDashboardPage.
 */
public class JazzDashboardPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads dashboard of user profile page and addNewTab(final String tabName, final int noOfColumn) will create a new
   * tab with the given name and given number of column layouts. In this case 3.
   */
  @Test
  public void testAddNewTab() {
    loadPage("ccm/add_new_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<Integer, String> clickToPagePath = new LinkedHashMap<Integer, String>();
    clickToPagePath.put(1, "ccm/click_to_edit.html");
    clickToPagePath.put(2, "ccm/textbox_newtab.html");
    clickToPagePath.put(3, "ccm/before_dropdown.html");
    clickToPagePath.put(4, "ccm/new_tab_layout.html");
    clickToPagePath.put(5, "ccm/layout_column.html");
    loadNewPageOnNthDriverClickElementCall(clickToPagePath);
    assertNotNull(jazz);
    jazz.addNewTab("TestTab", "2 Columns");
  }

  /**
   * Loads dashboard of user profile page and changeTabLayout(final String tabName, final String noOfColumns) will
   * change the column layout to the given number. In this case 2.
   */
  @Test
  public void testChangeTabLayout() {
    loadPage("ccm/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<Integer, String> clickToPagePath = new LinkedHashMap<Integer, String>();
    clickToPagePath.put(1, "ccm/delete_tab.html");
    clickToPagePath.put(2, "ccm/new_tab_layout.html");
    clickToPagePath.put(3, "ccm/layout_column.html");
    loadNewPageOnNthDriverClickElementCall(clickToPagePath);
    assertNotNull(jazz);
    jazz.changeTabLayout("TestTab", 2);
  }

  /**
   * Loads dashboard of user profile page and changeTabLayout(final String tabName, final String noOfColumns) will
   * change the column layout to the given number. In this case 1.
   */
  @Test
  public void testChangeTabLayouts() {
    loadPage("ccm/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<Integer, String> clickToPagePath = new LinkedHashMap<Integer, String>();
    clickToPagePath.put(1, "ccm/delete_tab.html");
    clickToPagePath.put(2, "ccm/new_tab_layout.html");
    clickToPagePath.put(3, "ccm/layout_column.html");
    loadNewPageOnNthDriverClickElementCall(clickToPagePath);
    assertNotNull(jazz);
    jazz.changeTabLayout("TestTab", 1);
  }

  /**
   * Loads dashboard of user profile page and renameTab(final String oldName, final String newName) will rename the
   * existing tab with the new name.
   */
  @Test
  public void testRenameTab() {
    loadPage("ccm/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<Integer, String> clickToPagePath = new LinkedHashMap<Integer, String>();
    clickToPagePath.put(1, "ccm/click_to_edit.html");
    clickToPagePath.put(2, "ccm/textbox_newtab.html");
    loadNewPageOnNthDriverClickElementCall(clickToPagePath);
    assertNotNull(jazz);
    jazz.renameTab("TestTab", "NewTab");
  }

  /**
   * Loads dashboard of user profile page and ddDashboardWidget(final String tabName, final Enums.widgetCategory
   * widgetCategory, final String widgetName) adds a new widget with the given name to the specified tab.
   */
  @Test
  public void testAddDashboardWidget() {
    loadPage("ccm/delete_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<Integer, String> clickToPagePath = new LinkedHashMap<Integer, String>();
    clickToPagePath.put(1, "ccm/delete_tab.html");
    clickToPagePath.put(2, "ccm/wizard_selection_page.html");
    clickToPagePath.put(3, "ccm/dropdown_select.html");
    clickToPagePath.put(4, "ccm/widget_search.html");
    clickToPagePath.put(5, "ccm/widget_filter.html");
    clickToPagePath.put(6, "ccm/create_widget.html");
    loadNewPageOnNthDriverClickElementCall(clickToPagePath);
    assertNotNull(jazz);
    jazz.addDashboardWidget("TestTab", "All", "News Feed");
  }

  /**
   * Loads change and configuration management all projects page and select project area.
   */
  @Test
  public void selectProjectTest() {

    loadPage("jrs/ccm_select_project_area.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.selectProject("BBM ALM (CCM)_System_Test_Testdata");
  }

  /**
   * Loads change and configuration management dashboard page, click on add widget button.
   *
   * @throws InterruptedException if any thread interrupted the current thread.
   */
  @Test
  public void addWidget() throws InterruptedException {
    loadPage("jrs/add_widget_1.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_widget_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jazz.addWidget("Report");
  }

  /**
   * Loads change and configuration management dashboard page, click on save button to save widget on dashboard.
   *
   * @throws InterruptedException if any thread interrupted the current thread.
   */
  @Test
  public void saveWidgetTest() throws InterruptedException {
    loadPage("jrs/save_widget_1.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/save_widget_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jazz.saveWidget();
  }

  /**
   * Loads change and configuration management dashboard page, click on new tab button.
   */
  @Test
  public void addNewTabInDashboardTest() {
    loadPage("jrs/add_widget_1.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.addNewTabInDashboard();
  }

  /**
   * Open test will take the current url and use to open.
   */
  @Test
  public void openTest() {
    loadPage("jrs/add_widget_1.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.open(driver.getCurrentUrl());
  }

  /**
   * Open test will take the current url and use to open.
   */
  @Test
  public void openTests() {
    loadPage("jrs/add_widget_1.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    Map<String, String> clickNumberToPagePath = new HashMap<>();
    assertNotNull(jazz);
    jazz.open(driver.getCurrentUrl(), "test", clickNumberToPagePath);
  }
  /**
   * Loads Dashboard page and Add 'New Tab' in dashboard.
   */
  @Test
  public void testAddNewTabInDashboard() {
    loadPage("ccm/add_new_tab.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.addNewTabInDashboard();
  }
  /**
   * Loads Dashboard page and edit the tab name.
   */
  @Test
  public void testEditTabName() {
    loadPage("ccm/click_to_edit.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/textbox_newtab.html");
    clickNumberToPagePath.put(2,null );
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jazz.editTabName("TestTab");
  }
  /**
   * Loads Dashboard page and click on 'Add Widget' link in dashboard.
   */
  @Test
  public void testClickOnAddWidgetLink() {
    loadPage("jrs/new_tab_personal_dashboard.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.clickOnAddWidgetLink();
  }
  /**
   * Loads Dashboard page and select category in dashboard.
   */
  @Test
  public void testSelectCategory() {
    loadPage("jrs/widget_category_personal_dashboard.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.selectCategory("Project/Team");
  }
  /**
   * Loads Dashboard page and verify dialog is displayed.
   */
  @Test
  public void testIsDialogVisible() {
    loadPage("jrs/added_widget_personal_dashboard.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    Assert.assertTrue(jazz.isDialogVisible("My Projects"));
  }
  /**
   * Loads Dashboard page and verify tab is deleted.
   */
  @Test
  public void testIsTabDeleted() {
    loadPage("jrs/new_tab_personal_dashboard.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    Assert.assertTrue(jazz.isTabDeleted("test tab"));
  }
  /**
   * Loads Dashboard page and Add widget to dashboard.
   */
  @Test
  public void testAddWidgetToDashboard() {
    loadPage("jrs/widget_category_personal_dashboard.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.addWidgetToDashboard("My Projects");
  }
  /**
   * Loads Dashboard page and remove the added widget.
   */
  @Test
  public void testrRemoveWidget() {
    loadPage("jrs/remove_widgets.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/delete_popup.html");
    assertNotNull(jazz);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    jazz.removeWidget("My Requirements Projects ");
  }
  /**
   * Loads Dashboard page and verify dialog is removed.
   */
  @Test
  public void testIsDialogRemoved() {
    loadPage("jrs/widget_category_personal_dashboard.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    Assert.assertFalse(jazz.isDialogRemoved("My Projects"));
  }
  /**
   * Loads Dashboard page and click on button.
   */
  @Test
  public void testClickOnButtons() {
    loadPage("jrs/widget_category_personal_dashboard.html");
    JazzDashboardPage jazz = getJazzPageFactory().getJazzDashboardPage();
    assertNotNull(jazz);
    jazz.clickOnButtons("Save");
  }

}
