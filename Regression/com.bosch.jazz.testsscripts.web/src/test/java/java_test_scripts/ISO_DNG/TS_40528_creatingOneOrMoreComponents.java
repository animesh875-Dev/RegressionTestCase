/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 *
 */
public class TS_40528_creatingOneOrMoreComponents extends AbstractFrameworkTest {
  /**
   * This test case for creating new component.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_40528_creatingOneOrMoreComponents() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_GC_PROJECT_AREA");
    String globalArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String name = this.testDataRule.getConfigData("NEW_COMPONENT_NAME")+ DateUtil.getCurrentDateAndTime();
    String description = this.testDataRule.getConfigData("COMPONENT_DESCRIPTION");
    String templateName = this.testDataRule.getConfigData("TEMPLATE_NAME");

    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMDashBoardPage().selectProjectAreaAndGlobalConfiguration(projectArea, globalArea,
        componentName, streamName);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    //Create New Component from Administrator menu
    getJazzPageFactory().getRMDashBoardPage().createComponentUsingTemplate(name, description, templateName);
    Reporter.logPass("Create new component with name:"+name);
    //Verify that component is created successfully
    Map<String, String> param = new LinkedHashMap();
    param.put("applicationName","RM");
    param.put("expectedProjectArea",projectArea);
    param.put("expectedComponent",name);
    param.put("expectedStream",name+" Initial Stream");
    getJazzPageFactory().getGlobalConfigurationsPage().verifyThatOpenNewAddedConfigurationSucessfully(param);
    Reporter.logInfo("Create new component successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    //Create duplicate component
    getJazzPageFactory().getRMDashBoardPage().createComponentUsingTemplate(name, description, templateName);
    Reporter.logPass("Create new component with name:"+name);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Verify Duplicate component with same component name is not created
    assertTrue(
        getJazzPageFactory().getRMDashBoardPage().isWarningDuplicateComponentDisplayed(name));
    Reporter.logPass("Duplicate component with same component name is not created");
    
    //Close Create Component dialog
    getJazzPageFactory().getRMDashBoardPage().clickOnDialogButton("Create Component", "Cancel");
    Reporter.logPass("Close Create Component dialog successfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    
    // Click on Administration menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsMenu("Administration");
    Reporter.logPass("Click on Admistration menu");
    // Click on Manage Components and Configurations menu
    getJazzPageFactory().getRMDashBoardPage().openSettingsSubMenu("Manage Components and Configurations");
    Reporter.logPass("Click on Manage Components and Configurations menu");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Click on "Browse Components" link
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink("Browse Components");
    Reporter.logPass("Click on Browse Components link");
    //Search for component name
    getJazzPageFactory().getRMManageComponentsAndConfigurations().searchComponentInBrowseComponentPage(name);
    Reporter.logPass("Search for Component name to archive");
    //Open Component
    getJazzPageFactory().getRMManageComponentsAndConfigurations().clickOnLink(name);
    Reporter.logPass("Open component successfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Archive the new created component
    getJazzPageFactory().getRMManageComponentsAndConfigurations().archiveActiveComponent();;
    Reporter.logPass("Archive active component");
  }

}
