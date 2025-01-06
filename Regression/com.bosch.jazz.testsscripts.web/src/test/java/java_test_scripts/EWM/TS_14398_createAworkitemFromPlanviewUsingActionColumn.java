/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author UUM4KOR
 */
public class TS_14398_createAworkitemFromPlanviewUsingActionColumn extends AbstractFrameworkTest {

  /**
   * This test case creating new Work Items from plan view using action column and Fill Summary, Field Against,owned by
   * and check created Work Item then validating work item created in plan.
   *
   * @throws Throwable Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_14398_createWIFromPlanViewUsingActionColumn() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String planphase = this.testDataRule.getConfigData("PLAN_PHASE");
    String viewas = this.testDataRule.getConfigData("VIEW_AS");
    String planname = this.testDataRule.getConfigData("PLAN_NAME");
    String summary = this.testDataRule.getConfigData("SUMMARY");
    String filedAgainst = this.testDataRule.getConfigData("FILED_AGAINST");
    String ownedBy = this.testDataRule.getConfigData("OWNED_BY");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + " : project area opened successfully.");
    // Select plans from Work Items drop down menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.PLANS.toString());
    Reporter.logPass(CCMMenus.PLANS.toString() + " : menu opened successfully.");
    // click on All Plans sub menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("All Plans");
    Reporter.logPass("All Plans" + " : sub menu opened successfully.");

    // search plan phase.
    getJazzPageFactory().getCCMPlanPage().searchBox(planphase);
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    Reporter.logPass(planphase + " : plan phase search successfully.");
    // click on welcome to work items sub menu.
    getJazzPageFactory().getCCMPlanPage().clickOnPlanPhase(planphase);
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    Reporter.logPass(planphase + " : Plan phase opend successfully.");
    // select plan view as from drop down.
    getJazzPageFactory().getCCMPlanPage().viewAs(viewas);
    Reporter.logPass("Plan view as : " + viewas + " selected successfully.");
    // Clicking on selected SubButtons work item story.
    getJazzPageFactory().getCCMPlanPage().selectWorkItemToCreateFromLeftSideActionButtonOfPlanItems(planname, "Story");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    Reporter.logPass("Story" + " Work item selected successfully.");
    // click on sub button Edit Work Item
    getJazzPageFactory().getCCMPlanPage().openDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu("",
        "Edit Work Item");
    Reporter.logPass(" Work item edit : page opened successfully.");
    // set summary value.
    String workitemName =
        getJazzPageFactory().getCCMEditWorkItemDialogInPlanPage().setAttributeValueInTextBox("Summary", summary);
    Reporter.logPass("Work item summary : " + summary + " sets successfully.");
    // set field Against value
    getJazzPageFactory().getCCMEditWorkItemDialogInPlanPage()
        .setDropDownAttributeValue(WorkItemAttribute.FILED_AGAINST.toString(), filedAgainst);
    Reporter.logPass("Work item field against : " + filedAgainst + " sets successfully.");
    // set owned By value.
    getJazzPageFactory().getCCMEditWorkItemDialogInPlanPage()
        .setDropDownAttributeValue(WorkItemAttribute.OWNED_BY.toString(), ownedBy);
    Reporter.logPass("Work item owned by : " + ownedBy + " sets successfully.");
    // click on Save and Close.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzSpanButtons("Save and Close");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    Reporter.logPass("Work item saved successfully.");
    // validate work item is saved in plan.
    getJazzPageFactory().getCCMPlanPage().isWorkItemDisplayedUnderPlanItem(summary);
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    // validat the work item is saved in plan.
    Assert.assertTrue(getJazzPageFactory().getCCMPlanPage().isWorkItemDisplayedUnderPlanItem(workitemName));
    Reporter.logPass("Work item saved in plan and validation done successfully");

  }
}
