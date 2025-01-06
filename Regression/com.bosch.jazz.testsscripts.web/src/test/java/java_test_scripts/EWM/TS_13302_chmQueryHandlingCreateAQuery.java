/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMSubMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;


/**
 * This test case says how to create a query in ccm and validates the query result, then delete the created query.
 */
public class TS_13302_chmQueryHandlingCreateAQuery extends AbstractFrameworkTest {

  /**
   * This test case says how to create a query in ccm and validates the query result, then delete the created query.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13302_QueryHandlingCreate_a_Query() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String queryName = this.testDataRule.getConfigData("QUERY_NAME");
    String type = "Conditions";
    String id = this.testDataRule.getConfigData("WORKITEM_ID");
    String querySummary = this.testDataRule.getConfigData("QUERY_SUMMARY");
    String ownedBy = this.testDataRule.getConfigData("OWNED_BY");
    String defectWorkItemId = this.testDataRule.getConfigData("WORKITEM_DEFECT_ID");
    String defectQuerySummary = this.testDataRule.getConfigData("DEFECT_QUERY_SUMMARY");


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Select Create Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.CREATE_QUERY.toString());
    Reporter.logPass(CCMSubMenus.CREATE_QUERY.toString() + " Selected from Work Items drop down.");
    // Set Query name in new query.
    getJazzPageFactory().getCCMQueryPage().setQueryName(queryName);
    queryName = getJazzPageFactory().getCCMQueryPage().getQueryname();
    Reporter.logPass("Query name given as " + queryName);
    // Click on Conditions tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(type);
    Reporter.logPass(type + " Tab is selected from new quries");
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("id");
    Reporter.logPass("Clicks on Type Filter Text and filter the attribute");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Id");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().setId(id);
    Reporter.logPass("ID name given as " + id);
    // Click on Save button.
    getJazzPageFactory().getCCMQueryPage().button("Save");
    Reporter.logPass(" Query is saved");
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass(" Query is running");
    // Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName));
    Reporter.logPass(queryName + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Run the searched query.
    getJazzPageFactory().getCCMQueryPage().clickOnQuery(queryName);
    Reporter.logPass(queryName + " is running.");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Validate Id in the searched query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Id").contains(id));
    // Validate Owned By in the searched query.
    Assert.assertTrue(
        getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Owned By").contains(ownedBy));
    // Validate Summary in the searched query.
    Assert.assertTrue(
        getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Summary").contains(querySummary));
    // Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName);
    Reporter.logPass(queryName + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Delete the searched query.
    getJazzPageFactory().getCCMQueryPage().deleteQuery(queryName);
    Reporter.logPass(queryName + " is deleted from My queries.");
    // Select Create Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.CREATE_QUERY.toString());
    Reporter.logPass(CCMSubMenus.CREATE_QUERY.toString() + " Selected from Work Items drop down.");
    // Set Query name in new query.
    getJazzPageFactory().getCCMQueryPage().setQueryName(queryName);
    queryName = getJazzPageFactory().getCCMQueryPage().getQueryname();
    Reporter.logPass("Query name given as " + queryName);
    // Click on Conditions tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(type);
    Reporter.logPass(type + " Tab is selected from new quries");
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select group of condition
    getJazzPageFactory().getCCMQueryPage().selectConditionType("AND", "All must match");
    Reporter.logPass("Group condition selected as All must match");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("Type");
    Reporter.logPass("Clicks on Type Filter Text and filter the attribute");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Type");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().clickOnSelectType("Defect");
    Reporter.logPass("Type is selected as Defect");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("Owned By");
    Reporter.logPass("Clicks on Type Filter Text and filter the attribute");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Name");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set owned by name from the Owned By > Name
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox("Owned By > Name",ownedBy);
    // Click on Save button.
    getJazzPageFactory().getCCMQueryPage().button("Save");
    Reporter.logPass(" Query is saved");
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass(" Query is running");
    // Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName));
    Reporter.logPass(queryName + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Run the searched query.
    getJazzPageFactory().getCCMQueryPage().clickOnQuery(queryName);
    Reporter.logPass(queryName + " is running.");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Validate Id in the searched query.
    Assert.assertTrue(
        getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Id").contains(defectWorkItemId));
    // Validate Owned By in the searched query.
    Assert.assertTrue(
        getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Owned By").contains(ownedBy));
    // Validate Summary in the searched query.
    Assert.assertTrue(
        getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Summary").contains(defectQuerySummary));
    // Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName);
    Reporter.logPass(queryName + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Delete the searched query.
    getJazzPageFactory().getCCMQueryPage().deleteQuery(queryName);
    Reporter.logPass(queryName + " is deleted from My queries.");
  }
}