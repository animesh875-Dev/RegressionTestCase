/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author JSC1COB
 *
 */
public class TS_40458_saveCollection extends AbstractFrameworkTest {
  /**
   * This test case for create/edit artifact content inside module.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  
  public void tcs_40458_saveCollection() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    
    String collectionID = this.testDataRule.getConfigData("COLLECTION_ID");
    String artifactName = this.testDataRule.getConfigData("ARTIFACT_NAME") + DateUtil.getCurrentDateAndTime();
    String artifactType = this.testDataRule.getConfigData("ARTIFACT_TYPE");
    String artifactFormat = this.testDataRule.getConfigData("ARTIFACT_FORMAT");
    String newArtifactName = artifactName+ " Modified";
    
   
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    
    // Quick search for Collection ID
    getJazzPageFactory().getRMDashBoardPage().quickSearch(collectionID);
    Reporter.logPass(collectionID + " - Collection found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    
    // Open the searched Collection
    getJazzPageFactory().getRMDashBoardPage().openSearchedSpecification(collectionID);
    Reporter.logPass("Opened '" + collectionID + "' Collection in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    // Click on 'Create New Artifact' link inside module and input data into dialog
    getJazzPageFactory().getRMArtifactPage().clickOnCreateNewArtifactLinkAndSetName(artifactName,artifactType,artifactFormat);
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Save");
    Reporter.logPass("New artifact '" + artifactName + "' was created successfully inside collection.");
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactName));
      
    // Get Artifact ID inside module by artifact content
    String artifactID = getJazzPageFactory().getRMArtifactPage().getArtifactIdByIndex("1");
    Reporter.logPass(" Artifact ID is "+ artifactID);
        
    // Add 'Review' column
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("Artifact Format");
    Reporter.logPass("'Artifact Format' column added to the collection table view  successfully");
    
    // Edit name of artifact
    getJazzPageFactory().getRMArtifactPage().editArtifactName(artifactName, newArtifactName);
    Reporter.logPass("Edited artifact Name successfully"+ "'" +newArtifactName +"'" );
    
    
    // Verify that edit and save artifact 
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isExistingTextInArtifactContent(newArtifactName));
    Reporter.logPass( "'" + newArtifactName + "'" + "  with new name is displayed in collection");
    
    // Remove artifact from Collection
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Remove Artifact...");
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Yes");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    Reporter.logPass(newArtifactName + "  was Removed successfully from the collection");
      
  //Delete Artifact
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
    Reporter.logPass(artifactID  + " was filtered successfully");
    
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Delete Artifact...");
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Delete Artifact");
    Reporter.logPass(artifactID + " was deleted successfully");
    Assert.assertFalse(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
    Reporter.logPass("Verified '" + artifactID + "' does not exists.");
           
  }
}
