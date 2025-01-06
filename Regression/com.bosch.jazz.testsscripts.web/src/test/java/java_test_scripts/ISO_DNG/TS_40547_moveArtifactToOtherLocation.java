/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 *
 */
public class TS_40547_moveArtifactToOtherLocation extends AbstractFrameworkTest{
  /**
   * This test case for moving artifcat to another folder.
   *
   * @throws Throwable is use to handle any kind of exception. 
   */
  @Test
  public void tcs_40547_moveArtifactToOtherLocation() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactId = this.testDataRule.getConfigData("ARTIFACT_ID");
    String rootFolder = this.testDataRule.getConfigData("ROOT_FOLDER");
    String targetFolder = this.testDataRule.getConfigData("TARGET_FOLDER");
    
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
    getJazzPageFactory().getRMArtifactPage().waitForSecs(4);
    //Select root folder where artifact is inside.
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(rootFolder);
    Reporter.logPass(rootFolder.toString() + " is selected successfully.");
    //Click on 'Add Filter' button
    getJazzPageFactory().getRMArtifactPage().addFilter();
    Reporter.logPass("Click on 'Add Filter' button successfully");
    //Select attribute value in New Filter dialog
    getJazzPageFactory().getRMArtifactPage().chooseAttributeValueForSelectedAttribute("Artifact ID", artifactId);
    Reporter.logPass("Add filter by Artifact ID successfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open the context menu and select "Move Artifact to Folder..."
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId,"Move Artifact to Folder...");
    Reporter.logPass(artifactId + ": click on context menu and select option 'Move Artifact to Folder...'");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    //Select folder to move artifact 
    getJazzPageFactory().getRMArtifactPage().clickOnDialogLabel("Move Artifact to Folder...",targetFolder);
    Reporter.logPass( "Select folder to move artifact successfully");
    //Select 'OK' button in 'Move artifact to folder' dialog
    getJazzPageFactory().getRMArtifactPage().clickOnDialogButton("Move Artifact to Folder...", "OK");
    Reporter.logPass( "Click on OK in 'Move Artifact to Folder...' dialog successfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    
    //Verify that Artifact is not retain in folder after moving to another folder
    Assert.assertFalse(
    getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactId));
    Reporter.logPass( "Artifact is moved out of current folder successfully");
    //Open the target folder
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(targetFolder);
    Reporter.logPass(targetFolder + " is selected successfully.");
    //Click on 'Add Filter' button
    getJazzPageFactory().getRMArtifactPage().addFilter();
    Reporter.logPass("Click on 'Add Filter' button successfully");
    //Select attribute value in New Filter dialog
    getJazzPageFactory().getRMArtifactPage().chooseAttributeValueForSelectedAttribute("Artifact ID", artifactId);
    Reporter.logPass("Add filter by Artifact ID successfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    //Verify that Artifact is existed in the target folder
    Assert.assertTrue(
    getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactId));
    Reporter.logPass( "Artifact is moved to target folder successfully");
  }

}
