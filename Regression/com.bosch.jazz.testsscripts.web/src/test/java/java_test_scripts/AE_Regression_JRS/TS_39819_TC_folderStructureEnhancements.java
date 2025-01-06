/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_JRS;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author ILG2KOR
 *
 */
public class TS_39819_TC_folderStructureEnhancements extends AbstractFrameworkTest {

  
  /**
   * @throws Throwable
   */
  @Test
  public void tcs_39819_TC_folderStructureEnhancements() throws Throwable {
    
    String url = this.testDataRule.getConfigData("JRS_SERVER_URL");
//    String reportName = this.testDataRule.getConfigData("REPORT_NAME");
//    String attributeValue = this.testDataRule.getConfigData("ATTRIBUTE_COL_VALUE");
//    String domainConfig = this.testDataRule.getConfigData("DOMAIN_CONFIGURATION");
//    String projectArea = this.testDataRule.getConfigData("PROJECT_AREA");
//    String chooseComponent = this.testDataRule.getConfigData("CHOOSE_COMPONENT");
//    String chooseConfig = this.testDataRule.getConfigData("CHOOSE_CONFIGURATION");
      String folderFirst = this.testDataRule.getConfigData("FOLDER_NAME_1");
      String reportName = this.testDataRule.getConfigData("REPORT_NAME");
      String folderSecond = this.testDataRule.getConfigData("SECOND_FOLDER");
      
      
      
      
    Reporter.logInfo("**************************Test script Started**************************");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    
    // Select All reports tab
    getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
    Reporter.logPass(" All reports have been clicked successfully.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    
//    
    // Selecting GroupBy Folder
    
    // select group by tag
    getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("Group by tag");
    getJazzPageFactory().getJRSAllReportsPage().clickOnLink("Group by folder");
    Reporter.logPass("Clicked on Group by folder successfully.");
      
    // select the required tag
    getJazzPageFactory().getJRSAllReportsPage().selectFolder(folderFirst);
    Reporter.logPass("Doneness is clicked successfully by Xpath.");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(20);
    
    // De-select the required report
    getJazzPageFactory().getJRSAllReportsPage().clickOnReportCheckbox(reportName);
    //Reporter.logPass("Report selected sucessfully.");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    // Verify deselection of parent and the required report
    getJazzPageFactory().getJRSAllReportsPage().verifyReportSelection(reportName,folderFirst);
    //Reporter.logPass("Report selected sucessfully.");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    
    
    
//    
    // Support to create new folder in " Select A Folder" dialog
    Reporter.logInfo("**************************Support to create new folder in \" Select A Folder\" dialog Started**************************");
    
    // Clicking on Build report tab, to create report 
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnJazzSpanButtons("Build report");
    Reporter.logPass("Clicked on Build button.");
    
    // Click on Name and Share option
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnReportTab("Name and share");
    Reporter.logPass("Clicked on Name and Share button.");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    // Open Select Folder Dialogue and check support 
    getJazzPageFactory().getJRSBuildNewReportPage().checkNewFolderSupport("/","New Folder");
    
    TestAcceptanceMessage TA = getJazzPageFactory().getJRSBuildNewReportPageVerification().verifySelectFolder("New Folder",null);
    if(TA.getState().contains("FAILED")) {
      throw new WebAutomationException(TA.getMessage());
    }
    Reporter.logPass(TA.getMessage());
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    // Close Report 
    getJazzPageFactory().getJRSBuildNewReportPage().clickCancelButton();
    Reporter.logPass("Cancel Button clicked.");
    getJazzPageFactory().getJRSBuildNewReportPage().clickOkButtonToDiscardChanges();
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    Reporter.logPass("Report Closed.");
    Reporter.logInfo("**************************Support to New Folder verifiaction Completed**************************");
  
//    
//    
//    
    
    Reporter.logInfo("**************************Tear Down Started for support**************************");
    
 // Select All reports tab
  getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
  Reporter.logPass(" All reports have been clicked successfully.");
  getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);

  
  // Selecting GroupBy Folder
  // select group by tag
  getJazzPageFactory().getJRSAllReportsPage().refresh();
  getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
  getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("Group by tag");
  getJazzPageFactory().getJRSAllReportsPage().clickOnLink("Group by folder");
  Reporter.logPass("Clicked on Group by folder successfully.");
    
    // Deleting the report by passing report_name and folder_name
    getJazzPageFactory().getJRSAllReportsPage().deleteFolder("New Folder");
    Reporter.logPass("Folder Deleted Successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    
    Reporter.logInfo("**************************Tear Down Completed**************************");
    
    
    
    Reporter.logInfo("**************************Search report/folder in collapsed tag/folder category**************************");
    
 // select group by tag
    
    getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
    getJazzPageFactory().getJRSAllReportsPage().refresh();
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("Group by tag");
    getJazzPageFactory().getJRSAllReportsPage().clickOnLink("Group by folder");
    Reporter.logPass("Clicked on Group by folder successfully.");
    
    
    getJazzPageFactory().getJRSAllReportsPage().searchReport(reportName);
    Reporter.logPass(reportName + " search done Successfully.");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
   
    Reporter.logInfo("**************************Search report/folder in collapsed tag/folder category completed**************************");
    
    
    
      Reporter.logInfo("**************************Move Support Started**************************");
      
      getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
      getJazzPageFactory().getJRSAllReportsPage().refresh();
      getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
      getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("Group by tag");
      getJazzPageFactory().getJRSAllReportsPage().clickOnLink("Group by folder");
      getJazzPageFactory().getJRSAllReportsPage().searchReport(reportName);
      
      
      getJazzPageFactory().getJRSAllReportsPage().moveReport(reportName, folderSecond);
      Reporter.logPass("Report Moved sucessfully.");
      getJazzPageFactory().getJRSAllReportsPage().refresh();
      getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
      
      Reporter.logInfo("**************************Move Support Completed**************************");
      Reporter.logInfo("**************************Move Support Teardown started**************************");
      getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
      getJazzPageFactory().getJRSAllReportsPage().refresh();
      getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
      getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("Group by tag");
      getJazzPageFactory().getJRSAllReportsPage().clickOnLink("Group by folder");
      getJazzPageFactory().getJRSAllReportsPage().searchReport(reportName);
      
      
      getJazzPageFactory().getJRSAllReportsPage().moveReport(reportName, folderFirst);
      Reporter.logPass("Report Moved sucessfully.");
      getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
      Reporter.logInfo("**************************Move Support Teardown Completed**************************");
    
    
      
      Reporter.logInfo("************************** Copy Support Started**************************");
      getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
      getJazzPageFactory().getJRSAllReportsPage().refresh();
      getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
      getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("Group by tag");
      getJazzPageFactory().getJRSAllReportsPage().clickOnLink("Group by folder");
      getJazzPageFactory().getJRSAllReportsPage().searchReport(reportName);
      
      getJazzPageFactory().getJRSAllReportsPage().copyReport(reportName,folderFirst);
      getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
      Reporter.logPass("Report Copied sucessfully.");
      Reporter.logInfo("************************** Copy Support Completed**************************");
      
      
      Reporter.logInfo("************************** Copy Support Teardown started**************************");
      getJazzPageFactory().getJRSBuildNewReportPage().deleteReport(reportName,"Group by folder",folderFirst);
      getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
      Reporter.logInfo("************************** Copy Support teardown Completed**************************");
    
  }
}
