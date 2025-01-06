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
public class TS_40546_testTheCopyAndPasteOfArtifact extends AbstractFrameworkTest {

  /**
   * This test case for copy and paste of the Artifact  in different module.
   * @throws InvalidKeyException 
   *
   */
  @Test
  public void tcs_40546_testTheCopyAndPasteOfArtifact() throws InvalidKeyException  {
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactID = this.testDataRule.getConfigData("ARTIFACT_ID");
    String moduleID = this.testDataRule.getConfigData("MODULE_ID");
    String artifactID_2 = this.testDataRule.getConfigData("ARTIFACT_ID_2");
    
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
    
    //Copy Artifact
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID,"Copy Artifact");
    Reporter.logPass(artifactID + "Opened context menu and Copied Artifact");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    //Search for module ID
    getJazzPageFactory().getRMArtifactPage().clickOnArtifactTypes("Modules");
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(moduleID);
    Reporter.logPass(moduleID + "was selected succesfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    //Open Module ID
    getJazzPageFactory().getRMArtifactPage().clickOnArtifact(moduleID);
    Reporter.logPass(moduleID + "open succesfully");
    //select Artifact inside module
    getJazzPageFactory().getRMLinksPage().selectContextMenuOfArtifactInModule(artifactID_2, "Paste Artifact", "After");
    Reporter.logPass("in" + artifactID_2 + "pasted succesfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //verifying artifact has pasted
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
    Reporter.logPass("Verified '" + artifactID + "' is displayed in artifact table.");
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
    Reporter.logPass("Verified '" + artifactID + "' is displayed in Module.");
    
    //Remove copied artifact from module
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID,"Remove Artifact...");
    Reporter.logPass(artifactID + ": click on context menu and select option 'Remove Artifact successfully'");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Remove");
    Reporter.logPass("Remove Artifact out of Module successfully");
}
}