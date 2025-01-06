/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 
package java_test_scripts.EWM;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkTest;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkSection;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemLinkTypes;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemTab;
import com.bosch.jazz.automation.web.reporter.Reporter;

*//**
 * @author UUM4KOR
 *//*
public class TS_14373_createAWorkitemByCreateLinkedTask extends AbstractFrameworkTest {

  *//**
   * This test case creating new Work Items from Link section of a work item except Summary, verifying the error message
   * from notification area and Fill Summary then check created Work Item ID then Removing the linked work item.
   *
   * @throws Throwable use to handle any kind of exception.
   *//*
  @Test
  public void tcs_14373_createAWorkitemByCreateLinkedTask() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String summary = this.testDataRule.getConfigData("SUMMARY");
    String workItemID = this.testDataRule.getConfigData("WORK_ITEM_ID");
    String taskSummary = summary + DateUtil.getCurrentDateAndTime();
    String errorMsg = this.testDataRule.getConfigData("ERROR_MESSAGE");
    String filedAgainst = this.testDataRule.getConfigData("FILED_AGAINST");
    String ownedby = this.testDataRule.getConfigData("OWNED_BY");
    String linkType = this.testDataRule.getConfigData("LINK_TYPE");
    String createLinkType = this.testDataRule.getConfigData("CREATE_LINK_TYPE");
    String dropdownText = this.testDataRule.getConfigData("DROP_DOWN_TEXT");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");

    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Select Work Items from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    Reporter.logPass("clicked on workitems");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);

    // click on welcome to workitems sub menu.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu("Welcome to Work Items");
    Reporter.logPass("clicked on welcome workitems");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);

    // Search for workitem.
    getJazzPageFactory().getCCMWelcomeToWorkItemPage().searchWorkItem(workItemID);
    Reporter.logPass("searching for wi");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);

    // clicked on links tab.
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemTab.LINKS.toString());
    Reporter.logPass("clicked on links tab");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", dropdownText);
    additionalParams.put("linkActions", linkType);
    additionalParams.put("createLinkType", createLinkType);
    getJazzPageFactory().getCCMWorkItemEditorPage().createLink(additionalParams);
    Reporter.logPass("clicked on links tab");

    // set filed against attribute value.
    getJazzPageFactory().getCCMWorkItemEditorPage()
        .setDropDownAttributeValue(WorkItemAttribute.FILED_AGAINST.toString(), filedAgainst);
    Reporter.logPass("filed against text field filled");

    // set owned by attribute value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setDropDownAttributeValue(WorkItemAttribute.OWNED_BY.toString(),
        ownedby);
    Reporter.logPass("Owned by text field filled");

    // click on save.
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("clicked on save button");

    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(1);
    String s = getJazzPageFactory().getCCMWorkItemEditorPage().getValidationMessageFromNotificationArea();
    Reporter.logPass(s);

    // validate the validation message from notification area.
    assertTrue(
        getJazzPageFactory().getCCMWorkItemEditorPage().getValidationMessageFromNotificationArea().contains(errorMsg));

    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);
    Reporter.logPass("validating the error message");

    // set summary value.
    getJazzPageFactory().getCCMWorkItemEditorPage().setAttributeValueInTextBox(WorkItemAttribute.SUMMARY.toString(),
        taskSummary);
    Reporter.logPass("summary text field filled");

    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass("clicked on save button");

    // Check save button is desabled.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass(" Work item saved successfully ");

    // Get newly created work item ID.
    String createdLinkworkItemID = getJazzPageFactory().getCCMWorkItemEditorPage().getWorkItemID();
    Reporter.logPass("Created workitem id: " + createdLinkworkItemID);

    // Search the existing workitem.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(workItemID);
    Reporter.logPass(workItemID + ": Task workitem opened successfully.");
    // refresh the page.
    getJazzPageFactory().getCCMWorkItemEditorPage().refresh();
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab(WorkItemTab.LINKS.toString());
    Reporter.logPass("clicked on links tab");
    getJazzPageFactory().getCCMWorkItemEditorPage().waitForSecs(2);

    // Removing linked work item fron Related links type.
    getJazzPageFactory().getCCMWorkItemEditorPage().removeAllLinks(WorkItemLinkSection.PROCESS_LINKS.toString(),
        WorkItemLinkTypes.RELATED_LINKS.toString());

    // Click on save.
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    Reporter.logPass(createdLinkworkItemID + " : Related link Removed ");

    // Check save button is desabled.
    Assert.assertTrue(getJazzPageFactory().getCCMWorkItemEditorPage().isWorkItemSaved());
    Reporter.logPass(" Work item saved successfully ");
  }
}*/