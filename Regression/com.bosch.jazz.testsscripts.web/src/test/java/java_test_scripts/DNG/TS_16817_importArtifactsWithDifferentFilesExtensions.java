/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YNT2HC
 */
public class TS_16817_importArtifactsWithDifferentFilesExtensions extends AbstractFrameworkTest {

  /**
   * This test case is created to import the artifacts from migration package files and verifying the artifacts are
   * added to the folder correctly
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_16817_importArtifactsWithDifferentFilesExtensions() throws Throwable {

    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");

    List<String> listMigizFileName = new ArrayList<String>();
    listMigizFileName.add(this.testDataRule.getConfigData("MIGRATION_EXCEL_XLSX_FILE_NAME"));
    listMigizFileName.add(this.testDataRule.getConfigData("MIGRATION_EXCEL_XLS_FILE_NAME"));
    listMigizFileName.add(this.testDataRule.getConfigData("MIGRATION_CSV_FILE_NAME"));
    listMigizFileName.add(this.testDataRule.getConfigData("MIGRATION_TEXT_DOCX_FILE_NAME"));
    listMigizFileName.add(this.testDataRule.getConfigData("MIGRATION_TEXT_TXT_FILE_NAME"));

    // Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");

    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Open Artifacts menu.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);

    // Open Import Dialog
    boolean finalResult = true;
    boolean result;
    for (int i = 0; i < listMigizFileName.size(); i++) {
      getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Import Artifact...");
      Reporter.logPass("Import Dialog is opened successfully.");
      getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
      result = getJazzPageFactory().getRMArtifactPage()
          .importRequirementsFromAMigrationPackageWithError(listMigizFileName.get(i));
      getJazzPageFactory().getRMArtifactPage().waitForSecs(2);
      if (!result) {
        Reporter
            .logFailure("The error message is displayed wrong when importing file '" + listMigizFileName.get(i) + "'");
      }
      else {
        Reporter.logPass("The error message is displayed correctly as expected when importing file '" +
            listMigizFileName.get(i) + "'");
      }
      finalResult = finalResult & result;
    }
    Assert.assertTrue(finalResult);
  }

}
