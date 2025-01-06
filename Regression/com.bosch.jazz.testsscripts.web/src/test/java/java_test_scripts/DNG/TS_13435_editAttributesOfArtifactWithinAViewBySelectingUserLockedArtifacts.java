/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactTypes;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Menu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBW1KOR
 */
public class TS_13435_editAttributesOfArtifactWithinAViewBySelectingUserLockedArtifacts extends AbstractFrameworkTest {

  /* *//**
        * test cases for creating "Implemented By" and "Validated By" links in requirements project area and verifies
        * link is generated or not in other domain project areas and vice versa.
        *
        * @throws Throwable is use to handle any kind of exception.
        */
  @Test
  public void tcs_13435_editAttributesOfArtifactWithinAViewBySelectingUserLockedArtifacts() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID");
    String viewname = this.testDataRule.getConfigData("VIEW_NAME");
    String editattributeDescription = this.testDataRule.getConfigData("EDIT_ATTRIBUTE_DESCRIPTION");
    String descriptionvalue = this.testDataRule.getConfigData("DESCRIPTION_VALUE");
    String artifactId2 = this.testDataRule.getConfigData("ARTIFACT_ID2");
    String artifactId1 = this.testDataRule.getConfigData("ARTIFACT_ID1");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Open Artifacts menu.
    getJazzPageFactory().getRMDashBoardPage().openMenu(Menu.ARTIFACTS.toString());
    Reporter.logPass(Menu.ARTIFACTS.toString() + " menu opened successfully.");
    // Open Modules tab.
    getJazzPageFactory().getRMArtifactPage().clickOnArtifactTypes(ArtifactTypes.MODULES.toString());
    // Open existing module artifact which contains view.
    getJazzPageFactory().getRMDashBoardPage().quickSearch(moduleId);
    Reporter.logPass(moduleId + ": module opened successfully.");
    // Select particular view.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().searchView(viewname);
    Reporter.logPass(viewname + ": view searched.");
    // From View Options drop down Select Edit Attribute from View… option.
    getJazzPageFactory().getRMArtifactPage().selectView(viewname);
    Reporter.logPass(viewname + "View selected.");
    // Returns list of artifacts present under the module.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    List<String> artifactlist = getJazzPageFactory().getRMModulePage().getArtifactIDListFromModule();
    // Select all the artifacts present in the list.
    for (String s : artifactlist) {
      getJazzPageFactory().getRMArtifactPage().selectArtifact(s);
      Reporter.logPass(s + ": artifact selected successfully.");
    }
    // Select artifact and Open the context menu
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId2,
        "Edit the attributes for 2 Artifacts...");
    getJazzPageFactory().getRMArtifactPage().clickOnDialogButton("Locked Artifacts", "Yes");
    // “Edit Attributes” dialog box select the attribute check box.
    getJazzPageFactory().getRMArtifactPage().clickOnEditAttributeCheckbox(editattributeDescription);
    Reporter.logPass(editattributeDescription + ": check box selected successfully.");
    // Select or type the value for the selected attributes (e.g.: Description, Tags).
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().setAttributeValue(editattributeDescription, descriptionvalue);
    // Click on [Close]
    getJazzPageFactory().getRMArtifactPage().clickOnDialogButton("Edit Attributes", "Save");
    // Add column for edited attribute, if it is not available.
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), editattributeDescription);
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    // Columns should be added successfully.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    // Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isColoumAddedToTable(editattributeDescription));
    // verify locked artifact content
    System.out.println(getJazzPageFactory().getRMArtifactPage().verifyArtifactValuesInModuleView(artifactId2,
        editattributeDescription, ""));
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().verifyArtifactValuesInModuleView(artifactId2,
        editattributeDescription, ""));
    getJazzPageFactory().getRMArtifactPage().selectArtifact(artifactId1);
    Reporter.logPass(artifactId1 + ": artifact selected successfully.");
    getJazzPageFactory().getRMArtifactPage().openContextMenuForSelectedArtifact(artifactId1, "Edit Attributes...");
    // “Edit Attributes” dialog box select the attribute check box.
    getJazzPageFactory().getRMArtifactPage().clickOnEditAttributeCheckbox(editattributeDescription);
    Reporter.logPass(editattributeDescription + ": check box selected successfully.");
    getJazzPageFactory().getRMArtifactPage().clearAttributeValue(editattributeDescription);
    // Click on [Close]
    getJazzPageFactory().getRMArtifactPage().clickOnDialogButton("Edit Attributes", "Save");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    // Undo All the changes.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzImageButtons("Undo all changes to this view");
  }

}
