/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author MOW2KOR
 *
 */
public class TS_40505_addingAttributeToTheSpecificArtifactType extends AbstractFrameworkTest{

  /**
   * This test case is created to test editing artifact type of an artifact.
   * @throws InvalidKeyException 
   */
  @SuppressWarnings("javadoc")
  @Test
  public void tcs_40505_addingAttributeToTheSpecificArtifactType() throws InvalidKeyException {
    String url=this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea=this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactId=this.testDataRule.getConfigData("ARTIFACT_ID");
    String artifactType=this.testDataRule.getConfigData("ARTIFACT_TYPE");
    String updatedArtifactType=this.testDataRule.getConfigData("UPDATED_ARTIFACT_TYPE");
    
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
    //Search for Artifact ID.  
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactId);
    Reporter.logPass(artifactId +" selected successfully.");
    //Select the artifact and open the context menu.
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact (artifactId, "Edit Attributes...");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(artifactId + " selected and context menu opened successfully.");
    //Modifying Artifact Type to Specific ArtifactType.
    getJazzPageFactory().getRMArtifactPage().editArtifactAttribute(artifactType, updatedArtifactType);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(artifactId + " of artifact type " +artifactType+ " has changed to " +updatedArtifactType);
    //Click on Save button.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Save");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(artifactId + " Artifact saved successfully.");
    //Click on confirmation buttion.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Yes");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass("Confirmation message 'Yes' clicked.");
    //Open Artifact.
    getJazzPageFactory().getRMArtifactPage().clickOnArtifact(artifactId);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass("Artifact opened successfully.");
    //Verifying the Artifact Type.
    String actualArtifactType= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Type");
    Assert.assertEquals(updatedArtifactType, actualArtifactType);
    Reporter.logPass("Verified: Expected and actual Artifact Type '"+actualArtifactType+"' are equal.");
    //Verifying the Artifact type is not editable after saving the artifact.
    WebElement readonlyType= driver.findElement(By.xpath("//label[text()='Type:']/span"));
    boolean readonly=readonlyType.isDisplayed();
    Assert.assertTrue(readonly);
    Reporter.logPass("Artifact Type is not editable after saving the artifact: "+readonly);  
    getJazzPageFactory().getRMArtifactPage().navigateBack();
  
    //Reverting the artifact type to default type.
    //getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId);
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact (artifactId, "Edit Attributes...");
    Reporter.logPass(artifactId + " selected and context menu opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    //Changing the Artifact Type to default ArtifactType.
    getJazzPageFactory().getRMArtifactPage().editArtifactAttribute(updatedArtifactType, artifactType);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(artifactId + " of artifact Type reverted back to default artifact Type.");
    //Click on Save button.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Save");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(artifactId + " Artifact saved successfully."); 
    //Click on confirmation buttion.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Yes");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass("Confirmation message 'Yes' clicked.");
    

  }

}
