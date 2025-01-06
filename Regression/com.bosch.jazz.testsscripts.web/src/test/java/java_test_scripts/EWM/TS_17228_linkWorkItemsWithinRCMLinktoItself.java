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
 * @author BBO1KOR
 */
public class TS_17228_linkWorkItemsWithinRCMLinktoItself extends AbstractFrameworkTest {

  /**
   * This test case describes the negative scenario linking work items to itself should not be possible.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_17228_linkWorkItemsWithinRCMLinktoItself() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String taskWorkitemid = this.testDataRule.getConfigData("TASK_WORK_ITEM_ID");
    String msg = this.testDataRule.getConfigData("MESSAGE");
    String workItem = this.testDataRule.getConfigData("WORK_TEM");
    String dropdownText = this.testDataRule.getConfigData("DROP_DOWN_TEXT");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search the existing workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(taskWorkitemid);
    Reporter.logPass(taskWorkitemid + ": Task WorkItem opened successfully.");
    // Navigate to Links tab in defect work item.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemEnums.WorkItemTab.LINKS.toString());
    Reporter.logPass(WorkItemEnums.WorkItemTab.LINKS.toString() + ": Tab opened successfully in Defect workitem.");

    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", dropdownText);
    additionalParams.put("linkActions", "Add Children");
    additionalParams.put("linkTypeID", taskWorkitemid);
    // Verify while adding the same work item 'OK' button disbled.
    Assert.assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams).contains(msg));
    Reporter.logPass("Child WorkItem: " + workItem + " is same as Parent WorkItem");
    // Verify the added child work item is disabled.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemDisabled(workItem));
    Reporter.logPass("WorkItem: " + workItem + " is disabled.");


  }
}
