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
 * @author BBO1KOR
 */
public class TS_13300_chmQueryHandlingEditColumnDisplaySortingQueriesResults extends AbstractFrameworkTest {

  /**
   * This test case editing and sorting result column of query and Verifying query is saved as per modification done in
   * the query.
   * 
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13300_QueryHandling_EditColumn_Display_SortingQueries() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String queryName = this.testDataRule.getConfigData("QUERY_NAME");
    String queryTab = "Column Display";
    String parent = this.testDataRule.getConfigData("PARENT");
    String child = this.testDataRule.getConfigData("CHILDREN");
    String id = this.testDataRule.getConfigData("ID");
    String summary = this.testDataRule.getConfigData("SUMMARY");

    // Log into alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Search for the existing query.
    getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName);
    Reporter.logPass(queryName + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Click on 'Edit' for the searched query.
    getJazzPageFactory().getCCMQueryPage().editQuery(queryName);
    Reporter.logPass("User clicked on Edit query icon");
    // Click on 'Column Display' tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab);
    Reporter.logPass(queryTab + " tab is selected from the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Add the 'Id' attribute in 'Select Attributes'wizard using 'Add Column'link.
    getJazzPageFactory().getCCMQueryPage().addColumn("Id");
    // Add the 'Summary' attribute in 'Select Attributes'wizard using 'Add Column'link.
    getJazzPageFactory().getCCMQueryPage().addColumn("Summary");
    // Set the column order
    getJazzPageFactory().getCCMQueryPage().setColumnOrder("Result Columns", "Id", "Down");
    Reporter.logPass("Column Order is set");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Click on 'Save' button.
    getJazzPageFactory().getCCMQueryPage().button("Save");
    Reporter.logPass("Query is Saved");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    // Navigate to 'Column Display' tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab);
    Reporter.logPass(queryTab + " tab is selected from the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    // Add 'Id' attribute in 'Select Attribute' wizard of 'Add Sort Column' link.
    getJazzPageFactory().getCCMQueryPage().addSortColumn("Id");
    // Set the column order of the attribute in the query.
    getJazzPageFactory().getCCMQueryPage().setColumnOrder("Result Columns", "Id", "Up");
    // Set the Sorting Order Type in the query.
    getJazzPageFactory().getCCMQueryPage().setSortingOrderType("Id", "Ascending");
    Reporter.logPass("Attribute is Sorted");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Click on 'Save'button.
    getJazzPageFactory().getCCMQueryPage().button("Save");
    Reporter.logPass("Query is Saved");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    // Navigate to 'Column Display' tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab);
    Reporter.logPass(queryTab + " tab is selected from the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    // Enable 'Show Link' check box and add 'Parent' attribute in Select Attribute wizard of Add Column link.
    getJazzPageFactory().getCCMQueryPage().addColumn("Parent");
    Reporter.logPass("Parent attribute is added");
    // Enable 'Show Link' check box and add 'Children' attribute in Select Attribute wizard of Add Column link.
    getJazzPageFactory().getCCMQueryPage().addColumn("Children");
    Reporter.logPass("Children attribute is added");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Click on 'Save'button.
    getJazzPageFactory().getCCMQueryPage().button("Save");
    Reporter.logPass("Query is Saved");
    // Click on 'Run'button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is Running");

    // Verify the Content of the each attribute of the work item as displayed in the query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Id").contains(id));
    Assert
        .assertTrue(getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Summary").contains(summary));
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Parent").contains(parent));
    Assert
        .assertTrue(getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Children").contains(child));
    // Verify Work item 'Id' is sorted as per Sort order in the Query results page.
    String COL_VAL = getJazzPageFactory().getCCMQueryPage().getListOfContentsForEachColumn("Id");
    Assert.assertTrue(COL_VAL.contains(getJazzPageFactory().getCCMQueryPage().getSortedOrderValues("Id", "Ascending")));

    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    // Navigate to 'Column Display' tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab);
    Reporter.logPass(queryTab + " tab is selected from the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    getJazzPageFactory().getCCMQueryPage().button("Run");

    // Verify the added columns are found in the Query results page.
    Assert.assertTrue(!getJazzPageFactory().getCCMQueryPage().validateResultColumns()
        .contains("not same as columns displayed in the table"));
  }
}
