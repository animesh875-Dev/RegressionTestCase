/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Key;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.pagemodel.AbstractJazzWebPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.container.DialogFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;

/**
 * Represents the JRS page to build a new JRS report.
 */
public class JRSBuildNewReportPage extends JRSPage {

  /**
   *
   */
  private static final String NO_SET_VALUE = "No set value";
  /**
   *
   */
  private static final String NO_OF_ROWS_IN_THE_WEB_TABLE_MESSAGE = "No. of Rows in the WebTable: ";
  private static final String COUNT_NUMBER_OF_ARTIFACTS_BY_ID = "Count number of artifacts by ID";
  private static final String COUNT_NUMBER_OF_ARTIFACTS_BY_URL = "Count number of artifacts by URL";
  static final String ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX =
      " attribute not found in the workitem editor or not a type textbox.";

  /**
   * See {@link JRSPage#JRSPage(WebDriverCustom)}
   */
  public JRSBuildNewReportPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new JazzDropdownFinder(), new JazzTextFinder(),
        new JazzDialogFinder(), new DialogFinder(), new JazzRowFinder(driverCustom.getWebDriver()), new RowCellFinder(),
        new LinkFinder(), new JazzTabFinder());
  }

  private int j;
  private int countButton;
  private boolean historicalTrend = false;
  private boolean multipleRelation = false;
  int firstTimeCount = 1;
  String savedReportName;
  protected WebElement webElement;

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom.openURL(repositoryURL + "/reports#build/new");
  }

  /**
   * The method clicks on "Continue" button present in "CHOOSE DATA" tab of the Build new report page of JRS.
   */
  public void clickContinueButton() {
    this.driverCustom.isElementClickable("(//button[text()='Continue'][not(@disbaled)])[1]", Duration.ofSeconds(120));
    Button btnContinue =
        this.engine.findElementWithDuration(Criteria.isButton().withText(JRSConstants.CONTINUE), Duration.ofSeconds(5))
            .getFirstElement();
    btnContinue.click();
    LOGGER.LOG.info("Clicked on Continue button.");
    waitForSecs(2);
  }

  /**
   * The method clicks on "Continue" button present in "CHOOSE DATA" tab of the Build new report page of JRS.
   */
  public void clickOnContinueButton(final Map<String, String> additionalParams) {
    waitForSecs(5);
    String reportTab = additionalParams.get(JRSConstants.JRSCREATEREPORTPAGE_TAB);
    String panelHeading = additionalParams.get(JRSConstants.JRSCREATEREPORTPAGE_PANELHEADING);
    switch (reportTab) {
      case "Choose data":
        // "Choose a report type";
        if (panelHeading.contentEquals("Choose a report type")) {
          this.driverCustom.getWebElement("//div[@id='reportType-tab']//button[text()='Continue']").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'reportType-tab");
          waitForSecs(5);
          break;
        }
        else if (panelHeading.contentEquals("Limit the scope")) {
          this.driverCustom.getWebElement("//div[@id='project-section']//button[text()='Continue']").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'project-section");
          waitForSecs(5);
          break;
        }
        else if (panelHeading.contentEquals("Choose an artifact")) {
          this.driverCustom.getWebElement("//div[@id='datatypes-tab']//button[text()='Continue']").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'Choose an artifact");
          waitForSecs(5);
          break;
        }
        else if (panelHeading.contentEquals("Trace relationships and add artifacts")) {
          this.driverCustom.getWebElement("//div[@id='relationships-section']//button[text()='Continue']").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'Trace relationships and add artifacts'");
          waitForSecs(5);
          break;
        }
        else if (panelHeading.contentEquals("Set conditions")) {

          this.driverCustom.getWebElement("//div[@id='filters-tab']//button[text()='Continue']").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'Set conditions'");
          waitForSecs(5);
          break;
        }

      case "Format results":
        if (panelHeading.contentEquals("Format")) {

          this.driverCustom.getWebElement("//div[@id='reportType-tab']//button[text()='Continue']").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'reportType-tab");
          waitForSecs(5);
          break;
        }
        else if (panelHeading.contentEquals("Choose an artifact")) {

          this.driverCustom.getWebElement("//div[@id='datatypes-tab']//button[text()='Continue']").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'Choose an artifact");
          waitForSecs(5);
          break;
        }


      case "Name and share":
        if (panelHeading.contentEquals("Details")) {

          this.driverCustom.getWebElement("//div[@id='createReportTab']//button[contains(text(),'Continue')]").click();
          LOGGER.LOG.info("Clicked on 'Continue' button in 'Details");
          waitForSecs(5);
          break;
        }
    }
    // LOGGER.LOG.info("Clicked on Continue button.");
    waitForSecs(10);
  }

  /**
   * Select the input project area in the "Limit the Scope" section of "CHOOSE DATA" tab in build report page of JRS.
   *
   * @param attributeName the name of the project area to be selected
   * @throws IllegalStateException if the project area is not found.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public void setProjectAreaName(final String attributeName) {
    waitForPageLoaded();
    boolean projectFind = false;
    WebElement txbSearch = this.driverCustom
        .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCH_PROJECTAREA_TEXTFIELD_XPATH);
    if (txbSearch.isDisplayed()) {
      try {
        WebElement btnClear = this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_X_BUTTON_XPATH);
        btnClear.click();
      }
      catch (Exception ex) {}
      while (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
          this.timeInSecs)) {
        this.driverCustom
            .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCH_PROJECTAREA_TEXTFIELD_XPATH);
        if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
            Duration.ofSeconds(5)) &&
            this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_PRJECTAREASECTION_PROJECTAREA_XPATH,
                Duration.ofSeconds(5))) {
          this.driverCustom
              .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCH_PROJECTAREA_TEXTFIELD_XPATH)
              .sendKeys(attributeName);
          List<WebElement> textBoxAttributeList =
              this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH);
          for (WebElement ele : textBoxAttributeList) {
            if (ele.getText().trim().contains(attributeName.trim())) {
              ele.click();
              projectFind = true;
              break;
            }
          }
          if (!projectFind) {
            fail("Project Not Found");
          }
        }
        if (this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCH_PROJECTAREA_TEXTFIELD_XPATH)
            .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equals(attributeName)) {
          LOGGER.LOG.info(attributeName + " Id value entered in query Id field.");
          break;
        }
      }
    }
    else {
      List<WebElement> textBoxAttributeList =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH);
      for (WebElement ele : textBoxAttributeList) {
        if (ele.getText().trim().contains(attributeName.trim())) {
          ele.click();
          projectFind = true;
          break;
        }
      }
      if (!projectFind) {
        fail("Project Not Found");
      }
    }
  }

  /**
   * Select the "Work Item" radio button in "Choose an artifact" section of "CHOOSE DATA" tab in build report page of
   * JRS.
   */
  public void setAttributeValueInRadioButton() {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_ARTIFACT_ARTIFACTID_XPATH,
        Duration.ofSeconds(30))) {
      WebElement artifact = this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ARTIFACT_ARTIFACTID_XPATH);
      artifact.click();
    }

  }

  /**
   * Select the days value and days_option
   *
   * @param days_option, days_value
   */
  @SuppressWarnings("javadoc")
  public void clickOnOption(final String days_option, final String days_value) {
    waitForSecs(5);
    this.driverCustom
        .getPresenceOfWebElement("//select[@class=\"selectdaysago\"]/option[text() = 'DYNAMIC_VAlUE']", days_option)
        .click();
    LOGGER.LOG.info("Clicked days_option button.");
    waitForSecs(5);
    WebElement element =
        this.driverCustom.getWebElement("//input[@type='text' and @tabindex='-1' and @class='daysago']");
    element.sendKeys(days_value);
    LOGGER.LOG.info("Clicked days_value button.");

  }

  /**
   * Click the addAndClose
   */
  public void addAndClose() {
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement("//button[text() = 'Add and Close']").click();
    waitForSecs(2);
    LOGGER.LOG.info("Clicked on addAndClose button.");

  }

  /**
   * Select the Data source in "Choose a report type" section of "CHOOSE DATA" tab in build report page of JRS.
   *
   * @param value the value to be selected. Values available are "Life cycle Query Engine","Rational Data
   *          Warehouse","Life cycle Query Engine scoped by a configuration".
   */
  public void editDataSource(final String value) {

    if(this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']",timeInSecs)){
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    this.driverCustom.getPresenceOfWebElement("//button[@id='edit-data-source']");
    while (!this.driverCustom.getWebDriver().findElement(By.xpath("//label[contains(text(),'Change the data source')]"))
        .isDisplayed()) {
      try {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_EDITDATASOURCE_XPATH);
      }
      catch (Exception e) {
        e.getMessage();
      }
      if (this.driverCustom.isElementVisible("//label[contains(text(),'Change the data source')]",
          Duration.ofSeconds(20))) {
        break;
      }
    }
    Select dropdown =
        new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_SELECTDATASOURCE_XPATH));
    dropdown.selectByVisibleText(value);
    clickOKButton();
  }

  /**
   * Select the value from the drop down with the label provided.
   *
   * @param label the label of the dropdown
   * @param value the value to be selected.
   */
  public void setValueforDropdown(final String label, final String value) {
    waitForSecs(2);
    Dropdown drdElement = this.engine.findElement(Criteria.isDropdown().withText(label)).getFirstElement();
    drdElement.selectOptionWithText(value);
  }

  /**
   * Click on the "OK" button element in the JRS build report page.
   */
  public void clickOKButton() {
    waitForPageLoaded();
    List<WebElement> elements = this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_OK_BUTTON_XPATH);
    while (!this.driverCustom.isElementVisible(elements.get(this.countButton), Duration.ofSeconds(5))) {
      this.countButton++;
    }
    this.driverCustom.clickUsingActions(elements.get(this.countButton));
    this.driverCustom.waitForPageLoaded();
  }

  /**
   * Click on "Cancel" button in the build report page.
   *
   * @author KYY1HC
   */
  public void clickCancelButton() {
    Button btnOK =
        this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Cancel"), Duration.ofSeconds(20));
    btnOK.click();
    this.driverCustom.waitForPageLoaded();
  }

  /**
   * Clicks on the 'OK' button in the warning popup dialog that is shown if the 'CANCEL' button is pressed while
   * creating a new report.
   */
  public void clickOkButtonToDiscardChanges() {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(10), "OK");
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTON_XPATH, "OK");
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    this.driverCustom.waitForPageLoaded();
  }

  /**
   * Set the attribute value for text box.
   *
   * @param attributeName the Title of the text box for which the value needs be set
   * @param attributeValue the Value to be set in the text box.
   * @throws WebAutomationException if the attribute with the input name is not found.
   *           {@link WebAutomationException#WebAutomationException(String)}
   */
  public void setAttributeValueInTextBox(final String attributeName, final String attributeValue) {

    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_SAVEREPORT_SAVEREPORTTITLE_XPATH,
        Duration.ofSeconds(30))) {
      if (attributeName.equals("Report Name")) {
        this.savedReportName = attributeValue;
      }
      boolean attributeFound = setAttributeForField(attributeName, attributeValue,
          JRSConstants.JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TEXTBOX_XPATH);
      checkIfAttributeFound(attributeName, attributeFound);
    }
  }

  private void checkIfAttributeFound(final String attributeName, final boolean attributeFound) {
    if (!attributeFound) {
      throw new WebAutomationException(
          attributeName + ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX);
    }
  }

  /**
   * Set the attribute value for text area.
   *
   * @param attributeName the Title of the text area for which the value needs be set
   * @param attributeValue the Value to be set in the text area.
   * @throws WebAutomationException if the attribute with the input name is not found.
   *           {@link WebAutomationException#WebAutomationException(String)}
   */
  public void setAttributeValueInTextAreaBox(final String attributeName, final String attributeValue) {
    waitForPageLoaded();
    boolean attributeFound = false;
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TEXTAREABOX_XPATH,
        Duration.ofSeconds(5))) {
      attributeFound = setAttributeForField(attributeName, attributeValue,
          JRSConstants.JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TEXTAREABOX_XPATH);
    }
    checkIfAttributeFound(attributeName, attributeFound);
  }


  private boolean setAttributeForField(final String attributeName, final String attributeValue,
      final String fieldXpath) {
    boolean attributeFound = false;
    List<WebElement> textBoxAttributeList = this.driverCustom.getWebElements(fieldXpath);
    for (WebElement ele : textBoxAttributeList) {
      if (ele.getAttribute("title").equals(attributeName)) {
        attributeFound = true;
        ele.sendKeys(attributeValue);
        break;
      }
    }
    return attributeFound;
  }

  /**
   * Set the Tag value for Name and Share tab of the Build new report page of JRS.
   *
   * @param attributeValue the Value to be set in the text area.
   * @return attributeValue the Value to be set in the text area.
   * @throws WebAutomationException if the attribute with the input name is not found.
   *           {@link WebAutomationException#WebAutomationException(String)}
   */
  public String setTagValue(final String attributeValue) {

    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TAG_XPATH,
        Duration.ofSeconds(30))) {
      WebElement tagValue =
          this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TAG_XPATH);
      tagValue.sendKeys(attributeValue + Keys.ENTER);
      return attributeValue;
    }
    throw new WebAutomationException(ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX);
  }


  /**
   * Add owner for the "Report Owner" attribute of the "NAME AND SHARE" tab of the Build new report page of JRS.
   *
   * @param attributeValue the name of the owner to be added.
   * @throws WebAutomationException if the "Report Owner" is not available in the current page.
   *           {@link WebAutomationException#WebAutomationException(String)}
   */
  public void setOwnerValue(final String attributeValue) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH);
      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERSEARCHTEXT_XPATH,
          Duration.ofSeconds(30))) {
        WebElement ownerValue =
            this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERSEARCHTEXT_XPATH);
        ownerValue.sendKeys(attributeValue);
        Button btnSave = this.engine
            .findElementWithDuration(Criteria.isButton().withText("Search"), Duration.ofSeconds(30)).getFirstElement();
        btnSave.click();
      }
      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERNAME_XPATH,
          Duration.ofSeconds(30))) {
        Select dropdown =
            new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERNAME_XPATH));
        dropdown.selectByIndex(0);
      }
      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_ADDREPORTMANAGER_BUTTON_XPATH,
          Duration.ofSeconds(30))) {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_ADDREPORTMANAGER_BUTTON_XPATH);
      }
      clickOKButton();

    }
    else {
      throw new WebAutomationException(ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX);
    }
  }

  /**
   * Select Folder for the "Report Folder" attribute of the "NAME AND SHARE" tab of the Build new report page of JRS.
   *
   * @param folderName the name of the owner to be added.
   * @throws WebAutomationException if the "Report Folder" is not available in the current page.
   *           {@link WebAutomationException#WebAutomationException(String)}
   */
  public void selectFolder(final String folderName) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_SELECTFOLDER_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_SELECTFOLDER_XPATH);
      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_SELECTFOLDER_COLLECTION_XPATH,
          Duration.ofSeconds(30))) {
        int i = 0;
        for (String folderitem : folderName.split(JRSConstants.JRSCREATEREPORTPAGE_SELECTFOLDER_FOLDERSPLITPATH)) {
          WebElement displayName =
              this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_SELECTFOLDER_ITEM_XPATH, folderitem);
          WebElement radio =
              displayName.findElement(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_SELECTFOLDER_RADIO_XPATH));
          if (i == (folderName.split(JRSConstants.JRSCREATEREPORTPAGE_SELECTFOLDER_FOLDERSPLITPATH).length - 1)) {
            radio.click();
            waitForSecs(3);
            Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs)
                .getFirstElement();
            btnOK.click();
            return;
          }
          WebElement showIcon =
              displayName.findElement(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_SELECTFOLDER_SHOWICON_XPATH));
          if (displayName.isDisplayed() && displayName.getText().trim().equals(folderitem)) {
            showIcon.click();
          }
          i++;

        }
      }
      clickOKButton();
    }
    else {
      throw new WebAutomationException(ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX);
    }
  }
  
  
  
  /**
   * @param folderName to be selected in Select a folder dialog 
   * @param buttonName to be checked 
   * @return returns result of function execution
   */
  public void checkNewFolderSupport(String folderName, String buttonName) {
    
    

  
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_SELECTFOLDER_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_SELECTFOLDER_XPATH);

      this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_SELECTFOLDERRADIO_XPATH, folderName).click();
    
      
      this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_NEWFOLDER_XPATH, buttonName).click();
       
      Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs)
          .getFirstElement();
      btnOK.click();
   
