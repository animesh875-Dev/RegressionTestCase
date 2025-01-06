/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;
import junit.framework.Assert;

/**
 * @author NVV1HC
 */
@SuppressWarnings("deprecation")
public class TS_20418_createWorkItemFromWorkItemTemplateUsingWebClient extends AbstractFrameworkTest {

  /**
   * This test case creating a set of Work Items by using “Work Item Template” feature. and check created Work Item then
   * validating work item created correctly.
   *
   * @throws Throwable Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_20418_createWorkItemFromWorkItemTemplateUsingWebClient() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String WORK_ITEM_TEMPLATE_NAME = this.testDataRule.getConfigData("WORK_ITEM_TEMPLATE_NAME");
    String EPIC_SUMMARY = this.testDataRule.getConfigData("EPIC_SUMMARY");
    String EPIC_DESCRIPTION = this.testDataRule.getConfigData("EPIC_DESCRIPTION");
    String STORY_SUMMARY = this.testDataRule.getConfigData("STORY_SUMMARY");
    String STORY_DESCRIPTION = this.testDataRule.getConfigData("STORY_DESCRIPTION");
    String TASK_SUMMARY_1 = this.testDataRule.getConfigData("TASK_SUMMARY_1");
    String TASK_DESCRIPTION_1 = this.testDataRule.getConfigData("TASK_DESCRIPTION_1");
    String TASK_SUMMARY_2 = this.testDataRule.getConfigData("TASK_SUMMARY_2");
    String TASK_DESCRIPTION_2 = this.testDataRule.getConfigData("TASK_DESCRIPTION_2");
    String FILED_AGAINST = this.testDataRule.getConfigData("FILED_AGAINST");
    String PLANNED_FOR = this.testDataRule.getConfigData("PLANNED_FOR");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + " : project area opened successfully.");

    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMainMenuByMenuTitle("Work Items");
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("Create From Template...");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(2);
    Map<String, String> params = new HashMap<String, String>();
    List<String> listWorkItemID_displayed =
        getJazzPageFactory().getCCMProjectAreaDashboardPage().createWorkItemsFromWorkItemTemplate(params);

    if (listWorkItemID_displayed.size() == 4) {
      String epicID = getJazzPageFactory().getCCMProjectAreaDashboardPage().getWorkItemIDGenerated(EPIC_SUMMARY);
      String storyID = getJazzPageFactory().getCCMProjectAreaDashboardPage().getWorkItemIDGenerated(STORY_SUMMARY);
      String task1ID = getJazzPageFactory().getCCMProjectAreaDashboardPage().getWorkItemIDGenerated(TASK_SUMMARY_1);
      String task2ID = getJazzPageFactory().getCCMProjectAreaDashboardPage().getWorkItemIDGenerated(TASK_SUMMARY_2);

      // verify Epic work item
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage()
          .verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(WORK_ITEM_TEMPLATE_NAME, epicID, EPIC_SUMMARY,
              "Epic", FILED_AGAINST, PLANNED_FOR, EPIC_DESCRIPTION));
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage().verifyTabLinks("Children", storyID));
      getJazzPageFactory().getCCMProjectAreaDashboardPage().backToQueryPage();

      // verify Story work item
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage()
          .verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(WORK_ITEM_TEMPLATE_NAME, storyID,
              STORY_SUMMARY, "Story", FILED_AGAINST, PLANNED_FOR, STORY_DESCRIPTION));
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage().verifyTabLinks("Parent", epicID));
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage().verifyTabLinks("Children", task1ID));
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage().verifyTabLinks("Children", task2ID));
      getJazzPageFactory().getCCMProjectAreaDashboardPage().backToQueryPage();

      // verify Task 1 work item
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage()
          .verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(WORK_ITEM_TEMPLATE_NAME, task1ID,
              TASK_SUMMARY_1, "Task", FILED_AGAINST, PLANNED_FOR, TASK_DESCRIPTION_1));
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage().verifyTabLinks("Parent", storyID));
      getJazzPageFactory().getCCMProjectAreaDashboardPage().backToQueryPage();

      // verify Task 2 work item
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage()
          .verifyOverviewInformationOfWorkItemIsCreatedByWorkItemTemplate(WORK_ITEM_TEMPLATE_NAME, task2ID,
              TASK_SUMMARY_2, "Task", FILED_AGAINST, PLANNED_FOR, TASK_DESCRIPTION_2));
      Assert.assertTrue(getJazzPageFactory().getCCMProjectAreaDashboardPage().verifyTabLinks("Parent", storyID));
      getJazzPageFactory().getCCMProjectAreaDashboardPage().backToQueryPage();
    }
    else {
      Assert.assertTrue("The numnber of work item displayed is different with the number of expected work item", false);
    }
  }
}
