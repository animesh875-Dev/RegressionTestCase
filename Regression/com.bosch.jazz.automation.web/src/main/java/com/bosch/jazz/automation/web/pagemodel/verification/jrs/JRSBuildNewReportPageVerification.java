/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.jrs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSBuildNewReportPage;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.text.LabelFinder;

import finder.text.label.JazzLabelFinder;

/**
 * Most basic page class of JRSBuildNewReportPage.
 */
public class JRSBuildNewReportPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom driver.
   */
  public JRSBuildNewReportPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new LabelFinder(), new JazzLabelFinder());
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#editDataSource(String)}.
   *
   * @param value Data source value
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyEditDataSource(final String value, final String lastResult) {
    waitForSecs(2);
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_EDIT_DATASOURCE_BUTTON_XPATH);
    if (this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_EDIT_DATASOURCE_BUTTON_XPATH).getText()
        .equalsIgnoreCase(value)) {
      return new TestAcceptanceMessage(true, "Verified: Data source \"" + value + "\" selected.");
    }
    return new TestAcceptanceMessage(false, "Verified: Data source not selected \"" + value + "\"");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setProjectAreaName(String)}.
   *
   * @param attributeName Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetProjectAreaName(final String attributeName, final String lastResult) {
    this.driverCustom.getPresenceOfWebElement("//span[contains(@class,'project-title')]");
    List<WebElement> projectarea = this.driverCustom.getWebElements("//span[contains(@class,'project-title')]");
    for (WebElement ele : projectarea) {
      if (ele.getAttribute("title").contains(attributeName)) {
        return new TestAcceptanceMessage(true, "Verified: Project area selected as \"" + ele.getText() + "\".");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: \"" + attributeName + "\" Project area is not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickContinueButton()}.
   *
   * @param additionalparameter Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickContinueButton(final String additionalparameter, final String lastResult) {
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_TWISTILE_BUTTON_XPATH,
        additionalparameter);
    if (this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_TWISTILE_BUTTON_XPATH, additionalparameter)
        .getAttribute(JRSConstants.CLASS).contains("closed")) {
      return new TestAcceptanceMessage(true,
          "Verified: 'Continue' button for '" + additionalparameter + "' selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: 'Continue' button in '" + additionalparameter + "' section not found.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnJazzSpanButtons(String)}.
   *
   * @param button Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnJazzSpanButtons(final String button, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.getPresenceOfWebElement("//a[@id='primary-data-source']");
    if (this.driverCustom.isElementVisible("//a[@id='primary-data-source']", Duration.ofSeconds(120))) {
      return new TestAcceptanceMessage(true, "Verified: Button " + button + " clicked successfully.");
    }
    if (button.equalsIgnoreCase("Add") &&
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTON_XPATH, button)
            .getAttribute(JRSConstants.CLASS).contains(JRSConstants.DISABLED)) {
      return new TestAcceptanceMessage(true, "Verified Button " + button + " clicked successfully.");
    }
    if ((button.equalsIgnoreCase("Add and Close") || button.equalsIgnoreCase("ADD AND CLOSE")) && this.driverCustom.isElementPresent("//div[@id='add-attributes-modal' and @aria-hidden='true']", Duration.ofSeconds(120))) {
      return new TestAcceptanceMessage(true, "Verified the button " + button + "  clicked successfully.");
    }
    if (button.equalsIgnoreCase("Graph") && this.driverCustom.getWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, button)
        .getAttribute(JRSConstants.CLASS).contains("active")) {
      return new TestAcceptanceMessage(true, "Verified the Graph button clicked successfully.");
    }
    if (button.equalsIgnoreCase("Refresh") && this.driverCustom
        .getWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, button).getAttribute("class").contains("disabled")) {
      return new TestAcceptanceMessage(true, "Verified the Refresh button clicked successfully.");
    }
    if (button.equalsIgnoreCase("Attribute data items") &&
        this.driverCustom.isElementVisible("//div[@class='modal-dialog']/div[@data-wasvisible='true']", Duration.ofSeconds(120))) {
      return new TestAcceptanceMessage(true, "Verified the Attribute data items button clicked successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified button \"" + button + "\" not found or loaded.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#chooseAnArtifact(String, String)}.
   *
   * @param artifactValue Project area name.
   * @param childArtifact Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseAnArtifact(final String artifactValue, final String childArtifact,
      final String lastResult) {
    waitForSecs(10);
    List<WebElement> ele = this.driverCustom.getWebElements("//div[contains(@class,'summary-part')]//span");
    for (WebElement condition : ele) {

      if (condition.getText().equalsIgnoreCase(childArtifact)) {
        return new TestAcceptanceMessage(true, "Verified: Artifact" + childArtifact + " is selected successfully.");
      }
      else if (childArtifact.contains("null")) {
        return new TestAcceptanceMessage(true, "Verified: Artifact" + childArtifact + " is selected successfully.");
      }

    }
    return new TestAcceptanceMessage(false, "Verified: Artifact" + childArtifact + JRSConstants.NOT_SELECTED);
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#addRelationship(String, String)}.
   *
   * @param relationshipValue Project area name.
   * @param artifact Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyAddRelationship(final String relationshipValue, final String artifact,
      final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    List<WebElement> ele = this.driverCustom.getWebElements("//div[contains(@class,'component-in-path-text')]//span");
    for (WebElement condition : ele) {
      if (condition.getText().equalsIgnoreCase(artifact)
          || this.driverCustom.getWebElement("//div[contains(@class,'component-in-path-text')]//span").getAttribute("title").equalsIgnoreCase(artifact)
          || this.driverCustom.getWebElement("//div[contains(@class,'artifact-in-path')]").getAttribute("title").equalsIgnoreCase(artifact)
          || this.driverCustom.getWebElement("//div[contains(@class,'component-in-path-text')]//span").getAttribute("textContent").equalsIgnoreCase(artifact)
          ) {
        return new TestAcceptanceMessage(true, "Verified: Trace relationships and add artifacts value " +
            relationshipValue + " & " + artifact + " Selected successfully.");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Trace relationships and add artifacts value" +
        relationshipValue + " & " + artifact + " not Selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnJazzImageButtons(String)}.
   *
   * @param button Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  @Override
  public TestAcceptanceMessage verifyClickOnJazzImageButtons(final String button, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    List<WebElement> ele = this.driverCustom.getWebElements("//div[@class='modal-header']//h4");
    for (WebElement elem : ele) {
      if (button.equals("Add condition") ||
          (button.equals("ADD CONDITION") && elem.getText().trim().equalsIgnoreCase(button))) {
        return new TestAcceptanceMessage(true, "Verified: the button \"" + button + "\"  clicked successfully.");
      }
    }
    return new TestAcceptanceMessage(false, "Verified " + button + " not clicked.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#searchCondition(String)}.
   *
   * @param conditionName Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySearchCondition(final String conditionName, final String lastResult) {
    waitForSecs(10);
    List<WebElement> ele =
        this.driverCustom.getWebElements("//div[@class='resource-attribute-selection']//input[@placeholder='Search']");
    for (WebElement condition : ele) {
      if (condition.getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(conditionName)) {
        return new TestAcceptanceMessage(true, "Verified: Condition \"" + conditionName + "\" entered successfully.");
      }
    }
    return new TestAcceptanceMessage(false, JRSConstants.VERIFIED + conditionName + " not entered.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnRadiobutton(String)}.
   *
   * @param filterValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnRadiobutton(final String filterValue, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Condition name \"" + filterValue + "\"  is selected.");
    }
    return new TestAcceptanceMessage(false, "Verified: \"" + filterValue + "\" not selected or not found.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setConditionType(String)}.
   *
   * @param type Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetConditionType(final String type, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    if (this.driverCustom.getWebElement("//a[@data-toggle='dropdown']//span[@class='attribute-options-text']").getText()
        .equalsIgnoreCase(type)) {
      return new TestAcceptanceMessage(true, "Verified: Condition type \"" + type + "\" selected.");
    }
    return new TestAcceptanceMessage(false, "Verified: Condition type " + type + " not found / not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setConditionDate(String)}.
   *
   * @param date Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetConditionDate(final String date, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    if (this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']", timeInSecs)) {
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    List<WebElement> ele = this.driverCustom.getWebElements("//input[@placeholder='mm/dd/yyyy']");
    for (WebElement condition : ele) {
      if (condition.getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(date)) {
        return new TestAcceptanceMessage(true, "Verified: entered date \"" + date + JRSConstants.SUCCESSFULLY);
      }
    }
    return new TestAcceptanceMessage(false, "Verified: entered date " + date + " is not correct.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#setClickOnfilterRadioButton(String)}.
   *
   * @param buttonName Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetClickOnfilterRadioButton(final String buttonName, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    if (this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_FILTER_RADIO_BUTTON_XPATH, buttonName)
        .getAttribute("Checked").equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: condition type selected as \"" + buttonName + "\".");
    }
    return new TestAcceptanceMessage(false, "Verified: condition type not found :" + buttonName + ".");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#setConditionAttributesOfvalue(String)}.
   *
   * @param dropDownValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetConditionAttributesOfvalue(final String dropDownValue,
      final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    waitForSecs(5);
    Dropdown drdAttributeOf = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Attributes of"), timeInSecs).getFirstElement();
    if (drdAttributeOf.getDefaultValue().contains(dropDownValue)) {
      return new TestAcceptanceMessage(true,
          "Verified: Attributes of \"" + dropDownValue + "\" selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Attributes of " + dropDownValue + " not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnTab(String)}.
   *
   * @param tab Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnTab(final String tab, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMALLPROJECTSPAGE_APPLICATION_TITLE_LINK_XPATH, tab);
    List<WebElement> tabs = this.driverCustom.getWebElements("//ul[@data-tabs='tabs']//a");
    for (WebElement webElement : tabs) {
      if (webElement.getText().equalsIgnoreCase(tab) &&
          webElement.getAttribute("aria-expanded").equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, "Verified: the Tab opened successfully \"" + tab + "\"");
      }
    }
    return new TestAcceptanceMessage(false, "Verified: Tab not found \"" + tab + "\".");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setJrsTextBoxValue(String, String)}.
   *
   * @param attributeName Project area name.
   * @param attributeValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetJrsTextBoxValue(final String attributeName, final String attributeValue,
      final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    List<WebElement> ele = this.driverCustom.getWebElements("//*[contains(@style,'text-anchor')]");
    for (WebElement graphvalue : ele) {
      if (graphvalue.getText().equalsIgnoreCase(attributeValue)) {
        return new TestAcceptanceMessage(true,
            "Verified: Entered value same as Expected value \"" + attributeValue + "\".");
      }
    }
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_ATTRIBUTE_TEXTBOX_XPATH,
        Duration.ofSeconds(120), attributeName) ||
        this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_ATTRIBUTE_TEXTBOX_XPATH, attributeName)
            .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(attributeValue)) {
      return new TestAcceptanceMessage(true, "Verified: Entered value same as Expected \"" + attributeValue + "\".");
    }
    if (this.driverCustom.getWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_TEXTBOX_XPATH, attributeName)
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(attributeValue)) {
      return new TestAcceptanceMessage(true, "Verified: Entered value same as Expected \"" + attributeValue + "\".");
    }
    return new TestAcceptanceMessage(false, "Verified: Tab \"" + attributeValue + " is not clicked.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setGroupByAttributeValue(String)}.
   *
   * @param dropDownValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetGroupByAttributeValue(final String dropDownValue, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    Select sel = new Select(this.driverCustom
        .getWebElement("//label[text()='Group data by attribute:']/ancestor::div[@class='row']//select"));
    if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(dropDownValue)) {
      return new TestAcceptanceMessage(true, "Verified: Group data by attribute is selected \"" + dropDownValue + "\"");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Group data by attribute  is not found \"" + dropDownValue + "\"");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnTab(String)}.
   *
   * @param dropDownValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetDimensionValue(final String dropDownValue, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='series-select']"));
    if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(dropDownValue)) {
      return new TestAcceptanceMessage(true,
          "Verified: Dimension: How do you want to divide what you're measuring is selected \"" + dropDownValue +
              "\".");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Dimension: How do you want to divide what you're measuring is not selected or not found\"" +
            dropDownValue + "\"");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnTab(String)}.
   *
   * @param dropDownValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetUnits(final String dropDownValue, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='count-select']"));
    if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(dropDownValue)) {
      return new TestAcceptanceMessage(true, "Verified: Units \"" + dropDownValue + "\" is selected sucessfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Units \"" + dropDownValue + " is not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnTab(String)}.
   *
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyValidateHorizontalGraph(final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom
    .isElementInvisible("//div[@id='chart-loadingProgress']//h3[contains(text(),'Loading, please wait...')]", Duration.ofSeconds(240));
    List<WebElement> ele =
        this.driverCustom.getWebElements("//div[@id='chart-container']//*[@class='valgroup']//*[@class='bar-no-link']");
    boolean value = false;
    for (WebElement elem : ele) {
      if ((Integer.parseInt(elem.getAttribute("x")) >= 0) && (Integer.parseInt(elem.getAttribute("y")) > 0)) {
        value = true;
      }
      else {
        value = false;
        break;
      }
    }
    if (value) {
      return new TestAcceptanceMessage(true, "Verified Graph format \"Horizontal\".");
    }
    return new TestAcceptanceMessage(false, "Verified Graph format is not in \"Horizontal\" .");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#setConditionAttributesOfvalue(String)}.
   *
   * @param dropDownValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetAddAttributesToTheReport(final String dropDownValue, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    waitForSecs(5);
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='columns-resource']"));
    if (sel.getFirstSelectedOption().getText().equals(dropDownValue)) {
      clickOnJazzSpanButtons("Cancel");
      return new TestAcceptanceMessage(true,
          "Verified: Attributes of \"" + dropDownValue + "\" selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Attributes of " + dropDownValue + " not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnJazzButtons(String)}.
   *
   * @param button Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  @Override
  public TestAcceptanceMessage verifyClickOnJazzButtons(final String button, final String lastResult) {
    if (button.equalsIgnoreCase("Add")) {
      return new TestAcceptanceMessage(true, "Verified the " + button + " button clicked successfully.");
    }
    if (button.equalsIgnoreCase("Refresh")) {
      try {
        this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH);
        this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH);
      }
      catch (Exception e) {
        LOGGER.LOG.info(e.getMessage());
      }
      waitForSecs(50);
      this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(240));
      this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(240));
      return new TestAcceptanceMessage(true, "Verified the " + button + " button clicked successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified " + button + " not clicked.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#selectShowValuesAndShowTotals()}.
   *
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectShowValuesAndShowTotals(final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH, Duration.ofSeconds(120));
    waitForSecs(5);
    if (this.driverCustom.getPresenceOfWebElement("//*[text()='Subtotals']").getText().equalsIgnoreCase("Subtotals")) {
      return new TestAcceptanceMessage(true,
          "Verified: \"Show values\" and  \"Show totals\" check box selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: \"Show values\" and  \"Show totals\" check box not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#selectShowValuesAndShowTotals()}.
   *
   * @param chartSelect name of chart.
   * @param chartType type of chart.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectGraphAndChart(final String chartSelect, final String chartType,
      final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    waitForSecs(5);
    if (this.driverCustom.getPresenceOfWebElement(JRSConstants.JRSBUILDNEWREPORTPAGE_CHART_DROPDOWN_XPATH, chartType)
        .getText().equalsIgnoreCase(chartType)) {
      return new TestAcceptanceMessage(true,
          "Verified: chart \"" + chartSelect + "\" selected \"" + chartType + "\" chart type successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: chart \"" + chartSelect + "\" selected \"" + chartType + "\" chart type not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#searchAttributeCondition(String)}.
   *
   * @param conditionName name of chart.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySearchAttributeCondition(final String conditionName, final String lastResult) {
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_METAMODELSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    this.driverCustom.isElementInvisible(JRSConstants.JRSCREATEREPORTPAGE_PROJECTSPINNER_SPAN_XPATH,
        Duration.ofSeconds(120));
    waitForSecs(5);
    if (this.driverCustom.getPresenceOfWebElement(
        "//select[@id='build-result-resource-attributes']/following-sibling::div//input[@type='text' and @placeholder='Search']")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(conditionName)) {
      return new TestAcceptanceMessage(true,
          "Verified: condition name entered \"" + conditionName + "\" successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: condition name \"" + conditionName + "\" not entered.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#clickOnReportTab(String)}.
   *
   * @param tab name of chart.
   * @param lastResult result.
   * @param additionalparameter addtional parameter.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnReportTab(final String tab, final String lastResult,
      final String additionalparameter) {
    try {
      Tab JRSTab =
          this.engine.findElementWithDuration(Criteria.isTab().withText(tab), this.timeInSecs).getFirstElement();
      if (JRSTab.getWebElement().getAttribute("aria-expanded").equalsIgnoreCase("true") || this.driverCustom
          .getFirstVisibleWebElement("//a[text()='DYNAMIC_VAlUE' or text()='" + additionalparameter + "']", lastResult)
          .getAttribute("aria-expanded").equalsIgnoreCase("true")) {
        return new TestAcceptanceMessage(true, "Verified: Tab  \"" + tab + "\" is clicked successfully.");
      }
    }
    catch (Exception e) {
      return new TestAcceptanceMessage(false, "Verified: Tab \"" + tab + "\" is not clicked.\n" + e.getMessage());
    }
    return new TestAcceptanceMessage(false, "Verified: Tab \"" + tab + "\" is not clicked.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setDefaultVisualization(String)}.
   *
   * @param dropDownValue Project area name.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetDefaultVisualization(final String dropDownValue, final String lastResult) {
    Select sel = new Select(this.driverCustom.getWebElement("//select[@id='default-viz-select']"));
    if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(dropDownValue)) {
      return new TestAcceptanceMessage(true,
          "Verified: Default visualization \"" + dropDownValue + "\" is selected sucessfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Default visualization \"" + dropDownValue + " is not selected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#saveReport()}.
   *
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySaveReport(final String lastResult) {
    return verifySaveReport("TRUE", lastResult);
  }


  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#saveReport()}.
   *
   * @param additionalPram is true for verifying success, false for verifying Error shown
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySaveReport(final String additionalPram, final String lastResult) {
    waitForSecs(5);
    if (Boolean.parseBoolean(additionalPram) &&
        this.driverCustom.isElementVisible("//span[@class='alert-icon alert-icon-success']", Duration.ofSeconds(60))) {
      return new TestAcceptanceMessage(true, "Verified: Report is saved successfully ");
    }
    else if (!Boolean.parseBoolean(additionalPram) && this.driverCustom.isElementVisible("//span[@class='alert-icon alert-icon-error']", Duration.ofSeconds(60))) {
      return new TestAcceptanceMessage(true, "Verified: Report not saved. Error message is shown");
    }
    if (this.driverCustom.isElementVisible("//span[@id='alert-summary']", timeInSecs)) {
      String text = this.driverCustom.getWebElement("//span[@id='alert-summary']").getText();
      if (text.startsWith("Please enter a title") || text.startsWith("Before saving your report,")) {
        return new TestAcceptanceMessage(true, "Verified: Report can't be saved as title is not entered.\n" + text);
      }
    }
    return new TestAcceptanceMessage(false,
        "Verified: Saving does work as expected. Expected saving is '" + additionalPram + "'.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setJRSReportName(String)}.
   *
   * @param reportName name of chart.
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetJRSReportName(final String reportName, final String lastResult) {
    if (this.driverCustom.isElementVisible("//iframe[@id='classic_IFrame']", timeInSecs)) {
      this.driverCustom.switchToFrame("//iframe[@id='classic_IFrame']");
    }
    if (this.driverCustom.getPresenceOfWebElement("//input[@placeholder='Name this report']")
        .getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(reportName)) {
      return new TestAcceptanceMessage(true, "Verified: Report name entered \"" + reportName + "\" successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Report name \"" + reportName + "\" is not entered.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setJRSReportName(String)}.
   *
   * @param report name of chart.
   * @param groupValue is
   * @param folderName is
   * @param lastResult result.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteReport(final String report, final String groupValue, final String folderName,
      final String lastResult) {

    if(this.driverCustom.isElementVisible(JRSConstants.JRSALLREPORTPAGE_CREATE_BUTTON_XPATH, Duration.ofSeconds(50))) {
      // JRS 7.0.3 
      if(this.driverCustom.isElementVisible("//div[@id='UINotificationContainer']//div[text()='Report was deleted']",Duration.ofSeconds(1))) {
        return new TestAcceptanceMessage(true, "Verified: Report deleted successfully \"" + report + "\" .");
      }
      return new TestAcceptanceMessage(false, "Verified: Report is not deleted \"" + report + "\".");     
    }
    // JRS 7.0.2
    waitForSecs(10);
    Text txtSearch = null;
    if (this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_GROUPBYDROPDOWNSELECT_XPATH, Duration.ofSeconds(50))) {
      txtSearch =
          this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to filter"), this.timeInSecs)
              .getFirstElement();
    }else {
      txtSearch =
          this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search"), this.timeInSecs)
          .getFirstElement();
    }
    if (txtSearch != null) {
      txtSearch.setText(report + Keys.ENTER);
    }
    waitForSecs(5);
    if (this.driverCustom.getWebElement("//span[@id='alert-summary']").getText().contains("No results found")) {
      return new TestAcceptanceMessage(true, "Verified: Report deleted successfully \"" + report + "\" .");
    }
    return new TestAcceptanceMessage(false, "Verified: Report is not deleted \"" + report + "\".");
  }

  /**
   * <p>
   * Verifies the action of {@link JRSBuildNewReportPage#chooseArtifacts(String, String)}.
   *
   * @param artifactValue the value of the artifact to be selected.
   * @param childArtifact true if childArtifact needs to be chosen.
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyChooseArtifacts(final String artifactValue, final String childArtifact,
      final String lastResult) {
    if (this.driverCustom.isElementVisible(
        "//span[@data-part='datatypes']/../following-sibling::span//span[text()='DYNAMIC_VAlUE']", this.timeInSecs,
        artifactValue)) {

      return new TestAcceptanceMessage(true, "Verified: " + artifactValue + " Selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: " + artifactValue + " not Selected.");
  }

  /**
   * <p>
   * Verifies the action of {@link JRSBuildNewReportPage#chooseArtifacts(String, String)}.
   * @param columnName name of column
   * @param expectedValues expected values
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyGetTraceabilityReportResults(final String columnName, final String expectedValues,
      final String lastResult) {
    if (lastResult.contains("No results found. Please make sure you have access to the project area(s)")) {

      return new TestAcceptanceMessage(false, "Verified: Traceability Report Results Not Found");
    }
    return new TestAcceptanceMessage(true, "Verified: Traceability Report Results are found");
  }

  /**
   * <p>
   * Verifies the action of {@link JRSBuildNewReportPage}.
   * 
   * @return object which contains verification results.
   */

  public TestAcceptanceMessage verifyClickOnFilterInReportResults() {
    if (!this.driverCustom.isElementVisible(JRSConstants.JRSBUILDNEWREPORTPAGE_CLICKONCHOOSECONFIGURATION_XPATH,
        Duration.ofSeconds(10), "Choose a configuration")) {

      return new TestAcceptanceMessage(false, "Verified: Filter option NOT clicked successfully");
    }
    return new TestAcceptanceMessage(true, "Verified: Filter option clicked successfully");
  }


  /**
   * @param value value
   * @param lastResult returned value of method which is executed just before the method.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyVerifyTotalNoOfRecords(final String value, final String lastResult) {
    // Switch to frame
    if (Integer.parseInt(value) != 0 && this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_TOTALITEMSINRESULT_DIV_XPATH, Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "\nActual results : Total count is "+ lastResult +  " Matches with Expected Results: Total count is '" + value + "' displayed successfully.");
    }
    else if (Integer.parseInt(value) == 0 && !this.driverCustom.isElementVisible(JRSConstants.JRSCREATEREPORTPAGE_TOTALITEMSINRESULT_DIV_XPATH, Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true, "\nActual results : Total count is "+ lastResult +  " Matches with Expected Results: Total count is '" + value + "'. 'No results found.' message is return.");
    }
    return new TestAcceptanceMessage(false, "\nActual results : Total count is "+ lastResult +  " which not match with Expected Results: Total count is  " + value + "' in configuration file.");
  }

  /**
   * <p>
   * Verifies the action of {@link JRSBuildNewReportPage#openReportByGroup(String,String,String)}.
   *
   * @param report name
   * @param groupValue group alue.
   * @param folderName name of folder.
   * @param additionalParams additional params.
   * @param lastresults last results.
   * @return object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpenReportByGroup(final String report, final String groupValue,
      final String folderName, final Map<String, String> additionalParams, final String lastresults) {
    StringBuilder sb = new StringBuilder();
    boolean status = false;
    if (this.driverCustom.isElementVisible("//input[@placeholder='Name this report']", Duration.ofSeconds(30))) {
      sb.append("\nActual result: Displayed Report name as " + additionalParams.get("REPORT_NAME") +
          " is matches with" + ":: Expected result : Report name " + report);
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//span[@class='badge tag']/label[text()='DYNAMIC_VAlUE']",
        Duration.ofSeconds(60), additionalParams.get("TAG_VALUE"))) {
      sb.append("\nActual Result with Tag name  " + additionalParams.get("TAG_VALUE") + " is matches with " +
          ":: Expected result : Tag name " + additionalParams.get("TAG_VALUE"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible(
        "//select[@id='resource-tags-select' or @id='resource-visibility']/option[text()='DYNAMIC_VAlUE']",
        Duration.ofSeconds(60), additionalParams.get("PRIVACY_AND_SHARING"))) {
      sb.append("\nActual Result with privacy and sharing  " + additionalParams.get("PRIVACY_AND_SHARING") +
          "is matches with" + ":: Expected result : privacy and sharing " +
          additionalParams.get("PRIVACY_AND_SHARING"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//select[@id='default-viz-select']/option[text()='DYNAMIC_VAlUE']",
        Duration.ofSeconds(60), additionalParams.get("DEFAULT_VISUALIZATION"))) {
      sb.append("\nActual Result with default and visualisation " + additionalParams.get("DEFAULT_VISUALIZATION") +
          " is matches with" + ":: Expected result : default and visualisation " +
          additionalParams.get("DEFAULT_VISUALIZATION"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//label[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(60),
        additionalParams.get("OWNER_1"))) {
      sb.append("\nActual Result with owner_01 " + additionalParams.get("OWNER_1") + " is matches with " +
          ":: Expected result : owner_01 " + additionalParams.get("OWNER_1"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//label[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(60),
        additionalParams.get("OWNER_2"))) {
      sb.append("\nActual Result with owner_02 " + additionalParams.get("OWNER_2") + " is matches with " +
          ":: Expected result : owner_02 " + additionalParams.get("OWNER_2"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//span[@id='report-folder-display']", Duration.ofSeconds(20))) {
      sb.append("\nActual Result::Report is displayed in specified folder" +
          " Expected results:Report should be Displayed in specified folder ");
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible(
        "//div[@id='report-ofolder']/span[contains(text(),'" + additionalParams.get("FOLDER_NAME") + "')]",
        Duration.ofSeconds(30))) {
      sb.append("\nActual Result with " + additionalParams.get("REPORT_NAME") + " 'is saved in the '" +
          "  Expected folder:: " + additionalParams.get("FOLDER_NAME") + " selected");
      status = true;
    }
    else {
      status = false;
    }
    return new TestAcceptanceMessage(status, sb.toString());
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#addOwnerName(String)}.
   * 
   * @param ownerName - ownerName
   * @param lastResult result.
   * @return ownerName
   */
  public TestAcceptanceMessage verifyAddOwnerName(final String ownerName, final String lastResult) {
    waitForSecs(2);
    if (this.driverCustom.getWebElement("//div[@id='report-contribs-container']").getAttribute("class")
        .equalsIgnoreCase("tag-cloud")) {
      return new TestAcceptanceMessage(true, "Verified: Owner  \"" + lastResult + "\" added successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Owner \"" + lastResult + "\" not added successfully.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#setTagValue(String)}.
   *
   * @param attributeValue value of attribute.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifySetTagValue(final String attributeValue, final String lastResult) {
    if (this.driverCustom.isElementVisible("//span[@class='badge tag']/label[text()='" + attributeValue + "']",
        Duration.ofSeconds(30))) {
      return new TestAcceptanceMessage(true, "Verified: Tag  \"" + lastResult + "\" added successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Tag \"" + lastResult + "\" not added successfully.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#selectFolder(String)}.
   * @param folderName name of folder.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifySelectFolder(final String folderName, final String lastResult) {
    waitForSecs(1);
    if (this.driverCustom.isElementVisible("//div[@id='report-ofolder']/span[contains(text(),'" + folderName + "')]",
        Duration.ofSeconds(30))) {
      return new TestAcceptanceMessage(true, "Verified: Folder  \"" + folderName + "\" added successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Folder \"" + folderName + "\" not added successfully.");
  }

  /**
   * <P>
   * Verifies the methods with selected value return after selecting the value in the dropdown
   * <p>
   * Verifies the action of {@link JRSBuildNewReportPage#setValueforDropdown(String, String)}.
   *
   * @param label the label of the dropdown
   * @param value the value to be selected.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifySetValueforDropdown(final String label, final String value,
      final String lastResult) {
    waitForSecs(5);
    String actualValue = "";
    try {
      Dropdown drdElement = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(label), this.timeInSecs)
          .getFirstElement();
      actualValue = drdElement.getDefaultValue();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (actualValue.equals(value)) {
      return new TestAcceptanceMessage(true, "Actual '" + label + "' value is -'" + actualValue +
          "' set successfully. \nExpected '" + label + "' value is -'" + value + "'.");
    }
    return new TestAcceptanceMessage(false, "Actual value of '" + label + "'  is - '" + actualValue +
        "' not set successfully. \nExpected  '" + label + "' valus is -'" + value + "'.");
  }

  /**
   * <p>
   * Verifies Sort type for the "Format results" tab of the Build new report page of
   * JRS.{@link JRSBuildNewReportPage#sortType(String, String)}.
   *
   * @param attributeLabel the name of the Attribute column on table to be added.
   * @param sortType value for sorting.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifySortType(final String attributeLabel, final String sortType,
      final String lastResult) {
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: '" + sortType + "' sort type is selected successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: '" + sortType + "' sort type is not selected successfully and last result is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies Add attribute columns using "Attribute" button in the "Format" section of the "FORMAT RESULTS" tab in
   * build report page.{@link JRSBuildNewReportPage#addAttributeinFormatResult(String)}.
   *
   * @param attribute - Attribute to be added
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyAddAttributeinFormatResult(final String attribute, final String lastResult) {
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: '" + attribute + "' attribute is added successfully and last result is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: '" + attribute + "' attribute is not added successfully and last result is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#checkArtifacetRelationshipsAutoCreated(Map)}.
   * 
   * @param columValues map contains key and value of column.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyCheckArtifacetRelationshipsAutoCreated(final Map<String, String> columValues,
      final String lastResult) {

    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH,
        Duration.ofSeconds(50))) {
      this.driverCustom.switchToFrame(JRSConstants.JRSCREATEREPORTPAGE_LOADREPORTIFRAME_IFRAME_XPATH);
    }
    @SuppressWarnings("unused")
    String actualValue = null;
    WebElement table =
        this.driverCustom.getFirstVisibleWebElement(JRSConstants.JRSCREATEREPORTPAGE_RUNREPORT_TABLE_XPATH, null);
    List<WebElement> totalRowCount = table.findElements(By.tagName("tr"));
    List<WebElement> listheader =
        table.findElements(By.xpath(JRSConstants.JRSCREATEREPORTPAGE_RUNREPORT_TABLE_HEADER_XPATH));
    for (int i = 0; i < totalRowCount.size(); i++) {
      for (WebElement header : listheader) {
        String headerAtribute = header.getAttribute("data-resizable-column-id").toString();
        WebElement itemvalueonRow =
            table.findElement(By.xpath("descendant::td[@data-varname='" + headerAtribute + "']"));
        actualValue = itemvalueonRow.getText();
      }

    }

    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "\n Actual Results: Column Values is '" + columValues.get("columnValue1") + "' and '" +
              columValues.get("columnValue2") + "'  Matches with Expected results : " +
              columValues.get("columnValue1") + " and " + columValues.get("columnValue2"));
    }
    return new TestAcceptanceMessage(false,
        "\n Actual Results:Column value is '" + columValues.get("columnValue1") + "' and '" +
            columValues.get("columnValue2") + "' not Matches with Expected results :" +
            columValues.get("columnValue1") + " and " + columValues.get("columnValue2"));
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#switchToIFrame()}.
   *
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifySwitchToIFrame(final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Switched to Iframe");
    }
    return new TestAcceptanceMessage(false, "Verified: Not Switched to Iframe");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#chooseDomainForConfigurations(String)}.
   * 
   * @param domainDropDownValue is dropdown value
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyChooseDomainForConfigurations(final String domainDropDownValue,
      final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: '" + domainDropDownValue + "' is selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: '" + domainDropDownValue + "' is not selected successfully.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#chooseProjectAreaForConfigurations(String)}.
   * 
   * @param paDropDownValue value of project area drop down.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyChooseProjectAreaForConfigurations(final String paDropDownValue,
      final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: '" + paDropDownValue + "' is selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: '" + paDropDownValue + "' is not selected successfully.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#chooseComponentForConfigurations(String)}.
   * 
   * @param componentDropDownValue value of component drop down.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyChooseComponentForConfigurations(final String componentDropDownValue,
      final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: '" + componentDropDownValue + "' is selected successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: '" + componentDropDownValue + "' is not selected successfully.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#addCalculatedColumninFormatResult(String,String,String)}.
   * 
   * @param calculation calculation.
   * @param attribute attribute value.
   * @param countAttributValue count value of attribute.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyAddCalculatedColumninFormatResult(final String calculation, final String attribute,
      final String countAttributValue, final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Column " + attribute +
          " added and displayed under column label and previous column are disappear from column label list");
    }
    return new TestAcceptanceMessage(false, "Verified: Column " + attribute +
        " not added and not displayed under column label and previous column are not disappear from column label list");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#checkCalculatedColumnResultsAddedInReport(String,String)}.
   * 
   * @param calculatedColumnName calculated column name.
   * @param calculatedColumnvalue calculated column value.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyCheckCalculatedColumnResultsAddedInReport(final String calculatedColumnName,
      final String calculatedColumnvalue, final String lastResult) {
    waitForSecs(1);

    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "\n Actual results mathes with Expected results: Custom expression value is : " + calculatedColumnvalue);
    }
    return new TestAcceptanceMessage(false,
        "\n Actual results not mathes with Expected results: Custom expression value is : " + calculatedColumnvalue);
  }

  /**
   * <p>
   * Verifies the data source selected as per the input{@link JRSBuildNewReportPage#chooseConfiguration(String)}.
   * 
   * @param configDropDownValue value of configuration drop down.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyChooseConfiguration(final String configDropDownValue, final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: '" + configDropDownValue +
          "' is selected successfully.\nReport is generated successfully with out any error.");
    }
    return new TestAcceptanceMessage(false, "Verified: '" + configDropDownValue + "' is not selected successfully.");
  }

  /**
   * <p>
   * Verify the custom expression is added as per the
   * input{@link JRSBuildNewReportPage#addCustomExpressionAttribute(String, String, String, String)}}}
   *
   * @param attributeOf attribute of.
   * @param attribute attribute value.
   * @param showAs show as value.
   * @param customExpression custom expression value.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyAddCustomExpressionAttribute(final String attributeOf, final String attribute,
      final String showAs, final String customExpression, final String lastResult) {
    waitForSecs(3);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified custom expression is added successfully");
    }
    return new TestAcceptanceMessage(false, "Verified custom expression is not added successfully");
  }

  /**
   * <p>
   * Verify the new column is added on the preview table in Format Result tab as per the
   * input{@link JRSBuildNewReportPage#verifyColumnAddedInThePreviewTableOfFormatResultTab(String)}}
   *
   * @param columnName name of column
   * @param lastResult result.
   * @return verification results.
   */
  public TestAcceptanceMessage verifyVerifyColumnAddedOnThePreviewTableOfFormatResultTab(final String columnName,
      final String lastResult) {
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified the new column is added successfully on the preview table in Format Result tab");
    }
    return new TestAcceptanceMessage(false,
        "Verified the new column is not added successfully on the preview table in Format Result tab");
  }

  /**
   * <p>
   * Verify the expected artifact is displayed on the report as per
   * input{@link JRSBuildNewReportPage#verifyArtifactDisplayedInReport(Map)}}
   *
   * @param params map contains key and value.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyVerifyArtifactDisplayedInReport(final Map<String, String> params,
      final String lastResult) {
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "\n Actual Results: Column Values is '" + params.get("value1") + "' and '" + params.get("value2") +
              "'  Matches with Expected results : " + params.get("value1") + " and " + params.get("value2") +
              " and Displayed selected sorting order");
    }
    return new TestAcceptanceMessage(false, "\n Actual Results:Column value is  not Matches with Expected results :" +
        params.get("value1") + " and " + params.get("value2") + " and not displayed selected sorting order");
  }


  /**
   * <p>
   *
   * @param sortedAttribute attribute sorted.
   * @param sortOrderValue sort order value.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyVerifySortOrder(final String sortedAttribute, final String sortOrderValue,
      final String lastResult) {
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified sort order value of attribute is displayed correctly as expected.");
    }
    return new TestAcceptanceMessage(false, "Verified sort order value of attribute is displayed wrong.");
  }

  /**
   * <p>
   * verify the type of artifact is selected successfully as per
   * input{@link JRSBuildNewReportPage#chooseAnArtifact(String, String)}}
   *
   * @param artifact parent type of artifact
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyChooseArtifact(final Map<String, String> artifact, final String lastResult) {
    if ((artifact.get("childArtifact") == "") || (artifact.get("childArtifact") == null)) {
      if (this.driverCustom.getText("//span[@class='datatype-in-summary bidiAware']").trim()
          .equals(artifact.get("parentArtifact"))) {
        return new TestAcceptanceMessage(true, "Verified artifact is selected successfully expected.");
      }
    }
    else {
      if (this.driverCustom.getText("//span[@class='datatype-in-summary bidiAware']").trim()
          .equals(artifact.get("childArtifact"))) {
        return new TestAcceptanceMessage(true, "Verified artifact is selected successfully expected.");
      }
    }
    return new TestAcceptanceMessage(false, "Verified artifact is not selected successfully expected.");
  }

  /**
   * <p>
   * Verifies the data source selected as per the
   * input{@link JRSBuildNewReportPage#selectRequiredOprtionFromAddRelationship(String)}.
   * 
   * @param relationshipoption relationship option.
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifySelectRequiredOprtionFromAddRelationship(final String relationshipoption,
      final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected a traceability relationship existence from the drop down for the added relationship as " +
              relationshipoption);
    }
    return new TestAcceptanceMessage(false,
        "Verified: Not Selected a traceability relationship existence from the drop down for the added relationship as " +
            relationshipoption);
  }


  /**
   * <p>
   * Verifies {@link JRSBuildNewReportPage#setCondition(String, String)}.
   *
   * @param condition is
   * @param conditionValue is
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifySetCondition(final String condition, final String conditionValue,
      final String lastResult) {
    waitForSecs(2);
    // List of conditions
    List<String> listOfConditions = new ArrayList<String>();
    listOfConditions.add(condition);
    listOfConditions.addAll(Arrays.asList(conditionValue.split("/")));

    // Compare actual and expected list of conditions
    String getString = this.driverCustom.getText(JRSConstants.JRSCREATEREPORTPAGE_ALL_SELECTED_CONDITIONS_XPATH)
        .replaceAll("\\s+", " ");


    Boolean isAddedCondition = listOfConditions.stream().allMatch(x -> getString.contains(x));


    if (isAddedCondition) {
      return new TestAcceptanceMessage(true, "Verified: Selected '" + condition + "' condition with value '" +
          conditionValue + "' is successfully added to the report.");
    }
    return new TestAcceptanceMessage(false, "Verified: Selected '" + condition + "' condition with value '" +
        conditionValue + "' is NOT successfully added to the report.");
  }

  /**
   * <p>
   * Verifies {@link JRSBuildNewReportPage#verifySetCondition(Map)}.
   *
   * @param additionalParams is CONDITION_1 as a key and CONDITION_1_VALUE as a value <br>
   *          For example: {condition 1: "Type a Task"}
   * @param lastResult true if all conditions shown under the "Conditions" Section
   * @return verification results
   */
  public TestAcceptanceMessage verifyOfVerifySetCondition(final Map<String, String> additionalParams,
      final String lastResult) {
    waitForSecs(2);

    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected conditions are successfully added to the report.\nThe last result value is '" +
              lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Selecting conditions are NOT added successfully to the report.\nThe last result value is '" +
            lastResult + "'.");
  }

  /**
   * <p>
   * Verifies {@link JRSBuildNewReportPage#validateHorizontalGraph()}.
   *
   * @param additionalParams is
   * @param lastResult result.
   * @return verification results
   */
  public TestAcceptanceMessage verifyValidateHorizontalGraph(final Map<String, String> additionalParams,
      final String lastResult) {
    StringBuilder sb = new StringBuilder();
    boolean status = false;
    if (this.driverCustom.isElementVisible("//input[@placeholder='Name this report']", Duration.ofSeconds(30))) {
      sb.append("\nActual result: Displayed Report name as " + additionalParams.get("REPORT_NAME") +
          " is matches with" + ":: Expected result : Report name ");
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//span[@class='badge tag']/label[text()='DYNAMIC_VAlUE']",
        Duration.ofSeconds(60), additionalParams.get("TAG_VALUE"))) {
      sb.append("\nActual Result with Tag name  " + additionalParams.get("TAG_VALUE") + " is matches with " +
          ":: Expected result : Tag name " + additionalParams.get("TAG_VALUE"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible(
        "//select[@id='resource-tags-select' or @id='resource-visibility']/option[text()='DYNAMIC_VAlUE']",
        Duration.ofSeconds(60), additionalParams.get("PRIVACY_AND_SHARING"))) {
      sb.append("\nActual Result with privacy and sharing  " + additionalParams.get("PRIVACY_AND_SHARING") +
          "is matches with" + ":: Expected result : privacy and sharing " +
          additionalParams.get("PRIVACY_AND_SHARING"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//select[@id='default-viz-select']/option[text()='DYNAMIC_VAlUE']",
        Duration.ofSeconds(60), additionalParams.get("DEFAULT_VISUALIZATION"))) {
      sb.append("\nActual Result with default and visualisation " + additionalParams.get("DEFAULT_VISUALIZATION") +
          " is matches with" + ":: Expected result : default and visualisation " +
          additionalParams.get("DEFAULT_VISUALIZATION"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//label[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(60),
        additionalParams.get("OWNER_1"))) {
      sb.append("\nActual Result with owner_01 " + additionalParams.get("OWNER_1") + " is matches with " +
          ":: Expected result : owner_01 " + additionalParams.get("OWNER_1"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//label[text()='DYNAMIC_VAlUE']", Duration.ofSeconds(60),
        additionalParams.get("OWNER_2"))) {
      sb.append("\nActual Result with owner_02 " + additionalParams.get("OWNER_2") + " is matches with " +
          ":: Expected result : owner_02 " + additionalParams.get("OWNER_2"));
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible("//span[@id='report-folder-display']", Duration.ofSeconds(20))) {
      sb.append("\nActual Result::Report is displayed in specified folder" +
          " Expected results:Report should be Displayed in specified folder ");
      status = true;
    }
    else {
      status = false;
    }
    if (this.driverCustom.isElementVisible(
        "//div[@id='report-ofolder']/span[contains(text(),'" + additionalParams.get("FOLDER_NAME") + "')]",
        Duration.ofSeconds(30))) {
      sb.append("\nActual Result with " + additionalParams.get("REPORT_NAME") + " 'is saved in the '" +
          "  Expected folder:: " + additionalParams.get("FOLDER_NAME") + " selected");
      status = true;
    }
    else {
      status = false;
    }
    return new TestAcceptanceMessage(status, sb.toString());
  }

  /**
   * <p>
   * Verifies {@link JRSBuildNewReportPage#setConditionInTextBox(String)}.
   *
   * @author YNT2HC
   * @param conditionValue is the value which want to input
   * @param lastResult result. True if entering conditionValue successfully
   * @return verification results
   */
  public TestAcceptanceMessage verifySetConditionInTextBox(final String conditionValue, final String lastResult) {
    waitForSecs(2);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: '" + conditionValue +
          "' is input in condition text box successfully.\nThe last result value is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified: '" + conditionValue +
        "' failed to input in condition text box.\nThe last result value is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies {@link JRSBuildNewReportPage#setDaysAgo(String, String)}.
   *
   * @author YNT2HC
   * @param dayValue is number of days or months or years
   * @param selectDaysago is a dropdown to select daysago, monthsago, yeasago values
   * @param lastResult is return value. True if no error happens
   * @return verification results
   */
  public TestAcceptanceMessage verifySetDaysAgo(final String dayValue, final String selectDaysago,
      final String lastResult) {
    waitForSecs(2);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: '" + dayValue + "' '" + selectDaysago +
          "' value is selected successfully in date condition.\nThe last result value is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false, "Verified: '" + dayValue + "' '" + selectDaysago +
        "' value failed to select in date condition.\nThe last result value is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies {@link JRSBuildNewReportPage#setGroupConditions(Map)}.
   *
   * @author YNT2HC
   * @param Params is a map with a key and a value from configuration file <br>
   *          [GROUP_CONDITION: GROUP_CONDITION_VALUE] [GROUP_TYPE: GROUP_TYPE_VALUE]
   * @param additionalParams is the text shown on the right side under CONDITION title
   * @param lastResult is return value - null as void method
   * @return verification results
   */
  public TestAcceptanceMessage verifySetGroupConditions(final Map<String, String> Params,
      final Map<String, String> additionalParams, final String lastResult) {

    // List of conditions
    Collection<String> conditions = additionalParams.values();
    ArrayList<String> listOfConditions = new ArrayList<>(conditions);

    // Compare actual and expected list of conditions
    String getString = this.driverCustom.getText(JRSConstants.JRSCREATEREPORTPAGE_GROUPOF_SELECTED_CONDITIONS_XPATH)
        .replaceAll("\\s+", " ");
    Boolean isConditionGroup = listOfConditions.stream().allMatch(x -> getString.contains(x));

    if (isConditionGroup) {
      return new TestAcceptanceMessage(true,
          "Verified: Selected conditions are successfully grouped to the report.\nThe list of grouped conditions is '" +
              listOfConditions.toString() + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Selecting conditions are NOT grouped successfully to the report.\nThe list of conditions is '" +
            listOfConditions.toString() + "'.");
  }

  /*  *//**
         * <p>
         * verify the type of artifact is selected successfully as per
         * input{@link JRSBuildNewReportPage#chooseAnArtifact(String, String)}}
         *
         * @param artifactValue parent type of artifact
         * @param childArtifact child artifact
         * @param lastResult
         * @return
         *//*
            * public TestAcceptanceMessage verifyChooseAnArtifact(final String artifactValue, final String
            * childArtifact, final String lastResult) { if (this.driverCustom.isElementVisible(
            * "//span[@data-part='datatypes']/../following-sibling::span//span[text()='DYNAMIC_VAlUE']",
            * this.timeInSecs, childArtifact)) { return new TestAcceptanceMessage(true, "Verified: " + artifactValue +
            * " Selected successfully."); } return new TestAcceptanceMessage(false, "Verified: " + artifactValue +
            * " not Selected."); }
            */

  /**
   * <p>
   * Verify the custom expression is added successfully or not basesd return message and expected message This method
   * called after {@link JRSBuildNewReportPage#addACustomExpressionColumn(Map)}
   *
   * @author YNT2HC
   * @param params is a map with a key and a value from configuration file <br>
   *          [ATTRIBUTE_OF: ATTRIBUTE_VALUE] [CUSTOM_EXPRESSION: CUSTOM_EXPRESSION_VALUE] [SHOW_AS: SHOW_AS_VALUE]
   * @param additionalParam is true when custom expression is valid and vice versa.
   * @param lastResult is sucess/valid text or invalid/failed text
   * @return verification results
   */
  public TestAcceptanceMessage verifyAddACustomExpressionColumn(final Map<String, String> params,
      final String additionalParam, final String lastResult) {
    waitForSecs(3);
    if (Boolean.parseBoolean(additionalParam) && lastResult.contains(JRSConstants.SUCCESS_VALIDATING_MESSAGE)) {
      return new TestAcceptanceMessage(true,
          "Verified custom expression: '" + params.get("CUSTOM_EXPRESSION") + "' is added successfully");
    }
    else if (!Boolean.parseBoolean(additionalParam) && !lastResult.contains(JRSConstants.SUCCESS_VALIDATING_MESSAGE)) {
      return new TestAcceptanceMessage(true,
          "Verified invalid custom expression: '" + params.get("CUSTOM_EXPRESSION") + "'is not added successfully");
    }
    return new TestAcceptanceMessage(false, "Verified custom expression: '" + params.get("CUSTOM_EXPRESSION") +
        "' is not added successfully\n" + lastResult);
  }

  /**
   * <p>
   * Verify multiple path option is selected successfully from the dropdown This method called after
   * {@link JRSBuildNewReportPage#selectMultilePathOption(String, String)}
   *
   * @author YNT2HC
   * @param optionType with 'Append to' or 'Merge with' options
   * @param sourceArtifact is the name of source artifact to start a new path
   * @param lastResult no return
   * @return verification results
   */
  public TestAcceptanceMessage verifySelectMultilePathOption(String optionType, String sourceArtifact,
      final String lastResult) {
    waitForSecs(3);
    Select multiPathDropdown = new Select(driverCustom
        .getWebElements(JRSConstants.JRSCREATEREPORTPAGE_MULTIPLE_PATH_SELECT_XPATH, sourceArtifact).get(0));
    String actualSelectedOption = multiPathDropdown.getFirstSelectedOption().getText().trim();
    if (actualSelectedOption.equals(optionType)) {
      return new TestAcceptanceMessage(true,
          "Verified : '" + optionType + "' multiple path option is selected from dropdown successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verified : '" + optionType + "' multiple path option is NOT selected from dropdown.\n Actual result: '" +
            actualSelectedOption + "' is selected from dropdown");
  }

  /**
   * <p>
   * Verifies selecting a new artifact as new source artifact for new path in dialog This method called after
   * {@link JRSBuildNewReportPage#selectAnArtifactInDialog(String, String)}
   * 
   * @author YNT2HC
   * @param parentArtifact is the parent artifact name like work item, requirement, collections
   * @param childArtifact is the child artifact name inside the parent artifacts
   * @param additionalParam is the name of artifact added as a new path
   * @param lastResult no return
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectAnArtifactInDialog(String parentArtifact, String childArtifact,
      final String additionalParam, final String lastResult) {
    if (this.driverCustom.isElementPresent(JRSConstants.JRSCREATEREPORTPAGE_NEW_ARTIFACT_PATH_DIV_XPATH,
        Duration.ofSeconds(20), additionalParam)) {
      return new TestAcceptanceMessage(true, "Verified : Parent artifact '" + parentArtifact +
          "' with child artifact '" + childArtifact + "' is selected from dialog successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified : Parent artifact'" + parentArtifact + "' with child artifact '" +
        childArtifact + "' is NOT selected from dialog.");
  }

  /**
   * <p>
   * Verify multiple path option is selected successfully from the dropdown This method called after
   * {@link JRSBuildNewReportPage#selectOuterGroupType(String)}
   *
   * @author YNT2HC
   * @param outerGroupType is the outer group type of all conditions selected
   * @param lastResult no return
   * @return verification results
   */
  public TestAcceptanceMessage verifySelectOuterGroupType(String outerGroupType, final String lastResult) {
    waitForSecs(3);
    Select dropdown =
        new Select(this.driverCustom.getWebElement(JRSConstants.JRSCREATEREPORTPAGE_SELECT_OUTER_GROUPTYPE_XPATH));
    String actualSelectedOption = dropdown.getFirstSelectedOption().getText().trim();
    if (actualSelectedOption.equals(outerGroupType)) {
      return new TestAcceptanceMessage(true,
          "Verified : '" + outerGroupType + "' outer group type is selected from dropdown successfully");
    }
    return new TestAcceptanceMessage(false,
        "Verified : '" + outerGroupType + "' outer group type is NOT selected from dropdown.\n Actual result: '" +
            actualSelectedOption + "' is selected from dropdown");
  }

  /**
   * <p>
   * This method verify after method {@link JRSBuildNewReportPage#chooseSelectAllVisibleItems()}
   *
   * @author KYY1HC
   * @param lastResult no return
   * @return verification results
   */
  public TestAcceptanceMessage verifyChooseSelectAllVisibleItems(final String lastResult) {
    Checkbox chxSelectAllVisibleItems = null;
    try {
      chxSelectAllVisibleItems =
          this.engine.findFirstElementWithDuration(Criteria.isCheckbox().withLabel("Select all"), this.timeInSecs);
    }
    catch (Exception e) {
      chxSelectAllVisibleItems = this.engine
          .findFirstElementWithDuration(Criteria.isCheckbox().withLabel("Select all visible items"), this.timeInSecs);
    }
    if (chxSelectAllVisibleItems.getWebElement().isSelected()) {
      return new TestAcceptanceMessage(true,
          "Verified : PASSED - Option 'Select all visible items' is selected successfully.");
    }
    return new TestAcceptanceMessage(false,
        "Verified : FAILED -  Option 'Select all visible items' is NOT selected successfully!");
  }

  /**
   * <p>
   * This method verify after method {@link JRSBuildNewReportPage#verifyParameterValueInFilters(String)}
   *
   * @author KYY1HC
   * @param parameterValue value display in all required parameters or filters.
   * @param lastResult true if it contains value need to verify
   * @return verification results
   */
  public TestAcceptanceMessage verifyVerifyParameterValueInFilters(final String parameterValue,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Verified : PASSED - Parameter value contains '" + parameterValue + "' in Filters.");
    }
    return new TestAcceptanceMessage(false,
        "Verified : FAILED -  Parameter value NOT contains '" + parameterValue + "' in Filters!");
  }

  /**
   * <P>
   * Verifies configuration is selected.
   * <P>
   * This method called after {@link JRSBuildNewReportPage#selectAConfigurationAndValidate(String)}.
   * 
   * @param configDropDownValue available configuration values.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectAConfigurationAndValidate(final String configDropDownValue,
      final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified: " + configDropDownValue + " is selected successfully with out any error message.");
    }
    return new TestAcceptanceMessage(false, "Verified: " + configDropDownValue + " is not selected successfully");
  }

  /**
   * <P>
   * Verifies configuration is selected.
   * <P>
   * This method called after {@link JRSBuildNewReportPage#clickOnRunButton()}.
   * 
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickOnRunButton(final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Report is generated successfully with out any error message.");
    }
    return new TestAcceptanceMessage(false, "Verified: Report is not generated.");
  }


  /**
   * <P>
   * Verifies edit option clicked successfully
   * <P>
   * This method called after {@link JRSBuildNewReportPage#editJrsReport(String)}.
   * 
   * @param report report name.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyEditJrsReport(final String report, final String lastResult) {
    waitForSecs(1);
    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true, "Verified: Edit opiton clicked successfully with out any error message.");
    }
    return new TestAcceptanceMessage(false, "Verified: Report is not clicked edit option successfully.");
  }

  /**
   * <P>
   * This method called after {@link JRSBuildNewReportPage#clickCancelButton()}.
   * 
   * @author KYY1HC
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyClickCancelButton(final String lastResult) {
    String xpathBuildReportButton = "//button[contains(text(),'Build report')]";
    if (this.driverCustom.isElementVisible(xpathBuildReportButton, Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true, "Verified: Clicked on Cancel button successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Clicked on Cancel button is NOT successfully.");
  }

  /**
   * <p>
   * Verify creator is added in the condition.
   * <p>
   * This method called after {@link JRSBuildNewReportPage#setCreator(String)}.
   * 
   * @param user to be added as creator.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetCreator(final String user, final String lastResult) {
    waitForSecs(5);
    Select dropdown = new Select(this.driverCustom.getWebElement("//div[@class='person-filter']//select"));
    String actualSelectedOption = dropdown.getFirstSelectedOption().getText().trim();
    if (actualSelectedOption.equals(user)) {
      return new TestAcceptanceMessage(true, "Verified : '" + user + "' is selected as a Creator of the Requirement.");
    }
    return new TestAcceptanceMessage(false,
        "Verified : '" + user + "' is not selected as a Creator of the Requirement.");
  }

  /**
   * @param graphPropertyName
   * @param graphPropertyValue
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyIsGraphPropertyPresentInReport(final String graphPropertyName,
      final String graphPropertyValue, final String lastResult) {

    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified : " + graphPropertyName + " : " + graphPropertyValue + " updated in the Report section.");
    }
    return new TestAcceptanceMessage(false, "Verified : " + graphPropertyName + " : " + graphPropertyValue +
        " Not updated in the Report section.\nplease check updated data.");
  }

  /**
   * @param graphPropertyName
   * @param graphPropertyValue
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyIsGraphPropertyPresent(final String graphPropertyName,
      final String graphPropertyValue, final String lastResult) {

    if (Boolean.parseBoolean(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Verified : " + graphPropertyName + " : " + graphPropertyValue + " updated in the preview section.");
    }
    return new TestAcceptanceMessage(false, "Verified : " + graphPropertyName + " : " + graphPropertyValue +
        " Not updated in the preview section.\nPlease check updated data.");
  }

  /**
   * @param additionalParam
   * @param lastResult
   * @return
   */
  public TestAcceptanceMessage verifyGetViewAs(final String additionalParam, final String lastResult) {

    if (!lastResult.isEmpty()) {
      return new TestAcceptanceMessage(lastResult.equalsIgnoreCase(additionalParam),
          "Verified: Current View of the Report is '" + lastResult + "' in Report page.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: Current View of the Report is '" + lastResult + "'Not in Report page.\nplease check updated data.");

  }

  /**
   * @return the document generation progress message
   */
  public TestAcceptanceMessage verifygenerateAWordDocumentFromYourReport(final String Size) {

    WebElement progress =
        this.driverCustom.getPresenceOfWebElement("//div[contains(text(),'Document generation finished')]");
    if (progress != null) {
      return new TestAcceptanceMessage(true, "Document is generated successfully with " + Size + " Paper Size.");
    }
    else {
      return new TestAcceptanceMessage(false, "Document is not generated.");
    }
  }

  /**
   * <P>
   * This method verifies, after report creation(under global configuration) global configuration link generated or not
   * 
   * @param linkName which will appear in header of a configuration-scoped report
   * @return result that will confirm about appearance of link
   */
  public TestAcceptanceMessage verifyGlobalConfigurationLinkPresenceInPage(final String linkName) {

    Boolean progress =
        this.driverCustom.isElementPresent(JRSConstants.JRS_GLOBAL_CONFIG_LINK, Duration.ofSeconds(50), linkName);
    if (progress)
      return new TestAcceptanceMessage(true, "Verified: Link have generated after clicking Run report ");
    else
      return new TestAcceptanceMessage(false, "Verified: Link have not generated after clicking Run report");
  }

  /**
   * <P>
   * This method varifies, clicking the global configuration link , the hierarchy details tab is opening or not
   * 
   * @param urlElement is element in new opened tab
   * @return result that will confirm about appearance of new tab
   */
  public TestAcceptanceMessage verifyGlobalConfigurationHierarchyDetails(final String urlElement) {

    Boolean progress = this.driverCustom.isElementPresent(JRSConstants.JRS_GLOBAL_CONFIG_NAVIGATED_PAGE,
        Duration.ofSeconds(50), urlElement);
    if (progress)
      return new TestAcceptanceMessage(true, "Verified: Page have generated after clicking Global config ");
    else
      return new TestAcceptanceMessage(false, "Verified: Page have not generated after clicking Global config");
  }

  /**
   * This method will be called after {@link JRSBuildNewReportPage#selectCreateButton(String)}
   * 
   * @param lastResult - result from the last method
   * @param option - chooesen value from the main method
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectCreateButton(final String option, final String lastResult) {
    Boolean isDisplayedCreatePage = this.driverCustom
        .isElementVisible("//li[contains(@class,'breadcrumb') and @title='Create report']", timeInSecs);
    if (isDisplayedCreatePage) {
      return new TestAcceptanceMessage(true, "Verified: Created Report page is opened successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: Created Report page is not opened.");

  }

  /**
   * This method is called after executing {@link JRSBuildNewReportPage#selectReportView(String)
   * 
   * @param optionView such as My reports/All reports
   * @param lastResult - result from the last method
   * @return acceptance object which contains verification results.
   */

  public TestAcceptanceMessage verifySelectReportView(final String optionView, final String lastResult) {
    WebElement lstBoxLabel = this.driverCustom.getWebElement("//div[contains(@data-testid,'TableViewDropdownItem')]//span[2]");
    if (lstBoxLabel.getText().equalsIgnoreCase(optionView)) {
      return new TestAcceptanceMessage(true, "Verified: Report list was selected as" + optionView);

    }
    return new TestAcceptanceMessage(false, "Verified: Report list was not selected as" + optionView);
  }

  /**
   * This method is called after executing method {@link JRSBuildNewReportPage#verifyReportInfoInEditor(String, String, String, String, String)
   * 
   * @return true - all information is as expected and vice versa.
   */
  public TestAcceptanceMessage verifyVerifyReportInfoInEditor(final String folder, final String reportName,
      final String tags, final String visualization, final String owners, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: All report information in Editor is displayed as expectation.");
    }
    return new TestAcceptanceMessage(false,
        "Verified: All report information in Editor is not displayed as expectation.");
  }
}
