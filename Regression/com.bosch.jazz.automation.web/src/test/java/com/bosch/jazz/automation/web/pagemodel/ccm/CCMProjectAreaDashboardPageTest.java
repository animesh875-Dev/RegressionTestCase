/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.WebAutomationException;

/**
 * Unit tests for the {@link CCMProjectAreaDashboardPage}
 *
 * @author hrt5kor
 */
public class CCMProjectAreaDashboardPageTest extends AbstractFrameworkUnitTest {

  /**
   *
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Loads an change and configuration management dashboard of an workitem and checks if quickSearch() search the proper
   * work item.
   */

  @Test
  public void testQuickSearch() {
    loadPage("ccm/workItemQuickSearch2.html");
    CCMProjectAreaDashboardPage wi = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(wi);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/workItemQuickSearch2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.quickSearch("458225");
  }

  /**
   * Loads an change and configuration management project area page and checks if openSubMenu("Select Type") clicking on
   * select type from work item drop down menu.
   */
  @Test
  public void testClickOnSelectWorkItemType() {
    loadPage("ccm/projectarea_clickon_select_workitemtype.html");
    CCMProjectAreaDashboardPage wi = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(wi);
    wi.openSubMenu("Select Type");
  }

  /**
   * Loads change and configuration management projectarea page and checks if selectWorkItem() gets selecting the
   * respective work item
   */
  @Test
  public void testSelectWorkItem() {
    loadPage("ccm/projectarea_select_workItem.html");
    CCMProjectAreaDashboardPage wi = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(wi);
    List<String> workItems =
        Arrays.asList("Task", "Change Request", "Need", "Defect", "Defect Fix", "Review", "Problem", "Meeting",
            "Delivery", "Release", "Milestone", "Defect Eval", "Issue", "Relevant Stream", "Adoption Item");
    Iterator itr = workItems.iterator();
    while (itr.hasNext()) {
      wi.selectWorkItemFromCreateWorkitemDialogToCreate((String) itr.next());
    }
  }

  /**
   * Loads change and configuration management project area page and checks if openMainMenu() open the main menu using
   * menu name.
   */
  @Test
  public void testOpenMainMenu() {
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    CCMProjectAreaDashboardPage wi = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(wi);
    List<String> menuHref =
        Arrays.asList("Project Dashboards", "Work Items", "Plans", "Source Control", "Builds", "Reports");
    Iterator itr = menuHref.iterator();
    while (itr.hasNext()) {
      wi.openMenu((String) itr.next());
    }
  }

  /**
   * Loads change and configuration management project area page and checks if openMainMenu() open the main menu using
   * menu name.
   */
  @Test
  public void testOpenMainMenuWithInvalid() {
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    CCMProjectAreaDashboardPage wi = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(wi);
    List<String> menuHref =
        Arrays.asList("Project Dashboards", "Work Items", "Plans", "Source Control", "Stream", "Reports");
    this.thrown.expect(WebAutomationException.class);
    Iterator itr = menuHref.iterator();
    while (itr.hasNext()) {
      wi.openMenu((String) itr.next());
    }
  }

  /**
   * Loads change and configuration management projectarea page and checks if openSubMenu() open the submenu.
   */
  @Test
  public void testOpenSubMenu() {
    loadPage("ccm/projectarea_open_submenu.html");
    CCMProjectAreaDashboardPage wi = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(wi);
    List<String> submenu =
        Arrays.asList("Create Query", "Welcome to Work Items", "My Queries", "Shared Queries", "Task", "Change Request",
            "Need", "Defect", "Defect Fix", "Review", "Problem", "Meeting", "Delivery", "Release");
    Iterator itr = submenu.iterator();
    while (itr.hasNext()) {
      wi.openSubMenu((String) itr.next());
    }
  }

  /**
   * Loads change and configuration management projectarea page and checks if getWorkItemsLists() gets workItem types
   * from the list box
   */
  @Test
  public void testGetWorkItemsLists() {
    loadPage("ccm/projectarea_get_workitems_list.html");
    CCMProjectAreaDashboardPage wi = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(wi);
    List<String> workItems =
        Arrays.asList("Task", "Change Request", "Need", "Defect", "Defect Fix", "Review", "Problem", "Meeting",
            "Delivery", "Release", "Milestone", "Defect Eval", "Issue", "Relevant Stream", "Adoption Item");
    assertEquals(workItems, wi.getWorkItemsList());

  }

  /**
   * Search workitem under the welcomes to work items menu
   */
  @Test
  public void testSearchWi() {
    loadPage("ccm/search_workitems.html");
    CCMWelcomeToWorkItemPage wi = getJazzPageFactory().getCCMWelcomeToWorkItemPage();
    assertNotNull(wi);
    wi.searchWorkItem("308652");
  }

