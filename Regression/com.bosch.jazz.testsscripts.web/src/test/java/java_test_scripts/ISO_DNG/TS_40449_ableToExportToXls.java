/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author AZP1KOR
 *
 */
public class TS_40449_ableToExportToXls extends AbstractFrameworkTest {
  /**
   * @throws InvalidKeyException
   * @throws IOException 
   */
  @SuppressWarnings("javadoc")
  @Test
  public void linkTheArtifact() throws InvalidKeyException, IOException {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String username = this.testDataRule.getConfigData("USERNAME");
    String password = this.testDataRule.getConfigData("PASSWORD");
    String artifactID=this.testDataRule.getConfigData("ARTIFACT_ID");
   
    
      Reporter.logInfo("*********Set Up***********");
      
   // Login to alm application with valid user name and password.
      getJazzPageFactory().getLoginPage().loginWithGivenPassword(username, password, url);
      Reporter.logPass(username + " user logged in to the " + url + " repository successfully.");
      // Select the project area.
      getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
      getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
      Reporter.logPass(projectArea + ": project area opened successfully.");
     
       getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
       Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
       getJazzPageFactory().getRMArtifactPage().clickOnFolder("ISO-CandQ (RM)");
       getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
       Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
       getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactID);
       Reporter.logPass(artifactID + " Selected Successfully");
       getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Export Artifact...");
       getJazzPageFactory().getRMArtifactPage().clickOnRadioButtonInExportDialog("XLS");
       getJazzPageFactory().getRMArtifactPage().clickOnButtons("OK");
       getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
       
       String path=getJazzPageFactory().getRMArtifactPage().getDownloadedFileNameAndPath();
       System.out.println(path);
       getJazzPageFactory().getRMArtifactPage().getDataFromCellOfXLS(path, "0");
       Reporter.logInfo("verified attribute value of artifact displayed in XLS file");
  }
}