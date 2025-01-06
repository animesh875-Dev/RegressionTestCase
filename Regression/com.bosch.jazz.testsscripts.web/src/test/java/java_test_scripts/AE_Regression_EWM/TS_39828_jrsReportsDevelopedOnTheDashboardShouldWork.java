/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 *
 */
public class TS_39828_jrsReportsDevelopedOnTheDashboardShouldWork extends AbstractFrameworkTest {

  /**
   * Verify test case creation, input summary, select Filed Against and Save 
   * should be reflect immediately 
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39828_jrsReportsDevelopedOnTheDashboardShouldWork() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_PROJECT_AREA");
    String reportWidgetName = this.testDataRule.getConfigData("REPORT_WIDGET_NAME");
    String domainDropDownValue = this.testDataRule.getConfigData("DOMAIN_DROPDOWN_VALUE");
    String gcProjectName = this.testDataRule.getConfigData("GC_PROJECT_NAME");
    String componentName = this.testDataRule.getConfigData("COMPONET_NAME");
    String configurationName = this.testDataRule.getConfigData("CONFIGURATION_NAME");
    String reqID1 = this.testDataRule.getConfigData("REQ_ID_1");
    String reqID2 = this.testDataRule.getConfigData("REQ_ID_2");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    //Add a JRS widget into the dashboard page.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().waitForPageLoaded();
    getJazzPageFactory().getCCMProjectAreaDashboardPage().addWidget(reportWidgetName);
    Reporter.logPass("'Add Widget' into dashboard page successfully. ");
    //Save dashboard
    getJazzPageFactory().getCCMProjectAreaDashboardPage().saveDashboard();
    Reporter.logPass("Save new added Widget into dashboard page successfully. ");
    //Close widget catalog.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().clickOnCloseWidgetDialog();
    Reporter.logPass("Close widget catalog successfully. ");
    //Choose a configuration for JRS report in CCM dashboard
    getJazzPageFactory().getCCMProjectAreaDashboardPage().chooseConfigurationInJrsReportWidget(domainDropDownValue, gcProjectName, componentName, configurationName);
    Reporter.logPass("Choose configuration for new added Widget in dashboard page successfully. ");
    //Click on Save button in report widget
    getJazzPageFactory().getCCMProjectAreaDashboardPage().clickOnButtons("Save");
    Reporter.logPass("Save configuration successfully. ");

    
    //Verify result report
//    Map<String,String> param = new LinkedHashMap<String, String>();
//    param.put("columnValue1", reqID1);
//    param.put("columnValue2", reqID2);
//    getJazzPageFactory().getJRSBuildNewReportPage().checkArtifacetRelationshipsAutoCreated(param);
    getJazzPageFactory().getJRSBuildNewReportPage().verifyTotalNoOfRecords("1", "//iframe[@name='jazz_opensocial_Gadget_1']");
    
     
    //Remove widget out of dashboard
    getJazzPageFactory().getCCMProjectAreaDashboardPage().removeWidgetFromMiniDashboard(reportWidgetName);
    Reporter.logPass("Remove new added Widget out of dashboard page successfully. ");
    //Save dashboard
    getJazzPageFactory().getCCMProjectAreaDashboardPage().saveDashboard();
    Reporter.logPass("Save new added Widget into dashboard page successfully. ");
    //Verify widget was removed out of dashboard
    assertFalse(
        getJazzPageFactory().getCCMProjectAreaDashboardPage().isWidgetVisible());
    Reporter.logPass("New added widget is removed successfully");

    
    
  }
}
