/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests for the CCMPlanPage
 */
public class CCMPlanPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads change and configuration management plans page and checks if getPlans() gets the proper menus or not.
   */
  @Test
  public void testGetPlans() {
    loadPage("ccm/plans_tab.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    List<String> plans = Arrays.asList("My Current Plans", "Current Plans", "All Plans");
    assertEquals(plans, plan.getPlans());
  }

  /**
   * Loads a change and configuration management plans page and checks if PlanViewSelecteColumnDisplay() gets all the
   * columns texts or not.
   */
  @Test
  public void testGetPlanViewSelecteColumnDisplay() {
    loadPage("ccm/plan_page_add_column.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    List<String> plans = Arrays.asList("Accumulated Time", "Accumulated Time (Read Only)", "Actual Start Date",
        "Affected by Defect", "Affects Plan Item", "Affects Requirement", "Affects Test Case Result", "Attachments",
        "Blocks", "Blocks Test Execution", "Change Sets", "Change Sets", "Change Sets (Remote)",
        "Change Sets That Fill Gaps", "Change Sets That Were Not Promoted", "Children", "ClearCase Activities",
        "ClearCase Versions", "Completion Probability", "Contributes To", "Copied From", "Copies", "Corrected Estimate",
        "Created By", "Creation Date", "Date of Receipt", "Depends On", "Deployment Definition", "Deployment Result",
        "Description", "Deviation (hrs)", "Due Date", "Duplicate Of", "Duplicated By",
        "Earliest Contributes To End Date", "Effective Estimate", "Effort Tracking",
        "Elaborated by Architecture Element", "Engineering Level", "Estimate", "Estimated Delivery Date",
        "Estimated Execution Sum", "Estimation Sum (hrs)", "Execution Progress", "External Id", "Found In",
        "Git Commits", "Implements Requirement", "Included in Builds", "Included in Deployments",
        "Included in Packages", "Included in Promotions", "Intended Use", "Issued By", "Issuer Class", "Maturity Level",
        "Maximal Estimate", "Mentions", "Minimal Estimate", "Modified By", "Modified Date", "Owned By",
        "Packaging Definition", "Packaging Result", "Packaging Summary Work Item", "Parent", "Planned End Date",
        "Planned Estimate (hrs)", "Planned Start Date", "Planned Time Variance", "PM Interface Change Log",
        "PM Interface Element ID", "PM Interface Project Status", "PM Interface Resource Group Assignment",
        "Predecessor", "Previously Promoted Change Sets", "Primary Tag", "Progress", "Project Area",
        "Promoted Build Maps", "Promoted Change Sets", "Promoted Work Items", "Promotion Build Result",
        "Promotion Definition", "Proposed End Date", "Proposed Start Date", "Related", "Related Artifacts",
        "Related Change Request", "Related Test Case", "Related Test Case Result", "Related Test Execution Record",
        "Related Test Plan", "Related Test Script", "Reported Against Builds", "Residual Estimate (hrs)", "Resolution",
        "Resolution Date", "Resolved By", "Resolved By (com.ibm.team.apt.attribute.planitem.resolvedBy)", "Resolves",
        "Responsible Class", "Scheduled Time", "Spent Execution Time", "State Group", "Story Points", "Successor",
        "SVN Revisions", "Task Type", "Tested By Test Case", "Time Spent", "Timeline", "Tracks", "Tracks Requirement",
        "Version", "Work Item Type", "Work Items Included in Packages", "Workflow");
    assertEquals(plans, plan.getPlanViewSelecteColumnDisplay());
  }

  /**
   * Loads change and configuration management plans page and checks if selectedPlan() gets the current Plans tab or
   * not.
   */
  @Test
  public void testSelectedPlan() {
    loadPage("ccm/selected_plan.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    assertEquals("Current Plans", plan.selectedPlan());
  }

  /**
   * Loads change and configuration management plans page and checks if addAttributeInColumnDisplay() gets columns List
   * from columunDisplay or not.
   */
  @Test
  public void testAddAttributeInColumnDisplay() {
    loadPage("ccm/add_attribute_in_column_display.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    List<String> plans = Arrays.asList("Summary", "Id", "Rank", "Tags", "Filed Against", "Planned For", "Story Points",
        "Status", "Resolution", "Owned By");
    assertEquals(plans, plan.addAttributeInColumnDisplay());
  }

  /**
   * Loads change and configuration management plans page and checks if gettingAllPlans() gets all plans name from the
   * particular plan or not.
   */
  @Test
  public void testGettingAllPlans() {
    loadPage("ccm/all_plans.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    List<String> plans = Arrays.asList("UBK Standard ALM Iterations Plan", "KanbanView UBK ALM Dev (Product Backlog)",
        "UBK Standard ALM Dev Product Backlog", "ALM-Dev - Iterations Plan", "Kanban");
    assertEquals(plans, plan.gettingAllPlans());
  }

  /**
   * Loads change and configuration management plan page and checks if listOfPlan() gets list of plans from the plans
   * dropdown or not.
   */
  @Test
  public void testListOfPlan() {
    loadPage("ccm/list_of_plans.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    List<String> plans = Arrays.asList("My Current Plans", "Current Plans", "All Plans", "Phase Plan",
        "Cross-Project Plan", "Release Plan");
    assertEquals(plans, plan.listOfPlan());
  }

  /**
   * Loads change and configuration management plan page and checks if clickonEditPlanView() gets the proper menus.
   */
  @Test
  public void testClickonEditPlanView() {
    loadPage("ccm/edit_Plan.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    plan.clickonEditPlanView();
  }

  /**
   * Loads change and configuration management plans page and checks SearchBox() gets searching the proper plan name.
   */
  @Test
  public void testSearchBox() {
    loadPage("ccm/search_box.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    plan.searchBox("RCM-Overview Backlog filtered");
  }

  /**
   * Loads change and configuration management plans page and checks showProgrescheckbox() gets Clicking on show progres
   * check box in planview.
   */
  @Test
  public void testShowProgrescheckbox() {
    loadPage("ccm/show_progres_checkbox.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    plan.showProgressCheckbox();
  }

  /**
   * Loads change and configuration management plans page and checks if planProgressBar() gets the Attriubte of progres
   * bar is their or not.
   */
  @Test
  public void testPlanProgressBar() {
    loadPage("ccm/plan_progress_bar.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String plans = "New Plan";
    assertTrue(plan.planProgressBar(plans));
  }

  /**
   * Loads change and configuration management plans page and checks if planProgressBar() gets the Attriubte of progres
   * bar is their or not.
   */

  @Test
  public void testPlanProgressBarOne() {
    loadPage("ccm/plan_progress_bar.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String plans = "Plan";
    assertFalse(plan.planProgressBar(plans));
  }

  /**
   * Loads change and configuration management plans page and checks if clickOnSelectPlanType() gets selecting the
   * proper plan type.
   */
  @Test
  public void testClickOnSelectPlanType() {
    loadPage("ccm/click_on_select_planType.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String plans = "Release Plan";
    plan.clickOnSelectPlanType(plans);
  }

  /**
   * Loads change and configuration management plans page and checks if checkingWi() checking the workitem name in plan.
   */
  @Test
  public void testCheckingWi() {
    loadPage("ccm/checking_workitem.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String workItem = "Link to AM";
    assertTrue(plan.checkingWi(workItem));
  }

  /**
   * Loads change and configuration management plans page and checks if clickOnPlanPhase() gets clicking on proper plan
   * in plan phase.
   */
  @Test
  public void testClickOnPlanPhase() {
    loadPage("ccm/click_on_plan_phase.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String plans = "New Plan";
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/plan_phase.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.clickOnPlanPhase(plans);
  }

  /**
   * Loads change and configuration management plans page and checks if exculdeWorkItem() selecting the exculding
   * options in plan.
   */
  @Test
  public void testExculdeWorkItem() {
    loadPage("ccm/plan_exculde_workItem.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String value = "Expression";
    String Type = "type:Change Request";

    plan.excludeWorkItem(value, Type);

  }

  /**
   * Loads change and configuration management plans page and open the plan with planUUID and project area.
   */
  @Test
  public void testOpen() {
    loadPage("ccm/open.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    Map<String, String> params = new HashMap<String, String>();
    params.put("PLAN_UUID", "_bq1MID5EEemjcY1k4_iJpw&planMode");
    plan.open(repositoryURL, projectAreaName, params);
  }

  /**
   * Loads change and configuration management plans page and open the plan with planUUID and project area.
   */
  @Test
  public void testOpenPlan() {
    loadPage("ccm/open_plan.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    String planUUID = "_bq1MID5EEemjcY1k4_iJpw&planMode";
    plan.openPlan(repositoryURL, projectAreaName, planUUID);
  }

  /**
   * Loads change and configuration management plans page and checks if planAttributeStatus() checking the Attribute of
   * Selected Plan
   */
  @Test
  public void testPlanAttributeStatus() {
    loadPage("ccm/plan_attribute_status.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String plans = "All Plans";
    plan.planAttributeStatus(plans);
  }

  /**
   * Loads change and configuration management plans page and checks if quickSearch() checking the Quick search results
   * text.
   */

  @Test
  public void testQuickSearch() {
    loadPage("ccm/quick_search.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/quick_search_plan.html");
    clickNumberToPagePath.put(2, "ccm/quick_search.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String PlanName = "Plan";
    String PlanType = "Phase Plan";
    plan.quickSearch(PlanType, PlanName);
  }

  /**
   * Loads change and configuration management plans page and checks if clickOnActionInPlanPhase() gets clicking on
   * selected SubButtons in Plan.
   */

  @Test
  public void testClickOnActionInPlanPhase() {
    loadPage("ccm/planpage_subbuttons.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String wiName = "Link to AM";
    plan.clickOnActionInPlanPhase(wiName, "Add Link");
  }

  /**
   * Loads change and configuration management plans page and checks if getAttributeValueInPlanPage() checking the
   * attribute values in Planing Tab
   */

  @Test
  public void testGetAttributeValueInPlanPage() {
    loadPage("ccm/get_attribute_value_in_planPage.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String[] plans = { "Id", "Status" };
    plan.getAttributeValueInPlanPage(plans);
  }

  /**
   * Loads change and configuration management plans page and checks if getAttributeValueInPlanPage() checking the
   * attribute value is hidden in Planing Tab
   */

  @Test
  public void testGetAttributeValueInPlanPageOne() {
    loadPage("ccm/get_attribute_value_in_planPage.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String[] plans = { "W", "T" };
    plan.getAttributeValueInPlanPage(plans);
  }

  /**
   * Loads change and configuration management plans page and checks if getAttributeValueInPlanPage() checking the
   * attribute value is equal to "PM Interface Change Log" in Planing Tab
   */

  @Test
  public void testGetAttributeValueInPlanPageTwo() {
    loadPage("ccm/get_attribute_value_in_planPage.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    String[] plans = { "PM Interface Change Log" };
    plan.getAttributeValueInPlanPage(plans);
  }

  /**
   * Loads change and configuration management plans page and checks if getAttributeValueInPlanPage() checking the
   * attribute value is equal to "PM Interface Change Log" in Planing Tab
   */

  @Test
  public void testClickOnLeftSideActionInPlanPhase() {
    loadPage("ccm/Test_clickOnLeftSideActionInPlanPhase_one.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);

    plan.selectWorkItemToCreateFromLeftSideActionButtonOfPlanItems("Iteration 1", "Story");
  }


  /**
   * isNewWorkItemDisplayedInPlan() validating actual value with expected value.
   */
  @Test
  public void testIsNewWorkItemDisplayedInPlan() {
    loadPage("ccm/plan_selectLeftSideActionmenuDropdownValueForNewWI.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);

    plan.isWorkItemDisplayedUnderPlanItem("Work item created for Automation_10_01_2020_13_01_716");
  }

  /**
   * This method clicks on add work item and then click on work item.
   */

  @Test
  public void testAddWorkItemInsidePlan() {
    loadPage("ccm/add_work_item_inside_plan.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.addWorkItemInsidePlan("Task");
  }

  /**
   * This method select work item to create from Left Side Action Button Of Plan Items.
   */
  @Test
  public void testSelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems() {
    loadPage("ccm/test_action_dropdown.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.selectWorkItemToCreateFromLeftSideActionButtonOfPlanItems("Iteration 1", "Story");
  }

  /**
   * This method select plan view as from drop down.
   */
  @Test
  public void testViewAs() {
    loadPage("ccm/test_action_dropdown.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.viewAs("010 Rough Project View");
  }

  /**
   *
   */
  @Test
  public void testFilterItems() {
    loadPage("ccm/plan_typeToFilter.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);

    plan.filterItems("Work item Story created for Automation to Perform Workflow Transition");
  }

  /**
   * Unit test coverage for {@link CCMPlanPage#inputDataForNewWorkItemRow(Map)}. <br>
   * @author VDY1HC
   */
  @Test
  public void testInputDataForNewWorkItemRow() {
    loadPage("ccm/inputDataForNewWorkItemRow.html");
    CCMPlanPage plan = getJazzPageFactory().getCCMPlanPage();
    assertNotNull(plan);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ATTRIBUTE_NAME", "Summary");
    additionalParams.put("ATTRIBUTE_VALUE", "test");
    plan.inputDataForNewWorkItemRow(additionalParams);
  }

}

