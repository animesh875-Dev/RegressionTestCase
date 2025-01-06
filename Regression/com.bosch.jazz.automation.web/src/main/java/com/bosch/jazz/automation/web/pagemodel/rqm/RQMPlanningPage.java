/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.cell.ColumnCellFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.button.JazzButtonFinder;
import finder.container.column.JazzColumnFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;

/**
 * Most basic page class of RQMPlanningPage.
 */
public class RQMPlanningPage extends AbstractRQMPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMPlanningPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzButtonFinder(), new JazzDialogFinder(),
        new RowCellFinder(), new JazzTextFinder(), new JazzColumnFinder(), new ColumnCellFinder(),
        new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(), new CheckboxFinder(),
        new DropdownFinder());
  }

  /**
   * This createSnapshot used to create a Snapshot in RQM Artifact.
   *
   * @param additionalParams use to provide data from test cases
   * @return return true if its saved
   */
  public boolean createSnapshot(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button btnSnapshot = this.engine.findElement(Criteria.isButton().withText("Create New Snapshot")).getFirstElement();
    btnSnapshot.click();
    this.driverCustom.getWebDriver().findElement(By.id("name")).sendKeys(additionalParams.get("SnapshotName"));
    this.driverCustom.getWebDriver().findElement(By.id("description"))
        .sendKeys(additionalParams.get("SnapshotDescription"));
    if (!this.driverCustom.isElementPresent(RQMConstants.RQMPROJECT_SNAPSHOTOK_BUTTON_XPATH, Duration.ofSeconds(5), "OK")) {
      Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
      btnOK.click();
      return true;
    }
    return false;
  }

  /**
   * This setToLocked used to Lock the RQM Artifact.
   *
   * @param additionalParams use to provide data for Electronic Signature
   */


  public void setToLocked(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button btnLocked = this.engine.findElement(Criteria.isButton().withToolTip("Set to locked")).getFirstElement();
    btnLocked.click();
    Dialog signDialog =
        this.engine.findElement(Criteria.isDialog().withTitle("Electronic Signature Required")).getFirstElement();
    TextField username = this.engine.findElement(Criteria.isTextField().inContainer(signDialog)).getElementByIndex(1);
    username.clearText();
    username.setText(additionalParams.get("userid"));
    TextField password = this.engine.findElement(Criteria.isTextField().inContainer(signDialog)).getElementByIndex(2);
    password.clearText();
    password.setText(additionalParams.get("userpassword"));
    WebElement txtComments =
        this.driverCustom.getWebDriver().findElement(By.xpath("//textarea[@class='dijitTextBox dijitTextArea']"));
    txtComments.sendKeys(additionalParams.get("lockComments"));
    Button okBtn =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(signDialog)).getFirstElement();
    okBtn.click();
  }

  /**
   * This setToUNLocked used to unLock the RQM Artifact.
   *
   * @param additionalParams use to provide data for Electronic Signature
   */

  public void setToUNLocked(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button btUnLocked = this.engine.findElement(Criteria.isButton().withToolTip("Set to unlocked")).getFirstElement();
    btUnLocked.click();
    Dialog signatureDialog =
        this.engine.findElement(Criteria.isDialog().withTitle("Electronic Signature Required")).getFirstElement();
    TextField username =
        this.engine.findElement(Criteria.isTextField().inContainer(signatureDialog)).getElementByIndex(1);
    username.clearText();
    username.setText(additionalParams.get("userid"));
    TextField password =
        this.engine.findElement(Criteria.isTextField().inContainer(signatureDialog)).getElementByIndex(2);
    password.clearText();
    password.setText(additionalParams.get("userpassword"));
    WebElement txtComments =
        this.driverCustom.getWebDriver().findElement(By.xpath("//textarea[@class='dijitTextBox dijitTextArea']"));
    txtComments.sendKeys(additionalParams.get("unlockComments"));
    Button okBtn =
        this.engine.findElement(Criteria.isButton().withText("OK").inContainer(signatureDialog)).getFirstElement();
    okBtn.click();
  }

  /**
   * This clickonButton handles click operation on Web Button whose html tag type is button
   *
   * @param button is used to pass the text of the Web Button
   */
  public void clickonButton(final String button) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASE_BUTTON_XPATH, button);
    this.driverCustom.click(RQMConstants.RQMPROJECT_TESTCASE_BUTTON_XPATH, button);


  }

  /**
   * This selectTestTeam is used to select Team Area in the RQM Artifact.
   *
   * @param additionalParams use to get value from the test cases
   */
  public void selectTestTeam(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Dropdown drdNewTabMenu = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Team Area:"));
    drdNewTabMenu.selectOptionWithText(additionalParams.get("testTeam"));
    clickonButton("Save");
  }

  /**
   * This removeAllReqColln used to Remove requirement links from the RQM artifact.
   *
   * @param additionalParams use to get value from the test cases
   * @param selectall used to select all items on all pages
   */
  public void removeAllReqColln(final Map<String, String> additionalParams, final String selectall) {
    selectParentCheckBoxReqLinks();
    selectAllItemsAllPages(additionalParams, selectall);
    Button btnRemoveLinks = this.engine.findElement(Criteria.isButton().withToolTip("Remove links")).getFirstElement();
    btnRemoveLinks.click();
    Button btnRemove = this.engine.findElement(Criteria.isButton().withText("Remove")).getFirstElement();
    btnRemove.click();

  }

  /**
   * @param additionalParams use to get value from the test cases
   */
  public void addTestEnvironment(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Link testEnvironmentLink =
        this.engine.findFirstElement(Criteria.isLink().withText(additionalParams.get("testenvironmentName")));
    testEnvironmentLink.click();
    Button btnManageSection = this.engine
        .findElement(Criteria.isButton().withToolTip(additionalParams.get("Add Environments"))).getFirstElement();
    btnManageSection.click();
    this.driverCustom.typeText(RQMConstants.RQMPROJECT_TESTENVIRONMENT_TEXTBOX_XPATH,
        additionalParams.get("testEnvironmentTitleName"));
    this.driverCustom.waitForSecs(Duration.ofSeconds(2));
    Text filterText = this.engine.findFirstElement(Criteria.isTextField().withToolTip("Type Filter Text"));
    filterText.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.JAZZPAGE_BUTTONS_XPATH, "OK");
    clickOnJazzButtons("OK");
    this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTCASE_BUTTON_XPATH, Duration.ofSeconds(10), "Save");
    clickonButton("Save");
  }

  /**
   * This resources used to send data to the resources
   *
   * @param additionalParams use to get value from the test cases
   */
  public void resources(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_ADDRESOURCELOCATION_BUTTON_XPATH);
    WebElement addResourceElement =
        this.driverCustom.getWebDriver().findElement(By.xpath("//a[@title='Add Resource Location']"));
    addResourceElement.click();
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Select Script Type"));
    drdMenu.selectOptionWithText(additionalParams.get("Select Script Type"));
    clickOnJazzButtons("OK");
    clickonButton("Save");
  }

  /**
   * @param additionalParams use to get value from the test cases
   */
  public void addTestSchedule(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    WebElement browseElement = this.driverCustom.getWebDriver()
        .findElement(By.xpath("//span[text()='" + additionalParams.get("browseButtonName") + "']"));
    browseElement.click();
    waitForSecs(5);
    WebElement iterationElement = this.driverCustom.getWebDriver()
        .findElement(By.xpath("//div[contains(text(),'" + additionalParams.get("iterationTitle") + "')]"));
    iterationElement.click();
    waitForSecs(2);
    clickOnJazzButtons("OK");
    this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_TESTCASE_BUTTON_XPATH, Duration.ofSeconds(10), "Save");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_TESTCASE_BUTTON_XPATH, "Save");
    waitForSecs(2);
    clickonButton("Save");
  }

  /**
   * This developmentPlanLinks used to add Development links to the RQM Artifact.
   *
   * @param additionalParams use to get value from the test cases
   */
  public void developmentPlanLinks(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Actions action = new Actions(this.driverCustom.getWebDriver());
    Button btnAddnewLinks =
        this.engine.findElement(Criteria.isButton().withToolTip(RQMConstants.ADDNEWLINKS)).getFirstElement();
    btnAddnewLinks.click();
    this.driverCustom.select(RQMConstants.RQMPROJECT_ARTIFACTCONTAINERSELECTION_DROPDOWN_XPATH,
        additionalParams.get("Artifact Container Selection"), SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
    action.sendKeys(additionalParams.get("Plan Name")).build().perform();
    while (!this.driverCustom.isElementInvisible(RQMConstants.RQMPROJECT_LINKSADDLINKS_BUTTON_XPATH, Duration.ofSeconds(10))) {
      this.driverCustom.click(RQMConstants.RQMPROJECT_MATCHINGPLANS_TEXTBOX_XPATH);
      this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_LINKSADDLINKS_BUTTON_XPATH, Duration.ofSeconds(10));
      this.driverCustom.click(RQMConstants.RQMPROJECT_LINKSADDLINKS_BUTTON_XPATH);
    }
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    clickonButton("Save");
  }

  /**
   * This addRequirementcollectionlink is use to add RM Requirements in Test Plan
   *
   * @param additionalParams use to get value from the test cases
   */
  public void addRequirementcollectionlink(final Map<String, String> additionalParams) {
    Button btnAddnewLinks = this.engine.findElement(Criteria.isButton().withToolTip("Add new links")).getFirstElement();
    btnAddnewLinks.click();
    waitForSecs(3);
    this.driverCustom.select(RQMConstants.RQMPROJECT_ARTIFACTCONTAINERSELECTION_DROPDOWN_XPATH,
        additionalParams.get("Artifact Container"), SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    clickOnJazzButtons("OK");
    waitForSecs(5);
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RMARTIFACTSPAGE_TYPETEXTTO_IFRAME_XPATH);
    this.driverCustom.isElementVisible(RQMConstants.RMARTIFACTSPAGE_TYPETEXTTO_IFRAME_XPATH, Duration.ofSeconds(5));
    this.driverCustom.typeText(RQMConstants.RMARTIFACTSPAGE_TYPETEXTTO_IFRAME_XPATH,
        additionalParams.get("Collection Selection ID"));
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.sendKeys(Keys.ENTER).build().perform();
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_ARTIFACT_XPATH);
    waitForSecs(5);
    this.driverCustom.click(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_ARTIFACT_XPATH);
    clickOnJazzButtons("OK");
    waitForSecs(2);
    clickonButton("Save");
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, Duration.ofSeconds(10), "Save")) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_SAVEREQUIREMENT_CHECKBOX_XPATH);
      this.driverCustom.click(RQMConstants.RQMPROJECT_SAVEREQUIREMENT_CHECKBOX_XPATH);
      waitForSecs(2);
      clickOnJazzButtons("OK");
      clickonButton("Save");
    }
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(3));
  }

  /**
   * This testPlanCriteria used to handle Quality Objectives, Entry & Entry Criteria in Test Plan
   *
   * @param additionalParams use to get value from the test cases
   */
  public void testPlanCriteria(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button btnAddqualityObjective =
        this.engine.findElement(Criteria.isButton().withToolTip("Add Quality Objectives")).getFirstElement();
    btnAddqualityObjective.click();
    Cell cellValue =
        this.engine.findElement(Criteria.isCell().withText(additionalParams.get("entryCriteria"))).getFirstElement();
    cellValue.click();
    this.driverCustom.getWebDriver().findElement(By.xpath("//button[text()='OK']")).click();
    clickonButton("Save");
  }

  /**
   * This normInfoDocs used to handle Normative and Informative Documents
   *
   * @param additionalParams use to get value from the test cases
   * @return True if it clicked on ok Button.
   */
  public boolean normInfoDocs(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Button btnCreateDocLink =
        this.engine.findElement(Criteria.isButton().withToolTip("Create Document Link")).getFirstElement();
    btnCreateDocLink.click();
    this.driverCustom.getWebDriver().findElement(By.xpath("//input[@dojoattachpoint='_descBox']"))
        .sendKeys(additionalParams.get("expResDes"));
    this.driverCustom.getWebDriver().findElement(By.xpath("//input[@dojoattachpoint='_linkBox']"))
        .sendKeys(this.driverCustom.getCurrUrl());
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    return this.driverCustom.isElementPresent(RQMConstants.RMARTIFACTSPAGE_SELECT_ARTIFACT_FORMAT_VALUE_XPATH, Duration.ofSeconds(5),
        additionalParams.get("expResDes"));
  }

  /**
   * This selectParentCheckBoxReqLinks used to selecting the parent checkbox in Requirement Links
   */
  public void selectParentCheckBoxReqLinks() {
    waitForPageLoaded();
    this.driverCustom.getWebDriver()
        .findElement(By.xpath("//a[@class='button' and @aria-label='Select... Drop-Down Menu']")).click();
  }

  /**
   * This verifyRemoveReqColln is used to return the Message after removing all requirement collection link.
   *
   * @param additionalParams use to get value from the test cases
   * @return Message after removing all requirement collection link.
   */
  public String verifyRemoveReqColln(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    return this.driverCustom
        .getPresenceOfWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, additionalParams.get("verifyMsg"))
        .getText();
  }

  /**
   * This selectAllItemsAllPages used to select all RQM requirement item on all pages.
   *
   * @param additionalParams use to get value from the test cases
   * @param selectall used to select all items on all pages
   */
  public void selectAllItemsAllPages(final Map<String, String> additionalParams, final String selectall) {
    waitForPageLoaded();
    this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(), new LinkFinder());
    Cell oneCell =
        this.engine.findElement(Criteria.isCell().withText(additionalParams.get(selectall))).getFirstElement();
    oneCell.click();
  }

  /**
   * This method is used to search the artifact id on all pages.
   *
   * @param artifactID is the id of the artifact to be searched.
   */
  public void searchTestSpecifications(final String artifactID) {
    waitForPageLoaded();
    waitForSecs(3);
    Button btnFilter = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear Filter Text"), timeInSecs).getFirstElement();
    btnFilter.click();
    LOGGER.LOG.info("Clicked on 'Clear Filter Text' button.");
    Text txtSearchField =
        this.engine.findFirstElementWithDuration(Criteria.isTextField().withPlaceHolder("Type filter text and press Enter"), timeInSecs);
    txtSearchField.setText(artifactID + Keys.ENTER);
    waitForSecs(30);
    LOGGER.LOG.info(artifactID + " - searched in 'Type filter text box'.");
    waitForSecs(10);
  }

  /**
   * This method is used to search the artifact id on all pages.
   *
   * @param artifactID is the id of the artifact to be searched.
   * @param dialogName dialogue name
   */
  public void searchTestSpecificationsForTestEnvironment(final String artifactID, final String dialogName) {
    waitForPageLoaded();
    waitForSecs(10);
    Dialog generateTestCaseExeRecDialog =
        this.engine.findElement(Criteria.isDialog().withTitle(dialogName)).getFirstElement();
    TextField txtSearchField = this.engine.findFirstElement(Criteria.isTextField()
        .withToolTip("Type filter text and press Enter").inContainer(generateTestCaseExeRecDialog));
    txtSearchField.setText(artifactID + Keys.ENTER);
    waitForSecs(15);
  }

  /**
   * This method is used to generate the execution record in 'test cases' sections.
   *
   * @param artifactId is the id of the artifact to be searched.
   */
  public void clickOnGenerateNewExecutionRecordButton(final String artifactId) {
    try {
      Row row = this.engine.findElement(Criteria.isRow().containsText(artifactId)).getFirstElement();
      Cell cell = this.engine.findElement(Criteria.isCell().inContainer(row)).getFirstElement();
      Checkbox checkbox = this.engine.findElement(Criteria.isCheckbox().inContainer(cell)).getFirstElement();
      checkbox.click();
      LOGGER.LOG.info("Clicked on the checkbox present in the row - " + artifactId);
      Button button =
          this.engine.findElement(Criteria.isButton().withToolTip("Generate Execution Record")).getFirstElement();
      button.click();
      LOGGER.LOG.info("Clicked on 'Generate Execution Record' button.");
      waitForSecs(5);
    }
    catch (Exception e) {
      throw new InvalidArgumentException(
          artifactId + " could not found.Please check for the valid input.\n" + "or\n" + e.getMessage());
    }
  }

  /**
   * This method is used to execute the 'generateTestEnv' executor.
   *
   * @param additionalParams adds value
   */
  public void setIterationAndTestEnvironment(final Map<String, String> additionalParams) {
    Dialog generateTestCaseExeRecDialog =
        this.engine.findElement(Criteria.isDialog().withTitle(additionalParams.get("dialogName"))).getFirstElement();
    LOGGER.LOG.info(additionalParams.get("dialogName") + " - dialog opened successfully.");

    Row row = this.engine.findElement(Criteria.isRow().withText(additionalParams.get(RQMConstants.TEST_ENV_NAME))
        .inContainer(generateTestCaseExeRecDialog)).getFirstElement();
    this.driverCustom.getPresenceOfWebElement(
        "//table[@summary='This is Generated Test Environments table']/descendant::tbody/tr/td[3]//div");
    List<WebElement> allOptions = this.driverCustom.getWebElements(
        "//table[@summary='This is Generated Test Environments table']/descendant::tbody/tr/td[3]//div");
    ArrayList<String> optionValues = new ArrayList<>();
    for (WebElement env : allOptions) {
      optionValues.add(env.getText());
    }
    if (!optionValues.contains(additionalParams.get(RQMConstants.TEST_ENV_NAME))) {
      throw new InvalidArgumentException(
          additionalParams.get("testEnvName") + " - searched test environment doesn't exist in test environment list.");
    }
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().withLabel(RQMConstants.ITERATION));
    drdMenu.selectOptionWithText(additionalParams.get(RQMConstants.ITERATION));
    Cell cell = this.engine.findElement(Criteria.isCell().inContainer(row)).getFirstElement();
    Checkbox checkbox = this.engine.findElement(Criteria.isCheckbox().inContainer(cell)).getFirstElement();
    waitForSecs(5);
    checkbox.getWebElement().click();
    LOGGER.LOG.info("Clicked on the checkbox contains row - " + additionalParams.get("testEnvName"));
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info(RQMConstants.CLICKED_ON_NEXT_BUTTON);
    waitForSecs(5);
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info(RQMConstants.CLICKED_ON_NEXT_BUTTON);
    waitForSecs(5);
    clickOnJazzButtons(additionalParams.get("finish and save"));
    LOGGER.LOG.info("Clicked on 'finish and save' button.");
    waitForSecs(5);
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, Duration.ofSeconds(10),
        RQMConstants.ALERT)) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
          RQMConstants.ALERT);
      Dialog alert = this.engine.findElement(Criteria.isDialog().withTitle(RQMConstants.ALERT)).getFirstElement();
      LOGGER.LOG.info(RQMConstants.ALERT_DIALOG_IS_DISPLAYED);
      Button btn = this.engine.findElement(Criteria.isButton().inContainer(alert).withText("OK")).getFirstElement();
      btn.click();
      LOGGER.LOG.info(RQMConstants.CLICKED_ON_OK_BUTTON_PRESENT_IN_ALERT_POP_UP);
    }
  }

  /**
   * used to scroll the page
   */
  public void pageScrollBar() {
    // For when process blocked by dialog with name 'Alert', find and close it.
    try {
      if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, Duration.ofSeconds(10),
          RQMConstants.ALERT)) {
        this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
            RQMConstants.ALERT);
        Dialog alert = this.engine.findElement(Criteria.isDialog().withTitle(RQMConstants.ALERT)).getFirstElement();
        LOGGER.LOG.info("'Alert' dialog is displayed.");
        Button btnOK = this.engine.findElement(Criteria.isButton().inContainer(alert).withText("OK")).getFirstElement();
        btnOK.click();
        LOGGER.LOG.info("Clicked on 'OK' button present in 'Alert' pop up.");
      }
    }catch (Exception e) {
      if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, Duration.ofSeconds(10),
          RQMConstants.ALERT)) {
        Button btnclose = this.engine.findElement(Criteria.isButton().withAriaLabel("close")).getFirstElement();
        btnclose.click();
      }
    }
    // Scroll page using scroll bar.
    JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
    js.executeScript("window.scrollBy(0,300)");
    LOGGER.LOG.info("Page is scrolled successfully.");
  }

  /**
   * This method is used to delete the execution recored created.
   *
   * @param testEnvName name of the environment to be deleted.
   * @param dlgName -
   */
  public void deleteTestExecutionRecord(final String testEnvName, final String dlgName) {
    waitForSecs(2);
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_HIDE_INLINE_FILTER_XPATH, Duration.ofSeconds(10))) {
      this.driverCustom.click(RQMConstants.RQMPROJECT_HIDE_INLINE_FILTER_XPATH);
      waitForSecs(2);
    }
    List<Row> rowList = this.engine.findElementWithDuration(Criteria.isRow().containsText(testEnvName), this.timeInSecs)
        .getElementList();
    List<Row> visibleRow = new ArrayList<>();
    for (Row row : rowList) {
      if (row.getWebElement().isDisplayed()) {
        visibleRow.add(row);
        try {
          Checkbox checkbox =
              this.engine.findElement(Criteria.isCheckbox().inContainer(visibleRow.get(0))).getFirstElement();
          checkbox.click();
          waitForSecs(2);
          Dropdown drdAction = this.engine
              .findElementWithDuration(Criteria.isDropdown().withToolTip("Actions").inContainer(row), this.timeInSecs)
              .getFirstElement();
          LOGGER.LOG.info("Clicked on 'Actions' drop down menu.");
          waitForSecs(2);
          drdAction.selectOptionWithText("Delete Execution Record");
          waitForSecs(2);
          Dialog dialog;
          // this.timeInSecs caused finding Dialog take long time. Increase time if fail.
          try {
            dialog = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Confirmation"), Duration.ofSeconds(2))
                .getFirstElement();
          }
          catch (Exception e) {
            dialog = this.engine.findElementWithDuration(Criteria.isDialog().withTitle(dlgName), Duration.ofSeconds(2))
                .getFirstElement();
          }

          Button deleteBtn = this.engine
              .findElementWithDuration(Criteria.isButton().inContainer(dialog).withText("Delete"), Duration.ofSeconds(2))
              .getFirstElement();
          deleteBtn.click();
          LOGGER.LOG.info("Selected menu option 'Delete Execution Record'.");
          LOGGER.LOG.info("Clicked on 'Delete' button.");
          waitForSecs(2);
          return;
        }
        catch (Exception e) {
          continue;
        }
      }
    }
    throw new WebAutomationException("Could not find row containing 'Delete' button.");
  }

  /**
   * Method used to check weather all attribute are visible in 'test case exe record tab or test Suite Exe record..'.
   *
   * @param attributeValue value of the test case execution record column value
   * @return true on success.
   */
  public boolean getExecutionRecordDetails(final String attributeValue) {
    searchTestSpecifications(attributeValue);
    waitForSecs(15);
    LOGGER.LOG.info("Searched " + attributeValue + " in 'Type To Filter text box' while creating execution record.  ");
    boolean flag = false;
    String executionRecordDetail = driverCustom.getWebElements(RQMConstants.RQM_TEST_ARTIFACT_EXECUTION_RECORD_TABLE).stream().filter(e -> e.isDisplayed()).findFirst().get().getText();
    if (this.driverCustom.isElementVisible(RQMConstants.RQM_TEST_CASE_EXECUTION_RECORD, Duration.ofSeconds((this.timeInSecs.getSeconds() / 2)), attributeValue)) {
      LOGGER.LOG.info(attributeValue + " exist in the execution record.");
      flag = true;
    }
    else if (this.driverCustom.isElementVisible("//span[text()='DYNAMIC_VAlUE']", Duration.ofSeconds((this.timeInSecs.getSeconds() / 2)),
        attributeValue)) {
      LOGGER.LOG.info(attributeValue + " exist in the execution record.");
      flag = true;
    }
    else if (this.driverCustom.isElementVisible("//div[contains(text(),'DYNAMIC_VAlUE' )]", Duration.ofSeconds((this.timeInSecs.getSeconds() / 2)),
        attributeValue)) {
      LOGGER.LOG.info(attributeValue + " exist in the execution record.");
      flag = true;
    }
    else if (executionRecordDetail.contains(attributeValue)) {
      LOGGER.LOG.info("Execution Records details : \n" + executionRecordDetail);
      LOGGER.LOG.info(attributeValue + " exist in the execution record.");
      flag = true;
    }
    else {
      LOGGER.LOG.info(attributeValue + " doesn't exist in the execution record.");
    }
    return flag;
  }

  /**
   * Method to select iteration as unassigned for Test Suite and click on Generate Test Envrionment.
   *
   * @param additionalParams takes values
   */
  public void selectIterationAndGenerateTestEnvironment(final Map<String, String> additionalParams) {
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().withLabel(RQMConstants.ITERATION));
    LOGGER.LOG.info("Clicked on drop down menu has label 'Iteration'.");
    drdMenu.deselect(additionalParams.get(RQMConstants.ITERATION));
    LOGGER.LOG.info("Deselected - " + additionalParams.get(RQMConstants.ITERATION));
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_ITERATION_SELECT_XPATH);
    List<WebElement> options =
        this.driverCustom.getWebElements(RQMConstants.RQMCONSTRUCTIONPAGE_EXECUTIONRECORD_ITERATION_SELECT_XPATH);
    ArrayList<String> optionvalues = new ArrayList<>();
    for (WebElement ele : options) {
      optionvalues.add(ele.getText());
    }
    if (!optionvalues.contains(additionalParams.get("Iteration"))) {
      throw new IllegalArgumentException(additionalParams.get("Iteration") +
          " is not present in Generate Test Case Execution Records test plan list ");
    }
    drdMenu.selectOptionWithText("Unassigned");
    LOGGER.LOG.info("Selected the option conatins text 'Unassigned'.");
    Link label = this.engine.findFirstElement(Criteria.isLink().withText("Generate Test Environments"));
    label.click();
    LOGGER.LOG.info("Clicked on label with text 'Generate Test Environments'.");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_BROWSERS_CHECKBOX_XPATH,
        additionalParams.get(RQMConstants.TEST_ENVIRONMENT));
    this.driverCustom.click(RQMConstants.RQMPROJECT_BROWSERS_CHECKBOX_XPATH,
        additionalParams.get(RQMConstants.TEST_ENVIRONMENT));
    LOGGER.LOG.info("Clicked on the check box - " + additionalParams.get(RQMConstants.TEST_ENVIRONMENT));
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info(RQMConstants.CLICKED_ON_NEXT_BUTTON);
    waitForSecs(5);
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info(RQMConstants.CLICKED_ON_NEXT_BUTTON);
    waitForSecs(5);
    clickOnJazzButtons("Finish and Save Test Plan");
    LOGGER.LOG.info("Clicked on 'Finish and Save Test Plan' button.");
    waitForSecs(5);
    if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, Duration.ofSeconds(10),
        RQMConstants.ALERT)) {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, "Alert");
      Dialog alert = this.engine.findElement(Criteria.isDialog().withTitle("Alert")).getFirstElement();
      LOGGER.LOG.info("'Alert' dialog is displayed.");
      Button btn = this.engine.findElement(Criteria.isButton().inContainer(alert).withText("OK")).getFirstElement();
      btn.click();
      LOGGER.LOG.info("Clicked on 'OK' button present in 'Alert' pop up.");
    }
    Button btn = this.engine.findElement(Criteria.isButton().withToolTip("Refresh")).getFirstElement();
    btn.click();
  }

  /**
   * Method used to check whether newly created Test Env is present, Unassigned iteration is selected.
   *
   * @param additionalParams contains value of test env and iteration
   * @return true on success.
   */
  public boolean isTestSuiteExecutionRecordsVisible(final Map<String, String> additionalParams) {
    searchTestSpecifications(additionalParams.get(RQMConstants.TSER_NAME));
    //waitForSecs(10);
    boolean isAllPresent = false;
    String TSER = additionalParams.get(RQMConstants.TSER_NAME) + "_" + additionalParams.get(RQMConstants.TEST_ENVIRONMENT);
    String executionRecordDetail = driverCustom.getWebElements(RQMConstants.RQM_TEST_ARTIFACT_EXECUTION_RECORD_TABLE).stream().filter(e -> e.isDisplayed()).findFirst().get().getText();
    boolean isTSERPresent = this.driverCustom.isElementVisible(RQMConstants.RQM_TEST_CASE_EXECUTION_RECORD, timeInSecs,
          TSER);
    
    //waitForSecs(15);
    if (isTSERPresent) {
      Row rowArtifactType =
          this.engine.findElementWithDuration(Criteria.isRow().containsText(additionalParams.get("TSER_Name")), timeInSecs).getFirstElement();
      LOGGER.LOG.info("Displayed row contains execution record - " + additionalParams.get("TSER_Name"));
      Cell cellTestEnv = this.engine.findElementWithDuration(
          Criteria.isCell().inContainer(rowArtifactType).withText(additionalParams.get(RQMConstants.TEST_ENVIRONMENT)), timeInSecs)
          .getFirstElement();
      if (!cellTestEnv.getText().equals(additionalParams.get(RQMConstants.TEST_ENVIRONMENT))) {
        LOGGER.LOG.info(
            additionalParams.get(RQMConstants.TEST_ENVIRONMENT) + " - displayed in the created execution record.");
        return isAllPresent;
      }
      Cell cllIteration = this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowArtifactType), timeInSecs).getFirstElement();
      if (cllIteration.getText().trim().length() <= 0) {
        return true;
      }
      return isAllPresent;
    }
    else if(executionRecordDetail.contains(TSER)) {
      return true;
    }
    return isAllPresent;
  }

  /**
   * @param additionalParams verify
   * @return true or
   */
  public boolean isTestEnvironmentAdded(final Map<String, String> additionalParams) {
    boolean isEnvPresent = false;
    Link label = this.engine.findFirstElement(Criteria.isLink().withText("Test Environment"));
    label.click();
    LOGGER.LOG.info("Clicked on 'Test Environment' link.");
    searchTestSpecifications(additionalParams.get(RQMConstants.TEST_ENVIRONMENT));
    LOGGER.LOG.info("Searched " + additionalParams.get(RQMConstants.TEST_ENVIRONMENT) + " in execution record.");
    Row row =
        this.engine.findElement(Criteria.isRow().containsText(additionalParams.get(RQMConstants.TEST_ENVIRONMENT)))
            .getFirstElement();
    if (row != null) {
      LOGGER.LOG.info(additionalParams.get(RQMConstants.TEST_ENVIRONMENT) + " displayed in the execution record.");
      return true;
    }
    return isEnvPresent;
  }

  /**
   * This method is used to delete the execution recored created.
   *
   * @param additionalParams name of the environment to be deleted.
   */
  public void deleteTestEnvironment(final Map<String, String> additionalParams) {
    Tab lnkTestEnvironemnt = this.engine.findElementWithDuration(Criteria.isTab().withText("Test Environment"), timeInSecs).getFirstElement();
    lnkTestEnvironemnt.click();
    List<Row> rowList =
        this.engine.findElementWithDuration(Criteria.isRow().containsText(additionalParams.get(RQMConstants.TEST_ENVIRONMENT)), timeInSecs)
            .getElementList();
    List<Row> visibleRow = new ArrayList<>();
    for (Row row : rowList) {
      if (row.getWebElement().isDisplayed()) {
        visibleRow.add(row);
      }
    }
    LOGGER.LOG.info("Displayed the row contains text - " + additionalParams.get("TestEnvironment"));
    Checkbox checkbox =
        this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(visibleRow.get(0)), this.timeInSecs)
            .getFirstElement();
    checkbox.click();
    LOGGER.LOG.info("Clicked on checkbox contains text - " + additionalParams.get("TestEnvironment"));
    try {
    Button button =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Remove Test Environments"),timeInSecs).getFirstElement();
    button.click();
    LOGGER.LOG.info("Clicked on 'Remove Test Environments' button.");
    Dialog dlgConfirmation = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Confirmation"), timeInSecs).getFirstElement();
    LOGGER.LOG.info("'Confirmation' pop up displayed.");
    Button deleteBtn = this.engine.findElementWithDuration(Criteria.isButton().withText("Delete").inContainer(dlgConfirmation), timeInSecs).getFirstElement();
    deleteBtn.click();
    LOGGER.LOG.info("Clicked on 'Delete' button.");
    }
    catch(Exception e) {
      Button button =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Delete"),timeInSecs).getFirstElement();
      button.click();
      LOGGER.LOG.info("Clicked on 'Delete' button.");
      Dialog dlgConfirmation = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Delete Test Environment"), timeInSecs).getFirstElement();
      LOGGER.LOG.info("'Delete Test Environment' pop up displayed.");
      Button deleteBtn = this.engine.findElementWithDuration(Criteria.isButton().withText("Delete").inContainer(dlgConfirmation), timeInSecs).getFirstElement();
      deleteBtn.click();
      LOGGER.LOG.info("Clicked on 'Delete' button.");
    }
  }

  /**
   * Click on the Test Plan from Test Execution Record
   *
   * @param testPlanName name of the test plan.
   * @author LTU7HC
   */
  public void clickOnTestPlanFromExecutionRecord(final String testPlanName) {
    waitForSecs(5);
   
    try {
     String testPlanLinkXpath = "//td[./div/img[@title='Test Plan']]//a/div[contains(text() , 'DYNAMIC_VAlUE')]";
     List<WebElement> testPlanList = this.driverCustom.getWebElements(testPlanLinkXpath, testPlanName);
     for(int i=0; i < testPlanList.size(); i++) {
       if(testPlanList.get(i).isDisplayed()) {
         testPlanList.get(i).click();
         break;
       }
     }
      waitForSecs(15);
    }
    catch (Exception e) {
      try {
        this.driverCustom.getWebElement(RQMConstants.RMARTIFACTSPAGE_SELECT_ARTIFACT_FORMAT_VALUE_XPATH, testPlanName)
            .click();
      }
      catch (Exception e1) {
        this.driverCustom.getWebElement("//a[contains(text(),'DYNAMIC_VAlUE')]", testPlanName).click();
      }
    }
    waitForSecs(6);
    refresh();
  }

  /**
   * <p>
   * While creating execution record deselect the default Iteration and select the Iteration as 'Unassigned'.
   *
   * @param Iteration iteration to be deselect while creating execution record.
   */
  public void selectIteration(final String Iteration) {
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().withLabel(RQMConstants.ITERATION));
    LOGGER.LOG.info("Clicked on drop down menu has label 'Iteration'.");
    drdMenu.deselect(Iteration);
    LOGGER.LOG.info("Deselected - " + Iteration);
    drdMenu.selectOptionWithText("Unassigned");
    Select sel = new Select(this.driverCustom.getWebElement(RQMConstants.RQMPLANNINGPAGE_SELECT_ITERATION_XPATH));
    if (sel.getAllSelectedOptions().size() > 1) {
      Actions act = new Actions(this.driverCustom.getWebDriver());
      WebElement optUnassigned = this.driverCustom
          .getWebElement("//label[text()='Iteration']/../following-sibling::td//select//option[@value='Unassigned']");
      act.moveToElement(optUnassigned).click().build().perform();
    }
  }

  /**
   * <p>
   * Click on the tab 'Generate Test Environments' and select the CPU,Test Adapter.... environment.
   *
   * @param testEnv name of the Test Environment
   */
  public void generateTestEnvironment(final String testEnv) {
    Link label = this.engine.findFirstElement(Criteria.isLink().withText("Generate Test Environments"));
    label.click();
    waitForSecs(5);
    LOGGER.LOG.info("Clicked on label with text 'Generate Test Environments'.");
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_BROWSERS_CHECKBOX_XPATH, testEnv);
    this.driverCustom.click(RQMConstants.RQMPROJECT_BROWSERS_CHECKBOX_XPATH, testEnv);
    LOGGER.LOG.info("Clicked on the check box - " + testEnv);
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info("Clicked on 'Next' button.");
    waitForSecs(5);
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info("Clicked on 'Next' button.");
    waitForSecs(5);
  }

  /**
   * <p>
   * Click on 'New Test Case Execution Record' button,Generate Test Execution Record pop up window will display. <br>
   * Set the Iteration for the Test execution record.
   *
   * @param Iteration to be select while creating execution record.
   */
  public void setIteration(final String Iteration) {
    Dropdown drdMenu = this.engine.findFirstElement(Criteria.isDropdown().withLabel(RQMConstants.ITERATION));
    drdMenu.selectOptionWithText(Iteration);
  }

  /**
   * <p>
   * Click on 'New Test Case Execution Record' button,Generate Test Execution Record pop up window will display. <br>
   * Reuse the existing test environment using 'Test Environment' name.
   *
   * @param testEnv name of the Test Environment
   */
  public void reuseExistingTestEnvironment(final String testEnv) {
    waitForSecs(2);
    try {
      Dialog dig = this.engine
          .findFirstElementWithDuration(Criteria.isDialog().withTitle("Generate Test Suite Execution Records"), Duration.ofSeconds(2));
      Row row = this.engine.findElement(Criteria.isRow().withText(testEnv).inContainer(dig)).getFirstElement();
      Cell cell = this.engine.findElement(Criteria.isCell().inContainer(row)).getFirstElement();
      Checkbox checkbox = this.engine.findElement(Criteria.isCheckbox().inContainer(cell)).getFirstElement();
      checkbox.click();
      LOGGER.LOG.info("Clicked on the checkbox contains row - " + testEnv);
    }
    catch (Exception e) {
      Row row = this.engine.findElement(Criteria.isRow().withText(testEnv)).getFirstElement();
      Cell cell = this.engine.findElement(Criteria.isCell().inContainer(row)).getFirstElement();
      Checkbox checkbox = this.engine.findElement(Criteria.isCheckbox().inContainer(cell)).getFirstElement();
      checkbox.click();
    }
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info(RQMConstants.CLICKED_ON_NEXT_BUTTON);
    waitForSecs(5);
    clickOnJazzButtons(RMConstants.NEXT);
    LOGGER.LOG.info(RQMConstants.CLICKED_ON_NEXT_BUTTON);
    waitForSecs(5);
  }

  /**
   * this method is for ccps 04Q for getting product value from product drop down.
   *
   * @param executionRecordType -
   */
  public void selectProductValueInDialog(final String executionRecordType) {
    waitForSecs(10);
    Dialog dialog1 = this.engine.findElement(Criteria.isDialog().withTitle(executionRecordType)).getFirstElement();
    Dropdown drdMenu1 =
        this.engine.findElement(Criteria.isDropdown().withLabel("Product:").inContainer(dialog1)).getFirstElement();
    drdMenu1.selectOptionWithPartText("ALM_RQM");
    waitForSecs(10);

    LOGGER.LOG.info("selected from product drop down-------------------------");
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH, "Planning");
  }
  /**
   * Open a specific test plan, select 'Test Cases' section and then create new test test case by click on image button
   * with tooltip 'Create Test Case' using method {@link RQMConstructionPage#clickOnImageButton(String)} <br>
   * Input data into all mandatory fields in 'New Test Case' dialog
   * @author NCY3HC
   * @param additionalParams -contains:
   * nameValue - testcase Name
   * testTypevalue - selected option from dropdown 
   */
  public void inputValueIntoNewTestCaseDlg(final Map<String,String> additionalParams) {
    String nameValue = additionalParams.get("NAME_VALUE");
    String testTypeValue = additionalParams.get("TEST_TYPE_VALUE");

    Dialog dlgNewTestCase = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("New Test Case"), this.timeInSecs).getFirstElement();
    if (this.driverCustom.isElementInvisible("//div[@style='']/div[1]/span[contains(text(),'Loading')]", timeInSecs)) {
      TextField name =
          this.engine.findFirstElement(Criteria.isTextField().hasLabel("Name:").inContainer(dlgNewTestCase));
      name.setText(nameValue);
      Dropdown drp = this.engine.findFirstElement(Criteria.isDropdown().withLabel("Test Type:"));
      drp.selectOptionWithText(testTypeValue);
    }
  }
  
  /**
   * This method to verify that new test case is created successfully with all inputted information
   * @author NCY3HC
   * @param params - contains:
   *                nameValue - Test case Name
   *                testTypeValue - value of test type that is selected from dropdown
   * @return true if test case is save with correct inputted information and vice versa.
   */
  public boolean isNewCreatedTestCase (final Map<String, String> params) {
    String nameValue = params.get("NAME_VALUE");
    String testTypeValue = params.get("TEST_TYPE_VALUE");
    String[] nameAndTestType = { nameValue, testTypeValue };
    boolean isTestTypeDisplayed = this.driverCustom.isElementVisible(RQMConstants.RQM_TETSCASE_IN_TESTPLAN, timeInSecs, nameAndTestType);
    if(isTestTypeDisplayed) {
      return true;
    }
    return false;
  }
  
  /**
   * Open a specific Test Plan, and then open tab 'Test Suite' inside 'Test Plan' using method {@link RQMConstructionPage#openRQMSection(String)}
   * Open 'Action' menu at a specific Test suite, select 'Run Test Suite'  option.
   * @param option - value in dropdown list
   * @author NCY3HC
   */
  public void runTestSuiteFromActionsMenu(final String option) {   
    Dropdown drdActions = this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Actions"), timeInSecs).getFirstElement();
    drdActions.click();
    Cell runTestSuite = this.engine.findElement(Criteria.isCell().withText("Run Test Suite")).getFirstElement();
    runTestSuite.click();
    Cell runOption = this.engine.findElement(Criteria.isCell().withText(option)).getFirstElement();
    runOption.click();
    waitForSecs(5);
    
  }
}
