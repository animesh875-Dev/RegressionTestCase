/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_19715_addANewTestScriptToATestCase extends AbstractFrameworkTest {

  /**
   * This test case Browse KeyWords,search for the test script inside Keywords. Create new manual step in the test
   * script, delete the added step and validate new step is added and also deleted from the test script.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_19715_addANewTestScriptToATestCase() throws Throwable {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String testCaseName = this.testDataRule.getConfigData("TEST_CASE_NAME");
    String testScriptName = this.testDataRule.getConfigData("TEST_SCRIPT_NAME");
    String description = this.testDataRule.getConfigData("DESCRIPTION");
    String ownerID = this.testDataRule.getConfigData("OWNER_USERID");
    String ownerDisplayName = this.testDataRule.getConfigData("OWNER_USERNAME");
    String descrContent = this.testDataRule.getConfigData("DESCRIPTION_CONTENT");
    String exrContent = this.testDataRule.getConfigData("EXPECTED_RESULT_CONTENT");
    String invalidID = this.testDataRule.getConfigData("INVALID_TEST_SCRIPT_ID");

    // Log in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Click on CONSTRUCTION tab
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.CONSTRUCTION.toString());
    Reporter.logPass("Clicked on construction tab");
    // Click on create Test Scripts.
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Create", "Test Script");
    Reporter.logPass("Clicked on create Test Script");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Add step value in the test script.
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Place holder name", "Enter New Test Script Name");
    additionalParams.put("testArtifactTitleValue", testScriptName);
    additionalParams.put("testArtifactDescriptionValue", description);
    // Input Summary of test script.
    testScriptName = getJazzPageFactory().getRQMConstructionPage().enterTestArtifactName(additionalParams);
    Reporter.logPass("Input Summary of test script");
    // Input Description of test script.
    getJazzPageFactory().getRQMConstructionPage().fillDescriptionInSummaryOuterNodeSection(additionalParams);
    Reporter.logPass("Input Description of test script");
    // Select Owner of test script.
    additionalParams.put("moreLinkValue", "More");
    additionalParams.put("userIdValue", ownerID);
    additionalParams.put("ownerValue", ownerDisplayName);
    getJazzPageFactory().getRQMConstructionPage().selectOwner(additionalParams);
    Reporter.logPass("Select Owner of test script");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Stack Left to Right
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Stack Left to Right");
    Reporter.logPass("Stack Left to Right");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Add Description and Expected Result.
    additionalParams.put("description", descrContent);
    additionalParams.put("descValue", "Step  1");
    additionalParams.put("expecResultValue", "Step  1 Expected Results");
    additionalParams.put("expectedResult", exrContent);
    getJazzPageFactory().getRQMConstructionPage().addStepValuesInNewTestScript(additionalParams);
    Reporter.logPass("Description and Expected Result added in the test script");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Click on Save button.
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    Reporter.logPass("Test script is saved successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    String testScriptID = getJazzPageFactory().getRQMConstructionPage().getRqmArtifactID();
    // Validate 'Description' is added in the test script.
    Assert.assertTrue(
        descrContent.contains(getJazzPageFactory().getRQMConstructionPage().getContentOfStep("1", "Description")));
    // Validate 'Expected Result' is added in the test script.
    Assert.assertTrue(
        exrContent.contains(getJazzPageFactory().getRQMConstructionPage().getContentOfStep("1", "Expected Results")));

    // Checks duplicate Test Script should not be created with same name
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Duplicate");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(2);
    // Click Finish button on Dialog.
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Finish");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Reporter.logPass("Click Finish button on Dialog.");
    // Refresh the page to rename test script copied.
    getJazzPageFactory().getRQMConstructionPage().refresh();
    String testScriptCopiedID = getJazzPageFactory().getRQMConstructionPage().getRqmArtifactID();
    Reporter.logPass("Refresh the page to rename test script copied.");
    // Rename test script with same old one.
    additionalParams.put("testArtifactTitleValue", testScriptName);
    getJazzPageFactory().getRQMConstructionPage().renameTestArtifactName(additionalParams);
    Reporter.logPass("Rename test script with same old one.");
    // Click on Save button.
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Reporter.logPass("Error Message is shown.");
    Assert.assertTrue(getJazzPageFactory().getRQMConstructionPage().isErrorMessageDisplay());

    // Checks Test Script is linked to particular Test Case or not
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.CONSTRUCTION.toString());
    Reporter.logPass("Clicked on construction tab");
    // Click on browse Test Cases.
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Cases");
    Reporter.logPass("Clicked on Browse Test Cases");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().searchRqmArtifactsInFilterTextBox(testCaseName);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(2);
    // Open test case.
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(testCaseName);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(2);
    Reporter.logPass("Open test case.");
    // Select test script to add to the test case.
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle("Test Scripts");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Add Test Scripts");
    getJazzPageFactory().getRQMConstructionPage().setIdOrNameToAddRQMArtifact("Add Test Scripts", testScriptID);
    getJazzPageFactory().getRQMConstructionPage().clickOnDialogToolTipButton("Add Test Scripts", "Apply all filters");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectTestScript(testScriptName);
    Reporter.logPass("Select test script to add to the test case.");
    getJazzPageFactory().getRQMConstructionPage().clickOnDialogButton("Add Test Scripts", "Add and Close");
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Reporter.logPass("Test script is saved successfully");
    Assert.assertTrue(getJazzPageFactory().getRQMConstructionPage().isAssociateArtifactDisplayed(testScriptName));

    // Checks able to add same Test Script with particular Test Case multiple times.
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Add Test Scripts");
    getJazzPageFactory().getRQMConstructionPage().setIdOrNameToAddRQMArtifact("Add Test Scripts", testScriptID);
    getJazzPageFactory().getRQMConstructionPage().clickOnDialogToolTipButton("Add Test Scripts", "Apply all filters");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Reporter.logPass("Add same test script into the test case.");
    Assert.assertTrue(getJazzPageFactory().getRQMConstructionPage().isNoFoundResultAfterSearchingInDialog());
    getJazzPageFactory().getRQMConstructionPage().clickOnDialogButton("Add Test Scripts", "Close");

    // Checks searching Test Script using invalid ID shows empty result or not
    additionalParams.put("invalidID", invalidID);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Add Test Scripts");
    getJazzPageFactory().getRQMConstructionPage().setIdOrNameToAddRQMArtifact("Add Test Scripts", invalidID);
    getJazzPageFactory().getRQMConstructionPage().clickOnDialogToolTipButton("Add Test Scripts", "Apply all filters");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Reporter.logPass("Add invalid test script ID into the test case.");
    Assert.assertTrue(getJazzPageFactory().getRQMConstructionPage().isNoFoundResultAfterSearchingInDialog());
    getJazzPageFactory().getRQMConstructionPage().clickOnDialogButton("Add Test Scripts", "Close");
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Refresh");
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(testScriptName);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Assert.assertTrue(getJazzPageFactory().getRQMConstructionPage().isParentTestCases(testCaseName));

    // Remove the test script and copied test script.
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.CONSTRUCTION.toString());
    Reporter.logPass("Clicked on construction tab");
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Scripts");
    Reporter.logPass("Clicked on Browse Test Scripts");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().filterAndDeleteRqmArtifact(testScriptID, "Delete Test Script",
        "Confirmation");
    Reporter.logPass("Remove the test script.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(2);
    getJazzPageFactory().getRQMConstructionPage().filterAndDeleteRqmArtifact(testScriptCopiedID, "Delete Test Script",
        "Confirmation");
    Reporter.logPass("Remove the copied test script.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(2);
  }

}
