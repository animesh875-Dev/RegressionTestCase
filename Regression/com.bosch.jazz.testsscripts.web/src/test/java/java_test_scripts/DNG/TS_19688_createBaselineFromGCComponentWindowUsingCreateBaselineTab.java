/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author RUN2HC
 *
 */
public class TS_19688_createBaselineFromGCComponentWindowUsingCreateBaselineTab extends AbstractFrameworkTest{
  
  /**
   * Access to Manage Components and Configuration from Administration menu, then access to a component and create its 
   * baseline by Create Baseline button
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void tcs_19688_CreateBaselineFromGCComponentWindowUsingCreateBaselineTab() throws InvalidKeyException{
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_CM_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String baselineName = this.testDataRule.getConfigData("BASELINE_NAME");
    String baselineDescription = this.testDataRule.getConfigData("BASELINE_DESCRIPTION");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    // Click on Administration menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsMenu("Administration");
    Reporter.logPass("Click on Admistraion menu");
    // Click on Manage Components and Configurations menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsSubMenu("Manage Components and Configurations");
    Reporter.logPass("Click on Manage Components and Configurations menu");
    getJazzPageFactory().getRMManageComponentsAndConfigurations().waitForSecs(5);
    // Click on "Browse Components" link
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink("Browse Components");
    Reporter.logPass("Click on Browse Components link");
    // Search Component
    getJazzPageFactory().getRMManageComponentsAndConfigurations().searchComponentInBrowseComponentPage(componentName);
    Reporter.logPass("Search " + componentName );
    // Click on Component link 
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink(componentName);
    Reporter.logPass("Click on " + componentName + " component link");
    // Click on Stream link 
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink(streamName);
    Reporter.logPass("Click on " + streamName + " stream link");
    // Click on Create Baseline button
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnJazzImageButtons("Create Baseline");
    Reporter.logPass("Click on Create Baseline button");
    //Create baseline
    String s1 = getJazzPageFactory().getRMManageComponentsAndConfigurations().createConfiguration(baselineName, baselineDescription);
    Reporter.logPass("Create baseline with name is " + s1 + " and description is " + baselineDescription);
    //Click on OK button
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnDialogButton("Create Baseline", "OK");
    Reporter.logPass("Click on OK button");
    // Verify created baseline link is displayed
    assertTrue(getJazzPageFactory().getRMManageComponentsAndConfigurations().isComponentOrConfigurationLinkDisplayed(s1));
    Reporter.logPass("Verify the created baseline " + s1 + " is displayed");
    // Click on context menu of initial stream
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnContextMenuForAConfigurations(s1, "Archive");
    Reporter.logPass("Click on Actions menu of " + s1 + " and select Archive");
    //Click on Archive the configuration and all children button
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnJazzSpanButtons("Archive the configuration and all children");
    Reporter.logPass("Click on 'Archive the configuration and all children' button");
  }

}
