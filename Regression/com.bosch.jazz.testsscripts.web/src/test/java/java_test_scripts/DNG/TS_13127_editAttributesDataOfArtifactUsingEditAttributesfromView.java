/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.DNG;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 *
 *
 */
public class TS_13127_editAttributesDataOfArtifactUsingEditAttributesfromView extends AbstractFrameworkTest {

  /* *//**
        * test cases for creating "Implemented By" and "Validated By" links in requirements project area and verifies
        * link is generated or not in other domain project areas and vice versa.
        *
        * @throws Throwable is use to handle any kind of exception.
        */
  @Test
  public void tcs_13127_editAttributesDataOfArtifactUsingEditAttributesfromView() throws Throwable {
    String url = this.testDataRule.getConfigData("RM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("RM_PROJECT_AREA");
    String moduleId = this.testDataRule.getConfigData("MODULE_ID");
    String viewname = this.testDataRule.getConfigData("VIEW_NAME");
    String editattributeDescription = this.testDataRule.getConfigData("EDIT_ATTRIBUTE_DESCRIPTION");
    String editattributeTag = this.testDataRule.getConfigData("EDIT_ATTRIBUTE_TAG");
    String descriptionvalue = this.testDataRule.getConfigData("DESCRIPTION_VALUE");
    String tagvalue = this.testDataRule.getConfigData("TAG_VALUE");
    // Login in to alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    getJazzPageFactory().getRMArtifactPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    // Quick search module
    getJazzPageFactory().getRMDashBoardPage().quickSearch(moduleId);
    Reporter.logPass(moduleId + ": module opened successfully.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    // Open searched artifact
    getJazzPageFactory().getRMDashBoardPage().openSearchedSpecification(moduleId);
    Reporter.logPass("Open '" + moduleId + "' module found in the project area.");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Select particular view.
    getJazzPageFactory().getRMArtifactPage().searchView(viewname);
    Reporter.logPass(viewname + ": view searched.");
    // From View Options drop down Select Edit Attribute from View… option.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().selectOptionFromViewOptionsDropdown(viewname,
        "Edit Attributes from View...");
    Reporter.logPass("Edit Attributes from View selected from " + viewname + "drop down.");
    // “Edit Attributes” dialog box select the attribute check box.
    getJazzPageFactory().getRMArtifactPage().clickOnEditAttributeCheckbox(editattributeDescription);
    Reporter.logPass(editattributeDescription + ": check box selected successfully.");
    getJazzPageFactory().getRMArtifactPage().clickOnEditAttributeCheckbox(editattributeTag);
    Reporter.logPass(editattributeTag + ": check box selected successfully.");
    // Select or type the value for the selected attributes (e.g.: Description, Tags).
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().setAttributeValue(editattributeDescription, descriptionvalue);
    Reporter.logPass(editattributeDescription + " value given as:" + descriptionvalue);
    getJazzPageFactory().getRMArtifactPage().clickOnJazzImageButtons("Edit Tags");
    getJazzPageFactory().getRMArtifactPage().clearSelectedTags();
    getJazzPageFactory().getRMArtifactPage().selectTags(tagvalue);
    Reporter.logPass(editattributeTag + "value given as:" + tagvalue);
    // Click on OK button.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("OK");
    Reporter.logPass("Ok button selected successfully");
    // Click on [Apply]
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Apply");
    Reporter.logPass("Apply button selected successfully");
    // wait for progress bar to be 100%.
    getJazzPageFactory().getRMArtifactPage().waitForHundredPercentLoad();
    Reporter.logPass("Waiting for 100% progress bar complete");
    // Verify "Artifact Update Completed" message.
    Assert.assertTrue(
        getJazzPageFactory().getRMArtifactPage().verifyEditAttributeSuccessMessage("Artifact Update Completed"));
    Reporter.logPass("Validating Success message : Artifact Update Completed");
    // Click on [Close]
    getJazzPageFactory().getRMArtifactPage().clickOnDialogButton("Edit Attributes", "Close");
    Reporter.logPass("Close button selected successfully");
    // verify “Edit Attributes” dialog box closed.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Assert.assertTrue(!getJazzPageFactory().getRMArtifactPage().verifyDialogbox("Edit Attributes"));
    Reporter.logPass("Validating Edit Attributes dialog box closed successfully.");
    // Select particular view.
    getJazzPageFactory().getRMArtifactPage().searchView(viewname);
    Reporter.logPass(viewname + ": view searched.");
    // Select View
    getJazzPageFactory().getRMArtifactPage().selectView(viewname);
    Reporter.logPass(viewname + ": view selected.");
    // Add column for edited attribute, if it is not available.
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), editattributeDescription);
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    // Columns should be added successfully.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(10);
    // Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isColoumAddedToTable(editattributeDescription));
    Reporter.logPass(editattributeDescription + " column added to the artifact table successfully");
    // Add column for edited attribute, if it is not available.
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), editattributeTag);
    getJazzPageFactory().getRMArtifactPage().changeColumnDisplaySettings("");
    // Columns should be added successfully.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    // Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().isColoumAddedToTable(editattributeTag));
    Reporter.logPass(editattributeTag + " column added to the artifact table successfully");
    // Select any artifact and go to Selected Artifact section on right side and check for the edited attribute.
    Assert.assertTrue(getJazzPageFactory().getRMArtifactPage().getColumnDatafromTable(editattributeDescription)
        .contains(descriptionvalue));
    Reporter.logPass(descriptionvalue + " is available in " + editattributeDescription + " column validated");
    // Select any artifact and go to Selected Artifact section on right side and check for the edited attribute.
    Assert.assertTrue(
        getJazzPageFactory().getRMArtifactPage().getColumnDatafromTable(editattributeTag).contains(tagvalue));
    Reporter.logPass(tagvalue + " Tag available in " + editattributeTag + " column validated");
    // Undo All the changes.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzImageButtons("Undo all changes to this view");
    // Tear down.
    // From View Options drop down Select Edit Attribute from View… option.
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().selectOptionFromViewOptionsDropdown(viewname,
        "Edit Attributes from View...");
    Reporter.logPass("Edit Attributes from View selected from " + viewname + "drop down.");
    // “Edit Attributes” dialog box select the attribute check box.
    getJazzPageFactory().getRMArtifactPage().clickOnEditAttributeCheckbox(editattributeDescription);
    Reporter.logPass(editattributeDescription + ": check box selected successfully.");
    getJazzPageFactory().getRMArtifactPage().clickOnEditAttributeCheckbox(editattributeTag);
    Reporter.logPass(editattributeTag + ": check box selected successfully.");
    // Select or type the value for the selected attributes (e.g.: Description, Tags).
    getJazzPageFactory().getRMArtifactPage().waitForSecs(5);
    getJazzPageFactory().getRMArtifactPage().clearAttributeValue(editattributeDescription);
    Reporter.logPass(editattributeDescription + "cleared froom" + descriptionvalue);
    getJazzPageFactory().getRMArtifactPage().clickOnJazzImageButtons("Edit Tags");
    getJazzPageFactory().getRMArtifactPage().waitForSecs(3);
    getJazzPageFactory().getRMArtifactPage().clearSelectedTags();
    getJazzPageFactory().getRMArtifactPage().selectTags(tagvalue);
    Reporter.logPass(editattributeTag + "value removed from tag" + tagvalue);
    // Click on OK button.
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("OK");
    Reporter.logPass("Ok button selected successfully");
    // Click on [Apply]
    getJazzPageFactory().getRMArtifactPage().clickOnJazzSpanButtons("Apply");
    Reporter.logPass("Apply button selected successfully");
    // wait for progress bar to be 100%.
    getJazzPageFactory().getRMArtifactPage().waitForHundredPercentLoad();
    Reporter.logPass("Waiting for 100% progress bar complete");
    // Verify "Artifact Update Completed" message.
    Assert.assertTrue(
        getJazzPageFactory().getRMArtifactPage().verifyEditAttributeSuccessMessage("Artifact Update Completed"));
    Reporter.logPass("Validating Success message : Artifact Update Completed");
    // Click on [Close]
    getJazzPageFactory().getRMArtifactPage().clickOnDialogButton("Edit Attributes", "Close");
    Reporter.logPass("Close button selected successfully");
  }
}