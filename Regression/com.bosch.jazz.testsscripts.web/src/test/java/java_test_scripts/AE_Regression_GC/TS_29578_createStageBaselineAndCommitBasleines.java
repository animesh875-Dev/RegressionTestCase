/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_GC;

import java.util.Date;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import common.AbstractFrameworkTest;

/**
 * @author XHZ5KOR
 *
 */
public class TS_29578_createStageBaselineAndCommitBasleines extends AbstractFrameworkTest {
  
  /**
   * This test case for create/edit artifact content inside module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_29578_createStageBaselineAndCommitBasleines() throws Throwable {
    String url = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String stageBaselineName = this.testDataRule.getConfigData("NAME_SUFFIX");
    
    // Get current date and time in desired format
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss"); // Can be customized as needed
    String currentDate = dateFormat.format(new Date());

    // Append current date and time to baseline name
    String baselineName = stageBaselineName + "_" + currentDate;
    
       
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
    getJazzPageFactory().getGCStreamsPage().clickOnImageButton("Stage Baseline - Duplicate the selected stream to be the staging area to assemble a baseline");
    getJazzPageFactory().getGlobalConfigurationsPage().createStageBaseline(baselineName, null, null);
    Reporter.logPass(" Stage Baseline created successfully.");
    getJazzPageFactory().getGlobalConfigurationsPage().clickCommitBaseline(driver);
    Reporter.logPass("Stage Baseline committedd successfully.");
    getJazzPageFactory().getGlobalConfigurationsPage().clickGoToStream(streamName);
    Reporter.logPass("Clicked on: streamName successfully.");
    getJazzPageFactory().getGlobalConfigurationsPage().clickBaseline(baselineName);
    Reporter.logPass("Clicked on: baselineName successfully.");
    getJazzPageFactory().getGlobalConfigurationsPage().clickOnButtonInConfigPage("Edit");
    Reporter.logPass(" Clicked on: Edit button ");
    getJazzPageFactory().getGlobalConfigurationsPage().archive();
    Reporter.logPass(" Clicked on Archive button successfully ");
    getJazzPageFactory().getGlobalConfigurationsPage().clickOnButtonInConfigPage("Save");
    Reporter.logPass(" Clicked on Archive Save successfully ");
    getJazzPageFactory().getGlobalConfigurationsPage().confirmArchive();
    Reporter.logPass(" Clicked on confirmArchive button successfully ");
      
       
  }

}