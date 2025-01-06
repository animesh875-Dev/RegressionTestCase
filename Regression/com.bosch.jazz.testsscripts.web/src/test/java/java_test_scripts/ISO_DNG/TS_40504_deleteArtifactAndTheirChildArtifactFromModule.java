/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 *
 */
public class TS_40504_deleteArtifactAndTheirChildArtifactFromModule extends AbstractFrameworkTest{
  /**
   * This test case for deleting artifact and their child arifacts from module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_40504_deleteArtifactAndTheirChildArtifactFromModule() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String moduleID = this.testDataRule.getConfigData("MODULE_ID");
    String artifactName = this.testDataRule.getConfigData("ARTIFACT_NAME")+ DateUtil.getCurrentDateAndTime();
    String artifactType = this.testDataRule.getConfigData("ARTIFACT_TYPE");
    String artifactFormat = this.testDataRule.getConfigData("ARTIFACT_FORMAT");
    String existingArtifactID = this.testDataRule.getConfigData("EXISTING_ARTIFACT_ID");


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
    // Open searched collection
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
    //Select option 'Insert existing artifact ID below as a child
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("TARGET_ARTIFACT_ID", artifactID);
    additionalParams.put("TYPE_OF_INSERTION", "Below (as a Child)...");
    additionalParams.put("EXISTING_ARTIFACT_ID", existingArtifactID);
    getJazzPageFactory().getRMModulePage().insertExistingArtifact(additionalParams);
    Reporter.logPass("New artifact '" + artifactName + "' was created successfully inside module."); 
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Remove artifact ID out of module
    getJazzPageFactory().getRMModulePage().removeArtifact(artifactID);
    Reporter.logPass("Remove'" + artifactID + "' out of module successfully."); 
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Verify that parent artifact ID was removed successfully.
    Assert.assertFalse(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
    Reporter.logPass("Verified '" + artifactID + "' is not displayed inside module.");
    //Verify that child artifact ID was removed successfully.
    Assert.assertFalse(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(existingArtifactID));
    Reporter.logPass("Verified '" + existingArtifactID + "' is not displayed inside module.");
    
    
    
    //Quick search for the new created artifact ID
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactID);
    Reporter.logPass(artifactID + ": module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open searched collection
    getJazzPageFactory().getRMDashBoardPage().openSearchedSpecification(artifactID);
    Reporter.logPass("Open '" + artifactID + "' module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Delete the new created Artifact
    getJazzPageFactory().getRMModulePage().moreAction("Delete Artifact");
    Reporter.logPass("Delete'" + artifactID + "'successfully.");
    
  }

}
