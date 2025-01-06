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
 * @author bbo1kor
 *
 */
public class TS_16322_browseArtifactsUsingIDandText extends AbstractFrameworkTest {
  /**
   * Test case is for browsing Artifact for Artifact Id, Artifact with text, Lowest Artifact Id,Highest Artifact Id
   * and Verifies all content (summary, project, created on, created by, type and status) of all searched IDs or name 
   * is matching with the previous stored values.
   * 
   * @throws InvalidKeyException if the property doesn't exist.
   * 
   */
  @Test
  public void tcs_16322_browseArtifactsUsingIDandText() throws InvalidKeyException{
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    
    String artifactId = this.testDataRule.getConfigData("ARTIFACT_ID");
    String artifactId_Summary=this.testDataRule.getConfigData("ARTIFACT_ID_SUMMARY");
    String artifactId_Project=this.testDataRule.getConfigData("ARTIFACT_ID_PROJECT");
    String artifactId_CreatedOn=this.testDataRule.getConfigData("ARTIFACT_ID_CREATED_ON");
    String artifactId_CreatedBy=this.testDataRule.getConfigData("ARTIFACT_ID_CREATED_BY");
    String artifactId_Type=this.testDataRule.getConfigData("ARTIFACT_ID_TYPE");
    String artifactId_Status=this.testDataRule.getConfigData("ARTIFACT_ID_STATUS");
    
    String artifactName=this.testDataRule.getConfigData("ARTIFACT_NAME");
    String artifactName_Summary=this.testDataRule.getConfigData("ARTIFACT_NAME_SUMMARY");
    String artifactName_Project=this.testDataRule.getConfigData("ARTIFACT_NAME_PROJECT");
    String artifactName_CreatedOn=this.testDataRule.getConfigData("ARTIFACT_NAME_CREATED_ON");
    String artifactName_CreatedBy=this.testDataRule.getConfigData("ARTIFACT_NAME_CREATED_BY");
    String artifactName_Type=this.testDataRule.getConfigData("ARTIFACT_NAME_TYPE");
    String artifactName_Status=this.testDataRule.getConfigData("ARTIFACT_NAME_STATUS");
    
    String artifactId_lowest=this.testDataRule.getConfigData("ARTIFACT_ID_LOWEST");
    String artifactIdLowest_Summary=this.testDataRule.getConfigData("ARTIFACT_ID_LOWEST_SUMMARY");
    String artifactIdLowest_Project=this.testDataRule.getConfigData("ARTIFACT_ID_LOWEST_PROJECT");
    String artifactIdLowest_CreatedOn=this.testDataRule.getConfigData("ARTIFACT_ID_LOWEST_CREATED_ON");
    String artifactIdLowest_CreatedBy=this.testDataRule.getConfigData("ARTIFACT_ID_LOWEST_CREATED_BY");
    String artifactIdLowest_Type=this.testDataRule.getConfigData("ARTIFACT_ID_LOWEST_TYPE");
    String artifactIdLowest_Status=this.testDataRule.getConfigData("ARTIFACT_ID_LOWEST_STATUS");
    
    String artifactId_highest=this.testDataRule.getConfigData("ARTIFACT_ID_HIGHEST");
    String artifactIdHighest_Summary=this.testDataRule.getConfigData("ARTIFACT_ID_HIGHEST_SUMMARY");
    String artifactIdHighest_Project=this.testDataRule.getConfigData("ARTIFACT_ID_HIGHEST_PROJECT");
    String artifactIdHighest_CreatedOn=this.testDataRule.getConfigData("ARTIFACT_ID_HIGHEST_CREATED_ON");
    String artifactIdHighest_CreatedBy=this.testDataRule.getConfigData("ARTIFACT_ID_HIGHEST_CREATED_BY");
    String artifactIdHighest_Type=this.testDataRule.getConfigData("ARTIFACT_ID_HIGHEST_TYPE");
    String artifactIdHighest_Status=this.testDataRule.getConfigData("ARTIFACT_ID_HIGHEST_STATUS");
    
    
   //Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
   //Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    //Search for the existing artifact id.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactId);
    Reporter.logPass(artifactId + ": artifact found in the project area");
    //Validate Summary of searched artifact id is matching with the previous stored result.
    String artifactIdSummary=getJazzPageFactory().getRMArtifactPage().getRMSpecificationsSummary();
    Assert.assertEquals(artifactId_Summary, artifactIdSummary);
    //Validate Project of searched artifact id is matching with the previous stored result.
    String artifactIdProject= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Project");
    Assert.assertEquals(artifactId_Project,artifactIdProject);
    //Validate Created On of searched artifact id is matching with the previous stored result.
    String artifactIdCreatedOn= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created On");
    Assert.assertEquals(artifactId_CreatedOn,artifactIdCreatedOn);
    //Validate Created By of searched artifact id is matching with the previous stored result.
    String artifactIdCreatedBy= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created By");
    Assert.assertEquals(artifactId_CreatedBy,artifactIdCreatedBy);
    //Validate Type of searched artifact id is matching with the previous stored result.
    String artifactIdType= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Type");
    Assert.assertEquals(artifactId_Type,artifactIdType);
    //Validate Status of searched artifact id is matching with the previous stored result.
    String artifactIdStatus= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Status");
    Assert.assertEquals(artifactId_Status,artifactIdStatus);
    
    //Search for the existing artifact with text.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactName);
    Reporter.logPass(artifactName + ": artifact found in the project area");
    //Validate Summary of searched artifact name is matching with the previous stored result.
    String artifactNameSummary=getJazzPageFactory().getRMArtifactPage().getRMSpecificationsSummary();
    Assert.assertEquals(artifactName_Summary, artifactNameSummary);
    //Validate Project of searched artifact name is matching with the previous stored result.
    String artifactNameProject= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Project");
    Assert.assertEquals(artifactName_Project,artifactNameProject);
    //Validate Created On of searched artifact name is matching with the previous stored result.
    String artifactNameCreatedOn= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created On");
    Assert.assertEquals(artifactName_CreatedOn,artifactNameCreatedOn);
    //Validate Created By of searched artifact name is matching with the previous stored result.
    String artifactNameCreatedBy= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created By");
    Assert.assertEquals(artifactName_CreatedBy,artifactNameCreatedBy);
    //Validate Type of searched artifact name is matching with the previous stored result.
    String artifactNameType= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Type");
    Assert.assertEquals(artifactName_Type,artifactNameType);
    //Validate Status of searched artifact name is matching with the previous stored result.
    String artifactNameStatus= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Status");
    Assert.assertEquals(artifactName_Status,artifactNameStatus);
    
    //Search for the existing artifact with lowest artifact id.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactId_lowest);
    Reporter.logPass(artifactId_lowest + ": artifact found in the project area");
    //Validate Summary of searched lowest artifact id is matching with the previous stored result.
    String artifactIdLowestSummary=getJazzPageFactory().getRMArtifactPage().getRMSpecificationsSummary();
    Assert.assertEquals(artifactIdLowest_Summary, artifactIdLowestSummary);
    //Validate Project of searched lowest artifact id is matching with the previous stored result.
    String artifactIdLowestProject= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Project");
    Assert.assertEquals(artifactIdLowest_Project,artifactIdLowestProject);
    //Validate Created On of searched lowest artifact id is matching with the previous stored result.
    String artifactIdLowestCreatedOn= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created On");
    Assert.assertEquals(artifactIdLowest_CreatedOn,artifactIdLowestCreatedOn);
    //Validate Created By of searched lowest artifact id is matching with the previous stored result.
    String artifactIdLowestCreatedBy= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created By");
    Assert.assertEquals(artifactIdLowest_CreatedBy,artifactIdLowestCreatedBy);
    //Validate Type of searched lowest artifact id is matching with the previous stored result.
    String artifactIdLowestType= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Type");
    Assert.assertEquals(artifactIdLowest_Type,artifactIdLowestType);
    //Validate Status of searched lowest artifact id is matching with the previous stored result.
    String artifactIdLowestStatus= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Status");
    Assert.assertEquals(artifactIdLowest_Status,artifactIdLowestStatus);
    
    //Search for the existing artifact with highest artifact id.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactId_highest);
    Reporter.logPass(artifactId_highest + ": artifact found in the project area");
    //Validate Summary of searched highest artifact id is matching with the previous stored result.
    String artifactIdHighestSummary=getJazzPageFactory().getRMArtifactPage().getRMSpecificationsSummary();
    Assert.assertEquals(artifactIdHighest_Summary, artifactIdHighestSummary);
    //Validate Project of searched highest artifact id is matching with the previous stored result.
    String artifactIdHighestProject= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Project");
    Assert.assertEquals(artifactIdHighest_Project,artifactIdHighestProject);
    //Validate Created On of searched highest artifact id is matching with the previous stored result.
    String artifactIdHighestCreatedOn= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created On");
    Assert.assertEquals(artifactIdHighest_CreatedOn,artifactIdHighestCreatedOn);
    //Validate Created By of searched highest artifact id is matching with the previous stored result.
    String artifactIdHighestCreatedBy= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Created By");
    Assert.assertEquals(artifactIdHighest_CreatedBy,artifactIdHighestCreatedBy);
    //Validate Type of searched highest artifact id is matching with the previous stored result.
    String artifactIdHighestType= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Type");
    Assert.assertEquals(artifactIdHighest_Type,artifactIdHighestType);
    //Validate Status of searched highest artifact id is matching with the previous stored result.
    String artifactIdHighestStatus= getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("Status");
    Assert.assertEquals(artifactIdHighest_Status,artifactIdHighestStatus);
  }
  

}
