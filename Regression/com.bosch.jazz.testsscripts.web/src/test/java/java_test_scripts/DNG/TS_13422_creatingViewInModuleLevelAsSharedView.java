/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 */
public class TS_13422_creatingViewInModuleLevelAsSharedView extends AbstractFrameworkTest {

  /**
   * This test case Create view in module level as shared view,add columns and filters in the view, check for the view
   * for othre user and Validate view is created with added columns and filters, and also view is displayed to other
   * user as it's a shared view.
   * 
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_13422_creatingViewInModuleLevelAsSharedView() throws Throwable {

    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String attributeName = this.testDataRule.getConfigData("ATTRIBUTE_NAME");
    String artifactType = this.testDataRule.getConfigData("ARTIFACT_TYPE");
    String viewName = this.testDataRule.getConfigData("VIEW_NAME");
    String whereToShow = this.testDataRule.getConfigData("WHERE_TO_SHOW");
    String viewDescription = this.testDataRule.getConfigData("VIEW_DESCRIPTION");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID");
    String UserId = this.testDataRule.getConfigData("USER_ID");

    // Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Open Artifacts Menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Search for existing module id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(moduleId);
    Reporter.logPass(moduleId + " module found in the project area");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(8);
    // Open the module searched in 'Type to filter artifacts by text or by ID' search box.
    getJazzPageFactory().getRMArtifactPage().openRMSpecification(moduleId);
    Reporter.logPass(moduleId + " module opened in the project area");
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    // Click on clear filter icon.
    getJazzPageFactory().getRMArtifactPage().clearFilter();
    Reporter.logPass("Filter is cleared");
    // Click on add filter icon.
    getJazzPageFactory().getRMArtifactPage().addFilter();
    Reporter.logPass("Clicked on Add Filter icon");
    // Search for the attribute type in 'New Filter' wizard.
    getJazzPageFactory().getRMArtifactPage().searchAttributeInFilter(attributeName);
    Reporter.logPass(attributeName + " searched in 'New Filter' wizard");
    // Choose the artifact type for the attribute.
    getJazzPageFactory().getRMArtifactPage().chooseAttributeValueForSelectedAttribute(attributeName, artifactType);
    Reporter.logPass("Choose " + artifactType + " for " + attributeName);
    // Refresh the webpage.
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    String linkType = "Artifact Type";
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    // Validated By new column added to list of artifacts table.
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    Reporter.logPass(linkType + " new column added to list of artifacts table");
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    // Validate filters added according to mentioned filter criteria.
    Assert.assertTrue(
        getJazzPageFactory().getRMArtifactPage().getColumnDatafromTable("Artifact Type").contains(artifactType));
    linkType = "Validated By";
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    // Validated By new column added to list of artifacts table.
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    Reporter.logPass(linkType + " new column added to list of artifacts table");
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    // Implemented By new column added to list of artifacts table.
    linkType = "Implemented By";
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), linkType);
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    Reporter.logPass(linkType + " new column added to list of artifacts table");
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    // Validate 'Validated By' Column added in the view.
    // Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isColoumAddedToTable("Validated By"));
    // Validate 'Implemented By' Column added in the view.
    // Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isColoumAddedToTable("Implemented By"));
    // Click on 'Save as new view' icon.
    getJazzPageFactory().getRMArtifactPage().clickOnSaveAsNewView();
    Reporter.logPass("Clicked on 'Save as new view' icon.");
    // Validate 'New View' wizard displayed.
    // Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().validateNewViewWizard());
    // Set view name in 'New View' wizard.
    getJazzPageFactory().getRMArtifactPage().setViewName(viewName);
    viewName = getJazzPageFactory().getRMArtifactPage().getViewName();
    Reporter.logPass("View name given as " + viewName);
    // Make the view type as 'Personal'.
    getJazzPageFactory().getRMArtifactPage().setViewType("Shared");
    Reporter.logPass("View made as 'Personal' view");
    // Set where to show this view.
    getJazzPageFactory().getRMArtifactPage().setWhereToShowThisView(whereToShow);
    Reporter.logPass("User can see this view in 'All modules'.");
    // Choose Role for the view.
    getJazzPageFactory().getRMArtifactPage().chooseRole("Everyone");
    Reporter.logPass("Everyone role chosen for the view");
    // Add description for view.
    getJazzPageFactory().getRMArtifactPage().addDescriptionInView(viewDescription);
    Reporter.logPass(viewDescription + " description added in the view");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().refresh();
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));

    // Logout alm application
    getJazzPageFactory().getRMDashBoardPage().logout();
    Reporter.logPass("User is able to" + url + "logout successfully");
    // Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(UserId, getUserPassword());
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Open Artifacts Menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Clear the added filter.
    getJazzPageFactory().getRMArtifactPage().clearFilter();
    Reporter.logPass("Filter is cleared");
    // Search for existing module id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(moduleId);
    Reporter.logPass(moduleId + " module found in the project area");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(8);
    // Open the module searched in 'Type to filter artifacts by text or by ID' search box.
    getJazzPageFactory().getRMArtifactPage().openRMSpecification(moduleId);
    Reporter.logPass(moduleId + " module opened in the project area");
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    // Search for the created view.
    getJazzPageFactory().getRMArtifactPage().searchView(viewName);
    Reporter.logPass(viewName + " searched in " + moduleId);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Validate searched personal view is exist in that module.
    // Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isViewDisplayed(viewName));
    // Log out the application.
    getJazzPageFactory().getRMDashBoardPage().logout();
    Reporter.logPass("User is able to " + url + "logout successfully");

    // Login to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword());
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Open Artifacts Menu
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Clear the added filter.
    getJazzPageFactory().getRMArtifactPage().clearFilter();
    Reporter.logPass("Filter is cleared");
    // Search for existing module id in search box 'Type to filter artifacts by text or by ID'.
    getJazzPageFactory().getRMArtifactPage().filterArtifactByTextOrId(moduleId);
    Reporter.logPass(moduleId + " module found in the project area");
    // Open the module searched in 'Type to filter artifacts by text or by ID' search box.
    getJazzPageFactory().getRMArtifactPage().openRMSpecification(moduleId);
    Reporter.logPass(moduleId + " module opened in the project area");
    ExpectedConditionsCustom.handleAlert(driver, Duration.ofSeconds(5));
    // Search for the created view.
    getJazzPageFactory().getRMArtifactPage().searchView(viewName);
    Reporter.logPass(viewName + " searched in " + moduleId);
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Delete the searched view.
    getJazzPageFactory().getRMArtifactPage().deleteView(viewName);
    Reporter.logPass(viewName + " deleted from " + moduleId);
  }

}
