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
public class TS_19735_createBaselineFromGCComponentWindowUsingActionsDropDownMenu extends AbstractFrameworkTest{
  /**
   * Access to Manage Components and Configuration from Administration menu, then create a component and create its baseline
   * 
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void tcs_19735_CreateBaselineFromGCComponentWindowUsingActionsDropDownMenu() throws InvalidKeyException{
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
    // Click on Administration menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsMenu("Administration");
    Reporter.logPass("Click on Admistraion menu");
    // Click on Manage Components and Configurations menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsSubMenu("Manage Components and Configurations");
    Reporter.logPass("Click on Manage Components and Configurations menu");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Click on "Browse Components" link
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink("Browse Components");
    Reporter.logPass("Click on Browse Components link");
    // Click on Create Component button
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnJazzSpanButtons("Create Component");
    Reporter.logPass("Click on Create Component button");
    // Create Component
    //String s = getJazzPageFactory().getRMManageComponentsAndConfigurations().createComponent(componentName, componentDescription, componentTemplate);
    //Reporter.logPass("Create component with name is '" + s + "', description is '" + componentDescription + "' and template is '" + componentTemplate);
    // Click on context menu of initial stream
   // getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnContextMenuForAConfigurations(s, "Create Baseline");
   // Reporter.logPass("Click on Actions menu of " + s + " Initial Stream and select Create Baseline");
    //Create baseline
    String s1 = getJazzPageFactory().getRMManageComponentsAndConfigurations().createConfiguration(baselineName, baselineDescription);
    Reporter.logPass("Create baseline with name is " + s1 + " and description is " + baselineDescription);
    //Click on OK button
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnDialogButton("Create Baseline", "OK");
    Reporter.logPass("Click on OK button");
    //Click on Initial Stream
    //getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLinkWithPartOfText(s);
    Reporter.logPass("Click on the Initial Stream link");
    // Verify created baseline link is displayed
    assertTrue(getJazzPageFactory().getRMManageComponentsAndConfigurations().isComponentOrConfigurationLinkDisplayed(s1));
    Reporter.logPass("Verify the created baseline is displayed");
    // Click on Component link in breadcumb
   // getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink(s);
    Reporter.logPass("Click on created component link");
    // Click on Archive button 
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnJazzImageButtons("Archive this component to hide it and its contents");
    Reporter.logPass("Click on Archive button");
    // Click on Archive button on Confirm Archive dialog
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnJazzButtons("Archive");
    Reporter.logPass("Click on Archive button on Confirm Archive dialog");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
  }

}
