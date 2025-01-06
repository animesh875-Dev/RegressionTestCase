/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 
package java_test_scripts.EWM;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkTest;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.reporter.Reporter;

*//**
 * @author UUM4KOR
 *//*
public class TS_14344_createAWorkItemFromDropdownMenu extends AbstractFrameworkTest {

  *//**
   * This test case creating new Work Items from drop down menu except Summary, verifying the error message from
   * notification area and Fill Summary then check created Work Item ID.
   *
   * @throws Throwable use to handle any kind of exception.
   *//*
  @Test
  public void tcs_14344_createWIFromDropDownMenu() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String workitemtype = this.testDataRule.getConfigData("WORK_ITEM_TYPE");
    String filedAgainst = this.testDataRule.getConfigData("FILED_AGAINST");
    String ownedby = this.testDataRule.getConfigData("OWNED_BY");
    String errorMsg = "Attribute 'Summary' not set.";

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);

    // Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    getJazzPageFactory().getCCMProjectAreaDashboardPage().refresh();

    // Select the project area.
    getJazzPageFactory().getCCMAllProjectsPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Select Work Items from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    Reporter.logPass("clicked on work items sub menu");

    // Click on Select Type present under the Work Items menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("Select Type");
    Reporter.logPass("clicked on select type");

    // select work item Task Release.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectWorkItemFromCreateWorkitemDialogToCreate(workitemtype);
    Reporter.logPass("select Work item Task");

    // page refreah
    getJazzPageFactory().getCCMProjectAreaDashboardPage().refresh();

    // set attribute Filed Against value.
    getJazzPageFactory().getCCMWorkItemEditorPage()
        .setDropDownAttributeValue(WorkItemAttribute.FILED_AGAINST.toString(), filedAgainst);

    // set attribute Filed Against value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue(WorkItemAttribute.OWNED_BY.toString(),
        ownedby);

    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    String s = getJazzPageFactory().getCCMWorkItemEditorPage().getValidationMessageFromNotificationArea();
    Reporter.logPass(s);

    // validate the validation message from notification area.
    assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().getValidationMessageFromNotificationArea().contains(errorMsg));

    String wiType = "Work item created for Automation " + DateUtil.getCurrentDateAndTime();
    getJazzPageFactory().getCCMWorkItemEditorPage().setAttributeValueInTextBox(WorkItemAttribute.SUMMARY.toString(),
        wiType);

    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();

    // Get work iten ID.
    getJazzPageFactory().getCCMWorkItemEditorPage().getWorkItemID();
    Reporter.logPass(getJazzPageFactory().getCCMWorkItemEditorPage().getWorkItemID());

    // Check save button is enabled
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
  }
}*/