/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.security.InvalidKeyException;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactTypes;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_17473_browseTheArtifactsInsideTheCollections extends AbstractFrameworkTest {

  /**
   * Test case is for browsing the Artifact inside a collection in 'Type to filter artifacts by text or by ID' search
   * box of Artifact page and Verifies all content (summary, project, created on, created by, type and status) of all
   * searched IDs or text is matching with the previous stored values.
   *
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void tcs_17473_browseTheArtifactsInsideTheCollections() throws InvalidKeyException {

    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String collectionId = this.testDataRule.getConfigData("COLLECTION_ID");
    String artifactId = this.testDataRule.getConfigData("ARTIFACT_ID");

    String artifactId_Summary = this.testDataRule.getConfigData("ARTIFACT_ID_SUMMARY");
    String artifactId_Project = this.testDataRule.getConfigData("ARTIFACT_ID_PROJECT");
    String artifactId_CreatedOn = this.testDataRule.getConfigData("ARTIFACT_ID_CREATED_ON");
    String artifactId_CreatedBy = this.testDataRule.getConfigData("ARTIFACT_ID_CREATED_BY");
    String artifactId_Type = this.testDataRule.getConfigData("ARTIFACT_ID_TYPE");
    String artifactId_Status = this.testDataRule.getConfigData("ARTIFACT_ID_STATUS");
    // Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Open Artifacts Menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Click on Modules hyper link.
    getJazzPageFactory().getRMArtifactPage().clickOnArtifactTypes(ArtifactTypes.COLLECTIONS.toString());
    Reporter.logPass(ArtifactTypes.COLLECTIONS.toString() + " hyper link of Artifacts opened successfully.");

    // Search for existing module id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(collectionId);
    Reporter.logPass(collectionId + " collection found in the project area");
    // Open the module searched in 'Type to filter artifacts by text or by ID' search box.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    getJazzPageFactory().getRMArtifactPage().openRMSpecification(collectionId);
    Reporter.logPass(collectionId + " collection opened in the project area");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Search for existing artifact id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactId);
    Reporter.logPass(artifactId + " artifact found in the project area");
    // Open the artifact searched in 'Type to filter artifacts by text or by ID' search box.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    getJazzPageFactory().getRMArtifactPage().openRMSpecification(artifactId);
    Reporter.logPass(artifactId + " artifact opened in the project area");

    // Validate Summary of searched artifact id is matching with the previous stored result.
    String artifactIdSummary = getJazzPageFactory().getRMArtifactPage().getRMSpecificationsSummary();
    Assert.assertEquals(artifactId_Summary, artifactIdSummary);
    // Validate Project of searched artifact id is matching with the previous stored result.
    String artifactIdProject = getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Project");
    Assert.assertEquals(artifactId_Project, artifactIdProject);
    // Validate Created On of searched artifact id is matching with the previous stored result.
    String artifactIdCreatedOn = getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created On");
    Assert.assertEquals(artifactId_CreatedOn, artifactIdCreatedOn);
    // Validate Created By of searched artifact id is matching with the previous stored result.
    String artifactIdCreatedBy = getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created By");
    Assert.assertEquals(artifactId_CreatedBy, artifactIdCreatedBy);
    // Validate Type of searched artifact id is matching with the previous stored result.
    String artifactIdType = getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Type");
    Assert.assertEquals(artifactId_Type, artifactIdType);
    // Validate Status of searched artifact id is matching with the previous stored result.
    String artifactIdStatus = getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Status");
    Assert.assertEquals(artifactId_Status, artifactIdStatus);
  }

}
