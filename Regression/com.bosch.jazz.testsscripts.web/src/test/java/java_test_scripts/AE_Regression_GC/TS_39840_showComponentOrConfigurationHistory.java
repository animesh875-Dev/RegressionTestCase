/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_GC;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author GUA3HC
 *
 */
public class TS_39840_showComponentOrConfigurationHistory extends AbstractFrameworkTest {
  /**
   * This test case for verify full text query search 
   * should return correct data contains text
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39840_showComponentOrConfigurationHistory() throws Throwable {
    String url = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String linkText = this.testDataRule.getConfigData("TEXT_INPUT");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String button = this.testDataRule.getConfigData("BUTTON_1");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String button2 = this.testDataRule.getConfigData("BUTTON_2");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    getJazzPageFactory().getGCAllProjectsPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
 // Click on Browse and Component
    getJazzPageFactory().getBrowseComponentsPage().clickOnLink(linkText);
    
    
    
 //Filter Component and click on Show History
    getJazzPageFactory().getComponentsPage().filterAndSelectComponent(componentName);
    getJazzPageFactory().getGCStreamsPage().clickOnButtons(button);
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    
    
  // Back to stream and click on Show History image
    getJazzPageFactory().getAbstractGCPage().navigateBack();
    getJazzPageFactory().getGCStreamsPage().filterAndSelectStream(streamName);
    getJazzPageFactory().getAbstractGCPage().clickOnJazzImageButtons(button2);
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    
    
    
  }
  
}
