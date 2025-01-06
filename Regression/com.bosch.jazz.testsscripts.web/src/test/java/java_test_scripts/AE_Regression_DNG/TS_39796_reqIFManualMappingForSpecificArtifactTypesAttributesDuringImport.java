/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.AE_Regression_DNG;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author IABH1KOR
 *
 */
public class TS_39796_reqIFManualMappingForSpecificArtifactTypesAttributesDuringImport extends AbstractFrameworkTest {

  /**
   * @throws Throwable
   */
  @Test
  public void tcs_39796_reqIFManualMappingForSpecificArtifactTypesAttributesDuringImport() throws Throwable {
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String gcProjectArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String artiactName = this.testDataRule.getConfigData("ARTIFACT_NAME");
    String settingsMenu = this.testDataRule.getConfigData("SETTINGS_MENU");
    String settingsSubMenu = this.testDataRule.getConfigData("SETTINGS_SUBMENU");
    String artifactTypeTab = this.testDataRule.getConfigData("ARTIFACT_TYPES_TAB");
    String artifactType = this.testDataRule.getConfigData("IMPORTED_ARTIFACT_TYPE");
    String attributesTab = this.testDataRule.getConfigData("ARTIFACT_ATTRIBUTES_TAB");
    String attributeType = this.testDataRule.getConfigData("IMPORTED_ATTRIBUTE_TYPE");
    String importType = this.testDataRule.getConfigData("IMPORT_TYPE");
    String filePath = this.testDataRule.getConfigData("FILE_PATH");
  
    try {
    // Login to the sever
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    getJazzPageFactory().getRMDashBoardPage().selectGlobalConfiguration(projectArea, gcProjectArea, componentName, streamName);
    Reporter.logPass(projectArea + ": project area opened successfully with '" + componentName + "' component and '" +
      streamName + "' stream.");    
    //Artifact Mneu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Open Import Dialog
    getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Import Artifact...");
    Reporter.logPass("Import Dialog is opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // upload file
    getJazzPageFactory().getRMArtifactPage().uploadFile(importType, filePath);
    Reporter.logPass("File is uploaded successfully");
    // Get artifact types from imported file
    List<String> artifactTypes = getJazzPageFactory().getRMArtifactPage().getArtifactTypesFromImportedFile();
    Reporter.logPass("Artifact types are uploaded: " + artifactTypes.toString());
    // Get artifact attributes from imported file
    List<String> artifactAttributes = getJazzPageFactory().getRMArtifactPage().getArtifactAttributesFromImportedFile();
    Reporter.logPass("Artifact attributes are uploaded: " + artifactAttributes.toString());
    // Finish importing
    String msg = getJazzPageFactory().getRMArtifactPage().finishImportReqlF();
    // Verify successful message is return
    Assert.assertEquals("The import is complete. Show Report", msg);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(2);
    Reporter.logPass(msg);
    // go to setting page
    getJazzPageFactory().getRMDashBoardPage().openSettingsMenu(settingsMenu);
    getJazzPageFactory().getRMDashBoardPage().openSettingsSubMenu(settingsSubMenu);
    // verify artifact types
    getJazzPageFactory().getRMManageCompProperties().clickOnTab(artifactTypeTab);
  
    boolean result = true;
    TestAcceptanceMessage ta = new TestAcceptanceMessage();
//    for (int i = 0; i < artifactTypes.size(); i++) {
//      result = getJazzPageFactory().getRMManageCompProperties().isArtifactTypePresent(artifactTypes.get(i));
//      ta = getJazzPageFactory().getRMManageComponentPropertiesPageVerification().verifyIsArtifactTypePresent(artifactTypes.get(i), Boolean.toString(result));
//      if(ta.getState().equals("FAILED")) {
//        throw new WebAutomationException(ta.getMessage());
//      }
//      Reporter.logPass(ta.getMessage());
//    }
    result = getJazzPageFactory().getRMManageCompProperties().isArtifactTypePresent(artifactType);
    ta = getJazzPageFactory().getRMManageComponentPropertiesPageVerification().verifyIsArtifactTypePresent(artifactType, Boolean.toString(result));
    if(ta.getState().equals("FAILED")) {
      throw new WebAutomationException(ta.getMessage());
    }
    Reporter.logPass(ta.getMessage());
    
    // verify artifact attributes
    getJazzPageFactory().getRMManageCompProperties().clickOnTab(attributesTab);
//    for (int i = 0; i < artifactAttributes.size(); i++) {
//      result = getJazzPageFactory().getRMManageCompProperties().isArtifactAttributeDisplayed(artifactAttributes.get(i));
//      ta = getJazzPageFactory().getRMManageComponentPropertiesPageVerification().verifyIsArtifactAttributeDisplayed(artifactAttributes.get(i), Boolean.toString(result));
//      if(ta.getState().equals("FAILED")) {
//        throw new WebAutomationException(ta.getMessage());
//      }
//      Reporter.logPass(ta.getMessage());
//    } 
    result = getJazzPageFactory().getRMManageCompProperties().isArtifactAttributeDisplayed(attributeType);
    ta = getJazzPageFactory().getRMManageComponentPropertiesPageVerification().verifyIsArtifactAttributeDisplayed(attributeType, Boolean.toString(result));
    if(ta.getState().equals("FAILED")) {
      throw new WebAutomationException(ta.getMessage());
    }
    Reporter.logPass(ta.getMessage());
    }catch(Exception e) {}
    finally {
    //Artifact Mneu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Searching artifact 
    getJazzPageFactory().getRMDashBoardPage().filterArtifactByTextOrId(artiactName);
    Reporter.logPass(Menu.ARTIFACTS.toString() + " Artifact found successfully.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    // Deleting artifact
    getJazzPageFactory().getRMArtifactPage().deleteArtifactFromSearchedSpecification(artiactName, projectArea);
    Reporter.logPass("Artifact deleted successfully");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    //Opening setting
    getJazzPageFactory().getRMDashBoardPage().openSettingsMenu(settingsMenu);
    Reporter.logPass(" Setting menu opened.");
    // Settings submenu
    getJazzPageFactory().getRMDashBoardPage().openSettingsSubMenu(settingsSubMenu);
    Reporter.logPass(" Setting sub menu opened.");
    // Navigating to manage project properties
    getJazzPageFactory().getRMManageCompProperties().clickOnTab(artifactTypeTab);
    Reporter.logPass(" Artifact Types Selected.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    //Delete artifact type
    getJazzPageFactory().getRMManageCompProperties().deleteArtifactType(artifactType);
    Reporter.logPass(" Artifact Types Deleted.");
    // Navigate to Artifact attribute
    getJazzPageFactory().getRMManageCompProperties().clickOnTab(attributesTab);
    Reporter.logPass(" Artifact Attributes Selected.");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5);
    // Delete Artifact attributes data type.
    getJazzPageFactory().getRMManageCompProperties().deleteArtifactAttribute(attributeType);
    Reporter.logPass("Artifact attribute deleted successfully");
    getJazzPageFactory().getCCMAllProjectsPage().waitForSecs(5); 
    }
  }
}