//    return false;
    }
  }

  /**
   * Sort type for the "Format results" tab of the Build new report page of JRS.
   *
   * @param attributeLabel the name of the Attribute column on table to be added.
   * @param sortType value for sorting.
   * @return string is the value selected
   * @throws WebAutomationException if the "Report Folder" is not available in the current page.
   *           {@link WebAutomationException#WebAutomationException(String)}
   */
  public Boolean sortType(final String attributeLabel, final String sortType) {
    waitForSecs(3);
    if(this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']",timeInSecs)){
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_TABLE_SORTYYPE_XPATH,
        Duration.ofSeconds(30))) {
      WebElement tableSort = this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_TABLE_SORTYYPE_XPATH);
      List<WebElement> row = tableSort.findElements(By.cssSelector("tr"));
      for (WebElement item : row) {
        if (item.findElement(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_COLUMN_SORTYYPE_XPATH)).getText().trim()
            .equals(attributeLabel)) {
          WebElement sortTypeDropdown =
              item.findElement(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_DROPDOWN_SORTYYPE_XPATH));
          sortTypeDropdown.sendKeys(sortType);
          return checkSelectedType(sortTypeDropdown, sortType);
        }
      }
    }
    else {
      throw new WebAutomationException("Format result is empty or not display");
    }
    return false;
  }

  private Boolean checkSelectedType(final WebElement sortTypeDropdown, final String sortType) {
    WebElement optionSelcted =
        sortTypeDropdown.findElement(By.xpath(String.format(".//option[text()='%s']", sortType)));
    if (optionSelcted.isDisplayed()) {
      return true;
    }
    return false;
  }

  /**
   * Save the report created by clicking the "Save" button in "NAME AND SHARE" tab of the Build new report page of JRS.
   */
  public void saveReport() {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_ADDREPORTMANAGER_BUTTON_XPATH,
        Duration.ofSeconds(5))) {
      if (this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_SAVE_BUTTON_XPATH,
          Duration.ofSeconds(5))) {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SAVE_BUTTON_XPATH);
        LOGGER.LOG.info("Clicked on 'Save' button.");
        if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_SAVEREPORTSPINNER_SPAN_XPATH,
            Duration.ofSeconds(10))) {
          LOGGER.LOG.info("Report Saved Successfully.");
        }
        else {
          fail("There was problem in saving the report");
        }
      }
      else {
        throw new WebAutomationException("Save button is disabled cannot able to save the report.");
      }
    }
  }

  /**
   * Add relationship in the "Trace relationships and add artifacts" section of "CHOOSE DATA" tab in Build new report
   * page of JRS.
   *
   * @param relationshipValue the relationship value to be selected.
   * @param artifact the relationship to be selected
   * @throws IllegalStateException if the relationship is not found.
   *           {@link IllegalStateException#IllegalStateException(String)} @ if any thread interrupted the current
   *           thread before or while the current thread was waiting for a notification. The <i>interrupted status</i>
   *           of the current thread is cleared when this exception is thrown. {@link InterruptedException}
   */
  public void addRelationship(final String relationshipValue, final String artifact) {
    waitForPageLoaded();

    if (!relationshipValue.isEmpty()) {
      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
          this.timeInSecs)) {
        selectAddRelationShip();
        this.driverCustom.waitForPageLoaded();
        waitForElement(5);
        pickRelationShipValueFromRelationShips(relationshipValue);
        WebElement elementBtnOK = this.driverCustom.getWebElement(
            "//div[text()='Pick a relationship']/parent::div//button[contains(text(), 'OK')][contains(@class, 'action-ok')]");
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", elementBtnOK);
        elementBtnOK.click();
//      sometimes method 'clickOKButton' fail, can not move to element
//        clickOKButton();
        this.driverCustom.isElementVisible("//div[contains(text(),'Pick an artifact')]", this.timeInSecs);
        pickArtifactsFromRelationships(artifact);
        this.j = this.j + 2;
      }
    }
    else {
      Reporter.logInfo("Relatioship value is empty");
    }
  }

  private void selectAddRelationShip() {

    if (!this.driverCustom.isElementInvisible("//td/button[contains(text(),'Add a relationship')]",
        Duration.ofSeconds(10))) {
      this.driverCustom.click("//td/button[contains(text(),'Add a relationship')]");
    }
    else if (this.j == 0) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_ADDRELATIONSHIP_RELATIONSHIP_XPATH);
    }

    else if (this.multipleRelation) {
      if (this.driverCustom.isElementInvisible(
          JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTADDBUTTON_BUTTON_XPATH, Duration.ofSeconds(10))) {
        List<WebElement> scopeOptions7 =
            this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_ADDRELATIONSHIP_RELATIONSHIP_XPATH);
        if (scopeOptions7.size() == 2) {
          scopeOptions7.get(1).click();
        }
        else {
          scopeOptions7.get(2).click();
        }
      }
    }
    else {
      List<WebElement> scopeOptions2 =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_ADDRELATIONSHIPBUTTON_RELATIONSHIP_XPATH);
      scopeOptions2.get(this.j - 1).click();
    }
  }


  private void pickRelationShipValueFromRelationShips(final String relationshipValue) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_ADDREL_ADDRELTITLE_XPATH,
        Duration.ofSeconds(10))) {
      // Loop through the options and select the one that matches
      clickAndCheckIfFound(relationshipValue, "Given Relationship Does not exist",
          JRSConstants.JRSCREATEREPORTPAGE_ADDRELATIONSHIP_RELATIONSHIPVALUE_XPATH, false);
    }
  }

  private void pickArtifactsFromRelationships(final String artfact) {
    String btnOK =
        "//div[text()='Pick an artifact']/parent::div//button[contains(text(), 'OK')][contains(@class, 'action-ok')]";
    if (artfact.contains("Work Item")) {
      List<WebElement> scopeOptions1 =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_LISTVALUE_PICK_AN_ARTIFACT_XPATH);
      for (WebElement option : scopeOptions1) {
        if (option.getText().trim().equals(artfact)) {
          option.click();
          // Element detectable on 12Q but may not work on other servers.
          if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_OK_BUTTON_XPATH, this.timeInSecs) ||
              this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_OK_BUTTON_XPATH, this.timeInSecs)) {
            try {
              clickOKButton();
            }
            catch (Exception e) {
              this.driverCustom.isElementPresent(btnOK, Duration.ofSeconds(60));
              WebElement elementBtnOK = this.driverCustom.getWebDriver().findElement(By.xpath(btnOK));
              this.driverCustom.scrollIntoCenterOfView(elementBtnOK);
              elementBtnOK.click();
            }
          }
          break;
        }
      }
    }

    else if (artfact.contains("Quality Approval")) {
      List<WebElement> scopeOptions1 =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CLICKONTEXT_DIV_XPATH);
      scopeOptions1.get(this.j).click();
    }
    else if (!artfact.isEmpty()) {
      if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_ADDREL_ADDRELTITLE_XPATH,
          Duration.ofSeconds(30))) {
        List<WebElement> scopeOptions3 =
            this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_LISTVALUE_TRACEBILITY_XPATH);
        // Loop through the options and select the one that matches
        for (WebElement opt1 : scopeOptions3) {
          Reporter.logInfo("text is:  " + opt1.getAttribute("data-text").trim());

          if (opt1.getAttribute("data-text").trim().contains(artfact.trim())) {
            if (opt1.isDisplayed() && opt1.isEnabled()) {
              opt1.click();
              this.driverCustom.clickUsingActions(opt1);
              WebElement elementBtnOK = this.driverCustom.getWebElement(btnOK);
              JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
              je.executeScript("arguments[0].scrollIntoView(true);", elementBtnOK);
              elementBtnOK.click();
              break;
            }
          }
        }
      }
    }
    else {
      List<WebElement> scopeOptions1 =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CLICKONTEXT_DIV_XPATH);
      scopeOptions1.get(this.j).click();
    }

    if (artfact.contains("QM Test Case")) {
      List<WebElement> scopeOptions1 =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CLICKONTEXT_DIV_XPATH);
      scopeOptions1.get(this.j).click();
    }

  }

  /**
   * Select the artifact whose label matches the input value.
   *
   * @param artifactValue the value of the artifact to be selected
   * @param childArtifact true if childArtifact needs to be chosen
   * @throws IllegalStateException if the artifact is not found.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public void chooseArtifacts(final String artifactValue, final String childArtifact) {
    waitForPageLoaded();
    boolean artfactFound = false;
    showMyChoices();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30)) &&
        this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
            Duration.ofSeconds(30))) {
      List<WebElement> scopeOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CHOOSEAETIFACT_LABEL_XPATH);
      // Loop through the options and select the one that matches
      for (WebElement opt1 : scopeOptions) {
        artfactFound = clickArtifactIfMatch(artifactValue, artfactFound, opt1, Boolean.valueOf(childArtifact));
        if (artfactFound) {
          break;
        }
      }
      if (!artfactFound) {
        fail("Artifact not found");
      }
    }
  }


  private boolean clickArtifactIfMatch(final String artifactValue, final boolean artfactFound, final WebElement opt1,
      final boolean childArtifact) {
    boolean artifact = artfactFound;
    if (!this.historicalTrend || childArtifact) {
      if (opt1.getText().equals(artifactValue)) {
        opt1.click();
        artifact = true;
      }
    }
    else {
      if (opt1.getText().trim().equals(artifactValue.trim())) {
        opt1.click();
        artifact = true;
      }
    }
    return artifact;
  }

  /**
   * Add condition to the report using "Set Conditons" section of the "CHOOSE DATA" tab in the Build new report page of
   * JRS
   *
   * @param condition the condition to be added
   * @param conditionValue the condition value if needed @ if any thread interrupted the current thread before or while
   *          the current thread was waiting for a notification. The <i>interrupted status</i> of the current thread is
   *          cleared when this exception is thrown. {@link InterruptedException}
   */
  public void setCondition(final String condition, final String conditionValue) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_FILTERSPINNER_SPAN_XPATH,
        this.timeInSecs)) {
      if (this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_ADDCONDITION_BUTTON_XPATH,
          this.timeInSecs)) {
        Button addButton = this.engine.findElement(Criteria.isButton().withToolTip("Add condition")).getFirstElement();
        addButton.click();
      }
      if (!condition.isEmpty()) {
        if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TEXTBOX_XPATH,
            this.timeInSecs)) {
          Text txtSearch = this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder(JRSConstants.SEARCH));
          txtSearch.setText(condition);
          addCondition(condition, conditionValue);
        }
        else {
          fail("Attribute Name is not their");
        }
      }
      else {
        if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CLOSEBUTTON_SPAN_XPATH,
            Duration.ofSeconds(30))) {
          this.driverCustom.click(
              "//h4[text()='Add condition']/ancestor::div[@class='top-section-nopadding']//button[text()='Close']");
        }
      }
    }
  }

  private void addCondition(final String condition, final String conditionValue) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_HIGHLIGHTTEXT_SPAN_XPATH,
        Duration.ofSeconds(30), condition)) {
      selectIfMatches(condition, false, JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH, false);
      for (String value : conditionValue.split("/")) {
        selectRadioButtonOrCheckBox(value.trim());
      }
      clickAddAndCloseButton();
    }
  }

  private void selectRadioButtonOrCheckBox(final String value) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_RADIO_BUTTON_XPATH,
        Duration.ofSeconds(10))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_RADIO_BUTTON_XPATH);
    }
    else {
      for (WebElement cbx : this.driverCustom
          .getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CONDITION_VALUE_CHECKBOX__XPATH, value)) {
        if (cbx.isEnabled()) {
          try {
            cbx.click();
          }
          catch (Exception e) {}
        }
      }
    }
  }

  private void clickAddAndCloseButton() {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_ADDANDCLOSE_BUTTON_V7_XPATH,
        Duration.ofSeconds(60))) {
      if (this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_ADDANDCLOSE_BUTTON_V6_XPATH,
          Duration.ofSeconds(30))) {
        Button addCloseButton =
            this.engine.findElement(Criteria.isButton().withText("ADD AND CLOSE")).getFirstElement();
        addCloseButton.click();
      }
      else if (this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_ADDANDCLOSE_BUTTON_V7_XPATH,
          Duration.ofSeconds(30))) {
        Button addCloseButton =
            this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
        addCloseButton.click();
      }
      else {
        Reporter.logFailure("Button not Clickable");
      }
    }
  }

  /**
   * Add attribute columns using "Attribute" button in the "Format" section of the "FORMAT RESULTS" tab in build report
   * page.
   *
   * @param attribute - Attribute to be added
   * @return true if adding successfully
   * @throws IllegalStateException if the attribute is not found.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public Boolean addAttributeinFormatResult(final String attribute) {
    waitForPageLoaded();
    // After Refresh button clicked on previous step, wait until loading icon to disappear.
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(300));
    wait.pollingEvery(Duration.ofSeconds(5)).ignoring(TimeoutException.class).until(ExpectedConditions
        .invisibilityOfElementLocated(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH)));
    // Add column Attribute
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDATTRIBUTE_XPATH);
      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_ADDATTRIBUTE_SELECT_XPATH,
          Duration.ofSeconds(50))) {
        this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_SEARCHBOX_SPAN_XPATH,
            Duration.ofSeconds(20));
        Text txtSearchVerify =
            this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder(JRSConstants.SEARCH));
        txtSearchVerify.setText(attribute);
        if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_HIGHLIGHTTEXT_SPAN_XPATH,
            Duration.ofSeconds(30), attribute)) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_HIGHLIGHTTEXT_SPAN_XPATH, attribute);
          this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_ADD_BUTTON_ADDATTRIBUTE_XPATH,
              Duration.ofSeconds(10));
          this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ADD_BUTTON_ADDATTRIBUTE_XPATH).click();
        }
        else {
          fail("Attribute is not present");
          return false;
        }

        List<String> columnValues = fetchColumnValues();

        return checkIfAttributeAvailable(attribute, columnValues, "Failed: Test Category Column not found");
      }
    }
    return false;
  }

  private Boolean checkIfAttributeAvailable(final String attribute, final List<String> columnValues,
      final String message) {
    if (columnValues.contains(attribute)) {
      Reporter.logPass("Test Successfull");
      return true;
    }
    Reporter.logFailure("Test Failed");
    fail(message);
    return false;
  }


  /**
   * Add columns using "Calculated Value" button in the "Format" section of the "FORMAT RESULTS" tab in build report
   * page.
   *
   * @param calculation the calculation value to be selected from the 'Add Calculated Value Column' pop-up
   * @param attribute the attribute to be added
   * @param countAttributValue the value of the attribute. @ if any thread interrupted the current thread before or
   *          while the current thread was waiting for a notification. The <i>interrupted status</i> of the current
   *          thread is cleared when this exception is thrown. {@link InterruptedException}
   * @return true or false.
   * @throws IllegalStateException if the attribute is not found.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public boolean addCalculatedColumninFormatResult(final String calculation, final String attribute,
      final String countAttributValue) {
    waitForPageLoaded();
    String addedAttribute = attribute;

    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDCALCULATEDCOLUMN_XPATH);
      Select dropdown = new Select(
          this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDCALCULATEDCOLUMNSELECTLIST_XPATH));
      dropdown.selectByVisibleText(calculation);

      // Loop through the options and select the one that matches
      if (!calculation.equalsIgnoreCase(COUNT_NUMBER_OF_ARTIFACTS_BY_URL) &&
          !calculation.equalsIgnoreCase(COUNT_NUMBER_OF_ARTIFACTS_BY_ID)) {
        selectIfMatches(addedAttribute, false, JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH, false);
      }
      if (calculation.equalsIgnoreCase(COUNT_NUMBER_OF_ARTIFACTS_BY_URL) ||
          calculation.equalsIgnoreCase(COUNT_NUMBER_OF_ARTIFACTS_BY_ID)) {
        if (addedAttribute.equalsIgnoreCase("All (Count all artifacts in the group)")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SELECTCOUNTALLARTIFACTINCALCULATEDCOLUMN_SPAN_XPATH);
          addedAttribute = setAttributeValue(calculation);
        }
        else {
          selectCalculatedValueWhenLimit(addedAttribute, countAttributValue);
        }
      }

      clickSaveClaseButtonForCalculatedColumn();
      waitForSecs(5);
      clickOKAddCalculatedColumn();
      List<String> columnValues = fetchColumnValues();
      checkIfAttributeAvailable(addedAttribute, columnValues, "Failed: Test Category Column not found");
      return true;
    }
    return false;

  }

  private String setAttributeValue(final String calculation) {
    String attribute;
    if (calculation.equalsIgnoreCase(COUNT_NUMBER_OF_ARTIFACTS_BY_URL)) {
      attribute = "URL";
    }
    else {
      attribute = "ID";
    }
    return attribute;
  }

  private void clickSaveClaseButtonForCalculatedColumn() {
    List<WebElement> elements =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_ADD_SAVEANDCLOSECALCULATEDCOLUMNBUTTON_XPATH);
    if (elements.size() == 1) {
      elements.get(0).click();
      waitForSecs(2);
    }
    else {
      elements.get(1).click();
      waitForSecs(2);
    }
  }

  private void clickOKAddCalculatedColumn() {
    if (((this.firstTimeCount == 1) && this.driverCustom
        .getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ADDCALCCOLUMNBUTTON_OKBUTTON_XPATH).isDisplayed()) ||
        this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_ADDCALCCOLUMNBUTTON_OKBUTTON_XPATH,
            Duration.ofSeconds(10))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_ADDCALCCOLUMNBUTTON_OKBUTTON_XPATH);
      this.firstTimeCount = 0;
    }
  }

  private List<String> fetchColumnValues() {
    List<WebElement> totalRowCount =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_TABLECLASS_TABLE_XPATH);
    Reporter.logInfo(NO_OF_ROWS_IN_THE_WEB_TABLE_MESSAGE + totalRowCount.size());
    // Now we will Iterate the Table and print the Values
    int rowIndex = 1;
    List<String> columnValues = new ArrayList<>();

    for (WebElement rowElement : totalRowCount) {
      List<WebElement> totalColumnCount = rowElement.findElements(By.cssSelector("td.column-name.manualEdit-hide"));
      addColumnValues(rowIndex, columnValues, totalColumnCount);

      rowIndex = rowIndex + 1;
    }
    return columnValues;
  }

  private int addColumnValues(final int RowIndex, final List<String> columnValues,
      final List<WebElement> totalColumnCount) {
    int columnIndex = 1;
    for (WebElement colElement : totalColumnCount) {
      Reporter.logInfo("Row " + RowIndex + " Column " + columnIndex + " Data " + colElement.getText());
      columnValues.add(colElement.getText());
      columnIndex = columnIndex + 1;
    }
    return columnIndex;
  }

  private void selectCalculatedValueWhenLimit(final String attribute, final String countAttributValue) {
    List<WebElement> scopeOptions;
    this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SELECTLIMITARTIFACTINCALCULATEDCLOUMN_SPAN_XPATH);
    scopeOptions = this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH);
    for (WebElement opt1 : scopeOptions) {
      Reporter.logInfo("text is:  " + opt1.getText().trim());
      if (opt1.getText().trim().equals(attribute.trim())) {
        opt1.click();

        if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCOLUMNCOUNTVALUE_INPUT_XPATH,
            Duration.ofSeconds(5))) {
          selectCalculatedValueForString(countAttributValue);
        }

        else if (this.driverCustom.isElementVisible(
            JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNCOUNTVALUE2_INPUT_XPATH, Duration.ofSeconds(5))) {
          selectCalculatedValueForBoolean(countAttributValue);
        }

        else if (this.driverCustom.isElementVisible(
            JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNCOUNTVALUE3_INPUT_XPATH, Duration.ofSeconds(5))) {
          selectCalculatedValueForUser(countAttributValue);
        }

        else if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNDATE1_BUTTON_XPATH,
            Duration.ofSeconds(5))) {
          selectCalculatedValueForTimeRange(countAttributValue);
        }

        else if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CHILDARTIFACT_ARTIFACT_XPATH,
            Duration.ofSeconds(5))) {
          selectIfMatches(countAttributValue, false, JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH, false);
        }

        if (this.driverCustom.isElementVisible(
            JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNREMOVEBUTTON_BUTTON_XPATH, Duration.ofSeconds(5))) {
          selectCalculatedValueForRemove(countAttributValue);
        }
        break;
      }
    }
  }

  private void selectCalculatedValueForRemove(final String countAttributValue) {
    List<WebElement> elements = this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_SEARCHBOX_SPAN_XPATH);
    elements.get(2).sendKeys(countAttributValue);

    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_HIGHLIGHTTEXT_SPAN_XPATH,
        Duration.ofSeconds(10))) {
      List<WebElement> highlightedElements =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_HIGHLIGHTTEXT_SPAN_XPATH);
      highlightedElements.get(0).click();
    }
  }

  private void selectCalculatedValueForTimeRange(final String countAttributValue) {
    Button btn = this.engine.findElement(Criteria.isButton().withText("Set time range...")).getFirstElement();
    btn.click();
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEADDBUTTON_BUTTON_XPATH,
        Duration.ofSeconds(30))) {

      List<WebElement> dateOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGESTARTDATE_INPUT_XPATH);
      dateOptions.get(4).click();

      List<WebElement> exactDateOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEPASSDATE_INPUT_XPATH);
      exactDateOptions.get(0).sendKeys(countAttributValue);
      Button addButton = this.engine.findElement(Criteria.isButton().withText("Add")).getFirstElement();
      addButton.click();
      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEADDBUTTON_BUTTON_XPATH,
          Duration.ofSeconds(30))) {
        Reporter.logPass("popup successfully closed");
      }
    }
  }

  private void selectCalculatedValueForUser(final String countAttributValue) {
    List<WebElement> countOptions2 =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNCOUNTVALUE3_INPUT_XPATH);
    if (countAttributValue.equalsIgnoreCase(NO_SET_VALUE)) {
      countOptions2.get(0).click();
    }
    else if (countAttributValue.equalsIgnoreCase("Current User")) {
      countOptions2.get(1).click();
    }
    else {
      countOptions2.get(2).click();
      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE7_SPAN_XPATH,
          Duration.ofSeconds(20))) {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE7_SPAN_XPATH);
      }
      if (this.driverCustom.isElementVisible(
          JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE11_ADDCLOSEBUTTON_XPATH, Duration.ofSeconds(20))) {
        List<WebElement> searchOptions =
            this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE8_INPUTSEARCH_XPATH);
        searchOptions.get(0).sendKeys(countAttributValue);
        Button searchButton = this.engine.findElement(Criteria.isButton().withText("Search")).getFirstElement();
        searchButton.click();
      }
      if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE10_SELECTUSER_XPATH,
          Duration.ofSeconds(20))) {

        List<WebElement> selectUserOptions =
            this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE10_SELECTUSER_XPATH);
        waitForElement(5);
        Select userDropdown = new Select(selectUserOptions.get(2));
        userDropdown.selectByIndex(0);
        if (this.driverCustom.isElementClickable(
            JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNVALUE11_ADDCLOSEBUTTON_XPATH, Duration.ofSeconds(20))) {
          Button addAndClose = this.engine.findElement(Criteria.isButton().withText("Add and Close")).getFirstElement();
          addAndClose.click();
        }
      }
    }
  }

  private void selectCalculatedValueForBoolean(final String countAttributValue) {
    List<WebElement> countOptions2 =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCLOUMNCOUNTVALUE2_INPUT_XPATH);
    if (countAttributValue.equalsIgnoreCase("True")) {
      countOptions2.get(0).click();
    }
    else if (countAttributValue.equalsIgnoreCase("False")) {
      countOptions2.get(1).click();
    }
    else if (countAttributValue.equalsIgnoreCase(NO_SET_VALUE)) {
      countOptions2.get(2).click();
    }
  }

  private void selectCalculatedValueForString(final String countAttributValue) {
    List<WebElement> countOptions =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CALCULATEDCOLUMNCOUNTVALUE_INPUT_XPATH);
    if (countAttributValue.equalsIgnoreCase(NO_SET_VALUE)) {
      countOptions.get(0).click();
    }
    else {
      countOptions.get(1).click();
      List<WebElement> inputOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_RICHTEXTEDITORWIDGET_TEXTBOX_XPATH);
      inputOptions.get(1).sendKeys(countAttributValue);
    }
  }

  /**
   * Check if the "Format results" tab loaded successfully
   */
  public void checkFormatResultTab() {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_FORMATRESULT_FORMATRESULTTITLE_XPATH,
        Duration.ofSeconds(30))) {
      Reporter.logPass("Format Result tab Loaded successfully");
    }
  }

  /**
   * Verify the total number of Items in the result of the report to the expected input value.
   *
   * @param value the expected input value as a number . Ex: "5"
   * @return string of number
   * @throws IllegalStateException if the expected and actual number of records do not match or no records found or run
   *           report is not visible. {@link IllegalStateException#IllegalStateException(String)}
   */
  public String verifyTotalNoOfRecords(final String value) {
    if(this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']",timeInSecs)){
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    waitForPageLoaded();
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    verifyTotalNoOfRecords(value, JRSConstants.JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH);
    return value;
  }

  /**
   * Select the report type in the "Choose a Report Type" section of "CHOOSE DATA" tab in build report page of JRS.
   *
   * @param reportType the type to be selected
   */
  public void chooseReportType(final String reportType) {
    if (reportType.equalsIgnoreCase("Current Data") && this.driverCustom
        .isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_REPORTTYPE1_INPUT_XPATH, Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_REPORTTYPE1_INPUT_XPATH);
    }
    if (reportType.equalsIgnoreCase("Historical Trends") && this.driverCustom
        .isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_REPORTTYPE2_INPUT_XPATH, Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_REPORTTYPE2_INPUT_XPATH);
      this.historicalTrend = true;
    }
  }

  /**
   * Set the time Rage in the "Set start datre" section of "CHOOSE DATA" tab in build report page of JRS. The Set time
   * range is valid when the report type selected is Historical Trends.
   *
   * @param startDate the start date of the range. Format : MM/DD/YYYY
   */
  public void setStartDate(final String startDate) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_TIMERANGESPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      if (this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEBUTTON_BUTTON_XPATH,
          Duration.ofSeconds(30))) {
        Button btn = this.engine.findElement(Criteria.isButton().withText("Set Time Range")).getFirstElement();
        btn.click();
        WebElement radio = this.driverCustom.getWebElement("(//input[@name='date-start'])[5]");
        radio.click();
        WebElement ele = this.driverCustom.getFirstVisibleWebElement("(//input[@class='exactdate'])[1]", null);
        ele.sendKeys(startDate);
      }
    }
  }

  /**
   * Set the time Rage in the "Set end date" section of "CHOOSE DATA" tab in build report page of JRS. The Set time
   * range is valid when the report type selected is Historical Trends.
   *
   * @param endDate the end date of the range. Format : MM/DD/YYYY
   */
  public void setEndDate(final String endDate) {
    this.driverCustom.click("//input[@name='date-end']/following::span[text()='Today']");
    this.driverCustom.click("(//button[text()='Add'])[5]");
  }

  /**
   * @param Table
   */
  @SuppressWarnings("javadoc")
  public void clickTableTab() {
    waitForSecs(5);
    this.driverCustom.click("//a[@id='showTable']");
  }

  /**
   * @param Table
   */
  @SuppressWarnings("javadoc")
  public void clickGraphTab() {
    waitForSecs(5);
    this.driverCustom.click("//a[@id='showGraph']");
    this.driverCustom.click("//input[@id='seriesSelectByValue']");
    this.driverCustom
        .click("//input[@type='radio' and @name='chartOptionsRadios' and @value='option1' and @class='editable']");
    this.driverCustom.click("//select[@id='count-select']");
    this.driverCustom.click("(//option[@title='Work Item: Count total number of artifacts'])[1]");
    this.driverCustom.click("//select[@id='series-select']");
    this.driverCustom.click("(//option[text()='Change Date (Custom Trends for Work Items)'])[2]");
    this.driverCustom.click("//button[@id='datescale-select-button']");
    this.driverCustom.click("//li//input[@id='scale-by-months']");

  }

  /**
   * Set the time Rage in the "Set time Range" section of "CHOOSE DATA" tab in build report page of JRS. The Set time
   * range is valid when the report type selected is Historical Trends.
   *
   * @param startDate the start date of the range. Format : MM/DD/YYYY
   * @param endDate the end date of the range. Format : MM/DD/YYYY
   */
  public void setTimeRangeforHistoricaltrends(final String startDate, final String endDate) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_TIMERANGESPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      if (this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEBUTTON_BUTTON_XPATH,
          Duration.ofSeconds(30))) {
        Button btn = this.engine.findElement(Criteria.isButton().withText("SET TIME RANGE")).getFirstElement();
        btn.click();
      }

      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEPOPUP_DIV_XPATH,
          Duration.ofSeconds(30))) {
        List<WebElement> passDate =
            this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEPASSDATE_INPUT_XPATH);
        int d = 0;

        for (WebElement ele : passDate) {
          if (d == 0) {
            setDate(startDate, ele, JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGESTARTDATE_INPUT_XPATH);
            d++;
          }
          else if (d == 1) {
            setDate(endDate, ele, JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEENDDATE_INPUT_XPATH);
          }
        }

        clickSetTimeRangeAddButton();
      }
    }
  }

  private void setDate(final String startDate, final WebElement ele, final String xpath) {
    List<WebElement> startDateList = this.driverCustom.getWebElements(xpath);
    startDateList.get(5).click();
    ele.sendKeys(startDate);
  }

  private void clickSetTimeRangeAddButton() {
    if (this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEADDBUTTON_BUTTON_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEADDBUTTON_BUTTON_XPATH);
    }
  }

  /**
   * Verify if valid start and end date are selected in the "Set time range" pop-up
   *
   * @throws IllegalStateException if the time range is not set.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public void verifySetTimeRangeResult() {
    waitForPageLoaded();
    verifySetTimeRange(JRSConstants.JRSCREATEREPORTPAGE_BLABEL_LABEL_XPATH, "Start date:", "End date:");
  }


  /**
   * Verify if start date and end date are added and is available in the "Set Conditions" section of "CHOOSE DATA" tab
   * in build report page of JRS. The Verification is valid only when the "Custom Trends for Work Items" is the artifact
   * in focus.
   *
   * @throws IllegalStateException if the artifact in focus is other than "Custom Trends for Work Items" or if the time
   *           range is not set. {@link IllegalStateException#IllegalStateException(String)}
   */
  public void verifySetTimeRangeResultInSetCondition() {
    waitForPageLoaded();
    verifySetTimeRange(JRSConstants.JRSCREATEREPORTPAGE_SETCONDITIONVERIFY_SPAN_XPATH,
        "(Custom Trends for Work Items) Start date", "(Custom Trends for Work Items) End date");
  }

  private void verifySetTimeRange(final String xpath, final String startTitle, final String endTitle) {
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_SETTIMERANGEADDBUTTON_BUTTON_XPATH,
        Duration.ofSeconds(60))) {
      List<String> start = new ArrayList<>();
      List<String> end = new ArrayList<>();
      List<WebElement> scopeOptions = this.driverCustom.getWebElements(xpath);
      // Loop through the options and select the one that matches
      for (WebElement opt1 : scopeOptions) {
        Reporter.logInfo("" + opt1.getText());
        addToListIfTextMatch(start, opt1, startTitle);
        addToListIfTextMatch(end, opt1, endTitle);
      }
      if ((start.size() == 1) && (end.size() == 1)) {
        Reporter.logPass("Test Passed Successfully");
      }
      else {
        fail("More then one start or end date");
      }
    }
  }

  private void addToListIfTextMatch(final List<String> list, final WebElement opt1, final String text) {
    if (opt1.getText().equals(text)) {
      list.add(opt1.getText());
    }
  }


  /**
   * LogOut from the JRS server. The method clicks on the LogOut option available in the JRS page. @ - exception during
   * thread wait
   */
  public void logOutFromJRS() {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_USERLOGOUT_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_USERLOGOUT_SPAN_XPATH);
    }
    // Loop through the options and select the one that matches
    selectIfMatches("Log Out", false, JRSConstants.JRSCREATEREPORTPAGE_LOGOUT_A_XPATH, false);
  }


  /**
   * Add mutilple relationship path by enabling multiple paths option in "Trace relationships and add artifacts" section
   * of "CHOOSE DATA" tab in build report page of JRS.
   *
   * @param mergeOrAppend the combining options to combine the relationships. "Merge" or "Append" or "Append in new
   *          columns"
   */
  public void addMultipleRelationship(final String mergeOrAppend) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.multipleRelation = true;
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_ENABLEMUTIPLEPATH_INPUT_XPATH);

      List<WebElement> scopeOptions5 =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_ADDMULTIPLERELATIONSHIPPATH_BUTTON_XPATH);
      scopeOptions5.get(0).click();

      if (mergeOrAppend.equalsIgnoreCase("Merge") && this.driverCustom.isElementPresent(
          JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTMERGE_DIV_XPATH, Duration.ofSeconds(30))) {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTMERGE_DIV_XPATH);
      }
      if (mergeOrAppend.equalsIgnoreCase("Append") && this.driverCustom.isElementPresent(
          JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTAPPEND_DIV_XPATH, Duration.ofSeconds(30))) {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTAPPEND_DIV_XPATH);
      }
      if (mergeOrAppend.equalsIgnoreCase("Append in new columns") && this.driverCustom.isElementPresent(
          JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTAPPENDWITHNEWCOLUMN_DIV_XPATH,
          Duration.ofSeconds(30))) {
        this.driverCustom
            .click(JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTAPPENDWITHNEWCOLUMN_DIV_XPATH);
      }
      if (this.driverCustom.isElementPresent(
          JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTADDBUTTON_BUTTON_XPATH, Duration.ofSeconds(200))) {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_COMBINEMULTIPLEPATHRESULTADDBUTTON_BUTTON_XPATH);
      }
    }
  }

  /**
   * Select an Artifact type in the "Trace relationships and add artifacts" section of "CHOOSE DATA" tab in build report
   * page of JRS. Available when "Enable multiple path" is selected.
   *
   * @param sourceArtifact the artifact to be selected.
   */
  public void changeSourceArtifactInTracebility(final String sourceArtifact) {
    waitForPageLoaded();
    if (this.driverCustom.isElementPresent(
        JRSConstants.JRSCREATEREPORTPAGE_ADDARTIFACTMULTIPLERELATIONSHIP_BUTTON_XPATH, Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_ADDARTIFACTMULTIPLERELATIONSHIP_BUTTON_XPATH);
      chooseArtifacts(sourceArtifact, "false");
      if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_SELECTARTIFACTOKBUTTON_BUTTON_XPATH,
          Duration.ofSeconds(30))) {
        Button btn = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
        btn.click();
      }
    }
  }


  /**
   * Set the column filters in the "RUN REPORT" tab of build report page.
   *
   * @param columnValue Value to be filtered.
   * @param columnName The name of the column to be filtered.
   */
  public void openColumnFilters(final String columnValue, final String columnName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_WIDGETCONTENTSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.switchToFrame(0);
      clickColumnFilter(columnName);
      this.driverCustom.switchToFrame(0);
      Text txtSearchVerify =
          this.engine.findFirstElement(Criteria.isTextField().withPlaceHolder("Enter value here..."));
      txtSearchVerify.setText(columnValue);
      Link filter = this.engine.findFirstElement(Criteria.isLink().withText("Filter"));
      filter.click();
      this.driverCustom.getWebDriver().switchTo().defaultContent();
      this.driverCustom.switchToFrame(0);
      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_WIDGETCONTENTSPINNER_SPAN_XPATH,
          Duration.ofSeconds(20))) {
        List<WebElement> totalRowCount =
            this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_RESULTPAGETABLEROWS_TABLEROWS_XPATH);
        Reporter.logInfo(NO_OF_ROWS_IN_THE_WEB_TABLE_MESSAGE + totalRowCount.size());
        // Now we will Iterate the Table and print the Values
        int rowIndex = 1;
        List<String> columnValues = new ArrayList<>();
        for (WebElement rowElement : totalRowCount) {
          List<WebElement> totalColumnCount = rowElement.findElements(By.className("sortable"));
          addColumnValues(rowIndex, columnValues, totalColumnCount);
          rowIndex = rowIndex + 1;

        }
      }
    }
  }

  private int clickColumnFilter(final String columnName) {
    int columnNumber = 0;
    int count = 0;
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_COLUMNFILTER_TITLE_XPATH,
        Duration.ofSeconds(20))) {
      List<WebElement> columnfilter =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_COLUMNFILTER_TITLE_XPATH);
      for (WebElement elem : columnfilter) {
        if (elem.getText().equals(columnName)) {
          columnNumber = count;
          break;
        }
        count++;
      }
      columnfilter.get(columnNumber - 1).click();
    }
    return columnNumber;
  }

  /**
   * Verify the results for the Unassigned Value.
   *
   * @throws IllegalStateException if the report is not generated.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public void verifyResultForUnassignedValue() {
    waitForPageLoaded();
    this.driverCustom.switchToFrame(0);
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_RESULTCOLUMNCLASS_TD_XPATH,
        Duration.ofSeconds(60))) {
      Reporter.logInfo("Element is present");
    }
    else {
      fail("Result not generated");
    }
  }

  /**
   * Select "Graph" option in the "FORMAT RESULTS" tab in build report page. @ if any thread interrupted the current
   * thread before or while the current thread was waiting for a notification. The <i>interrupted status</i> of the
   * current thread is cleared when this exception is thrown. {@link InterruptedException}
   */
  public void selectGraphInFormatResult() {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      waitForPageLoaded();
      if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_SELECTGRAPHBUTTON_BUTTON_XPATH,
          Duration.ofSeconds(30))) {
        this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SELECTGRAPHBUTTON_BUTTON_XPATH);
      }
      waitForPageLoaded();
      // Loop through the options and select the one that matches
      selectIfMatches("Run report", false, JRSConstants.JRSCREATEREPORTPAGE_LOGOUT_A_XPATH, false);
    }
  }


  /**
   * Select the "View As" value in the Result Page of build report page.
   *
   * @param value the value to be selected from drop down "Graph" or "Table"
   */
  public void selectGraphInResultPage(final String value) {
    waitForPageLoaded();
    this.driverCustom.switchToFrame(0);
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_SELECTGRAPHONRESULTPAGE_DROPDOWN_XPATH,
        Duration.ofSeconds(30))) {
      Select dropdown = new Select(
          this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_SELECTGRAPHONRESULTPAGE_DROPDOWN_XPATH));
      dropdown.selectByVisibleText(value);
    }
  }

  /**
   * Set the relationship existence for the relationship added in the "Trace relationships and add artifacts" section of
   * "CHOOSE DATA" tab in build report page of JRS.
   *
   * @param existenceOptions the existence option to be selected. "Required", "Optional", "Does not exist". @ -
   *          exception during thread wait
   */
  public void addpathExistenceOptions(final String existenceOptions) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(60))) {
      Reporter.logInfo("Found ");
      selectIfMatches(existenceOptions, false,
          JRSConstants.JRSCREATEREPORTPAGE_ADDRELATIONSHIP_PATHEXISTENCEOPTIONS_XPATH, false);
    }
  }

  /**
   * Click on the "RUN REPORT" tab in the build report page.
   */
  public void clickRunReportButton() {
    waitForPageLoaded();
    clickElementButton(JRSConstants.JRSCREATEREPORTPAGE_HEADERMENU_RUNREPORTBUTTON_XPATH);
  }

  private void clickElementButton(final String xpath) {
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      List<WebElement> elements = this.driverCustom.getWebElements(xpath);
      if (elements.size() == 1) {
        elements.get(0).click();
      }
      else {
        fail("There are more than 1 run report button, test case fail due to error in Report page");
      }
    }
  }

  /**
   * Click on the "CHOOSE DATA" tab in the build report page.
   */
  public void clickChooseDataButton() {
    waitForPageLoaded();
    clickElementButton(JRSConstants.JRSCREATEREPORTPAGE_HEADERMENU_CHOOSEDATABUTTON_XPATH);
  }

  /**
   * Verify the alert message matches the expected alert message.
   *
   * @param alertMessage the expected alert message
   */
  public void verifyAlertSession(final String alertMessage) {
    waitForPageLoaded();
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_ALERTSESSION_ALERTSUMMARY_XPATH,
        Duration.ofSeconds(30))) {
      List<WebElement> elements =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_ALERTSESSION_ALERTSUMMARY_XPATH);
      if (elements.size() == 1) {
        Reporter.logInfo("oda1hc Message: " + elements.get(0).getText());
        if (elements.get(0).getText().contains(alertMessage)) {
          Reporter.logPass("Alert Message Matched, test case passed");
        }
        else {
          Reporter.logInfo("oda1hc Message: " + elements.get(0).getText());
          fail("Alert Message not Matched,test case failed");
        }
      }
    }
  }

  /**
   * Select the "Export the report to Microsoft Excel" in the "RUN REPORT" tab of build report page and download the
   * file.
   */
  /*
   * public void exportToExcel() { waitForPageLoaded(); if
   * (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_WIDGETCONTENTSPINNER_SPAN_XPATH, 30)) {
   * this.driverCustom.switchToFrame(0); if
   * (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_EXPORTTOEXCELBUTTON_BUTTON_XPATH, 30)) {
   * this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_EXPORTTOEXCELBUTTON_BUTTON_XPATH); } if
   * (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_EXPORTTOEXCELLIST_A_XPATH, 30)) {
   * this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_EXPORTTOEXCELLIST_A_XPATH); }
   * this.driverCustom.switchBrowserTabs();
   * this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_DOWNLOADEXCELSPREADSHEET_A_XPATH); } }
   */

  /**
   * Validate the report that are exported to excel using the "Export the report to Microsoft Excel" option
   *
   * @param totalRecordsInExcel the expected number of records in the exported file.
   * @throws IOException Exception during reading the excel file. {@link IOException}
   */
  /*
   * public void validateExcelResult(final String totalRecordsInExcel) throws IOException { waitForSecs(10); String
   * excelReportName = this.savedReportName.replace(" ", "_"); try ( InputStream excelFileToRead = new
   * FileInputStream(new File(this.driverCustom.getDownloadFolderLocation(), excelReportName + ".xlsx")); XSSFWorkbook
   * wb = new XSSFWorkbook(excelFileToRead)) { XSSFSheet sheet = wb.getSheetAt(1); int totalRecords =
   * sheet.getPhysicalNumberOfRows(); int result = Integer.parseInt(totalRecordsInExcel); if ((totalRecords - 1) ==
   * result) { Reporter.logPass("No of records are correct- Test case passed"); } else {
   * fail("Number of records in excel are not correct"); } } }
   */

  /**
   * Verify if the Graph result is as per the expected result.
   *
   * @param screen expected graph result
   * @param pattern expected graph result pattern
   * @param finder instance that helps to find image/pattern in another
   */
  /*
   * public void verifyResultThroughSnapshot(final Screen screen, final Pattern pattern, final Finder finder) {
   * waitForPageLoaded(); if
   * (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_REPORTLOADINGSPINNER_SPAN_XPATH, 30)) {
   * verifyPattern(pattern, finder); } }
   */

  /**
   * Verify if the Graph result is as per the expected result.
   *
   * @param screen expected graph result
   * @param pattern expected graph result pattern
   */
  /*
   * public void verifyGraphResult(final Screen screen, final Pattern pattern) { waitForPageLoaded(); if
   * (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_REPORTLOADINGSPINNER_SPAN_XPATH, 30) &&
   * this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_WIDGETCONTENTSPINNER_SPAN_XPATH, 30)) {
   * this.driverCustom.switchToFrame(0); verifyGraphResultInContainer(screen, pattern); } }
   */

  /**
   * Verify the results for the calculated column in the "RUN REPORT" tab of build report page.
   *
   * @param calculatedColumnName the column to be tested
   * @param calculatedColumnvalue the expected value for the column
   * @return true or false.
   */
  public boolean checkCalculatedColumnResultsAddedInReport(final String calculatedColumnName,
      final String calculatedColumnvalue) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH,
        Duration.ofSeconds(50))) {
      this.driverCustom.switchToFrame(JRSConstants.JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH);
    }
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_RESULTTABLECLASS_TABLE_XPATH,
        Duration.ofSeconds(50))) {
      List<WebElement> totalRowCount =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_RESULTTABLECLASS_TABLE_XPATH);
      Reporter.logInfo(NO_OF_ROWS_IN_THE_WEB_TABLE_MESSAGE + totalRowCount.size());
      // Now we will Iterate the Table and print the Values
      Map<String, String> colValues = fetchColValue(totalRowCount);

      if (colValues.containsKey(calculatedColumnName)) {
        if (colValues.containsValue(calculatedColumnvalue)) {
          Reporter.logPass("Test Pass");
          this.driverCustom.switchToParentFrame();
          return true;
        }
        fail("calculated column value is not correct");
        return false;
      }
      fail("calculated calumn name is not correct");
      return false;
    }
    return false;
  }

  private Map<String, String> fetchColValue(final List<WebElement> totalRowCount) {
    int rowIndex = 1;
    List<String> columnValues = new ArrayList<>();
    List<String> columnHedValues = new ArrayList<>();
    Map<String, String> colValues = new HashMap<>();

    for (WebElement rowElement : totalRowCount) {
      List<WebElement> totalColumnCount = rowElement.findElements(By.cssSelector("td"));
      List<WebElement> totalHeadingColumnCount = rowElement.findElements(By.cssSelector("th"));
      addColumnValues(rowIndex, columnHedValues, totalHeadingColumnCount);

      addColumnValues(rowIndex, columnValues, totalColumnCount);

      for (int i = 0; (i < columnHedValues.size()) && (i < columnValues.size()); i++) {
        colValues.put(columnHedValues.get(i).trim(), columnValues.get(i).trim());
      }
      rowIndex = rowIndex + 1;
    }
    return colValues;
  }

  /**
   * Set the units and dimensions for the graph when "Graph" is selected in the "FORMATS RESULTS" tab of build report
   * page.
   *
   * @param units the units value to be selected
   * @param dimension the dimension value to be selected
   */
  public void setGraphValuesInFormatResult(final String units, final String dimension) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      Select unitDropdown =
          new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_GRAPHUNITS_DROPDOWN_XPATH));
      unitDropdown.selectByVisibleText(units);
      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
          Duration.ofSeconds(30))) {
        Select dimensionDropdown = new Select(
            this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_GRAPHDIMENSIONS_DROPDOWN_XPATH));
        dimensionDropdown.selectByVisibleText(dimension);
      }
    }
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(20))) {
      Reporter.logInfo("Graph is set now");
    }
  }

  /**
   * Find a report in the "All Reports" Page of JRS. The method searches for the report that was saved in a previous
   * action.
   */
  public void findReport() {

    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_SELECTALLCHECKBOXES_CHECKBOXES_XPATH,
        Duration.ofSeconds(30))) {
      boolean artfactFound = false;
      List<WebElement> scopeOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_REPORTNAME_REPORTNAME_XPATH);
      // Loop through the options and select the one that matches
      for (WebElement opt1 : scopeOptions) {
        if (opt1.getText().trim().equals(this.savedReportName.trim())) {
          Reporter.logPass("Report found");
          artfactFound = true;
          break;
        }
      }
      if (!artfactFound) {
        fail("Tag not found");
      }
    }
  }

  /**
   * Select the Graph Type when Graph was selected in the "FORMATS RESULTS" tab of build report page.
   *
   * @param graphType the graph type to be selected
   */
  public void setGraphType(final String graphType) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SETGRAPHTYPE_BUTTON_XPATH);

      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_STACKEDCHARTTYPE_DIV_XPATH,
          Duration.ofSeconds(30))) {

        if (graphType.equals("Stacked Bar")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_STACKEDCHARTTYPE_DIV_XPATH);
        }
        if (graphType.equals("Grouped Bar")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_GROUPEDBARCHARTTYPE_DIV_XPATH);
        }
        if (graphType.equals("Line")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_LINECHARTTYPE_DIV_XPATH);
        }
      }
    }
  }

  /**
   * Set the Date Scale when Graph was selected in the "FORMATS RESULTS" tab in the build report page. For Date Scale to
   * be available for historical trends and when attribute of type Date is selected for the axis.
   *
   * @param Date the date scale to be selected. options available "Days","Weeks","Months","Years".
   */
  public void setDateScale(final String Date) {
    waitForPageLoaded();
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SETDATESCALE_BUTTON_XPATH);

      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_SCALEBYDAYS_INPUT_XPATH,
          Duration.ofSeconds(30))) {

        if (Date.equals("Days")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SCALEBYDAYS_INPUT_XPATH);
        }
        if (Date.equals("Weeks")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SCALEBYWEEKS_INPUT_XPATH);
        }
        if (Date.equals("Months")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SCALEBYMONTHS_INPUT_XPATH);
        }
        if (Date.equals("Years")) {
          this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_SCALEBYYEARS_INPUT_XPATH);
        }
      }
    }
  }

  /**
   * @param reportname use to provide the name of JRS report.
   */
  public void setReportName(final String reportname) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_REPORTNAME_TEXTBOX_XPATH, Duration.ofSeconds(5),
        "resource-title");
    this.driverCustom.typeText(JRSConstants.JRSCREATEREPORTPAGE_REPORTNAME_TEXTBOX_XPATH, reportname, "resource-title");
  }

  /**
   * @param testcaseid use to provide the name of JRS report.
   * @return ccmworkitemID as per testcaseID.
   */
  public String validateReport(final String testcaseid) {
    waitForPageLoaded();
    this.driverCustom.switchToFrame(JRSConstants.JRSVIEWREPORTPAGE_RUNREPORTFRAME_ID_XPATH);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSVIEWREPORTPAGE_QMWORKITEM_ID_XPATH, testcaseid);
    return this.driverCustom.getWebElement(JRSConstants.JRSVIEWREPORTPAGE_CCMWORKITEM_ID_XPATH, testcaseid).getText();
  }

  /**
   * @param label use to find the label from the pick a relationship.
   * @param relationship use to select under the particular label.
   */

  public void addRelationshipByLabel(final String label, final String relationship) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    try {
      new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ESCAPE).build().perform();
      waitForSecs(3);
      selectAddRelationShip();
    }
    catch (Exception e) {
      new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ESCAPE).build().perform();
      waitForSecs(3);
      this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
          this.timeInSecs);
      this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
      clickOnJazzButtons("Add a relationship");
    }
    this.driverCustom.waitForPageLoaded();
    waitForPageLoaded();
    By locator = this.driverCustom.createLocatorFromProperty(
        JRSConstants.JRSVIEWREPORTPAGE_PICKARELATIONSHIP_TEXT_XPATH, new String[] { label, relationship });
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    waitForSecs(10);
    WebElement ele = this.driverCustom.getWebDriver().findElement(locator);
    JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
    je.executeScript("arguments[0].scrollIntoView(true);", ele);
    List<WebElement> elem = this.driverCustom.getWebDriver().findElements(locator);
    for (WebElement elemen : elem) {
      if (elemen.isDisplayed()) {
        elemen.click();
      }
    }
    clickOKButton();
  }


  /**
   * This method clicking on add relationship.
   */
  public void clickOnAddRelationship() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSVIEWREPORTPAGE_CLOCKONADDRELATIONSHIP_XPATH);
    waitForPageLoaded();
    this.driverCustom.click(JRSConstants.JRSVIEWREPORTPAGE_CLOCKONADDRELATIONSHIP_XPATH);
  }

  /**
   * This method clicking on pic a relationship.
   */
  public void picARelationship() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSVIEWREPORTPAGE_PICARELATIONSHIP_XPATH);
    waitForPageLoaded();
    this.driverCustom.click(JRSConstants.JRSVIEWREPORTPAGE_PICARELATIONSHIP_XPATH);
    waitForPageLoaded();
  }

  /**
   * This method clicking on ok button in relationship.
   */
  public void clickOkRelationship() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSVIEWREPORTPAGE_CLICKONOKRELATIONSHIP_XPATH);
    waitForPageLoaded();
    this.driverCustom.click(JRSConstants.JRSVIEWREPORTPAGE_CLICKONOKRELATIONSHIP_XPATH);
    waitForPageLoaded();
  }

  /**
   * This method clicking on Work Item in pic an artifact from related work item.
   */
  public void picAnAtrifactFromRelatedWorkItem() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSVIEWREPORTPAGE_PICANARTIFACT_XPATH);
    waitForPageLoaded();
    this.driverCustom.click(JRSConstants.JRSVIEWREPORTPAGE_PICANARTIFACT_XPATH);
    waitForPageLoaded();
  }

  /**
   * This method clicking on ok button from an artifact.
   */
  public void clickOkfromAnArtifact() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSVIEWREPORTPAGE_CLICKOKFROMARIFACT_XPATH);
    waitForPageLoaded();
    this.driverCustom.click(JRSConstants.JRSVIEWREPORTPAGE_CLICKOKFROMARIFACT_XPATH);
    waitForPageLoaded();
  }

  /**
   * Select the artifact whose label matches the input value.
   *
   * @param artifactValue the value of the artifact to be selected
   * @param childArtifact true if childArtifact needs to be chosen
   * @throws IllegalStateException if the artifact is not found.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public void chooseAnArtifact(final String artifactValue, final String childArtifact) {
    waitForPageLoaded();
    showMyChoices();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEARTIFACT_LABEL_XPATH,
        artifactValue);
    if (childArtifact.contains("null")) {
      this.driverCustom
          .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEARTIFACT_LABEL_XPATH, artifactValue)
          .click();
      return;
    }
    else if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30)) &&
        this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
            Duration.ofSeconds(30))) {
      this.driverCustom.getPresenceOfWebElement(
          JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEARTIFACT_LABELEXPAND_BUTTON_XPATH, artifactValue).click();
      waitForSecs(7);
      List<WebElement> scopeOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CHOOSEAETIFACT_LABEL_XPATH);
      // Loop through the options and select the one that matches
      for (WebElement opt1 : scopeOptions) {
        if (opt1.getText().trim().equals(childArtifact.trim())) {
          opt1.click();
          if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_WORNINGWIDGET_OKBUTTON_XPATH,
              Duration.ofSeconds(10))) {
            this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_WORNINGWIDGET_OKBUTTON_XPATH);
          }
          waitForSecs(2);
          return;
        }
      }
    }
  }

  /**
   * <p>
   * Set condition type like: is, isn't, after {@link JRSBuildNewReportPage#setCondition(String, String)}
   *
   * @param type condition type.
   */
  public void setConditionType(final String type) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement("//div[@class='dropdown attribute-options']//span[@class='caret']")
        .click();
    this.driverCustom
        .getPresenceOfWebElement("//div[@class='dropdown attribute-options open']//ul[@class='dropdown-menu']");
    List<WebElement> types = this.driverCustom
        .getWebElements("//div[@class='dropdown attribute-options open']//ul[@class='dropdown-menu']//li/a");
    for (WebElement ele : types) {
      if (ele.getText().equalsIgnoreCase(type)) {
        ele.click();
        LOGGER.LOG.info("Condition Type is : " + type);
      }
    }

  }

  /**
   * <p>
   * Set condition type like: is, isn't, after {@link JRSBuildNewReportPage#setCondition(String, String)}
   *
   * @param dropDownValue add condition Attributes of value.
   */
  public void setConditionAttributesOfvalue(final String dropDownValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    if(this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']",timeInSecs)){
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    this.driverCustom.getPresenceOfWebElement("//select[@id='condition-resource']").click();
    waitForSecs(10);
    Dropdown drdAttributeOf = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Attributes of"), this.timeInSecs).getFirstElement();
    drdAttributeOf.selectOptionWithPartText(dropDownValue);
    LOGGER.LOG.info("Drop down selected as " + dropDownValue);
  }

  /**
   * <p>
   * Set condition value in search box.
   *
   * @param conditionName name of the condition.
   */
  public void searchCondition(final String conditionName) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    WebElement txtSearch = this.driverCustom
        .getPresenceOfWebElement("//div[@class='resource-attribute-selection']//input[@placeholder='Search']");
    txtSearch.clear();
    txtSearch.sendKeys(conditionName);
  }

  /**
   * <p>
   * Click on filtered value {@link JRSBuildNewReportPage#searchCondition(String)}
   *
   * @param filterValue value.
   * @return true if the button is selected successfully
   */
  public Boolean clickOnRadiobutton(final String filterValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    waitForSecs(5);
    List<WebElement> scopeOptions =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH);
    for (WebElement opt9 : scopeOptions) {
      if (opt9.getText().trim().equals(filterValue.trim())) {
        opt9.click();
        return true;
      }
    }
    return false;
  }

  /**
   * <p>
   * Set condition date from add condition widget. {@link JRSBuildNewReportPage#setConditionType(String)}
   *
   * @param buttonName date.
   */
  public void setClickOnfilterRadioButton(final String buttonName) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom
        .getFirstVisibleWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_FILTER_RADIO_BUTTON_XPATH, buttonName).click();
  }

  /**
   * <p>
   * Set condition date from add condition widget. {@link JRSBuildNewReportPage#setConditionType(String)}
   *
   * @param date date.
   */
  public void setConditionDate(final String date) {
    Text txbDate =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("mm/dd/yyyy"), this.timeInSecs)
            .getFirstElement();
    this.driverCustom.click("//input[@value='exactdate'][@type='radio']");
    waitForSecs(2);
    txbDate.setText(date);
  }

  /**
   * <p>
   * Set a condition value into text field after selecting a condition attribue.
   * {@link JRSBuildNewReportPage#setConditionType(String)}
   *
   * @author YNT2HC
   * @param conditionValue is the value which want to input
   * @return true if entering conditionValue successfully
   */
  public Boolean setConditionInTextBox(final String conditionValue) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_CONDITION_INPUT_XPATH, this.timeInSecs)) {
      WebElement txtCondition = this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_CONDITION_INPUT_XPATH);
      txtCondition.click();
      txtCondition.sendKeys(conditionValue);
      waitForSecs(2);
      return true;
    }
    return false;
  }

  /**
   * <p>
   * Set condition date from add condition widget with 'daysago' type and appropriate value.
   * {@link JRSBuildNewReportPage#setConditionType(String)}
   *
   * @author YNT2HC
   * @param dayValue is number of days or months or years
   * @param selectDaysago is a dropdown to select daysago, monthsago, yeasago values
   * @return true if entering daysago successfully
   */
  public Boolean setDaysAgo(final String dayValue, final String selectDaysago) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_CONDITION_DAYSAGO_INPUT_XPATH,
        this.timeInSecs)) {
      Select drdDaysago =
          new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_CONDITION_DAYSAGO_SELECT_XPATH));
      drdDaysago.selectByVisibleText(selectDaysago);
      WebElement txtDaysago =
          this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_CONDITION_DAYSAGO_INPUT_XPATH);
      txtDaysago.sendKeys(dayValue);
      waitForSecs(2);
      return true;
    }
    return false;
  }

  /**
   * <p>
   * Clicks on tab which is present in same window with multiple pages. <br>
   * E.g. CCM work item page Overview, Links, Attachment etc tabs.
   *
   * @param name is common for all image buttons
   * @return true if clicking successfully
   */
  public Boolean clickOnFormatButton(final String name) {
    try {
      WebElement btnFormat =
          this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSCREATEREPORTPAGE_FORMAT_BUTTON_XPATH, name);
      btnFormat.click();
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * <p>
   * Clicks on tab which is present in same window with multiple pages. <br>
   * E.g. CCM work item page Overview, Links, Attachment etc tabs.
   *
   * @param tab is common for all image buttons
   * @return the name of tab
   */
  public String clickOnReportTab(final String tab) {
    waitForSecs(5);
    try {
      JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
      js.executeScript("window.scrollBy(0,-450)");
    }
    catch (Exception e) {}
    if(this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']",timeInSecs)){
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    try {
      Tab JRSTab =
          this.engine.findElementWithDuration(Criteria.isTab().withText(tab), this.timeInSecs).getFirstElement();
      JRSTab.click();
    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement("//a[@data-label='" + tab + "']").click();
    }
    waitForSecs(2);
    return tab;
  }

  /**
   * <p>
   * Set text box value as per the attribute name in Format Results, Name and Share.
   *
   * @param attributeName name of attribute.
   * @param attributeValue value of attribute.
   */
  public void setJrsTextBoxValue(final String attributeName, final String attributeValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_ATTRIBUTE_TEXTBOX_XPATH, this.timeInSecs,
        attributeName)) {
      this.driverCustom
          .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_ATTRIBUTE_TEXTBOX_XPATH, attributeName)
          .sendKeys(attributeValue);
    }
    else {
      this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_TEXTBOX_XPATH, attributeName)
          .sendKeys(attributeValue);
    }
  }

  /**
   * @param graphPropertyName
   * @param graphPropertyValue
   * @return
   */
  public boolean isGraphPropertyPresent(final String graphPropertyName, final String graphPropertyValue) {
    if (this.driverCustom.isElementPresent("//*[local-name()='text' and text()='" + graphPropertyValue + "']",
        Duration.ofSeconds(30))) {

      LOGGER.LOG.info(graphPropertyName + " : " + graphPropertyValue + " updated in the preview section.");
      return true;
    }
    LOGGER.LOG.info(" not automated successfully. - Expected data " + graphPropertyName +
        " not available in the preview section. please check updated data.");
    return false;
  }

  /**
   * @param graphPropertyName
   * @param graphPropertyValue
   * @return
   */
  public boolean isGraphPropertyPresentInReport(final String graphPropertyName, final String graphPropertyValue) {
    waitForSecs(5);
    this.driverCustom.switchToFrame("//iframe[@id= 'run-report-frame']");
    this.driverCustom.getPresenceOfWebElement(
        "//div[@id='chart-page-content']//*[local-name()='text' and text()='" + graphPropertyValue + "']");
    if (this.driverCustom.isElementPresent(
        "//div[@id='chart-page-content']//*[local-name()='text' and text()='" + graphPropertyValue + "']",
        Duration.ofSeconds(30))) {

      LOGGER.LOG.info(graphPropertyName + " : " + graphPropertyValue + " updated in the Report section.");
      this.driverCustom.switchToDefaultContent();
      return true;
    }
    else {
      LOGGER.LOG.info(" not automated successfully. - Expected data " + graphPropertyName +
          " not available in the preview section. please check updated data.");
      return false;
    }


  }

  /**
   * @return
   */
  public String getViewAs() {
    waitForPageLoaded();
    waitForSecs(5);
    this.driverCustom.switchToFrame("//iframe[@id= 'run-report-frame']");
    this.driverCustom.getPresenceOfWebElement("//select[@id='viz-select']");
    if (!this.driverCustom.isElementVisible("//select[@id='viz-select']",
        this.driverCustom.getWebDriverSetup().getConfigurationForImplicitWaitTime())) {
      throw new WebAutomationException("View as drop down not loaded in Report page");
    }
    try {
      String presentState = this.driverCustom.getSelectedValueFromDropDown("//select[@id='viz-select']");
      LOGGER.LOG.info("Current View state of the Report is : " + presentState);
      return this.driverCustom.getSelectedValueFromDropDown("//select[@id='viz-select']");
    }
    catch (Exception e) {
      throw new WebAutomationException("Not found state of the Report. " + " or \n" + e.getMessage());
    }
  }

  /**
   * <p>
   * Set Group data by attribute value.
   *
   * @param dropDownValue drop down value.
   */
  public void setGroupByAttributeValue(final String dropDownValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    Select sel = new Select(this.driverCustom
        .getWebElement("//label[text()='Group data by attribute:']/ancestor::div[@class='row']//select"));
    sel.selectByVisibleText(dropDownValue);
  }

  /**
   * <p>
   * Set Group data by attribute value.
   *
   * @param dropDownValue drop down value.
   */
  public void setDimensionValue(final String dropDownValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='series-select']"));
    sel.selectByVisibleText(dropDownValue);
  }

  /**
   * <p>
   * Set Group data by attribute value.
   *
   * @param dropDownValue drop down value.
   */
  public void setUnits(final String dropDownValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='count-select']"));
    sel.selectByVisibleText(dropDownValue);
  }

  /**
   * <p>
   * This method selects Graph chart type from "Graph type", "Sort" and "Orientation".
   *
   * @param chartSelect this is the name of chart drop down like "Graph type", "Sort" and "Orientation".
   * @param chartType this is the type of chart you want to select from the drop down e.g.:- from "Graph type" drop down
   *          "Bar" or "Pie". From "Sort" drop down "High to Low" or "Low To High". From "Orientation" drop down
   *          "Vertical" or "Horizontal".
   */
  public void selectGraphAndChart(final String chartSelect, final String chartType) {
    waitForSecs(5);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CHARTTYPE_DROPDOWN_XPATH, chartSelect)
        .click();
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CHART_BUTTON_XPATH, chartType).click();
    waitForSecs(10);
    LOGGER.LOG.info("selected " + chartSelect + " " + chartType);
  }

  /**
   *
   */
  public void selectShowValuesAndShowTotals() {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom
        .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CHARTTYPE_DROPDOWN_XPATH, "Graph type:").click();
    this.driverCustom
        .getPresenceOfWebElement("//label[@class='graph-type-show-values']/preceding-sibling::input[@type='checkbox']")
        .click();
    waitForSecs(5);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom
        .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CHARTTYPE_DROPDOWN_XPATH, "Graph type:").click();
    this.driverCustom
        .getPresenceOfWebElement("//label[@class='graph-type-show-totals']/preceding-sibling::input[@type='checkbox']")
        .click();
  }

  /**
   * @return true or false.
   */
  public boolean validateHorizontalGraph() {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(
        "//div[@id='chart-loadingProgress']//h3[contains(text(),'Loading, please wait...')]", Duration.ofSeconds(240));
    List<WebElement> ele = this.driverCustom
        .getWebElements("//div[@id='chart-container']//*[@class='valgroup']//*[contains(@class,'bar')]");
    for (WebElement elem : ele) {
      String xvalues = elem.getAttribute("x");
      String yvalues = elem.getAttribute("y");
      System.out.println(xvalues);
      System.out.println(yvalues);

      if (xvalues.contains("58") && yvalues.contains("150")) {
        return false;
      }
    }
    return true;
  }

  /**
   * <p>
   * This method clicks on the Attribute button of the Format Results tab.
   */
  public void clickAttributesButton() {
    if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWSPINNER_SPAN_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDATTRIBUTE_XPATH);
      LOGGER.LOG.info("Attribute button is been clicked.");
    }
  }

  /**
   * <p>
   * This method clicks on the Add Attribute button after columns added on the Format Results tab.
   */
  public void clickAttributeAddButton() {
    this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ADD_COLUMN_BUTTON_ADDATTRIBUTE_XPATH).click();
  }

  /**
   * <p>
   * This method clicks on the Search button in the Add Attribute section on the Format Results tab.
   *
   * @param value - select the value from the list of values in the add attribute of:
   */
  public void clickSearchedAttribute(final String value) {
    {
      if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CHILDARTIFACT_ARTIFACT_XPATH,
          Duration.ofSeconds(5))) {
        selectIfMatches(value, false, JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH, false);
      }
    }
  }


  /**
   * <p>
   * This method verifys whether the cursor drags and drops the column header on the Run Report tab.
   *
   * @throws InterruptedException
   */
  public void clickDragAndDropCursorWidth() throws InterruptedException {

    WebDriver driver = this.driverCustom.getWebDriver();

    // Locate the table element
    WebElement table = driver.findElement(By.id("table-header-container-54374"));
    System.out.println("Report results Table found");

    // Get the target column element (adjust locator based on your table structure)
    WebElement columnHeader = table.findElement(By.xpath("//*[@id='table-viewport']"));
    System.out.println("The column header xpath found");

    // Cast the driver to JavascriptExecutor
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Script to set the width of the column element
    String script = "arguments[0].style.width = '400px';";
    js.executeScript(script, columnHeader);
    System.out.println("The column header is adjusted to 400px");


  }

  /**
   * <p>
   * Set condition type like: is, isn't, after {@link JRSBuildNewReportPage#setCondition(String, String)}
   *
   * @param dropDownValue add condition Attributes of value.
   */
  public void setAddAttributesToTheReport(final String dropDownValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.getPresenceOfWebElement("//select[@id='columns-resource']").click();
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='columns-resource']"));
    sel.selectByVisibleText(dropDownValue);
    waitForSecs(3);
    new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.TAB).build().perform();
    waitForSecs(3);
    LOGGER.LOG.info("Drop down selected as " + dropDownValue);
  }

  /**
   * <p>
   * Set condition value in search box.
   *
   * @param conditionName name of the condition.
   */
  public void searchAttributeCondition(final String conditionName) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    Text txtSearch = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Search")).getFirstElement();
    txtSearch.setText(conditionName);
  }

  /**
   * <p>
   * Set Privacy and sharing by attribute value in report formation under Name and Share section
   *
   * @param dropDownValue drop down value.
   */
  public void setPrivacyAndSharing(final String dropDownValue) {
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='resource-visibility']"));
    sel.selectByVisibleText(dropDownValue);
  }

  /**
   * <p>
   * Set Privacy and sharing by attribute value in all reports page under Filter Report
   *
   * @param dropDownValue drop down value.
   */
  public void setPrivacyAndSharingInAllReportsPage(final String dropDownValue) {
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='all-reports-filter-select-visibility']"));
    sel.selectByVisibleText(dropDownValue);
  }

  /**
   * <p>
   * Set Group data by attribute value.
   *
   * @param dropDownValue drop down value.
   */
  public void setDefaultVisualization(final String dropDownValue) {
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='default-viz-select']"));
    sel.selectByVisibleText(dropDownValue);
  }

  /**
   * @param reportName name.
   */
  public void setJRSReportName(final String reportName) {
    Text txtSearch =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Name this report"), this.timeInSecs)
            .getFirstElement();
    txtSearch.setText(reportName);
  }

  /**
   * @param report name of report.
   * @param groupValue group value.
   * @param folderName name of folder.
   */
  public void deleteReport(final String report, final String groupValue, final String folderName) {
    String url = this.driverCustom.getWebDriver().getCurrentUrl();
    String mainurl = url.substring(0, url.indexOf("rs")+ 2);
//    this.driverCustom.getWebDriver().navigate().to(mainurl + "#mine");
    this.driverCustom.getWebDriver().navigate().to(mainurl);
//    refresh();
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_MYREPORT_LINK_XPATH, Duration.ofSeconds(10));
    this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWN_XPATH,
        Duration.ofSeconds(10));
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECT_XPATH,
        Duration.ofSeconds(30))) {
      Select dropdown =
          new Select(this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECT_XPATH));
      List<WebElement> listOptions = dropdown.getOptions();
      for (WebElement opt1 : listOptions) {
        if (opt1.getText().contains(groupValue)) {
          opt1.click();
          break;
        }
      }
      this.driverCustom
          .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYFOLDEREXPAND_XPATH, folderName).click();
      Text txtSearch =
          this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter")).getFirstElement();
      txtSearch.setText(report + Keys.ENTER);
    }
    else {
      if(this.driverCustom.isElementVisible("//div[@id='mine-reports-dropdown']", Duration.ofSeconds(30))) {
      // Select Group of folder in 02t1 v7
      this.driverCustom.click("//div[@id='mine-reports-dropdown']");
      List<WebElement> listOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_TAB_XPATH, groupValue);
      for (WebElement opt1 : listOptions) {
        if (opt1.isDisplayed()) {
          opt1.click();
          break;
        }
      }
      this.driverCustom
      .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYFOLDEREXPAND_XPATH, folderName).click();
    Text txtSearch = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Search")).getFirstElement();
    txtSearch.setText(report + Keys.ENTER);
      }
      else
      {
        // JRS 7.0.3
        //search folder
        Text txtFolderSearch = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Search in current folder")).getFirstElement();
        txtFolderSearch.setText(folderName + Keys.ENTER);
        this.driverCustom
        .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_FOLDER_XPATH, folderName).click();
        Text txtReportSearch = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Search in current folder")).getFirstElement();
        txtReportSearch.setText(report + Keys.ENTER);
      }
    }
    try {
      // JRS 7.0.3
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORTMOREACTION_XPATH).click();
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORTDELETE_XPATH).click();
      this.driverCustom.getWebElement("//button[contains(text(),'Delete')]").click();
    }
    catch (Exception e) {
      // JRS 7.0.2
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORTNAME_XPATH, report).click();
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORT_DELETE_XPATH, report).click();
      this.driverCustom.getWebDriver().switchTo().alert().accept();
    }
