/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_JRS;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author XHZ5KOR
 */
public class TS_39823_TC_createAJrsDwhWithCustomTrendsForAnyRandomConditionForDngRqmRtc extends AbstractFrameworkTest {

  /**
   * This test case for create/edit artifact content inside module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39823_TC_createAJrsDwhWithCustomTrendsForAnyRandomConditionForDngRqmRtc() throws Throwable {
    String url = this.testDataRule.getConfigData("JRS_SERVER_URL");
    String dataSource = this.testDataRule.getConfigData("JRS_DATA_SOURCE");
    String projectArea = this.testDataRule.getConfigData("JRS_PROJECT_AREA");
    String work_item = this.testDataRule.getConfigData("WORK_ITEM");
    String child_artifact = this.testDataRule.getConfigData("CHILD_ARTIFACT");
    String artifact_value = this.testDataRule.getConfigData("ARTIFACT_VALUE");
    String work_item_value = this.testDataRule.getConfigData("WORK_ITEM_VALUE");
    String searchstartdate = this.testDataRule.getConfigData("SEARCH_START_DATE");
    String searchduedate = this.testDataRule.getConfigData("SEARCH_DUE_DATE");
    String attributeof_value = this.testDataRule.getConfigData("ATTRIBUTE_Of_VALUE");
    String attributeValue = this.testDataRule.getConfigData("ATTRIBUTE_VALUE");
    String tab_name_1 = this.testDataRule.getConfigData("TAB_NAME_1");
    String tab_name_2 = this.testDataRule.getConfigData("TAB_NAME_2");
    String report_name = this.testDataRule.getConfigData("REPORT_NAME");
    String folder_name = this.testDataRule.getConfigData("FOLDER_NAME");
    String privacy_mode = this.testDataRule.getConfigData("PRIVACY_MODE");
    boolean flag = false;


    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnJazzSpanButtons("Build report");
    Reporter.logPass("Clicked on Build button.");
    getJazzPageFactory().getJRSBuildNewReportPage().editDataSource(dataSource);
    Reporter.logPass("Clicked on data source type " + dataSource);
    getJazzPageFactory().getJRSBuildNewReportPage().chooseReportType("Historical Trends");
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Choose a report type Continue button clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().setProjectAreaName(projectArea);
    Reporter.logPass("Clicked on project area " + projectArea);
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().chooseAnArtifact(work_item, child_artifact);
    Reporter.logPass("Clicked on ChooseAnArtifact ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().addRelationshipByLabel(work_item_value, artifact_value);
    Reporter.logPass("Clicked on AddRelationship ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().setStartDate(searchstartdate);
    Reporter.logPass("Clicked on setstartDate ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().setEndDate(searchduedate);
    Reporter.logPass("Clicked on setendtDate ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);

    // Switch to Format Results Tab
    getJazzPageFactory().getJRSBuildNewReportPage().checkFormatResultTab();
    Reporter.logPass("Format Section is displayed.");
    getJazzPageFactory().getJRSBuildNewReportPage().clickTableTab();
    Reporter.logPass("Format Section Table tab is Clicked.");


    // Provide Attributes to the Report
    getJazzPageFactory().getJRSBuildNewReportPage().clickAttributesButton();
    Reporter.logPass("Attribute Button have been clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().setAddAttributesToTheReport(attributeof_value);
    Reporter.logPass("Attribute Function have been added successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().searchAttributeCondition(attributeValue);
    getJazzPageFactory().getJRSBuildNewReportPage().clickSearchedAttribute(attributeValue);
    getJazzPageFactory().getJRSBuildNewReportPage().clickAttributeAddButton();
    Reporter.logPass("Columns on Format Result Section have been added successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().clickGraphTab();
    Reporter.logPass("Format Section Graph tab is Clicked.");
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    Reporter.logPass("Clicked on Continue Button ");
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnReportTab(tab_name_1);

    // Appending current date and time with report name and setting the Sharing and Visualization mode
    report_name = report_name + DateUtil.getCurrentDateAndTime();
    getJazzPageFactory().getJRSBuildNewReportPage().setJRSReportName(report_name);

    getJazzPageFactory().getJRSBuildNewReportPage().setPrivacyAndSharing(privacy_mode);
    getJazzPageFactory().getJRSBuildNewReportPage().selectFolder(folder_name);
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().saveReport();
    flag = true;
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnReportTab(tab_name_2);
    getJazzPageFactory().getJRSBuildNewReportPage().clickRunReportButton();
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    String fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(),
        "clickRunReportButton", driver);
    Reporter.logPassWithScreenshot("Graph Generated Successfully with Valid Data", fileName, fileName);

    if (flag) {
      // Deleting the report by passing report_name and folder_name
      getJazzPageFactory().getJRSBuildNewReportPage().deleteReport(report_name, "Group by folder", folder_name);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);

    }
  }

}
