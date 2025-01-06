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
 * @author BBO1KOR
 *
 */
public class TS_17474_browseArtifactsUsingInvalidIdSpecialcharactersAndNegativeTest extends AbstractFrameworkTest {
  /**
   * Test case is for browsing Invalid Artifact Id,literal text,special character in quick search text box  
   * and Verifies it should not display any result for the negative search.
   * 
   * 
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void tcs_17474_browseArtifactsUsingInvalidIdSpecialcharactersAndNegativeTest() throws InvalidKeyException{
    
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    
    String artifactInvalidId=this.testDataRule.getConfigData("ARTIFACT_INVALID_ID");
    String artifactLiteralText=this.testDataRule.getConfigData("ARTIFACT_LITERAL_TEXT");
    String artifactSpecialChar=this.testDataRule.getConfigData("ARTIFACT_SPECIAL_CHARACTERS");
    String artifactWildcards=this.testDataRule.getConfigData("ARTIFACT_WILDCARDS");
    
    //Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    //Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    
    //Search for Invalid artifact id in quick search text box.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactInvalidId);
    Reporter.logPass(artifactInvalidId + " artifact not found");
    //Validate user should not get any result for the searched invalid artifact id.
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isSeachResultsNotFound());
    
    //Search for Literal text in quick search text box.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactLiteralText);
    Reporter.logPass(artifactLiteralText + " artifact not found");
    //Validate user should not get any result for the searched literal text.
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isSeachResultsNotFound());
    
    //Search for Special Char in quick search text box.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactSpecialChar);
    Reporter.logPass(artifactSpecialChar + " artifact not found");
    //Validate user should not get any result for the searched special character.
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isSeachResultsNotFound());
    
    //Search for Wildcards in quick search text box.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(artifactWildcards);
    Reporter.logPass(artifactWildcards + " artifact not found");
    //Validate user should not get any result for the searched wildcards.
     Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isSeachResultsNotFound());
  }

}
