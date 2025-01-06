/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_19725_CreateAndAssociateTestScriptFromTestScriptSectionOfTestCase extends AbstractFrameworkTest {

  /**
   * This test case Browse KeyWords,search for the test script inside Keywords. Create new manual step in the test
   * script, delete the added step and validate new step is added and also deleted from the test script.
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_19725_CreateAndAssociateTestScriptFromTestScriptSectionOfTestCase() throws Throwable {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String testCaseName = this.testDataRule.getConfigData("TEST_CASE_NAME");
    String testScriptName = this.testDataRule.getConfigData("TEST_SCRIPT_NAME") + DateUtil.getCurrentDateAndTime();
    String description = this.testDataRule.getConfigData("DESCRIPTION");
    String descrContent = this.testDataRule.getConfigData("DESCRIPTION_CONTENT");

    // Log in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
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
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Reporter.logPass("Open test case.");
    // Select test script to add to the test case.
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle("Test Scripts");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Create Test Script");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Add step value in the test script.
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("varName", testScriptName);
    additionalParams.put("varDescription", description);
    additionalParams.put("varDescriptionContent", descrContent);
    getJazzPageFactory().getRQMConstructionPage().createNewTestScriptInDialog(additionalParams);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    Reporter.logPass("Test script is saved successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Refresh");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(testScriptName);
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().refresh();
    String testScriptID = getJazzPageFactory().getRQMConstructionPage().getRqmArtifactID();
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Assert.assertTrue(getJazzPageFactory().getRQMConstructionPage().isParentTestCases(testCaseName));

    // Test Script should not be created with same name
    getJazzPageFactory().getRQMConstructionPage().navigateBack();
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().refresh();
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle("Test Scripts");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Create Test Script");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().createNewTestScriptInDialog(additionalParams);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    Reporter.logPass("Error Message is shown.");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    Assert.assertTrue(getJazzPageFactory().getRQMConstructionPage().isErrorMessageDisplay());
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Cancel");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // Select Yes button in Confirmation dialog
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Yes");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);

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
  }

}
