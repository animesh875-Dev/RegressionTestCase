/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMDashBoardPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;

/**
 * @author hrt5kor
 */
public class RMDashBoardPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom must not be null.
   */
  public RMDashBoardPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <P>
   * Verifies menu from RM Pages.
   * <P>
   * This method called after {@link RMDashBoardPage#openMenu(String)}.
   *
   * @param Menu of the RM Page like Artifacts,Reviews and Reports.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenMenu(final String Menu, final String lastResult) {
    List<WebElement> menulist =
        this.driverCustom.getWebElements("//div[@class='net-jazz-ajax-PageList']/following-sibling::a");
    for (WebElement menu : menulist) {
      if (menu.getAttribute("title").contentEquals(Menu)) {
        return new TestAcceptanceMessage(true, "Verified title of the Menu.\n Actual menu title is \"" + Menu +
            "\"matched with Expected menu title \"" + Menu + "\".");
      }
    }
    return new TestAcceptanceMessage(true, "Verified title of the Menu.\n Actual menu title is \"" + Menu +
        "\"not matched with Expected menu title \"" + Menu + "\".");
  }

  /**
   * <P>
   * Verifies clicked on config option from 'Current Configuration' drop down.
   * <P>
   * This method called after {@link RMDashBoardPage#clickOnCurrentConfigurationDropdownMenu(String)}.
   *
   * @param menuItemInConfigMenu - option to be select from 'Current Configuration'.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnCurrentConfigurationDropdownMenu(final String menuItemInConfigMenu,
      final String lastResult) {
    waitForSecs(20);
    String expectedDialogTitle = "";
    switch (menuItemInConfigMenu) {
      case "Create Stream...":
        expectedDialogTitle = "Create Stream";
        break;
      case "Create Baseline...":
        expectedDialogTitle = "Create Baseline";
        break;
      case "Compare Configuration...":
        expectedDialogTitle = "Select Configuration to Compare";
        break;
      case "Deliver Changes...":
        expectedDialogTitle = "Select Target Configuration";
        break;
      case "Deliver Change Set...":
        expectedDialogTitle = "Existing Delivery Sedssion Detected";
        break;
      case "Accept Changes...":
        expectedDialogTitle = "Select Source Configuration";
        break;
      case "Create Change Set...":
        expectedDialogTitle = "Create Change Set";
        break;
      default:
        throw new WebAutomationException("Configuration option is not handle in automation");
    }
    boolean isDialogVisibleV6 =
        this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(10), expectedDialogTitle);
    boolean isDialogVisibleV7 =
        this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_DIALOG_TITLE_XPATH, Duration.ofSeconds(10), expectedDialogTitle);
    boolean isAlreadyChangeSet =
        this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_PROJECTDB_ADDWIDGET_DROPDOWN_SELECTDNG_XPATH, Duration.ofSeconds(10),
            "Current configuration context already change set");
    if (isDialogVisibleV6 || isDialogVisibleV7 || isAlreadyChangeSet) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on 'Current Configuration' drop down and selected '" + menuItemInConfigMenu +
          "' option.\n" + "Actual Result option \"" + menuItemInConfigMenu +
          "\" is selected.\nExpected Result option \"" + menuItemInConfigMenu + "\" should be selected.");
    }
    else if (this.driverCustom.isElementVisible("//div[text()='DYNAMIC_VAlUE']", this.timeInSecs,
        "Existing Delivery Sessions Detected") ||
        this.driverCustom.isElementVisible("//h1[text()='Deliver Change Sets']", this.timeInSecs)) {
      return new TestAcceptanceMessage(true,
          "Verified: Clicked on 'Current Configuration' drop down and clicked 'Deliver Change Set' option.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Not clicked on 'Current Configuration' drop down or not selected appropriate option.");
  }

  /**
   * <P>
   * Verifies config is created.
   * <P>
   * This method called after {@link RMDashBoardPage#createConfig(Map)}.
   *
   * @param additionalParams refers to key and value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyCreateConfig(final Map<String, String> additionalParams, final String lastResult) {
    waitForSecs(10);
    this.driverCustom.isElementClickable(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(10));
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH);
    waitForSecs(5);
    List<WebElement> changeSetDetails = this.driverCustom.getWebElements(
        "//table[@class='dijit dijitMenu dijitMenuPassive dijitReset dijitMenuTable']//tr[6]/td//div[1]/descendant::div//a");
    for (WebElement changeset : changeSetDetails) {
      if (changeset.getText().trim().equalsIgnoreCase(additionalParams.get(RMConstants.CONFIG_NAME))) {
        String result = "Verified: New Change Set is created. \nActual Result name of the change set is " + '"' +
            changeset.getText() + '"' + "\nExpected Result name of the change set should be " +
            additionalParams.get(RMConstants.CONFIG_NAME) + "\".";
        return new TestAcceptanceMessage(true, result);
      }
    }
    return new TestAcceptanceMessage(false, "Verified: New Change Set is not created.");
  }

  /**
   * <P>
   * Verifies change set delivery option is selected. <br>
   * This method called after {@link RMDashBoardPage#chooseChangeSetDeliveryOption(String)}.
   *
   * @param changeSetOption change set option.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseChangeSetDeliveryOption(final String changeSetOption,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//span[text()='DYNAMIC_VAlUE']/../preceding-sibling::div/input[@aria-checked='true']", Duration.ofSeconds(10), changeSetOption)) {
      return new TestAcceptanceMessage(true,
          "Verified: Required option is selected while delivering change set.\nActual Result \"" + changeSetOption +
          "\" option is selected.\n" + "Expected Result \"" + changeSetOption + "\" option should be selected.\n");  
    }
    return new TestAcceptanceMessage(false,
        "Verified: Required option \"" + changeSetOption + "\" is not selected while delivering the change set.");
  }

  /**
   * <P>
   * Verifies configuration is switched to 'Global Configuration'.
   * <P>
   * This method called after {@link RMDashBoardPage#selectTheConfigContext(Map)}.
   *
   * @param additionalParams refers to key and value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectTheConfigContext(final Map<String, String> additionalParams,
      final String lastResult) {
    waitForSecs(10);
    // Update due to fail Unit test:
    // JavascriptExecutor jse = (JavascriptExecutor)driverCustom;
    // jse.executeScript("window.scrollBy(0,-250)");
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("window.scrollBy(0,-250)");

    /*
     * WebElement Element = driverCustom.getWebElement("//div[@class='content jazz-ui-PageTemplate-flexrow']");
     * jse.executeScript("arguments[0].scrollIntoView();", Element); waitForSecs(10);
     */
    this.driverCustom.isElementInvisible("//div[@role='dialog']//div[text()='Select the Configuration Context']",
        this.timeInSecs);
    String streamName = this.driverCustom.getText("//img/following-sibling::span[@class='titleNode']");
    if (streamName.trim().equalsIgnoreCase(additionalParams.get("streamName"))) {
      return new TestAcceptanceMessage(true, "Verified: Switched stream name.\nActual stream name \"" +
          additionalParams.get("streamName") + "\".Expected stream name \"" + streamName + "\".");
    }
    return new TestAcceptanceMessage(false, "Verified: Switched stream name not matched with expected stream.");
  }

  /**
   * <P>
   * Verifies artifact is delivered to the stream.
   * <P>
   * This method called after {@link RMDashBoardPage#getDeliveredArtifact(String)}.
   *
   * @param artifactID ID of the artifact.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetDeliveredArtifact(final String artifactID, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      String msg = this.driverCustom.getWebElement("//div[@data-dojo-attach-point='_infoLine']/table//td").getText();
      return new TestAcceptanceMessage(true,
          "Verified: Changes are ready to deliver.\nIt will be delivered to target configuration - '" + msg + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified: Changes are not ready to deliver.");
  }

  /**
   * <P>
   * Verifies change set delivery option enabled.
   * <P>
   * This method called after {@link RMDashBoardPage#enableChangeSetDeliveryModeForConflicts(String)}.
   *
   * @param option to be enable while delivering change set.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */

  public TestAcceptanceMessage verifyEnableChangeSetDeliveryModeForConflicts(final String option,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//span[contains(text(),'DYNAMIC_VAlUE')]/../preceding-sibling::div/input[@aria-checked='true']", Duration.ofSeconds(20), option)) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected the change set delivery for conflicts.\nActual Result \"" + option +
          "\" is selected.\nExpected Result \"" + option + "\" should be selected.");
    }
    return new TestAcceptanceMessage(false, "Verified: Change set delivery mode for conflicts is not enabled.");

  }

  /**
   * <p>
   * Verifies the action of {@link RMDashBoardPage#logOut(String, String)}.
   *
   * @param ssoEnabled boolean value to be passed in the method.
   * @param repositoryUrl server url.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyLogOut(final String ssoEnabled, final String repositoryUrl,
      final String lastResult) {
    if (this.driverCustom.isElementVisible("//label[contains(text(),'Password')]", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified: User logged out the application successfully.\nActual Result \"" + repositoryUrl +
          "\" repository logged out.");
    }
    return new TestAcceptanceMessage(false, "Verified: User not logged out the server successfully.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMDashBoardPage#clickOnDialogLabel(String, String)}.
   *
   * @param dialog name of dialog.
   * @param labelName name of label.
   * @param lastResult result.
   * @return true if label selected.
   */
  public TestAcceptanceMessage verifyClickOnDialogLabel(final String dialog, final String labelName,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_EXPORT_BUTTON_XPATH, Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified: Radio button clicked  \"" + labelName + "\" ");
    }
    return new TestAcceptanceMessage(false, "Verified: radio button not selected.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }

  /**
   * <p>
   * Verify change set is discarded successfully
   * <p>
   *
   * @param changesetName name of change set to be discarded
   * @param lastResult result from ${@link RMDashBoardPage#discardChangeset(String)}
   * @return true if the change set is discarded successfully or vice versa
   */
  public TestAcceptanceMessage verifyDiscardChangeset(final String changesetName, final String lastResult) {
    if (!this.driverCustom.isElementVisible(RMConstants.RMDASHBOARD_CHANGESET_CONFIG, Duration.ofSeconds(10), changesetName)) {
      return new TestAcceptanceMessage(true, "Verified discard change set '" + changesetName + "': PASSED!");
    }
    return new TestAcceptanceMessage(true,
        "Verified discard change set '" + changesetName + "': FAILED!. Change set is not discarded!");
  }

  /**
   * <p>
   * Verify the target configuration is enabled or not after
   * ${@link RMDashBoardPage#checkCurrentConfigurationsEnabled(String)}
   * <p>
   *
   * @author NVV1HC
   * @param name e.g: Create Baselines..., Compare Configuration...
   * @param additionalParam_expectedEnable true or false, true if we want check the configuration is enabled or vice
   *          versa
   * @param lastResult result from ${@link RMDashBoardPage#checkCurrentConfigurationsEnabled(String)}
   * @return true if the current status of the configuration is similar with the additionalParam_expectedEnabled
   */
  public TestAcceptanceMessage verifyCheckCurrentConfigurationsEnabled(final String name,
      final String additionalParam_expectedEnable, final String lastResult) {
    if (lastResult.equalsIgnoreCase(additionalParam_expectedEnable)) {
      return new TestAcceptanceMessage(true,
          "Verified the status of the configuration matches with expectation: PASSED!");
    }
    return new TestAcceptanceMessage(false,
        "Verified the status of the configuration matches with expectation: FAILED!");
  }

  /**
   * <p>
   * This method verifies the component is archived or not after ${@link RMDashBoardPage#archiveComponent()})}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMDashBoardPage#archiveComponent()})}
   * @return true if the component is archived successfully or vice versa
   */
  public TestAcceptanceMessage verifyArchiveComponent(final String lastResult) {
    try {
      this.driverCustom.getPresenceOfWebElement("//a[@class='button']/img[@alt='Restore this component']");
      return new TestAcceptanceMessage(true, "Component is archived successfully!");
    }
    catch (Exception e) {}
    return new TestAcceptanceMessage(false, "Component is not archived successfully!");
  }

  /**
   * <p>
   * Verify a new component is created successfully or not after
   * ${@link RMDashBoardPage#createComponentUsingTemplate(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   * @param name Component name
   * @param description component's description
   * @param templateName name of template which we use to create a new component
   * @param lastResult result from ${@link RMDashBoardPage#createComponentUsingTemplate(String, String, String)}
   * @return true if a new component is created successfully or vice versa
   */
  public TestAcceptanceMessage verifyCreateComponentUsingTemplate(final String name, final String description,
      final String templateName, final String lastResult) {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), this.timeInSecs);
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//a[contains(@title,'Current Project Component')]/span[text()='" + name + "']")));
      return new TestAcceptanceMessage(true, "Component '" + name + "' is created successfully!");
    }
    catch (Exception e) {}
    return new TestAcceptanceMessage(false, "Component '" + name + "' is not created successfully!");
  }

  /**
   * Verify selected Stream to delivery changes from Component to a Stream successfully <br>
   * This method called after {@link RMDashBoardPage#deliveryChangesToAStream(String)}<br>
   *
   * @author VDY1HC
   * @param streamName - name of local stream to delivery changes
   * @param lastResult - last result of method
   * @return true - page loaded to Deliver Change Sets successfully
   */
  public TestAcceptanceMessage verifyDeliveryChangesToAStream(final String streamName, final String lastResult) {
    return new TestAcceptanceMessage(this.driverCustom.isElementVisible
        (RMConstants.RMDASHBOARDPAGE_CHECKING_HEADER_XPATH, timeInSecs, "Deliver Change Sets"),
        "Select Delivery Changes option from current component to stream name: " + streamName);
  }

  /**
   * Click on "Next >" in delivery changes set view, until found wanted section This method called after
   * {@link RMDashBoardPage#clickOnNextButtonToDeliverySpecificSection(String)}
   *
   * @author VDY1HC
   * @param sectionName - name of Section expected
   * @param lastResult - lastResult
   * @return true - if navigated to expected section
   */
  public TestAcceptanceMessage verifyClickOnNextButtonToDeliverySpecificSection(final String sectionName,
      final String lastResult) {
    WebElement currentSectionTitle =
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_CURRENT_DELIVERY_SECTION_TITLE);
    String currentSection = currentSectionTitle.getText();
    if (currentSection.equalsIgnoreCase(sectionName)) {
      return new TestAcceptanceMessage(true, "Navigated to Delivery changes for section: " + sectionName);
    }
    return new TestAcceptanceMessage(false, "Unable to navigated to Delivery changes for section: " + sectionName +
        "(Current section: " + currentSection + ")");
  }

  /**
   * Verify after check on checkbox in filter dialog <br>
   * This method called after
   * {@link RMDashBoardPage#selectCheckboxInDropDownWhenDeliveryChanges(String, String, String)}
   *
   * @author VDY1HC
   * @param drdToolTip - Tooltip of filter dropdown
   * @param option - option to be checked
   * @param isChecked - Expected state of option is checked or not if checked: isChecked = true
   * @param lastResult - true - if perform action click false - if not perform action click
   * @return true - if state of checkbox match expected
   */
  public TestAcceptanceMessage verifySelectCheckboxInDropDownWhenDeliveryChanges(final String drdToolTip,
      final String option, final String isChecked, final String lastResult) {
    WebElement drdFilter = null;
    switch (drdToolTip) {
      case "Filter Artifacts":
        drdFilter = this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_FILTER_SECTION_DROPDOWN_XPATH,
            drdToolTip);
        break;
      case "Filter by change type":
        drdFilter = this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_FILTER_TYPE_DROPDOWN_XPATH,
            drdToolTip);
        break;
      default:
        throw new WebAutomationException("Dropdown is not defined");
    }
    if (lastResult.equalsIgnoreCase("true")) {
      this.driverCustom.getClickableWebElement(drdFilter).click();
    }
    WebElement selectOption =
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_FILTER_RADIOBUTTON_XPATH, option);
    if (selectOption.getAttribute("aria-checked").equalsIgnoreCase(isChecked)) {
      this.driverCustom.getClickableWebElement(drdFilter).click();
      return new TestAcceptanceMessage(true,
          "Successful select checkbox in " + drdToolTip + " dropdown. Current state of checkbox is: " + isChecked);
    }
    return new TestAcceptanceMessage(false, "Unable to select checkbox in " + drdToolTip + " dropdown.");
  }

  /**
   * Verify 'select a view' link and click successfully <br>
   * This method called after {@link RMDashBoardPage#addWidgetToDashboard}<br>
   *
   * @author @author UUM4KOR
   * @param lastResult - last result of method
   * @return true if 'select a view' link not visible after clicking 'select a view' link.
   */
  public TestAcceptanceMessage verifyClickOnSelectAViewLink(final String lastResult) {
    waitForSecs(5);
    if (this.driverCustom.getPresenceOfWebElement(("//div[@dojoattachpoint='_settingsContainer']//table"))
        .isDisplayed()) {
      return new TestAcceptanceMessage(true,
          "Verified view table after clicking 'select a view' link.\nlink 'select a view' clicked successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified view table after clicking 'select a view' link.\nlink'select a view' not clicked successfully.");
  }

  /**
   * @author UUM4KOR
   * @param attribute attribute name.
   * @param value attribute value
   * @param lastResult lastResult - last result of method
   * @return true if value set successfully
   */
  public TestAcceptanceMessage verifySelectViewValue(final String attribute, final String value,
      final String lastResult) {
    waitForSecs(10);
    if (this.driverCustom.isElementVisible("//div[@class='header-primary'][@title='" + value + "']", this.timeInSecs)) {
      return new TestAcceptanceMessage(true,
          "Verified attribute " + attribute + " value '" + value + "' set successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified attribute " + attribute + " value '" + value + "' Not set successfully.");
  }

  /**
   * @author UUM4KOR
   * @param viewTitle expected view title
   * @param viewData view updated data
   * @param additionalParam updated data
   * @param lastResult returned value of method which is executed just before the method.
   * @return true if updated view data is displayed.
   */
  public TestAcceptanceMessage verifyIsViewDataDisplayed(final String viewTitle, final String viewData,
      final String additionalParam, final String lastResult) {
    waitForSecs(2);
    try {
      List<WebElement> viewDataList =
          this.driverCustom.getWebElements("//a[@class='jazz-ui-ResourceLink'][text()='" + viewData + " ']");
      for (WebElement del : viewDataList) {
        if (del.getText().contains(viewData)) {
          return new TestAcceptanceMessage(true,
              "Verified view data '" + additionalParam + "' link is displayed on the second page successfully.");
        }
      }
    }
    catch (Exception e) {
      if (Boolean.valueOf(lastResult)) {
        return new TestAcceptanceMessage(true,
            "Verified view data '" + additionalParam + "' link is displayed on the second page successfully.");
      }
      return new TestAcceptanceMessage(false,
          "Verified view data '" + additionalParam + "' link is not displayed on the second page successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified view data '" + additionalParam + "' link is not displayed on the second page successfully.");
  }


  /**
   * <p>
   * Verifies the action of {@link RMDashBoardPage#saveProjectDashboard}.
   *
   * @param additionalParam is expected value to be verified against actual value.
   * @param lastResult returned value of method which is executed just before the method.
   * @return return true if Save button is disable return after clicking Save button
   */
  public TestAcceptanceMessage verifySaveProjectDashboard(final String additionalParam, final String lastResult) {
    waitForSecs(3);
    List<WebElement> elements = this.driverCustom.getWebElements("//input[contains(@class,'saveButton')][@value='Save']");
    boolean isClicked =
        elements.size() >= 1 ? elements.get(elements.size() - 1).getAttribute(CCMConstants.DISABLED) != null : false;
        if (isClicked && Boolean.valueOf(lastResult) &&
            this.driverCustom.getPresenceOfWebElement("//div[@class='messageSummary'][text()='" + additionalParam + "']")
            .getText().toString().contains(additionalParam)) {
          return new TestAcceptanceMessage(Boolean.valueOf(lastResult),
              "Verified save button is 'disabled' hence 'Project Dashboard' is saved successfully.\nand verified '" +
                  additionalParam + "' message.");
        }
        return new TestAcceptanceMessage(false,
            "Verified save button is 'not disabled' hence 'Project Dashboard' is not saved successfully.\nand verified '" +
                lastResult + "' message not displayed.");
  }

  /**
   * <p>
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMDashBoardPage#clickOnHomeMenu()}}
   * @return verification message
   */
  public TestAcceptanceMessage verifyClickOnHomeMenu(final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_HOMEMENU_XPATH, this.timeInSecs)) {
      return new TestAcceptanceMessage(true, "Verified PASSED: Click on Home Menu successfully!");
    }
    return new TestAcceptanceMessage(false, "Verified PASSED: Click on Home Menu failed!");
  }

  /**
   * Verify for action 'selectValuesForRequirementsView' <br>
   * 
   * This method called after {@link RMDashBoardPage#selectValuesForRequirementsView(Map)}<br>
   *
   * @author LTU7HC
   * @param additionalParams refers to key and value pair.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectValuesForRequirementsView(final Map<String, String> additionalParams, final String lastResult) {
    String view = additionalParams.get("VIEW").trim();
    waitForPageLoaded();
    waitForSecs(30);
    boolean isVisible =
        this.driverCustom.isElementVisible(RMConstants.HEADER_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(30), view);
    if (isVisible) {
      return new TestAcceptanceMessage(true, String.format(
          "Verified that select values for 'Requiremnets View' successfully with view as: '%s'", view));
    }
    return new TestAcceptanceMessage(false, String.format(
        "Verified that select values for 'Requiremnets View' failed - Expected View: '%s'", view));
  }

  /**
   * Verify for action 'removeWidgetWithContains' <br>
   * 
   * This method called after {@link RMDashBoardPage#removeWidgetWithContains(String)}<br>
   *
   * @author LTU7HC
   * 
   * @param widgetNameOrWidgetContainsName is full name of widget want to remove or widget contains name widget want to remove
   * @param lastResult - last result of method
   * @return verification message.
   */
  public TestAcceptanceMessage verifyRemoveWidgetWithContains(final String widgetNameOrWidgetContainsName,
      final String lastResult) {
    boolean isInvisible =
        this.driverCustom.isElementInvisible(RMConstants.HEADER_REQUIREMENTS_VIEW_XPATH, Duration.ofSeconds(30), widgetNameOrWidgetContainsName);
    if (isInvisible) {
      return new TestAcceptanceMessage(true, String.format("Verified Widget: '%s' is removed successfully!", widgetNameOrWidgetContainsName));
    }
    return new TestAcceptanceMessage(false, String.format("Verified Widget: '%s' is not removed", widgetNameOrWidgetContainsName));
  }
  
  /**
   * Verify for action 'selectConfigurationToCompare' <br>
   * 
   * This method called after {@link RMDashBoardPage#selectConfigurationToCompare(String)}<br>
   * @param streamName - the stream name
   * @param lastResult - last result of method
   * @return verification message.
   */
  public TestAcceptanceMessage verifySelectConfigurationToCompare(final String streamName, final String lastResult) {  
    return new TestAcceptanceMessage(this.driverCustom.isElementClickable(RMConstants.DNG_TEMPLATEPAGE_OKBUTTON_XPATH, timeInSecs), 
        "Select Configurations option to compare from current component to stream name: " + streamName);
  }
  
  /**
   * Verify action {@link RMDashBoardPage#getListOfAllStreamsUnderComponent}
   * @param lastResult : returned value of last method which is executed just before.
   * @return acceptance object which contains verification results.
   * 
   */
  public TestAcceptanceMessage verifyGetListOfAllStreamsUnderComponent(final String lastResult) {
    if(!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, " Stream list contains: "+lastResult);
    }
       return new TestAcceptanceMessage(false, "Unable to get Stream list");
  }
}
