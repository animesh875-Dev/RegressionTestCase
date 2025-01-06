/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import static org.junit.Assert.assertEquals;

import java.security.InvalidKeyException;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author RUN2HC
 */
public class TS_19684_createBaselineViaCurrentConfigurationTab extends AbstractFrameworkTest {

  /**
   * Click on "Current Configuration" and select "Create Baseline.." to create a new baseline
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void ts_19684_createBaselineViaConstructionTab() throws InvalidKeyException {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_CM_PROJECT_AREA");
    String baselineName = this.testDataRule.getConfigData("BASELINE_NAME");
    String baselineDescription = this.testDataRule.getConfigData("BASELINE_DESCRIPTION");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    //Get current configuration
    String s1 = getJazzPageFactory().getRMManageComponentsAndConfigurations().getCurrentConfiguration();
    //Click on "Current Configuration" dropdown and select Create Baseline
    getJazzPageFactory().getRMManageComponentsAndConfigurations().openAndSelectSubMenuInCurrentConfiguration("Create Baseline...");
    Reporter.logPass("Click on Current Configuration menu and select Create Baseline... submenu");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Input baseline name and description and click on Create button
    String s2 = getJazzPageFactory().getRMManageComponentsAndConfigurations().createConfiguration(baselineName, baselineDescription);
    Reporter.logPass("Create baseline with name is " + s2 + " and description is " + baselineDescription);
    //Click on OK button
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnDialogButton("Create Baseline", "OK");
    Reporter.logPass("Click on OK button");
    //Switch to Global configuration and select the created baseline which is associated with the project area.
    getJazzPageFactory().getRMManageComponentsAndConfigurations().selectConfigContext("Requirements Management Configuration", "Baselines", s2);
    Reporter.logPass("Switch to the created baseline " + s2);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Verify selected baseline is displayed as current configuration
    String s3 = getJazzPageFactory().getRMManageComponentsAndConfigurations().getCurrentConfiguration();
    assertEquals(s3,s2);
    Reporter.logPass("Verify the created baseline is displayed as current configuration");   
    // Click on Administration menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsMenu("Administration");
    Reporter.logPass("Click on Admistration menu");
    // Click on Manage Components and Configurations menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsSubMenu("Manage Components and Configurations");
    Reporter.logPass("Click on Manage Components and Configurations menu");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Click on Initial Stream
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink(s1);
    Reporter.logPass("Click on the Initial Stream link");
    // Click on context menu of initial stream
    getJazzPageFactory().getRMManageComponentsAndConfigurations().archiveConfiguration(s2);
    Reporter.logPass("Click on Actions menu of " + s2 + " and select Archive");
  }
}
