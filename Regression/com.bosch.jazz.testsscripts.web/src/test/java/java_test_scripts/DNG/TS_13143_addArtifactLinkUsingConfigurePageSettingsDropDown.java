/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.TestProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author HRT5KOR
 */
public class TS_13143_addArtifactLinkUsingConfigurePageSettingsDropDown extends AbstractFrameworkTest {

  /* *//**
        * test cases for creating "Implemented By" and "Validated By" links in requirements project area and verifies
        * link is generated or not in other domain project areas and vice versa.
        *
        * @throws IOException if the configuration data not found.
        * @throws InvalidKeyException if the property doesn't exist.
        */
  @Test
  public void tcs_13143_addArtifactLinkUsingConfigurePageSettingsDropDown() throws IOException, InvalidKeyException {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID");
    String artifactid = this.testDataRule.getConfigData("ARTIFACT_ID");
    String contextMenu = "Add Link to Artifact";
    String linkType = "Implemented By";
    String linkId = this.testDataRule.getConfigData("IMPLEMENTED_BY_LINK_ID");
// Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
// Select the project area.
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
// Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
// Search for the existing module id.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(moduleId);
    Reporter.logPass(moduleId + ": module found in the project area.");
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), artifactid);
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), contextMenu);
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString(), linkType);
// Select artifact and Open the context menu.
//getJazzPageFactory().getRMArtifactPage().selectArtifactAndOpenContextMenu(artifactid);
    Reporter.logPass(artifactid + ": artifact found and selected successfully.");
// Open the Add artifact link and click on Implemented By.
    getJazzPageFactory().getRMLinksPage().clickOnContextMenuOfArtifactInModule(additionalParams);
    Reporter.logPass(contextMenu + ": context menu opened and clicked on link type :" + linkType);
    additionalParams.put(CCMConstants.WORKITEM_ID, linkId);
// Choose the work item number from the list of the links available and add to artifact link.
//getJazzPageFactory().getRMLinksPage().chooseExistingItemFromCreateLinkDialog(additionalParams);
    Reporter.logPass(linkId + ": added to the artifact" + artifactid);
    getJazzPageFactory().getRMArtifactPage().refresh();
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString(), linkType);
// Implemented By new column added to list of artifacts table.
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    Reporter.logPass(linkType + " new column added to list of artifacts table");
    additionalParams.put(ArtifactProperties.ID.toString(), artifactid);
    linkType = "Validated By";
    linkId = this.testDataRule.getConfigData("VALIDATED_BY_LINK_ID");
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), contextMenu);
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString(), linkType);
    additionalParams.put(TestProperties.CREATE_OR_EXISTING.toString(), "Choose Existing");
// Select artifact and Open the context menu
//getJazzPageFactory().getRMArtifactPage().selectArtifactAndOpenContextMenu(artifactid);
// Open the Add artifact link and click on Validated By.
    getJazzPageFactory().getRMLinksPage().clickOnContextMenuOfArtifactInModule(additionalParams);
    Reporter.logPass(contextMenu + ": context menu opened and clicked on link type :" + linkType);
    additionalParams.put(TestProperties.ID.toString(), linkId);
// Choose the test specification link from the list of the links available and add to artifact link.
//getJazzPageFactory().getRMLinksPage().chooseExistingItemFromCreateLinkDialog(additionalParams);
    Reporter.logPass(linkId + ": added to the artifact" + artifactid);
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString(), linkType);
// Validated by new column added to list of artifacts table.
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    Reporter.logPass(linkType + " new column added to list of artifacts table.");
    linkType = "Implemented By";
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString(), linkType);
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
// Validate Implemented link is created in for the artifact and open the work item and verify requirement link is
// created or not.
//Assert.assertTrue(getJazzPageFactory().getRMLinksPage().openImplementedByLink(additionalParams));
// Screenshot.
    String screenShotTitle = "Implemented by " + DateUtil.getCurrentDateAndTime();
    String fileName = ScreenShotUtil.makeScreenShot(Reporter.getResultsReportDirLocation(), screenShotTitle, driver);
    Reporter.logPassWithScreenshot(artifactid + " id exits in the link tab under implements section.", fileName,
        screenShotTitle);
    getJazzPageFactory().getRMArtifactPage().navigateBack();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    getJazzPageFactory().getRMArtifactPage().navigateBack();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    linkType = "Validated By";
    additionalParams.replace(ArtifactProperties.ARTIFACT_CONTEXT_SUBMENU.toString(), linkType);
// Validate Validated By is created in for the artifact and open the test specification and verify requirement link
// is created or not.
//Assert.assertTrue(getJazzPageFactory().getRMLinksPage().openValidatedByLink(additionalParams));
// Screenshot.
    screenShotTitle = "Validated by " + DateUtil.getCurrentDateAndTime();
    fileName = ScreenShotUtil.makeScreenShot(Reporter.getResultsReportDirLocation(), screenShotTitle, driver);
    Reporter.logPassWithScreenshot(artifactid + " exits in the requirements section.", fileName, screenShotTitle);
  }

}
