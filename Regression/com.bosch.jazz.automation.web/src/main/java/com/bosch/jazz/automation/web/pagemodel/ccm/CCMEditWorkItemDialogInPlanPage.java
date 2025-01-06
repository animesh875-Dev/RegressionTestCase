/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.container.RowFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * @author UUM4KOR
 *         <p>
 *         Represents Edit work item dialog in Plan page, when work item is created from plan using left side action
 *         button.<br>
 *         Work item are displayed under the plan, Select the work item, open action button at first column in the left
 *         side of work item, click on "Edit Work item" menu, A dialog opened with the name of the work item to edit.
 *         <P>
 *         It has methods to set summary using name of the attribute , select value from the drop down , save and etc.
 *         Class is extended from {@link CCMWorkItemEditorPage} for reusable methods.
 */
public class CCMEditWorkItemDialogInPlanPage extends CCMWorkItemEditorPage {

  /**
   * Constructor setting the object of {@link WebDriverCustom} class.
   *
   * @param driverCustom required for interacting with the browser.
   */


  /**
   * @param driverCustom @see {@link #driverCustom}
   */
  public CCMEditWorkItemDialogInPlanPage(final WebDriverCustom driverCustom) {
    super(driverCustom);

    this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new JazzTextFieldFinder(),
        new JazzDropdownFinder(), new JazzButtonFinder(), new RowCellFinder(), new JazzTabFinder(),
        new JazzDialogFinder(), new CheckboxFinder(), new RowFinder(), new JazzLabelFinder());
  }

  /**
   * The name of the parameter that shall hold the attribute name.
   */
  private static final String CLASS = "class";

  /**
   * <p>
   * After opening Edit work item dialog from {@link CCMPlanPage#clickOnActionInPlanPhase(String, String)}. <br>
   * There are drop downs for Summary, Filed against, owned by and etc... Choose drop down and set value.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param dropDownAttributeName name of the DropDown.
   * @param attributeValue value of the attribute.
   */

  @Override
  public Boolean setDropDownAttributeValue(final String dropDownAttributeName, final String attributeValue) {
    waitForPageLoaded();
    WebElement dropDown = findDropDown(dropDownAttributeName);
    // logic to make dropdown visible
    WebElement arrow = dropDown.findElement(By.xpath(".//span[starts-with(@class, 'ArrowPlaceHolder')]"));
    new Actions(this.driverCustom.getWebDriver()).moveToElement(arrow).click().build().perform();
    if (selectAttribute(attributeValue)) {
      LOGGER.LOG.info(dropDownAttributeName + " attribute value " + attributeValue + " selected successfully");
      return true;
    }
    return checkDropDownAttributeValue(dropDown, dropDownAttributeName, attributeValue, arrow);
  }

  /**
   * <p>
   * This method set the dropDownAttributeName.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param dropDownAttributeName name of the Drop Down.
   * @throws WebAutomationException Engineering Level,Maturity Level,Intended Use,Filed Against,Owned By,Target
   *           Configuration,Planned For,Constraint Type
   */
  @Override
  protected WebElement findDropDown(final String dropDownAttributeName) {
    waitForPageLoaded();
    List<WebElement> attributeList = this.driverCustom.getWebElements("//div[@class = 'ValueHolder ViewBorder']");
    Optional<WebElement> dropdown =
        attributeList.stream().filter(x -> x.getAttribute("aria-label").startsWith(dropDownAttributeName)).findFirst();
    if (!dropdown.isPresent()) {
      throw new WebAutomationException(dropDownAttributeName + " atrribute name not matching.");
    }
    return dropdown.get();
  }

  /**
   * <p>
   * This method selecting the drop down value and return that value.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeValue value of the attribute.
   * @return true if attribute is selected
   */

  protected boolean selectAttribute(final String attributeValue) {
    waitForPageLoaded();
    if (!this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_FILTER_TEXTBOX_XPATH, Duration.ofSeconds(2))) {
      return false;
    }
    List<WebElement> filterList =
        this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_FILTER_TEXTBOX_XPATH);
    for (WebElement ele : filterList) {
      WebElement parent = ele.findElement(By.xpath(".."));
      parent = parent.findElement(By.xpath(".."));
      String attribute = parent.getAttribute(CLASS);

      if (!attribute.contains("NotExpanded") && attribute.contains("Filterable")) {
        ele.sendKeys(attributeValue.trim());
        if (this.driverCustom.isLocaterVisible(By.xpath("//span[text() = '" + attributeValue.trim() + "']"), Duration.ofSeconds(5))) {
          break;
        }
        else if (this.driverCustom
            .isLocaterVisible(By.xpath(CCMConstants.CCMCREATEQUERYPAGE_DROPDOWNMOREVALUE_BUTTON_XPATH), Duration.ofSeconds(5))) {
          this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_DROPDOWNMOREVALUE_BUTTON_XPATH);
          this.driverCustom.typeText(CCMConstants.CCMCREATEPLANPAGE_SEARCHBOX_TEXTFEILD_XPATH, attributeValue.trim());
          this.driverCustom.select("//select[@dojoattachpoint = 'userSelector']", attributeValue.trim(),
              SelectTypeEnum.SELECT_BY_VISIBILE_TEXT);
          Dialog dlglink = this.engine.findElement(Criteria.isDialog().withTitle("Select User")).getFirstElement();

          Button okbutton =
              this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlglink)).getFirstElement();
          okbutton.click();
          return true;

        }

      }
    }
    return false;
  }

  /**
   * This method save the work item editor.
   */
  @Override
  public void saveWorkItem() {
    clickOnJazzSpanButtons("Save and Close");
    LOGGER.LOG.info("Save and Close button clicked successfully");
  }

  /**
   * <p>
   * This method check the dropdown value and return that value.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param dropDownAttributeName name of the DropDown.
   * @param attributeValue value of the attribute.
   */

  @Override
  protected Boolean checkDropDownAttributeValue(final WebElement dropDown, final String dropDownAttributeName,
      final String attributeValue, final WebElement arrow) {
    waitForPageLoaded();
    List<WebElement> elementsLIst =
        this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_OPTIONSLIST_LISTBOX_XPATH);
    int breakPoint = elementsLIst.size() * 3;
    WebElement optionsLIst = null;
    while (true) {
      optionsLIst = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
      optionsLIst.sendKeys(Keys.ARROW_DOWN);
      optionsLIst.sendKeys(Keys.ENTER);
      String tempr;
      try {
        tempr = dropDown.getText().trim();
      }
      catch (StaleElementReferenceException e) {
        // The page has changed significantly after pressing the ENTER. Search for the drop down again:
        tempr = findDropDown(dropDownAttributeName).getText().trim();
      }
      if (tempr.equalsIgnoreCase(attributeValue.trim())) {
        LOGGER.LOG.info(dropDownAttributeName + " attribute value " + attributeValue + " selected successfully");
        return true;
      }
      if (breakPoint == 0) {
        arrow.click();
        throw new WebAutomationException(attributeValue + " element not found.");
      }
      breakPoint--;
    }

  }


  /**
   * <p>
   * Check Edit Work item Dialog Plan Page loaded or not. <br>
   * If not Check Edit Work item Dialog Plan Page loaded method return timeout message.
   */
  @Override
  public void waitForPageLoaded() {
    String errorMessage =
        "Edit Work item Dialog Plan Page not loaded or The Condition used to check whether page is loaded or not general.";
    this.driverCustom.anyMatch(By.xpath("//div[@class = 'header-primary' or @class='jazz-ui-Dialog-heading']"), 2,
        x -> x.getText().equals("Edit New Work Item"), errorMessage);
  }

  /**
   * <P>
   * Verifies the methods with Boolean value return such as saveWorkItem and isWorkItemSaved, isXXX ...
   *
   * @param dropDownAttributeName drop Down Attribute Name.
   * @param attributeValue attribute Value.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySetDropDownAttributeValue(final String dropDownAttributeName,
      final String attributeValue, final String lastResult) {

    String actualValue = "";
    try {
      Dropdown drdType = this.engine.findFirstElement(Criteria.isDropdown().withLabel(dropDownAttributeName + ":"));
      actualValue = drdType.getDefaultValue();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    if (actualValue.equals(attributeValue)) {
      return new TestAcceptanceMessage(true, "Actual '" + dropDownAttributeName + "' value is -'" + actualValue +
          "' set successfully. \nExpected '" + dropDownAttributeName + "' value is -'" + attributeValue + "'.");
    }
    return new TestAcceptanceMessage(false, "Actual value of '" + dropDownAttributeName + "'  is - '" + actualValue +
        "' not set successfully. \nExpected  '" + dropDownAttributeName + "' valus is -'" + attributeValue + "'.");
  }

}
