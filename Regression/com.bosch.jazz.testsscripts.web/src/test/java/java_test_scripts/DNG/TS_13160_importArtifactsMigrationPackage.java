/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author YNT2HC
 */
public class TS_13160_importArtifactsMigrationPackage extends AbstractFrameworkTest {

  /**
   * This test case is created to import the artifacts from migration package files and verifying the artifacts are
   * added to the folder correctly
   *
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13160_importArtifactsMigrationPackage() throws Throwable {

    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String globalArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String migizFileName = this.testDataRule.getConfigData("MIGRATION_MIGIZ_FILE_NAME");
    String artifactAttribute = this.testDataRule.getConfigData("ATTRIBUTE_IMPORTED_ARTIFACT");
    String moduleAttribute = this.testDataRule.getConfigData("ATTRIBUTE_IMPORTED_MODULE");
    String importedModuleFolder = this.testDataRule.getConfigData("IMPORTED_ARTIFACT_FOLDER");
    String importedModuleName = this.testDataRule.getConfigData("IMPORTED_MODULE_NAME");
    String migrationArtifactFolder = this.testDataRule.getConfigData("MIGRATION_ARTIFACTS_FOLDER");

    // Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectAreaAndGlobalConfiguration(projectArea, globalArea,
        componentName, streamName);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    Reporter.logPass(projectArea + ": project area opened successfully.");

    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);

    // Open Import Dialog
    getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Import Artifact...");
    Reporter.logPass("Import Dialog is opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);

    // Upload Migiz file on Import Dialog
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(RMConstants.FILETYPE, migizFileName);
    additionalParams.put("importedFolder", importedModuleFolder);
    additionalParams.put("artifactAttribute", artifactAttribute);
    additionalParams.put("moduleAttribute", moduleAttribute);
    additionalParams.put("migrationFolder", migrationArtifactFolder);
    String getActualString =
        getJazzPageFactory().getRMArtifactPage().importArtifactsWithMigizFileExtension(additionalParams);
    Reporter.logPass("Migrating " + migizFileName + " into " + importedModuleFolder + " folder is success.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);

    // Verify successful message is return
    Assert.assertEquals("Import requirements from a migration package :: The import is complete. Show Report",
        getActualString);

    // Clean up migrated artifacts
    // getJazzPageFactory().getRMArtifactPage().deleteArtifactInFolder("ALM_System", importedModuleName);
    Reporter.logPass(importedModuleName + " in " + importedModuleFolder + " is deleted successfully.");
    getJazzPageFactory().getRMArtifactPage().deleteArtifactFolder(migrationArtifactFolder, "FALSE");
    Reporter.logPass(migrationArtifactFolder + " folder is deleted successfully.");
  }

}
