/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.WorkItemEnums;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author UUM4KOR
 */
public class TS_14404_linkWorkItemsWithinCCM extends AbstractFrameworkTest {

  /**
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_14404_linkWorkItemsWithinCCM() throws Throwable {


    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String taskWorkItemId = this.testDataRule.getConfigData("TASK_WORKITEMID");
    String epicParentWorkItemId = this.testDataRule.getConfigData("EPIC_PARENTWORKITEMID");
    String reviewWorkItemId = this.testDataRule.getConfigData("REVIEW_WORKITEMID");
    String defectWorkItemId = this.testDataRule.getConfigData("DEFECT_WORKITEMID");
    String storyWorkItemId = this.testDataRule.getConfigData("STORY_WORKITEMID");
    String projectForContributes = this.testDataRule.getConfigData("PROJECT_FOR_CONTRIBUTES");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(storyWorkItemId);
    Reporter.logPass(storyWorkItemId + ": Story WorkItem opened successfully.");
    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");


    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Set Parent");
    additionalParams.put("linkTypeID", epicParentWorkItemId);

    // Select 'Tested By Test Case' option from 'Links' section and create a link.
    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams);
    Reporter.logPass("New test case -----------" + epicParentWorkItemId + "-------- is created in Links section ");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Save the defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnJazzButtons("Save");
    Reporter.logPass(storyWorkItemId + " saved successfully.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Verify test case is added in the work item.

    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(epicParentWorkItemId));


    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", epicParentWorkItemId,
        "Parent");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(storyWorkItemId));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", storyWorkItemId,
        "Children");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(epicParentWorkItemId));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(epicParentWorkItemId);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(taskWorkItemId);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    Map<String, String> additionalParams1 = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Contributes To");
    additionalParams.put("ccmProjectArea", projectForContributes);

    additionalParams.put("linkTypeID", storyWorkItemId);


    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams1);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(storyWorkItemId));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", storyWorkItemId,
        "Contributes To");

    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);


    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(taskWorkItemId));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", taskWorkItemId,
        "Tracks");

    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(storyWorkItemId));

    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(storyWorkItemId);

    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    // +++++++++++++++++++++++++++++++++++

    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(reviewWorkItemId);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    Map<String, String> additionalParams2 = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("linkActions", "Add Related");
    additionalParams.put("linkTypeID", defectWorkItemId);


    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams2);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(defectWorkItemId));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", defectWorkItemId,
        "Related");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(reviewWorkItemId));

    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", reviewWorkItemId,
        "Related");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemAddedInProcessLinkSection(defectWorkItemId));
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);

    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(defectWorkItemId);
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(5);
  }

}
