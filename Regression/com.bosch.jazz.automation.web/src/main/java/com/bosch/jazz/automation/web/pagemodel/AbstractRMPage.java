/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.element.text.TextField;

/**
 * <p>
 * Represents abstraction of methods to requirement management pages. <br>
 * Acts as super class to all Requirement management pages. <br>
 * Declare methods which are common to requirement management pages., declare abstract methods which are to be
 * overridden by every other pages of requirement management pages..
 *
 * @author HRT5KOR
 */
public class AbstractRMPage extends AbstractJazzWebPage {

  private String config = "Streams";

  /**
   * Constructor setting the object of {@link WebDriverCustom} class.
   *
   * @param driverCustom required for interacting with the browser.
   */
  public AbstractRMPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
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
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
  }

  /**
   * <p>
   * Login in to alm application of rational requirements management "https://<local- host:8080>/rm" using
   * {@link JazzLoginPage#loginWithGivenPassword(String, String)}. <br>
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the
   * list.{@link #selectProjectArea(String)}. <br>
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
   * @param projectAreaName name of the project area.
   * @param gcNaMe name of global configuration.
   * @param compOnentName name of the component.
   * @param streaMName name of the stream.
   */
  public void selectGlobalConfiguration(final String projectAreaName, final String gcNaMe, final String compOnentName,
      final String streaMName) {
    if (gcNaMe.equalsIgnoreCase("null") || compOnentName.equalsIgnoreCase("null") ||
        streaMName.equalsIgnoreCase("null")) {
      return;
    }
    if (!this.driverCustom.getText("//span[contains(@class,'component-menu')]").equals(compOnentName)) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH);
      if (this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5), "Component Setup")) {
        clickOnJazzButtons("Close");
      }
      for (int i = 0; i < 6; i++) {
        if (!this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10),
            RMConstants.CHOOSE_ANOTHER_COMPONENT)) {
          this.driverCustom.isElementClickable(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH, Duration.ofSeconds(10));
          try {
            Link currentProjectlink =
                this.engine.findFirstElement(Criteria.isLink().withToolTip("Current Project Component"));
            currentProjectlink.click();
          }
          catch (Exception e) {
            Optional<WebElement> confi =
                this.driverCustom.getWebElements("//a[starts-with(@id, 'jazz_ui_MenuPopup')]").stream()
                .filter(x -> x.getAttribute("aria-label").equalsIgnoreCase("Current Configuration Drop-Down Menu"))
                .findFirst();
            if (!confi.isPresent()) {
              throw new WebAutomationException(
                  "Current component set up menu not found \nOr\nConfiguration management not enabled.");
            }
            confi.get().click();

          }
        }
        else {
          break;
        }
      }
      this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, RMConstants.CHOOSE_ANOTHER_COMPONENT);
      this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH, Duration.ofSeconds(5));
      for (int i = 0; i < 6; i++) {
        if (!this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH, Duration.ofSeconds(5),
            projectAreaName)) {
          this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH);
        }
        else {
          break;
        }
      }
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH, projectAreaName);
      this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SEARCHCOMP_XPATH, Duration.ofSeconds(5));
      Dialog dialog = this.engine.findElement(Criteria.isDialog().withTitle("Choose Component")).getFirstElement();
      TextField txt = this.engine.findFirstElement(
          Criteria.isTextField().inContainer(dialog).withPlaceHolder("Type to search names (enter * to show all)"));
      txt.setText(compOnentName.trim());
      if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, Duration.ofSeconds(10),
          compOnentName.trim())) {
        this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, compOnentName.trim());
        clickOnJazzButtons("OK");
        this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTTYPE_LINKTEXT_LINKTEXT, Duration.ofSeconds(20), "All");
      }
      else {
        clickOnJazzButtons("Cancel");
      }
      waitForSecs(Duration.ofSeconds(5));
    }

    // Since after selecting the component, page will get blink, after that if we click other config menu
    // blink closes the menu , to avoid this wait for seconds added.So that after some wait blink doen't occur.
    String currentConfig;
    if (this.driverCustom.isElementVisible(RMConstants.RMCURRENT_CONFIGURATION_XPATH, Duration.ofSeconds(10))) {
      currentConfig = this.driverCustom.getText(RMConstants.RMCURRENT_CONFIGURATION_XPATH);
    }
    else {
      currentConfig = this.driverCustom.getText("//img/following-sibling::span[@class='titleNode']");
    }
    if (!currentConfig.equals(streaMName)) {
      this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH,  Duration.ofSeconds(10));
      this.driverCustom.getFirstVisibleWebElement(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, null).click();
      this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(5), RMConstants.SWITCH);
      Button btnSwitch = this.engine.findElement(Criteria.isButton().withText(RMConstants.SWITCH)).getFirstElement();
      btnSwitch.click();
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SWITCHTOGC_CONFIGURATION_XPATH);

      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH, Duration.ofSeconds(5));
      this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH,  Duration.ofSeconds(10));
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);

      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, Duration.ofSeconds(10), RMConstants.STREAMS);
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, RMConstants.STREAMS);
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH);
      this.driverCustom.typeText(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH, streaMName);
      for (int j = 0; j < 6; j++) {
        if (this.driverCustom.isElementVisible(RMConstants.RMPROJECT_DISABLE_BUTTON_XPATH, Duration.ofSeconds(6), "OK")) {
          this.driverCustom.click(RMConstants.RMARTIFACTPAGE_BASELINESEARCH_FILTER_XPATH);
        }
        else {
          break;
        }
      }
      this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(6), "OK");
      if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(6), "OK")) {
        clickOnJazzButtons("OK");
        this.driverCustom.switchToParentFrame();
      }
      this.driverCustom.switchToParentFrame();
    }
  }


  /**
   * <p>
   * Login in to alm application of rational requirements management "https://<local- host:8080>/rm" using
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
   * @param proJectAreaName name of the project area to be opened.
   * @param gcName name of the configuration.
   * @param componentName name of the component.
   * @param streamName name of the stream.
   */
  @Override
  public void selectProjectAreaAndGlobalConfiguration(final String proJectAreaName, final String gcName,
      final String componentName, final String streamName) {
    this.driverCustom.clickOnLink(proJectAreaName, false);
    LOGGER.LOG.info(proJectAreaName + "project area opened successfully.");
    if (this.driverCustom.isElementVisible("//div[@class='jazz-ui-Dialog-close-button-icon']", Duration.ofSeconds(15))) {
      this.driverCustom.getFirstVisibleWebElement("//div[@class='jazz-ui-Dialog-close-button-icon']", null).click();
    }
    if (gcName.equalsIgnoreCase("null") || componentName.equalsIgnoreCase("null") ||
        streamName.equalsIgnoreCase("null")) {
      return;
    }

    String xpathComponentName = "//span[contains(@class,'component-menu')]";
    if (!this.driverCustom.getText(xpathComponentName).equals(componentName)) {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH);
      int breakPoint = 5;
      while (breakPoint > 0) {
        if (!this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(5), "Component Setup")) {
          break;
        }
        clickOnJazzButtons("Close");
        breakPoint--;
      }

      String xpathCurrentConfiguration = "//a[contains(@title,'Current Project Component')]";
      this.driverCustom.isElementPresent(xpathCurrentConfiguration, Duration.ofSeconds(60));
      this.driverCustom.isElementPresent("//*[@title='Current Configuration']", Duration.ofSeconds(30));
      if (!this.driverCustom.getWebElement(xpathCurrentConfiguration).getText().trim()
          .equals(componentName.trim())) {
        for (int i = 0; i < 6; i++) {
          if (!this.driverCustom.isElementVisible(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, Duration.ofSeconds(10),
              "Choose Another Component...")) {
            this.driverCustom.isElementClickable(RMConstants.RMDASHBOARDPAGE_SETTINGS_CURRENTCONFIGCOMP_XPATH, Duration.ofSeconds(20));
            try {
              this.driverCustom.getPresenceOfWebElement(xpathCurrentConfiguration).click();
            }
            catch (Exception e) {
              Optional<WebElement> configDropdownMenu =
                  this.driverCustom.getWebElements("//a[starts-with(@id, 'jazz_ui_MenuPopup')]").stream()
                  .filter(
                      x -> x.getAttribute("aria-label").equalsIgnoreCase("Current Configuration Drop-Down Menu"))
                  .findFirst();
              if (!configDropdownMenu.isPresent()) {
                throw new WebAutomationException(
                    "Current component set up menu not found \nOr\nConfiguration management not enabled.");
              }
              configDropdownMenu.get().click();
            }
          }
          else {
            break;
          }
        }

        this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, RMConstants.CHOOSE_ANOTHER_COMPONENT);
        try {
          waitForSecs(2);
          if(this.driverCustom.isElementVisible("//div[@role='dialog']//input[@type='checkbox']/../..//div[contains(@class,'dijitCheckBoxChecked ')]", Duration.ofSeconds(5)))
          this.driverCustom.click("//div[@role='dialog']//input[@type='checkbox']");
          waitForSecs(2);
          LOGGER.LOG.info( "check box 'Only show components in current global configuration' not selected");
        }catch (Exception e) {
          // TODO: handle exception
          LOGGER.LOG.info( "check box 'Only show components in current global configuration' selected");
          
        }
        this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH, Duration.ofSeconds(20));
        for (int i = 0; i < 6; i++) {
          if (!this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH, Duration.ofSeconds(20),
              proJectAreaName)) {
            this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTPA_XPATH);
          }
          else {
            break;
          }
        }
        this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH, proJectAreaName);
        this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SEARCHCOMP_XPATH, Duration.ofSeconds(20));
        this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_SETTINGS_SEARCHCOMP_XPATH)
        .sendKeys(componentName.trim());
        if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, Duration.ofSeconds(30),
            componentName.trim())) {
          this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_SELECTCOMP_XPATH, componentName.trim());
          clickOnJazzButtons("OK");
          this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_ARTIFACTTYPE_LINKTEXT_LINKTEXT, Duration.ofSeconds(20), "All");
        }
        else {
          throw new WebAutomationException(componentName + " component name not found or not selected to add.");
        }
      }
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(20));
    String xpathIconGlobalConfiguration = "//a[@class='configurationUiNode hideLink']//img[contains(@class, 'globalConfigurationNode')]";
    String xpathStreamText = "//*[@title='Current Configuration']//*[@class='titleNode'][text()='DYNAMIC_VALUE']";
    this.driverCustom.waitForSecs(Duration.ofSeconds(5)); 
    if (!(this.driverCustom.isElementPresent(xpathIconGlobalConfiguration, Duration.ofSeconds(15)) && this.driverCustom.isElementVisible(xpathStreamText, Duration.ofSeconds(15), streamName))) {
      WebElement currentConfiguration = this.driverCustom.getPresenceOfWebElement("//a[@aria-label='Current Configuration Drop-Down Menu']");
      currentConfiguration.click(); 
      this.driverCustom.waitForSecs(Duration.ofSeconds(5)); 
      Button btnSwitch = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Switch"), Duration.ofSeconds(60));
      btnSwitch.click();
      this.driverCustom.waitForSecs(Duration.ofSeconds(5)); 
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SWITCHTOGC_CONFIGURATION_XPATH);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH, Duration.ofSeconds(20));
      this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH, Duration.ofSeconds(20));
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, Duration.ofSeconds(20), RMConstants.STREAMS);
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, this.config);
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH);
      this.driverCustom.waitForSecs(Duration.ofSeconds(5)); 
      
      this.driverCustom.typeText(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH, streamName);
      this.driverCustom.getPresenceOfWebElement("//span[@class='gc-ScrollableTable-focusedTitle']");
      if (!this.driverCustom.isElementVisible("//span[@class='gc-ScrollableTable-focusedTitle']",Duration.ofSeconds(10))) {
        String javScriptCode = "arguments[0].scrollIntoView(true)";
        while (!this.driverCustom.isElementVisible("//span[@class='gc-ScrollableTable-focusedTitle']",Duration.ofSeconds(10)) ||
            this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, "Next").isEnabled()) {
          if (this.driverCustom.isElementVisible("//span[@class='gc-ScrollableTable-focusedTitle']",Duration.ofSeconds(10))) {
            WebElement eleme =
                this.driverCustom.getPresenceOfWebElement("//span[@class='gc-ScrollableTable-focusedTitle']");
            JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
            je.executeScript(javScriptCode, eleme);
            break;
          }
          this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, "Next").click();
        }
      }
      try {
        this.driverCustom.switchToDefaultContent();
        this.waitForSecs(1);
        this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
        this.waitForSecs(1);
        // Select the stream
        WebElement row = this.driverCustom.getPresenceOfWebElement("//span[@class='gc-ScrollableTable-focusedTitle']//img[@alt='Stream']");
        row.click();
      }
      catch (Exception e) {
        throw new InvalidArgumentException(streamName + " - could not be found. ");
      }

      // Click on OK button
      try {
        Button btnOKToSelectStream = this.engine.findElementWithinDuration(Criteria.isButton().withText("OK"), timeInSecs).getFirstElement();
        btnOKToSelectStream.click();
      }
      catch (Exception ex) {
        // Clicked on OK
      }
      this.driverCustom.switchToDefaultContent();
    }
  }

  /**
   * @param proJectAreaName -
   * @param gcName -
   * @param componentName -
   * @param streamName -
   * @param StreamType -
   */
  public void selectProjectAreaAndGlobalConfiguration(final String proJectAreaName, final String gcName,
      final String componentName, final String streamName, final String StreamType) {
    this.config = StreamType;
    selectProjectAreaAndGlobalConfiguration(proJectAreaName, gcName, componentName, streamName);
  }

  /**
   * <p>
   * This method used to ccps project to select personal streams option for gc project
   *
   * @param proJectAreaName project name
   * @param gcName gc name
   * @param componentName component Name
   * @param streamName stream Name
   */

  /**
   * <p>
   * Opens the requirement management specifications like artifacts , modules and collection from the list of items
   * displayed in the table using id.
   *
   * @param id identity number of the artifact, module or collection.
   */
  public void openRMSpecification(final String id) {
    try {
      Row row = this.engine.findElementWithDuration(Criteria.isRow().withText(id), timeInSecs).getFirstElement();
      LOGGER.LOG.info(id + " - displayed in the artifacts table.");
      Link link = this.engine.findFirstElement(Criteria.isLink().withText(id).inContainer(row));
      link.click();
      LOGGER.LOG.info("Clicked on " + id + " displayed in the table.");
      waitForSecs(Duration.ofSeconds(5));
    }
    catch (Exception e) {
      try {
        this.driverCustom
        .getWebElement(
            "//div[@class='results-area']//a[@class='jazz-ui-ResourceLink']//span[contains(text(),'" + id + ":')]")
        .click();
      }
      catch (Exception ex) {
        throw new WebAutomationException(
            id + ": searched artifact not found,Please check the artifact id or name provided.\n" + " or\n" +
                ex.getMessage());
      }
    }
  }

  /**
   * <p>
   * Open Requirement management specifications like artifacts , modules , collections using
   * {@link #openRMSpecification(String)}.
   * <p>
   * Requirement management specifications like artifacts , modules , collections and views has some attributes like
   * Description,Component,Team Ownership,Created On, Created By and etc.
   *
   * @param attributeName name of the requirement management attributes.
   * @return value of the attribute.
   */
  public String getRMAttributeValue(final String attributeName) {
    waitForSecs(10);
    LOGGER.LOG.info("10 Seconds waiting to load Artifact Overview.");
    List<WebElement> attributeList = this.driverCustom.getWebElements("//div[@class = 'attribute-groups']/div");
    Function<WebElement, String> pred = e -> e.findElement(By.xpath(".//label")).getText();
    List<String> attrList = attributeList.stream().map(pred).collect(Collectors.toList());
    LOGGER.LOG.info(attrList + " - Attributes displayed in Overview section of the Artifacts.");
    Map<String, String> map = attrList.stream().map(s -> s.split(":", 2))
        .collect(Collectors.toMap(x -> x[0], x -> x.length > 1 ? x[1].trim() : "", (a1, a2) -> a1));
    if (map.get(attributeName) == null) {
      throw new InvalidArgumentException(
          attributeName + " does not exist in the map, please check the attribute name.");
    }
    return map.get(attributeName);
  }

  /**
   * <p>
   * Open Requirement management specifications like artifacts , modules , collections using
   * {@link #openRMSpecification(String)}.
   * <p>
   * Requirement management specifications like artifacts , modules , collections and views has some attributes like
   * Description,Component,Team Ownership,Created On, Created By and etc.
   *
   * @return value of the attribute.
   */
  public String getRMAttributes() {
    waitForSecs(110);
    List<WebElement> attributeList = this.driverCustom.getWebElements("//div[@class = 'attribute-groups']/div");
    Function<WebElement, String> pred = e -> e.findElement(By.xpath(".//label")).getText();
    List<String> attrList = attributeList.stream().map(pred).collect(Collectors.toList());
    LOGGER.LOG.info(attrList + " - attributes displayed in Overview section of the Artifacts.");
    Map<String, String> map = attrList.stream().map(s -> s.split(":", 2))
        .collect(Collectors.toMap(x -> x[0], x -> x.length > 1 ? x[1].trim() : "", (a1, a2) -> a1));
    String str = "";

    for (String key : map.keySet()) {
      str = str + "/n" + "KEY :" + key + " VALUE :" + map.get(key);
    }
    return str;
  }

  /**
   * <p>
   * Open Requirement management specifications like artifacts , modules , collections using
   * {@link #openRMSpecification(String)}. <br>
   * Left side there will be details present of any requirement management specification. Finds the document-name from
   * existing artifacts, modules or collections.
   *
   * @return summary of the requirement management specifications.
   */
  public String getRMSpecificationsSummary() {
    waitForSecs(115);
    this.driverCustom.isElementVisible(
        "//div[@class = 'artifact-details']//div[@class = 'document-title-line with-image']/span", Duration.ofSeconds(10));
    List<WebElement> summaryList = this.driverCustom
        .getWebElements("//div[@class = 'artifact-details']//div[@class = 'document-title-line with-image']/span");
    Optional<WebElement> matchingOptional =
        summaryList.stream().filter(x -> x.getAttribute("class").contains("document-name")).findFirst();
    if (matchingOptional.isPresent()) {
      LOGGER.LOG.info(matchingOptional.get().getText() + " is displayed in overview section of the artifact.");
      return matchingOptional.get().getText();
    }
    throw new WebAutomationException("document-name/summary of the requirements specifications is not found.");
  }

  /**
   * <p>
   * Search any requirements specification by name or id using quick search test box present at top right side of the
   * page.{@link #quickSearch}
   *
   * @return true if the search results not found.
   */
  @SuppressWarnings("javadoc")
  public boolean isSeachResultsNotFound() {
    LOGGER.LOG.info("Searching for 'No results found for' message as searched artifact is not available.");
    List<WebElement> searchResults = this.driverCustom.getWebElements("//div[@class = 'results-area-wrapper']");
    Optional<WebElement> matchingOptional =
        searchResults.stream().filter(x -> x.getText().startsWith("No results found for '")).findFirst();
    return matchingOptional.isPresent();
  }

  /**
   * <p>
   * Login in to alm application of rational requirements management "https://<local- host:8080>/rm" using
   * {@link JazzLoginPage#loginWithGivenPassword(String, String)}. <br>
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the
   * list.{@link #selectProject(String)}. <br>
   * Click on "Switch" button and select "Global Configuration" tab present on the "Select the Configuration Context"
   * dialog. <br>
   * Select the project area from the project area drop down. <br>
   * Search the stream name from the search text field present in "Select the Configuration Context" dialog. <br>
   * Select the stream if found in the Select the Configuration Context" dialog.Click on "Ok" button.
   *
   * @param additionalParams holds keys like "type", "Streams", "streamName".
   * @return true if it switched to Configurations.
   */
  public boolean selectTheConfigContext(final Map<String, String> additionalParams) {
    waitForPageLoaded();
    if (additionalParams.containsKey("type")) {
      this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(30));
      this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH);
      this.driverCustom.isElementVisible("//button[text()='Switch']", Duration.ofSeconds(10));
      Button btnSwitch =
          this.engine.findElement(Criteria.isButton().withText(additionalParams.get("type"))).getFirstElement();
      btnSwitch.click();
      if (additionalParams.containsValue("Global Configuration")) {
        this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SWITCHTOGC_CONFIGURATION_XPATH);
      }
      else if (additionalParams.containsValue("Requirements Management Configuration")) {
        this.driverCustom.click(RMConstants.JAZZ_SPANSELECTION_XPATH, "Requirements Management Configuration");
      }
    }
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH, Duration.ofSeconds(5));
    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH, Duration.ofSeconds(10));
    this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAM_DROPDOWN_XPATH);
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH, Duration.ofSeconds(10),
        additionalParams.get(RMConstants.CONTEXT_DROPDOWN));
    this.driverCustom.click(RMConstants.RMARTIFACTPAGE_STREAMS_DROPDOWN_XPATH,
        additionalParams.get(RMConstants.CONTEXT_DROPDOWN));
    waitForSecs(Duration.ofSeconds(5));
    if (additionalParams.containsValue("Global Configuration")) {
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH);
      this.driverCustom.typeText(RMConstants.RMARTIFACTPAGE_GCCOMPONENTSEARCH_FILTER_XPATH,
          additionalParams.get(RMConstants.STREAM_NAME));
      waitForSecs(Duration.ofSeconds(5));
      if (this.driverCustom.isElementVisible("//span[@class='gc-ScrollableTable-focusedTitle']", Duration.ofSeconds(20))) {
        WebElement elem = this.driverCustom.getWebElement("//span[@class='gc-ScrollableTable-focusedTitle']");
        JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
        je.executeScript(RMConstants.SCROLL_INTO_VIEW, elem);
      }
      else {
        while (!this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_GC_ROW_XPATH, Duration.ofSeconds(120),
            additionalParams.get(RMConstants.STREAM_NAME)) ||
            this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, "Next").isEnabled()) {
          if (this.driverCustom.isElementVisible(RMConstants.RMARTIFACTSPAGE_GC_ROW_XPATH, Duration.ofSeconds(120),
              additionalParams.get(RMConstants.STREAM_NAME))) {
            WebElement eleme = this.driverCustom.getWebElement(RMConstants.RMARTIFACTSPAGE_GC_ROW_XPATH,
                additionalParams.get(RMConstants.STREAM_NAME));
            JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
            je.executeScript(RMConstants.SCROLL_INTO_VIEW, eleme);
            break;
          }
          this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, "Next").click();
        }
      }
      try {
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMARTIFACTSPAGE_GC_ROW_XPATH,
            additionalParams.get(RMConstants.STREAM_NAME)).click();
        LOGGER.LOG.info("Select " + additionalParams.get(RMConstants.STREAM_NAME));
      }
      catch (Exception e) {
        throw new InvalidArgumentException(additionalParams.get("streamName") + " - could not be found.");
      }
    }
    else {
      this.driverCustom.isElementVisible(RMConstants.RMARTIFACTPAGE_COMPONENTSEARCH_FILTER_XPATH, Duration.ofSeconds(5));
      this.driverCustom.click(RMConstants.RMARTIFACTPAGE_COMPONENTSEARCH_FILTER_XPATH);
      this.driverCustom.typeText(RMConstants.RMARTIFACTPAGE_COMPONENTSEARCH_FILTER_XPATH,
          additionalParams.get("streamName"));
    }
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), "OK");
    if (this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), "OK")) {
      clickOnJazzButtons("OK");
      this.driverCustom.getWebDriver().switchTo().defaultContent();
      return true;
    }
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    return false;
  }

  /**
   * <p>
   * Login in to alm application of rational requirements management "https://<local- host:8080>/rm" using
   * {@link JazzLoginPage#loginWithGivenPassword(String, String)}. <br>
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the
   * list.{@link #selectProject(String)}. <br>
   * Click on "Switch" button and select "Global Configuration" tab present on the "Select the Configuration Context"
   * dialog. <br>
   * Select the project area from the project area drop down. <br>
   * Search the stream name from the search text field present in "Select the Configuration Context" dialog. <br>
   * Select the stream if found in the Select the Configuration Context" dialog.Click on "Ok" button.
   *
   * @param configManagementType is "Global Configuration" or "Requirements Management Configuration"
   * @param configType is Streams, Baselines or Change Sets
   * @param configName is name of stream, baseline or change set
   */
  public void selectConfigContext(final String configManagementType, final String configType, final String configName) {
    WebElement btnCurrentStream = this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH);
    // DONOT EDIT THIS CODE - Click the small button next to current stream label if Current Stream popup does not show
    if (!this.driverCustom.isElementVisible("//div[@class='dijitPopup jazz-ui-MegaMenuPopup' and contains(@style,'visibility: visible')]", Duration.ofSeconds(5))) {
      btnCurrentStream.click();
      LOGGER.LOG.info("Open Current Configuration context menu");
    }
    boolean isOnlyLocalConfig = false;
    if (this.driverCustom.isElementVisible("//span[text()='Switch Configuration...']", Duration.ofSeconds(30))) {
      Optional<WebElement> opt =
          this.driverCustom.getWebElements("//span[text()='Switch Configuration...']").stream().findFirst();
      if (opt.isPresent()) {
        opt.get().click();
      }
      else {
        throw new WebAutomationException("Configuration not found");
      }
      isOnlyLocalConfig = true;
    }
    else {
      Button btnSwitch = this.engine.findElement(Criteria.isButton().withText("Switch")).getFirstElement();
      btnSwitch.click();
      LOGGER.LOG.info("Click on Switch button");
      waitForSecs(Duration.ofSeconds(5));
    }
    waitForSecs(10);
    LOGGER.LOG.info("The Configuration Context dialog is displayed");
    if (!isOnlyLocalConfig) {
      waitForSecs(Duration.ofSeconds(5));
      if (configManagementType.contains("Global Configuration")) {
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SWITCHTOGC_CONFIGURATION_XPATH).click();
      }
      else {
        this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_SWITCH_TO_GC_CONFIGURATION_SC_XPATH).click();
      }
      LOGGER.LOG.info("Select configuration management is " + configManagementType);
      waitForSecs(Duration.ofSeconds(5));
    }
    try {
    Dropdown drdStream = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip("Streams"), this.timeInSecs).getFirstElement();
    if (!drdStream.getText().equalsIgnoreCase(configType)) {
      drdStream.selectOptionWithText(configType);
    }
    }catch (Exception e) {
      Dropdown drdStream = this.engine
      .findElementWithDuration(Criteria.isDropdown().withToolTip("Search Streams"), this.timeInSecs).getFirstElement();
  if (!drdStream.getText().equalsIgnoreCase(configType)) {
    drdStream.selectOptionWithText(configType);
  }
    }
    LOGGER.LOG.info("Click on dropdown menu and select " + configType);
