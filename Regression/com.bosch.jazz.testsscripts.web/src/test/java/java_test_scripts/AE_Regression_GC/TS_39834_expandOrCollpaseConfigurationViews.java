/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_GC;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YUU3HC
 *
 */
public class TS_39834_expandOrCollpaseConfigurationViews extends AbstractFrameworkTest{
  /**
   * This test case for verify expand all and collapse all button function 
   * in Configuration Views
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39834_expandOrCollpaseConfigurationViews() throws Throwable {
    String url = this.testDataRule.getConfigData("GC_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    
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
    getJazzPageFactory().getComponentsPage().filterAndSelectComponent(componentName);
    getJazzPageFactory().getGCStreamsPage().filterAndSelectStream(streamName);
    Reporter.logPass("Selected component: " + componentName + "\nSelected Stream: " + streamName);
        
    getJazzPageFactory().getAbstractGCPage().waitForSecs(5);
    //Find and click on Expand All button
    gcExpandAllCollapseAll("Expand All");
    Reporter.logPass("Expand All button clicked - All the configurations inside the component expanded");
    // Find all elements contains attribute "aria-expanded" as true
    String ariaExpandText= driver.findElement(By.className("gc-Tree-label")).getAttribute("aria-expanded");
    // Find Stream
    WebElement mainStream = driver.findElement(By.xpath("//img[@class='gc-Tree-icon gc-icon-stream']//following-sibling::span"));  
    // Verify if all dropdown expanded
    Assert.assertTrue("Dropdown is expanded", ariaExpandText.equalsIgnoreCase("true") && mainStream.getText().equalsIgnoreCase(streamName));
    Reporter.logPass("Verified all the configurations inside the component expanded");
    
    getJazzPageFactory().getAbstractGCPage().waitForSecs(5);
    //Find and click on Collapse All button
    gcExpandAllCollapseAll("Collapse All");
    Reporter.logPass("Collapse All button clicked - All the configurations inside the component collapsed");
    // Find all elements contains attribute "aria-expanded" as false
    String ariaExpandText1= driver.findElement(By.className("gc-Tree-label")).getAttribute("aria-expanded");
    // Verify if all dropdown collapsed
    Assert.assertTrue("Dropdown is collapse", ariaExpandText1.equalsIgnoreCase("false") && mainStream.getText().equalsIgnoreCase(streamName));
    Reporter.logPass("Verified all the configurations inside the component expanded");    
  }
  
  /**
   * <p>
   * This method find Expand/Collapse All button <br>
   * based on it's current action: expand or collapse
   * @param action - "Expand All","Collapse All".
   */
  public void gcExpandAllCollapseAll(final String action) {
    // Xpath for Expand All button
    WebElement expandAllBtn = driver.findElement(By.xpath("//img[contains(@alt,'Expand All')]"));    
    // Xpath for Expand action 
    WebElement expandAction = driver.findElement(By.xpath("//a[contains(@class,'button') and contains(@title, '"+ action +"')]"));
    switch(action) {
      case "Expand All":
        // Check if element collapsed, click to expand
        if(expandAction.getAttribute("title").contains("Expand All")) {
          expandAllBtn.click();
        }
        LOGGER.LOG.info("All the configurations inside the component expanded");
        break;
      case "Collapse All":
        // Check if element expanded, click to collapse
        if(expandAction.getAttribute("title").contains("Collapse All")) {
          expandAllBtn.click();
        }
        LOGGER.LOG.info("All the configurations inside the component collapsed");
        break;
      default:
        throw new WebAutomationException("Expand All is not handle in automation");
    }      
  }
  
  @SuppressWarnings("javadoc")
  public void verifyGCExpandAllCollapseAll() {
    // Xpath for Stream/ subStream
    WebElement mainStream = driver.findElement(By.xpath("//img[@class='gc-Tree-icon gc-icon-stream']"));
    WebElement stream = driver.findElement(By.xpath("//span[@class='gc-Tree-label' and @role='treeitem']"));
    try {
      if(stream.isDisplayed()) {
        LOGGER.LOG.info("All the configurations inside the component expanded");
      }
    }
    catch (Exception e) {
      if(mainStream.isDisplayed() && !stream.isDisplayed()) {
        LOGGER.LOG.info("All the configurations inside the component collapsed");
      }
    }
  }
  
  @SuppressWarnings("javadoc")
  public boolean isElementVisible(String name) {
    return driver
        .findElement(
            By.xpath(("//*[text()='"+name+"']")))
        .isDisplayed();
  }
}
