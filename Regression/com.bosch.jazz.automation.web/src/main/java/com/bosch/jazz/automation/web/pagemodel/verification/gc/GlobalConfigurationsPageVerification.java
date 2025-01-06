/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.gc;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.InvalidArgumentException;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SourceControlEnums;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.gc.GlobalConfigurationsPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;

/**
 * @author HRT5KOR
 */
public class GlobalConfigurationsPageVerification extends AbstractWebPageVerification {

  private static final String CLICKED_AS_EXPECTED = " clicked as expected.";

  /**
   * @param driverCustom driver
   */
  public GlobalConfigurationsPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);

  }

  /**
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#clickOnCreateBaselineButton()}.
   *
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyClickOnCreateBaselineButton(final String lastResult) {
    try {
      waitForSecs(5);
      this.engine.findElement(Criteria.isDialog().withTitle("Create Baseline")).getFirstElement();
      return new TestAcceptanceMessage(true,
          "Verified by Create baseline line dialog opened.\n\nActual result is Create baseline dialog opened as expected.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          "Verified by Create baseline line dialog opened.\n\nActual result is Create baseline dialog not opened.");
    }

  }


  /**
   * Creates baseline by setting value to "Name Suffix:" , "Description".
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#createBaseline(String, String, String, String)}.
   *
   * @param baselineName name of the baseline.
   * @param baselineDescription description of baseline.
   * @param existingTag name of the existing tag.
   * @param additionalTag name of the addition tag.
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyCreateBaseline(final String baselineName, final String baselineDescription,
      final String existingTag, final String additionalTag, final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(15));
    boolean inVisibleElemnt = this.driverCustom.isElementInvisible("//div[@class='header-primary']",
        this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime());

    if (inVisibleElemnt && this.driverCustom.getWebElements("//div[@class='messageSummary']").stream()
        .anyMatch(x -> x.getText().startsWith("Created the") && x.getText().contains(baselineName))) {
      new GlobalConfigurationsPage(this.driverCustom).selectTabInConfigurationPage("Baselines");
      return new TestAcceptanceMessage(true,
          "Verified by displyed message on page and baseline visiblity under baselines tab section on right hand side.\n\nActual result is baseline created with name containing " +
              baselineName + "\nand\nbaseline " + baselineName +
              " is displayed under baselines tab section on right hand side as expected.");
    }

    return new TestAcceptanceMessage(false,
        "Verified by displyed message on page.\n\nActual result is baseline created with name not containing " +
            baselineName + " as expected.");
  }

  /**
   * Clicks on tab present on the configuration page.
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#selectTabInConfigurationPage(String)}.
   *
   * @param tabName name of the tab.
   * @param lastResult result.
   * @return true
   */
  public TestAcceptanceMessage verifySelectTabInConfigurationPage(final String tabName, final String lastResult) {
    if (this.driverCustom.getWebElements("//div[@class='xcm-TabGroup-tabArea']/span").stream()
        .anyMatch(x -> x.getText().startsWith(tabName) && x.getAttribute("aria-selected").equals("true"))) {
      return new TestAcceptanceMessage(true,
          "Verified by Selected line on tab.\n\nActual result is " + tabName + " selected as expected.");
    }

    return new TestAcceptanceMessage(false,
        "Verified by Selected line on tab.\\n\\nActual result is " + tabName + " not selected.");

  }

  /**
   * Archives the configuration.
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#archive()}.
   *
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyArchive(final String lastResult) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    if (this.driverCustom
        .getWebElements(
            "(//div[@class='gc-flexrow gc-padding' or @class='gc-flexrow gc-margin']//button[text() = 'Save'])[1]")
        .stream().anyMatch(x -> x.getText().startsWith("Save") && (x.getAttribute("disabled") == null))) {
      return new TestAcceptanceMessage(true,
          "Verified by enabling of save button.\n\nActual Result is archive checkbox checked as expected.");
    }
    return new TestAcceptanceMessage(false,
        "Verified by enabling of save button.\n\nActual Result is archive checkbox not checked as expected.");
  }

  /**
   * Confirms archiving the baseline.
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#confirmArchive()}.
   *
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyConfirmArchive(final String lastResult) {
    try {
      this.engine.findElement(Criteria.isDialog().withTitle("Confirm Archive")).getFirstElement();
      return new TestAcceptanceMessage(false,
          "Verified by Confirm Archive.\n\nActual Result is archive not confirmed as expected.");
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(true,
          "Verified by Confirm Archive.\n\nActual Result is archive confirmed and Restore button is enabled as expected.");

    }

  }

  /**
   * Clicks on edit or save button.
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#clickOnButtonInConfigPage(String)}.
   *
   * @param name of the button.
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyClickOnButtonInConfigPage(final String name, final String lastResult) {
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    if (name.equalsIgnoreCase("Save")) {
      try {
        waitForSecs(15);
        if (this.driverCustom.isElementPresent("//span[contains(text(),'Archive')]", Duration.ofSeconds(120))) {
          return new TestAcceptanceMessage(true,
              "Verified Save configuration after Confirm Archive dialog opened.\n\nActual Result is archive confirmed dialog opened as expected.");
        }
      }
      catch (Exception e) {
        return new TestAcceptanceMessage(false,
            "Verified Save configuration after Confirm Archive dialog opened.\n\nActual Result is archive confirmed dialog not opened as expected.");
      }
    }

    if (!this.driverCustom.isElementVisible("//button[@class='gc-button-primary' and text() = 'DYNAMIC_VAlUE']", Duration.ofSeconds(5),
        name)) {
      return new TestAcceptanceMessage(true,
          "Verified by change in button action.\n\nActual result is " + name + CLICKED_AS_EXPECTED);
    }
    return new TestAcceptanceMessage(false,
        "Verified by change in button action.\n\nActual result is " + name + " not" + CLICKED_AS_EXPECTED);
  }

  /**
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#openActionMenu(Map)}.
   *
   * @param params data
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyOpenActionMenu(final Map<String, String> params, final String lastResult) {
    if (this.driverCustom.isElementVisible("//table[@role='menu']", Duration.ofSeconds(5))) {
      return new TestAcceptanceMessage(true,
          "Verified by opening action menu.\n\nActual result is Actions menu opened as expected.");
    }
    return new TestAcceptanceMessage(false,
        "Verified by opening action menu.\n\nActual result is Actions menu is not opened.");
  }

  /**
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#clickFromActionMenu(String)}.
   *
   * @param actionMenu menu
   * @param lastResult result
   * @return true
   */
  public TestAcceptanceMessage verifyClickFromActionMenu(final String actionMenu, final String lastResult) {
    if(actionMenu.equalsIgnoreCase("Open in New Window")) {
      if(this.driverCustom.getWebDriver().getWindowHandles().size()>1) {
        return new TestAcceptanceMessage(true,
            "Verified by Closing action menu.\n\nActual result is " + actionMenu + CLICKED_AS_EXPECTED);
      }
    }
    if (!this.driverCustom.isElementVisible("//table[@role='menu']//td[starts-with(text(),'DYNAMIC_VAlUE')]", Duration.ofSeconds(5),
        actionMenu)) {
      return new TestAcceptanceMessage(true,
          "Verified by Closing action menu.\n\nActual result is " + actionMenu + CLICKED_AS_EXPECTED);
    }
    return new TestAcceptanceMessage(false,
        "Verified by Closing action menu.\n\nActual result is " + actionMenu + CLICKED_AS_EXPECTED);
  }

  /**
   * Click on Current Configuration context menu and click on Switch button, then select the Configuration Management,
   * then search and select the configuration
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#addConfiguration(Map)}.
   *
   * @return
   */
  @SuppressWarnings({ "javadoc", "unlikely-arg-type" })
  public TestAcceptanceMessage verifyAddConfiguration(final Map<String, String> params, final String lastResult) {
    waitForSecs(5);
    String xpath = "";
    if(params.get(SourceControlEnums.CONFIGURATION.toString()).equalsIgnoreCase("Global Configuration Management")) {
      if(params.get("ConfigType").equalsIgnoreCase("Baselines") || params.get("ConfigType").equalsIgnoreCase("Baseline Staging Streams")) {
        xpath = String.format("//span[@role='treeitem'][substring-after(normalize-space(.),'')='%s']",  params.get("ConfigName")); 
      }
      else {
        xpath = String.format("//span[@role='treeitem'][normalize-space(text())='%s']",  params.get("ConfigName"));
      }
    }
    else {
      // Do not remove this block in case of IBM change locator > use this block
//      xpath = String.format("//span[@role='treeitem'][./a[normalize-space(text())='%s']]", params.get("ConfigName"));
      xpath = String.format("//span[@role='treeitem'][./a[substring-after(normalize-space(.),'')='%s']]", params.get("ConfigName"));
    }
    
    String projectArea = params.get("ProjectArea");
    if(projectArea.contains("RM")) {
      xpath = xpath + "[./span[text()='RM']]";
    }
    else if (projectArea.contains("QM")) {
      xpath = xpath + "[./span[text()='QM']]";
    }
    else if (projectArea.contains("CCM")) {
      xpath = xpath + "[./span[text()='CCM']]";
    }

    try {
      this.driverCustom.getPresenceOfWebElement(xpath);
      String configuration = params.get(SourceControlEnums.CONFIGURATION.toString());
      if(configuration.equalsIgnoreCase("Global Configuration Management")) {
        return new TestAcceptanceMessage(true, String.format(
            "Verify that configuration: '%s' has been added under main Stream.\n\'Select configurations to add to this stream': '%s'.\n\'Project Area': '%s'.\n\'Search box dropdown': '%s'.",
            params.get("ConfigName"), configuration, projectArea,
            params.get(SourceControlEnums.CONFIG_TYPE.toString())));
      }
      else if(configuration.equalsIgnoreCase("Change and Configuration Management")) {
        return new TestAcceptanceMessage(true, String.format(
            "Verify that configuration: '%s' has been added under main Stream.\n\'Select configurations to add to this stream': '%s'.\n\'Project Area': '%s'.\n\'Type': '%s'.",
            params.get("ConfigName"), configuration, projectArea,
            params.get(SourceControlEnums.CONFIG_TYPE.toString())));
      }
      else if(configuration.equalsIgnoreCase("Quality Management")) {
        return new TestAcceptanceMessage(true, String.format(
            "Verify that configuration: '%s' has been added under main Stream.\n\'Select configurations to add to this stream': '%s'.\n\'Project Area': '%s'.\n\'Search box dropdown': '%s'.",
            params.get("ConfigName"), configuration, projectArea,
            params.get(SourceControlEnums.CONFIG_TYPE.toString())));
      }
      else if(configuration.equalsIgnoreCase("Requirements Management")) {
        return new TestAcceptanceMessage(true, String.format(
            "Verify that configuration: '%s' has been added under main Stream.\n\'Select configurations to add to this stream': '%s'.\n\'Project Area': '%s'.\n\'Component': '%s'.\n\'Search box dropdown': '%s'.",
            params.get("ConfigName"), configuration, projectArea, params.get("GC_Component"),
            params.get(SourceControlEnums.CONFIG_TYPE.toString())));
      }
      return new TestAcceptanceMessage(false,
          String.format("Verify that configuration: '%s' was not added to main Stream.",
              params.get(SourceControlEnums.CONFIG_NAME.toString())));
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false,
          String.format("Verify that configuration: '%s' was not added to main Stream.",
              params.get(SourceControlEnums.CONFIG_NAME.toString())));
    }
  }
  
   /**
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#expandOrCollapse(String, String, String)}.
   *
   * @param name name of configuration want to expand/collapse
   * @param applicationName name of application to expand like RM, QM , CCM
   * @param expandOrCollapse option: "expand" or "collapse"
   * @param lastResult the last result
   * @return test acceptance message
   * 
   * @author LTU7HC
   */
  public TestAcceptanceMessage verifyExpandOrCollapse(final String name, final String applicationName,
      final String expandOrCollapse, final String lastResult) {
    waitForSecs(5);
    String attributeXpath = "";
    switch (applicationName.toLowerCase()) {
      case "RM":
      case "QM":
      case "CCM":
        attributeXpath = String.format("//span[./a[normalize-space(text())='%s']][./span[text()='%s']]", name.trim(), applicationName.trim());
        break;
      
      case "null":
      case "Null":
//        attributeXpath = String.format("//span[@role='treeitem'][normalize-space(text())='%s']", name.trim());
        attributeXpath = String.format("//span[@role='treeitem'][substring-after(normalize-space(.),'')='%s']", name.trim());
        break;
        
      default:
        throw new InvalidArgumentException("Invalid application name");
    }
    // Get attribute
    this.driverCustom.isElementVisible(attributeXpath, Duration.ofSeconds(10));
    String attribute = this.driverCustom.getAttribute(attributeXpath, "aria-expanded");
    if(expandOrCollapse.equalsIgnoreCase("expand")) {
      if(attribute.equals("true")) {
        return new TestAcceptanceMessage(true, String.format("Verify that '%s' is expanded", name));
      }
      return new TestAcceptanceMessage(false, String.format("Verify that '%s' is not expanded", name));
    }
    else if (expandOrCollapse.equalsIgnoreCase("collapse")) {
      if(attribute.equals("false")) {
        return new TestAcceptanceMessage(true, String.format("Verify that '%s' is collapsed", name));
      }
      return new TestAcceptanceMessage(false, String.format("Verify that '%s' is not collapsed", name));
    }
    return new TestAcceptanceMessage(false, "FAILED - Verify that method failed!");
  }
  
  /**
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#verifyThatOpenNewAddedConfigurationSucessfully(Map)}.
   *
   * @param additionalParams - store keys and values:
   *        expectedProjectArea - expected project area name <br>
   *        applicationName - name of aplication as: RM, QM, CCM
   *        expectedComponent - expected current component name <br>
   *        expectedStream - expected current stream name <br>
   * @param lastResult the last result
   * @return test acceptance message
   * 
   * @author LTU7HC
   */
  public TestAcceptanceMessage verifyVerifyThatOpenNewAddedConfigurationSucessfully(
      final Map<String, String> additionalParams, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true, "Verify that users open configuration successfully");
    }
    return new TestAcceptanceMessage(false, "Verify that users open configuration not successfully");
  }
  
  /**
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#closeCurrentWindowAndSwitchToAnother}.
   * @param lastResult the last result
   * @return test acceptance message
   * 
   * @author LTU7HC
   */
  public TestAcceptanceMessage verifyCloseCurrentWindowAndSwitchToAnother(final String lastResult) {
    if (this.driverCustom.getWebDriver().getWindowHandles().size() == 1) {
      return new TestAcceptanceMessage(true, "Verify that users close current window and swith to another window successfully");
    }
    return new TestAcceptanceMessage(false, "Verify that users close current window and swith to another window NOT successfully");
  }
  
  /**
   * <p>
   * Verifies the action of {@link GlobalConfigurationsPage#switchToWindowWithTitleContains(String)}.
   * @param expectedTitle expected title
   * @param lastResult the last result
   * @return test acceptance message
   * 
   * @author LTU7HC 
   */
  public TestAcceptanceMessage verifySwitchToWindowWithTitleContains(final String expectedTitle,
      final String lastResult) {
    if (this.driverCustom.getCurrentPageTitle().trim().contains(expectedTitle)) {
      return new TestAcceptanceMessage(true,
          String.format("Verify that users switch to window successfully.\nPage title: '%s' contains expected: '%s'",
              this.driverCustom.getCurrentPageTitle().trim(), expectedTitle));
    }
    return new TestAcceptanceMessage(false,
        String.format(
            "Verify that users switch to window NOT successfully.\nPage title: '%s' NOT contains expected: '%s'",
            this.driverCustom.getCurrentPageTitle().trim(), expectedTitle));
  }
}
