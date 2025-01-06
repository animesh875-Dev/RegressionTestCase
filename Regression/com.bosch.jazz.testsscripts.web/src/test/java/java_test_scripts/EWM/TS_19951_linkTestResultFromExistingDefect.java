/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author VDY1HC
 */
public class TS_19951_linkTestResultFromExistingDefect extends AbstractFrameworkTest {

  /**
   *
   */
  @SuppressWarnings("javadoc")
  @Test
  public void tcs_19951_linkTestResultFromExistingDefect() throws Throwable {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String rqmProjectArea = this.testDataRule.getConfigData("QM_PROJECT_AREA");
    String TEST_CASE_RESULT_ID = this.testDataRule.getConfigData("TEST_CASE_RESULT_ID");
    String TEST_CASE_RESULT_NAME = this.testDataRule.getConfigData("TEST_CASE_RESULT_NAME");
    String EXISTING_DEFECT_ID = this.testDataRule.getConfigData("EXISTING_DEFECT_ID");
    String EXISTING_DEFECT_NAME = this.testDataRule.getConfigData("EXISTING_DEFECT_NAME");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linkActions", "Add Affects Test Case Result");
    additionalParams.put("linksSection", "Links");
    additionalParams.put("dropdownText", "Add Related");
    additionalParams.put("rqmProjectArea", rqmProjectArea);
    additionalParams.put("linkTypeID", TEST_CASE_RESULT_ID);
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Search and open an existing defect WI
    getJazzPageFactory().getCCMProjectAreaDashboardPage().quickSearch(EXISTING_DEFECT_ID);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSearchedSpecification(EXISTING_DEFECT_ID);
    Reporter.logPass("Defect Work item ID: '" + EXISTING_DEFECT_ID + "' is opened successfully");
    // Click on the "Links" tab
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab("Links");
    // Add link to defect
    getJazzPageFactory().getCCMWorkItemEditorPage().addLinkToExistingObject(additionalParams);
    // Click on save button
    getJazzPageFactory().getCCMWorkItemEditorPage().saveWorkItem();
    // Verify new link is add defect
    getJazzPageFactory().getCCMWorkItemEditorPage().isTestArtifactAddedInLinksSection(EXISTING_DEFECT_NAME);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnLinkFromWorkItemLinksSection("Links", TEST_CASE_RESULT_ID,
        "Affects Test Case Result");
    getJazzPageFactory().getRMDashBoardPage().openRQMSection("Defects");
    getJazzPageFactory().getRQMExecutionPage().isDefectVisibleInDefectsSection(EXISTING_DEFECT_ID);
    getJazzPageFactory().getCCMWorkItemEditorPage().clickOnWorkItemFromTestArtifact(EXISTING_DEFECT_ID);
    // Click on the "Links" tab
    getJazzPageFactory().getCCMWorkItemEditorPage().selectTab("Links");
    getJazzPageFactory().getCCMWorkItemEditorPage().deleteAllLinks(TEST_CASE_RESULT_NAME);
    // Click on save button
    getJazzPageFactory().getAbstractCCMPage().clickOnButtons("Save");
  }
}
