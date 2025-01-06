/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.rqm;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstructionPage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMExecutionPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.finder.text.LabelFinder;

import finder.text.label.JazzLabelFinder;

/**
 * This Page represents RQM Execution Page.
 */
public class RQMExecutionPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom driver.
   */
  public RQMExecutionPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new LabelFinder(), new JazzLabelFinder());
  }

  /**
   * <p>
   * Verifies the action of {@link RQMExecutionPage#selectCategory(String)}.
   *
   * @param category name or id of artifact
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectCategory(final String category, final String lastResult) {
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
    List<WebElement> results = this.driverCustom
        .getWebElements("//div[@dojoattachpoint='titleOutterContainerNode']//span[contains(@dojoattachpoint,'view')]");
    for (WebElement artifact : results) {
      if (category.contains(artifact.getText())) {
        return new TestAcceptanceMessage(true,
            "Verified: Created Execution Record is opened from Execution Record.\nActual Result \"" + category +
                "\" is opened." + RQMConstants.EXPECTED_RESULTS + category + "\" should be opened");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Created Execution Record is not opened from Execution Record.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMExecutionPage#clickOnJazzImageButtons(String)}.
   *
   * @param button button
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyclickOnJazzImageButtons(final String button, final String lastResult) {
    switch (button) {
      case "Run Test Case  (Ctrl+Shift+X)":
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_EXECUTIONRECORDS_BUTTON_XPATH);
        if (this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_EXECUTIONRECORDS_BUTTON_XPATH)
            .getAttribute("aria-expanded").equals("true")) {
          return new TestAcceptanceMessage(true, "Verified 'Run Test Case  (Ctrl+Shift+X)' button clicked sucessfully." +
              RQMConstants.ACTUAL_RESULT_IS + button + RQMConstants.IS_BUTTON_CLICKED_SUCESSFULLY);
        }
        break;
      case "Run Test Suite (Ctrl+Shift+X)":
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_RUN_TEST_SUITE_BUTTON_XPATH);
        if (this.driverCustom.getFirstVisibleWebElement(RQMConstants.RQMEXECUTIONPAGE_RUN_TEST_SUITE_BUTTON_XPATH, "Run")
            .getAttribute("aria-expanded").equals("true")) {
          return new TestAcceptanceMessage(true,
              "Verified 'Run Test Suite (Ctrl+Shift+X)' button clicked successfully." + "\nActual result '" + button +
                  "' is clicked successfully.\nExpected Result '" + button + "' should clicked successfully.");
        }
        break;
      case "Run":
      case "Show Result":
        this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTTYPEVALUE_CHECKBOX_XPATH, Duration.ofSeconds(10), button);
        if (!this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTTYPEVALUE_CHECKBOX_XPATH, Duration.ofSeconds(10), button)) {
          return new TestAcceptanceMessage(true, RQMConstants.VERIFIED + button + "' button clicked sucessfully." +
              RQMConstants.ACTUAL_RESULT_IS + button + RQMConstants.IS_BUTTON_CLICKED_SUCESSFULLY);
        }
        break;
      case "Pass (Ctrl+Shift+P)":
        try {
          this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, "Passed");
          if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, Duration.ofSeconds(10), "Passed")) {
            return new TestAcceptanceMessage(true, "Verified 'Pass' button clicked sucessfully." +
                RQMConstants.ACTUAL_RESULT_IS + button + RQMConstants.IS_BUTTON_CLICKED_SUCESSFULLY);
          }
        }
        catch (Exception e) {
          this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, "Pass");
          if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, Duration.ofSeconds(10), "Pass")) {
            return new TestAcceptanceMessage(true, "Verified 'Pass' button clicked sucessfully." +
                RQMConstants.ACTUAL_RESULT_IS + button + RQMConstants.IS_BUTTON_CLICKED_SUCESSFULLY);
          }
        }
        break;
      case "Fail (Ctrl+Shift+F)":
        try {
          this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, "Fail");
          if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, Duration.ofSeconds(10), "Fail")) {
            return new TestAcceptanceMessage(true, "Verified 'Fail' button clicked sucessfully." +
                RQMConstants.ACTUAL_RESULT_IS + button + RQMConstants.IS_BUTTON_CLICKED_SUCESSFULLY);
        }}
        catch (Exception e) {
          this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, "Failed");
          if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, Duration.ofSeconds(10), "Failed")) {
            return new TestAcceptanceMessage(true, "Verified 'Fail' button clicked sucessfully." +
                RQMConstants.ACTUAL_RESULT_IS + button + RQMConstants.IS_BUTTON_CLICKED_SUCESSFULLY);
        }
        }
        break;
      case "Close and Show Results":
        try {
          WebElement drdActualStatus = this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH);
          this.driverCustom.getClickableWebElement(drdActualStatus).click();
          Select sel = new Select(drdActualStatus);
          sel.getFirstSelectedOption().getText();
          return new TestAcceptanceMessage(true,
              "Verified: Test Suite Execution Result Status.\nActual Result \"" + sel.getFirstSelectedOption().getText() +
                  "\"" + " is the result of the Test Suit Execution.\nExpected Result \"" +
                  sel.getFirstSelectedOption().getText() + "\" should be result of" + " Test Suite Execution.");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              "Verified: Test Suite Execution Result Status not displayed as expected.");
        }
        default:
          if (Boolean.parseBoolean(lastResult)) {
            return new TestAcceptanceMessage(true,
                "Verified: '" + button + "' button is clicked successfully and last result is '" + lastResult + "'.");
          }
          return new TestAcceptanceMessage(false,
              "Verified: '" + button + "' button is not clicked successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified " + button + " is not clicked.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMExecutionPage#clickOnAllPassButton()}.
   *
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnAllPassButton(final String lastResult) {
    waitForSecs(5);
    if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)), "Passed")) {
      return new TestAcceptanceMessage(true, "Verified all 'Pass' button clicked sucessfully." +
          "\nActual result is '" + "Pass (Ctrl+Shift+G)" + "' is clicked sucessfully.");
    }
    else if (this.driverCustom.isElementVisible(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 10)), "Pass")) {
      return new TestAcceptanceMessage(true, "Verified all 'Pass' button clicked sucessfully." +
          "\nActual result is '" + "Pass (Ctrl+Shift+P)" + "' is clicked sucessfully.");
    }
    return new TestAcceptanceMessage(false, "Verified all 'Pass' button not clicked.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMExecutionPage#getTestExecutonRecordStepResults()}.
   *
   * @param additionalparameter data.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetTestExecutonRecordStepResults(final String additionalparameter,
      final String lastResult) {
    String[] verdict = lastResult.split(",");
    LOGGER.LOG.info(Arrays.toString(verdict));
    for (String iterable_element : verdict) {
      if (!iterable_element.contains(additionalparameter)) {
        return new TestAcceptanceMessage(false,
            "Verified all '" + additionalparameter + "' button not clicked sucessfully.");
      }
    }
    return new TestAcceptanceMessage(true, "Verified the verdict of remaining steps is '" + additionalparameter + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMExecutionPage#getTestCaseExecutionResultStatus()}.
   * 
   * @param additionalparameter data.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifygetTestCaseExecutionResultStatus(final String additionalparameter,
      final String lastResult) {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_ACTUALRESULT_DROPDOWN);
    if (additionalparameter.equalsIgnoreCase(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified Actual result status sucessfully." +
          "\nActual result status is '" + additionalparameter + "' Verified sucessfully.");
    }
    return new TestAcceptanceMessage(false, "Verified all 'Pass' button not clicked.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMExecutionPage#getTestExecutonRecordStepResult(String)}.
   *
   * @param stepNumber number.
   * @param additionalparameter data.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifygetTestExecutonRecordStepResult(final String stepNumber,
      final String additionalparameter, final String lastResult) {
    if (lastResult.contains(additionalparameter)) {
      return new TestAcceptanceMessage(true,
          "Verified the verdict of step '" + stepNumber + "' is '" + lastResult + "'.");
    }
    LOGGER.LOG.info(additionalparameter + "  additional    last  " + lastResult);
    return new TestAcceptanceMessage(false,
        "Verified step '" + stepNumber + "' '" + additionalparameter + "' button not clicked.");
  }

  /**
   * <p>
   * Verify 'Start Manual Step' link is clicked.
   * <p>
   * This method called after {@link RQMExecutionPage#clickOnStartManualStep()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnStartManualStep(final String lastResult) {
    if (this.driverCustom.isElementVisible("//button[@title='Press to close the view']", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified: Clicked on 'Start Manual Step' link successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: 'Start Manual Step' link not clicked.");
  }

  /**
   * <p>
   * Verify all step result is applied.
   * <p>
   * This method can be called after #{@link RQMExecutionPage#applyAllStepResult(String)}.
   *
   * @param verdict to be added in the result.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyApplyAllStepResult(final String verdict, final String lastResult) {
    this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_MESSAGE_TEXT_XPATH,Duration.ofSeconds(60));
    if (this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_MESSAGE_TEXT_XPATH+"/strong").getText()
        .contains(verdict)) {
      return new TestAcceptanceMessage(true,
          "Verified: Message area info of the test case execution record.\nActual Result" +
              " Over all verdict of the TCER is \"" + verdict +
              "\".\nExpected result Over all verdict of the TCER should be \"" + verdict + "\".");
    }
    return new TestAcceptanceMessage(false, "Verified: Proper result not displayed for the execution record.");
  }

  /**
   * <p>
   * Verify 'Test Suite Execution Record' is clicked.
   * <p>
   * This method called after {@link RQMExecutionPage#clickOnTestSuiteExecutionRecord(String)}.
   *
   * @param recordName name of the execution record.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTestSuiteExecutionRecord(final String recordName, final String lastResult) {
    List<WebElement> results = this.driverCustom
        .getWebElements("//div[@dojoattachpoint='titleOutterContainerNode']//span[contains(@dojoattachpoint,'view')]");
    for (WebElement artifact : results) {
      if (recordName.contains(artifact.getText())) {
        return new TestAcceptanceMessage(true,
            "Verified: Created Execution Record is opened from Execution Record.\nActual Result \"" + recordName +
                "\" is opened from Test Suite Execution Record." + "\nExpected Result \"" + recordName +
                "\" should be opened from Test Suite Execution Record.");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Created Execution Record is not opened from Execution Record.");
  }


  @Override
  public TestAcceptanceMessage verifyClickOnDialogButton(final String dialog, final String button,
      final String lastResult) {
    waitForSecs(20);
    if (this.driverCustom.isElementVisible(RQMConstants.RQM_EXECUTION_RECORD_BUTTON_XPATH, Duration.ofSeconds(10), "Pause")) {
      return new TestAcceptanceMessage(true, "Verified: Test Case Execution started for the Test Suite.");
    }
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(20), "Completed")) {
      return new TestAcceptanceMessage(true, "Verified: Test Case Execution started for the Test Suite.");
    }
    if (dialog.equalsIgnoreCase("Confirmation") && button.equalsIgnoreCase("Yes")) {
      this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary']");
      List<WebElement> summary = this.driverCustom.getWebElements("//div[@class='messageSummary']");
      for (WebElement eleme : summary) {
        LOGGER.LOG.info(eleme.getText() + "message summary");
        if (eleme.getText().contains("The test execution")) {
          return new TestAcceptanceMessage(true, "Verified '" +
              "The test execution records associated with this test case were created" + "' successfully.");
        }
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Test Case Execution is not started for the Test Suite.");
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
    if (button.equals("Close")) {
      if (this.driverCustom.isElementVisible("//td[@class='group-field-holder']/a", Duration.ofSeconds(10))) {
        return new TestAcceptanceMessage(true, "Verified: Clicked on 'Close' button successfully.");
      }
    }
    if (button.equals("Stop Run")) {
      List<WebElement> btn = this.driverCustom.getWebElements("//button");
      for (WebElement buttn : btn) {
        // OK change for Yes
        if (buttn.getText().equalsIgnoreCase("OK") || buttn.getText().equalsIgnoreCase("Yes")) {
          return new TestAcceptanceMessage(true,
              RQMConstants.VERIFIED_COLON + button + "' button clicked successfully.");
        }
      }
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + button + "' button is not clicked.");
  }

  /**
   * <p>
   * Verify test case result from Execution Order.
   * <p>
   * This method called after {@link RQMExecutionPage#getTestCaseResultFromExecutionOrder(String)}.
   *
   * @param executionRecordOrder step number.
   * @param additionalParams refers to key value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetTestCaseResultFromExecutionOrder(final String executionRecordOrder,
      final Map<String, String> additionalParams, final String lastResult) {
    StringBuilder sb = new StringBuilder();
    boolean res = false;
    if (additionalParams.get("VerificationType").equalsIgnoreCase("TestCaseExecutionResult")) {
      sb.append("Verified: Test execution result of each test case. \n\n");
      String resVal = this.driverCustom.getWebElement(
          "//table[@summary='This is Test Suite Execution table']/tbody/tr//td[2]/div[@title='DYNAMIC_VAlUE']/ancestor::tr/td[11]/span/img",
          executionRecordOrder).getAttribute("title");
      res = resVal.equals(additionalParams.get("StepResult"));
      sb.append("Test Case \"" + executionRecordOrder + "\" result is \"" + resVal + "\".\n");
      return new TestAcceptanceMessage(res, sb.toString());

    }
    return new TestAcceptanceMessage(false, "Verified: Proper result not displayed for the execution.");
  }

  /**
   * <p>
   * Verifies the action of {@link RQMExecutionPage#getNotExecutedTestCase(String)}.
   *
   * @param testCaseName name of the test case.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetNotExecutedTestCase(final String testCaseName, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Test Case is not executed once the test case execution result is not Pass.\n" +
              "Actual Result \"" + testCaseName + "\" is not executed.\nExpected Result \"" + testCaseName +
              "\" should not executed.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Test Case is executed once the test case execution result is not Pass. ");
  }

  /**
   * <p>
   * Verify check point is selected.
   * <p>
   * This method called after {@link RQMExecutionPage#chooseExecutionCheckPoint(String)}.
   *
   * @param option to be select while execution.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseExecutionCheckPoint(final String option, final String lastResult) {
    if (!this.driverCustom.isElementVisible(
        "//span[text()='DYNAMIC_VAlUE']/../preceding-sibling::input[contains(@dojoattachevent,'onclick')]", Duration.ofSeconds(10),
        option)) {
      return new TestAcceptanceMessage(true,
          "Verified: Required option is clicked while running 'Test Suite'.\nActual Result \"" + option + "\"" +
              " is selected.\nExpected Result \"" + option + "\" should be selected.");
    }
    return new TestAcceptanceMessage(false, "Verified: Required option is not selected.");
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link RQMExecutionPage#getTestCasesNotRunResult()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyGetTestCasesNotRunResult(final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: System has executed all the test case added in the test suite.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: System has not executed all the test case added in the test suite.");
  }

  /**
   * <p>
   * Verify cell button is clicked.
   * <p>
   * This method called after {@link RQMExecutionPage#clickOnCellButton(String)}.
   *
   * @param button name of the button.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnCellButton(final String button, final String lastResult) {
    this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTTYPEVALUE_CHECKBOX_XPATH, Duration.ofSeconds(10), button);
    if (!this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTTYPEVALUE_CHECKBOX_XPATH, Duration.ofSeconds(10), button)) {
      return new TestAcceptanceMessage(true,
          "Verified '" + button + "' button clicked sucessfully." + "\nActual result is '" + button + "' is clicked sucessfully.");
    }
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
        "Run Test Suite");
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, Duration.ofSeconds(10),
        "Run Test Suite")) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on '" + button + "' button successfully.'Run Test Suite' dialog displayed.");
    }

    return new TestAcceptanceMessage(false, "Verified: '" + button + "' button is not clicked.");
  }

  /**
   * <p>
   * Verify execution check point is selected or not.
   * <p>
   * This method can be called after {@link RQMExecutionPage#isDefectVisibleInDefectsSection(String)}.
   *
   * @param defectID - ID of defect to be check.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsDefectVisibleInDefectsSection(final String defectID, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Defect ID: \"" + defectID + "\" added to Defects Section");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Defect ID: \"" + defectID + "\" is not displayed in Defects Section");
  }

  /**
   * <p>
   * Verify execution check point is selected or not.
   * <p>
   * This method can be called after {@link RQMExecutionPage#isExecutionCheckPointSelected(String)}.
   *
   * @param option to be select.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsExecutionCheckPointSelected(final String option, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Provided option is not selected.\n Actual Result \"" + option +
          "\" is not selected." + "\nExpected Result \"" + option + "\" should not be selected.");
    }
    return new TestAcceptanceMessage(false, "Verified: Provided option is selected.");
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link RQMExecutionPage#clickOnTab(String)}.
   *
   * @param tab name of tab.
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTab(final String tab, final String lastResult) {
    List<WebElement> tabs = this.driverCustom.getWebElements("//span[@role='tab']/a");
    for (WebElement tabname : tabs) {
      if (tabname.getText().equalsIgnoreCase(tab) && tabname.getAttribute("class").contains("selected")) {
        return new TestAcceptanceMessage(true, RQMConstants.VERIFIED_COLON + tab + RQMConstants.CLICKED_SUCESSFULLY);
      }
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + tab + RQMConstants.NOT_CLICKED_SUCESSFULLY);
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after
   * {@link RQMConstructionPage#chooseDropdownAndSetValueInExecutionRecordDialog(String, String, String)}.
   *
   * @param dialogTitle title
   * @param selectName name
   * @param selectValue value.
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseDropdownAndSetValueInExecutionRecordDialog(final String dialogTitle,
      final String selectName, final String selectValue, final String lastResult) {
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTSUITERUN_DROPDOWN_XPATH, selectName);
    WebElement ele = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_DROPDOWN_XPATH, selectName);
    Select sel = new Select(ele);
    if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(selectValue)) {
      return new TestAcceptanceMessage(true, RQMConstants.VERIFIED_COLON + selectValue + "' is set successfully for '"+selectName+"'.");
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + selectValue + "' not clicked successfully.");
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link RQMExecutionPage#openRQMSection(String)}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  @Override
  public TestAcceptanceMessage verifyOpenRQMSection(final String Sections, final String lastResult) {
    List<WebElement> sections = this.driverCustom.getWebElements(RQMConstants.RQM_SECTION_TITLE_XPATH, Sections);
    for (WebElement section : sections) {
      if (section.getAttribute("aria-pressed").equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, RQMConstants.VERIFIED_COLON + Sections + "' clicked successfully.");
      }
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + Sections + "' not clicked successfully.");
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link RQMExecutionPage#getTestCaseExecutionRecordsID()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyGetTestCaseExecutionRecordsID(final String lastResult) {
    List<WebElement> ele = this.driverCustom
        .getWebElements("//table[@summary='This is Test Case Execution Records table']//tbody//tr[1]//td");
    for (WebElement elemen : ele) {
      if (elemen.getText().equalsIgnoreCase(lastResult)) {
        return new TestAcceptanceMessage(true, "Verified the execution record '" + lastResult + "' successfully.");
      }
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + lastResult + "' not found.");
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link RQMExecutionPage#getTestCaseExecutionRecordsID()}.
   *
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnLastResult(final String lastResult) {
    waitForSecs(Duration.ofSeconds(5));
    if (!this.driverCustom.isElementVisible("//span[text()='Last Result: ']/../..//a[@aria-label='Last Result']", Duration.ofSeconds(30))) {
      return new TestAcceptanceMessage(true, "Verified: Test case result opened successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Test case result not opened.");
  }

  /**
   * <p>
   * Verify test cases not run result.
   * <p>
   * This method called after {@link RQMExecutionPage#getTestCaseExecutionRecordsID()}.
   *
   * @param additionalparameter data for verification.
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyGetExecutionRecordMessageSummary(final String additionalparameter,
      final String lastResult) {
    List<WebElement> ele = this.driverCustom.getWebElements("//div[@role='status']//div[@class='messageSummary']");
    for (WebElement elemen : ele) {
      if (elemen.getText().contains(additionalparameter)) {
        return new TestAcceptanceMessage(true,
            RQMConstants.VERIFIED_COLON + additionalparameter + "' updated successfully.");
      }
    }
    return new TestAcceptanceMessage(false, RQMConstants.VERIFIED_COLON + additionalparameter + "' not updated.");
  }

  /**
   * <p>
   * Verify the existing defect is added to test case result.
   * <p>
   * Verifies the action of {@link RQMExecutionPage#linkExistingDefectFromTestResult(String, String, String)}.
   *
   * @param ccmProjectAreaName the name of RTC project area linked to RQM project area
   * @param existingDefectID the id of existing defect
   * @param existingDefectName the name of exisiting defect
   * @param lastResult get the last result of
   *          {@link RQMExecutionPage#linkExistingDefectFromTestResult(String, String, String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifyLinkExistingDefectFromTestResult(final String ccmProjectAreaName,
      final String existingDefectID, final String existingDefectName, final String lastResult) {
    try {
      this.engine.findElementWithDuration(Criteria.isRow().withText(existingDefectName), this.timeInSecs)
          .getFirstElement();
      return new TestAcceptanceMessage(true,
          "Verified: The existing defect '" + existingDefectName + "' is added successfully.\n" +
              "Actual Result is the existing row '" + existingDefectName + "' showing on the screen");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified: The existing defect '" + existingDefectName + "' is not added successfully.\n" +
              "So Exception is thrown out when finding the existing defect row.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Verify the existing defect is removed to test case result.
   * <p>
   * Verifies the action of {@link RQMExecutionPage#removeLinkedDefect(String, String)}.
   *
   * @param defectID the Id of the defect
   * @param defectName the name/summary of the defect
   * @param lastResult get the last result of {@link RQMExecutionPage#removeLinkedDefect(String, String)}
   * @return validation message
   */
  public TestAcceptanceMessage verifyRemoveLinkedDefect(final String defectID, final String defectName,
      final String lastResult) {
    try {
      this.engine.findElement(Criteria.isRow().containsText(defectName)).getFirstElement();
      return new TestAcceptanceMessage(false,
          "Verified the existing defect '" + defectName + "' is not removed from Test Result.\n" +
              "Actual result is the existing row '" + defectName + "' is showing on the screen");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(true,
          "Verified the existing defect '" + defectName + "' is removed from Test Result successfully.\n" +
              "Actual result is the existing row '" + defectName + "' is Not showing on the screen");
    }
  }

  /**
   * <p>
   * Verify a new defect is created from the test result
   * <p>
   * Verifies the action of {@link RQMExecutionPage#createANewDefectFromTheTestResult(String, String, String)}.
   *
   * @param projectArea_CCM ccm project area name.
   * @param defectName name of the defect.
   * @param newDefectFiledAgainst filed against of the defect.
   * @param lastResult returned value of method which is executed just before the method.
   * @return which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateANewDefectFromTheTestResult(final String projectArea_CCM,
      final String defectName, final String newDefectFiledAgainst, final String lastResult) {
    List<WebElement> listDefect =
        this.driverCustom.getWebElements("//table[@summary='This is Defects table']//a[@class='jazz-ui-ResourceLink']");
    WebElement defect = listDefect.get(listDefect.size() - 1);
    String defectContent = defect.getText();
    if (defectContent.contains(defectName)) {
      return new TestAcceptanceMessage(true,
          "Verified a new defect '" + defectName + "' is created from the Test Result.\n" +
              "Actual result is the new defect '" + defectName + "' is showing on the screen");
    }
    return new TestAcceptanceMessage(false,
        "Verified a new defect '" + defectName + "' is created from the Test Result.\n" +
            "Actual result is the new defect '" + defectName + "' is not showing on the screen");
  }

  /**
   * This method is called after get selected value in dropdown using method  {@link RQMExecutionPage#getDropdownValue(String)}
   * and then compare with expected value
   * @param label - field label
   * @param sectionName - section name like Test suite execution record, Test Case Execution Record,..
   * @param expectedValue - expected value which is compared with last result
   * @param lastResult - return result from last execution
   * @return verification message
   */
  public TestAcceptanceMessage verifySelectedValue(final String label,final String sectionName, final String expectedValue, final String lastResult) {
    if (lastResult.equalsIgnoreCase(expectedValue)) {
      return new TestAcceptanceMessage(true, "Actual result in '"+sectionName+"' is '"+ lastResult+"'. \nMatching with expected result '"+expectedValue+"'.");
    }
    return new TestAcceptanceMessage(false, "Actual result in '"+sectionName+"' is '"+ lastResult+"'. \nDon't match with expected result '"+expectedValue+"'.");
  }
  /**
   * This method is called after executing method {@link RQMExecutionPage#clickOnTestArtifactFromExecutionResult(String)}
   * @param label - label name
   * @param lastResult - return result from last execution
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnTestArtifactFromExecutionResult(final String label, final String lastResult) {
    refresh();
    boolean breadcrumbLInk = this.driverCustom.isElementVisible(RQMConstants.RQM_BREADCRUMB_LINKTEXT, timeInSecs, label);
    if(breadcrumbLInk) {
      return new TestAcceptanceMessage(true, "Link with label '"+label+"' is clicked on successfully.");
    }
    return new TestAcceptanceMessage(false, "Link with label '"+label+"' is not clicked on successfully.");
  }
  /**
   * This method is called after executing method {@link RQMExecutionPage#checkLastResultTestExecutionRecord(String, String, String, String)}
   * @param resultType - expected result of test execution record
   * @param testSuiteName - name of test suite/test case which selected to execute
   * @param envName - name of environment which is selected to execute
   * @param iteration - name of iteration which selected to execute
   * @param testExecutionRecordName - name of test execution section like Test suite execution record, test case execution record,...
   * @param lastResult - return result from last execution
   * @return verification message
   */
  public TestAcceptanceMessage verifyCheckLastResultTestExecutionRecord(final String resultType, final String testSuiteName, final String envName,final String iteration,final String testExecutionRecordName, final String lastResult) {
    if(lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Last result of "+testExecutionRecordName+":'"+testSuiteName+"' is '"+resultType );
    }
    return new TestAcceptanceMessage(false, "Last result of "+testExecutionRecordName+":'"+testSuiteName+"' not match with expected result '"+resultType);
  }
  
  /**
   * This method is called after executing method {@link RQMExecutionPage#setDefaultQuery(Map)}
   * @param additionalParams list of keys like 'option','queryOption','folderName', 'buttonName',...
   * @param lastResult returned value of method which is executed just before the method.
   * @return verification message
   * @author LTU7HC
   */
  public TestAcceptanceMessage verifySetDefaultQuery(final Map<String, String> additionalParams, final String lastResult) {
    if(lastResult.equalsIgnoreCase("The default query was updated successfully.")) {
      return new TestAcceptanceMessage(true, String.format("'Set Default Query' with option: '%s' successfully.", additionalParams.get("option")));
    }
    return new TestAcceptanceMessage(false, String.format("Can not 'Set Default Query' with option: '%s'.", additionalParams.get("option")));
  }
}