try {
    Text txtSearch = this.engine
        .findElement(Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)")).getFirstElement();
    txtSearch.setText(configName);
}catch (Exception e) {
  Text txtSearch = this.engine
      .findElement(Criteria.isTextField().withPlaceHolder("Type to search names or tags (enter * to show all)")).getFirstElement();
  txtSearch.setText(configName);
}
    LOGGER.LOG.info("Search " + configName + " in the 'Type to search (enter * to show all)' text field");
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    WebElement ele = this.driverCustom.getWebElement("//iframe[@title='Configuration Picker']");
    this.driverCustom.getWebDriver().switchTo().frame(ele);
    if (this.driverCustom.isElementVisible("//span[@class='gc-ScrollableTable-focusedTitle']",Duration.ofSeconds(10))) {
      WebElement elemen = this.driverCustom.getWebElement("//span[@class='gc-ScrollableTable-focusedTitle']");
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
      je.executeScript("arguments[0].scrollIntoView(true);", elemen);
    }
    // This block of code will make the program stuck in infinite loop - DO NOT UNBLOCK IT
//    else {
//      while (!this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, 120, configName) ||
//          this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, "Next").isEnabled()) {
//        if (this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, 120, configName)) {
//          WebElement elemen =
//              this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, configName);
//          JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
//          je.executeScript("arguments[0].scrollIntoView(true);", elemen);
//          break;
//        }
//        this.driverCustom.getWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, "Next").click();
//      }
//    }
    try {
      if(this.driverCustom.isElementVisible(String.format("//td[@cell-value][substring-after(normalize-space(.),'')='%s']", configName), Duration.ofSeconds(10))){
        this.driverCustom.getPresenceOfWebElement(String.format("//td[@cell-value][substring-after(normalize-space(.),'')='%s']", configName)).click();
        LOGGER.LOG.info("Select " + configName);
      }else {
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMDASHBOARDPAGE_CREATE_CONFIG_XPATH, configName).click();
      LOGGER.LOG.info("Select " + configName);
      }
    }
    catch (Exception e) {//span[@class='gc-ScrollableTable-focusedTitle']
      
//     this.driverCustom.getPresenceOfWebElement("//span[@class='gc-ScrollableTable-focusedTitle']").click();
//      LOGGER.LOG.info("Select " + configName);
      throw new InvalidArgumentException(configName + " - could not be found.");
    }
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    waitForSecs(3);
    Button btnOK = this.engine.findElement(Criteria.isButton().withText("OK")).getFirstElement();
    btnOK.click();
    waitForSecs(10);
    LOGGER.LOG.info("Click on OK button");
  }

  /**
   * <p>
   * Navigate to the Linked URL found from the Excel.
   *
   * @param url Artifact URL.
   */
  public void navigateToArtifactURL(final String url) {
    this.driverCustom.getWebDriver().navigate().to(url);
    waitForSecs(Duration.ofSeconds(15));
  }

  /**
   * <p>
   * Login in to alm application of rational requirements management "https://<local- host:8080>/rm" using
   * {@link JazzLoginPage#loginWithGivenPassword(String, String)}. <br>
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the
   * list.{@link #selectProject(String)}. <br>
   * Click on "Switch" button and select "Global Configuration" tab present on the "Select the Configuration Context"
   * dialog. <br>
   * Select the project area from the project area drop down. <br>
   * Search the stream name from the search text field present in "Select the Configuration Context" dialog. <br>
   * Get Creation Date from searching stream name
   *
   * @param configType is Streams, Baselines or Change Sets
   * @param configName is name of stream, baseline or change set
   * @return Creation Date of input Baseline in "Select the Configuration Context" dialog
   */
  public String getCreationDateFromBaseline(final String configType, final String configName) {

    String creationDate = "";

    // click on "Switch Configuration..."
    Dropdown drdConfiguration =
        this.engine.findElementWithDuration(Criteria.isDropdown().withToolTip("Current Configuration"), this.timeInSecs)
        .getFirstElement();
    drdConfiguration.selectOptionWithText("Switch Configuration...");
    LOGGER.LOG.info("Click on Switch button");

    waitForSecs(Duration.ofSeconds(15));
    LOGGER.LOG.info("The Configuration Context dialog is displayed");


    // Select configType (Stream or Baseline)
    Dropdown drdStream = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip("Streams"), this.timeInSecs).getFirstElement();
    if (!drdStream.getText().equalsIgnoreCase(configType)) {
      drdStream.selectOptionWithText(configType);
    }
    LOGGER.LOG.info("Click on dropdown menu and select " + configType);

    // search baseline
    Text txtSearch = this.engine
        .findElement(Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)")).getFirstElement();
    txtSearch.setText(configName);
    LOGGER.LOG.info("Search " + configName + " in the 'Type to search (enter * to show all)' text field");
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    WebElement ele = this.driverCustom.getWebElement("//iframe[@title='Configuration Picker']");
    this.driverCustom.getWebDriver().switchTo().frame(ele);

    // get Creation Date
    try {
      String xpathAtributes = "//tbody[@role='listbox']//tr//td[@class='config-row-cell'][3]";
      creationDate = this.driverCustom.getWebElement(xpathAtributes).getText();
      LOGGER.LOG.info("Get Creation Date successfully");
      System.out.println("creationDate = " + creationDate);
    }
    catch (Exception e) {
      LOGGER.LOG.info("Get Creation Date fail");
    }

    return creationDate;
  }


  /**
   * <p>
   * Check if Date01 before Date02
   *
   * @author LPH1HC
   * @param date1 format date: "MMM dd, yyyy, h:mm:ss a" or "MMM dd, yyyy h:mm a".
   * @param date2 format date: "MMM dd, yyyy, h:mm:ss a" or "MMM dd, yyyy h:mm a".
   * @return true if Date01 before Date02. False if Date01 after/equal to Date02.
   * @throws ParseException -
   */
  public boolean isDate01beforeDate02(final String date1, final String date2) throws ParseException {
    Boolean flag = false;
    // Synchronize format for input string date1 and date2 to: "MMM dd, yyyy, h:mm:ss a"
    // Reason to Synchronize format:
    // Value of Creation Date from Baseline as "MMM dd, yyyy h:mm a".
    // But Value of Modified On (Created On) as "MMM dd, yyyy, h:mm:ss a"
    String tempDate1 = "";
    String tempDate2 = "";
    String tempHour = "";
    String tempMinute = "";
    String tempAmPm = "";
    String[] tempTotal;

    if (date1.length() <= 21) {
      tempTotal = date1.substring(13, date1.length()).trim().split(":");
      tempHour = tempTotal[0];
      tempMinute = tempTotal[1].split(" ")[0].trim();
      tempAmPm = tempTotal[1].split(" ")[1].trim();
      tempDate1 = date1.substring(0, 12) + ", " + tempHour + ":" + tempMinute + ":00 " + tempAmPm;
      LOGGER.LOG.info("Change format of string " + date1 + "to MMM dd, yyyy, h:mm:ss a");
    }
    else {
      tempDate1 = date1;
      LOGGER.LOG.info("Not change format of string " + date1 + "to MMM dd, yyyy, h:mm:ss a");
    }

    if (date2.length() <= 21) {
      tempTotal = date2.substring(13, date2.length()).trim().split(":");
      tempHour = tempTotal[0];
      tempMinute = tempTotal[1].split(" ")[0].trim();
      tempAmPm = tempTotal[1].split(" ")[1].trim();
      tempDate2 = date2.substring(0, 12) + ", " + tempHour + ":" + tempMinute + ":00 " + tempAmPm;
      LOGGER.LOG.info("Change format of string " + date2 + "to MMM dd, yyyy, h:mm:ss a");
    }
    else {
      tempDate2 = date2;
      LOGGER.LOG.info("Not change format of string " + date2 + "to MMM dd, yyyy, h:mm:ss a");
    }

    // Date Format:
    SimpleDateFormat formatter1 = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a");
    // Set Format for date1
    Date dateTime1 = formatter1.parse(tempDate1);
    // Set Format for date2
    Date dateTime2 = formatter1.parse(tempDate2);
    // compare
    if (dateTime1.before(dateTime2)) {
      flag = true;
      LOGGER.LOG.info("Compare date time' '" + date1 + "' is before date time as '" + date2);
    }
    else if (dateTime1.after(dateTime2) || dateTime1.equals(dateTime2)) {
      LOGGER.LOG.info("Compare date time' '" + date1 + "' is not before date time as '" + date2);
    }

    return flag;
  }

  /**
   * <p>
   * compare 2 numbers such as Input1 and Input2 with expect result as expectResult <br>
   *
   * @param input1 - number 1 <br>
   * @param input2 - number 2 <br>
   * @param expectResult as "greater"/"less than"/"equal"/"not equal" for comparing between input1 and input2. Ex:
   *          expectResult as greater, it means that verify if input1 greater than input2 <br>
   * @return true if actual result is the same as expectResult.
   */
  public boolean compareTwoInputAsNumber(final String input1, final String input2, final String expectResult) {
    // convert input from string to integer
    int inputInt1 = Integer.parseInt(input1);
    int inputInt2 = Integer.parseInt(input2);
    switch (expectResult) {
      case "greater":
        if (inputInt1 >= inputInt2) {
          return true;
        }
        break;
      case "less than":
        if (inputInt1 <= inputInt2) {
          return true;
        }
        break;
      case "equal":
        if (inputInt1 == inputInt2) {
          return true;
        }
        break;
      case "not equal":
        if (inputInt1 != inputInt2) {
          return true;
        }
        break;
      default:
        throw new InvalidArgumentException(
            "Please input Expected Result as correct format, such as: 'greater' or 'less than' or 'equal' or 'not equal' ");
    }
    return false;
  }

  /**
   * Compare 2 Text such as Input1 and Input2 with expect result as expectResult <br>
   * @author VDY1HC <br>
   * @param input1 - text 1 <br>
   * @param input2 - text 2 <br>
   * @param expectResult - contains, equal
   * @return true if actual result is the same as expectResult.
   */
  public boolean compareTwoInputAsText (final String input1, final String input2, final String expectResult) {
    waitForSecs(Duration.ofSeconds(5));
    switch (expectResult) {
      case "contains":
        if (input1.contains(input2) || input2.contains(input1)) {
          return true;
        }
        break;
      case "equal":
        if (input1.equalsIgnoreCase(input2)) {
          return true;
        }
        break;
      case "not equal":
        if (!input1.equalsIgnoreCase(input2)) {
          return true;
        }
        break;
      case "not contains":
        if (!input1.contains(input2) || !input2.contains(input1)) {
          return true;
        }
        break;
      default:
        throw new WebAutomationException(
            "Please input expectResult as correct format, such as: 'equal', 'contains', 'not equal', 'not contains'");
    }
    return false;
  }

  /**
   * <p>
   * Verify the current position is in the artifactID or not
   * <p>
   *
   * @author NVV1HC
   * @param artifactID ID of artifact we want to check
   * @return true if the current position is in the artifactID
   */
  public boolean verifyPositionInTheArtifactOrNot(final String artifactID) {
    String resourceID = "";
    try {
      resourceID = this.driverCustom.getWebElement("//span[@class='resource-id']").getText();
      resourceID.trim();
    }
    catch (Exception e) {}
    return resourceID.equals(artifactID);
  }

  /**
   * <p>
   * Verify a changeset is displayed or not
   * <p>
   *
   * @author NVV1HC
   * @param changesetName name of the changeset to be checked
   * @return true if the changeset is displayed or vice versa
   */
  public boolean isChangesetDisplayed(final String changesetName) {
    //waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    try {
      this.driverCustom
      .getWebElement("//span[@class='changeset-open']/span[@class='titleNode' and text()='" + changesetName + "']");
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * <p>
   * Switch to the other tab - (from current tab at the first tab in browser)
   *
   * @param index - index of tab need to switch to
   * @return true if switch to index tab successfully
   * @author LPH1HC
   */
  public boolean switchToTheOtherTab(final String index) {
    try {
      int tabIndex = Integer.parseInt(index);
      waitForSecs(Duration.ofSeconds(5));
      WebDriver driver = this.driverCustom.getWebDriver();
      ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
      driver.switchTo().window(tabs2.get(tabIndex));
      LOGGER.LOG.info("Switch to tab with index '" + index + "' completely");
      waitForSecs(Duration.ofSeconds(5));
      return true;
    }
    catch (Exception e) {
      fail("Switch to tab with index '" + index + "' failed!");
      return false;
    }
  }

  /**
   * Verify if date input is least than 1 hours late from current <br>
   * @author VDY1HC
   * @param dateTime - input data
   * @param dateTimeFormat - example:
   *        Jul Duration.ofSeconds(5), 2021, 2:41:13 PM - MMM d, yyyy, hh:mm:ss aaa
   * @return true - if input data least than 1 hour late from current
   *         false - if input data more than 1 hour late from current
   */
  public boolean isDateTimeAtTheMoment (String dateTime, String dateTimeFormat) {
    DateFormat formatter = new SimpleDateFormat(dateTimeFormat);
    Date actualDate = new Date();
    Date inputData = null;
    try {
      inputData = formatter.parse(dateTime);
    }
    catch (ParseException e) {
      throw new WebAutomationException("Unable to parse: " + dateTime + " with format: " + dateTimeFormat);
    }
    return DateUtils.addHours(inputData, 1).after(actualDate);
  }
  /**
   * <p>Open any RM project area.<br>Click on "Current configuration" drop down present on the right side of the web page.
   * <br>Click on "Switch" button, "Select the Configuration Context" dialog is displayed.<br>Select "Global Configuration" tab present on the "Select the Configuration Context"
   * dialog. <br>Select the project area from the project area drop down. <br>Select the config type as 'Stream' from 'Search Stream' drop down menu.
   * <br> Search the stream using '*' from the search text field present in "Select the Configuration Context" dialog, all the streams will display for the selected project area.
   * <br>Count the total stream displayed for the selected project area.
   * 
   * @param proJectAreaName project area to select for config.
   * @param itemName name of the item like Streams,Baselines...
   * @return total stream count.
   */
  public String getGlobalConfigItemsCount(final String proJectAreaName,final String itemName) {
    // Click on "Switch" button
    this.driverCustom.isElementVisible(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, Duration.ofSeconds(10));
    this.driverCustom.getFirstVisibleWebElement(RMConstants.RMDASHBOARDPAGE_CURRENTCONFIG_XPATH, null).click();
    this.driverCustom.isElementVisible(RMConstants.JAZZPAGE_BUTTON_XPATH, Duration.ofSeconds(5), RMConstants.SWITCH);
    Button btnSwitch = this.engine.findElement(Criteria.isButton().withText(RMConstants.SWITCH)).getFirstElement();
    btnSwitch.click();

    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("The Configuration Context dialog is displayed");
    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);

    //Select the project area from 'Select The Config Context' dialog.
    this.driverCustom.isElementVisible("//span[text()='Project Area:']/../following-sibling::div", Duration.ofSeconds(20));
    this.driverCustom.click("//span[text()='Project Area:']/../following-sibling::div");
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.click(RMConstants.RMDASHBOARDPAGE_SETTINGS_PROJECTAREA_XPATH, proJectAreaName);

    // Select global configuration Type (Streams)
    Dropdown drdStream = this.engine
        .findElementWithDuration(Criteria.isDropdown().withToolTip("Search Streams"), this.timeInSecs).getFirstElement();
    if (!drdStream.getText().equalsIgnoreCase(itemName)) {
      drdStream.selectOptionWithText(itemName);
    }
    LOGGER.LOG.info("Click on drop down menu and select config type as 'Streams'. ");

    // Search streams using '*' .
    Text txtSearch = this.engine
        .findElement(Criteria.isTextField().withPlaceHolder("Type to search names or tags (enter * to show all)")).getFirstElement();
    txtSearch.setText("*");
    LOGGER.LOG.info("Search '*' in 'Type to search names or tags (enter * to show all)' text field");
    waitForSecs(Duration.ofSeconds(5));

    this.driverCustom.switchToFrame(RMConstants.RMARTIFACTPAGE_SETCONFIGURATON_IFRAME_XPATH);
    String streamCount = this.driverCustom.getWebElement("//span[@dojoattachpoint='_total']").getText();
    LOGGER.LOG.info("Stream Count: " + streamCount);
    return streamCount;
  }
}
