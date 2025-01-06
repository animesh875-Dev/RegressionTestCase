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
 * @author JSC1COB
 *
 */
public class TS_40460_ableToExportMultipleArtifacts extends AbstractFrameworkTest{

  /**
   * This testcase tests exporting artifact from a folder in XLS form 
   * @throws InvalidKeyException 
   * 
   */
  @Test
  public void tcs_40460_ableToExportMultipleArtifacts() throws InvalidKeyException{
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactID_1= this.testDataRule.getConfigData("ARTIFACT_ID1");
    String artifactID_2 = this.testDataRule.getConfigData("ARTIFACT_ID2");
    String artifactID_3 = this.testDataRule.getConfigData("ARTIFACT_ID3");
    String folderName = this.testDataRule.getConfigData("TARGET_FOLDER");
    String rootFolder = this.testDataRule.getConfigData("ROOT_FOLDER");
    String artifactName1= this.testDataRule.getConfigData("ARTIFACT_NAME1");
    String artifactName2= this.testDataRule.getConfigData("ARTIFACT_NAME2");
    String artifactName3= this.testDataRule.getConfigData("ARTIFACT_NAME3");
    String artifact1Type= this.testDataRule.getConfigData("ARTIFACT1_TYPE");
    String artifact2Type= this.testDataRule.getConfigData("ARTIFACT2_TYPE");
    String artifact3Type= this.testDataRule.getConfigData("ARTIFACT3_TYPE");
   
    Reporter.logInfo("*********Set Up***********");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + "User logged into the " + url + "repository succesfully");
    
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    // open Artifact menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " Menu opened succesfully");
    
    // Select root folder
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(rootFolder);
    Reporter.logPass(rootFolder + ": which is root folder selected.");
    
    // Select a folder 
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(folderName);
    Reporter.logPass(folderName + ": Selected the Folder under root folder - ISO-CandQ.");
   
    //Search and Select the Artifacts using Artifacts ID
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactID_1);
    Reporter.logPass(artifactID_1 + " Artifact1 selected succesfully");
  
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactID_2);
    Reporter.logPass(artifactID_2 + " Artifact 2 selected succesfully");
   
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactID_3);
    Reporter.logPass(artifactID_3 + " Artifact 3 selected succesfully");
    
      
    // Open Context Menu for Selected Artifacts
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID_3,"Export 3 Artifacts...");
    Reporter.logPass("'" +artifactID_1+ "'"+  "'"+artifactID_2+"'" + "'"+artifactID_3+"'"   + " are selected and opened context menu and clicked on Export 3 artifacts");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    
    //In XLS form
    getJazzPageFactory().getRMArtifactPage().clickOnRadioButtonInExportDialog("XLS");
    Reporter.logPass("'" +artifactID_1+ "'"+  "'"+artifactID_2+"'" + "'"+artifactID_3+"'" + " are exported in XLS format ");
    getJazzPageFactory().getRMArtifactPage().clickOnButtons("OK");
    
    String path = getJazzPageFactory().getRMArtifactPage().getDownloadedFileNameAndPath();
    System.out.println(path);
    
    //Verification of Artifact Contents
    //Verification of Artifact ID
    String actualID_1=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","1","0");
    System.out.println("Actualresults: " + actualID_1);
    System.out.println("Expectedresults: " + artifactID_1);
    Assert.assertEquals(artifactID_1, actualID_1);
    Reporter.logPass("Verified: Exported and actual Artifact ID- '"+artifactID_1+ "' are equal."); 
    
    String actualID_2=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","2","0");
    System.out.println("Actualresults: " + actualID_2);
    System.out.println("Expectedresults: " + artifactID_2);
    Assert.assertEquals(artifactID_2, actualID_2);
    Reporter.logPass("Verified: Exported and actual Artifact ID- '"+artifactID_2+ "' are equal."); 
    
    String actualID_3=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","3","0");
    System.out.println("Actualresults: " + actualID_3);
    System.out.println("Expectedresults: " + artifactID_3);
    Assert.assertEquals(artifactID_3, actualID_3);
    Reporter.logPass("Verified: Exported and actual Artifact ID- '"+artifactID_3+ "' are equal."); 
    
    //Verification of Artifact Names
    String actualName1=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","1","1");
    System.out.println("Actualresults: " + actualName1);
    System.out.println("Expectedresults: " + artifactName1);
    Assert.assertEquals(artifactName1, actualName1);
    Reporter.logPass("Verified: Exported and actual Artifact Name- '"+artifactName1+ "' are equal."); 
    
    String actualName2=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","2","1");
    System.out.println("Actualresults: " + actualName2);
    System.out.println("Expectedresults: " + artifactName2);
    Assert.assertEquals(artifactName2, actualName2);
    Reporter.logPass("Verified: Exported and actual Artifact Name- '"+artifactName2+ "' are equal."); 
    
    String actualName3=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","3","1");
    System.out.println("Actualresults: " + actualName3);
    System.out.println("Expectedresults: " + artifactName3);
    Assert.assertEquals(artifactName3, actualName3);
    Reporter.logPass("Verified: Exported and actual Artifact Name- '"+artifactName3+ "' are equal."); 
    
    //Verification of Artifact Types
    String actualType1=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","1","2");
    System.out.println("Actualresults: " + actualType1);
    System.out.println("Expectedresults: " + artifact1Type);
    Assert.assertEquals(artifact1Type, actualType1);
    Reporter.logPass("Verified: Exported and actual Artifact Name- '"+artifact1Type+ "' are equal."); 
    
    String actualType2=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","2","2");
    System.out.println("Actualresults: " + actualType2);
    System.out.println("Expectedresults: " + artifact2Type);
    Assert.assertEquals(artifact2Type, actualType2);
    Reporter.logPass("Verified: Exported and actual Artifact Name- '"+artifact2Type+ "' are equal."); 
    
    String actualType3=getJazzPageFactory().getRMArtifactPage().getIndividualDataFromCellOfXLS(path, "0","3","2");
    System.out.println("Actualresults: " + actualType3);
    System.out.println("Expectedresults: " + artifact3Type);
    Assert.assertEquals(artifact3Type, actualType3);
    Reporter.logPass("Verified: Exported and actual Artifact Name- '"+artifact3Type+ "' are equal."); 
    
  }
}
