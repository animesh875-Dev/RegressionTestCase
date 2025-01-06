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
 *
 */
public class TS_13316_chmQueryHandlingDeletingACreatedOrSharedQuery extends AbstractFrameworkTest {
  /**
   * This test case creating a my query and shared query, deleting the queries and Verifying query is no longer available for
   * users with whom query has shared and also in My Query and Shared Query section.
   * 
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13316_chmQueryHandlingDeletingACreatedOrSharedQuery() throws Throwable  {
    
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String myQuery = this.testDataRule.getConfigData("MY_QUERY");
    String sharedQuery = this.testDataRule.getConfigData("SHARED_QUERY");
    String id = this.testDataRule.getConfigData("WORKITEM_ID_MYQUERY");
    String id2 = this.testDataRule.getConfigData("WORKITEM_ID_SHAREDQUERY");
    String queryTab1 ="Conditions";
    String queryTab2 = "Details";
    String UserId = this.testDataRule.getConfigData("USER_ID");
    String UserValue = this.testDataRule.getConfigData("USER_VALUE");
    String ProjectArea = this.testDataRule.getConfigData("PROJECT_AREA");
    String TeamArea =this.testDataRule.getConfigData("TEAM_AREA");
   
        
    //Login in to alm application with valid user name and password.
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
    getJazzPageFactory().getCCMQueryPage().setQueryName(myQuery);
    myQuery = getJazzPageFactory().getCCMQueryPage().getQueryname();
    Reporter.logPass("Query name given as " + myQuery);
    // Click on Conditions tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab1);
    Reporter.logPass(queryTab1 + " Tab is selected from new quries");
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
    Reporter.logPass("Query is saved");
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
//    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);

    //Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    getJazzPageFactory().getCCMQueryPage().searchQueryName(myQuery);
    Reporter.logPass(myQuery + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Delete the searched query.
    getJazzPageFactory().getCCMQueryPage().deleteQuery(myQuery);
    Reporter.logPass(myQuery + " is deleted from My queries.");
    //Validate the deleted my query is not present in My Queries section.
    Assert.assertFalse(getJazzPageFactory().getCCMQueryPage().searchQueryName(myQuery));
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    
    // Select Create Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.CREATE_QUERY.toString());
    Reporter.logPass(CCMSubMenus.CREATE_QUERY.toString() + " Selected from Work Items drop down.");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Set Query name in new query.
    getJazzPageFactory().getCCMQueryPage().setQueryName(sharedQuery);
    sharedQuery = getJazzPageFactory().getCCMQueryPage().getQueryname();
    Reporter.logPass("Query name given as " + sharedQuery);
    // Click on Conditions tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab1);
    Reporter.logPass(queryTab1 + " Tab is selected from new quries");
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
    getJazzPageFactory().getCCMQueryPage().setId(id2);
    Reporter.logPass("ID name given as " + id2);
    // Click on Save button.
    getJazzPageFactory().getCCMQueryPage().button("Save");
    Reporter.logPass("Query is saved");
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
//    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    //Click on Details tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab2);
    Reporter.logPass(queryTab2 + " tab is selected from queries");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    getJazzPageFactory().getCCMQueryPage().button("Save");
    //Click on Add Team or Project Area link and select the Team Area from 'Select Team Area or project Area'widzard.
    getJazzPageFactory().getCCMQueryPage().selectTeamAreaOrProjectArea(ProjectArea,TeamArea);
    Reporter.logPass(ProjectArea + " project area added for the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    
    //Click on Add User link and select the user from 'Select Users' widzard.
    getJazzPageFactory().getCCMQueryPage().addUser(UserId,UserValue);
    Reporter.logPass(UserId + " is added for " + UserValue);
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().button("Save");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    Reporter.logPass("Shared Query is saved");
   
    //Select Shared Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.SHARED_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.SHARED_QUERIES.toString() + " Selected from Work Items drop down.");
    // Validate query by searching query name.
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(10);
    getJazzPageFactory().getCCMQueryPage().searchQueryName(sharedQuery);
    Reporter.logPass(sharedQuery + " is present in " + CCMSubMenus.SHARED_QUERIES.toString());
    // Delete the searched query.
    getJazzPageFactory().getCCMQueryPage().deleteQuery(sharedQuery);
    Reporter.logPass(sharedQuery + " is deleted from My queries.");
    //Validate the deleted shared query is not present in Shared Queries section.
    Assert.assertFalse(getJazzPageFactory().getCCMQueryPage().searchQueryName(sharedQuery));
    
    //Logout alm application 
    getJazzPageFactory().getCCMProjectAreaDashboardPage().logOutWithUrl(url);
    Reporter.logPass("User is able to " + url + " logout successfully" );
    //Login in to alm application with valid user name and password as a another user.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(UserId, getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    //Select Shared Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.SHARED_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.SHARED_QUERIES.toString() + " Selected from Work Items drop down.");
   //Validate the deleted shared query is not present in Shared Queries section.
    Assert.assertFalse(getJazzPageFactory().getCCMQueryPage().searchQueryName(sharedQuery));
    Reporter.logPass(sharedQuery + " is not present in " + CCMSubMenus.SHARED_QUERIES.toString());
    
  }

}
