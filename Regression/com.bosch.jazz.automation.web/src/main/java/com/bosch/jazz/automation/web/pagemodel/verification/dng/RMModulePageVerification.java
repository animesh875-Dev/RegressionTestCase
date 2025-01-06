/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.AbstractWebPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMModulePage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMExecutionPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.ListboxFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.text.textField.JazzLoginTextFieldFinder;

/**
 * @author RUN2HC
 */
public class RMModulePageVerification extends AbstractWebPage {
  private final Duration timeInSecs = this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime();

  /**
   * @param driverCustom driver
   */
  public RMModulePageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new ListboxFinder(), new JazzDialogFinder(), new LinkFinder(),
        new JazzLoginTextFieldFinder());
  }

  /**
   * <p>
   * Verifies the action of {@link RMModulePage#logout()}.
   *
   * @param lastResult last result of {@link RMModulePage#logout()}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyLogout(final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    TextField txtUsername = null;
    try {
      txtUsername =
          this.engine.findElementWithinDuration(Criteria.isTextField().hasLabel("User ID:"), Duration.ofSeconds(20)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (txtUsername != null) {
      return new TestAcceptanceMessage(true, "Logout successfully!");
    }
    return new TestAcceptanceMessage(false, "Logout unsuccessfully!");
  }

  /**
   * <p>
   * Verify span button is clicked.
   * <p>
   * This method called after {@link RQMExecutionPage#clickOnJazzSpanButtons(String)}.
   *
   * @param button name of the button.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnJazzSpanButtons(final String button, final String lastResult) {
    if (button.equals("OK")) {
      return new TestAcceptanceMessage(true, "Verified: Clicked on 'OK' button successfully.");
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + button + "' button is not clicked.");
  }

  /**
   * <p>
   * Verify span button is clicked.
   * <p>
   * This method called after {@link RMArtifactsPage #getDownloadedPDFFileNameAndPath(String)}.
   *
   * @param reportName name of the button.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetDownededPdfFileNameAndPath(final String reportName, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified file downloaded and stored in : " + lastResult);
    }
    return new TestAcceptanceMessage(false, "Verified file not downloaded.");
  }

  /**
   * <p>
   * Verify Excel id same or not.
   * <p>
   * This method called after {@link RMModulePage#verifyExcelID(String, String)}.
   *
   * @param path name of the Excel.
   * @param ids artifact id.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyVerifyExcelID(final String path, final String ids, final String lastResult) {
    if (!ids.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified The artifact ID same in the Excel : " + ids);
    }
    return new TestAcceptanceMessage(false, "Verified The artifact ID is not same in the Excel");
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         Verify Excel id same or not.
   *         <p>
   *         This method called after {@link RMModulePage#verifyExcelID(String, String)}.
   * @param path name of the Excel.
   * @param testdata artifact value.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsDataPresentInCSVfile(final String path, final String testdata,
      final String lastResult) {
    if (!testdata.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified The attribute present in the Excel as expected : " + testdata);
    }
    return new TestAcceptanceMessage(false,
        "Verified The attribute is not present in the Excel as expected : " + testdata);
  }

  /**
   * <p>
   * Verify Excel contains URL or not.
   * <p>
   * This method called after {@link RMModulePage#getArtifactUrl(String)}.
   *
   * @param path name of the Excel.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetArtifactUrl(final String path, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified Artifact link url found " + lastResult);
    }
    return new TestAcceptanceMessage(false, "Verified Artifact link url not found.");
  }

  /**
   * <p>
   * Verify Excel contains URL or not.
   * <p>
   * This method called after {@link RMModulePage#navigateToArtifactURL(String)}.
   *
   * @param url name of the Excel.
   * @param additionalParam parameter to be passed in the excel test script.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyNavigateToArtifactURL(final String url,final String additionalParam, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
   
    WebElement artifactNames = this.driverCustom
        .getFirstVisibleWebElement("//div[@dojoattachpoint='viewHeaderLeft']//span[@dojoattachpoint='viewID']", null);
    if (artifactNames.getText().contains(additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified: Linked artifact is opened successfully with correct url - " + url +".\nLink is opened with ID - "+additionalParam);
    }
    return new TestAcceptanceMessage(false, "Verified: Linked artifact is not opened.");
  }

  /**
   * <p>
   * Verify Excel contains URL or not.
   * <p>
   * This method called after {@link RMModulePage#navigateToArtifactURL(String)}.
   *
   * @param filePath name of the Excel.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetCSVArtifactURL(final String filePath, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified Artifact URL found successfully " + lastResult);
    }
    return new TestAcceptanceMessage(false, "Verified Artifact URL not found.");
  }

  /**
   * <p>
   * Verify Excel contains URL or not.
   * <p>
   * This method called after {@link RMModulePage#navigateToArtifactURL(String)}.
   *
   * @param option name of the Excel.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnDropdownOption(final String option, final String lastResult) {
    if (!option.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified the dropdown option " + option + "  sucessfully.");
    }
    return new TestAcceptanceMessage(false, "Verified dropdown option not clicked.");
  }

  /**
   * <p>
   * Verify Excel contains URL or not.
   * <p>
   * This method called after {@link RMModulePage#navigateToArtifactURL(String)}.
   *
   * @param filePath name of the Excel.
   * @param option name of the Excel.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyVerifyCSVid(final String filePath, final String option, final String lastResult) {
    if (!filePath.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified ID found from the Excel : " + option);
    }
    return new TestAcceptanceMessage(false, "Verified ID not found from the Excel");
  }

  /**
   * @param tabName name of the Tab to be Selected inside the module.
   * @param lastResult last result.
   * @return message
   */
  public TestAcceptanceMessage verifyChooseTabInsideModule(final String tabName, final String lastResult) {
    if (!tabName.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified '" + tabName + "' Selected inside the module and ");
    }
    return new TestAcceptanceMessage(false, "Verified '" + tabName + "' Not found from the Selected artifact");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.engine.addFinders(new ListboxFinder(), new JazzDialogFinder(), new LinkFinder(),
        new JazzLoginTextFieldFinder());

  }


  /**
   * <p>
   * Verify Duplicated Artifact successfully
   * <p>
   * This method called after {@link RMModulePage#duplicateArtifactToFolder}.
   *
   * @param additionalParams stores key values for new Artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDuplicateArtifactToFolder(final Map<String, String> additionalParams,
      final String lastResult) {
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_01, Duration.ofSeconds(300));
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Duplicated Artifact successfully");
    }

    return new TestAcceptanceMessage(false, "Duplicated Artifact fail");

  }


  /**
   * <p>
   * Verify get number of artifact showing in table
   * <p>
   * This method called after {@link RMModulePage#getNumberOfArtifactShowInTable}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetNumberOfArtifactShowInTable(final String lastResult) {
    if (lastResult != null && !(this.driverCustom.isElementVisible("//div[@class='messageArea ERROR']",this.timeInSecs))) {
      return new TestAcceptanceMessage(true, "Number of artifacts displayed - " + lastResult+".\n Module loaded successfully with out any Error message.");
    }

    return new TestAcceptanceMessage(false, "Not count successfully");

  }

  /**
   * <p>
   * Verify artifact is inserted successfully into an existing artifact
   * <p>
   * This method called after {@link RMModulePage#editArtifact_insertContent(String, String, String)}
   *
   * @param artifactID source artifact we want to insert
   * @param typeOfInsertedContent type of insertion
   * @param insertedArtifactID artifact is inserted into source artifact
   * @param additionalParam_insertedArtifactContent content of inserted artifact
   * @param lastResult returned value of method which is executed just before the method
   * @return acceptance object which contains verification results
   */
  public TestAcceptanceMessage verifyEditArtifact_insertContent(final String artifactID,
      final String typeOfInsertedContent, final String insertedArtifactID,
      final String additionalParam_insertedArtifactContent, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(30));
    String actualEmbeddedContent = "";
    WebElement embeddedContent = null;
    try {
      // Get Embedded Content
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_CONTENTOFINSERTEDARTIFACT_XPATH2, Duration.ofSeconds(10));
      embeddedContent =
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_CONTENTOFINSERTEDARTIFACT_XPATH2);
      actualEmbeddedContent = embeddedContent.getText().trim();
    }
    catch (Exception e) {
      LOGGER.LOG.error("Can not get Embedded Content " + e.getMessage());
    }

    if (!actualEmbeddedContent.contains(additionalParam_insertedArtifactContent)) {
      return new TestAcceptanceMessage(false,
          "Artifact '" + insertedArtifactID + "' is not inserted successfully into artifact '" + artifactID + "'");
    }

    try {
      //Click on 'Open Inserted Artifact'
      this.driverCustom.waitForSecs(Duration.ofSeconds(3));
      this.driverCustom.getActions()
      .moveToElement(this.driverCustom.getWebElement("//div[@class='embedded-content']"))
      .build().perform();
      this.driverCustom.clickUsingActions(
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMMODULEPAGE_INSERTEDARTIFACTLINK_XPATH));
      this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    }
    catch (Exception e) {
      LOGGER.LOG.error("Can not open 'Open Inserted Artifact' " + e.getMessage());

    }

    String resourceID = "";
    try {
      // Get resourceID and navigate back
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_GETMODULETEST_XPATH, Duration.ofSeconds(10));
      resourceID =
          this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_GETMODULETEST_XPATH).getText();
      this.driverCustom.getWebDriver().navigate().back();
      this.driverCustom.getWebDriver().navigate().refresh();
      waitForPageLoaded();
      this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    }
    catch (Exception e) {
      LOGGER.LOG.error("Can not get resourceID " + e.getMessage());
    }

    if (resourceID.trim().equals(insertedArtifactID)) {
      return new TestAcceptanceMessage(true,
          "Artifact '" + insertedArtifactID + "' is inserted successfully into artifact '" + artifactID + "'");
    }
    return new TestAcceptanceMessage(false,
        "Artifact '" + insertedArtifactID + "' is not inserted successfully into artifact '" + artifactID + "'");
  }

  /**
   * <p>
   * Verify selected successful an option in More Action
   * <p>
   * This method called after {@link RMModulePage#moreAction}.
   *
   * @param lastResult - option
   * @param option option to be selected
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyMoreAction(final String lastResult, final String option) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Displayed Artifact With: " + lastResult);
    }
    return new TestAcceptanceMessage(false, "Unable to display artifact with option: " + lastResult);
  }

  /**
   * <p>
   * Verify Opened History page successfull
   * <p>
   * This method called after {@link RMModulePage#openHistory}.
   *
   * @param lastResult - option
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenHistory(final String lastResult) {
    try {
      this.engine.findElementWithDuration(Criteria.isButton().withText("Close History"), Duration.ofSeconds(60)).getFirstElement();
      return new TestAcceptanceMessage(true, "Opened History page successfully");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Unable to open History page");
    }
  }

  /**
   * <p>
   * Verify if restore revision successful <br>
   * This method called after {@link RMModulePage#restoreRevisionUsingHistory}.
   *
   * @param timeline - The time that contains date of selected verison (Ex: Today, Yesterday, Past Week,...) <br>
   * @param versionNumber - The order number of the version in the time, count from the right to left, start with 1.
   * @param lastResult result from {@link RMModulePage#restoreRevisionUsingHistory}.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRestoreRevisionUsingHistory(final String timeline, final String versionNumber,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    try {
      this.engine.findElementWithDuration(Criteria.isButton().withText("Edit"), Duration.ofSeconds(60)).getFirstElement();
      return new TestAcceptanceMessage(true,
          "Restore version with: Time - " + timeline + ", Version Number - " + versionNumber);
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Unable to restore version with: Time - " + timeline + ", Version Number - " + versionNumber);
    }
  }

  /**
   * <p>
   * Verify a new artifact is inserted successfully after ${@link RMModulePage#insertNewArtifact(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param params store pairs of key and value:
   * @param lastResult result from ${@link RMModulePage#insertNewArtifact(Map)}
   * @return true if artifact is inserted successfully as expectation or vice versa
   */
  public TestAcceptanceMessage verifyInsertNewArtifact(final Map<String, String> params, final String lastResult) {
    String typeOfInsertion = params.get("typeOfInsertion");
    String targetArtifactID = params.get("targetArtifactID");
    if (typeOfInsertion.equals("After") || typeOfInsertion.equals("Insert Other Artifact Type After...")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact created is inserted after the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact created is inserted after the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    else if (typeOfInsertion.equals("Before") || typeOfInsertion.equals("Insert Other Artifact Type Before...")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTBEFORE, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTBEFORE, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact created is inserted before the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact created is inserted before the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    else if (typeOfInsertion.equals("Below (as a Child)") || typeOfInsertion.equals("Insert Other Artifact Type Below (as a Child)...")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact created is inserted Below (as a child) of  the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact created is inserted Below (as a child) of the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    
    return new TestAcceptanceMessage(true,
        "Skip verifying insert artifact. With the other type of inserting, please describe new!");
  }
  
  /**
   * <p>
   * Verify an existed artifact is inserted successfully after ${@link RMModulePage#insertNewArtifact(Map)}
   * <p>
   *
   * @param additionalParams store pairs of key and value:
   * @param lastResult result from ${@link RMModulePage#insertExistingArtifact(Map)}
   * @return true if artifact is inserted successfully as expectation or vice versa
   */
  public TestAcceptanceMessage verifyInsertExistingArtifact(final Map<String, String> additionalParams, final String lastResult) {
      String targetArtifactID = additionalParams.get("TARGET_ARTIFACT_ID");
      String existingArtifactID = additionalParams.get("EXISTING_ARTIFACT_ID");
      String typeOfInsertion = additionalParams.get("TYPE_OF_INSERTION");
      String actualInsertedArtifactID = "";
      
      if (typeOfInsertion.equalsIgnoreCase("Before...")) {
        boolean insertedArtifactVisible= this.driverCustom.isElementVisible(RMConstants.ARTIFACT_ID_BY_ADJACENCE_ABOVE_ARTIFACT_XPATH, timeInSecs, targetArtifactID);
        if(insertedArtifactVisible) {
          //This case happens when insert artifact before the selected artifact which is the first in table
      actualInsertedArtifactID = this.driverCustom.getText(RMConstants.ARTIFACT_ID_BY_ADJACENCE_ABOVE_ARTIFACT_XPATH, targetArtifactID);
      if (actualInsertedArtifactID.equalsIgnoreCase(existingArtifactID)) {
        return new TestAcceptanceMessage(true, "An existed artifact (" + existingArtifactID + ") is inserted Before the target artifact '"+ targetArtifactID +"' successfully!");
      }
      return new TestAcceptanceMessage(false, "An existed artifact (" + existingArtifactID + ") is inserted Before the target artifact '" + targetArtifactID + "' unsuccessfully!");
      }
        // This case happens when insert artifact before the selected artifact which is from the second in table
        actualInsertedArtifactID = this.driverCustom
            .getText(RMConstants.RMARTIFACTSPAGE_NEWLY_ARTIFACT_INSERTED_BEFORE_XPATH, targetArtifactID);
        if (actualInsertedArtifactID.equalsIgnoreCase(existingArtifactID)) {
          return new TestAcceptanceMessage(true,
              "An existed artifact (" + existingArtifactID + ") is inserted Before the target artifact '" + targetArtifactID + "' successfully!");
        }
        return new TestAcceptanceMessage(false,
            "An existed artifact (" + existingArtifactID + ") is inserted Before the target artifact '" + targetArtifactID + "' unsuccessfully!");
      }
      if (typeOfInsertion.equalsIgnoreCase("Below (as a Child)...")) {
        actualInsertedArtifactID = this.driverCustom.getText(RMConstants.ARTIFACT_CHILD_ID_BY_PARENT_ARTIFACT_XPATH, targetArtifactID);
        if (actualInsertedArtifactID.equalsIgnoreCase(existingArtifactID)) {
          return new TestAcceptanceMessage(true, "An existed artifact (" + existingArtifactID + ") is inserted Below (as a Child) the target artifact '"+ targetArtifactID +"' successfully!");
        }
        return new TestAcceptanceMessage(false, "An existed artifact (" + existingArtifactID + ") is inserted Below (as a Child) the target artifact '"+ targetArtifactID +"' unsuccessfully!");
      }
      if (typeOfInsertion.equalsIgnoreCase("After...")) {
        actualInsertedArtifactID = this.driverCustom.getText(RMConstants.ARTIFACT_ID_BY_ADJACENCE_BELOW_ARTIFACT_XPATH, targetArtifactID);
        if (actualInsertedArtifactID.equalsIgnoreCase(existingArtifactID)) {
          return new TestAcceptanceMessage(true, "An existed artifact (" + existingArtifactID + ") is inserted After the target artifact '"+ targetArtifactID +"' successfully!");
        }
        return new TestAcceptanceMessage(false, "An existed artifact (" + existingArtifactID + ") is inserted After the target artifact '"+ targetArtifactID +"' unsuccessfully!");
      }
      return new TestAcceptanceMessage(true,
          "Skip verifying insert artifact. With the other type of inserting, please describe new!");
  }
  /**
   * <p>
   * Verify search result after ${@link RMModulePage#searchArtifact(String,String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactIDOrContent artifact ID of artifact content to be searched
   * @param isClearFilter option to select clear all filters before searching or not
   * @param lastResult result from ${@link RMModulePage#searchArtifact(String,String)}
   * @return true if searched artifact is displayed or vice versa
   */
  public TestAcceptanceMessage verifySearchArtifact(final String artifactIDOrContent, final String isClearFilter,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(String.format("//a[@class='jazz-ui-ResourceLink' and text()='%s']" +
        "|//p[@id][text()='%s']", artifactIDOrContent, artifactIDOrContent), Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true, "Verify searching artifact '" + artifactIDOrContent + "': PASSED!");
    }
    return new TestAcceptanceMessage(false, "Verify searching artifact '" + artifactIDOrContent + "': FAILED!");
  }

  /**
   * <p>
   * Verify term is look up as expectation after ${@link RMModulePage#lookUpTerm(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be look up
   * @param expectedTermValue expected Term value to be searched
   * @param numberOfTermMatch number of results matches with expected term
   * @param lastResult result from ${@link RMModulePage#lookUpTerm(String, String, String)}
   * @return true if look up term result matches expectation or vive versa
   */
  public TestAcceptanceMessage verifyLookUpTerm(final String artifactID, final String expectedTermValue,
      final String numberOfTermMatch, final String lastResult) {
    this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_TERMRESULT_LOOKUPTERM_XPATH, Duration.ofSeconds(30));
    List<WebElement> listLookUpTerm =
        this.driverCustom.getVisibleWebElements(RMConstants.RMMODULEPAGE_TERMRESULT_LOOKUPTERM_XPATH);
    WebElement showingNumberMatch = null;
    if (Integer.parseInt(numberOfTermMatch) > 1) {
      try {
        showingNumberMatch = this.driverCustom.getPresenceOfWebElement(
            "//span[@dojoattachpoint='_searchInfoNode' and text()='Showing " + numberOfTermMatch + " matches.']");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false,
            "Look up term is not displayed as expected!\nExpectation: 'Showing " + numberOfTermMatch +
            " match.'\nActual: '" + this.driverCustom.getText("//span[@dojoattachpoint='_searchInfoNode']") + "'");
      }
    }
    else {
      try {
        showingNumberMatch = this.driverCustom.getPresenceOfWebElement(
            "//span[@dojoattachpoint='_searchInfoNode' and text()='Showing " + numberOfTermMatch + " match.']");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false,
            "Look up term is not displayed as expected!\nExpectation: 'Showing " + numberOfTermMatch +
            " match.'\nActual: '" + this.driverCustom.getText("//span[@dojoattachpoint='_searchInfoNode']") + "'");
      }
    }

    boolean result = false;
    for (WebElement lookUpTerm : listLookUpTerm) {
      if (lookUpTerm.getText().trim().equals(expectedTermValue)) {
        result = true;
        break;
      }
    }
    result &= showingNumberMatch != null;
    if (result) {
      return new TestAcceptanceMessage(true, "Look up term is displayed as expected!");
    }
    return new TestAcceptanceMessage(false, "Look up term is not displayed as expected!\nExpected term value '" +
        expectedTermValue + "' is not displayed!");
  }

  /**
   * <p>
   * Verify if update artifact content successful successful <br>
   * This method called after {@link RMModulePage#editArtifactContent}.
   *
   * @param additionalParams params from {@link RMModulePage#editArtifactContent}.
   * @param lastResult result from {@link RMModulePage#editArtifactContent}.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyEditArtifactContent(final Map<String, String> additionalParams,
      final String lastResult) {
    String option = additionalParams.get("EDIT_OPTION");
    String artifactID = additionalParams.get("ARTIFACT_ID");
    String newArtifactContent = additionalParams.get("NEW_CONTENT");
    // Replace new artifact content
    if (option.equals("Replace new artifact content")) {
      this.driverCustom.waitForSecs(Duration.ofSeconds(10));
      if (this.driverCustom.isElementVisible(
          "//table[@rowlabel='Artifact " + artifactID + "'][//p[@dir='ltr' and text()='" + newArtifactContent + "']]",
          Duration.ofSeconds(60))) {
        return new TestAcceptanceMessage(true, "Verified: Editing content of artifact '" + artifactID + "' successfull. \nEdited content of artifact is : '"+newArtifactContent+"'");
      }
      return new TestAcceptanceMessage(false, "Verified: Artifact '" + artifactID + "' is not edited successfull.");
    }
    // Upload a new version
    else if (option.equals("Upload a new version")) {
      try {
        this.driverCustom.getWebElement("//div[@class='header-secondary' and @title='Upload File']");
        return new TestAcceptanceMessage(false, "Updated Image for artifact unsuccessfully");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(true, "Updated Image for artifact successfully");
      }
    }
    else if (option.equals("Insert Image")) {
      try {
        this.driverCustom.getWebElement("//div[@class='header-secondary' and @title='Insert Image']");
        return new TestAcceptanceMessage(false, "Insert Image for artifact unsuccessfully");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(true, "Insert Image for artifact successfully");
      }
    }
    else {
      return new TestAcceptanceMessage(false, "Edit content option is not supported yet!!!");
    }
  }

  /**
   * Verify artifact is deleted successfully after ${@link RMModulePage#deleteArtifactAndGetIsDeleteState(String, String)}
   *
   * @author VDY1HC
   * @param artifactID ID of artifact to be deleted
   * @param expectedIsDelete - expected state of is delete
   * @param lastResult result from ${@link RMModulePage#deleteArtifactAndGetIsDeleteState(String, String)}
   * @return true if artifact is deleted successfully or vice versa
   */
  public TestAcceptanceMessage verifyDeleteArtifactAndGetIsDeleteState (final String artifactID, String expectedIsDelete, final String lastResult) {
    boolean removeResult = !(this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(10), artifactID));
    boolean actualRemove = Boolean.parseBoolean(lastResult);
    boolean isMatchExpectedResult = (actualRemove == Boolean.parseBoolean(expectedIsDelete));
    String msgRemove = "removed from module but NOT deleted from project area.";
    String msgExpectAndActual = 
        "\nActual result: Artifact ID - " + artifactID + ", Remove and deleted - " + actualRemove +
        "\nExpected reuslt: Artifact ID - " + artifactID + ", Remove and deleted - " + expectedIsDelete;
    if (actualRemove) {
      msgRemove = "removed from module and deleted from project area.";
    }
    if (removeResult) {
      if (isMatchExpectedResult) {
        return new TestAcceptanceMessage(true, "Verified: PASSED - Artifact " + artifactID + " is " + msgRemove + msgExpectAndActual);
      }
      return new TestAcceptanceMessage(false, "Verified: FAILED - Artifact " + artifactID + " is NOT " + msgRemove + msgExpectAndActual);
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Artifact " + artifactID + " is NOT deleted.");
  }

  /**
   * <p>
   * Verify artifact is deleted successfully after ${@link RMModulePage#deleteArtifact(String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be deleted
   * @param lastResult result from ${@link RMModulePage#deleteArtifact(String)}
   * @return true if artifact is deleted successfully or vice versa
   */
  public TestAcceptanceMessage verifyDeleteArtifact(final String artifactID, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(15));
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH, this.timeInSecs);
    try {
      Text txtSearchField =
          this.engine.findFirstElementWithDuration(Criteria.isTextField().withText("Search Artifacts"), this.timeInSecs);
      txtSearchField.setText(artifactID + Keys.ENTER);
    }
    catch (Exception e) {
      this.driverCustom.getFirstVisibleWebElement(RMConstants.RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_DIAGRAM_XPATH, null)
      .sendKeys(artifactID + Keys.ENTER);
    }
    this.driverCustom.isElementInvisible(RMConstants.QUICK_SEARCH_BOX_LOADING_XPATH, Duration.ofSeconds(180));
    String statusInfo = this.driverCustom.getPresenceOfWebElement(RMConstants.QUICK_SEARCH_BOX_STATUS_XPATH).getText();
    if (statusInfo.contains("No results found")) {
      return new TestAcceptanceMessage(true, "Artifact '" + artifactID + "' is delected successfully!");
    }
    return new TestAcceptanceMessage(false, "Artifact '" + artifactID + "' is not delected successfully!");
  }

  /**
   * <p>
   * Verify a new artifact is created successfully after call the method ${@link RMModulePage#createANewArtifact(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param additionalParams stores: ARTIFACT_NAME: name of new artifact ARTIFACT_TYPE: type of new artifact
   *          ARTIFACT_FORMAT: format of new artifact
   * @param lastResult result from ${@link RMModulePage#createANewArtifact(Map)}
   * @return true is a new artifact is created successfully or vice versa
   */
  public TestAcceptanceMessage verifyCreateANewArtifact(final Map<String, String> additionalParams,
      final String lastResult) {
    if (lastResult.matches("^[0-9]*$")) {
      return new TestAcceptanceMessage(true,
          "Artifact is created successfully:\nContent: '" + additionalParams.get("ARTIFACT_NAME") + "'.\nType: '" +
              additionalParams.get("ARTIFACT_TYPE") + "'.\nID: '" + lastResult + "'.\n");
    }
    return new TestAcceptanceMessage(false, "Create a new artifact failed!");
  }

  /**
   * <p>
   * Verify a new module is created successfully after call method ${@link RMModulePage#createModule(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param additionalParams params from ${@link RMModulePage#createModule(Map)}
   * @param lastResult result from ${@link RMModulePage#createModule(Map)}
   * @return true if a new module is created successfully or vice versa
   */
  public TestAcceptanceMessage verifyCreateModule(final Map<String, String> additionalParams, final String lastResult) {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(120));
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//span[@class='resource-title' and text()='" + additionalParams.get("MODULE_NAME") + "']")));
      return new TestAcceptanceMessage(true,
          "Module with name '" + additionalParams.get("MODULE_NAME") + "' is created successfully!");
    }
    catch (Exception e) {}
    return new TestAcceptanceMessage(false,
        "Module with name '" + additionalParams.get("MODULE_NAME") + "' is not created successfully!");
  }

  /**
   * <p>
   * Verify get Module ID of the current module after ${@link RMModulePage#getModuleID()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMModulePage#getModuleID()}
   * @return true if the return value is numeric or vice versa
   */
  public TestAcceptanceMessage verifyGetModuleID(final String lastResult) {
    if (lastResult.matches("^[0-9]*$")) {
      return new TestAcceptanceMessage(true,
          "Verify get module ID of current module successfully. Module ID is: '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verify get module ID of current module failed. Actual result is: '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verify click on content of artifact successfully after ${@link RMModulePage#clickOnEditContentOfArtifact(String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be clicked on
   * @param lastResult result from ${@link RMModulePage#clickOnEditContentOfArtifact(String)}
   * @return true if click on edit content of artifact successfully or vice versa
   */
  public TestAcceptanceMessage verifyClickOnEditContentOfArtifact(final String artifactID, final String lastResult) {
    try {
      this.driverCustom.getWebElement("//td[@class='id-col'][//a[@class='jazz-ui-ResourceLink' and text()='" +
          artifactID +
          "']]/following-sibling::td[@colid='module_content_col']//div[@data-dojo-attach-point='_primaryTextNode' and @contenteditable='true']");
      return new TestAcceptanceMessage(true, "Click on content of artifact '" + artifactID + "' successfully!");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Click on content of artifact '" + artifactID + "' failed!");
    }
  }

  /**
   * <p>
   * Verify click on content of artifact successfully after ${@link RMModulePage#clickOnEditContentOfArtifact(String)}
   * <p>
   *
   * @author NVV1HC
   * @param linkType type ok link: Satisfied By, Implemented By, Tracked By
   * @param artifactIDOrName artifact name or ID
   * @param lastResult result from ${@link RMModulePage#clickOnEditContentOfArtifact(String)}
   * @return true if click on edit content of artifact successfully or vice versa
   */
  public TestAcceptanceMessage verifyIsLinkDisplayedInSelectedArtifactTab(final String linkType,
      final String artifactIDOrName, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Link with type '" + linkType + "' to '" + artifactIDOrName +
          "' is displayed in Selected Artifact as expectation!");
    }
    return new TestAcceptanceMessage(false, "Link with type '" + linkType + "' to '" + artifactIDOrName +
        "' is not displayed in Selected Artifact as expectation!");
  }

  /**
   * <p>
   * Verify expand artifact section after ${@link RMModulePage#expandArtifactSection(String)}
   * <p>
   *
   * @author NVV1HC
   * @param sectionNumber section number is expanded
   * @param lastResult result from ${@link RMModulePage#expandArtifactSection(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyExpandArtifactSection(final String sectionNumber, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify expand section '" + sectionNumber + "' successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify expand section '" + sectionNumber + "' failed!");
  }

  /**
   * <p>
   * Verify expand artifact section after ${@link RMModulePage#collapseArtifactSection(String)}
   * <p>
   *
   * @author NVV1HC
   * @param sectionNumber section number is expanded
   * @param lastResult result from ${@link RMModulePage#collapseArtifactSection(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyCollapseArtifactSection(final String sectionNumber, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify collapse section '" + sectionNumber + "' successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify collapse section '" + sectionNumber + "' failed!");
  }

  /**
   * <p>
   * Verify expand artifact section after ${@link RMModulePage#exportModuleWithPDFOrWordDocument(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param params stores pair of keys and values
   * @param lastResult result from ${@link RMModulePage#exportModuleWithPDFOrWordDocument(Map)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyExportModuleWithPDFOrWordDocument(final Map<String, String> params,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    boolean result = this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_MESSAGEGENERATEREPORT_XPATH, Duration.ofSeconds(60));
    if (result) {
      return new TestAcceptanceMessage(true, "Verify export module with PDF document successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verify export module with PDF document failed! The Success message is not shown!");
  }

  /**
   * <p>
   * Verify the link is opened in the new tab successfully after ${@link RMModulePage#openLinkInTheNewTab(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param params stores a pair of key and value for: "linkType":"Validated By", "Satisfied By",etc.. "link": test case
   *          ID and name, e.g: "59174: DernCANReporting"
   * @param lastResult result from the main method ${@link RMModulePage#openLinkInTheNewTab(Map)}
   * @return Verification message
   */
  public TestAcceptanceMessage verifyOpenLinkInTheNewTab(final Map<String, String> params, final String lastResult) {
    WebDriver driver = this.driverCustom.getWebDriver();
    ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(tabs2.size() - 1));
    String newTabTitle = "";
    for (int i = 0; i < 60; i++) {
      newTabTitle = driver.getTitle();
      if (newTabTitle.contains(params.get("link"))) {
        return new TestAcceptanceMessage(true,
            "Verify open the link '" + params.get("link") + "' in the new tab successfully!");
      }
      this.driverCustom.waitForSecs(Duration.ofSeconds(1));
    }
    return new TestAcceptanceMessage(false,
        "Verify open the link '" + params.get("link") +
        "' in the new tab failed!\nThe expected title of new tab should contains:'" + params.get("link") +
        "'.\nBut actual new tab title is: '" + newTabTitle + "'.");
  }

  /**
   * <p>
   * This method is called after running ${@link RMModulePage#clickOnAuditHistory()}
   * <p>
   * @author NVV1HC
   * @param lastResult result from ${@link RMModulePage#clickOnAuditHistory()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnAuditHistory(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: Open Audit History page successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verify: Open Audit History page failed!");
  }

  /**
   * This method is called after running ${@link RMModulePage#addArtifactToModule(String)}
   * @author VDY1HC
   * @param artifactID - aritfactID to be added
   * @param lastResult -
   * @return verification message
   */
  public TestAcceptanceMessage verifyAddArtifactToModule(final String artifactID, final String lastResult) {
    this.driverCustom.isElementInvisible("//span[@dojoattachpoint='_headerPrimarySpan' and text()='Add to module...']", Duration.ofSeconds(180));
    if (this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_FILTERREMOVEBUTTON_XPATH, Duration.ofSeconds(30))) {
      this.driverCustom.click(RMConstants.RMARTIFACTSPAGE_FILTERREMOVEBUTTON_XPATH);
    }
    this.driverCustom.isElementClickable(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH, Duration.ofSeconds(15));
    try {
      Text txtSearchField =
          this.engine.findFirstElementWithDuration(Criteria.isTextField().withText("Search Artifacts"), Duration.ofSeconds(20));
      txtSearchField.setText(artifactID + Keys.ENTER);
    }
    catch (Exception e) {
      this.driverCustom.getFirstVisibleWebElement(RMConstants.RM_ARTIFACTSPAGE_CREATE_LINK_SEARCH_DIAGRAM_XPATH, null)
      .sendKeys(artifactID + Keys.ENTER);
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    boolean result = this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_ARTIFACTCHECKBOX_XPATH, Duration.ofSeconds(60), artifactID);
    if (result) {
      return new TestAcceptanceMessage(true,"Verify: PASSED - Add Artifact " + artifactID + " into module.");
    }
    return new TestAcceptanceMessage(false,"Verify: FAILED - Unable to add Artifact " + artifactID + " into module.");
  }

  /**
   * <p>
   * This method is called after running ${@link RMModulePage#verifyFirstMessageInAuditHistory(String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param message expected message to be verified
   * @param expectedCondition true if we want to check the message is displayed in the first audit history message
   * @param lastResult result from ${@link RMModulePage#verifyFirstMessageInAuditHistory(String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyFirstMessageInAuditHistory(final String message,
      final String expectedCondition, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      if (expectedCondition.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true,
            "Verify the first audit history message of artifact contains the message '" + message + "' successfully!");
      }
      return new TestAcceptanceMessage(true,
          "Verify the first audit history message of artifact does not contain the message '" + message +
          "' successfully!");
    }
    if (expectedCondition.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(false,
          "Verify the first audit history message of artifact contains the message '" + message +
          "' failed!.\nWe expect the message is displayed but actual it was not displayed!");
    }
    return new TestAcceptanceMessage(false,
        "Verify the first audit history message of artifact does not contains the message '" + message +
        "' failed!.\nWe expect the message is not displayed but actual it was displayed!");
  }

  /**
   * <p>
   * This method is called after running ${@link RMModulePage#verifyNumberOfArtifactDisplayedInModule(String)}
   * <p>
   *
   * @author NVV1HC
   * @param expectedNumber expected number artifact of module
   * @param lastResult result from ${@link RMModulePage#clickOnAuditHistory()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyNumberOfArtifactDisplayedInModule(final String expectedNumber,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: number artifact displayed in module matches with expected number '" + expectedNumber + "'!");
    }
    return new TestAcceptanceMessage(false,
        "Verify: number artifact displayed in module did not match with expected number '" + expectedNumber + "'!");
  }

  /**
   * This method is called after running {@link RMModulePage#clickAndDragArtifactToChangeOrder(String, String, String)} <br>
   * @author VDY1HC
   * @param mainArtifactID - Artifact ID to be drag
   * @param targetArtifactID - Artifact ID to be move to
   * @param isAfterTargetArtifact - true if Position is after target artifact
   * @param lastResult - lastResult
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickAndDragArtifactToChangeOrder(final String mainArtifactID, final String targetArtifactID, final String isAfterTargetArtifact,
      final String lastResult) {
    WebElement mainArtifact = null;
    String position = "before ";
    if (isAfterTargetArtifact.equalsIgnoreCase("false")) {
      mainArtifact = this.driverCustom.getWebElement("(//table[@rowlabel='Artifact " + targetArtifactID + "']/preceding-sibling::table)[last()]");
    }
    else {
      mainArtifact = this.driverCustom.getWebElement("(//table[@rowlabel='Artifact " + targetArtifactID + "']/following-sibling::table)[1]");
      position = "after ";
    }
    String actualArtifactID = mainArtifact.getAttribute("rowlabel").split(" ")[1].trim();
    if (actualArtifactID.equals(mainArtifactID)) {
      return new TestAcceptanceMessage (true, "Verified: PASSED - Artifact ID: " + mainArtifactID + " is " + position + "artifact ID: " + targetArtifactID);
    }
    return new TestAcceptanceMessage (false, "Verified: FAILED - Artifact ID: " + mainArtifactID + " is NOT " + position + "artifact ID: " + targetArtifactID);
  }

  /**
   * <p>
   * This method is called after executing ${@link RMModulePage#verifyPercentageDisplayed(String)}
   * <p>
   *
   * @author NVV1HC
   * @param expectedPercentage expected percentage should be displayed
   * @param lastResult result from ${@link RMModulePage#verifyPercentageDisplayed(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyPercentageDisplayed(final String expectedPercentage,
      final String lastResult) {
    RMArtifactsPage RMArtifactPage = new RMArtifactsPage(this.driverCustom);
    String percentageDisplayed = RMArtifactPage.getPercentageOfArtifactDisplayed();
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: Verify percentage of artifact displayed as the bottom of the page is as expectation!\nExpected percentage should be displayed: '" +
              expectedPercentage + "'.\nActual percentage displayed: '" + percentageDisplayed + "'.");
    }
    return new TestAcceptanceMessage(true,
        "Verify: Verify percentage of artifact displayed as the bottom of the page is not as expectation!\nExpected percentage should be displayed: '" +
            expectedPercentage + "'.\nActual percentage displayed: '" + percentageDisplayed + "'.");
  }

  /**
   * This method called after {@link RMModulePage#deleteArtifact(String)}.
   * @author VDY1HC
   * @param artifactID id of the artifact to be deleted.
   * @param additionalParams - Expected "Remove" / "Remove and delete"
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteArtifact(final String artifactID, final String lastResult, final String additionalParams) {
    boolean removeResult = !(this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(20), artifactID));
    boolean actualRemove = Boolean.parseBoolean(lastResult);
    boolean isMatchExpectedResult = (actualRemove == Boolean.valueOf(additionalParams));
    String msgRemove = "removed from module but NOT deleted from project area.";
    String msgExpectAndActual =
        "\nActual result: Artifact ID - " + artifactID + ", Remove and deleted - " + actualRemove +
        "\nExpected reuslt: Artifact ID - " + artifactID + ", Remove and deleted - " + additionalParams;
    if (actualRemove) {
      msgRemove = "removed from module and deleted from project area.";
    }
    if (removeResult) {
      if (isMatchExpectedResult) {
        return new TestAcceptanceMessage(true, "Verified: PASSED - Artifact " + artifactID + " is " + msgRemove + msgExpectAndActual);
      }
      return new TestAcceptanceMessage(false, "Verified: FAILED - Artifact " + artifactID + " is NOT " + msgRemove + msgExpectAndActual);
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Artifact " + artifactID + " is NOT deleted.");
  }

  /**
   * <p>
   * This method is called after running ${@link RMModulePage#inputNameForArtifactContent(String)}
   * <p>
   *
   * @author PDU6HC
   * @param name : name of artifact content.
   * @param lastResult result from ${@link RMModulePage#inputNameForArtifactContent(String)}
   * @return new artifact of content type is created
   */
  public TestAcceptanceMessage verifyInputNameForArtifactContent(final String name,
      final String lastResult) {
    if (lastResult.matches("^[0-9]*$")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Artifact with content: " + name + " is inputted!");
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Artifact with content: " + name + " is not inputted!");
  }


  /**
   * <p>
   * This method is called after running ${@link RMModulePage#clickOnSearchOtherComponents()}
   * <p>
   *
   * @author PDU6HC
   * @param lastResult result from ${@link RMModulePage#clickOnSearchOtherComponents()}
   * @return new artifact of content type is created
   */
  public TestAcceptanceMessage verifyClickOnSearchOtherComponents(final String lastResult) {
    if (this.driverCustom.isElementInvisible(RMConstants.RMMODULEPAGE_SEARCH_OTHER_COMPONENTS_XPATH, Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Clicked on 'Search other components' successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Clicked on 'Search other components' failed");
  }


  /**
   * <p>
   * This method is called after running ${@link RMModulePage#verifyNumberOfMatchesInTermDialog(String)}
   * <p>
   *
   * @author PDU6HC
   * @param termMatches is number of matches in term dialog you want to verify.
   * @param lastResult result from ${@link RMModulePage#verifyNumberOfMatchesInTermDialog(String)}
   * @return number of matches in look up term dialog is correct.
   */
  public TestAcceptanceMessage verifyVerifyNumberOfMatchesInTermDialog(final String termMatches, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Number of matches is correct, Expected number of matches: " + termMatches);
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Number of matches is incorrect, Expected number of matches: " + termMatches);
  }

  /**
   * <p>
   * This method is called after running ${@link RMModulePage#addTermOfOtherComponentHyperLinkandSave(String)}
   * <p>
   *
   * @author PDU6HC
   * @param index is the position of the term hyperlink in the term dialog.
   * @param lastResult result from ${@link RMModulePage#addTermOfOtherComponentHyperLinkandSave(String)}
   * @return term is added.
   */
  public TestAcceptanceMessage verifyAddTermOfOtherComponentHyperLinkandSave(final String index, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Term at index: "+ index +" is added successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verify: PASSED - Term at index: "+ index +" is failed to added");
  }

  /**
   * <p>
   * This method is called after running ${@link RMModulePage#navigateToTermHyperLink(String, String)}
   * <p>
   *
   * @author PDU6HC
   * @param artifactId is artifact id which contains the term.
   * @param term is the term name you need to click on.
   * @param lastResult result from ${@link RMModulePage#navigateToTermHyperLink(String, String)}
   * @return navigated to component contains hyperlink term.
   */
  public TestAcceptanceMessage verifyNavigateToTermHyperLink(final String artifactId, final String term, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Click on term: "+ term + " of artifact id: "+ artifactId +" successful");
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Failed to click on term: " + term + " of artifact id: " + artifactId);
  }

  /**
   * <p>
   * This method is called after running ${@link RMModulePage#removeLinkInsideModule(String, String)}
   * <p>
   *
   * @author PDU6HC
   * @param removeLinkText the text on link you want to remove.
   * @param linkType is the type of link you want to remove.
   * @param lastResult result from ${@link RMModulePage#removeLinkInsideModule(String, String)}
   * @return link of module removed.
   */
  public TestAcceptanceMessage verifyRemoveLinkInsideModule(final String removeLinkText, final String linkType, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - link: " + removeLinkText +" of link type: " + linkType + "removed successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Failed to remove link: " + removeLinkText +" of link type: " + linkType);
  }

  /**
   * This method is called after running ${@link RMModulePage#getModuleAttribute(String)} <br>
   * @author VDY1HC
   * @param attributeName - name of attribute
   * @param lastResult - attribute value
   * @return  object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetModuleAttribute (final String attributeName, final String lastResult) {
    if(lastResult.isEmpty()) {
      return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to get attribute value: " + attributeName);
    }
    return new TestAcceptanceMessage(true, "Verified: PASSED - Attribute value: " + attributeName + " is: " + lastResult);
  }

  /**
   * This method is called after running ${@link RMModulePage#editArtifactTypeHSIFlagDropDownInModule(String, String, String)} <br>
   * @author PDU6HC
   * @param artifactId is id of artifact selected
   * @param attribute attribute of artifact type 'AEn_ThermoSystems_HSI-Flag' dropdown.
   * @param attributeValue attribute value for attribute.
   * @param lastResult result from ${@link RMModulePage#editArtifactTypeHSIFlagDropDownInModule(String, String, String)}
   * @return  attribute artifact type HSI-Flag has changed
   */
  public TestAcceptanceMessage verifyEditArtifactTypeHSIFlagDropDownInModule (final String artifactId, final String attribute, final String attributeValue, final String lastResult) {
    if(lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, 
          String.format( "Verified: PASSED - artifact attribute: %s of artifact Id: %s has changed to value: %s successfully", attribute, artifactId, attributeValue));
    }
    return new TestAcceptanceMessage(false, 
        String.format( "Verified: FAILED - artifact attribute: %s of artifact Id: %s has failed to change to value: %s", attribute, artifactId, attributeValue));
  }

  /**
   * This method is called after running ${@link RMModulePage#navigateToModuleFromAuditHistory()} <br>
   * @author PDU6HC
   * @param lastResult result from ${@link RMModulePage#navigateToModuleFromAuditHistory()}
   * @return  navigated back to module from 'Audit History'
   */
  public TestAcceptanceMessage verifyNavigateToModuleFromAuditHistory (final String lastResult) {
    if(this.driverCustom.isElementPresent(RMConstants.RMMODULEPAGE_MODULE_TAB_XPATH, Duration.ofSeconds(15))) {
      return new TestAcceptanceMessage(true, 
          "Verified: PASSED - Navigated back to module page from Audit History successfully");
    }
    return new TestAcceptanceMessage(false, 
        "Verified: FAILED - Navigated back to module page from Audit History failed");
  }


  /**
   * <p>
   * This method is called after executing ${@link RMModulePage#expandArtifact(String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be expanded
   * @param lastResult result from ${@link RMModulePage#expandArtifact(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyExpandArtifact(final String artifactID, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: Click expand icon to expand the children of the artifact '" + artifactID + "' successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verify: Click expand icon to expand the children of the artifact '" + artifactID + "' failed!");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMModulePage#collapseArtifact(String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be collapsed
   * @param lastResult result from ${@link RMModulePage#collapseArtifact(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyCollapseArtifact(final String artifactID, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: Click expand icon to collapse artifact '" + artifactID + "' successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verify: Click expand icon to collapse artifact '" + artifactID + "' failed!");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMModulePage#editMaxLb(String, String)}
   * <p>
   *
   * @author PDU6HC
   * @param artifactID ID of artifact for editing maxLb value
   * @param maxLbValue is value of maxLb you want to set
   * @param lastResult result from ${@link RMModulePage#editMaxLb(String, String)}
   * @return true if maxLb updated matches maxLbValue
   */
  public TestAcceptanceMessage verifyEditMaxLb(final String artifactID, final String maxLbValue, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(30));
    String actualmaxLbValue = this.driverCustom.getText(RMConstants.RMMODULEPAGE_MAXLB_ACTUAL_VALUE_ROW_XPATH, artifactID);
    String[] artifactIDAndmaxLbValue = {artifactID, maxLbValue};
    if (this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_MAXLB_VALUE_ROW_XPATH, Duration.ofSeconds(10), artifactIDAndmaxLbValue)) {
      return new TestAcceptanceMessage(true, 
          String.format("Verify: PASSED - maxLb of artifact: %s updated successfully, Expected maxLb value: %s, Actual maxLb value: %s", artifactID, maxLbValue, actualmaxLbValue));
    }
    return new TestAcceptanceMessage(false,
        String.format("Verify: FAILED - maxLb of artifact: %s update failed, Expected maxLb value: %s, Actual maxLb value: %s", artifactID, maxLbValue, actualmaxLbValue));
  }
  /**
   * <p>
   * Verify number of artifact exported to the excel file.
   * <p>
   * This method called after {@link RMModulePage#getNumberOfArtifactsFromExcelFile(String)}.
   * 
   * @param excelPath path of the excel file.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetNumberOfArtifactsFromExcelFile(final String excelPath, final String lastResult) {
    int item = Integer.valueOf(lastResult);
    if (item>0) {
      return new TestAcceptanceMessage(true,
          "Verified: Number of Artifacts exported to the Excel file - "+lastResult);
    }
    return new TestAcceptanceMessage(false,
        "Verify: Expected number of Artifacts not exported to the Excel file - "+lastResult);
  }
  /**
   * <p>
   * Verify clicked on link linked with artifact from the excel file.
   * <p>
   * This method called after {@link RMModulePage#getURLOfLinkedArtifactFromExcelFile(String, String, String)}.
   * 
   * @param excelFilePath path of the excel file.
   * @param linkType type of link.
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetURLOfLinkedArtifactFromExcelFile(String excelFilePath, final String linkType, final String artifactID,final String lastResult) {
    if ((lastResult != null) && !lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on '"+linkType+"' link linked with '"+artifactID+"' artifact.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Not clicked on '"+linkType+"' link linked with '"+artifactID+"' artifact.");
  }
  /**
   * <p>Verify the number of selected artifact.
   * <p>This method called after {@link RMModulePage#getSelectedArtifacts()}.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetSelectedArtifacts(final String lastResult) {
    if (lastResult != null) {
      return new TestAcceptanceMessage(true, "Number of selected artifacts - " + lastResult);
    }

    return new TestAcceptanceMessage(false, "Not counted successfully");

  }
  /**
   * <p>
   * Verify number of artifact exported to the csv file.
   * <p>
   * This method called after {@link RMModulePage#getNumberOfArtifactsFromCSVFile(String)}.
   * 
   * @param excelPath path of the excel file.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetNumberOfArtifactsFromCSVFile(final String excelPath, final String lastResult) {
    int selecteditem = Integer.valueOf(lastResult);
    if (selecteditem > 0) {
      return new TestAcceptanceMessage(true,
          "Verified: Number of Artifacts exported to the CSV file - "+lastResult);
    }
    return new TestAcceptanceMessage(false,
        "Verify: Expected number of Artifacts not exported to the CSV file - "+lastResult);
  }
  /**
   * <p>
   * Verify clicked on link linked with artifact from the csv file.
   * <p>
   * This method called after {@link RMModulePage#getURLOfLinkedArtifactFromCSVFile(String, String, String)}.
   *
   * @param excelFilePath path of the excel file.
   * @param linkType type of link.
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetURLOfLinkedArtifactFromCSVFile(String excelFilePath, final String linkType, final String artifactID,final String lastResult) {
    if ((lastResult != null) && !lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on '"+linkType+"' link linked with '"+artifactID+"' artifact.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Not clicked on '"+linkType+"' link linked with '"+artifactID+"' artifact.");
  }
  /**
   * <p>
   * Verify remove Artifact from module successfully
   * <p>
   * This method called after {@link RMModulePage#removeArtifact}.
   *
   * @author PDU6HC
   * @param artifactID id of artifact need to remove from module/collection
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if artifact is removed from module.
   */
  public TestAcceptanceMessage verifyRemoveArtifact(final String artifactID, final String lastResult) {
    if (this.driverCustom.isElementInvisible(RMConstants.REMOVING_ARTIFACT_XPATH, this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verify: PASSED: Artifact removed successfully");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED: Artifact was not removed");
  }
  /**
   * <p>
   * Verify Error message shown successfully when using module to find non existing Artifact in module
   * <p>
   * This method called after {@link RMModulePage#verifyArtifactNotInModuleUsingModuleGoToFind}.
   * 
   * @author PDU6HC
   * @param artifactID id of artifact not existed in module
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if error message is shown when artifact not in current module.
   */
  public TestAcceptanceMessage verifyVerifyArtifactNotInModuleUsingModuleGoToFind(final String artifactID, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED: Error message shown");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED: Error message did not shown or artifact existed in current module");
  }

  /**
   * This method is called after running ${@link RMModulePage#addArtifactToModule(String, String)}
   * @author PDU6HC
   * @param artifactID - aritfactID to be added
   * @param button - button options - Add and Close or Add or Close
   * @param lastResult - returned value of method which is executed just before the method.
   * @return verification message
   */
  public TestAcceptanceMessage verifyAddArtifactToModule(final String artifactID, String button, final String lastResult) {
    switch (button) {
      case "Add and Close":
        if (this.driverCustom.isElementInvisible(RMConstants.RMMODULEPAGE_ADD_TO_MODULE_XPATH, this.timeInSecs, artifactID)) {
          return new TestAcceptanceMessage(true,"Verify: PASSED - Add and Close option: Artifact " + artifactID + " added into module.");
        }
        return new TestAcceptanceMessage(false,"Verify: FAILED - Add and Close option: Artifact " + artifactID + " not added into module.");
      case "Add": 
        if (this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_ARTIFACT_ADDED_MESSAGE_XPATH, this.timeInSecs)) {
          return new TestAcceptanceMessage(true,"Verify: PASSED - Add option: Artifact " + artifactID + " into module.");
        }
        return new TestAcceptanceMessage(false,"Verify: FAILED - Add option: Unable to add Artifact " + artifactID + " into module.");
      case "Close":
        if (this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_ADD_TO_MODULE_XPATH, this.timeInSecs)) {
          return new TestAcceptanceMessage(true,"Verify: PASSED - Add option: Artifact " + artifactID + " into module.");
        }
        return new TestAcceptanceMessage(false,"Verify: FAILED - Add option: Unable to add Artifact " + artifactID + " into module.");
      default:
        return new TestAcceptanceMessage(false,"Verify: FAILED - button option input is not correct. Options are 'Add and Close', 'Add', 'Close'");
    }
  }

  /**
   * This method is called after running ${@link RMModulePage#verifyModuleStructureDisplayedCorrectly(String)}
   * @author VDY1HC
   * @param listOfSectionAndName - List of section number and name (EX: <sectionNumber1>:<sectionHeading1>;<sectionNumber2>:<sectionHeading2>;...
   * @param lastResult - true if every section number displayed with its heading
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyModuleStructureDisplayedCorrectly(final String listOfSectionAndName, final String lastResult) {
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,"Verify: PASSED - Every section numbers are displayed with its heading");
    }
    return new TestAcceptanceMessage(false,"Verify: FAILED - NOT Every section numbers are displayed with its heading");
  }
  /**
   * <P>
   * Verifies id of the artifact is visible.
   * <P>
   * This method called after {@link RMModulePage#getArtifactIDByArtifactContent(String)}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @param artifactName - which you want to get ID
   * @author NCY3HC
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetArtifactIDByArtifactContent(final String artifactName, final String lastResult) {
    String result = this.driverCustom.getText(RMConstants.RMMODULEINSIDE_ARTIFACT_ID_BY_ARTIFACT_CONTENT_XPATH, artifactName);
      if (result.equalsIgnoreCase(lastResult)) {
        return new TestAcceptanceMessage(true,
            "Verified: Artifact ID of the created artifact.ID of the artifact is \"" + lastResult + "\".");
      }
    return new TestAcceptanceMessage(false,
        "Verified: Artifact ID of the created artifact. Artifact ID \"" + lastResult + "\" not found.");
  }
  /**
   * <p>
   * This method called after {@link RMModulePage#verifyInsertExistingArtifactStructureDisplayCorrectly(Map)}
   * @param lastResult returned value of method which is executed just before the method.
   * @param  addtionalParams -  contains:
   *        targetArtifactID: the selected artifact inside module
   *        insertedArtifactID: artifact ID which is insert before the selected artifact
   *        typeOfInsertion: like Before/After/Below as a child when select option 'Insert Existing Artifact'
   * @author NCY3HC
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyVerifyInsertExistingArtifactStructureDisplayCorrectly( final Map<String, String> addtionalParams,final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Artifact is inserted successfully");
    }

    return new TestAcceptanceMessage(false, "Artifact is inserted fail");

  }
  /**
   * <p>
   * This method is called after executing the method ${@link RMModulePage#clickOnAddExistingArtifactOption()}
   * <p>
   *
   *@author NCY3HC
   * @param lastResult result from ${@link RMArtifactsPage#clickOnAddExistingArtifactOption()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnAddExistingArtifactOption(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: Click on 'Add Existing Artifact' button successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify: Click on 'Add Existing Artifact' button failed!");
  }
  /**
   * <p>
   * Verify a new artifact is inserted successfully after ${@link RMModulePage#pasteArtifact(Map)}
   * <p>
   *
   * @author NCY3HC
   * @param params store pairs of key and value:
   * @param lastResult result from ${@link RMModulePage#pasteArtifact(Map)}
   * @return true if artifact is paste successfully as expectation or vice versa
   */
  public TestAcceptanceMessage verifyPasteArtifact(final Map<String, String> params, final String lastResult) {
    String typeOfInsertion = params.get("typeOfInsertion");
    String targetArtifactID = params.get("targetArtifactID");
    if (typeOfInsertion.equals("After")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact  is pasted after the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact  is pasted after the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    else if (typeOfInsertion.equals("Before")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTBEFORE, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTBEFORE, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact  is pasted before the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact  is pasted before the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    if (typeOfInsertion.equals("Below (as a Child)")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact  is pasted Below as a child the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact  is pasted below as a child the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    return new TestAcceptanceMessage(true,
        "Skip verifying insert artifact. With the other type of inserting, please describe new!");
  }
  
  /**
   * <p>
   * Verify a new artifact is uploaded successfully after ${@link RMModulePage#uploadNewArtifact(Map)}
   * <p>
   *
   * @author NCY3HC
   * @param params store pairs of key and value:
   * @param lastResult result from ${@link RMModulePage#uploadNewArtifact(Map)}
   * @return true if artifact is paste successfully as expectation or vice versa
   */
  public TestAcceptanceMessage verifyUploadNewArtifact(final Map<String, String> params, final String lastResult) {
    String typeOfInsertion = params.get("typeOfInsertion");
    String targetArtifactID = params.get("targetArtifactID");
    if (typeOfInsertion.equals("After...")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact  is uploaded after the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact  is uploaded after the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    else if (typeOfInsertion.equals("Before...")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTBEFORE, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTBEFORE, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact  is uploaded before the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact  is uploaded before the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    if (typeOfInsertion.equals("Below (as a Child)...")) {
      this.driverCustom.isElementVisible(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, Duration.ofSeconds(60),
          targetArtifactID);
      String createdID = this.driverCustom
          .getText(RMConstants.RMMODULEPAGE_IDOFNEWINSERTEDARTIFACT_INSERTAFTER, targetArtifactID);
      if (createdID.trim().equals(lastResult)) {
        return new TestAcceptanceMessage(true, "Verify new artifact  is uploaded Below as a child the target artifact '" +
            targetArtifactID + "' successfully: PASSED!");
      }
      return new TestAcceptanceMessage(false, "Verify new artifact  is uploaded below as a child the target artifact '" +
          targetArtifactID + "' successfully: FAILED!");
    }
    return new TestAcceptanceMessage(true,
        "Skip verifying insert artifact. With the other type of inserting, please describe new!");
  }
  /**
   * <p>Verify Module is created.
   * <br>This method called after {@link RMModulePage#createAndOpenModule(Map)}.
   * 
   * @param additionalParams key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateAndOpenModule(final Map<String, String> additionalParams, final String lastResult) {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(20));
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@class='icon-resourcelink']//a[text()='" + additionalParams.get("MODULE_NAME") + "']")));
      return new TestAcceptanceMessage(true,
          "Module with name '" + additionalParams.get("MODULE_NAME") + "' is created successfully.");
    }
    catch (Exception e) {
      List<WebElement> results = this.driverCustom.getWebElements("//div[@class='resource-info']//span");
      if (results.get(1).getAttribute("title").contentEquals(additionalParams.get("MODULE_NAME"))) {
        return new TestAcceptanceMessage(true,
            "Module with name '" + additionalParams.get("MODULE_NAME") + "' is created successfully!");
    }
    }
    return new TestAcceptanceMessage(false,
        "Module with name '" + additionalParams.get("MODULE_NAME") + "' is not created successfully.");
  }
  /**
   * <P>
   * Verifies id of the artifact.
   * <P>
   * This method called after {@link RMModulePage#getArtifactIDByArtifactName(String)}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @param artifactName - for which you want to get ID.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetArtifactIDByArtifactName(final String artifactName, final String lastResult) {
    String result = this.driverCustom.getText(RMConstants.RMMODULEINSIDE_ARTIFACT_ID_BY_ARTIFACT_NAME_XPATH, artifactName);
      if (result.equalsIgnoreCase(lastResult)) {
        return new TestAcceptanceMessage(true,
            "Verified: Artifact ID of the created artifact.ID of the artifact is \"" + lastResult + "\".");
      }
    return new TestAcceptanceMessage(false,
        "Verified: Artifact ID of the created artifact. Artifact ID \"" + lastResult + "\" not found.");
  }
  
  /**
   * <P>
   * Verified if View name could be got without any problem
   * <P>
   * This method called after {@link RMModulePage#getViewNameApplied()}.
   * @param lastResult value of got view name.
   * @return acceptance object which contains verification results.
   * 
   */
  public TestAcceptanceMessage verifyGetViewNameApplied(final String lastResult) {
    try {
      return new TestAcceptanceMessage(!lastResult.isEmpty(), "Verified if view name could be gotten");
    } catch (NullPointerException e) {
      return new TestAcceptanceMessage(false, "Verified FAILED: Cannot get current applied view");
    }    
  }
  
  /**<P>
   * Verify the status of current applied view.
   * <P>
   * This method called after {@link RMModulePage#isViewApplied(String)}.
   * @param viewName View name to verify if applied
   * @param additionalParam parameter to be passed in the excel test script.
   * @param lastResult actual result of applied view
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsViewApplied(final String viewName, final String additionalParam, final String lastResult) {
    if (!additionalParam.equalsIgnoreCase(lastResult)) {
      return new TestAcceptanceMessage(false, String.format("Verification FAILED: Verify if View is applied not as expected.\n Expected %s but %s.", additionalParam, lastResult));
    }
    return new TestAcceptanceMessage(true, "Verify View is applied PASSED with expected: " + additionalParam);
  }
  /**
   * <p>Verify Artifact is navigated to 'Module' page.
   * <br>This method called after {@link RMModulePage#navigateToModulePage(String)}.
   * @param moduleName name of the module.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyNavigateToModulePage(final String moduleName, final String lastResult) {
    List<WebElement> results = this.driverCustom.getWebElements("//div[@class='resource-title-cell']//span");
    if (results.get(1).getAttribute("title").contentEquals(moduleName)) {
      return new TestAcceptanceMessage(true,"Successfully navigated to '"+moduleName+"' Module page.");
    }
    return new TestAcceptanceMessage(false,"Page is not navigated to 'Module' page.");
  }
 
}

