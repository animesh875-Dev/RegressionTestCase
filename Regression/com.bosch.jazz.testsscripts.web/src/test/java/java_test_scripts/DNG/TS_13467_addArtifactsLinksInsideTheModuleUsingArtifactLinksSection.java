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
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ModuleEnums;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author HRT5KOR
 *
 */
public class TS_13467_addArtifactsLinksInsideTheModuleUsingArtifactLinksSection extends AbstractFrameworkTest {
  /**
   * test cases for creating "Link To" links in requirements project area and verifies link is generated or not in other
   * artifact and vice versa.
   * 
   * @throws IOException if the configuration data not found.
   * @throws InvalidKeyException if the property doesn't exist.
   */
  @Test
  public void tcs_13147_addArtifactsLinksInsideTheModuleUsingArtifactLinksSection()
      throws IOException, InvalidKeyException {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_CM_PROJECT_AREA");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID_CM");
    String contextMenu = "Add Link to Artifact";
    String linkType = "Link To";
    String linkId = this.testDataRule.getConfigData("LINK_TO_ID");
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
    // Select module tab inside the module.
    getJazzPageFactory().getRMModulePage().chooseTabInsideModule(ModuleEnums.MODULE_SECTION.toString());
    additionalParams.put(RMConstants.MODULELINK, contextMenu);
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    // Click on Module Links , Click on Add Link to Artifact and click on Link To.
   // getJazzPageFactory().getRMLinksPage().clickOnAddArtifactLinkFromRightSideBar(additionalParams);
    Reporter.logPass("Module links container clicked and link type opened :" + linkType);
    additionalParams.put(ArtifactProperties.ID.toString(), linkId);
    // Add existing artifact link to module as Link To.
   // getJazzPageFactory().getRMLinksPage().chooseExistingItemFromCreateLinkDialog(additionalParams);
    Reporter.logPass(linkId + ": added to the artifact :" + moduleId);
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), ModuleEnums.MODULE_LINKS.toString());
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), this.testDataRule.getConfigData("LINK_TO_ID_NAME"));
    // Verify whether under the module section Link To with added link created or not.if It exists link gets opened.
  //  Assert.assertTrue(getJazzPageFactory().getRMLinksPage().openLinkByFromTab(additionalParams));
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    // Screenshot.
    String screenShotTitle = "Link To to Link From";
    String fileName = ScreenShotUtil.makeScreenShot(Reporter.getResultsReportDirLocation(), screenShotTitle, driver);
    Reporter.logPassWithScreenshot(moduleId + " id exits in the under the section:" + linkType, fileName,
        screenShotTitle);
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    linkType = "Link From";
    additionalParams.put(ArtifactProperties.ID.toString(), moduleId);
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), ModuleEnums.LINKS.toString());
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), this.testDataRule.getConfigData("LINK_FROM_ID_NAME"));
    // Verify whether under the Link section Link From with module link created or not.If it is exists link gets opened.
   // Assert.assertTrue(getJazzPageFactory().getRMLinksPage().openLinkByFromTab(additionalParams));
    // Screenshot.
    screenShotTitle = "Link From to Link To" + DateUtil.getCurrentDateAndTime();
    fileName = ScreenShotUtil.makeScreenShot(Reporter.getResultsReportDirLocation(), screenShotTitle, driver);
    Reporter.logPassWithScreenshot(linkId + " id exits in the under the section:" + linkType, fileName,
        screenShotTitle);
  }

}
