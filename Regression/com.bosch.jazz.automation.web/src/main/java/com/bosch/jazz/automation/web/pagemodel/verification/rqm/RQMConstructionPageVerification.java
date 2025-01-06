/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.rqm;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstructionPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.text.LabelFinder;

import finder.text.label.JazzLabelFinder;

/**
 * @author RUN2HC
 */
public class RQMConstructionPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMConstructionPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new LabelFinder(), new JazzLabelFinder());
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#searchRqmArtifactsInFilterTextBox(String)}.
   *
   * @param artifactName name or id of artifact
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySearchRqmArtifactsInFilterTextBox(final String artifactName,
      final String lastResult) {
    return verifySearchRqmArtifactsInFilterTextBox(artifactName, "TRUE", lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#searchRqmArtifactsInFilterTextBox(String)}.
   *
   * @param artifactName name of the artifact.
   * @param additionalParam to be pass in the method.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySearchRqmArtifactsInFilterTextBox(final String artifactName,
      final String additionalParam, final String lastResult) {
    boolean result;
    if (artifactName.matches("-?\\d+(\\.\\d+)?")) {
      result = this.driverCustom.isElementVisible(RQMConstants.RQMCONSTRUCTIONPAGE_ARTIFACTROW_ID_XPATH,
          this.timeInSecs, artifactName);
    }
    else {
      result = this.driverCustom.isElementVisible(RQMConstants.RQMCONSTRUCTIONPAGE_ARTIFACTROW_XPATH, this.timeInSecs,
          artifactName);
    }
    if (result) {
      return new TestAcceptanceMessage(result,
          "Verify displayed artifacts after filter by artifact name or id.\nActual result is '" + artifactName +
              "'found after filter.\nExpected is '" + artifactName + "'");
    }
    if (!Boolean.parseBoolean(additionalParam)) {
      List<WebElement> lstElement = this.driverCustom.getWebElements(RQMConstants.TABLE_MESSAGE_XPATH);
      for (WebElement element : lstElement) {
        if (element.getText().equals("No items match the current filter criteria.")) {
          return new TestAcceptanceMessage(true, "Verify displayed artifacts after filter by artifact name or id." +
              "\nActual result is 'No items match the current filter criteria.' message is displayed after filter.\n" +
              "\nExpected is '" + artifactName + "'");
        }
      }
    }
    return new TestAcceptanceMessage(false, "Verify displayed artifacts after filter by artifact name or id." +
        "\nActual result is 'No items match the current filter criteria'.\nExpected is '" + artifactName + "'");
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#filterRqmArtifactByInlineFilterColoumnName(Map)}
   * 
   * @param additionalParams - "COLUMN_NAME","SEARCH_VALUE".
   * @param additionalParam to be pass in the method.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyFilterRqmArtifactByInlineFilterColoumnName
  (final Map<String, String> additionalParams, final String additionalParam, final String lastResult) {
    String searchValue = additionalParams.get("SEARCH_VALUE");
    return verifySearchRqmArtifactsInFilterTextBox(searchValue, additionalParam, lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#selectTestData(String)}.
   *
   * @param artifactName name or id of artifact
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectTestData(final String artifactName, final String lastResult) {
    waitForSecs(Duration.ofSeconds(8));
    try {
      WebElement artifactNames = this.driverCustom
          .getFirstVisibleWebElement("//div[@dojoattachpoint='viewHeaderLeft']//span[@dojoattachpoint='viewID']", null);
      if (artifactNames.getText().contains(artifactName)) {
        return new TestAcceptanceMessage(true, "Verified displayed page after click on artifact by name or ID." +
            "\nActual result is the artifact details page with ID is '" + artifactName +
            "' is displayed.\nExpected is the artifact details page with ID is '" + artifactName + "' is displayed");

      }
    }
    catch (Exception e) {
      try {
        this.engine
            .findElementWithDuration(Criteria.isTextField().isMultiLine().withText(artifactName), this.timeInSecs)
            .getFirstElement();
        return new TestAcceptanceMessage(true, "Verified displayed page after click on artifact by name or Id." +
            "\nActual result is the artifact details page with name is '" + artifactName +
            "' is displayed.\nExpected is the artifact details page with name is '" + artifactName + "' is displayed");
      }
      catch (Exception e2) {
        return new TestAcceptanceMessage(false,
            "Verified displayed page after click on  artifact by name or id." +
                "\nActual result is the artifact details page with name or id '" + artifactName +
                "' is not displayed.\nExpected Result is the artifact details page with name/id '" + artifactName +
                RQMConstants.IS_DISPLAYED);
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Title of the artifact.\nActual result is \"" + artifactName +
        "\"" + "\nExpected result is not matched \"" + artifactName + "\"");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#clickOnTestScriptId(String)}.
   *
   * @param artifactId name or id of artifact
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTestScriptId(final String artifactId, final String lastResult) {
    WebElement artifactNames = this.driverCustom
        .getFirstVisibleWebElement("//div[@dojoattachpoint='viewHeaderLeft']//span[@dojoattachpoint='viewID']", null);
    if (artifactNames.getText().contains(artifactId)) {
      return new TestAcceptanceMessage(true, "Verified displayed page after click on artifact by name or id." +
          " \nActual result is the artifact details page with ID is '" + artifactId +
          "' displayed.\nExpected is the artifact details page with ID is '" + artifactId + RQMConstants.IS_DISPLAYED);

    }
    return new TestAcceptanceMessage(false,
        "Verified displayed page after click on artifact by name or id." +
            "\nActual result is the artifact details page with name/id is '" + artifactId +
            "' is not displayed.\nExpected is the artifact details page with name/id is '" + artifactId +
            "' is displayed.");

  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#createNewStep(String, String)}.
   *
   * @param Menu is Insert New Step Before/Insert New Step After
   * @param subMenu menu.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateNewStep(final String Menu, final String subMenu, final String lastResult) {
    switch (Menu) {
      case "Insert New Step Before":
        if (this.driverCustom.isElementVisible(
            RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH, Duration.ofSeconds(5) ,"Step  1 Description") &&
            this.driverCustom.isElementVisible(
                RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH, Duration.ofSeconds(5),
                "Step  1 Expected Results")) {
          return new TestAcceptanceMessage(true, "Verified displayed test script step after clicking on '" + subMenu +
              "' submenu under '" + Menu + "' Menu." +
              "\nActual result is new test script step is displayed as step 1.\nExpected is new test script step is displayed as step 1.");
        }
        return new TestAcceptanceMessage(false, "Verified displayed test script step after clicking on '" + subMenu +
            "' submenu under '" + Menu + "' Menu." +
            "\nActual result is new test script step is not displayed as step 1.\nExpected is is new test script step is displayed as step 1.");
      case "Insert New Step After":
        if (this.driverCustom.isElementVisible(
            RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH, Duration.ofSeconds(5), "Step  2 Description") &&
            this.driverCustom.isElementVisible(
                RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH, Duration.ofSeconds(5),
                "Step  2 Expected Results")) {
          return new TestAcceptanceMessage(true, "Verified displayed test script step after click on '" + subMenu +
              "' subMenu under '" + Menu + "' menu." +
              "\nActual result is new test script step is displayed as step 2. \nExpected is is new test script step is displayed as step 2");
        }
        return new TestAcceptanceMessage(false, "Verified displayed test script step after click on '" + subMenu +
            "' subMenu under '" + Menu + "' menu." +
            "\nActual result is new test script step is not displayed as step 2.\nExpected is is new test script step is displayed as step 2");
      default:
        return new TestAcceptanceMessage(false, "The " + Menu + " does not match.");
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#addStepValuesInTestScript(Map)}.
   *
   * @param additionalParams data
   * @param lastResult result
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyAddStepValuesInTestScript(final Map<String, String> additionalParams,
      final String lastResult) {
    String description =
        this.driverCustom.getText(RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH,
            additionalParams.get(RQMConstants.DESCRIPTION_VALUE));
    this.driverCustom.switchToFrame("//iframe[contains(@title,'Editor, editor1')]");
    String expectedResult = this.driverCustom.getText("//body[contains(@class,'script-step-content')]");
    if (description.contains(additionalParams.get(RQMConstants.DESCRIPTION)) &&
        expectedResult.contains(additionalParams.get(RQMConstants.EXPECTED_RESULT))) {
      this.driverCustom.getWebDriver().switchTo().defaultContent();
      return new TestAcceptanceMessage(true, "Verified value of added step in test script after adding." +
          "\nActual Result is added Description step value is '" + description +
          RQMConstants.ADDED_EXPECTED_RESULT_STEP_VALUE_IS + expectedResult +
          "'\nExpected is added Description step value is '" + additionalParams.get(RQMConstants.DESCRIPTION) +
          RQMConstants.ADDED_EXPECTED_RESULT_STEP_VALUE_IS + additionalParams.get(RQMConstants.EXPECTED_RESULT) + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified value of added step in test script after adding." +
            "\nActual result is added Description step has value is '" + description +
            "' and added Expected Result step value is '" + expectedResult +
            "'\nExpected is added Description has value is '" + additionalParams.get(RQMConstants.DESCRIPTION) +
            "' and added Expected Result step value is '" + additionalParams.get(RQMConstants.EXPECTED_RESULT) + "'");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#getContentOfStep(String, String)}.
   *
   * @param stepNo step
   * @param stepValue value
   * @param additionalParam data
   * @param match value
   * @param lastResult result
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetContentOfAddedStep(final String stepNo, final String stepValue,
      final String additionalParam, final String match, final String lastResult) {
    if (!lastResult.isEmpty()) {
      if (Boolean.valueOf(match)) {
        return new TestAcceptanceMessage(lastResult.contains(additionalParam),
            "Verified content of the step.\n\nActual result is '" + lastResult + "'\n\nExpected is '" +
                additionalParam + "'.");
      }
      return new TestAcceptanceMessage(!lastResult.contains(additionalParam),
          "Verified content of the step after delete.i.e Actual and expected should not match.\n\nActual result is '" +
              lastResult + "'\n\nExpected is '" + additionalParam + "'.");
    }
    return new TestAcceptanceMessage(lastResult.contains(additionalParam),
        "Verified content of the step.\n\nActual result is empty.\n\nExpected is '" + additionalParam + "'");

  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#deleteStep(String)}.
   *
   * @param stepNo to add decription or expected result.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteStep(final String stepNo, final String lastResult) {
    return new TestAcceptanceMessage(true,
        "Verified step of test script after deleting.\nActual Result is deleted step is not displayed.\nExpected is deleted step is not displayed.");

  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#deleteStep(String)}.
   *
   * @param stepNo no of the step.
   * @param description of the manual step.
   * @param expRes expected result of the manual step.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteStep(final String stepNo, final String description, final String expRes,
      final String lastResult) {
    return new TestAcceptanceMessage(true,
        "Verified: Newly added step is deleted.Step no - " + stepNo + ".\nActual Result \"" + description +
            "\" and \"" + expRes + "\" is deleted.\nExpected Result \"" + description + "\" and \"" + expRes +
            "\" should be deleted");

  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#enterTestArtifactName(Map)}.
   *
   * @param additionalParams data
   * @param lastResult get the last result of {@link RQMConstructionPage#enterTestArtifactName(String)}}
   * @return validation message
   */
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifyEnterTestArtifactName(final Map<String, String> additionalParams,
      final String lastResult) {

    String getTestArtifact = this.driverCustom.getPresenceOfWebElement(
        "//textarea[contains(@class,'view-title') and @aria-disabled='false'] | //textarea[contains(@class,'view-title') and @aria-required='true'] ")
        .getAttribute(RQMConstants.VALUE).trim();
    if (!lastResult.isEmpty() && getTestArtifact.equals(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified test artifact name is '" + getTestArtifact +
          "' set.\nActual result value set is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified test artifact name is '" + getTestArtifact +
        "'\nActual result value is '" + lastResult + "' not set.");
  }


  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)}.
   *
   * @param additionalParams the description of test artifact.
   * @param lastResult get the last result of {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)}}
   * @return return true if description is displayed on description field.
   */
  public TestAcceptanceMessage verifyFillDescriptionInSummaryOuterNodeSection(
      final Map<String, String> additionalParams, final String lastResult) {
    String getActualDescription = "";
    String expectedDescription = additionalParams.get("testArtifactDescriptionValue");
    getActualDescription =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEDESCRIPTION_TEXTBOX_XPATH)
            .getAttribute(RQMConstants.VALUE).trim();
    if (!getActualDescription.isEmpty() && getActualDescription.equals(expectedDescription)) {
      return new TestAcceptanceMessage(true,
          "Verified actual description '" + getActualDescription +
              "' displayed in Description field.\nExpected description  is -'" + expectedDescription +
              "' added to Description field.");
    }
    return new TestAcceptanceMessage(false,
        "Verified actual description '" + getActualDescription +
            "' displayed in Description field.\nExpected description  is -'" + expectedDescription +
            "' not added to Description field.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#getRqmArtifactID()}.
   *
   * @param lastResult get the last result of {@link RQMConstructionPage#getRqmArtifactID}}
   * @return validation message
   */
  public TestAcceptanceMessage verifyGetRqmArtifactID(final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified test artifact's ID return is '" + lastResult + "' and valid ID value.");
    }
    return new TestAcceptanceMessage(false, "Verified the string return is '" + lastResult + "' and invalid ID value.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#setIdOrNameToAddRQMArtifact(String, String)}.
   *
   * @param dialog the title of dialog
   * @param value the value input in text field (id or name)
   * @param lastResult get the last result of {@link RQMConstructionPage#setIdOrNameToAddRQMArtifact(String)}
   * @return validation message
   */
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifySetIdOrNameToAddRQMArtifact(final String dialog, final String value,
      final String lastResult) {
    String regex = "(^*\\d){4,}"; // matching the beginning of string 4+ digits
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(value);
    String getIDString = "";
    String getNameString = "";
    if (matcher.matches()) {
      getIDString = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH)
          .getAttribute(RQMConstants.VALUE).trim();
      getNameString = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_ADDREQUIREMENTNAME_TEXTBOX_XPATH)
          .getAttribute(RQMConstants.VALUE).trim();
      if (getIDString.equals(value) && getNameString.isEmpty()) {
        return new TestAcceptanceMessage(true, "Verified ID of test artifact is '" + value +
            "' and name of test artifact is empty set in '" + dialog + RQMConstants.DIALOG_SUCCESSFULLY);
      }
      return new TestAcceptanceMessage(false,
          "Verified ID of test artifact is '" + value + "' and name of test artifact is empty not set in '" + dialog +
              RQMConstants.DIALOG_SUCCESSFULLY + "Actual ID of test artifact is '" + getIDString +
              "' and actual name of test artifact is '" + getNameString + "' put in '" + dialog + "dialog.");
    }
    getIDString = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH)
        .getAttribute("value").trim();
    getNameString = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_ADDREQUIREMENTNAME_TEXTBOX_XPATH)
        .getAttribute("value").trim();
    if (getIDString.isEmpty() && getNameString.equals(value)) {
      return new TestAcceptanceMessage(true, "Verified ID of test artifact is empty and name of test artifact is '" +
          value + "' set in '" + dialog + "' dialog successfully.\n");
    }
    return new TestAcceptanceMessage(false,
        "Verified ID of test artifact is empty and name of test artifact is '" + value + "' not set in '" + dialog +
            "' dialog successfully.\n" + "Actual ID of test artifact is '" + getIDString +
            "' and actual name of test artifact is '" + getNameString + "' put in '" + dialog + "dialog.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#selectTestScript(String)}.
   *
   * @param testScript name or id of artifact
   * @param lastResult get the last result of {@link RQMConstructionPage#selectTestScript(String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifySelectTestScript(final String testScript, final String lastResult) {
    Boolean isSelected = false;
    try {
      Row rowTestScript1 = this.engine.findElement(Criteria.isRow().withText(testScript)).getFirstElement();
      Cell cllCheckbox1 =
          this.engine.findElement(Criteria.isCell().inContainer(rowTestScript1).withIndex(1)).getFirstElement();
      Checkbox cbxTestScript1 =
          this.engine.findElement(Criteria.isCheckbox().inContainer(cllCheckbox1)).getFirstElement();
      isSelected = Boolean.valueOf(cbxTestScript1.getWebElement().getAttribute("aria-checked").trim());
      if (isSelected) {
        return new TestAcceptanceMessage(true, "Verified  the '" + testScript +
            "' is selected successfully. \nActual result is '" + testScript + "'  row highlighed.");
      }
    }
    catch (Exception e) {
      waitForSecs(Duration.ofSeconds(2));
    }
    return new TestAcceptanceMessage(false, "Verified  test script '" + testScript +
        "' is not selected./nActual result is test script row not highlighted.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#filterAndDeleteRqmArtifact(String, String, String)}.
   *
   * @param artifactName name or id of artifact
   * @param drdOption delete option should be there
   * @param dialogName the title of Delete test artifact dialog
   * @param lastResult get the last result of {@link RQMConstructionPage#filterAndDeleteRqmArtifact(String)}
   * @return validation message
   */
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifyFilterAndDeleteRqmArtifact(final String artifactName, final String drdOption,
      final String dialogName, final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    try {
      try {
        this.driverCustom.executeJavaScript(
            "element = document.querySelector('span.filter-area>input'); element.value='" + artifactName + "';");
      }
      catch (Exception e) {
        Text txtSearch = this.engine.findElementWithDuration(
            Criteria.isTextField().withPlaceHolder(RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER),
            this.timeInSecs).getFirstElement();
        txtSearch.setText(artifactName);
      }
      Button btnFilter =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.FILTERS), this.timeInSecs)
              .getFirstElement();
      btnFilter.click();

      // Verify no result found.
      try {
        String getStringResult = this.driverCustom.getPresenceOfWebElement(
            "//div[@class='table-message' and @style='display: block;' and text()='DYNAMIC_VAlUE']",
            RQMConstants.TABLE_MESSAGE_NO_RESULT_IN_DIALOG_1).getText().trim();
        return new TestAcceptanceMessage(true,
            RQMConstants.VERIFIED_THE_TEST_ARTIFACT + artifactName + "' is removed successfully.\n" +
                "Actual result is that no test artifact found and the return text is '" + getStringResult + "' .");
      }
      catch (Exception e1) {
        waitForSecs(Duration.ofSeconds(2));
      }
      return new TestAcceptanceMessage(false, "Verified the test artifact '" + artifactName +
          "' is not removed successfully.\n" + "The test artifact row is still displayed on the screen.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verified the test artifact '" + artifactName + "' is not removed.\n" +
          "Actual result is that the script cannot enter '" + artifactName + "' into search field.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#createNewTestScriptInDialog(Map)}.
   *
   * @param additionalParams the content of new test script
   * @param lastResult get the last result of {@link RQMConstructionPage#createNewTestScriptInDialog(Map)}
   * @return validation message
   */
  public TestAcceptanceMessage verifyCreateNewTestScriptInDialog(final Map<String, String> additionalParams,
      final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    try {
      this.engine.findElement(Criteria.isRow().containsText(additionalParams.get(RQMConstants.VAR_NAME)))
          .getFirstElement();
      return new TestAcceptanceMessage(true,
          "Verified the test script '" + additionalParams.get(RQMConstants.VAR_NAME) + "' is created successfully.\n" +
              "Actual result is the test script '" + additionalParams.get("varName") + "' is showing on the screen");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified the test script '" + additionalParams.get("varName") + "' is not created successfully.\n" +
              "So Exception is thrown out when finding the test script row.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#createNewTestScriptWithSameNameInDialog(Map)}.
   *
   * @param additionalParams the content of new test script
   * @param additionalPram data.
   * @param lastResult get the last result of
   *          {@link RQMConstructionPage#createNewTestScriptWithSameNameInDialog(String)}
   * @return validation message
   */
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifyCreateNewTestScriptWithSameNameInDialog(final Map<String, String> additionalParams,
      final String additionalParam, final String lastResult) {
    if (lastResult.contains(additionalParam)) {
      return new TestAcceptanceMessage(true,
          "Verified the test script '" + additionalParams.get(RQMConstants.VAR_NAME) + "' is NOT created.\n" +
              "Actual result is error message '" + lastResult + "' is showing on the screen");
    }
    return new TestAcceptanceMessage(false,
        "Verified the test script '" + additionalParams.get(RQMConstants.VAR_NAME) + "' is created successfully.\n" +
            "Actual result is error message '" + lastResult + "' is NOT displayed on the screen");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#addStringWithDateTime(String)}.
   *
   * @param lastResult get the last result of {@link RQMConstructionPage#addStringWithDateTime}}
   * @return validation message
   */
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifyAddStringWithDateTime(final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true,
          "Verified the String is added successully.\n Actual result is the string '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified the String is not added successully.\n Actual result is the string '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#isErrorMessageDisplay()}.
   *
   * @param additionalPram data.
   * @param lastResult get the last result of {@link RQMConstructionPage#isErrorMessageDisplay}} Verifies the action of
   *          {@link RQMConstructionPage#isNoFoundResultAfterSearchingInDialog()}.
   * @return validation message
   */
  public TestAcceptanceMessage verifyIsErrorMessageDisplay(final String additionalPram, final String lastResult) {
    String resultStatus = "FAILED";
    String errorMsg = "";
    if (lastResult.equalsIgnoreCase(additionalPram)) {
      resultStatus = "PASSED";
    }
    if (additionalPram.equalsIgnoreCase("false")) {
      errorMsg = "NOT ";
    }
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalPram),
        "Verified: " + resultStatus + " - Error message is " + errorMsg + "displayed \nExpected value: " +
            additionalPram + "\nActual value: " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#isSavedSuccessfully()}.
   *
   * @param additionalPram data.
   * @param lastResult get the last result of {@link RQMConstructionPage#isSavedSuccessfully}}
   * @return validation message
   */
  public TestAcceptanceMessage verifyIsSavedSuccessfully(final String additionalPram, final String lastResult) {
    String resultStatus = "FAILED";
    String errorMsg = "";
    if (lastResult.equalsIgnoreCase(additionalPram)) {
      resultStatus = "PASSED";
    }
    if (additionalPram.equalsIgnoreCase("false")) {
      errorMsg = "NOT ";
    }
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalPram),
        "Verified: " + resultStatus + " - Successful message is " + errorMsg + "displayed \nExpected value: " +
            additionalPram + "\nActual value: " + lastResult);
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#isAssociateArtifactDisplayed(String)}.
   *
   * @param artifactName name.
   * @param additionalPram data.
   * @param lastResult get the last result of {@link RQMConstructionPage#isAssociateArtifactDisplayed}}
   * @return validation message
   */
  public TestAcceptanceMessage verifyIsAssociateArtifactDisplayed(final String artifactName,
      final String additionalPram, final String lastResult) {
    return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalPram),
        "Verify that the '" + artifactName + " is displayed.\nActual is that the expected value - '" + additionalPram +
            "' matching the actual result - '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#selectRqmArtifact(String)}.
   *
   * @param artifactName name of the artifact.
   * @param lastResult get the last result of {@link RQMConstructionPage#selectRqmArtifact}}
   * @return validation message
   */
  public TestAcceptanceMessage verifySelectRqmArtifact(final String artifactName, final String lastResult) {
    WebElement artifactElement = null;
    try {
      if (artifactName.chars().allMatch(Character::isDigit)) {
        artifactElement = this.driverCustom.getPresenceOfWebElement(
            "//span[contains(@class,'view-title') and contains(., 'DYNAMIC_VAlUE')]", artifactName);
      }
      else {
        artifactElement = this.driverCustom.getPresenceOfWebElement(
            "//span[@role='heading' and text()='DYNAMIC_VAlUE'] | //span[@role='heading' and text()='DYNAMIC_VAlUE" +
                "..." + "']",
            artifactName);
      }
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified displayed page after click on artifact by name/id." +
              "\nActual result is the artifact details page with name/id is'" + artifactName +
              "' is not displayed.\nExpected is the artifact details page with name/id is '" + artifactName +
              "' is displayed.\n" + e.getMessage());
    }
    if (artifactElement != null) {
      return new TestAcceptanceMessage(true, "Verified displayed page after click on artifact by name/id" +
          " \nActual result is the artifact details page with Name/id is '" + artifactName +
          "' displayed.\nExpected is the artifact details page with Name/id is '" + artifactName + "' is displayed.");
    }
    return new TestAcceptanceMessage(false,
        "Verified displayed page after click on artifact by name." +
            "\nActual result is the artifact details page with Name is '" + artifactName +
            "' is not displayed.\nExpected is the artifact details page with Name is '" + artifactName +
            "' is displayed.\n");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#selectOwner(Map)}.
   *
   * @param additionalParams data.
   * @param lastResult get the last result of {@link RQMConstructionPage#selectOwner}}
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectOwner(final Map<String, String> additionalParams, final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    String actualValue = "";
    try {
      Dropdown drdOwner = this.engine.findFirstElement(Criteria.isDropdown().withLabel(RQMConstants.OWNER_LABEL));
      actualValue = drdOwner.getDefaultValue();
    }
    catch (Exception e) {
      waitForSecs(Duration.ofSeconds(2));
    }
    if (additionalParams.get(RQMConstants.OWNER_VALUE).contains(actualValue)) {
      return new TestAcceptanceMessage(true, "Actual Owner drodown value is -'" + actualValue +
          "' set successfully. \nExpected Owner dropdown value is -'" + additionalParams.get("ownerValue") + "'.");
    }
    return new TestAcceptanceMessage(false, "Actual value of Owner dropdown is - '" + actualValue +
        "' not set successfully. \nExpected Owner dropdown value is -'" + additionalParams.get("ownerValue") + "'.");
  }


  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#associateTestScriptWithTestCase(String)}.
   *
   * @param testCaseName the name of test case.
   * @param lastResult get the last result of {@link RQMConstructionPage#associateTestScriptWithTestCase(String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifyAssociateTestScriptWithTestCase(final String testCaseName,
      final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    // click on related site bar
    WebElement siteBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
    if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", Duration.ofSeconds(5))) {
      siteBar.click();
    }
    try {
      WebElement parentElement =
          this.driverCustom.getPresenceOfWebElement(RQMConstants.TITLE_CONTAINER_XPATH, "Parent Test Cases");
      if (parentElement.getAttribute("aria-pressed").equalsIgnoreCase("false")) {
        parentElement.click();
        this.engine.findElement(Criteria.isLink().withText(testCaseName)).getFirstElement();
      }
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified the test script is NOT associated with the test case '" + testCaseName + "' successfully.\n" +
              "Actual result is that Exception throwing out when finding the case's link.\n" + e.getMessage());
    }
    return new TestAcceptanceMessage(true,
        "Verified the test script is associated with the test case '" + testCaseName + "' successfully.\n" +
            "Actual result is the test case '" + testCaseName + "' is showing on the Parent Test Cases section.");
  }

  /**
   * <p>
   * Verify Test Case Execution Record is created.
   * <p>
   * This method called after {@link RQMConstructionPage#generateNewTestCaseExecutionRecord(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGenerateNewTestCaseExecutionRecord(final Map<String, String> additionalParams,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10), "Completed")) {
      return new TestAcceptanceMessage(true,
          "Verified: Generated new test case execution record for the test artifact.\nActual Result execution record created using \"" +
              additionalParams.get("searchTestEnvName") + "\" test environment,\"" +
              additionalParams.get(RQMConstants.TESTPLAN_NAME) +
              "\" test plan and Iteration as 'Unassigned' .\nExpected Result execution record should be created using \"" +
              additionalParams.get("searchTestEnvName") + "\" test environment,\"" +
              additionalParams.get(RQMConstants.TESTPLAN_NAME) + "\" test plan" + " and Iteration as 'Unassigned'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: New Test case execution record not created for the test artifact.");
  }

  /**
   * <p>
   * Verify Test Suite Execution Record is created.
   * <p>
   * This method called after {@link RQMConstructionPage#generateNewTestSuiteExecutionRecord(String)}.
   *
   * @param testPlanName name of the Test Plan.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGenerateNewTestSuiteExecutionRecord(final String testPlanName,
      final String lastResult) {
    if (this.driverCustom.isElementVisible("//*[text()='Generate Test Suite Execution Records']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified: Generated new test suite execution record for the test artifact.\nActual Result execution record created for \"" +
              testPlanName + "\".\nExpected Result execution record should be created for \"" + testPlanName + "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified: New Test Suite Execution Record not created for the test artifact.");
  }

  /**
   * <p>
   * Verify Project Area is selected from Artifact Container wizard.
   * <p>
   * This method called after {@link RQMConstructionPage#chooseProjectAreaFromArtifactContainer(String)}.
   *
   * @param RMProjectArea name of the RM project area.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseProjectAreaFromArtifactContainer(final String RMProjectArea,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,  Duration.ofSeconds(30),
        "Requirement Selection")) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected the appropriate project area from 'Artifact Container selection' wizard.\nSelected project area - " +
              RMProjectArea);
    }
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,  Duration.ofSeconds(30),
        "Create new Requirement")) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected the appropriate project area from 'Artifact Container selection' wizard for creating new requirement.\nSelected project area - " +
              RMProjectArea);
    }
    return new TestAcceptanceMessage(false,
        "Proper project area is not selected from 'Artifact Container selection' wizard.");
  }

  /**
   * <p>
   * Verify Requirement is added in the test case.
   * <p>
   * This method called after {@link RQMConstructionPage#addRequirementInTestCase(String)}.
   *
   * @param artifactName name of the requirement to be added.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyAddRequirementInTestCase(final String artifactName, final String lastResult) {
    if( this.driverCustom.isElementVisible("//table[@summary='This is Requirement Links table']//tr[contains(@name,'_table_row_for_external')]//a/div", timeInSecs)) {
      List<String> allRequirementLinks = this.driverCustom
          .getWebElements(
              "//table[@summary='This is Requirement Links table']//tr[contains(@name,'_table_row_for_external')]//a/div")
          .stream().map(x -> x.getText()).collect(Collectors.toList());
      if ((allRequirementLinks != null) &&
          allRequirementLinks.stream().anyMatch(x -> (x != null) && x.contains(artifactName))) {
        return new TestAcceptanceMessage(true,
            "Verified: Requirement is added in the test case.\nExpected result '" + artifactName +
                "' is added in the test case.\nActual Result '" + artifactName + "' should be added in the test case.");
        }
      }
     else {
       String reqLink ="//*[contains(text(),'DYNAMIC_VAlUE')]";
       if (this.driverCustom.isElementVisible(reqLink,timeInSecs, artifactName)){
         return new TestAcceptanceMessage(true,
             "Verified: Requirement is added in the test case.\nExpected result '" + artifactName +
                 "' is added in the test case.\nActual Result '" + artifactName + "' should be added in the test case.");
       }   
     } 
     return new TestAcceptanceMessage(false, "Verified: Requirement is not added in the test case.");
  }

  /**
   * <p>
   * Verify requirement is clicked successfully.
   * <p>
   * This method called after {@link RQMConstructionPage#clickOnRequirementLink(String)}.
   *
   * @author VDY1HC
   * @param nameOrID name or ID of artifact to be clicked.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnRequirementLink(final String nameOrID, final String lastResult) {
    String[] arrNameOfID = nameOrID.split(":");
    List<WebElement> resourceInfo = null;
    for (String splited_Name_ID : arrNameOfID) {
      resourceInfo = this.driverCustom.getVisibleWebElements("//div[@class='resource-info']//*[contains(text(),'" + splited_Name_ID.trim() + "')]");
    }
    return new TestAcceptanceMessage(resourceInfo != null && !resourceInfo.isEmpty(), "Verified: Clicked on the requirement with text - " + nameOrID);
  }

  /**
   * <p>
   * Verify clicked on 'Manage Suspicion' option.
   * <p>
   * This method called after {@link RQMConstructionPage#manageSuspicion()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyManageSuspicion(final String lastResult) {
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPAGE_DIALOG_TITLE_XPATH,  Duration.ofSeconds(30), "Choose Suspicion Profiles")) {
      return new TestAcceptanceMessage(true, "Verified: Choose Suspicion Profile wizard is displayed.");
    }
    return new TestAcceptanceMessage(false, "Verified: Choose Suspicion Profile wizard is not displayed.");
  }

  /**
   * <p>
   * Verify Suspicion Profile is enabled for the requirement.
   * <p>
   * This method called after {@link RQMConstructionPage#chooseSuspicionProfile(String)}.
   *
   * @param profileName name of Suspicion profile.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseSuspicionProfile(final String profileName, final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//label[text()='DYNAMIC_VAlUE']/..//input[contains(@name,'suspicionProfileCheckBox')]",  Duration.ofSeconds(10), profileName)) {
      return new TestAcceptanceMessage(true, "Verified: Suspicion profile is enabled for '" + profileName + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified: Suspicion profile is not enabled for '" + profileName + "'.");

  }

  /**
   * <p>
   * Verify artifact is selected.
   * <p>
   * This method called after {@link RQMConstructionPage#selectTheSearchedArtifact(String)}.
   *
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectTheSearchedArtifact(final String artifactID, final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//div[contains(text(),'DYNAMIC_VAlUE')]/ancestor::td[1]/preceding-sibling::td//input[@aria-checked='true']",  Duration.ofSeconds(20),
        artifactID)) {
      return new TestAcceptanceMessage(true,
          "Verified: Searched artifact is selected.\nActual Result '" + artifactID +
              "' is selected from Requirement Links section.\nExpected Result '" + artifactID +
              "'should be selected from Requirement Links section.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Searched artifact is not selected from Requirement Links section.");
  }

  /**
   * <p>
   * Verify requirement is selected for the reconcile.
   * <p>
   * This method called after {@link RQMConstructionPage#selectTheRequirementForReconcile(String)}.
   *
   * @param artifactID id of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectTheRequirementForReconcile(final String artifactID,
      final String lastResult) {
    try {
      if (this.driverCustom.isElementVisible(
          "//a[text()='DYNAMIC_VAlUE']/../../preceding-sibling::td//input[@aria-checked='true']",  Duration.ofSeconds(20), artifactID) &&
          this.driverCustom.isElementVisible(
              "//div[@dojoattachpoint='listNode']//div[@class='actions-area']//a[@aria-disabled='false']",  Duration.ofSeconds(20))) {
        return new TestAcceptanceMessage(true, "Verified: Artifact is selected for reconcile.\nActual Result '" +
            artifactID + "' is selected for reconcile.\nExpected Result '" + artifactID +
            "'should be selected for reconcile.\n\nRequirement is selected and 3 options 'Ignore','Clear Suspicion','Mark Suspect' icon is enabled.");

      }
      if (this.driverCustom.isElementVisible(
          "//a[contains(text(),'DYNAMIC_VAlUE')]/../../preceding-sibling::td//input[@aria-checked='true']",  Duration.ofSeconds(20),
          artifactID)) {
        return new TestAcceptanceMessage(true, "Verified: Archived artifact is selected for reconcile.");
      }
    }
    catch (Exception e) {
      waitForSecs(Duration.ofSeconds(2));
    }

    return new TestAcceptanceMessage(false, "Verified: Artifact is not selected for reconcile.");
  }

  /**
   * <p>
   * Verify reconciled requirement.
   * <p>
   * This method called after {@link RQMConstructionPage#getReconcileRequirement()}.
   *
   * @param additionalParam to be passed in Excel Test Script.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetReconcileRequirement(final String additionalParam, final String lastResult) {
    if (this.driverCustom.isElementVisible("//td[@class='non-clip-cell-alignment']//a[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(20),
        additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified: Suspected requirement is updated in Reconcile Requirements.");
    }
    if (this.driverCustom.isElementVisible(
        "//td[@class='non-clip-cell-alignment']//a[contains(text(),'DYNAMIC_VAlUE')]",  Duration.ofSeconds(20), additionalParam)) {
      return new TestAcceptanceMessage(true,
          "Verified: Reconcile deleted item is displayed the deleted requirement with archived link.");
    }
    return new TestAcceptanceMessage(false, "Verified: Suspected changes not ignored in selected requirement.");
  }

  /**
   * <p>
   * Verify the suspected status of the requirement.
   * <p>
   * This method called after {@link RQMConstructionPage#getSuspectStatusOfRequirement(String, String)}.
   *
   * @param artifactIDName requirement with ID and name.
   * @param suspectStatus status of suspect contributor.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetSuspectStatusOfRequirement(final String artifactIDName,
      final String suspectStatus, final String lastResult) {
    StringBuilder sb = new StringBuilder();
    try {
    if (Boolean.valueOf(lastResult)) {
      sb.append("Verified: Suspicion status of Requirement \'" + artifactIDName + "\' is updated in 'Suspect Contributor' column.\n");
    }
    if(suspectStatus.equalsIgnoreCase("Yes")) {
      sb.append("Suspect Contributor status is updated to 'Suspect'.");
    }
    else {
    sb.append("Suspect Contributor status is updated to 'Non Suspect'.");
    }
    return new TestAcceptanceMessage(true, sb.toString());
    }
    catch(Exception e) {
      waitForSecs(Duration.ofSeconds(1));
    }
    return new TestAcceptanceMessage(false,
        "Verified: Suspicion status of Requirement \'" + artifactIDName + "\' is not updated in 'Suspect Contributor' column.");
  }

  /**
   * <p>
   * Verify the requirement is created.
   * <p>
   * This method called after {@link RQMConstructionPage#createNewRequirement(Map)}.
   *
   * @param additionalParams contains list of key value pair data.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateRequirement(final Map<String, String> additionalParams,
      final String lastResult) {
    List<String> allRequirementLinks = this.driverCustom
        .getWebElements(
            "//table[@summary='This is Requirement Links table']//tr[contains(@name,'_table_row_for_external')]//a/div")
        .stream().map(x -> x.getText()).collect(Collectors.toList());
    if ((allRequirementLinks != null) && allRequirementLinks.stream()
        .anyMatch(x -> (x != null) && x.contains(additionalParams.get("Requirement Name")))) {
      return new TestAcceptanceMessage(true, "Verified: Requirement is created using provided details.");
    }
    return new TestAcceptanceMessage(false, "Verified: Requirement is not created using provided details.");
  }

  /**
   * <p>
   * Verify all the requirements selected from 'Requirement Links' section.
   * <p>
   * This method called after {@link RQMConstructionPage#selectAllRequirements(String)}.
   *
   * @param option to select or deselect all the requirements.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectAllRequirements(final String option, final String lastResult) {
    if (this.driverCustom
        .getWebElement("//table[@summary='This is Requirement Links table']//th[@title='Select All/Clear All']//img")
        .getAttribute("class").contains("button-img checkbox-checked-icon")) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected all the requirements from 'Requirement Links' section.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: All the requirements not selected from 'Requirement Links' section.");
  }

  /**
   * <p>
   * Verify all the requirements selected from 'Requirement Reconcile' window.
   * <p>
   * This method called after {@link RQMConstructionPage#selectAllRequirementsForReconcile(String)}.
   *
   * @param option to select or deselect all the requirements for reconcile.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectAllRequirementsForReconcile(final String option, final String lastResult) {
    if (this.driverCustom
        .getWebElement("//table[@summary='This is Development Items table']//th[@title='Select All/Clear All']//img")
        .getAttribute("class").contains("button-img checkbox-checked-icon")) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected all the requirements from 'Reconcile Requirement' wizard.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: All the requirements not selected from 'Reconcile Requirement' wizard.");
  }

  /**
   * <p>
   * Verify clicking on the Cancel button after inputting same name in New Test Script dialog
   * <p>
   * This method called after {@link RQMConstructionPage#clickOnCancelButtonWhenErrorMsgDisplayed()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnCancelButtonWhenErrorMsgDisplayed(final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: 'Cancel' button is clicked successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: 'Cancel' button is not clicked successfully and last result is '" + lastResult + "'.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH,
        RQMConstants.CONSTRUCTION);

  }

  /**
   * <p>
   * Verify is link Validity Visible in Requirement Links
   * <p>
   * This method called after {@link RQMConstructionPage#isLinkValidityVisibleInRequirementLinks(String)}.
   *
   * @param id of requirement to be clicked.
   * @param additionalParam is additional Parameter set expect result
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsLinkValidityVisibleInRequirementLinks(final String id,
      final String additionalParam, final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalParam)) {
      return new TestAcceptanceMessage(true,
          "Verified the artifact ID '" + id + "' displayed in Requirement Links tab. \nExpected result: '" +
              additionalParam + "'\nActual result: '" + lastResult + "'");
    }
    return new TestAcceptanceMessage(false,
        "Verified the artifact ID '" + id + "' displayed in Requirement Links tab. \nExpected result: '" +
            additionalParam + "'\nActual result: '" + lastResult + "'");
  }

  /**
   * <p>
   * Verify requirement ID is clicked successfully.
   * <p>
   * This method called after {@link RQMConstructionPage#clickOnRequirementIDFromRequirementLinks(String)}.
   *
   * @param ID of requirement to be clicked.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  @SuppressWarnings("javadoc")
  public TestAcceptanceMessage verifyClickOnRequirementIDFromRequirementLinks(final String ID,
      final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    String artifactID = this.driverCustom.getWebElement("//span[@class='resource-id']").getText();
    if (artifactID.trim().equals(ID)) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on the expected requirement.Clicked on the requirement - " + ID);
    }
    return new TestAcceptanceMessage(false, "Verified: Not clicked on the requirement.");
  }

  /**
   * <p>
   * Verify requirement displays or not after ${@link RQMConstructionPage#filterRequirementLinks(String)}
   * <p>
   *
   * @author NVV1HC
   * @param nameOrID name or ID of artifact
   * @param expectedFilter true or false, true if you want to check filtering has result return, false if you want to
   *          check no result return after filtering
   * @param lastResult result from ${@link RQMConstructionPage#filterRequirementLinks(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyFilterRequirementLinks(final String nameOrID, final String expectedFilter,
      final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    boolean actualResult = false;
    try {
      actualResult = this.driverCustom.isElementClickable(
          "//a[@class='jazz-ui-ResourceLink']/div[contains(text(),'" + nameOrID + "')]", Duration.ofSeconds((this.timeInSecs.getSeconds() * 5)));
    }
    catch (Exception e) {}

    if (String.valueOf(actualResult).equalsIgnoreCase(expectedFilter.toLowerCase())) {
      if (actualResult) {
        return new TestAcceptanceMessage(true, "Verified filter requirement '" + nameOrID +
            "' successfully!\nRequirement link is displayed as expectation!");
      }
      return new TestAcceptanceMessage(true, "Verified filter requirement '" + nameOrID +
          "' successfully!\nRequirement link is not displayed as expectation!");
    }
    if (actualResult) {
      return new TestAcceptanceMessage(true, "Verified filter requirement '" + nameOrID +
          "' failed!\nExpectation: Requirement link is not displayed!\\nActual: Requirement link is displayed!");
    }
    return new TestAcceptanceMessage(true, "Verified filter requirement '" + nameOrID +
        "' failed!\nExpectation: Requirement link is displayed!\\nActual: Requirement link is not displayed!");
  }

  /**
   * <p>
   * Verify Edited Rqm Artifact description successfully This method called after
   * {@link RQMConstructionPage#editDescriptionRqmArtifact(String)}.
   *
   * @author VDY1HC
   * @param newDescription - newly edited description
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyEditDescriptionRqmArtifact(final String newDescription, final String lastResult) {
    waitForSecs(Duration.ofSeconds(10));
    this.driverCustom
        .isElementPresent("//div[@dojoattachpoint='progressIndicator' and contains(@style,'display: none')]", Duration.ofSeconds(20));
    boolean isSaved = this.driverCustom.isElementPresent(
        "//div[@dojoattachpoint='actionsAreaRight']//button[@title='Save' and not(@disabled='')]", this.timeInSecs);
    if (isSaved) {
      return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to save artifact");
    }
    String expectedDescription = newDescription;
    if (newDescription.equalsIgnoreCase("null")) {
      expectedDescription = "< Click here to enter a description >";
    }
    WebElement txbDescription =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_DESCRIPTION_TEXTBOX_XPATH);
    String actualDescription = txbDescription.getAttribute("value");
    if (actualDescription.equalsIgnoreCase(expectedDescription)) {
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - Description is updated with new text (Current description: " + actualDescription + ")");
    }
    return new TestAcceptanceMessage(false,
        "Verified: FAILED - Description is NOT updated with new text (Current description: " + actualDescription + ")");
  }

  /**
   * <p>
   * Verify Edited Rqm Artifact description successfully This method called after
   * {@link RQMConstructionPage#editDescriptionRqmArtifactWithoutSave(String)}.
   *
   * @author VDY1HC
   * @param newDescription - newly edited description
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyEditDescriptionRqmArtifactWithoutSave(final String newDescription,
      final String lastResult) {
    String expectedDescription = newDescription;
    if (newDescription.equalsIgnoreCase("null")) {
      expectedDescription = "< Click here to enter a description >";
    }
    WebElement txbDescription =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_DESCRIPTION_TEXTBOX_XPATH);
    String actualDescription = txbDescription.getAttribute("value");
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    if (actualDescription.equalsIgnoreCase(expectedDescription)) {
      return new TestAcceptanceMessage(true,
          "Description is updated with new text (Current description: " + actualDescription + ")");
    }
    return new TestAcceptanceMessage(false,
        "Description is NOT updated with new text (Current description: " + actualDescription + ")");
  }

  /**
   * <p>
   * Verify Get Rqm Artifact description successfully This method called after
   * {@link RQMConstructionPage#getDescriptionOfRqmArtifact()}.
   *
   * @author VDY1HC
   * @param lastResult - description of Rqm Artifact
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetDescriptionOfRqmArtifact(final String lastResult) {
    if (lastResult.isEmpty()) {
      return new TestAcceptanceMessage(false, "Unable to get Description of RQM artifact");
    }
    return new TestAcceptanceMessage(true, "Description of RQM artifact is " + lastResult);
  }

  /**
   * Verify result from: Verify every categoies displayed on Summary section <br>
   * This method called after {@link RQMConstructionPage#verifyAllCategoriesValue(String)}. <br>
   *
   * @author VDY1HC
   * @param dataInput - special string to contains Category Name and Category Vale <br? Follow rule:
   *          <categoryName1>,<categoryValue1>;<categoryName2>,<categoryValue2>;...
   * @param lastResult - result return
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyVerifyAllCategoriesValue(final String dataInput, final String lastResult) {
    if (lastResult.equalsIgnoreCase("false")) {
      return new TestAcceptanceMessage(false,
          "Verified FAILED: Not all the categories displayed with correct value. \nExpected value: " + dataInput);
    }
    return new TestAcceptanceMessage(true, "Verified PASSED: All the categories displayed with correct value.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#clickOnImageButton(String)}.
   *
   * @param button button.
   * @param lastResult string.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnImageButton(final String button, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified Button visibility  of '" + button +
          "' And button Clicked successfully.\n or \nbutton already Clicked successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified Button visibility  of '" + button + "' But button not Clicked successfully.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#clickOnTab(String)}.
   *
   * @param button button.
   * @param lastResult string.
   * @return {@link Boolean}
   */
  public TestAcceptanceMessage verifyClickOnTab(final String button, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verify click on Tab successfully.");
    }
    return new TestAcceptanceMessage(false, "Verify Can not click on Tab.");
  }

  /**
   * Verifies the action of {@link RQMConstructionPage#removeLinksFromRequirementLinks(String)}.
   *
   * @param requirementId Id of QM resources
   * @param lastResult - last result
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRemoveLinksFromRequirementLinks(final String requirementId,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - Requirement ID " + requirementId + " is removed from current QM artifact");
    }
    return new TestAcceptanceMessage(false,
        "Verified: FAILED - Requirement ID " + requirementId + " is NOT removed from current QM artifact "
            + "or FAILED to remove requirement artifact from current QM artifact");
  }

  /**
   * Verifies the action of {@link RQMConstructionPage#chooseComponentFromArtifactSelection(String)}.
   *
   * @author VDY1HC
   * @param RMComponent - name of the RM artifact component.
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseComponentFromArtifactSelection(final String RMComponent,
      final String lastResult) {
    if (this.driverCustom.isElementVisible("//select[@aria-label='Component:' and @disabled='']", Duration.ofSeconds(10)) 
        || this.driverCustom.isElementInvisible("//select[@aria-label='Component:']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Dropdown component is not enable for selecting");
    }
    Dropdown drdComponent =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Component:"), Duration.ofSeconds(10)).getFirstElement();
    String selectedOption = drdComponent.getDefaultValue();
    if (selectedOption.equalsIgnoreCase(RMComponent)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Selected component with name: " + RMComponent +
          "\nActual result: " + selectedOption + "\nExpected result: " + RMComponent);
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Unable to selected component with name: " +
        RMComponent + "\nActual result: " + selectedOption + "\nExpected result: " + RMComponent);
  }

  /**
   * Verifies the action of {@link RQMConstructionPage#clickOnAddNewLinksFromRequirementLinks()}.
   *
   * @author KYY1HC
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnAddNewLinksFromRequirementLinks(final String lastResult) {
    try {
      this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Artifact Container selection"), timeInSecs);
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - click on 'Add new links' button.\n\nActual Result is 'Artifact Container selection' dialog opened as expected.");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
      return new TestAcceptanceMessage(false,
          "Verified: FAILED - click on 'Add new links' button.\n\nActual Result is 'Artifact Container selection' dialog is not opened as expected.");
    }
  }

  /**
   * Verifies the action of {@link RQMConstructionPage#addContentToTextAreaSection(Map)}.
   *
   * @author VDY1HC
   * @param additionalParams - input text in editor
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyAddContentToTextAreaSection(final Map<String, String> additionalParams,
      final String lastResult) {
    String inputText = additionalParams.get("testArtifactDesignDescriptionValue");
    if (inputText.equalsIgnoreCase("null")) {
      inputText = "";
    }
    StringBuilder actualTextDisplayed = new StringBuilder();
    this.driverCustom.switchToFrame(
        "//div[contains(@class,'dijitTitlePaneSelected') and not(contains(@class,'hidden'))]//iframe[@class='cke_wysiwyg_frame cke_reset']");
    try {
      WebElement rowInEditor = this.driverCustom.getWebElement("//body[contains(@class,'cke_editable')]");
      actualTextDisplayed = actualTextDisplayed.append(rowInEditor.getText());
    }
    catch (Exception e) {
      List<WebElement> rowsInEditor = this.driverCustom.getWebElements("(//body[contains(@class,'cke_editable')]/p)");
      for (WebElement row : rowsInEditor) {
        actualTextDisplayed = actualTextDisplayed.append("\n" + row.getText());
      }
    }
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    if (actualTextDisplayed.toString().contains(inputText.trim())) {
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - Text editor dialog contains input text. \nActual result: Text editor content: " +
              actualTextDisplayed);
    }
    return new TestAcceptanceMessage(false,
        "Verified: FAILED - Text editor dialog DOES NOT contain input text. \nActual result: Text editor content: " +
            actualTextDisplayed);
  }

  /**
   * Verifies the action of {@link RQMConstructionPage#getContentOfTextAreaSection()}. <br>
   *
   * @author VDY1HC
   * @param lastResult - content of text area section
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetContentOfTextAreaSection(final String lastResult) {
    if (lastResult.equalsIgnoreCase("null")) {
      return new TestAcceptanceMessage(true,
          "Verified: Content of text area is NOT inputted.\nActual result: Add Content button is enabled");
    }
    return new TestAcceptanceMessage(true,
        "Verified: Content of text area is inputted. \nActual result: " + lastResult);
  }

  /**
   * Verifies the action of {@link RQMConstructionPage#verifyUpdateAttributeHistoryByIndex(Map)}. <br>
   *
   * @author VDY1HC
   * @param additionalParams - additionalParams
   * @param lastResult - lastResult
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyVerifyUpdateAttributeHistoryByIndex(final Map<String, String> additionalParams,
      final String lastResult) {
    String index = additionalParams.get("HISTORY_INDEX");
    String expectedAttributeName = additionalParams.get("ATTRIBUTE_NAME");
    String updateType = additionalParams.get("UPDATE_TYPE");
    String expectedAttributeNewValue = additionalParams.get("ATTRIBUTE_NEW_VALUE");
    String expectedAttributeOldValue = additionalParams.get("ATTRIBUTE_OLD_VALUE");
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified: PASSED - History row with index " + index + " is match expected." +
              "\n Expected value: \nAttribute name:" + expectedAttributeName + "\nAttribute old value: " +
              expectedAttributeOldValue + "\nAttribute new value: " + expectedAttributeNewValue + "\nUpdate type: " +
              updateType + "(base on update type: some value will be null)");
    }
    return new TestAcceptanceMessage(false,
        "Verified: FAILED - History row with index " + index + " is NOT match expected." +
            "\n Expected value: \nAttribute name:" + expectedAttributeName + "\nAttribute old value: " +
            expectedAttributeOldValue + "\nAttribute new value: " + expectedAttributeNewValue + "\nUpdate type: " +
            updateType + "(base on update type: some value will be null)");
  }

  /**
   * <p>
   * This method is called after executing the method
   * ${@link RQMConstructionPage#generateExecutionRecordAndGetResult(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param additionalParams stores keys "Test Plan value", "Iteration value", "Test Environment value", "Test Suite
   *          Execution Record value", "selectall".
   * @param lastResult result from ${@link RQMConstructionPage#generateExecutionRecordAndGetResult(Map)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyGenerateExecutionRecordAndGetResult(final Map<String, String> additionalParams,
      final String lastResult) {
    String pageTitle = this.driverCustom.getCurrUrl();
    if (additionalParams.get("testArtifactName").equalsIgnoreCase("test cases")) {
      if (pageTitle.contains("viewResult")) {
        return new TestAcceptanceMessage(true, "Verified: Generate Test Case Result successfully.");
      }
      return new TestAcceptanceMessage(false,
          "Verified: Generate Test Case Result failed.\nAfter running Test Case, should navigate to the Test Case Result page, but actual page is: '" +
              pageTitle + "'");
    }
    if (pageTitle.contains("Test Suite Result")) {
      return new TestAcceptanceMessage(true, "Verified: Generate Test Suite Result successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Generate Test Suite Result failed.\nAfter running Test Suite, should navigate to the Test Suite Result page, but actual page is: '" +
            pageTitle + "'");
  }

  /**
   * <p>
   * This method is called after executing ${@link RQMConstructionPage#clickOnLinkInRightSide(String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param linkType type of link, e.g: Parent Test Plans, Related Test Suites, Related Test Scripts, Validates
   *          Requirements
   * @param link link is clicked
   * @param additionalParam_pageTitle expected page title after clicking on the link
   * @param lastResult result from ${@link RQMConstructionPage#clickOnLinkInRightSide(String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnLinkInRightSide(final String linkType, final String link,
      final String additionalParam_pageTitle, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    String actualPageTitle = this.driverCustom.getCurrentPageTitle();
    if (actualPageTitle.contains(additionalParam_pageTitle)) {
      return new TestAcceptanceMessage(true,
          "Verified: '"+link+"' is displayed in " +linkType+" section. And click on the link '" + additionalParam_pageTitle + "' successfully!");
    }

    if (actualPageTitle.contains(additionalParam_pageTitle)) {
      return new TestAcceptanceMessage(true,
          "Verified: "+link+" is displayed in " +linkType+" section. And click on the link '" + additionalParam_pageTitle + "' successfully!");
    }
    if (!actualPageTitle.contains(additionalParam_pageTitle)) {
      try {
        List<WebElement> results1 = this.driverCustom.getWebElements("//div[@dojoattachpoint='_contentNode']//a");


        for (WebElement artifact : results1) {
          if (link.contains(artifact.getText())) {
            return new TestAcceptanceMessage(true,
                "Verified: Navigated to target pages after clicking link from the Work Item." +
                    "\nActual Result is clicked on \"" + link + "\" from \"" + linkType +
                    "  section of the Work Item.\nExpected Result is \"" + link + "\" should be displayed from \"" +
                    linkType + " section of the Work Item.");
          }
        }
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false,
            "Verified: Click on the link '" + additionalParam_pageTitle +
                "' failed!\nExpected page title should contains: '" + additionalParam_pageTitle +
                "'.\nBut actual page title is: '" + actualPageTitle + "'");
      }


    }
    return new TestAcceptanceMessage(false,
        "Verified: Click on the link '" + additionalParam_pageTitle +
            "' failed!\nExpected page title should contains: '" + additionalParam_pageTitle +
            "'.\nBut actual page title is: '" + actualPageTitle + "'");
  }

  /**
   * <p>
   * This method is called after executing ${@link RQMConstructionPage#isLinkDisplayed(String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param linkType type of link, e.g: Parent Test Plans, Related Test Suites, Related Test Scripts, Validates
   *          Requirements
   * @param link is to be verified
   * @param additionalParam true or false, true if we want to check link is visible or vice versa
   * @param lastResult result from ${@link RQMConstructionPage#isLinkDisplayed(String, String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyIsLinkDisplayed(final String linkType, final String link,
      final String additionalParam, final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalParam.toLowerCase())) {
      if (lastResult.equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, "Verified: The visibility of link '" + link + "' under '" + linkType +
            "' section PASSED!.\nLink is visible as expectation");
      }
      return new TestAcceptanceMessage(true, "Verified: The visibility of link '" + link + "' under '" + linkType +
          "' section PASSED!.\nLink is invisible as expectation");
    }
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(false, "Verified: The visibility of link '" + link + "' under '" + linkType +
          "' section failed!\nExpected result: Link is invisible.\nActual result: Link is visible");
    }
    return new TestAcceptanceMessage(false, "Verified: The visibility of link '" + link + "' under '" + linkType +
        "' section failed!\nExpected result: Link is visible.\nActual result: Link is invisible");
  }

  /**
   * <p>
   * Verify clicked on test case link from test execution result.
   * <p>
   * This method called after {@link RQMConstructionPage#clickOnTestCaseLinkFromTestExecutionResult(String)}.
   *
   * @param testCaseID id of the test case.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTestCaseLinkFromTestExecutionResult(final String testCaseID,
      final String lastResult) {
    WebElement artifactNames = this.driverCustom
        .getFirstVisibleWebElement("//div[@dojoattachpoint='viewHeaderLeft']//span[@dojoattachpoint='viewID']", null);
    if (artifactNames.getText().contains(testCaseID)) {
      return new TestAcceptanceMessage(true,
          "Verified: Displayed page after click on artifact by name or id." +
              "\nActual Result is the artifact details page with name/id is '" + testCaseID +
              "' is displayed.\nExpected is the artifact details page with name/id is '" + testCaseID +
              "' should display.");

    }
    return new TestAcceptanceMessage(false,
        "Verified: displayed page after click on artifact by name or id." +
            "\nActual result is the artifact details page with name/id is '" + testCaseID +
            "' is not displayed.\nExpected is the artifact details page with name/id is '" + testCaseID +
            "' is displayed.");

  }

  /**
   * <p>
   * Verify link validity status is set as expected.
   * <p>
   * This method called after {@link RQMConstructionPage#setLinkValidityStatus(String, String)}.
   *
   * @param linkValidityStatus status of link validity.
   * @param artifactID id of the linked artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetLinkValidityStatus(final String linkValidityStatus, final String artifactID,
      final String lastResult) {
    if (this.driverCustom
        .isElementVisible(
            "//div[contains(text(),'" + artifactID +
                "')]/../../..//preceding-sibling::td//img[contains(@src,'validity/is" + linkValidityStatus + "')]",
            this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verified:Link Validity status changed to '" + linkValidityStatus +
          "' for requirement '" + artifactID + "' as expected.");
    }
    return new TestAcceptanceMessage(false,
        "Link Validity status not changed to '" + linkValidityStatus + "'as expected.");
  }

  /**
   * <p>
   * Verify link validity status.
   * <p>
   * This method called after {@link RQMConstructionPage#verifyLinkValidityStatus(String, String)}.
   *
   * @param linkValidityStatus status of link validity.
   * @param artifactID id of the linked artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyVerifyLinkValidityStatus(final String linkValidityStatus, final String artifactID,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified: Updated Link Validity status for requirement '" + artifactID + "'- '" + linkValidityStatus + "'");
    }
    return new TestAcceptanceMessage(false, "Link Validity status is not updated - " + linkValidityStatus);
  }

  /**
   * <p>
   * Verify validity summary of the test case.
   * <p>
   * This method called after {@link RQMConstructionPage#getValiditySummary(String, String)}.
   *
   * @param linkValidityStatus status of link validity.
   * @param testCaseID id of the test case.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetValiditySummary(final String linkValidityStatus, final String testCaseID,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: Validity Summary of the test case '" + testCaseID +
          "' is updated to '" + linkValidityStatus + "' as expected.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Validity Summary of the test case is not updated to - " + linkValidityStatus);
  }

  /**
   * <p>
   * Verify get requirement of the test case.
   * <p>
   * This method called after {@link RQMConstructionPage#getRequirement()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetRequirement(final String lastResult) {
    if (lastResult != null) {
      return new TestAcceptanceMessage(true, "Got the Requirement from 'Requirement Links' section.");
    }
    return new TestAcceptanceMessage(false, "Did not find the created requirement.");
  }

  /**
   * <p>
   * Verify clear suspicion status of the requirement.
   * <p>
   * This method called after {@link RQMConstructionPage#getClearSuspicionStatus()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @param additionalParam to be pass in the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetClearSuspicionStatus(final String lastResult, final String additionalParam) {
    if (lastResult.equals(additionalParam)) {
      return new TestAcceptanceMessage(true,
          "Displayed wizard '" + additionalParam + "' after the Clear Suspicion requirement.");
    }
    return new TestAcceptanceMessage(false, "Expected wizard is not displayed.");
  }
  
  /**
   * Verify click on Show Inline Filters<br>
   * This method called after {@link RQMConstructionPage#selectSlideDown()}.
   * @author VDY1HC
   * @param lastResult - returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectSlideDown(final String lastResult) {
    if (this.driverCustom.isElementPresent(RQMConstants.RQMPROJECT_HIDE_SLIDERDOWN_DROPDOWN_XPATH, timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Clicked on Show Inline Filters");
    }
    return new TestAcceptanceMessage(true, "Verify: FAILED - Not clicked on Show Inline Filters");
  }
  
  /**
   * Verify Test Case link href available on Test Suites table<br>
   * This method called after {@link RQMConstructionPage#getTestCaseLinkFromTestSuitesTable(String)}.
   * @author YUU3HC
   * @param testCaseID id of the test case.
   * @param lastResult - returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetTestCaseLinkFromTestSuitesTable(final String testCaseID, final String lastResult) {
    if(lastResult.contains(testCaseID)) {
      return new TestAcceptanceMessage(true, "Verify: PASSED - Links to the Test Cases are available in the duplicated test suite. The href is present.");
    }
    return new TestAcceptanceMessage(false, "Verify: FAILED - Links to the Test Cases are unavailable in the duplicated test suite.The href is not present...");    
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#associateTestCaseWithTestSuite(String, String)}.
   *
   * @param testSuiteName the name of test suite.
   * @param testSuiteDescription description of test suite
   * @param lastResult get the last result of {@link RQMConstructionPage#associateTestCaseWithTestSuite(String, String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifyAssociateTestCaseWithTestSuite(final String testSuiteName, final String testSuiteDescription,
      final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    // click on related site bar
    WebElement siteBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
    if (this.driverCustom.isElementPresent("//div[@class='arrow-left']",Duration.ofSeconds(5))) {
      siteBar.click();
    }
    try {
      WebElement parentElement =
          this.driverCustom.getPresenceOfWebElement(RQMConstants.TITLE_CONTAINER_XPATH, "Parent Test Suites");
      if (parentElement.getAttribute("aria-pressed").equalsIgnoreCase("false")) {
        parentElement.click();
        this.engine.findElement(Criteria.isLink().withText(testSuiteName)).getFirstElement();
      }
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified the test case is NOT associated with new test suite successfully.\n" +
              "Actual result is that Exception throwing out when finding the case's link.\n" + e.getMessage());
    }
    return new TestAcceptanceMessage(true,
        "Verified the test case is associated with the test suite '" + lastResult + "' successfully.\n" +
            "Actual result is the test case '" + lastResult + "' is showing on the Parent Test Cases section.");
  }

  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#associateTestCaseWithTestPlan(String, String)}.
   *
   * @author YUU3HC
   * @param button to select radio button
   * @param testPlanName the name of test plan.
   * @param additionalParam true when need to verify if invalid Test Plan is not associate.
   * @param lastResult default param for verification method to work
   * @return validation message
   */
  public TestAcceptanceMessage verifyAssociateTestCaseWithTestPlan(final String button, final String testPlanName, final String additionalParam, final String lastResult) {
    Boolean flag = false;
    // click on related site bar
    WebElement siteBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
    if (this.driverCustom.isElementPresent("//div[@class='arrow-left']",Duration.ofSeconds(5))) {
      siteBar.click();
    }
    try {
    WebElement parentElement =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.TITLE_CONTAINER_XPATH, "Parent Test Plans");
    if (parentElement.getAttribute("aria-pressed").equalsIgnoreCase("false")) {
      parentElement.click();        
        LOGGER.LOG.info(" '"+ button +"' successfully.");
    }
      this.engine.findElement(Criteria.isLink().withText(testPlanName)).getFirstElement();
    }
    catch (Exception e) {
      if (additionalParam.equalsIgnoreCase("false")) {
            return new TestAcceptanceMessage(true, String.format("Verified the test case is NOT associated with test plan '%s' successfully", testPlanName));
      }
      return new TestAcceptanceMessage(false,
          "Verified the test case is NOT associated with test plan successfully.\n" +
              "Actual result is that Exception throwing out when finding the plan's link.\n" + e.getMessage());
    }
        return new TestAcceptanceMessage(true, 
        "Verified the test case is associated with the test plan '" + testPlanName + "' successfully.\n" +
            "Actual result is the test plan'" + testPlanName + "' is showing on the 'Parent Test Plan' section.");
//    WebElement parentElement =
//        this.driverCustom.getPresenceOfWebElement(RQMConstants.TITLE_CONTAINER_XPATH, "Parent Test Plans");
//    if (parentElement.getAttribute("aria-pressed").equalsIgnoreCase("false")) {
//      parentElement.click();        
//    }
//      if(this.driverCustom.isElementPresent("//*[text()='"+testPlanName+"']", timeInSecs)){
//        LOGGER.LOG.info(" '"+ button +"' successfully.");
//        flag = true;
//    }
//    // Set additionalParam=TRUE if verified Invalid case (flag=false)
//    if (additionalParam.equalsIgnoreCase("TRUE")) {  
//      if(flag == false) {
//        return new TestAcceptanceMessage(true,
//            "Verified: the test case is not associated with the test plan: '" + testPlanName + "'.\n" +
//                "\nExpected result: '" + testPlanName + "' is not showing on the Parent Test Plans section." +
//                "\nActual result is the test plan: '" + testPlanName + "' is not showing on the Parent Test Plans section.");
//      }
//      return new TestAcceptanceMessage(true,     
//        "Verified: the test case is associated with the test plan: '" + testPlanName + "'.\n" +
//        "\nExpected result: '" + testPlanName + "' is showing on the Parent Test Plans section." +
//        "\nActual result is the test plan: '" + testPlanName + "' is showing on the Parent Test Plans section.");
//    }
//    // Set additionalParam=FALSE if verified valid case (flag=true)
//    if (additionalParam.equalsIgnoreCase("FALSE")) {  
//      if (flag) {
//        return new TestAcceptanceMessage(true, 
//            "Verified: the test case is associated with the test plan: '" + testPlanName + "'.\n" +
//                "\nExpected result: '" + testPlanName + "' is showing on the Parent Test Plans section." +
//                "\nActual result is the test plan: '" + testPlanName + "' is showing on the Parent Test Plans section.");
//      }    
//    }
//    return new TestAcceptanceMessage(false, 
//        "Verified: the test case is not associated with the test plan: '" + testPlanName + "'.\n" +
//            "\nExpected result: '" + testPlanName + "' is not showing on the Parent Test Plans section." +
//            "\nActual result is the test plan: '" + testPlanName + "' is not showing on the Parent Test Plans section.");
  }
  
  /**
   * This method is called after executing {@link RQMConstructionPage#associateArtifactToRqmArtifact(String, String)}
   *@param artifactName - name/id of artifact
   *@param dialogTitle - dialog name
   *@param lastResult - result returned from main method
   *@return verification message
   */
  public TestAcceptanceMessage verifyAssociateArtifactToRqmArtifact(final String artifactName, final String dialogTitle, final String lastResult) {
    String cbxSelectTestCase = this.driverCustom.getPresenceOfWebElement("//input[contains(@aria-label,'DYNAMIC_VAlUE')]", artifactName).getAttribute("tabindex");
    if(cbxSelectTestCase.equalsIgnoreCase("-1")) {
      return new TestAcceptanceMessage(true, "Testcase "+artifactName+ " is selected successfully!");
    }
    return new TestAcceptanceMessage(false, "Testcase "+artifactName+ " is selected unsuccessfully!");
  }
  
  /**
   * <p>
   * Verifies the action of select value in dropdown.
   *
   * @param additionalParams data.
   * @param dropdownValue used to be chosen in the select criteria drop down.
   * @param dropdownLabel is the drop down attribute.
   * @param lastResult get the  result  from last execution
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectDropdownValue(final Map<String, String> additionalParams,final String dropdownLabel,final String dropdownValue,  final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    String actualValue = "";
    try {
       actualValue = this.driverCustom.getPresenceOfWebElement("//label[contains(text(),'DYNAMIC_VAlUE')]/following::div[1]//span[@class='selection-label']", dropdownLabel).getText();
    }
    catch (Exception e) {
      waitForSecs(Duration.ofSeconds(2));
    }
    if (dropdownValue.contains(actualValue)) {
      return new TestAcceptanceMessage(true, "Actual dropdown value is -'" + actualValue +
          "' set successfully. \nMatching with expected dropdown value is -'" + dropdownValue + "'.");
    }
    return new TestAcceptanceMessage(false, "Actual value of dropdown is - '" + actualValue +
        "' not set successfully. \nExpected dropdown value is -'" + dropdownValue + "'.");
  }
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#selectCriteria(String, String)}
   *
   * @param value used to be chosen in the select criteria drop down.
   * @param label is the drop down attribute.
   * @param lastResult get the  result  from last execution
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectCriteria(final String value,final String label,  final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    String actualValue = "";
    try {
       actualValue = this.driverCustom.getPresenceOfWebElement("//label[contains(text(),'DYNAMIC_VAlUE')]/following::div[1]//span[@class='selection-label']", label).getText();
    }
    catch (Exception e) {
      Dropdown drd = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(label), timeInSecs).getFirstElement();
      actualValue = drd.getDefaultValue();
    }
    if (value.equalsIgnoreCase(actualValue)) {
      return new TestAcceptanceMessage(true, "Select dropdown value successfully.\nActual drodown value is -'" + actualValue +
          "'. \nMatching with expected dropdown value is -'" + value + "'.");
    }
    return new TestAcceptanceMessage(false, "Actual value of dropdown is - '" + actualValue +
        "' not set successfully. \nExpected dropdown value is -'" + value + "'.");
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#removeAssociatedArtifactInRqmArtifact(String, String)}
   *
   * @param artifactName used to be chosen in the select criteria drop down.
   * @param drdOption is the drop down attribute.
   * @param lastResult get the  result  from last execution
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRemoveAssociatedArtifactInRqmArtifact(final String artifactName, final String drdOption, final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    boolean isCbxDisplayed = this.driverCustom.isElementVisible("//input[contains(@aria-label,'DYNAMIC_VAlUE')]", timeInSecs, artifactName);
    if (!isCbxDisplayed) {
      return new TestAcceptanceMessage(true, "Artifact Id/Name'" + artifactName +
          "' is removed successfully.");
    }
    return new TestAcceptanceMessage(false, "Artifact Id/Name'" + artifactName +
        "' is removed unsuccessfully.");
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#clickOnAddorRemoveColumns(String)}
   * 
   * @param categoryName refer to the 'Add Or Remove Columns' 
   * @param lastResult get the  result  from last execution
   * @return acceptance object which contains verification results. 
   */
  
  public TestAcceptanceMessage verifyClickOnAddorRemoveColumns(final String categoryName, final String lastResult) {
    if (this.driverCustom.isElementPresent(RQMConstants.RQM_SELECT_COMP_XPATH, this.timeInSecs,categoryName)){
      return new TestAcceptanceMessage(true, "Verified The dropdown '" + categoryName +"' is clicked successfully ");
    }
    return new TestAcceptanceMessage(false, "Verified The dropdown '" + categoryName +"' is not clicked as expected");
    
  }
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#addColumns(String, String)}
   * 
   * @param columnName is the column title that added
   * @param lastResult get the  result  from last execution
   * @return acceptance object which contains verification results. 
   */
  public TestAcceptanceMessage verifyAddColumns(final String columnName, final String lastResult) {
    if (this.driverCustom.isElementPresent(RQMConstants.RQM_TEST_CASE_EXECUTION_RECORD, this.timeInSecs,columnName)){
      return new TestAcceptanceMessage(true, "Verified displayed The '" + columnName + "' column in the table successfully");
    }
    return new TestAcceptanceMessage(false, "Verified not displayed The '" + columnName + "' column in the table");
    
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage#isLinkDisplayedInTable(String, String, String)}
   * 
   * @param columnName is the column title that added
   * @param lastResult get the  result  from last execution
   * @param additionalParam refer to true/false
   * @return acceptance object which contains verification results. 
   */
  public TestAcceptanceMessage verifyIsLinkDisplayedInTable(final String columnName, final String ID, final String link, 
      final String additionalParam,final String lastResult) {
    if (lastResult.equals(additionalParam)){
      if(lastResult.equals("true")) {
        return new TestAcceptanceMessage(true, "Verified: The visibility of link '" + link + "' under '" + columnName +
          "' section PASSED!."
          + "\nLink is visible as expectation");
      }
      return new TestAcceptanceMessage(false, "Verified: The visibility of link '" + link + "' under '" + columnName +
        "' section FAILED!."
        + "\nLink is invisible");
    }
    if (lastResult.equals(additionalParam)==equals("false")) {
      return new TestAcceptanceMessage(true, "Verified: The link under '" + columnName + "' is not displayed as expectation. "
          + "\n Verification is PASSED!");
    }
    return new TestAcceptanceMessage(false, "Verified: The link under '" + columnName + "' still displayed. "
        + "\n Verification is FAILED!");
  }
  /**
   * <p> Verified the action of {@link RQMConstructionPage#addLinkType(String, String)}}
   * 
   * @param columnName refer to the column: Validates Architecture Elements, State, Tests Development Items,...
   * @param linkType refer to the Adding link type button: Add Architecture Element Link, Add Development Item Link,...
   * @return _
   */
  public TestAcceptanceMessage verifyAddOrRemoveLinkType(final String columnName, final String linkType,
      final String additionalParam,final String lastResult) {
    String linksDialog="//div[@class='jazz-ui-Dialog-header']//div[contains(text(),'Links')]";
    if(additionalParam.equals("true")) {
      if (this.driverCustom.isElementVisible(linksDialog, this.timeInSecs)) {
        return new TestAcceptanceMessage(true, "Verified: the '" + linkType + "' is clicked successfully.");
      }
      return new TestAcceptanceMessage(false,"Verified: The '" + linkType + "' is not clicked");
    }
    if (this.driverCustom.isElementPresent("//div[text()='DYNAMIC_VAlUE']", timeInSecs, linkType)) {
          return new TestAcceptanceMessage(false, "Verified: the '" + linkType + "' is still displayed in the table.");
        }
    return new TestAcceptanceMessage(true, "Verified: the '" + linkType + "' is removed successfully. ");
      
  }
  /**
   * <p>
   * Verified the action of {@link RQMConstructionPage #addValidatesArchitectureElementsLink(String, String)}}
   * Verified Component and Artifact are selected as expected in Links dialog
   *
   * @param componentName refer to the Component name in Links dialog
   * @param artifactName refer to the Artifact name in Links dialog
   * @return _
   */
  public TestAcceptanceMessage verifyAddValidatesArchitectureElementsLink(final String componentName, final String artifactName, final String lastResult)
  {
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
    this.waitForSecs(Duration.ofSeconds(5));
    String xpathArtifact ="//a[text()='DYNAMIC_VAlUE']//ancestor::div[@aria-selected='true']";
    if (this.driverCustom.isElementPresent(xpathArtifact,this.timeInSecs,artifactName )) {
      return new TestAcceptanceMessage(true, "Verified The '" + artifactName + "' artifact is selected successfully");
    }
    this.driverCustom.switchToDefaultContent();
    return new TestAcceptanceMessage(false, "Verified: The '" + artifactName + "' artifact is not selected");
    
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage #getAllItemsFromTable(String)}.
   *
   *@param tableName ex: This is Test Scripts table,...
   * @param lastResult - of {@link RQMConstructionPage#getAllItemsFromTable(String)}
   * @return message
   */
  public TestAcceptanceMessage verifyGetAllItemsFromTable(final String tableName,final String lastResult) {
    if (!lastResult.isEmpty()) {
      List<WebElement> linksList =
          this.driverCustom.getWebElements("//table[@summary='DYNAMIC_VAlUE']//tbody/tr",tableName);
      LOGGER.LOG.info("Total number of artifacts from selected view is : " + linksList.size());
      return new TestAcceptanceMessage(true, "Actual value is : \n>>---->>>> \"" + lastResult + "\".\n");
    }
    return new TestAcceptanceMessage(false, "Actual value  is : \"" + lastResult + "\".");
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage #countNumberOfArtifactsDisplayed(String)}.
   *
   * @param tableName _
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCountNumberOfArtifactsDisplayed(final String tableName, final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, String.format("Counted %s artifacts displayed", lastResult));
    }
    return new TestAcceptanceMessage(false, "Error when counting artifacts");
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage #exportRqmArtifacts(Map)}.
   *
   * @param additionalParams _
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyExportRqmArtifacts(final Map<String, String> additionalParams, final String lastResult) {
    String exportComplete = "//tr[@class='selected-row-highlight']//img[contains(@src,'complete_obj.gif')]";
    if (this.driverCustom.isElementPresent(exportComplete,timeInSecs)){
      return new TestAcceptanceMessage(true, "Verified the '" + additionalParams.get("exportInline") + "' file is exported successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified The '" + additionalParams.get("exportInline") + "' file is not exported.");
  }
  
  /**
   * Verify action {@link RQMConstructionPage#getListSectionsMenuItem}
   * @param lastResult : returned value of last method which is executed just before.
   * @return acceptance object which contains verification results.
   * 
   */
  public TestAcceptanceMessage verifyGetListSectionsMenuItem(final String lastResult) {
    if(!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, " Menu item list contains: "+lastResult);
    }
       return new TestAcceptanceMessage(false, "Unable to get menu item list");
  }
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage #selectModuleInLinksDialog(String)}.
   * 
   * @param moduleID of module
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance message
   */
  public TestAcceptanceMessage verifySelectModuleInLinksDialog(final String moduleID, final String lastResult) {
    this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='iframe']");
    waitForSecs(Duration.ofSeconds(10));
    if (this.driverCustom.isElementInvisible("//span[@class='matching-results']", this.timeInSecs)) {
      return new TestAcceptanceMessage(false,
          "Verified FAILED - Can't click on modules radio button or input Modules ID failed");     
    }
    this.driverCustom.switchToDefaultContent();
    return new TestAcceptanceMessage(true, "Verified PASSED - Click on modules radio button and input Modules ID successfully!"); 
  }
  
  /**
   * <p>
   * Verifies the action of {@link RQMConstructionPage #selectModuleInLinksDialog(String)}.
   * 
   * @param moduleID of module
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance message
   */
  public TestAcceptanceMessage verifyMergeChangesToAStream(final Map<String, String> additionalParams, final String lastResult) {
    String finalMergeStatus = "//div[contains(text(),'All of the changes were resolved in the target configuration.')]";
    String artifactMergeStatus = "//img[contains(@title,'Artifact merged successfully')]";
    if (this.driverCustom.isElementPresent(finalMergeStatus,timeInSecs) &&
        this.driverCustom.isElementPresent(artifactMergeStatus,timeInSecs)){
      return new TestAcceptanceMessage(true, "Verified all of the changes were resolved in the target configuration.");
    }
    return new TestAcceptanceMessage(false, "Verified total of '" + additionalParams.get("total") + "' differences between the configurations')].");
  }
  
  /**
   * @param sections stores the list of sections provided by user
   * @param additionalParams stores keys 'creatingUsingTemplateName' and 'testArtifactTemplateValue'
   * @param sectionsList stores the list of sections retrieved from plan created
   * @return acceptance message
   */
  public TestAcceptanceMessage verifyChooseTemplateFromSummaryForTestPlan(final String[] sections, final Map<String, String> additionalParams, final List<String> sectionsList) {
    int count = 0;
    if(this.driverCustom.getFirstVisibleWebElement("//label[contains(text(),'DYNAMIC_VAlUE')]/following-sibling::div/span[@class='selection-label']", additionalParams.get("creatingUsingTemplateName")).getText().equals(additionalParams.get("testArtifactTemplateValue")))
    {
      for(int i=0; i< sections.length ; i++) {
        if(sections[i].equalsIgnoreCase(sectionsList.get(i))){
          count++;
        }
      }
      if(count == sections.length) {
        return new TestAcceptanceMessage(true, "Test Plan is successfully created from newly created Test Plan Template and only related sections are displayed.");
      }
      else {
        return new TestAcceptanceMessage(false, "Test Plan is successfully created from newly created Test Plan Template but related sections are not displayed.");
      }
    }
    else {
      return new TestAcceptanceMessage(false, "Test Plan is not created from newly created Test Plan Template.");
    }
    
  }
}
