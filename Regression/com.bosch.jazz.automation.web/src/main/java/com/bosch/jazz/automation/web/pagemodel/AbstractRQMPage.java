/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.TextField;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.RadioButtonFinder;
import com.bosch.psec.web.test.finder.container.PanelFinder;
import com.bosch.psec.web.test.finder.container.RowFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * <p>
 * Represents abstraction of methods to quality management pages. <br>
 * Acts as super class to all Requirement management pages. <br>
 * Declare methods which are common to requirement management pages., declare abstract methods which are to be
 * overridden by every other pages of requirement management pages..
 *
 * @author HRT5KOR
 */
public class AbstractRQMPage extends AbstractJazzWebPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public AbstractRQMPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new JazzTextFieldFinder(),
        new JazzDropdownFinder(), new JazzButtonFinder(), new RowCellFinder(), new JazzTabFinder(),
        new JazzDialogFinder(), new CheckboxFinder(), new RowFinder(), new JazzLabelFinder(), new LinkFinder(),
        new DropdownFinder(), new RadioButtonFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(),
        new PanelFinder(), new RowCellFinder());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    throw new UnsupportedOperationException();
  }

  /**
   * <p>
   * Login in to alm application of rational quality management "https://<local- host:8080>/qm" using
   * {@link JazzLoginPage#loginWithGivenPassword(String, String)}. <br>
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the list.
   * <br>
   * Left side of the menu "Current Project component" drop down menu will be present, open the drop down and click on
   * "Choose Another Component.." menu. <br>
   * Select the project area from the project area drop down. <br>
   * Search the component name from the search text field present in "Choose component" dialog. <br>
   * Select the component if found in the choose component dialog.Click on ok. <br>
   * On the same line left side starting of the page, there will be a "Current configuration" drop down present on the
   * page. <br>
   * Click on "Switch" button and select "Global Configuration" tab present on the "Select the Configuration Context"
   * dialog. <br>
   * Select the project area from the project area drop down. <br>
   * Search the stream name from the search text field present in "Select the Configuration Context" dialog. <br>
   * Select the stream if found in the Select the Configuration Context" dialog.Click on "Ok" button.
   *
   * @param projectAreaName name of the project area to be opened.
   * @param gcName name of the configuration.
   * @param componentName name of the component.
   * @param streamName name of the stream.
   */
  @Override
  public void selectProjectAreaAndGlobalConfiguration(final String projectAreaName, final String gcName,
      final String componentName, final String streamName) {
    this.driverCustom.clickOnLink(projectAreaName, false);
    LOGGER.LOG.info(projectAreaName + "project area opened successfully.");
    if (gcName.equalsIgnoreCase("null") || componentName.equalsIgnoreCase("null") ||
        streamName.equalsIgnoreCase("null")) {
      return;
    }
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH);
    if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5), "Component Setup")) {
      clickOnJazzButtons("Close");
    }
    this.driverCustom.getPresenceOfWebElement("//*[@title='Current Project Component']");
    this.driverCustom.getPresenceOfWebElement("//*[@title='Current Configuration']");
    waitForSecs(Duration.ofSeconds(5));
    if (!this.driverCustom.getWebElement("//*[@title='Current Project Component']").getText().trim()
        .equals(componentName.trim())) {

      for (int i = 0; i < 6; i++) {
        if (!this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10),
            "Choose Another Component...")) {
          this.driverCustom.isElementClickable(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH, Duration.ofSeconds(10));
          try {
            Link currentProjectComponentlink =
                this.engine.findFirstElement(Criteria.isLink().withToolTip("Current Project Component"));
            currentProjectComponentlink.click();
          }
          catch (Exception e) {
            Optional<WebElement> config =
                this.driverCustom.getWebElements("//a[starts-with(@id, 'jazz_ui_MenuPopup')]").stream()
                    .filter(x -> x.getAttribute("aria-label").equalsIgnoreCase("Current Configuration Drop-Down Menu"))
                    .findFirst();
            if (!config.isPresent()) {
              throw new WebAutomationException(
                  "Current component set up menu not found \nOr\nConfiguration management not enabled.");
            }
            config.get().click();

          }
        }
        else {
          break;
        }
      }
      this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, "Choose Another Component...");
      this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH, Duration.ofSeconds(5));
      for (int i = 0; i < 6; i++) {
        if (!this.driverCustom.isElementVisible(RQMConstants.RQMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH, Duration.ofSeconds(5),
            projectAreaName)) {
          this.driverCustom.click(RQMConstants.RQMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH);
        }
        else {
          break;
        }
      }
      this.driverCustom.click(RQMConstants.RQMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH, projectAreaName);
      TextField txt =
          this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter list")).getFirstElement();
      txt.setText(projectAreaName);
      Cell cell = this.engine.findElement(Criteria.isCell().withText(projectAreaName)).getFirstElement();
      cell.click();
      Dialog dialog = this.engine.findElement(Criteria.isDialog().withTitle("Choose a Component")).getFirstElement();
      TextField t = this.engine.findFirstElement(
          Criteria.isTextField().inContainer(dialog).withPlaceHolder("Type to search names (enter * to show all)"));
      t.setText(componentName.trim());
      waitForSecs(2);
      if (this.driverCustom.isElementVisible(RQMConstants.RQM_SELECT_COMP_XPATH, Duration.ofSeconds(10), componentName.trim())) {
        this.driverCustom.click(RQMConstants.RQM_SELECT_COMP_XPATH, componentName.trim());
        clickOnJazzButtons("OK");
        this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTTYPE_LINKTEXT_LINKTEXT, Duration.ofSeconds(20), "All");
      }
      else {
        clickOnJazzButtons("Cancel");
      }
    }
    waitForSecs(Duration.ofSeconds(5));// Since after selecting the component, page will get blink, after that if we click other config
                   // menu,
    // blink closes the menu , to avoid this wait for seconds added.So that after some wait blink doen't occur.
    this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(10));
    if (!(this.driverCustom.isElementPresent(
        "//a[@class='configurationUiNode hideLink']//img[contains(@class, 'globalConfigurationNode')]", Duration.ofSeconds(120)) &&
        this.driverCustom.getWebElement("//*[@title='Current Configuration']").getText().trim()
            .equalsIgnoreCase(streamName.trim()))) {
      this.driverCustom.getFirstVisibleWebElement(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, null).click();
      this.driverCustom.isElementVisible("//button[text()='Switch']", Duration.ofSeconds(5));
      Button btnSwitch = this.engine.findElement(Criteria.isButton().withText("Switch")).getFirstElement();
      btnSwitch.click();
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SWITCHTOGC_CONFIGURATION_XPATH);

      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH, Duration.ofSeconds(5));
      this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH, Duration.ofSeconds(10));
      this.driverCustom.getPresenceOfWebElement("//a[@title='Search Streams']");
      this.driverCustom.getFirstVisibleWebElement("//a[@title='Search Streams']", null).click();

      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, Duration.ofSeconds(10), "Streams");
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, "Streams");
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH);
      this.driverCustom.typeText(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH, streamName);
      for (int i = 0; i < 5; i++) {
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_SNAPSHOTOK_BUTTON_XPATH, Duration.ofSeconds(5), "OK")) {
          // Pending
          this.driverCustom.click(RQMConstants.RQMPROJECT_BASELINESEARCH_FILTER_XPATH, streamName);
        }
        else {
          break;
        }
      }
      this.driverCustom.isElementVisible(RQMConstants.JAZZPAGE_BUTTONS_XPATH,Duration.ofSeconds(5), "OK");
      if (this.driverCustom.isElementVisible(RQMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), "OK")) {
        clickOnJazzButtons("OK");
        this.driverCustom.switchToDefaultContent();
      }
      this.driverCustom.switchToDefaultContent();
      waitForSecs(Duration.ofSeconds(5));
    }
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
   * This method will get number of row in table
   * 
   * @param tableName name as: Test Suites, Test cases, Test scripts ,...
   * @return number of row (String)
   * @author LTU7HC
   */
  public String getNumberOfRowsInTable(final String tableName) {
    try {
      List<WebElement> lstRows =
          this.driverCustom.getWebElements(RQMConstants.RQM_LISTVALUE_NUMBER_OF_ROW_IN_TABLE, tableName);
      return String.valueOf(lstRows.size());
    }
    catch (Exception e) {
      throw new WebAutomationException(String.format("Get number of rows in table '%s' errors: %s", tableName, e));
    }
  }

  /**
   * <p>
   * This method used to click on 'Show Inline Filters' in RQM table
   * 
   * @author LTU7HC
   */
  public void showInlineFilters() {
    waitForPageLoaded();
    if (this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 6)))) {
      this.driverCustom.click(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH);
      LOGGER.LOG.info("Clicked on 'Show Inline Filters'");
    }
  }

  /**
   * <p>
   * This method used to click on 'Show Filters' in RQM table
   * 
   * @author LTU7HC
   */
  public void showFilters() {
    waitForPageLoaded();
    if (this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_SHOW_FILTERS_BUTTON_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() / 6)))) {
      this.driverCustom.click(RQMConstants.RQMPROJECT_SHOW_FILTERS_BUTTON_XPATH);
      LOGGER.LOG.info("Clicked on 'Show Filters'");
    }
  }

  /**
   * <p>
   * This method used to click on 'Show Filters' in RQM table
   * 
   * @author LTU7HC
   */
  public void filterBy(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    String dropDownOption = additionalParams.get("dropDownOption");
    // Click on dropdown
    this.driverCustom.isElementClickable("//div[@class='rqm-expando-panel']//div[@class='drop-down']", Duration.ofSeconds(30));
    this.driverCustom.getWebDriver().findElement(By.xpath("//div[@class='rqm-expando-panel']//div[@class='drop-down']"))
        .click();
    // Select drop down option
    String optionXpath = String.format("//div[@class='popup-tab-choices']//div[text()='%s']", dropDownOption);
    this.driverCustom.isElementClickable(optionXpath, Duration.ofSeconds(30));
    this.driverCustom.getWebDriver().findElement(By.xpath(optionXpath)).click();
    LOGGER.LOG.info("Clicked on drop down and select option: " +dropDownOption);
    
    switch (dropDownOption) {
      case "Test Plans":
        
        break;

      default:
        throw new WebAutomationException("Unsupported for the another options yet. Please implement it.");
    }

  }
}
