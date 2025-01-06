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
public class TS_40439_exportArtifactWithCorrectData extends AbstractFrameworkTest {
  /**
   * This testcase tests exporting artifact from a folder in XLSX form 
   * 
   */
  @Test
  public void tcs_40439_exportArtifactWithCorrectData() throws InvalidKeyException  {
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactID = this.testDataRule.getConfigData("ARTIFACT_ID");
    String folderName = this.testDataRule.getConfigData("TARGET_FOLDER");
    String rootFolder = this.testDataRule.getConfigData("ROOT_FOLDER");
    String artifactName = this.testDataRule.getConfigData("ARTIFACT_NAME");
    String artifactType = this.testDataRule.getConfigData("ARTIFACT_TYPE");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass("'"+ getUserId() + "' user logged into the '" + url + "' repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // open Artifact menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " Menu opened successfully.");
    // Select root folder
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(rootFolder);
    Reporter.logPass("'"+rootFolder +"': root folder selected.");
    // Select a folder 
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(folderName);
    Reporter.logPass("'"+folderName + "': Selected the Folder under root folder - '"+rootFolder+"'");
    //Select the artifact
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactID);
    Reporter.logPass("'"+artifactID + "' Artifact id Selected under the folder.");
    // select the artifact and open context menu
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID,"Export Artifact...");
    Reporter.logPass( "Opened context menu and clicked on Export artifact.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //In XLSX form
    getJazzPageFactory().getRMArtifactPage().clickOnRadioButtonInExportDialog("XLSX");
    Reporter.logPass("Artifact exported into XLSX format.");
    getJazzPageFactory().getRMArtifactPage().clickOnButtons("OK");
    
    
   String path = getJazzPageFactory().getRMArtifactPage().getDownloadedFileNameAndPath();
   System.out.println(path);
   //Asserting the data of artifact ID
   String actualID = getJazzPageFactory().getRMArtifactPage().getDataFromCellOfXLSX(path, "0","1","0");
  // Reporter.logPass(actualID);
   System.out.println("Actualresults: " + actualID);
   System.out.println("Expectedresults: " + artifactID);
   Assert.assertEquals(artifactID, actualID);
   Reporter.logPass("Verified: Exported and actual Artifact ID- '"+artifactID+ "' are equal."); 
   //Asserting the data of artifact Name
   String actualName = getJazzPageFactory().getRMArtifactPage().getDataFromCellOfXLSX(path, "0", "1", "1");      
   System.out.println("Actualresults: " + actualName);
   System.out.println("Expectedresults: " + artifactName);
   
   Assert.assertEquals(actualName,artifactName );
   Reporter.logPass("Verified: Exported and actual Artifact Name- '"+artifactName+ "' are equal.");  
   //Asserting the data of artifact type
   String actualType = getJazzPageFactory().getRMArtifactPage().getDataFromCellOfXLSX(path, "0", "1", "2");      
   System.out.println("Actualresults: " + actualType);
   System.out.println("Expectedresults: " + artifactType);
   
   Assert.assertEquals(actualType,artifactType );
   Reporter.logPass("Verified: Exported and actual Artifact Type- '"+artifactType+ "' are equal.");
  }
  
  }

