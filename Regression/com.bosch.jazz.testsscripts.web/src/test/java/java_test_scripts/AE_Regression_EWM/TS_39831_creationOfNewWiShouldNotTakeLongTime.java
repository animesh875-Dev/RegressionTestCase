/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 *
 */
public class TS_39831_creationOfNewWiShouldNotTakeLongTime extends AbstractFrameworkTest {

  /**
   * Verify test case creation, input summary, select Filed Against and Save 
   * should be reflect immediately 
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39831_creationOfNewWiShouldNotTakeLongTime() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");
    String workitemSummary = this.testDataRule.getConfigData("WORKITEM_SUMMARY");
    String workitemType = this.testDataRule.getConfigData("WORKITEM_TYPE");
    String filedAgainst = this.testDataRule.getConfigData("WORKITEM_FILED_AGAINST");    
    String setResolutionInvalid = "Invalid";
    String setStateClose = "close";

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    //Click on Work Item menu bar
    // Select Work Items from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    Reporter.logPass("Clicked on work items sub menu");
    // Click on Select Type present under the Work Items menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("Select Type");
    Reporter.logPass("Clicked on select type");
    // select work item "Task".
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectWorkItemFromCreateWorkitemDialogToCreate(workitemType);
    Reporter.logPass("Selected Work item type: " + workitemType);
    // page refresh
    getJazzPageFactory().getCCMProjectAreaDashboardPage().refresh();
    //Create function to Append Current Date and Time, before insert into Summary
    DateFormat dateFormat = new SimpleDateFormat("MMddyyyy_HHmmss");
    Date date = new Date();    
    String date1= dateFormat.format(date);
    //Input Summary and Current Date Time
    getJazzPageFactory().getCCMWorkItemEditorPage().setAttributeValueInTextBox(WorkItemAttribute.SUMMARY.toString(),workitemSummary + "_" + date1);
    Reporter.logPass("Summary text field filled: " + workitemSummary + "_" + date1);
    // set attribute Filed Against value.
    getJazzPageFactory().getCCMWorkItemEditorPage()
        .setDropDownAttributeValue(WorkItemAttribute.FILED_AGAINST.toString(), filedAgainst); 
    Reporter.logPass("Selected Filed Against value: " + filedAgainst);
    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("Work item saved successfully");
    //Get Work Item ID
    getJazzPageFactory().getCCMWorkItemEditorPage().getWorkItemID();
    Reporter.logPass("Work item ID generated is: " + getJazzPageFactory().getCCMWorkItemEditorPage().getWorkItemID()); 
    
    //Clean up - Delete or Close Work Item
    // Set work item state.
    getJazzPageFactory().getCCMWorkItemEditorPage().setStatus(setStateClose);
    Reporter.logPass("Transition state set as : " + setStateClose);
    // Set work item resolution.
    getJazzPageFactory().getCCMWorkItemEditorPage().setResolution(setResolutionInvalid);
    Reporter.logPass("Resolution set as : " + setResolutionInvalid);
    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("Work item saved successfully");
    // page refresh
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    // Validate State and Resolution
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getStatus().contains("Closed"));
    assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().getResolution().contains("Invalid"));
    Reporter.logPass("Target state is " + getJazzPageFactory().getCCMWorkItemEditorPage().getStatus() +
        " and Target Resolution is " + getJazzPageFactory().getCCMWorkItemEditorPage().getResolution());
    Reporter.logPass("Target State and Resolution validated successfully");
  }
}
