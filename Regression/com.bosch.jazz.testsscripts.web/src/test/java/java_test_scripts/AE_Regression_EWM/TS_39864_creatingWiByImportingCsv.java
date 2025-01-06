/*
 * Copyright (c) ETAS GmbH 2024. All rights reserved.
 */
package java_test_scripts.AE_Regression_EWM;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 */
public class TS_39864_creatingWiByImportingCsv extends AbstractFrameworkTest {

  /**
   * @author KAZ1COB This test case for login to CCM web and create the WI by importing the CSV.
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_39864_creatingWiByImportingCsv() throws Throwable {

    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String menu = this.testDataRule.getConfigData("CCM_MENU");
    String subMenu = this.testDataRule.getConfigData("CCM_SUB_MENU");
    String importfileName = this.testDataRule.getConfigData("FILE_NAME");

    Reporter.logInfo("**************************Test script Started**************************");

    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");

    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().waitForSecs(10);
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Click on Work Item menu bar
    // Select Work Items from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(menu);
    Reporter.logPass("Clicked on work items menu");

    // Click on Work Item sub menu bar
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(subMenu);
    Reporter.logPass("Clicked on Import From CSV File from sub menu");

    // Import CSV File
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openImportFromCSVFileTab(importfileName);
    Reporter.logPass("Successfully new WI created by importing the CSV file!!!");

    // verify whether the expected message is displayed
    getJazzPageFactory().getCCMProjectAreaDashboardPage()
        .verifyValidationMessageAfterCSVFileImported("Successfully imported work items from CSV file.");

    String fileName = ScreenShotUtil.makeScreenShotAndReturnFileNameWithUUID(Reporter.getResultsReportDirLocation(),
        "verifyValidationMessageAfterCSVFileImported", driver);
    Reporter.logPassWithScreenshot("Verified : Successfully imported work items from CSV file.", fileName, fileName);

    Reporter.logInfo("**************************Test script Completed**************************");

  }
}
