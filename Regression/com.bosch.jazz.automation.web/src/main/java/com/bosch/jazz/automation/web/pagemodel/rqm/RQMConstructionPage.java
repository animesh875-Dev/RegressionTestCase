/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMDashBoardPage;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.IElement;
import com.bosch.psec.web.test.element.RadioButton;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextEditor;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.ColumnFinder;
import com.bosch.psec.web.test.finder.container.DialogFinder;
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
 * Represents Rational quality management construction page.
 */
public class RQMConstructionPage extends AbstractRQMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RQMConstructionPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new TextFieldFinder(), new JazzTextFieldFinder(),
        new JazzTextEditorFinder(), new JazzDropdownFinder(), new DropdownFinder(),
        new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(), new ColumnFinder(), new LinkFinder(),
        new JazzRadioButtonFinder(), new CheckboxFinder(), new LabelFinder(), new JazzLabelFinder(),
        new DialogFinder());
  }

  /**
   * <p>
   * Rational quality manager has test suites, test cases, test scripts , test environments. <br>
   * After opening test suites, test cases, test scripts and test environments.
   * {@link AbstractJazzWebPage#openMainMenuByMenuTitle(String)},{@link AbstractJazzWebPage#openRQMSection(String)}
   * {@link AbstractJazzWebPage#openSubMenuUnderSection(String, String)} <br>
   * Left side top there will be unique id associated to it.
   *
   * @return id of the rational quality management artifact's.
   */
  public String getRqmArtifactID() {
    waitForPageLoaded();
    waitForSecs(30);
    refresh();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASE_ID_XPATH);
    LOGGER.LOG.info("Returns Test case, test script or test plan ID");
    return this.driverCustom.getText(RQMConstants.RQMPROJECT_TESTCASE_ID_XPATH).substring(0,
        this.driverCustom.getText(RQMConstants.RQMPROJECT_TESTCASE_ID_XPATH).length() - 1);
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}. <br>
   * Open {@link RQMSectionMenus#TEST_CASE_EXECUTION_RECORDS} from {@link #openRQMSection(String)} present under
   * sections tab on left side. <brClicks on "Generate New Execution Records" button present on the top of table at
   * right side opens "Generate Test Case Execution Records" dialog, <br>
   * Click on "Generate Test Environments" link and choose the environment and click on next page, select the required
   * check box and click next, then click on "Finish and Save" button.
   *
   * @param additionalParams stores keys "testEnvironmentNmae", "Generate New Execution Records", "Finish and Save",
   *          "searchTestEnvName", "testPlanName".
   */
  public void generateNewTestCaseExecutionRecord(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.click(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
        additionalParams.get("Generate New Execution Records"));
    LOGGER.LOG.info("Clicked on Generate New Execution Records");
    Dialog generateTestCaseExeRecDialog = this.engine
        .findElement(Criteria.isDialog().withTitle("Generate Test Case Execution Records")).getFirstElement();
    LOGGER.LOG.info("Generate New Execution Records dialog opened successfully.");
    waitForSecs(5);
    Dropdown drdTestPlan = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Test Plan"), this.timeInSecs).getFirstElement();
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_TESTPLANSELECT_XPATH);
    List<WebElement> options =
        this.driverCustom.getWebElements(RQMConstants.RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_TESTPLANSELECT_XPATH);
    ArrayList<String> optionvalues = new ArrayList<>();
    for (WebElement ele : options) {
      optionvalues.add(ele.getText());
    }
    if (!optionvalues.contains(additionalParams.get(RQMConstants.TESTPLAN_NAME))) {
      throw new IllegalArgumentException(additionalParams.get(RQMConstants.TESTPLAN_NAME) +
          " is not present in Generate Test Case Execution Records test plan list ");
    }
    drdTestPlan.selectOptionWithText(additionalParams.get(RQMConstants.TESTPLAN_NAME));
    LOGGER.LOG.info(additionalParams.get("testPlanName") +
        " test plan name selected from Generate Test Case Execution Records widget.");
    waitForSecs(5);
    Select sel = new Select(this.driverCustom.getWebDriver()
        .findElement(By.xpath("//label[text()='Iteration']/../following-sibling::td//select")));
    sel.deselectAll();
    sel.selectByVisibleText("Unassigned");
    Link label = this.engine.findFirstElement(
        Criteria.isLink().withText("Generate Test Environments").inContainer(generateTestCaseExeRecDialog));
    label.click();
    LOGGER.LOG.info("Clicked on Generate Test Environments section.");
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_BROWSERS_CHECKBOX_XPATH,
        additionalParams.get(RQMConstants.SEARCH_TEST_ENV_NAME));
    this.driverCustom.click(RQMConstants.RQMPROJECT_BROWSERS_CHECKBOX_XPATH, additionalParams.get("searchTestEnvName"));
    LOGGER.LOG.info(RMConstants.CLICKED_ON + additionalParams.get("searchTestEnvName") + " envionment checkbox.");
    waitForSecs(5);
    // 04Q specific product value introduced
    if (this.driverCustom.isElementVisible("//div/label[starts-with(text(),'Product')]", Duration.ofSeconds(5))) {
      waitForSecs(3);

      Dialog dialog1 = this.engine.findElement(Criteria.isDialog().withTitle("Generate Test Case Execution Records"))
          .getFirstElement();
      Dropdown drdMenu1 =
          this.engine.findElement(Criteria.isDropdown().withLabel("Product:").inContainer(dialog1)).getFirstElement();
      drdMenu1.selectOptionWithPartText("ALM_RQM");
    }
    clickOnJazzButtons(RQMConstants.NEXT);
    LOGGER.LOG.info("Clicked on 'Next' button");
    waitForSecs(5);
    clickOnJazzButtons("Next >");
    LOGGER.LOG.info("Clicked on 'Next' button");
    waitForSecs(5);
    clickOnJazzButtons(additionalParams.get("Finish and Save"));
    LOGGER.LOG.info("clicked on Finish and Save button");
    waitForSecs(5);
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, Duration.ofSeconds(5),
        RQMConstants.ALERT)) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, "Alert");
      Dialog alert = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Alert"), this.timeInSecs)
          .getFirstElement();
      try{
        Button btn =
            this.engine.findElementWithDuration(Criteria.isButton().inContainer(alert).withText("OK"), this.timeInSecs)
                .getFirstElement();
        btn.click();
        LOGGER.LOG.info("Clicked on OK button");
      }
      catch(Exception e) {
        Button btn =
            this.engine.findElementWithDuration(Criteria.isButton().inContainer(alert).withAriaLabel("close"), this.timeInSecs)
                .getFirstElement();
        btn.click();
        LOGGER.LOG.info("Clicked on close button");
      }
    }

  }


  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Suites" by
   * {@link #openSubMenuUnderSection(String, String)}. <br>
   * Open {@link RQMSectionMenus#TEST_SUITE_EXECUTION_RECORDS} from {@link #openRQMSection(String)} present under
   * sections tab on left side. <br>
   * Clicks on "Generate New Test Suite Execution Records" button present on the top of table at right side opens
   * "Generate Test Suite Execution Records" dialog. <br>
   * Open "Test Plan" drop down by providing the test plan name.
   *
   * @param testPlanName name of the test plan.
   */
  public void generateNewTestSuiteExecutionRecord(final String testPlanName) {
    waitForPageLoaded();
    waitForSecs(5);
    this.driverCustom.click(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
        "Generate New Test Suite Execution Records");
    LOGGER.LOG.info("Clicked on Generate New Test Suite Execution Records");
    waitForSecs(5);
    Dialog generateTestCaseExeRecDialog = this.engine
        .findElement(Criteria.isDialog().withTitle("Generate Test Suite Execution Records")).getFirstElement();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_TESTPLANSELECT_XPATH);
    List<WebElement> options =
        this.driverCustom.getWebElements(RQMConstants.RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_TESTPLANSELECT_XPATH);
    ArrayList<String> optionvalues = new ArrayList<>();
    for (WebElement ele : options) {
      optionvalues.add(ele.getText());
    }
    if (!optionvalues.contains(testPlanName)) {
      throw new IllegalArgumentException(optionvalues + " Doesn't contains " + testPlanName);
    }
    Dropdown drdFieldAgainst = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Test Plan"));
    LOGGER.LOG.info("Clicked on drop down menu with label 'Test Plan'.");
    drdFieldAgainst.selectOptionWithText(testPlanName);
    LOGGER.LOG.info("Selected option " + testPlanName + " from drop down menu.");
    Tab tb = this.engine
        .findElement(
            Criteria.isTab().withText("Reuse Existing Test Environments").inContainer(generateTestCaseExeRecDialog))
        .getFirstElement();
    tb.click();
    LOGGER.LOG.info("clicked on Reuse Existing Test Environments tab.");
    waitForPageLoaded();
    waitForSecs(8);
  }


  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Suites" by
   * {@link #openSubMenuUnderSection(String, String)}. <br>
   * Open {@link RQMSectionMenus#TEST_ENVIRONMENTS} from {@link #openRQMSection(String)} present under sections tab on
   * left side. <br>
   * Clicks on "Test Environment" link, Click on "Add Environments" tool tip button, Search environment existing, add it
   * If it is found and save the artifact.
   *
   * @param additionalParams stores keys "testEnvironment", "Add Environments", "testEnvironmentName".
   */
  public void addTestEnvironmentsToRqmArtifacts(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Link testEnvironmentLink = this.engine.findFirstElement(Criteria.isLink().withText("Test Environment"));
    testEnvironmentLink.click();
    LOGGER.LOG.info("clicked on Test Environment section");
    this.driverCustom.click(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
        additionalParams.get("Add Environments"));
    LOGGER.LOG.info("clicked on Add Environments button.");
    Dialog dlgAvailableEnvironmentOption =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select Test Environments"), this.timeInSecs)
            .getFirstElement();
    TextField textfeild = this.engine.findFirstElement(Criteria.isTextField()
        .withPlaceHolder(RQMConstants.TYPE_FILTER_TEXT_PLACEHOLDER).inContainer(dlgAvailableEnvironmentOption));
    textfeild.setText(additionalParams.get(RQMConstants.TEST_ENVIRONMENT_NAME));
    LOGGER.LOG.info("searched the test environment name");
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    Row row = this.engine
        .findElementWithDuration(Criteria.isRow().containsText(additionalParams.get(RQMConstants.TEST_ENVIRONMENT_NAME))
            .inContainer(dlgAvailableEnvironmentOption), this.timeInSecs)
        .getFirstElement();
    Cell cell = this.engine.findElementWithDuration(Criteria.isCell().inContainer(row).withIndex(1), this.timeInSecs)
        .getFirstElement();
    Checkbox checkBox =
        this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(cell), this.timeInSecs).getFirstElement();
    checkBox.getWebElement().click();
    LOGGER.LOG.info("Clicked on the searched test environment");
    clickOnJazzButtons("OK");
    LOGGER.LOG.info("Clicked on OK button");
    this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTCASE_BUTTON_XPATH, Duration.ofSeconds(10), "Save");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASE_BUTTON_XPATH, "Save");
    clickOnJazzButtons("Save");
    LOGGER.LOG.info("Clicked on Save Button.");
  }

  /**
   * <p>
   * Open "Lab Management" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Create", "Test Environments" by
   * {@link #openSubMenuUnderSection(String, String)}. <br>
   * Selects the criteria in the test environment page of rational quality manager.
   *
   * @param value used to be chosen in the select criteria drop down.
   * @param label is the drop down attribute.
   */
  public void selectCriteria(final String value, final String label) {
    Dropdown drp = this.engine.findFirstElement(Criteria.isDropdown().withLabel(label));
    drp.selectOptionWithText(value);
    LOGGER.LOG.info("Drop down value is selected" + value);
  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc. <br>
   * Summary section of test artifacts has feilds like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)},
   * {@link RQMConstructionPage#chooseTemplateFromSummaryOuterNodeSection(Map)} <br>
   * Choose category drop down and select visible text in Categories outline present in the middle of Summary section.
   *
   * @param additionalParams stores values "attributeName", "attributeValue".
   */

  public void chooseCategoriesTypeAndSelectValues(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.select(
        this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_DROPDOWN_XPATH,
            additionalParams.get("attributeName")),
        additionalParams.get("attributeValue"), SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    LOGGER.LOG.info("Drop down value is selected" + additionalParams.get("attributeValue"));
  }

  /**
   * <p>
   * Open create test script page using values "Create", "Test Script" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Click on "Stack Left to Right" tool tip button, Select the test step and add actual and expected text in to it.
   *
   * @param additionalParams stores keys "testStep1Value", "testScriptStep1Name".
   * @param testScriptStep1Name is step number in Test Script Record which have to handle it. For Example: for Step 1,
   *          "testScriptStep1Name" = Step 1
   * @param testStep1Value used to type the value in the defined Step.
   */
  public void enterTextInTestSteps(final Map<String, String> additionalParams, final String testScriptStep1Name,
      final String testStep1Value) {
    waitForPageLoaded();
    Button btn =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Stack Left to Right"), this.timeInSecs)
            .getFirstElement();
    btn.click();
    LOGGER.LOG.info("Clicked on Stack Left to Right button");
    waitForSecs(1);
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTSCRIPTSTEP1DESC_TEXTBOX_XPATH,
        additionalParams.get(testScriptStep1Name));
    waitForSecs(2);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(additionalParams.get(testStep1Value)).build().perform();
    LOGGER.LOG.info("Test script step value given as " + additionalParams.get(testStep1Value));
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_SCRIPTS}. <br>
   * Clicks on "Associate Existing Script" tool tip button, enter test script id in the "Add Test Scripts" dialog, Click
   * <br>
   * on "Add and Close" button, then save the rqm test artifact.
   *
   * @param additionalParams stores keys {@link RQMConstants#RM_REQ_ID_VALUE},{@link RQMConstants#TEST_ARTIFACT_NAME}.
   */
  public void associateTestScriptInToRqmArtifacts(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    if ((additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME)).equalsIgnoreCase(RQMConstants.KEYWORD)) {
      Button btn = this.engine
          .findElementWithDuration(Criteria.isButton().withToolTip("Associate Existing Script"), this.timeInSecs)
          .getFirstElement();
      btn.click();
      LOGGER.LOG.info("Clicked on Associate Existing Script button");
      waitForSecs(5);
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH,
          additionalParams.get(RQMConstants.RM_REQ_ID_VALUE));
      LOGGER.LOG.info("Id value given as " + additionalParams.get(RQMConstants.RM_REQ_ID_VALUE));
      waitForSecs(5);
    }
    else if (!(additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME)).equalsIgnoreCase(RQMConstants.KEYWORD)) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_ADDREQUIREMENT_ID_XPATH);
      this.driverCustom.click(RQMConstants.RQMPROJECT_ADDREQUIREMENT_ID_XPATH);
      LOGGER.LOG.info("Clicked on Add Test button");
      waitForSecs(3);
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH,
          additionalParams.get(RQMConstants.RM_REQ_ID_VALUE));
      LOGGER.LOG.info("Id value given as " + additionalParams.get(RQMConstants.RM_REQ_ID_VALUE));
      waitForSecs(3);
    }
    new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ENTER).build().perform();
    LOGGER.LOG.info("Clicked on Enter button");
    waitForSecs(3);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_CHECKBOX_XPATH,
        additionalParams.get("rmreqIdValue"));
    this.driverCustom.click(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_CHECKBOX_XPATH,
        additionalParams.get(RQMConstants.RM_REQ_ID_VALUE));
    LOGGER.LOG.info("Clicked on searched id checkbox" + additionalParams.get(RQMConstants.RM_REQ_ID_VALUE));
    this.driverCustom.getPresenceOfWebElement(RQMConstants.JAZZPAGE_BUTTONS_XPATH, RQMConstants.ADD_AND_CLOSE);
    clickOnJazzButtons("Add and Close");
    LOGGER.LOG.info("Clicked on Add and Close button");
    clickOnJazzButtons("Save");
    LOGGER.LOG.info("Clicked on Save button");
  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", ""Test Case"" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)},
   * {@link RQMConstructionPage#chooseTemplateFromSummaryOuterNodeSection(Map)} <br>
   * <br>
   * Opens "Team area" drop down, select the value with visible text other wise select the team area name by clicking.
   *
   * @param additionalParams stores keys "teamAreaValue", "testArtifactName".
   */
  public void selectTeamArea(final Map<String, String> additionalParams) {
    if ((additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME)).equalsIgnoreCase(RQMConstants.KEYWORD)) {
      waitForPageLoaded();
      Dropdown teamAreaDropdown = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Team Area:"));
      teamAreaDropdown.selectOptionWithText(additionalParams.get(RQMConstants.TEAMAREA_VALUE));
      LOGGER.LOG.info("Selected Team Area: value as " + additionalParams.get(RQMConstants.TEAMAREA_VALUE));
    }
    waitForPageLoaded();
    Dropdown teamAreaDropdown = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Team Area:"));
    teamAreaDropdown.selectOptionWithText(additionalParams.get("teamAreaValue"));
    LOGGER.LOG.info("Selected Team Area: value as " + additionalParams.get("teamAreaValue"));

  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", ""Test Case"" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)},
   * {@link RQMConstructionPage#chooseTemplateFromSummaryOuterNodeSection(Map)} <br>
   * Click on "Add Content" button, add description in test case design section.
   *
   * @param additionalParams stores keys "testArtifactDesignDescriptionValue".
   */
  public void addContentToTextAreaSection(final Map<String, String> additionalParams) {
    String inputText = additionalParams.get("testArtifactDesignDescriptionValue");
    waitForSecs(5);
    try {
      Link btnEdit = this.engine.findElementWithDuration(Criteria.isLink().withToolTip("Edit"), Duration.ofSeconds(60)).getFirstElement();
      btnEdit.click();
      LOGGER.LOG.info("Clicked on Edit button.");
    }
    catch (Exception e) {
      Button btnAddContentTCD =
          this.engine.findElementWithDuration(Criteria.isButton().withText(RQMConstants.ADD_CONTENT), this.timeInSecs)
              .getFirstElement();
      btnAddContentTCD.click();

      LOGGER.LOG.info("Clicked on Add Content button.");
    }
    waitForSecs(5);
    List<IElement> list =
        this.engine.findElementWithDuration(Criteria.isTextEditor(), this.timeInSecs).getElementList();
    int lastIndex = list.size();
    Text txtTestCaseDesign =
        this.engine.findElementWithDuration(Criteria.isTextEditor(), this.timeInSecs).getElementByIndex(lastIndex);
    try {
      txtTestCaseDesign.clearText();
      waitForSecs(2);
      if (!inputText.equalsIgnoreCase("null")) {
        txtTestCaseDesign.setText(inputText);
      }
    }
    catch (Exception e) {
      LOGGER.LOG.info("Find editor box after opened multi editor in browser");
      switchToDefaultContent();
      waitForSecs(2);
      this.driverCustom.switchToFrame(
          "//div[contains(@class,'dijitTitlePaneSelected') and not(contains(@class,'hidden'))]//iframe[@class='cke_wysiwyg_frame cke_reset']");
      WebElement txbInput = this.driverCustom.getWebElement("//body[@contenteditable='true']");
      txbInput.click();
      txbInput.clear();
      txbInput.sendKeys(Keys.CONTROL + "a");
      txbInput.sendKeys(Keys.DELETE);
      waitForSecs(2);
      if (!inputText.equalsIgnoreCase("null")) {
        txbInput.sendKeys(inputText);
      }
    }
    LOGGER.LOG.info("Description given as " + inputText);
    switchToDefaultContent();
  }

  /**
   * Get content inside text editor of RQM contruction page <br>
   *
   * @author VDY1HC
   * @return content of text area section
   */
  public String getContentOfTextAreaSection() {
    StringBuilder actualTextDisplayed = new StringBuilder();
    try {
      WebElement contentAfterSave = this.driverCustom.getWebElement(
          "//div[@dojoattachpoint='domNode' and @role='region' and not(contains(@class,'hidden'))]//div[@class='content rqm-rt-content']/div[text()]");
      actualTextDisplayed = actualTextDisplayed.append(contentAfterSave.getText());
    }
    catch (Exception ex) {
      try {
        this.driverCustom.switchToFrame("//iframe[contains(@title,'Rich Text Editor')]");
        List<WebElement> rowsInEditor = this.driverCustom.getWebElements("(//body[contains(@class,'cke_editable')]/p)");
        for (WebElement row : rowsInEditor) {
          actualTextDisplayed = actualTextDisplayed.append("\n" + row.getText());
        }
      }
      catch (Exception exc) {
        //Click to reopen editor box and get text.(affect TS_25822)
        Link btnEdit = this.engine.findElementWithDuration(Criteria.isLink().withToolTip("Edit"), Duration.ofSeconds(60)).getFirstElement();
        btnEdit.click();
        LOGGER.LOG.info("Clicked on Edit button.");
        LOGGER.LOG.info("Find editor box after opened multi editor in browser");
        switchToDefaultContent();
        waitForSecs(2);
        this.driverCustom.switchToFrame(
            "//div[contains(@class,'dijitTitlePaneSelected') and not(contains(@class,'hidden'))]//iframe[@class='cke_wysiwyg_frame cke_reset']");
        WebElement rowInEditor = this.driverCustom.getWebElement("//body[contains(@class,'cke_editable')]/div/p");
        actualTextDisplayed = actualTextDisplayed.append(rowInEditor.getText());
      }
    }
    return actualTextDisplayed.toString();
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#FORMAL_REVIEW}. <br>
   * Clicks on "Formal Review" link under section, Open Approval drop down, Select the approver name and add.
   *
   * @param additionalParams stores "approvalName", "addApproverName", "reviewUserIdValue", reviewUserValue.
   */
  public void formalReviewSection(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Link lnkFormalReview = this.engine
        .findElementWithDuration(Criteria.isLink().withText("Formal Review"), this.timeInSecs).getFirstElement();
    lnkFormalReview.click();
    LOGGER.LOG.info("Clicked on Formal Review button.");
    Dropdown drdFormalReview =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("New:"), this.timeInSecs).getFirstElement();
    drdFormalReview.selectOptionWithText(additionalParams.get("approvalName"));
    LOGGER.LOG.info("Clicked on Review type from the Approval drop down.");
    waitForSecs(3);
    Button btnAddApprover = this.engine
        .findElementWithDuration(Criteria.isButton().withText(additionalParams.get("addApproverName")), this.timeInSecs)
        .getFirstElement();
    btnAddApprover.click();
    LOGGER.LOG.info("Clicked on Add Approver or Add Reviewer button.");
    Dialog dlgSelectUser = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select Users"), this.timeInSecs).getFirstElement();
    Text txtSearchApprover = this.engine.findElementWithDuration(
        Criteria.isTextField().hasLabel("Enter a name filter to load the list.").inContainer(dlgSelectUser),
        this.timeInSecs).getFirstElement();
    txtSearchApprover.setText(additionalParams.get("reviewUserIdValue"));
    LOGGER.LOG.info("search for user using user id or name");
    waitForSecs(2);
    Dropdown drdMatchingApprover = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Matching users:").inContainer(dlgSelectUser),
            this.timeInSecs)
        .getFirstElement();
    waitForSecs(5);
    drdMatchingApprover.selectOptionWithPartText(additionalParams.get("reviewUserValue"));
    LOGGER.LOG.info("Click on searched user using Half Name");
    waitForSecs(2);
    if (this.driverCustom.isElementVisible("//button[@disabled and text()='Add and Close']", this.timeInSecs)) {
      drdMatchingApprover.selectOptionsWithText(additionalParams.get("reviewUserValue"));
      LOGGER.LOG.info("Click on searched user using full Name");
    }
    Button btnAddandCloseinSelectUser = this.engine
        .findElement(Criteria.isButton().withText("Add and Close").inContainer(dlgSelectUser)).getFirstElement();
    btnAddandCloseinSelectUser.click();
    LOGGER.LOG.info("Click on Add and Close button.");
    waitForSecs(2);
  }

  /**
   * <p>
   * Add review or approver using {@link #formalReviewSection(Map)}.
   *
   * @param reviewerName is name of reviewer, example "‎CDG ALM Exchange system-user-CC (CAP-SST/ESM3)"
   * @return true if Reviewer/Approver is displayed, false if Reviewer/Approver is not displayed
   */
  public boolean isReviewerDisplayed(final String reviewerName) {
    Label lblReviewerVerify = null;
    try {
      lblReviewerVerify = this.engine
          .findElementWithDuration(Criteria.isLabel().withText(reviewerName), this.timeInSecs).getFirstElement();
      LOGGER.LOG.info(reviewerName + " is added successfully.");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lblReviewerVerify != null;
  }

  /**
   * <p>
   * Add review or approver using {@link #formalReviewSection(Map)}.
   * <p>
   * Delete all reviewer and approver.
   */
  public void deleteAllReviewer() {
    waitForPageLoaded();

    Button btnDeleteReview = this.engine
        .findElement(Criteria.isButton().withToolTip(RQMConstants.RQM_DELETE_APPROVAL_GROUP_BUTTON)).getFirstElement();
    btnDeleteReview.click();
    LOGGER.LOG.info("click on Delete Approval Group button.");
    this.engine.addFinder(new JazzDialogFinder());
    Dialog dlgConfirmation = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Confirmation"), this.timeInSecs).getFirstElement();
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgConfirmation), this.timeInSecs)
        .getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("click on OK button.");
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#MANAGE_SECTIONS}. <br>
   * Adds all the sections under "Available Sections" column to "Selected Sections".
   */
  public void addAllSectionsInManageSections() {
    waitForPageLoaded();
    Dialog dlg = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Manage Sections"), this.timeInSecs)
        .getFirstElement();
    Button btn = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Add All").inContainer(dlg), this.timeInSecs)
        .getFirstElement();
    btn.click();
    LOGGER.LOG.info("click on Add All button.");
    waitForSecs(1);
    clickOnJazzButtons("OK");
    LOGGER.LOG.info("click on OK button.");
  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)},
   * {@link RQMConstructionPage#chooseTemplateFromSummaryOuterNodeSection(Map)} <br>
   * <br>
   * Enters tag name to the "Tags:" text field present rqm artifacts page.
   *
   * @param additionalParams stores keys "tagsValue".
   */
  public void setTagName(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Text tagTextField = this.engine.findFirstElement(Criteria.isTextField().isMultiLine().hasLabel("Tags:"));
    tagTextField.setText(additionalParams.get("tagsValue"));
    LOGGER.LOG.info("Tag value given as " + additionalParams.get("tagsValue"));
  }

  /**
   * <p>
   * Create test data using "Create", "Test Data" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Upload test data file in to rqm "Test data" artifact.
   *
   * @param additionalParams stores keys "testDataCSVFilePathValue".
   */
  public void uploadTestData(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String attachmentLocation = Paths.get(additionalParams.get("testDataCSVFileValue")).toAbsolutePath().toString();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTDATA_UPLOADCSVBUTTON_XPATH)
        .sendKeys(attachmentLocation);
    waitForSecs(5);
    LOGGER.LOG.info("Test data uploaded successfully.");
  }

  /**
   * <p>
   * Browse test artifacts using values "Browse", "Test Data" or "Browse", "Test Suites" or "Browse", "Test Scripts"
   * <br>
   * from {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   *
   * @param additionalParams stores keys {@link RQMConstants#ARTIFACT_ID}, {@link RQMConstants#TEST_ARTIFACT_NAME}.
   */
  public void filterRqmArtifacts(final Map<String, String> additionalParams) {
    WebElement ele = null;
    Actions act = new Actions(this.driverCustom.getWebDriver());
    if ((additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME)).equalsIgnoreCase("Test Data")) {
      waitForPageLoaded();
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTNAME_TEXTBOX_XPATH,
          additionalParams.get(RQMConstants.ARTIFACT_ID));
      LOGGER.LOG.info("Test data searched with name " + additionalParams.get(RQMConstants.ARTIFACT_ID));
      waitForSecs(3);
      new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ENTER).build().perform();
      LOGGER.LOG.info("Clicked on Enter button to search test data.");
      waitForSecs(3);
      ele = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
          additionalParams.get(RQMConstants.ARTIFACT_ID));
    }
    else {
      waitForPageLoaded();
      waitForSecs(5);
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH,
          additionalParams.get(RQMConstants.ARTIFACT_ID));
      waitForSecs(5);
      LOGGER.LOG.info("Test data searched with name " + additionalParams.get(RQMConstants.ARTIFACT_ID));
      waitForSecs(3);
      new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ENTER).build().perform();
      LOGGER.LOG.info("Clicked on Enter button to search test data.");
      waitForSecs(3);
      if ((additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME)).equalsIgnoreCase("Keywords")) {
        ele = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
            additionalParams.get(RQMConstants.ARTIFACT_ID));
      }
      else {
        ele = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH,
            additionalParams.get(RQMConstants.ARTIFACT_ID));
      }
    }
    act.click(ele).build().perform();
    LOGGER.LOG.info("Clicked on searched data.");
  }

  /**
   * <p>
   * Open test script page by "Browse", "Test Scripts" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Select the test step by step number, add text in to it. <br>
   * Edit test script steps and save the test script.
   *
   * @param additionalParams stores keys "step", "stepNumber".
   */
  public void editTestScriptSteps(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Actions act = new Actions(this.driverCustom.getWebDriver());
    this.driverCustom.getWebDriver().switchTo().activeElement().click();
    act.moveToElement(this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTSCRIPTSTEP2MODIFY_TEXTBOX_XPATH,
        additionalParams.get("stepNumber"))).click().build().perform();
    act.sendKeys(Keys.CONTROL).build().perform();
    waitForSecs(1);
    act.sendKeys(Keys.ENTER).build().perform();
    waitForSecs(4);
    act.sendKeys(Keys.chord(Keys.ALT, Keys.ARROW_UP)).build().perform();
    waitForSecs(3);
    act.sendKeys(additionalParams.get("step")).build().perform();
    LOGGER.LOG.info("Test script new step value given as " + additionalParams.get("step"));
    waitForSecs(3);
    clickOnJazzButtons("Save");
    LOGGER.LOG.info("Clicked on Save button.");
  }


  /**
   * <p>
   * Browse test artifacts using values "Browse", "Test Data" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * open test artifact by {@link #filterRqmArtifacts(Map)} <br>
   * get's id of the Test data.
   *
   * @return id as a string.
   */
  public String getTestDataID() {
    waitForPageLoaded();
    LOGGER.LOG.info("Returns ID of test case, test plan, test suite, test script etc.");
    return this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENTID_GETTEXT_XPATH)
        .getText();
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#EXECUTION_VARIABLES}. <br>
   * Clicks on the "Create Execution Variable" tool tip button ,sends variable name and value to a "Create Execution
   * Variable" pop up.
   *
   * @param additionalParams stores "varName", "valName".
   * @return true if it clicks on create Button.
   */
  public boolean createExecutionVariable(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITE_CREATEEXECUTEVARBUTTON_XPATH);
    Button button = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Create Execution Variable"), this.timeInSecs)
        .getFirstElement();
    button.click();
    LOGGER.LOG.info("Clicked on Create Execution Variable button.");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_EXEVAR_NAME_XPATH);
    Text textField = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Name"));
    textField.setText(additionalParams.get(RQMConstants.VAR_NAME));
    LOGGER.LOG.info("Execution variable name given as " + additionalParams.get(RQMConstants.VAR_NAME));
    this.driverCustom.typeText(RQMConstants.RQMPROJECT_EXEVAR_VALUE_XPATH, additionalParams.get("valName"));
    LOGGER.LOG.info("Execution variable value given as " + additionalParams.get("valName"));
    this.driverCustom.getPresenceOfWebElement(RQMConstants.JAZZPAGE_BUTTONS_XPATH, RQMConstants.CREATE);
    if (!this.driverCustom.isElementPresent(RQMConstants.RQMPROJECT_SNAPSHOTOK_BUTTON_XPATH, Duration.ofSeconds(60), "Create")) {
      clickOnJazzButtons("Create");
      LOGGER.LOG.info("Clicked on Create button.");
      return true;
    }
    return false;
  }

  /**
   * <p>
   * Open keyword page by "Browse", "Keywords" from {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} using {@link RQMSectionMenus#DETAILED_DESCRIPTION}.
   * <p>
   * Edits keyword description text area.
   *
   * @param additionalParams stores keys "testArtifactDescriptionValue".
   */
  public void editKeyowrdRqmArtifact(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_KEYWORDS_EDITBUTTON_XPATH);
    this.driverCustom.click(RQMConstants.RQMPROJECT_KEYWORDS_EDITBUTTON_XPATH);
    waitForSecs(3);
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_TESTCASEDESIGN_IFRAMETEXTBOX_XPATH);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.END).build().perform();
    new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ENTER).build().perform();
    new Actions(this.driverCustom.getWebDriver())
        .sendKeys(additionalParams.get(RQMConstants.TEST_ARTIFACT_DESCRIPTION_VALUE)).build().perform();
    waitForSecs(3);
    this.driverCustom.getWebDriver().switchTo().defaultContent();
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Keywords" or "Browse", "Test Cases" or Browse", "Test Scripts" or "Browse",
   * "Test Suites" and etc from {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Filter required test atifacts using {@link #filterRqmArtifacts(Map)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#SUMMARY}. <br>
   * Click on "Export PDF" tool tip button present on right side top of the page and export test artifacts as pdf.
   *
   * @param additionalParams stores keys "exportDetails", "exportInline".
   * @return true if the export is completed successfully.
   */
  public boolean exportRqmArtifacts(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    // this is using for export the large file
    int waitTime;
    if (additionalParams.get("waitTimeInSec") == null) {
      waitTime = 10;
    }
    else {
      waitTime = Integer.valueOf(additionalParams.get("waitTimeInSec"));
    }
    // click on Export PDF tool tip button, select option to export: Export All Pages, Export Current Page
    Button exportToPdf = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Export PDF"), this.timeInSecs).getFirstElement();
    exportToPdf.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH,
        additionalParams.get("exportDetails"));
    Cell exportDetails = this.engine
        .findElementWithDuration(Criteria.isCell().withText(additionalParams.get("exportDetails")), this.timeInSecs)
        .getFirstElement();
    exportDetails.click();
    waitForSecs(5);
    // Wait for export completed
    try {
      Robot r = new Robot();
      r.keyPress(KeyEvent.VK_ENTER);
      r.keyRelease(KeyEvent.VK_ENTER);
    }
    catch (AWTException e) {
      return false;
    }
    waitForSecs(waitTime);
    // check on the first exported row
    Row row = this.engine
        .findElementWithDuration(Criteria.isRow().containsText(additionalParams.get(RQMConstants.EXPORTINLINE)),
            this.timeInSecs)
        .getFirstElement();
    Cell cell = this.engine.findElementWithDuration(Criteria.isCell().inContainer(row).withIndex(1), this.timeInSecs)
        .getFirstElement();
    Checkbox checkBox =
        this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(cell), this.timeInSecs).getFirstElement();
    checkBox.click();
    return true;
  }

  /**
   * <p>
   * Export test artifacts using {@link #exportRqmArtifacts(Map)}
   * <p>
   * Show all export jobs table contains all the exported jobs. Select the job name by checking cell at index 1 in the
   * same row. Click on "Delete Export Job" tool tip button and accept the confirmation of deletion.
   *
   * @param additionalParams stores keys "exportInline", "alertAccept".
   */
  public void deleteExportedItem(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXPORT_CHECKBOX_XPATH,
        additionalParams.get("exportInline"));
    Row row = this.engine
        .findElementWithDuration(Criteria.isRow().containsText(additionalParams.get("exportInline")), this.timeInSecs)
        .getFirstElement();
    Cell cell = this.engine.findElementWithDuration(Criteria.isCell().inContainer(row).withIndex(1), this.timeInSecs)
        .getFirstElement();
    Checkbox cbxArtifactType =
        this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(cell), this.timeInSecs).getFirstElement();
    cbxArtifactType.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DELETEBTN_XPATH);
    Button deleteExportButton =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Delete Export Job"), this.timeInSecs)
            .getFirstElement();
    deleteExportButton.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.JAZZPAGE_BUTTONS_XPATH,
        additionalParams.get(RQMConstants.ALERTACCEPT));
    clickOnJazzButtons(additionalParams.get(RQMConstants.ALERTACCEPT));
  }

  /**
   * <p>
   * Open test data page by "Browse", "Test Data" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Filter required test atifacts using {@link #filterRqmArtifacts(Map)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#SUMMARY}. <br>
   * Downloads test data by clicking the file name link.
   *
   * @param additionalParams stores keys "File name", "CSVFileName", "download path".
   * @return true if the file is successfully downloaded.
   */
  public boolean downloadTestData(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTDATACSV_FILELINK_XPATH,
        additionalParams.get("File name"));
    Link linkTest = this.engine.findFirstElement(Criteria.isLink().withText(additionalParams.get("File name")));
    linkTest.click();
    File tempFile =
        new File(additionalParams.get("downloadpath") + RQMConstants.SLACE + additionalParams.get("CSVFileName"));
    waitForSecs(10);
    return tempFile.exists();
  }

  /**
   * <p>
   * Open "Construction" menu using {@link #openMainMenuByMenuTitle(String)}.
   * <p>
   * Open test script page by "Browse", "Test Scripts" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * After browsing test artifacts select {@link #selectSlideDown()} if artifacts are not visible. <br>
   * Browse test artifacts by id by entering in to the placeholder "Type Filter Text" and select the row where test
   * artifact id is present, open arrow drop down present in the row and click on Delete test artifact option then
   * confirm deletion.
   *
   * @param additionalParams stores keys "testplanID", "deleteTestPlan", "webButton".
   */
  public void filterRqmArtifactsAndDelete(final Map<String, String> additionalParams) {
    Actions act = new Actions(this.driverCustom.getWebDriver());
    waitForPageLoaded();
    this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH,
        additionalParams.get("testplanID"));
    waitForSecs(3);
    act.sendKeys(Keys.ENTER).build().perform();
    waitForSecs(3);
    act.moveToElement(this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_RQMREQ_ACTIONDROPDOWN_XPATH)).click()
        .build().perform();
    act.click(this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_LINK_XPATH,
        additionalParams.get("deleteTestPlan"))).build().perform();
    waitForSecs(3);
    clickOnJazzButtons(additionalParams.get("webButton"));
    waitForSecs(3);
  }

  /**
   * <p>
   * Delete filtered test artifacts using {@link #filterAndDeleteRqmArtifact(String, String, String)} <br>
   * Search for the test artifact by id or name by entering in Type Filter Text text field and check for message No
   * items found to verify.
   *
   * @param additionalParams stores keys "verifyMsg", "testName", "testplanID".
   * @return message present on the page after filtering the text.
   */
  public String verifyDeletedRqmArtifact(final Map<String, String> additionalParams) {
    Actions act = new Actions(this.driverCustom.getWebDriver());
    waitForPageLoaded();
    if (additionalParams.get("testName").equalsIgnoreCase("name")) {
      TextField textFeild = this.engine.findElementWithDuration(
          Criteria.isTextField().withPlaceHolder(RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER),
          this.timeInSecs).getFirstElement();
      textFeild.setText(additionalParams.get(RQMConstants.TESTPLANNAME));

    }
    else if (additionalParams.get("testName").equalsIgnoreCase("id")) {
      TextField textFeild = this.engine.findElementWithDuration(
          Criteria.isTextField().withPlaceHolder(RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER),
          this.timeInSecs).getFirstElement();
      textFeild.setText(additionalParams.get("testplanID"));
    }
    waitForSecs(3);
    act.sendKeys(Keys.ENTER).build().perform();
    waitForSecs(3);
    return this.driverCustom
        .getWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, additionalParams.get("verifyMsg"))
        .getText();
  }

  /**
   * <p>
   * Delete duplicated artifacts using {@link #duplicateRqmArtifacts(Map)}. <br>
   * Get the title of the test artifacts page and verify whether is contains "Copy of" text in it. Method is usually
   * called after duplicating any test artifacts.
   *
   * @return title of test artifact, null if not found.
   */
  public String verifyDuplictedRqmArtifactByTitle() {
    waitForPageLoaded();
    String pageTitle = this.driverCustom.getWebDriver().getTitle();
    String[] array = pageTitle.split(":");
    for (String a : array) {
      if (a.contains("Copy of")) {
        String[] main = a.split("-");
        return main[0].trim();
      }
    }
    return null;
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Show in line filter contains column names and text fields for each cell in the heading row of the table. (using
   * {@link RQMConstructionPage#selectSlideDown()} Enter respective test artifact value in the text box and press enter.
   * Displays filtered value in the table if found.
   *
   * @param additionalParams - "COLUMN_NAME","SEARCH_VALUE".
   */
  public void filterRqmArtifactByInlineFilterColoumnName(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    waitForSecs(3);
    String columnName = additionalParams.get("COLUMN_NAME");
    String searchValue = additionalParams.get("SEARCH_VALUE");
    switch (columnName) {
      case "ID":
        String attributeID = columnName.toLowerCase();
        WebElement txbSearchFilterID = this.driverCustom
            .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TEXTBOX_SEARCHFILTER_XPATH, attributeID);
        txbSearchFilterID.click();
        txbSearchFilterID.sendKeys(searchValue + Keys.ENTER);
        waitForSecs(3);
        break;
      case "Name":
        String attributeName = columnName.toLowerCase();
        WebElement txbSearchFilter = this.driverCustom
            .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TEXTBOX_SEARCHFILTER_XPATH, attributeName);
        txbSearchFilter.click();
        txbSearchFilter.sendKeys(searchValue + Keys.ENTER);
        waitForSecs(3);
        break;
      default:
        throw new WebAutomationException("Column with name: " + columnName + " - is not handle in automation");
    }
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Checks on the particular test artifact name present in the row, click on delete button shows confirmation pop up
   * then confirm the deletion.
   *
   * @param additionalParams stores keys "testplanName", "alertAccept".
   */
  public void deleteTestData(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_DELTESTDATA_CHECKBOX_XPATH,
        additionalParams.get("testplanName"));
    Row row =
        this.engine.findElement(Criteria.isRow().withText(additionalParams.get("testplanName"))).getFirstElement();
    Cell cel = this.engine.findElementWithDuration(Criteria.isCell().inContainer(row).withIndex(1), this.timeInSecs)
        .getFirstElement();
    cel.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTDATA_DELBUTTON_XPATH);
    Button btn = this.engine.findElementWithDuration(Criteria.isButton().withText(RQMConstants.DELETE), this.timeInSecs)
        .getFirstElement();
    btn.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.JAZZPAGE_BUTTONS_XPATH, additionalParams.get("alertAccept"));
    clickOnJazzButtons(additionalParams.get("alertAccept"));
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Clicks on the "Duplicate" tool tip button present on the rqm artifacts.
   *
   * @param additionalParams contains list of key value pair data. keys present in the map are "dupType",
   *          "testArtifactName", finishButton.
   */
  public void duplicateRqmArtifacts(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_DUPTESTCASE_DUPBUTTON_XPATH);
    Button btn = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Duplicate"), this.timeInSecs)
        .getFirstElement();
    btn.click();
    if (additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME).equalsIgnoreCase(RQMConstants.TESTCASES)) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_DUPTESTCASE_DUPTYPEBUTTON_XPATH,
          additionalParams.get(RQMConstants.DUPTYPE));

      Dialog dlg =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Duplicating Test Cases"), this.timeInSecs)
              .getFirstElement();
      RadioButton radioBtn =
          this.engine
              .findElementWithDuration(
                  Criteria.isRadioButton().withText(additionalParams.get("dupType")).inContainer(dlg), this.timeInSecs)
              .getFirstElement();
      radioBtn.click();
    }
    else if (!additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME).equalsIgnoreCase("Test Cases")) {
      this.driverCustom
          .getWebElement(RQMConstants.RQMPROJECT_DUP_TESTSUITE_CHECKBOX_XPATH, additionalParams.get("dupType"))
          .isSelected();
    }
    clickOnJazzButtons(additionalParams.get("finishButton"));
  }


  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_ITEMS}. <br>
   * Method clicks on the "Add Development Items" tool tip button. Search for work item by id, Select work item and add
   * in to the test artifacts section.
   *
   * @param additionalParams stores keys "Artifact Container Selection", "workitemID".
   */
  public void addDevelopmentItem(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
        "Add Development Items");
    Button btn =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Add Development Items"), this.timeInSecs)
            .getFirstElement();
    btn.click();
    this.driverCustom.select(RQMConstants.RQMPROJECT_ARTIFACTCONTAINERSELECTION_DROPDOWN_XPATH,
        additionalParams.get("Artifact Container Selection"), SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.JAZZPAGE_BUTTONS_XPATH, "OK");
    clickOnJazzButtons("OK");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
    this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH, Duration.ofSeconds(10));
    Dialog dlg = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Development Item"), this.timeInSecs)
        .getFirstElement();
    TextField txt = this.engine.findFirstElement(Criteria.isTextField()
        .hasLabel("Work Item Number or Words Contained in the Text. Use quotes for a phrase search:").inContainer(dlg));
    txt.setText(additionalParams.get("workitemID"));
    this.driverCustom.select(RQMConstants.RQMPROJECT_MATCHINGWORKITEM_WORKITEM_XPATH,
        additionalParams.get("workitemID"), SelectTypeEnum.SELECT_BY_VALUE);
    clickOnJazzButtons("OK");
    clickOnJazzButtons("Save");
  }

  /**
   * <p>
   * Open test suites page by "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} using {@link RQMSectionMenus#TEST_SUITES}.
   * <p>
   * Filter required test artifacts using {@link #filterRqmArtifacts(Map)}. <br>
   * Clicks on "Test Suite Execution Record" link in sections column, Click on "Run Test Suite (Ctrl+Shift+X)" tool tip
   * button. Runs the test suite by submitting finish button.Clicks on "Start Manual Test" link to execute test case.
   * Click on "Close and Show result" button after completing the execution.Select value from the "Actual result:" drop
   * down will be returned. This method opens the Test Suite Execution Record sections and runs the existing test suite
   * records.
   *
   * @return returns status of the test case execution record.
   */
  public String generateTestSuiteExecutionRecordAndGetStatus() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
        "Test Suite Execution Record");
    Link link = this.engine.findFirstElement(Criteria.isLink().withToolTip("Test Suite Execution Record"));
    link.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    refresh();
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, "Run T");
    Button btn = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Run Test Suite (Ctrl+Shift+X)"), this.timeInSecs)
        .getFirstElement();
    btn.click();
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH, "Run");
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH, "Run");
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    clickOnJazzButtons(RQMConstants.FINISH);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH,
        RQMConstants.START_MANUAL_TEST);
    this.driverCustom.isEnabled(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, RQMConstants.START_MANUAL_TEST);
    while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, Duration.ofSeconds(10),
        RQMConstants.START_MANUAL_TEST)) {
      this.driverCustom.waitForSecs(Duration.ofSeconds(2));
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH,
          "Start Manual Test");
      this.driverCustom.click(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, "Start Manual Test");
      this.driverCustom.waitForSecs(Duration.ofSeconds(2));
      while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH, Duration.ofSeconds(10),
          RQMConstants.PASS_CTRL_SHIFT_P)) {
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
            RQMConstants.CTRL_SHIFT_P);
        this.driverCustom.click(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH, RQMConstants.CTRL_SHIFT_P);
        this.driverCustom.waitForSecs(Duration.ofSeconds(2));
      }
      clickOnJazzButtons("Close");
      this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    clickOnJazzButtons(RQMConstants.CLOSE_AND_SHOW_RESULT);
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH).click();
    Select sel = new Select(this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH));
    return sel.getFirstSelectedOption().getText();
  }

  /**
   * <p>
   * Open test cases page by "Browse", "Test Cases" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} using {@link RQMSectionMenus#TEST_CASES}.
   * <p>
   * Filter required test artifacts using {@link #filterRqmArtifacts(Map)}.
   * <p>
   * Clicks on "Run Test Case (Ctrl+Shift+X)" tool tip button,Click Run from the drop down, Click on Pass button and
   * click on show result button.
   *
   * @return status of the test case from "Actual result:" drop down.
   */
  public String generateTestCaseExecutionRecordAndGetStatus() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, RQMConstants.RUN_T);
    Button button = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Run Test Case  (Ctrl+Shift+X)"), this.timeInSecs)
        .getFirstElement();
    button.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH, "Run");
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH, "Run");
    while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH, Duration.ofSeconds(10),
        RQMConstants.PASS_CTRL_SHIFT_P)) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
          RQMConstants.PASS_CTRL_SHIFT_P);
      Button passButton = this.engine
          .findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.CTRL_SHIFT_P), this.timeInSecs)
          .getFirstElement();
      passButton.click();
      this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    }
    clickOnJazzButtons(RQMConstants.SHOW_RESULT);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTVALUE_DROPDOWN_XPATH);
    return this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTVALUE_DROPDOWN_XPATH).getText();
  }

  /**
   * <p>
   * Open test cases page by "Browse", "Test Cases" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} using {@link RQMSectionMenus#TEST_CASES}.
   * <p>
   * Filter required test artifacts using {@link #filterRqmArtifacts(Map)}.
   * <p>
   * Generate test case execution using {@link #generateTestCaseExecutionRecordAndGetStatus()}. <br>
   * Get test case execution record id as string after completion of test case execution.
   *
   * @return test case execution record id after test case execution.
   */
  public String getTestcaseExecRecordID() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXECUTIONRECORD_LINK_XPATH);
    String id =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXECUTIONRECORD_LINK_XPATH).getText();
    String[] ids = id.split(":");
    return ids[0];
  }

  /**
   * <p>
   * Open test cases page by "Browse", "Test Cases" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} using {@link RQMSectionMenus#TEST_CASES}.
   * <p>
   * Filter required test artifacts using {@link #filterRqmArtifacts(Map)}. Clicks on "Run Test Case (Ctrl+Shift+X)"
   * tool tip button,Click Run from the drop down, Select Test plan, Iteration, Test Environment, Select all items from
   * Run Test Suite/Run Test case dialog, Click on Click on Pass button to pass all the test steps in test script and
   * click on show result button.
   *
   * @param additionalParams stores keys "Test Plan value", "Iteration value", "Test Environment value", "Test Suite
   *          Execution Record value", "selectall".
   * @return status of the test case from "Actual result:" drop down.
   */
  public String generateExecutionRecordAndGetResult(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, RQMConstants.RUN_T);
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTSUITERUN_BUTTON_XPATH, RQMConstants.RUN_T);
    if (additionalParams.get(RQMConstants.TEST_ARTIFACT_NAME).equalsIgnoreCase("test cases")) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH, "Run");
      this.driverCustom.click(RQMConstants.RQMPROJECT_TESTCASEEXPORT_DETAILS_XPATH, "Run");
    }
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTSUITERUN_WIDGET_XPATH);
    }
    catch (Exception e) {
    }

    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, Duration.ofSeconds(10),
        RQMConstants.RUN_TEST_SUITE)) {
      chooseDropdownAndSetValueInExecutionRecordDialog(RQMConstants.RUN_TEST_SUITE, "Test Suite Execution Record:",
          additionalParams.get("Test Suite Execution Record value"));
      chooseDropdownAndSetValueInExecutionRecordDialog(RQMConstants.RUN_TEST_SUITE, "Test Plan:",
          additionalParams.get("Test Plan value"));
      chooseDropdownAndSetValueInExecutionRecordDialog(RQMConstants.RUN_TEST_SUITE, "Iteration:",
          additionalParams.get("Iteration value"));
      chooseDropdownAndSetValueInExecutionRecordDialog(RQMConstants.RUN_TEST_SUITE, "Test Environment:",
          additionalParams.get("Test Environment value"));
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
          RQMConstants.SELECT);
      Button btnSelectAll =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.SELECT), this.timeInSecs)
              .getFirstElement();
      btnSelectAll.click();
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
          additionalParams.get("selectall"));
      this.driverCustom.click(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
          additionalParams.get("selectall"));
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RQMConstants.FINISH);
      Button btnFinish =
          this.engine.findElementWithDuration(Criteria.isButton().withText(RQMConstants.FINISH), this.timeInSecs)
              .getFirstElement();
      btnFinish.click();
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH,
          RQMConstants.START_MANUAL_TEST);
      while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, Duration.ofSeconds(10),
          RQMConstants.START_MANUAL_TEST)) {
        this.driverCustom.click(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, RQMConstants.START_MANUAL_TEST);
        this.driverCustom.waitForSecs(Duration.ofSeconds(2));
        while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH, Duration.ofSeconds(10),
            RQMConstants.PASS_CTRL_SHIFT_P)) {
          this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
              RQMConstants.PASS_CTRL_SHIFT_P);
          Button btnPass = this.engine
              .findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.CTRL_SHIFT_P), this.timeInSecs)
              .getFirstElement();
          btnPass.click();
        }
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RQMConstants.CLOSE);
        Button btnClose = this.engine.findElementWithDuration(Criteria.isButton().withText("Close"), this.timeInSecs)
            .getFirstElement();
        btnClose.click();
      }
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RQMConstants.CLOSE_AND_SHOW_RESULT);
      Button btnCloseAndShowResult = this.engine
          .findElementWithDuration(Criteria.isButton().withText(RQMConstants.CLOSE_AND_SHOW_RESULT), this.timeInSecs)
          .getFirstElement();
      btnCloseAndShowResult.click();
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH).click();
      if (!this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH, Duration.ofSeconds(10))) {
        throw new IllegalArgumentException("Actual Result attribute is not visible.");
      }
      Select sel = new Select(this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTRESULTSELECT_DROPDOWN_XPATH));
      return sel.getFirstSelectedOption().getText();
    }
    else if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, Duration.ofSeconds(10),
        RQMConstants.RUN_TEST_CASE)) {
      if (this.driverCustom.isElementVisible(RQMConstants.RQMCONSTRUCTIONPAGE_GENERATE_A_NEW_TCER_TAB_XPATH,
          this.timeInSecs)) {
        if (additionalParams.get("isGenerateANewTestCaseExecutionRecord").equalsIgnoreCase("true")) {
          this.driverCustom.click(RQMConstants.RQMCONSTRUCTIONPAGE_GENERATE_A_NEW_TCER_TAB_XPATH);
          waitForSecs(2);
        }
      }
      chooseDropdownAndSetValueInExecutionRecordDialog(RQMConstants.RUN_TEST_CASE, "Test Plan:",
          additionalParams.get("Test Plan value"));
      chooseDropdownAndSetValueInExecutionRecordDialog("Run Test Case", "Iteration:",
          additionalParams.get("Iteration value"));
      chooseDropdownAndSetValueInExecutionRecordDialog("Run Test Case", "Test Environment:",
          additionalParams.get("Test Environment value"));
      Button btnFinish1 =
          this.engine.findElementWithDuration(Criteria.isButton().withText(RQMConstants.FINISH), this.timeInSecs)
              .getFirstElement();

      if ((additionalParams.get("Create a result without executing") != null) &
          additionalParams.get("Create a result without executing").equalsIgnoreCase("true")) {
        this.driverCustom.click(RQMConstants.RQMCONSTRUCTIONPAGE_CREATE_A_RESULT_WITHOUT_EXECUTING_CHECKBOX_XPATH);
        chooseDropdownAndSetValueInExecutionRecordDialog("Run Test Case", "Set verdict of the result as:",
            additionalParams.get("verdict of the result"));
        btnFinish1.click();
      }
      else {
        btnFinish1.click();
        while (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH, Duration.ofSeconds(10),
            RQMConstants.PASS_CTRL_SHIFT_P)) {
          this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_MANAGESECTION_BUTTON_XPATH,
              RQMConstants.PASS_CTRL_SHIFT_P);
          Button btnPass1 = this.engine
              .findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.CTRL_SHIFT_P), this.timeInSecs)
              .getFirstElement();
          btnPass1.click();
          this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RQMConstants.SHOW_RESULT);
          Button btnShowResult = this.engine
              .findElementWithDuration(Criteria.isButton().withText(RQMConstants.SHOW_RESULT), this.timeInSecs)
              .getFirstElement();
          btnShowResult.click();
        }
      }
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTVALUE_DROPDOWN_XPATH);
      return this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTRESULTVALUE_DROPDOWN_XPATH)
          .getText();
    }
    return null;
  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)},
   * {@link RQMConstructionPage#chooseTemplateFromSummaryOuterNodeSection(Map)}
   * <p>
   * Enters description to the test artifacts present in summary outer node section.
   *
   * @param additionalParams stores keys "testArtifactDescriptionValue".
   */
  public void fillDescriptionInSummaryOuterNodeSection(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    waitForSecs(5);
    // Click on "Minimize Steps Display" button if Description is not displayed.
    try {
      Button btnMinimize =
          this.engine.findElement(Criteria.isButton().withToolTip("Minimize Steps Display")).getFirstElement();
      btnMinimize.click();
      LOGGER.LOG.info("Clicked on 'Minimise Steps Display' button properly.");
      waitForSecs(5);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEDESCRIPTION_TEXTBOX_XPATH);
    // Input data into Create Test Case form
    this.driverCustom.typeText(RQMConstants.RQMPROJECT_TESTCASEDESCRIPTION_TEXTBOX_XPATH,
        additionalParams.get("testArtifactDescriptionValue"));
    LOGGER.LOG.info(additionalParams.get("testArtifactDescriptionValue") + " is entered in Description section.");
    waitForSecs(5);
  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)},
   * {@link RQMConstructionPage#chooseTemplateFromSummaryOuterNodeSection(Map)} <br>
   * Selects owner from the owner drop down by selecting visible text or searching the value from the text field by name
   * and selecting.
   *
   * @param additionalParams stores keys "ownerValue", "userIdValue", "moreLinkValue".
   */
  public void selectOwner(final Map<String, String> additionalParams) {
    waitForSecs(3);
    waitForPageLoaded();
    waitForSecs(5);
    Dropdown ownerDropdown =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(RQMConstants.OWNER_LABEL), this.timeInSecs)
            .getFirstElement();
    if (additionalParams.get(RQMConstants.MORELINKVALUE).contains("More")) {
      additionalParams.put(RQMConstants.MORELINKVALUE, RQMConstants.MORE);
    }
    waitForSecs(5);
    ownerDropdown.selectOptionWithText(additionalParams.get("moreLinkValue"));
    waitForSecs(5);
    if (additionalParams.get("moreLinkValue").equalsIgnoreCase("More...")) {
      // On version 7.0
      if (this.driverCustom.isElementPresent("//div[@class='bx--modal-container']", Duration.ofSeconds(20))) {
        Dialog selectOwnerDialog = this.engine
            .findElementWithDuration(Criteria.isDialog().withTitle("Select user"), this.timeInSecs).getFirstElement();
        TextField searchBoxTextField =
            this.engine.findFirstElement(Criteria.isTextField().inContainer(selectOwnerDialog));
        searchBoxTextField.setText(additionalParams.get("userIdValue"));
        LOGGER.LOG.info(additionalParams.get("userIdValue") + "is entered in search field.");
        Label ownerSelected = this.engine.findElementWithDuration(
            Criteria.isLabel().containText(additionalParams.get("ownerValue")).inContainer(selectOwnerDialog),
            this.timeInSecs).getFirstElement();
        ownerSelected.click();
      }
      else {
        Dialog selectOwnerDialog = this.engine
            .findElementWithDuration(Criteria.isDialog().withTitle("Select User"), this.timeInSecs).getFirstElement();
        TextField searchBoxTextField =
            this.engine.findFirstElement(Criteria.isTextField().inContainer(selectOwnerDialog));
        searchBoxTextField.setText(additionalParams.get("userIdValue"));
        LOGGER.LOG.info(additionalParams.get("userIdValue") + "is entered in search field.");
        waitForSecs(10);
        Dropdown drdMatchingUser =
            this.engine
                .findElementWithDuration(
                    Criteria.isDropdown().withLabel("Matching users:").inContainer(selectOwnerDialog), this.timeInSecs)
                .getFirstElement();
        waitForSecs(8);
        drdMatchingUser.selectOptionWithPartText(additionalParams.get("ownerValue"));
        waitForSecs(8);
      }
      LOGGER.LOG.info(additionalParams.get("ownerValue") + "is selected successfully.");
    }
    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Clicked on 'OK' button properly.");
    waitForSecs(8);
  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)}. <br>
   * Choose template in all test artifacts summary outer node section by opening the drop down "Creating using
   * template".
   *
   * @param additionalParams stores keys "creatingUsingTemplateName", "testArtifactTemplateValue".
   */
  public void chooseTemplateFromSummaryOuterNodeSection(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    WebElement templateElement = null;
    try {
      templateElement = this.driverCustom.getPresenceOfWebElement(
          RQMConstants.RQMPROJECT_TESTCASEDROPDOWN_TEXTBOX_XPATH, additionalParams.get("creatingUsingTemplateName"));
    }
    catch (Exception e) {
      LOGGER.LOG.info("No 'Creating using template' dropdown showing in the current screen." + e.getMessage());
    }
    if (templateElement != null) {
      String testArtifactTemplateValue = additionalParams.get("testArtifactTemplateValue");
      List<Dropdown> drpTemplateList =
          this.engine.findElement(Criteria.isDropdown().withLabel("Creating using template:")).getElementList();
      Dropdown drpTemplate = null;
      for (int i = 0; i < drpTemplateList.size(); i++) {
        if (drpTemplateList.get(i).getText().contains("template")) {
          drpTemplate = drpTemplateList.get(i);
          if (!drpTemplate.getText().equalsIgnoreCase(testArtifactTemplateValue)) {
            drpTemplate.selectOptionWithText(testArtifactTemplateValue);
            LOGGER.LOG.info(testArtifactTemplateValue + " template is selected successfully.");
            if (this.driverCustom.isElementVisible(RQMConstants.JAZZPAGE_BUTTONS_XPATH, this.timeInSecs, "Yes")) {
              clickOnJazzButtons("Yes");
              LOGGER.LOG.info("Clicked on 'Yes' button on Confirmation dialog.");
              waitForSecs(2);
              new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.PAGE_UP).build().perform();
            }
          }
          break;
        }
      }
    }
  }
  
  /**
   * @param additionalParams stores keys 'creatingUsingTemplateName' and 'testArtifactTemplateValue'
   */
  public void chooseTemplateFromSummaryForTestPlan(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    WebElement templateElement = null;
    try {
      templateElement = this.driverCustom.getFirstVisibleWebElement(RQMConstants.RQMPROJECT_TESTCASEDROPDOWN_TEXTBOX_XPATH, additionalParams.get("creatingUsingTemplateName"));
    }
    catch (Exception e) {
      LOGGER.LOG.info("No 'Creating using template' dropdown showing in the current screen." + e.getMessage());
    }
    if (templateElement != null) {
      String testArtifactTemplateValue = additionalParams.get("testArtifactTemplateValue");
      templateElement.click();
      WebElement template = this.driverCustom.getFirstVisibleWebElement("//span[@dojoattachpoint='labelNode' and text()='DYNAMIC_VAlUE']",testArtifactTemplateValue);
      template.click();
    }
  }

  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)}. <br>
   * Selects action from the "Action" drop down by visible text.
   *
   * @param additionalParams stores keys "selectActionValue".
   */
  public void selectLifeCycleState(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Dropdown action = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Action:"));
    action.selectOptionWithText(additionalParams.get("selectActionValue"));
  }

  /**
   * <p>
   * Open {@link #chooseCategoriesTypeAndSelectValues(Map)} for categories section. <br>
   * Select Release name and release value.
   *
   * @param selectName name of Drop down with check box attribute
   * @param selectValue value of Drop down attribute that need to select.
   */
  public void selectCheckBoxInDropDown(final String selectName, final String selectValue) {
    waitForPageLoaded();
    Dropdown releaseDropdown = this.engine.findFirstElement(Criteria.isDropdown().withLabel(selectName));
    releaseDropdown.click();
    Checkbox c = this.engine.findElementWithDuration(Criteria.isCheckbox().withLabel(selectValue), this.timeInSecs)
        .getFirstElement();
    c.click();
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.sendKeys(Keys.ESCAPE).build().perform();
  }

  /**
   * <p>
   * Create test artifacts by "Create", "Test Scripts" or "Create", "Test Cases", "Create", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Enters name or title on the test artifact page.
   *
   * @param additionalParams stores keys {@link RQMConstants#TEST_ARTIFACT_TITLE_VALUE} "Place holder name".
   * @return test case name.
   */
  public String enterTestArtifactName(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    waitForSecs(5);
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEHEADING_TEXTBOX_XPATH,
          additionalParams.get(RQMConstants.TEST_ARTIFACT_TITLE_VALUE));
      Text txtSummary = this.engine
          .findFirstElement(Criteria.isTextField().withPlaceHolder(additionalParams.get("Place holder name")));
      String testCaseName =
          additionalParams.get(RQMConstants.TEST_ARTIFACT_TITLE_VALUE) + DateUtil.getCurrentDateAndTime();
      txtSummary.setText(testCaseName);
      LOGGER.LOG.info("The name  of the rqm artifact is set successfully - " + testCaseName);
      waitForSecs(5);
      return testCaseName;
    }
    catch (Exception e2) {
      throw new WebAutomationException("Summary text field is NOT present." + "\n" + e2.getMessage());
    }
  }

  /**
   * <p>
   * Edit test artifacts by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * After browsing test artifacts select {@link #selectSlideDown()} if artifacts are not visible.
   * <p>
   * Filter required test artifacts using {@link #filterRqmArtifacts(Map)}. Renames name or tile on the test artifact
   * page.
   *
   * @param additionalParams stores {@link RQMConstants#TEST_ARTIFACT_TITLE_VALUE}.
   * @return name of the renamed test case.
   */
  public String renameTestArtifactName(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String testCaseName = additionalParams.get(RQMConstants.TEST_ARTIFACT_TITLE_VALUE);
    try {
      WebElement txtSummary =
          this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASEHEADING_TEXTBOX_XPATH,
              additionalParams.get(RQMConstants.TEST_ARTIFACT_TITLE_VALUE));
      txtSummary.clear();
      txtSummary.sendKeys(testCaseName);
      LOGGER.LOG.info("Changed the name  of the rqm artifact to - " + testCaseName);
      return testCaseName;
    }
    catch (Exception e) {
      throw new WebAutomationException(
          "Cannot change the name of the rqm artifact to - " + testCaseName + "\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Browse test artifacts by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * get's name of artifact.
   *
   * @return artifact value
   */
  public String getTestArtifactName() {
    return this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTCASE_NAME_XPATH).getAttribute("value");
  }


  /**
   * <p>
   * Create or edit test artifacts like Test cases, Test suites, Test scripts and etc.
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Summary section of test artifacts has fields like Owner, Action, Priority, Team area, Description.
   * {@link RQMConstructionPage#addContentToTextAreaSection(Map)},{@link RQMConstructionPage#selectTeamArea(Map)},
   * {@link RQMConstructionPage#selectOwner(Map)}}
   * {@link RQMConstructionPage#fillDescriptionInSummaryOuterNodeSection(Map)}. <br>
   * Selects priority from the priority drop down by selecting visible text.
   *
   * @param additionalParams stores keys "priorityValue".
   */
  public void selectPrority(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Dropdown priority = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Priority:"), this.timeInSecs).getFirstElement();
    priority.selectOptionWithText(additionalParams.get("priorityValue"));
    LOGGER.LOG.info(additionalParams.get("priorityValue") + " is selected from Priority dropdown successfully.");
  }

  /**
   * <p>
   * {@link #generateExecutionRecordAndGetResult(Map)} <br>
   * Common method to open drop down based on label and select visible text from the drop down.Which are present on the
   * run suite/test case dialog.
   *
   * @param selectName label of the drop down.
   * @param selectValue visible text to be selected in drop down.
   * @param dialogTitle title of the execution record dialog
   */
  public void chooseDropdownAndSetValueInExecutionRecordDialog(final String dialogTitle, final String selectName,
      final String selectValue) {
    waitForPageLoaded();
    Dialog dialog = this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialogTitle), this.timeInSecs)
        .getFirstElement();
    Dropdown d = this.engine.findFirstElement(Criteria.isDropdown().withLabel(selectName).inContainer(dialog));
    d.selectOptionWithText(selectValue);
  }

  /**
   * <p>
   * {@link #generateExecutionRecordAndGetResult(Map)} <br>
   * Common method to open drop down based on label and select visible text from the drop down.Which are present on the
   * run suite/test case dialog.
   *
   * @param selectName label of the drop down.
   * @param selectValue visible text to be selected in drop down.
   */
  public void chooseDropdownAndSetValueOnlyInExecutionRecordDialog(final String selectName, final String selectValue) {
    waitForPageLoaded();
    Dropdown d = this.engine.findFirstElement(Criteria.isDropdown().withLabel(selectName));
    d.selectOptionWithText(selectValue);
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#REQUIREMENT_LINKS}. <br>
   * Get the list of requirement links.
   *
   * @return list of requirements links.
   */
  public List<WebElement> getRequirementLinks() {
    waitForPageLoaded();
    return this.driverCustom.getWebElements(RQMConstants.RQMPROJECT_REQUIREMENTSLINKS_LIST_XPATH);
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#REQUIREMENT_LINKS}. <br>
   * Verifies Link Validity with specific id displayed or not on Requirement Links under Sections of Test Case.
   *
   * @param id artifact id or module id in Requirement Links of test case
   * @return true if the Link is displayed, false if the Link is not displayed
   */
  public boolean isLinkValidityVisibleInRequirementLinks(final String id) {
    this.driverCustom.isElementInvisible("//span[@class='progress-label']", Duration.ofSeconds(30));
    return this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_REQUIREMENTSLINKS_DIV_CONTAINS_ID_XPATH, Duration.ofSeconds(10), id);
  }

  /**
   * <p>
   * Browse test artifacts by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Selects the type of the view after displaying all test artifacts.
   *
   * @param selectValue as 'General' or 'Traceability'.
   */
  public void selectViewAs(final String selectValue) {
    waitForPageLoaded();
    Dropdown viewAsDropdown = this.engine.findFirstElement(Criteria.isDropdown().withLabel("View As:"));
    viewAsDropdown.selectOptionWithText(selectValue);
    waitForSecs(3);
  }

  /**
   * <p>
   * Browse test artifacts by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Clicks on "Change column display settings" tool tip button.
   */
  public void clickChangeColumnDisplay() {
    waitForPageLoaded();
    this.driverCustom
        .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_CHANGECOLUMNDISPLAYSETTINGS_XPATH);
    waitForSecs(2);
    Button btn = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Change column display settings"), this.timeInSecs)
        .getFirstElement();
    btn.click();
  }

  /**
   * <p>
   * {@link #clickChangeColumnDisplay()} <br>
   * Removes all the display Columns in Change Column Display Settings dialog.
   */
  public void removeAllColumns() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_REMOVEALLCOLUMNS_XPATH);
    waitForSecs(2);
    Button btn = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Remove All"), this.timeInSecs)
        .getFirstElement();
    btn.click();
  }

  /**
   * <p>
   * Browse test artifacts by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Clicks on the Type Filter text field present on the "Change Column Display Settings" dialog.
   */
  public void selectFilterText() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_TYPEFILTERTEXT_XPATH);
    Dialog dlg =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.CHANGE_COLUMN_DISPLAY_SETTING),
            this.timeInSecs).getFirstElement();
    TextField filterText = this.engine.findFirstElement(Criteria.isTextField().inContainer(dlg));
    filterText.click();

  }

  /**
   * <p>
   * Open Change column display setting dialog by {@link #clickChangeColumnDisplay()}. <br>
   * Clicks on the column name available under "Available Columns" section in "Change Column Display Settings" dialog.
   *
   * @param coloumName column name to be selected.
   */
  public void selectAvailableColumn(final String coloumName) {
    waitForPageLoaded();
    this.driverCustom
        .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_SELECTAVAILABLECOLUMNSID_XPATH);
    waitForSecs(3);
    Dialog dlgAvailableSections = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Change Column Display Settings"), this.timeInSecs)
        .getFirstElement();
    TextField availableColoumtextField = this.engine
        .findFirstElement(Criteria.isTextField().hasLabel("Type Filter Text").inContainer(dlgAvailableSections));
    availableColoumtextField.setText(coloumName);
    Text availableColoumLabel =
        this.engine.findFirstElement(Criteria.isLabel().withText(coloumName).inContainer(dlgAvailableSections));
    availableColoumLabel.click();
  }

  /**
   * <p>
   * Select available column using {@link #selectAvailableColumn(String)}. <br>
   * Click on "Add Select" tool tip button on Change Column Display Settings dialog.
   */
  public void clickOnAddSelect() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_ADDSELECT_XPATH);
    Dialog dlgAvailableSections = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Change Column Display Settings"), this.timeInSecs)
        .getFirstElement();
    Button btn = this.engine
        .findElement(Criteria.isButton().withToolTip("Add Select").inContainer(dlgAvailableSections)).getFirstElement();
    btn.click();
  }

  /**
   * <p>
   * Add Selected available column using {@link #clickOnAddSelect()}. <br>
   * Click on ok in change column display setting.
   */
  public void clickOk() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_CLICKOK_XPATH);
    waitForSecs(3);
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_CLICKOK_XPATH);
    waitForSecs(2);
  }

  /**
   * <p>
   * Browse test artifacts by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Clicks on the "Show Inline Filters" tool tip button.
   * 
   * @author VDY1HC
   */
  public void selectSlideDown() {
    waitForPageLoaded();
    if (this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 6)))) {
      this.driverCustom.click(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH);
    }
  }

  /**
   * p>Select "Tests Development Items" available column using {@link #selectAvailableColumn(String)}.
   * <p>
   * Add Selected available column using {@link #clickOnAddSelect()}.{@link #clickOk()} Clicks on the "Test Development
   * item" drop do wn.
   */
  public void clickOnTestDevelopmentItemDropdown() {
    waitForPageLoaded();
    this.driverCustom
        .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_CLICKONTESTDEVELOPMENTITEMDROPDOWN_XPATH);
    waitForSecs(3);
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_CLICKONTESTDEVELOPMENTITEMDROPDOWN_XPATH);
  }

  /**
   * <p>
   * Open "Tests Development Items" drop down using {@link #clickOnTestDevelopmentItemDropdown()}. <br>
   * Choose the option from the "Test Development Item" drop down.
   *
   * @param option value to be selected.
   */
  public void selectOptionFromTestDevelopmentItemDropdown(final String option) {
    waitForPageLoaded();
    this.driverCustom
        .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_SELECTHASDEVELOPMENTITEMFROMDROPDOWN_XPATH);
    this.driverCustom.select(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_SELECTHASDEVELOPMENTITEMFROMDROPDOWN_XPATH,
        option, SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
  }

  /**
   * <p>
   * Browse test artifacts by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Clicks on "Filter" button to select the particular artifact by applying conditions.
   */
  public void clickOnFilterButton() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_CLICKFILTERBUTTON_XPATH);
    waitForSecs(3);
    Button filterButton =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.FILTERS), this.timeInSecs)
            .getFirstElement();
    filterButton.click();
  }

  /**
   * <p>
   * Search for the text case link shown in results table after browsing the test case.
   *
   * @return test case id.
   */
  public String getTestCaseIdFromResultsTable() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTCASEID_XPATH);
    return this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTCASEID_XPATH).getText();

  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_ITEMS}. <br>
   * Search for the work item(ID:Name) with link present on the row.
   *
   * @return test linked work item ID and Name.
   */
  public String getDevelopmentItemIdAndName() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTDEVELOPMENTITEM_XPATH);
    return this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTDEVELOPMENTITEM_XPATH)
        .getText();

  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_ITEMS}. <br>
   * Search for the work item(ID:Name) with link present on the row.
   *
   * @return work item id.
   */
  public String getDevelopmentItemId() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTDEVELOPMENTITEM_XPATH);
    String s = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTDEVELOPMENTITEM_XPATH)
        .getText();
    String[] words = s.split(":");// splits the string based on whitespace
    // using java foreach loop to print elements of string array
    return words[0];
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_ITEMS}. <br>
   * Search for the work item(ID:Nname) with link present on the row.
   *
   * @return work item name as string.
   */
  public String getDevelopmentItemName() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTDEVELOPMENTITEM_XPATH);
    String s = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_GETTESTDEVELOPMENTITEM_XPATH)
        .getText();
    String[] words = s.split(":");// splits the string based on whitespace
    // using java foreach loop to print elements of string array
    return words[1];
  }

  /**
   * <p>
   * Open create test script page using values "Create", "Test Script" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Create a new manual step in the test script.<br>
   * <br>
   * Insert the manual step before or after the existing step.
   *
   * @param menu takes values like "Cut","Insert New Step Before","Insert New Step After" etc.
   * @param subMenu takes values like "Create Empty Step","Copy Current Custom Attributes" etc.
   */
  public void createNewStep(final String menu, final String subMenu) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible("//span[@class='jazz-ui-toolbar-Button']/a[@title='Stack Left to Right']",
        Duration.ofSeconds(20))) {
      Button btnStackLeftToRight =
          this.engine.findElement(Criteria.isButton().withToolTip("Stack Left to Right")).getFirstElement();
      btnStackLeftToRight.click();
    }
    Dropdown drdActions = this.engine.findFirstElement(Criteria.isDropdown().withToolTip(RQMConstants.ACTIONS));
    drdActions.click();
    LOGGER.LOG.info("Clicked on Actions drop down menu.");
    Cell cellMenu =
        this.engine.findElementWithDuration(Criteria.isCell().withText(menu), this.timeInSecs).getFirstElement();
    cellMenu.getWebElement().click();
    LOGGER.LOG.info("Clicked on menu option - " + menu);
    Cell cellSubMenu =
        this.engine.findElementWithDuration(Criteria.isCell().withText(subMenu), this.timeInSecs).getFirstElement();
    cellSubMenu.getWebElement().click();
    LOGGER.LOG.info("Clicked on sub menu option - " + subMenu);
  }

  /**
   * <p>
   * Open create test script page using values "Create", "Test Script" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Create new step using {@link #createNewStep(String, String)}.
   * <p>
   * Add manual step content in the test script.<br>
   * Here it add "Description" and "Expected Result" values in the manual step of the test script.
   *
   * @param additionalParams stores keys {@link RMConstants#DESCRIPTION} and "expecResultValue".
   */
  public void addStepValuesInTestScript(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    waitForSecs(5);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(additionalParams.get(RQMConstants.DESCRIPTION)).build()
        .perform();
    LOGGER.LOG
        .info("Description value '" + additionalParams.get(RQMConstants.DESCRIPTION) + "' added in the Test Script.");
    waitForSecs(2);
    this.driverCustom.click(RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH,
        additionalParams.get(RQMConstants.EXPECTED_RESULT_VALUE));
    LOGGER.LOG.info("Clicked on '" + additionalParams.get("expecResultValue") + "'.");
    waitForSecs(2);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(additionalParams.get(RQMConstants.EXPECTED_RESULT)).build()
        .perform();
    LOGGER.LOG.info(
        "Expected Result value '" + additionalParams.get(RQMConstants.EXPECTED_RESULT) + "' added in the Test Script.");
  }

  /**
   * <p>
   * Create new step using {@link #createNewStep(String, String)} <br>
   * Add manual step content in the test script.<br>
   * Here it add "Description" and "Expected Result" values in the manual step of the test script.
   *
   * @param additionalParams stores keys "description", "descValue" and "expectedResult".
   */
  public void addStepValuesInNewTestScript(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    waitForSecs(5);
    this.driverCustom.click(RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH_UPDATE,
        additionalParams.get("descValue").replaceAll(" ", ""));
    waitForSecs(2);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(additionalParams.get("description")).build().perform();
    LOGGER.LOG.info(additionalParams.get("description") + " is added description of test script.");
    waitForSecs(2);
    this.driverCustom.click(RQMConstants.RQM_TESTSCRIPT_STEP_EXPECTEDRESULT_DESCRIPTION_TEXTBOX_XPATH_UPDATE,
        additionalParams.get("expecResultValue").replaceAll(" ", ""));
    waitForSecs(2);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(additionalParams.get("expectedResult")).build().perform();
    LOGGER.LOG.info(additionalParams.get("expectedResult") + " is added test step of test script.");
  }


  /**
   * <p>
   * Create new step using {@link #createNewStep(String, String)}. <br>
   * Add manual step content in the test script.{@link #addStepValuesInTestScript(Map)}.
   * <p>
   * Gets content of 'Description' and 'Expected Result' in Manual Steps by step number and step coloumn name.
   *
   * @param stepNo is the manual step number i.e. 1,2,3,.... in the test script.
   * @param stepValue refers to title like "Description" and "Expected Results" of the manual steps.
   * @return content of the Description and Expected Result.
   */
  public String getContentOfStep(final String stepNo, final String stepValue) {
    waitForSecs(5);
    By locator = this.driverCustom.createLocatorFromProperty(RQMConstants.RQM_MANUALSTEP_TESTSCRIPT_XPATH,
        new String[] { stepNo, stepValue });
    String getStepContent = this.driverCustom.getWebDriver().findElement(locator).getText();
    if (!getStepContent.isEmpty() && !getStepContent.contains(RQMConstants.TEST_SCRIPT_STEP_HINT_TEXT)) {
      LOGGER.LOG.info("Description and Expected Result is added in the manual step.");
      return this.driverCustom.getWebDriver().findElement(locator).getText();
    }
    this.driverCustom.switchToFrame("//iframe[contains(@title,'Editor, editor1')]");
    LOGGER.LOG.info("Fetching the content of Description and Expected Result from the manual step.");
    String s = this.driverCustom.getWebElement("//body[contains(@class,'script-step-content')]").getText();
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    return s;
  }

  /**
   * <p>
   * Create new step using {@link #createNewStep(String, String)} <br>
   * Delete the manual step from the test script.
   *
   * @param stepNo number.
   * @return true if the step is deleted.
   */
  public boolean deleteStep(final String stepNo) {

    List<WebElement> listElement1 = this.driverCustom.getWebElements("//span[@dojoattachpoint='stepNum']");
    int numberOfStepBeforeDelete = listElement1.size();
    int i = Integer.parseInt(stepNo);
    if (Integer.parseInt(stepNo) <= numberOfStepBeforeDelete) {
      this.driverCustom.clickUsingActions(this.driverCustom.getWebElement("//span[text()='" + i +
          "']/ancestor::div[@class='content-layout']//div[contains(@aria-label,'Description')]"));
      waitForSecs(2);
      LOGGER.LOG.info("Get number of step before delete.");
      Dropdown dnd = this.engine.findFirstElement(Criteria.isDropdown().withToolTip("Actions"));
      dnd.click();
      LOGGER.LOG.info("Clicked on the 'Actions' drop down menu.");
      Cell cellDeleteStep = this.engine
          .findElementWithDuration(Criteria.isCell().withText("Delete Step"), this.timeInSecs).getFirstElement();
      cellDeleteStep.getWebElement().click();
      waitForSecs(3);
      LOGGER.LOG.info("Clicked on 'Delete Step' option from the drop down list.");
      List<WebElement> listElement2 = this.driverCustom.getWebElements("//span[@dojoattachpoint='stepNum']");
      int numberOfStepAfterDelete = listElement2.size();
      LOGGER.LOG.info("Get number of step after delete.");
      return numberOfStepBeforeDelete != numberOfStepAfterDelete;
    }
    throw new WebAutomationException(i + " step not there to delete.");

  }


  /**
   * <p>
   * Open keywords page using "Browse", "Keywords" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using "Test Script".
   * <p>
   * Open KeyWords with KeyWord id.<br>
   * <p>
   * Navigate to Test Script section inside keyword. <br>
   * Click on the test script with id.
   *
   * @param id refers to test script id.
   */
  public void clickOnTestScriptId(final String id) {
    waitForPageLoaded();
    try {
      Link lnk = this.engine.findElementWithDuration(Criteria.isLink().withText(id), this.timeInSecs).getFirstElement();
      lnk.click();
    }
    catch (Exception e) {
      throw new WebAutomationException(
          id + " Test Script not found.Please check for the valid input.\n" + " Or\n" + e.getMessage());
    }
    LOGGER.LOG.info(RQMConstants.CLICKED_ON + id + " from 'Test Scripts' section of the keyword.");
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Open browse web page for any of the artifacts like Test Scripts,Test Cases,Keywords,Test Suites etc.. <br>
   * <br>
   * Search the artifacts in 'Type filter text and press Enter' text box.
   *
   * @param artifactName name of the test cases or test scripts or keywords etc...
   */
  public void searchRqmArtifactsInFilterTextBox(final String artifactName) {
    waitForPageLoaded();
    try {
      Button btnClearFilter =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.CLEAR_FILTER_BUTTON_TOOLTIP),
              this.timeInSecs).getFirstElement();
      btnClearFilter.click();
    }
    catch (Exception e) {
      LOGGER.LOG.info("Continues without clear filter.");
    }
    Text txtSearch = this.engine.findElementWithDuration(
        Criteria.isTextField().withToolTip(RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER), this.timeInSecs)
        .getFirstElement();
    txtSearch.setText(artifactName);
    LOGGER.LOG.info(artifactName + " - searched in 'Type Filter' text box.");
    waitForSecs(3);
    Button btnFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.FILTERS), this.timeInSecs)
            .getFirstElement();
    btnFilter.click();
    LOGGER.LOG.info("Clicked on 'Filter' button.");
    this.driverCustom.isElementInvisible(RQMConstants.RQMCONSTRUCTIONPAGE_LOADING_MESSAGE_XPATH, this.timeInSecs);
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Delete artifact from Browse Plans/ Browse Test Cases... page
   *
   * @param artifactName name or id of artifact
   * @param drdOption option which will get after clicking Actions menu.
   * @param dialogName name of the dialog box.
   */
  public void filterAndDeleteRqmArtifact(final String artifactName, final String drdOption, final String dialogName) {
    waitForPageLoaded();
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.FILTER_TEXTBOX_XPATH,
          RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER);
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
      LOGGER.LOG.info("Input test case into search text box - " + artifactName);
      this.driverCustom.switchToDefaultContent();
      Button btnFilter = this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.FILTERS), Duration.ofSeconds(5))
          .getFirstElement();
      btnFilter.click();
      LOGGER.LOG.info("Clicked on Filter button.");
      waitForSecs(5);
      try {
        waitForSecs(5);
        WebElement rqmArtifact = this.driverCustom.getPresenceOfWebElement(
            "//div[@dojoattachpoint='tableContainerFormNode'][1]//a[text()='" + artifactName + "']/../..//td[1]");
        // Scroll into view when Artifacts is out of bounds
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", rqmArtifact);
        rqmArtifact.click();
//        this.driverCustom.getWebElement("//div[@dojoattachpoint='tableContainerFormNode'][1]//a[text()='"+artifactName+"']/../..//td[1]").click();
        LOGGER.LOG.info(artifactName + " Check box selected.");
      }
      catch (Exception e) {
        // TODO: handle exception
      }
      waitForSecs(2);
      try {
        Row rowArtifact =
            this.engine.findElementWithDuration(Criteria.isRow().containsText(artifactName), Duration.ofSeconds(5)).getFirstElement();
        Cell cllAction =
            this.engine.findElement(Criteria.isCell().inContainer(rowArtifact).withIndex(2)).getFirstElement();
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(2));
        Dropdown drdAction =
            this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Actions").inContainer(cllAction), Duration.ofSeconds(5))
                .getFirstElement();
        drdAction.selectOptionWithText(drdOption);
      }
      catch (Exception e) {
        this.driverCustom.getPresenceOfWebElement("//div[@class='row-action-cell']//img[@alt='Actions']").click();

        waitForSecs(5);
        this.driverCustom.click("//td[text()='" + drdOption + "']");
      }
      LOGGER.LOG.info(drdOption + " is selected from drop down successfully.");
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(2));
      Button btnDelete =
          this.engine.findElementWithDuration(Criteria.isButton().withText(RQMConstants.DELETE), Duration.ofSeconds(5)).getFirstElement();
      btnDelete.click();
      LOGGER.LOG.info("Clicked on Delete button.");
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
      Button btnClearFilter = this.engine
          .findElement(Criteria.isButton().withToolTip(RQMConstants.CLEAR_FILTER_TEXT_TOOLTIP)).getFirstElement();
      btnClearFilter.click();
      LOGGER.LOG.info("Clicked on Clear Filter Text button.");
    }
    catch (Exception e) {
      throw new WebAutomationException("Cannot select '" + drdOption + "' in '" + dialogName + "' dialog of the '" +
          artifactName + "' test artifact row.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Test data"
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Delete a test Data by browsing the test data. Define new because of being unale to reuse filterAndDeleteRqmArtifact
   *
   * @param testDataName name of the test data.
   */
  public void filterAndDeleteTestData(final String testDataName) {
    waitForPageLoaded();
    Text txtSearch = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder(RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER),
        this.timeInSecs).getFirstElement();
    txtSearch.setText(testDataName);
    Button btnFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.FILTERS), this.timeInSecs)
            .getFirstElement();
    btnFilter.click();

    Row rowTestData = this.engine.findElementWithDuration(Criteria.isRow().containsText(testDataName), this.timeInSecs)
        .getFirstElement();
    Cell cllCheckbox =
        this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowTestData).withIndex(1), this.timeInSecs)
            .getFirstElement();
    Checkbox cbxTestData = this.engine
        .findElementWithDuration(Criteria.isCheckbox().inContainer(cllCheckbox), this.timeInSecs).getFirstElement();
    cbxTestData.click();
    clickOnJazzImageButtons("Delete");

    // Click Delete on Confirmation dialog
    clickOnJazzButtons("Delete");
  }

  /**
   * <p>
   * Open test artifacts page by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Open browse web page for any of the artifacts like Test Scripts,Test Cases,Keywords,Test Suites etc.. <br>
   * <br>
   * Click on the searched artifact.
   *
   * @param artifactName name of the test cases or test scripts or keywords etc...
   */
  public void selectRqmArtifact(final String artifactName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementClickable(RQMConstants.RQMCONSTRUCTIONPAGE_ARTIFACTROW_ID_XPATH, timeInSecs,
        artifactName)) {
      WebElement lnkArtifact = this.driverCustom
          .getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_ARTIFACTROW_ID_XPATH, artifactName);
      try {
        lnkArtifact.click();
      }
      catch (Exception e) {
        JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
        // Scroll down 40px double height of footer
        js.executeScript("window.scrollBy(0,40)");
        lnkArtifact.click();
      }
    }
    else {
      try {
        Link lnkArtifactName = this.engine
            .findElementWithDuration(Criteria.isLink().withText(artifactName), this.timeInSecs).getFirstElement();
        lnkArtifactName.click();
        LOGGER.LOG.info("Clicked on - " + artifactName);
        waitForSecs(5);
      }
      catch (Exception e) {
        throw new WebAutomationException("Searched artifact : " + artifactName +
            " not available.Please check for the valid artifact.\n" + "or\n" + e.getMessage() + e.getMessage());
      }
    }
  }

  /**
   * <p>
   * Search test data using {@link #selectRqmArtifact(String)}. <br>
   * Click on the searched test data.
   *
   * @param testDataName name of the test data link
   */
  public void selectTestData(final String testDataName) {
    waitForPageLoaded();
    try {
      this.driverCustom.getWebElement(RQMConstants.TEST_DATA_LINK_XPATH, testDataName).click();
      LOGGER.LOG.info("Clicked on the rqm artifact - " + testDataName);
    }
    catch (Exception e) {
      throw new WebAutomationException(
          testDataName + " - not found.Please check for the valid input.\n" + "or\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Open "Construction" menu using {@link #openMainMenuByMenuTitle(String)}. <br>
   * Do some changes in any of the artifacts. Create or edit test artifacts. E.G
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * <br>
   * Click on save button, Save button disable after save successfully
   *
   * @return true if Save button disable or else it will return false.
   */
  public boolean isSavedSuccessfully() {
    waitForPageLoaded();
    return !this.driverCustom.isElementClickable("//div[@dojoattachpoint='actionsAreaRight']//button[@title='Save']",
        Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
  }

  /**
   * <p>
   * Open "Construction" menu using {@link #openMainMenuByMenuTitle(String)}. <br>
   * Do some changes in any of the artifacts. Create or edit test artifacts. E.G
   * <p>
   * Open create test case page using values "Create", "Test Case" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * <br>
   * Click on save button,Saved successfully message will display. <br>
   * Verify Error message is displayed.
   * 
   * @author VDY1HC
   * @return true if Error message message displayed else it will return false.
   */
  public boolean isErrorMessageDisplay() {
    RMArtifactsPage page = new RMArtifactsPage(driverCustom);
    page.waitForLoadingMessage();
    this.driverCustom.isElementVisible(RQMConstants.RQM_LOADED_MESSAGE_XPATH, timeInSecs);
    return this.driverCustom.isElementVisible(RQMConstants.RQM_ERROR_MESSAGE_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 3)));
  }

  /**
   * <p>
   * Filter by "Add Test Scripts", "Apply all filters" using {@link #clickOnDialogToolTipButton(String, String)}
   * <p>
   * Search any test artifact by name or id using quick search test box in Dialog
   *
   * @return true if the search results not found.
   */
  public boolean isNoFoundResultAfterSearchingInDialog() {
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.VISIBLE_TABLE_MESSAGE_XPATH);
    List<WebElement> searchResults = this.driverCustom.getWebElements(RQMConstants.TABLE_MESSAGE_XPATH);
    if (!searchResults.isEmpty()) {
      Optional<WebElement> matchingOptional1 = searchResults.stream()
          .filter(x -> x.getText().startsWith(RQMConstants.TABLE_MESSAGE_NO_RESULT_IN_DIALOG_1)).findFirst();
      Optional<WebElement> matchingOptional2 = searchResults.stream()
          .filter(x -> x.getText().startsWith(RQMConstants.TABLE_MESSAGE_NO_RESULT_IN_DIALOG_2)).findFirst();
      if (matchingOptional1.isPresent() || matchingOptional2.isPresent()) {
        LOGGER.LOG.info("No result message is showing on the dialog.");
        return true;
      }
      LOGGER.LOG.info("Found result(s) is return on the dialog.");
      return false;
    }
    throw new WebAutomationException("There are no message showing on the screen.");
  }

  /**
   * <p>
   * Associate test scripts using {@link #associateTestScriptWithTestCase(String)} <br>
   * "Add Test Scripts", "Close" {@link #clickOnDialogButton(String, String)} closes add test scripts dialog. <br>
   * Verify test case is displayed as parent test case of the test script
   *
   * @param testCaseName name of the test case.
   * @return true if the test case found.
   */
  public boolean isParentTestCases(final String testCaseName) {
    waitForSecs(5);
    // Click on Left arrow if Related site is collapsed.
    WebElement siteBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
    if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", this.timeInSecs)) {
      siteBar.click();
    }
    try {
      WebElement parentElement =
          this.driverCustom.getPresenceOfWebElement(RQMConstants.TITLE_CONTAINER_XPATH, "Parent Test Cases");
      if (parentElement.getAttribute("aria-pressed").equalsIgnoreCase("false")) {
        parentElement.click();
        LOGGER.LOG.info("Clicked on the Parent Test Cases section.");
      }
      return isAttachmentAdded(testCaseName);
    }
    catch (Exception e) {
      throw new WebAutomationException(
          "Parent Test Cases section of the test  script is NOT found.\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using "Quality Objectives". <br>
   * Click on "Add Quality Objectives" tool tip button. Select quality objective and click on ok.
   *
   * @param qualityObjective is name or description of quality objective
   */

  public void addQualityObjectives(final String qualityObjective) {
    waitForPageLoaded();
    Button btnAddQualityObjectives =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Add Quality Objectives"), this.timeInSecs)
            .getFirstElement();
    btnAddQualityObjectives.click();
    LOGGER.LOG.info("Add Quality Objectives button is clicked.");
    waitForSecs(5);
    Dialog dlgSelectQualityObjectives =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select Quality Objectives"), this.timeInSecs)
            .getFirstElement();
    Row rowObjective = this.engine
        .findElementWithDuration(
            Criteria.isRow().containsText(qualityObjective).inContainer(dlgSelectQualityObjectives), this.timeInSecs)
        .getFirstElement();
    rowObjective.click();
    Button btnOKSelectQualityObjectives = this.engine
        .findElement(Criteria.isButton().withText("OK").inContainer(dlgSelectQualityObjectives)).getFirstElement();
    btnOKSelectQualityObjectives.click();
    LOGGER.LOG.info(qualityObjective + " quality row is selected successfully.");
  }

  /**
   * <p>
   * Add quality objectives {@link #addQualityObjectives(String)} <br>
   * Verify the Quality Objective is displayed or not
   *
   * @param qualityObjective is name or description of quality objective
   * @return true if quality objective is displayed, false if quality objective is not displayed
   */
  public boolean isQualityObjectiveDisplayed(final String qualityObjective) {
    waitForPageLoaded();
    Row rowObjectiveVerify = null;
    try {
      rowObjectiveVerify = this.engine
          .findElementWithDuration(Criteria.isRow().withText(qualityObjective), this.timeInSecs).getFirstElement();
      LOGGER.LOG.info(qualityObjective + " is showing properly");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return rowObjectiveVerify != null;
  }


  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_ENVIRONMENTS}. <br>
   * Select "Environment Type" from the drop down menu in the "Available Environment Options" dialog.
   *
   * @param type is environment type.
   */
  public void selectEnvironmentType(final String type) {
    waitForPageLoaded();
    Dialog dlgAvailableEnvironmentOption =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.RQM_MANAGE_PLATFORM_DIALOG),
            this.timeInSecs).getFirstElement();
    Dropdown drdEnvironmentType = this.engine.findElementWithDuration(
        Criteria.isDropdown().withLabel("Environment Types").inContainer(dlgAvailableEnvironmentOption),
        this.timeInSecs).getFirstElement();
    drdEnvironmentType.selectOptionWithText(type);
    LOGGER.LOG.info(type + " is selected from 'Environment Types' dropdown successfully.");
  }

  /**
   * <p>
   * Open {@link #selectEnvironmentType(String)}. <br>
   * Select Environment Option from available options and click on "Add Select" button. <br>
   * Click on ok to close.
   *
   * @param environmentOption is available environment in the "Available Environment Options" dialog
   */
  public void selectEnvironmentOption(final String environmentOption) {
    waitForPageLoaded();
    Dialog dlgAvailableEnvironmentOption = this.engine
        .findElement(Criteria.isDialog().withTitle(RQMConstants.RQM_MANAGE_PLATFORM_DIALOG)).getFirstElement();
    Button btnAddSelect = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Add Select"), this.timeInSecs).getFirstElement();
    Dropdown drdAvailableEnvironments = this.engine
        .findElementWithDuration(
            Criteria.isDropdown().withLabel("Available").inContainer(dlgAvailableEnvironmentOption), this.timeInSecs)
        .getFirstElement();
    drdAvailableEnvironments.selectOptionWithText(environmentOption);
    btnAddSelect.click();
    LOGGER.LOG.info(environmentOption + " is added to 'Available Environment Options' successfully.");
  }

  /**
   * <p>
   * Open {@link #selectEnvironmentType(String)}. <br>
   * Un Select Environment Option from Selected options and click on "Remove Select" button.
   *
   * @param environmentOption selected to be removed in the "Available Environment Options" dialog.
   */
  public void deSelectEnvironmentOption(final String environmentOption) {
    waitForPageLoaded();
    Dialog dlgAvailableEnvironmentOption =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.RQM_MANAGE_PLATFORM_DIALOG),
            this.timeInSecs).getFirstElement();
    Button btnRemoveSelect = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Remove Select"), this.timeInSecs).getFirstElement();
    Dropdown drdSelectedEnvironment = this.engine
        .findElementWithDuration(
            Criteria.isDropdown().withToolTip("Selected").inContainer(dlgAvailableEnvironmentOption), this.timeInSecs)
        .getFirstElement();

    drdSelectedEnvironment.selectOptionWithText(environmentOption);
    btnRemoveSelect.click();
    LOGGER.LOG.info(environmentOption + " is removed from 'Available Environment Options' successfully.");
  }

  /**
   * <p>
   * Select environment option {@link #selectEnvironmentOption(String)}. <br>
   * Verify Environment is displayed or not.
   *
   * @param environmentType is environment dialog in the "Available Environment Options" dialog.
   * @param environmentOption is selected environment in the "Available Environment Options" dialog.
   * @return true if environment option is displayed, false if environment option is not displayed.
   */
  public boolean isEnvironmentDisplayed(final String environmentType, final String environmentOption) {
    waitForPageLoaded();
    Cell cellBrowser = null;
    try {
      Row rowBrowser = this.engine.findElementWithDuration(Criteria.isRow().withText(environmentType), this.timeInSecs)
          .getFirstElement();
      cellBrowser =
          this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowBrowser).withText(environmentOption),
              this.timeInSecs).getFirstElement();
      LOGGER.LOG.info(environmentOption + " is verified - showing properly");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return cellBrowser != null;
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_SUITES}. <br>
   * Click on "Add Test Suites" tool tip button present in the rights side of the table.
   */
  public void clickOnRqmAddTestSuitesButton() {
    waitForPageLoaded();
    Button btnAddTestSuites = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Add Test Suites"), this.timeInSecs).getFirstElement();
    btnAddTestSuites.click();
  }

  /**
   * <p>
   * Open "Add Test Scripts" dialog.{@link #clickOnRqmAddTestSuitesButton()} <br>
   * Add test suite to test plan.
   *
   * @param artifactName that need to add to Test Plan
   * @param dialogTitle name of dialog. Example "Add Test Suites", "Add Test Case"... This methods use to add test suite
   *          to test plan
   */
  public void associateArtifactToRqmArtifact(final String artifactName, final String dialogTitle) {
    waitForPageLoaded();
    Dialog dlgAddArtifact = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(dialogTitle), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info(dialogTitle + "is showing on the screen.");
    Button btnClearFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear all filters"), this.timeInSecs)
            .getFirstElement();
    btnClearFilter.click();
    Text txtSearch = this.engine
        .findElementWithDuration(Criteria.isTextField().inContainer(dlgAddArtifact)
            .withPlaceHolder(RQMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER_PLACEHOLDER), this.timeInSecs)
        .getFirstElement();
    txtSearch.setText(artifactName);
    LOGGER.LOG.info(artifactName + " is typed in search field.");
    Button btnFilter = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Filter").inContainer(dlgAddArtifact), this.timeInSecs)
        .getFirstElement();
    btnFilter.click();
    waitForSecs(5);
    try {
      Row rowTestSuite = this.engine.findElementWithDuration(Criteria.isRow().withText(artifactName), this.timeInSecs)
          .getFirstElement();
      Cell cllCheckbox =
          this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowTestSuite).withIndex(1), this.timeInSecs)
              .getFirstElement();
      Checkbox cbxTestSuite = this.engine
          .findElementWithDuration(Criteria.isCheckbox().inContainer(cllCheckbox), this.timeInSecs).getFirstElement();
      cbxTestSuite.click();
      LOGGER.LOG.info(artifactName + " row is checked successfully.");
    }
    catch (Exception e) {
      WebElement cbxTestCase =
          this.driverCustom.getPresenceOfWebElement("//*[text()='DYNAMIC_VAlUE']//preceding::input[1]", artifactName);
      cbxTestCase.click();
      waitForSecs(2);
    }
    Button btnAdd = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Add").inContainer(dlgAddArtifact), this.timeInSecs)
        .getFirstElement();
    btnAdd.click();
    LOGGER.LOG.info("Clicked on Add button.");
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_SCRIPTS}. <br>
   * Click on "Associate this test script with test case" image button using {@link #clickOnJazzImageButtons(String)}.
   * <p>
   * Associate test script with test case in dialog: Associate this test script with test case.
   *
   * @param testCaseName name of test case.
   */

  public void associateTestScriptWithTestCase(final String testCaseName) {
    try {
      Dialog dlgAscociate =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Associate Test Script with Test Case"),
              this.timeInSecs).getFirstElement();
      LOGGER.LOG.info("<Associate Test Script with Test Case> dialog is showing on the screen.");
      waitForSecs(5);
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQM_LABEL_TEXT_XPATH, "Associate with Existing Test Case")
          .click();
      LOGGER.LOG.info("Clicked on <Associate with Existing Test Case> radio button.");
      waitForSecs(10);
      Text txtSearch = this.engine.findElementWithDuration(
          Criteria.isTextField().inContainer(dlgAscociate).withToolTip(RQMConstants.TYPE_FILTER_TEXT_OR_ID),
          this.timeInSecs).getFirstElement();
      txtSearch.setText(testCaseName);
      LOGGER.LOG.info("Input test case into search text box - " + testCaseName);
      Button btnFilter = this.engine
          .findElementWithDuration(Criteria.isButton().withToolTip("Filter").inContainer(dlgAscociate), this.timeInSecs)
          .getFirstElement();
      btnFilter.click();
      LOGGER.LOG.info("Clicked on Filter button");
      waitForSecs(5);

      // Click on the test case
      this.driverCustom.getWebElement(RQMConstants.RADIO_TEST_ARTIFACT_XPATH, testCaseName).click();
      LOGGER.LOG.info("Clicked on " + testCaseName + "row found.");

      Button btnOK = this.engine
          .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAscociate), this.timeInSecs)
          .getFirstElement();
      btnOK.click();
      LOGGER.LOG.info("Clicked on OK button on the dialog");
    }
    catch (Exception e) {
      throw new WebAutomationException(
          "Cannot associate the test script with '" + testCaseName + "'.\n" + e.getMessage());
    }
  }


  /**
   * <p>
   * Associate test script to test case {@link #associateTestScriptWithTestCase(String)}. <br>
   * Clear's filter, Search test artifact, if the artifact found remove the association.
   *
   * @param artifactName name of the test script.
   * @param drdOption option available after clicking Actions menu.
   */
  public void removeAssociatedArtifactInRqmArtifact(final String artifactName, final String drdOption) {
    try {
      Button clearFilterbtn =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear Filter Text"), this.timeInSecs)
              .getFirstElement();
      clearFilterbtn.click();
      LOGGER.LOG.info("Clicked on 'Clear Filter Text' button");

    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    try {
      Text txtSearch = this.engine
          .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type filter text and press Enter"),
              this.timeInSecs)
          .getFirstElement();
      txtSearch.setText(artifactName + Keys.ENTER);
      LOGGER.LOG.info(artifactName + "is typed in search field.");
      waitForSecs(5);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    Row rowTestScriptWithDropdown =
        this.engine.findElementWithDuration(Criteria.isRow().withText(artifactName), this.timeInSecs).getFirstElement();
    Cell cellAction = this.engine.findElement(Criteria.isCell().inContainer(rowTestScriptWithDropdown).withIndex(2))
        .getFirstElement();
    Dropdown drdAction = this.engine.findElement(Criteria.isDropdown().inContainer(cellAction)).getFirstElement();
    drdAction.selectOptionWithText(drdOption);
    LOGGER.LOG.info(drdOption + " is selected successfully.");
    waitForSecs(3);
  }


  /**
   * <p>
   * Click on 'Construction'menu.<br>
   * Create a keyword. <br>
   * Navigate to 'Detailed Description' section. <br>
   * Add content in Detailed Description section and verify the added content.
   *
   * @param description to be added in keyword.
   * @return true if the value matches to the added content.
   */
  public boolean verifyDetailedDescription(final String description) {
    waitForPageLoaded();
    Text getDetailDescription =
        this.engine.findElementWithDuration(Criteria.isTextEditor(), this.timeInSecs).getFirstElement();
    return getDetailDescription.getText().equals(description);
  }

  /**
   * <p>
   * Click on 'Construction'menu.<br>
   * Create and delete a keyword. <br>
   * Verify keyword is deleted.
   *
   * @param artifactName name of the keyword.
   * @return true if keyword is deleted.
   */
  public boolean isRqmArtifactDisplayed(final String artifactName) {
    waitForPageLoaded();
    Link lnkRqmArtifact = null;
    try {
      lnkRqmArtifact = this.engine.findElementWithDuration(Criteria.isLink().withText(artifactName), this.timeInSecs)
          .getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lnkRqmArtifact == null;
  }

  /**
   * <p>
   * Select owner {@link #selectOwner(Map)}. <br>
   * Click on 'Construction'menu.<br>
   * <br>
   * Create a keyword. <br>
   * <br>
   * Set the Owner for the keyword and verify the owner is added.
   *
   * @param owner of the keyword,test case,test script etc...
   * @return true if the added owner is visible.
   */
  public boolean isOwnerAdded(final String owner) {
    waitForPageLoaded();
    Dropdown drpOwner = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Owner:"), this.timeInSecs)
        .getFirstElement();
    return drpOwner.getText().equals(owner);
  }

  /**
   * <p>
   * Click on 'Construction'menu.<br>
   * Create a keyword. <br>
   * Click on 'Manage Sections' link in rqm section <br>
   * Add the section and verify section is added in rqm section of the keyword.
   *
   * @param sectionName section to be added or removed.
   * @return true if section is added.
   */
  public boolean isSectionAdded(final String sectionName) {
    waitForPageLoaded();
    Link lnkTestPreparation = null;
    try {
      lnkTestPreparation = this.engine
          .findElementWithDuration(Criteria.isLink().withToolTip(sectionName), this.timeInSecs).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lnkTestPreparation != null;
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#MANAGE_SECTIONS}.
   * <p>
   * Selects available section from list of options and click on "Add" button to add.
   *
   * @param sectionName section to be added or removed.
   */
  public void addSectionsOfArtifact(final String sectionName) {
    waitForPageLoaded();
    Dialog dlgManageSections = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Manage Sections"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("'Manage Sections' dialog is showing.");
    Dropdown drdAvailableSection =
        this.engine
            .findElementWithDuration(
                Criteria.isDropdown().withLabel("Available Sections:").inContainer(dlgManageSections), this.timeInSecs)
            .getFirstElement();
    Button btnAdd = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Add").inContainer(dlgManageSections), this.timeInSecs)
        .getFirstElement();
    drdAvailableSection.selectOptionWithText(sectionName);
    LOGGER.LOG.info(sectionName + " is selected successfully.");
    btnAdd.click();
    LOGGER.LOG.info("Clicked on Add button.");
  }

  /**
   * <p>
   * Add association {@link #addSectionsOfArtifact(String)} <br>
   * Verify test scripts are added in the keyword.
   *
   * @param artifactName name of the test script.
   * @return true if test script added.
   */
  public boolean isAssociateArtifactDisplayed(final String artifactName) {
    Row rowArtifact = null;
    try {
      rowArtifact = this.engine.findElementWithDuration(Criteria.isRow().containsText(artifactName), this.timeInSecs)
          .getFirstElement();
      LOGGER.LOG.info(artifactName + " is showing properly.");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return rowArtifact != null;
  }

  /**
   * <br>
   * Add association {@link #addSectionsOfArtifact(String)} <br>
   * Verify test scripts are added in the keyword.{@link #isAssociateArtifactDisplayed(String)}
   *
   * @param testScript name of the test script.
   */
  public void selectTestScript(final String testScript) {
    waitForSecs(5);
    Row rowTestScript1 =
        this.engine.findElementWithDuration(Criteria.isRow().withText(testScript), this.timeInSecs).getFirstElement();
    Cell cllCheckbox1 = this.engine.findElement(Criteria.isCell().inContainer(rowTestScript1)).getFirstElement();
    Checkbox cbxTestScript1 =
        this.engine.findElement(Criteria.isCheckbox().inContainer(cllCheckbox1)).getFirstElement();
    cbxTestScript1.click();
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#RISK_ASSESSMENT}. <br>
   * Click on "Risk Type" image button using {@link #clickOnJazzImageButtons(String)}. <br>
   * Select Risk Type of Risk Assessment in "Add Risk" dialog.
   *
   * @param riskType select Type of Risk in the "Add Risk" dialog.
   */
  public void addRiskType(final String riskType) {
    waitForPageLoaded();
    Dialog dlgAddRisk = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.ADD_RISK_DIALOG_TITLE), this.timeInSecs)
        .getFirstElement();

    Dropdown drdRiskType =
        this.engine
            .findElementWithDuration(
                Criteria.isDropdown().withLabel(RQMConstants.RISK_TYPE_LABEL).inContainer(dlgAddRisk), this.timeInSecs)
            .getFirstElement();
    drdRiskType.selectOptionWithText(riskType);
    LOGGER.LOG.info(riskType + " is selected from Risk Type dropdown successfully.");
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#RISK_ASSESSMENT}. <br>
   * Click on "Risk Type" image button using {@link #clickOnJazzImageButtons(String)}. <br>
   * Select Risk of Risk Assessment "Add Risk" dialog.
   *
   * @param riskName select Risk in the "Add Risk" dialog.
   */
  public void addRisk(final String riskName) {
    waitForPageLoaded();
    Dialog dlgAddRisk = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.ADD_RISK_DIALOG_TITLE), this.timeInSecs)
        .getFirstElement();

    Dropdown drdRisk = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel(RQMConstants.RISK_LABEL).inContainer(dlgAddRisk),
            this.timeInSecs)
        .getFirstElement();
    drdRisk.selectOptionWithText(riskName);
    LOGGER.LOG.info(riskName + " is selected from Risk dropdown successfully.");
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#RISK_ASSESSMENT}. <br>
   * Select Likelihood of Risk Assessment in Artifact.
   *
   * @param likelihood select likelihood of Risk in the "Add Risk" dialog
   */
  public void addLikelihood(final String likelihood) {
    waitForPageLoaded();
    Button btnAddRick = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.ADD_RISK_TOOLTIP), this.timeInSecs)
        .getFirstElement();
    btnAddRick.click();
    LOGGER.LOG.info("Clicked on Add Risk button.");

    Dialog dlgAddRisk = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.ADD_RISK_DIALOG_TITLE), this.timeInSecs)
        .getFirstElement();

    Dropdown drdLikelihood =
        this.engine
            .findElementWithDuration(
                Criteria.isDropdown().withLabel(RQMConstants.LIKELIHOOD_LABEL).inContainer(dlgAddRisk), this.timeInSecs)
            .getFirstElement();
    drdLikelihood.selectOptionWithText(likelihood);
    LOGGER.LOG.info(likelihood + " is selected from Likelihood dropdown successfully.");
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#RISK_ASSESSMENT}. <br>
   * Select Impact of Risk Assessment in Artifact.
   *
   * @param impact select impact of Risk in the "Add Risk" dialog.
   */
  public void addImpact(final String impact) {
    waitForPageLoaded();

    Button btnAddRick = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip(RQMConstants.ADD_RISK_TOOLTIP), this.timeInSecs)
        .getFirstElement();
    btnAddRick.click();
    LOGGER.LOG.info("Clicked on Add Risk button.");

    Dialog dlgAddRisk = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.ADD_RISK_DIALOG_TITLE), this.timeInSecs)
        .getFirstElement();

    Dropdown drdImpact = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel(RQMConstants.IMPACT_LABEL).inContainer(dlgAddRisk),
            this.timeInSecs)
        .getFirstElement();
    drdImpact.selectOptionWithText(impact);
    LOGGER.LOG.info(impact + " is selected from Impact dropdown successfully.");
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#RISK_ASSESSMENT}. <br>
   * Enter input text to "Mitigation Action:" text box.
   *
   * @param mitigationAction input mitigation action.
   */
  public void inputMitigationAction(final String mitigationAction) {
    waitForPageLoaded();
    Dialog dlgAddRisk = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.ADD_RISK_DIALOG_TITLE), this.timeInSecs)
        .getFirstElement();

    Text txtMitigationAction = this.engine.findElementWithDuration(
        Criteria.isTextField().isMultiLine().hasLabel(RQMConstants.MITIGATION_ACTION_LABEL).inContainer(dlgAddRisk),
        this.timeInSecs).getFirstElement();
    txtMitigationAction.setText(mitigationAction);
    LOGGER.LOG.info(mitigationAction + "is entered in Mitigation Action text field.");
  }

  /**
   * <P>
   * "Add Risk" by {@link #addRisk(String)}, {@link #addLikelihood(String)}, {@link #addImpact(String)},
   * {@link #inputMitigationAction(String)}. <br>
   * Verify the Risk Assessment is displayed or not.
   *
   * @param riskName name of risk has been added.
   * @param mitigationAction content of mitigation action has inputed.
   * @return true if risk is displayed, false if risk is not displayed.
   */
  public boolean isRiskAssessmentDisplayed(final String riskName, final String mitigationAction) {
    waitForPageLoaded();
    Row rowRiskVerify = null;
    try {
      rowRiskVerify = this.engine.findElementWithDuration(Criteria.isRow().containsText(riskName), this.timeInSecs)
          .getFirstElement();
      LOGGER.LOG.info(riskName + " risk is verified - showing properly");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (rowRiskVerify != null) {
      return rowRiskVerify.getText().contains(mitigationAction);
    }
    return false;
  }

  /**
   * <p>
   * Add section by {@link #addSectionsOfArtifact(String)}. <br>
   * Removes the Selected section.
   *
   * @param sectionName that we need to remove to artifact view
   */
  public void removeSectionsOfArtifact(final String sectionName) {
    waitForPageLoaded();

    Dialog dlgManageSections =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RQMConstants.MANAGE_SECTIONS_DIALOG_TITLE),
            this.timeInSecs).getFirstElement();
    Button btnRemove =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Remove").inContainer(dlgManageSections),
            this.timeInSecs).getFirstElement();
    this.driverCustom.click(RQMConstants.SELECTED_SECTIONS_OPTION_XPATH, sectionName);
    btnRemove.click();
    LOGGER.LOG.info("Clicked on Remove button.");
  }

  /**
   * <p>
   * {@link #isAttachmentAdded(String)}
   * <p>
   * Verify visibility of uploaded file {{@link #isUploadedFileDisplayed(String)}. <br>
   * Remove the uploaded file.
   *
   * @param fileName name of the file to upload.
   */
  public void removeUploadedFile(final String fileName) {
    waitForPageLoaded();

    Row rowAttachment =
        this.engine.findElementWithDuration(Criteria.isRow().containsText(fileName), this.timeInSecs).getFirstElement();
    Cell cellCheckbox =
        this.engine.findElement(Criteria.isCell().inContainer(rowAttachment).withIndex(1)).getFirstElement();
    Checkbox cbxAttachment = this.engine.findElement(Criteria.isCheckbox().inContainer(cellCheckbox)).getFirstElement();
    cbxAttachment.click();
    LOGGER.LOG.info(fileName + " check box is checked successfully.");

    Button btnRemove = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Remove"), this.timeInSecs)
        .getFirstElement();
    btnRemove.click();
    LOGGER.LOG.info("Clicked on Remove button.");
  }

  /**
   * <p>
   * {@link #isAttachmentAdded(String)} <br>
   * Verify the uploaded file is displayed or not
   *
   * @param fileName example is report docx.
   * @return true if file is displayed, false if file is not displayed.
   */
  public boolean isUploadedFileDisplayed(final String fileName) {
    waitForPageLoaded();

    Link lnkTestPreparation = null;
    try {
      lnkTestPreparation =
          this.engine.findElementWithDuration(Criteria.isLink().withText(fileName), this.timeInSecs).getFirstElement();
      LOGGER.LOG.info(fileName + " row is verified - showing");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lnkTestPreparation != null;
  }

  /**
   * <p>
   * Click on tool tip button to open "Add Test Scripts" dialog {@link #clickOnJazzImageButtons(String)}
   * <p>
   * Sets id or name to test artifacts.
   *
   * @param dialog name of dialog.
   * @param value is script name or id
   */
  public void setIdOrNameToAddRQMArtifact(final String dialog, final String value) {
    String regex = "(^*\\d){4,}"; // matching the beginning of string 4+ digits
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(value);
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMQUERYPAGE_TEAMAREA_USER_ACTIONS_XPATH, dialog);
    Dialog dlgManageSections =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dialog), this.timeInSecs).getFirstElement();
    if (dlgManageSections == null) {
      throw new WebAutomationException("Dialog not found");
    }
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH);
    if (matcher.matches()) {
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH, value);
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTNAME_TEXTBOX_XPATH, "");
    }
    else {
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTID_TEXTBOX_XPATH, "");
      this.driverCustom.typeText(RQMConstants.RQMPROJECT_ADDREQUIREMENTNAME_TEXTBOX_XPATH, value);
    }
  }


  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#ATTACHMENTS}.
   *
   * @param attachment section to be added or removed.
   * @return true if section is added.
   */
  public boolean isAttachmentAdded(final String attachment) {
    waitForPageLoaded();
    Link lnkTestPreparation = null;
    try {
      lnkTestPreparation = this.engine.findElementWithDuration(Criteria.isLink().withText(attachment), this.timeInSecs)
          .getFirstElement();
      LOGGER.LOG.info(attachment + " is showing on the screen.");
      return lnkTestPreparation != null;
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lnkTestPreparation != null;
  }

  /**
   * <p>
   * {@link #isParentTestCases(String)}
   *
   * @return true if test case sections are visible.
   */
  public boolean isTestCaseSecionVisible() {
    return this.driverCustom.isElementVisible("//div[@aria-label='Sections']/ul", this.timeInSecs);
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_ITEMS}. <br>
   * Click on "Development Item" tool tip button. <br>
   * Search work item in the same default project area selected.
   *
   * @param ccmWorkItem id of the work item number.
   */
  public void addCCMWorkitemToRQMArtifact(final String ccmWorkItem) {
    addCCMWorkitemToRQMArtifact(ccmWorkItem, null);
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_ITEMS}. <br>
   * Click on "Development Item" tool tip button. <br>
   * Search work item in other project area if "ccmProjectArea" is not null.
   *
   * @param ccmWorkItem name or Id of the CCM Work item with Container.
   * @param ccmProjectArea the name of CCM project area.
   */
  public void addCCMWorkitemToRQMArtifact(final String ccmWorkItem, final String ccmProjectArea) {
    waitForPageLoaded();
    if (ccmProjectArea != null) {
      chooseProjectAreaFromArtifactContainer(ccmProjectArea);
    }
    Dialog linkDialogCriteria = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Development Item"), this.timeInSecs).getFirstElement();
    // Search and select plan link
    Text txtSearchPlanCriteria = this.engine.findElementWithDuration(Criteria.isTextField()
        .hasLabel("Work Item Number or Words Contained in the Text. Use quotes for a phrase search:")
        .inContainer(linkDialogCriteria), this.timeInSecs).getFirstElement();
    txtSearchPlanCriteria.setText(ccmWorkItem);
    LOGGER.LOG.info(ccmWorkItem + " is entered in search field.");
    // Check Global Configuration checkbox
    if (ccmProjectArea != null) {
      Checkbox cbxGlobalConfiguration = this.engine
          .findElement(
              Criteria.isCheckbox().withLabel("Show only Work Items related to the current Global Configuration"))
          .getFirstElement();
      cbxGlobalConfiguration.click();
      LOGGER.LOG
          .info("'Show only Work Items related to the current Global Configuration' checked is clicked successfully.");
    }
    waitForSecs(2);
    // Select Plan link
    Dropdown drdMatchingWorkItem =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Matching Work Items:"), this.timeInSecs)
            .getFirstElement();
    drdMatchingWorkItem.selectOptionWithPartText(ccmWorkItem);
    LOGGER.LOG.info(ccmWorkItem + " is selected successfully");
    // Click on OK button
    Button btnOKDevelopmentItem =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOKDevelopmentItem.click();
    LOGGER.LOG.info("Clicked on OK button.");
    this.driverCustom.getWebDriver().switchTo().defaultContent();
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_PLAN_LINKS}.
   * <br>
   * Click on "Development Plan Links" tool tip button. <br>
   * Search plans in the same default project area selected.
   *
   * @param developmentPlan name or Id of the development plan.
   */
  public void addDevelopmentPlanToTestPlan(final String developmentPlan) {
    addDevelopmentPlanToTestPlan(developmentPlan, null);
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#DEVELOPMENT_PLAN_LINKS}.
   * <br>
   * Click on "Development Plan Links" tool tip button. <br>
   * Search plans in other project area if "ccmProjectArea" is not null.
   *
   * @param developmentPlan name or Id of the development plan.
   * @param ccmProjectArea the name of CCM project area
   */
  public void addDevelopmentPlanToTestPlan(final String developmentPlan, final String ccmProjectArea) {
    waitForPageLoaded();
    if (ccmProjectArea != null) {
      chooseProjectAreaFromArtifactContainer(ccmProjectArea);
    }
    Dialog linkDialogCriteria =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Links"), this.timeInSecs).getFirstElement();
    // Search and select plan link
    Text txtSearchPlanCriteria = this.engine.findElementWithDuration(
        Criteria.isTextField().hasLabel("Use Words Contained in the Plan Name:").inContainer(linkDialogCriteria),
        this.timeInSecs).getFirstElement();
    txtSearchPlanCriteria.setText(developmentPlan);
    LOGGER.LOG.info(developmentPlan + " is entered in search field.");
    // Check Global Configuration checkbox
    if (ccmProjectArea != null) {
      Checkbox cbxGlobalConfiguration = this.engine
          .findElement(Criteria.isCheckbox().withLabel("Only show plans related to the current Global Configuration"))
          .getFirstElement();
      cbxGlobalConfiguration.click();
      LOGGER.LOG.info("'Only show plans related to the current Global Configuration' checked is clicked successfully.");
    }
    // Select Plan link
    Label lblDevelopmentPlanLink = this.engine
        .findElementWithDuration(Criteria.isLabel().containText(developmentPlan), this.timeInSecs).getFirstElement();
    lblDevelopmentPlanLink.click();
    LOGGER.LOG.info(developmentPlan + " is selected successfully");
    // Click on OK button
    Button btnOKDevelopmentItem =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOKDevelopmentItem.click();
    LOGGER.LOG.info("Clicked on OK button.");
    this.driverCustom.switchToDefaultContent();
  }

  /**
   * <p>
   * Set priority {@link RQMConstructionPage#selectPrority(Map)}.
   *
   * @param priority name or Id of the CCM Work item.
   * @return name or Id of the CCM Work item.
   */
  public boolean verifyPriority(final String priority) {
    Dropdown getDropdownPriority = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Priority:"), this.timeInSecs).getFirstElement();
    return getDropdownPriority.getText().equals(priority);
  }

  /**
   * <p>
   * Set priority {@link RQMConstructionPage#selectOwner(Map)}.
   *
   * @param owner name or Id of the CCM Work item.
   * @return name or Id of the CCM Work item.
   */
  public boolean verifyOwner(final String owner) {
    Dropdown getDropdownOwner = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Owner:"), this.timeInSecs).getFirstElement();
    return getDropdownOwner.getText().equals(owner);
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_CASE_DESIGN}.
   *
   * @param textContent the content of text area section
   * @return name or Id of the CCM Work item.
   */
  public boolean verifyTextAreaAdded(final String textContent) {
    this.driverCustom.getPresenceOfWebElement("//div[@xmlns]");
    WebElement getExpectedResult = this.driverCustom.getWebElement("//div[@xmlns]");
    return getExpectedResult.getText().equals(textContent);
  }

  /**
   * <p>
   * Open {@link #inputMitigationAction(String)}
   *
   * @param risk name of risk.
   * @param mitigationAction name of action.
   * @return true if quality objective is displayed, false if quality objective is not displayed
   */
  public boolean verifyMitigation(final String risk, final String mitigationAction) {
    waitForPageLoaded();
    Row rowObjectiveVerify =
        this.engine.findElementWithDuration(Criteria.isRow().withText(risk), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("Verified risk - '" + risk + "' containing mitigation - '" + mitigationAction + " is - " +
        rowObjectiveVerify.getText().contains(mitigationAction));
    return rowObjectiveVerify.getText().contains(mitigationAction);
  }

  /**
   * <p>
   * Add test data row {@link #uploadTestData(Map)}
   *
   * @param emailID : provide email ID of user need to verify
   * @param passWD :provide password of email ID belonging to the user need to verify
   * @return Boolean value. If user is exsiting -> return true
   */
  public Boolean verifyTestDataRowDisplayed(final String emailID, final String passWD) {
    Row rowData1Verify =
        this.engine.findElementWithDuration(Criteria.isRow().withText(emailID), this.timeInSecs).getFirstElement();
    Cell cellRow1 = this.engine
        .findElementWithDuration(Criteria.isCell().withText(passWD).inContainer(rowData1Verify), this.timeInSecs)
        .getFirstElement();
    LOGGER.LOG.info(emailID + " and " + passWD + " is verified - showing properly");
    return cellRow1 != null;
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_SCRIPTS}. <br>
   * Clicks on the "Create New Test Script" in New Test Script dialog
   *
   * @param additionalParams stores keys "varName", "varDescription", "varCategories", "varDescriptionContent".<br>
   *          If no required categories, add value = null for varCategories in configure file
   */
  public void createNewTestScriptInDialog(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Dialog dldNewTestScript = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("New Test Script"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("<New Test Script> dialog is showing on the screen.");
    Text txtName =
        this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Name:").inContainer(dldNewTestScript),
            this.timeInSecs).getFirstElement();
    txtName.setText(additionalParams.get(RQMConstants.VAR_NAME));
    LOGGER.LOG
        .info("Input the name of test script into Name text box - " + additionalParams.get(RQMConstants.VAR_NAME));
    Text txtDescription = this.engine
        .findElement(Criteria.isTextField().hasLabel("Description:").isMultiLine().inContainer(dldNewTestScript))
        .getFirstElement();
    txtDescription.setText(additionalParams.get(RQMConstants.VAR_DESCRIPTION));
    LOGGER.LOG
        .info("Input the description of test script in Description field - " + additionalParams.get("varDescription"));
    TextEditor txtEditorDecription =
        this.engine.findElementWithDuration(Criteria.isTextEditor(), this.timeInSecs).getFirstElement();
    txtEditorDecription.setText(additionalParams.get(RQMConstants.VAR_DESCRIPTION_CONTENT));
    LOGGER.LOG.info("Input Description test step of test script in Text Editior -  " +
        additionalParams.get("varDescriptionContent"));

    // Handle categories required fields
    if (!additionalParams.get(RQMConstants.VAR_CATEGORIES).equals("null")) {
      String[] categories = additionalParams.get(RQMConstants.VAR_CATEGORIES).split(",");
      for (String cate : categories) {
        String[] field = cate.split("=");
        String fieldName = field[0].trim();
        String fieldValue = field[1].trim();
        waitForSecs(3);
        Dropdown drdMenu1 = this.engine.findElement(Criteria.isDropdown().withLabel(fieldName)).getFirstElement();
        drdMenu1.selectOptionWithPartText(fieldValue);
        waitForSecs(3);
        LOGGER.LOG.info("selected value " + fieldValue + "in " + fieldName + " drop down successfully.");
      }
    }
    else {
      LOGGER.LOG.info("No categories required fields when creating a new test script.");
    }

    Button btnOK = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs);
    btnOK.click();
    LOGGER.LOG.info("Clicked on OK button on Dialog");
  }

  /**
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_SCRIPTS}. <br>
   * Clicks on the "Create New Test Script" in New Test Script dialog
   *
   * @param additionalParams stores keys "varName", "varDescription", "varCategories", "varDescriptionContent".<br>
   *          If no required categories, add value = null for varCategories in configure file <br>
   * @return String error Message
   */
  public String createNewTestScriptWithSameNameInDialog(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String errorMsg = "";
    Dialog dldNewTestScript = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("New Test Script"), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info("<New Test Script> dialog is showing on the screen.");
    Text txtName =
        this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Name:").inContainer(dldNewTestScript),
            this.timeInSecs).getFirstElement();
    txtName.setText(additionalParams.get(RQMConstants.VAR_NAME));
    LOGGER.LOG
        .info("Input the name of test script into Name text box - " + additionalParams.get(RQMConstants.VAR_NAME));
    Text txtDescription = this.engine
        .findElement(Criteria.isTextField().hasLabel("Description:").isMultiLine().inContainer(dldNewTestScript))
        .getFirstElement();
    txtDescription.setText(additionalParams.get(RQMConstants.VAR_DESCRIPTION));
    LOGGER.LOG
        .info("Input the description of test script in Description field - " + additionalParams.get("varDescription"));

    // Handle categories required fields
    if (!additionalParams.get(RQMConstants.VAR_CATEGORIES).equals("null")) {
      String[] categories = additionalParams.get(RQMConstants.VAR_CATEGORIES).split(",");
      for (String cate : categories) {
        String[] field = cate.split("=");
        String fieldName = field[0].trim();
        String fieldValue = field[1].trim();
        waitForSecs(3);
        Dropdown drdMenu1 = this.engine.findElement(Criteria.isDropdown().withLabel(fieldName)).getFirstElement();
        drdMenu1.selectOptionWithPartText(fieldValue);
        waitForSecs(3);
        LOGGER.LOG.info("selected value " + fieldValue + "in " + fieldName + " drop down successfully.");
      }
    }
    else {
      LOGGER.LOG.info("No categories required fields when creating a new test script.");
    }

    TextEditor txtEditorDecription =
        this.engine.findElementWithDuration(Criteria.isTextEditor(), this.timeInSecs).getFirstElement();
    txtEditorDecription.setText(additionalParams.get(RQMConstants.VAR_DESCRIPTION_CONTENT));
    LOGGER.LOG.info("Input Description test step of test script in Text Editior -  " +
        additionalParams.get("varDescriptionContent"));
    Dialog dldNewTestScripts = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("New Test Script"), this.timeInSecs).getFirstElement();
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dldNewTestScripts), this.timeInSecs)
        .getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Clicked on OK button on Dialog");
    // Version 7.0
    if (this.driverCustom.isElementVisible("//div[@class='messageArea ERROR']", Duration.ofSeconds(20))) {
      WebElement errorElement = this.driverCustom.getWebElement("//div[@class='messageArea ERROR']");
      errorMsg = errorElement.getText().trim();
    }
    // Version 6.0
    else {
      Button btnSave =
          this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();
      btnSave.click();
      WebElement errorElement = this.driverCustom.getWebElement("//div[@class='messageArea ERROR']");
      errorMsg = errorElement.getText().trim();
    }
    return errorMsg;
  }

  /**
   * <p>
   * createNewTestScriptWithSameNameInDialog Open
   * {@link RQMConstructionPage#createNewTestScriptWithSameNameInDialog(Map)}. <br>
   * Clicks on the Cancel button after inputting same name in New Test Script dialog
   *
   * @return Boolean
   */
  public Boolean clickOnCancelButtonWhenErrorMsgDisplayed() {
    try {
      Button btnCancel = this.engine.findElementWithDuration(Criteria.isButton().withText("Cancel"), this.timeInSecs)
          .getFirstElement();
      btnCancel.click();
      LOGGER.LOG.info("Clicked on Cancel button.");
      // Click Yes on Confirmation dialog if version 6.0
      if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTPLANQUALITYOBJ_OKBUTTON_XPATH, Duration.ofSeconds(20), "Yes")) {
        Button btnYes =
            this.engine.findElementWithDuration(Criteria.isButton().withText("Yes"), this.timeInSecs).getFirstElement();
        btnYes.click();
        LOGGER.LOG.info("Clicked on Yes button on Dialog");
      }
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * <p>
   * Switch to any of the frame {@link WebDriverCustom#switchToFrame(int)}.
   **/
  public void switchToDefaultContent() {
    this.driverCustom.getWebDriver().switchTo().defaultContent();
  }

  /**
   * <p>
   * Add review {@link #formalReviewSection(Map)}.
   * <p>
   * Verify reviewer is added or not.
   *
   * @param reviewerName name of reviewer.
   * @return true if found user.
   */
  public boolean verifyReview(final String reviewerName) {
    return this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_DUP_TESTSUITE_CHECKBOX_XPATH, Duration.ofSeconds(10), reviewerName);
  }

  /**
   * <p>
   * Add step {@link #addStepValuesInTestScript(Map)} or {@link #addStepValuesInNewTestScript(Map)}.
   *
   * @param step is added or not.
   * @param descriptionOrExpectResult we can choose 'Description' or 'Expected Results'
   * @return content of 'Description' or 'Expected Results'
   */
  public String validateAddedStep(final String step, final String descriptionOrExpectResult) {
    waitForPageLoaded();
    waitForSecs(2);
    String content = "";
    if (descriptionOrExpectResult.equals("Description")) {
      if (step.equals("1")) {
        WebElement iframeSwitch = this.driverCustom.getWebElement("//div[@class='script-step-container']//iframe");
        this.driverCustom.getWebDriver().switchTo().frame(iframeSwitch);
        WebElement descriptionfirst = this.driverCustom.getWebElement("//html[@dir='ltr']//div[@xmlns]");
        content = descriptionfirst.getText();
        this.driverCustom.getWebDriver().switchTo().parentFrame();
      }
      else {
        int stepNumber = Integer.parseInt(step);
        stepNumber--;
        WebElement description = this.driverCustom.getWebElement(
            "(//div[contains(@aria-label,'Description')]//div[@xmlns])[" + Integer.toString(stepNumber) + "]");
        // Scroll into view
        ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
            description);
        content = description.getText();
      }
    }
    else {
      WebElement description = this.driverCustom
          .getWebElement("(//div[contains(@aria-label,'Expected Results')]//div[@xmlns])[" + step + "]");
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
          description);
      content = description.getText();
    }
    return content;
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the Test Case. <br>
   * Click on 'Add Links' icon, 'Artifact Container selection' wizard will display. <br>
   * Choose appropriate 'RMProjectArea' from 'Artifact Container selection' wizard.
   *
   * @param RMProjectArea name of the RM project area to be added in the 'Artifact Container'.
   */
  public void chooseProjectAreaFromArtifactContainer(final String RMProjectArea) {
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,  Duration.ofSeconds(30),
        "Artifact Container selection")) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
          "Artifact Container selection");
      Dropdown drdArtifactContainer = this.engine
          .findElement(Criteria.isDropdown().withLabel(
              "More than one artifact container is configured, please select the one you want to connect with"))
          .getFirstElement();
      drdArtifactContainer.selectOptionWithPartText(RMProjectArea);
      LOGGER.LOG.info("Selected the '" + RMProjectArea + "' from 'Artifact Container selection' dialog.");
      Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
      btnOK.click();
      LOGGER.LOG.info("Clicked on 'OK' button from 'Artifact Container selection' dialog.");
    }
  }

  /**
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the Test Case. <br>
   * Click on 'Add Links' icon, 'Artifact Container selection' wizard will display. <br>
   * Choose appropriate 'RMProjectArea' from 'Artifact Container selection' wizard. <br>
   * Select Component for RM artifact <br>
   *
   * @param RMComponent - name of the RM artifact component.
   */
  public void chooseComponentFromArtifactSelection(final String RMComponent) {
    waitForSecs(2);
    this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='iframe']");
    if (this.driverCustom.isElementVisible("//select[@aria-label='Component:']", Duration.ofSeconds(30))) {
      if (this.driverCustom.isElementVisible("//select[@aria-label='Component:' and (@disabled)]/option",  Duration.ofSeconds(10))) {
        LOGGER.LOG.info("Component selection Drop down is disabled.");
      }
      else {
        Dropdown drdComponent =
            this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Component:"),  Duration.ofSeconds(10)).getFirstElement();
        drdComponent.selectOptionWithText(RMComponent);
        LOGGER.LOG.info("Selected component from 'Artifact Container selection' wizard - " + RMComponent);
      }
    }
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the Test Case. <br>
   * After clicking 'Add Links' icon, choose appropriate RMProjectArea from 'Artifact Container selection' wizard. <br>
   * After selecting the project area, you will be getting 'Requirement Selection' wizard. <br>
   * Search and select the required 'Requirement' in 'Requirement Selection' wizard.
   *
   * @param artifactID ID of the artifact.
   */
  public void addRequirementInTestCase(final String artifactID) {
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='iframe']");
    Text txbSearch =
        this.engine
            .findElementWithDuration(
                Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID"),  Duration.ofSeconds(10))
            .getFirstElement();
    txbSearch.setText(artifactID + Keys.ENTER);
    LOGGER.LOG.info("Searched '" + artifactID + "' in 'Type to filter' text box.");
    Link lnkArtifactID =
        this.engine.findElementWithDuration(Criteria.isLink().withText(artifactID + ":"), Duration.ofSeconds(10)).getFirstElement();
    lnkArtifactID.getWebElement().findElement(By.xpath("./parent::a")).click();
    LOGGER.LOG.info("Clicked on searched artifact - " + artifactID);
    Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(10)).getFirstElement();
    btnOK.click();
    waitForSecs(2);
    LOGGER.LOG.info("Clicked on 'OK' button in 'Add Requirement' dialog.");
    this.driverCustom.switchToDefaultContent();
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the Test Case. <br>
   * Search any requirement in 'Type to Filter' textbox. <br>
   * Click on the searched requirement.
   *
   * @author NVV1HC
   * @param nameOrID to be clicked from 'Requirement Links' section.
   */
  public void clickOnRequirementLink(final String nameOrID) {
    waitForPageLoaded();
    String xpathRequirementLink = String.format(
        "//table[@summary='This is Requirement Links table']//a[@class='jazz-ui-ResourceLink']/div[contains(text(), '%s')]",
        nameOrID);
    this.driverCustom.isElementClickable(xpathRequirementLink, Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)));
    this.driverCustom.click(xpathRequirementLink);
    LOGGER.LOG.info("Clicked on " + nameOrID + " from 'Requirement Links' section of the Test Case.");
  }

  /**
   * <p>
   * Click on 'More Actions' icon from the requirement. <br>
   * Click on the option 'Manage Suspicion'.
   */
  public void manageSuspicion() {
    Dropdown drdMoreActions =
        this.engine.findFirstElementWithDuration(Criteria.isDropdown().withToolTip("More Actions"), this.timeInSecs);
    drdMoreActions.selectOptionWithText("Manage Suspicion");
    LOGGER.LOG.info("'Manage Suspicion' option is selected from 'More Actions' drop down.");
  }

  /**
   * <p>
   * Click on 'More Actions' icon from the requirement. <br>
   * Click on the option 'Manage Suspicion'. <br>
   * Select the required suspicion profile using Profile name.
   *
   * @param profileName name of suspicion profile.
   */
  public void chooseSuspicionProfile(final String profileName) {
    List<Checkbox> cbxList = this.engine
        .findElementWithDuration(Criteria.isCheckbox().withLabel(profileName), this.timeInSecs).getElementList();
    for (int i = 0; i < cbxList.size(); i++) {
      if (!cbxList.get(i).getWebElement().isSelected() && cbxList.get(i).getWebElement().isDisplayed()) {
        cbxList.get(i).click();
        LOGGER.LOG.info("Clicked on checbox of " + profileName + ".");
      }
    }
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the Test Case. <br>
   * Search any requirement in 'Type to Filter' textbox. <br>
   * Select the searched requirement.
   *
   * @param artifactID id of the artifact to be searched.
   */
  public void selectTheSearchedArtifact(final String artifactID) {
    try {
      Row row = this.engine.findElement(Criteria.isRow().containsText(artifactID)).getFirstElement();
      Cell cell = this.engine.findElement(Criteria.isCell().inContainer(row)).getFirstElement();
      Checkbox checkbox = this.engine.findElement(Criteria.isCheckbox().inContainer(cell)).getFirstElement();
      checkbox.click();
      LOGGER.LOG.info("Clicked on the checkbox present in the row to select the artifact - " + artifactID);
      waitForSecs(2);
    }
    catch (Exception e) {
      throw new InvalidArgumentException(
          artifactID + " could not found.Please check for the valid input.\n" + "or\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the Test Case. <br>
   * Click on 'Create Requirement' icon, 'Create Requirement' wizard will display. <br>
   * Provide necessary details and create the requirement.
   *
   * @param additionalParams contains list of key value pair data.
   */
  public void createNewRequirement(final Map<String, String> additionalParams) {
    Dialog createRequirementDialog =
        this.engine.findElement(Criteria.isDialog().withTitle("Create new Requirement")).getFirstElement();
    LOGGER.LOG.info("'Create new Requirement' dialog displayed.");
    // Input requirement initial content and automatically fills name textfield too.
    Text txtInitalContent =
        this.engine.findElement(Criteria.isTextField().hasLabel(RMConstants.NAME).inContainer(createRequirementDialog))
            .getElementByIndex(2);
    txtInitalContent.setText(additionalParams.get("Requirement Name"));
    LOGGER.LOG.info("Requirement name is provided with value - " + additionalParams.get("Requirement Name"));
    // Select requirement Type
    Dropdown drdRequirementType = this.engine
        .findFirstElement(Criteria.isDropdown().withLabel("Artifact type:").inContainer(createRequirementDialog));
    drdRequirementType.selectOptionWithText(additionalParams.get("Artifact type"));
    LOGGER.LOG.info("Requirement type is added with value - " + additionalParams.get("Artifact type"));
    // Select requirement Format
    try {
      Dropdown drdRequirementFormat = this.engine
          .findFirstElement(Criteria.isDropdown().withLabel("Artifact format:").inContainer(createRequirementDialog));
      drdRequirementFormat.selectOptionWithText(additionalParams.get("Artifact format"));
      LOGGER.LOG.info("Requirement format is added with value - " + additionalParams.get("Artifact format"));
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    this.driverCustom.switchToFrame(CCMConstants.NEW_TESTCASE_FRAME_XPATH);
    // Click on OK button
    try {
      Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(120)).getFirstElement();
      btnOK.click();
      LOGGER.LOG.info("Clicked on 'OK' button from 'Create Requirement' dialog.");
      this.driverCustom.getWebDriver().switchTo().defaultContent();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    waitForSecs(10);
  }

  /**
   * <p>
   * Click on the 'Reconcile Requirement' icon from 'Requirement Links' section of the test case. <br>
   * 'Reconcile Requirement' wizard will display. <br>
   * Search the requirement in 'Type filter text and Press Enter' text box. <br>
   * Select the requirement from 'Reconcile Requirement' window.
   *
   * @param artifactID id of the artifact.
   */
  public void selectTheRequirementForReconcile(final String artifactID) {
    try {
      Dialog dlg = this.engine.findElement(Criteria.isDialog().withTitle("Reconcile Requirements")).getFirstElement();
      Row row = this.engine.findElement(Criteria.isRow().withText(artifactID).inContainer(dlg)).getFirstElement();
      Checkbox checkbox = this.engine.findElement(Criteria.isCheckbox().inContainer(row)).getElementByIndex(1);
      checkbox.click();
      LOGGER.LOG.info("Clicked on the checkbox present in the row - " + artifactID);
      waitForSecs(2);
    }
    catch (Exception e) {
      throw new InvalidArgumentException(
          artifactID + " could not found.Please check for the valid input.\n" + "or\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the test case. <br>
   * Click on 'Select...' button from 'Requirement Links' section. <br>
   * Select the option to select or deselect all the requirements.
   *
   * @param option to select or deselect all the requirement.
   */
  public void selectAllRequirements(final String option) {
    Button btnSelect = this.engine.findElement(Criteria.isButton().withToolTip("Select...")).getFirstElement();
    btnSelect.click();
    LOGGER.LOG.info("Clicked on button 'Select...'.");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, option);
    this.driverCustom.click(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, option);
    LOGGER.LOG.info("Clicked on provided option - " + option);
  }

  /**
   * <p>
   * Click on the 'Reconcile Requirement' icon from 'Requirement Links' section of the test case. <br>
   * 'Reconcile Requirement' wizard will display. <br>
   * Check for the availability of the requirement which is reconciled.
   *
   * @return requirement for reconcile.
   */
  public String getReconcileRequirement() {
    LOGGER.LOG.info("Get the reconciled requirement.");
    return this.driverCustom.getWebElement("//td[@class='non-clip-cell-alignment']//a").getText();
  }

  /**
   * <p>
   * Click on the 'Reconcile Requirement' icon from 'Requirement Links' section of the test case. <br>
   * Select any of the requirement. <br>
   * Make the selected requirement as 'Mark Suspect'.Click on 'Finish' button. <br>
   * Check requirement is marked as Suspected item in the test case with suspicion icon.
   *
   * @param artifactIDName requirement which contains ID and Name.
   * @param suspectStatus suspect contributor status of test case.
   * @return true if provided artifactIDName is visible
   */
  public boolean getSuspectStatusOfRequirement(final String artifactIDName, final String suspectStatus) {
    LOGGER.LOG.info("Get the suspect status of the requirement - " + artifactIDName);
    return this.driverCustom.isElementVisible(
        "//div[text()='DYNAMIC_VAlUE']/../../../preceding-sibling::td//img[@title='" + suspectStatus + "']", Duration.ofSeconds(10),
        artifactIDName);
  }

  /**
   * <p>
   * Create Requirement in 'Requirement Links' section. <br>
   * Get the created requirement.
   *
   * @return created requirement.
   */
  public String getRequirement() {
    LOGGER.LOG.info("Get the requirement from Requirement Links table.");
    return this.driverCustom.getText(
        "//table[@summary='This is Requirement Links table']//tr[contains(@name,'_table_row_for_external')]//a/div");
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the test case. <br>
   * Click on 'Reconcile Requirement' icon from 'Requirement Links' section,'Reconcile Requirement' window will display.
   * <br>
   * Select the option to select or deselect all the requirements for reconcile.
   *
   * @param option to select or deselect all the requirement for reconcile.
   */
  public void selectAllRequirementsForReconcile(final String option) {
    Dialog dlgReconcile =
        this.engine.findElement(Criteria.isDialog().withTitle("Reconcile Requirements")).getFirstElement();
    LOGGER.LOG.info("Dialog 'Reconcile Requirements' is displayed.");
    Button btnSelect = this.engine.findElement(Criteria.isButton().withToolTip("Select...").inContainer(dlgReconcile))
        .getFirstElement();
    btnSelect.click();
    LOGGER.LOG.info("Clicked on button 'Select...'.");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, option);
    this.driverCustom.click(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, option);
    LOGGER.LOG.info("Clicked on provided option - " + option);
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}. <br>
   * Wait until construction menu is visible.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH,
        RQMConstants.CONSTRUCTION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH,
          RQMConstants.CONSTRUCTION);
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }

  }

  /**
   * This method to remove the link in Requirement Links of Test Case. Precondition: user stay at Requirement Links of
   * Test Case from Construction Page in RQM
   *
   * @param requirementId Id of QM resources
   * @return true if the link is removed, otherwise return false
   */
  public boolean removeLinksFromRequirementLinks(final String requirementId) {
    try {
      searchRqmArtifactsInFilterTextBox(requirementId);
      selectTheSearchedArtifact(requirementId);
      Button btnDelete = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Remove links"),  Duration.ofSeconds(10));
      btnDelete.click();
      clickOnDialogButton("Confirmation", "Remove");
      clickOnButtons("Save");
      // Wait for loading completed
      String progressXpath = "//div[@class='progress-image']";
      this.driverCustom.isElementVisible(progressXpath, Duration.ofSeconds(5));
      this.driverCustom.isElementInvisible(progressXpath, Duration.ofSeconds(5));
      LOGGER.LOG.info(String.format("Link '%s' is removed successfully", requirementId));
      return true;
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
      return false;
    }
  }

  /**
   * This method to add new links from 'Requirement Links' section of Test Case
   */
  public void clickOnAddNewLinksFromRequirementLinks() {
    waitForSecs(3);
    clickOnJazzImageButtons("Add new links");
    LOGGER.LOG.info("Clicked on Button 'Add new links'");
  }

  /**
   * This method to select value inside Product drop-down
   *
   * @param ProductValue Select value of Product: field
   */
  public void selectProductValue(final String ProductValue) {
    waitForSecs(3);
    this.driverCustom.getWebElement("//ul[@class='entries']//span[text()='Summary']").click();
    waitForSecs(3);
    Dropdown drdMenu1 = this.engine.findElement(Criteria.isDropdown().withLabel("Product:")).getFirstElement();
    drdMenu1.selectOptionWithPartText(ProductValue);
  }

  /**
   * <p>
   * Search requirement with name or ID
   * <p>
   *
   * @author NVV1HC
   * @param nameOrID name or ID of requirement to be searched
   */
  public void filterRequirementLinks(final String nameOrID) {
    Button clearFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear Filter Text"), this.timeInSecs)
            .getFirstElement();
    clearFilter.click();
    waitForSecs(2);
    Text searchTextBox =
        this.engine.findElementWithDuration(Criteria.isTextField().withToolTip("Type filter text and press Enter"),
            this.timeInSecs).getFirstElement();
    searchTextBox.setText(nameOrID + Keys.ENTER);
    waitForSecs(5);
    LOGGER.LOG.info("Search requirement '" + nameOrID + "'");
  }

  /**
   * In Rqm Artifact details page, clear all the existing data and input with new description <br>
   * If new description is change into blank, new description should set as null
   *
   * @author VDY1HC
   * @param newDescription - new description, null if expected blank description
   */
  public void editDescriptionRqmArtifact(final String newDescription) {
    WebElement txbDescription =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_DESCRIPTION_TEXTBOX_XPATH);
    this.driverCustom.clickUsingActions(txbDescription);
    waitForSecs(2);
    txbDescription.sendKeys(Keys.CONTROL, "a");
    txbDescription.sendKeys(Keys.DELETE);
    if (!newDescription.equalsIgnoreCase("null")) {
      txbDescription.sendKeys(newDescription);
    }
    Button btnSave =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();
    btnSave.click();
  }

  /**
   * In Rqm Artifact details page, clear all the existing data and input with new description <br>
   * If new description is change into blank, new description should set as null
   *
   * @author VDY1HC
   * @param newDescription - new description, null if expected blank description
   */
  public void editDescriptionRqmArtifactWithoutSave(final String newDescription) {
    WebElement txbDescription =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_DESCRIPTION_TEXTBOX_XPATH);
    this.driverCustom.clickUsingActions(txbDescription);
    waitForSecs(2);
    txbDescription.sendKeys(Keys.CONTROL, "a");
    txbDescription.sendKeys(Keys.DELETE);
    if (!newDescription.equalsIgnoreCase("null")) {
      txbDescription.sendKeys(newDescription);
    }
  }

  /**
   * In Rqm Artifact details page, get Description from Summary section.<br>
   * Rqm artifact must be saved before get description. <br>
   *
   * @author VDY1HC
   * @return currentDescription - description of RQM artifact.
   */
  public String getDescriptionOfRqmArtifact() {
    WebElement txbDescription =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_DESCRIPTION_TEXTBOX_XPATH);
    return txbDescription.getAttribute("value");
  }

  /**
   * Verify every categoies displayed on Summary section <br>
   *
   * @author VDY1HC
   * @param inputdata - special string to contains Category Name and Category Vale <br? Follow rule:
   *          <categoryName1>,<categoryValue1>;<categoryName2>,<categoryValue2>;...
   * @return true - if all the input data match expected false - if one pairs not match.
   */
  public boolean verifyAllCategoriesValue(final String inputdata) {
    String[] dataPairs = inputdata.split(";");
    for (String data : dataPairs) {
      String[] dataSplit = data.split(",");
      WebElement drdCategory = this.driverCustom
          .getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASECATEGORIES_DROPDOWN_XPATH, dataSplit[0]);
      String optionValue = drdCategory.getAttribute("value");
      String categoryValue = this.driverCustom
          .getWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_OPTION_VALUE_XPATH, optionValue).getAttribute("title");
      if (!categoryValue.equalsIgnoreCase(dataSplit[1])) {
        return false;
      }
    }
    return true;
  }

  /**
   * Verify History item with update with new value <br>
   *
   * @author VDY1HC
   * @param additionalParams - contains: HISTORY_INDEX - index of history in table ATTRIBUTE_NAME - Attribute of updated
   *          item UPDATE_TYPE - Type of update: "Insert", "Delete", "Update" ATTRIBUTE_OLD_VALUE - Old value of
   *          attribute ATTRIBUTE_NEW_VALUE - New value of attribute
   * @return true - if attribute is updated match expected.
   */
  public boolean verifyUpdateAttributeHistoryByIndex(final Map<String, String> additionalParams) {
    String index = additionalParams.get("HISTORY_INDEX");
    String expectedAttributeName = additionalParams.get("ATTRIBUTE_NAME");
    String updateType = additionalParams.get("UPDATE_TYPE");
    String changedAttributeValue = "";
    String expectedAttributeNewValue = "";
    String expectedAttributeOldValue = "";
    WebElement changedAttributeValueElement = null;
    waitForSecs(10);
    List<WebElement> historyItem = this.driverCustom.getWebElements(
        "(//div[@dojoattachpoint='_historyMainPanel']/table)[" + index + "]/following-sibling::div[1]//tr");
    for (WebElement oneItem : historyItem) {
      String changedAttributeName = this.driverCustom.getChildElement(oneItem, By.xpath("./td[1]")).getText();
      changedAttributeValueElement = this.driverCustom.getChildElement(oneItem, By.xpath("./td[2]"));
      changedAttributeValue = changedAttributeValueElement.getText();
      if (changedAttributeName.equalsIgnoreCase(expectedAttributeName)) {
        break;
      }
    }
    switch (updateType) {
      case "Update":
        expectedAttributeNewValue = additionalParams.get("ATTRIBUTE_NEW_VALUE").trim();
        expectedAttributeOldValue = additionalParams.get("ATTRIBUTE_OLD_VALUE").trim();
        if (changedAttributeValue.contains(expectedAttributeNewValue) &&
            changedAttributeValue.contains(expectedAttributeOldValue)) {
          return true;
        }
        break;
      case "Insert":
        expectedAttributeNewValue = additionalParams.get("ATTRIBUTE_NEW_VALUE").trim();
        String insertText =
            this.driverCustom.getChildElement(changedAttributeValueElement, By.xpath("./ins")).getText();
        if (insertText.equalsIgnoreCase(expectedAttributeNewValue)) {
          return true;
        }
        break;
      case "Delete":
        expectedAttributeOldValue = additionalParams.get("ATTRIBUTE_OLD_VALUE").trim();
        String removeText =
            this.driverCustom.getChildElement(changedAttributeValueElement, By.xpath("./del")).getText();
        if (removeText.equalsIgnoreCase(expectedAttributeOldValue)) {
          return true;
        }
        break;
      default:
        throw new WebAutomationException("Update type is implemented. Implemented type: Update, delete, insert");
    }
    return false;
  }

  /**
   * <p>
   * Quick search Test case or test script by using ${@link RMDashBoardPage#quickSearch(String)} Open searched
   * specification Test case or test script by using ${@link RMDashBoardPage#openSearchedSpecification(String)} Then
   * call this method to open a link displays in the right side
   * <p>
   *
   * @author NVV1HC
   * @param linkType type of link, e.g: Parent Test Plans, Related
   * @param link link to be clicked, e.g: Test suite for testing Traceability Links
   */
  public void clickOnLinkInRightSide(final String linkType, final String link) {
    waitForSecs(5);
    WebElement siteBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
    if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", this.timeInSecs)) {
      siteBar.click();
      LOGGER.LOG.info("clicked on arrow-left in right side");
    }
    String[] linkTypeAndLink = { linkType, link };
    this.driverCustom.click(RQMConstants.RQMCONSTRUCTIONPAGE_LINKINRIGHTSIDE_XPATH, linkTypeAndLink);
    LOGGER.LOG.info("clicked on '" + link + "' in '" + linkType + "'");
    waitForSecs(10);
  }

  /**
   * <p>
   * Quick search Test case or test script by using ${@link RMDashBoardPage#quickSearch(String)} Open searched. <br>
   * specification Test case or test script by using ${@link RMDashBoardPage#openSearchedSpecification(String)}. <br>
   * Then call this method to verify if the link is displayed or not. <br>
   * <p>
   *
   * @author NVV1HC
   * @param linkType type of link, e.g: Parent Test Plans, Related
   * @param link link to be clicked, e.g: Test suite for testing Traceability Links
   * @return true if link is visible or vice versa
   */
  public boolean isLinkDisplayed(final String linkType, final String link) {
    int i = 0;
    do {
      if (this.driverCustom.isElementVisible("//*[text()='Name not available']", timeInSecs)) {
        this.driverCustom.getWebDriver().navigate().refresh();
      }
      WebElement siteBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
      if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", timeInSecs)) {
        // Scrollbar obscurb element click, workaround 
        ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].click();",
            siteBar);
      }
      i++;
      if (i > 3)
        break;
    }
    while (!this.driverCustom.isElementInvisible("//*[text()='Name not available']", Duration.ofSeconds((this.timeInSecs.getSeconds() * 3))));
    String[] linkTypeAndLink = { linkType, link };
    return this.driverCustom.isElementVisible(RQMConstants.RQMCONSTRUCTIONPAGE_LINKINRIGHTSIDE_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() *3)),
        linkTypeAndLink);
  }

  /**
   * <p>
   * Search and open any existing test case. <br>
   * Run the test case and create execution record using
   * ${@link RQMConstructionPage#generateExecutionRecordAndGetResult}. <br>
   * Click on the test case displayed on the Execution Result Page.
   *
   * @param testCaseID id of the test case to be clicked.
   */
  public void clickOnTestCaseLinkFromTestExecutionResult(final String testCaseID) {
    this.driverCustom.getWebElement("//a[contains(text(),'" + testCaseID + "') and @title='Test Case']").click();
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the test case. <br>
   * Add the requirement in 'Requirement Links' section using
   * ${@link RQMConstructionPage#addRequirementInTestCase(String)}. <br>
   * Set the link validity status of the added requirement.
   *
   * @param linkValidityStatus status of Link Validity like Valid,Suspect,Invalid.
   * @param artifactID id of the linked artifact.
   */
  public void setLinkValidityStatus(final String linkValidityStatus, final String artifactID) {
    WebElement element = this.driverCustom.getWebElement(
        "//div[contains(text(),'" + artifactID + "')]/../../..//preceding-sibling::td//img[contains(@src,'validity')]");
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.moveToElement(element).doubleClick().build().perform();
    this.driverCustom.click(
        "//div[contains(text(),'" + artifactID + "')]/../../..//preceding-sibling::td//img[contains(@src,'validity')]");
    this.driverCustom.click(
        "(//table[contains(@class,'dijitMenuActive')] | //table[contains(@class,'dijitMenuSelected ')])//td[contains(text(),'DYNAMIC_VAlUE')]",
        linkValidityStatus);
    waitForSecs(5);
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the test case. <br>
   * Add the requirement in 'Requirement Links' section using
   * ${@link RQMConstructionPage#addRequirementInTestCase(String)}. <br>
   * Set the link validity status of the added requirement using
   * ${@link RQMConstructionPage#setLinkValidityStatus(String, String)}. <br>
   * Verify required link validity status is set.
   *
   * @param linkValidityStatus status of Link Validity like Valid,Suspect,Invalid.
   * @param artifactID id of the linked artifact.
   * @return status of link validity.
   */
  public boolean verifyLinkValidityStatus(final String linkValidityStatus, final String artifactID) {
    WebElement element = this.driverCustom.getWebElement(
        "//div[contains(text(),'" + artifactID + "')]/../../..//preceding-sibling::td//img[contains(@src,'validity')]");
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.moveToElement(element).build().perform();
    return this.driverCustom.isElementVisible("//div[@class='side-trim']//div[contains(text(),'DYNAMIC_VAlUE')]",
        this.timeInSecs, linkValidityStatus);
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the test case. <br>
   * Add the requirement in 'Requirement Links' section using
   * ${@link RQMConstructionPage#addRequirementInTestCase(String)}. <br>
   * Set the link validity status of the added requirement using
   * ${@link RQMConstructionPage#setLinkValidityStatus(String, String)}. <br>
   * Open browse test case web page using ${@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Search the required test case using ${@link RQMConstructionPage#searchRqmArtifactsInFilterTextBox(String)}. <br>
   * Get the validity summary of the searched test case.
   *
   * @param testCaseID id of the test case.
   * @param linkValidityStatus status of Link Validity like Valid,Suspect,Invalid.
   * @return status of validity summary.
   */
  public boolean getValiditySummary(final String linkValidityStatus, final String testCaseID) {
    String xpathValiditySummary = String.format(
        "//a[text()='%s']/parent::td/following-sibling::td//img[contains(@src,'%s')]/ancestor::div[@class='table-container-node']//div[text()='Validity Summary']",
        testCaseID, linkValidityStatus);
    return this.driverCustom.isElementVisible(xpathValiditySummary, this.timeInSecs);
  }

  /**
   * <p>
   * Open any existing test case. <br>
   * Navigate to 'Requirement Links' section of the test case. <br>
   * Click on 'Reconcile Requirements' icon from 'Requirement Links' section,'Reconcile Requirements' window will
   * display. <br>
   * Select the requirement for Reconcile and click on 'Clear Suspicion'.<br>
   * Go back to the test case and follow the above steps.<br>
   * Get the text visible after clear suspicion.
   * 
   * @return text showing after clear suspicion.
   */
  public String getClearSuspicionStatus() {
    this.driverCustom.getPresenceOfWebElement("//div[@class='info-panel']");
    return this.driverCustom.getText("//div[@class='info-panel']");
  }

  /**
   * <p>
   * Open any existing test suites. <br>
   * Navigate to 'Test Cases' section of the test suite. <br>
   * Get attribute href link from Test Case row available in Test Suite table. <br>
   * <p>
   * 
   * @author YUU3HC
   * @param testCaseID id of the test case.
   * @return text showing after clear suspicion.
   */
  public String getTestCaseLinkFromTestSuitesTable(final String testCaseID) {
    LOGGER.LOG.info("Test case link is available on Test Suite table.");
    return this.driverCustom
        .getAttribute("//table[@summary='This is Test Cases in Test Suite table']//a[.='" + testCaseID + "']", "href");
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}.
   * <p>
   * Open {@link RQMConstructionPage#openRQMSection(String)} by using {@link RQMSectionMenus#TEST_SCRIPTS}. <br>
   * Click on "Associate this test case with test suite" image button using {@link #clickOnJazzImageButtons(String)}.
   * <p>
   * Associate test case with test suite in dialog: Associate this test case with test suite.
   *
   * @author YUU3HC
   * @param testSuiteName name of test suite.
   * @param testSuiteDescription description of test suite.
   */

  public void associateTestCaseWithTestSuite(final String testSuiteName, final String testSuiteDescription) {
    try {
      this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Associate Test Case with Test Suite"),
          this.timeInSecs).getFirstElement();
      LOGGER.LOG.info("<Associate Test Case with Test Suite> dialog is showing on the screen.");
      waitForSecs(5);
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQM_LABEL_TEXT_XPATH, "Associate with New Test Suite")
          .click();
      LOGGER.LOG.info("Clicked on <Associate with New Test Suite> radio button.");
      waitForSecs(10);
      // Find <input> tag Name and click
      WebElement txtName = this.driverCustom
          .getPresenceOfWebElement("//label[text()='Name:']/../../following-sibling::td//input[@name='textField']");
      txtName.click();
      // Enter test case name
      txtName.sendKeys(testSuiteName);
      // Find <textarea> tag Description and click
      WebElement txtDescription = this.driverCustom
          .getPresenceOfWebElement("//label[text()='Description:']/../../following-sibling::td//textarea");
      txtDescription.click();
      // Enter description content
      txtDescription.sendKeys(testSuiteDescription);
      WebElement btnOK =
          this.driverCustom.getPresenceOfWebElement("//*[contains(text(),'OK') and @class='j-button-primary']");
      btnOK.click();
      LOGGER.LOG.info("Clicked on OK button on the dialog");
    }
    catch (Exception e) {
      throw new WebAutomationException("Cannot associate the test case with New Test Suite" + e.getMessage());
    }
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}.
   * <p>
   * Click on "Associate this test case with test plan" image button using {@link #clickOnJazzImageButtons(String)}.
   * <p>
   * Associate test case with test plan in dialog: Associate this test case with test plan.
   *
   * @author YUU3HC
   * @param button name to select radio button: "Associate with New Test Plan", "Associate with Existing Test Plan".
   * @param testPlanName refer to existed Test Plan ID use for associate.
   */
  public void associateTestCaseWithTestPlan(final String button, final String testPlanName) {
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds((this.timeInSecs.getSeconds() / 10)));
    if (button.equalsIgnoreCase("Associate with Existing Test Plan")) {
      try {
        // Verify dialog appeared
        Dialog dlgAscociate =
            this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Associate Test Case with Test Plan"),
                this.timeInSecs).getFirstElement();
        LOGGER.LOG.info("<Associate Test Case with Test Plan> dialog is showing on the screen.");
        // Select radio button
        this.driverCustom
            .getPresenceOfWebElement(RQMConstants.RQM_LABEL_TEXT_XPATH, "Associate with Existing Test Plan").click();
        LOGGER.LOG.info("Clicked on <Associate with Existing Test Plan> radio button.");
        // Filter by ID
        Text txtSearch = this.engine.findElementWithDuration(
            Criteria.isTextField().inContainer(dlgAscociate).withToolTip(RQMConstants.TYPE_FILTER_TEXT_OR_ID),
            this.timeInSecs).getFirstElement();
        txtSearch.setText(testPlanName);
        LOGGER.LOG.info("Input test case into search text box - " + testPlanName);
        Button btnFilter =
            this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Filter").inContainer(dlgAscociate),
                this.timeInSecs).getFirstElement();
        btnFilter.click();
        LOGGER.LOG.info("Clicked on Filter button");
        // Click on the test plan
        this.driverCustom.getWebElement(RQMConstants.RADIO_TEST_ARTIFACT_XPATH, testPlanName).click();
        LOGGER.LOG.info("Clicked on " + testPlanName + "row found.");
        // Click Ok button
        WebElement btnOK =
            this.driverCustom.getPresenceOfWebElement("//*[contains(text(),'OK') and @class='j-button-primary']");
        btnOK.click();
        LOGGER.LOG.info("Clicked on OK button on the dialog");
      }
      catch (Exception e) {
        try {
          this.driverCustom.getPresenceOfWebElement(
              "//*[contains(text(),'No items match the current filter criteria.') and @class='table-message']");
          WebElement btnCancel = this.driverCustom
              .getPresenceOfWebElement("//*[contains(text(),'Cancel') and @class='j-button-secondary']");
          btnCancel.click();
          LOGGER.LOG.info("Clicked on CANCEL button on the dialog");
        }
        catch (Exception e1) {
          throw new WebAutomationException(
              "Cannot associate the test case with '" + testPlanName + "'.\n" + e.getMessage());
        }
      }
    }
  }


  /**
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}.
   * <p>
   * Click on column "Add or Remove Columns"
   * 
   * @author GLN4HC
   * @param categoryName refer to the AddOrRemove column
   */
  public void clickOnAddorRemoveColumns(final String categoryName) {
    WebElement ele = this.driverCustom.getWebElement(RQMConstants.RQM_SELECT_COMP_XPATH, categoryName);
    ele.click();
    LOGGER.LOG.info("Clicked on '" + categoryName + "' dropdown.");
    waitForSecs(5);
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}.
   * <p>
   * Click on column "Add or Remove Columns" using {@link #clickOnAddorRemoveColumn(String)}} Check on the name of
   * column that you want to add to the table If the column is already checked, then return
   * 
   * @author GLN4HC
   * @param columnName to select the cell name of the column that you want to add
   */
  public void addColumns(final String columnName) {
    String xpathCheckedIcon =
        "//td[text()='DYNAMIC_VAlUE']/preceding-sibling::td//span[@class='dijitInline dijitIcon dijitMenuItemIcon']";
    if (this.driverCustom.isElementPresent(xpathCheckedIcon, timeInSecs, columnName)) {
      Cell cbxFunctional = this.engine.findElementWithDuration(Criteria.isCell().withText(columnName), this.timeInSecs)
          .getFirstElement();
      cbxFunctional.scrollToElement();
      cbxFunctional.click();
      LOGGER.LOG.info(columnName + "cell is selected successfully.");
    }
    else {
      LOGGER.LOG.info(columnName + "cell is already selected.");
      return;
    }
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}. Search 1 testcase Verifies the Link is displayed under the column
   * 
   * @param columnName refer to the column: Validates Architecture Elements, State, Tests Development Items,...
   * @param ID is the ID of the artifact
   * @param link refer to the existing link under the column
   * @return
   */
  public boolean isLinkDisplayedInTable(final String columnName, final String ID, final String link) {
    // get the index of column title
    this.waitForSecs(5);
    this.driverCustom.switchToDefaultContent();
    List<WebElement> lst =
        this.driverCustom.getWebElements("//table[@summary='This is Test Cases table']//th[@scope='col']");
    int index = 0;
    for (WebElement ele : lst) {
      if (ele.getText().equals(columnName)) {
        index = lst.indexOf(ele);
        break;
      }
    }
    // get the xpath of link based on the column index
    this.waitForSecs(5);
    WebElement ele2 = this.driverCustom
        .getWebElement(String.format("//td[.='DYNAMIC_VAlUE']/ancestor::tr[contains(@name,'row')]//td[%s]", index), ID);
    return this.driverCustom.isElementVisible(ele2, timeInSecs);
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}. Search 1 testcase Add the column into table by
   * {@link #addColumns(String)}}
   * <p>
   * Click on Action btn > Click on Add Link type button to open Links dialog
   * 
   * @param columnName refer to the column: Validates Architecture Elements, State, Tests Development Items,...
   * @param linkType refer to the Adding link type button: Add Architecture Element Link, Add Development Item Link,...
   */
  public void addOrRemoveLinkType(final String columnName, final String linkType) {
    // click on pencil btn and click link type, such as "Add Architecture Element Link"
    this.driverCustom.getWebElement("//img[@alt ='DYNAMIC_VAlUE' and @title='Actions']//parent::div", columnName)
        .click();
    this.driverCustom.getWebElement("//div[@class='wrapper']//td[text()='DYNAMIC_VAlUE']", linkType).click();
    this.waitForSecs(timeInSecs);
    LOGGER.LOG.info("'" + linkType + "' button is clicked successfully");
  }

  /**
   * <p>
   * Open "Construction" menu by {@link #openMainMenuByMenuTitle(String)} sub menu by "Browse", "Test Cases" by
   * {@link #openSubMenuUnderSection(String, String)}. Search 1 testcase Click on "Actions" pencil btn, select 'Add
   * Architecture Element Link' by using {@link #addOrRemoveLinkType(String, String)}}
   * <p>
   * Links Dialog is opened, select component and artifact in Link dialog > click "Ok"
   * 
   * @param componentName refer to component name
   * @param artifactName refer to the Artifact name within the Create
   */
  public void addValidatesArchitectureElementsLink(final String componentName, final String artifactName) {
    // Search the component on Links Dialog
    Dialog dlgAddLinks =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Links"), this.timeInSecs).getFirstElement();
    TextField textfield =
        this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Name contains:").inContainer(dlgAddLinks),
            this.timeInSecs).getFirstElement();
    textfield.setText(componentName);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ENTER).build().perform();
    // Select the Component name
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
    String xpathSelectComp =
        "//div[@class='com-ibm-team-rmm-SearchView']//following::*[contains(text(),'DYNAMIC_VAlUE')][1]//ancestor::tr";
    this.driverCustom.getWebElement(xpathSelectComp, componentName).click();
    LOGGER.LOG.info("Component is selected successfully");
    // Select the Artifact name
    this.driverCustom.getWebElement("//*[text()='DYNAMIC_VAlUE']//ancestor::div[1]", artifactName).click();
    LOGGER.LOG.info("Artifact is selected successfully");
    this.driverCustom.switchToDefaultContent();
  }

  /**
   * <p>
   * this method to get all content in the RQM table: ID, name,...
   * 
   * @param tableName : table in RQM page, ex: This is Test Scripts table,...
   * @return all the Items in table
   */
  public String getAllItemsFromTable(final String tableName) {
    StringBuilder sb = new StringBuilder();
    String lblNextDisabledXpath =
        "//div[@dojoattachpoint='footerPageControlNode']//a[@aria-disabled='true']//span[text()='Next']";
    try {
      waitForPageLoaded();
      WebElement lblNext =
          this.driverCustom.getWebElement("//div[@dojoattachpoint='footerPageControlNode']//span[text()='Next']");
      LOGGER.LOG.info("Get all the items in page");
      do {
        List<WebElement> linksList =
            this.driverCustom.getWebElements("//table[@summary='DYNAMIC_VAlUE']//tbody/tr", tableName);
        try {
          for (int i = 0; i < linksList.size(); i++) {
            sb.append(linksList.get(i).getText());
            sb.append(",");
          }
          lblNext.click();
        }
        // Next button may be stale
        catch (StaleElementReferenceException e) {
          lblNext = this.driverCustom.getWebElement("//div[@dojoattachpoint='footerPageControlNode']//span[text()='Next']");
          lblNext.click();
        }
      } while (this.driverCustom.isElementInvisible(lblNextDisabledXpath, Duration.ofSeconds(5)));   
      List<WebElement> linksList =
          this.driverCustom.getWebElements("//table[@summary='DYNAMIC_VAlUE']//tbody/tr", tableName);
      for (int i = 0; i < linksList.size(); i++) {
        sb.append(linksList.get(i).getText());
        sb.append(",");
        }
      }    
    catch (Exception e) {
      LOGGER.LOG.info("Items only shown on 1 page");
      List<WebElement> linksList =
          this.driverCustom.getWebElements("//table[@summary='DYNAMIC_VAlUE']//tbody/tr", tableName);
      for (int i = 0; i < linksList.size(); i++) {
        sb.append(linksList.get(i).getText());
        sb.append(",");
      }
    }
    LOGGER.LOG.info("----------------------All Items: ----------------------------------------");
    LOGGER.LOG.info("---->" + sb.toString());
    LOGGER.LOG.info("----------------------------------------------------------------------\n");
    return sb.toString();

  }

  /**
   * <p>
   * to count the number of artifacts displayed on RQM table
   * 
   * @param tableName refer to the Test Scriptss, Test Suites,... table. ex: This is Test Scripts table,...
   * @return the number of artifacts
   */
  public String countNumberOfArtifactsDisplayed(final String tableName) {
    String artifactRowXpath = "//table[@summary='DYNAMIC_VAlUE']//tbody/tr";
    int totalArtifacts = 0;
    try {
      WebElement lblNext = this.driverCustom.getWebElement(
          "//div[@dojoattachpoint='footerPageControlNode']//a[@aria-disabled='true']//span[text()='Next']");
      LOGGER.LOG.info("Counting number of artifacts displayed.");
      do {
        // Count artifacts number
        totalArtifacts += this.driverCustom.getWebElements(artifactRowXpath).size();
        lblNext.click();

      }
      while (this.driverCustom.isElementInvisible(
          "//div[contains(@style,'display: block')]//a[@dojoattachpoint='next' and @class='disable']", Duration.ofSeconds(1)));
      totalArtifacts += this.driverCustom.getWebElements(artifactRowXpath, tableName).size();
    }
    // Case only one artifact page
    catch (Exception e) {
      LOGGER.LOG.info("Only one page of artifact needed to be counted");
      totalArtifacts = this.driverCustom.getWebElements(artifactRowXpath, tableName).size();
    }
    LOGGER.LOG.info(String.format("Counted total %s artifacts displayed.", totalArtifacts));
    return String.valueOf(totalArtifacts);
  }
  /**
   * <p>
   * Open test artifacts page by "Browse", "Test Scripts" or "Browse", "Test Cases", "Browse", "Test Suites" from
   * {@link RQMConstructionPage#openSubMenuUnderSection(String, String)}. <br>
   * Delete artifact from Browse Plans/ Browse Test Cases... page
   *
   * @param artifactName name or id of artifact
   * @param drdOption option which will get after clicking Actions menu.
   * @param dialogName name of the dialog box.
   */
  public void deleteRqmArtifact(final String artifactName, final String drdOption, final String dialogName) {
    waitForPageLoaded();
      try {
        WebElement rqmArtifact = this.driverCustom.getPresenceOfWebElement(
            "//div[@dojoattachpoint='tableContainerFormNode'][1]//a[text()='" + artifactName + "']/../..//td[1]");
        // Scroll into view when Artifacts is out of bounds
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", rqmArtifact);
        rqmArtifact.click();
        LOGGER.LOG.info(artifactName + " Check box selected.");
      }
      catch (Exception e) {
        // TODO: handle exception
      }
      waitForSecs(2);
      try {
        Row rowArtifact =
            this.engine.findElementWithDuration(Criteria.isRow().containsText(artifactName),  Duration.ofSeconds(5)).getFirstElement();
        Cell cllAction =
            this.engine.findElement(Criteria.isCell().inContainer(rowArtifact).withIndex(2)).getFirstElement();
        ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(2));
        Dropdown drdAction =
            this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Actions").inContainer(cllAction), Duration.ofSeconds(5))
                .getFirstElement();
        drdAction.selectOptionWithText(drdOption);
      }
      catch (Exception e) {
        this.driverCustom.getPresenceOfWebElement("//div[@class='row-action-cell']//img[@alt='Actions']").click();

        waitForSecs(5);
        this.driverCustom.click("//td[text()='" + drdOption + "']");
      }
      LOGGER.LOG.info(drdOption + " is selected from drop down successfully.");
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(2));
      Button btnDelete =
          this.engine.findElementWithDuration(Criteria.isButton().withText(RQMConstants.DELETE), Duration.ofSeconds(5)).getFirstElement();
      btnDelete.click();
      LOGGER.LOG.info("Clicked on Delete button.");
      ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
      Button btnClearFilter = this.engine
          .findElement(Criteria.isButton().withToolTip(RQMConstants.CLEAR_FILTER_TEXT_TOOLTIP)).getFirstElement();
      btnClearFilter.click();
      LOGGER.LOG.info("Clicked on Clear Filter Text button.");
  
  }
  
  /** 
   * Open a specific Test Case, Test Script, Test Plan,....
   * Get list section items in the left menu.
   * @author NCY3HC
   * @return list of section items.
   * 
   */
  
    public List<String> getListSectionsMenuItem () {
      
      List<WebElement> list = this.driverCustom.getWebElements("//div[@aria-label='Sections']/ul[@class='entries']//a");
      List<String> values = new ArrayList<>();
      for(WebElement el:list){
          String value = el.getAttribute("title");
          values.add(value);
 
    }
      return values;
    }
    
    /**   
     *<p> This method used to select Modules in Links dialog when creating Requirement links
     *<p> Select a test case > open add Requiremnt link "Links" dialog > input the module ID
     * 
     *  @param moduleID of module
     */
    public void selectModuleInLinksDialog(final String moduleID) {
      this.driverCustom.switchToDefaultContent();
      this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='iframe']");
      this.driverCustom.isElementVisible("//span[contains(@dojoattachpoint,'Modules')]", this.timeInSecs);
      String moduleRadioBtn = "//span[contains(@dojoattachpoint,'Modules')]//input[@type='radio']";
      try {
        this.driverCustom.isElementClickable(moduleRadioBtn, Duration.ofSeconds(30));
        WebElement rdoModules = this.driverCustom.getWebElement(moduleRadioBtn);
        rdoModules.click();
        waitForSecs(Duration.ofSeconds(15));
      }
      catch(Exception e){
        LOGGER.LOG.info("No Module to select!");
      }
      String xpathModuleDropDown = "//input[contains(@id,'ArtifactInstancesFilteringSelect')][@style='color: gray;']";
      WebElement txtModules = null;
      this.driverCustom.isElementVisible(xpathModuleDropDown, Duration.ofSeconds(30));
      txtModules = this.driverCustom.getWebDriver().findElement(By.xpath(xpathModuleDropDown));
      txtModules.clear();
      txtModules.sendKeys(moduleID);
      waitForSecs(Duration.ofSeconds(10));
      txtModules.sendKeys(Keys.ENTER);
      waitForSecs(Duration.ofSeconds(10));
      this.driverCustom.switchToDefaultContent();
    }
    
    /**
     * <p>
     * Open 'Artifacts' menu using {@link RMDashBoardPage #openMenu(String)}. <br>
     * Click on 'All','Modules','Collection' link using {@link RMArtifactsPage#clickOnArtifactTypes(String)}.<br>
     * Click on 'Current Configuration' drop down present nect to Configuration name.<br>
     * Click on required option from the displayed drop down list.
     *
     * @param subMenu menu name displays after clicking 'Current Configuration' drop down 'Create Baseline...','Create
     *          Change Set...' etc.
     */
    @Override
    public void openAndSelectSubMenuInCurrentConfiguration(final String subMenu) {
      waitForPageLoaded();
      Dropdown currentConfiguration =
          this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Current Configuration"), this.timeInSecs)
              .getFirstElement();
      currentConfiguration.selectOptionWithText(subMenu);
      LOGGER.LOG.info("Click on 'Current Configuration' context menu and select " + subMenu);
      waitForSecs(2);
    }
    
  /**
   * Select Stream to delivery changes from Component to a Stream <br>
   * This method called after {@link RMDashBoardPage#clickOnCurrentConfigurationDropdownMenu(String)} with option
   * "Delivery Changes..." <br>
   *
   * @author VDY1HC
   * @param streamName - name of local stream to delivery changes
   * @param workItemName - name of work item
   * @param option - action select for merge
   * @param total - total of differences between the configurations
   */
  public void mergeChangesToAStream(final String streamName, final String workItemName, final String option,
      final String total) {
    // Select Configuration - search Stream
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(300));
    wait.pollingEvery(Duration.ofSeconds(5)).ignoring(TimeoutException.class).until(ExpectedConditions
        .visibilityOfAllElementsLocatedBy(By.xpath(RQMConstants.RQM_TYPETOSEARCH_MERGECONFIGURATION_DIALOG)));
    WebElement searchbox =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQM_TYPETOSEARCH_MERGECONFIGURATION_DIALOG);
    searchbox.click();
    searchbox.clear();
    searchbox.sendKeys(streamName + Keys.ENTER);
    WebElement searchedBaseLine =
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQM_STREAM_MERGECONFIGURATION_DIALOG, streamName);
    searchedBaseLine.click();
    // Click button Finish
    Button btnFinish = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Finish"), Duration.ofSeconds(20)).getFirstElement();
    btnFinish.click();
    wait.pollingEvery(Duration.ofSeconds(5)).ignoring(TimeoutException.class).until(
        ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='hidden' and text()='Comparing...']")));
    waitForSecs(10);
    // For loop
    List<WebElement> cbxList = driverCustom.getVisibleWebElements("//div[@class='summary-line']");
    for (int i = 0; i < cbxList.size(); i++) {
      if (this.driverCustom.isElementPresent("//div[@class='total'and text() != '0']", Duration.ofSeconds(2))) {
        Actions act = new Actions(this.driverCustom.getWebDriver());
        WebElement differentMerge =
            this.driverCustom.getPresenceOfWebElement(By.xpath("//div[@class='total' and text() = '" + total +
                "']//preceding-sibling::div[@dojoattachpoint='titleNode']"));
        waitForSecs(2);
        act.click(differentMerge).perform();
        act.sendKeys(Keys.ENTER);
        waitForSecs(2);
        selectAllRequirements("Select all items on this page");
        actionMergeConfiguration(option);
      }
    }
    // Verify Total differences between the configurations
    this.driverCustom.isElementVisible("//*[text()= 'Merged Artifacts: " + total + "']", timeInSecs);
    LOGGER.LOG.info("Merged Artifacts: '" + total + "'");
    this.driverCustom.isElementVisible("//*[text()= 'All of the changes were resolved in the target configuration.']",
        timeInSecs);
    LOGGER.LOG.info("All of the changes were resolved in the target configuration.");
  }

  /**
   * Select artifact and choose Merge Configuration option to proceed, on Compare Configuration page. <br>
   * Open 'Action' menu at a specific artifact, select 'Replace with Source','Manually Merge','Keep Target Version'
   * option.
   * 
   * @param option - value in dropdown list
   * @author YUU3HC
   */
  public void actionMergeConfiguration(final String option) {
    waitForPageLoaded();
    waitForSecs(5);
    // Open Actions dropdown menu > Click on option in dropdown list
    Dropdown drdActions = this.engine.findFirstElement(Criteria.isDropdown().withToolTip(RQMConstants.ACTIONS));
    drdActions.click();
    LOGGER.LOG.info("Clicked on Actions drop down menu.");
    Cell cellMenu =
        this.engine.findElementWithDuration(Criteria.isCell().withText(option), this.timeInSecs).getFirstElement();
    cellMenu.getWebElement().click();
    LOGGER.LOG.info("Clicked on menu option - " + option);

    // Confirmation popup
    Dialog confirmation =
        this.engine.findElement(Criteria.isDialog().withTitle(RQMConstants.CONFIRMATION)).getFirstElement();
    LOGGER.LOG.info(RQMConstants.CONFIRMATION_DIALOG_IS_DISPLAYED);
    Button btn =
        this.engine.findElement(Criteria.isButton().inContainer(confirmation).withText("OK")).getFirstElement();
    btn.click();
    LOGGER.LOG.info("Click on OK button to confirmed accept selected artifacts into the current configuration.");
    waitForSecs(5);
  }
    
  /**
   * <p>
   * Open Compare Configuration... dialog by "Current Configuration", "Compare Configuration..."
   * {@link RQMConstructionPage#openAndSelectSubMenuInCurrentConfiguration(String)}. <br>
   * Verify dialog is opened. Select View As: dropdown. Search and select the 1st configuration, depends on View As
   * selected. Search and select the 2nd configuration. Compare is active, waiting for process to complete. Get the
   * result on screen.
   * 
   * @author YUU3HC
   * @param viewAs - dropdown View As value on Select Configuration dialog
   * @param streamName - Stream name to search and select
   * @param testPlanName - Test Plan name to search and select
   * @return compare result between current stream and selected stream
   */
  public String actionCompareConfiguration(final String viewAs, final String streamName, final String testPlanName) {
    waitForPageLoaded();
    StringBuilder actualTextDisplayed = new StringBuilder();
    // Click on dialog button if another compare session is active
    String compareConfig = "Compare Configuration...";
    // Find dialog that match title
    this.driverCustom.anyMatch(By.xpath("//div[contains(@class,'jazz-ui-Dialog-heading')]"), 120,
        x -> x.getText().equalsIgnoreCase(compareConfig), "Compared Configurations... dialog not opened.");
    LOGGER.LOG.info("Verified " + compareConfig + " dialog is opened.");
    Reporter.logPass("Verified " + compareConfig + " dialog is opened.");
    waitForSecs(5);

    // Find View As: dropdown and select value
    this.driverCustom.isElementClickable("//label[text()=' View As: ']//following::div[@role='listbox']",
        Duration.ofSeconds(5));
    this.driverCustom.click("//label[text()=' View As: ']//following::div[@role='listbox']");
    this.driverCustom.getPresenceOfWebElement(String.format("//span[text()='%s']", viewAs)).click();
    LOGGER.LOG.info("Dropdown value selected for View As: " + viewAs);
    Reporter.logPass("Dropdown value selected for View As: " + viewAs);
    waitForSecs(2);

    // Search and select the 1st configuration.
    Text txtSearch = null;
    txtSearch = this.engine
        .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to search"), Duration.ofSeconds(30))
        .getFirstElement();
    txtSearch.setText(streamName);
    LOGGER.LOG.info("Searched: " + streamName);
    Reporter.logPass("Searched: " + streamName);
    // Press Enter to Search instead of find and click on search filter button
    new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ENTER).build().perform();
    Reporter.logPass("Pressed on Enter button");
    // Select Configuration
    this.driverCustom
        .click("//div[@dojoattachpoint='tableContainerFormNode']//following::div[text()='" + streamName + "']");
    LOGGER.LOG.info("Clicked to select configuration on table, with stream name as: " + streamName);
    Reporter.logPass("Clicked to select configuration on table, with stream name as: " + streamName);
    // Click on Next button
    clickOnDialogButton("Compare Configuration...", "Next");
    LOGGER.LOG.info("Clicked on button Next");
    Reporter.logPass("Clicked on button Next");

    // Search and select the 2nd configuration.
    txtSearch = this.engine
        .findElementWithDuration(Criteria.isTextField().withPlaceHolder("This is Test Plans table: filter text input"),
            Duration.ofSeconds(30))
        .getFirstElement();
    txtSearch.setText(testPlanName);
    LOGGER.LOG.info("Searched: " + testPlanName);
    Reporter.logPass("Searched: " + testPlanName);
    // Press Enter to Search instead of find and click on search filter button
    new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ENTER).build().perform();
    LOGGER.LOG.info("Pressed on Enter button");
    Reporter.logPass("Pressed on Enter button");
    waitForSecs(2);
    // Select Configuration
    this.driverCustom
        .click("//table[@summary='This is Test Plans table']//following::div[text()='" + testPlanName + "']");
    LOGGER.LOG.info("Clicked to select configuration on table, with test plan name as: " + testPlanName);
    Reporter.logPass("Clicked to select configuration on table, with test plan name as: " + testPlanName);
    // Click button Finish
    clickOnDialogButton("Compare Configuration...", "Finish");
    LOGGER.LOG.info("Clicked on button Finish");
    Reporter.logPass("Clicked on button Finish");

    // Wait until Compare is completed - 'Comparing...' status element is not found on screen.
    this.driverCustom.waitForUnclickabilityOfElement(
        "//div[@class='summary-line']/child::div[@class='status-loading' and text()='Comparing...']", timeInSecs);
    LOGGER.LOG.info("Comparing... status inactive. Compare process is completed");
    Reporter.logPass("Comparing... status inactive. Compare process is completed");

    // Get comparison result between configurations
    List<WebElement> rowsInEditor =
        this.driverCustom.getWebElements("//div[@class='summary-lines-area']//div[@class='summary-line']");
    for (WebElement row : rowsInEditor) {
      actualTextDisplayed = actualTextDisplayed.append("\n" + row.getText());
    }
    LOGGER.LOG
        .info("Compared result: Total of differences between the configurations: " + actualTextDisplayed.toString());
    Reporter
        .logPass("Compared result: Total of differences between the configurations: " + actualTextDisplayed.toString());
    return actualTextDisplayed.toString();
  }
  
  /**
   * <p>
   * Verify all artifacts are merged from source to target stream.
   * @return element present
   */
  public boolean isRqmArtifactMerged() {
    waitForPageLoaded();
    return this.driverCustom.isElementPresent(
        "//div[@dojoattachpoint='finalStatusMsgNode' and text()='All of the changes were resolved in the target configuration.']",
        timeInSecs);

  }
}