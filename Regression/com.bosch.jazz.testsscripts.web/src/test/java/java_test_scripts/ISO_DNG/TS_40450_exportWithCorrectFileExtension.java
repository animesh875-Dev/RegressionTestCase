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
 * @author MOW2KOR
 *
 */
public class TS_40450_exportWithCorrectFileExtension extends AbstractFrameworkTest{

  /**
   * This test case is created to test the exporting the file in to correct extension.
   * @throws InvalidKeyException 
   */
  @SuppressWarnings("javadoc")
  @Test
  public void tcs_40450_exportWithCorrectFileExtension() throws InvalidKeyException {
    String url=this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea=this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String rootFolder=this.testDataRule.getConfigData("ROOT_FOLDER");
    String childFolder=this.testDataRule.getConfigData("CHILD_FOLDER");
    String artifactId=this.testDataRule.getConfigData("ARTIFACT_ID");
    String artifactName=this.testDataRule.getConfigData("ARTIFACT_NAME");
    String artifactType=this.testDataRule.getConfigData("ARTIFACT_TYPE");
    String fileExtension=this.testDataRule.getConfigData("FILE_EXTENSION");

    //Login to ALM application with valid username and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " User logged in to the " + url + " repository successfully.");
    //Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(projectArea+ ": project area opened successfully.");    
    //Open Artifact menu. 
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " Menu opened successfully.");
    //Select Root folder.
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(rootFolder);
    Reporter.logPass(rootFolder+" Folder selected successfully.");
    //Select Child folder.
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(childFolder);
    Reporter.logPass(childFolder+" Folder selected successfully.");
    //Select the artifact.
    getJazzPageFactory().getRMArtifactPage().selectArtifactAndOpenContextMenu(artifactId);
    Reporter.logPass("Artifact ID '"+artifactId+"' selected successfully.");
    //Openen context menu of the selected artifact and select the Export Artifact option.
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId, "Export Artifact...");
    Reporter.logPass("Export Artifact... option selected successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //selecting the file format option.
    getJazzPageFactory().getRMArtifactPage().clickOnRadioButtonInExportDialog("XLSX");
    Reporter.logPass("XLSX file format selected successfully.");
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("OK");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Getting the downloaded file path.
    String filePath=getJazzPageFactory().getRMArtifactPage().getDownloadedFileNameAndPath();
    Reporter.logPass("Downloaded file path :"+filePath);
    //Getting the file extension.
    String[] actualFileExtension=filePath.split("[.]", 0);
    System.out.println("Actual File Extension:"+actualFileExtension[1]);
    System.out.println("Expected file extension:"+fileExtension);
    Assert.assertEquals(fileExtension, actualFileExtension[1]);
    Reporter.logPass("Verified: Expected and actual file extension '"+actualFileExtension[1]+"' are equal.");
    //Get Artifact ID from the downloaded XLSX file.
    String actualArtifactID= getJazzPageFactory().getRMArtifactPage().getDataFromCellOfXLSX(filePath, "0", "1", "0");
    System.out.println("Actual Artifact ID: "+actualArtifactID);
    System.out.println("Expected Artifact ID: "+artifactId);
    //Verifing the Artifact ID.
    Assert.assertEquals(artifactId,actualArtifactID);
    Reporter.logPass("Verified: Expected and actual Artifact ID '"+actualArtifactID+"' are equal.");
    // Get Artifact Name from the downloaded XLSX file.
    String actualArtifactName=getJazzPageFactory().getRMArtifactPage().getDataFromCellOfXLSX(filePath, "0", "1", "1");
    System.out.println("Actual ArtifactcName: "+actualArtifactName);
    System.out.println("Expected ArtifactcName: "+artifactName);
    //Verifing the Artifact Name.
    Assert.assertEquals(artifactName, actualArtifactName);
    Reporter.logPass("Verified: Expected and actual Artifact Name '"+actualArtifactName+"' are equal.");
    //Get Artifact Type from the downloaded XLSX file.
    String actualArtifactType=getJazzPageFactory().getRMArtifactPage().getDataFromCellOfXLSX(filePath, "0", "1", "2");
    System.out.println("Actual ArtifactcType:" +actualArtifactType);
    System.out.println("Expected Artifact Type: "+artifactType);
    //Verifing the Artifact Type.
    Assert.assertEquals(artifactType, actualArtifactType);
    Reporter.logPass("Verified: Expected and actual Artifact Type '"+actualArtifactType+"' are equal."); 
    
    
 
    
  }

}
