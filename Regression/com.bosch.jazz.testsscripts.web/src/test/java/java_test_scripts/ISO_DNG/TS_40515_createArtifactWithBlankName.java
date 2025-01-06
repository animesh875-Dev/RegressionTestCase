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
public class TS_40515_createArtifactWithBlankName extends AbstractFrameworkTest{
  /**
   * This test case for Create artifact without Name and Initial content.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_40515_createArtifactWithBlankName() throws Throwable{
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    
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
    Parameters.put("INITIAL_CONTENT","");
    Parameters.put("ARTIFACT_NAME","");
    Parameters.put("ARTIFACT_TYPE","Heading");
    Parameters.put("ARTIFACT_FORMAT","Text");
    Parameters.put("OPEN_ARTIFACT","Open Artifact");
    getJazzPageFactory().getRMArtifactPage().createAndOpenAnArtifact(Parameters);
    Reporter.logInfo("Artifact created successfully without a Name and initial content");
  }
}
