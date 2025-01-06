/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.ColumnFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LabelFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextEditorFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * This Page represents RQM Execution Page.
 */
public class RQMExecutionPage extends AbstractRQMPage {

  /**
   * @param driverCustom driver.
   */
  public RQMExecutionPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new TextFieldFinder(), new JazzTextFieldFinder(),
        new JazzTextEditorFinder(), new JazzDropdownFinder(), new DropdownFinder(),
        new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(), new ColumnFinder(), new LinkFinder(),
        new JazzRadioButtonFinder(), new CheckboxFinder(), new LabelFinder(), new JazzLabelFinder());
  }

  /**
   * <p>
   * getTestCaseExecutionResultStatus returns the execution record status if the execution record created successfully.
   *
   * @return Execution result status.
   */
  public String getTestCaseExecutionResultStatus() {
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_ACTUALRESULT_DROPDOWN);
    }
    catch (Exception e) {
      waitForSecs(10);
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_ACTUALRESULT_DROPDOWN);
    }
    waitForSecs(10);
    try {
      if (this.driverCustom.isElementVisible("//a[@title='Minimize Steps Display']", Duration.ofSeconds(120))) {
        this.driverCustom.getPresenceOfWebElement("//a[@title='Minimize Steps Display']").click();
      }
    }
    catch (Exception e) {
      LOGGER.LOG.info("Summary report is in Maximize state.");
    }
    return this.driverCustom.getWebElement(RQMConstants.RQMEXECUTIONPAGE_ACTUALRESULT_DROPDOWN).getText();
  }

  /**
   * @param stepNumber step number.
   * @return step status.
   */
  public String getTestExecutonRecordStepResult(final String stepNumber) {
    String step = String.valueOf(stepNumber);
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH, step);
    }
    catch (Exception e) {
      driverCustom.executeJavaScript("window.scrollBy(0,1000)");
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH, step);
    }
    LOGGER.LOG.info(this.driverCustom.getWebElement(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH, step)
        .getAttribute(RQMConstants.TITLE));
    return this.driverCustom.getWebElement(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH, step)
        .getAttribute(RQMConstants.TITLE);
  }

  /**
   * @return step status.
   */
  public String getTestExecutonRecordStepResults() {
    int step = 3;
    List<String> verdict = new ArrayList<>();
    driverCustom.executeJavaScript("window.scrollBy(0,1000)");
    waitForSecs(2);
    while (this.driverCustom.isElementVisible(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH,
        this.timeInSecs, String.valueOf(step))) {
      try {
        String stepNumber = String.valueOf(step);
        this.driverCustom.isElementVisible(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH,
            this.timeInSecs, stepNumber);
        driverCustom.executeJavaScript("window.scrollBy(0,1000)");
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH,
            stepNumber);
        verdict.add(
            this.driverCustom.getWebElement(RQMConstants.RQMEXECUTIONPAGE_TESTEXECUTIONRESULT_TITLE_XPATH, stepNumber)
            .getAttribute("title"));
        step++;
      }
      catch (Exception e) {
        break;
      }
    }
    return String.join(",", verdict);
  }

  /**
   * <p>
   * Run the Test Suite.
   * <p>
   * Added test case in the suite will start executing.
   * <p>
   * Click on 'Start Manual Step' link to run the test suite.
   */
  public void clickOnStartManualStep() {
    waitForSecs(10);
    int count = 5;
    while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, this.timeInSecs,
        RQMConstants.START_MANUAL_TEST)) {
      try {
        waitForSecs(5);
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH,
            "Start Manual Test");
        this.driverCustom.click(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, "Start Manual Test");
        LOGGER.LOG.info("Clicked on 'Start Manual Step' link.");
      }
      catch (Exception e) {
        LOGGER.LOG.info("Could not able to click,Exception occured.count=" + count);
      }
      count--;
      if (count == 0) {
        break;
      }
    }
  }

  /**
   *
   */
  public void getExecutionRecordMessageSummary() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_STATUS_MESSAGE_TEXT_XPATH);
  }

  /**
   *
   */
  public void clickOnAllPassButton() {
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds((this.timeInSecs.getSeconds() / 10)));
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)),
        RQMConstants.PASS_CTRL_SHIFT_G)) {
      while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 10)),
          RQMConstants.PASS_CTRL_SHIFT_G)) {
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH,
            RQMConstants.PASS_CTRL_SHIFT_G);
        this.driverCustom
        .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, RQMConstants.PASS_CTRL_SHIFT_G)
        .click();
        this.driverCustom.waitForSecs(Duration.ofSeconds(2));
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds((this.timeInSecs.getSeconds() / 10)));
      
    }
    }else {
      while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 10)),
          RQMConstants.PASS_CTRL_SHIFT_P)) {
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH,
            RQMConstants.PASS_CTRL_SHIFT_P);
        this.driverCustom
        .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, RQMConstants.PASS_CTRL_SHIFT_P)
        .click();
        this.driverCustom.waitForSecs(Duration.ofSeconds(2));
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds((this.timeInSecs.getSeconds() / 10)));
      }
    }
  }
  /**
   * <p>
   * Open any Test Suite,Test Case,TSER,TCER etc.
   * <p>
   * Click on 'Run Test Suite' or 'Run Test Case' button.
   * <p>
   * Click on cell button like 'Run','Run Offline' etc displayed after clicking 'Run Test Suite' or 'Run Test Case'
   * button.
   *
   * @param button name of button.
   */
  public void clickOnCellButton(final String button) {
    Cell cel = this.engine.findElement(Criteria.isCell().withText(button)).getFirstElement();
    cel.click();
    waitForSecs(5);
    LOGGER.LOG.info("Clicked on '" + button + "'.");
  }

  /**
   * <p>
   * Click on 'Start Manual Step' link.
   * <p>
   * Click on 'Apply All' button, 'Apply all confirmation' dialog will display.
   * <p>
   * Choose required verdict like 'Pass','Fail','Error' etc.. and click on 'OK' button.
   *
   * @param verdict to be set as 'Pass','Fail,'Error' etc.
   */
  public void applyAllStepResult(final String verdict) {
    waitForSecs(5);
    if (this.driverCustom.isElementVisible(RQMConstants.RQMEXECUTION_START_LINK_XPATH, this.timeInSecs)) {
      this.driverCustom.getWebElement(RQMConstants.RQMEXECUTION_START_LINK_XPATH).click();
      LOGGER.LOG.info("Clicked on 'Start' link.");
    }
    Button btnApplyAll = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Apply All"), timeInSecs).getFirstElement();
    btnApplyAll.click();
    LOGGER.LOG.info("Clicked on 'Apply All' button.");
    Dialog dlgApplyAll =
        this.engine.findElement(Criteria.isDialog().withTitle("Apply all confirmation")).getFirstElement();
    Dropdown dnd =
        this.engine.findElement(Criteria.isDropdown().withLabel("Verdict:").inContainer(dlgApplyAll)).getFirstElement();
    dnd.selectOptionWithText(verdict);
    LOGGER.LOG.info("Selected '" + verdict + "' from 'Apply all confirmation' dialog.");
    Button btnOk =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgApplyAll)).getFirstElement();
    btnOk.click();
    LOGGER.LOG.info("Clicked on 'OK' button from 'Apply all confirmation' dialog.");
    waitForSecs(2);

  }

  /**
   * <p>
   * Get the test case execution result from the test suite execution result.
   * <p>
   * Verify the result of the each test case execution result.
   *
   * @param executionRecordOrder executed test case number.
   * @return true if test case result displayed as per Execution Order.
   */
  public String getTestCaseResultFromExecutionOrder(final String executionRecordOrder) {
    LOGGER.LOG.info("Verified the result of the test case '" + executionRecordOrder + "'.");
    return this.driverCustom.getWebElement(
        "//table[@summary='This is Test Suite Execution table']/tbody/tr//td[2]/div[@title='DYNAMIC_VAlUE']/ancestor::tr/td[11]/span/img",
        executionRecordOrder).getAttribute("title");
  }

  /**
   * <p>
   * Get test suite execution Result Status.
   * <p>
   * Get value of test suite execution Result Status.
   *
   * @return The value of test suite execution.
   */
  public String getTestSuiteExecutionResultStatus() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH).click();
    Select sel = new Select(this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH));
    LOGGER.LOG.info("Test suite execution result status is: " + sel.getFirstSelectedOption().getText());
    return sel.getFirstSelectedOption().getText();
  }

  /**
   * <p>
   * Run the test suite.
   * <p>
   * Choose option like 'Stop suite execution if any test case does not pass','Schedule execution:' etc..
   *
   * @param option to be provided for check point.
   */
  public void chooseExecutionCheckPoint(final String option) {
    Checkbox cb = this.engine.findElement(Criteria.isCheckbox().withLabel(option)).getFirstElement();
    cb.click();
    LOGGER.LOG.info("Select option: " + option);
  }

  /**
   * <p>
   * Get the test case execution result from the test suite execution result.
   * <p>
   * Verify the test case which is not executed.
   *
   * @param testCaseName name of the test case.
   * @return true if test case not available.
   */
  public boolean getNotExecutedTestCase(final String testCaseName) {
    LOGGER.LOG.info("Verified the test case which is not executed - " + testCaseName);
    return !this.driverCustom.isElementVisible(
        "//table[@summary='This is Test Suite Execution table']/tbody/tr//td[12]/div[@class='table-cell-resize-marker']//div[text()='DYNAMIC_VAlUE']",
        this.timeInSecs, testCaseName);
  }

  /**
   * <p>
   * Create a 'Test Suite Execution Record' .
   * <p>
   * Click on the created test suite execution record.
   *
   * @param recordName name of the Execution record.
   */
  public void clickOnTestSuiteExecutionRecord(final String recordName) {
    waitForSecs(2);
    Text txtSearchField ;
    try {

      txtSearchField=   this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Type filter text and press Enter"));
    }
    catch (Exception e) {
      txtSearchField =
          this.engine.findFirstElement(Criteria.isTextField().withAriaLabel("This is Test Suite Execution Record table: filter text input"));
    }

    txtSearchField.setText(recordName + Keys.ENTER);
    LOGGER.LOG.info("Searched '" + recordName + "' from 'Type filter' text box.");
    this.driverCustom.getWebElement(RQMConstants.RMARTIFACTSPAGE_SELECT_ARTIFACT_FORMAT_VALUE_XPATH, recordName)
    .click();
    waitForSecs(5);
    LOGGER.LOG.info("Clicked on '" + recordName + "'.");
  }

  /**
   * <p>
   * After running all the test case from the test suite.
   * <p>
   * Verify the Test Cases Not Run Result.
   *
   * @return true if test case not run value is 0.
   */
  public boolean getTestCasesNotRunResult() {
    LOGGER.LOG.info("Verified Test Cases Not Run Result.");
    return this.driverCustom.isElementVisible(RQMConstants.RQMEXECUTIONPAGE_TEST_CASE_NOT_RUN_XPATH, this.timeInSecs);
  }

  /**
   * <p>
   * Click on 'Run' button to run the Test Suite. <br>
   * Verify provided option is not selected.
   *
   * @param option check point options available in 'Run Test Suite' dialog.
   * @return true if option is not selected
   */
  public boolean isExecutionCheckPointSelected(final String option) {
    return !this.driverCustom.isElementVisible(
        "//span[text()='DYNAMIC_VAlUE']/../preceding-sibling::input[contains(@dojoattachevent,'onclick')]",
        this.timeInSecs, option);
  }

  /**
   * @param defectID id of the defect.
   * @return defectID.
   */
  public boolean isDefectVisibleInDefectsSection(final String defectID) {
    return this.driverCustom.isElementVisible("//tr[contains(@name,'defect') and contains(@name,'DYNAMIC_VAlUE-row')]",
        Duration.ofSeconds(10), defectID);
  }

  /**
   * @return Execution Records ID.
   */
  public String getTestCaseExecutionRecordsID() {
    clickOnJazzImageButtons("Refresh");
    waitForSecs(5);
    String xpathTCERTable = "//table[@summary='This is Test Case Execution Records table']/tbody/tr/td[3]/a";
    
    return this.driverCustom.getWebElements(xpathTCERTable).get(0).getText();
  }

  /**
   *
   */
  public void clickOnLastResult() {
    refresh();
    WebElement lastResult =
        this.driverCustom.getPresenceOfWebElement("//span[text()='Last Result: ']/../..//a[@aria-label='Last Result']");
    lastResult.click();
  }

  /**
   * linkToExistingDefect: link an existing defect to a test case result
   *
   * @param ccmProjectAreaName the name of RTC project area linked to RQM project area
   * @param existingDefectID the id of existing defect
   * @param existingDefectName the name of exisiting defect
   */
  public void linkExistingDefectFromTestResult(final String ccmProjectAreaName, final String existingDefectID,
      final String existingDefectName) {

    Button btnLinkExsitingDefect =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Link to Existing Defect"), this.timeInSecs)
        .getFirstElement();
    btnLinkExsitingDefect.click();
    waitForSecs(3);

    try {
      Dropdown drdContainer = this.engine.findElementWithDuration(
          Criteria.isDropdown().withLabel(
              "More than one artifact container is configured, please select the one you want to connect with"),
          this.timeInSecs).getFirstElement();
      drdContainer.selectOptionWithPartText(ccmProjectAreaName);

      Button btnOK =
          this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
      btnOK.click();
    }
    catch (Exception e1) {
      waitForSecs(2);
    }

    Dialog dlgSelectWorkItem =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Link to Existing Defect"), this.timeInSecs)
        .getFirstElement();

    // Search and select plan link
    Text txtSearchPlanLink = this.engine.findElementWithDuration(Criteria.isTextField()
        .hasLabel("Work Item Number or Words Contained in the Text. Use quotes for a phrase search:")
        .inContainer(dlgSelectWorkItem), this.timeInSecs).getFirstElement();
    txtSearchPlanLink.setText(existingDefectID);
    waitForSecs(2);

    // Check if GC project area
    try {
      Checkbox cbxGlobalConfiguration = this.engine
          .findElement(
              Criteria.isCheckbox().withLabel("Show only Work Items related to the current Global Configuration"))
          .getFirstElement();
      cbxGlobalConfiguration.click();
      waitForSecs(2);
    }
    catch (Exception e) {
      waitForSecs(2);
    }

    // Select Plan link
    Dropdown drdMatchingWorkItem =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Matching Work Items:"), this.timeInSecs)
        .getFirstElement();
    drdMatchingWorkItem.selectOptionWithPartText(existingDefectName);

    // Click on OK button
    Button btnOKSelect =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOKSelect.click();
    this.driverCustom.getWebDriver().switchTo().defaultContent();
  }

  /**
   * removeLinkedDefect: removed the defect linked to test case result
   *
   * @param defectID the Id of the defect
   * @param defectName the name/summary of the defect
   */
  public void removeLinkedDefect(final String defectID, final String defectName) {

    Row rowCollectionLink =
        this.engine.findElementWithDuration(Criteria.isRow().withText(defectID + ": " + defectName), this.timeInSecs)
        .getFirstElement();
    Cell cllCheckbox =
        this.engine.findElement(Criteria.isCell().inContainer(rowCollectionLink).withIndex(1)).getFirstElement();
    Checkbox cbxCollection = this.engine.findElement(Criteria.isCheckbox().inContainer(cllCheckbox)).getFirstElement();
    cbxCollection.scrollToElement();
    cbxCollection.click();

    // Click remove links button
    Button btnRemoveLinks =
        this.engine.findElement(Criteria.isButton().withToolTip("Remove Defects")).getFirstElement();
    btnRemoveLinks.click();

    // Click Remove button
    Button btnRemove =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Remove"), this.timeInSecs).getFirstElement();
    btnRemove.click();
  }

  /**
   * @param projectArea_CCM ccm project area name.
   * @param newDefectName defect name to be added to create a new defect.
   * @param newDefectFiledAgainst defect filed against to be added.
   * @return defect content.
   */
  public String createANewDefectFromTheTestResult(final String projectArea_CCM, final String newDefectName,
      final String newDefectFiledAgainst) {
    waitForSecs(2);
    Button createNewDefect =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Create New Defect"), this.timeInSecs)
        .getFirstElement();
    createNewDefect.click();
    waitForSecs(2);
    try {
      this.driverCustom.getPresenceOfWebElement("//select[@id='selectServiceProvider']").click();
      waitForSecs(2);
      this.driverCustom.getPresenceOfWebElement(
          "//select[@id='selectServiceProvider']/option[contains(text(),'" + projectArea_CCM + "')]").click();
      this.driverCustom.getPresenceOfWebElement("//button[text()='OK']").click();
    }
    catch (Exception e) {}

    Text summaryField =
        this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Summary"), Duration.ofSeconds(30)).getFirstElement();
    summaryField.setText(newDefectName);
    Dropdown filedAgainstDropdown =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Filed Against"), Duration.ofSeconds(30)).getFirstElement();
    filedAgainstDropdown.selectOptionWithText(newDefectFiledAgainst);

    // Click on OK button
    JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
    js.executeScript("arguments[0].scrollIntoView();",
        this.driverCustom.getPresenceOfWebElement("//button[text()='OK']"));
    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK.click();
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    waitForSecs(8);
    List<WebElement> listDefect =
        this.driverCustom.getWebElements("//table[@summary='This is Defects table']//a[@class='jazz-ui-ResourceLink']");
    WebElement defect = listDefect.get(listDefect.size() - 1);
    String defectContent = defect.getText();
    return defectContent.split(":")[0];
  }

  
  /**
   * Get selected value in dropdown 
   * @author NCY3HC
   * @param label - label of dropdown
   * @return selected value in dropdown
   */
  public String getDropdownValue (final String label) {
    Dropdown drd = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(label), timeInSecs).getFirstElement();
    return drd.getDefaultValue();
  }

  /**
   * Click on link with label name (like Click on Test Suite Execution Record, test Plans,...) 
   * @author NCY3HC
   * @param label - label name
   */
  public void clickOnTestArtifactFromExecutionResult(final String label) {
      WebElement link = this.driverCustom.getPresenceOfWebElement(RQMConstants.RQM_LINK_WITH_LABEL, label);
      link.click();
      waitForSecs(2);
  }
  /**
   * Verify 'Last Result' in Test Suite Execution Records/Test Case Execution Records section
   * @author NCY3HC
   * @param resultType - expected result of test execution record
   * @param testSuiteName - name of test suite/test case which selected to execute
   * @param iteration - name of iteration which selected to execute
   * @param envName - name of environment which is selected to execute
   * @return result
   */
  public boolean checkLastResultTestExecutionRecord(final String resultType, final String testSuiteName, final String envName, final String iteration ) {
    String[] tserName= {testSuiteName,envName,iteration};
    switch(resultType) {
      case "Passed":
        return this.driverCustom.isElementVisible(RQMConstants.RQM_LASTRESULT_TESTEXECUTIONRECORD_PASSED, timeInSecs, tserName);
      case "Error":
        return this.driverCustom.isElementVisible(RQMConstants.RQM_LASTRESULT_TESTEXECUTIONRECORD_ERROR, timeInSecs, tserName);
      case "Failed":
        return this.driverCustom.isElementVisible(RQMConstants.RQM_LASTRESULT_TESTEXECUTIONRECORD_FAIL, timeInSecs, tserName);
      case "Inconclusive":
        return this.driverCustom.isElementVisible(RQMConstants.RQM_LASTRESULT_TESTEXECUTIONRECORD_INCONCLUSIVE, timeInSecs, tserName);
        
    }
    return false;
  }
  
  /**
   * <p>
   * This method is used to 'Set Default Quert'
   * <p>
   * @param additionalParams list of keys like 'option','queryOption','folderName', 'buttonName',...
   * @return message if select option successfully and blank string if select unsuccessfully.
   * @author LTU7HC
   */
  public String setDefaultQuery(final Map<String, String> additionalParams) {
    String option = additionalParams.get("option");
    Dropdown drpSetDefaultQuery = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Set Default Query"), Duration.ofSeconds(60)).getFirstElement();
    String msgSaveSuccessfullyXpath = "(//div[@class='messageArea OK' and @role='alert'])[1]";
    String msgSaveSuccessfully = "";
    boolean isSaveSuccessfully = false;
    
    switch (option.trim()) {
      case "User Current Query":
        LOGGER.LOG.error("This method does not support for this option yet.");
        break;
      case "No Default Query":
        drpSetDefaultQuery.selectOptionWithText(option);
        // Do nothing for this option, just check the message if click on it successfully.
        break;
      case "Select...":
        drpSetDefaultQuery.selectOptionWithText(option);
        String queryOption = additionalParams.get("queryOption");
        String folderName = additionalParams.get("folderName");
        // Wait for pop up displays
        this.driverCustom.isElementVisible("//div[@class='jazz-ui-Dialog-content jazz-ui-Dialog-content-padding-full']", Duration.ofSeconds(30));
        //Click on expand button
        String expandButton = "//div[contains(@class,'jazz-ui-Dialog-content')]//div[@title='%s']/img[contains(@class, 'dijitTreeExpandoClosed')]";
        String expandButtonXpath = String.format(expandButton, queryOption);
        if(this.driverCustom.isElementVisible(expandButtonXpath, Duration.ofSeconds(15))) {
          this.driverCustom.getWebDriver().findElement(By.xpath(expandButtonXpath)).click();
        }
        // Click expand button on folder
        if(!folderName.equals("")) {
          expandButtonXpath = String.format(expandButton, folderName);
          if(this.driverCustom.isElementVisible(expandButtonXpath, Duration.ofSeconds(5))) {
            this.driverCustom.getWebDriver().findElement(By.xpath(expandButtonXpath)).click();
          }
        }
        // Select default query for list view and click on 'OK' OR 'CANCEL' button
        String defaultQueryForListView = String.format("//div[text()='%s']", additionalParams.get("defaultQueryForListView"));
        this.driverCustom.isElementVisible(defaultQueryForListView, Duration.ofSeconds(15));
        this.driverCustom.getWebDriver().findElement(By.xpath(defaultQueryForListView)).click();
        break;
      case "User Project Default":
        LOGGER.LOG.error("This method does not support for this option yet.");
        break;

      default:
        throw new WebAutomationException(String.format("Option with given value as '%s' not found.", option));
    }
    // Click on button
    clickOnJazzSpanButtons(additionalParams.get("buttonName"));
    
    // Message successfully displays
    isSaveSuccessfully = this.driverCustom.isElementVisible(msgSaveSuccessfullyXpath, Duration.ofSeconds(20));
    if(isSaveSuccessfully) {
      msgSaveSuccessfully = "The default query was updated successfully.";
      this.driverCustom.isElementInvisible("(//span[text()='Loading...'])[last()]", Duration.ofSeconds(10));
    }
    return msgSaveSuccessfully;
  }
}
