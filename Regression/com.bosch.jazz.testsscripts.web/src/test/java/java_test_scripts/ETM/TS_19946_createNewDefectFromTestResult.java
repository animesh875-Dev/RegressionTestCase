/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.ETM;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author NEE2KOR
 */
public class TS_19946_createNewDefectFromTestResult extends AbstractFrameworkTest {

  /**
   * The Test Cases should test the creation of Test Case Execution Records as well as the creation of Test Suite
   * Execution Records. With this we should also see if the selection of Iterations and Test Environments is working
   * fine.
   *
   * @throws IOException , on any exception occur
   * @throws InvalidKeyException , on any exception occur
   */
  @Test
  public void tcs_19946_createNewDefectFromTestResult() throws IOException, InvalidKeyException {

    String url = this.testDataRule.getConfigData("QM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String projectArea_CCM = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String testCaseResultName = this.testDataRule.getConfigData("TEST_CASE_RESULT_NAME"); // Test Result with verdict
    // "Blocked"
    String testCaseResultId = this.testDataRule.getConfigData("TEST_CASE_RESULT_ID");

    String gcName = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");

    String newDefectName = this.testDataRule.getConfigData("SUMMARY_OF_NEW_DEFECT");
    String newDefectFiledAgainst = this.testDataRule.getConfigData("FILED_AGAINST_OF_NEW_DEFECT");

    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    getJazzPageFactory().getRQMConstructionPage().selectProjectAreaAndGlobalConfiguration(projectArea, gcName,
        componentName, streamName);
    // Reporter.logPass(projectArea + ": project area opened successfully.");
    // getJazzPageFactory().getRQMConstructionPage().selectProjectArea(projectArea);

    // click on Execution tab
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(8);
    getJazzPageFactory().getRQMConstructionPage().openMainMenuByMenuTitle(RQMMainMenus.EXECUTION.toString());
    Reporter.logPass("Clicked on Execution tab");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);

    // click on browse test 'plans'
    getJazzPageFactory().getRQMConstructionPage().openSubMenuUnderSection("Browse", "Test Case Results");
    Reporter.logPass("clicked on Browse Test Case Results");

    // click on existing Test Case Result
    getJazzPageFactory().getRQMConstructionPage().searchRqmArtifactsInFilterTextBox(testCaseResultId);
    getJazzPageFactory().getRQMConstructionPage().selectRqmArtifact(testCaseResultId);
    Reporter.logPass("Browse To Test Case Result Successfully");
    // getJazzPageFactory().getRQMConstructionPage().waitForSecs("5");

    // Click on Defect tab
    getJazzPageFactory().getRQMConstructionPage().openRQMSection(RQMSectionMenus.DEFECTS.toString());
    Reporter.logPass(RQMSectionMenus.DEFECTS.toString() + " opened successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);

    String newDefectID = getJazzPageFactory().getRQMExecutionPage().createANewDefectFromTheTestResult(projectArea_CCM,
        newDefectName,
        newDefectFiledAgainst);
    System.out.println("Defect ID: '" + newDefectID + "'");

    // Click Save button
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);

    // Click on Yes to confirm saving the Test Case Result
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Yes");
    Reporter.logPass("Test Case Result is saved successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);

    // Click on Defect link
    // getJazzPageFactory().getRQMConstructionPage().clickOnJazzImageButtons("Refresh");
    // getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);
    // System.out.println(newDefectID + ": " + newDefectName);
    getJazzPageFactory().getCCMWorkItemEditorPage().openMainMenuByMenuTitle(newDefectID + ": " + newDefectName);
    Reporter.logPass("Clicked on the new defect added - " + newDefectName);

    // Click Link Tab
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab("Links");
    getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(newDefectID);
    Reporter.logPass("Open the Links Tab");

    // Remove defect link
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", testCaseResultName,
        "Affects Test Case Result");
    getJazzPageFactory().getRQMConstructionPage().openRQMSection(RQMSectionMenus.DEFECTS.toString());
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
    getJazzPageFactory().getRQMExecutionPage().removeLinkedDefect(newDefectID, newDefectName);
    Reporter.logPass(newDefectName + " is removed successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(5);

    // Click Save button
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Save");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(3);
    getJazzPageFactory().getRQMConstructionPage().clickOnJazzButtons("Yes");
    Reporter.logPass("Test Case Result is saved successfully");
    getJazzPageFactory().getRQMConstructionPage().waitForSecs(10);
  }
}
