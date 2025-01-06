/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.container.TableFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.button.JazzButtonFinder;
import finder.dropdown.JazzDropdownFinder;

/**
 * Represents the JRS page to select and run a JRS report.
 */
public class JRSAllReportsPage extends JRSPage {

  /**
   * See {@link JRSPage#JRSPage(WebDriverCustom)}
   */
  public JRSAllReportsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new JazzDropdownFinder(), new RowCellFinder(), new TableFinder(),
        new CheckboxFinder());

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom.openURL(repositoryURL + "/reports#use");
  }

  /**
   * Open or Expand the input Tag in "ALL REPORTS" page or "MY REPORTS" page of JRS.
   *
   * @param tagName the tag to be opened
   */
  public void openTag(final String tagName) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_SELECTALLCHECKBOXES_CHECKBOXES_XPATH,
        Duration.ofSeconds(30))) {
      clickAndCheckIfFound(tagName, "Tag not found", JRSConstants.JRSALLREPORTPAGE_REPORTTAGNAME_REPORTTAG_XPATH, true);
    }
  }


  /**
   * Open the report from "ALL REPORTS" page or "MY REPORTS" page.
   *
   * @param reportName the name of the report that needs to be opened
   */
  public void openReport(final String reportName) {
    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_EXPANDICON_EXPAND_XPATH,
        Duration.ofSeconds(30))) {
      clickAndCheckIfFound(reportName, "Report not found", JRSConstants.JRSALLREPORTPAGE_REPORTNAME_REPORTNAME_XPATH,
          true);
    }
  }

  /**
   * Open the report in new tab - from "ALL REPORTS" page or "MY REPORTS" page.
   *
   * @param reportName the name of the report that needs to be opened
   */
  public void openReportInNewTab(final String reportName) {
    if(this.driverCustom.isElementInvisible(JRSConstants.JRSALLREPORTPAGE_LOADING_XPATH, timeInSecs))
    {    
      WebElement report = this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSALLREPORTPAGE_REPORTNAME_XPATH, reportName);
      this.driverCustom.scrollIntoCenterOfView(report);
      this.driverCustom.getActions().keyDown(Keys.CONTROL)
      .keyDown(Keys.SHIFT)
      .click(report).perform();
      this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    }
    else
      fail("Report not found");
  }
  
  
  /**
   * Switch to the tab in the browser.
   * @param number 
   */
  public void switchBrowserTab(Integer number) {
    this.driverCustom.switchToFrame(number);
  }
  
  /**
   * Switch to the next frame in the browser.
   */
  public void switchFrame() {
    this.driverCustom.switchToFrame(1);
  }

  /**
   * Add filter values in the JRS report page or the JRS widget.
   *
   * @param filterName the filter parameter name
   * @param filterValue value to be set in the filter
   * @param child set to true if search option should be used to search the filterValue.
   * @throws IllegalStateException if the filter is not found and set.
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  /*
   * public void passFilterValues(final String filterName, final String filterValue, final boolean child) { if
   * (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_FILTERFRAME_FILTERFRAME_XPATH, 30)) { boolean
   * artfactFound = false; List<WebElement> scopeOptions =
   * this.driverCustom.getWebElements(JRSConstants.JRSALLREPORTPAGE_FILTERVALUE_FILTER_XPATH); // Loop through the
   * options and select the one that matches artfactFound = selectFilterOptions(filterName, filterValue, child,
   * scopeOptions); if (!artfactFound) { fail("Artifact not found" + filterName + filterValue); } } }
   */

  /*
   * private boolean selectFilterOptions(final String filterName, final String filterValue, final boolean child, final
   * List<WebElement> scopeOptions) { boolean artfactFound = false; for (WebElement opt1 : scopeOptions) { if
   * (opt1.getText().trim().contains(filterName.trim())) { opt1.click(); waitForElement(5); if
   * (filterValue.equals("All")) { List<WebElement> scopeOptions6 =
   * this.driverCustom.getWebElements(JRSConstants.JRSALLREPORTPAGE_PROJECTFILTER_ALLPROJECT_XPATH);
   * scopeOptions6.get(this.i).click(); artfactFound = true; } else if (filterName.equals("Team Area") &&
   * filterValue.equals("Select all visible items")) { List<WebElement> scopeOption7 =
   * this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_FILTERTEAMAREA_INPUT_XPATH);
   * scopeOption7.get(0).click(); artfactFound = true; } else { if (!child) { artfactFound =
   * selectIfMatches(filterValue, artfactFound, JRSConstants.JRSCREATEREPORTPAGE_CHECKBOX_LABELTEXT_XPATH, false); }
   * else { List<WebElement> scopeOptions1 =
   * this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_SEARCHBOX_SPAN_XPATH);
   * scopeOptions1.get(this.i).sendKeys(filterValue); waitForElement(5); List<WebElement> scopeOptions2 =
   * this.driverCustom.getWebElements(JRSConstants.JRSCREATEREPORTPAGE_HIGHLIGHTTEXT_SPAN_XPATH);
   * scopeOptions2.get(0).click(); artfactFound = true; } this.i = this.i + 1; break; } } } return artfactFound; }
   */

  /**
   * Click on "Run"/"Save" button once the filter is set in the JRS report page or the JRS widget.
   */
  public void clickRunButton() {
    Button btnRun = this.engine.findElement(Criteria.isButton().withText("Run")).getFirstElement();
    btnRun.click();
  }

  /**
   * Verify if the Graph result is as per the expected result.
   *
   * @param screen expected graph result
   * @param pattern expected graph result pattern
   */
  public void verifyGraphResult(final Screen screen, final Pattern pattern) {
    waitForPageLoaded();
    if (this.driverCustom.isElementPresent(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH,
        Duration.ofSeconds(50))) {
      if (this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_REPORTLOADINGSPINNER_SPAN_XPATH,
          Duration.ofSeconds(50))) {
        this.driverCustom.switchToFrame(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH);
        verifyGraphResultInContainer(screen, pattern);
      }
    }
    else {
      fail("frame not found");
    }
  }

  /**
   * This method is specifically used for "ALM_Project_Burndown_Chart_for_USA_2018_1_0" report Open the burnDown report.
   *
   * @param reportName Name of the report to be selected in the burn down
   * @throws IllegalAccessException if the snapshot was created.
   */
  public void openBurndownReport(final String reportName) throws IllegalAccessException {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_EXPANDICON_EXPAND_XPATH,
        Duration.ofSeconds(30))) {
      if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(5), reportName)) {
        Link reportlink = this.engine.findFirstElement(Criteria.isLink().withText(reportName.trim()));
        reportlink.click();
        waitForPageLoaded();
        this.driverCustom.switchToFrame(JRSConstants.JRSALLREPORTPAGE_REPORTIFRAME_IFRAME_XPATH);
        if (this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_CHARTCONTAINER_DIV_XPATH,
            Duration.ofSeconds(60))) {
          return;
        }
        throw new IllegalAccessException("SnapShot Created");
      }
      fail("Report not found");
    }
  }

  /**
   * Verify the total number of Items in the result of the report to the expected input value.
   *
   * @param value the expected input value as a number . Ex: "5"
   * @throws IllegalStateException if the expected and actual number of records do not match or no records found or run
   *           report is not visible. {@link IllegalStateException#IllegalStateException(String)}
   */
  public void verifyTotalNoOfRecords(final String value) {
    verifyTotalNoOfRecords(value, JRSConstants.JRSALLREPORTPAGE_VIEWREPORTIFRAME_IFRAME_XPATH);
  }

  /**
   * <p>
   * Click on all reports show filter explorer button on All report page
   */
  public void clickOnShowFilterExplorer() {
    WebElement element = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_SHOWFILTER_XPATH);
    element.click();
  }


  /**
   * <p>
   * Select report filter
   * @param filterLabel 
   * @param value 
   */
  public void selectReportFilter (final String filterLabel, final String value) {
    Dropdown drdUserProfile = this.engine
        .findElement(Criteria.isDropdown().withLabel(filterLabel)).getFirstElement();
    drdUserProfile.selectOptionWithText(value);
  }
  
  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page. This method wait for
   * presence of all the reports in 'JRSAllReportsPage'.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSALLREPORTPAGE_REPORTNAME_REPORTNAME_XPATH);

  }

 
  
  
  /**
   * Folder is selected in Group by Folder in JRSAllReportsPage
   * @param folderName to be selected
   */
  public void selectFolder(String folderName) {
//    this.driverCustom.getPresenceOfWebElement("//tr[@data-name='Audi_PPE_CHM_PRM']//input[@type='checkbox']").click();
    this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_SELECTFOLDERCHECKBOX_XPATH, folderName).click();
  }
  
  
  /**
   * @param folderName to be deleted
   */
  public void deleteFolder(String folderName) {
    selectFolder(folderName);
    
    this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_DELETEFOLDER_XPATH).click();
    Alert alt = this.driverCustom.getWebDriver().switchTo().alert();
    alt.accept();
  }
  
  /**
   * @param reportName
   * Report checkbox is clicked on JRS All reports page
   */
  public void clickOnReportCheckbox(String reportName) {
    
    this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_REPORTNAME_CHECKBOX_XPATH, reportName).click();
    Reporter.logPass("Report checkbox clicked sucessfully.");
    
  }
  
  /**
   * @param reportName name of the report to be searched 
   */
  public void searchReport(String reportName) {
    waitForPageLoaded();
    this.driverCustom.isElementInvisible(JRSConstants.JRSALLREPORTPAGE_SEARCHTXT_XPATH, this.timeInSecs);
    WebElement txtSearch = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_SEARCHTXT_XPATH);
    txtSearch.sendKeys(reportName);
    this.driverCustom.click(JRSConstants.JRSALLREPORTPAGE_SEARCHICON_XPATH);
    waitForPageLoaded();
    boolean checkReport = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_REPORTNAME_XPATH, reportName).isDisplayed();
    if(checkReport) {
      Reporter.logPass(reportName+ " Report Searched successfully.");
    }else {
      Reporter.logPass(reportName+ " Report Not found.");
    }
  }
  
  
  /**
   * @param reportName is the name of the report to be moved
   * @param folderName is the name of the folder where it needs to be moved
   */
  public void moveReport(String reportName, String folderName) {
    
//    
    this.driverCustom.getWebElement("//tr[td//span[contains(text(), reportName)]]//input[@type='checkbox']").click();
    
    this.driverCustom.isElementInvisible(JRSConstants.JRSALLREPORTPAGE_MOVEBUTTON_XPATH, this.timeInSecs);
    this.driverCustom.click(JRSConstants.JRSALLREPORTPAGE_MOVEBUTTON_XPATH);    
    this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_MOVEFOLDERCHECKBOX_XPATH, folderName).click();
    
    this.driverCustom.getWebElement("//button[contains(text(),'Move')]").click();
  }
  
  /**
   * @param reportName is the report name that is to be copied
   * @param folderName is the folder where the report must be copied.
   */
  public void copyReport(String reportName, String folderName) {
    this.driverCustom.getWebElement("//tr[td//span[contains(text(), reportName)]]//input[@type='checkbox']").click();
    
    this.driverCustom.isElementInvisible(JRSConstants.JRSALLREPORTPAGE_COPYBUTTON_XPATH, this.timeInSecs);
    this.driverCustom.click(JRSConstants.JRSALLREPORTPAGE_COPYBUTTON_XPATH); 
    this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_MOVEFOLDERCHECKBOX_XPATH, folderName).click();
    System.out.println("Copy folder selected");
    
    this.driverCustom.getWebElement("//button[contains(text(),'Copy')]").click();
  }
  
  
  
  /**
   * @param reportName 
   * 
   */
  public void verifyReportSelection(String reportName, String folderName) {
    
    waitForPageLoaded();
    boolean checkReport = this.driverCustom.getWebElement(JRSConstants.JRSALLREPORTPAGE_REPORTNAME_XPATH, reportName).isSelected();
    
    if(checkReport) {
      Reporter.logPass(reportName+ " Report checkbox is selected.");
    }else {
      Reporter.logPass(reportName+ " Report checkbox is not selected.");
    }
    
    waitForPageLoaded();
    boolean checkFolder = this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYFOLDEREXPAND_XPATH, folderName).isSelected();
    
    if(checkFolder) {
      Reporter.logPass(folderName+ " Parent Folder checkbox is selected.");
    }else {
      Reporter.logPass(folderName+ " Parent Folder checkbox is not selected.");
    }
    
    
    
    
  }

}
