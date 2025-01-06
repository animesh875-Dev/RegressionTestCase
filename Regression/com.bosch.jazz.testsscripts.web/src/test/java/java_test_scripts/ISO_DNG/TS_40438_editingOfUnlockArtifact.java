/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BSPA1KOR
 *
 */
public class TS_40438_editingOfUnlockArtifact extends AbstractFrameworkTest {
  /**
   * This test case for Unlocking the Artifact.
   * @throws InvalidKeyException 
   *
   */
  @Test
  public void tcs_40438_editingOfUnlockArtifact() throws InvalidKeyException  {
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactID = this.testDataRule.getConfigData("ARTIFACT_ID");
    
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
    //Open artifact ID
    getJazzPageFactory().getRMArtifactPage().clickOnArtifact(artifactID);
    Reporter.logPass(artifactID + "open succesfully");
    //Lock Artifact     
    getJazzPageFactory().getRMArtifactPage().clickOnImageButton("The artifact is unlocked. Click to manually lock it for editing.");
    Reporter.logPass(artifactID +"The Artifact has locked");
    //Verification of Artifact is Locked
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isButtonDisplayed("You locked this artifact. Click to unlock."));
    Reporter.logPass("Verified '" + artifactID + "' is locked succesfully"); 
    //getJazzPageFactory().getRMArtifactPage().navigateBack();
    //unlock
    getJazzPageFactory().getRMArtifactPage().clickOnImageButton("You locked this artifact. Click to unlock.");
    Reporter.logPass(artifactID + "unlocked succesfully");
    //verifying Unlocking
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isButtonDisplayed("The artifact is unlocked. Click to manually lock it for editing."));
    Reporter.logPass("Verified '" + artifactID + "' is unlocked succesfully");
    
  

}
}