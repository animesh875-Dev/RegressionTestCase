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
 * @author JSC1COB
 *
 */
public class TS_40517_saveArtifact  extends AbstractFrameworkTest{
  /**
   * This test case for Artifact creation and Save it.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
 
  @Test
  public void tcs_40517_saveArtifact() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
   
    String artifactName = "Artifact_"+ DateUtil.getCurrentDateAndTime();
  
     // String msg="New Test Value";
    String msg=this.testDataRule.getConfigData("MSG");

    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
 
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    
    //Click on Create Button
     getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Other...");
     getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
               
     //Create and Open an Artifact
     Map<String, String> inputParameters = new LinkedHashMap<String, String>();
     inputParameters.put("ARTIFACT_NAME", artifactName);
     inputParameters.put("ARTIFACT_TYPE", "Heading");
     inputParameters.put("ARTIFACT_FORMAT", "Text");
     inputParameters.put("txtInitalContent", "Artifact Values");
       
     getJazzPageFactory().getRMArtifactPage().createArtifact(inputParameters);
     getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Done");
     
     Reporter.logPass("New artifact '" + artifactName + "' was created successfully ");
     String artifactID = getJazzPageFactory().getRMArtifactPage().getArtifactID();
     Reporter.logPass(" Artifact ID is"+artifactID);
        
     //Edit Artifact Content
     getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Edit");
     getJazzPageFactory().getRMArtifactPage().inputArtifactContent(msg);
     getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Done");
     Reporter.logPass("Edited artifact '" + artifactName + "' successfully ");
     getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
     
     
     //Navigate To Artifacts Page
     getJazzPageFactory().getRMArtifactPage().navigateToArtifactsPage();
     getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
     
    
     //Filter Artifact By TextOrId
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactName);
    Reporter.logPass(artifactName + "was selected successfully");
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactName));
    Reporter.logPass("Verified '" + artifactName + "' is displayed in the table.");
    
    //Delete Artifact 
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Delete Artifact...");
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Delete Artifact");
    Reporter.logPass(artifactName + "was deleted successfully");
    Assert.assertFalse(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
    Reporter.logPass("Verified '" + artifactName + "' is not  displayed in the table.");
          
  }

}
