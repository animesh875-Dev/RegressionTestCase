/*
 * Copyright (c) ETAS GmbH 2024. All rights reserved.
 */
package java_test_scripts.AE_Regression_JRS;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author KAZ1COB
 */
public class TS_39815_adjustColumnWidthInReportResults extends AbstractFrameworkTest {

  /**
   * This test case is to adjust the  column width in the report results.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39815_adjustColumnWidthInReportResults() throws Throwable {

    String url = this.testDataRule.getConfigData("JRS_SERVER_URL");
    String reportName = this.testDataRule.getConfigData("REPORT_NAME");
    String attributeValue = this.testDataRule.getConfigData("ATTRIBUTE_COL_VALUE");
    String domainConfig = this.testDataRule.getConfigData("DOMAIN_CONFIGURATION");
    String projectArea = this.testDataRule.getConfigData("PROJECT_AREA");
    String chooseComponent = this.testDataRule.getConfigData("CHOOSE_COMPONENT");
    String chooseConfig = this.testDataRule.getConfigData("CHOOSE_CONFIGURATION");


    Reporter.logInfo("**************************Test script Started**************************");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");

    // Select All reports tab
    getJazzPageFactory().getJRSAllReportsPage().clickOnJazzSpanButtons("All reports");
    Reporter.logPass(" All reports have been clicked successfully.");

    // Search and edit the required report
    getJazzPageFactory().getJRSBuildNewReportPage().editJrsReport(reportName);
    Reporter.logPass("Search and edit condition clicked successfully.");

    // Switch to Format Results Tab
    getJazzPageFactory().getJRSBuildNewReportPage().checkFormatResultTab();
    Reporter.logPass("Format Section is displayed.");
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnReportTab("Format results");
    Reporter.logPass("Format Tab have been clicked successfully.");

    // Provide Attributes to the Report
    getJazzPageFactory().getJRSBuildNewReportPage().clickAttributesButton();
    Reporter.logPass("Attribute Button have been clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().setAddAttributesToTheReport(
        "\r\n" + "SYS SysRS Requirement Module [Type: SYS SysRS Requirement Module]\r\n" + "");
    Reporter.logPass("Attribute Function have been added successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().searchAttributeCondition(attributeValue);
    getJazzPageFactory().getJRSBuildNewReportPage().clickSearchedAttribute(attributeValue);
    getJazzPageFactory().getJRSBuildNewReportPage().clickAttributeAddButton();
    Reporter.logPass("Columns on Format Result Section have been added successfully.");

    // Refresh the preview Table
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnButtons("Refresh");
    Reporter.logPass("Refresh Button have been clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage()
        .verifyColumnAddedInThePreviewTableOfFormatResultTab("Owner (SYS SysRS Requirement Module)");
    String fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(),
        "verifyColumnAddedInThePreviewTableOfFormatResultTab", driver);
    Reporter.logPassWithScreenshot("Verified : Column Added", fileName, fileName);

    // Switch to Run report Tab
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnReportTab("Run report");
    Reporter.logPass("Run report Section have been clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().switchToIFrame();
    Reporter.logPass("Frame Switched");

    // Provide the Configuration Filters
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().chooseDomainForConfigurations(domainConfig);
    Reporter.logPass("Choose Domain have been clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().chooseProjectAreaForConfigurations(projectArea);
    Reporter.logPass("Project Area have been clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().chooseComponentForConfigurations(chooseComponent);
    Reporter.logPass("Choose Component have been clicked successfully.");
    getJazzPageFactory().getJRSBuildNewReportPage().chooseConfiguration(chooseConfig);
    Reporter.logPass("Choose Configuration have been clicked successfully.");

    // Adjust Column Width of the report
    getJazzPageFactory().getJRSBuildNewReportPage().clickDragAndDropCursorWidth();
    Reporter.logPass("Drag and drop Works Successfully!!");

    Reporter.logInfo("**************************Test script Completed**************************");

  }
}