//    if(this.driverCustom.isElementVisible("//div[@id='#modal_root']", Duration.ofSeconds(30))) {
//      // JRS 7.0.3
//      WebElement btn = this.driverCustom.getWebElement("//button[contains(text(),'Delete')]");
//
////      Button btn = this.engine.findElement(Criteria.isButton().withText("Delete")).getFirstElement();
//      btn.click();
//    }
//    else {
//      // JRS 7.0.2
//      Alert alt = this.driverCustom.getWebDriver().switchTo().alert();
//      alt.accept();
//    }

  }

  /**
   *
   */
  public void setColor() {
    waitForSecs(10);
    this.driverCustom.getFirstVisibleWebElement("//input[@id='DYNAMIC_VAlUE']/following-sibling::div", "colorPicker0")
        .click();
    this.driverCustom.getFirstVisibleWebElement("//input[@id='DYNAMIC_VAlUE']", "colorPicker0")
        .getAttribute("data-color");
    this.driverCustom.getFirstVisibleWebElement("//input[@id='DYNAMIC_VAlUE']/following-sibling::div", "colorPicker1")
        .click();
    this.driverCustom.getFirstVisibleWebElement("//input[@id='DYNAMIC_VAlUE']", "colorPicker1")
        .getAttribute("data-color");
    this.driverCustom.getFirstVisibleWebElement("//input[@id='DYNAMIC_VAlUE']/following-sibling::div", "colorPicker2")
        .click();
    this.driverCustom.getFirstVisibleWebElement("//input[@id='DYNAMIC_VAlUE']", "colorPicker2")
        .getAttribute("data-color");
  }

  /**
   * <p>
   * Click on 'Choose Configuration' option from 'Filter' window.
   *
   * @return true or false.
   */
  public boolean clickOnchooseConfigurationInFilter() {
    waitForSecs(5);
    // Switch to frame
    this.driverCustom.switchToFrame(JRSConstants.JRSBUILDNEWREPORTPAGE_SWITCHTOIFRAME_XPATH);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONFILTEROPTION_XPATH).click();
    return true;
  }

  /**
   * <p>
   * Choose Domain from 'Choose Configuration' wizard.
   *
   * @param domainDropDownValue dropdown value
   * @return true or false.
   */

  public boolean chooseDomainForConfigurations(final String domainDropDownValue) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_XPATH,
        Duration.ofSeconds(20), "Choose a configuration")) {
      this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_XPATH,
          "Choose a configuration").click();
      // Choose Domain from 'Choose Configuration' wizard.
      Button button = null;
      try {
        if (this.driverCustom.isElementVisible(
            "//p[text()='Choose a configuration']/parent::button[@aria-expanded='false']", this.timeInSecs)) {
          this.driverCustom.getWebElement("//p[text()='Choose a configuration']/parent::button").click();
        }
        button = this.engine.findElement(Criteria.isButton().withText("Choose a configuration")).getFirstElement();
      }
      catch (Exception ex) {
        this.driverCustom.switchToFrame(JRSConstants.JRSBUILDNEWREPORTPAGE_SWITCHTOIFRAME_XPATH);
        this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_XPATH,
            "Choose a configuration").click();
        // run on ELM 6.0
        return true;
      }
      button.click();
      this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_LOCATECHOOSEDOMAIN_XPATH,
          Duration.ofSeconds(10));
      this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEDOMAIN_XPATH, domainDropDownValue,
          SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      waitForSecs(1);
      return true;
    }
    return true;
  }

  /**
   * <p>
   * Choose a Project Area from 'Choose Configuration' wizard.
   *
   * @param paDropDownValue dropdown value
   * @return true or false.
   */
  public boolean chooseProjectAreaForConfigurations(final String paDropDownValue) {
    // Choose a Project Area from 'Choose Configuration' wizard.
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONPROJECTAREA_XPATH,
        Duration.ofSeconds(20))) {
      this.driverCustom.click(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONPROJECTAREA_XPATH);
      this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHPROJECTAREATEXTBOX_XPATH)
          .sendKeys(paDropDownValue);
      this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSEPROJECTAREA_XPATH, paDropDownValue,
          SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      return true;
    }
    return true;
  }

  /**
   * <p>
   * Choose Component from 'Choose Configuration' wizard.
   *
   * @param componentDropDownValue dropdown value
   * @return true or false.
   */
  public boolean chooseComponentForConfigurations(final String componentDropDownValue) {
    // Choose Component from 'Choose Configuration' wizard.
    this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH, Duration.ofSeconds(10));
    this.driverCustom.click(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCOMPONENT_XPATH);
    if (!this.driverCustom.isElementPresent("//span[text()=' Unassigned']", Duration.ofSeconds(10))) {
      this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHCOMPONENTTEXTBOX_XPATH)
          .sendKeys(componentDropDownValue + Keys.ENTER);
      Boolean isDialogTitle = false;
      try {
        isDialogTitle = this.driverCustom.isElementPresent("//p[@id='choose-config-title']", Duration.ofSeconds(30));
      }
      catch (Exception e) {
        // not found element
      }
      if (!isDialogTitle) {
        WebElement optionComponent = this.driverCustom
            .getPresenceOfWebElement("//span[@class='label-text' and text()=' " + componentDropDownValue + "']");
        try {
          optionComponent.click();
        }
        catch (Exception e) {
          this.driverCustom.clickUsingActions(optionComponent);
        }
      }
      else {
        this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSECOMPONENT_XPATH, componentDropDownValue,
            SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
      }
      return true;
    }
    return true;

  }

  /**
   * <p>
   * switch to iframe
   *
   * @return true or false.
   */
  public boolean switchToIFrame() {
    waitForPageLoaded();
    waitForSecs(3);
    this.driverCustom.switchToFrame(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH);
    return true;
  }

  /**
   * <p>
   * Choose a Configuration from 'Choose Configuration' wizard.
   *
   * @param configDropDownValue drop down value
   * @return true or false.
   */
  public boolean chooseConfiguration(final String configDropDownValue) {
    this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH, Duration.ofSeconds(5));
    WebElement drdbtn = null;
    try {
      // 7.0
      drdbtn = this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_7_XPATH);
    }
    catch (Exception ex) {
      // 6.0
      drdbtn = this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_6_XPATH);
    }
    drdbtn.click();
    LOGGER.LOG.info("Clicked on 'Choose a configuration' option.");
    this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHCONFIGURATIONTEXTBOX_XPATH)
        .sendKeys(configDropDownValue + Keys.ENTER);
    WebElement dialogTitle = null;
    try {
      dialogTitle = this.driverCustom.getWebElement("//p[@id='choose-config-title']");
    }
    catch (Exception e) {
      // not found element
    }
    if (dialogTitle == null) {
      WebElement optConfiguration = this.driverCustom.getWebElement(
          "//li[@data-text='" + configDropDownValue + "']//input[not (contains(@style,'display: none;'))]");
      optConfiguration.click();
      waitForSecs(2);
    }
    else {
      this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSECONFIGURATION_XPATH, configDropDownValue,
          SelectTypeEnum.SELECT_BY_VALUE);
      // Click on Accept button
      Button accept = this.engine.findElement(Criteria.isButton().withText("Accept")).getFirstElement();
      accept.click();
      LOGGER.LOG.info("Clicked on 'Accept' button.");
    }
    // Click on Run
    waitForSecs(1);
    Button btnRun = this.engine.findElement(Criteria.isButton().withText("Run")).getFirstElement();
    btnRun.click();
    LOGGER.LOG.info("Clicked on 'Run' button.");
    waitForSecs(2);
    if (!this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CHOOSE_COMPONENT_RESULT_XPATH,
        Duration.ofSeconds(this.timeInSecs.getSeconds() * 3))) {
      this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CHOOSE_COMPONENT_RESULT_XPATH,
          Duration.ofSeconds(this.timeInSecs.getSeconds() * 3));
    }
    this.driverCustom.switchToDefaultContent();
    return true;
  }

  /**
   * Open a module. Select a view. verify all column headers from table
   *
   * @param columValues list value of column.
   * @return true or false.
   */
  public boolean checkArtifacetRelationshipsAutoCreated(final Map<String, String> columValues) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH,
        Duration.ofSeconds(50))) {
      this.driverCustom.switchToFrame(JRSConstants.JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH);
    }
    WebElement table =
        this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSCREATEREPORTPAGE_RUNREPORT_TABLE_XPATH, null);
    List<WebElement> totalRowCount = table.findElements(By.tagName("tr"));
    Reporter.logInfo(NO_OF_ROWS_IN_THE_WEB_TABLE_MESSAGE + totalRowCount.size());
    if (totalRowCount.size() == 0) {
      LOGGER.LOG.info("No results found. Please make sure you have access to the project area(s)");
      return false;
    }
    LOGGER.LOG.info("List of item auto created.");
    List<WebElement> listheader =
        table.findElements(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_RUNREPORT_TABLE_HEADER_XPATH));
    for (WebElement element : totalRowCount) {
      int j1 = 1;
      for (WebElement header : listheader) {
        String headerAtribute = header.getAttribute("data-resizable-column-id").toString();
        WebElement itemvalueonRow =
            table.findElement(By.xpath("descendant::td[@data-varname='" + headerAtribute + "']"));
        if (!itemvalueonRow.getText().equals(columValues.get("columnValue" + j1))) {
          LOGGER.LOG.info("Relationship is created wrongly");
          return false;

        }
        LOGGER.LOG.info(header.getText().toString() + ":" + columValues.get("columnValue" + j1) + ",");
        j1++;
      }
    }
    LOGGER.LOG.info("Relationship is created and  Column added successfully");
    return true;
  }


  /**
   * select folder and check reprot is visible in specified folder and click on edit view. verify all report
   *
   * @param report name of report.
   * @param groupValue group value.
   * @param folderName name of folder.
   */
  public void openReportByGroup(final String report, final String groupValue, final String folderName) {
    waitForSecs(25);
    this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_MYREPORT_LINK_XPATH, Duration.ofSeconds(10));
    this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWN_XPATH,
        Duration.ofSeconds(10));
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECT_XPATH,
        Duration.ofSeconds(30))) {
      // ver 6061ifix018
      Select dropdown =
          new Select(this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECT_XPATH));
      List<WebElement> listOptions = dropdown.getOptions();
      for (WebElement opt1 : listOptions) {
        if (opt1.getText().contains(groupValue)) {
          opt1.click();
          break;
        }
      }
    }
    else {
      // ver ELM702ifix008
      this.driverCustom.click(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWN_XPATH);
      List<WebElement> listOptions =
          this.driverCustom.getWebElements(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECTOPTION_XPATH);
      for (WebElement opt1 : listOptions) {
        if (opt1.getText().contains(groupValue)) {
          opt1.click();
          break;
        }
      }
    }
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYFOLDEREXPAND_XPATH, folderName)
        .click();
    waitForSecs(15);
    Text txtSearch;
    try {
      // ver 6061ifix018
      txtSearch = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter")).getFirstElement();
    }
    catch (Exception e) {
      // ver ELM702ifix008 02T1
      txtSearch = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Search")).getFirstElement();
    }
    txtSearch.setText(report + Keys.ENTER);
    waitForSecs(10);
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORTNAME_XPATH, Duration.ofSeconds(60),
        report)) {
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORTNAME_XPATH, report).click();
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORT_EDIT_XPATH, report).click();
    }

  }

  /**
   * add owner to report
   *
   * @param ownerName ,attributeValue.
   * @return string of owner name.
   */
  public String addOwnerName(final String ownerName) {
    // 7.0.3 - changes from 'Add Owner' to 'Select users' dialog.
       try {
         if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH,
             Duration.ofSeconds(30))) {
           waitForSecs(5);
           this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH);
           // Search and select user
           Dialog dlgSelectUser = this.engine
               .findElementWithDuration(Criteria.isDialog().withTitle("Select users"), Duration.ofSeconds(30)).getFirstElement();
           // Search and select approver
           Text txtSearchApprover = this.engine.findElement(
               Criteria.isTextField().withPlaceHolder("Search for users in this project area").inContainer(dlgSelectUser))
               .getFirstElement();
           txtSearchApprover.setText(ownerName);
           LOGGER.LOG.info(ownerName + " is typed in Search textbox successfully.");
           waitForSecs(Duration.ofSeconds(3));
           WebElement drdMatchingApprover =
               this.driverCustom.getWebElement("//div[@aria-label='" + ownerName + "']/following-sibling::button");
           drdMatchingApprover.click();
           LOGGER.LOG.info(ownerName + " username is selected successfully.");
           Button btnOK = this.engine
               .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgSelectUser), this.timeInSecs)
               .getFirstElement();
           btnOK.click();
           LOGGER.LOG.info("OK button is clicked successfully.");
         }
       }
       // 7.0.2 - 'Add Owner' will call out 'Choose report owners' dialog
       catch (Exception e) {
         if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH,
             Duration.ofSeconds(30))) {
           // Sendkey Esc to close dialog open by Try
           new Actions(this.driverCustom.getWebDriver()).sendKeys(Keys.ESCAPE).build().perform();
           waitForSecs(5);
           this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH);
           if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERSEARCHTEXT_XPATH,
               Duration.ofSeconds(30))) {
             WebElement txtSearch =
                 this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERSEARCHTEXT_XPATH);
             txtSearch.click();
             txtSearch.sendKeys(ownerName + Keys.ENTER);
             waitForSecs(5);
           }
           if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERSEARCHTEXT_XPATH,
               Duration.ofSeconds(30))) {
             this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERSEARCHTEXT_XPATH);
             waitForElement(5);
           }
           if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERNAME_XPATH,
               Duration.ofSeconds(50))) {
             Select dropdown =
                 new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNERNAME_XPATH));
             List<WebElement> listOptions = dropdown.getOptions();
             for (WebElement opt1 : listOptions) {
               if (opt1.getText().contains(ownerName)) {
                 opt1.click();
                 break;
               }
             }
           }
           waitForSecs(10);
           if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_ADDREPORTMANAGER_BUTTON_XPATH,
               Duration.ofSeconds(30))) {
             this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_ADDREPORTMANAGER_BUTTON_XPATH);
           }
           clickOKButton();
         }
         else {
           throw new WebAutomationException(ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX);
         }
       }
       return ownerName;
     }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page. This method check for
   * presence of Build report preview tab in 'JRSBuildNewReport'page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDREPORT_PREVIEWTAB_DIV_XPATH);

  }

  /**
   * @param showAs show as value.
   */
  public void selectShowAsDropDownCustomerExpressionTab(final String showAs) {
    Dropdown showAsDrp = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Show as:"), this.timeInSecs).getFirstElement();

    if (!showAsDrp.getDefaultValue().equals(showAs)) {
      showAsDrp.selectOptionWithText(showAs);
    }
  }

  /**
   * @param dropDownValue value of drop down.
   */
  public void setCustomExpressionOfValue(final String dropDownValue) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, this.timeInSecs);
    this.driverCustom
        .getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_ATTRIBUTEOF_DROPDOWN_CUSTOMEXPRESSION_XPATH).click();
    waitForSecs(3);
    Select sel = new Select(
        this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ATTRIBUTEOF_DROPDOWN_CUSTOMEXPRESSION_XPATH));
    waitForSecs(3);
    sel.selectByVisibleText(dropDownValue);
    try {
      sel.selectByValue(dropDownValue);
    }
    catch (Exception e) {}
  }

  /**
   * @param attributeOf attribute of.
   * @param attribute attribute value.
   * @param showAs show as value.
   * @param customExpression custom expression.
   * @return true or false.
   */
  public boolean addCustomExpressionAttribute(final String attributeOf, final String attribute, final String showAs,
      final String customExpression) {
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_CUSTOMEXPRESSION_BUTTON_XPATH).click();
    setCustomExpressionOfValue(attributeOf);
    Text searchTxt = this.engine
        .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search"), this.timeInSecs).getFirstElement();
    searchTxt.setText(attribute);
    clickOnRadiobutton(attribute);
    Button addAttribute =
        this.engine.findElementWithDuration(Criteria.isButton().withText(">"), this.timeInSecs).getFirstElement();
    addAttribute.click();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_TEXTAREA_CUSTOMEXPRESSION_XPATH).clear();
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_TEXTAREA_CUSTOMEXPRESSION_XPATH)
        .sendKeys(customExpression);
    selectShowAsDropDownCustomerExpressionTab(showAs);
    waitForSecs(2);
    this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_VALIDATE_BUTTON_CUSTOMEXPRESSION_XPATH);
    waitForSecs(3);
    this.driverCustom.isElementClickable(JRSConstants.JRSCREATEREPORTPAGE_ADD_BUTTON_CUSTOMEXPRESSION_XPATH,
        Duration.ofSeconds(10));
    waitForSecs(10);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_ADD_BUTTON_CUSTOMEXPRESSION_XPATH)
        .click();
    clickOKAddCalculatedColumn();
    waitForSecs(10);
    List<String> columnValues = fetchColumnValues();
    checkIfAttributeAvailable(attribute, columnValues, "Failed: Test Category Column not found");
    return true;
  }

  /**
   * @param columnName name of column.
   * @return true or false.
   */
  public boolean verifyColumnAddedInThePreviewTableOfFormatResultTab(final String columnName) {
//    waitForSecs(50);
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(300));
    wait.pollingEvery(Duration.ofSeconds(5)).ignoring(TimeoutException.class).until(ExpectedConditions
        .visibilityOfAllElementsLocatedBy(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWREPORT_COLUMN_HEADER_XPATH)));
    List<WebElement> listHeaderElm =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_PREVIEWREPORT_COLUMN_HEADER_XPATH);
    for (WebElement header : listHeaderElm) {
      if (columnName.equals(header.getText())) {
        System.out.println("Column '" + columnName + "' is displayed as expected!");
        return true;
      }
    }
    System.out.println("Added column is not displayed as expected!");
    return false;
  }

  /**
   * @param params list artifact value.
   * @return true or false.
   */
  public boolean verifyArtifactDisplayedInReport(final Map<String, String> params) {
    waitForSecs(10);
    List<WebElement> listHeaderElm =
        this.driverCustom.getVisibleWebElements(JRSConstants.JRSRUNREPORTPAGE_REPORT_COLUMN_HEADER_XPATH);

    String diplayed_artifact_xpath =
        "//div[@id='table-viewport']//tbody[@id='_resultBody']/tr[td[1][.='" + params.get("value1") + "']";
    for (int i = 2; i <= listHeaderElm.size(); i++) {
      diplayed_artifact_xpath += " and td[" + i + "][.='" + params.get("value" + i) + "']";
    }
    diplayed_artifact_xpath += "]";
    this.driverCustom.isElementVisible(diplayed_artifact_xpath, this.timeInSecs);
    return true;
  }

  /**
   * @param sortedAttribute attribute to sort.
   * @param sortOrderValue order value of sort.
   * @return true or false.
   */
  public boolean verifySortOrder(final String sortedAttribute, final String sortOrderValue) {
    return this.driverCustom
        .getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_SORT_ORDER_VALUE_XPATH, sortedAttribute)
        .getAttribute("value").trim().equals(sortOrderValue);
  }

  /**
   * @param artifact list of value includes key and value into map.
   */
  public void chooseArtifact(final Map<String, String> artifact) {
    waitForPageLoaded();
    waitForSecs(3);
    if ((artifact.get("childArtifact") == "") || (artifact.get("childArtifact") == null)) {
      this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_ARTIFACT_PARENT_XPATH,
          artifact.get("parentArtifact")).click();
    }
    else {
      this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_ARTIFACT_EXPAND_ICON_XPATH,
          artifact.get("parentArtifact")).click();
      waitForSecs(5);
      this.driverCustom.getPresenceOfWebElement(String.format(JRSConstants.JRSCREATEREPORTPAGE_ARTIFACT_CHILD_XPATH,
          artifact.get("parentArtifact"), artifact.get("childArtifact"))).click();
    }
    waitForSecs(3);
  }

  /**
   * @param relationshipoption relation-ship option.
   * @return true or false.
   */
  public boolean selectRequiredOprtionFromAddRelationship(final String relationshipoption) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_ADDEDRELATIONSHIPAS_REQUIRED_DROPDOWN_XPATH,
        Duration.ofSeconds(50))) {
      Select dropdown = new Select(this.driverCustom
          .getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ADDEDRELATIONSHIPAS_REQUIRED_DROPDOWN_XPATH));
      List<WebElement> listOptions = dropdown.getOptions();
      for (WebElement opt1 : listOptions) {
        if (opt1.getText().contains(relationshipoption)) {
          opt1.click();
          break;
        }
      }
    }
    return true;
  }

  /**
   * <p>
   * After setting a condition or multiple condtions using {@link JRSBuildNewReportPage#setCondition(String, String)},
   * <br>
   * To verify all conditions are successfully added to the report and visible under the "Conditions" Section on Right
   * side.
   *
   * @author YNT2HC
   * @param additionalParams is CONDITION_1 as a key and CONDITION_1_VALUE as a value <br>
   *          For example: {condition 1: "Type a Task"}
   * @return true if all conditions shown under the "Conditions" Section
   */
  public boolean verifySetCondition(final Map<String, String> additionalParams) {
    // List of conditions
    Collection<String> conditions = additionalParams.values();
    ArrayList<String> listOfConditions = new ArrayList<>(conditions);
    LOGGER.LOG.info("Expected value" + listOfConditions);
    // Compare actual and expected list of conditions
    String getString = this.driverCustom.getText(JRSConstants.JRSCREATEREPORTPAGE_ALL_SELECTED_CONDITIONS_XPATH)
        .replaceAll("\\s+", " ");
    LOGGER.LOG.info("Actual value" + getString);
    for (String name : listOfConditions) {

      if (getString.contains(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * <p>
   * Group the two conditions: State is any of approved, Draft, Priority is high, medium Selected conditions should be
   * grouped successfully and "Any Can match" should be available correctly under the "Conditions" section <br>
   * After setting a condition or multiple condtions using {@link JRSBuildNewReportPage#setCondition(String, String)},
   *
   * @author YNT2HC
   * @param params is a map with a key and a value from configuration file <br>
   *          [GROUP_CONDITION: GROUP_CONDITION_VALUE] [GROUP_TYPE: GROUP_TYPE_VALUE]
   */
  public void setGroupConditions(final Map<String, String> params) {

    // Select condition checkboxes
    for (String key : params.keySet()) {
      if (key.startsWith("GROUP_CONDITION")) {
        WebElement cbxCondition = this.driverCustom
            .getWebElement(JRSConstants.JRSCREATEREPORTPAGE_CONDITION_ROW_CHECKBOX_XPATH, params.get(key));
        cbxCondition.click();
        waitForSecs(5);
      }
    }

    // Click on Group button
    clickOnButtons("Group");

    // Select group type
    try {
      Select dropdownGroupType =
          new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_SELECT_GROUPTYPE_XPATH));
      dropdownGroupType.selectByVisibleText(params.get("GROUP_TYPE"));
    }
    catch (Exception e) {
      Select dropdownInnerGroupType =
          new Select(this.driverCustom.getWebElement("//div[@class='filter-group' and @data-id='group2']//select"));
      dropdownInnerGroupType.selectByVisibleText(params.get("INNER_GROUP_TYPE"));
    }

  }

  /**
   * <p>
   * After opening 'Format Result' tab {@link JRSBuildNewReportPage#clickOnReportTab(String)}, click on 'Custom
   * Expression' button to a custom expression column of any attribute <br>
   *
   * @author YNT2HC
   * @param params is a map with a key and a value from configuration file <br>
   *          [ATTRIBUTE_OF: ATTRIBUTE_VALUE] [CUSTOM_EXPRESSION: CUSTOM_EXPRESSION_VALUE] [SHOW_AS: SHOW_AS_VALUE]
   * @return String is the return message after clicking 'validate' button
   */
  public String addACustomExpressionColumn(final Map<String, String> params) {

    // Select attribute of
    Select sel = new Select(
        this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_ATTRIBUTEOF_DROPDOWN_CUSTOMEXPRESSION_XPATH));
    sel.selectByVisibleText(params.get("ATTRIBUTE_OF"));

    Text searchTxt = this.engine
        .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search"), this.timeInSecs).getFirstElement();
    for (String value : params.get("ATTRIBUTE_VALUE").split("/")) {

      // Search an attribute
      searchTxt.setText(value);
      clickOnRadiobutton(value);
      waitForSecs(2);

      // Click Add Attribute button
      clickOnButtons(">");
    }

    // Input custom expression
    Text txtCustomExpression = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Custom expression").isMultiLine(), this.timeInSecs)
        .getFirstElement();
    txtCustomExpression.setText(params.get("CUSTOM_EXPRESSION"));
    LOGGER.LOG.info("custom expression: " + params.get("CUSTOM_EXPRESSION") + " is added successfully.");
    // Select aggregate expression checkbox
    if (Boolean.valueOf(params.get("IS_SELECT_AGGREGATE_EXPRESSION"))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_AGGREGATE_CHECKBOX_XPATH);
    }

    // Select show as dropdown
    selectShowAsDropDownCustomerExpressionTab(params.get("SHOW_AS"));

    String getValidatingMsg = "";
    // VERSION 7.0
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_HEADER_CUSTOM_EXPRESSION_PTAG_XPATH,
        Duration.ofSeconds(20))) {
      // Click Validate button
      clickOnButtons("Validate");

      getValidatingMsg = this.driverCustom.getText(JRSConstants.JRSCREATEREPORTPAGE_DIV_VALIDATING_MESSAGE_XPATH);

      // Click Add or Close button
      if (getValidatingMsg.contains(JRSConstants.SUCCESS_VALIDATING_MESSAGE)) {
        clickOnButtons("Add");
        LOGGER.LOG.info("Validation message is : " + JRSConstants.SUCCESS_VALIDATING_MESSAGE);
      }
      else {
        clickOnButtons("Close");
      }
    }
    // VERSION 6.0
    else {
      clickOnButtons("VALIDATE");

      getValidatingMsg = this.driverCustom.getText(JRSConstants.JRSCREATEREPORTPAGE_DIV_VALIDATING_MESSAGE_XPATH);

      // Click Add or Close button
      if (getValidatingMsg.contains(JRSConstants.SUCCESS_VALIDATING_MESSAGE)) {
        clickOnButtons("ADD");
        LOGGER.LOG.info("Validation message is : " + JRSConstants.SUCCESS_VALIDATING_MESSAGE);
      }
      else {
        clickOnButtons("CLOSE");
      }
    }

    return getValidatingMsg;
  }

  /**
   * Method to click on Radio Button.
   */
  public void clickOnRadioButton() {
    this.driverCustom.isElementVisible("//input[@id='seriesSelectByValue']", Duration.ofSeconds(10));
    this.driverCustom.click("//input[@id='seriesSelectByValue']");

  }

  /**
   * <p>
   * There are two option with 'Append to' or 'Merge with' to configure multiple parths for other source artifact <br>
   * After select a source artifact sucessfully in the dialog
   * {@link JRSBuildNewReportPage#selectAnArtifactInDialog(String, String)},
   *
   * @author YNT2HC
   * @param optionType with 'Append to' or 'Merge with' options
   * @param sourceArtifact is the name of source artifact to start a new path
   */
  public void selectMultilePathOption(final String optionType, final String sourceArtifact) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_MULTIPLE_PATH_SELECT_XPATH, this.timeInSecs,
        sourceArtifact)) {
      Select multiPathDropdown = new Select(this.driverCustom
          .getWebElements(JRSConstants.JRSCREATEREPORTPAGE_MULTIPLE_PATH_SELECT_XPATH, sourceArtifact).get(0));
      multiPathDropdown.selectByVisibleText(optionType);
    }
  }

  /**
   * <p>
   * After clicking on 'ADD AN ARTIFACT' button, 'Select an artifact type' is shown. Select a new artifact as new source
   * artifact for new path <br>
   * After enabling checkbox of multple paths {@link AbstractJazzWebPage#enableACheckBox(String, String)},
   *
   * @author YNT2HC
   * @param parentArtifact is the parent artifact name like work item, requirement, collections
   * @param childArtifact is the child artifact name inside the parent artifacts
   */
  public void selectAnArtifactInDialog(final String parentArtifact, final String childArtifact) {
    // Select an artifact
    chooseAnArtifact(parentArtifact, childArtifact);

    // Click on OK button
    clickOnButtons("OK");
  }

  /**
   * <p>
   * All selected conditions will be presented under the "Conditions" section By default, "All must match (AND)" is set
   * as type group of conditions. We can change it by <Any can match (OR):> <Not all match (AND NOT):>, <None of them
   * match (OR NOT):> <br>
   * After setting a condition or multiple condtions using {@link JRSBuildNewReportPage#setCondition(String, String)},
   *
   * @author YNT2HC
   * @param outerGroupType is the outer group type of all conditions selected <br>
   */
  public void selectOuterGroupType(final String outerGroupType) {

    // Select group type
    Select dropdown =
        new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_SELECT_OUTER_GROUPTYPE_XPATH));
    dropdown.selectByVisibleText(outerGroupType);
  }

  /**
   * <p>
   * In 'Set conditions' of building report. Open ADD CONDITION window by clicking on ADD CONDITION button.</br>
   *
   * @author KYY1HC
   */
  public void chooseSelectAllVisibleItems() {
    Checkbox chxSelectAllVisibleItems = null;
    try {
      chxSelectAllVisibleItems =
          this.engine.findFirstElementWithDuration(Criteria.isCheckbox().withLabel("Select all"), this.timeInSecs);
    }
    catch (Exception e) {
      chxSelectAllVisibleItems = this.engine
          .findFirstElementWithDuration(Criteria.isCheckbox().withLabel("Select all visible items"), this.timeInSecs);
    }
    chxSelectAllVisibleItems.click();
    waitForSecs(3);
  }

  /**
   * <p>
   * After setting a condition or multiple condtions using
   * {@link JRSBuildNewReportPage#setCondition(String, String)},</br>
   * To verify all conditions are successfully added to the report and visible into the "Filters" Section on the left
   * corner side.</br>
   *
   * @author KYY1HC
   * @param parameterValue value display in all required parameters or filters.
   * @return true if it contains value need to verify
   */
  public boolean verifyParameterValueInFilters(final String parameterValue) {
    boolean result = false;
    switchToIFrame();
    List<WebElement> lstElements =
        this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_ALL_PARAMS_VALUES_IN_FILTERS_XPATH);
    for (WebElement webElement : lstElements) {
      LOGGER.LOG.info(webElement.getText() + " - ");
      if (webElement.getText().trim().contains(parameterValue)) {
        result = true;
        break;
      }
    }
    return result;
  }

  /**
   * <p>
   * Click on 'Run Report' tab using {@link JRSBuildNewReportPage#clickOnReportTab(String)}. <br>
   * Filters window will display if 'Lifecycle Query Engine using Configurations' data source is selected in 'Edit Data
   * Source' section. <br>
   * If 'Choose a configuration' button available Click on 'Choose a configuration' button,'Choose a configuration' pop
   * up displayed. <br>
   * Choose a required configuration.
   *
   * @param configDropDownValue configuration values displayed after clicking choose a configuration label inside
   *          'Choose a configuration' dialog.
   * @return true if a configuration value selected.
   */
  public boolean selectAConfigurationAndValidate(final String configDropDownValue) {
    this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH, Duration.ofSeconds(10));
    waitForSecs(3);
    WebElement drdbtn = null;
    try {
      // 7.0
      drdbtn = this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_7_XPATH);
    }
    catch (Exception ex) {
      // 6.0
      drdbtn = this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCONFIGURATION_6_XPATH);
    }
    drdbtn.click();
    waitForSecs(3);
    this.driverCustom
        .getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHCONFIGURATIONTEXTBOX_LABELEXPANDED_XPATH);
    this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_SEARCHCONFIGURATIONTEXTBOX_XPATH)
        .sendKeys(configDropDownValue + Keys.ENTER);

    WebElement dialogTitle = null;
    try {
      dialogTitle = this.driverCustom.getWebElement("//p[@id='choose-config-title']");
    }
    catch (Exception e) {
      // not found element
    }
    if (dialogTitle == null) {
      WebElement optConfiguration = this.driverCustom.getPresenceOfWebElement(
          "//li[@data-text='" + configDropDownValue + "']//input[not (contains(@style,'display: none;'))]");
      optConfiguration.click();
      waitForSecs(2);
    }
    else {
      waitForSecs(20);
      this.driverCustom.select(JRSConstants.JRSBUILDNEWREPORTPAGE_CHOOSECONFIGURATION_XPATH, configDropDownValue,
          SelectTypeEnum.SELECT_BY_VALUE);
    }
    return true;
  }

  /**
   * <p>
   * Click on 'Run Report' tab using {@link JRSBuildNewReportPage#clickOnReportTab(String)}. <br>
   * Filters window will display if 'Lifecycle Query Engine using Configurations' data source is selected in 'Edit Data
   * Source' section. <br>
   * If 'Choose a configuration' button available Click on 'Choose a configuration' button,'Choose a configuration' pop
   * up is displayed. <br>
   * Choose required domain, component,configuration inside 'Choose a configuration' dialog and click on 'Accept' button
   * if available. <br>
   * Click on 'Run' button present next to the Filters dialog.
   *
   * @return true if Run button is clicked.
   */
  public boolean clickOnRunButton() {
    try {
      // Click on Accept button
      Button accept = this.engine.findElement(Criteria.isButton().withText("Accept")).getFirstElement();
      accept.click();
    }
    catch (Exception e) {
      // not found element
    }

    // Click on Run
    waitForSecs(1);
    Button btnRun = this.engine.findElement(Criteria.isButton().withText("Run")).getFirstElement();
    btnRun.click();
    waitForSecs(360);
    if (!this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CHOOSE_COMPONENT_RESULT_XPATH,
        Duration.ofSeconds((this.timeInSecs.getSeconds() * 3)))) {
      this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CHOOSE_COMPONENT_RESULT_XPATH,
          Duration.ofSeconds((this.timeInSecs.getSeconds() * 3)));
    }
    this.driverCustom.switchToDefaultContent();
    return true;
  }

  /**
   * <p>
   * Open created JRS report and click on Edit option <br>
   * Search JRS Report using type filter text filed <br>
   * Select searched JRS report and right click on the report and click edit option
   *
   * @param report name of report.
   * @return true if edit option is clicked.
   */
  public boolean editJrsReport(final String report) {
    this.driverCustom.switchToDefaultContent();
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_LOADINGMESSAGE_DIV_XPATH, this.timeInSecs);
    Text txtSearch = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Search")).getFirstElement();
    txtSearch.setText(report + Keys.ENTER);
    waitForSecs(50);
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORTNAME_XPATH, this.timeInSecs,
        report)) {
      Dropdown drpDwn = this.engine.findElement(Criteria.isDropdown().withToolTip("Choose an action from the list"))
          .getFirstElement();
      drpDwn.click();
      this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_REPORT_EDIT_XPATH, report).click();
      waitForPageLoaded();
    }
    // Waiting time after Run Report -> before click on tab Format Results
    waitForSecs(10);
    return true;
  }

  /**
   * <p>
   * Click on 'Add Condition' button under 'Set Conditions' section, 'Add Condition' dialog is displayed. <br>
   * Search the condition 'Creator' using {@link JRSBuildNewReportPage#setCondition(String, String)} method. <br>
   * Click on the 'Creator' condition using {@link JRSBuildNewReportPage#clickOnRadiobutton(String)} method. <br>
   * Click on the 'Add more users' icon present next to the 'Select User' option. <br>
   * 'Select Users' dialog is displayed. <br>
   * Search the user in the text field and click on 'Search' button. <br>
   * Select the searched user. <br>
   * Click on 'Add and Close' button.
   *
   * @param user to be added as a Creator.
   */
  public void setCreator(final String user) {
    if (this.driverCustom.isElementVisible("//div[@title='Add more users']", Duration.ofSeconds(30))) {
      this.driverCustom.click("//div[@title='Add more users']");
      Dialog dlgSelectUser =
          this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Select Users"), this.timeInSecs);
      WebElement creatorValue = this.driverCustom.getWebElement("//input[@class='form-control search-text']");
      creatorValue.sendKeys(user);
      waitForSecs(5);
      LOGGER.LOG.info("Searched user -  " + user);
      Button btnSearch = this.engine.findFirstElementWithDuration(
          Criteria.isButton().withText("Search").inContainer(dlgSelectUser), this.timeInSecs);
      btnSearch.click();
      LOGGER.LOG.info("Click on 'Search' button.");
      waitForSecs(2);
      if (this.driverCustom.isElementVisible("//select[@class='search-values']/option", this.timeInSecs)) {
        this.driverCustom.getWebElement("//select[@class='search-values']/option").click();
        LOGGER.LOG.info(user.trim() + " selected user from Matching results.");
        waitForSecs(5);
        Button btnAddandClose = this.engine.findFirstElementWithDuration(
            Criteria.isButton().withText("Add and Close").inContainer(dlgSelectUser), this.timeInSecs);
        btnAddandClose.click();
        LOGGER.LOG.info("Clicked on Add and Close button.");
      }
    }
  }

  /**
   * @param params contains parameters related such as orientation, papersize, fontsize and file format to be genarated.
   */
  public void generateAWordDocumentFromYourReport(final Map<String, String> params) {

    Checkbox check = this.engine
        .findFirstElementWithDuration(Criteria.isCheckbox().withLabel(params.get("File format")), this.timeInSecs);
    check.click();

    Select orientation = new Select(this.driverCustom.getWebElement("//select[@name='Orientation']"));
    orientation.selectByVisibleText(params.get("Orientation"));

    Select paperSize = new Select(this.driverCustom.getWebElement("//select[@name='PaperSize']"));
    paperSize.selectByVisibleText(params.get("Paper size"));

    Select fontSize = new Select(this.driverCustom.getWebElement("//select[@name='FontSize']"));
    fontSize.selectByVisibleText(params.get("Font size"));

    WebElement generate = this.driverCustom.getWebElement("//input[@value='Generate']");
    generate.click();

  }
  
  /**
   * On 7.0.3 new UI changes introduced 2 dialogs asking for a tour to new UI
   * Purpose to close off 2 popup dialogs
   * And switch new UI back to Classic UI
   * @author YUU3HC
   */
  public void switchToClassic() {
    if (this.driverCustom.isElementPresent(
        "//div[contains(@class, 'wm-outer-div wm-shoutout')]//*[contains(text(),'Maybe later')]",
        Duration.ofSeconds(5))) {
      this.driverCustom.click("//div[text()='x']");
      waitForPageLoaded();
    }
    driverCustom.getPresenceOfWebElement("//button[@id='user']").click();
    driverCustom.getPresenceOfWebElement("//*[contains(text(),'Go to classic experience')]").click();
    driverCustom.waitForSecs(Duration.ofSeconds(5));
    
  }

  /**
   * This method to click on 'Show My Choices' arrow on right sidebar.
   * Purpose to help verification method to verify selected options on Report Builder.
   * @author YUU3HC
   */
  public void showMyChoices() {
    //10-T4 summary part - right sidebar need to click to display on classic mode
    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_SHOWMYCHOICES_XPATH,
        Duration.ofSeconds(5))) {
      this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSALLREPORTPAGE_SHOWMYCHOICES_XPATH).click();
    }
    
  }

  /**
   * This method to click on Create button on JRS page
   * @param option - is value what you want to select from Create dropdown
   * @author NCY3HC
   */
  public void selectCreateButton(final String option) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_CREATE_BUTTON_XPATH, timeInSecs)) {
      this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_CREATE_BUTTON_XPATH).click();
      
      //wait for dropdown displayed
     WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
      wait.pollingEvery(Duration.ofSeconds(5)).ignoring(TimeoutException.class).until(ExpectedConditions
          .visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='createBtn_menu_menu']")));
      //Select create Report or Folder
        this.driverCustom.getWebElement("//li[@aria-label='DYNAMIC_VAlUE']", option).click();
      }
    }
  
  
  /**
   * add owner to report
   *
   * @param ownerName ,attributeValue.
   * @return string of owner name.
   */
  public String addOwnerName1(final String ownerName) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH,
        Duration.ofSeconds(30))) {
      this.driverCustom.click(JRSConstants.JRSCREATEREPORTPAGE_BUTTON_ADDOWNER_XPATH);
      //wait for 'Select user' dialog displayed
     WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(10));
      wait.pollingEvery(Duration.ofSeconds(5)).ignoring(TimeoutException.class).until(ExpectedConditions
          .visibilityOfAllElementsLocatedBy(By.xpath(JRSConstants.JRSALLREPORTPAGE_SELECT_USER_DIALOG_XPATH)));
      }
    WebElement searchBoxAddUser = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_SEARCHBOX_ADDUSER_DIALOG_XPATH);
    searchBoxAddUser.sendKeys(ownerName);
    if(this.driverCustom.isElementVisible("//button[@aria-label='Add DYNAMIC_VAlUE']", timeInSecs, ownerName)) {
      this.driverCustom.getWebElement("//button[@aria-label='Add DYNAMIC_VAlUE']",ownerName).click();
    }
    waitForSecs(3);
      clickOKButton();
    return ownerName;
  }

  /**
   * Select view as My Reports or All Reports in report list page
   *@param optionView - value from list box
   *@author NCY3HC
   * 
   */ 
 public void selectReportView(final String optionView) {
   if(this.driverCustom.isElementVisible("//button[@aria-haspopup='listbox']", timeInSecs)) {
     this.driverCustom.getWebElement("//button[@aria-haspopup='listbox']").click();
     //Select view as My Reports or All Reports
     WebElement view = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_SELECT_VIEW_AS_XPATH, optionView);
     view.click();
   }
 }
   
   
   /**
    * Searching for a specific report in the list and then click on Edit Report button
    *@author NCY3HC
    *@param folder , reportName, tags, visualization, owners - information was inputted when creating report
    *@return the result of verifying the report info. 
    */
  public Boolean verifyReportInfoInEditor(final String folder, final String reportName, final String tags,
      final String visualization, final String owners) {
    waitForSecs(10);
    WebElement searchBox = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_TOOLBAR_SEARCHFOLDER_XPATH);
    if (!folder.equalsIgnoreCase("null")) {
      searchBox.sendKeys(folder + Key.ENTER);
      // Open folder
      waitForSecs(3);
      this.driverCustom.getWebElement("//a[text()='DYNAMIC_VAlUE']", folder).click();

    }
    // Click Filter button and search for report name
    WebElement btnfilter= this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_FILTER_BUTTON_XPATH);
    btnfilter.click();
    waitForSecs(5);
    WebElement filterName = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_TEXTBOX_NAME_FILTERBAR_XPATH);
    filterName.sendKeys(reportName + Key.ENTER);
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
    wait.pollingEvery(Duration.ofSeconds(5)).ignoring(TimeoutException.class).until(
        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//button[contains(@data-testid,'edit')])[1]")));
   this.driverCustom.getWebElement("(//td[@data-column='resource_type_label'])[1]").click();
   //close filter bar
   btnfilter.click();
   //Open report in editor
   WebElement btnEdit= this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_EDITBUTTON_IN_EDITOR_XPATH);
    btnEdit.click();
    
    if(this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']",timeInSecs)){
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    //wait for pageload successfully
    wait.pollingEvery(Duration.ofSeconds(60)).ignoring(TimeoutException.class)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()='Details']")));
