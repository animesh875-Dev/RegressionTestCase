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
 *  This test case explains how to create a copy of a query and 
 *  validate the query results using column names. finally it
 *  is going to delete the created query
 */
public class TS_13301_chmQueryHandlingCreateACopyOfQuery extends AbstractFrameworkTest {
  /**
   * This test case use to create a copy of query
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13301_QueryHandling_Create_a_Copy_of_Query() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String queryName = this.testDataRule.getConfigData("QUERY_NAME");
    String id = this.testDataRule.getConfigData("WORKITEM_ID");
    String querySummary = this.testDataRule.getConfigData("QUERY_SUMMARY");
    String ownedBy = this.testDataRule.getConfigData("OWNED_BY");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName);
    Reporter.logPass(queryName + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Click on query.
    getJazzPageFactory().getCCMQueryPage().clickOnQuery(queryName);
    Reporter.logPass("user clicked on query " + queryName);
    // Click on Edit current query and create a copy of the query.
    getJazzPageFactory().getCCMQueryPage().clickOnEditCurrentQuery();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().button("Save Copy");
    // Set a new name for copied query.
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().setQueryName("New_Query_");
    String copiedqueryName = getJazzPageFactory().getCCMQueryPage().getQueryname();
    Reporter.logPass("Copied query name set as " + copiedqueryName);
    // Click on Enable Input of Condition Values when Query is Run to pass the value when query runs
    getJazzPageFactory().getCCMQueryPage().enableInputConditionValuesWhenQueryIsRun();
    getJazzPageFactory().getCCMQueryPage().button("Save");
    Reporter.logPass(copiedqueryName + " Query is saved");
    // Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().handleAlert();
    String newCopiedQuery = copiedqueryName + "...";
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().searchQueryName(newCopiedQuery));
    Reporter.logPass(newCopiedQuery + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Run the searched query.
    getJazzPageFactory().getCCMQueryPage().clickOnQuery(newCopiedQuery);
    Reporter.logPass(newCopiedQuery + " is running.");
    // Set the id value which you want to search.
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox("Assign Values to Query Parameters:", id);
    getJazzPageFactory().getCCMQueryPage().button("Run");
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
    getJazzPageFactory().getCCMQueryPage().handleAlert();
    getJazzPageFactory().getCCMQueryPage().searchQueryName(newCopiedQuery);
    Reporter.logPass(newCopiedQuery + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Delete the searched query.
    getJazzPageFactory().getCCMQueryPage().deleteQuery(newCopiedQuery);
    Reporter.logPass(newCopiedQuery + " is deleted from My queries.");
  }
}
