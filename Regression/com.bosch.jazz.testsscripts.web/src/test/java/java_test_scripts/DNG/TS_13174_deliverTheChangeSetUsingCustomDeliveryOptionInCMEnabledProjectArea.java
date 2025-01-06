/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.constants.ConfigurationManagementEnums.CurrentConfig;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Artifact;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactTypes;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_13174_deliverTheChangeSetUsingCustomDeliveryOptionInCMEnabledProjectArea extends AbstractFrameworkTest {

  /**
   * This test case for Deliver the change set using 'Custom' Delivery option.
   *
   * @throws Throwable is use to handle any kind of exception.
   */
  @Test
  public void tcs_13174_deliverTheChangeSetUsingCustomDeliveryOptionInCMEnabledProjectArea() throws Throwable {

    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String gcProjectArea = this.testDataRule.getConfigData("GC_PROJECT_AREA");
    String componentName = this.testDataRule.getConfigData("COMPONENT_NAME");
    String streamName = this.testDataRule.getConfigData("STREAM_NAME");
    String artifactType = this.testDataRule.getConfigData("ARTIFACT_TYPE");
    String artifactName = this.testDataRule.getConfigData("ARTIFACT_NAME");
    String artifactFormat = this.testDataRule.getConfigData("ARTIFACT_FORMAT");
    String changeSetName = this.testDataRule.getConfigData("CHANGE_SET_NAME");

    // Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectAreaAndGlobalConfiguration(projectArea, gcProjectArea,
        componentName, streamName);
    Reporter.logPass(projectArea + ": project area opened successfully with '" + componentName + "' component and '" +
        streamName + "' stream.");
    // Open Artifacts Menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Click on 'Current Configuration'and choose 'Create Change Set' option.
    getJazzPageFactory().getRMDashBoardPage()
        .clickOnCurrentConfigurationDropdownMenu(CurrentConfig.CREATECHANGESET.toString());
    Reporter.logPass("Clicked on " + CurrentConfig.CREATECHANGESET.toString() + " option successfully.");
    // Create a 'Change Set' from 'Current Configuration' menu.
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ConfigName", changeSetName + DateUtil.getCurrentDateAndTime());
    additionalParams.put("ConfigOption", CurrentConfig.CREATECHANGESET.toString());
    additionalParams.put(Artifact.NAME.toString(), artifactName + DateUtil.getCurrentDateAndTime());
    additionalParams.put("Artifact type", artifactType);
    additionalParams.put("Artifact format", artifactFormat);
    additionalParams.put("Streams", "Streams");
    additionalParams.put("type", "Switch");
    additionalParams.put("configuration", "Global Configuration");
    additionalParams.put("streamName", streamName);
    getJazzPageFactory().getRMDashBoardPage().createConfig(additionalParams);
    Reporter.logPass("Change set is created successfully.");
    // Select Requirement type from 'Other...' option.
    getJazzPageFactory().getRMArtifactPage().clickOnCreateButton("Other...");
    Reporter.logPass(artifactType + " type is selected for the Artifact.");
    // Create a Artifact with required options.
    getJazzPageFactory().getRMArtifactPage().createArtifact(additionalParams);
    Reporter.logPass("Artifact is created.");
    // Click on the 'Done' button.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Done");
    Reporter.logPass("Clicked on 'Done' button.");
    String artifactID = getJazzPageFactory().getRMArtifactPage().getArtifactID();
    // Deliver the created change set.
    getJazzPageFactory().getRMDashBoardPage()
        .clickOnCurrentConfigurationDropdownMenu(CurrentConfig.DELIVERCHANGESET.toString());
    Reporter.logPass("Delivered the created change set.");
    // Choose the change set delivery option as 'Standard'.
    getJazzPageFactory().getRMDashBoardPage().chooseChangeSetDeliveryOption("Custom");
    Reporter.logPass("Choose the change set delivery option as 'Custom'.");
    // Click on 'Next' button.
    getJazzPageFactory().getRMDashBoardPage().clickOnJazzButtons("Next >");
    Reporter.logPass("Clicked on 'Next' button.");
    // Verify the delivered artifact.
    Assert.assertTrue(getJazzPageFactory().getRMDashBoardPage().getDeliveredArtifact(artifactID));
    Reporter.logPass("Verified the delivered artifact using Id of the artifact.");
    // Click on 'Deliver' button.
    getJazzPageFactory().getRMDashBoardPage().clickOnJazzButtons("Deliver");
    Reporter.logPass("Clicked on 'Deliver' button.");
    // Click on 'Done' button.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Done");
    Reporter.logPass("Clicked on 'Done' button.");
    // Switch to 'Global Configuration' and select the stream which is associated with the project area.
    getJazzPageFactory().getRMDashBoardPage().selectTheConfigContext(additionalParams);
    Reporter.logPass("Switched to 'Global Configuration' and selected the sream. ");
    // Click on 'Done' button.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzButtons("Done");
    Reporter.logPass("Clicked on 'Done' button.");
    // Open Artifacts Menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Click on All hyper link.
    getJazzPageFactory().getRMArtifactPage().clickOnArtifactTypes(ArtifactTypes.ALL.toString());
    Reporter.logPass(ArtifactTypes.ALL.toString() + " hyper link of Artifacts opened successfully.");
    // Search for created artifact id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
    Reporter.logPass(artifactID + " artifact found in the project area");
    // Verify the created artifact is displayed.
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
    Reporter.logPass("Verified '" + artifactID + "' is displayed in artifact table.");
    // Delete the created artifact.
    getJazzPageFactory().getRMArtifactPage().deleteArtifact(artifactID);
    Reporter.logPass("Delete the created artifact " + artifactID);
    // Search for created artifact id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(artifactID);
    Reporter.logPass(artifactID + " artifact found in the project area");
    // Verify the newly created artifact is deleted.
    Assert.assertFalse(getJazzPageFactory().getRMArtifactPage().isArtifactDisplayed(artifactID));
    Reporter.logPass("Verified '" + artifactID + "' is deleted from artifact table.");
  }
}
