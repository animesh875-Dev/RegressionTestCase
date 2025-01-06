/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 */
public class TS_40437_saveTheModule extends AbstractFrameworkTest {

  /**
   * This test case for create/edit artifact content inside module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_40437_saveTheModule() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String moduleID = this.testDataRule.getConfigData("MODULE_ID");
    String artifactName = this.testDataRule.getConfigData("ARTIFACT_NAME") + DateUtil.getCurrentDateAndTime();
    String artifactType = this.testDataRule.getConfigData("ARTIFACT_TYPE");
    String artifactFormat = this.testDataRule.getConfigData("ARTIFACT_FORMAT");
    String newArtifactName = artifactName+ "updated";


    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    //Quick search for Module ID
    getJazzPageFactory().getRMDashBoardPage().quickSearch(moduleID);
    Reporter.logPass(moduleID + ": module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open the searched module
    getJazzPageFactory().getRMDashBoardPage().openSearchedSpecification(moduleID);
    Reporter.logPass("Open '" + moduleID + "' module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Click on 'Create New Artifact' link inside module and input data into dialog
    getJazzPageFactory().getRMArtifactPage().clickOnCreateNewArtifactLinkAndSetName(artifactName,artifactType,artifactFormat);
    Reporter.logPass("New artifact '" + artifactName + "' was created successfully inside module.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Get Artifact ID inside module by artifact content
    String artifactID = getJazzPageFactory().getRMModulePage().getArtifactIDByArtifactContent(artifactName);
    Reporter.logPass(" Artifact ID is"+artifactName);
    //Add 'Artifact Name' column
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("Name");
    Reporter.logPass("Add artifact Name column to view successfully");
    //Edit name of artifact
    getJazzPageFactory().getRMArtifactPage().editArtifactName(artifactName, newArtifactName);
    Reporter.logPass("Edit artifact Name successfully");
    //Verify that edit and save artifact successfully
    getJazzPageFactory().getRMArtifactPage().isExistingTextInArtifactContent(newArtifactName);
    Reporter.logPass("Artifact with new name is displayed in module");
    
    //Delete artifact
    getJazzPageFactory().getRMModulePage().deleteArtifact(artifactID);
    Reporter.logPass("Artifact with new name is displayed in module");
    
  }
}
