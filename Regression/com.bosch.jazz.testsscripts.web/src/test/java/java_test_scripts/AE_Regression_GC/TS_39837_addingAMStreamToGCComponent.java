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
 * @author XHZ5KOR
 *
 */
public class TS_39837_addingAMStreamToGCComponent extends AbstractFrameworkTest {

  /**
   * This test case for create/edit artifact content inside module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39837_addingAMStreamToGCComponent () throws Throwable {
    String url = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME"); 
    
    // Data for AM page
    String configToAddGC_AM = this.testDataRule.getConfigData("CONFIGURATION_TO_ADD_GC_AM");
    String projectArea_AM = this.testDataRule.getConfigData("PROJECT_AREA_AM");
    String config_Type_AM = this.testDataRule.getConfigData("CONFIG_TYPE_AM");
    String config_Name_AM = this.testDataRule.getConfigData("CONFIG_NAME_AM");
    String application_Name_AM = this.testDataRule.getConfigData("APPLICATION_NAME_AM");
    
    // Data for Remove button
    String removeButton = this.testDataRule.getConfigData("REMOVE_BUTTON");
    String removeDialogTitle = this.testDataRule.getConfigData("REMOVE_DIALOG_TITLE");
    String removeOption = this.testDataRule.getConfigData("REMOVE_OPTION");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Browse Component > Select Component > Select Stream
    getJazzPageFactory().getGCDashBoardPage().clickOnLink("Browse and create components");
    Reporter.logPass("Clicked on: Browse and create components");
    getJazzPageFactory().getComponentsPage().filterAndSelectComponent(componentName);
    getJazzPageFactory().getGCStreamsPage().filterAndSelectStream(streamName);
    Reporter.logPass("Selected component: " + componentName + "\nSelected Stream: " + streamName);
    
    // Open Add Configuration > Select configurations to add to this stream
     // AM
    addConfigurationFromExcel(configToAddGC_AM, projectArea_AM, null, config_Type_AM, config_Name_AM);
    // Remove Configuration AM
    removeConfiguration(config_Name_AM, application_Name_AM, removeOption, removeDialogTitle, removeButton); 
  }
  
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
  public void addConfigurationFromExcel(final String configToAddGC, final String projectArea,
      final String componentName, final String config_Type, final String config_Name) throws InvalidKeyException {
    openAndSelectActionDropdown();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Configuration", configToAddGC);
    additionalParams.put("ProjectArea", projectArea);
    additionalParams.put("Components", componentName);
    additionalParams.put("ConfigType", config_Type);
    additionalParams.put("ConfigName", config_Name);
    getJazzPageFactory().getGlobalConfigurationsPage().addConfiguration(additionalParams);
    Assert.assertTrue(isElementVisible(config_Name));
    Reporter.logPass("Selected Configuration: " + configToAddGC 
        +"\nSelected Project Area: " + projectArea
        +"\nSelected Component: " + componentName
        +"\nSelected Config Type: " + config_Type
        +"\nAdded configuration: " + config_Name);
  }
  
  @SuppressWarnings("javadoc")
  public void removeConfiguration(final String config_Name, final String application_Name, final String removeOption,
      final String removeDialogTitle, final String removeButton) {
    Map<String, String> additionalParams6 = new LinkedHashMap<String, String>();
    additionalParams6.put("ConfigurationName", config_Name);
    additionalParams6.put("ApplicationName", application_Name);
    getJazzPageFactory().getGlobalConfigurationsPage().openActionMenu(additionalParams6);
    getJazzPageFactory().getGlobalConfigurationsPage().clickFromActionMenu(removeOption);
    getJazzPageFactory().getGlobalConfigurationsPage().clickOnDialogButton(removeDialogTitle, removeButton);
    Reporter.logPass("Removed configuration: " + config_Name);
  }
  
  @SuppressWarnings("javadoc")
  public boolean isElementVisible(String config_Name) {
    return driver
        .findElement(
            By.xpath(("//span[@class='gc-Tree-label']/following::*[text()[normalize-space()='" + config_Name + "']]")))
        .isDisplayed();
  }


}
