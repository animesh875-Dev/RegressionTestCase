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
public class TS_40448_moveArtifact_2 extends AbstractFrameworkTest {
  /**
   * This test case for move the Artifact to folder .
   * @throws InvalidKeyException 
   *
   */
  @Test
  public void tcs_40448_moveArtifact_2()throws InvalidKeyException {
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactID = this.testDataRule.getConfigData("ARTIFACT_ID");
    String folderName = this.testDataRule.getConfigData("TARGET_FOLDER");
    String sourcefolderName = this.testDataRule.getConfigData("SOURCE_FOLDER");
    
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
    Reporter.logPass(artifactID + "Searched succesfully");
    // select the artifact and open context menu
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Move Artifact to Folder...");
    Reporter.logPass(artifactID + "Opened context menu and clicked on Move artifact to folder");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // select the folder to move artifact
    getJazzPageFactory().getRMArtifactPage().selectFolderFromMoveArtifactToFolder(folderName);
    Reporter.logPass(folderName + "Selected the folder successfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Click on Ok button
    getJazzPageFactory().getRMArtifactPage().clickOnButtons("OK");
    Reporter.logPass(folderName + "Clicked on Ok button");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    Reporter.logPass("Verification of the Artifact moved to the folder");
    
    // Verify that artifact has moved to the folder
    getJazzPageFactory().getRMArtifactPage().clearFilter();
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(folderName);
    //Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().verifyArtifactVisibleInViewport(artifactID));
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
    Reporter.logPass(artifactID + "is moved to the" + folderName + "folder");
    
    // Reverting back to its Source folder
    getJazzPageFactory().getRMArtifactPage().clearFilter();
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(folderName);
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Move Artifact to Folder...");
    getJazzPageFactory().getRMArtifactPage().selectFolderFromMoveArtifactToFolder(sourcefolderName);
    Reporter.logPass(artifactID + "is moved to the" + sourcefolderName + "folder");
    
  }
  

}
