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
 * @author AZP1KOR
 *
 */
public class TS_40545_linkTheArtifact extends AbstractFrameworkTest{
  /**
   * @throws InvalidKeyException
   */
  @SuppressWarnings("javadoc")
  @Test
  public void linkTheArtifact() throws InvalidKeyException {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String username = this.testDataRule.getConfigData("USERNAME");
    String password = this.testDataRule.getConfigData("PASSWORD");
    String artifactID=this.testDataRule.getConfigData("ARTIFACT_ID");
    String linkType=this.testDataRule.getConfigData("ARTIFACT_LINKTYPE");
    String duplicateArtifactName=this.testDataRule.getConfigData("DUPLICATE_ARTIFACT_NAME");
    
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
       getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
       Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
       getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactID);
       Reporter.logPass(artifactID + "Selected Successfully");
       getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactID, "Duplicate Artifact...");
       getJazzPageFactory().getRMArtifactPage().selectLinkTheArtifactCheckBox();
       getJazzPageFactory().getRMArtifactPage().selectOptionFromDropDown(linkType);
       Reporter.logPass(linkType + "Link type selected");
       getJazzPageFactory().getRMArtifactPage().clickOnButtons("OK");
       Reporter.logPass(duplicateArtifactName + "Created Successfully");
       getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
       getJazzPageFactory().getRMArtifactPage().deleteArtifactFromSearchedSpecification(duplicateArtifactName, "");
       Assert.assertFalse( getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(duplicateArtifactName));
       Reporter.logPass(duplicateArtifactName + "Deleted Successfully");
    
     
      // String fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(), "Screenshot_2", driver);

       //  String  resMessage= "Actual: <"+">\nExpected: <"+toBeProsCount+"> is contained in actual.";

         //  Reporter.logPassWithScreenshot("Verified the total count of requirement \"To be processed\" between the \"System development\" & \"System development details\" Dashboard.", resMessage, fileName, fileName);

       }
}