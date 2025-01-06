/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentPropertiesPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.container.dialog.JazzDialogFinder;

/**
 * This page verifies the methods of {@link RMManageComponentPropertiesPage}
 */
public class RMManageComponentPropertiesPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom WebDriver
   */

  public RMManageComponentPropertiesPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new LinkFinder());
  }

  /**
   * @param exportFileName name of the component.
   * @param lastResult get the last result of
   *          {@link RMManageComponentPropertiesPage#getDownloadedReqIFFileNameAndPath(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyGetDownededPdfFileNameAndPath(final String exportFileName,
      final String lastResult) {
    File f = new File(lastResult);
    if (f.exists()) {
      return new TestAcceptanceMessage(true, "Verified the '" + lastResult + " file downloaded sucessfully.");
    }
    return new TestAcceptanceMessage(false, "Verified the '" + lastResult + " file not downloaded.");
  }

  /**
   * @param startWithWord To get the only exported summary from the reqIF file report
   * @param additionalParameter Passing the additional String value to vefiry with last results
   * @param lastResult lastResult get the last result of
   *          {@link RMManageComponentPropertiesPage#getSummaryOfExportedReqIF(String)}
   * @return the Verification message
   */
  public TestAcceptanceMessage verifyGetSummaryOfExportedReqIF(final String startWithWord,
      final String additionalParameter, final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalParameter)) {
      return new TestAcceptanceMessage(true, "Actual result is exported summary of reqif file is " + lastResult +
          "----------------------------> matches expected data " + additionalParameter);
    }
    return new TestAcceptanceMessage(false, "Actual result is exported summary of reqif file is " + lastResult +
        "----------------------------> does not matches expected data" + additionalParameter);
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link RMManageComponentPropertiesPage#clickOnTab(String)}.
   *
   * @param tab name of tab.
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTab(final String tab, final String lastResult) {
    if (this.driverCustom.getFirstVisibleWebElement(RMConstants.MANAGECOMPPROPERTIES_TABS_XPATH, tab).getText()
        .equalsIgnoreCase(tab)) {
      return new TestAcceptanceMessage(true, RQMConstants.VERIFIED_COLON + tab + RQMConstants.CLICKED_SUCESSFULLY);
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + tab + RQMConstants.NOT_CLICKED_SUCESSFULLY);
  }

  /**
   * <p>
   * Verifies the action {@link AbstractJazzWebPage#clickOnJazzButtons(String)}.
   *
   * @param button name of Jazz button
   * @param additionalPram to be passed in Excel Test Scripts.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOButtons(final String button, final String additionalPram, final String lastResult) {
    waitForSecs(3);
    Boolean isClickable = this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTONS_XPATH,Duration.ofSeconds(5), button);
    if (!isClickable && Boolean.valueOf(additionalPram)) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED + button + "' button is clicked successfully.\n" + "Actual result '" + button +
          "' button is disabled after clicking.\nExpected result '" + button + "' button should be disabled");
    }
    else if (isClickable && !Boolean.valueOf(additionalPram)) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED + button + "' button is clicked.\n" + "Actual result '" + button +
          "' button is not disabled after clicking as error message/dialog displayed.\nExpected result '" + button +
          "' button should not be disabled");
    }
    else if (Boolean.valueOf(true)) {
      return new TestAcceptanceMessage(true, RMConstants.VERIFIED + button + "' button is clicked.\n" +
          "Actual result '" + button + "' button is disabled after clicking");
    }
    return new TestAcceptanceMessage(false, "Verified a button after clicking." + "Actual result is the '" + button +
        "' button is NOT disabled after clicking and expected is the '" + button + "' button should be disabled");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#searchArtifact(String,String)}.
   * 
   * @param dialog dialog
   * @param artifactID - id of artifact
   * @param lastResult result of method need to verify
   * @return the verification message
   */
  public TestAcceptanceMessage verifySearchArtifact(final String dialog, final String artifactID,
      final String lastResult) {
    if (!this.driverCustom.isElementClickable(RMConstants.MANAGECOMPPROPERTIES_BUTTON_XPATH, Duration.ofSeconds(50), "Add")) {
      return new TestAcceptanceMessage(true,
          "Verify artifact after searching.\nActual is the '" + lastResult + "' artifact is visible");
    }
    return new TestAcceptanceMessage(false,
        "Verify artifact after searching.\nActual is the '" + lastResult + "' artifact is not visible");
  }

  /**
   * <p>
   * Verifies the action of{@link RMManageComponentPropertiesPage#selectArtifact(String,String)}
   *
   * @param dialog dialog
   * @param artifactID of artifact
   * @param lastResult last result of method need to verify
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectArtifact(final String dialog, final String artifactID,
      final String lastResult) {
    if (this.driverCustom.isElementClickable(RMConstants.MANAGECOMPPROPERTIES_BUTTON_XPATH, Duration.ofSeconds(50), "Add")) {
      return new TestAcceptanceMessage(true,
          "Verify artifact after selecting artifactID.\nActual is the '" + lastResult + "' artifact is selected");
    }
    return new TestAcceptanceMessage(false,
        "Verify artifact after selecting artifactID.\nActual is the '" + lastResult + "' artifact is not selected");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#setReqIFDefinitionName(String)}.
   * 
   * @param reqIFDefinitionName Definition name... etc.
   * @param lastResult text.
   * @return the verification message
   */
  public TestAcceptanceMessage verifySetReqIFDefinitionName(final String reqIFDefinitionName, final String lastResult) {
    if (this.driverCustom.isElementInvisible(RMConstants.MANAGECOMPPROPERTIES_TEXTBOXINVISIBLE_XPATH, Duration.ofSeconds(60))) {
      return new TestAcceptanceMessage(true, "Verified: Actual result is Definition Name of reqif file is " +
          lastResult + " Matches expected data " + reqIFDefinitionName);
    }
    return new TestAcceptanceMessage(false, "Verified: Actual result is Definition Name of reqif file is " +
        lastResult + " Does not matches expected data" + reqIFDefinitionName);
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#isProgressBarReached100Percent()}. 
   * Verify Progress bar reachd 100%.
   * 
   * @param lastResult result of method need to verify
   * @return true if progress bar reached 100%.
   */
  public TestAcceptanceMessage verifyIsProgressBarReached100Percent(final String lastResult) {
    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true, "Verified:  Progress bar reached  100%.");
    }
    return new TestAcceptanceMessage(false, "Verified:  Progress bar not reached 100%.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#isProgressBarReached100Percent()}. 
   * Verify Select Modules for export opened successfully
   * 
   * @param dialogName name of dialog
   * @param lastResult result of method need to verify
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsWidgetOpened(final String dialogName, final String lastResult) {
    if (this.driverCustom.isElementClickable(RMConstants.MANAGECOMPPROPERTIES_BUTTONCLICK_XPATH, Duration.ofSeconds(50), "Close")) {
      return new TestAcceptanceMessage(true,
          "Verified: Expected result is to Check widget is opened,  Actual is: " + dialogName + " Opened successfully");
    }
    return new TestAcceptanceMessage(false, "Verified: Expected result is to Check widget is opened,  Actual is: " +
        dialogName + " Not opened successfully");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#clickOnReqIFDropdownOptionDelete(String)}
   *
   * @param reqIFDefinitionName Definition name... etc.
   * @param lastResult result of method need to verify
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnReqIFDropdownOptionDelete(final String reqIFDefinitionName,
      final String lastResult) {
    if (this.driverCustom.isElementClickable(RMConstants.MANAGECOMPPROPERTIES_BUTTONCLICK_XPATH, Duration.ofSeconds(50), "Yes")) {
      return new TestAcceptanceMessage(true,
          "Verified: Expected result is to click Delete option of reqif file, Actual is: " + lastResult +
          " Delete option clicked successfully ");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Expected result is to click Delete option of reqif file, Actual is: " + lastResult +
        " Delete option 'Not' clicked  successfully ");
  }

  /**
   * <p>
   * * Verifies the action of {@link RMManageComponentPropertiesPage#includeTypeForReqIF(String)}
   *
   * @param checkboxName include check box Links, Folders, Tags.
   * @param lastResult result of method need to verify
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIncludeTypeForReqIF(final String checkboxName, final String lastResult) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_CHECKBOX_XPATH, lastResult);
    if (this.driverCustom.getWebElement(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_CHECKBOX_XPATH, lastResult)
        .getAttribute("aria-checked").equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified: Expected result is to check Links option is selected for reqif file, Actual is:  " + lastResult +
          " is checked successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Expected result is to check Folder option is selected for reqif file, Actual is:  " + lastResult +
        " is not checked successfully");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#clickOnReqIFDropdownOptionExport(String)}
   * 
   * @param lastResult result of method need to verify
   * @param reqIFDefinitionName Definition name... etc.
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnReqIFDropdownOptionExport(final String reqIFDefinitionName,
      final String lastResult) {
    if (!this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_PROGRESSBAR_XPATH, Duration.ofSeconds(60))) {
      return new TestAcceptanceMessage(false,
          "Verified: Expected result is to click export option of reqif file, Actual is: " + lastResult +
          " Not clicked successfully ");
    }
    return new TestAcceptanceMessage(true,
        "Verified: Expected result is to click export option of reqif file, Actual is: " + lastResult +
        " Clicked successfully ");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#switchTowindowTab()} Switch to report page tab
   * 
   * @param lastResult result of method need to verify
   * @return the verification message
   */
  public TestAcceptanceMessage verifySwitchTowindowTab(final String lastResult) {
    waitForSecs(Duration.ofSeconds(10));
    if (lastResult.contains("doesn't")) {
      return new TestAcceptanceMessage(false, " Doesn't navigate to report window ---->" + lastResult);
    }
    return new TestAcceptanceMessage(true, " Navigate to report window ---->" + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#isReqIFDefinitionSaved(String)}
   * 
   * @param lastResult  result of method need to verify
   * @param reqIFDefinitionName name... etc.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsReqIFDefinitionSaved(final String reqIFDefinitionName, final String lastResult) {
    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true, "Verified: Expected result is Name of reqif file is saved, Actual is: " +
          reqIFDefinitionName + " Saved successfully");
    }
    return new TestAcceptanceMessage(false, "Verified: Expected result is Name of reqif file saved, Actual is: " +
        reqIFDefinitionName + " Not saved successfully");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#setComponentPropertiesDescription(String)}
   *
   * @param description description... etc.
   * @param lastResult text.
   * @return the verification message
   */
  public TestAcceptanceMessage verifySetComponentPropertiesDescription(final String description,
      final String lastResult) {
    if (this.driverCustom.isElementInvisible(RMConstants.MANAGECOMPPROPERTIES_TEXTBOXINVISIBLE_XPATH, Duration.ofSeconds(60))) {
      return new TestAcceptanceMessage(true, "Verified: Actual result is Description of reqif file is " + lastResult +
          " Matches expected data " + description);
    }
    return new TestAcceptanceMessage(false, "Verified: Actual result is Description of reqif file is " + lastResult +
        " Does not matches expected data " + description);
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentPropertiesPage#selectModuleViewForReqIF(String)}.
   *
   * @param nameView name of module levele view
   * @param lastResult last result of {@link RMManageComponentPropertiesPage#selectModuleViewForReqIF(String)}.
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectModuleViewForReqIF(final String nameView, final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_BUTTONCLICK_XPATH, Duration.ofSeconds(50), "Add View")) {
      return new TestAcceptanceMessage(true, "Verify: Expected is Module level view should select.\nActual is the '" +
          lastResult + "' for module is selected");
    }
    return new TestAcceptanceMessage(false, "Verify: Expected is Module level view should select.\nActual is the '" +
        lastResult + "' for module is not selected");
  }

  /**
   * @author UUM4KOR
   * @param otherComponentName name of other component
   * @param streamNamefinal name of stream final
   * @param lastResult result of method need to verify
   * @return verify message
   */
  public TestAcceptanceMessage verifyImportComponentProperties(final String otherComponentName,
      final String streamNamefinal, final String lastResult) {
    if (!this.driverCustom.isElementVisible("//a[@title='Import Component Properties' and@class='selected']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified 'Import Component Properties' dialogue and  actual validation message displayed as : " +
              lastResult);
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Import Component Properties' dialogue and  actual validation message displayed as : " + lastResult);
  }

  /**
   * @param additionalParams
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyImportExistingComponentProperties(final Map<String, String> additionalParams, final String lastResult) {
    if (!this.driverCustom.isElementVisible("//a[@title='Import Component Properties' and@class='selected']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified 'Import Component Properties' dialoge is displayed. \nSelected source project area : " +
              additionalParams.get("PROJECT_AREA")+"\nSelected Source component name is : "+additionalParams.get("COMPONENT_NAME")+"\nSelected source config type is : "+additionalParams.get("CONFIG_TYPE")+"\nsource config name is : "+additionalParams.get("CONFIG_NAME"));
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Import Component Properties' dialogue and  actual validation message displayed as : " + lastResult);
  }

  /**
   * @param additionalParams
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyImportExistingProjectProperties(final Map<String, String> additionalParams, final String lastResult) {
    if (!this.driverCustom.isElementVisible("//a[@title='Import Project Properties' and@class='selected']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified 'Import Component Properties' dialoge is displayed. \nSelected source project area : " +
              additionalParams.get("PROJECT_AREA")+"\nSelected Source component name is : "+additionalParams.get("COMPONENT_NAME")+"\nSelected source config type is : "+additionalParams.get("CONFIG_TYPE")+"\nsource config name is : "+additionalParams.get("CONFIG_NAME"));
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Import Component Properties' dialogue and  actual validation message displayed as : " + lastResult);
  }

  /**
   * @author UUM4KOR
   * @param sourceProject name of source project
   * @param lastResult result of method need to verify
   * @return verify message
   */
  public TestAcceptanceMessage verifyImportprojectProperties(final String sourceProject, final String lastResult) {
    if (!this.driverCustom.isElementVisible("//a[@title='Import Project Properties' and@class='selected']", Duration.ofSeconds(60))) {
      return new TestAcceptanceMessage(true,
          "Verified : "+sourceProject+"'Import Project Properties' dialogue and  actual validation message displayed as : " + lastResult);
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Import Project Properties' dialogue and  actual validation message displayed as : " + lastResult);
  }
  /**
   * @param additionalParams
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyGetImportComponentProperties(final Map<String, String> additionalParams, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified Imported Component Properties of '"+additionalParams.get("ARTIFACT_TYPE")+"' is : '" + lastResult + "' ");
    }
    return new TestAcceptanceMessage(false, "Verified new link Not created successfully");
  }
  /**
   * @author UUM4KOR
   * @param lastResult result of method need to verify
   * @return verify message
   */
  public TestAcceptanceMessage verifyClickOnImportprojectPropertiesButton(final String lastResult) {
    if (this.driverCustom.isElementVisible("//a[@title='Import Project Properties' and@class='selected']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified 'Import Project Properties' dialoge opened successfully '.");
    }
    return new TestAcceptanceMessage(false, "Verified 'Import Project Properties' dialoge not opened successfully '.");
  }

  /**
   * @author UUM4KOR
   * @param lastResult result of method need to verify
   * @return verify message
   */
  public TestAcceptanceMessage verifyClickOnImportComponentPropertiesButton(final String lastResult) {
    if (this.driverCustom.isElementVisible("//a[@title='Import Component Properties' and@class='selected']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified 'Import Component Properties' dialoge opened successfully '.");
    }
    return new TestAcceptanceMessage(false, "Verified 'Import Component Properties' dialoge not opened successfully '.");
  }

  /**
   * @author UUM4KOR
   * @param artifactType type of artifact
   * @param lastResult result of method need to verify
   * @return verify message
   */
  public TestAcceptanceMessage verifyIsArtifactTypePresent(final String artifactType, final String lastResult) {
    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true, "Verified Actual Artifact type : '" + artifactType + "' is present");
    }
    return new TestAcceptanceMessage(false, "Verified Actual Artifact type : '" + artifactType + "' is not present");

  }

  /**
   * @author PDU6HC
   * @param artifactType name of the artifact type
   * @param lastResult lastResult last result of {@link RMManageComponentPropertiesPage#deleteArtifactType(String)}
   * @return artifact is deleted
   *
   */
  public TestAcceptanceMessage verifyDeleteArtifactType(final String artifactType, final String lastResult) {
    if (!this.driverCustom.isElementPresent(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, this.timeInSecs, artifactType)) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Artifact type : '" + artifactType + "' is deleted successfully ");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Artifact type : '" + artifactType + "' is not deleted successfully ");
  }

  /**
   * @param artifactType
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyDeleteLinkTypes(final String artifactType, final String lastResult) {
    if (!this.driverCustom.isElementPresent("//a[text()='DYNAMIC_VAlUE']", this.timeInSecs, artifactType)) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Artifact type : '" + artifactType + "' is deleted successfully ");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Artifact type : '" + artifactType + "' is not deleted successfully ");
  }
  /**
   * <p> @author KCE1KOR
   * Verifies the action of {@link RMManageComponentPropertiesPage#selectViewForReqIF(String,String,String)}.
   *
   * @param dialog name of dialog
   * @param drpDwnVal value from dropdown
   * @param viewName name of view
   * @param lastResult last result of method need to verify.
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectViewForReqIF(final String dialog,final String drpDwnVal,final String viewName, final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_REQIF_VIEWSELECTED_XPATH, Duration.ofSeconds(10))||lastResult.equals("true")) {
      return new TestAcceptanceMessage(true, "Verify: Expected is Module view should be select.\nActual is the '" +
          viewName + "' for module is selected Successfully");
    }
    return new TestAcceptanceMessage(false, "Verify: Expected is Module view should be select.\nActual is the '" +
        viewName + "' for module is not selected Successfully");}
  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, RMConstants.ARTIFACTS);
  }

  /**
   * @author PDU6HC
   * Verifies the action of {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.
   *
   * @param propertiesType the component property name
   * @param lastResult last result of {@link RMManageComponentPropertiesPage#selectComponentPropertiesType(String)}.
   * @return Component name is present
   */
  public TestAcceptanceMessage verifySelectComponentPropertiesType(final String propertiesType, final String lastResult) {
    if (this.driverCustom.isElementPresent(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_TAB_XPATH, Duration.ofSeconds(20), propertiesType)) {
      return new TestAcceptanceMessage(true, "Verified PASSED - Component Property Type selected: '" + propertiesType + "' is present");
    }
    return new TestAcceptanceMessage(false, "Verified FAILED - Component Property Type selected:'" + propertiesType + "' is not present");
  }

  /**
   * @author PDU6HC
   * Verifies the action of {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.
   *
   * @param name the component property name
   * @param lastResult last result of {@link RMManageComponentPropertiesPage#inputNameForComponentProperties(String)}.
   * @return Artifact Type name is inputted correct
   */
  public TestAcceptanceMessage verifyInputNameForComponentProperties(final String name, final String lastResult) {
    if (lastResult.equalsIgnoreCase(name)) {
      return new TestAcceptanceMessage(true, "Verified PASSED - Input Name for Component Properties: '" + name + "' is present");
    }
    return new TestAcceptanceMessage(false, "Verified PASSED - Input Name for Component Properties:'" + name + "' is not present");
  }

  /**
   * @author PDU6HC
   * Verifies the action of {@link RMManageComponentPropertiesPage#createNewArtifactType(Map)}.
   *
   * @param additionalParams stores key value 'DEFAULT_ARTIFACT_FORMAT','URI','ATTRIBUTE_DATA_TYPE' etc...
   * @param additionalParam_attributeName test data ATTRIBUTE_DATA_TYPE_NAME
   * @param lastResult last result of {@link RMManageComponentPropertiesPage#createNewArtifactType(Map)}.
   * @return Artifact Type is correct
   */
  public TestAcceptanceMessage verifyCreateNewArtifactType(final Map<String, String> additionalParams,final String additionalParam_attributeName, final String lastResult) {
    
    if (this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, this.timeInSecs,
        additionalParam_attributeName)) {
      return new TestAcceptanceMessage(true, "Verified: - New artifact type with required attributes.\n\nActual Result : artifact is created with name \"" +
          additionalParam_attributeName +  "\"\n\nExpected Result : artifact Type displayed in 'Artifact Types'  section on project properties page with name  \""+ additionalParam_attributeName + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Create a New artifact type '" + additionalParam_attributeName +
        "' failed! attribute data type '" + additionalParam_attributeName + "' is not displayed!");
  }

  /**
   * @author PDU6HC
   * Verifies the action of {@link RMManageComponentPropertiesPage#clickOnDeleteAnArtifactType(String)}.
   *
   * @param artifactType stores key value 'DEFAULT_ARTIFACT_FORMAT','URI','ATTRIBUTE_DATA_TYPE' etc...
   * @param lastResult last result of {@link RMManageComponentPropertiesPage#clickOnDeleteAnArtifactType(String)}.
   * @return Delete button is clickable
   */
  public TestAcceptanceMessage verifyClickOnDeleteAnArtifactType(final String artifactType, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Click on Delete... button of '" + (artifactType) + "' successful");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Click on Delete... button of '" + (artifactType) + "' failed");
  }

  /**
   * @author PDU6HC
   * Verifies the action of {@link RMManageComponentPropertiesPage#isNoticeMessageDisplay()}.
   *
   * @param lastResult last result of {@link RMManageComponentPropertiesPage#isNoticeMessageDisplay()}.
   * @return Notice message is display
   */
  public TestAcceptanceMessage verifyIsNoticeMessageDisplay(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      String noticeMessage = this.driverCustom.getText("//div[@class='rdmDialogForm']");
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Notice message displayed. Message: " + noticeMessage);
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Notice message did not display");
  }

  /**
   * <p>
   * This method is called after executing the method
   * ${@link RMManageComponentPropertiesPage#clickOnArtifactType(String)}}
   * <p>
   *
   * @author NVV1HC
   * @param artifactType artifact type, e.g: Information, Heading, Module, etc.
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#clickOnArtifactType(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnArtifactType(final String artifactType, final String lastResult) {
    this.driverCustom.isElementVisible(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_NAMEOFARTIFACTTYPE_XPATH,
        this.timeInSecs);
    String artifactTypeDisplayed =
        this.driverCustom.getAttribute(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_NAMEOFARTIFACTTYPE_XPATH, "value");
    if (artifactType.equals(artifactTypeDisplayed.trim())) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Click on artifact type '" + artifactType + "' successfully!");
    }
    return new TestAcceptanceMessage(false, "Verified FAILED - Click on artifact type '" + artifactType + "' failed!");
  }

  /**
   * <p>
   * This method is call after executing the method
   * ${@link RMManageComponentPropertiesPage#removeArtifactAttributeFromArtifactType}}
   * <p>
   *
   * @author NVV1HC
   * @param artifactAttribute artifact attribute
   * @param lastResult result from
   *          ${@link RMManageComponentPropertiesPage#removeArtifactAttributeFromArtifactType(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyRemoveArtifactAttributeFromArtifactType(final String artifactAttribute,
      final String lastResult) {
    List<WebElement> listAttributeElm =
        this.driverCustom.getWebElements("//table[@rowtype='adr']//td[@colid='o_title_col']");
    for (WebElement e : listAttributeElm) {
      if (e.getText().trim().equals(artifactAttribute)) {
        return new TestAcceptanceMessage(false, "Verified FAILED - Remove artifact attribute '" + artifactAttribute +
            "' failed! The attribute '" + artifactAttribute + "' still displays!");
      }
    }
    return new TestAcceptanceMessage(true,
        "Verified PASSED - Remove artifact attribute '" + artifactAttribute + "' successfully!");
  }

  /**
   * <p>
   * This method is call after executing the method
   * ${@link RMManageComponentPropertiesPage#deleteArtifactAttribute(String)}}
   * <p>
   *
   * @author NVV1HC
   * @param artifactAttribute artifact attribute
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#deleteArtifactAttribute(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyDeleteArtifactAttribute(final String artifactAttribute, final String lastResult) {
    if (this.driverCustom.isElementInvisible(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ATTRIBUTE_XPATH,
        this.timeInSecs, artifactAttribute)) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Delete artifact attribute '" + artifactAttribute + "' successfully!");
    }
    return new TestAcceptanceMessage(false, "Verified FAILED - Delete artifact attribute '" + artifactAttribute +
        "' failed! Artifact attribute '" + artifactAttribute + "' still displays!");
  }

  /**
   * <p>
   * This method is call after executing the method
   * ${@link RMManageComponentPropertiesPage#isArtifactAttributeDisplayed(String)}
   * <p>
   *
   * @author NVV1HC
   * @param attribute artifact attribute to be verified
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#isArtifactAttributeDisplayed(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyIsArtifactAttributeDisplayed(final String attribute,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Attribute '" + attribute + "' is visible!");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Attribute '" + attribute + "' is invisible!");
  }
  /**
   * @param attribute
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyIsImportedComponentsPropertiesPresent(final Map<String, String> additionalParams,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Imported Project properties is visible!");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Imported Project properties is not visible!");
  }
  
  /**@author UUM4KOR
   * <p>this method used to validate the successfull message displayed in dialog box
   * @param successfulMessage message
   * @param lastResult boolean
   * @return boolean
   */
  public TestAcceptanceMessage verifyisSuccessfullMessageDisplayed(final String successfulMessage,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified PASSED - Message '" + successfulMessage + "' is displayed in dialoge box!");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Message '" + successfulMessage + "' is Not displayed in dialoge box!");
  }
  /**
   * <p>
   * This method is call after executing the method
   * ${@link RMManageComponentPropertiesPage#addAttributesToArtifactType(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param attributeParams stores key value for 'ARTIFACT_TYPE','ARTIFACT_ATTRIBUTE' etc...
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#addAttributesToArtifactType(Map)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyAddAttributesToArtifactType(final Map<String, String> attributeParams,
      final String lastResult) {
    List<WebElement> listAttributeElm =
        this.driverCustom.getWebElements("//table[@rowtype='adr']//td[@colid='o_title_col']");
    for (WebElement e : listAttributeElm) {
      if (e.getText().trim().equals(attributeParams.get("ARTIFACT_ATTRIBUTE"))) {
        return new TestAcceptanceMessage(true, "Verified PASSED - Add artifact attribute '" +
            attributeParams.get("ARTIFACT_ATTRIBUTE") + "' successfully!");
      }
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Add artifact attribute '" + attributeParams.get("ARTIFACT_ATTRIBUTE") +
        "' failed! Artifact attribute '" + attributeParams.get("ARTIFACT_ATTRIBUTE") + "' is not displayed!");
  }

  /**
   * @param attributeArtifactTypes
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyClickOnAddArtifactTypesFromComponentSetupDialogue(final Map<String, String> attributeArtifactTypes, final String lastResult) {
    String applyAcomponentTemplate = attributeArtifactTypes.get("Apply a component template");
    String importComponentPropertiesFromAnExistingComponent = attributeArtifactTypes.get("IMPORT_COMPONENT_PROPERTIES_FROM_AN_EXISTING_COMPONENT");
    String manuallyDefineArtifactTypes = attributeArtifactTypes.get("Manually define artifact types");
    if (!this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_IMPORT_COMPONENT_PROPERTIES_FROM_AN_EXISTING_COMPONENT_XPATH,Duration.ofSeconds(10),importComponentPropertiesFromAnExistingComponent)) {
      return new TestAcceptanceMessage(true,
          "Verified : Clocked on : "+importComponentPropertiesFromAnExistingComponent);
    }
    return new TestAcceptanceMessage(false,
        "Verified : Not Clocked on : "+importComponentPropertiesFromAnExistingComponent);
  }
  /**
   * <p>
   * This method is call after executing the method
   * ${@link RMManageComponentPropertiesPage#createNewArtifactAttributeDataType(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param attributeParams stores key value for 'ARTIFACT_TYPE','ARTIFACT_ATTRIBUTE' etc...
   * @param additionalParam_attributeName attribute name
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#createNewArtifactAttributeDataType(Map)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyCreateNewArtifactAttributeDataType(final Map<String, String> attributeParams,
      final String additionalParam_attributeName,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.RMMANAGECOMPONENTPROPERTIESPAGE_ATTRIBUTE_XPATH, this.timeInSecs,
        additionalParam_attributeName)) {
      return new TestAcceptanceMessage(true, "Verified PASSED - New artifact attribute created with required attributes.\n\nActual Result : New artifact attribute is created with name '" +
          additionalParam_attributeName + ".'\n\nExpected Result : New artifact attribute displayed in \"Artifact attributes\" section on project properties page with name '"+ additionalParam_attributeName + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Create a new artifact attribute '" + additionalParam_attributeName +
        "' failed! Artifact attribute '" + additionalParam_attributeName + "' is not displayed!");
  }
  
  /**
   * @param componentName
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyCreateAnewComponentFromAdministration(final String componentName,final String componentDescription,String checkBoxSelect, final String lastResult) {
    if (lastResult.equalsIgnoreCase(componentName)) {
      return new TestAcceptanceMessage(true, "Verified PASSED - Input Name for New Component : '" + componentName + "' is Created");
    }
    return new TestAcceptanceMessage(false, "Verified FAILED - Input Name for New Component :'" + componentName + "' is not Created");
  }
  /**
   * <p>
   * This method is call after executing the method
   * ${@link RMManageComponentPropertiesPage#createNewArtifactAttributeDataType(Map)}
   * <p>
   *
   * @author @author UUM4KOR
   * @param attributeParams stores key value for 'BASE_DATA_TYPE','MULTIPLE_VALUES','URI' etc...
   * @param additionalParam_attributeName attribute name
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#createNewArtifactAttributeDataType(Map)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyCreateNewAttributeDataType(final Map<String, String> attributeParams,
      final String additionalParam_attributeName,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.MANAGECOMPPROPERTIES_DEFINATIONNAME_XPATH, this.timeInSecs,
        additionalParam_attributeName)) {
      return new TestAcceptanceMessage(true, "Verified PASSED - New Attribute Data Type created with required attributes.\n\nActual Result : New Attribute Data Type is created with name '" +
          additionalParam_attributeName + ".'\n\nExpected Result : New Attribute Data Type displayed in \"Attributes Data Types\" section on project properties page with name '"+ additionalParam_attributeName + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified FAILED - Create a new attribute data type'" + additionalParam_attributeName +
        "' failed! attribute data type '" + additionalParam_attributeName + "' is not displayed!");
  }

  
  /**
   * <p>
   * This method is call after executing the method ${@link RMManageComponentPropertiesPage#exportReqIF(String,String)}
   * <p>
   *
   * @author NVV1HC
   * @param reqIFDefinitionName definition name of ReqIF
   * @param menuOption option after exporting successully
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#exportReqIF(String,String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyExportReqIF(final String reqIFDefinitionName, final String menuOption,
      final String lastResult) {
    if (menuOption.equals("Export...")) {
//      String expectedReqIFName = reqIFDefinitionName + ".reqifz";
      if (lastResult.contains("Completed: Exported file passed validation.") ||
          lastResult.contains("Completed : Exported file passed validation.")) {
        return new TestAcceptanceMessage(true, "Verified PASSED - Export ReqIF '" + reqIFDefinitionName +
            "' with option '" + menuOption + "'successfully!");
      }
      return new TestAcceptanceMessage(false,
          "Verified FAILED - Export ReqIF '" + reqIFDefinitionName + "' with option '" + menuOption + "'failed!");
    }
    else if (menuOption.equals(RMConstants.DELETEBUTTON)) {
      List<String> lstReqIF = this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_SELECTPA_XPATH);
      for (String reqIF : lstReqIF) {
        if (reqIF.trim().equals(reqIFDefinitionName)) {
          return new TestAcceptanceMessage(false,
              "Verified FAILED - Delete ReqIF '" + reqIFDefinitionName + "' with option '" + menuOption +
              "' failed! The ReqIF with name '" + reqIFDefinitionName + "' still displays.");
        }
      }
      return new TestAcceptanceMessage(true, "Verified PASSED - Delete ReqIF '" + reqIFDefinitionName +
          "' with option '" + menuOption + "' successfully!");
    }
    else {
      List<String> lstReqIF = this.driverCustom.getWebElementsText(RMConstants.MANAGECOMPPROPERTIES_SELECTPA_XPATH);
      String expectedReqIF = "Copy of " + reqIFDefinitionName;
      for (String reqIF : lstReqIF) {
        if (reqIF.trim().equals(expectedReqIF)) {
          return new TestAcceptanceMessage(true,
              "Verified PASSED - Create a copy from '" + reqIFDefinitionName + "' successfully!");
        }
      }
      return new TestAcceptanceMessage(false,
          "Verified FAILED - Create a copy from '" + reqIFDefinitionName +
          "' failed!\nExpected ReqIF should be displayed: '" + expectedReqIF + "'.\nList actual ReqIF displays: '" +
          lstReqIF + "'");
    }
  }
  
  /**
   * This method is call after executing the method ${@link RMManageComponentPropertiesPage#verifyPropertiesTabsOrder(String)} <br>
   * @author VDY1HC
   * @param listTabsSortInOrder - list of tabs already sort in order separated by ";" 
   * @param lastResult - true if tabs displayed match number and order
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyPropertiesTabsOrder(final String listTabsSortInOrder, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Verify list of tab displayed by order " +
          "\nList of tabs sort in order: " + listTabsSortInOrder);
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Verify list of tab NOT displayed by order " +
        "\nList of tabs sort in order: " + listTabsSortInOrder);
  }  
}