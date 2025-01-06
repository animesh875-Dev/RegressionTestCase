/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author SET2BAN
 *
 */
public class TS_40503_createDuplicateArtifacts extends AbstractFrameworkTest{
  /**
   * This test case for Creating duplicate artifacts.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_40503_createDuplicateArtifacts() throws Throwable{
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    //String artifactName = "Duplicate Artifact_"+ DateUtil.getCurrentDateAndTime();
    String duplicateartifactname = this.testDataRule.getConfigData("ARTIFACT_NAME");   
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Create Artifact.
    getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Other...");  
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);   
 // Passing the parameters through Map.
    Map<String, String> Parameters = new LinkedHashMap();
    Parameters.put("INITIAL_CONTENT",duplicateartifactname);
    Parameters.put("ARTIFACT_NAME",duplicateartifactname);
    Parameters.put("ARTIFACT_TYPE","Heading");
    Parameters.put("ARTIFACT_FORMAT","Text");
    Parameters.put("OPEN_ARTIFACT","Open Artifact");
    getJazzPageFactory().getRMArtifactPage().createAndOpenAnArtifact(Parameters);
    Reporter.logInfo("'"+duplicateartifactname+"' created successfully with a Name and initial content"); 
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Create Artifact.
    getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Other...");   
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);   
 // Passing the parameters through Map.
    Map<String, String> Parameters1 = new LinkedHashMap();
    Parameters1.put("INITIAL_CONTENT",duplicateartifactname);
    Parameters1.put("ARTIFACT_NAME",duplicateartifactname);
    Parameters1.put("ARTIFACT_TYPE","Heading");
    Parameters1.put("ARTIFACT_FORMAT","Text");
    Parameters1.put("OPEN_ARTIFACT","Open Artifact");
    getJazzPageFactory().getRMArtifactPage().createAndOpenAnArtifact(Parameters1);
    Reporter.logInfo("Duplicate Artifact '"+duplicateartifactname+"' created successfully with a Duplicate Name and a Duplicate initial content");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
  }
}
