/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_GC;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BSPA1KOR
 *
 */
public class TS_39811_changeTheContextFromOneGcStreamToOtherGcStreamInRqm extends AbstractFrameworkTest{
  /**
   * This test case for verify full text query search 
   * should return correct data contains text
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39811_changeTheContextFromOneGcStreamToOtherGcStreamInRqm() throws Throwable {
    String url = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    //String linkText = this.testDataRule.getConfigData("TEXT_INPUT");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String SwitchstreamName = this.testDataRule.getConfigData("SWITCH_STREAM_NAME");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    getJazzPageFactory().getGCAllProjectsPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Click on Browse and Component
    getJazzPageFactory().getBrowseComponentsPage().clickOnLink("Browse and create components");
    Reporter.logPass("Clicked on Browse and create components successfully ");
    //Search component
    getJazzPageFactory().getComponentsPage().filterAndSelectComponent(componentName);
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    getJazzPageFactory().getGCStreamsPage().clickOnLink(streamName);
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    getJazzPageFactory().getGCStreamsPage().clickOnLink(SwitchstreamName);
    getJazzPageFactory().getAbstractGCPage().waitForSecs(10);
    
    getJazzPageFactory().getRMArtifactPage().switchToTheOtherTab("1");
    
    getJazzPageFactory().getRMDashBoardPage().refresh();
    getJazzPageFactory().getAbstractGCPage().waitForSecs(30);
    
    getJazzPageFactory().getRMDashBoardPage().changeStream(streamName);
    Reporter.logPass("switches current configuration ");
    
        
         
  }
  }



