/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_JRS;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author GHO4KOR
 *
 */
public class TS_39821_createLQEReportWithTraceibilityIncluded_DNG_RQM_RTC extends AbstractFrameworkTest {

  /**
   * This test case is for creation of LQE report with traceibility included
   * 
   * @throws Throwable is used to handle any kind of exception.
   */
  @Test
  public void tcs_39821_createLQEReportWithTraceibilityIncluded_DNG_RQM_RTC() throws Throwable {
    String url = testDataRule.getConfigData("JRS_SERVER_URL");
    String dataSource = testDataRule.getConfigData("JRS_DATA_SOURCE");
    String jrs_ccm_project_area = testDataRule.getConfigData("JRS_CCM_PROJECT_AREA");
    String jrs_qm_project_area = testDataRule.getConfigData("JRS_QM_PROJECT_AREA");
    String jrs_rm_project_area = testDataRule.getConfigData("JRS_RM_PROJECT_AREA");
    String work_item = testDataRule.getConfigData("WORK_ITEM");
    String child_artifact = testDataRule.getConfigData("CHILD_ARTIFACT");
    String relationship_value_1 = testDataRule.getConfigData("RELATIONSHIP_VALUE_1");
    String relationship_artifact_1 = testDataRule.getConfigData("RELATIONSHIP_ARTIFACT_1");
    String relationship_value_2 = testDataRule.getConfigData("RELATIONSHIP_VALUE_2");
    String relationship_artifact_2 = testDataRule.getConfigData("RELATIONSHIP_ARTIFACT_2");
    String relationship_value_3 = testDataRule.getConfigData("RELATIONSHIP_VALUE_3");
    String relationship_artifact_3 = testDataRule.getConfigData("RELATIONSHIP_ARTIFACT_3");
    String relationship_value_4 = testDataRule.getConfigData("RELATIONSHIP_VALUE_4");
    String relationship_artifact_4 = testDataRule.getConfigData("RELATIONSHIP_ARTIFACT_4");
    String tab_name_1 = testDataRule.getConfigData("TAB_NAME_1");
    String tab_name_2 = testDataRule.getConfigData("TAB_NAME_2");
    String report_name = testDataRule.getConfigData("REPORT_NAME");
    String privacy_mode = testDataRule.getConfigData("PRIVACY_MODE");
    String default_viz_mode = testDataRule.getConfigData("DEFAULT_VISUALIZATION_MODE");
    String expected_wi_id = testDataRule.getConfigData("EXPECTED_WI_ID");
    String expected_wi_1_id = testDataRule.getConfigData("EXPECTED_WI_1_ID");
    String expected_requirement_id = testDataRule.getConfigData("EXPECTED_REQUIREMENT_ID");
    String expected_test_case_id = testDataRule.getConfigData("EXPECTED_TEST_CASE_ID");
    String expected_tcer_id = testDataRule.getConfigData("EXPECTED_TCER_ID");
    String folder_name = testDataRule.getConfigData("FOLDER_NAME");
    boolean flag = false;

    Map<String, String> columnValue = new HashMap<>();
    
    try
    {
      Reporter.logInfo("**************************Test script Started**************************");
      
      // Logging in to alm application JRS domain with valid user name and password.
      getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
      Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
      
      // Clicking on Build report tab, to create report 
      getJazzPageFactory().getJRSBuildNewReportPage().clickOnJazzSpanButtons("Build report");
      Reporter.logPass("Clicked on Build button.");
      
      // Selecting Data Source and project area of ECA engine for the report
      getJazzPageFactory().getJRSBuildNewReportPage().editDataSource(dataSource);
      Reporter.logPass("Clicked on data source type " + dataSource);
      getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
      Reporter.logPass("Choose a report type Continue button clicked successfully.");
      getJazzPageFactory().getJRSBuildNewReportPage().setProjectAreaName(jrs_ccm_project_area);
      Reporter.logPass("Clicked on project area " + jrs_ccm_project_area);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(2);
      getJazzPageFactory().getJRSBuildNewReportPage().setProjectAreaName(jrs_qm_project_area);
      Reporter.logPass("Clicked on project area " + jrs_qm_project_area);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(2);
      getJazzPageFactory().getJRSBuildNewReportPage().setProjectAreaName(jrs_rm_project_area);
      Reporter.logPass("Clicked on project area " + jrs_rm_project_area);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
      Reporter.logPass("Clicked on Continue Button of Limit the scope section ");
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      
      // Selecting artifact and child artifact as Work Item and Change Request respectively
      getJazzPageFactory().getJRSBuildNewReportPage().chooseAnArtifact(work_item, child_artifact);
      Reporter.logPass("Clicked on Choose an artifact ");
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
      Reporter.logPass("Clicked on Continue Button of Choose an artifact section ");
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      
      /* Creating relationship of Work Item-->Resolved by-->Work Item 1-->Implements-->Requirement-->Validated by Test Case-->
       * Test Case-->All link types-->Test Case Execution Record */
      getJazzPageFactory().getJRSBuildNewReportPage().addRelationshipByLabel(relationship_value_1 , relationship_artifact_1);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      Reporter.logPass("Established relationship between :" + relationship_artifact_1 + " and " + relationship_value_1 );
      getJazzPageFactory().getJRSBuildNewReportPage().addRelationshipByLabel( relationship_value_2 , relationship_artifact_2);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      Reporter.logPass("Established relationship between :" + relationship_artifact_2 + " and " + relationship_value_2 );
      getJazzPageFactory().getJRSBuildNewReportPage().addRelationshipByLabel(relationship_value_3 , relationship_artifact_3);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      Reporter.logPass("Established relationship between :" + relationship_artifact_3 + " and " + relationship_value_3 );
      getJazzPageFactory().getJRSBuildNewReportPage().addRelationshipByLabel(relationship_value_4 , relationship_artifact_4);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      Reporter.logPass("Established relationship between :" + relationship_artifact_4 + " and " + relationship_value_4 );
      getJazzPageFactory().getJRSBuildNewReportPage().clickContinueButton();
      Reporter.logPass("Clicked on Continue Button of Trace relationships and add artifacts section ");
      getJazzPageFactory().getJRSBuildNewReportPage().clickOnReportTab(tab_name_1);

      // Appending current date and time with report name and setting the Sharing and Visualization mode
      report_name = report_name  + DateUtil.getCurrentDateAndTime();
      getJazzPageFactory().getJRSBuildNewReportPage().setJRSReportName(report_name);

      getJazzPageFactory().getJRSBuildNewReportPage().setPrivacyAndSharing(privacy_mode);
      getJazzPageFactory().getJRSBuildNewReportPage().setDefaultVisualization(default_viz_mode);
      getJazzPageFactory().getJRSBuildNewReportPage().selectFolder(folder_name);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      getJazzPageFactory().getJRSBuildNewReportPage().saveReport();
      flag = true ;

      // Selecting Run report tab and putting some preknown Value and Key pairs in map, so as to verify the report table
      getJazzPageFactory().getJRSBuildNewReportPage().clickOnReportTab(tab_name_2);
      columnValue.put("columnValue2", expected_wi_id);
      columnValue.put("columnValue4", expected_wi_1_id);
      columnValue.put("columnValue6", expected_requirement_id);
      columnValue.put("columnValue8", expected_test_case_id);
      columnValue.put("columnValue10", expected_tcer_id);
      getJazzPageFactory().getJRSBuildNewReportPage().checkArtifacetRelationshipsAutoCreated(columnValue);
      getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
      
      Reporter.logInfo("**************************Test script Completed**************************");
    }

    finally
    {

      if(flag) {
        Reporter.logInfo("**************************Tear Down Started**************************");
        
        // Deleting the report by passing report_name and folder_name
        getJazzPageFactory().getJRSBuildNewReportPage().deleteReport(report_name,"Group by folder",folder_name);
        getJazzPageFactory().getJRSBuildNewReportPage().waitForSecs(10);
        
        Reporter.logInfo("**************************Tear Down Completed**************************");
      }
    }
  }


}
