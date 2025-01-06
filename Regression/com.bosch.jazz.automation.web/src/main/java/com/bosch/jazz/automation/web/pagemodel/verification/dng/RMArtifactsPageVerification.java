/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.excel.TimeZoneConverter;
import com.bosch.jazz.automation.web.pagemodel.AbstractWebPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.utility.ExcelCSVReader;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Listbox;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.ButtonFinder;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.ListboxFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextFinder;
import finder.text.label.JazzSpanLabelFinder;
import finder.text.textField.JazzSpanTextFieldFinder;


/**
 * @author BFE6SI
 */
public class RMArtifactsPageVerification extends AbstractWebPage {

  private final Duration timeInSecs = this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime();

  /**
   * @param driverCustom must not be null.
   */
  public RMArtifactsPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTreeNodeFinder(), new JazzDropdownFinder(), new JazzButtonFinder(),
        new JazzTextFinder(), new JazzSpanLabelFinder(), new JazzRowFinder(driverCustom.getWebDriver()),
        new JazzDialogFinder(), new RowCellFinder(), new LinkFinder(), new JazzSpanTextFieldFinder(), new TextFinder(),
        new JazzTabFinder(), new ListboxFinder(), new CheckboxFinder(), new ButtonFinder(),
        new JazzRadioButtonFinder());
  }

  /**
   * <P>
   * Verifies type of the artifact.
   * <P>
   * 
   * 
   * This method called after {@link RMArtifactsPage#clickOnArtifactTypes(String)}
   *
   * @param Type of the artifact
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnArtifactTypes(final String Type, final String lastResult) {
    List<String> results = this.driverCustom.getWebElements("//div[@class='page-navigation']//a").stream()
        .map(x -> x.getAttribute("class") + "=" + x.getText()).collect(Collectors.toList());
    if ((results != null) && results.stream().anyMatch(x -> (x != null) && x.contentEquals("current=" + Type))) {

      return new TestAcceptanceMessage(true, "Verified selected type of 'Artifacts'.\nActual Result is \"" + Type +
          "\" " + "current selected artifact and also matched with Expected result \"" + Type + "\".");
    }

    return new TestAcceptanceMessage(false,
        "Artifact type is not matched with current artifact type \"" + Type + "\".");
  }

  /**
   * <P>
   * Verifies all the attribute displayed in 'Overview' section of the artifact.
   * <P>
   * This method called after {@link RMArtifactsPage#getRMAttributeValue}.
   *
   * @param additionalParams key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetRMAttributes(final Map<String, String> additionalParams,
      final String lastResult) {
    if (!lastResult.isEmpty()) {
      String attribute = RMConstants.KEY + RMConstants.CREATED_ON + RMConstants.VALUE;
      StringBuilder acceptanceMessage = new StringBuilder();
      acceptanceMessage.append("Verified attributes and values of rm specification.\n\n");
      for (String key : additionalParams.keySet()) {
        if (lastResult.contains(attribute) && key.equalsIgnoreCase("Created On")) {
          String value = new RMArtifactsPage(this.driverCustom).getRMAttributeValue("Created On");
          String afterConversion = TimeZoneConverter.changeTimezone(value, "Asia/Kolkata", 0);
          String resultAfterConversion = lastResult;
          if (resultAfterConversion.contains(RMConstants.KEY + key + RMConstants.VALUE + additionalParams.get(key))) {
            resultAfterConversion =
                resultAfterConversion.replace(RMConstants.KEY + key + RMConstants.VALUE + additionalParams.get(key),
                    RMConstants.KEY + key + RMConstants.VALUE + afterConversion);
          }
          if (resultAfterConversion.contains("KEY :" + key + " VALUE :" + afterConversion)) {
            acceptanceMessage.append(RMConstants.ACTUAL_VALUE_OF_ATTRIBUTE + key + RMConstants.IS + value +
                "' converted to Asia/Kolkata time zone '" + afterConversion + "'.Expected value of attribute '" + key +
                RMConstants.IS + value + ".\n\n");
          }

          LOGGER.LOG.info("Value Before Convertion of Time Zone" + value);
          LOGGER.LOG
          .info("Value After Convertion of Time Zone" + TimeZoneConverter.changeTimezone(value, "Asia/Kolkata", 0));
        }
        else if (lastResult.contains("KEY :" + key + " VALUE :" + additionalParams.get(key))) {
          acceptanceMessage
          .append(RMConstants.ACTUAL_VALUE_OF_ATTRIBUTE + key + RMConstants.IS + additionalParams.get(key) +
              "'\nExpected value of attribute '" + key + RMConstants.IS + additionalParams.get(key) + "'.\n\n");
        }
        else if (key.contains("Status") &&
            lastResult.contains("KEY :State (Default) VALUE :" + additionalParams.get(key))) {
          acceptanceMessage
          .append(RMConstants.ACTUAL_VALUE_OF_ATTRIBUTE + key + RMConstants.IS + additionalParams.get(key) +
              "'\nExpected value of attribute '" + key + RMConstants.IS + additionalParams.get(key) + "'.\n\n");
        }
        else if (additionalParams.get(key).equalsIgnoreCase("null")) {
          // Put "null" to ignore an unuse attribute in excel file instead of cloning excel script file.
        }
        else {
          acceptanceMessage.append(RMConstants.ACTUAL_VALUE_OF_ATTRIBUTE + key + RMConstants.IS +
              additionalParams.get(key) + "' not matched with expected.\n\n");
        }
      }
      String msg = acceptanceMessage.toString();
      return new TestAcceptanceMessage(!msg.contains("' not matched with expected."), msg);
    }
    return new TestAcceptanceMessage(false, "Actual result is empty.");

  }

  /**
   * <P>
   * Verifies artifact id is filtered in 'Type To Filter' text box.
   * <P>
   * This method called after {@link RMArtifactsPage#filterArtifactByTextOrId(String)}.
   *
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyFilterArtifactByTextOrId(final String artifactID, final String lastResult) {
    try {
      this.engine.findElementWithDuration(Criteria.isRow().withText(artifactID) , this.timeInSecs).getFirstElement();
      return new TestAcceptanceMessage(true, "Searched artifact found.\nActual artifact id \"" + artifactID +
          "\" is matched with \nExpected artifact id\"" + artifactID + "\".");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Searched artifact not found.\nActual artifact id \"" + artifactID +
          "\" is not matched with \nExpected artifact id\"" + artifactID + "\".");
    }

  }

  /**
   * <P>
   * Verifies artifact is opened.
   * <P>
   * This method called after {@link RMArtifactsPage#openRMSpecification(String)}.
   *
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenRMSpecification(final String artifactID, final String lastResult) {
    if (this.driverCustom.isElementPresent("(//span[starts-with(@class,'section-header section-header-collapsed')])[1]",
        Duration.ofSeconds(10))) {
      this.driverCustom.clickUsingActions(this.driverCustom
          .getWebElement("(//span[starts-with(@class,'section-header section-header-collapsed')])[1]"));
    }


    List<String> results =
        this.driverCustom.getWebElements("//div[@class='resource-title-node']//span[contains(@class,'resource')]")
        .stream().map(x -> x.getText()).collect(Collectors.toList());
    if ((results != null) && results.stream().anyMatch(x -> (x != null) && x.contains(artifactID))) {
      return new TestAcceptanceMessage(true, "Verified searched artifact is opened.\nOpened artifact id \"" +
          artifactID + "\"matched with artifact \"" + results + "\" displayed in Info of the artifact.");
    }
    return new TestAcceptanceMessage(false, "Verified searched artifact is opened.\nOpened artifact id \"" +
        artifactID + "\" not matched with artifact displayed in Info of the artifact.");

  }

  /**
   * <P>
   * Verifies summary of the artifact displayed in 'Overview' section of the artifact.
   * <P>
   * This method called after {@link RMArtifactsPage#getRMSpecificationsSummary}.
   *
   * @param additionalParams data
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifyGetRMSpecificationsSummary(final String additionalParams,
      final String lastResult) {
    if (!lastResult.isEmpty()) {
      LOGGER.LOG.info("Expected result " + additionalParams);
      LOGGER.LOG.info("Actual result " + lastResult);
      return new TestAcceptanceMessage(lastResult.contains(additionalParams.trim()),
          "Verified summary of the artifact from 'Overview' section.\nActual result is \"" + lastResult +
          "\"\nExpected result is \"" + additionalParams + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified summary of the artifact from 'Overview' section.\nActual result is \"" + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#searchView(String)}.
   *
   * @param viewname name
   * @param additionalParams data
   * @param lastResult last result of {@link RMArtifactsPage#searchView(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySearchView(final String viewname, final String additionalParams,
      final String lastResult) {
    if (additionalParams.equalsIgnoreCase("true")) {
      try {
        this.engine.findElementWithDuration(Criteria.isListbox().containText(viewname), Duration.ofSeconds(5)).getFirstElement();
        return new TestAcceptanceMessage(true, "Verified: Searched View is displayed.\nActual Result '" + viewname +
            " view is displayed'.\nExpected Result '" + viewname + "' view should be displayed.");

      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false,
            "Verified displayed view after searching.\nActual is searched view not found by inputed keyword '" +
                viewname + "'.\nExpected is searched view should be found.");
      }
    }
    else if (additionalParams.equalsIgnoreCase("false")) {
      try {
        this.engine.findElementWithDuration(Criteria.isListbox().containText(viewname), Duration.ofSeconds(5)).getFirstElement();
        return new TestAcceptanceMessage(false,
            "Verified displayed view after searching.\nActual is searched view found by inputted keyword '" + viewname +
            "'.\nExpected is searched view should not be found.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(true,
            "Verified displayed view after searching.\nActual is searched view not found by inputted keyword '" +
                viewname + "'.\nExpected is searched view should not be found.");
      }
    }
    return new TestAcceptanceMessage(false, "Search view did not work, search field still available");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectView(String)}.
   *
   * @param viewname name
   * @param lastResult last result of {@link RMArtifactsPage#selectView(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectView(final String viewname, final String lastResult) {
    this.driverCustom.getPresenceOfWebElement("//div[@dojoattachpoint='moduleContentPane']");
    if (!this.driverCustom.isElementVisible("//span[@aria-selected='true' and text()='" + viewname + "']", Duration.ofSeconds(5))) {
      List<String> headerLists = this.driverCustom.getWebElements(RMConstants.VIEW_COLUMN_HEADER_XPATH).stream()
          .map(x -> x.getText()).collect(Collectors.toList());
      List<WebElement> viewColumnHeaders = this.driverCustom.getWebDriver()
          .findElements(By.xpath("//div[@class='result-set-grid-view']//td[@role='columnheader']"));
      List<String> columnheaders = new ArrayList<>();
      viewColumnHeaders.stream().map(WebElement::getText).forEach(columnheaders::add);
      StringBuilder sb = new StringBuilder();
      sb.append("Verifed: Searched view is selected.\nActual Result'" + viewname + "' view is selected.");
      sb.append("\n\nColumns displayed for the view are -" + String.join(",", headerLists));
      sb.append("\nExpected columns to be displayed for the view are - " + String.join(",", headerLists));
      for (int i = 2; i < columnheaders.size(); i++) {
        String currentheader = columnheaders.get(i);
        if (columnheaders.stream().anyMatch(x -> x.equals(currentheader))) {
          try {
            List<String> allLists = this.driverCustom
                .getWebElements("//table[@role='row presentation']//tr[1]//td[DYNAMIC_VAlUE]", Integer.toString(i + 3))
                .stream().map(x -> x.getText().trim()).collect(Collectors.toList());
            if (currentheader.equals("Artifact Type")) {
              sb.append("\n\nUnder 'Artifact Type' column available column value '" + allLists.get(0) +
                  "' which is same as filter added in the view.");
            }
            else {
              sb.append(
                  "\n\nUnder '" + columnheaders.get(i) + "'column links available are -" + String.join(",", allLists));

            }
          }
          catch (Exception e) {
          }
        }
      }

      return new TestAcceptanceMessage(true, sb.toString());
    }
    return new TestAcceptanceMessage(false,
        "Verify a view after selecting.\nActual is the '" + viewname + "' view is not selected");

  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectArtifact(String)}.
   *
   * @param id id
   * @param lastResult last result of {@link RMArtifactsPage#selectArtifact(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectArtifact(final String id, final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//table[contains(@class,'row-checked') and @aria-label='Artifact " + id + " selected.']", Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true,
          "Verify artifact after selecting.\nActual is the '" + id + "' artifact is selected");
    }
    return new TestAcceptanceMessage(false,
        "Verify artifact after selecting.\nActual is the '" + id + "' artifact is not selected");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#deSelectArtifact(String)}.
   *
   * @param id id
   * @param lastResult last result of {@link RMArtifactsPage#selectArtifact(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyDeSelectArtifact(final String id, final String lastResult) {
    if (!this.driverCustom.isElementVisible(
        "//table[contains(@class,'row-checked') and @aria-label='Artifact " + id + " selected.']", Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true,
          "Verify artifact after deSelecting.\nActual is the '" + id + "' artifact is deSelected");
    }
    return new TestAcceptanceMessage(false,
        "Verify artifact after deSelecting.\nActual is the '" + id + "' artifact is selected");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectMultipleArtifact(String)}.
   *
   * @param numberOFArtifacts number of artifacts to be selected.
   * @param lastResult last result of {@link RMArtifactsPage#selectArtifact(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectMultipleArtifact(final String numberOFArtifacts, final String lastResult) {
    List<String> idsList =
        this.driverCustom.getWebElementsText("//table[@rowType='resourceRow']//td[@class='id-col']//a");
    if (Integer.parseInt(numberOFArtifacts) > idsList.size()) {
      throw new WebAutomationException("Number of artifacts to be selected is " + numberOFArtifacts +
          " but there are only " + idsList.size() + " artifacts displayed.");
    }
    StringBuilder sb = new StringBuilder();
    sb.append("Verify artifact after selecting");
    int count = Integer.parseInt(numberOFArtifacts);
    int i = 0;
    for (String id : idsList) {
      if (i < count) {
        if (this.driverCustom.isElementVisible(
            "//table[contains(@class,'row-checked') and @aria-label='Artifact " + id + " selected.']", Duration.ofSeconds(5))) {
          sb.append("\nActual is the '" + id + "' artifact is selected");
        }
        else {
          sb.append("\nActual is the '" + id + "' artifact id is not selected");
        }
      }

    }
    return new TestAcceptanceMessage(true, sb.toString());

  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)}.
   *
   * @param artifact artifact
   * @param option option
   * @param lastResult result
   * @return true or false.
   */
  public TestAcceptanceMessage verifyOpenContextMenuForSelectedArtifacts(final String artifact, final String option,
      final String lastResult) {
    return verifyOpenContextMenuForSelectedArtifacts(artifact, option, null, lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)}.
   *
   * @param artifact artifact
   * @param option option
   * @param additionalParams data
   * @param lastResult last Result last result of
   *          {@link RMArtifactsPage#openContextMenuForSelectedArtifact(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyOpenContextMenuForSelectedArtifacts(final String artifact, final String option,
      final String additionalParams, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if ((additionalParams != null) && additionalParams.contains("Locked Artifacts")) {
      try {
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Locked Artifacts"),timeInSecs).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_ARTIFACT_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Locked Artifacts' dialog is displayed as expected.");

      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Locked Artifacts' dialog is not displayed as expected.");
      }
    }
    if ((additionalParams != null) && additionalParams.contains("Manually Lock Artifact for Editing")) {
      try {
        // Reopen context menu of selected artifact
        this.driverCustom.click("//td[@aria-label='Menu button for artifact DYNAMIC_VAlUE']//img", artifact);
        // Check for option Manually Lock Artifact for Editing
        String optionXpath = "(//table[contains(@class,'dijitMenuActive')] | //table[contains(@class,'dijitMenuSelected ')])//td[contains(text(),'Unlock Artifact')]";
        this.driverCustom.isElementVisible(optionXpath, this.timeInSecs);
        // Click to exit context menu
        this.driverCustom.click("//div[@class='toggle']");
        return new TestAcceptanceMessage(true, "Artifact ID" + artifact + " is unlocked as expected.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, "Artifact ID" + artifact + " is unlocked");
      }
    }
    if ((additionalParams != null) && additionalParams.contains("Unlock Artifact")) {
      try {
        this.engine.findFirstElementWithDuration(Criteria.isRow().containsText(artifact), this.timeInSecs);
        this.driverCustom.click("//td[@aria-label='Menu button for artifact DYNAMIC_VAlUE']//img", artifact);
        // Check for option Manually Lock Artifact for Editing
        String optionXpath = "(//table[contains(@class,'dijitMenuActive')] | //table[contains(@class,'dijitMenuSelected ')])//td[contains(text(),'Manually Lock Artifact for Editing')]";
        this.driverCustom.isElementVisible(optionXpath, this.timeInSecs);
     // Click to exit context menu
        this.driverCustom.click("//div[@class='toggle']");
        return new TestAcceptanceMessage(true, "Artifact ID" + artifact + " is unlocked as expected.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, "Artifact ID" + artifact + " is still locked");
      }
    }
    if (option.contains("Generate Report for Artifact") || option.equals("Generate Report for 2 Artifact...")) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_A_REPORT)).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            RMConstants.ACTUAL_IS_THE_CREATE_A_REPORT_DIALOG_IS_DISPLAYED_AS_EXPECTED);
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            RMConstants.ACTUAL_IS_THE_CREATE_A_REPORT_DIALOG_IS_NOT_DISPLAYED_AS_EXPECTED);
      }
    }
    if (option.contains("Edit the attributes") || option.equals("Edit Attributes...")) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle("Edit Attributes")).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Edit Attributes' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Edit Attributes' dialog is not displayed as expectation.");
      }
    }
    if (option.equals("Edit Artifact Row")) {
      if (this.driverCustom.isElementVisible("//div[@role='textbox' and @contenteditable='true']", Duration.ofSeconds(5))) {
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_ARTIFACT_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the artifact row is enable for edit as expectation.");
      }
      return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_ARTIFACT_AFTER_CLICK_ON_THE + option +
          "' of artifact.\nActual is the artifact row is not enable for edit as expectation.");
    }
    if (option.contains("Select Tags")) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle("Select Tags")).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Select Tags' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Select Tags' dialog is not displayed as expectation.");
      }
    }
    if (option.contains("Copy")) {
      if (this.driverCustom.isElementVisible("//table[contains(@rowlabel,'Artifact ') and contains(@class,'row-copy')]",
          Duration.ofSeconds(5))) {
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_ARTIFACT_AFTER_CLICK_ON_THE + option +
            RMConstants.OF_ARTIFACT_ACTUAL_IS_THE + artifact + "' artifact is copied as expectation.");
      }
      return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_ARTIFACT_AFTER_CLICK_ON_THE + option +
          RMConstants.OF_ARTIFACT_ACTUAL_IS_THE + artifact + "' artifact is not copied as expectation.");
    }
    if (option.contains("Cut")) {
      if (this.driverCustom.isElementVisible("//table[contains(@rowlabel,'Artifact ') and contains(@class,'row-copy')]",
          Duration.ofSeconds(5))) {
        return new TestAcceptanceMessage(true, "Verify the artifact after click on the '" + option +
            "' of artifact.\nActual is the '" + artifact + "' artifact is cut as expectation.");
      }
      return new TestAcceptanceMessage(false, "Verify the artifact after click on the '" + option +
          "' of artifact.\nActual is the '" + artifact + "' artifact is not cut as expectation.");
    }
    if (option.contains("Remove")) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle("Confirm Removal")).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Confirm Removal' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Confirm Removal' dialog is not displayed as expectation.");
      }
    }
    if (option.contains("Delete Artifact")) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle("Confirm Deletion")).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Confirm Deletion' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Confirm Removal' dialog is not displayed as expectation.");
      }
    }
    if (option.contains(RMConstants.EXPORT)) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle("Export")).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Export' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Export' dialog is not displayed as expectation.");
      }
    }
    if (option.contains(RMConstants.LINK_BY_ATTRIBUTE)) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle("Link by Attribute")).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Link by Attribute' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Link by Attribute' dialog is not displayed as expectation.");
      }
    }
    if (option.contains("Generate Report")) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_A_REPORT)).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Create a Report' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
            "' of artifact.\nActual is the 'Create a Report' dialog is not displayed as expectation.");
      }
    }
    if (option.contains("Add Link to Artifact")) {
      boolean result = this.driverCustom
          .isElementVisible("//td[contains(text(),'Satisfies')]/span[@aria-label='Outgoing link']", this.timeInSecs);
      return new TestAcceptanceMessage(result, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + option +
          "' of artifact.\nActual is the second dialog is displayed as expectation: " + result);
    }
    return new TestAcceptanceMessage(false, RMConstants.THE + option + "' does not match");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#editArtifactAttribute(String, String)}.
   *
   * @param attributeName NAME
   * @param newValue VALUE
   * @param lastResult last result of {@link RMArtifactsPage#editArtifactAttribute(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyEditArtifactAttribute(final String attributeName, final String newValue,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verify value of the '" + attributeName +
          "' should be updated to new value is '" + newValue + RMConstants.ACTUAL_IS + lastResult);
    }
    return new TestAcceptanceMessage(false, "Verify value of the '" + attributeName +
        "' should be updated to new value is '" + newValue + "'.\nActual is " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#verifyArtifactValuesInModuleView(String, String, String)}.
   *
   * @param additionalPram DATA
   * @param artifactID id
   * @param columnName name
   * @param value value
   * @param lastResult last result of {@link RMArtifactsPage#verifyArtifactValuesInModuleView(String, String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyVerifyArtifactValuesInModuleView(final String artifactID, final String columnName,
      final String value, final String additionalPram, final String lastResult) {
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalPram.toLowerCase()), "Verified the '" +
        columnName + RMConstants.OF + artifactID + "' artifact is '" + value + "'.\nActual is " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#verifyEditAttributeSuccessMessage(String)}.
   *
   * @param message message
   * @param additionalPram data
   * @param lastResult last result of {@link RMArtifactsPage#verifyEditAttributeSuccessMessage(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyVerifyEditattributeSuccessMessage(final String message,
      final String additionalPram, final String lastResult) {
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalPram),
        "Verify that the '" + message + " is displayed.\nActual is " + lastResult);
  }

  /**
   * <p>
   * Verifies the action
   *
   * @param columnName column Name.
   * @param additionalPram additional Parameter.
   * @param lastResult last result
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsColoumAddedToTable(final String columnName, final String additionalPram,
      final String lastResult) {
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalPram),
        "Verified that the '" + columnName + " is displayed in table.\nActual is " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}.
   *
   * @param view view
   * @param dropdownValue drop down Value
   * @param lastResult {@link RMArtifactsPage#selectOptionFromViewOptionsDropdown(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectValueFromViewDropdown(final String view, final String dropdownValue,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    switch (dropdownValue) {
      case RMConstants.EDIT_VIEW:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle("Edit Shared View")).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Edit Shared View' dialog is displayed as expectation.");
        }
        catch (Exception e1) {
          try {
            this.engine.findElement(Criteria.isDialog().withTitle("Edit Personal View")).getFirstElement();
            return new TestAcceptanceMessage(true,
                RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue +
                "' of artifact.\nActual is the 'Edit Personal View' dialog is displayed as expectation.");
          }
          catch (Exception e2) {
            return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE +
                dropdownValue + RMConstants.OF + view +
                "' view.\nActual is the 'Edit Shared View'/'Edit Personal view' dialog is not displayed as expectation.");
          }
        }
      case RMConstants.GENERAL_REPORT_FOR_VIEW:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle("Create a Report")).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Create a Report' dialog is displayed as expectation.");
        }
        catch (Exception e1) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + "' of '" + view +
              "' view.\nActual is the 'Create a Report' dialog is not displayed as expectation.");
        }
      case RMConstants.EXPORT_VIEW:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle("Export")).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Export' dialog is displayed as expectation.");
        }
        catch (Exception e1) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Export' dialog is not displayed as expectation.");
        }
      case RMConstants.EDIT_ATTRIBUTE_FROM_VIEW:
      case "Edit Attributes from View":
        try {
          this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Edit Attributes"), timeInSecs);
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Edit Attributes' dialog is displayed as expectation.");
        }
        catch (Exception e1) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Edit Attributes' dialog is not displayed as expectation.");
        }
      case RMConstants.LINK_BY_ATTRIBUTE_TEXT:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle("Link by Attribute")).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Link by Attribute' dialog is displayed as expectation.");
        }
        catch (Exception e1) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Link by Attribute' dialog is not displayed as expectation.");
        }
      case RMConstants.DELETE_VIEW:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle("Delete View")).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Delete View' dialog is displayed as expectation.");
        }
        catch (Exception e1) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Delete View' dialog is not displayed as expectation.");
        }
      case RMConstants.SHARE_LINK_TO_VIEW:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle("Share Link")).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Share Link' dialog is displayed as expectation.");
        }
        catch (Exception e1) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE + dropdownValue + RMConstants.OF + view +
              "' view.\nActual is the 'Share Link' dialog is not displayed as expectation.");
        }
      default:
        return new TestAcceptanceMessage(false, RMConstants.THE + dropdownValue + "' option     ");
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clickOnEditAttributeCheckbox(String)}.
   *
   * @param attributename name
   * @param lastResult last result of {@link RMArtifactsPage#clickOnEditAttributeCheckbox(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnEditAttributeCheckbox(final String attributename, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    switch (attributename) {
      case RMConstants.ARTIFACTS_TYPE:
        if (this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_ARTIFACT_TYPE_TEXTBOX_XPATH, Duration.ofSeconds(5),
            RMConstants.ARTIFACTS_TYPE)) {
          return new TestAcceptanceMessage(true, "The dropdown menu is displayed after Artifact Type is selected.");
        }
        return new TestAcceptanceMessage(false, "The dropdown menu is not displayed after Artifact Type is selected.");
      case RMConstants.DESCRIPTION:
        if (this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5), RMConstants.DESCRIPTION)) {
          return new TestAcceptanceMessage(true, "The textarea is displayed after Description is selected.");
        }
        return new TestAcceptanceMessage(false, "The textarea is not displayed after Description is selected.");
      case RMConstants.CUSTOMER_ID:
        if (this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5), RMConstants.CUSTOMER_ID)) {
          return new TestAcceptanceMessage(true, "The textarea is displayed after Customer ID is selected.");
        }
        return new TestAcceptanceMessage(false, "The textarea is not displayed after Customer ID is selected.");
      case RMConstants.CUSTOMER_COMMENT:
        if (this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5),
            RMConstants.CUSTOMER_COMMENT)) {
          return new TestAcceptanceMessage(true, "The textarea is displayed after Customer Comment is selected.");
        }
        return new TestAcceptanceMessage(false, "The textarea is not displayed after Customer Comment is selected.");
      case RMConstants.HISTORY_LOG:
        if (this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5), RMConstants.HISTORY_LOG)) {
          return new TestAcceptanceMessage(true, "The textarea is displayed after History Log is selected.");
        }
        return new TestAcceptanceMessage(false, "The textarea is not displayed after History Log is selected.");
      case "Tags":
        if (this.driverCustom.isElementVisible(RMConstants.RMPROJECTAREADASHBOARDPAGE_MENUITEM_XPATH, Duration.ofSeconds(5), "Edit Tags")) {
          return new TestAcceptanceMessage(true, "The 'Edit Tags' button is displayed after Tags is selected.");
        }
        return new TestAcceptanceMessage(false, "The 'Edit Tags' button is not displayed after Tags is selected.");
      default:
        return new TestAcceptanceMessage(false, RMConstants.THE + attributename + "' does not match.");
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#setAttributeValue(String, String)}.
   *
   * @param attributename name of attribute.
   * @param text value
   * @param lastResult last result of {@link RMArtifactsPage#setAttributeValue(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySetAttributeValue(final String attributename, final String text,
      final String lastResult) {
    if (lastResult.equals(text)) {
      return new TestAcceptanceMessage(lastResult.contains(text),
          "Description has been updated by new value is '" + text + "'");
    }
    return new TestAcceptanceMessage(false, "Description has not been update by new value is '" + text + "'");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectTags(String)}.
   *
   * @param text value
   * @param lastResult last result of {@link RMArtifactsPage#selectTags(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySelectTag(final String text, final String lastResult) {
    if (this.driverCustom.isElementVisible("//span[text()='" + text + "' and @aria-selected='true']", Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true, RMConstants.THE + text + "' is selected as expected.");
    }
    return new TestAcceptanceMessage(false, RMConstants.THE + text + "' is not selected as expected.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clearSelectedTags()}.
   *
   * @param lastResult last result of {@link RMArtifactsPage#clearSelectedTags()}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClearSelectedTags(final String lastResult) {
    if (this.driverCustom.isElementInvisible(
        "//div[@title='Select Tags']/following::span[contains(@class,'tag-name') and @aria-selected='true']", Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true, "All selected tags are cleared");
    }
    return new TestAcceptanceMessage(false, "All selected tags are not cleared");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#waitForHundredPercentLoad()}.
   *
   * @param lastResult last result of {@link RMArtifactsPage#waitForHundredPercentLoad()}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyWaitForHundredPercentLoad(final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Edit artifact has completed 100%");
    }
    return new TestAcceptanceMessage(false, "Edit artifact has not completed 100%");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#changeColumnDisplaySettings(String)}.
   *
   * @param addLinkToArtifact stores different link type to add 'Validated By','Implemented By' etc...
   * @param lastResult last result of {@link RMArtifactsPage#changeColumnDisplaySettings(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyChangeColumnDisplaySettings(final String addLinkToArtifact,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));

    // Apply for Column without Column Name displayed in the table. Ex: Used in Modules,...
    if (addLinkToArtifact.equals("Used in Modules")) {
      try {
        this.driverCustom
        .getPresenceOfWebElement("//td[@role='columnheader' and contains(@title,'" + addLinkToArtifact + "')]");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false,
            RMConstants.THE + addLinkToArtifact + "' column is not displayed in table.");
      }
      try {
        List<WebElement> valuesInColumn = this.driverCustom.getWebElements("//td[@colid='reuseCount']//img");
        return new TestAcceptanceMessage(true,
            "Verified: Column is added and have " + valuesInColumn.size() + " links available for the column.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(true, "Verified: Column is added but no links available for the column.");
      }
    }

    // Apply for Column with Column Name displayed in the table.
    List<WebElement> viewColumnHeaders = this.driverCustom.getWebDriver()
        .findElements(By.xpath("//div[@class='result-set-grid-view']//td[@role='columnheader']"));
    List<String> columnheaders = new ArrayList<>();
    for (int i = 0; i < viewColumnHeaders.size(); i++) {
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView();",
          viewColumnHeaders.get(i));
      columnheaders.add(viewColumnHeaders.get(i).getText());
    }
    StringBuilder sb = new StringBuilder();
    if (columnheaders.stream().anyMatch(x -> x.equals(addLinkToArtifact))) {
      for (int i = 0; i < columnheaders.size(); i++) {
        if (columnheaders.get(i).equals(addLinkToArtifact)) {
          try {
            List<String> allLists = this.driverCustom
                .getWebElements("//table[@role='row presentation']//tr[1]//td[DYNAMIC_VAlUE]", Integer.toString(i + 3))
                .stream().map(x -> x.getText()).collect(Collectors.toList());
            List<String> allIdLists =
                this.driverCustom.getWebElements("//table[@role='row presentation']//tr[1]//td[@class='id-col']")
                .stream().map(x -> x.getText()).collect(Collectors.toList());
            boolean message = true;
            for (int j = 0; (j < allIdLists.size()) && (j < allLists.size()); j++) {
              if (addLinkToArtifact.equals("Artifact Type")) {
                if (message) {
                  sb.append(
                      "Verified: 'Artifact Type' column is added.Module is displayed according to the mentioned filtered criteria and the value added for the column is - " +
                          allLists.get(j));
                  message = false;
                }
              }
              else {
                sb.append("Artifact ID '" + allIdLists.get(j).trim() + "' linked to '" + allLists.get(j).trim() +
                    "' by " + addLinkToArtifact + " link.\n\n");
              }
            }
            return new TestAcceptanceMessage(true, sb.toString());
          }
          catch (Exception e) {
            return new TestAcceptanceMessage(true, "Verified:Column is added but no links available for the column.");
          }
        }
      }
    }
    return new TestAcceptanceMessage(false,
        RMConstants.THE + addLinkToArtifact + "' column is not displayed in table.");

  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getColumnDatafromTable(String)}.
   *
   * @param columnName name
   * @param additionalPram data
   * @param lastResult last result is {@link RMArtifactsPage#getColumnDatafromTable(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyGetColumnDatafromTable(final String columnName, final String additionalPram,
      final String lastResult) {

    if (lastResult.contains(additionalPram)) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFY_DATA_OF_THE + columnName + "' Column.\n\nActual value is '" + lastResult +
          RMConstants.EXPECTED_IS_ALL_VALUES_SHOULD_BE + additionalPram + "'.");
    }
    return new TestAcceptanceMessage(false, "Verify data of the '" + columnName + "' Column.\n\nActual value is '" +
        lastResult + "'.\n\nExpected is all values should be '" + additionalPram + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clearAttributeValue(String)}.
   *
   * @param attributename name
   * @param lastResult last result is {@link RMArtifactsPage#clearAttributeValue(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClearAttributeValue(final String attributename, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Description has been cleared");
    }
    return new TestAcceptanceMessage(false, "Description has not been cleared");
  }

  /**
   * <p>
   * Verifies the action
   *
   * @param additionalPram data
   * @param lastResult last result
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsSaveButtonInEditAttributeDialogVisible(final String additionalPram,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalPram)) {
      return new TestAcceptanceMessage(true,
          "Verify the 'Save' button in the 'Edit Attributes' dialog is visible.\nActual is " + lastResult);
    }
    return new TestAcceptanceMessage(false,
        "Verify the 'Save' button in the 'Edit Attributes' dialog is visible.\nActual is " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#uncheckAttributeInEditAttibutesDialog(String)}.
   *
   * @param attributeName name
   * @param lastResult last result is {@link RMArtifactsPage#uncheckAttributeInEditAttibutesDialog(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyUncheckAttributeInEditAttibutesDialog(final String attributeName,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));


    switch (attributeName) {

      case RMConstants.ARTIFACTS_TYPE:
        this.driverCustom.waitForSecs(Duration.ofSeconds(5));
        if (!this.driverCustom.isElementVisible("//span[@title='Artifact Type']/../../../..//input[@role='textbox']",
            Duration.ofSeconds(15))) {
          return new TestAcceptanceMessage(true,
              "The dropdown menu is not displayed after Artifact Type is unselected.");
        }
        return new TestAcceptanceMessage(false,
            "The dropdown menu is still displayed after Artifact Type is unselected.");
      case RMConstants.DESCRIPTION:
        if (!this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5), RMConstants.DESCRIPTION)) {
          return new TestAcceptanceMessage(true, "The textarea is not displayed after Description is unselected.");
        }
        return new TestAcceptanceMessage(false, "The textarea is still displayed after Description is unselected.");
      case RMConstants.CUSTOMER_ID:
        if (!this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5), RMConstants.CUSTOMER_ID)) {
          return new TestAcceptanceMessage(true, "The textarea is not displayed after Customer ID is unselected.");
        }
        return new TestAcceptanceMessage(false, "The textarea is still displayed after Customer ID is unselected.");
      case RMConstants.CUSTOMER_COMMENT:
        if (!this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5),
            RMConstants.CUSTOMER_COMMENT)) {
          return new TestAcceptanceMessage(true, "The textarea is not displayed after Customer Comment is unselected.");
        }
        return new TestAcceptanceMessage(false,
            "The textarea is still displayed after Customer Comment is unselected.");
      case RMConstants.HISTORY_LOG:
        if (!this.driverCustom.isElementVisible(RMConstants.EDIT_ATTRIBUTE_TEXTBOX_XPATH, Duration.ofSeconds(5), RMConstants.HISTORY_LOG)) {
          return new TestAcceptanceMessage(true, "The textarea is not displayed after History Log is unselected.");
        }
        return new TestAcceptanceMessage(false, "The textarea is still displayed after History Log is unselected.");
      case "Tags":
        if (!this.driverCustom.isElementVisible(RMConstants.RMPROJECTAREADASHBOARDPAGE_MENUITEM_XPATH,Duration.ofSeconds(5),
            "Edit Tags")) {
          return new TestAcceptanceMessage(true, "The 'Edit Tags' button is not displayed after Tags is unselected.");
        }
        return new TestAcceptanceMessage(false, "The 'Edit Tags' button is still displayed after Tags is unselected.");
      default:
        return new TestAcceptanceMessage(false, RMConstants.THE + attributeName + "' does not match.");
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clearFilter()}.
   *
   * @param lastResult last result is {@link RMArtifactsPage#clearFilter()}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClearFilter(final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CLEAR_FILTER_XPATH, Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(false, "The filter has not been clear.");
    }
    return new TestAcceptanceMessage(true, "The filter has been clear.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addFilter()}.
   *
   * @param lastResult last result is {@link RMArtifactsPage#addFilter()}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyAddFilter(final String lastResult) {
    Dialog dlgNewFilter = null;
    try {
      dlgNewFilter =
          this.engine.findElementWithinDuration(Criteria.isDialog().withTitle("New Filter"), Duration.ofSeconds(10)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (dlgNewFilter != null) {
      return new TestAcceptanceMessage(true, "The 'New Filter' is displayed after click on 'Add Filter' button.");
    }
    return new TestAcceptanceMessage(false, "The 'New Filter' is not displayed after click on 'Add Filter' button.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#searchAttributeInFilter(String)}.
   *
   * @param attribute name
   * @param lastResult last result is {@link RMArtifactsPage#searchAttributeInFilter(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySearchAttributeInFilter(final String attribute, final String lastResult) {
    Label lblAttributeType = null;
    try {
      lblAttributeType =
          this.engine.findElementWithinDuration(Criteria.isLabel().withText(attribute), Duration.ofSeconds(5)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (lblAttributeType != null) {
      return new TestAcceptanceMessage(true, RMConstants.THE + attribute + "' is displayed after searching.");
    }
    return new TestAcceptanceMessage(false, RMConstants.THE + attribute + "' is not displayed after searching.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#chooseAttributeValueForSelectedAttribute(String, String)}.
   *
   * @param attribute name
   * @param value type
   * @param lastResult last result is {@link RMArtifactsPage#chooseAttributeValueForSelectedAttribute(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyAddAndCloseValueForAttributeInFilter(final String attribute, final String value,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.FILTER_XPATH, Duration.ofSeconds(10), value)) {
      return new TestAcceptanceMessage(true,
          RMConstants.THE_FILTER_WITH_THE + attribute + RMConstants.IS + value + "' is added.");
    }
    return new TestAcceptanceMessage(false,
        RMConstants.THE_FILTER_WITH_THE + attribute + RMConstants.IS + value + "' is not added.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addAndCloseListValueForAttributeInFilter(String, String)}.
   *
   * @param attribute name
   * @param value type
   * @param lastResult last result is {@link RMArtifactsPage#addAndCloseListValueForAttributeInFilter(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyAddValueForAttributeInFilter(final String attribute, final String value,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.FILTER_XPATH, Duration.ofSeconds(10), value)) {
      return new TestAcceptanceMessage(true, "The filter with the '" + attribute + "' is '" + value + "' is added.");
    }
    return new TestAcceptanceMessage(false, "The filter with the '" + attribute + "' is '" + value + "' is not added.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#handleAlert()}.
   *
   * @param driver driver
   * @param timeout timeout
   * @param lastResult last result is {@link RMArtifactsPage#handleAlert()}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyHandleAlert(final WebDriver driver, final Duration timeout, final String lastResult) {
    try {
      WebDriverWait tempWait = new WebDriverWait(driver, timeout);
      tempWait.until(ExpectedConditions.alertIsPresent());
      return new TestAcceptanceMessage(false, "The alert is still displayed.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(true, "The alert is closed.");
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clickOnSaveAsNewView()}.
   *
   * @param lastResult last result is {@link RMArtifactsPage#clickOnSaveAsNewView()}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnSaveAsNewView(final String lastResult) {
    Dialog dlgNewView = null;
    try {
      dlgNewView =
          this.engine.findElementWithinDuration(Criteria.isDialog().withTitle("New View"),Duration.ofSeconds(30)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (dlgNewView != null) {
      return new TestAcceptanceMessage(true,
          "The 'New View' dialog is displayed after clicking on the 'Save as new view' button.");
    }
    return new TestAcceptanceMessage(false,
        "The 'New View' dialog is not displayed after clicking on the 'Save as new view' button.");
  }

  /**
   * <p>
   * Verifies the action
   *
   * @param viewName name
   * @param typeOfView type view
   * @param showOfView show view
   * @param descriptionOfView view
   * @param lastResult last result
   * @return the verification message
   */
  public TestAcceptanceMessage verifyCreateNewView(final String viewName, final String typeOfView,
      final String showOfView, final String descriptionOfView, final String lastResult) {
    Listbox lbView = null;
    this.driverCustom.getPresenceOfWebElement(
        "//span[text()='DYNAMIC_VAlUE' and contains(@class,'preferred-view-name')]", lastResult);
    try {
      lbView =
          this.engine.findElementWithinDuration(Criteria.isListbox().containText(lastResult), Duration.ofSeconds(120)).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (lbView != null) {
      return new TestAcceptanceMessage(true,
          RMConstants.THE + lastResult + "' view is displayed in listbox after creating view.");
    }
    return new TestAcceptanceMessage(false,
        RMConstants.THE + lastResult + "' view is not displayed in listbox after creating view.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#deleteView(String)}.
   *
   * @param view view
   * @param lastResult last result {@link RMArtifactsPage#deleteView(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyDeleteView(final String view, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (!this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, Duration.ofSeconds(15), view)) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(15));
    if (!this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, Duration.ofSeconds(15), view)) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(15));
    if (!this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_VIEW_XPATH, Duration.ofSeconds(15), view)) {
      return new TestAcceptanceMessage(true, "The '" + view + "' view is deleted.");
    }
    }
    }
    return new TestAcceptanceMessage(false, "The '" + view + "' view is not deleted.");
    
    
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isApplyButtonEnabled()}.
   *
   * @param additionalParam additional value.
   * @param lastResult last result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsApplyButtonEnabled(final String additionalParam, final String lastResult) {
    if (Boolean.valueOf(lastResult) && Boolean.valueOf(additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified 'Apply' button is not enabled");
    }
    else if (!Boolean.valueOf(lastResult) && !Boolean.valueOf(additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified 'Apply' button is enabled");
    }
    return new TestAcceptanceMessage(false, "Verified 'Apply' button is not enabled");

  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getRMAttributeTagValue(String)}.
   *
   * @param attributeId name
   * @param additionalPram data
   * @param lastResult last result is {@link RMArtifactsPage#getRMAttributeTagValue(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyGetRMAttributeTagValue(final String attributeId, final String additionalPram,
      final String lastResult) {
    if (lastResult.contains(additionalPram)) {
      return new TestAcceptanceMessage(true,
          "Verify data of the tag '" + attributeId +
          "' Artifact Tag in 'Selected Artifact' section.\n\nActual value is '" + lastResult +
          "'.\n\nExpected values should be '" + additionalPram + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verify data of the '" + attributeId + "' Artifact Tag in 'Selected Artifact' section.\n\nActual value is '" +
            lastResult + "'.\n\nExpected is all values should be '" + additionalPram + "'.");
  }

  /**
   * @param attributeId attribute Id.
   * @param TagName name of the tag.
   * @param lastResult last result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnRMAttributeSelectTags(final String attributeId, final String TagName,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase(TagName)) {
      return new TestAcceptanceMessage(true,
          "Verified clicked on 'Select Tags' button 'Selected Artifact' section with respect to '" + attributeId + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified not clicked on 'Select Tags' button 'Selected Artifact' section with respect to '" + attributeId +
        "'");

  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#createNewTag(String, String)}.
   *
   * @param tagName tag Name.
   * @param tagDescription tag Description.
   * @param lastResult string.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyCreateNewTag(final String tagName, final String tagDescription,
      final String lastResult) {
    if (this.driverCustom.isElementPresent("//span[text()='" + tagName + "']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified 'new tag' created as '" + tagName + "'");
    }
    return new TestAcceptanceMessage(false, "Verified Verified 'new tag' not created as '" + tagName + "'");

  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clickOnImageButton(String)}.
   *
   * @param button button.
   * @param lastResult string.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnImageButton(final String button, final String lastResult) {
    if (!this.driverCustom.isElementPresent("//a[@title='" + button + "' or aria-disabled='false']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified Button visibility  of '" + button +
          "' And button Clicked successfully.\n or \nbutton already Clicked successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified Button visibility  of '" + button + "' And button not Clicked successfully.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectMenuItemFromMoreActions(String)}.
   *
   * @param attributeName attribute Name.
   * @param lastResult string.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectMoreActionsForAttribute(final String attributeName,
      final String lastResult) {

    if (lastResult.equalsIgnoreCase(attributeName)) {
      return new TestAcceptanceMessage(true,
          "Verified attribute name '" + attributeName + "' Selected from 'more action' successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified attribute name '" + attributeName +
        "' Not selected from 'more action' successfully. \n Or \n Attribute name '" + attributeName +
        "' Not found from 'more action' drop down .");

  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#manageTags(String, String, String)}.
   *
   * @param searchTagName tag name.
   * @param tagAction action.
   * @param deleteTagAction action.
   * @param lastResult string.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyManageTag(final String searchTagName, final String tagAction,
      final String deleteTagAction, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (tagAction.equalsIgnoreCase("Delete Tag")) {
      Dialog dlg = this.engine.findElement(Criteria.isDialog().withTitle("Manage Tags")).getFirstElement();
      Text txtSearch1 =
          this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Type to filter").inContainer(dlg));
      txtSearch1.click();
      txtSearch1.clearText();
      txtSearch1.setText(searchTagName);
      this.driverCustom.waitForSecs(Duration.ofSeconds(10));
      if (!this.driverCustom.isElementPresent("//label[text()='" + searchTagName + "']", Duration.ofSeconds(10))) {
        return new TestAcceptanceMessage(true,
            "Verified tag name visibility '" + searchTagName + "' And tag deleted successfully.");
      }
    }
    return new TestAcceptanceMessage(false,
        "Verified tag name visibility '" + searchTagName + "' And tag not deleted successfully.");
  }

  /**
   * <P>
   * Verifies clicked on create button to select type of the artifact
   *
   * @param type of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnCreateButtonWithType(final String type, final String lastResult) {
    List<String> types = Arrays.asList("Create Artifact", "Import", "Upload File");
    Boolean isClicked = false;
    String dialogTitle = "";
    for (String str : types) {
      if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(60), str)) {
        isClicked = true;
        dialogTitle = str;
        break;
      }

    }
    if (isClicked) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on '" + type + "' from 'Create' button and '" + dialogTitle + "' dialog displayed.\n" +
              "Actual Result \"" + type + "\" is clicked.\nExpected Result \"" + type + "\" should be clicked.");
    }
    return new TestAcceptanceMessage(false, " Verified: Not clicked on '" + type + "' from 'Create' button.");
  }


  /**
   * <P>
   * Verifies artifact is created.
   * <P>
   * This method called after {@link RMArtifactsPage#createArtifact(Map)}.
   *
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   * @throws InterruptedException -
   */
  public TestAcceptanceMessage verifyCreateArtifact(final Map<String, String> additionalParams, final String lastResult)
      throws InterruptedException {
    waitForPageLoaded();
    try {
    List<WebElement> results = this.driverCustom.getWebElements("//div[@class='resource-info']//span");
    if (results.get(1).getAttribute("title").contains(additionalParams.get("ARTIFACT_NAME"))) {
      return new TestAcceptanceMessage(true,
          "Verified: Artifact is created with required attributes.\n\nActual Result : artifact is created with name \"" + (additionalParams.get("ARTIFACT_NAME")) +
              "\"\n\nExpected Result : artifact displayed with name \"" + (additionalParams.get("ARTIFACT_NAME")) + "\".");
    }
    }
    catch (Exception e) {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@class='icon-resourcelink']//a[text()='" + additionalParams.get("ARTIFACT_NAME") + "']")));
      return new TestAcceptanceMessage(true,
          "Artifact with name '" + additionalParams.get("ARTIFACT_NAME") + "' is created successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Artifact is not created with required attributes.");
  }

  /**
   * <P>
   * Verifies id of the artifact is visible.
   * <P>
   * This method called after {@link RMArtifactsPage#getArtifactID()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetArtifactID(final String lastResult) {
    List<WebElement> results = this.driverCustom.getWebElements("//div[@class='resource-info']//a/span");
    for (WebElement result : results) {
      if (result.getText().equalsIgnoreCase(lastResult)) {
        return new TestAcceptanceMessage(true,
            "Verified: Artifact ID of the created artifact.ID of the artifact is \"" + lastResult + "\".");
      }
    }
    return new TestAcceptanceMessage(false,
        "Verified: Artifact ID of the created artifact. Artifact ID \"" + lastResult + "\" not found.");
  }

  /**
   * <P>
   * Verifies id of the artifact is visible.
   * <P>
   * This method called after {@link RMArtifactsPage#getArtifactIdByIndex(String)}.
   *
   * @param index artifact id index.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetArtifactIdByIndex(final String index, final String lastResult) {
    List<WebElement> results =
        this.driverCustom.getWebElements("//table[@rowType='resourceRow']//td[@class='id-col']//a");
    for (WebElement result : results) {
      if (result.getText().equalsIgnoreCase(lastResult)) {
        return new TestAcceptanceMessage(true,
            "Verified displayed id of the artifact.\n\nActual result is artifact with " + lastResult +
            " is present in the table.");
      }
    }
    return new TestAcceptanceMessage(true, "Verified displayed id of the artifact.\n\nActual result is artifact with " +
        lastResult + " is not present in the table.");
  }

  /**
   * <P>
   * Verifies artifact is deleted from searched specification.
   * <P>
   * This method called after {@link RMArtifactsPage#deleteArtifactFromSearchedSpecification(String,String)}.
   *
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteArtifactFromSearchedSpecification(final String artifactID,
      final String lastResult) {
    return verifyDeleteArtifactFromSearchedSpecification(artifactID, "FALSE", lastResult);
  }

  /**
   * <P>
   * Verifies artifact is deleted from searched specification.
   * <P>
   * This method called after {@link RMArtifactsPage#deleteArtifactFromSearchedSpecification(String,String)}.
   *
   * @param artifactID id of the artifact.
   * @param isModule true if the artifact want to delete is a module/collect which containing child artifacts.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteArtifactFromSearchedSpecification(final String artifactID,
      final String isModule, final String lastResult) {
    if (!this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTID_LINK_XPATH, Duration.ofSeconds(15), artifactID)) {
      return new TestAcceptanceMessage(true, "Verified: Searched Artifact is deleted.\nActual Result \"" + artifactID +
          "\" Is deleted.\nExpected Result \"" + artifactID + RMConstants.SHOULD_BE_DELETED);
    }
    return new TestAcceptanceMessage(false, "Verified: Searched Artifact is not deleted.");
  }

  /**
   * <P>
   * Verifies artifact is deleted from searched specification.
   * <P>
   * This method called after {@link RMArtifactsPage#deleteArtifactFolder(String, String)}.
   *
   * @param artifactFolder the folder containing the artifact.
   * @param isEmptyContent true if the content in the folder is empty
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteArtifactFolder(final String artifactFolder, final String isEmptyContent,
      final String lastResult) {
    try {
      this.engine.findElement(Criteria.isTreeNode().containText(artifactFolder)).getFirstElement();
      return new TestAcceptanceMessage(false, "Verified: '" + artifactFolder + "' folder is not deleted.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(true, "Verified: '" + artifactFolder + "' folder is deleted.\nActual Result \"" +
          artifactFolder + "\" is deleted.\nExpected Result \"" + artifactFolder + "\" should be deleted.");
    }

  }

  /**
   * <P>
   * Verifies module artifact and related artifacts are imported successfully.
   * <P>
   * This method called after
   * {@link RMArtifactsPage#importRequirementsFromACSVFileOrSpreadsheetIntoAModule(String, String, String, String)}.
   *
   * @author KYY1HC
   * @param fileName file Name.
   * @param moduleId id of module
   * @param moduleName name of module (contents)
   * @param importOption value is 1 of 3 options: "Create new artifacts for all entries"; "Update artifacts that match
   *          entries, and create artifacts for new entries"; "Update artifacts that match entries, and ignore new
   *          entries"
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyImportRequirementsFromACSVFileOrSpreadsheetIntoAModule(final String fileName,
      final String moduleId, final String moduleName, final String importOption, final String lastResult) {
    String successfulMsg = "The import of the comma-separated values (CSV) or spreadsheet file is complete.";
    String xpathSuccessMessage =
        "//div[@class='messageArea OK']/div[@class='messageSummary' and text()='" + successfulMsg + "']";

    try {
      if (this.driverCustom.isElementVisible(xpathSuccessMessage, Duration.ofSeconds(20))) {
        return new TestAcceptanceMessage(true,
            "Verified: PASSED - The requirements from a CSV or Spreadsheet '" + fileName +
            "' is imported successfully.\nActual Result \"" + successfulMsg +
            "\" is displayed.\nExpected Result \"" + successfulMsg + "\" is displayed.");
      }
      return new TestAcceptanceMessage(false, "Verified: FAILED - The requirements from a CSV or Spreadsheet '" +
          fileName + "' is not imported.\nActual Result \"" + successfulMsg + "\" is not displayed.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified: FAILED - The requirements from a CSV or Spreadsheet '" + fileName +
          "' is not imported.\nActual Result \"" + successfulMsg + "\" is not displayed.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#importRequirementsFromAMigrationPackageWithError(String)}.
   *
   * @param fileName file Name.
   * @param lastResult - is true if the actual error message is similar to expected error message
   * @return the verification message
   */
  public TestAcceptanceMessage verifyImportRequirementsFromAMigrationPackageWithError(final String fileName,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified : The migration file '" + fileName +
          " is NOT the file type allowed. Actual result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified : The migration file '" + fileName +
        " is not allowed to import but imported successfully. Actual result is '" + lastResult + "'.");
  }

  /**
   * <P>
   * Verifies module artifact and related artifacts are imported successfully.
   * <P>
   * This method called after {@link RMArtifactsPage#importArtifactsWithMigizFileExtension(Map)}.
   *
   * @param additionalParams the parameters to import migiz file
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyImportArtifactsWithMigizFileExtension(final Map<String, String> additionalParams,
      final String lastResult) {
    try {
      this.driverCustom.clickTwice(RMConstants.RMARTIFACTSPAGE_PROJECT_FOLDER_XPATH,
          additionalParams.get("IMPORTED_FOLDER")); // "ALM_System"
      Text txtSearch = this.engine.findElementWithDuration(
          Criteria.isTextField().withPlaceHolder(RMConstants.TYPE_TO_FILTER_ARTIFACTS_BY_TEXT_OR_BY_ID),
          this.timeInSecs).getFirstElement();
      txtSearch.setText(additionalParams.get(RMConstants.IMPORTED_MODULE_NAME) + Keys.ENTER);

      Row rowModule = this.engine
          .findElementWithDuration(
              Criteria.isRow().containsText(additionalParams.get(RMConstants.IMPORTED_MODULE_NAME)), this.timeInSecs)
          .getFirstElement();

      if (rowModule != null) {
        return new TestAcceptanceMessage(true,
            "Verified : The migration file '" + additionalParams.get(RMConstants.FILETYPE) +
            "' is imported successfully.\nActual Result \"" +
            additionalParams.get(RMConstants.IMPORTED_MODULE_NAME) + "\" is imported.\nExpected Result \"" +
            additionalParams.get(RMConstants.IMPORTED_MODULE_NAME) + "\" is showing in the '" +
            additionalParams.get("IMPORTED_FOLDER") + "' folder.");
      }
      return new TestAcceptanceMessage(false,
          "Verified: The migration file '" + additionalParams.get(RMConstants.FILETYPE) +
          "' is not imported.\nActual Result '" + additionalParams.get(RMConstants.IMPORTED_MODULE_NAME) +
          "' is not showing.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified: The migration file '" + additionalParams.get(RMConstants.FILETYPE) +
          "' is not imported.\nActual Result '" + additionalParams.get(RMConstants.IMPORTED_MODULE_NAME) +
          "' is not showing.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clickOnGenerateAreportFromReportPage(String)}.
   *
   * @param attributeName attribute Name.
   * @param additionalParams additional parameter.
   * @param lastResult last Result.
   * @return message.
   */
  public TestAcceptanceMessage verifyClickOnGenerateAreportFromReportPage(final String attributeName,
      final String additionalParams, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    if (attributeName.contains("Generate a report")) {
      try {
        this.engine.findElement(Criteria.isDialog().withTitle("Create a Report")).getFirstElement();
        return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE +
            attributeName + "' of artifact.\nActual is the 'Create a Report' dialog is displayed as expectation.");
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON_THE +
            attributeName + "' of artifact.\nActual is the 'Create a Report' dialog is not displayed as expectation.");
      }
    }
    return new TestAcceptanceMessage(false, RMConstants.THE + attributeName + "' does not match");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectAvailableReportType(String)}.
   *
   * @param attributeName attribute Name.
   * @param lastResult last Result.
   * @return message.
   */
  public TestAcceptanceMessage verifySelectAvailableReportType(final String attributeName, final String lastResult) {

    if (lastResult.contains(attributeName)) {

      this.driverCustom.getPresenceOfWebElement("//a[@aria-selected='true']");
      return new TestAcceptanceMessage(true,
          "Verified report type '" + attributeName + "' Selected successfully Under 'Select the Report Type' section.");
    }
    return new TestAcceptanceMessage(false, "Verified report type '" + attributeName +
        "' Not selected Under 'Select the Report Type' section or report type is not available to select.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addArtifactsInTraceabilityReport(String, String, String)}.
   *
   * @param attributeAction attribute Action Add.. or Remove
   * @param attributeId attribute Id
   * @param attributeName attribute Name
   * @param additionalParam additional param.
   * @param lastResult last Result of SelectAddOrRemoveAttribute1
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectAddOrRemoveAttribute1(final String attributeAction, final String attributeId,
      final String attributeName, final String additionalParam, final String lastResult) {

    if (lastResult.contains(additionalParam)) {

      this.driverCustom.getPresenceOfWebElement("//a[text()='" + attributeId + ":']");


      return new TestAcceptanceMessage(true, "Verified availability of attribute '" + attributeId + ": " +
          attributeName + "' Added successfully Under 'Select the Artifacts' section.");
    }
    return new TestAcceptanceMessage(false, "Verified availability of attribute '" + attributeId + ": " +
        attributeName + "' Not Added successfully Under 'Select the Artifacts' section.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addArtifactsInTraceabilityReport(String, String, String)}.
   *
   * @param attributeAction add or remove attribute
   * @param attributeId attribute Id
   * @param attributeName attribute Name
   * @param additionalParam additional Parameter value.
   * @param lastResult last Result of SelectAddOrRemoveAttribute
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectAddOrRemoveAttribute(final String attributeAction, final String attributeId,
      final String attributeName, final String additionalParam, final String lastResult) {
    List<WebElement> elements = this.driverCustom.getWebElements("//button[text() = 'OK']");
    boolean isClicked =
        elements.size() >= 1 ? elements.get(elements.size() - 1).getAttribute(CCMConstants.DISABLED) != null : false;
        if (isClicked) {

          return new TestAcceptanceMessage(Boolean.valueOf(additionalParam),
              "Verified save button is 'disabled' hence Work item is saved successfully.");
        }

        else if (!Boolean.valueOf(additionalParam)) {
          return new TestAcceptanceMessage(true,
              "Verified OK button is 'enabled' in 'Add artifacts to report' dialog after searched artifact id and selected.\n Searched id and selected as : '" +
                  attributeId + ": " + attributeName + "'");
        }
        return new TestAcceptanceMessage(false,
            "Verified OK button is 'not disabled' in 'Add artifacts to report' dialog after searched artifact id or id not searched or artifact id not selected please give valid artifact.");
  }


  /**
   * <p>
   * Verifies the action of
   * {@link RMArtifactsPage#addReportInformationWithoutDateandTime(String, String, String, String)}.
   *
   * @param reportName report Name
   * @param reportType report Type
   * @param authorName author Name
   * @param companyName company Name
   * @param lastResult last Result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyAddReportInformationWithoutDateandTime(final String reportName,
      final String reportType, final String authorName, final String companyName, final String lastResult) {

    if (lastResult.contains(reportName) || !reportType.isEmpty() || !authorName.isEmpty() || !companyName.isEmpty()) {

      return new TestAcceptanceMessage(true, " Verified report information '" + reportName + "', " + reportType +
          "', " + authorName + "', " + companyName + "' Added successfully Under 'Report Information' section.");
    }
    return new TestAcceptanceMessage(false, " Verified report information '" + reportName + "', " + reportType + "', " +
        authorName + "', " + companyName + "' Not Added successfully Under 'Report Information' section.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addReportInformationWithDateandTime(Map)}.
   *
   * @param params store key values for: ("reportName", <NAME_OF_REPORT>) <br>
   *          ("appendDateTime", <APPEND_DATE_TIME_IN_THE_REPORT_NAME_OR_NOT>) <br>
   *          ("reportFormat", <FORMAT_OF_REPORT>) <br>
   *          ("reportType", <TYPE_OF_REPORT>) <br>
   * @param lastResult last Result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyAddReportInformationWithDateandTime(final Map<String, String> params,
      final String lastResult) {
    boolean result = false;
    // get actual report name
    String actualReportName =
        this.driverCustom.getWebElement("//input[contains(@id,'dijit_form_ValidationTextBox')]").getAttribute("value");
    if (actualReportName.equals(params.get("reportName"))) {
      result = true;
    }

    // verify "Append date/time information to the report name" checkbox
    String chkBox = this.driverCustom.getAttribute(
        RMConstants.RMARTIFACTSPAGE_ADD_APPENDDATETIME_INFORMATION_TO_THE_REPORTNAME_XPATH, RMConstants.ARIA_CHECKED);
    if (!chkBox.equals(params.get("appendDateTime"))) {
      result = false;
      LOGGER.LOG.error("'Append date/time information to the report name' checkbox is not displayed as expected!");
    }

    // Format of report
    String reportFormatDisplayed =
        this.driverCustom.getText("//div[@dojoattachpoint='_reportTypeNode']//span[@role='option']");
    if (!reportFormatDisplayed.equals(params.get("reportFormat"))) {
      result = false;
      LOGGER.LOG
      .error("Report format displayed is different with the expected value '" + params.get("reportFormat") + "'!");
    }
    else {
      LOGGER.LOG
      .info("Report format displayed is the same with the expected value '" + params.get("reportFormat") + "'!");
    }

    if (params.get("reportType").equals("Traceability Report")) {
      String companyName =
          this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_COMPANYNAME_IN_REPORT_XPATH).getText();
      result &= companyName.equals(params.get("companyName"));
      String authorName =
          this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_AUTHORNAME_IN_REPORT_XPATH).getText();
      result &= authorName.equals(params.get("authorName"));
    }
    if (result) {
      return new TestAcceptanceMessage(true,
          "Verified adding information for report with name '" + params.get("reportName") + "' successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verified adding information for report with name '" + params.get("reportName") +
        "' failed!\nExpected values should be displayed: \nReport name: '" + params.get("reportName") +
        "'.\n Is append Date/Time in report name: '" + params.get("appendDateTime") + "'.\nReport format: '" +
        params.get("reportFormat") + "'.");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#unSelectSaveReportToProjectCheckBox()}.
   *
   * @param lastResult last Result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyUnSelectSaveReportToProjectCheckBox(final String lastResult) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH);
    if (lastResult.equals("false")) {

      return new TestAcceptanceMessage(true,
          "Verified 'Save report to project' check box unselected Successfully under 'Save Report to Project' section");
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Save report to project' check box selected under 'Save Report to Project' section or check box not found to unselect");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectSaveReportToProjectCheckBox()}.
   *
   * @param lastResult last Result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectSaveReportToProjectCheckBox(final String lastResult) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH);
    if (lastResult.equals("true")) {

      return new TestAcceptanceMessage(true,
          "Verified 'Save report to project' check box selected Successfully under 'Save Report to Project' section");
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Save report to project' check box not selected under 'Save Report to Project' section or check box not found to unselect");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#selectOpenTheReportWhenTheWizardIsClosedCheckBox()}.
   *
   * @param lastResult last Result
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectOpenTheReportWhenTheWizardIsClosedCheckBox(final String lastResult) {
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_SAVE_REPORT_TO_PROJECT_XPATH);
    if (lastResult.equals("true")) {

      return new TestAcceptanceMessage(true,
          "Verified 'Open the report when the wizard is closed' check box selected Successfully under 'Generate the Report' section");
    }
    return new TestAcceptanceMessage(false,
        "Verified 'Open the report when the wizard is closed' check box not selected under 'Generate the Report' section or check box not found to select");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clickOnDownloadForViewingLink()}.
   *
   * @param additionalParam {@link Boolean}
   * @param lastResult last result of {@link #verifyClickOnViewingLink(String, String)}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnViewingLink(final String additionalParam, final String lastResult) {


    if (lastResult.contains(additionalParam)) {

      return new TestAcceptanceMessage(true,
          "Verified after Clicked On 'Viewing Link' \nActual PDF report file is downloaded in default folder location is : '" +
              lastResult + "'.\nExpected PDF report file name is : '" + additionalParam +
          "'.\nExpected PDF report file name is available in actual default folder location ");
    }
    return new TestAcceptanceMessage(false,
        "Verified after Clicked On 'Viewing Link' \nActual PDF report file is downloaded in default folder location is : '" +
            lastResult + "'.\nExpected PDF report file name is : '" + additionalParam +
        "'.\nExpected PDF report file name is not available in actual default folder location ");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getDownloadedPDFFileNameAndPath(String)}.
   *
   * @param additionalParam to be passed in Excel Test Script.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifygetDownloadedPDFFileNameAndPath(final String additionalParam,
      final String lastResult) {

    if (lastResult.contains(additionalParam)) {

      return new TestAcceptanceMessage(true,
          "Verified Downloaded file Name And Path.\n Actual Downloaded file Name And Path is: '" + lastResult +
          "'.\n Expected PDF report file name is : '" + additionalParam + "'.pdf");
    }
    return new TestAcceptanceMessage(false,
        "Verified Downloaded file Name And Path.\n Actual Downloaded file Name And Path is: '" + lastResult +
        "'.\n Expected PDF report file name is : '" + additionalParam +
        "'.pdf\n  Expected PDF report file name is not available in actual default folder location.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getDownloadedFileNameAndPath()}.
   *
   * @param additionalParam to be passed in Excel Test Script.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetDownloadedFileNameAndPath(final String additionalParam,
      final String lastResult) {

    if (lastResult.contains(additionalParam)) {

      return new TestAcceptanceMessage(true,
          "Verified Downloaded file Name And Path.\n Actual Downloaded file Name And Path is: '" + lastResult +
          "'.\n Expected the report file name is : '" + additionalParam + "'.pdf/.xlsx/.xls/.csv");
    }
    return new TestAcceptanceMessage(false,
        "Verified Downloaded file Name And Path.\n Actual Downloaded file Name And Path is: '" + lastResult +
        "'.\n Expected the report file name is : '" + additionalParam +
        "'.pdf/.xlsx/.xls/.csv\n  Expected the report file name is not available in actual default folder location.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getValidationMessageFromReportGenerationMessageArea(String)}.
   *
   * @param message message.
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult get last result of getValidationMessageFromNotificationArea(String)}}.
   * @return validation message.
   */
  public TestAcceptanceMessage verifyGetValidationMessageFromReportGenerationMessageArea(final String message,
      final String additionalParam, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.contains(additionalParam),
          "Verified Expected message displayed in 'Generate the Report' message area.\nActual validation message is :" +
              lastResult + ".\nExpected validation message is :" + additionalParam);
    }
    return new TestAcceptanceMessage(false,
        "Expected value message not displayed in 'Generate the Report' message area or \n Actual message displayed in 'Generate the Report' message area is - \"" +
            lastResult + ".");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isReportSavedInSelectedArea(String)}.
   *
   * @param area -
   * @param additionalParam -
   * @param lastResult of {@link RMArtifactsPage#isReportSavedInSelectedArea(String)}.
   * @return validation message.
   */

  public TestAcceptanceMessage verifyIsReportSavedInSelectedArea(final String area, final String additionalParam,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified Expected report generated in selected '" + area +
          "' area.\nActual selected area is : " + area + ".\nExpected area is :" + additionalParam);
    }
    return new TestAcceptanceMessage(false,
        "Expected report not generated in selected " + area + " area. or \n Actual area is - \"" + lastResult + ".");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getDownloadedPDFFileNameAndPath(String)}.
   *
   * @param reportName report Name
   * @param lastResult {@link #verifyGetDownededPdfFileNameAndPath(String, String)}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetDownededPdfFileNameAndPath(final String reportName, final String lastResult) {

    if (lastResult.contains(reportName)) {
      String path = this.driverCustom.getDownloadFolderLocation();
      LOGGER.LOG.info("Verified Download Folder Location  " + path);
      LOGGER.LOG.info("Verified Download file name as:  " + reportName + ".pdf");
      String directoriesPath = this.driverCustom.getDownloadFolderLocation();
      String pdffile = directoriesPath + "\\" + reportName + ".pdf";
      LOGGER.LOG.info("Verified File down loaded path and name as : " + pdffile);
      LOGGER.LOG.info("-------------------------------------------------------\n");
      return new TestAcceptanceMessage(lastResult.endsWith(".pdf"),
          "Verified Expected file name saved in '.pdf' format. \nActual file path is : \"" + lastResult +
          "\".\n Expected file name is : " + pdffile + ".pdf");
    }
    return new TestAcceptanceMessage(false, "Verified Expected file name not saved in 'pdf' format as - \n \"" +
        lastResult + "\".\n Verified Actual Downloaded file name");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getFileData(String)}.
   *
   * @param directoriesPath directories Path
   * @param lastResult get from {@link #verifyGetFileData(String, String)}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetFileData(final String directoriesPath, final String lastResult) {

    if (!lastResult.isEmpty()) {

      return new TestAcceptanceMessage(true,
          "Verified data get from downloaded target location file.\nFile target location is : " + directoriesPath);
    }
    return new TestAcceptanceMessage(false,
        "Verified file data not get from downloaded file or file not found to get the file data please check file is down loaded..? In target location");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isRmLinksAvailableInPdfFile(String, String)}.
   *
   * @param pdfFilePath pdf File Path
   * @param rmLinks rm Links
   * @param lastResult {@link #verifyisRmLinksAvailableInPdfFile(String, String, String)}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyisRmLinksAvailableInPdfFile(final String pdfFilePath, final String rmLinks,
      final String lastResult) {

    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true,
          "Verified  that selected view ALL RM links/artifacts available in down loaded pdf report file. ");
    }
    return new TestAcceptanceMessage(false,
        "Verified  that selected view ALL RM links/artifacts not available in down loaded pdf report file. ");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isArtifactsOfViewAvailableInPdfFile(String, String)}.
   *
   * @param pdfFilePath pdf File Path
   * @param artifact -
   * @param lastResult {@link #verifyisRmLinksAvailableInPdfFile(String, String, String)}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsArtifactsOfViewAvailableInPdfFile(final String pdfFilePath,
      final String artifact, final String lastResult) {
    List<WebElement> linksList =
        this.driverCustom.getWebElements("//table[@rowtype='resourceRow']//td[@class='id-col']//a");
    LOGGER.LOG.info("Total number of artifacts from selected view is : " + linksList.size());
    if (lastResult.equals("true")) {

      return new TestAcceptanceMessage(true,
          "Verified '" + linksList.size() + "' artifacts of selected view available in down loaded pdf report file. ");
    }
    return new TestAcceptanceMessage(false, "Verified '" + linksList.size() +
        "' artifacts of selected  view not available in down loaded pdf report file. ");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getAllArtifactIdFromSelectedView()}.
   *
   * @param lastResult - of {@link RMArtifactsPage#getAllArtifactIdFromSelectedView()}
   * @return message
   */
  public TestAcceptanceMessage verifyGetAllArtifactIdFromSelectedView(final String lastResult) {

    if (!lastResult.isEmpty()) {

      List<WebElement> linksList =
          this.driverCustom.getWebElements("//table[@rowtype='resourceRow']//td[@class='id-col']//a");
      LOGGER.LOG.info("Total number of artifacts from selected view is : " + linksList.size());
      return new TestAcceptanceMessage(true, "Actual value is : \n>>---->>>> \"" + lastResult + "\".\n");
    }
    return new TestAcceptanceMessage(false, "Actual value  is : \"" + lastResult + "\".");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isRmLinksAvailableInPdfFile(String, String)}.
   *
   * @param pdfFilePath pdf File Path
   * @param rmLinks rm Links
   * @param lastResult {@link #verifyisRmLinksAvailableInPdfFile(String, String, String)}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyValidatePdfContent(final String pdfFilePath, final String rmLinks,
      final String lastResult) {

    if (lastResult.equals("true")) {
      if (rmLinks.isEmpty()) {
        return new TestAcceptanceMessage(true,
            "Verified all artifacts and links of selected view/artifact is empty and not available in downloaded pdf report file. ");
      }
      return new TestAcceptanceMessage(true,
          "Verified all artifacts and links of selected view/artifact is available in downloaded pdf report file. ");
    }
    return new TestAcceptanceMessage(false,
        "Verified all artifacts and links of selected view is not available in downloaded pdf report file. ");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isDataAvailableInPdfFile(String, String)}.
   *
   * @param pdffilePath pdf file Path
   * @param expectedData expected Data
   * @param lastResult last result of {@link #verifyIsDataAvailableInPdfFile(String, String, String)}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyIsDataAvailableInPdfFile(final String pdffilePath, final String expectedData,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified Expected data available in downloaded pdf report file.\n Expected data is - '" + expectedData +
          "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified Expected data not available in downloaded pdf report file.\n Expected data is - '" + expectedData +
        "'");
  }

  /**
   * <P>
   * This methods use to return list of artifacts ID. <b> used after{@link RMArtifactsPage#artifactsID()}
   *
   * @param lastResult last result of {@link RMArtifactsPage#artifactsID()}
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyArtifactsID(final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified the number of Artifact found is " + lastResult.split(",").length);
    }
    return new TestAcceptanceMessage(false, "Verified no Artifact found.");
  }

  /**
   * <P>
   * This methods use to return list of artifacts ID. <b> used after{@link RMArtifactsPage#artifactsID()}
   *
   * @param lastResult last result of {@link RMArtifactsPage#artifactsID()}
   * @param id of artifact.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectArtifactAndOpenContextMenu(final String id, final String lastResult) {
    if (!id.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified that Artifact "+ id +" is selected and Click on menu button to open drop down sucessfully");
    }
    return new TestAcceptanceMessage(false, "Verified The drop down not opened.");
  }

  /**
   * <p>
   * Verify view name is set.
   * <p>
   * This method can be called after {@link RMArtifactsPage#setViewName(String)}.
   *
   * @param viewName name of the view.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetViewName(final String viewName, final String lastResult) {
    String viewname =
        this.driverCustom.getWebElement("//div[@class='saved-filter-name-field-div']//input").getAttribute("value");
    if (viewname.contains(viewName)) {
      String result = "Verified: View name present in the name text box .\n Actual view name \"" + viewName +
          "\".\n Expected view name \" " + viewname + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false,
        "Verified: View name is not present in the name text box .\n Actual view name \"" + viewName +
        "\" and \n not matched with Expected view name \"" + viewname + "\"");

  }

  /**
   * <p>
   * Verify view type is set.
   * <p>
   * This method can be called after {@link RMArtifactsPage#setViewType(String)}.
   *
   * @param typeOfView is the view type like 'Personal' and 'Shared'.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetViewType(final String typeOfView, final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//label[text()='DYNAMIC_VAlUE']/..//input[@name='typeGroup' and @aria-checked='true']", Duration.ofSeconds(10), typeOfView)) {
      return new TestAcceptanceMessage(true,
          "Verified: Required view type is selected while creating view.\nActual Result \"" + typeOfView +
          "\" type view is selected.\n" + "Expected Result \"" + typeOfView + "\" type view should be selected.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Required view type \"" + typeOfView + "\" is not selected while creating view.");
  }

  /**
   * <p>
   * Verify where to show the view.
   * <p>
   * This method can be called after {@link RMArtifactsPage#makePreferredView()}.
   *
   * @param showOfView refers where to show this view
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetWhereToShowThisView(final String showOfView, final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//label[text()='DYNAMIC_VAlUE']/..//input[@name='scopeGroup' and @aria-checked='true']", Duration.ofSeconds(10), showOfView)) {
      return new TestAcceptanceMessage(true,
          "Verified: Where to show this view is set while creating view.\nActual Result view is selected for \"" +
              showOfView + "\".\n" + "Expected Result view should be selected for \"" + showOfView + "\".");
    }
    return new TestAcceptanceMessage(false, "Verified: Where to show this view is not set while creating view.");

  }

  /**
   * <p>
   * Verify view is marked as preferred view.
   * <p>
   * This method can be called after {@link RMArtifactsPage#makePreferredView()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyMakePreferredView(final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//label[text()='Make this a preferred view']/..//input[@type='checkbox' and @aria-checked='true']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified:Created view is marked as 'Preferred View'.");
    }
    return new TestAcceptanceMessage(false, "Verified: Created view is not marked as 'Preferred View'.");

  }

  /**
   * <p>
   * Verify description is added in the view.
   * <p>
   * This method called after {@link RMArtifactsPage#addDescriptionInView(String)}.
   *
   * @param descriptionOfView refers to description for the view.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyAddDescriptionInView(final String descriptionOfView, final String lastResult) {
    String viewDescription = this.driverCustom
        .getWebElement("//div[@class='saved-filter-description-field-div']//textarea").getAttribute("value");
    if (viewDescription.contains(descriptionOfView)) {
      String result =
          "Verified: Description is added while creating the view .\n Actual description added for the view \"" +
              descriptionOfView + "\" and \n Expected description added for the view \" " + viewDescription + "\"";
      return new TestAcceptanceMessage(true, result);
    }
    return new TestAcceptanceMessage(false, "Verified: Description is not added while creating the view .");
  }

  /**
   * <p>
   * Verify module is opened with preferred view.
   * <p>
   * This method called after {@link RMArtifactsPage#openRMSpecification(String)}.
   *
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @param additionalParam to be passed in Excel Test Script.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenRMSpecification(final String artifactID, final String additionalParam,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//span[@aria-selected='true' and text()='DYNAMIC_VAlUE' and @class='private-tag-name list-view preferred-view-name select-item']",
      Duration.ofSeconds(5), additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified: View is opened as the view is preferred view.");
    }
    return new TestAcceptanceMessage(true, "Verified: View is not opened as the view is not preferred view.");
  }

  /**
   * <p>
   * Verify role is chosen for the view.
   * <p>
   * This method called after {@link RMArtifactsPage#chooseRole(String)}.
   *
   * @param role who can see the view
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseRole(final String role, final String lastResult) {
    if (this.driverCustom.isElementVisible("//input[@id='DYNAMIC_VAlUE' and @aria-checked='true']", Duration.ofSeconds(20), role)) {
      return new TestAcceptanceMessage(true,
          "Verified: Required role is selected for the view.View is selected with role - " + role);
    }
    return new TestAcceptanceMessage(false, "Verified: Required role is not selected for the view");
  }

  /**
   * <p>
   * Verify content is added in the artifact.
   * <p>
   * This method can be called after {@link RMArtifactsPage#inputArtifactContent(String)}.
   *
   * @param content added in the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyInputArtifactContent(final String content, final String lastResult) {
    String msg = this.driverCustom.getWebElement("//div[@class='dijitContentPane']/p").getText();
    if (msg.equalsIgnoreCase(content)) {
      return new TestAcceptanceMessage(true, "Verified: Content is added in the Artifact.\nExpected result '" +
          content + "' is added in the artifact.\nActual result '" + content + "' should be added in the artifact.");
    }
    return new TestAcceptanceMessage(false, "Verified: Content is not added in the Artifact.");
  }

  /**
   * <p>
   * Verify artifact is deleted successfully.
   * <p>
   * This method called after {@link RMArtifactsPage#deleteArtifact(String)}.
   *
   * @param artifactID id of the artifact to be deleted.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteArtifact(final String artifactID, final String lastResult) {
    if (!this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(20), artifactID)) {
      return new TestAcceptanceMessage(true, "Verified: Artifact is deleted.\n\nActual Result \"" + artifactID +
          "\" is deleted successfully.\n\nExpected Result \"" + artifactID + "\" should be deleted.");
    }
    return new TestAcceptanceMessage(false, "Verified: Artifact \"" + artifactID + "\" is not deleted.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {

    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addAndCloseValueForFolderAttributeInFilter(String)}.
   *
   * @param destinationFolder destination folder
   * @param lastResult last result is {@link RMArtifactsPage#addAndCloseValueForFolderAttributeInFilter(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyAddAndCloseValueForFolderAttributeInFilter(final String destinationFolder,
      final String lastResult) {
    if (this.driverCustom.isElementVisible("//div[@title='Folder -  - " + destinationFolder + "']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Add successfully filter for Folder attribute with value as " + destinationFolder);
    }
    return new TestAcceptanceMessage(false, "Add fail filter for Folder attribute with value as " + destinationFolder);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#searchGoToInModuleFind(String)}.
   *
   * @param artifactID id of artifact
   * @param lastResult last result is {@link RMArtifactsPage#searchGoToInModuleFind(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifySearchGoToInModuleFind(final String artifactID, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Found and Go to artifact which has ID being " + artifactID);
    }
    return new TestAcceptanceMessage(false, "Not found artifact which has ID being " + artifactID);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addItemAndValueForLimitByLifecycleStatusFilter(Map)}.
   *
   * @param additionalParams is MAP, included Attribute Name, Item Name and Item Value in Life Cycle. With elements such
   *          as (<ID>, <value>): ("DEVELOPMENT_ITEM", <DEVELOPMENT_VALUE>) <br>
   *          ("TRACKING_ITEM", <TRACKING_VALUE>) <br>
   *          ("AFFECTS_ITEM", <AFFECTS_VALUE>) <br>
   *          ("TEST_ITEM", <TEST_VALUE>) <br>
   *          <DEVELOPMENT_VALUE>,<TRACKING_VALUE>,<AFFECTS_VALUE>,<TEST_VALUE> has available value such as: None, All,
   *          Open, Resolved... <br>
   *          For not select any item, we put "not select" to value variable (Ex: not select DEVELOPMENT_ITEM
   *          ("DEVELOPMENT_ITEM", "not select") ) <br>
   * @param lastResult last result is {@link RMArtifactsPage#addItemAndValueForLimitByLifecycleStatusFilter(Map)} <br>
   * @return the verification message <br>
   */
  public TestAcceptanceMessage verifyAddItemAndValueForLimitByLifecycleStatusFilter(
      final Map<String, String> additionalParams, final String lastResult) {
    boolean flag = false;
    String itemXpath = "";

    if (!additionalParams.get("DEVELOPMENT_VALUE").equalsIgnoreCase("not select")) {
      itemXpath = additionalParams.get("DEVELOPMENT_ITEM") + ": " + additionalParams.get("DEVELOPMENT_VALUE");
    }

    if (!additionalParams.get("TRACKING_VALUE").equalsIgnoreCase("not select")) {
      itemXpath = itemXpath + ", " + additionalParams.get("TRACKING_ITEM") + ": " +
          additionalParams.get("TRACKING_VALUE") + ", ";
    }

    if (!additionalParams.get("AFFECTS_VALUE").equalsIgnoreCase("not select")) {
      itemXpath =
          itemXpath + additionalParams.get("AFFECTS_ITEM") + ": " + additionalParams.get("AFFECTS_VALUE") + ", ";
    }

    if (!additionalParams.get("TEST_VALUE").equalsIgnoreCase("not select")) {
      itemXpath = itemXpath + additionalParams.get("TEST_ITEM") + ": " + additionalParams.get("TEST_VALUE");
    }

    String filterResultXpath = "//div[@title='Limit by lifecycle status -  - " + itemXpath + "']";

    // verify filter display after Add filter dialog close
    if (this.driverCustom.isElementVisible(filterResultXpath, Duration.ofSeconds(20))) {
      flag = true;
    }

    // wait for loading complete - disappear "Loading" message
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_02, Duration.ofSeconds(300));
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (flag) {
      return new TestAcceptanceMessage(true,
          "Add LimitByLifecycleStatus filter successfull. .\nThe filter details \n" + additionalParams.get("DEVELOPMENT_ITEM") +
          ": " + additionalParams.get("DEVELOPMENT_VALUE") + ",\n " +
          additionalParams.get("TRACKING_ITEM") + ": " +
          additionalParams.get("TRACKING_VALUE") + ",\n " + additionalParams.get("AFFECTS_ITEM") +
          ": " + additionalParams.get("AFFECTS_VALUE") + ",\n" +
          additionalParams.get("TEST_ITEM") + ": " + additionalParams.get("TEST_VALUE"));
    }
    return new TestAcceptanceMessage(false, "Add LimitByLifecycleStatus filter fail");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#addAndCloseListValueForAttributeInFilter(String,String)}.
   *
   * @param attribute name of the attribute to add in the filter.<br>
   *          e.g. Artifact Type,Artifact ID etc.
   * @param values String contains all value seperated by comma ','
   * @param lastResult last result is {@link RMArtifactsPage#addItemAndValueForLimitByLifecycleStatusFilter(Map)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyAddAndCloseListValueForAttributeInFilter(final String attribute,
      final String values, final String lastResult) {
    boolean flag = false;
    String[] conditionFilter1 = { attribute, "is any of", values };
    String[] conditionFilter2 = { attribute, "exists", values };
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_FILTERCONDITION_XPATH, Duration.ofSeconds(20), conditionFilter1)) {
      flag = true;
    }
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_FILTERCONDITION_XPATH, Duration.ofSeconds(20), conditionFilter2)) {
      flag = true;
    }
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE_02, Duration.ofSeconds(300));
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (flag) {
      return new TestAcceptanceMessage(true, "Add " + attribute + " filter successfully with value as " + values);
    }
    return new TestAcceptanceMessage(false, "Add " + attribute + " filter fail with value as " + values);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getAllValuesAtColumnFromTable(String,String)}.
   *
   * @param columnName name of column header <br>
   * @param expectedValues expect value with separate 2 cases: <br>
   *          Case 1: If expectedValues being empty data, input to parameter emptydata <br>
   *          Case 2: If expectedValues being value, input to parameter with one value <br>
   * @param lastResult last result of {@link RMArtifactsPage#getAllValuesAtColumnFromTable(String,String)}. <br>
   *          true: If actual value is the same as expectedValues <br>
   *          false If actual value is different from expectedValues <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyGetAllValuesAtColumnFromTable(final String columnName, final String expectedValues,
      final String lastResult) {
    // TODO: Case 01: lastResult is true - If actual value is the same as expectedValues
    // TODO: Case 02: lastResult is false - If actual value is different from expectedValues

    // Apply for Column with Column Name displayed in the table.
    List<WebElement> viewColumnHeaders = this.driverCustom.getWebDriver()
        .findElements(By.xpath("//div[@class='result-set-grid-view']//td[@role='columnheader']"));
    List<String> columnheaders = new ArrayList<>();
    for (int i = 0; i < viewColumnHeaders.size(); i++) {
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView();",
          viewColumnHeaders.get(i));
      columnheaders.add(viewColumnHeaders.get(i).getText());
    }
    StringBuilder sb = new StringBuilder();
    if (columnheaders.stream().anyMatch(x -> x.equals(columnName)) && lastResult.equalsIgnoreCase("true")) //
    {
      for (int i = 0; i < columnheaders.size(); i++) {
        if (columnheaders.get(i).equals(columnName)) //
        {
          List<String> allLists = this.driverCustom
              .getWebElements("//table[@role='row presentation']//tr[1]//td[DYNAMIC_VAlUE]", Integer.toString(i + 3))
              .stream().map(x -> x.getText()).collect(Collectors.toList());
          List<String> allIdLists =
              this.driverCustom.getWebElements("//table[@role='row presentation']//tr[1]//td[@class='id-col']").stream()
              .map(x -> x.getText()).collect(Collectors.toList());
          for (int j = 0; (j < allIdLists.size()) && (j < allLists.size()); j++) {
            sb.append("Artifact ID '" + allIdLists.get(j).trim() + "' linked to '" + allLists.get(j).trim() + "' by " +
                columnName + " link.\n\n");
          }
          return new TestAcceptanceMessage(true, sb.toString() + "\n" + "All values get from " + columnName +
              " column in table has the same value as " + expectedValues);

        }
      }
    }
    return new TestAcceptanceMessage(false,
        "At least one value get from " + columnName + " column in table has different value from " + expectedValues);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isTheLinkVisibledOnTheTable(String)}.
   *
   * @param text - Text of Link to be displayed on table
   * @param expectedResult expected result
   * @param lastResult - Last result of {@link RMArtifactsPage#isTheLinkVisibledOnTheTable(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsTheLinkVisibledOnTheTable(final String text, final String expectedResult,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(expectedResult),
          "Verified that link with text: " + text + " is available in table. ");
    }
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(expectedResult),
        "Verified that link with text: " + text + " is NOT available in table. ");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getExcelSize(String)}.
   *
   * @param pathExcelFile path of Excel file <br>
   * @param lastResult last result of {@link RMArtifactsPage#getExcelSize(String)}. <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyGetExcelSize(final String pathExcelFile, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Get size of excel file successfully");
    }

    return new TestAcceptanceMessage(false, "Get size of excel file fail");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#clickOnRadioButtonInExportDialog(String)}.
   *
   * @param buttonLabel text label being next to radio button <br>
   * @param lastResult last result of {@link RMArtifactsPage#getExcelSize(String)}. <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyClickOnRadioButtonInExportDialog(final String buttonLabel,
      final String lastResult) {

    this.driverCustom.isElementPresent(RMConstants.RADIO_BUTTON_PROPERTIES_XPATH, Duration.ofSeconds(50), buttonLabel);
    String getResult = this.driverCustom.getWebElement(RMConstants.RADIO_BUTTON_PROPERTIES_XPATH, buttonLabel)
        .getAttribute("aria-checked");
    if (getResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Click on " + buttonLabel + " radio button successfully");
    }

    return new TestAcceptanceMessage(false, "Click on " + buttonLabel + " radio button fail");
  }


  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#getDataFromCellOfXLSX(String,String,String,String)}.
   *
   * @param excelPath path of excel file <br>
   * @param sheetIndex index of sheet need to read data <br>
   * @param rowIndex index of row need to read data <br>
   * @param columnIndex index of column need to read data <br>
   * @param lastResult return result of {@link RMArtifactsPage#getDataFromCellOfXLSX(String,String,String,String)} <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyGetDataFromCellOfXLSX(final String excelPath, final String sheetIndex,
      final String rowIndex, final String columnIndex, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Get successfully Data from XLSX excel file having excelPath as " + excelPath + ", index of sheet as " +
              sheetIndex + ", index of row as " + rowIndex + ", index of column as " + columnIndex);
    }

    return new TestAcceptanceMessage(false, "Get fail Data from XLSX excel file having excelPath as " + excelPath +
        ", index of sheet as " + sheetIndex + ", index of row as " + rowIndex + ", index of column as " + columnIndex);
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#cloneFromAComponent(Map)}.
   * <p>
   *
   * @param params store value: ("FOLDER_NAME", <FOLDER_NAME_VALUE>) <br>
   *          ("PROJECT_AREA", <PROJECT_AREA_VALUE>) <br>
   *          ("COMPONENT_NAME", <COMPONENT_NAME_VALUE>) <br>
   *          ("STREAMS_DROPDOWN_OPTION", <STREAMS_DROPDOWN_OPTION_VALUE>) <br>
   *          ("STREAM_NAME",<STREAM_NAME_VALUE>) <br>
   *          ("SELECT_ARTIFACT_TYPE", <ARTIFACT_TYPE>) <br>
   *          ("ARTIFACT_ID", <ARTIFACT_ID>) <br>
   * @param lastResult result from {@link RMArtifactsPage#cloneFromAComponent(Map)}.
   * @param additionalParam true if you want to verify Cloning passed and vice versa false if you want to verify Cloning
   *          failed
   * @return the verification message to check artifact is cloned or not
   */
  public TestAcceptanceMessage verifyCloneFromAComponent(final Map<String, String> params, final String additionalParam,
      final String lastResult) {
    if(additionalParam.equalsIgnoreCase("true")) {
      String msgCompletedSuccessfully = "//div[@class='messageSummary'][contains(text(),'The operation completed successfully.')]";
      if(this.driverCustom.isElementVisible(msgCompletedSuccessfully,  Duration.ofSeconds((this.timeInSecs.getSeconds() / 2)))) {
        return new TestAcceptanceMessage(true,
            "Verify that 'Clone From a Component...' completed successfully");
      }
    }
    if (additionalParam.equalsIgnoreCase("false")) {
      WebElement errorMsg = this.driverCustom.getPresenceOfWebElement(
          "//div[@class='messageArea ERROR']/div[@class='messageSummary' and text()='No artifacts were cloned.']");
      if (errorMsg != null) {
        return new TestAcceptanceMessage(true,
            "Verify 'Clone artifact from a component failed with expected error message': PASSED!");
      }
    }
    return new TestAcceptanceMessage(false, "Verify that 'Clone From a Component...' failed");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#openEditFilterDialogOfOneCondition(String)}.
   *
   * @param attributeFilter is Attribute Name need to edit value <br>
   * @param lastResult last result of {@link RMArtifactsPage#openEditFilterDialogOfOneCondition(String)}. <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyOpenEditFilterDialogOfOneCondition(final String attributeFilter,
      final String lastResult) {
    if (this.driverCustom.isElementPresent(RMConstants.HEADER_EDIT_FILTER_DIALOG_XPATH, Duration.ofSeconds(50))) {
      return new TestAcceptanceMessage(true, "Open Edit filter dialog successfully");
    }
    return new TestAcceptanceMessage(false, "Open Edit filter dialog fail");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#openEditFilterDialogOfAttributeName(String)}.
   *
   * @author PDU6HC
   * @param attributeName is the name of attribute you need to click on edit button
   * @param lastResult last result of {@link RMArtifactsPage#openEditFilterDialogOfAttributeName(String)}. <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyOpenEditFilterDialogOfAttributeName(final String attributeName,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.HEADER_EDIT_FILTER_DIALOG_XPATH, this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Open Edit filter dialog successfully");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Open Edit filter dialog fail");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#editItemAndValueForLimitByLifecycleStatusFilter(Map)}.
   *
   * @param additionalParams is MAP, included Attribute Name, Item Name and Item Value in Life Cycle. With elements such
   *          as (<ID>, <value>): <>("DEVELOPMENT_ITEM", <DEVELOPMENT_VALUE>) <br>
   *          ("TRACKING_ITEM", <TRACKING_VALUE>) <br>
   *          ("AFFECTS_ITEM", <AFFECTS_VALUE>) <br>
   *          ("TEST_ITEM", <TEST_VALUE>) <br>
   *          <DEVELOPMENT_VALUE>,<TRACKING_VALUE>,<AFFECTS_VALUE>,<TEST_VALUE> has available value such as: None, All,
   *          Open, Resolved... <br>
   *          For not select any item, we put "not select" to value variable (Ex: not select DEVELOPMENT_ITEM
   *          ("DEVELOPMENT_ITEM", "not select") ) <br>
   * @param lastResult last result is {@link RMArtifactsPage#addItemAndValueForLimitByLifecycleStatusFilter(Map)} <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyEditItemAndValueForLimitByLifecycleStatusFilter(
      final Map<String, String> additionalParams, final String lastResult) {

    boolean flag = false;
    String itemXpath = "";

    if (!additionalParams.get("DEVELOPMENT_VALUE").equalsIgnoreCase("not select")) {
      itemXpath = additionalParams.get("DEVELOPMENT_ITEM") + ": " + additionalParams.get("DEVELOPMENT_VALUE") + ", ";
    }

    if (!additionalParams.get("TRACKING_VALUE").equalsIgnoreCase("not select")) {
      itemXpath =
          itemXpath + additionalParams.get("TRACKING_ITEM") + ": " + additionalParams.get("TRACKING_VALUE") + ", ";
    }

    if (!additionalParams.get("AFFECTS_VALUE").equalsIgnoreCase("not select")) {
      itemXpath =
          itemXpath + additionalParams.get("AFFECTS_ITEM") + ": " + additionalParams.get("AFFECTS_VALUE") + ", ";
    }

    if (!additionalParams.get("TEST_VALUE").equalsIgnoreCase("not select")) {
      itemXpath = itemXpath + additionalParams.get("TEST_ITEM") + ": " + additionalParams.get("TEST_VALUE");
    }

    String filterResultXpath = "//div[@title='Limit by lifecycle status -  - " + itemXpath + "']";
    // verify filter display after Add filter dialog close
    if (this.driverCustom.isElementVisible(filterResultXpath, Duration.ofSeconds(20))) {
      flag = true;
    }

    // wait for loading complete - disappear "Loading" message
    String messageLoadingXpath = "//div[@class='status-message' and text()='Loading']";
    this.driverCustom.isElementInvisible(messageLoadingXpath,  Duration.ofSeconds(180));

    if (flag) {
      return new TestAcceptanceMessage(true,
          "Add LimitByLifecycleStatus filter successfully with item as " + additionalParams.get("ITEM_LIFECYCLE") +
          " and value of item as " + additionalParams.get("VALUE_OF_ITEM_LIFECYCLE"));
    }
    return new TestAcceptanceMessage(false, "Add LimitByLifecycleStatus filter fail");
  }

  /**
   * <p>
   * Verifies the action of {@link RMArtifactsPage#isExistingTextInArtifactContent(String)}.
   *
   * @param textToVerify - Text within a line in artifact content, should not contains any special character. <br>
   * @param lastResult last result of {@link RMArtifactsPage#isExistingTextInArtifactContent(String)}. <br>
   * @return the verification message <br>
   */

  public TestAcceptanceMessage verifyIsExistingTextInArtifactContent(final String textToVerify,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Existing one line in artifact content matched text: " + textToVerify);
    }
    return new TestAcceptanceMessage(false, "NO existing in artifact content matched text: " + textToVerify);
  }

  /**
   * <P>
   * Verifies Edit artifact successfully
   * <P>
   * This method called after {@link RMArtifactsPage#editArtifactContent}.
   *
   * @param additionalParams keys: "EDIT_ACTION", "OLD_TEXT", "NEW_TEXT".
   * @param lastResult last result of {@link RMArtifactsPage#editArtifactContent}.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyEditArtifactContent(final Map<String, String> additionalParams,
      final String lastResult) {
    String artifactID = additionalParams.get("Artifact ID");
    String typeSection = additionalParams.get("Select Type Section");
    if (typeSection.equals(RMConstants.OVERVIEW)) {
      // Edit Description
      return new TestAcceptanceMessage(true, String.format("Succesful added description to artifact %s", artifactID));
    }
    else if (typeSection.equals("Comments")) {
      String commentSubject = additionalParams.get("Comment Subject");
      String commentDescription = additionalParams.get("Comment Description");
      return new TestAcceptanceMessage(true, String.format("Succesful added comment to artifact %s. "
        + "\nThe comment is added on the right side, below \"Artifact Comment\" section."
        + "\n Subject: %s, Description: %s", artifactID, commentSubject, commentDescription));
    } else return new TestAcceptanceMessage(false, "Error occured when trying to edit Artifact");
  }


  /**
   * <P>
   * Verifies Edit artifact successfully
   * <P>
   * This method called after {@link RMArtifactsPage#editArtifactName(String,String)}
   *
   * @param oldArtifactName - old name before edit name <br>
   * @param newArtifactName - new name after edit name <br>
   * @param lastResult - edit name <br>
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyEditArtifactName(final String oldArtifactName, final String newArtifactName,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase(newArtifactName)) {
      return new TestAcceptanceMessage(true, "Edit name of artifact successfully");
    }
    return new TestAcceptanceMessage(false, "Edit name of artifact un-successfully");
  }

  /**
   * <P>
   * Verifies Edit artifact successfully
   * <P>
   * This method called after {@link RMArtifactsPage#loadArtifactsInRootFolder(String)}
   *
   * @param rootFolderName - name of the root folder.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyLoadArtifactsInRootFolder(final String rootFolderName, final String lastResult) {
    String filterXpath = "//div[@title='Folder -  - " + rootFolderName + "']";
    WebElement filterTag = this.driverCustom.getWebElement(filterXpath);
    if (filterTag.isDisplayed()) {
      return new TestAcceptanceMessage(true, "Load Artifacts In Root Folder as " + rootFolderName + " successfully");
    }
    return new TestAcceptanceMessage(false, "Load Artifacts In Root Folder as " + rootFolderName + " fail");
  }

  /**
   * <P>
   * Verifies click on Create button successfully
   * <P>
   * This method called after {@link RMArtifactsPage#clickOnCreateButton(String)}.
   *
   * @param type artifact type,module type,collection type visible under create menu.
   * @param lastResult last result of {@link RMArtifactsPage#clickOnCreateButton(String)}.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnCreateButton(final String type, final String lastResult) {
    String dialogTitle = "";
    String contentType = "";
    switch (type) {
      case "Other...":
        dialogTitle = "Create Artifact";
        break;
      case "Import Artifact...":
        dialogTitle = "Import";
        break;
      case "Upload Artifact...":
        dialogTitle = "Upload File";
        break;
      case "Heading":
        contentType = "Heading";
        break;
      case "Information":
        contentType = "Information";
        break;
      case "SYS Requirement":
        contentType = "SYS Requirement";
        break;
      case "SYS Non-Functional Requirement":
        contentType = "SYS Non-Functional Requirement";
        break;
      case "Lean SYS Requirement":
        contentType = "Lean SYS Requirement";
        break;
      case "SYS Constraint":
        contentType = "SYS Constraint";
        break;
      default:
        dialogTitle = "Create Artifact";
    }
    String headerDialogXpath = "//div[@role='dialog']//*[contains(text(),'" + dialogTitle + "')]";
    boolean headerDialog = this.driverCustom.isElementPresent(headerDialogXpath, this.timeInSecs);
    if (headerDialog) {
      return new TestAcceptanceMessage(true,
          "Open successfully " + dialogTitle + " Dialog - Artifact type has default value as " + type);
    }
    else if (this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_TITLE_XPATH, this.timeInSecs, contentType)) {
      return new TestAcceptanceMessage(true, "Verify PASSED - Open successfully " + contentType +
          " Content edit dropdown Artifact type has default value as " + contentType);
    }
    return new TestAcceptanceMessage(false,
        "Open fail " + dialogTitle + " Dialog - Artifact type has default value as " + type + "Or" +
            "Verify FAILED - Open fail " + contentType + " Content edit dropdown Artifact type has default value as " +
            type);
  }

  /**
   * <P>
   * Verifies Getting the first URL in cell
   * <P>
   * This method called after {@link RMArtifactsPage#getUriOfFromCellData(String)}.
   *
   * @param inputData input data
   * @param lastResult last result of {@link RMArtifactsPage#getUriOfFromCellData(String)}.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetUriOfFromCellData(final String inputData, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Get URI value from cell successfully. (URI: " + lastResult + ")");
    }
    return new TestAcceptanceMessage(false, "Get URI value from cell unsuccessful. (Result: " + lastResult + ")");
  }

  /**
   * <p>
   * Verify the Create Artifact dialog is displayed after we right click on a folder and select option "Create Artifact"
   * -> select sub-option
   * <p>
   *
   * @author VDY1HC
   * @param folderName Name of folder we want to create an artifact
   * @param option "Other...", "Import Artifact...", "Upload Artifact..."
   * @param lastResult result from the {@link RMArtifactsPage#rightClickOnFolderAndSelectCreateArtifact(String, String)}
   * @return return true if we can successfully open the dialog to create a new artifact or import, upload an artifact
   */
  public TestAcceptanceMessage verifyRightClickOnFolderAndSelectCreateArtifact(final String folderName,
      final String option, final String lastResult) {
    String dialogHeading = "";
    switch (option) {
      case "Import Artifact...":
        dialogHeading = "Import";
        break;
      case "Upload Artifact...":
        dialogHeading = "Upload File";
        break;
      default:
        dialogHeading = "Create Artifact";
    }
    boolean result = this.driverCustom
        .isElementVisible("//div[@class='jazz-ui-Dialog-heading' and text()='" + dialogHeading + "']", timeInSecs);
    return new TestAcceptanceMessage(result,
        "Verify: Right click on folder '" + folderName + "' and select option '" + option);
  }

  /**
   * <p>
   * Check if we can click on a folder successfully
   * <p>
   *
   * @author NVV1HC
   * @param folderName name of folder that we want to click to
   * @param lastResult result from the ${@link RMArtifactsPage#clickOnFolder(String)}
   * @return return true if the folder is clicked successfully
   */
  public TestAcceptanceMessage verifyClickOnFolder(final String folderName, final String lastResult) {
    String xpathSelectedFolder = String.format("//span[@class='condition-summary-value'][text()='%s']", folderName);
    if (this.driverCustom.isElementVisible(xpathSelectedFolder, Duration.ofSeconds(30))) {
      return new TestAcceptanceMessage(true, "PASSED - Click on Folder '" + folderName + "' successfully!");
    }
    return new TestAcceptanceMessage(false, "FAILED - Click on Folder '" + folderName + "' failed!");
  }

  /**
   * <p>
   * Verify we can select option to import on Import dialog
   * <p>
   *
   * @author NVV1HC
   * @param importOption e.g: "Import requirements from within a text document", "Import requirements from a CSV file or
   *          spreadsheet", "Import requirements from a ReqIF file", "Import requirements from a ReqIF file (Advanced)"
   * @param lastResult result from the ${@link RMArtifactsPage#selectOptionOnImportDialogAndClickNext(String)}
   * @return return true if select the option successfully and navigate to the next step of importing artifact
   */
  public TestAcceptanceMessage verifySelectOptionOnImportDialogAndClickNext(final String importOption,
      final String lastResult) {
    boolean result = this.driverCustom.isElementVisible(
        "//*[text()='Select Package File' or text()='Import Requirements' or text()='Select text document']",
        timeInSecs);
    return new TestAcceptanceMessage(result, "Select option '" + importOption + "' on Import dialog.");
  }

  /**
   * <p>
   * Verify the target artifact is displayed or not
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of the target artifact
   * @param additionalParam true or false, true if we want to check the artifact is displayed, false if we want to check
   *          the artifact is not displayed
   * @param lastResult result from the ${@link RMArtifactsPage#isArtifactDisplayed}
   * @return true if lastResult is the same with additionalParam or vice versa
   */
  public TestAcceptanceMessage verifyIsArtifactDisplayed(final String artifactID, final String additionalParam,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalParam)) {
      if (lastResult.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, "Artifact '" + artifactID + "' is diplayed as expected result!");
      }
      return new TestAcceptanceMessage(true, "Artifact '" + artifactID + "' is not diplayed as expected result!");
    }
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(false, "Expected result: artifact '" + artifactID +
          "' is not displayed!\nActual result: artifact '" + artifactID + "' is displayed!");
    }
    return new TestAcceptanceMessage(false, "Expected result: artifact '" + artifactID +
        "' is displayed!\nActual result: artifact '" + artifactID + "' is not displayed!");
  }

  /**
   * <P>
   * Verifies access to Artifact page
   * <P>
   * This method called after {@link RMArtifactsPage#clickOnArtifact(String)}
   *
   * @param artifactID ID of artifact to be clicked
   * @param lastResult returned value
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnArtifact(final String artifactID, final String lastResult) {
    String className = "resource-id";
    try {
      Integer.parseInt(artifactID);
    }
    catch (NumberFormatException e) {
      className = "resource-title";
    }
    String actual = "";
    if (this.driverCustom.isElementPresent(RMConstants.TITLE_ID_MOLDUE_IN_MODULE_PAGE_XPATH, Duration.ofSeconds(180), className)) {
      actual = this.driverCustom.getWebElement(RMConstants.TITLE_ID_MOLDUE_IN_MODULE_PAGE_XPATH, className).getText();
    }
    if (actual.equalsIgnoreCase(artifactID)) {
      return new TestAcceptanceMessage(true, "Access successfully to Artifact which has ID as " + artifactID);
    }
    return new TestAcceptanceMessage(false, "Access fail to Artifact which has ID as " + artifactID);

  }

  /**
   * <p>
   * Verify a new attribute is added to artifact table after
   * ${@link RMArtifactsPage#addArtifactAttributeInToArtifactTable(String)}
   * <p>
   *
   * @author NVV1HC
   * @param columnHeaderName name of attribute we want to add, e.g: Name, Artifact Type,..
   * @param lastResult result from ${@link RMArtifactsPage#addArtifactAttributeInToArtifactTable(String)}
   * @return true if a new attribute is displayed in the artifact table
   */
  public TestAcceptanceMessage verifyAddArtifactAttributeInToArtifactTable(final String columnHeaderName,
      final String lastResult) {
    if (columnHeaderName.equalsIgnoreCase("Comments") &&
        this.driverCustom.isElementPresent(RMConstants.COMMENT_COLLUMN_XPATH, this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Attribute column '" + columnHeaderName + "' is added successfully!");
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(15));
    List<WebElement> listHeaderElm =
        this.driverCustom.getWebElements("//td[@role='columnheader']//*[contains(@id,'ViewHeader')]");
    List<String> listHeader = new ArrayList<String>();
    for (WebElement columnHeaderElm : listHeaderElm) {
      listHeader.add(columnHeaderElm.getAttribute("innerText").trim());
    }
    if (listHeader.contains(columnHeaderName)) {
      return new TestAcceptanceMessage(true, "Attribute column '" + columnHeaderName +
          "' is added successfully!\nList column header displayed: '" + listHeader + "'.");
    }
    return new TestAcceptanceMessage(false, "Attribute column '" + columnHeaderName +
        "' is not added successfully!\nList column header displayed: '" + listHeader + "'.");
  }

  /**
   * <P>
   * Verifies artifact is exported successfully
   * <P>
   * This method called after {@link RMArtifactsPage#exportArtifact}.
   *
   * @param artifactID -
   * @param fileType -
   * @param lastResult -
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyExportArtifact(final String artifactID, final String fileType,
      final String lastResult) {
    String exportMsg =
        "The export operation has started. The process might take a considerable amount of time, depending on how many requirements are being exported.";
    try {
      this.driverCustom.getWebElement("//div[@dojoattachpoint='messageArea']//a[@title='Close']").click();
      return new TestAcceptanceMessage(true, "Export is in-process (Message: " + exportMsg + ")");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "No Export job is created");
    }
  }

  /**
   * <p>
   * Verify artifact is imported successfully or not after ${@link RMArtifactsPage#importArtifactTypes(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param additionalParams param stores key values for
   *          'HEADING_DROP_DOWN_VALUE','IMAGE_DROP_DOWN_VALUE','OTHER_TEXT_DROP_DOWN_VALUE' etc...
   * @param lastResult result from ${@link RMArtifactsPage#importArtifactTypes(Map)}
   * @return true if artifact is imported successfully
   */
  @SuppressWarnings("null")
  public TestAcceptanceMessage verifyImportArtifactTypes(final Map<String, String> additionalParams,
      final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.CSVIMPORT)) {
      if ((lastResult != null) & (lastResult != "") & (!lastResult.contains("cannot be imported"))) {
        return new TestAcceptanceMessage(true,
            "Import a CSV file with file path '" + additionalParams.get(RMConstants.FILETYPE) + "' successfully!");
      }
      return new TestAcceptanceMessage(false,
          "Import a CSV file with file path '" + additionalParams.get(RMConstants.FILETYPE) + "' failed!");
    }
    if (additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.REQIF_IMPORT)) {
      if (lastResult.contains("The import is complete")) {
        return new TestAcceptanceMessage(true,
            "Import a ReqIF file with file path '" + additionalParams.get(RMConstants.FILETYPE) + "' successfully!");
      }
      return new TestAcceptanceMessage(false,
          "Import a ReqIF file with file path '" + additionalParams.get(RMConstants.FILETYPE) + "' failed!");
    }
    if (additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.TEXTDOCUMENT)) {
      if (lastResult.contains("Success!")) {
        return new TestAcceptanceMessage(true, "Import a Text document file with file path '" +
            additionalParams.get(RMConstants.FILETYPE) + "' successfully!");
      }
      return new TestAcceptanceMessage(false,
          "Import a Text document file with file path '" + additionalParams.get(RMConstants.FILETYPE) + "' failed!");
    }
    if (additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.MIGRATION_IMPORT)) {
      if (lastResult.contains("Import completed with errors")) {
        return new TestAcceptanceMessage(false,
            "Import a Migration file with file path '" + additionalParams.get(RMConstants.FILETYPE) + "' failed!");
      }
      return new TestAcceptanceMessage(false,
          "Import a Migration file with file path '" + additionalParams.get(RMConstants.FILETYPE) + "' passed!");
    }
    if (additionalParams.get(RMConstants.IMPORTTYPE).equals(RMConstants.RICH_TEXT_IMPORT)) {
      if (lastResult.contains("The document was converted")) {
        return new TestAcceptanceMessage(true, "Import a rich text document file with file path '" +
            additionalParams.get(RMConstants.FILETYPE) + "' successfully!");
      }
      return new TestAcceptanceMessage(false, "Import a rich text document file with file path '" +
          additionalParams.get(RMConstants.FILETYPE) + "' failed!");
    }
    return new TestAcceptanceMessage(false,
        "Import a file failed. Please check again the type of file to be imported because it does not match with any kind of type in this method or this type is not covered. In case of the type is not covered beforehand, please take time to define new!");
  }

  /**
   * <P>
   * Verifies artifact is exported successfully
   * <P>
   * This method called after {@link RMArtifactsPage#getColumnHeaderFromCSVFileByIndex(String,String)}.
   *
   * @param lastResult -
   * @param columnIndex -
   * @param pathCSVFile -
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetColumnHeaderFromCSVFileByIndex(final String pathCSVFile,
      final String columnIndex, final String lastResult) {
    if (!lastResult.equalsIgnoreCase("")) {
      return new TestAcceptanceMessage(true, "Get column header from CSV File by Index successfully");

    }
    return new TestAcceptanceMessage(false, "Get column header from CSV File by Index fail or header is empty");

  }


  /**
   * <p>
   * Verifies artifact is exported successfully
   * <P>
   * This method called after {@link RMArtifactsPage#expandFilters()}.
   *
   * @param lastResult -
   * @return message
   */
  public TestAcceptanceMessage verifyExpandFilters(final String lastResult) {
    Button btnCollapseFilter =
        this.engine.findElement(Criteria.isButton().withToolTip("Collapse filters")).getFirstElement();
    if (btnCollapseFilter.isElementEnable(Duration.ofSeconds(50))) {
      return new TestAcceptanceMessage(true, "Expand Filter successfully");
    }
    return new TestAcceptanceMessage(false, "Can not expand filter");
  }


  /**
   * <p>
   * Verifies artifact is exported successfully
   * <P>
   * This method called after {@link RMArtifactsPage#isViewSelected(String)}.
   *
   * @param view name of view
   * @param lastResult last result of {@link RMArtifactsPage#isViewSelected(String)}.
   * @return true if view selected else it will return false
   */
  public TestAcceptanceMessage verifyIsViewSelected(final String view, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "View as " + view + " is selected successfully");
    }
    return new TestAcceptanceMessage(false, "View as " + view + " is not selected");
  }


  /**
   * @author UUM4KOR
   * @param childArtifactName child artifact name
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySetNewChildArtifactName(final String childArtifactName, final String lastResult) {

    if (!childArtifactName.isEmpty()) {
      return new TestAcceptanceMessage(true, "Successfully set artifact name : '" + childArtifactName + "'");
    }

    return new TestAcceptanceMessage(false, "Successfully not set artifact name as : '" + childArtifactName + "'");
  }

  /**
   * @author UUM4KOR
   * @param childArtifactName child artifact name .
   * @param lastResult string.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetChildArtifactId(final String childArtifactName, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Successfully get child artifact id : '" + lastResult + "' of child artifact name : " + childArtifactName);
    }

    return new TestAcceptanceMessage(false,
        "Successfully not get child artifact id : '" + lastResult + "' of child artifact name : " + childArtifactName);
  }

  /**
   * @author UUM4KOR
   * @param additionalParam warning message
   * @param lastResult string warning message
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetValidationMessageFromConfirmRemovaldialog(final String additionalParam,
      final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalParam),
          "Actual validation message is : '" + lastResult + "' Expected validation message is : '" + additionalParam);
    }

    return new TestAcceptanceMessage(false,
        "Actual validation message is : '" + lastResult + "' Expected validation message is : '" + additionalParam);
  }

  /**
   * @param attributeName artifact name
   * @param attributeValue attribute value
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyChooseAttributeValueForSelectedAttribute(final String attributeName,
      final String attributeValue, final String lastResult) {

    if (!attributeName.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Successfully selected arifact name as : '" + attributeName + "' and value as : '" + attributeValue);
    }

    return new TestAcceptanceMessage(false,
        "Successfully not selected arifact name as : '" + attributeName + "' and value as : '" + attributeValue);
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         verify the check box to delete conformation from remove dialog
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifySelectCheckBoxFromConfirmRemovalDialog(final String lastResult) {

    if (this.driverCustom.isElementPresent("//button[text()='Remove' or text()='Remove and Delete']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Successfully verified 'Remove and Delete' button is visible after selecting 'If the artifacts are not in other modules, permanently delete them' check box");
    }

    return new TestAcceptanceMessage(false,
        "Successfully verified 'Remove and Delete' button is not visible after selecting 'If the artifacts are not in other modules, permanently delete them' check box");
  }

  /**
   * @author UUM4KOR
   * @param artifactName name of new artifact
   * @param artifactType type of new artifact
   * @param artifactFormat formate of new artifact
   * @param lastResult string artifact id
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnCreateNewArtifactLinkAndSetName(final String artifactName,
      final String artifactType, final String artifactFormat, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "successfully clicked on 'Create New Artifact' link and New artifact id is : " + lastResult);
    }

    return new TestAcceptanceMessage(false,
        "successfully not clicked on 'Create New Artifact' link and New artifact id not set. actual id is : " +
            lastResult);
  }

  /**
   * This method called after {@link RMArtifactsPage#isButtonDisplayed(String)}.<br>
   *
   * @author KYY1HC
   * @param title value of title attribute of button
   * @param lastResult last result return
   * @return true if button is displayed else it will return false
   */
  public TestAcceptanceMessage verifyIsButtonDisplayed(final String title, final String lastResult) {
    return verifyIsButtonDisplayed(title, "TRUE", lastResult);
  }

  /**
   * This method called after {@link RMArtifactsPage#isButtonDisplayed(String)}.<br>
   *
   * @author KYY1HC
   * @param title value of title attribute of button
   * @param additionalParameter parameter for failed case
   * @param lastResult last result return
   * @return true if button is displayed else it will return false
   */
  public TestAcceptanceMessage verifyIsButtonDisplayed(final String title, final String additionalParameter,
      final String lastResult) {
    String messagePassed = "Verified: PASSED - Button with title '";
    String messageFailed = "Verified: FAILED - Button with title '";
    if (additionalParameter.toLowerCase().equalsIgnoreCase("true")) {
      if (lastResult.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, messagePassed + title + " is displayed");
      }
      return new TestAcceptanceMessage(false, messageFailed + title + " is not displayed");
    }
    if (lastResult.equalsIgnoreCase("false")) {
      return new TestAcceptanceMessage(true, messagePassed + title + " is not displayed");
    }
    return new TestAcceptanceMessage(false, messageFailed + title + " is displayed");
  }

  /**
   * <p>
   * Verify artifact is delete without searching after ${@link RMArtifactsPage#deleteArtifactWithoutSearch(String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact you want to delete
   * @param lastResult result from ${@link RMArtifactsPage#deleteArtifactWithoutSearch(String)}
   * @return true if artifact is deleted successfully or vice versa
   */
  public TestAcceptanceMessage verifyDeleteArtifactWithoutSearch(final String artifactID, final String lastResult) {
    try {
      this.driverCustom.getWebElement("//a[@class='jazz-ui-ResourceLink' and text()='" + artifactID + "']");
      return new TestAcceptanceMessage(false, "Artifact '" + artifactID + "' is not deleted successfully!");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(true, "Artifact '" + artifactID + "' is deleted successfully!");
    }
  }

  /**
   * @author LPH1HC
   *         <p>
   *         Verifies artifact is exported successfully
   *         <P>
   *         This method called after
   *         {@link RMArtifactsPage#selectConditionWhenAddAndCloseListValueForAttributeInFilter(Map)}.
   * @param additionalParams - additionalParams
   * @param lastResult result from
   *          ${@link RMArtifactsPage#selectConditionWhenAddAndCloseListValueForAttributeInFilter(Map)}
   * @return true if add new attribute filter with condition successfully
   */
  public TestAcceptanceMessage verifySelectConditionWhenAddAndCloseListValueForAttributeInFilter(
      final Map<String, String> additionalParams, final String lastResult) {
    boolean flag = false;
    String attribute = additionalParams.get("ARTIFACT_NAME");
    String expectCondition = additionalParams.get("EXPECT_CONDITION");
    String filterResultXpath = "//div[@title='" + attribute + " - " + expectCondition + " - (any)']";

    // verify filter display after Add filter dialog close
    if (this.driverCustom.isElementVisible(filterResultXpath, Duration.ofSeconds(20))) {
      flag = true;
    }

    // wait for loading complete - disappear "Loading..." message

    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE, Duration.ofSeconds(180));

    if (flag) {
      return new TestAcceptanceMessage(true,
          "Add " + attribute + " filter successfully with value as " + expectCondition);
    }
    return new TestAcceptanceMessage(false, "Add " + attribute + " filter fail with value as " + expectCondition);
  }

  /**
   * @author LPH1HC <br>
   *         Count number of tab in browser and verify this method as
   *         {@link RMArtifactsPage#clickOnArtifactByOpenNewTab(String)}.
   * @param artifactID - Artifact ID hyperlink need to open
   * @param additionParam - expected number of tab display in window
   * @param lastResult -result from {@link RMArtifactsPage#clickOnArtifactByOpenNewTab(String)}.
   * @return true if open new tab successfully
   */
  public TestAcceptanceMessage verifyClickOnArtifactByOpenNewTab(final String artifactID, final String additionParam,
      final String lastResult) {
    String artifactIDInNewTab = "";
    int expectTab = Integer.parseInt(additionParam);
    WebDriver driver = this.driverCustom.getWebDriver();
    Set<String> allWindowHandles = driver.getWindowHandles();
    ArrayList<String> tabs = new ArrayList<String>(allWindowHandles);
    int count = tabs.size();
    ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(1));
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.ARTIFACT_ID_XPATH, Duration.ofSeconds(150));
    artifactIDInNewTab = this.driverCustom.getWebElement(RMConstants.ARTIFACT_ID_XPATH).getText();
    driver.switchTo().window(tabs2.get(0));
    waitForPageLoaded();
    if ((count == expectTab) && artifactIDInNewTab.equalsIgnoreCase(artifactID)) {
      return new TestAcceptanceMessage(true,
          "Verify Click On Artifact " + artifactID + " By Open New Tab successfully");
    }
    return new TestAcceptanceMessage(false, "Verify Click On Artifact " + artifactID + " By Open New Tab fail");
  }

  /**
   * @author LPH1HC <br>
   *         verify for method
   *         {@link RMArtifactsPage#addAndCloseListValueByTextFieldForAttributeInFilter(String,String)}.
   * @param attributeName - Attribute Name
   * @param listValue - list value
   * @param lastResult -result from
   *          {@link RMArtifactsPage#addAndCloseListValueByTextFieldForAttributeInFilter(String,String)}.
   * @return true if add attribute in filter successfully
   */

  public TestAcceptanceMessage verifyAddAndCloseListValueByTextFieldForAttributeInFilter(final String attributeName,
      final String listValue, final String lastResult) {
    String listValuetemp = listValue;
    String filterResultXpath = "";
    switch (attributeName) {
      case "Full Text":
        filterResultXpath = "//div[@class='condition-div' and @title='" + attributeName + " -  - " + listValue + "']";
        break;
      case "Verification Criteria":
      case "Verif Criteria":
        filterResultXpath =
        "//div[@class='condition-div' and starts-with(@title,'Verif') and contains(@title,'" + listValue + "')]";
        break;
      default: 
        if (listValue.contains(",")) {
          listValuetemp = listValue.replace(",", ", ");
        }
        filterResultXpath = "//span[@class='condition-summary-title' and text()='" + attributeName + "']/following-sibling::span[text()='" + listValuetemp + "']";
    }
    this.driverCustom.isElementInvisible(RMConstants.LOADING_MESSAGE, Duration.ofSeconds(180));
    if (this.driverCustom.isElementVisible(filterResultXpath, Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - Add " + attributeName + " filter successfully with value as " + listValue);
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Add " + attributeName + " filter fail with value as " + listValue);
  }

  /**
   * Verify for method {@link RMArtifactsPage#searchGoToInModuleFindWhat(String,String)}.
   *
   * @author KCE1KOR
   * @param artifactContent -
   * @param artifactID -
   * @param lastResult -
   * @return -
   */
  public TestAcceptanceMessage verifySearchGoToInModuleFindWhat(final String artifactContent, final String artifactID,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: Searched  Artifact ID: " + artifactID +
          " Displayed successfully related to the searched '" + artifactContent + "' Content");
    }
    return new TestAcceptanceMessage(false, "Verify: Searched  Artifact ID: " + artifactID +
        "  is 'Not' Displayed  successfully related to the searched '" + artifactContent + "' Content");
  }

  /**
   * @author KCE1KOR verify for method
   *         {@link RMArtifactsPage#selectSearchedArtifactCheckBoxAndCopyContent(String, String)}.
   * @param artifactContent -
   * @param artifactID -
   * @param lastResult -
   * @return -
   */
  public TestAcceptanceMessage verifySelectSearchedArtifactCheckBoxAndCopyContent(final String artifactContent,
      final String artifactID, final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CHECKBOX_OFARTIFACT_XPATH, Duration.ofSeconds(10), artifactID)) {
      return new TestAcceptanceMessage(true, "Verify: Searched  Artifact using content is  " + artifactID +
          "  Displayed successfully and copied content of the artifact");
    }
    return new TestAcceptanceMessage(false, " Verify: Searched  Artifact using content is  " + artifactID +
        " is 'Not' Displayed successfully and 'Not' copied content of the artifact ");
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used after creating {@link RMArtifactsPage#clickOnCreateNewArtifactLinkAndSetName}
   * @param artifactName name of artifact which artifact id required
   * @param lastResult string attribute id.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyGetArtifactIdFromModule(final String artifactName, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "successfully get artifact id '" + lastResult + "' from module '" + artifactName + "'");
    }

    return new TestAcceptanceMessage(false,
        "successfully not get artifact id '" + lastResult + "' from module '" + artifactName + "'");
  }

  /**
   * @uthor UUM4KOR
   *        <p>
   *        this method used after {@link RMArtifactsPage#rightClickOnSelectedArtifactContents(String, String)}
   * @param artifactId artifact id
   * @param artifactName artifact name
   * @param lastResult not used
   * @return boolean
   */
  public TestAcceptanceMessage verifyRightClickOnSelectedArtifactContents(final String artifactId,
      final String artifactName, final String lastResult) {
    if (this.driverCustom.isElementVisible("//div[@class='editmenu com-ibm-rdm-web-grid-EditMenu']", Duration.ofSeconds(10))) {

      return new TestAcceptanceMessage(true, "Verified: context menu of artifact is displayed in the grid view of '" +
          artifactId + "'\nContext menu Displayed successfully after right clicking on artifact " + artifactName);
    }
    return new TestAcceptanceMessage(false, "Verified: context menu of artifact is not displayed in the grid view of " +
        artifactId + " \nContext menu not Displayed successfully after right clicking on artifact" + artifactName);
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         This method used after {@link RMArtifactsPage#refresh}
   * @param lastResult not used
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyRefresh(final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));

    if (this.driverCustom.isElementVisible("//a[text()='Artifacts']", Duration.ofSeconds(60))) {

      return new TestAcceptanceMessage(true,
          "Verified: 'Artifacts' menu of artifact is displayed in the RM page. \nArtifacts menu Displayed successfully after Browser refresh.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: 'Artifacts' menu of artifact is not displayed in the RM page. \nRM Artifacts menu not Displayed successfully after Browser refresh.");
  }

  /**
   * This method used after {@link RMArtifactsPage#chooseAllAttributeValuesForSelectedAttribute(String, String, String)}
   *
   * @author VDY1HC <br>
   * @param expectedValue - expected test displayed in filter section Ex: Artifact Type - is any of - (*your expected
   *          value*) displayed in filter section
   * @param attributeName - name of the attribute to add in the filter.<br>
   *          e.g. Artifact Type,Artifact ID etc.
   * @param valueCondition - Condition value for attributeName<br>
   *          e.g. is any of, is not any of
   * @param lastResult - number of selected values
   * @return the verification message
   */
  public TestAcceptanceMessage verifyChooseAllAttributeValuesForSelectedAttribute(final String attributeName,
      final String valueCondition, final String expectedValue, final String lastResult) {
    String nameAndCondition = attributeName + " - " + valueCondition;
    WebElement conditionDiv =
        this.driverCustom.getPresenceOfWebElement(RMConstants.ARTIFACTSPAGE_FILTER_CONDITION_XPATH, nameAndCondition);
    String textDisplayedInCondition =
        conditionDiv.findElement(By.xpath(RMConstants.ARTIFACTSPAGE_FILTER_ATTRIBUTEVALUE_CONDITION_XPATH)).getText();
    if (Integer.parseInt(lastResult) > 8) {
      return new TestAcceptanceMessage(textDisplayedInCondition.contains(lastResult),
          "Successfully filter with condtion: " + nameAndCondition + " - " + expectedValue);
    }
    return new TestAcceptanceMessage(textDisplayedInCondition.equalsIgnoreCase(expectedValue),
        "Verify: Filter with condtion: " + nameAndCondition + " - " + expectedValue);
  }

  /**
   * @author LPH1HC <br>
   *         This method verify result of {@link RMArtifactsPage#isEmptyDataExistingFromArtifactTable(String)} <br>
   * @param additionalparam - additional parameter expect result as true if expect there is empty data, as false if
   *          there is not any empty data <br>
   * @param columnName columnName Name of column in Artifact Table <br>
   * @param lastResult result of {@link RMArtifactsPage#isEmptyDataExistingFromArtifactTable(String)} <br>
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsEmptyDataExistingFromArtifactTable(final String columnName,
      final String additionalparam, final String lastResult) {
    String message = "Verify data in Artifact Table with column ";
    if (lastResult.equalsIgnoreCase(additionalparam)) {
      if (additionalparam.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, message + columnName + " . There is at least one existing empty data");
      }
      return new TestAcceptanceMessage(true, message + columnName + " . There is not any existing empty data");
    }

    if (additionalparam.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(false, message + columnName + " . There is not any existing empty data");
    }
    return new TestAcceptanceMessage(false, message + columnName + " .  There is at least one existing empty data");
  }


  /**
   * This method verify result of {@link RMArtifactsPage#verifyDataDisplayedInColumn(String,String)} <br>
   * 
   * @author KYY1HC <br>
   * @param additionalparam - additional parameter expect result as true if expect expected data existed, as false if it
   *          is not existed <br>
   * @param columnName columnName Name of column in Artifact Table <br>
   * @param dataCompare the value in cell to verify is displayed or not <br>
   * @param lastResult result of {@link RMArtifactsPage#verifyDataDisplayedInColumn(String,String)} <br>
   * @return the verification message
   */
  public TestAcceptanceMessage verifyVerifyDataDisplayedInColumn(final String columnName, final String dataCompare,
      final String additionalparam, final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalparam)) {
      if (additionalparam.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true,
            String.format("Verified: Data '%s' is displayed in Column '%s' from table.", dataCompare, columnName));
      }
      return new TestAcceptanceMessage(true,
          String.format("Verified: Data '%s' is NOT displayed at Column '%s'.'", dataCompare, columnName));
    }

    if (additionalparam.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(false,
          String.format("Verified: Data '%s' is NOT displayed in Column '%s' from table.", dataCompare, columnName));
    }
    return new TestAcceptanceMessage(false,
        String.format("Verified: Data '%s' is displayed at Column '%s'.'", dataCompare, columnName));
  }

  /**
   * @author LPH1HC <br>
   *         This method verify result of
   *         {@link RMArtifactsPage#isExistingIDAndURLInOneColumnFromExcelXLSXExport(String,String,String)} <br>
   * @param excelPath - Path of excel file (XLSX)<br>
   * @param sheetIndex - sheet index of excel file need to use <br>
   * @param columnIndex - column index need to check <br>
   * @param additionalparam - additional parameter - true if expect existing ID and URL
   * @param lastResult result of
   *          {@link RMArtifactsPage#isExistingIDAndURLInOneColumnFromExcelXLSXExport(String, String, String)}<br>
   *          <br>
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsExistingIDAndURLInOneColumnFromExcelXLSXExport(final String excelPath,
      final String sheetIndex, final String columnIndex, final String additionalparam, final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalparam)) {
      return new TestAcceptanceMessage(true,
          "The excel export has the link details under the " + columnIndex + " column with the link id and its url");

    }
    return new TestAcceptanceMessage(false,
        "Not found link id and url under the " + columnIndex + " column in excel export");
  }

  /**
   * @author LPH1HC <br>
   *         This method verify result of {@link RMArtifactsPage#openCreateTermDialogByRightClick()} <br>
   * @param lastResult result of {@link RMArtifactsPage#openCreateTermDialogByRightClick()}<br>
   * @return the verification message <br>
   */
  public TestAcceptanceMessage verifyOpenCreateTermDialogByRightClick(final String lastResult) {
    String getHeaderDialog = "";
    if (this.driverCustom.isElementVisible(RMConstants.XPATH_HEADING_OF_CREATE_TERM_DIALOG,Duration.ofSeconds(50))) {
      getHeaderDialog = this.driverCustom.getWebElement(RMConstants.XPATH_HEADING_OF_CREATE_TERM_DIALOG).getText();
    }

    this.driverCustom.switchToDefaultContent();
    if (getHeaderDialog.equalsIgnoreCase("Create Term")) {
      return new TestAcceptanceMessage(true, "Open Create Term Dialog by right click successfully");

    }
    return new TestAcceptanceMessage(false, "Open Create Term Dialog by right click fail");
  }


  /**
   * @author LPH1HC <br>
   *         This method verify result of {@link RMArtifactsPage#createNewTerm(Map)} <br>
   * @param additionalParams - text need to selected in text content of artifact, included <br>
   *          Initial_Content: Initial Content <br>
   *          Artifact_Type: Artifact Type <br>
   * @param lastResult - of {@link RMArtifactsPage#createNewTerm(Map)} <br>
   * @return the verification message <br>
   */
  public TestAcceptanceMessage verifyCreateNewTerm(final Map<String, String> additionalParams,
      final String lastResult) {
    if (this.driverCustom.isElementInvisible(RMConstants.XPATH_HEADING_OF_CREATE_TERM_DIALOG,Duration.ofSeconds(50))) {
      return new TestAcceptanceMessage(true,
          "Create Term successfully - with initial term as " +
              additionalParams.get(RMConstants.INITIAL_CONTENT_IN_CREATE_TERM_DIALOG) + " and Artifact Type as " +
              additionalParams.get(RMConstants.ARTIFACT_TYPE_IN_CREATE_TERM_DIALOG));
    }
    return new TestAcceptanceMessage(false,
        "Create Term fail - with initial term as " +
            additionalParams.get(RMConstants.INITIAL_CONTENT_IN_CREATE_TERM_DIALOG) + " and Artifact Type as " +
            additionalParams.get(RMConstants.ARTIFACT_TYPE_IN_CREATE_TERM_DIALOG));
  }

  /**
   * @author LPH1HC <br>
   *         This method verify result of {@link RMArtifactsPage#editArtifactIDFilter(String)} <br>
   * @param newID - new Artifact ID <br>
   * @param lastResult - of {@link RMArtifactsPage#editArtifactIDFilter(String)} <br>
   * @return the verification message <br>
   */
  public TestAcceptanceMessage verifyEditArtifactIDFilter(final String newID, final String lastResult) {
    this.driverCustom.isElementInvisible(RMConstants.XPATH_MESSAGE_LOADING, Duration.ofSeconds(20));
    String getFilter = this.driverCustom.getWebElement(RMConstants.XPATH_ARTIFACT_ID_FILTER).getText();
    getFilter = getFilter.replace(",", "");
    if (getFilter.equalsIgnoreCase(newID)) {
      return new TestAcceptanceMessage(true, "Update Artifact ID filter successfully with new ID as " + newID);
    }
    return new TestAcceptanceMessage(false, "Update Artifact ID filter fail with new ID as " + newID);
  }

  /**
   * @author LPH1HC <br>
   *         This method verify result of {@link RMArtifactsPage#openLookUpTermDialogByRightClick()} <br>
   * @param lastResult - of {@link RMArtifactsPage#openLookUpTermDialogByRightClick()} <br>
   * @return the verification message <br>
   */
  public TestAcceptanceMessage verifyOpenLookUpTermDialogByRightClick(final String lastResult) {
    boolean result = false;
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    List<WebElement> listLookUpTerm = this.driverCustom.getVisibleWebElements(RMConstants.XPATH_HEADER_LOOKUP_TERM);
    if (!listLookUpTerm.isEmpty()) {
      result = true;
    }
    if (result) {
      return new TestAcceptanceMessage(true, "Open Look Up Term Dialog by right click successfully");
    }
    return new TestAcceptanceMessage(false, "Open Look Up Term Dialog by right click fail");
  }


  /**
   * @author LPH1HC <br>
   *         This method verify result of {@link RMArtifactsPage#isTermExistingLookUpTermDialog(String)} <br>
   * @param termKeyWord - term was created before<br>
   * @param lastResult - of {@link RMArtifactsPage#isTermExistingLookUpTermDialog(String)} <br>
   * @return the verification message <br>
   */
  public TestAcceptanceMessage verifyIsTermExistingLookUpTermDialog(final String termKeyWord, final String lastResult) {
    Actions closeDialog = new Actions(this.driverCustom.getWebDriver());
    closeDialog.sendKeys(Keys.ESCAPE).build().perform();
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Term as " + termKeyWord + " display in Look Up Term dialog");
    }
    return new TestAcceptanceMessage(false, "Not found term as " + termKeyWord + " in Look Up Term dialog");
  }


  /**
   * <p>
   * Verify column is removed from the artifact table after ${@link RMArtifactsPage#removeColumnFromTable(String)}
   * <p>
   *
   * @author NVV1HC
   * @param columnName name of column is removed, e.g: Artifact Type, Name, Content, etc.
   * @param lastResult result from ${@link RMArtifactsPage#removeColumnFromTable(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyRemoveColumnFromTable(final String columnName, final String lastResult) {
    List<WebElement> viewColumnHeaders = this.driverCustom.getWebDriver()
        .findElements(By.xpath(RMConstants.RMARTIFACTSPAGE_COLUMNBORDER_DROPDOWN_XPATH));
    List<String> columnheaders = new ArrayList<>();
    for (int i = 0; i < viewColumnHeaders.size(); i++) {
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView();",
          viewColumnHeaders.get(i));
      columnheaders.add(viewColumnHeaders.get(i).getText().trim());
    }
    if (columnheaders.contains(columnName)) {
      return new TestAcceptanceMessage(false,
          "Verify removing column '" + columnName + "' failed. This column is not removed from the table!");
    }
    return new TestAcceptanceMessage(true,
        "Verify removing column '" + columnName +
        "' passed. This column is removed from the table!\nList column displayed: " + columnheaders +
        "\nColumn should be removed: " + columnName);
  }

  /**
   * <p>
   * Verify 'Save changes to this review' button is invisible after clicking
   * ${@link RMArtifactsPage#clickSaveChangesToThisView()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMArtifactsPage#clickSaveChangesToThisView()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickSaveChangesToThisView(final String lastResult) {
    boolean isSaveChangesToThisReviewButtonInvisible =
        this.driverCustom.isElementInvisible(RMConstants.ARTIFACTPAGE_SAVECHANGESTOTHISVIEW_XPATH,  Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
    if (isSaveChangesToThisReviewButtonInvisible) {
      return new TestAcceptanceMessage(true, "Verify click on 'Save changes to this review' button passed!");
    }
    return new TestAcceptanceMessage(false, "Verify click on 'Save changes to this review' button failed!");
  }

  /**
   * @author LPH1HC This method verify result of
   *         ${@link RMArtifactsPage#isAllTheCountValuesMatchingWithTheOriginalValue(String)}
   * @param originalValue -
   * @param lastResult -
   * @return verification message
   */
  public TestAcceptanceMessage verifyIsAllTheCountValuesMatchingWithTheOriginalValue(final String originalValue,
      final String lastResult) {

    String showingArtifacttxt = "";
    String filteredCount = "";
    try {
      if (lastResult.equalsIgnoreCase("true")) {
        showingArtifacttxt =
            this.driverCustom.getWebElement(RMConstants.NUMBER_ARTIFACT_SHOWING_IN_TABLE_XPATH).getText();
        filteredCount = showingArtifacttxt.split(" ")[1].trim();
        return new TestAcceptanceMessage(true,
            "Verify all the count of filtered values is matching with the expected value as " + filteredCount);
      }
    }
    catch (Exception e) {
      LOGGER.LOG.info("Verify all the filtered count values is mismatching with the expected value");
    }
    if (lastResult.equalsIgnoreCase("true")) {

      return new TestAcceptanceMessage(true,
          "Verify all the count values is matching with the original value as " + originalValue);
    }
    return new TestAcceptanceMessage(false, "Verify all the count values is mismatching with the original value");
  }

  /**
   * @author LPH1HC This method verify result of
   *         ${@link RMArtifactsPage#isPassedStatusOfValidatedByLinkExistingInOneArtifactRow()}
   * @param lastResult -
   * @return verification message
   */
  public TestAcceptanceMessage verifyIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify there is at least one Validated By link havs Passed status in one Artifact row");
    }
    return new TestAcceptanceMessage(false,
        "Verify not found any Validate By link having Passed status in one Artifact row");
  }

  /**
   * Verify if there is no error message displayed <br>
   * This method called after {@link RMArtifactsPage#verifyNoErrorMessageDisplay()}
   *
   * @author VDY1HC
   * @param lastResult - last result return
   * @return true - if error message displayed false - if no error message displayed
   */
  public TestAcceptanceMessage verifyVerifyNoErrorMessageDisplay(final String lastResult) {
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "No error message displayed");
    }
    WebElement errorMsg = this.driverCustom.getPresenceOfWebElement(RMConstants.ERROR_MESSAGE_XPATH);
    return new TestAcceptanceMessage(false, "Error message with text: '" + errorMsg.getText() + "' displayed");
  }

  /**
   * <p>
   * Verifies condition is set in filter successfully.
   * <P>
   * This method called after {@link RMArtifactsPage#isConditionSetInFilter(String)}.
   *
   * @author KYY1HC
   * @param condition the condition in the Filter.
   * @param lastResult last result return
   * @return true if condition is displayed else it will return false.
   */
  public TestAcceptanceMessage verifyIsConditionSetInFilter(final String condition, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Condition '" + condition + "' is displayed.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Condition '" + condition + "' is not displayed.");
  }

  /**
   * <p>
   * Verifies click to override the lock artifact successfully.
   * <P>
   * This method called after {@link RMArtifactsPage#overrideTheLockedArtifact()}.
   *
   * @author KYY1HC
   * @param lastResult last result return
   * @return true if condition is displayed else it will return false.
   */
  public TestAcceptanceMessage verifyOverrideTheLockedArtifact(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - The locked artifact is unlocked successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - The artifact is still locked by other user");
  }

  /**
   * <p>
   * Verifies remove filter by full-text of condition successfully.
   * <P>
   * This method called after {@link RMArtifactsPage#removeFilterByCondition(String)}.
   *
   * @author KYY1HC
   * @param conditionText full-text of condition
   * @param lastResult last result return
   * @return true if condition is displayed else it will return false.
   */
  public TestAcceptanceMessage verifyRemoveFilterByCondition(final String conditionText, final String lastResult) {
    if (this.driverCustom.isElementInvisible(String.format("//div[@class='condition-div'][@title='%s']", conditionText),
        Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true,
          String.format("Verified: PASSED - The filter with condition '%s' is removed successfully.", conditionText));
    }
    return new TestAcceptanceMessage(false,
        String.format("Verified: FAILED - Unable to remove filter with condition '%s'", conditionText));
  }

  /**
   * verify for method {@link RMArtifactsPage#isColumnHeaderDisplayed(String)}.
   *
   * @author KYY1HC
   * @param columnName name of Column
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsColumnHeaderDisplayed(final String columnName, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Column name " + columnName + " is displayed on Table");
    }
    return new TestAcceptanceMessage(false,
        " Verify: FAILED - Column name " + columnName + " is 'Not' Displayed on Table");
  }

  /**
   * Verify for method {@link RMArtifactsPage#setDataFromCellOfXLSX(String, String, String, String, String)}.
   *
   * @author VDY1HC
   * @param excelPath - path of excel file , type as XLSX
   * @param sheetIndex - sheet index need to read value
   * @param rowIndex - row index need to read value
   * @param columnIndex - column index need to read value
   * @param updateValue - new value to be updated in XLSX
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetDataFromCellOfXLSX(final String excelPath, final String sheetIndex,
      final String rowIndex, final String columnIndex, final String updateValue, final String lastResult) {
    ExcelCSVReader reader = new ExcelCSVReader(excelPath);
    String currentValue = reader.readDataFromCellOfXLSX(excelPath, sheetIndex, rowIndex, columnIndex);
    if (currentValue.equals(updateValue)) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Excel cell data is updated with new value \nActual value: " + currentValue +
          "\nExpected value: " + updateValue);
    }
    return new TestAcceptanceMessage(true,
        "Verify: FAILED - Excel cell data is NOT updated with new value \nActual value: " + currentValue +
        "\nExpected value: " + updateValue);
  }

  /**
   * Verify for method {@link RMArtifactsPage#getCurrentURL()}.
   *
   * @param lastResult - lastResult return
   * @return test acceptance message
   */
  public TestAcceptanceMessage verifyGetCurrentURL(final String lastResult) {
    if (lastResult.isEmpty()) {
      return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to get URL of current webpage");
    }
    return new TestAcceptanceMessage(true, "Verified: PASSED - URL of current webpage: " + lastResult);
  }

  /**
   * <p>
   * This method is called after running ${@link RMArtifactsPage#verifyArtifactVisibleInViewport(String)}}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact to be verified
   * @param lastResult result from the main method ${@link RMArtifactsPage#verifyArtifactVisibleInViewport(String)}}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyArtifactVisibleInViewport(final String artifactID, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify artifact '" + artifactID + "' is displayed in viewport: PASSED!");
    }
    return new TestAcceptanceMessage(false, "Verify artifact '" + artifactID + "' is displayed in viewport: FAILED!");
  }

  /**
   * Verify link details dialog loaded completedy This method called after
   * {@link RMArtifactsPage#hoverOnArtifactLink(String, String, String, String)}.
   *
   * @author VDY1HC
   * @param artifactID - main Artifact ID
   * @param linkType - Link type of linked artifact
   * @param artifactLinkText - Text of link displayed
   * @param waitTimeInSecs - Max time to wait until loading finished
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyHoverOnArtifactLink(final String artifactID, final String linkType,
      final String artifactLinkText, final String waitTimeInSecs, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(Integer.parseInt(waitTimeInSecs)));
    WebDriver driverWithOutWait = this.driverCustom.getWebDriver();
    String linkedArtifactID = artifactLinkText.split(":")[0];
    Boolean popupOpened = false;
    Boolean loaded = false;
    Boolean titleText = false;
    String title = "";
    try {
      loaded = !driverWithOutWait.findElement(By.xpath("//span[@class='image-loading hidden']")).isDisplayed();
      popupOpened = driverWithOutWait.findElement(By.xpath("//iframe[@dojoattachpoint='hoverIframe']")).isDisplayed();
      title = driverWithOutWait.findElement(By.xpath("//a[@dojoattachpoint='titleLink']")).getText();
      titleText = title.contains(linkedArtifactID + ": ");
    }
    catch (Exception e) {
      LOGGER.LOG.info("One condition failed due to: " + e.toString());
    }
    if (popupOpened && loaded && titleText) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Link popup is opened and loaded successfully after " + waitTimeInSecs + " secs " +
              "\nActual loading status: Pop up opened - " + popupOpened + ", Popup loaded - " + loaded +
              ", Title text displayed - " + title);
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Link popup is opened and loaded successfully after " + waitTimeInSecs + " secs " +
            "\nActual loading status: Pop up opened - " + popupOpened + ", Popup loaded - " + loaded +
            ", Title text displayed - " + title);
  }

  /**
   * Verified Clicked on close button on Popup if existing <br>
   * This method called after {@link RMArtifactsPage#clickOnCloseButtonOnPopup()}.
   *
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   * @author VDY1HC
   */
  public TestAcceptanceMessage verifyClickOnCloseButtonOnPopup(final String lastResult) {
    if (this.driverCustom.isElementClickable(RMConstants.CLOSE_BUTTON, Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(false, "Verify: FAILED - Clicked on Close popup button");
    }
    return new TestAcceptanceMessage(true, "Verify: PASSED - Clicked on Close popup button");
  }

  /**
   * <p>
   * This method is called after running ${@link RMArtifactsPage#editVerificationCriteria(String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of an artifact
   * @param content content of Verification Criteria which you inputed
   * @param lastResult result from the main method ${@link RMArtifactsPage#editVerificationCriteria(String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyEditVerificationCriteria(final String artifactID, final String content,
      final String lastResult) {
    String contentDisplayed = this.driverCustom
        .getAttribute(RMConstants.ARTIFACTPAGE_EDIT_VERIFICATOINCRITERIA_TEXTAREA_XPATH, "value").trim();
    if (contentDisplayed.equals(content)) {
      return new TestAcceptanceMessage(true,
          "Verify: Edit Verification Criteria for the artifact '" + artifactID + "' is PASSED!");
    }
    return new TestAcceptanceMessage(false, "Verify: Edit Verification Criteria for the artifact '" + artifactID +
        "' is FAILED!\nContent inputed: '" + content + "'.\nContent displayed: '" + contentDisplayed + "'.");
  }

  /**
   * <p>
   * This method is called after running ${@link RMArtifactsPage#clearVerificationCriteria()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult from ${@link RMArtifactsPage#clearVerificationCriteria()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClearVerificationCriteria(final String lastResult) {
    WebElement inputTextArea =
        this.driverCustom.getWebElement(RMConstants.ARTIFACTPAGE_EDIT_VERIFICATOINCRITERIA_TEXTAREA_XPATH);
    String verificationCriteriaDisplayed = inputTextArea.getAttribute("value");
    if (verificationCriteriaDisplayed.replaceAll(" ", "").isEmpty()) {
      return new TestAcceptanceMessage(true, "Verify: Clear all Verification Criteria value: PASSED");
    }
    return new TestAcceptanceMessage(false,
        "Verify: Clear all Verification Criteria value: FAILED.\nActual result: Verification Criteria is not empty, it's: '" +
            inputTextArea.getText() + "'");
  }

  /**
   * <p>
   * This method is called after running
   * ${@link RMArtifactsPage#closeVerificationCriteriaPopupByClickingOutOfThePopup()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult from ${@link RMArtifactsPage#closeVerificationCriteriaPopupByClickingOutOfThePopup()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyCloseVerificationCriteriaPopupByClickingOutOfThePopup(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: Close Verification Criteria popup without clicking OK or Cancel button successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verify: Close Verification Criteria popup without clicking OK or Cancel button failed!");
  }

  /**
   * <p>
   * This method is called after executing the method ${@link RMArtifactsPage#clickOnApplyFilters()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMArtifactsPage#clickOnApplyFilters()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnApplyFilters(final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: Click on 'Apply' button successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify: Click on 'Apply' button failed!");
  }

  /**
   * <p>
   * This method is called after executing the method ${@link RMArtifactsPage#clickOnAddExistingArtifactOption()}
   * <p>
   *
   * @author KCE1KOR
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
   * This method is called after executing ${@link RMArtifactsPage#exportView(String, String)}}
   * <p>
   *
   * @author NVV1HC
   * @param viewName name of view to be exported
   * @param exportFormat format of exported file, e.g: XLS, CSV
   * @param lastResult result from ${@link RMArtifactsPage#exportView(String, String)}}
   * @return verification message
   */
  public TestAcceptanceMessage verifyExportView(final String viewName, final String exportFormat,
      final String lastResult) {
    if (lastResult.contains("export." + exportFormat.toLowerCase())) {
      return new TestAcceptanceMessage(true, "Verify: Export view successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify: Export view failed!");
  }

  /**
   * <p>
   * This method is called after executing
   * ${@link RMArtifactsPage#verifyAttributeValueOfArtifactDisplayedInXLSFile(String, String, String, String)}}
   * <p>
   *
   * @author NVV1HC
   * @param expectedAttributeValues expected value displays in XLS file
   * @param fileNameAndPath name and path of file
   * @param artifactID ID of artifact to be verified
   * @param attribute attribute, e.g: Link:Satisfied By Architecture Element, Link:Validated By
   * @param lastResult result from
   *          ${@link RMArtifactsPage#verifyAttributeValueOfArtifactDisplayedInXLSFile(String, String, String, String)}}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyAttributeValueOfArtifactDisplayedInXLSFile(
      final String expectedAttributeValues, final String fileNameAndPath, final String artifactID,
      final String attribute, final String lastResult) {
    ExcelCSVReader excelReader = new ExcelCSVReader(fileNameAndPath);
    String actualValue = "";
    try {
      actualValue = excelReader.getAttributeValueOfArtifactFromXLSFile(attribute, artifactID);
    }
    catch (IOException e) {
    }
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: value of attribute '" + attribute + "' of artifact '" +
          artifactID + "' is displayed in XLS file as expectation!");
    }
    return new TestAcceptanceMessage(false,
        "Verify: value of attribute '" + attribute + "' of artifact '" + artifactID +
        "' is not displayed or displayed incorrectly in XLS file as expectation!\nExpected value: \n'" +
        expectedAttributeValues + "'.\nActual value: \n'" + actualValue + "'.");
  }

  /**
   * <p>
   * This method is called after executing
   * ${@link RMArtifactsPage#verifyTestCaseStatusUnderValidateByAttributeOfArtifact(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of target artifact
   * @param testCase test case ID and name
   * @param testcaseStatus test case status, e.g: Passed, Failed
   * @param lastResult result from
   *          ${@link RMArtifactsPage#verifyTestCaseStatusUnderValidateByAttributeOfArtifact(String, String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyTestCaseStatusUnderValidateByAttributeOfArtifact(final String artifactID,
      final String testCase, final String testcaseStatus, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: test case status displayed under 'Validated By' column of artifact '" + artifactID +
          "' is displayed correctly.\nExpected status: '" + testcaseStatus + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verify: test case status displayed under 'Validated By' column of artifact '" + artifactID +
        "' is displayed incorrectly.\nExpected status: '" + testcaseStatus + "'.\nActual status is not: '" +
        testcaseStatus + "'.");
  }


  /**
   * <p>
   * This method is called after executing the method ${@link RMArtifactsPage#openEditFilterDialogOfAttributeName}
   * <p>
   *
   * @author PDU6HC
   * @param attributeName name of attribute
   * @param attributeValue name of attribute value
   * @param lastResult result from ${@link RMArtifactsPage#addFilterForAttributeName(String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyAddFilterForAttributeName(final String attributeName, final String attributeValue,
      final String lastResult) {
    String result = "//div[@title='" + attributeName + " - is any of - " + attributeValue + "']";
    if (this.driverCustom.isElementPresent(result, this.timeInSecs)) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Add new Filter and click on 'Update' button successfully!");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Add new Filter and click on 'Update' button failed!");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#getPercentageOfArtifactDisplayed()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMArtifactsPage#getPercentageOfArtifactDisplayed()}
   * @return verification message
   */
  public TestAcceptanceMessage verifyGetPercentageOfArtifactDisplayed(final String lastResult) {
    String percentage = lastResult.substring(0, lastResult.length() - 1);
    if (lastResult.contains("%") & percentage.matches("^[0-9]*$")) {
      return new TestAcceptanceMessage(true, "Verify: Get percentage of artifact displayed in module successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verify: Get percentage of artifact displayed in module successfully.\nActual displayed: " + lastResult);
  }

  /**
   * <p>
   * Verifies 'Collapse filter' button clicked successfully
   * <P>
   * This method called after {@link RMArtifactsPage#collapseFilters()}.
   *
   * @author PDU6HC
   * @param lastResult -
   * @return message
   */
  public TestAcceptanceMessage verifyCollapseFilters(final String lastResult) {
    Button btnExpandFilter =
        this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Expand filters"), this.timeInSecs);
    if (btnExpandFilter.isElementEnable(Duration.ofSeconds(50))) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Collapse Filter successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verify: PASSED - Can not collapse filter, collapse button icon may not visible");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#hoverOnHyperLink(String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact
   * @param hyperLink hyper link displayed in artifact content
   * @param additionalParamHyperLinkIDAndName expected ID and name of linked artifact displayed after hovering on the
   *          hyperlink of the target artifact
   * @param lastResult result from ${@link RMArtifactsPage#hoverOnHyperLink(String,String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyHoverOnHyperLink(final String artifactID, final String hyperLink,
      final String additionalParamHyperLinkIDAndName, final String lastResult) {

    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_HOVER_POPUP_HYPERLINK_INARTIFACTCONTENT_XPATH,
        this.timeInSecs, additionalParamHyperLinkIDAndName)) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Hover on hyperlink of artifact '" + artifactID + "' then a popup is opened successfully.");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Hover on hyperlink of artifact '" + artifactID +
        "' but the popup is not opened successfully.");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#verifyHyperlinkDisplayed(String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact
   * @param hyperLink hyper link displayed in artifact content
   * @param lastResult result from ${@link RMArtifactsPage#verifyHyperlinkDisplayed(String,String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyVerifyHyperlinkDisplayed(final String artifactID, final String hyperLink,
      final String lastResult) {

    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Verify the hyperlink '" + hyperLink +
          "' is displayed in content section of artifact '" + artifactID + "'.");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Verify the hyperlink '" + hyperLink +
        "' is not displayed in content section of artifact '" + artifactID + "'.");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#clickOnSelectAllModules()}
   * <P>
   *
   * @author PDU6HC
   * @param additionalParams is total number of all artifact selected.
   * @param lastResult result from ${@link RMArtifactsPage#clickOnSelectAllModules()}
   * @return All items is selected
   */
  public TestAcceptanceMessage verifyClickOnSelectAllModules(final String additionalParams, final String lastResult) {
    //get the total of modules 
   String totalModules = this.driverCustom.getText("//.[contains(@id,'ProjectDashboard') and contains(@style,'display: block')]//div[@class='footer']/span[@class='numArtifacts']");
   totalModules = totalModules.split(" ")[3].trim();
   System.out.println(totalModules);
   
   //get the number of the selected artifacts
   String numSelectedArtifacts = this.driverCustom.getText("//div[@class='numSelectedArtifacts']/span[@dojoattachpoint='numSelectedArtifacts' and @style='display: inline;']");
   numSelectedArtifacts  = numSelectedArtifacts.split(" ")[0].trim();
   System.out.println(numSelectedArtifacts);
   
   //compare the number of selected artifacts and total artifacts
   boolean result = numSelectedArtifacts.equals(totalModules);
   if(result) {
      
//    if (this.driverCustom.isElementPresent(RMConstants.RMARTIFACTPAGE_SELECTED_MODULE_NUM_XPATH, this.timeInSecs,
//        additionalParams)) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - All artifact modules is selected successfully. Expected number of modules: " +
              numSelectedArtifacts);
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - All artifact modules is not selected. Expected number of modules: " + additionalParams);
  }


  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#removeLinkOfArtifactFromTable(String)}
   * <P>
   *
   * @author PDU6HC
   * @param removeLinkText text of Link need to remove
   * @param lastResult result from ${@link RMArtifactsPage#removeLinkOfArtifactFromTable(String)}
   * @return All items is selected
   */
  public TestAcceptanceMessage verifyRemoveLinkOfArtifactFromTable(final String removeLinkText,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Link of artifact is removed successfully. Expected deleted link: " + removeLinkText);
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Failed to remove Link of artifact. Expected deleted link: " + removeLinkText);
  }


  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#removeLinkOnModuleWithId(String, String)}
   * <P>
   *
   * @author PDU6HC
   * @param moduleId is Id of module.
   * @param linkText is text of the link.
   * @param lastResult result from ${@link RMArtifactsPage#removeLinkOnModuleWithId(String, String)}
   * @return All items is selected
   */
  public TestAcceptanceMessage verifyRemoveLinkOnModuleWithId(final String moduleId, final String linkText,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Expected Link: " + linkText + " of module id: " + moduleId + " is removed successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Expected Link: " + linkText + " of module id: " + moduleId + " is not removed.");
  }

  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#navigateToAuditHistory(String)}
   * <P>
   *
   * @author PDU6HC
   * @param artifactId is Id of module.
   * @param lastResult result from ${@link RMArtifactsPage#navigateToAuditHistory(String)}
   * @return 'Audit History' tab is present.
   */
  public TestAcceptanceMessage verifyNavigateToAuditHistory(final String artifactId, final String lastResult) {
    if (this.driverCustom.isElementPresent(RMConstants.ARTIFACTHISTORYPAGE_AUDITHISTORY_TAB_XPATH, this.timeInSecs)) {
      return new TestAcceptanceMessage(true,
          String.format("Verified: PASSED - Navigated to 'Audit History' of artifact id: %s successfully", artifactId));
    }
    return new TestAcceptanceMessage(false,
        String.format("Verified: FAILED - Navigated to 'Audit History' of artifact id: %s failed", artifactId));
  }

  /**
   * Verify method {@link RMArtifactsPage#hoverOnLinksByText(String)}.
   *
   * @author KYY1HC
   * @param textOfLink text of Link
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyHoverOnLinksByText(final String textOfLink, final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_LINK_HOVERIFRAME_QUICKINFO_XPATH,
        this.timeInSecs)) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Quick-Info is displayed after hovering on the link contains text '" + textOfLink + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Quick-Info is NOT displayed after hovering on the link contains text '" + textOfLink + "'.");
  }

  /**
   * Verify method {@link RMArtifactsPage#checkLinkWithTextOnQuickInfo(String)}.
   *
   * @author KYY1HC
   * @param textOfLink text of Link
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCheckLinkWithTextOnQuickInfo(final String textOfLink, final String lastResult) {
    this.driverCustom.switchToDefaultContent();
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verify: PASSED - Link with text '" + textOfLink + "' is displayed on Quick-Info Frame.");
    }
    return new TestAcceptanceMessage(false,
        "Verify: FAILED - Link with text '" + textOfLink + "' is NOT displayed on Quick-Info Frame.");
  }

  /**
   * Verify method {@link RMArtifactsPage#deleteComment(String, String)}.
   *
   * @author PDU6HC
   * @param artifactId is id of artifact selected
   * @param commentSubject is subject of the comment needed to be deleted
   * @param lastResult - lastResult
   * @return true if comment is deleted
   */
  public TestAcceptanceMessage verifyDeleteComment(final String artifactId, final String commentSubject,
      final String lastResult) {
    String[] commentIndentifier = { artifactId, commentSubject };
    if (!this.driverCustom.isElementVisible(RMConstants.COMMENT_ARTIFACT_XPATH, Duration.ofSeconds(5), commentIndentifier)) {
      return new TestAcceptanceMessage(true, String.format(
          "Verify: PASSED - Comment with subject '%s' in artifact id '%s' is deleted", commentSubject, artifactId));
    }
    return new TestAcceptanceMessage(false, String.format(
        "Verify: FAILED - Comment with subject '%s' in artifact id '%s' is not deleted", commentSubject, artifactId));
  }
  
  /**
   * Verify method {@link RMArtifactsPage#verifyFolderHierarchy(Map)}.
   *
   * @author LTU7HC.
   * @param additionalParams key value pair.
   * @param lastResult last result is {@link RMArtifactsPage#verifyFolderHierarchy(Map)}.
   * @return the verification message.
   */
  public TestAcceptanceMessage verifyVerifyFolderHierarchy(final Map<String, String> additionalParams,
      final String lastResult) {
    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true, "Verified that folder hirarchy displays as expected");
    }
    return new TestAcceptanceMessage(false, "Verified that folder hirarchy does not display as expected");
  }
  /**
   * <p>
   * Verify name of the newly created artifact.
   * <p>
   * This method called after {@link RMArtifactsPage#getArtifactName()}.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetArtifactName(final String lastResult) {
    List<WebElement> results = this.driverCustom.getWebElements("//div[@class='resource-info']//span");
    for (WebElement result : results) {
      if (result.getText().equalsIgnoreCase(lastResult)) {
        return new TestAcceptanceMessage(true,
            "Verified: Artifact Name of the created artifact.\nName of the artifact is \"" + lastResult + "\".");
      }
    }
    return new TestAcceptanceMessage(false,
        "Verified: Artifact Name of the created artifact. Artifact Name \"" + lastResult + "\" not found.");
  }
  /**
   * <p>Verify Artifact is navigated to 'Artifacts' page.
   * <br>This method called after {@link RMArtifactsPage#navigateToArtifactsPage()}.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyNavigateToArtifactsPage(final String lastResult) {
    this.driverCustom.getWebDriver().navigate().refresh();
    if(this.driverCustom.isElementVisible("//div[@class='page-title']", Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true,"Successfully navigated to 'Artifacts' page.");
    }
    return new TestAcceptanceMessage(false,"Page is not navigated to Artifacts page.");
  }
  
  /**
   * <p> 
   * Verify that can select option in dropdown using method {@link RMArtifactsPage#selectOptionFromCreateDropDown(String)}
   * @param option : which selected from dropdown
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectOptionFromCreateDropDown(final String option, final String lastResult) {
   boolean result= this.driverCustom.isElementInvisible(RMConstants.RMARTIFACTSPAGE_SELECTREQUIREMENT_XPATH, Duration.ofSeconds(30), option);
    if(result) {
      return new TestAcceptanceMessage(true,"Select option from dropdown successfully.");
    }
    return new TestAcceptanceMessage(false,"Select option from dropdown fail.");
  }
  
  /**
   * <p>Verify Artifact quick info .
   * <br>This method called after {@link RMArtifactsPage#getArtifactQuickInfo()}.
   * 
   * @param additionalParams key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetArtifactQuickInfo(final Map<String, String> additionalParams,final String lastResult) {
    this.driverCustom.switchToDefaultContent();
    if (!lastResult.isEmpty()) {
      StringBuilder acceptanceMessage = new StringBuilder();
      acceptanceMessage.append("Verified attributes and values of Artifact in Quick Info window.\n\n");
      for (String key : additionalParams.keySet()) {
        if (lastResult.contains("KEY :" + key + " VALUE :" + additionalParams.get(key))) {
          acceptanceMessage
          .append(RMConstants.ACTUAL_VALUE_OF_ATTRIBUTE + key + RMConstants.IS + additionalParams.get(key) +
              "'\nExpected value of attribute '" + key + RMConstants.IS + additionalParams.get(key) + "'.\n\n");
        }
      }
      String msg = acceptanceMessage.toString();
      return new TestAcceptanceMessage(!msg.contains("' not matched with expected."), msg);
    }
    return new TestAcceptanceMessage(false, "Actual result is empty.");
  }
  
  /**
   * <p>Verify option selected from folder.
   * <br><br>This method called after ${@link RMArtifactsPage#selectOptionFromFolder(Map)}.
   * 
   * @param additionalParams represents key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectOptionFromFolder(final Map<String, String> additionalParams, final String lastResult) {
    String dialogHeading = "";
    switch (additionalParams.get("MENU_OPTION")) {
      case "Import Artifact...":
        dialogHeading = "Import";
        break;
      case "Upload Artifact...":
        dialogHeading = "Upload File";
        break;
      default:
        dialogHeading = "Create Artifact";
    }
    boolean result = this.driverCustom
        .isElementVisible("//div[@class='jazz-ui-Dialog-heading' and text()='" + dialogHeading + "']", timeInSecs);
    return new TestAcceptanceMessage(result,
        "Verified: For the folder '" + additionalParams.get("FOLDER_NAME") + "' and selected option '" + additionalParams.get("MENU_OPTION"));
  }
  
  /**
   * <p>Verify location of an Artifact.
   * <br>This method called after {@link RMArtifactsPage#getLocationOfAnArtifact()}.
   * 
   * @param additionalParam represents key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetLocationOfAnArtifact(final String additionalParam,final String lastResult) {
    this.driverCustom.switchToDefaultContent();
    if (lastResult.contains(additionalParam.trim())) {
    return new TestAcceptanceMessage(true,"Verified: Created Artifact displayed under '"+additionalParam+"' folder.");
  }
  return new TestAcceptanceMessage(false,"Verified: Created Artifact not displayed under '"+additionalParam+"' folder.");
  }
  
  /**
   * <p>Verify inserted artifact name in Artifacts page.
   * <br>This method called after {@link RMArtifactsPage#insertArtifactNameInArtifactsPage(String)}.
   * 
   * @param artifactName name of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyInsertArtifactNameInArtifactsPage(final String artifactName,final String lastResult) {
    List<WebElement> results =
        this.driverCustom.getWebElements("//a[@class='jazz-ui-ResourceLink']");
    for (WebElement result : results) {
      if (result.getText().equalsIgnoreCase(artifactName)) {
      return new TestAcceptanceMessage(true, " Verified: New Artifact is created.\n Insterted artifact name - "+artifactName);
     }
    }
    return new TestAcceptanceMessage(false, "New Artifact is not created.");
  }
  
  /**
   * <p>Verify inserted artifact name in Artifacts page.
   * <br>This method called after {@link RMArtifactsPage#countNumberOfArtifactDisplayed()}.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCountNumberOfArtifactDisplayed(final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, String.format("Counted %s artifacts displayed", lastResult));
    }
    return new TestAcceptanceMessage(false, "Error when counting artifacts");
  }
  
  /**
   * <p>Verify inserted artifact name in Artifacts page.
   * <br>This method called after {@link RMArtifactsPage#getCurrentDisplayedArtifactsNumber()}.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetCurrentDisplayedArtifactsNumber(final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, String.format("Verified %s artifacts displayed in a page", lastResult));
    }
    return new TestAcceptanceMessage(false, "Error when counting artifacts");
  }
  
  /**
   * <p>Verify inserted artifact name in Artifacts page.
   * <br>This method called after {@link RMArtifactsPage#getAllArtifactsNumber()}.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetAllArtifactsNumber(final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, String.format("Verified total %s artifacts counted", lastResult));
    }
    return new TestAcceptanceMessage(false, "Error when counting artifacts");
  }
  
  
 
   
  /**
   * @param additionalParam
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifySelectRadioButtonWhatToImport(final String selectWhatToImport,final String lastResult) {
    Dialog dlgImport =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs).getFirstElement();
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,"Verified: '"+ selectWhatToImport+"' radio button selected.");
    }
    return new TestAcceptanceMessage(false, "Verified: '"+ selectWhatToImport+"' radio button not selected.");
  }
  
  /**
   * @param additionalParams 
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifySelectTheFileToImport(final Map<String, String> additionalParams,final String lastResult) {
   
    String fileName=this.driverCustom.getText("//div[@class='messageSummary']");
    if (fileName.contains(additionalParams.get(RMConstants.FILETYPE))) {
      return new TestAcceptanceMessage(true,"Verified:  Package file '"+ additionalParams.get(RMConstants.FILETYPE)+" has been uploaded.");
    }
    else if (lastResult.contains(additionalParams.get(RMConstants.FILETYPE))) {
      return new TestAcceptanceMessage(true,"Verified:  Package file '"+ additionalParams.get(RMConstants.FILETYPE)+" has been uploaded.");
    }
    return new TestAcceptanceMessage(false, "Verified: Package file '"+ additionalParams.get(RMConstants.FILETYPE)+" has not uploaded.");
  }
  
  
  /**
   * @param additionalParam
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyGetValidationMessage(final String additionalParam,final String lastResult) {
   
    if(this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_LINK_XPATH, Duration.ofSeconds(60), RMConstants.SHOWREPORT)) {
      String ShowReport = this.driverCustom.getText(RMConstants.RMARTIFACTSPAGE_REQIFSUCCESSMSG_XPATH);
      LOGGER.LOG.info("Verified : validation message is : '" + ShowReport + "'.");
      return new TestAcceptanceMessage(true,"Verified: Actual validation message is : '"+ShowReport+"'.\nExpected validation message is :'"+additionalParam+"'");
    }
     
    else if (lastResult.equalsIgnoreCase(additionalParam)) {
      return new TestAcceptanceMessage(true,"Verified: Actual validation message is : '"+lastResult+"'.\nExpected validation message is :'"+additionalParam+"'");
    }
    return new TestAcceptanceMessage(false,"Verified: Actual validation message is : '"+lastResult+"'.\nExpected validation message is :'"+additionalParam+"'");
    }
  
  
  /**
   * @param additionalParams
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyBrowseImportFolder(final Map<String, String> additionalParams,final String lastResult) {
    Dialog dlgSelectFolder = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a folder"), this.timeInSecs).getFirstElement();
    
    if(!lastResult.isEmpty()) {  
      return new TestAcceptanceMessage(true,"Verified: '"+additionalParams.get("IMPORTED_FOLDER") +"' folder selected in 'Select a folder' dialogue");
    }
  
    return new TestAcceptanceMessage(false,"Verified: '"+additionalParams.get("IMPORTED_FOLDER") +"' folder not selected in 'Select a folder' dialogue");
  }
  
  
  /**
   * @param additionalParams
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifySelectCheckBox(final Map<String, String> additionalParams,final String lastResult) {
    Dialog dlgImport =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Import"), this.timeInSecs).getFirstElement();
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,"Verified: Check Box selected successfully and last result is : "+lastResult);
    }
    return new TestAcceptanceMessage(false, "Verified: Check Box not selected successfullyand last result is : "+lastResult);
  }
  
  /**
   * @param additionalParams
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyBrowseImportModule(final Map<String, String> additionalParams,final String lastResult) {
    Dialog dlgSelectModule = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select a module"), this.timeInSecs).getFirstElement();
   
    if(!lastResult.isEmpty()) {  
      return new TestAcceptanceMessage(true,"Verified: '"+additionalParams.get("MODULE_ID") +"' folder selected in 'Select a folder' dialogue");
    }
  
    return new TestAcceptanceMessage(false,"Verified: '"+additionalParams.get("MODULE_ID") +"' folder not selected in 'Select a folder' dialogue");
  }
  
  
  
  /**
   * @param additionalParam
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyGetArtifactContent(final String artifactID,final String additionalParam,final String lastResult) {
    
    
    if (lastResult.equalsIgnoreCase(additionalParam)) {
      return new TestAcceptanceMessage(true,"Verified: Actual Artifact content of artifact id '"+artifactID+"' is '"+lastResult+"'. \nExpected Artifact content of artifact id '"+artifactID+"'"+" is '"+additionalParam+"'");
    }
    return new TestAcceptanceMessage(false,"Verified: Actual Artifact content of artifact id  '"+artifactID+"' is '"+lastResult+"'. \nExpected Artifact content of artifact id '"+artifactID+"'"+" is '"+additionalParam+"'");
    }
  
  /**
   * <p>
   * This method is called after executing ${@link RMArtifactsPage#compareConfigurations()}
   * <p>
   * @param lastResult : last result of method
   * @return verification message
   */
  public TestAcceptanceMessage verifyCompareConfigurations(final String lastResult) {
    if (lastResult.equals("true")) {
      return new TestAcceptanceMessage(true, "Verified: Compared Streams/configurations under same component");
    }
    return new TestAcceptanceMessage(false, "Verified: Compared Streams/configurations does not working.");
  }
  
  /**
   * This method is called after executing ${@link RMArtifactsPage#clickOnCloneFromAComponentBtn(String) }
   * @param forderName : foler name
   * @param lastResult : last result of method
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnCloneFromAComponentBtn(final String forderName, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(3));
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_CLONE_FROM_A_COMPONENT_OPTION, this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verified - PASSED: The button Clone From a Component... is enabled successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified - FAILED: The button Clone From a Component... is NOT enabled to select.");
  }
}