  /**
   * Loads change and configuration management dashboard list of all persaonal dashboard page and check if
   * getListOfAllPersonalDashBoardNames() gets names of all personal dashboard or not.
   */
  @Test
  public void testGetListOfAllPersonalDashBoardNames() {
    loadPage("ccm/dashboard_get_list_of_all_personal_dashboard_names.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.getListOfAllPersonalDashBoardNames();
  }

  /**
   * Loads change and configuration management dashboard and check if isWidgetVisible() saved or not
   */
  @Test
  public void testIsWidgetVisible() {
    loadPage("ccm/dashboard_is_widget_visible.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    assertTrue(dashboard.isWidgetVisible());
  }

  /**
   * Loads change and configuration management dashboard with list of widgets and check if
   * getListOfAllPersonalDashBoardNames() gets names of all Widgets dashboard or not.
   */
  @Test
  public void testGetListOfWidgetsOnDashBoard() {
    loadPage("ccm/projectdashboard_list_of_widgets_on_dash_board.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    assertTrue(dashboard.getListOfWidgetsOnDashBoard().size() > 0);
  }

  /**
   * Loads change and configuration management dashboard page and check if isSomeUserLoggedIn() gets the name of the
   * logged in user or not.
   */
  @Test
  public void testIsSomeUserLoggedIn() {
    loadPage("ccm/dashboard_is_some_user_logged_in.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    assertTrue(dashboard.isSomeUserLoggedIn());
  }

  /**
   * Loads Project dashboards page and check clickOnAddWidgetButton() gets clicking on AddWidget button.
   */
  @Test
  public void testClickOnAddWidgetButton() {
    loadPage("ccm/clickOnAddWidgetButton_1.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/clickOnAddWidgetButton_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(dashboard.clickOnAddWidgetButton());
  }

  /**
   * Loads All personal dashboards page and check clickOnAllPersonalDashboardLink() gets clicking on All personal
   * dashboard.
   */
  @Test
  public void testClickOnAllPersonalDashboardLink() {
    loadPage("ccm/click_on_all_personal_dashboard.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.clickOnAllPersonalDashboardLink();
  }

  /**
   * Loads dashboards page and check clickOnMiniDashbaord() gets ckicking on Mini dashboard.
   */
  @Test
  public void testClickOnMiniDashBoard() {
    loadPage("ccm/dashboard_click_on_mini_dashbaord.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.clickOnMiniDashbaord();
  }

  /**
   * Loads Project dashboards page and check clikOnSelectCategoryDropdown() gets clicking on Select catogory dropdowm.
   */
  @Test
  public void testClikOnSelectCategoryDropdown() {
    loadPage("ccm/dashboard_clik_on_select_category_dropdown.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.clickOnSelectCatalogDropdown();
  }

  /**
   * Loads Project dashboards page and check saveDashboard() saving the dashboard.
   */
  @Test
  public void testSaveDashboard() {
    loadPage("ccm/savedashboard.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.saveDashboard();
  }

  /**
   * Loads Project dashboards page and check chooseCategoryFromTheDropdown() gets selecting the proper Category.
   */
  @Test
  public void testChooseCategoryFromDropdown() {
    loadPage("ccm/dashboard_choose_category_from_drodown.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    dashboard.selectCatalogDropdownValue("Change and Configuration Management");
  }

  /**
   * Loads Project dashboards page and check searchWidgetByName() gets search the proper Widget.
   */
  @Test
  public void testSearchWidgetByName() {
    loadPage("ccm/dashboard_search_widget_by_name.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.searchWidgetByName("Headlines");
  }

  /**
   * Loads Project dashboards page and check clickOnAddWidgetButton()gets Add Widget to dashboard.
   */
  @Test
  public void testAddWidgetToDashboard() {
    loadPage("ccm/dashboard_add_widget_to_dashboard.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    assertTrue(dashboard.addWidgetToDashboard());
  }

  /**
   * Loads Project dashboards page and check open()gets open project area dashboard.
   */
  @Test
  public void testOpen() {
    loadPage("ccm/projectareadashboard_open.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "SYS-TEST-com.bosch.rtc.tmpl.Formal_2017.2.0_IBM_6.0.5";
    Map<String, String> param = new HashMap<String, String>();
    param.put("", "");
    this.thrown.expect(UnsupportedOperationException.class);
    this.thrown.expectMessage(CoreMatchers.is("Currently Method is not support to any operation"));
    dashboard.open(repositoryURL, projectAreaName, param);
  }


  /**
   * Loads Project dashboards page and check addSocialGadget()gets add SocialGadget.
   */
  @Test
  public void testaddSocialGadget() {
    loadPage("ccm/dashboard_add_social_gadget.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/dashboard_add_social_gadget_url.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    dashboard.addSocialGadget("url");
  }

  /**
   * Loads Project dashboards page and open openPersonalDashboard() page
   */
  @Test
  public void testOpenPersonalDashboard() {
    loadPage("ccm/openPersoanl_Dashboard.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.openPersonalDashboard();
  }

  /**
   * Loads Project dashboards page and search work item with id
   */
  @Test
  public void testSearchForCCMArtifacts() {
    loadPage("ccm/work_item_inputText.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/work_item_search.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String wiNum = "298247";
    dashboard.searchForCCMArtifacts(wiNum);
  }

  /**
   * Loads Project dashboards page and clickOnHomeButtonDropDown clicking on home menu dropdown.
   */
  @Test
  public void testClickOnHomeButtonDropDown() {
    loadPage("ccm/dashboard_add_widget_to_dashboard.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.clickOnHomeButtonDropDown();
  }

  /**
   * <p>
   * Unit test corver for ${@link CCMProjectAreaDashboardPage#createWorkItemsFromWorkItemTemplate(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testCreateWorkItemsFromWorkItemTemplate() {
    loadPage("ccm/createWorkItemsFromWorkItemTemplate_01.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<String, String> params = new HashMap<>();
    params.put("templateName", "Work item template for automation testing UC 173420_update");
    params.put("filedAgainst", "CB Development");
    params.put("plannedFor", "2016 Golf Diesel");
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/createWorkItemsFromWorkItemTemplate_02.html");
    clickNumberToPagePath.put(3, "ccm/createWorkItemsFromWorkItemTemplate_03.html");
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "ccm/createWorkItemsFromWorkItemTemplate_04.html");
    clickNumberToPagePath.put(6, "ccm/createWorkItemsFromWorkItemTemplate_05.html");
    clickNumberToPagePath.put(7, null);
    clickNumberToPagePath.put(8, "ccm/createWorkItemsFromWorkItemTemplate_06.html");
    clickNumberToPagePath.put(9, "ccm/createWorkItemsFromWorkItemTemplate_07.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    List<String> listCreatedWorkItemID = dashboard.createWorkItemsFromWorkItemTemplate(params);
    assertTrue(listCreatedWorkItemID.size() > 0);
  }

  /**
   * <p>
   * Unit test covers for
   * ${@link CCMProjectAreaDashboardPage#getOverviewInformationOfOneWorkItemCreatedFromWorkItemTemplate(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testGetOverviewInformationOfOneWorkItemCreatedFromWorkItemTemplate() {
    loadPage("ccm/getOverviewInformationOfOneWorkItemCreatedFromWorkItemTemplate.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    dashboard.getOverviewInformationOfOneWorkItemCreatedFromWorkItemTemplate("270960");
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPage#backToQueryPage()}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testBackToQueryPage() {
    loadPage("ccm/getOverviewInformationOfOneWorkItemCreatedFromWorkItemTemplate.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/backToQueryPage.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    dashboard.backToQueryPage();
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPage#getWorkItemIDGenerated(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testGetWorkItemIDGenerated() {
    loadPage("ccm/createWorkItemsFromWorkItemTemplate_07.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    String workItemID = dashboard.getWorkItemIDGenerated("Epic for automation testing TS_20418 default summary");
    assertTrue(workItemID.matches("^[0-9]*$"));
  }

  /**
   * <p>
   * Unit test covers for ${@link CCMProjectAreaDashboardPage#verifyTabLinks(String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyTabLinks() {
    loadPage("ccm/verifyTabLinks.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    // clickNumberToPagePath.put(2, "ccm/verifyTabLinks.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(dashboard.verifyTabLinks("Parent", "270960"));
  }

  /**
   * <p>
   * Unit test covers for
   * ${@link CCMProjectAreaDashboardPage#verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(String, String, String, String, String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate() {
    loadPage("ccm/backToQueryPage.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/verifyOverviewInformationOfWorkItemCreatedByWorkItemTemplate.html");
    // clickNumberToPagePath.put(2, "ccm/verifyTabLinks.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    dashboard.verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(
        "Work item template for automation testing UC 173420-02T1_NonGC_1", "270960",
        "Epic for automation testing UC 173420_Summary", "Epic", "ALM Project", "Sprint 1",
        "Epic for automation testing UC 173420_Description");
  }

  /**
   *
   */
  @Test
  public void testClickOnClose() {
    loadPage("ccm/ClickOnCloseWidget.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/ClickOnCloseWidget_04.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(dashboard.clickOnClose("Closed Work Items by Priority"));
  }

  /**
   * Unit test covers the method {@link CCMProjectAreaDashboardPage#archiveTimelineOrIteration(String)}
   */
  @Test
  public void testArchiveTimelineOrIteration() {
    loadPage("ccm/archiveTimeline.html");
    CCMProjectAreaDashboardPage dashboard = getJazzPageFactory().getCCMProjectAreaDashboardPage();
    assertNotNull(dashboard);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "ccm/archiveTimeline2.html");
    clickNumberToPagePath.put(3, "ccm/archiveTimeline3.html");
    clickNumberToPagePath.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(dashboard.archiveTimelineOrIteration("TimeLine created by Automation 29_09_2022_14_09_685"));
  }
}
