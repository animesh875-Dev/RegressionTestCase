/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_GC;

import java.security.InvalidKeyException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 *
 */
public class TS_39833_compareConfigurationsOfTheSameComponent extends AbstractFrameworkTest{
  /**
   * This test case for verify expand all and collapse all button function 
   * in Configuration Views
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39833_compareConfigurationsOfTheSameComponent() throws Throwable {
    String url = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String tag_Name = this.testDataRule.getConfigData("TAG_NAME");
    String config_Type = this.testDataRule.getConfigData("CONFIG_TYPE");
    String config_Name = this.testDataRule.getConfigData("CONFIG_NAME");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Browse Component > Select Component > Select Stream
    getJazzPageFactory().getGCDashBoardPage().clickOnLink("Browse and create components");
    Reporter.logPass("Clicked on: Browse and create components");
    getJazzPageFactory().getGlobalConfigurationsPage().filterTagUsedByConfigurations(tag_Name);
    getJazzPageFactory().getComponentsPage().filterAndSelectComponent(componentName);
    getJazzPageFactory().getGCStreamsPage().filterAndSelectStream(streamName);
    Reporter.logPass("Selected component: " + componentName + "\nSelected Stream: " + streamName);        
    getJazzPageFactory().getAbstractGCPage().waitForSecs(2);
    // Click on button Compare With
    getJazzPageFactory().getAbstractGCPage().clickOnJazzImageButtons("Compare With - Compare the selected configuration with another");
    Reporter.logPass("Clicked on: 'Compare With - Compare the selected configuration with another' button");
    getJazzPageFactory().getAbstractGCPage().waitForSecs(2);
    // Select Configuration: Stream to Compare
    selectConfigurationtoCompare(null, null, null, config_Type, config_Name);
    Reporter.logPass("Configuration for Compare with is selected: " + config_Name);
    getJazzPageFactory().getAbstractGCPage().waitForSecs(2);
    // Compare items size between configurations
    getJazzPageFactory().getGlobalConfigurationsPage().compareWith();
    Reporter.logPass("Selected configuration is compared with current configuration and the results are loaded");
  }
  
  
  @SuppressWarnings("javadoc")
  public boolean isElementVisible(String name) {
    return driver
        .findElement(
            By.xpath(("//*[text()='"+name+"']")))
        .isDisplayed();
  }
  
  @SuppressWarnings("javadoc")
  public void openAndSelectActionDropdown() throws InvalidKeyException {
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String actionMenu = this.testDataRule.getConfigData("ACTIONS_MENU_OPTION");
    // Click on Actions dropdown sitting next to StreamName
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ConfigurationName", streamName);
    getJazzPageFactory().getGlobalConfigurationsPage().openActionMenu(additionalParams);
    Reporter.logPass("Selected and opened Actions dropdown for stream: " + streamName);
    // Select Actions menu option
    getJazzPageFactory().getGlobalConfigurationsPage().clickFromActionMenu(actionMenu);
    Reporter.logPass("Clicked on action: " + actionMenu);
  }

  
  @SuppressWarnings("javadoc")
  public void selectConfigurationtoCompare(final String configToAddGC, final String projectArea,
      final String componentName, final String config_Type, final String config_Name) {
//    openAndSelectActionDropdown();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ConfigType", config_Type);
    additionalParams.put("ConfigName", config_Name);
    getJazzPageFactory().getGlobalConfigurationsPage().addCompareWithConfiguration(additionalParams);
    Assert.assertTrue(isElementVisible(config_Name));
    Reporter.logPass("Selected Config Type: " + config_Type
        +"\nAdded configuration: " + config_Name);
  }
}
