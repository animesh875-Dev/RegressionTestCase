/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ISO_DNG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NCY3HC
 *
 */
public class TS_40511_ableToChangeTheStateOfAnArtifact extends AbstractFrameworkTest{
  /**
   * This test case for moving artifcat to another folder.
   *
   * @throws Throwable is use to handle any kind of exception. 
   */
  @Test
  public void tc_40511_ableToChangeTheStateOfAnArtifact() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String artifactName = this.testDataRule.getConfigData("ARTIFACT_NAME")+ DateUtil.getCurrentDateAndTime();
    String rootFolder = this.testDataRule.getConfigData("ROOT_FOLDER");
    String stateValue = this.testDataRule.getConfigData("STATE_VALUE");
    String actualStatus = this.testDataRule.getConfigData("ACTUAL_STATUS");
    String message = this.testDataRule.getConfigData("ERROR_MESSAGE");
    
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
 
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(4);
    //Select root folder where artifact is inside.
    getJazzPageFactory().getRMArtifactPage().clickOnFolder(rootFolder);
    Reporter.logPass(rootFolder.toString() + " is selected successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(4);
    // Click on 'Create' button
    getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Other...");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass("Open 'Create Artifact' successfully.");
    //Create new artifact inside folder
    Map<String, String> Parameters = new LinkedHashMap();
    Parameters.put("INITIAL_CONTENT","");
    Parameters.put("ARTIFACT_NAME",artifactName);
    Parameters.put("ARTIFACT_TYPE","Heading");
    Parameters.put("ARTIFACT_FORMAT","Text");
    Parameters.put("OPEN_ARTIFACT","true");
    getJazzPageFactory().getRMArtifactPage().createAndOpenAnArtifact(Parameters);
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Done");
    Reporter.logPass("Create a new artifact  successfully.");
    //Click an artifact and select "Edit" on the right side of the webpage.
    //In the right side tab of the webpage under "Overview" select the "State" field and change the state to "Start Working"
    Map<String, String> Parameters2 = new LinkedHashMap();
    Parameters2.put("ATTRIBUTE_TYPE","Combo box");
    Parameters2.put("ATTRIBUTE_LABEL","Type");
    Parameters2.put("ATTRIBUTE_VALUE",stateValue);
    Parameters2.put("SAVE_OR_DONE_BUTTON","Save");
    getJazzPageFactory().getRMArtifactPage().editRMAttributesUnderOverviewSection(Parameters2);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(2);
    
    //Verify that 'Save' button is disable
    assertFalse(    
      getJazzPageFactory().getRMArtifactPage().isButtonEnabled("Save"));
    getJazzPageFactory().getRMArtifactPage().waitForSecs(2);
    Reporter.logPass("Save button is disable");
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Done");
    //Verify the state of the artifact is changed to "In Progress".
    getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("State (Default)");
    System.out.println(getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("State (Default)"));
    assertEquals(getJazzPageFactory().getRMArtifactPage().getRMAttributeValue("State (Default)"), actualStatus);
    //Verify that No warning message is displayed near the field State as "No actions are available. User might not have permission to perform this action"
    assertFalse(    
        getJazzPageFactory().getRMArtifactPage().verifyEditAttributeSuccessMessage(message));
      getJazzPageFactory().getRMArtifactPage().waitForSecs(2);
    
    
    
    //Delete Artifact
    getJazzPageFactory().getRMArtifactPage().selectMenuItemFromMoreActions("Delete Artifact");
    getJazzPageFactory().getRMArtifactPage().clickOnDialogButton("Confirm Deletion", "Delete Artifact");
    
  }

}
