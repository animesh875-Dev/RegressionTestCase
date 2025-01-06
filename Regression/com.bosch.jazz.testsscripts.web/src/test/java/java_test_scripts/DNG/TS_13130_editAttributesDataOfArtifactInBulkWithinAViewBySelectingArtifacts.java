/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author RUN2HC
 */
public class TS_13130_editAttributesDataOfArtifactInBulkWithinAViewBySelectingArtifacts extends AbstractFrameworkTest {

  /**
   * Test case is for edit attribute (artifact type) of a bulk artifact inside module
   * 
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void ts_13130_editAttributesDataOfArtifactInBulkWithinAViewBySelectingArtifacts() throws InvalidKeyException {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID");
    String viewName = this.testDataRule.getConfigData("VIEW_NAME");
    String artifactId1 = this.testDataRule.getConfigData("ARTIFACT_ID_1");
    String artifactId2 = this.testDataRule.getConfigData("ARTIFACT_ID_2");
    String artifactId3 = this.testDataRule.getConfigData("ARTIFACT_ID_3");
    String attributeName = this.testDataRule.getConfigData("ATTRIBUTE_NAME");
    String newAttributeValue = this.testDataRule.getConfigData("NEW_ATTRIBUTE_VALUE");
    String defaultAttributeValue = this.testDataRule.getConfigData("DEFAULT_ATTRIBUTE_VALUE");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search for the existing module id.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(moduleId);
    Reporter.logPass(moduleId + ": module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open searched artifact
    getJazzPageFactory().getRMDashBoardPage().openSearchedSpecification(moduleId);
    Reporter.logPass("Open '" + moduleId + "' module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Search view inside Module
    getJazzPageFactory().getRMArtifactPage().searchView(viewName);
    Reporter.logPass(viewName + ": search a view in module");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Select view inside Module
    getJazzPageFactory().getRMArtifactPage().selectView(viewName);
    Reporter.logPass(viewName + ": click on a view in module");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Select artifact 1 inside Module
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId1);
    Reporter.logPass(artifactId1 + ": select artifact");
    // Select artifact 2 inside Module
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId2);
    Reporter.logPass(artifactId2 + ": select artifact");
    // Select artifact 3 inside Module
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId3);
    Reporter.logPass(artifactId3 + ": select artifact");
    // Open the context menu and select "Edit the attributes for 3 Artifacts..."
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId1,
        "Edit the attributes for 3 Artifacts...");
    Reporter.logPass(artifactId1 + ": click on context menu and select menu to open Edit Attributes dialog");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Select new attribute value in Edit Attribites dialog
    getJazzPageFactory().getRMArtifactPage().editArtifactAttribute(attributeName, newAttributeValue);
    Reporter.logPass("Select " + attributeName + " and select new value is " + newAttributeValue);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Click on Save button
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Save");
    Reporter.logPass("Click on Save button on the Edit Attributes dialog to save change");
    // Click on Yes button
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Yes");
    Reporter.logPass("Click on Yes button on the confirm message dialog");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Verify new attribute type of Artifact 1
    boolean a1 = getJazzPageFactory().getRMArtifactPage().verifyArtifactValuesInModuleView(artifactId1, attributeName,
        newAttributeValue);
    Assert.assertTrue(a1);
    Reporter.logPass("Verify " + attributeName + " of " + artifactId1 + " is " + newAttributeValue);
    // Verify new attribute type of Artifact 2
    boolean a2 = getJazzPageFactory().getRMArtifactPage().verifyArtifactValuesInModuleView(artifactId2, attributeName,
        newAttributeValue);
    Assert.assertTrue(a2);
    Reporter.logPass("Verify " + attributeName + " of " + artifactId2 + " is " + newAttributeValue);
    // Verify new attribute type of Artifact 3
    boolean a3 = getJazzPageFactory().getRMArtifactPage().verifyArtifactValuesInModuleView(artifactId3, attributeName,
        newAttributeValue);
    Assert.assertTrue(a3);
    Reporter.logPass("Verify " + attributeName + " of " + artifactId3 + " is " + newAttributeValue);
    // Open artifact 1
    getJazzPageFactory().getRMArtifactPage().openRMSpecification(artifactId1);
    Reporter.logPass("Open " + artifactId1 + " artifact.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Verfiy artifact type of artifact 1
    String s = getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Type");
    Assert.assertEquals(s, newAttributeValue);
    Reporter.logPass("Verify type of " + artifactId1 + " should be " + newAttributeValue);
    // Search for the existing module id.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(moduleId);
    Reporter.logPass(moduleId + ": module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open searched artifact
    getJazzPageFactory().getRMDashBoardPage().openSearchedSpecification(moduleId);
    Reporter.logPass("Open '" + moduleId + "' module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Select artifact 1 inside Module
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId1);
    Reporter.logPass(artifactId1 + ": select artifact");
    // Select artifact 2 inside Module
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId2);
    Reporter.logPass(artifactId2 + ": select artifact");
    // Select artifact 3 inside Module
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId3);
    Reporter.logPass(artifactId3 + ": select artifact");
    // Open the context menu and select "Edit the attributes for 3 Artifacts..."
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId1,
        "Edit the attributes for 3 Artifacts...");
    Reporter.logPass(artifactId1 + ": click on context menu and select menu to open Edit Attributes dialog");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open the context menu and select "Edit the attributes for 3 Artifacts..."
    getJazzPageFactory().getRMArtifactPage().editArtifactAttribute(attributeName, defaultAttributeValue);
    Reporter.logPass("Select " + attributeName + " and select new value is " + defaultAttributeValue);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Click on Save button
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Save");
    Reporter.logPass("Click on Save button on the Edit Attributes dialog to save change");
    // Click on Yes button
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Yes");
    Reporter.logPass("Click on Yes button on the confirm message dialog");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
  }
}
