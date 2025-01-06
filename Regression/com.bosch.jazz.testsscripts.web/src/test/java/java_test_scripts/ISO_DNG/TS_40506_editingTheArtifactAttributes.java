/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import java.security.InvalidKeyException;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BSPA1KOR
 *
 */
public class TS_40506_editingTheArtifactAttributes extends AbstractFrameworkTest {
  /**
   * This test case for copy and paste of the Artifact  in different module.
   * @throws InvalidKeyException 
   *
   */
  @Test
  public void tcs_40506_editingTheArtifactAttributes() throws InvalidKeyException  {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactID = this.testDataRule.getConfigData("ARTIFACT_ID");
    String artifactType = this.testDataRule.getConfigData("ARTIFACT_TYPE");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + "User logged into the " + url + "repository succesfully");
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // open Artifact menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + "Menu opened succesfully");
    //Search for artifact ID
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
    Reporter.logPass(artifactID + "was selected succesfully");
    // select the artifact and open context menu
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Edit Attributes...");
    Reporter.logPass(artifactID + "Opened context menu and Edit Attributes");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Changing Artifact type 
    getJazzPageFactory().getRMArtifactPage().editArtifactAttribute("Artifact Type", "Requirement");
    Reporter.logPass(artifactID + "Artifact Type Requirement has been changed");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Click on Save button
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Save");
    Reporter.logPass(artifactID + "Artifact has been saved succesfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //click on confirmation button 
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Yes");
    Reporter.logPass(artifactID + "Confirm Message Yes has been clicked");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    
    //Changing Attributes to previous
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Edit Attributes...");
    Reporter.logPass(artifactID + "Opened context menu and Edit Attributes");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Changing Artifact type 
    getJazzPageFactory().getRMArtifactPage().editArtifactAttribute("Artifact Type", artifactType);
    Reporter.logPass(artifactID + "Artifact Type Requirement has been changed to previous one");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Click on Save button
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Save");
    Reporter.logPass(artifactID + "Artifact has been saved succesfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //click on confirmation button 
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Yes");
    Reporter.logPass(artifactID + "Confirm Message Yes has been clicked");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
  
}
  
}


