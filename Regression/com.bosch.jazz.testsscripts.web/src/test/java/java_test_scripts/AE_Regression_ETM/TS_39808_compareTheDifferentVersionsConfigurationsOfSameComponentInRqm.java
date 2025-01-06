/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_ETM;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 *
 */
public class TS_39808_compareTheDifferentVersionsConfigurationsOfSameComponentInRqm extends AbstractFrameworkTest {
  /**
   * This test case for create a baseline then uses "Merge Configuration"
   * to accept changes from one stream to another stream
   * under the same component.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39808_compareTheDifferentVersionsConfigurationsOfSameComponentInRqm() throws Throwable {
    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    
    String viewAs = this.testDataRule.getConfigData("VIEW_AS");
    String baselineName = this.testDataRule.getConfigData("BASELINE_NAME");
    String testPlanName = this.testDataRule.getConfigData("TEST_PLAN_NAME");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    // Open submenu in Current Configuration and select Compare Configuration...
    getJazzPageFactory().getRQMConstructionPage().openAndSelectSubMenuInCurrentConfiguration("Compare Configuration...");
    Reporter.logPass("Compare Configuration... dialog is opened");
    // Compare Configuration 
    String rqm = getJazzPageFactory().getRQMConstructionPage().actionCompareConfiguration(viewAs, baselineName, testPlanName);
    // Assert return result is not null
    assertNotNull(rqm);   
  }
}
