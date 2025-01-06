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
public class TS_13299_chmQueryHandlingShareAQuery extends AbstractFrameworkTest {
  /**
   * This test case Sharing a query and Verifying created shared query is displaying in Shared Queries section.
   * 
   * @throws Throwable use to handle any kind of exception.
   *   
   */
  @Test
  public void tcs_13299_chmQueryHandlingShareAQuery() throws Throwable  {
    
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String queryName=this.testDataRule.getConfigData("QUERY_NAME");
    String queryTab="Details";
    String description=this.testDataRule.getConfigData("QUERY_DESCRIPTION");
    String ProjectArea=this.testDataRule.getConfigData("PROJECT_AREA");
    String TeamArea=this.testDataRule.getConfigData("TEAM_AREA");
    String UserId=this.testDataRule.getConfigData("USER_ID");
    String UserValue=this.testDataRule.getConfigData("USER_VALUE");
    
   //Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    //Select My Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.MY_QUERIES.toString());
    Reporter.logPass(CCMSubMenus.MY_QUERIES.toString() + " Selected from Work Items drop down.");
    //Search for the existing query.
    getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName);
    Reporter.logPass(queryName + " is present in " + CCMSubMenus.MY_QUERIES.toString());
    // Click on query.
    getJazzPageFactory().getCCMQueryPage().clickOnQuery(queryName);
    Reporter.logPass("user clicked on query " + queryName);
    // Click on Edit current query.
    getJazzPageFactory().getCCMQueryPage().clickOnEditCurrentQuery();
    //Click on Details tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab);
    Reporter.logPass(queryTab + " Tab is selected from queries");
    //Click on Edit Description in description section.
    getJazzPageFactory().getCCMQueryPage().editDescription(description);
    Reporter.logPass(description + " Description added in the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    //Click on Add Team or Project Area link and select the Team Area from 'Select Team Area or project Area'widzard.
    getJazzPageFactory().getCCMQueryPage().selectTeamAreaOrProjectArea(ProjectArea,TeamArea);
    Reporter.logPass(TeamArea + "Team area added for the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    
    //Click on Add User link and select the user from 'Select Users' widzard.
    getJazzPageFactory().getCCMQueryPage().addUser(UserId,UserValue);
    Reporter.logPass(UserId + " is added for " + UserValue);
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().button("Save");
    
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().refresh();
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    
    //Logout alm application 
    getJazzPageFactory().getCCMProjectAreaDashboardPage().logOutWithUrl(url);
    Reporter.logPass("User is able to" + url + "logout successfully" );
    
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
    //Validate the shared query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().searchQueryName(queryName));
    // Click on query.
    getJazzPageFactory().getCCMQueryPage().clickOnQuery(queryName);
    Reporter.logPass("User clicked on query " + queryName);
    // Click on Edit current query.
    getJazzPageFactory().getCCMQueryPage().clickOnEditCurrentQuery();
    //Click on Details tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab);
    Reporter.logPass(queryTab + " tab is selected from queries");
    //Validate Description of the query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getDetails("Description").contains(description));
    //Validate Team or Project Area of the query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getDetails("Team and Project Area Sharing").contains(TeamArea));
    //Validate user added for the query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().getDetails("User Sharing").contains(UserValue));
    //Remove the description added
    getJazzPageFactory().getCCMQueryPage().deleteDescription();
    Reporter.logPass("Description is removed from the query");
    //Remove Project or Team area added in the query
    getJazzPageFactory().getCCMQueryPage().removeTeamArea(TeamArea);
    Reporter.logPass(TeamArea + " is removed from the query");
    //Remove User Id added in the query
    getJazzPageFactory().getCCMQueryPage().removeUser(UserValue);
    Reporter.logPass(UserValue + " is removed from the query");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().button("Save");
   
    }

}
