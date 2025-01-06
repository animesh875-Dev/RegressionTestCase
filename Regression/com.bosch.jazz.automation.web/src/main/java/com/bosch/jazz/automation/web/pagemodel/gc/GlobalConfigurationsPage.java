/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.common.constants.SourceControlEnums;
import com.bosch.jazz.automation.web.pagemodel.AbstractGCPage;
import com.bosch.jazz.automation.web.reporter.Reporter;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.text.Text;

/**
 * This page represents GlobalConfigurations Page.
 */
public class GlobalConfigurationsPage extends AbstractGCPage {

  /**
   * @param driverCustom driver.
   */
  public GlobalConfigurationsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);

  }

  /**
   * Clicks on create baseline button appears on top right side of the page.
   */
  public void clickOnCreateBaselineButton() {
    this.driverCustom.getFirstVisibleWebElement("//a[@class='button']/span[text()='Create Baseline...']", null).click();
    LOGGER.LOG.info("Clicked on Create baseline button.");
  }


  /**
   * Creates baseline by setting value to "Name Suffix:" , "Description".
   *
   * @param baselineName name of the baseline.
   * @param baselineDescription description of baseline.
   * @param existingTag name of the existing tag.
   * @param additionalTag name of the addition tag.
   */
  public void createBaseline(final String baselineName, final String baselineDescription, final String existingTag,
      final String additionalTag) {

    Duration timeInSecs1 = this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime();
    Dialog dlgCreateBaseline = this.engine
        .findElementWithinDuration(Criteria.isDialog().withTitle("Create Baseline"), timeInSecs1).getFirstElement();

    Text txtName = this.engine.findElementWithinDuration(
        Criteria.isTextField().hasLabel("Name Suffix:").inContainer(dlgCreateBaseline), timeInSecs1).getFirstElement();
    txtName.clearText();
    txtName.setText(baselineName);
    LOGGER.LOG.info("Input configuration name is " + baselineName);
    Text txtDescription = this.engine
        .findElementWithinDuration(
            Criteria.isTextField().isMultiLine().hasLabel("Description:").inContainer(dlgCreateBaseline), timeInSecs1)
        .getFirstElement();
    txtDescription.setText(baselineDescription);
    LOGGER.LOG.info("Input configuration description is " + baselineDescription);
    waitForSecs(2);
    Button btn = this.engine
        .findElementWithinDuration(Criteria.isButton().withText("OK").inContainer(dlgCreateBaseline), timeInSecs1)
        .getFirstElement();
    btn.click();
    waitForSecs(60);// After clicking on ok button it takes time to create and close the dialog completely.

  }
  
  /**
   * Creates Stage Baseline by setting value to "Name Suffix:".
   * @param baselineName name of the baseline
   * @param existingTag
   * @param additionalTags
   */
  @SuppressWarnings("javadoc")
  public void createStageBaseline(final String baselineName, final String existingTag, final String additionalTag) {

    Duration timeInSecs1 = this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime();
    Dialog dlgCreateBaseline = this.engine
            .findElementWithinDuration(Criteria.isDialog().withTitle("Stage Baseline"), timeInSecs1).getFirstElement();

    Text txtName = this.engine.findElementWithinDuration(
            Criteria.isTextField().hasLabel("Name Suffix:").inContainer(dlgCreateBaseline), timeInSecs1).getFirstElement();
    txtName.clearText();
    txtName.setText(baselineName);
    LOGGER.LOG.info("Input configuration name is " + baselineName);
    waitForSecs(2);

    Button btn = this.engine
            .findElementWithinDuration(Criteria.isButton().withText("OK").inContainer(dlgCreateBaseline), timeInSecs1)
            .getFirstElement();
    btn.click();
    waitForSecs(60); // After clicking on ok button it takes time to create and close the dialog completely.

}
  
    /**
     * Clicks on CommitBaseline.
     * @param driver
     */
    @SuppressWarnings("javadoc")
    public void clickCommitBaseline(WebDriver driver) {
      waitForSecs(5);
        // Find the element by its ID
      this.driverCustom.getFirstVisibleWebElement("//a[@class='button']/span[text()='Commit Baseline...']", null).click();
      LOGGER.LOG.info("Clicked on Commit baseline button.");
      waitForSecs(5);
      this.driverCustom.getFirstVisibleWebElement("//div[contains(@class,'j-buttonGroup-bleed-two')]//button[contains(text(),'Commit')]", null).click();
      LOGGER.LOG.info("Clicked on Commit button.");
    }
    
    /**
     * clicks on GoToStream page.
     * @param streamName 
     */
    @SuppressWarnings("javadoc")
    public void clickGoToStream(final String streamName) {
      waitForSecs(5);
      this.driverCustom.getFirstVisibleWebElement(
          "//a[@title='Go To Stream' and text()='DYNAMIC_VAlUE']", streamName).click();
      waitForSecs(5);// After click wait some time to pop up the dialog.
      LOGGER.LOG.info("Clicked goto sretam button.");
      
    }

    /**
     * clicks on Baseline.
     * @param baselineName 
     */
    @SuppressWarnings("javadoc")
    public void clickBaseline(final String baselineName) {
      waitForSecs(5);
      this.driverCustom.getPresenceOfWebElement("//span[@class='xcm-TabGroup-title' and contains(text(),'Baselines')]").click();
      LOGGER.LOG.info("Clicked on clickBaseline.");
      waitForSecs(2);
      WebElement ele = this.driverCustom.getFirstVisibleWebElement("//input[@placeholder='Filter by name']", null);
      ele.sendKeys(baselineName);
      LOGGER.LOG.info("Clicked on Filter by name.");
      waitForSecs(2);
      this.driverCustom.getFirstVisibleWebElement("//div[@dojoattachpoint='_tableParent']//table[@dojoattachpoint='_table']//a[@target='_self']", baselineName).click();
      LOGGER.LOG.info("Selected on BaselineName.");
      waitForSecs(10);
    }
  /**
   * Clicks on tab present on the configuration page.
   *
   * @param tabName name of the tab.
   */
  public void selectTabInConfigurationPage(final String tabName) {
    waitForSecs(10);
    long count = this.driverCustom.getWebElements("//div[@class='xcm-TabGroup-tabArea']/span").stream().count();
    if (count < 1) {
      throw new WebAutomationException("No tabs loaded list of tabs loaded is " + count);
    }
    Optional<WebElement> opt = this.driverCustom.getWebElements("//div[@class='xcm-TabGroup-tabArea']/span").stream()
        .filter(x -> x.getText().startsWith(tabName)).findFirst();
    if (opt.isPresent()) {
      opt.get().click();
    }
    else {
      throw new WebAutomationException(tabName + " invalid tab name.");
    }
    LOGGER.LOG.info("Clicked on " + tabName + " tab.");
  }

  /**
   * Archives the configuration.
   */
  public void archive() {
    this.driverCustom.getFirstVisibleWebElement(
        "(//label[contains(text(), 'Archived:')]/../following-sibling::td/input[@type='checkbox'])[1]", null).click();
    waitForSecs(5);// After click wait some time to pop up the dialog.
    LOGGER.LOG.info("Clicked on archieve button.");

  }

  /**
   * Confirms archiving the baseline.
   */
  public void confirmArchive() {
    waitForSecs(5);
    this.driverCustom.getPresenceOfWebElement("//button[text() = 'Archive']").click();
    waitForSecs(2);

    LOGGER.LOG.info("Clicked on confirm archieve button.");

    waitForSecs(10);// After click wait some time to pop up the dialog.
  }

  /**
   * Clicks on edit or save button.
   *
   * @param name of the button.
   */
  public void clickOnButtonInConfigPage(final String name) {
    waitForSecs(15);
    try
    {
    this.driverCustom.getFirstVisibleWebElement(
        "(//div[@class='gc-flexrow gc-padding' or @class='gc-flexrow gc-margin']//button[text() = 'DYNAMIC_VAlUE'])[1]",
        name).click();
    }
    catch(Exception e)
    {
    	refresh();
    	waitForSecs(15);
       this.driverCustom.clickUsingActions(this.driverCustom.getFirstVisibleWebElement(
        "(//div[@class='gc-flexrow gc-padding' or @class='gc-flexrow gc-margin']//button[text() = 'DYNAMIC_VAlUE'])[1]",
        name));
       
      /* this.driverCustom.getActions().moveToElement(this.driverCustom.getFirstVisibleWebElement(
    	        "(//div[@class='gc-flexrow gc-padding' or @class='gc-flexrow gc-margin']//button[text() = 'DYNAMIC_VAlUE'])[1]",
    	        name)).click().build().perform();*/
       
   
       
    }

    waitForSecs(5);// After click wait some time to complete the action.
    LOGGER.LOG.info("Clicked on button " + name + " on configuration page.");
  }

  /**
   * @param params data for the openActionMenu.
   */
  public void openActionMenu(final Map<String, String> params) {
    String applicationName = String.valueOf(params.get("ApplicationName")).trim();
    String configurationName = params.get("ConfigurationName").trim();
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
    Actions action = new Actions(this.driverCustom.getWebDriver());
    // Always keep the mouse at table which contains configurations
    this.driverCustom.isElementPresent("//div[starts-with(@class,'gc-ConfigurationExplorer')]", Duration.ofSeconds(10));
    WebElement gcTable = this.driverCustom.getWebElement("//div[starts-with(@class,'gc-ConfigurationExplorer')]");
    action.moveToElement(gcTable).build().perform();
    Optional<WebElement> streams = null;
    String streamXpath = "//div[starts-with(@class,'gc-Tree-row')]";
    this.driverCustom.isElementVisible(streamXpath, Duration.ofSeconds(30));
    if (applicationName.equals("RM") || applicationName.equals("QM") || applicationName.equals("CCM")) {
      streams = this.driverCustom.getWebElements(streamXpath).stream()
          .filter(x -> x.getText().trim().contains(configurationName))
          .filter(x -> x.getText().trim().endsWith(applicationName))
          .findFirst();
    }
    // Condition for AM configuration - ends with "StreamName AM CCM"
    else if(applicationName.equals("AM")) {
      streams = this.driverCustom.getWebElements(streamXpath).stream()
          .filter(x -> x.getText().trim().contains(configurationName))
          .filter(x -> x.getText().trim().endsWith("CCM"))
          .findFirst();
    }
    else if(applicationName.equals("null")) {
      streams = this.driverCustom.getWebElements(streamXpath).stream()
          .filter(x -> x.getText().trim().equalsIgnoreCase(configurationName))
          .findFirst();
    }
    
    if (streams.isPresent()) {
      // Uncheck all current checkboxs
      List<WebElement> listCheckbox =
          this.driverCustom.getWebElements(streamXpath+"//span[@role='checkbox']");
      for (WebElement checkboxElement : listCheckbox) {
        if (checkboxElement.getAttribute("aria-checked").equals("true")) {
          wait.until(ExpectedConditions.elementToBeClickable(checkboxElement)).click();
        }
      }
      // Click on "Actions" dropdown
      WebElement actionsDropdown =
          this.driverCustom.getChildElement(streams.get(), By.xpath(".//a[@class='button' and @title='Actions']"));
      WebElement checkbox = this.driverCustom.getChildElement(streams.get(), By.xpath(".//span[@role='checkbox']"));
      this.driverCustom.isElementVisible(checkbox, Duration.ofSeconds(10));
      action.moveToElement(checkbox).click().build().perform();
      waitForSecs(2);
      actionsDropdown.click();
    }
    else {
      throw new WebAutomationException(String.format("No configuration found with the name: '%s'", configurationName));
    }
    LOGGER.LOG.info("Action menu opened from " + params.get("ConfigurationName"));
    waitForSecs(5);
  }

  /**
   * @param actionMenu menu.
   */
  public void clickFromActionMenu(final String actionMenu) {
    this.driverCustom
        .getFirstVisibleWebElement("//table[@role='menu']//td[starts-with(text(),'DYNAMIC_VAlUE')]", actionMenu)
        .click();
    if(actionMenu.equalsIgnoreCase("Open in New Window")) {
      if (this.driverCustom.isElementVisible("//div[@class='jazz-ui-Dialog-header']/div[text()='Open in New Window']",
          Duration.ofSeconds(15))) {
        // If pop up 'Open in New Window' displays then select the second option (Preferred:)
        this.driverCustom.click("//div[@dojoattachpoint='defaultNode']/a");
      }
      switchToWindow();
    }
    waitForSecs(5);
    LOGGER.LOG.info("Clicked on menu from action " + actionMenu);
  }

  /**
   * Click on Current Configuration context menu and click on Switch button, then select the Configuration Management,
   * then search and select the configuration
   *
   * @param params params data for the Configuration.
   * @Configuration - Quality Management, Requirements Management, Change and Configuration Management.
   * @ProjectArea - Name of the Project Area.
   * @ConfigType - Select dropdown option: Streams, Baselines, Snapshots, Changesets.
   * @ConfigName - Name of Streams, Baselines, Snapshots, Changesets.
   */
  public void addConfiguration(final Map<String, String> params) {
    String addConfig = "Add Configurations";
    this.driverCustom.anyMatch(By.xpath("//div[@class='header-primary' or @class='jazz-ui-Dialog-header']"), 120,
        x -> x.getText().equalsIgnoreCase(addConfig), "Add Configurations dialog not opened.");
    LOGGER.LOG.info(addConfig + " dialog opened.");
    waitForSecs(5);
    this.driverCustom
        .getPresenceOfWebElement(
            "//div[text()='Select configurations to add to this stream.']/following::div[@class='jazz-ui-fat-caret']")
        .click();
    if (!params.get(SourceControlEnums.CONFIGURATION.toString()).equals("Change and Configuration Management")) {
      Text txtSearch = this.engine
          .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to filter list"), Duration.ofSeconds(30)).getFirstElement();
      txtSearch.setText(params.get(SourceControlEnums.CONFIGURATION.toString()) + Keys.ENTER);
    }
    else {
      if(params.get("ProjectArea").contains("CCM")){
        String ccmConfigurationXpath = "//span[normalize-space(text()) = '%s'][./span[contains(text(),'ccm')]]";
        this.driverCustom
            .click(String.format(ccmConfigurationXpath, params.get(SourceControlEnums.CONFIGURATION.toString())));
      }
      if(params.get("ProjectArea").contains("AM")){
        String ccmConfigurationXpath = "//span[normalize-space(text()) = '%s'][./span[contains(text(),'am')]]";
        this.driverCustom
            .click(String.format(ccmConfigurationXpath, params.get(SourceControlEnums.CONFIGURATION.toString())));
      }
    }
    LOGGER.LOG.info("Search " + params.get(SourceControlEnums.CONFIGURATION.toString()) +
        " in the 'Type to filter list' text field");
    this.driverCustom.isElementInvisible("//span[@dojoattachpoint='_spinner']//span[text()='Loading...']", Duration.ofSeconds(60));
    waitForSecs(5);
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    
    if(params.get(SourceControlEnums.CONFIGURATION.toString()).equals("Requirements Management")) {
      this.addConfigurationsWithRequirementsManagement(params);
    }
    
    else if(params.get(SourceControlEnums.CONFIGURATION.toString()).equals("Change and Configuration Management")) {
      this.addConfigurationsWithChangeAndConfigurationManagement(params);
    }
    
    else if(params.get(SourceControlEnums.CONFIGURATION.toString()).equals("Quality Management")) {
      this.addConfigurationsWithQualityManagement(params);
    }
    
    else if(params.get(SourceControlEnums.CONFIGURATION.toString()).equals("Global Configuration Management")) {
      this.addConfigurationsGlobalConfigurationManagement(params);
    }
    
    waitForSecs(5);
    Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(30)).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Click on 'OK' button");
    waitForSecs(5);
    this.driverCustom.switchToDefaultContent();
    refresh();
    waitForSecs(5);
  }
  
  
  private void addConfigurationsGlobalConfigurationManagement(final Map<String, String> params) {
    waitForSecs(5);
    // Select 'Project Area'
    String projectAreaXpath =
        "//label[contains(@class, 'label')][./span[normalize-space(text()) = 'Project Area:']]/following-sibling::div[contains(@class,'select')]/div[@class='jazz-ui-fat-caret']";
    this.driverCustom.isElementVisible(projectAreaXpath, Duration.ofSeconds(5));
    this.driverCustom.click(projectAreaXpath);
    Text txtSearch = null;
    txtSearch =
        this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter list")).getFirstElement();
    txtSearch.setText(params.get("ProjectArea") + Keys.ENTER);
    LOGGER.LOG.info(String.format("Select 'Project Area': '%s'", params.get("ProjectArea")));
    waitForSecs(2);
    // Select scope menu option
    this.driverCustom.isElementVisible("//a[@class='button gc-ScopeMenuButton-titleNode']", Duration.ofSeconds(5));
    this.driverCustom.click("//a[@class='button gc-ScopeMenuButton-titleNode']");
    waitForSecs(2);
    this.driverCustom.getPresenceOfWebElement("//span[@class='MenuItemContent']//span[text()='DYNAMIC_VAlUE']",
        params.get(SourceControlEnums.CONFIG_TYPE.toString())).click();
    LOGGER.LOG.info("Click on dropdown menu and select " + params.get(SourceControlEnums.CONFIG_TYPE.toString()));
    waitForSecs(2);
    // Search at 'Type to search names or tags (enter * to show all)'
    txtSearch = this.engine
        .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to search names or tags (enter * to show all)"), Duration.ofSeconds(30))
        .getFirstElement();
    txtSearch.setText(params.get(SourceControlEnums.CONFIG_NAME.toString()));
    LOGGER.LOG.info("Search " + params.get(SourceControlEnums.CONFIG_NAME.toString()) +
        " in the 'Type to search names or tags (enter * to show all)' text field");
    waitForSecs(2);
    String configurationOptionXpath = "//form//*[@cell-value][substring-after(normalize-space(.),'')='DYNAMIC_VAlUE']";
    String dynamicValue = params.get("ConfigName");
    try {
      this.driverCustom.isElementVisible(configurationOptionXpath, Duration.ofSeconds(10), dynamicValue);
      this.driverCustom.getPresenceOfWebElement(configurationOptionXpath, dynamicValue).click();
      this.driverCustom.isElementVisible("//td[@class='gc-PickerResultRow-titleCell'][1]", Duration.ofSeconds(10));
      this.driverCustom.getPresenceOfWebElement("//td[@class='gc-PickerResultRow-titleCell'][1]").click();
      LOGGER.LOG.info("Select " + params.get("ConfigName"));
    }
    catch (Exception e) {   
      throw new InvalidArgumentException(
          params.get(SourceControlEnums.CONFIG_NAME.toString()) + " - could not be found.");
    }
    this.driverCustom.switchToDefaultContent();
  }
  
  private void addConfigurationsWithRequirementsManagement(final Map<String, String> params) {
    waitForSecs(Duration.ofSeconds(5));
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
    String iframe = "//iframe[contains(@src,'rm/configurationPicker')]";
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
    // Select 'Project Area'
    String projectAreaXpath = "//td[@dojoattachpoint='_projectNode' and @class='project-select']//div[@dojoattachpoint='_caret']";
    this.driverCustom.isElementVisible(projectAreaXpath, Duration.ofSeconds(5));
    this.driverCustom.click(projectAreaXpath);
    Text txtSearch = null;
    txtSearch =
        this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter list")).getFirstElement();
    txtSearch.setText(params.get("ProjectArea") + Keys.ENTER);
    LOGGER.LOG.info(String.format("Select 'Project Area': '%s'", params.get("ProjectArea")));
    waitForSecs(2);
    // Select 'Component'
    this.driverCustom.switchToDefaultContent();
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
    String componentXpath = "//td[@dojoattachpoint='_componentNode' and @class='project-select']//div[@dojoattachpoint='_caret']";
    this.driverCustom.isElementVisible(componentXpath, Duration.ofSeconds(5));
    this.driverCustom.click(componentXpath);
    txtSearch =
        this.engine.findElement(Criteria.isTextField().withPlaceHolder("Type to filter list")).getFirstElement();
    if (params.get("GC_Component") != null) {
      txtSearch.setText(params.get("GC_Component") + Keys.ENTER);
      waitForSecs(2);
      LOGGER.LOG.info(String.format("Select 'Component': '%s'", params.get("GC_Component")));
    }
    else {
      txtSearch.setText(params.get("Components") + Keys.ENTER);
      waitForSecs(2);
      LOGGER.LOG.info(String.format("Select 'Component': '%s'", params.get("Components")));
    }
    // Select scope menu option
    this.driverCustom.switchToDefaultContent();
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
    this.driverCustom.isElementVisible("//a[@class='button gc-ScopeMenuButton-titleNode']", Duration.ofSeconds(5));
    this.driverCustom.click("//a[@class='button gc-ScopeMenuButton-titleNode']");
    this.driverCustom.getPresenceOfWebElement(
        String.format("//span[text()='%s']", params.get(SourceControlEnums.CONFIG_TYPE.toString()))).click();
    LOGGER.LOG.info("Click on dropdown menu and select " + params.get(SourceControlEnums.CONFIG_TYPE.toString()));
    waitForSecs(2);
    // Search at 'Type to search (enter * to show all)'
    txtSearch = this.engine
        .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to search (enter * to show all)"), Duration.ofSeconds(30))
        .getFirstElement();
    txtSearch.setText(params.get(SourceControlEnums.CONFIG_NAME.toString()));
    LOGGER.LOG.info("Search " + params.get(SourceControlEnums.CONFIG_NAME.toString()) +
        " in the 'Type to search (enter * to show all)' text field");
    waitForSecs(2);
    String configurationOptionXpath = "//div[@class='config-table']//*[normalize-space(text())='DYNAMIC_VAlUE']";
    String dynamicValue = params.get("ConfigName");
    try {
      this.driverCustom.switchToDefaultContent();
      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
      this.driverCustom.isElementVisible(configurationOptionXpath, Duration.ofSeconds(10), dynamicValue);
      this.driverCustom.getPresenceOfWebElement(configurationOptionXpath, dynamicValue).click();
      LOGGER.LOG.info("Select " + params.get("ConfigName"));
    }
    catch (Exception e) {   
      throw new InvalidArgumentException(
          params.get(SourceControlEnums.CONFIG_NAME.toString()) + " - could not be found.");
    }
    this.driverCustom.switchToDefaultContent();
  }
  
  private void addConfigurationsWithChangeAndConfigurationManagement(final Map<String, String> params) {
    waitForSecs(5);
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
    String iframe = "//iframe[contains(@src,'/am/rtcoslc')]";
    /*String iframe = null;
    if(params.get("ProjectArea").contains("CCM")) {
      iframe = "//iframe[contains(@src,'/ccm')]";  
    }
    else {
      iframe = "//iframe[contains(@src,'/am')]";
    }*/
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
    // Select 'Project Area:'
    String projectAreaXpath = "//td[contains(@class, 'label')][./label[normalize-space(text()) = 'Project Area:']]/following::div[@class='jazz-ui-fat-caret']";
    this.driverCustom.isElementVisible(projectAreaXpath, Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement(projectAreaXpath).click();
    Text txtSearch = null;
    txtSearch =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to filter list"), Duration.ofSeconds(30)).getFirstElement();
    txtSearch.setText(params.get("ProjectArea") + Keys.ENTER);
    LOGGER.LOG.info(String.format("Select 'Project Area': '%s'", params.get("ProjectArea")));
    waitForSecs(2);
    // Select 'Type:'
    this.driverCustom.switchToDefaultContent();
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
    this.driverCustom.isElementVisible("//select[@dojoattachpoint='configTypeSelect']", Duration.ofSeconds(5));
    WebElement configTypeElement = this.driverCustom.getPresenceOfWebElement("//select[@dojoattachpoint='configTypeSelect']");
    this.driverCustom.select(configTypeElement, params.get(SourceControlEnums.CONFIG_TYPE.toString()),
        SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
    LOGGER.LOG.info(String.format("Select 'Type': '%s'", params.get(SourceControlEnums.CONFIG_TYPE.toString())));
    waitForSecs(2);
    // Search at 'Type to search (enter * to show all)'
    txtSearch = this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search by stream name"), Duration.ofSeconds(30))
        .getFirstElement();
    txtSearch.setText(params.get(SourceControlEnums.CONFIG_NAME.toString()));
    LOGGER.LOG.info("Search " + params.get(SourceControlEnums.CONFIG_NAME.toString()) +
        " in the 'Search by stream name' text field");
    waitForSecs(2);
    String configurationOptionXpath = "//div[@class='contentNode']//*[normalize-space(text())='DYNAMIC_VAlUE']";
    String dynamicValue = params.get("ConfigName");
    try {
      this.driverCustom.switchToDefaultContent();
      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
      this.driverCustom.isElementVisible(configurationOptionXpath, Duration.ofSeconds(10), dynamicValue);
      this.driverCustom.getPresenceOfWebElement(configurationOptionXpath, dynamicValue).click();
      LOGGER.LOG.info("Select " + params.get("ConfigName"));
    }
    catch (Exception e) {   
      throw new InvalidArgumentException(
          params.get(SourceControlEnums.CONFIG_NAME.toString()) + " - could not be found.");
    }
    this.driverCustom.switchToDefaultContent();
  }
  
  private void addConfigurationsWithQualityManagement(final Map<String, String> params) {
    waitForSecs(5);
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30));
    String iframe = "//iframe[contains(@src,'/qm')]";
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
    // Select 'Project Area:'
    String projectAreaXpath = "//div[contains(@class, 'label')][./label[normalize-space(text()) = 'Project Area:']]/following::div[@class='jazz-ui-fat-caret']";
    this.driverCustom.isElementVisible(projectAreaXpath, Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement(projectAreaXpath).click();
    Text txtSearch = null;
    txtSearch =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to filter list"), Duration.ofSeconds(30)).getFirstElement();
    txtSearch.setText(params.get("ProjectArea") + Keys.ENTER);
    LOGGER.LOG.info(String.format("Select 'Project Area': '%s'", params.get("ProjectArea")));
    waitForSecs(2);
    //Select scope menu option
    this.driverCustom.switchToDefaultContent();
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
    this.driverCustom.isElementVisible("//a[@class='button gc-ScopeMenuButton-titleNode']", Duration.ofSeconds(5));
    this.driverCustom.click("//a[@class='button gc-ScopeMenuButton-titleNode']");
    this.driverCustom.getPresenceOfWebElement(String.format("//span[text()='%s']", params.get(SourceControlEnums.CONFIG_TYPE.toString()))).click();
    LOGGER.LOG.info("Click on dropdown menu and select " + params.get(SourceControlEnums.CONFIG_TYPE.toString()));
    waitForSecs(2);
    // Search at 'Type to search (enter * to show all)'
    txtSearch = this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to search names (enter * to show all)"), Duration.ofSeconds(30))
        .getFirstElement();
    txtSearch.setText(params.get(SourceControlEnums.CONFIG_NAME.toString()));
    LOGGER.LOG.info("Search " + params.get(SourceControlEnums.CONFIG_NAME.toString()) +
        " in the 'Type to search names (enter * to show all)' text field");
    waitForSecs(2);
    String configurationOptionXpath = "//div[@class='picker-content list-selector']//*[normalize-space(text())='DYNAMIC_VAlUE0']/preceding::input[@aria-label='DYNAMIC_VAlUE1']";
    String[] dynamicValues = { params.get("ConfigName"), params.get("ConfigName") };
    try {
      this.driverCustom.switchToDefaultContent();
      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(iframe)));
      this.driverCustom.isElementVisible(configurationOptionXpath, Duration.ofSeconds(10), dynamicValues);
      this.driverCustom.getPresenceOfWebElement(configurationOptionXpath, dynamicValues).click();
      LOGGER.LOG.info("Select " + params.get("ConfigName"));
    }
    catch (Exception e) {   
      throw new InvalidArgumentException(
          params.get(SourceControlEnums.CONFIG_NAME.toString()) + " - could not be found.");
    }
    this.driverCustom.switchToDefaultContent();
  }

  /**
   * <P>
   * Click on Button which present inside a dialog.
   *
   * @param dialog name of the dialog.
   * @param button name of the button.
   */
  @Override
  public Boolean clickOnDialogButton(final String dialog, final String button) {
    try {
      this.driverCustom.getPresenceOfWebElement("//button[@class='gc-button-primary' or @Class='j-button-danger' and @type='button']").click();
    }
    catch (Exception e) {
      this.driverCustom.getPresenceOfWebElement("//button[@class='j-button-primary' and @type='button']").click();
    }
    // Wait for dialog closed and spinner disappeared
    this.driverCustom.isElementInvisible(String.format("//div[text()='%s']", dialog), Duration.ofSeconds(5));
    this.driverCustom.isElementInvisible("//img[@dojoattachpoint='_spinnerIcon']", Duration.ofSeconds(5));
    return true;
  }
  
  /**
   * <P>
   * Click on Expand button on streams, configurations or baselines
   *
   * @param name name of configuration want to expand/collapse
   * @param applicationName name of application to expand like RM, QM , CCM
   * @param expandOrCollapse option: "expand" or "collapse"
   * 
   * @author LTU7HC
   */
  public void expandOrCollapse(final String name, final String applicationName, final String expandOrCollapse) {
    String xpath = "";
    String attributeXpath = "";
    switch (applicationName.toLowerCase()) {
      case "RM":
      case "QM":
      case "CCM":
        xpath = String.format("//div[starts-with(@class,'gc-Tree-row')][.//a[normalize-space(text())='%s']][.//span[text()='%s']]//img[@class='gc-Tree-expando']", name, applicationName);
        attributeXpath = String.format("//span[./a[normalize-space(text())='%s']][./span[text()='%s']]", name.trim(), applicationName.trim());
        break;
      
      case "null":
      case "Null":
        //xpath = String.format("//div[starts-with(@class,'gc-Tree-row')][.//span[normalize-space(text())='%s']]//img[@class='gc-Tree-expando']", name);
        xpath = String.format("//div[starts-with(@class,'gc-Tree-row')][.//span[substring-after(normalize-space(.),'')='%s']]//img[@class='gc-Tree-expando']", name);
//        attributeXpath = String.format("//span[@role='treeitem'][normalize-space(text())='%s']", name.trim());
        attributeXpath = String.format("//span[@role='treeitem'][substring-after(normalize-space(.),'')='%s']", name.trim());
        break;
        
      default:
        throw new InvalidArgumentException("Invalid application name");
    }
    
    // Double click to expand button to make attribute visible in DOM otherwise attribute 'aria-expanded' will 'null'
    this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(10));
    this.driverCustom.clickTwice(xpath);
    waitForSecs(5);
    // Get attribute
    this.driverCustom.isElementVisible(attributeXpath, Duration.ofSeconds(10));
    String attribute = this.driverCustom.getAttribute(attributeXpath, "aria-expanded");
    if(expandOrCollapse.equalsIgnoreCase("expand")) {
      if(attribute.equals("false")) {
        this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(5));
        this.driverCustom.click(xpath);
        LOGGER.LOG.info(String.format("Clicked on 'Expand' button of '%s'", name));
      }
      else {
        LOGGER.LOG.info(String.format("'Expand' button of '%s' is already expanded", name)); 
      }
    }
    else if(expandOrCollapse.equalsIgnoreCase("collapse")) {
      if(attribute.equals("true")) {
        this.driverCustom.isElementVisible(xpath, Duration.ofSeconds(5));
        this.driverCustom.click(xpath);
        LOGGER.LOG.info(String.format("Clicked on 'Collapse' button of '%s'", name));
      }
      else {
        LOGGER.LOG.info(String.format("'Collapse' button of '%s' is already collapsed", name)); 
      }
    }
  }
  
  /**
   * <p>
   * Open 'Actions' menu using {@link GlobalConfigurationsPage#openActionMenu(Map)}<br>
   * Click on option in Action Menu using {@link GlobalConfigurationsPage#clickFromActionMenu(String)}
   * Then call this method to verify that users open configuration successfully<br>
   * 
   * @param additionalParams - store keys and values:
   *        expectedProjectArea - expected project area name <br>
   *        applicationName - name of aplication as: RM, QM, CCM
   *        expectedComponent - expected current component name <br>
   *        expectedStream - expected current stream name <br>
   *        expectedConfiguration - expected current configuration name <br>
   * 
   * 
   * @author LTU7HC
   * @return boolean true or false
   */
  public boolean verifyThatOpenNewAddedConfigurationSucessfully(final Map<String, String> additionalParams) {
    boolean isDisplayed = false;
    String applicationName = additionalParams.get("applicationName");
    String projectNameXpath = "//span[@dojoattachpoint='_bannerTitle']";
    String currentComponentXpath = "//span[@class='rdm-team-component-menu']|//span[@class='banner-title']";
    String currentConfiguration =
        "//span[@id='configurationTitleNode']|//span[./img[contains(@class,'globalConfigurationNode')]]//span[@class='titleNode']";

    String actualProjectName = "";
    String actualStream = "";
    waitForSecs(5);
    // Wait for page loading
    this.driverCustom.waitForPageLoaded(45);
    waitForSecs(10);
    if (applicationName.equalsIgnoreCase("RM") || applicationName.equalsIgnoreCase("QM")) {
      try {
        this.driverCustom.isElementVisible(projectNameXpath, Duration.ofSeconds(5));
        actualProjectName = this.driverCustom.getText(projectNameXpath).trim();
        this.driverCustom.isElementVisible(currentComponentXpath, Duration.ofSeconds(5));
        String actualComponent = this.driverCustom.getText(currentComponentXpath).trim();
        this.driverCustom.isElementVisible(currentConfiguration, Duration.ofSeconds(5));
        actualStream = this.driverCustom.getText(currentConfiguration).trim();
        assertEquals("Verify that 'Project Area' name is displayed as expectation", additionalParams.get("expectedProjectArea"), actualProjectName);
        assertEquals("Verify that 'Component' name is displayed as expectation", additionalParams.get("expectedComponent"), actualComponent);
        assertEquals("Verify that 'Stream' name is displayed as expectation", additionalParams.get("expectedStream"), actualStream);
        isDisplayed = true;
      }
      catch (Exception e) {
        LOGGER.LOG.error("ERROR: " + e);
      }
    }
    else if (applicationName.equalsIgnoreCase("CCM")) {
      try {
        String streamXpath = "//div[@dojoattachpoint='_gcLink']//a";
        String configurationNameXpath = "//div[@class='labelTextDiv']/span[@class='label']";
        this.driverCustom.isElementVisible(configurationNameXpath, Duration.ofSeconds(5));
        String actualConfigurationName = this.driverCustom.getText(configurationNameXpath).trim();
        this.driverCustom.isElementVisible(projectNameXpath, Duration.ofSeconds(5));
        actualProjectName = this.driverCustom.getText(projectNameXpath).trim();
        actualStream = this.driverCustom.getText(streamXpath).trim();
        assertEquals("Verify that 'Configuration' name is displayed as expectation", additionalParams.get("expectedConfiguration"), actualConfigurationName);
        assertEquals("Verify that 'Project Area' name is displayed as expectation", additionalParams.get("expectedProjectArea"), actualProjectName);
        assertEquals("Verify that 'Stream' name is displayed as expectation", additionalParams.get("expectedStream"), actualStream);
        isDisplayed = true;
      }
      catch (Exception e) {
        LOGGER.LOG.error("ERROR: " + e);
      }
    }
    return isDisplayed;
  }
  
  /**
   * This action used to close the current window and switch to the another window (in case of we have 2 windows) (This
   * action will close the window where driver staying)
   * 
   * @author LTU7HC
   */
  public void closeCurrentWindowAndSwitchToAnother() {
    if (this.driverCustom.getWebDriver().getWindowHandles().size() > 1) {
      switchToWindow();
      this.driverCustom.closeAllTabsExceptOne();
    }
    else {
      LOGGER.LOG.info("Can not close current window - Actual number of window: " +
          this.driverCustom.getWebDriver().getWindowHandles().size());
    }
  }
  
  /**
   * This action used to switch to window - with title of page contains expected string
   * 
   * @author LTU7HC
   * @param expectedTitle expected title name
   */
  public void switchToWindowWithTitleContains(final String expectedTitle) {
    String originalHandle = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> winHandles = this.driverCustom.getWebDriver().getWindowHandles();
    boolean isNotFound = true;
    if (winHandles.size() > 1 && !(this.driverCustom.getCurrentPageTitle().trim().contains(expectedTitle))) {
      for (String winHandle : winHandles) {
        if (!winHandle.equals(originalHandle)) {
          this.driverCustom.getWebDriver().switchTo().window(winHandle);
          if (this.driverCustom.getCurrentPageTitle().trim().contains(expectedTitle)) {
            isNotFound = false;
            break;
          }
        }
      }
    }
    if(isNotFound) {
      // In case of winHandles.size() > 1 and title is not equal expected 
      // then the driver will switch back to the original window
      this.driverCustom.getWebDriver().switchTo().window(originalHandle);
    }
  }
  
  /**
   * This action used to Compare with - compare the selected configuration with another.
   * Only use to compare items size available.
   * Find list webelement based on highlight row on both column and compare.
   * @author YUU3HC
   * 
   */
  public void compareWith() {
    Integer leftGcAddedRow, leftGcRemovedRow, rightGcRemovedRow, rightGcAddedRow;
    leftGcAddedRow = leftGcRemovedRow = rightGcRemovedRow = rightGcAddedRow = 0;
    List<WebElement> listLeftColumn =
        this.driverCustom.getVisibleWebElements("//div[@class='gc-CompareView-leftColumn']//a");

    List<WebElement> listRightColumn =
        this.driverCustom.getVisibleWebElements("//div[@class='gc-CompareView-rightColumn']//a");
    for (WebElement left: listLeftColumn) {
      leftGcAddedRow = left.findElements(By.xpath("//div[contains(@class,'gc-CompareTree-addedRow')]")).size();
      leftGcRemovedRow = left.findElements(By.xpath("//div[contains(@class,'gc-CompareTree-removedRow')]")).size();
      }
    for (WebElement right: listRightColumn) {
      rightGcAddedRow = right.findElements(By.xpath("//div[contains(@class,'gc-CompareTree-addedRow')]")).size();
      rightGcRemovedRow = right.findElements(By.xpath("//div[contains(@class,'gc-CompareTree-removedRow')]")).size();
      }
    assertEquals(leftGcAddedRow, rightGcAddedRow);
    assertEquals(leftGcRemovedRow, rightGcRemovedRow);
    Reporter.logPass("Left Column and Right Column have equal items size.");
  }
  
  /**
   * This action uses to find Compare With dialog on GC page
   * Select dropdown configuration type.
   * Type to search name or tags of configuration.
   * then search and select the configuration.
   * Finally, click on Compare button
   *
   * @param params params data for the Configuration.
   * @ConfigType - Select dropdown option: Streams, Baselines, Snapshots, Changesets.
   * @ConfigName - Name of Streams, Baselines, Snapshots, Changesets.
   */
  public void addCompareWithConfiguration(final Map<String, String> params) {
    waitForSecs(5);
    Text txtSearch = null;
    // Select scope menu option
    this.driverCustom.isElementVisible("//a[@class='button gc-ScopeMenuButton-titleNode']", Duration.ofSeconds(5));
    this.driverCustom.click("//a[@class='button gc-ScopeMenuButton-titleNode']");
    waitForSecs(2);
    this.driverCustom.getPresenceOfWebElement("//span[@class='MenuItemContent']//span[text()='DYNAMIC_VAlUE']",
        params.get(SourceControlEnums.CONFIG_TYPE.toString())).click();
    LOGGER.LOG.info("Click on dropdown menu and select " + params.get(SourceControlEnums.CONFIG_TYPE.toString()));
    waitForSecs(2);
    // Search at 'Type to search names or tags (enter * to show all)'
    txtSearch = this.engine
        .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to search names or tags (enter * to show all)"), Duration.ofSeconds(30))
        .getFirstElement();
    txtSearch.setText(params.get(SourceControlEnums.CONFIG_NAME.toString()));
    LOGGER.LOG.info("Search " + params.get(SourceControlEnums.CONFIG_NAME.toString()) +
        " in the 'Type to search names or tags (enter * to show all)' text field");
    waitForSecs(2);
    String configurationOptionXpath = "//form//*[@cell-value][substring-after(normalize-space(.),'')='DYNAMIC_VAlUE']";
    String dynamicValue = params.get("ConfigName");
    try {
      this.driverCustom.isElementVisible(configurationOptionXpath, Duration.ofSeconds(10), dynamicValue);
      this.driverCustom.getPresenceOfWebElement(configurationOptionXpath, dynamicValue).click();
      this.driverCustom.isElementVisible("//td[@class='gc-PickerResultRow-titleCell'][1]", Duration.ofSeconds(10));
      this.driverCustom.getPresenceOfWebElement("//td[@class='gc-PickerResultRow-titleCell'][1]").click();
      LOGGER.LOG.info("Select " + params.get("ConfigName"));
    }
    catch (Exception e) {   
      throw new InvalidArgumentException(
          params.get(SourceControlEnums.CONFIG_NAME.toString()) + " - could not be found.");
    }
    
    waitForSecs(5);
    Button btnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("Compare"), Duration.ofSeconds(30)).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Click on 'Compare' button");
    refresh();
    waitForSecs(5);
    this.driverCustom.switchToDefaultContent();
  }
  
  /**
   * This action used to select/ filter existing Tags Used by Configurations
   * 
   * @author YUU3HC
   * @param tagName - name of existing tag
   */
  public void filterTagUsedByConfigurations(final String tagName) {
    waitForSecs(2);
      this.driverCustom
          .getWebElement("//div[@class='gc-small-margin-r' and contains(text(),'Tags')]/parent::div/following::span[contains(text(),'"+tagName+"')]")
          .click();
      waitForSecs(2);
      Reporter.logPass("Filtered: Tags Used by Configuration: " + tagName);
  }
}