//    Open Details tag if it's closed
//    WebElement detailsTag = this.driverCustom.getWebElement("//a[text()='Details']");
//    String b= detailsTag.getAttribute("aria-expanded");
//    if (detailsTag.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
//      detailsTag.click();
//    }

    // Get report name in Editor
    String actualReportName = this.driverCustom
        .getWebElement(JRSConstants.JRSALLREPORTPAGE_REPORTNAME_IN_EDITOR_XPATH)
        .getAttribute("value");
    // Get actual Tags
    String actualTags = this.driverCustom
        .getWebElement(JRSConstants.JRSALLREPORTPAGE_TAGS_IN_EDITOR_XPATH).getText();
    // Get actual visualization
    String actualViz = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_VISUALIZATION_IN_EDITOR_XPATH).getAttribute("value");
    // Get actual owners
    List<WebElement> ownerslist = this.driverCustom.getWebElements(JRSConstants.JRSALLREPORTPAGE_OWNERS_LIST_IN_EDITOR_XPATH);
    String[] actualOwners = new String[ownerslist.size()];
    for (int i = 0; i < ownerslist.size(); i++) {
      actualOwners[i] = ownerslist.get(i).getText();
    }
    
    //Get the expected owners
    String[] expectedOwners = owners.split(",");
    for(int j=0; j < expectedOwners.length; j++) {
      expectedOwners[j]= expectedOwners[j].trim();
    }
    
    
    // Get folder
    String actualFolder = this.driverCustom.getWebElement("//span[@id='report-folder-display']").getText();
    // Compare actual report Name, tags, visualization, folder with the expectation
    String[] actualDetails = { actualReportName, actualTags, actualViz, actualFolder };
    String[] expectedDetails = { reportName, tags, visualization, "/"+folder };
    boolean result1 = Arrays.equals(actualDetails, expectedDetails);

    // Compare actual Owners list with the expectation
    boolean result2 = Arrays.equals(actualOwners, expectedOwners);
    Arrays.sort(expectedOwners);
    Arrays.sort(actualOwners);
    if (result1 && result2) {
      return true;
    }
    return false;
  }

  
}
