/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_JRS;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author XHZ5KOR
 *
 */
public class TS_39817_TC_dynamicDateFilteringByFutureDates extends AbstractFrameworkTest {
  
  /**
   * This test case for create/edit artifact content inside module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_398178_dynamicDateFilteringByFutureDates() throws Throwable {
    String url = testDataRule.getConfigData("JRS_SERVER_URL");
    String dataSource = testDataRule.getConfigData("JRS_DATA_SOURCE");
    String projectArea = testDataRule.getConfigData("JRS_PROJECT_AREA");
    String work_item = testDataRule.getConfigData("WORK_ITEM");
    String child_artifact = testDataRule.getConfigData("CHILD_ARTIFACT");
    String searchduedate = testDataRule.getConfigData("SEARCH_DUE_DATE");
    String days_option = testDataRule.getConfigData("SEARCH_DAYS_OPTION");
    String days_value = testDataRule.getConfigData("DAYS_VALUE");
    
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnJazzSpanButtons("Build report");
    Reporter.logPass("Clicked on Build button.");
    getJazzPageFactory().getJRSBuildNewReportPage().editDataSource(dataSource);
    Reporter.logPass("Clicked on data source type " + dataSource);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Choose a report type Continue button clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().setProjectAreaName(projectArea);
    Reporter.logPass("Clicked on project area " + projectArea);
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().chooseAnArtifact(work_item, child_artifact);
    Reporter.logPass("Clicked on chooseAnArtifact ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnJazzImageButtons("Add condition");
    Reporter.logPass("Clicked on Add condition Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().searchCondition(searchduedate);
    Reporter.logPass("Clicked on searchn condition ");
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnRadiobutton(searchduedate);
    Reporter.logPass("Clicked on Radio Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnOption(days_option, days_value);
    Reporter.logPass("Clicked on Days ago Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().addAndClose();
    Reporter.logPass("Clicked on addAndClose Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    
}
}