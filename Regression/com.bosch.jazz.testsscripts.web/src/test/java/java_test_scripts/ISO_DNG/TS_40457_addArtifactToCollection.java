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
 */
public class TS_40457_addArtifactToCollection  extends AbstractFrameworkTest{
  /**
   * This test case for Add artifact to a collection from context menu of the selected artifact.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_40457_addArtifactToCollection() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactId = this.testDataRule.getConfigData("ARTIFACT_ID");
    String menuOption = this.testDataRule.getConfigData("MENU_OPTION");
    String collectionId = this.testDataRule.getConfigData("COLLECTION_ID");
    


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
    //Search for artifact ID
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactId);
    Reporter.logPass(artifactId + "was selected successfully");
    // Open the context menu and select "Add Artifact to a Collection..."
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId,menuOption);
    Reporter.logPass(artifactId + ": click on context menu and select option 'Add artifact to collection successfully'");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Select Collection ID from 'Add Artifact to a collection' dialog.
    getJazzPageFactory().getRMCollectionsPage().addArtifactToCollection(collectionId);
    Reporter.logPass(collectionId + "was selected successfully");
    //Click on 'Add and Close' button in dialog
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Add and Close");
    Reporter.logPass("Add and Close button was selected successfully");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    //Quick search for collection ID
    getJazzPageFactory().getRMDashBoardPage().quickSearch(collectionId);
    Reporter.logPass(collectionId + ": module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open searched collection
    getJazzPageFactory().getRMDashBoardPage().openSearchedSpecification(collectionId);
    Reporter.logPass("Open '" + collectionId + "' module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Search for created artifact id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactId);
    Reporter.logPass(artifactId + " artifact found in the project area");
    // Verify the created artifact is displayed.
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactId));
    Reporter.logPass("Verified '" + artifactId + "' is displayed in artifact table.");
    
    // Remove Artifact ID from Collection.
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId,"Remove Artifact");
    Reporter.logPass(artifactId + ": click on context menu and select option 'Remove Artifact successfully'");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    //Click on 'Add and Close' button in dialog
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Yes");
    Reporter.logPass("Remove Artifact out of Collection successfully");
  }

}
