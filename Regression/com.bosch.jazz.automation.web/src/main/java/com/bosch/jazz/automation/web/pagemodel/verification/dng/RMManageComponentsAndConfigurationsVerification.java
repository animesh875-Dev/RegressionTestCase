/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentPropertiesPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentsAndConfigurations;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.RadioButton;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.container.dialog.JazzDialogFinder;

/**
 * @author RUN2HC
 */
public class RMManageComponentsAndConfigurationsVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom WebDriver
   */

  public RMManageComponentsAndConfigurationsVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new LinkFinder());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, RMConstants.ARTIFACTS);
  }

  /**
   * <p>
   * Verifies the action of
   * {@link RMManageComponentsAndConfigurations#openAndSelectSubMenuInCurrentConfiguration(String)}.
   *
   * @param subMenu one of sub menu in the Current Configuration context menu
   * @param lastResult get lastResult of
   *          {@link RMManageComponentsAndConfigurations#openAndSelectSubMenuInCurrentConfiguration(String)}
   * @return true if page or dialog is available
   */
  public TestAcceptanceMessage verifyOpenAndSelectSubMenuInCurrentConfiguration(final String subMenu,
      final String lastResult) {
    switch (subMenu) {

      case "Create Stream...":
        try {
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_STREAM_DIALOG)).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE + RMConstants.CREATE_STREAM_DIALOG +
              RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE + RMConstants.CREATE_STREAM_DIALOG +
              RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE + RMConstants.CREATE_STREAM_DIALOG +
              RMConstants.DIALOG_IS_NOT_DISPLAYED_EXPECTED_IS_THE + RMConstants.CREATE_STREAM_DIALOG +
              RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }

      case "Create Baseline...":
        try {
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.CREATE_BASELINE_DIALOG + RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.CREATE_BASELINE_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.CREATE_BASELINE_DIALOG + RMConstants.DIALOG_IS_NOT_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.CREATE_BASELINE_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }

      case "Compare Configuration...":
        try {
          this.engine.findElement(Criteria.isDialog().withText(RMConstants.COMPARE_CONFIGURATION_DIALOG))
          .getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.COMPARE_CONFIGURATION_DIALOG + RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.COMPARE_CONFIGURATION_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.COMPARE_CONFIGURATION_DIALOG + RMConstants.DIALOG_IS_NOT_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.COMPARE_CONFIGURATION_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }

      case "Create Change Set...":
        try {
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_CHANGE_SET_DIALOG))
          .getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.CREATE_CHANGE_SET_DIALOG + RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.CREATE_CHANGE_SET_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.CREATE_CHANGE_SET_DIALOG + RMConstants.DIALOG_IS_NOT_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.CREATE_CHANGE_SET_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }

      case "Link to a Work Item...":
        try {
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.LINK_TO_A_WORKITEM_SET_DIALOG))
          .getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.LINK_TO_A_WORKITEM_SET_DIALOG + RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.LINK_TO_A_WORKITEM_SET_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.LINK_TO_A_WORKITEM_SET_DIALOG + RMConstants.DIALOG_IS_NOT_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.LINK_TO_A_WORKITEM_SET_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }

      case "Compare with Stream...":
        String s1 = this.driverCustom.getText("//div[@class='header']/h1");
        if (s1.equals("Compare Configurations")) {
          return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_PAGE_AFTER_CLICKING_ON + subMenu +
              "' under Current Configuration.\nActual is the Compare Configurations page is displayed.\nExpected the Compare Configurations page should be displayed");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_PAGE_AFTER_CLICKING_ON + subMenu +
            "' under Current Configuration.\nActual is the Compare Configurations page is not displayed.\nExpected the Compare Configurations page should be displayed");

      case "Deliver Change Set":
        String s2 = this.driverCustom.getText("//div[@class='header']/h1");
        if (s2.equals("Deliver Change Sets")) {
          return new TestAcceptanceMessage(true, RMConstants.VERIFY_THE_PAGE_AFTER_CLICKING_ON + subMenu +
              "' under Current Configuration.\nActual is the Delivery change Sets page is displayed.\nExpected the Delivery change Sets page should be displayed");
        }
        return new TestAcceptanceMessage(false, RMConstants.VERIFY_THE_PAGE_AFTER_CLICKING_ON + subMenu +
            "' under Current Configuration.\nActual is the Delivery change Sets page is not displayed.\nExpected the Delivery change Sets page should be displayed");

      case "Discard Change Set...":
        try {
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle(RMConstants.DISCARD_CHANGE_SET_DIALOG),
              this.timeInSecs).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.DISCARD_CHANGE_SET_DIALOG + RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.DISCARD_CHANGE_SET_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.DISCARD_CHANGE_SET_DIALOG + "' dialog is not displayed.\nExpected is the '" +
              RMConstants.DISCARD_CHANGE_SET_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }

      case "Deliver Changes...":
        try {
          this.engine.findElement(Criteria.isDialog().withText("Select Target Configuration")).getFirstElement();
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.SELECT_SOURCE_CONFIGURATION_DIALOG + RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.SELECT_SOURCE_CONFIGURATION_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DIALOG_AFTER_CLICKING_ON + subMenu +
              RMConstants.UNDER_CURRENT_CONFIGURATION_MENU_ACTUAL_RESULT_IS_THE +
              RMConstants.SELECT_SOURCE_CONFIGURATION_DIALOG + RMConstants.DIALOG_IS_DISPLAYED_EXPECTED_IS_THE +
              RMConstants.SELECT_SOURCE_CONFIGURATION_DIALOG + RMConstants.DIALOG_SHOULD_BE_DISPLAYED);
        }
      default:
        return new TestAcceptanceMessage(false, "No match with" + subMenu);
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentsAndConfigurations#getCurrentConfiguration()}.
   *
   * @param lastResult get the last result of {@link RMManageComponentsAndConfigurations#getCurrentConfiguration()}}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyGetCurrentConfiguration(final String lastResult) {
    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(true, "Verified current configuration return is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified the string return is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentsAndConfigurations#createConfiguration(String, String)}.
   *
   * @param name of the baseline.
   * @param description of the baseline.
   * @param lastResult get the last result of
   *          {@link RMManageComponentsAndConfigurations#createConfiguration(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyCreateConfiguration(final String name, final String description,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.CREATE_CONFIGURATION_MESSAGE_SUCCESS_XPATH, Duration.ofSeconds(20))) {
      String getMessage = this.driverCustom.getText(RMConstants.CREATE_CONFIGURATION_MESSAGE_SUCCESS_XPATH);
      String s = "\"" + lastResult + "\" was created successfully.";
      if (getMessage.contains(s)) {
        return new TestAcceptanceMessage(true, "Verified status after create the configuration.\nActual result the '" +
            lastResult + "' is created successfully.\nExpected is the '" + lastResult + RMConstants.SHOULD_BE_CREATED);
      }
      return new TestAcceptanceMessage(false,
          "Verified status after create the configuration.\nActual result the '" + lastResult +
          "' is not created successfully.\nExpected is the '" + lastResult + RMConstants.SHOULD_BE_CREATED);
    }
    else if (!this.driverCustom.isElementVisible(RMConstants.CREATE_CONFIGURATION_MESSAGE_SUCCESS_XPATH, Duration.ofSeconds(180))) {
      String nameDisplayed = this.driverCustom
          .getPresenceOfWebElement(
              "//a[contains(@class,'configurationUiNode')]//span[@id='configurationTitleNode' or @class='titleNode']")
          .getText();
      if (nameDisplayed.trim().equals(name)) {
        return new TestAcceptanceMessage(true,
            "Verified the configuration is created successfully.\nActual result the '" + lastResult +
            "' is created successfully.\nExpected is the '" + lastResult + RMConstants.SHOULD_BE_CREATED);
      }
      // Cover for case: Create a new changeset
      waitForSecs(15);
      this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH,  Duration.ofSeconds(15));
      waitForSecs(15);
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH);
      LOGGER.LOG.info("Open Current Configuration context menu");
      waitForSecs(3);
      Button btnSwitch = this.engine.findElement(Criteria.isButton().withText("Switch")).getFirstElement();
      btnSwitch.click();
      LOGGER.LOG.info("Click on Switch button");
      waitForSecs(5);
      try {
        RadioButton radiobtnType = this.engine
            .findElement(Criteria.isRadioButton().withText("Requirements Management Configuration")).getFirstElement();
        radiobtnType.click();
      }
      catch (Exception e) {
        waitForSecs(2);
        this.driverCustom.getPresenceOfWebElement(
            "//label[@class='gc-ContextPicker-inlineLabel']/span[text()='Requirements Management Configuration']/preceding-sibling::input[1]")
        .click();
      }

      Dropdown drdStream = this.engine
          .findElementWithDuration(Criteria.isDropdown().withToolTip("Streams"), this.timeInSecs).getFirstElement();
      if (!drdStream.getText().equalsIgnoreCase("change sets")) {
        drdStream.selectOptionWithText("Change Sets");
      }

      Text txtSearch =
          this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)"))
          .getFirstElement();
      txtSearch.setText(name);

      waitForSecs(3);
      WebElement ele = this.driverCustom.getWebElement("//iframe[@title='Configuration Picker']");
      this.driverCustom.getWebDriver().switchTo().frame(ele);
      waitForSecs(2);
      if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, Duration.ofSeconds(20), name)) {
        this.driverCustom.switchToDefaultContent();
        this.driverCustom.click(RMConstants.RMMODULEPAGE_CLOSELOOKUPTERMDIALOGBUTTON_XPATH);
        waitForSecs(2);
        return new TestAcceptanceMessage(true,
            "Verified the configuration is created successfully.\nActual result the '" + lastResult +
            "' is created successfully.\nExpected is the '" + lastResult + RMConstants.SHOULD_BE_CREATED);
      }
      this.driverCustom.switchToDefaultContent();
      this.driverCustom.click(RMConstants.RMMODULEPAGE_CLOSELOOKUPTERMDIALOGBUTTON_XPATH);
      waitForSecs(2);
      return new TestAcceptanceMessage(false,
          "Verified the configuration is created successfully.\nActual result is the configuration is not created as expectation.\nExpected is the '" +
              lastResult + RMConstants.SHOULD_BE_CREATED);
    }
    return new TestAcceptanceMessage(false,
        "Verified status after create the configuration.\nActual result is no message is displayed.\nExpected is the '" +
            lastResult + RMConstants.SHOULD_BE_CREATED);
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentsAndConfigurations#archiveConfiguration(String)}.
   *
   * @param configName NAME
   * @param lastResult get the last result of {@link RMManageComponentsAndConfigurations#archiveConfiguration(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyArchiveConfiguration(final String configName, final String lastResult) {
    try {
      this.engine.findElement(Criteria.isLink().withText(configName)).getFirstElement();
      return new TestAcceptanceMessage(false, "Verified the configuration after archive.\nActual result is '" +
          configName + "' archived.\nExpected is '" + configName + "' should be archived");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(true, "Verified the configuration after archive.\nActual is the '" + configName +
          "' is archived.\nExpected is the '" + configName + "' should be archived");
    }
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentsAndConfigurations#isComponentOrConfigurationLinkDisplayed(String)}.
   *
   * @param componentName name of the component.
   * @param lastResult get the last result of
   *          {@link RMManageComponentsAndConfigurations#isComponentOrConfigurationLinkDisplayed(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsComponentOrConfigurationLinkDisplayed(final String componentName,
      final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED_THE + componentName + "' link is displayed or not.\nActual is displayed.");
    }
    return new TestAcceptanceMessage(false,
        RMConstants.VERIFIED_THE + componentName + "' link is displayed or not.\nActual is not displayed.");
  }

  /**
   * <p>
   * Verifies the action of
   * {@link RMManageComponentsAndConfigurations#createComponentFromAdministrations(String, String, String)}.
   *
   * @param componentName name of the component.
   * @param componentDescription name of the component description.
   * @param componentTemplate name of the component template.
   * @param lastResult get the last result of
   *          {@link RMManageComponentsAndConfigurations#createComponentFromAdministrations(String, String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyCreateComponent(final String componentName, final String componentDescription,
      final String componentTemplate, final String lastResult) {
    // Update:
    // this.driverCustom.getPresenceOfWebElement("//span[text()='" + lastResult + " Initial Stream']");
    waitForPageLoaded();
    waitForSecs(10);
    this.driverCustom.isElementPresent("//span[text()='" + lastResult + " Initial Stream']", Duration.ofSeconds(180));
    try {
      this.engine.findElement(Criteria.isLink().withText(lastResult + " Initial Stream")).getFirstElement();
      return new TestAcceptanceMessage(true, "Verified the '" + lastResult +
          "' component after creating.\nActual the '" + lastResult + "' is created successfully as expected");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verified the '" + lastResult +
          "' component after creating.\nActual the '" + lastResult + "' is not created successfully as expected");
    }
  }

  /**
   * <p>
   * Verifies the action of
   * {@link RMManageComponentsAndConfigurations#clickOnContextMenuForAConfigurations(String, String)}.
   *
   * @param configName name of the configuration.
   * @param subMenu name of the sub menu.
   * @param lastResult get the last result of
   *          {@link RMManageComponentsAndConfigurations#clickOnContextMenuForAConfigurations(String, String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyClickOnContextMenuForAConfigurations(final String configName, final String subMenu,
      final String lastResult) {
    switch (subMenu) {
      case RMConstants.ARCHIVE:
        try {
          this.engine.findElement(Criteria.isButton().withText("Archive the configuration and all children"));
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              RMConstants.ACTUAL_IS_THE_ARCHIVE_WARNING_DIALOG_IS_DISPLAYED);
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              "'.\nActual is not the Archive Warning dialog is not displayed");
        }
      case RMConstants.CREATE_STREAM:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_STREAM));
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              "'.\nActual is the Create Stream dialog is displayed");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              "'.\nActual is not the Create Stream dialog is not displayed");
        }
      case RMConstants.CREATE_BASELINE_DIALOG:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle(RMConstants.CREATE_BASELINE_DIALOG));
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              "'.\nActual is the Create Baseline dialog is displayed");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              "'.\nActual is not the Create Baseline dialog is not displayed");
        }
      case RMConstants.STREAMS:
        try {
          this.engine.findElement(Criteria.isDialog().withTitle("Rename Baseline"));
          return new TestAcceptanceMessage(true,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              "'.\nActual is the Rename Baseline dialog is displayed");
        }
        catch (Exception e) {
          return new TestAcceptanceMessage(false,
              RMConstants.VERIFY_THE_DISPLAYED_DIALOG_AFTER_CLICK_ON + subMenu +
              RMConstants.MENU_FROM_ACTIONS_DROPDOWN_MENU_OF + configName +
              "'.\nActual is not the Rename Baseline dialog is not displayed");
        }
      default:
        return new TestAcceptanceMessage(false, "The '" + subMenu + "' menu does not match.");
    }
  }
  
  /**
   * <p>
   * Verify Different mdoule name and same view artifacttype are added to the reqif definition<p>
   * 
   * @param lastResult result from ${@link RMManageComponentPropertiesPage#checkModuleNamesInReqIFDefinition(String, String, String, String)}
   *    * @author KCE1KOR
   * @param moduleNameOne of "ReqIF Definition"
   * @param moudleNameTwo of "ReqIF Definition"
   * @param viewName name of selecte view
   * @param artifactType of modules 
   * @return verify the module name 
   */
  public TestAcceptanceMessage verifyCheckModuleNamesInReqIFDefinition(final String moduleNameOne,final String moudleNameTwo,final String artifactType, final String viewName, final String lastResult) {
    String moduleViewNameOne=moduleNameOne + " (View: "  + viewName +")";
    String moduleViewNameTwo=moudleNameTwo + " (View: "  + viewName +")";
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified:.\nActual Different module names "+ moduleViewNameOne + " and " + moduleViewNameTwo + " but the same Artifact Type '"+ artifactType +"' in the ReqIF definition are matched with ---->Expected results: Different module names "+ moduleViewNameOne + " and " + moduleViewNameTwo + " but the same Artifact Type '"+ artifactType + "' in the ReqIF definition successfully");
    }
    return new TestAcceptanceMessage(false, "Verified:.\nActual Different module names "+ moduleViewNameOne + " and " + moduleViewNameTwo + " but the same Artifact Type '"+ artifactType +"' in the ReqIF definition are 'Not' matched with ---->Expected results: Different module names "+ moduleViewNameOne + " and " + moduleViewNameTwo + " but the same Artifact Type '"+ artifactType + "' in the ReqIF definition successfully");
  }

  /**
   * <p>
   * Verifies the action of {@link RMManageComponentsAndConfigurations#isErrorMessageDisplayed(String)}.
   *
   * @param errorMessage error message summary
   * @param lastResult get the last result of
   *          {@link RMManageComponentsAndConfigurations#isErrorMessageDisplayed(String)}
   * @return the verification message
   */
  public TestAcceptanceMessage verifyIsErrorMessageDisplayed(final String errorMessage, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          RMConstants.VERIFIED_THE + errorMessage + "'is displayed or not.\nActual is displayed.");
    }
    return new TestAcceptanceMessage(false,
        RMConstants.VERIFIED_THE + errorMessage + "' is displayed or not.\nActual is not displayed.");
  }

  /**
   * <p>
   * Verify back to Browse Component page successfully after
   * ${@link RMManageComponentsAndConfigurations#backToBrowseComponentPage()}
   * <p>
   * 
   * @author NVV1HC
   * @param lastResult result from ${@link RMManageComponentsAndConfigurations#backToBrowseComponentPage()}
   * @return true if back to Browse Component page successfully or vice versa
   */
  public TestAcceptanceMessage verifyBackToBrowseComponentPage(final String lastResult) {
    if (this.driverCustom.isElementVisible("//div[@dojoattachpoint='_pageTitleNode' and text()='Browse Components']",
        Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true, "Verify back to the 'Browse Components' page successufully!");
    }
    return new TestAcceptanceMessage(false, "Verify back to the 'Browse Components' page failed!");
  }

  /**
   * <p>
   * Verify search and explore to the component successfully after
   * ${@link RMManageComponentsAndConfigurations#searchAndExploreComponent(String)}
   * <p>
   * 
   * @author NVV1HC
   * @param componentName name of component to be searched and explored
   * @param lastResult result from ${@link RMManageComponentsAndConfigurations#searchAndExploreComponent(String)}
   * @return true if navigate to the component successfully or vice versa
   */
  public TestAcceptanceMessage verifySearchAndExploreComponent(final String componentName, final String lastResult) {
    if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_COMPONENT_NAME,  Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)),
        componentName)) {
      return new TestAcceptanceMessage(true, "Verify search and explore the component successfully: PASSED!");
    }
    return new TestAcceptanceMessage(false, "Verify search and explore the component successfully: FAILED!");
  }

  /**
   * <p>
   * Verify search the component in Browse Component successfully after
   * ${@link RMManageComponentsAndConfigurations#searchComponentInBrowseComponentPage(String)}
   * <p>
   * 
   * @author KYY1HC
   * @param componentName name of component to be searched
   * @param lastResult result from ${@link RMManageComponentsAndConfigurations#searchComponentInBrowseComponentPage(String)}
   * @return true if found the component successfully, otherwise return false.
   */
  public TestAcceptanceMessage verifySearchComponentInBrowseComponentPage(final String componentName, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Component '" + componentName + "' is found in Browse Component page.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Component '" + componentName + "' is not found in Browse Component page.");
  }

  /**
   * <p>
   * Verify select the first archived component in Browse Component successfully<p>
   * 
   * @author KYY1HC
   * @param lastResult result from ${@link RMManageComponentsAndConfigurations#selectTheFirstArchivedComponentInBrowseComponentPage()}
   * @return true if found the component successfully, otherwise return false.
   */
  public TestAcceptanceMessage verifySelectTheFirstArchivedComponentInBrowseComponentPage(final String lastResult) {
    WebElement componentName = this.driverCustom.getWebElement("//input[@placeholder='Component Name']");
    if (componentName.getText().equalsIgnoreCase(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Component '" + componentName + "' is selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Component '" + componentName + "' is not selected in Browse Component page.");
  }

  /**
   * <p>
   * Verify restore archived component in Browse Component successfully<p>
   * 
   * @author KYY1HC
   * @param lastResult result from ${@link RMManageComponentsAndConfigurations#restoreArchivedComponent()}
   * @return true if found the component successfully, otherwise return false.
   */
  public TestAcceptanceMessage verifyRestoreArchivedComponent(final String lastResult) {
    if (this.driverCustom.isElementVisible("//img[contains(@class,'componentLargeIcon')]", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Restore archived component successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Restore archived component is not successfully.");
  }

  /**
   * <p>
   * Verify archive active component in Browse Component successfully<p>
   * 
   * @author KYY1HC
   * @param lastResult result from ${@link RMManageComponentsAndConfigurations#archiveActiveComponent()}
   * @return true if found the component successfully, otherwise return false.
   */
  public TestAcceptanceMessage verifyArchiveActiveComponent(final String lastResult) {
    if (this.driverCustom.isElementVisible("//img[contains(@class,'componentArchivedLargeIcon')]", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Archive active component successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Archive active component is not successfully.");
  }

  /**
   * @param componenetName
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyArchiveCreatedNewComponent(String componenetName,final String lastResult) {
    if (this.driverCustom.isElementVisible("//img[contains(@class,'componentArchivedLargeIcon')]", Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "Verified: PASSED -' Archive active component successfully.\nArchive component is : "+componenetName);
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Archive active component is not successfully.");
  }

  /**
   * <p>
   * Verify explore active component in Browse Component successfully<p>
   * 
   * @author KYY1HC
   * @param componentName name of component to explore
   * @param lastResult result from ${@link RMManageComponentsAndConfigurations#exploreActiveComponent(String)}
   * @return true if found the component successfully, otherwise return false.
   */
  public TestAcceptanceMessage verifyExploreActiveComponent(final String componentName, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Active component '" + componentName + "' is explored successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Active component '" + componentName + "' is not explored successfully.");
  }
}
