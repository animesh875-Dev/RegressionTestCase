/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author JSC1COB
 *
 */
public class TS_39845_verifyCreationOfCategories  extends AbstractFrameworkTest{

  /**
   * This test case for verification of Categories 
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  
  public void tcs_39845_verifyCreationOfCategories() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");
    String menu=this.testDataRule.getConfigData("MENU");
    String menuName=this.testDataRule.getConfigData("MENU_NAME");
    String category=this.testDataRule.getConfigData("CATEGORY");
    String actionMenu=this.testDataRule.getConfigData("ACTION_MENU");
    String associateTeamArea =this.testDataRule.getConfigData("TEAM_AREA");
    String categoryName =this.testDataRule.getConfigData("CATEGORY_NAME");
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(10);
    
    // Select the project area.
    getJazzPageFactory().getCCMAllProjectsPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    // Select Manage this Project Area
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSettingsMenu(menu);
    Reporter.logPass( "Settings Menu  opened successfully.");
    getJazzPageFactory().getCCMProjectAreaDashboardPage().waitForSecs(3);   
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSettingsSubMenu(menuName);
    Reporter.logPass("'"+menuName+"'" + ": is opened successfully.");
    
    //Select Categories 
    getJazzPageFactory().getManageProjectAreaPage().selectCategory(category);
    Reporter.logPass(" Categories is Selected successfully.");
    
    //Click Action Menu
 
     getJazzPageFactory().getManageProjectAreaPage().clickOnJazzImageButtons(actionMenu);
     Reporter.logPass("Action button is Clicked Successfully");
     getJazzPageFactory().getManageProjectAreaPage().selectAddCategory("Add Category...");
     Reporter.logPass("Add Category  button is Clicked Successfully");
     getJazzPageFactory().getManageProjectAreaPage().waitForSecs(3); 
     getJazzPageFactory().getManageProjectAreaPage().inputCategory(categoryName);
     Reporter.logPass("Category Name Entered Successfully :"+ categoryName);
     Reporter.logPass("Category is Created after clicking on OK button :" + categoryName);
     
     // Associated with TeamArea     
     getJazzPageFactory().getManageProjectAreaPage().selectCreatedCategory(categoryName,"Associate...");
     Reporter.logPass("Associate button is selected successfully for the created category");
     getJazzPageFactory().getManageProjectAreaPage().clickOnToAssociateTeamArea(associateTeamArea);
     Reporter.logPass("TeamArea to be associated is selected successfully :"+associateTeamArea );
     getJazzPageFactory().getManageProjectAreaPage().clickOnButtons("Associate");
     Reporter.logPass("'" +categoryName + "'" + "  Category is Associated with the TeamArea " + "'"+associateTeamArea+"'");
      
  }}
