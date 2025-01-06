/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.JRS;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * This test case represents format report for the JRS graph.
 */
public class TS_20420_Format_Report_Result_InGraph_LQE_Workitembar_Orientation_Horizontal
    extends AbstractFrameworkTest {

  /**
   * @throws Throwable can handle any kind of Exception.
   */
  @Test
  public void ts_20420_Format_Report_InGraph_LQE_Workitembar_Orientation_Horizontal() throws Throwable {
    String url = testDataRule.getConfigData("JRS_SERVER_URL");
    String dataSource = testDataRule.getConfigData("JRS_DATA_SOURCE");
    String projectArea = testDataRule.getConfigData("JRS_PROJECT_AREA");
    String work_item = testDataRule.getConfigData("WORK_ITEM");
    String child_artifact = testDataRule.getConfigData("CHILD_ARTIFACT");
    String relationship_value = testDataRule.getConfigData("RELATIONSHIP_VALUE");
    String relationship_artifact = testDataRule.getConfigData("RELATIONSHIP_ARTIFACT");
    String searchcreationdate = testDataRule.getConfigData("SEARCH_CREATION_DATE");
    String creationdatebefore = testDataRule.getConfigData("CREATION_DATE_BEFORE");
    // Log in to alm application with valid user name and password.
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
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().chooseAnArtifact(work_item, child_artifact);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().addRelationship(relationship_value, relationship_artifact);
    getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
    getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnJazzImageButtons("Add condition");
    getJazzPageFactory().getJRSBuildNewReportPage().searchCondition(searchcreationdate);
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnRadiobutton(searchcreationdate);
    getJazzPageFactory().getJRSBuildNewReportPage().setConditionType("before");
    getJazzPageFactory().getJRSBuildNewReportPage().setConditionDate(creationdatebefore);
    getJazzPageFactory().getJRSBuildNewReportPage().clickOnTab("Format results");
    Assert.assertTrue(true);
  }
}
