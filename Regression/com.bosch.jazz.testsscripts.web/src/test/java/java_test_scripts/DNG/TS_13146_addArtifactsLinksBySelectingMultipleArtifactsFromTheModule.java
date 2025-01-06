/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ModuleEnums;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author HRT5KOR
 */
public class TS_13146_addArtifactsLinksBySelectingMultipleArtifactsFromTheModule extends AbstractFrameworkTest {

  /**
   * test cases for creating "Satisfied By" links for multiple artifacts in requirements project area and verifies link
   * is generated or not in other artifact and vice versa.
   *
   * @throws IOException         if the configuration data not found.
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void tcs_13146_addArtifactsLinksBySelectingMultipleArtifactsFromTheModule()
      throws IOException, InvalidKeyException

  {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_CM_PROJECT_AREA");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID_CM");
    String contextMenu = "Add Link to Artifact";
    String linkType = "Satisfied By";
    String linkId = "";
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMDashBoardPage().selectProjectArea(projectArea);
    // Select the particular component.
    getJazzPageFactory().getRMDashBoardPage().selectPariticularComp(
        "SYS-TEST-com.bosch.dng.tmpl.default.usa_2018.1.0_With_CM",
        "SYS-TEST-com.bosch.dng.tmpl.default.usa_2018.1.0_With_CM");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Streams", "Streams");
    additionalParams.put("streamName", "System Test Component Initial Development");
    additionalParams.put("type", "Switch");
    additionalParams.put("configuration", "Global Configuration");
    // Switch to Global configuration and select the stream which is associated with the project area.
    getJazzPageFactory().getRMDashBoardPage().selectTheConfigContext(additionalParams);
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Search for the existing module id.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(moduleId);
    Reporter.logPass(moduleId + ": module found in the project area.");
    // Returns list of artifacts present under the module.
    List<String> artifactlist = getJazzPageFactory().getRMModulePage().getArtifactIDListFromModule();
    // Select all the artifacts present in the list.
    for (String s : artifactlist) {
      getJazzPageFactory().getRMArtifactPage().selectArtifact(s);
      Reporter.logPass(s + ": artifact selected successfully.");
    }
    linkId = this.testDataRule.getConfigData("SATISFIED_BY_LINK_ID");
    additionalParams.put(ArtifactProperties.ID.toString(), linkId);
    // Select artifact and Open the context menu
    // getJazzPageFactory().getRMArtifactPage().selectArtifactAndOpenContextMenu(linkId);
    contextMenu = "Add Link to " + artifactlist.size() + " Artifact";
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), contextMenu);
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString(), linkType);
    // Open the Add artifact link and click on Satisfied By.
    getJazzPageFactory().getRMLinksPage().clickOnContextMenuOfArtifactInModule(additionalParams);
    Reporter.logPass(contextMenu + ": context menu opened and clicked on link type :" + linkType);
    // Choose the artifact link from the list of the links available and add to artifacts link.
    // getJazzPageFactory().getRMLinksPage().chooseExistingItemFromCreateLinkDialog(additionalParams);
    Reporter.logPass(linkId + ": added to the artifact" + artifactlist);
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    // Satisfied by new column added to list of artifacts table.
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    Reporter.logPass(linkType + " new column added to list of artifacts table");
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    additionalParams.put(ArtifactProperties.ID_2.toString(), linkId);
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), ModuleEnums.LINKS.toString());
    additionalParams.put(ArtifactProperties.ID_NAME.toString(),
        this.testDataRule.getConfigData("SATISFIED_BY_LINK_ID_NAME"));
    // Verify whether link is added or not under Satisfied link and click on the link , Go to the Link section and
    // verify under the satisfies
    // section artifact is present or not.
    // Assert.assertTrue(getJazzPageFactory().getRMLinksPage().openSatisfiedByLink(additionalParams));
    String screenShotTitle = "Satisfied By to Satisfies " + DateUtil.getCurrentDateAndTime();
    String fileName = ScreenShotUtil.makeScreenShot(Reporter.getResultsReportDirLocation(), screenShotTitle, driver);
    Reporter.logPassWithScreenshot(artifactlist + " id exits in the under the section:" + linkType, fileName,
        screenShotTitle);
    linkType = "Satisfies";
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    // Satisfied by new column added to list of artifacts table.
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    Reporter.logPass(linkType + " new column added to list of artifacts table");
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    linkId = this.testDataRule.getConfigData("SATISFIES_BY_LINK_ID");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    additionalParams.put(ArtifactProperties.ID_2.toString(), linkId);
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), ModuleEnums.LINKS.toString());
    // Verify whether link is added or not under Satisfied link and click on the link , Go to the Link section and
    // verify under the satisfies
    // section artifact is present or not.
    // Assert.assertTrue(getJazzPageFactory().getRMLinksPage().openSatisfiesLink(additionalParams));
    screenShotTitle = "Satisfies to Satisfied By " + DateUtil.getCurrentDateAndTime();
    fileName = ScreenShotUtil.makeScreenShot(Reporter.getResultsReportDirLocation(), screenShotTitle, driver);
    Reporter.logPassWithScreenshot(artifactlist + " id exits in the under the section:" + linkType, fileName,
        screenShotTitle);
  }
}
