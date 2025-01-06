/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 *
 */
public class TS_39805_renameConfigurations extends AbstractFrameworkTest {

  /**
   * This test case for create/edit artifact content inside module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39805_renameConfigurations() throws Throwable {
    String url = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    
    String editButton = this.testDataRule.getConfigData("EDIT_BUTTON");
    String saveButton = this.testDataRule.getConfigData("SAVE_BUTTON");
    String editStreamName = this.testDataRule.getConfigData("EDIT_STREAM_NAME"); 
    
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
    
    // Click on Edit button
    getJazzPageFactory().getGlobalConfigurationsPage().clickOnButtonInConfigPage(editButton);
    // Update new stream name
    editStreamOnlyName(editStreamName);
    // Click on Save button
    getJazzPageFactory().getGlobalConfigurationsPage().clickOnButtonInConfigPage(saveButton);
    //Assert result
    Assert.assertTrue(isElementVisible(editStreamName));
    Reporter.logPass("Stream name: " + streamName + "\nhas been updated to: " + editStreamName);
    
    //Teardown
    // Click on Edit button
    getJazzPageFactory().getGlobalConfigurationsPage().clickOnButtonInConfigPage(editButton);
    // Update new stream name
    editStreamOnlyName(streamName);
    // Click on Save button
    getJazzPageFactory().getGlobalConfigurationsPage().clickOnButtonInConfigPage(saveButton);
    //Assert result
    Assert.assertTrue(isElementVisible(streamName));
    Reporter.logPass("Stream name: " + editStreamName + " \nhas been reverted to original name: " + streamName);
  }
  
  @SuppressWarnings("javadoc")
  public boolean isElementVisible(String name) {
    return driver
        .findElement(
            By.xpath(("//*[text()='"+name+"']")))
        .isDisplayed();
  }
  @SuppressWarnings("javadoc")
  public void editStreamOnlyName(final String streamName) {
    WebElement streamNameXpath = driver.findElement(By.xpath(
        "//div[@dojoattachpoint='_titleNode']/child::input"));

    streamNameXpath.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    streamNameXpath.sendKeys(streamName);
    
    getJazzPageFactory().getGlobalConfigurationsPage().waitForSecs(5);// After click wait some time to pop up the dialog.
    LOGGER.LOG.info("New Stream Name:" + streamName + "updated.");
  }
}
