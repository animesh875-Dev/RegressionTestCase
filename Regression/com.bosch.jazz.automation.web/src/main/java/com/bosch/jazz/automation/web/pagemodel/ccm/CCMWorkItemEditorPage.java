package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;
import com.bosch.jazz.automation.web.common.constants.WorkItemEnums.WorkItemAttribute;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstructionPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.RadioButton;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Panel;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Link;
import com.bosch.psec.web.test.element.text.Text;
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
 * <P>
 * Represents Change and configuration management work item editor page, has capabilities to create, edit and change
 * Work flow state of any work items of any template.
 * 
 * @param webElement 
 */
public class CCMWorkItemEditorPage extends AbstractCCMPage {
  protected WebElement webElement;
  /**
   * Constructor setting the object of {@link WebDriverCustom} class.
   *
   * @param driverCustom required for interacting with the browser.
   */
  public CCMWorkItemEditorPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new JazzTextFieldFinder(),
        new JazzDropdownFinder(), new JazzButtonFinder(), new RowCellFinder(), new JazzTabFinder(),
        new JazzDialogFinder(), new CheckboxFinder(), new RowFinder(), new JazzLabelFinder(), new LinkFinder(),
        new DropdownFinder(), new RadioButtonFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(),
        new PanelFinder(), new RowCellFinder(), new RowCellFinder());
  }

  /**
   * <p>
   * After work item is created,a identical number will be generated at top left sideof the page. ID is used to search
   * The work items,add link to other modules and etc.
   * <p>
   * Store the id of the work in any variable or write in the file after generated. <br>
   * Access or read the id from the variable or file and pass it to the methods if required.
   * {@link CCMProjectAreaDashboardPage #quickSearch(String)}.
   *
   * @return identity number of the work item.
   */
  public String getWorkItemID() {
    waitForPageLoaded();
    String result = this.driverCustom
        .getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_WORKITEMID_TEXT_XPATH, null).getText();

    if (result != null) {
      String[] resArr = result.split(" ");
      if (resArr.length >= 2) {
        LOGGER.LOG.info("Work item id is '" + resArr[resArr.length - 1] + "'");
        return resArr[resArr.length - 1];
      }
    }
    return null;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)} and search any work item
   * {@link CCMProjectAreaDashboardPage #quickSearch} or create any work item by filling nessary attribute values using
   * {@link CCMWorkItemEditorPage #setAttributeValueInTextArea(String, String)}{@link CCMWorkItemEditorPage #setDropDownAttributeValue(String, String)}}
   * <d>this method is used to get work item status from state dropdown menu.
   *
   * @return current state of the work item.
   */
  public String getStatus() {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_STATE_LISTBOX_XPATH);
    if (!this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_STATE_LISTBOX_XPATH,
        this.driverCustom.getWebDriverSetup().getConfigurationForImplicitWaitTime())) {
      throw new WebAutomationException("Status drop down not loaded in work item page");
    }
    try {
      String presentState =
          this.driverCustom.getSelectedValueFromDropDown(CCMConstants.CCMWORKITEMEDITORPAGE_STATE_LISTBOX_XPATH);
      LOGGER.LOG.info("Current state of the work item is : " + presentState);
      return this.driverCustom.getSelectedValueFromDropDown(CCMConstants.CCMWORKITEMEDITORPAGE_STATE_LISTBOX_XPATH);
    }
    catch (Exception e) {
      throw new WebAutomationException("Not found state of the work item. " + " or \n" + e.getMessage());
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)} and search any work item
   * {@link CCMProjectAreaDashboardPage #quickSearch} set state of work item using{ @link CCMWorkItemEditorPage
   * {@link #setStatus(String)}}
   * <p>
   * this method used to get present resolution of work item from resolution dropdown.
   * 
   * @author VDY1HC
   * @return current resolution of the work item.
   */
  public String getResolution() {
    waitForPageLoaded();
    // Others servers
    try {
      Select drdResolution = new Select(
          this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_RESOLUTION_LISTBOX_XPATH));
      String presentResolution =  drdResolution.getFirstSelectedOption().getText();
      LOGGER.LOG.info("Current resolution of the work item is : " + presentResolution);
      return presentResolution;
    }
    // 05-p - Custom resolution - Detailed Status
    catch (Exception e) {
      String detailedStatus = this.driverCustom.getText(CCMConstants.CCMWORKITEMEDITORPAGE_DETAILEDSTATUS_XPATH);
      LOGGER.LOG.info("Current 'Detailed Status' of the work item is : " + detailedStatus);
      return detailedStatus;
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)} {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * Work item has drop downs to set value for "Filed against", "Owned By", and "Planned for" etc. See
   * {@link WorkItemAttribute} for more drop downs. Select the value from the drop down to set.
   *
   * @param dropDownAttributeName name of the drop down.
   * @param attributeValue value of the drop down to select..
   * @return true if the value is selected successfully.
   */
  public Boolean setDropDownAttributeValue(final String dropDownAttributeName, final String attributeValue) {
    if (setDropDownAttributeValueByEngine(dropDownAttributeName, attributeValue)) {
      return true;
    }
    return selectAttribute(dropDownAttributeName, attributeValue);
  }

  /**
   * Work item has drop downs to set value for "Filed against", "Owned By", and "Planned for" etc. See
   * {@link WorkItemAttribute} for more drop downs. Select the value from the drop down to set.
   * <p>
   * this method used inside the {@link #setDropDownAttributeValue(String, String)}
   *
   * @param dropDownAttributeName name of the drop down.
   * @param attributeValue value of the drop down to select..
   * @return true if dropdown is selected successfully
   */

  public boolean setDropDownAttributeValueByEngine(final String dropDownAttributeName, final String attributeValue) {
    waitForPageLoaded();
    Dropdown drdElement = null;
    try {
      drdElement = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel(dropDownAttributeName),
          this.timeInSecs);
      //Scroll into view
      this.driverCustom.scrollIntoCenterOfView(drdElement.getWebElement());
      if (!drdElement.getDefaultValue().contains(attributeValue)) {
        drdElement.selectOptionWithText(attributeValue);
        drdElement =
            this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel(dropDownAttributeName), Duration.ofSeconds(30));
      }
    }
    catch (Exception e) {
      LOGGER.LOG.info(dropDownAttributeName + " drop down attribute failed to select value '" + attributeValue +
          "' by Engine.\n" + e.getMessage());
      return false;
    }
    
    if (drdElement.getText().trim().equalsIgnoreCase(attributeValue)) {
      LOGGER.LOG
          .info(dropDownAttributeName + " dropdown attribute value is " + attributeValue + " selected successfully ");
      return true;
    }
    drdElement.click();
    LOGGER.LOG.info(
        dropDownAttributeName + " drop down attribute failed to select value '" + attributeValue + "' by Engine.");
    return false;
  }

  /**
   * This method check the drop down value and return that value.
   *
   * @param dropDown name.
   * @see WorkItemAttribute Enum for the attributes names.
   * @param dropDownAttributeName name of the Drop Down.
   * @param attributeValue value of the attribute.
   * @param arrow arrow.
   * @return true if the value is selected successfully.
   */

  protected Boolean checkDropDownAttributeValue(final WebElement dropDown, final String dropDownAttributeName,
      final String attributeValue, final WebElement arrow) {
    waitForPageLoaded();
    List<WebElement> elementsList =
        this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_OPTIONSLIST_LISTBOX_XPATH);
    int breakPoint = elementsList.size() * 3;
    WebElement optionsList = null;
    while (true) {
      optionsList = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
      optionsList.sendKeys(Keys.ARROW_DOWN);
      optionsList.sendKeys(Keys.ENTER);
      String temp;
      try {
        temp = dropDown.getText().trim();
      }
      catch (StaleElementReferenceException e) {
        // The page has changed significantly after pressing the ENTER. Search for the drop down again:
        temp = findDropDown(dropDownAttributeName).getText().trim();
      }
      if (temp.equalsIgnoreCase(attributeValue.trim())) {
        LOGGER.LOG.info(
            dropDownAttributeName + " drop down attribute value is " + attributeValue + " selected Successfully ");
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
   * This method set the drop Down Attribute Name.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param dropDownAttributeName name of the Drop Down. attribute Value value of the attribute.
   * @return @WebElement WebElement.
   * @throws WebAutomationException Engineering Level,Maturity Level,Intended Use,Filed Against,Owned By,Target
   *           Configuration,Planned For,Constraint Type
   */
  protected WebElement findDropDown(final String dropDownAttributeName) {
    waitForPageLoaded();
    List<WebElement> attributeList;
    String[] attrNameValue;
    attributeList = this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTES_LIST_XPATH);
    for (WebElement element1 : attributeList) {
      attrNameValue = element1.getText().split("\n");
      attrNameValue[0] = attrNameValue[0].replace(":", "");
      attrNameValue[0] = attrNameValue[0].replace("*", "").trim();
      if ((attrNameValue[0].length() != 0) && attrNameValue[0].equals(dropDownAttributeName)) {
        return element1.findElement(By.className("ValueColumn2"));
      }
    }
    throw new WebAutomationException(dropDownAttributeName + " atrribute name not matching.");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)} {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * This method selecting the dropdown value and return that value.
   *
   * @param dropDownAttributeName the label of dropdown
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeValue value of the attribute.
   * @return true if the value is selected successfully.
   */

  protected Boolean selectAttribute(final String dropDownAttributeName, final String attributeValue) {
    // 1. Planned For dropdown
    WebElement dropDown = findDropDown(dropDownAttributeName);
    // logic to make dropdown visible
    WebElement arrow = dropDown.findElement(By.className("ArrowPlaceHolder"));
    new Actions(this.driverCustom.getWebDriver()).moveToElement(arrow).click().build().perform();
    if (dropDownAttributeName.equalsIgnoreCase("planned for") && !attributeValue.equalsIgnoreCase("Unassigned")) {
      String xpathClickForMoreValuesButton = "//a[@class='button']//img[@alt= 'Click for more values']";
      this.driverCustom.isElementInvisible(xpathClickForMoreValuesButton, Duration.ofSeconds(10));
      this.driverCustom.getWebElement(xpathClickForMoreValuesButton).click();
      waitForSecs(5);

      Optional<WebElement> opt = this.driverCustom
          .getWebElements("//div[@class='FilterSection']//input[@type= 'search']").stream().findFirst();
      if (opt.isPresent()) {
        opt.get().sendKeys(attributeValue);
      }
      else {
        throw new WebAutomationException(dropDownAttributeName + " invalid attribute name.");
      }
      waitForSecs(2);
      this.driverCustom.click("//div[@class='BodySection']//div[contains(text(),'DYNAMIC_VAlUE')]", attributeValue);
      waitForSecs(2);
      Dialog dlgSelectUser1 = this.engine
          .findElement(Criteria.isDialog().withTitle("Choose from Iterations for Planned For")).getFirstElement();
      Button btnAddandCloseinSelectUser =
          this.engine.findElement(Criteria.isButton().withText("OK").inContainer(dlgSelectUser1)).getFirstElement();
      btnAddandCloseinSelectUser.click();
      LOGGER.LOG
      .info(dropDownAttributeName + " drop down attribute value is " + attributeValue + " selected Successfully ");
      return true;
    }
    // 2. Search filed
    if (this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_FILTER_TEXTBOX_XPATH, this.timeInSecs)) {
      List<WebElement> filterList =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_FILTER_TEXTBOX_XPATH);
      for (WebElement ele : filterList) {
        WebElement parent = ele.findElement(By.xpath(".."));
        parent = parent.findElement(By.xpath(".."));
        String attribute = parent.getAttribute(CCMConstants.CLASS);
        if (!attribute.contains("NotExpanded") && attribute.contains("Filterable")) {
          ele.sendKeys(attributeValue.trim());
          if (this.driverCustom.isLocaterVisible(
              By.xpath("//li[@role='option']//span[text()='" + attributeValue.trim() + "']"), this.timeInSecs)) {
            break;
          }
          else if (this.driverCustom.isLocaterVisible(
              By.xpath(CCMConstants.CCMCREATEQUERYPAGE_DROPDOWNMOREVALUE_BUTTON_XPATH), this.timeInSecs)) {
            this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_DROPDOWNMOREVALUE_BUTTON_XPATH);
            this.driverCustom.typeText(CCMConstants.CCMCREATEPLANPAGE_SEARCHBOX_TEXTFEILD_XPATH, attributeValue.trim());
            this.waitForSecs(2);
            this.driverCustom.click(CCMConstants.CCMCREATEQUERYPAGE_SELECT_WITHVALUE_TEXT_XPATH, attributeValue.trim());
            this.driverCustom.click(CCMConstants.CCMCREATEPLANPAGE_OK_BUTTON_XPATH);
            LOGGER.LOG.info(attributeValue + "- is selected from dropdown with text filter Successfully ");
            return true;
          }
        }
      }
    }
    // Select option by Keys
    return checkDropDownAttributeValue(dropDown, dropDownAttributeName, attributeValue, arrow);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)} {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * List box is shown after clicking on Select Work item type menu under DropDown.
   *
   * @param DropDownName is name of the drop down.
   * @return list of options from the drop down.
   */
  public List<String> getDropDownList(final String DropDownName) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTEDROPDOWN_XPATH, DropDownName);
    List<String> temp = new ArrayList<>();
    Dropdown drdType =
        this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel(DropDownName + ":"), this.timeInSecs);
    drdType.getWebElement().click();
    List<WebElement> lst = drdType.getWebElement()
        .findElements(By.xpath(CCMConstants.CCMWORKITEMEDITORPAGE_OPTIONSLIST_LISTBOX_TEXT_XPATH));
    for (WebElement ele : lst) {
      if (this.driverCustom.isElementVisible(ele, Duration.ofSeconds(1))) {
        temp.add(ele.getText());
      }
    }
    return temp;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)} {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * This method Check whether the attribute has a star '*' sign.
   *
   * @param attributeName name of the attribute.
   * @return list of all mandatory attributes in the work item editor.
   */
  public boolean isAttributeMandatory(final String attributeName) {
    waitForPageLoaded();
    List<String> mandDescriptionList =
        this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_MANDATORYDESCRIPTION_LABEL_XPATH);
    for (String mandDescriptionListstr : mandDescriptionList) {
      if (attributeName.equals(mandDescriptionListstr)) {
        LOGGER.LOG.info("Verified attribute with star(*) label.\n Attribute '" + attributeName + "' is mandatory ");
        return true;
      }
      List<String> mandSummaryList =
          this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_MANDATORYSUMMARY_LABEL_XPATH);
      for (String mandSummaryListstr : mandSummaryList) {
        mandSummaryListstr = mandSummaryListstr.replace(":", "");
        if (attributeName.equals(mandSummaryListstr)) {
          LOGGER.LOG.info("Verified attribute with star(*) label.\n Attribute '" + attributeName + "' is mandatory ");
          return true;
        }
        List<String> mandList =
            this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_MANDATORY_LABEL_XPATH);
        for (String str : mandList) {
          str = str.replace(":", "");
          if (attributeName.equals(str)) {
            LOGGER.LOG.info(" Verified attribute with star(*) label. \n Attribute " + attributeName + " is mandatory ");
            return true;
          }
        }
      }
    }
    LOGGER.LOG.info("Verified attribute with star(*) label.\n Attribute " + attributeName + " is not mandatory ");
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * This method Check whether the attribute has a star '*' sign.
   *
   * @param attributeName name of the attribute.
   * @return list of all mandatory attributes in the work item editor.
   */
  public boolean isAttributeNotMandatory(final String attributeName) {
    waitForPageLoaded();
    List<String> mandDescriptionList =
        this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_MANDATORYDESCRIPTION_LABEL_XPATH);
    for (String mandDescriptionListstr : mandDescriptionList) {
      if (attributeName.equals(mandDescriptionListstr)) {
        LOGGER.LOG.info("Verified attribute with star(*) label.\n Attribute " + attributeName + " is mandatory ");
        return false;
      }
      List<String> mandSummaryList =
          this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_MANDATORYSUMMARY_LABEL_XPATH);
      for (String mandSummaryListstr : mandSummaryList) {
        mandSummaryListstr = mandSummaryListstr.replace(":", "");
        if (attributeName.equals(mandSummaryListstr)) {
          LOGGER.LOG.info("Verified attribute with star(*) label. \n Attribute " + attributeName + " is mandatory");
          return false;
        }
        List<String> mandList =
            this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_MANDATORY_LABEL_XPATH);
        for (String str : mandList) {
          str = str.replace(":", "");
          if (attributeName.equals(str)) {
            LOGGER.LOG.info(" Verified attribute with star(*) label. \n Attribute " + attributeName + " is mandatory");
            return false;
          }
        }
      }
    }
    LOGGER.LOG.info("Verified attribute with star(*) label. \n Attribute " + attributeName + " is not mandatory");
    return true;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * This method used to verify is attribute exist...? and written {@link Boolean} condition.
   *
   * @param attributeName name of the attribute.
   * @return list of all mandatory attributes in the work item editor.
   */
  public boolean isAttributeExist(final String attributeName) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTE_LABEL_XPATH, Duration.ofSeconds(5));
    List<String> mandList =
        this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTE_LABEL_XPATH);
    for (String str : mandList) {
      str = str.replace(":", "");
      if (attributeName.equals(str)) {
        return true;
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * This method used to get present dropdown attribute value and Returns the Selected option from Drop down.
   *
   * @param attributeName in name of Attribute
   * @return value of value of down value
   */
  public String getDropDownValue(final String attributeName) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
    waitForSecs(20);
    Dropdown drdElement =
        this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel(attributeName + ":"), this.timeInSecs);
    LOGGER.LOG.info("Attribute " + attributeName + "  Present value is : " + drdElement.getText());
    waitForSecs(Duration.ofSeconds(10));
    return drdElement.getText();
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * Returns the Selected Right Side Attribute Value
   *
   * @param attributeName in name of Attribute
   * @return @value of Attribute value
   */
  public List<String> getRightAttributeValue(final String[] attributeName) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    List<String> lst = new ArrayList<>();
    for (String ele : attributeName) {
      lst.add(ele + " : " +
          this.driverCustom.getText(CCMConstants.CCMWORKITEMEDITORPAGE_RIGHTSIDEATTRIBUTE_SELECTEDTEXT_XPATH, ele));
    }
    return lst;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * Returns the Selected Left Side Attribute Value
   *
   * @param attributeName in name of Attribute
   * @return @value of Attribute value
   */
  public List<String> getLeftAttributeValue(final String[] attributeName) {
    waitForPageLoaded();
    this.driverCustom.getWebDriver().navigate().refresh();
    List<String> lst = new ArrayList<>();
    for (String ele : attributeName) {
      lst.add(ele + " : " +
          this.driverCustom.getText(CCMConstants.CCMWORKITEMEDITORPAGE_LEFTSIDEATTRIBUTE_SELECTEDTEXT_XPATH, ele));
    }
    return lst;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Type,Task Type,Severity,Priority,Filed Against,Owned By,Target
   *          Configuration, Planned For,Constraint Type,Detection Phase,Detection Method,Injection Phase,Injection
   *          Qualifier,Found In,Issuer Class, Engineering Level,Impact,Maturity Level,Story Points,Intended Use,State
   *          Of automatic evaluation,Evaluation result,Responsible Class)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isDropDownListBoxAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_DROPDOWNREADONLY_LISTBOX_XPATH, Duration.ofSeconds(5))) {
      List<WebElement> webElementsList =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_DROPDOWNREADONLY_LISTBOX_XPATH);
      for (WebElement webElement : webElementsList) {
        webElement = this.driverCustom.getWebElement(webElement);
        if (!webElement.getAttribute(CCMConstants.CLASS).contains(CCMConstants.DYNAMIC_READ_ONLY)) {
          WebElement drp = webElement.findElement(By.xpath(".//div[@class = 'ValueHolder ViewBorder']"));
          String name = drp.getAttribute(CCMConstants.ARIA_LABEL);
          if (name != null) {
            String dropDownName = name.replace(" " + webElement.getText(), "");
            if (dropDownName.equals(attributeName)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Summary,Description,Additional Information in HTML,Comments,Snapshot |
   *          Baseline URI,Base Snapshot | Base Baseline URI,Issued By,Analysis Result,External Id,External
   *          Participants,Stream URI ,Mitigation Actions)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isRichTextEditorWidgetTextBoxAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_RICHTEXTEDITORWIDGET_TEXTBOX_XPATH, Duration.ofSeconds(5))) {
      List<WebElement> textBoxList =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_RICHTEXTEDITORWIDGET_TEXTBOX_XPATH);
      for (WebElement ele : textBoxList) {
        ele = this.driverCustom.getWebElement(ele);
        if (this.driverCustom.isAttributeContains(ele, CCMConstants.CONTENTEDITABLE, "true", Duration.ofSeconds(3)) &&
            ele.getAttribute(CCMConstants.ARIA_LABEL).equals(attributeName)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Additional Information in TEXT,Version,Part Number,Planned Estimate
   *          (hrs))
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isAttributesTextAreaWritable(final String attributeName) {
    waitForPageLoaded();
    String name = attributeName;
    this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTES_TEXTAREA_XPATH, Duration.ofSeconds(5));
    if (attributeName.equalsIgnoreCase("Version") && attributeName.equalsIgnoreCase("Part Number") &&
        attributeName.equalsIgnoreCase("Planned Estimate (hrs)")) {
      name = attributeName + ":";
    }
    Text txtAdditionalInfoInText =
        this.engine.findFirstElementWithDuration(Criteria.isTextField().hasLabel(name), this.timeInSecs);
    List<WebElement> textAreaList = txtAdditionalInfoInText.getWebElement()
        .findElements(By.xpath(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTES_TEXTAREA_XPATH));
    for (WebElement ele : textAreaList) {
      ele = this.driverCustom.getWebElement(ele);
      if ((ele.getAttribute(CCMConstants.DISABLED) == null) &&
          ele.getAttribute(CCMConstants.ARIA_LABEL).equals(attributeName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Due Date,Date of Receipt)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isDateCalenderAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if ("Due Date".equals(attributeName) || ("Date of Receipt".equals(attributeName) &&
        this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_DATE_CALENDER_XPATH, Duration.ofSeconds(5)))) {
      Text dateCalenderAttribute = this.engine
          .findFirstElementWithDuration(Criteria.isTextField().hasLabel(attributeName + ": "), this.timeInSecs);
      List<WebElement> textBoxAttributeList = dateCalenderAttribute.getWebElement()
          .findElements(By.xpath(CCMConstants.CCMWORKITEMEDITORPAGE_DATE_CALENDER_XPATH));
      for (WebElement ele : textBoxAttributeList) {
        if (ele.getAttribute(CCMConstants.ARIA_LABEL).equals(attributeName)) {
          return (!this.driverCustom.isAttributeNotNull(ele, CCMConstants.DISABLED, Duration.ofSeconds(5)));
        }
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Detected In)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isDetectedInViewPickerAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_DETECTEDIN_VIEWPICKER_XPATH, Duration.ofSeconds(5))) {
      List<WebElement> textBoxAttributeList =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_DETECTEDIN_VIEWPICKER_XPATH);
      for (WebElement ele : textBoxAttributeList) {
        if (!this.driverCustom.isAttributeContains(ele, CCMConstants.CLASS, CCMConstants.DYNAMIC_READ_ONLY, Duration.ofSeconds(5))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Constraint Date)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isConstraintDateListBoxAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_CONSTRAINTDATEREADONLY_LISTBOX_XPATH,
        Duration.ofSeconds(5))) {
      List<WebElement> consDateList =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_CONSTRAINTDATEREADONLY_LISTBOX_XPATH);
      for (WebElement ele : consDateList) {
        if (this.driverCustom.isAttributeContains(ele, "aria-readonly", CCMConstants.FALSE, Duration.ofSeconds(5))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Tags)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isTagsTextBoxAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if ("Tags".equals(attributeName)) {
      WebElement elem =
          this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_TAGSREADONLY_TEXTBOX_XPATH);
      String js = "arguments[0].style.display='inline'; arguments[0].style.visibility='visible';";
      ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript(js, elem);
      return (!this.driverCustom.isAttributeContains(elem, CCMConstants.CLASS, CCMConstants.DYNAMIC_READ_ONLY, Duration.ofSeconds(5)));
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Estimate,Correction)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isEstimateTextFieldAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_ESTIMATE_TEXTFIELD_XPATH, Duration.ofSeconds(5))) {
      List<WebElement> estimateList =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_ESTIMATE_TEXTFIELD_XPATH);
      for (int i = 0; i < estimateList.size(); i++) {
        WebElement e = estimateList.get(i);
        new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(e.findElement(
            By.xpath(CCMConstants.DIV_CONTAINS_CLASS_COM_IBM_TEAM_APT_WEB_UI_INTERNAL_PARTS_DURATION_WIDGET))));
        if (!e
            .findElement(
                By.xpath(CCMConstants.DIV_CONTAINS_CLASS_COM_IBM_TEAM_APT_WEB_UI_INTERNAL_PARTS_DURATION_WIDGET))
            .getAttribute(CCMConstants.CLASS).contains(CCMConstants.READONLY)) {
          if ((i == 0) && attributeName.equals(CCMConstants.ESTIMATE)) {
            return true;
          }
          if (attributeName.equals(CCMConstants.CORRECTION)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Time Spent)
   * @return true if the element is writable, false if read-only.
   */
  public boolean isTimeTextFieldAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_TIMESPENT_TEXTFIELD_XPATH, Duration.ofSeconds(5));
    Row timeSpentRow = this.engine.findElementWithDuration(Criteria.isRow().withText(attributeName), this.timeInSecs)
        .getFirstElement();
    List<WebElement> durationInputs = timeSpentRow.getWebElement()
        .findElements(By.xpath(CCMConstants.DIV_CONTAINS_CLASS_COM_IBM_TEAM_APT_WEB_UI_INTERNAL_PARTS_DURATION_WIDGET));
    for (WebElement e : durationInputs) {
      if (e.findElement(By.xpath(CCMConstants.PRECEDING_SIBLING_TH_LABEL)).getText().replace(":", "")
          .equals(attributeName)) {
        return !e.getAttribute(CCMConstants.CLASS).contains(CCMConstants.READONLY);
      }
    }
    return false;
  }

  /**
   * @param attributeName name of the attribute. (Internal Participants,Evaluated Subjects,Contact Persons,Affected
   *          Teams,Removed from Related)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isAddButtonButtonAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_ADDBUTTON_BUTTON_XPATH, Duration.ofSeconds(5))) {
      List<WebElement> editorsElmsList =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_ADDBUTTON_BUTTON_XPATH);
      for (WebElement ele : editorsElmsList) {
        if (!this.driverCustom.isAttributeContains(ele, CCMConstants.CLASS, CCMConstants.DYNAMIC_READ_ONLY, Duration.ofSeconds(5))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param attributeName name of the attribute. (Unit Test,Requirement,Detailed Design)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isValidityCheckBoxAttributeWritable(final String attributeName) {
    waitForPageLoaded();

    Row rowValidAssociation = this.engine
        .findElementWithDuration(Criteria.isRow().withText(attributeName + ":"), this.timeInSecs).getFirstElement();
    Cell cell = this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowValidAssociation), this.timeInSecs)
        .getFirstElement();
    try {
      Checkbox cbxRequirement = this.engine
          .findElementWithDuration(Criteria.isCheckbox().inContainer(cell), this.timeInSecs).getElementByIndex(1);
      if (cbxRequirement.getWebElement().isEnabled()) {
        return true;
      }
    }
    catch (NullPointerException e) {
      return false;
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (Residual Estimate (hrs),Deviation (hrs),Estimation Sum (hrs))
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isPlanningEstimateTextViewAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    Row rowAttributeName = this.engine
        .findElementWithDuration(Criteria.isRow().withText(attributeName + ": "), this.timeInSecs).getFirstElement();

    return rowAttributeName.getWebElement().isDisplayed();
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   *
   * @param attributeName name of the attribute. (PM Interface Element ID,Execution Progress)
   * @return list of all writable attributes in the work item editor.
   */
  public boolean isPmInterfaceIdAndExecutionProgressAttributeWritable(final String attributeName) {
    waitForPageLoaded();
    Row row = this.engine.findElementWithDuration(Criteria.isRow().withText(attributeName + ": "), this.timeInSecs)
        .getFirstElement();
    return row.getWebElement().isEnabled();

  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link }{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <p>
   * Work item editor has text box to set summary and other details across all the tabs., <br>
   * Set value to test box like Summary,Description, Comments, Additional Information in HTML,Snapshot | Baseline URI,
   * Base Snapshot | Base Baseline URI, Issued By,Analysis Result,Predicted Implementation Date,RQ1 ID,RQ1 Relationship
   * Data,RQ1 Customer Data,RQ1-RTC-Sync,External Participants {@link WorkItemAttribute}
   *
   * @param attributeName name of the text box.
   * @param attributeValue vale to set in test box.
   */
  @SuppressWarnings("javadoc")
  public String setAttributeValueInTextBox(final String attributeName, final String attributeValue) {
    return setAttributeValueInTextBox(attributeName, attributeValue, "false");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link }{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <p>
   * Work item editor has text box to set summary and other details across all the tabs., <br>
   * Set value to test box like Summary,Description, Comments, Additional Information in HTML,Snapshot | Baseline URI,
   * Base Snapshot | Base Baseline URI, Issued By,Analysis Result,Predicted Implementation Date,RQ1 ID,RQ1 Relationship
   * Data,RQ1 Customer Data,RQ1-RTC-Sync,External Participants {@link WorkItemAttribute}
   *
   * @param attributeName name of the text box.
   * @param attributeValue vale to set in test box.
   * @param isMultiLine true if textbox has multiple lines
   */
  @SuppressWarnings("javadoc")
  public String setAttributeValueInTextBox(final String attributeName, final String attributeValue,
      final String isMultiLine) {
    waitForPageLoaded();
    // Input Summary
    Text txtTextFiled = null;
    if (isMultiLine.equalsIgnoreCase("true")) {
      txtTextFiled = this.engine
          .findElementWithDuration(Criteria.isTextField().hasLabel(attributeName).isMultiLine(), this.timeInSecs)
          .getFirstElement();
    }
    else {
      txtTextFiled = this.engine
          .findElementWithDuration(Criteria.isTextField().hasLabel(attributeName), this.timeInSecs).getFirstElement();
    }
    txtTextFiled.setText(attributeValue);
    LOGGER.LOG.info(attributeName + " set in text box '" + attributeValue + " ' successfully. ");
    return attributeValue;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * Set value to the text box using {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)} get the
   * Same value using this method.
   *
   * @param attributeName name of the test box attribute see Summary,Description, Comments, Additional Information in
   *          HTML,Snapshot | Baseline URI, Base Snapshot | Base Baseline URI, Issued By,Analysis Result,Predicted
   *          Implementation Date,RQ1 ID,RQ1 Relationship Data,RQ1 Customer Data,RQ1-RTC-Sync Info
   *          {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)}
   * @return value present in the text box.
   */
  public String getAttributeValueInTextBox(final String attributeName) {
    return getAttributeValueInTextBox(attributeName, "false");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * <P>
   * Set value to the text box using {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)} get the
   * Same value using this method.
   *
   * @param attributeName name of the test box attribute see Summary,Description, Comments, Additional Information in
   *          HTML,Snapshot | Baseline URI, Base Snapshot | Base Baseline URI, Issued By,Analysis Result,Predicted
   *          Implementation Date,RQ1 ID,RQ1 Relationship Data,RQ1 Customer Data,RQ1-RTC-Sync Info
   *          {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)}
   * @param isMultiLine true if textbox has multiple lines
   * @return value present in the text box.
   */
  public String getAttributeValueInTextBox(final String attributeName, final String isMultiLine) {
    waitForPageLoaded();
    String actualValue = "";
    Text txtTextFiled = null;
    if (Boolean.valueOf(isMultiLine)) {
      txtTextFiled = this.engine
          .findElementWithDuration(Criteria.isTextField().hasLabel(attributeName).isMultiLine(), this.timeInSecs)
          .getFirstElement();
    }
    else {
      txtTextFiled = this.engine
          .findElementWithDuration(Criteria.isTextField().hasLabel(attributeName), this.timeInSecs).getFirstElement();
    }
    if (txtTextFiled != null) {
      actualValue = txtTextFiled.getText().trim();
    }
    if (actualValue.isEmpty() && (txtTextFiled != null)) {
      actualValue = txtTextFiled.getWebElement().getAttribute("value").trim();
    }
    return actualValue;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * Set value to the Comment text box using {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)}
   * get the Same value using this method.
   *
   * @param expectedComment comment expected.
   * @return value of lastest comment in Comments Section
   */
  public Boolean isLastestCommentInputted(final String expectedComment) {
    String lastestComment =
        this.driverCustom.getWebElementsText("//div[@data-dojo-attach-point='_bodyComment']").get(0);
    return lastestComment.contains(expectedComment);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * Set value into the text area like Additional Information in TEXT, Version, Part Number,Planned Estimate (hrs)
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeName
   * @param attributeValue
   * @throws WebAutomationException
   * @throws InterruptedException
   */
  @SuppressWarnings("javadoc")
  public void setAttributeValueInTextArea(final String attributeName, final String attributeValue) {
    waitForPageLoaded();
    String name = attributeName;
    boolean attributeFound = false;
    this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTES_TEXTAREA_XPATH, Duration.ofSeconds(5));

    if (attributeName.equalsIgnoreCase("Version") && attributeName.equalsIgnoreCase("Part Number") &&
        attributeName.equalsIgnoreCase("Planned Estimate (hrs)")) {
      name = attributeName + ":";
    }

    Text txtAdditionalInfoInText =
        this.engine.findFirstElementWithDuration(Criteria.isTextField().hasLabel(name), this.timeInSecs);

    List<WebElement> textAreaList = txtAdditionalInfoInText.getWebElement()
        .findElements(By.xpath(CCMConstants.CCMWORKITEMEDITORPAGE_ATTRIBUTES_TEXTAREA_XPATH));

    for (WebElement ele : textAreaList) {
      if (ele.getAttribute(CCMConstants.ARIA_LABEL).equals(attributeName)) {
        attributeFound = true;
        if (ele.isEnabled()) {

          ele.clear();
          LOGGER.LOG.info(attributeName + " text area cleared successfully. ");
          ele.sendKeys(attributeValue);
          LOGGER.LOG
          .info(attributeName + " set with value in text area " + "'" + attributeValue + "'" + " successfully. ");
          break;
        }
      }
    }
    if (!attributeFound) {
      LOGGER.LOG.info(attributeName + "  attribute not found in the work item editor or not a type text box.");
      throw new WebAutomationException(
          attributeName + CCMConstants.ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX);

    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method checks or un checks the check boxes of validity.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeName attribute names in validity tab like Unit Test, Detailed Design and Requirement.
   * @throws WebAutomationException if attribute Name is not valid.
   */
  public void checkOrUncheckValidity(final String attributeName) {
    waitForPageLoaded();
    Row rowIncludeAll;
    try {
      rowIncludeAll = this.engine.findElementWithDuration(Criteria.isRow().withText(attributeName), this.timeInSecs)
          .getFirstElement();
    }
    catch (Exception e) {
      throw new WebAutomationException(
          attributeName + CCMConstants.ATTRIBUTE_NOT_FOUND_IN_THE_WORKITEM_EDITOR_OR_NOT_A_TYPE_TEXTBOX);
    }
    Cell cellCheckbox =
        this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowIncludeAll).withIndex(1), this.timeInSecs)
        .getFirstElement();
    Checkbox cbxIncludeAllItem = this.engine
        .findElementWithDuration(Criteria.isCheckbox().inContainer(cellCheckbox), this.timeInSecs).getFirstElement();
    cbxIncludeAllItem.click();
    LOGGER.LOG.info(attributeName + "  attribute is selected successfully.");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * This method selects the given tab in workitem editor.
   *
   * @param tabName Tab name provided like Overview, Links, Approvals, Misc, History, Time Tracking and Validity
   */
  public void selectTab(final String tabName) {
    waitForPageLoaded();
    if (this.driverCustom.isElementVisible("//a[@class='closeButton']", Duration.ofSeconds(5))) {
      this.driverCustom.getFirstVisibleWebElement("//a[@class='closeButton']", null).click();
    }
    int numberOfTabs = this.driverCustom.getWebElements(CCMConstants.CCMWORKITEM_EDITOR_PAGE_TAB_XPATH).size();
    if (numberOfTabs < 1) {
      throw new WebAutomationException("No tab's loaded on work item page");
    }
    if (this.driverCustom.getWebElements("//a[@class='tab']").stream()
        .noneMatch(x -> x.getText().contains(tabName))) {
      throw new WebAutomationException(
          tabName + " : tab not selected from work item editor page or invalid,Please provide valid input.");
    }
//    Optional<WebElement> opt = this.driverCustom.getWebElements("//a[@class='tab']").stream()
//        .filter(x -> x.getText().equalsIgnoreCase(tabName)indFirst();
    WebElement opt = this.driverCustom.getFirstVisibleWebElement("//a[@class='tab' and text()='DYNAMIC_VAlUE']",tabName);
    JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
    je.executeScript("arguments[0].scrollIntoView(false);",opt);
    if (opt.isDisplayed()) {
      opt.click();
    }
    else {
      throw new WebAutomationException(tabName + " invalid tab name.");
    }
    LOGGER.LOG.info(tabName + " : tab selected from work item editor page successfully.");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * Clicking on Select link type menu under Work items menu.
   *
   * @param LinkName is Link name
   */
  @Override
  public Boolean clickOnLink(final String LinkName) {
    waitForPageLoaded();
    this.driverCustom.getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_LINK_TYPE_XPATH, LinkName).click();
    return true;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * Navigate to the parent Work Item by clicking on the 'Parent (1)' link in the Quick Information section.
   */
  public void navigateToParentWI() {

    WebElement parentHref = this.driverCustom
        .getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_QUICKINFORMATION_PARENTLINK_XPATH, null);
    String parentId = parentHref.getText();
    parentHref.click();
    // Check that the old element become stale. Note that it may fail when single stepping in the debugger
    this.driverCustom.waitForPageLoaded(parentHref);
    this.driverCustom.waitForPageLoaded(" " + parentId + ":");

  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * List Tabs is shown after clicking on Select Work item type menu under Workitems menu.
   *
   * @return list of work items Select Tabs names present in the list box.
   */
  public List<String> getTabNamesList() {
    waitForPageLoaded();
    return this.driverCustom.getWebElementsText(CCMConstants.CCMWORKITEMEDITORPAGE_TABS_LIST_XPATH);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is used to set value in the Tags field.
   *
   * @param tagValue pass the value of the tag to be set.
   */
  public void setTags(final String tagValue) {
    waitForPageLoaded();
    WebElement elem = this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_TAGS_TEXTFIELD_XPATH);
    this.driverCustom.clickUsingActions(elem);
    this.driverCustom.getActions().moveToElement(elem).sendKeys(tagValue).build().perform();
  }
  
  /**
   * <p>
   * Select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * Search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is used to get list values in the Tags field.
   * 
   * @return list of values in Tags field
   */
  public List<String> getTagsValues() {
    waitForPageLoaded();
    List<String> lstValues = new ArrayList<String>();
    String tagsXpath = "//div[@aria-label='Tags']//span[@class='TagContent']";
    this.driverCustom.isElementVisible(tagsXpath, Duration.ofSeconds(10));
    List<WebElement> tags = this.driverCustom.getWebDriver().findElements(By.xpath(tagsXpath));
    if(!tags.isEmpty()) {
      for (WebElement tag : tags) {
        lstValues.add(tag.getText().trim());
      } 
    }
    else {
      LOGGER.LOG.error("List values of 'Tags' is empty - Actual List: " +lstValues.toString());
      return lstValues;
    }
    LOGGER.LOG.info("List values of 'Tags': " +lstValues.toString());
    return lstValues;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is used to set Estimate and Correction value and its units in the workitem editor.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributename Name of the attribute
   * @param attributeValue value of the attribute.
   * @param timeunits maximum time taken to search for the attribute value.
   */
  public void setEstimateOrCorrection(final String attributename, final String attributeValue, final String timeunits) {

    List<WebElement> webEleList =
        this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_ESTIMATE_TEXTFIELD_XPATH);
    if (attributename.equalsIgnoreCase(CCMConstants.ESTIMATE)) {
      for (WebElement e : webEleList) {
        Actions actions = new Actions(this.driverCustom.getWebDriver());
        actions.moveToElement(e);
        actions.click();
        actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        actions.sendKeys(attributeValue);
        actions.build().perform();
        new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30))
        .until(ExpectedConditions.visibilityOf(e.findElement(By.xpath(CCMConstants.INPUT))));
        String xpathExpression2 = CCMConstants.SELECT;
        if (e.findElement(By.xpath(CCMConstants.INPUT)).getAttribute(CCMConstants.ARIA_LABEL).contains("estimate")) {
          new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30))
          .until(ExpectedConditions.visibilityOf(e.findElement(By.xpath(xpathExpression2))));
          WebElement ele = e.findElement(By.xpath(xpathExpression2));
          Select sel = new Select(ele);
          sel.selectByVisibleText(timeunits);
          break;
        }
        else if (attributename.equals("Corrected Estimate")) {
          new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30))
          .until(ExpectedConditions.visibilityOf(e.findElement(By.xpath(xpathExpression2))));
          WebElement ele = e.findElement(By.xpath(xpathExpression2));
          Select sel = new Select(ele);
          sel.selectByVisibleText(timeunits);
        }
      }
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is used to set time spent value and its units in the work item editor.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeValue value of the time spent.
   * @param timeunits units of the time spent.
   */
  public void setTimeSpent(final String attributeValue, final String timeunits) {

    List<WebElement> webEleList =
        this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_TIMESPENT_TEXTFIELD_XPATH);
    for (WebElement e : webEleList) {
      Actions actions = new Actions(this.driverCustom.getWebDriver());
      actions.moveToElement(e);
      actions.click();
      actions.sendKeys(attributeValue);
      actions.build().perform();
      new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30))
      .until(ExpectedConditions.visibilityOf(e.findElement(By.xpath(CCMConstants.INPUT))));
      if (e.findElement(By.xpath(CCMConstants.INPUT)).getAttribute(CCMConstants.ARIA_LABEL)
          .contains(CCMConstants.TIME_SPENT)) {
        new WebDriverWait(this.driverCustom.getWebDriver(), Duration.ofSeconds(30))
        .until(ExpectedConditions.visibilityOf(e.findElement(By.xpath(CCMConstants.SELECT))));
        WebElement ele = e.findElement(By.xpath(CCMConstants.SELECT));
        Select sel = new Select(ele);
        sel.selectByVisibleText(timeunits);

      }
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is used to set the constraint date.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeName name of the constraint date.
   * @param attributeValue value for the constraint date.
   * @throws WebAutomationException if attribute name is not valid.
   */
  public void setConstraintDate(final String attributeName, final String attributeValue) {
    waitForPageLoaded();
    if (!attributeName.equals(CCMConstants.CONSTRAINT_DATE)) {
      throw new WebAutomationException(
          attributeName + " Attribute not found in the work item editor or not a type Date picker.");
    }
    Text consDate =
        this.engine.findFirstElementWithDuration(Criteria.isTextField().hasLabel("Constraint Date: "), this.timeInSecs);
    consDate.clearText();
    consDate.getWebElement()
    .findElement(By.xpath("//input[@aria-label='Constraint Date']/../preceding-sibling::div[2]")).click();
    this.driverCustom.getWebDriver().switchTo().activeElement().click();
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is to set the date in the date picker field.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeName Due Date
   * @param attributeValue pass value of the attribute to be set.
   */
  public void setDate(final String attributeName, final String attributeValue) {

    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_DATE_CALENDER_XPATH);
    // Input Due Date
    Text txtDueDate =
        this.engine.findFirstElementWithDuration(Criteria.isTextField().hasLabel(attributeName + ":"), this.timeInSecs);
    txtDueDate.clearText();
    txtDueDate.setText(attributeValue);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is to set the account number in the account number text field.
   *
   * @see WorkItemAttribute Enum for the attributes names.
   * @param attributeName Account Number
   * @param attributeValue pass value of the attribute to be set.
   */
  public void setAccountNumbers(final String attributeName, final String attributeValue) {

    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_ACCOUNTNUMBER_TEXTFIELD_XPATH);
    // Input Due Date
    Text txtaccountNumbers =
        this.engine.findFirstElementWithDuration(Criteria.isTextField().hasLabel(attributeName + ":"), this.timeInSecs);
    txtaccountNumbers.clearText();
    txtaccountNumbers.setText(attributeValue);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is to set contact person name by clicking add button and select from Select Users dialog
   *
   * @param attributeValue pass contact person name
   */
  public void addContactPersons(final String attributeValue) {
    // Click on Add button
    Button btnAdd =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Add"), this.timeInSecs).getFirstElement();
    btnAdd.click();
    LOGGER.LOG.info("Add button is clicked successfully.");

    // Search and select user
    Dialog dlgSelectUser = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select Users"), this.timeInSecs).getFirstElement();

    // Search and select approver
    Text txtSearchApprover = this.engine
        .findElement(
            Criteria.isTextField().hasLabel("Enter a name filter to load the list.").inContainer(dlgSelectUser))
        .getFirstElement();
    txtSearchApprover.setText(attributeValue);
    LOGGER.LOG.info(attributeValue + " is typed in Search textbox successfully.");
    waitForSecs(Duration.ofSeconds(3));

    Dropdown drdMatchingApprover = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Matching users:").inContainer(dlgSelectUser),
            this.timeInSecs)
        .getFirstElement();
    drdMatchingApprover.selectOptionWithPartText(attributeValue);
    LOGGER.LOG.info(attributeValue + " username is selected successfully.");

    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgSelectUser), this.timeInSecs)
        .getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("OK button is clicked successfully.");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method is to remove contact person by clicking delete button
   *
   * @param workItem_SelectUser pass contact person name
   */
  public void deleteContactPersons(final String workItem_SelectUser) {
    // Remove previous Contact Person
    WebElement spanContactPerson = this.driverCustom
        .getWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_WORKITEM_SELECTUSER_XPATH, workItem_SelectUser);
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.moveToElement(spanContactPerson).build().perform();
    waitForSecs(2);

    List<WebElement> btnDeleteList2 =
        this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_DELETECONTACTPERSONE_BUTTON_XPATH);
    for (WebElement del : btnDeleteList2) {
      if (del.isDisplayed()) {
        del.click();
        LOGGER.LOG.info(workItem_SelectUser + " is removed successfully.");
      }
    }
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)} or edit work item
   * <P>
   * This method save the work item editor.
   */
  public void saveWorkItem() {
    // DONOT COMMENT OUT WAITING TIME, SAVE BUTTON NEED TIME TO LOAD - Click on Save button
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.isElementClickable("//button[text()='Save']", timeInSecs);
    Button btnSave =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();

    List<WebElement> foundElements =
        btnSave.getWebElement().findElements(By.xpath(CCMConstants.CCMWORKITEMEDITORPAGE_SAVE_BUTTON_XPATH));

    Optional<WebElement> firstEnabled = foundElements.parallelStream().filter(WebElement::isEnabled).findFirst();
    if (firstEnabled.isPresent()) {
      LOGGER.LOG.info("Verified save button is enabled and present.");
      firstEnabled.get().click();
      LOGGER.LOG.info("Save button clicked successfully.");
      waitForSecs(Duration.ofSeconds(10));
    }
    else {
      throw new WebAutomationException("Save button is disabled, not able to save the work item.");
    }
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)} or edit work item
   * <P>
   * This method save the work item editor.
   */
  public void notSaveWorkItem() {
    // Click on Save button
    waitForSecs(2);
    Button btnSave =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Save"), this.timeInSecs).getFirstElement();

    List<WebElement> foundElements =
        btnSave.getWebElement().findElements(By.xpath(CCMConstants.CCMWORKITEMEDITORPAGE_SAVE_BUTTON_XPATH));

    Optional<WebElement> firstEnabled = foundElements.parallelStream().filter(WebElement::isEnabled).findFirst();
    if (firstEnabled.isPresent()) {
      LOGGER.LOG.info("Verified save button is enabled and present.");
      firstEnabled.get().click();
      LOGGER.LOG.info("Save button clicked successfully.");

    }
    else {
      throw new WebAutomationException("Save button is disabled, not able to save the work item.");
    }
    waitForSecs(2);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)} or edit work item
   * <P>
   *
   * @return true if the work item saved successfully.
   */
  public boolean isWorkItemSaved() {
    LOGGER.LOG.info("Verifying 'Save' button is disabled.");
    // Wait for 20s
    for (int i = 0; i < 10; i++) {
      if (!this.driverCustom.isElementVisible("//span[text()='Saving...']", Duration.ofSeconds(5)) && this.driverCustom
          .getWebElements("//button[text() = 'Save']").stream().anyMatch(x -> x.getAttribute("disabled") != null)) {
        LOGGER.LOG.info("Verified - Save button is clicked successfully.");
        return true;
      }
      waitForSecs(2);
    }
    return false;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item and miss amy mandatory field and save
   * {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)} or edit work item
   * <P>
   * This method used to get validation message from validation message notification area.
   *
   * @return error message on the work item editor.
   */
  public String getValidationMessage() {
    waitForPageLoaded();
    return this.driverCustom.getAttribute(CCMConstants.CCMWORKITEMEDITORPAGE_VALIDATIONMESSAGE_TEXTFIELD_XPATH,
        "title");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)} or edit work item
   * <P>
   * This method is used to get validation message from notification area
   *
   * @return error message on the work item editor from notification area.
   */
  public String getValidationMessageFromNotificationArea() {
    waitForPageLoaded();
    return this.driverCustom
        .getText("//div[@class=\"NotificationView validationMessageError headerValidationMessage\"]");
  }

  // CCMConstants.CCMWORKITEMEDITORPAGE_VALIDATIONMESSAGEFROMNOTIFICATIONAREA_TEXTFIELD_XPATH
  // div[@class="NotificationView validationMessageError headerValidationMessage"]
  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * After work item is created, State of the work item set to "New" in most of the work items. <br>
   * State of the work item provides information of the status of the work item.<br>
   * Usually state transition changes when work item owner updates work item based on his work status. <br>
   * <p>
   * State is a drop down with options "New", "In Progress", "Close" and etc. Select the option from drop down with the
   * Visible text.
   *
   * @param state input name of the transition state to change.
   *          <p>
   *          Considered as a test data , enumerators are not created for options, list the values from the ALM
   *          (CCM)application or requirements provided.
   */
  public void setStatus(final String state) {
    waitForPageLoaded();
    waitForSecs(2);
    if ((state != null) && !state.isEmpty()) {

      // set status from drop down
      try {
        Dropdown drdStatus =
            this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel("Status"), this.timeInSecs);
        drdStatus.selectOptionsWithText(state);
        LOGGER.LOG.info(state + ": Value selected from Status drop down successfully ");
      }
      catch (Exception e) {
        throw new WebAutomationException(state +
            ": Value not found in the Status drop down,Please provide valid input.\n" + " Or\n" + e.getMessage());
      }
      return;
    }
    throw new IllegalArgumentException(
        state + " : Value not found from status drop down  or Invalid Or null or empty. ");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <p>
   * After the state of the work item status is set using {@link #setStatus(String)}, resolution for the state also need
   * to set. default value will be set if not set. E.g: Resolution of the closed state will be "Passed", "Failed" and
   * etc in review work item.. <br>
   * Resolution of the work item provides information of the status condition of the work item, resolution is dependent
   * on the state of the work item.
   * <p>
   * State is a drop down with options "New", "In Progress", "Close" and etc. Select the option from drop down with the
   * visible text.
   *
   * @param resolution input name of the transition state to change.
   */
  public void setResolution(final String resolution) {
    waitForPageLoaded();

    if ((resolution != null) && !resolution.isEmpty()) {
      try {
        Dropdown drdTaskType =
            this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel("Resolution"), this.timeInSecs);
        drdTaskType.selectOptionWithText(resolution);
        LOGGER.LOG.info(resolution + " : value selected from resolution drop down successfully ");
      }
      catch (Exception e) {
        try {
          this.driverCustom.click(CCMConstants.CCMWORKITEMEDITORPAGE_DETAILEDSTATUS_DROPDOWN_XPATH);
          this.driverCustom.isElementClickable(CCMConstants.CCMWORKITEMEDITORPAGE_DETAILEDSTATUSOPTION_XPATH,
              this.timeInSecs, resolution);
          this.driverCustom.click(CCMConstants.CCMWORKITEMEDITORPAGE_DETAILEDSTATUSOPTION_XPATH, resolution);
        }
        catch (Exception e1) {
          throw new WebAutomationException(
              resolution + ": value not found or Invalid,Please provide valid input.\n" + " Or\n" + e.getMessage());
        }
      }
      return;
    }
    throw new IllegalArgumentException("value not found from resolution drop down or invalid or null or empty.");

  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <p>
   * Open any Work Items.<br>
   * Navigate to 'Links' or 'Process Links' section of the work item. <br>
   * Click on 'Add Related' drop down.<br>
   * Click on any of the Links like 'Add Tested By Test Case','Add Contributes To', 'Add Related','Add Children','Add
   * Related Test Plan' etc.... <br>
   * 'Add Link','Select Work Items' wizard will display as per the links option. <br>
   * Add Existing Link in the work item.
   *
   * @param additionalParams stores "drpodownText", "linksSection", "linkActions", "linkTypeID" (For add link inside
   *          CCM)
   * @return "linkActions" added into work item.
   */
  public String addLinkToExistingObject(final Map<String, String> additionalParams) {
    WebElement element = null;
    String linkActions = additionalParams.get(CCMConstants.LINKACTIONS);
    String linkActionsStr = (linkActions).replace("_", " ");
    String linkSection = additionalParams.get(CCMConstants.LINKS_SECTION);
    String dropDownText = additionalParams.get(CCMConstants.DROPDOWN_TEXT);
    String lnkTypeId = additionalParams.get(CCMConstants.LINKTYPE_ID);
    String rqmProjectArea = additionalParams.get(CCMConstants.RQM_PROJECT_AREA);
    Panel panel = this.engine.findFirstElementWithDuration(Criteria.isPanel().withTitle(linkSection), this.timeInSecs);
    Dropdown drdAddWILinks = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withText(dropDownText).inContainer(panel), Duration.ofSeconds(20));
    drdAddWILinks =
        this.engine.findFirstElementWithDuration(Criteria.isDropdown().withText(dropDownText).inContainer(panel), Duration.ofSeconds(20));
    drdAddWILinks.scrollToElement();
    drdAddWILinks.selectOptionWithText(linkActionsStr);
    LOGGER.LOG.info(linkActionsStr + " selected from " + (dropDownText + " in the title " + linkSection));
    waitForSecs(Duration.ofSeconds(5));
    
    switch (linkActions) {
      case "Add Contributes To":
      case "Add Tracks":
        addLinkAddTracks(additionalParams);
        break;
      case "Add Affected by Defect":
      case "Add Affects Plan Item":
        addLinkAddAffectsPlanItem(additionalParams);
        break;
      case "Add Related":
      case "Add Resolves":
      case "Add Resolved By":
      case "Add Children":
        String msg = "Added child work item is same as the current workitem";
        Dialog dlgSelectWorkItem = this.engine
            .findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.SELECTWORKITEMS), this.timeInSecs)
            .getFirstElement();
        Text txtSearchChildrenWI = this.engine.findElementWithDuration(
            Criteria.isTextField().hasLabel(CCMConstants.WORKITEMS_SEARCH).inContainer(dlgSelectWorkItem),
            this.timeInSecs).getFirstElement();
        txtSearchChildrenWI.setText(lnkTypeId);
        LOGGER.LOG.info(CCMConstants.SELECTED + lnkTypeId + "' in 'Select Work Items' dialog.");
        waitForSecs(5);
        Dropdown drdMatchingWorkItem = this.engine.findElementWithDuration(
                Criteria.isDropdown().withLabel(CCMConstants.MATCHING_WORKITEMS).inContainer(dlgSelectWorkItem), timeInSecs)
            .getFirstElement();
        drdMatchingWorkItem.selectOptionWithPartText(lnkTypeId);
        LOGGER.LOG.info(lnkTypeId + " displayed in Matching WorkItems.");
        waitForSecs(Duration.ofSeconds(5));
        try {
          if (this.driverCustom.isEnabled("//button[text()='OK']")) {
            this.driverCustom.getWebElement("//button[text()='OK']").click();
            LOGGER.LOG.info("Clicked on 'OK' button in 'Select Work Items' dialog.");
          }
        }
        catch (Exception e) {
          return msg;
        }
        break;
      case "Set Parent":
        addLinkSetParent(additionalParams);
        break;
      case "Add Duplicated By":
      case "Set Duplicate Of":
      case "Add Blocks":
      case "Add Depends On":
        waitForSecs(6);
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        waitForSecs(6);
        element.sendKeys(additionalParams.get(CCMConstants.VALUE));
        waitForSecs(6);
        element.sendKeys(Keys.TAB);
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());

        if (isAnyWIforLinkingFound()) {
          this.driverCustom.textTobePresentInTheElement(element, additionalParams.get(CCMConstants.VALUE));
          element.sendKeys(Keys.TAB);
          element.sendKeys(Keys.DOWN);
          element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
          element.sendKeys(Keys.TAB);
        }
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        element.sendKeys(Keys.TAB);
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        this.driverCustom.getClickableWebElement(element).click();
        break;
      case "Add Related Artifacts":
      case "Add SVN Revisions":
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        element.sendKeys(additionalParams.get("value"));
        element.sendKeys(Keys.TAB);
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        element.sendKeys(additionalParams.get("other"));
        element.sendKeys(Keys.TAB);
        element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
        element.sendKeys(Keys.ENTER);
        break;
      case "Add Tested By Test Case":
        try {
          Dialog dlgAddLink =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Text txtSearch = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink), this.timeInSecs);
          txtSearch.setText(rqmProjectArea);
          LOGGER.LOG.info("Searched in  '" + rqmProjectArea + CCMConstants.PROJECT_AREA_ADD_LINK_DIALOG);
        }
        catch (Exception e) {
          Dialog dlgAddLink = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Text txtSearch = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink), this.timeInSecs);
          txtSearch.setText(rqmProjectArea);
          LOGGER.LOG.info("Searched in  '" + rqmProjectArea + CCMConstants.PROJECT_AREA_ADD_LINK_DIALOG);
        }
        Dropdown drdProjectArea =
            this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
                this.timeInSecs).getFirstElement();
        drdProjectArea.selectOptionWithText(rqmProjectArea);
        LOGGER.LOG.info(CCMConstants.SELECTED + rqmProjectArea + "' project area from 'Add Link' dialog. ");
        waitForSecs(Duration.ofSeconds(5));
        try {
          Dialog dlgAddLink =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Button btnOK = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink), this.timeInSecs)
              .getFirstElement();
          btnOK.click();
          LOGGER.LOG.info(" Clicked on 'OK' button in 'Add Link' dialog.");
        }
        catch (Exception e) {
          Dialog dlgAddLink = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Button btnOK = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink), this.timeInSecs)
              .getFirstElement();
          btnOK.click();
          LOGGER.LOG.info(" Clicked on 'OK' button in 'Add Link' dialog.");
        }
        waitForSecs(Duration.ofSeconds(5));
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH, Duration.ofSeconds(5))) {
          this.driverCustom.click(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH);
          LOGGER.LOG.info("Clicked on 'Show Inline Filter' icon in 'Select Test Case' dialog.");
        }
        Button btnClearFilter = this.engine
            .findElementWithDuration(Criteria.isButton().withToolTip(CCMConstants.CLEAR_ALL_FILTERS), this.timeInSecs)
            .getFirstElement();
        btnClearFilter.click();
        LOGGER.LOG.info("Clicked on 'Clear All Filters' button.");
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH, Duration.ofSeconds(5));
        // update
        this.driverCustom.getWebElement(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH)
        .sendKeys(additionalParams.get(CCMConstants.LINKTYPE_ID));
        LOGGER.LOG.info(
            CCMConstants.SELECTED + additionalParams.get(CCMConstants.LINKTYPE_ID) + "' In 'Select Test Case' dialog.");
        this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_RUN_BUTTON_XPATH, Duration.ofSeconds(30));
        this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_FILTERBUTTON_XPATH).click();
        LOGGER.LOG.info(" Clicked on 'Filter' button. ");
        Row rowTestCase = this.engine
            .findElementWithDuration(Criteria.isRow().withText(additionalParams.get(CCMConstants.LINKTYPE_ID)),
                this.timeInSecs)
            .getFirstElement();
        Cell cllCheckbox = this.engine
            .findElementWithDuration(Criteria.isCell().inContainer(rowTestCase).withIndex(1), this.timeInSecs)
            .getFirstElement();
        Checkbox cbxTestCase = this.engine
            .findElementWithDuration(Criteria.isCheckbox().inContainer(cllCheckbox), this.timeInSecs).getFirstElement();
        cbxTestCase.click();
        LOGGER.LOG.info("Clicked on searched test case.");
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        Button btnOK2 =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
        btnOK2.click();
        LOGGER.LOG.info(CCMConstants.CLICKED_ON_OK_BUTTON_IN_SELECT_TEST_CASE_DIALOG);
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        break;
      case "Add Related Test Plan":
        try {
          Dialog dlgAddLink2 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Text txtSearch2 = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink2), this.timeInSecs);
          txtSearch2.setText(rqmProjectArea);
          LOGGER.LOG.info(CCMConstants.SEARCHED + rqmProjectArea + "' In 'Add Link' dialog. ");
        }
        catch (Exception e) {
          Dialog dlgAddLink2 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Text txtSearch2 = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink2), this.timeInSecs);
          txtSearch2.setText(rqmProjectArea);
          LOGGER.LOG.info(CCMConstants.SEARCHED + rqmProjectArea + "' In 'Add Link' dialog. ");
        }
        Dropdown drdProjectArea2 =
            this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
                this.timeInSecs).getFirstElement();
        drdProjectArea2.selectOptionWithText(rqmProjectArea);
        LOGGER.LOG.info(CCMConstants.SELECTED + rqmProjectArea + "' from 'Add link' dialog. ");
        waitForSecs(5);
        try {
          waitForSecs(5);
          Dialog dlgAddLink2 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Button btnOK3 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink2), this.timeInSecs)
              .getFirstElement();
          btnOK3.click();
          LOGGER.LOG.info(" Clicked on 'OK' button in 'Add Link' dialog. ");
          waitForSecs(5);
        }
        catch (Exception e) {
          waitForSecs(5);
          Dialog dlgAddLink2 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Button btnOK3 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink2), this.timeInSecs)
              .getFirstElement();
          btnOK3.click();
          LOGGER.LOG.info(" Clicked on 'OK' button in 'Add Link' dialog. ");
          waitForSecs(5);
        }
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        this.waitForSecs(5);
        if (this.driverCustom.isElementVisible(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH, Duration.ofSeconds(5))) {
          this.driverCustom.click(RQMConstants.RQMPROJECT_SHOW_SLIDERDOWN_DROPDOWN_XPATH);
          LOGGER.LOG.info("Clicked on 'Show Inline Filter' icon in 'Select Test Plan' dialog.");
        }
        Button btnClearFilter2 =
            this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear all filters"), this.timeInSecs)
            .getFirstElement();
        btnClearFilter2.click();
        LOGGER.LOG.info("Clicked on 'Clear all filters' icon.");
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH, Duration.ofSeconds(5));
        // update
        this.driverCustom.getWebElement(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH)
        .sendKeys(additionalParams.get(CCMConstants.LINKTYPE_ID));
        LOGGER.LOG.info(
            CCMConstants.SEARCHED + additionalParams.get(CCMConstants.LINKTYPE_ID) + "' in 'Select Test Case' dialog.");
        this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_RUN_BUTTON_XPATH, Duration.ofSeconds(10));

        this.driverCustom.getWebElement("//button[@title='Apply all filters']").click();
        LOGGER.LOG.info("Clicked on 'Filter' button.");
        Row rowTestCase2 = this.engine
            .findElementWithDuration(Criteria.isRow().withText(additionalParams.get(CCMConstants.LINKTYPE_ID)),
                this.timeInSecs)
            .getFirstElement();
        Cell cllCheckbox2 = this.engine
            .findElementWithDuration(Criteria.isCell().inContainer(rowTestCase2).withIndex(1), this.timeInSecs)
            .getFirstElement();
        Checkbox cbxTestCase2 =
            this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(cllCheckbox2), this.timeInSecs)
            .getFirstElement();
        cbxTestCase2.click();
        LOGGER.LOG.info("Clicked on searched test plan.");
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        Button btnOK4 =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
        btnOK4.click();
        LOGGER.LOG.info("Clicked on 'OK' button in 'Select Test Plan' dialog.");
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        break;
      case "Add Related Test Case":
        try {
          Dialog dlgAddLink3 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Text txtSearch3 = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink3), this.timeInSecs);
          txtSearch3.setText(rqmProjectArea);
          LOGGER.LOG.info(
              CCMConstants.SEARCHED + rqmProjectArea + "' in 'Add Link' dialog.");
        }
        catch (Exception e) {
          Dialog dlgAddLink3 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Text txtSearch3 = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink3), this.timeInSecs);
          txtSearch3.setText(rqmProjectArea);
          LOGGER.LOG.info(
              CCMConstants.SEARCHED + rqmProjectArea + "' in 'Add Link' dialog.");
        }

        Dropdown drdProjectArea3 =
            this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
                this.timeInSecs).getFirstElement();
        drdProjectArea3.selectOptionWithText(rqmProjectArea);
        LOGGER.LOG.info(
            CCMConstants.SELECTED + rqmProjectArea + "' in 'Add Link' dialog.");
        waitForSecs(Duration.ofSeconds(5));
        try {
          Dialog dlgAddLink3 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Button btnOk2 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink3), this.timeInSecs)
              .getFirstElement();
          btnOk2.click();
          LOGGER.LOG.info(" Clicked on 'OK' button in 'Add Link' dialog. ");
          waitForSecs(5);
        }
        catch (Exception e) {
          Dialog dlgAddLink3 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Button btnOk2 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink3), this.timeInSecs)
              .getFirstElement();
          btnOk2.click();
          LOGGER.LOG.info(" Clicked on 'OK' button in 'Add Link' dialog. ");
          waitForSecs(Duration.ofSeconds(5));
        }
        Button btnClearFilter3 =
            this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear all filters"), this.timeInSecs)
            .getFirstElement();
        btnClearFilter3.click();
        LOGGER.LOG.info("Clicked on 'Clear all filters' button");
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH, Duration.ofSeconds(5));
        // update
        this.driverCustom.getWebElement(RMConstants.RM_ARTIFACTSPAGE_TESTSPEC_ID_TEXTFEILD_XPATH)
        .sendKeys(additionalParams.get(CCMConstants.LINKTYPE_ID));
        LOGGER.LOG.info(
            CCMConstants.SEARCHED + additionalParams.get(CCMConstants.LINKTYPE_ID) + "' in 'Select Test Case' dialog.");
        this.driverCustom.isElementClickable(RMConstants.RM_ARTIFACRPAGE_TESTSPEC_RUN_BUTTON_XPATH, Duration.ofSeconds(5));

        this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_FILTERBUTTON_XPATH).click();
        LOGGER.LOG.info("Clicked on 'Filter' button.");
        Row rowTestCase3 = this.engine
            .findElementWithDuration(Criteria.isRow().withText(additionalParams.get(CCMConstants.LINKTYPE_ID)),
                this.timeInSecs)
            .getFirstElement();
        Cell cllCheckbox3 = this.engine
            .findElementWithDuration(Criteria.isCell().inContainer(rowTestCase3).withIndex(1), this.timeInSecs)
            .getFirstElement();
        Checkbox cbxTestCase3 =
            this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(cllCheckbox3), this.timeInSecs)
            .getFirstElement();
        cbxTestCase3.click();
        LOGGER.LOG.info("Clicked on searched test case.");
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        Button btnOk3 =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
        btnOk3.click();
        LOGGER.LOG.info("Clicked on 'OK' button in 'Select Test Case' dialog.");
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        waitForSecs(Duration.ofSeconds(5));
        break;
      case "Add Tracks Requirement":
        waitForSecs(5);
        try {
          Dialog dlgAddLink21 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Text txtSearch21 = this.engine
              .findFirstElement(Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink21));
          txtSearch21.setText(additionalParams.get(CCMConstants.RM_PROJECT_AREA));
          LOGGER.LOG.info(CCMConstants.SEARCHED + additionalParams.get(CCMConstants.RM_PROJECT_AREA) + CCMConstants.IN +
              CCMConstants.ADD_LINK + "' Dialog.");
        }
        catch (Exception e) {
          Dialog dlgAddLink21 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Text txtSearch21 = this.engine
              .findFirstElement(Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink21));
          txtSearch21.setText(additionalParams.get(CCMConstants.RM_PROJECT_AREA));
          LOGGER.LOG.info(CCMConstants.SEARCHED + additionalParams.get(CCMConstants.RM_PROJECT_AREA) + CCMConstants.IN +
              " Add Link " + "' Dialog.");
        }
        Dropdown drdProjectArea21 =
            this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
                this.timeInSecs).getFirstElement();
        drdProjectArea21.selectOptionWithText(additionalParams.get(CCMConstants.RM_PROJECT_AREA));
        LOGGER.LOG.info(CCMConstants.SELECTED + additionalParams.get(CCMConstants.RM_PROJECT_AREA) + CCMConstants.IN +
            CCMConstants.ADD_LINK + CCMConstants.DIALOG);
        waitForSecs(5);
        try {
          Dialog dlgAddLink21 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
              .getFirstElement();
          Button btnOK21 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink21), this.timeInSecs)
              .getFirstElement();
          btnOK21.click();
          LOGGER.LOG.info("Clicked on 'OK' button in '" + CCMConstants.ADD_LINK + CCMConstants.DIALOG);
          waitForSecs(5);
        }
        catch (Exception e) {
          waitForSecs(5);
          Dialog dlgAddLink21 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Button btnOK21 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink21), this.timeInSecs)
              .getFirstElement();
          btnOK21.click();
          LOGGER.LOG.info("Clicked on 'OK' button in '" + " Add Link " + CCMConstants.DIALOG);
          waitForSecs(5);
        }
        this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
        waitForSecs(Duration.ofSeconds(10));

        // Select from Component dropdown if existing
        WebElement drdComponent = null;
        try {
          drdComponent = this.driverCustom.getWebElement("//select[@aria-label='Component:']");
          this.driverCustom.switchToDefaultContent();
        }
        catch (Exception ex) {
          LOGGER.LOG.info("Component value does't exist in 'Requirement Selection' dialog.");
        }
        if (drdComponent != null) {
          try {
            Dropdown drdProjectArea1 =
                this.engine.findElementWithDuration(Criteria.isDropdown().withAriaLabel("Component:"), this.timeInSecs)
                .getFirstElement();
            drdProjectArea1.selectOptionWithText(additionalParams.get(CCMConstants.RM_COMPONENT_NAME));
            LOGGER.LOG.info(" Selected '" + additionalParams.get(CCMConstants.RM_COMPONENT_NAME) +
                "' Component from 'Requirement Selection' dialog.");
          }
          catch (Exception e) {}
          waitForSecs(5);
        }

        Text searchBox = this.engine
            .findElementWithDuration(
                Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID"), this.timeInSecs)
            .getFirstElement();
        searchBox.setText(additionalParams.get(CCMConstants.LINKTYPE_ID) + Keys.ENTER);
        // this.driverCustom.typeText("//input[@placeholder = 'Type to filter artifacts by text or by ID']",
        // additionalParams.get(CCMConstants.LINKTYPE_ID) + Keys.ENTER);
        LOGGER.LOG.info(CCMConstants.SEARCHED + additionalParams.get(CCMConstants.LINKTYPE_ID) +
            "' in 'Requirement Selection' dialog.");
        waitForSecs(15);
        this.engine.addFinder(new JazzLabelFinder());
        Label artifact = this.engine
            .findElementWithDuration(
                Criteria.isLabel().withText(additionalParams.get(CCMConstants.TRAKSREQUIREMENT_NAME)), this.timeInSecs)
            .getFirstElement();
        artifact.click();
        LOGGER.LOG.info(
            " Clicked on '" + additionalParams.get(CCMConstants.TRAKSREQUIREMENT_NAME) + "' searched requirement.");
        waitForSecs(5);
        Button btnOK211 =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
        btnOK211.click();
        LOGGER.LOG.info("Clicked on 'OK' button in 'Requirement Selection' dialog.");
        waitForSecs(15);
        break;
      case "Add Affects Test Case Result":
        try {
          //TS_19951 Add Link dialog may open and close very fast and skip this try/catch. Only 11Q2 need.
          waitForSecs(5);
          if (this.driverCustom.isElementVisible(CCMConstants.PROJECT_AREA_ADD_LINK_DIALOG_XPATH, Duration.ofSeconds(20))) {
            Text txtSearch1 = this.engine.findFirstElement(Criteria.isTextField().withText(CCMConstants.SEARCH));
            txtSearch1.setText(rqmProjectArea);
            LOGGER.LOG.info(" Searched '" + rqmProjectArea + "' in '" +
                CCMConstants.ADD_LINK + "' Dialog.");
  
            // Select project area
            Dropdown drdProjectArea1 = this.engine
                .findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
                    this.timeInSecs)
                .getFirstElement();
            drdProjectArea1.selectOptionWithText(rqmProjectArea);
            LOGGER.LOG.info(" Selected '" + rqmProjectArea + "' in '" +
                CCMConstants.ADD_LINK + "' dialog.");
            waitForSecs(5);
            // Click OK button
            Button btnOK1 = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs)
                .getFirstElement();
            btnOK1.click();
            LOGGER.LOG.info("Clicked on 'OK' button in '" + CCMConstants.ADD_LINK + "' dialog.");
          }
        }
        catch (Exception e) {
          // TODO: handle exception
        }

        waitForSecs(5);
        Text searchBox1 = this.engine
            .findElementWithDuration(
                Criteria.isTextField().withPlaceHolder(CCMConstants.TYPE_FILTER_TEXT_AND_PRESS_ENTER), this.timeInSecs)
            .getFirstElement();
        searchBox1.setText(additionalParams.get(CCMConstants.LINKTYPE_ID));
        LOGGER.LOG.info(
            " Searched '" + additionalParams.get(CCMConstants.LINKTYPE_ID) + "' in 'Requirement Selection' dialog.");
        Button btnFilter = this.engine
            .findElementWithDuration(Criteria.isButton().withToolTip("Filter"), this.timeInSecs).getFirstElement();
        btnFilter.click();
        LOGGER.LOG.info("Clicked on Filter button.");
        waitForSecs(15);
        Row rowTestResult = this.engine
            .findElementWithDuration(Criteria.isRow().withText(additionalParams.get(CCMConstants.LINKTYPE_ID)),
                this.timeInSecs)
            .getFirstElement();
        Cell cllCheckbox1 = this.engine
            .findElementWithDuration(Criteria.isCell().inContainer(rowTestResult).withIndex(1), this.timeInSecs)
            .getFirstElement();
        Checkbox cbxTestResult =
            this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(cllCheckbox1), this.timeInSecs)
            .getFirstElement();
        cbxTestResult.click();
        LOGGER.LOG.info("Clicked on searched test result.");
        Button btnOk =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
        btnOk.click();
        LOGGER.LOG.info("Clicked on 'OK' button in 'Select Test Case' dialog.");
        waitForSecs(15);
        break;
      case "Add Implements Requirement":
        addLinkAddImplementsRequirement(additionalParams);
        Button btnOK =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
        btnOK.click();
        break;
      default:
        throw new WebAutomationException(
            "WorkItem link type not supported (yet)" + additionalParams.get(CCMConstants.LINKACTIONS));
    }
    return linkActionsStr;
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)} select links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <P>
   * This method used to validate is any work item links in link section.
   *
   * @return
   */
  private boolean isAnyWIforLinkingFound() {

    String statusText = "";
    int numberOfAttepts = 5;
    while (true) {
      WebElement status = this.driverCustom
          .getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_LINKS_SEARCHSTATUS_XPATH, null);
      statusText = status.getText();
      if (statusText.contains(" result")) {
        break;
      }
      if (numberOfAttepts-- == 0) {
        throw new WebAutomationException("Could not determine if the search returned any results");
      }
    }
    return !statusText.startsWith("0 ");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <P>
   * This method used to get Children/Contributes To/Parent/Related links for 'Process Links' or 'Links' Section
   *
   * @param linkSection 'Process Links' or 'Links' Section
   * @param linksType a WorkItemLinkTypes enum value
   * @return the list of WebElements (may be empty)
   */
  public List<WebElement> getLinksList(final String linkSection, final String linksType) {
    waitForPageLoaded();
    String linkSectionStr = linkSection;
    this.driverCustom.getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_LINKS_PROCESSLINKSORLINKS_XPATH,
        linkSectionStr);
    By locator = this.driverCustom.createLocatorFromProperty(
        CCMConstants.CCMWORKITEMEDITORPAGE_LINKS_LINKSLISTLABEL_XPATH, new String[] { linkSectionStr, linksType });
    return this.driverCustom.getWebDriver().findElements(locator);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)}
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <P>
   * Delete all links of links Type in a 'Process Links' or 'Links' Section
   *
   * @param linkSection 'Process Links' or 'Links' Section
   * @param linksType a WorkItemLinkTypes enum value
   */
  public void removeAllLinks(final String linkSection, final String linksType) {
    this.driverCustom.getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_LINKS_PROCESSLINKSORLINKS_XPATH,
        linkSection);
    LOGGER.LOG.info("checking visibletity of link" + linksType + " in link section " + linkSection);
    Actions action = new Actions(this.driverCustom.getWebDriver());
    List<WebElement> deleteControls = this.driverCustom.getWebElements(
        CCMConstants.CCMWORKITEMEDITORPAGE_LINKS_DELETELINKS_XPATH, new String[] { linkSection, linksType });
    for (WebElement webElement : deleteControls) {
      // First, ensure that the webElement is in the view port
      Coordinates coordinate = ((Locatable) webElement).getCoordinates();
      coordinate.onPage();
      coordinate.inViewPort();
      action.moveToElement(webElement).perform();
      webElement.click();
      LOGGER.LOG.info("In link section " + linkSection + " link type " + linksType + " Removed successfully.");
    }
  }

  /**
   * additionalParams must contain as key "CCMConstants.WORKITEM_ID" a work item number as String or WORK_ITEM_TYPE
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    if ((additionalParams != null) && (!additionalParams.isEmpty())) {
      if (additionalParams.containsKey(CCMConstants.WORKITEM_ID)) {
        String wiId = additionalParams.get(CCMConstants.WORKITEM_ID);

        if (wiId == null) {
          throw new IllegalArgumentException("additionalParams must contain a valid work item id");
        }
        this.driverCustom.openURL(repositoryURL + "/resource/itemName/com.ibm.team.workitem.WorkItem/" + wiId);
      }
      else if (additionalParams.containsKey(CCMConstants.WORK_ITEM_TYPE)) {
        String wiType = additionalParams.get(CCMConstants.WORK_ITEM_TYPE);

        if (wiType == null) {
          throw new IllegalArgumentException("additionalParams must contain a valid work item type");
        }

        this.driverCustom.openURL(repositoryURL + "/web/projects/" + projectAreaName +
            "#action=com.ibm.team.workitem.newWorkItem&type=" + wiType.toLowerCase());
      }
      return;
    }
    throw new IllegalArgumentException("A work item id is required to open the page");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select approvals tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   * this method use to click on add approval from work item approvals tab and use to select type of approval.
   *
   * @param dropdown value will be like "Add Approval","Add Review","Add Verification".
   */
  public void clickOnAddApproval(final String dropdown) {
    waitForPageLoaded();
    try {
      this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMCREATEPLANPAGE_SELECT_PLANS_XPATH, "Add Approval");
      Dropdown approval =
          this.engine.findFirstElementWithDuration(Criteria.isDropdown().withText("Add Approval"), this.timeInSecs);
      approval.selectOptionWithText(dropdown);
      LOGGER.LOG.info(dropdown + " : selected from work item approval dropdown successfully ");
    }
    catch (Exception e) {
      throw new WebAutomationException(
          dropdown + ": drop down not found,Please provide valid input.\n" + "or\n" + e.getMessage());
    }
    waitForSecs(15);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select approvals tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   * This method used to add approvers in approvals section after clicking On Add Approval drop down
   * {@link #clickOnAddApproval(String)} <br>
   * and this method Click on Add Approvers link then search user in Select Users dialog then select matching user from
   * result section and clicking on 'Add and Close' button <br>
   * If matching user is disabled then clicking on 'Close' button.
   *
   * @param user use to select from the Select Users window.
   * @return Approver can't be selected or approval added.
   */
  public String addApprovers(final String user) {
    // Click on Add Approvers link
    Button btnAddApprover = this.engine
        .findElementWithDuration(Criteria.isButton().withText("Add Approvers..."), this.timeInSecs).getFirstElement();
    btnAddApprover.click();
    LOGGER.LOG.info("Click on 'Add Approvers...' link successfully.");
    try {
      Dialog dlgSelectUser1 = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Select users"), this.timeInSecs).getFirstElement();
      // Search and select approver
      Text txtSearchApprover = this.engine.findFirstElementWithDuration(
          Criteria.isTextField().withPlaceHolder("Search").inContainer(dlgSelectUser1),
          this.timeInSecs);
      txtSearchApprover.clearText();
      txtSearchApprover.setText(user);
      LOGGER.LOG.info(user + " entered in search text box successfully.");
      this.driverCustom.getPresenceOfWebElement(RMConstants.RMREVIEWSPAGE_SELECT_USER_XPATH);
      Dropdown drdMatchingApprover = this.engine.findFirstElementWithDuration(
          Criteria.isDropdown().withLabel("Matching users:").inContainer(dlgSelectUser1), this.timeInSecs);
      for (WebElement ele : drdMatchingApprover.getWebElement()
          .findElements(By.xpath(CCMConstants.CCMWORKITEMEDITORPAGE_SEARCH_USER_XPATH))) {
        // verify the approval user is selectable.
        if (ele.getAttribute(CCMConstants.DISABLED) != null) {
          LOGGER.LOG.info(user + " : Approver can't be selected (Approver is disabled)");
             return user + ": Approver can't be selected (Approver is disabled)";
    }
      }
    }
    catch (Exception e) {
      String txtSearch = "//div[@class='SectionsPanel-spp']//input[@placeholder='Search']";
      this.driverCustom.isElementClickable(txtSearch, Duration.ofSeconds(30));
      WebElement search = this.driverCustom.getWebDriver().findElement(By.xpath(txtSearch));
      search.clear();
      search.sendKeys(user);
      LOGGER.LOG.info(user + " entered in search text box successfully.");
    }
    // Wait for loading completed
    this.driverCustom.isElementInvisible("//div[@class='loadingIndicator-spp bx--loading']", Duration.ofSeconds(5));
    // Is searching found or not
    String noResultsFoundXpath = "//div[@aria-label ='Select users']//div[@id='noSearchResultsMessageDiv-spp']";
    boolean isNoResultsFound = this.driverCustom.isElementVisible(noResultsFoundXpath, Duration.ofSeconds(15));
    if(isNoResultsFound) {
      LOGGER.LOG.info(user + " NOT FOUND");
    return user + " NOT FOUND";
    }
    // If user found then check if it was added or not (add the same user is not allow)
    try {
      String disabledXpath = String.format("//div[@aria-label = '%s']/ancestor::div[@data-testid='searchResults_data']/div[@class='UserEntry-spp']", user);
      this.driverCustom.isElementVisible(disabledXpath, Duration.ofSeconds(15));
      String disabled = this.driverCustom.getWebDriver().findElement(By.xpath(disabledXpath)).getAttribute("data-testid").trim();
      if (disabled.contains("disabled")) {
        LOGGER.LOG.info(user + " : Approver can't be selected (Approver is disabled)");
        return user + ": Approver can't be selected (Approver is disabled)";
      }
    }
    catch (Exception e1) {
    }
    // Click on 'Add' button
    String addButtonXpath = "//div[@aria-label='Add']";
    this.driverCustom.isElementClickable(addButtonXpath, Duration.ofSeconds(30));
    WebElement addButtonElement = this.driverCustom.getWebDriver().findElement(By.xpath(addButtonXpath));
    // Sometimes Engine Finder and Selenium can click on 'Add' button but nothing happens so I use JS Executor to do that
    JavascriptExecutor executor = (JavascriptExecutor) this.driverCustom.getWebDriver();
    executor.executeScript("arguments[0].click();", addButtonElement);
    LOGGER.LOG.info("Clicked on 'Add' button");
    // Click on 'OK' button
    Button btnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("Clicked on 'OK' button");
    LOGGER.LOG.info(user + " : Approver added successfully.");
    return user + ": Approver added successfully.";

  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select approvals tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   *
   * @param approvalType is the type of approval attibute.
   * @param date Input Due Date of Review.
   */
  public void setDueDate(final String approvalType, final String date) {
    waitForPageLoaded();
    try {
      Row rowApproval = this.engine.findElementWithDuration(Criteria.isRow().withText(approvalType), this.timeInSecs)
          .getFirstElement();
      Cell cllDueDate1 =
          this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowApproval).withIndex(4), this.timeInSecs)
          .getFirstElement();
      Text txtDueDate1 =
          this.engine.findFirstElementWithDuration(Criteria.isTextField().inContainer(cllDueDate1), this.timeInSecs);
      txtDueDate1.setText(date);
      LOGGER.LOG.info(date + " Due Date of " + approvalType + " Added successfully.");
    }
    catch (Exception e) {
      try {
        // handle on 05Q server
        String xpathDueDate =
            "(//span[contains(@class ,'SectionMenuAction_Enabled')][text()='DYNAMIC_VAlUE']/ancestor::tr[@class='SectionListTableRow']/td[@class='SectionListTableColumn'][.//div[@class='DatePicker']]//input)[last()]";
        WebElement eleDueDate = this.driverCustom.getWebElement(xpathDueDate, approvalType);
        eleDueDate.clear();
        eleDueDate.sendKeys(date);
      }
      catch (Exception e1) {
        throw new WebAutomationException(
            date + " Due Date of " + approvalType + " Not Added successfully.\n" + " or\n" + e1.getMessage());
      }
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select approvals tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   *
   * @param approvalType is the type of approval attribute.
   * @param date Input Due Date of Review.
   * @return true if correct due date added to the work item approvalType.
   */
  public boolean isDueDateAdded(final String approvalType, final String date) {
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(3));
    LOGGER.LOG.info("Checking whether " + date + " added to " + approvalType);
    return this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMPAGE_DUEDATE_INPUT_XPATH, approvalType)
        .getAttribute("value").equals(date);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select approvals tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   *
   * @param approvalType is the type of approval attribute.
   * @param user Input Approval of work item.
   * @return if approval(user) added to the work item approval.
   */
  public boolean isApprovarUserAdded(final String approvalType, final String user) {
    waitForPageLoaded();
    waitForSecs(5);
    LOGGER.LOG.info("Checking whether " + user + " added to " + approvalType);
    try {
      WebElement approval =
          this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMPAGE_APROVARUSER_INPUT_XPATH, approvalType);
      return approval.getText().equals(user);
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select approvals tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   *
   * @param approvalType is the type of approval attribute.
   */
  public void deleteApproval(final String approvalType) {
    try {
      Row rowApproval = this.engine.findElementWithDuration(Criteria.isRow().withText(approvalType), this.timeInSecs)
          .getFirstElement();

      Button deleteApproval = this.engine
          .findElementWithDuration(
              Criteria.isButton().withToolTip("Delete this approval from the work item").inContainer(rowApproval),this.timeInSecs)
          .getFirstElement();
      deleteApproval.click();
      LOGGER.LOG.info(approvalType + " approval Deleted from the work item successfully.");

    }
    catch (Exception e) {
      throw new WebAutomationException(approvalType +
          ": approval not found to delete from the work item approvals section or invalid, Please provide valid input.\n " +
          " \n" + e.getMessage());
    }
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select Links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   * <p>
   * Open any Work Items.<br>
   * Navigate to 'Links' or 'Process Links' section of the work item. <br>
   * Click on 'Add Related' drop down.<br>
   * Click on any of the Links like 'Add Implements Requirement', 'Add Tested By Test Case', 'Add Related','Add
   * Children','Add Related Test Plan' etc.... <br>
   * 'Add Link','Select Work Items' wizard will display as per the links option. <br>
   * Create Test Plan,Test Case,Work Items etc...as per option provided.
   *
   * @param additionalParams contains list of key value pair data.
   * @return name of the test case or test plan.
   */
  public String createLinkToExistingObject(final Map<String, String> additionalParams) {
    String rqmProjectArea = additionalParams.get(CCMConstants.RQM_PROJECT_AREA);
    String linkActionsStr = (additionalParams.get(CCMConstants.LINKACTIONS)).replace("_", " ");
    Panel panel = this.engine
        .findElementWithDuration(Criteria.isPanel().withTitle(additionalParams.get(CCMConstants.LINKS_SECTION)),Duration.ofSeconds(5)).getFirstElement();
    waitForSecs(5);
    Dropdown drdAddWILinks = this.engine
        .findElementWithDuration(
            Criteria.isDropdown().withText(additionalParams.get("dropdownText")).inContainer(panel), Duration.ofSeconds(5)).getFirstElement();
    try {
      //drdAddWILinks.scrollToElement();
      drdAddWILinks.selectOptionWithText(linkActionsStr);
    }
    catch(Exception e) {
     //drdAddWILinks.click();
     drdAddWILinks.scrollToElement();
      drdAddWILinks.selectOptionWithText(linkActionsStr);
    }
    
    LOGGER.LOG.info(linkActionsStr + " selected from " +
        (additionalParams.get("dropdownText") + " in the title " + additionalParams.get(CCMConstants.LINKS_SECTION)));
    switch (additionalParams.get("linkActions")) {
      // ----------Add Related-------------------------------------------------------------------
      case "Add Related":
        Dialog dlglink =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select Work Items"), this.timeInSecs)
        .getFirstElement();
        waitForSecs(3);
        Link label = this.engine
            .findElement(
                Criteria.isLink().withText(additionalParams.get("createLinkType") + "...").inContainer(dlglink))
            .getFirstElement();
        label.click();
        break;
        // ----------Add Implements Requirement-----------------------------------------------------
      case "Add Implements Requirement":
        createLinkAddImplementsRequirement(additionalParams);
        break;
        // ----------Add Tested By Test Case-----------------------------------------------------
      case "Add Tested By Test Case":
        String newTestCaseName = null;
        try {
          Dialog dlgAddLink =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), Duration.ofSeconds(10))
              .getFirstElement();
          Text txtSearch = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink), Duration.ofSeconds(10));
          txtSearch.setText(rqmProjectArea);
          LOGGER.LOG.info("Searched '" + rqmProjectArea + "' project area in 'Add Link' dialog.");
        }
        catch (Exception e) {
          Dialog dlgAddLink = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), Duration.ofSeconds(10)).getFirstElement();
          Text txtSearch = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink), Duration.ofSeconds(10));
          txtSearch.setText(rqmProjectArea);
          LOGGER.LOG.info("Searched '" + rqmProjectArea +
              "' project area in 'Add Link' dialog.");
        }
        Dropdown drdProjectArea =
            this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
                Duration.ofSeconds(10)).getFirstElement();
        drdProjectArea.selectOptionWithText(rqmProjectArea);
        LOGGER.LOG.info("Selected '" + rqmProjectArea +
            "' project area from 'Add Link' dialog.");
        waitForSecs(5);
        try {
          Dialog dlgAddLink =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), Duration.ofSeconds(10))
              .getFirstElement();
          RadioButton rdoCreateLink =
              this.engine.findElementWithDuration(Criteria.isRadioButton().inContainer(dlgAddLink), Duration.ofSeconds(5))
              .getElementByIndex(2);
          rdoCreateLink.click();
          LOGGER.LOG.info("Clicked on 'Create Link' radio button in 'Add Link' dialog.");
          Button btnOK = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink), Duration.ofSeconds(5))
              .getFirstElement();
          btnOK.click();
          LOGGER.LOG.info("Clicked on 'OK' button in 'Add Link' dialog.");
        }
        catch (Exception e) {
          Dialog dlgAddLink = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), Duration.ofSeconds(10)).getFirstElement();
          RadioButton rdoCreateLink =
              this.engine.findElementWithDuration(Criteria.isRadioButton().inContainer(dlgAddLink), Duration.ofSeconds(10))
              .getElementByIndex(2);
          rdoCreateLink.click();
          LOGGER.LOG.info("Clicked on 'Create Link' radio button in 'Add Link' dialog.");
          Button btnOK = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink), Duration.ofSeconds(10))
              .getFirstElement();
          btnOK.click();
          LOGGER.LOG.info("Clicked on 'OK' button in 'Add Link' dialog.");
        }
        this.driverCustom.isElementVisible("//div[@title='New Test Case']/span", Duration.ofSeconds(10));
        Dialog dlgNewTestCase = this.engine
            .findElementWithDuration(Criteria.isDialog().withTitle("New Test Case"), Duration.ofSeconds(5)).getFirstElement();
        if ((additionalParams.get(CCMConstants.TESTCASE_NAME) != null) &&
            (!additionalParams.get("testCaseName").isEmpty())) {
          newTestCaseName = (additionalParams.get("testCaseName")) + DateUtil.getCurrentDateAndTime();
          try {
            Text txtName = this.engine
                .findElementWithDuration(Criteria.isTextField().hasLabel("Name:").inContainer(dlgNewTestCase),Duration.ofSeconds(5)).getFirstElement();
            txtName.setText(newTestCaseName);
            LOGGER.LOG.info("Added test case name as - " + newTestCaseName);
          }
          catch (Exception e) {
            LOGGER.LOG.info(e);
          }
        }
        waitForSecs(2);
        try {
          Text txtWeight = this.engine
              .findElementWithDuration(Criteria.isTextField().hasLabel("Weight:").inContainer(dlgNewTestCase),Duration.ofSeconds(5)).getFirstElement();
          txtWeight.setText(additionalParams.get("testCaseWeight"));
          LOGGER.LOG.info("Added test case weight as - " + additionalParams.get("testCaseWeight"));
        }
        catch (Exception e) {
          LOGGER.LOG.info(e);
        }
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        this.driverCustom.switchToFrame(CCMConstants.NEW_TESTCASE_FRAME_XPATH);
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(5), "Domain:")) {
          Dropdown dndDomain = this.engine
              .findElementWithDuration(Criteria.isDropdown().withLabel("Domain:"), Duration.ofSeconds(5)).getFirstElement();
          if (additionalParams.get(CCMConstants.TESTCASE_DOMAIN_MORE_LINK_VALUE).contains("More")) {
            additionalParams.put(CCMConstants.TESTCASE_DOMAIN_MORE_LINK_VALUE, RQMConstants.MORE);
          }
          dndDomain.selectOptionWithText(additionalParams.get("testCaseDomainMoreLinkValue"));
          if (additionalParams.get("testCaseDomainMoreLinkValue").equalsIgnoreCase("More...")) {
            Dialog dlgDomain = this.engine
                .findElementWithDuration(Criteria.isDialog().withTitle("Domain"), Duration.ofSeconds(5)).getFirstElement();
            TextField searchBoxTextField = this.engine
                .findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to Search").inContainer(dlgDomain),Duration.ofSeconds(10)).getFirstElement();
            searchBoxTextField.setText(additionalParams.get(CCMConstants.DOMAIN_VALUE));
            LOGGER.LOG.info("Set domain value as " + additionalParams.get(CCMConstants.DOMAIN_VALUE));
            Cell cell = this.engine.findElementWithDuration(
                Criteria.isCell().withText(additionalParams.get("domainValue")).inContainer(dlgDomain), Duration.ofSeconds(10))
                .getFirstElement();
            cell.click();
            LOGGER.LOG.info("Clicked on domain value " + additionalParams.get("domainValue"));
            Button btnOK2 = this.engine
                .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgDomain), Duration.ofSeconds(5))
                .getFirstElement();
            btnOK2.click();
            LOGGER.LOG.info("Clicked on 'OK' button inside 'Domain' dialog.");
          }
        }
        // Test Type:
        // CATEGORIES_TESTTYPE_LABEL
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(5),
            additionalParams.get("CATEGORIES_TESTTYPE_LABEL"))) {
          Dropdown dndTestType = this.engine
              .findElementWithDuration(
                  Criteria.isDropdown().withLabel(additionalParams.get("CATEGORIES_TESTTYPE_LABEL")), this.timeInSecs)
              .getFirstElement();
          dndTestType.selectOptionWithText(additionalParams.get("testCaseTestType"));
          LOGGER.LOG.info("Selected the test type as - " + additionalParams.get("testCaseTestType"));
          this.driverCustom.getWebDriver().switchTo().defaultContent();
          this.driverCustom.switchToFrame(CCMConstants.NEW_TESTCASE_FRAME_XPATH);
        }
        // Test Case Level:
        // CATEGORIES_TESTCASE_LEVEL_LABEL
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(5),
            additionalParams.get("CATEGORIES_TESTCASE_LEVEL_LABEL"))) {
          Dropdown dndTestType = this.engine.findElementWithDuration(
              Criteria.isDropdown().withLabel(additionalParams.get("CATEGORIES_TESTCASE_LEVEL_LABEL")), Duration.ofSeconds(5))
              .getFirstElement();
          dndTestType.selectOptionWithText(additionalParams.get("testCaseLevel"));
          LOGGER.LOG.info("Selected the Test Case Level as - " + additionalParams.get("testCaseLevel"));
        }
        // Test Design Techniques:
        // CATEGORIES_TESTDESIGN_TECHNIQUES_LABEL
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(5),
            additionalParams.get("CATEGORIES_TESTDESIGN_TECHNIQUES_LABEL"))) {
          Dropdown dndTestType = this.engine.findElementWithDuration(
              Criteria.isDropdown().withLabel(additionalParams.get("CATEGORIES_TESTDESIGN_TECHNIQUES_LABEL")),
              Duration.ofSeconds(5)).getFirstElement();
          dndTestType.selectOptionWithText(additionalParams.get("testDesignTechniques"));
          LOGGER.LOG.info("Selected the Test Design Techniques as - " + additionalParams.get("testDesignTechniques"));
        }
        // Featuremodul:
        // CATEGORIES_FEATURE_MODUL_LABEL
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(5),
            additionalParams.get("CATEGORIES_FEATURE_MODUL_LABEL"))) {
          Dropdown dndTestType = this.engine.findElementWithDuration(
              Criteria.isDropdown().withLabel(additionalParams.get("CATEGORIES_FEATURE_MODUL_LABEL")), Duration.ofSeconds(5))
              .getFirstElement();
          dndTestType.selectOptionWithText(additionalParams.get("featuremodul"));
          LOGGER.LOG.info("Selected the Feature module as - " + additionalParams.get("featuremodul"));
        }
        // Link to review protocol (URL to reviewtool / sharepoint):
        // CATEGORIES_URL_LABEL
        WebElement txtUrlLabel = null;
        try {
          txtUrlLabel = this.driverCustom.getWebElement(CCMConstants.TESTCASE_LABEL_XPATH,
              additionalParams.get("CATEGORIES_URL_LABEL"));
          waitForSecs(2);
        }
        catch (Exception ex) {}
        if (txtUrlLabel != null) {
          WebElement txtUrl =
              this.driverCustom.getWebElement("//label[text()='DYNAMIC_VAlUE']/../../following-sibling::td//textarea",
                  additionalParams.get("CATEGORIES_URL_LABEL"));
          txtUrl.click();
          txtUrl.sendKeys(additionalParams.get("TEST_CASE_URL"));
          LOGGER.LOG.info("Set the URL link to Protocol as - " + additionalParams.get("TEST_CASE_URL"));
        }
        try {
         // JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
      // js.executeScript("window.scrollBy(0,300)");
      //    LOGGER.LOG.info("Scroll down");
        this.driverCustom.click("//div[@class='remoteDialog']//button[text()='OK']");
        LOGGER.LOG.info("Clicked on 'OK' button.");
        this.driverCustom.isElementInvisible("//div[text()='Creating artifact...']", Duration.ofSeconds(15));
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        }catch (Exception e) {
       //    TODO: handle exception
        
        Button btnOK2 =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(30)).getFirstElement();
        btnOK2.click();
        LOGGER.LOG.info("Clicked on 'OK' button.");
        this.driverCustom.isElementInvisible("//div[text()='Creating artifact...']", Duration.ofSeconds(15));
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        }
        return newTestCaseName;
        
      case "Add Related Test Plan":
        String newTestPlanName = null;
        try {
          Dialog dlgAddLink2 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), Duration.ofSeconds(5))
              .getFirstElement();
          Text txtSearch2 = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText("Search...").inContainer(dlgAddLink2), Duration.ofSeconds(10));
          txtSearch2.setText(rqmProjectArea);
          LOGGER.LOG.info("Searched '" + rqmProjectArea +
              "' project area in 'Add Link' dialog.");
        }
        catch (Exception e) {
          Dialog dlgAddLink2 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          Text txtSearch2 = this.engine.findFirstElementWithDuration(
              Criteria.isTextField().withText("Search...").inContainer(dlgAddLink2), this.timeInSecs);
          txtSearch2.setText(rqmProjectArea);
          LOGGER.LOG.info("Searched '" + rqmProjectArea +
              "' project area in 'Add Link' dialog.");
        }
        Dropdown drdProjectArea2 =
            this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
                Duration.ofSeconds(5)).getFirstElement();
        drdProjectArea2.selectOptionWithText(rqmProjectArea);
        LOGGER.LOG.info("Selected '" + rqmProjectArea +
            "' project area from 'Add Link' dialog.");
        try {
          Dialog dlgAddLink2 =
              this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), Duration.ofSeconds(10))
              .getFirstElement();
          RadioButton rdoCreateLink2 =
              this.engine.findElementWithDuration(Criteria.isRadioButton().inContainer(dlgAddLink2), Duration.ofSeconds(5))
              .getElementByIndex(2);
          rdoCreateLink2.click();
          LOGGER.LOG.info("Clicked on 'Create Link' radio button in 'Add Link' dialog.");
          Button btnOK3 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink2), Duration.ofSeconds(5))
              .getFirstElement();
          btnOK3.click();
          LOGGER.LOG.info("Clicked on 'OK' button in 'Add Link' dialog.");
        }
        catch (Exception e) {
          Dialog dlgAddLink2 = this.engine
              .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
          RadioButton rdoCreateLink2 =
              this.engine.findElementWithDuration(Criteria.isRadioButton().inContainer(dlgAddLink2), this.timeInSecs)
              .getElementByIndex(2);
          rdoCreateLink2.click();
          LOGGER.LOG.info("Clicked on 'Create Link' radio button in 'Add Link' dialog.");
          Button btnOK3 = this.engine
              .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink2), this.timeInSecs)
              .getFirstElement();
          btnOK3.click();
          LOGGER.LOG.info("Clicked on 'OK' button in 'Add Link' dialog.");
        }
        this.driverCustom.isElementVisible("//div[@title='New Test Plan']/span", Duration.ofSeconds(10));
        Dialog dlgNewTestPlan = this.engine
            .findElementWithDuration(Criteria.isDialog().withTitle("New Test Plan"), Duration.ofSeconds(10)).getFirstElement();
        if ((additionalParams.get(CCMConstants.TESTPLAN_NAME) != null) &&
            (!additionalParams.get("testPlanName").isEmpty())) {
          newTestPlanName = (additionalParams.get("testPlanName")) + DateUtil.getCurrentDateAndTime();
          try {
            Text txtName = this.engine
                .findElementWithDuration(Criteria.isTextField().hasLabel("Name:").inContainer(dlgNewTestPlan),
                    Duration.ofSeconds(10))
                .getFirstElement();
            txtName.setText(newTestPlanName);
            LOGGER.LOG.info("Set the test plan name as - " + newTestPlanName);
          }
          catch (Exception e) {
            LOGGER.LOG.info(e);
          }
        }
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        this.driverCustom.switchToFrame(CCMConstants.NEW_TESTCASE_FRAME_XPATH);
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(2), "Product:")) {
          Dropdown dndProduct = this.engine
              .findElementWithDuration(Criteria.isDropdown().withLabel("Product:"), Duration.ofSeconds(2)).getFirstElement();
          dndProduct.selectOptionWithText(additionalParams.get("testPlanProduct"));
          LOGGER.LOG.info("Selected the product of the test plan as - " + additionalParams.get("testPlanProduct"));
        }
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(2), "Release:") &&
            this.driverCustom.isElementVisible("//label[text()='Release:']/following-sibling::span[text()='*']", Duration.ofSeconds(2))) {
          Dropdown dndRelease = this.engine
              .findElementWithDuration(Criteria.isDropdown().withLabel("Release:"), Duration.ofSeconds(2)).getFirstElement();
          if (additionalParams.get(CCMConstants.TESTPLAN_RELEASE_MORE_LINK_VALUE).contains("More")) {
            additionalParams.put(CCMConstants.TESTPLAN_RELEASE_MORE_LINK_VALUE, RQMConstants.MORE);
          }
          dndRelease.selectOptionWithText(additionalParams.get("testPlanReleaseMoreLinkValue"));
          if (additionalParams.get("testPlanReleaseMoreLinkValue").equalsIgnoreCase("More...")) {
            Dialog dlgRelease = this.engine
                .findElementWithDuration(Criteria.isDialog().withTitle("Release"), Duration.ofSeconds(2)).getFirstElement();
            TextField searchBoxTextField = this.engine
                .findFirstElement(Criteria.isTextField().withPlaceHolder("Type to Search").inContainer(dlgRelease));
            searchBoxTextField.setText(additionalParams.get(CCMConstants.TESTPLAN_RELEASE_VALUE));
            LOGGER.LOG.info("Set release value of the test plan as - " + additionalParams.get("testPlanReleaseValue"));
            Row rowRelease =
                this.engine
                .findElementWithDuration(
                    Criteria.isRow().containsText(additionalParams.get("testPlanReleaseValue")), Duration.ofSeconds(2))
                .getFirstElement();
            Checkbox cbRelease =
                this.engine.findElementWithDuration(Criteria.isCheckbox().inContainer(rowRelease), Duration.ofSeconds(2))
                .getFirstElement();
            cbRelease.click();
            LOGGER.LOG.info("Clicked on searched Release check box.");
            Button btnOK5 = this.engine
                .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgRelease), Duration.ofSeconds(2))
                .getFirstElement();
            btnOK5.click();
            LOGGER.LOG.info("Clicked on 'OK' button in 'Release' dialog.");
            this.driverCustom.switchToFrame(CCMConstants.NEW_TESTCASE_FRAME_XPATH);
          }
        }
        if (this.driverCustom.isElementVisible(CCMConstants.TESTCASE_LABEL_XPATH, Duration.ofSeconds(2), "Test Phase:")) {
          Dropdown dndTestPhase =
              this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Test Phase:"), Duration.ofSeconds(2))
              .getFirstElement();
          dndTestPhase.selectOptionWithText(additionalParams.get("testPlanTestPhase"));
          LOGGER.LOG.info("Selected the test phase of the test plan as - " + additionalParams.get("testPlanTestPhase"));
        }
        waitForSecs(2);
        Button btnOk =
            this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(5)).getFirstElement();
        btnOk.click();
        LOGGER.LOG.info("Clicked on 'OK' button.");
        this.driverCustom.getWebDriver().switchTo().defaultContent();
        waitForSecs(Duration.ofSeconds(10));
        return newTestPlanName;
      default:
        throw new WebAutomationException(
            "WorkItem link type not supported (yet)" + additionalParams.get("linkActions"));
    }
    return linkActionsStr;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <p>
   * After saving work item using {@link #saveWorkItem()} by setting work item state as 'close' using
   * {@link #setStatus(String)} and <br>
   * set resolution as 'Duplicate' using {@link #setResolution(String)}.
   * <p>
   * Add work item by clicking on the link displayed in notification area,search work item by id from 'Select Work Item'
   * dialog select if matched work item found. click on 'OK' button to add link.
   *
   * @param workitemID is which work item id to add.
   */
  public void addWorkItemFromNotificationAreaLink(final String workitemID) {

    WebElement duplicateLInk =
        this.driverCustom.getWebElement(CCMConstants.CCMWORKITEMPAGE_NOTIFICATIONAREA_LINK_XPATH);
    this.driverCustom.clickUsingActions(duplicateLInk);
    LOGGER.LOG.info("Clicked on link what displayed in notification area successfully ");
    Dialog duplicateItemlink = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Select Work Item"), this.timeInSecs).getFirstElement();
    Text txtSearchParentWI = this.engine.findFirstElementWithDuration(Criteria.isTextField()
        .hasLabel("Work Item Number or Words Contained in the Text. Use quotes for a phrase search:")
        .inContainer(duplicateItemlink), this.timeInSecs);
    txtSearchParentWI.setText(workitemID);
    LOGGER.LOG.info(workitemID + " passed in Work Item Number search field successfully");
    waitForSecs(Duration.ofSeconds(5));
    Dropdown drdMatchingWorkItem = this.engine
        .findFirstElement(Criteria.isDropdown().withLabel("Matching Work Items:").inContainer(duplicateItemlink));
    drdMatchingWorkItem.selectOptionWithPartText(workitemID);
    LOGGER.LOG.info(workitemID + " selected from dropdown result section successfully");
    waitForSecs(Duration.ofSeconds(5));
    try {
      Button btnOK1 = this.engine
          .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(duplicateItemlink), this.timeInSecs)
          .getFirstElement();
      waitForSecs(Duration.ofSeconds(3));
      btnOK1.click();
      LOGGER.LOG.info("Clicked on ok button and  work item id '" + workitemID + "' added successfully");
    }
    catch (Exception e) {
      throw new WebAutomationException(
          "Work item '" + workitemID + "' not added successfully" + "or\n" + e.getMessage());
    }
  }

  /**
   * <p>
   * This method used after selecting Acceptance tab {@link #selectTab(String)} add the test case in Criteria of
   * Acceptance section by selecting Add Tested By Test Case from drop down and clicking ok button from Add Link dialog
   * , clear Name text field Then pass test case name in name text field then click Filter button in result section sect
   * respective searched test case then click on ok.
   *
   * @param AcceptanceActions pass section as Criteria of Acceptance
   * @param TestCaseName pass name of test case.
   */
  public void addAcceptancetest(final String AcceptanceActions, final String TestCaseName) {

    // Add Tested By
    Dropdown drdAddWIIncludedinPackage = this.engine
        .findElementWithDuration(Criteria.isDropdown().withText(AcceptanceActions), this.timeInSecs).getFirstElement();
    drdAddWIIncludedinPackage.selectOptionWithText(AcceptanceActions);
    waitForSecs(2);
    // Click Ok on Add Link dialog
    Dialog dlgSelectUser = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Add Link:"), this.timeInSecs).getFirstElement();
    waitForSecs(Duration.ofSeconds(10));
    Button btnOK1 = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgSelectUser), this.timeInSecs)
        .getFirstElement();
    btnOK1.click();
    LOGGER.LOG.info("OK button in Add Link dialog is clicked successfully.");
    waitForSecs(15);
    // Search and Select Test Case
    // Set ID text field empty

    Button btnClearAllFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear Filter Text"), this.timeInSecs)
        .getFirstElement();
    btnClearAllFilter.click();
    Text searchBox =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type filter text and press Enter"),
            this.timeInSecs).getFirstElement();
    searchBox.setText(TestCaseName);
    LOGGER.LOG.info(TestCaseName + " passed in Select Test Case search field successfully");
    Button btnFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withText("Filter"), this.timeInSecs).getFirstElement();
    btnFilter.click();
    LOGGER.LOG.info("Filter button is clicked successfully.");
    waitForSecs(Duration.ofSeconds(5));
    Row rowTestCases =
        this.engine.findElementWithDuration(Criteria.isRow().withText(TestCaseName), this.timeInSecs).getFirstElement();
    Cell cellCheckBox =
        this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowTestCases).withIndex(1), this.timeInSecs)
        .getFirstElement();
    Checkbox cbxTestCaSe = this.engine
        .findElementWithDuration(Criteria.isCheckbox().inContainer(cellCheckBox), this.timeInSecs).getFirstElement();
    cbxTestCaSe.click();
    waitForSecs(Duration.ofSeconds(5));
    Button btnOK2 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK2.click();
    LOGGER.LOG.info("OK button in Select Test Case dialog is clicked successfully.");
    waitForSecs(2);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} epic or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select approvals tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <P>
   * This function used in epic planning after selecting planning tan {@link #selectTab(String)} This method used to add
   * pilot work item in epic planning page
   *
   * @param epic_PilotWIName pass pilot work item name
   */
  public void addPilotPlanningWorkItem(final String epic_PilotWIName) {
    addPilotPlanningWorkItem(epic_PilotWIName, "FALSE");
  }

  /**
   * <P>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} epic or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <p>
   * This function used in epic planning after selecting planning tan {@link #selectTab(String)} This method used to add
   * pilot work item in epic planning page
   *
   * @param epic_PilotWIName pass pilot work item name
   * @param isGCProjectArea true if GC project area like 05 server
   */
  public void addPilotPlanningWorkItem(final String epic_PilotWIName, final String isGCProjectArea) {

    // Add Pilot Planning
    Button btnAdd = null;
    if (Boolean.valueOf(isGCProjectArea)) {
      btnAdd = this.engine
          .findElementWithDuration(Criteria.isButton().withToolTip("Select a value from a list"), this.timeInSecs)
          .getFirstElement();
    }
    else {
      btnAdd =
          this.engine.findElementWithDuration(Criteria.isButton().withText("Add"), this.timeInSecs).getFirstElement();
    }
    btnAdd.click();
    LOGGER.LOG.info("Add button is clicked successfully.");

    Dialog dlgSelectWorkItem = null;
    if (Boolean.valueOf(isGCProjectArea)) {
      dlgSelectWorkItem =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select Work Item"), this.timeInSecs)
          .getFirstElement();
    }
    else {
      dlgSelectWorkItem =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select Work Items"), this.timeInSecs)
          .getFirstElement();
    }
    Text txtSearchParentWI = this.engine.findElementWithDuration(Criteria.isTextField()
        .hasLabel("Work Item Number or Words Contained in the Text. Use quotes for a phrase search:")
        .inContainer(dlgSelectWorkItem), this.timeInSecs).getFirstElement();
    txtSearchParentWI.setText(epic_PilotWIName);
    LOGGER.LOG.info(epic_PilotWIName + " is typed in Search textbox successfully.");
    waitForSecs(Duration.ofSeconds(10));
    Dropdown drdMatchingWorkItem =
        this.engine
        .findElementWithDuration(
            Criteria.isDropdown().withLabel("Matching Work Items:").inContainer(dlgSelectWorkItem), this.timeInSecs)
        .getFirstElement();
    drdMatchingWorkItem.selectOptionWithPartText(epic_PilotWIName);
    LOGGER.LOG.info(epic_PilotWIName + " username is selected successfully.");
    // Click OK button
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgSelectWorkItem), this.timeInSecs)
        .getFirstElement();
    btnOK.click();
    LOGGER.LOG.info("OK button is clicked successfully.");

  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} epic or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method delete after adding Pilot {@link CCMWorkItemEditorPage#addPilotPlanningWorkItem(String)}.
   *
   * @param epic_PilotWIName pass pilot work item name
   */
  public void deletePilotPlanningWorkItem(final String epic_PilotWIName) {
    deletePilotPlanningWorkItem(epic_PilotWIName, "FALSE");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} epic or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method delete after adding Pilot {@link CCMWorkItemEditorPage#addPilotPlanningWorkItem(String)}.
   *
   * @param epic_PilotWIName pass pilot work item name
   * @param isGCProjectArea true if GC project area like 05 server
   */
  public void deletePilotPlanningWorkItem(final String epic_PilotWIName, final String isGCProjectArea) {
    if (!Boolean.valueOf(isGCProjectArea)) {
      // Remove previous Pilot
      WebElement lnkPreviousPilot =
          this.driverCustom.getWebElement(("//a[contains(.,'DYNAMIC_VAlUE')]//ancestor::span"), epic_PilotWIName);

      Actions actions = new Actions(this.driverCustom.getWebDriver());
      actions.moveToElement(lnkPreviousPilot).build().perform();
      waitForSecs(2);
      List<WebElement> btnDeleteList2 =
          this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_DELETECONTACTPERSONE_BUTTON_XPATH);
      for (WebElement del : btnDeleteList2) {
        if (del.isDisplayed()) {
          del.click();
          LOGGER.LOG.info(epic_PilotWIName + " is removed successfully.");
          return;
        }
      }
    }
    else {
      Button btnClear =
          this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear the value"), this.timeInSecs)
          .getFirstElement();
      btnClear.click();
      LOGGER.LOG.info(epic_PilotWIName + " is removed successfully.");
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} epic or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)}
   * <P>
   * This method calling after adding pilot work item in epic planning section {@link #addPilotPlanningWorkItem(String)}
   * to validate the added pilot work item
   *
   * @param epic_PilotWIName pass pilot work item name
   * @return boolean value true means pilot work item is added if false pilot work item is not added.
   */
  public boolean isPilotPlanningWorkItemAdded(final String epic_PilotWIName) {
    WebElement lnkPreviousPilot =
        this.driverCustom.getWebElement(("//a[contains(.,'DYNAMIC_VAlUE')]"), epic_PilotWIName);
    return lnkPreviousPilot != null;
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select Links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   *
   * @param task_ChildrenWIName name of the children work item
   * @return true if work item is added.
   */
  public boolean isWorkItemAddedInProcessLinkSection(final String task_ChildrenWIName) {
    // Verify process link
    waitForSecs(Duration.ofSeconds(5));
    try {
      WebElement lnkProcessLink =
          this.driverCustom.getWebElement("//a[contains(.,'DYNAMIC_VAlUE')]", task_ChildrenWIName);
      return lnkProcessLink != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select Links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   *
   * @param testCaseName name of the test case
   * @return true if test case is visible
   */
  public boolean isTestArtifactAddedInLinksSection(final String testCaseName) {
    waitForPageLoaded();
    try {
      List<WebElement> lnkProcessLink =
          this.driverCustom.getWebElements("//a[contains(.,'DYNAMIC_VAlUE')]", testCaseName);
      return lnkProcessLink != null;
    }
    catch (Exception e) {
      return false;
    }
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select Link tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <P>
   * This method used after selecting Link tab {@link #selectTab(String)}to add existing link in Link sections.
   *
   * @param dropdownName pass drop down name in Link section Add Work Items Included in Packages...
   * @param selectdropdownOption select drop down options Add Related Test Case,Add Tested By Test Case
   * @param TestCaseName pass test case name which one user need to link.
   */
  public void addTestCaseInLinkSection(final String dropdownName, final String selectdropdownOption,
      final String TestCaseName) {
    addTestCaseInLinkSection(dropdownName, selectdropdownOption, TestCaseName, null);
  }


  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select Links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <P>
   * This method used after selecting Link tab {@link #selectTab(String)}to add existing link in Link sections.
   *
   * @param dropdownName pass drop down name in Link section Add Work Items Included in Packages...
   * @param selectdropdownOption select drop down options Add Related Test Case,Add Tested By Test Case
   * @param TestCaseName pass test case name which one user need to link.
   * @param qmProjectName the name of QM project area.
   */
  public void addTestCaseInLinkSection(final String dropdownName, final String selectdropdownOption,
      final String TestCaseName, final String qmProjectName) {

    // Add Tested By
    Dropdown drdAddWIIncludedinPackage = this.engine
        .findElementWithDuration(Criteria.isDropdown().withText(dropdownName), this.timeInSecs).getFirstElement();
    drdAddWIIncludedinPackage.selectOptionWithText(selectdropdownOption);
    waitForSecs(Duration.ofSeconds(5));
    // Click Ok on Add Link dialog
    Dialog dlgAddLink = this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Add Link:"), this.timeInSecs)
        .getFirstElement();
    waitForSecs(Duration.ofSeconds(5));
    if (qmProjectName != null) {
      Dropdown drdSelectProject = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(
          "Select either the location of the existing artifact you want to link to or the location of the new artifact you want to create"),
          this.timeInSecs).getFirstElement();
      drdSelectProject.selectOptionWithText(qmProjectName);
    }
    Button btnOK1 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink), this.timeInSecs)
        .getFirstElement();

    btnOK1.click();
    waitForSecs(Duration.ofSeconds(10));
    Button btnClearAllFilter =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Clear Filter Text"), this.timeInSecs)
        .getFirstElement();
    btnClearAllFilter.click();
    Text searchBox =
        this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type filter text and press Enter"),
            this.timeInSecs).getFirstElement();
    searchBox.setText(TestCaseName);

    Button btnRun = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Filter"), this.timeInSecs)
        .getFirstElement();
    btnRun.click();

    Row rowTestCase =
        this.engine.findElementWithDuration(Criteria.isRow().withText(TestCaseName), this.timeInSecs).getFirstElement();
    Cell cllCheckbox =
        this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowTestCase).withIndex(1), this.timeInSecs)
        .getFirstElement();
    Checkbox cbxTestCase = this.engine
        .findElementWithDuration(Criteria.isCheckbox().inContainer(cllCheckbox), this.timeInSecs).getFirstElement();
    cbxTestCase.click();
    waitForSecs(Duration.ofSeconds(3));
    Button btnOK2 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    btnOK2.click();
    waitForSecs(2);
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select Links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <P>
   * This method used after adding existing link in process link section
   * {@link CCMWorkItemEditorPage#addLinkToExistingObject(Map)} this method will delete work item from process link
   * section
   *
   * @param workitemName pass work item name to delete
   */
  public void deleteAllLinks(final String workitemName) {
    // Delete the previous process link
//    JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
//    js.executeScript("window.scrollBy(0,300)");
    WebElement lnkPreviousProcessLink = this.driverCustom.getWebElement(("//a[contains(.,'" + workitemName + "')]//ancestor::span"));;
    
    Actions actions = new Actions(this.driverCustom.getWebDriver());
    try {
      actions.moveToElement(lnkPreviousProcessLink).build().perform();
    }
    catch(Exception e){
      lnkPreviousProcessLink.click();
    }
    
    waitForSecs(3);
    WebElement wiIcon = this.driverCustom.getWebElement("//a[contains(.,'" + workitemName + "')]//ancestor::span/preceding-sibling::img");
    wiIcon.click();
//    List<WebElement> btnDeleteList2 =
//        this.driverCustom.getWebElements(CCMConstants.CCMWORKITEMEDITORPAGE_DELETECONTACTPERSONE_BUTTON_XPATH);
//    for (WebElement del : btnDeleteList2) {
//      if (del.isDisplayed()) {
//        del.click();
//        break;
//      }
//    }
    WebElement btnDeleteList2 =
        this.driverCustom.getWebElement("//a[contains(.,'" + workitemName + "')]//ancestor::div/following-sibling::span[@title='Remove' or @title='Delete']",workitemName);
    if(btnDeleteList2.isDisplayed()) {
      btnDeleteList2.click();
    }
    LOGGER.LOG.info("Removed '" + workitemName + "' from the 'Links' section of the work item.");
  }

  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},create new work item
   * {@link CCMProjectAreaDashboardPage #openMenu(String)}, open sub menu
   * {@link CCMProjectAreaDashboardPage #openSubMenu(String)} or
   * <p>
   * search existing Work item epic {@link CCMProjectAreaDashboardPage #quickSearch(String)}
   * ,{@link CCMProjectAreaDashboardPage#openSearchedSpecification(String)},select Links tab
   * {@link CCMWorkItemEditorPage#selectTab(String)}}
   * <p>
   * Open any 'WorkItem'.<br>
   * Navigate to 'Links' section of the work item. <br>
   * Add any type of link in Links section. <br>
   * Click on the added link.
   *
   * @param linkSection link section as 'Process Links','Links'.
   * @param linkId id of the test case,work item,test plan etc..
   * @param linkType type of the links
   */
  public void clickOnLinkFromWorkItemLinksSection(final String linkSection, final String linkId,
      final String linkType) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement("//div[@class='landmark']").click();
    for (WebElement ele : getLinksList(linkSection, linkType)) {
      if (ele.getText().startsWith(CCMConstants.LINK_ID + ":") || ele.getText().contains(linkId)) {
        try{
          String testCaseID = ele.getText();
          Link lnkTestCase =
              this.engine.findFirstElementWithDuration(Criteria.isLink().withText(testCaseID), this.timeInSecs);
          lnkTestCase.click();
          waitForSecs(Duration.ofSeconds(5));
        }catch (Exception e) {
          this.driverCustom.getWebElement("//*[contains(text(),'"+ linkId +"')]").click();
          waitForSecs(Duration.ofSeconds(5));
        }
      }     
    }
    LOGGER.LOG.info("Clicked on the link '" + linkId + "' added in '" + linkSection +
        "' Section of the work item under '" + linkType + "'.");
  }

  /**
   * <p>
   * Open any 'WorkItem'.<br>
   * Navigate to 'Links' section of the work item. <br>
   * Add 'Tested By Test Case','Add Related Test Case' link in Links section. <br>
   * Click on the added link,it will navigate to RQM construction Page. <br>
   * Navigate to 'Development Items'section and verify work item is added.
   *
   * @param testCaseId id of the test case.
   * @return true if work item is visible.
   */
  public boolean isWorkItemVisibleInTestCase(final String testCaseId) {
    waitForSecs(Duration.ofSeconds(5));
    refresh();
    LOGGER.LOG.info("page refreshed successfully (to loade updated data).");
    WebElement sideBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
    if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", Duration.ofSeconds(15))) {
      sideBar.click();
    }
    RQMConstructionPage rqmConsPage = new RQMConstructionPage(this.driverCustom);
    this.driverCustom.isElementClickable(RQMConstants.RQMPROJECT_TESTCASE_SECTIONS_XPATH, Duration.ofSeconds(10),
        RQMSectionMenus.DEVELOPMENT_ITEMS.toString());
    Link lnkDevItem = this.engine.findFirstElementWithDuration(
        Criteria.isLink().withText(RQMSectionMenus.DEVELOPMENT_ITEMS.toString()), this.timeInSecs);
    lnkDevItem.click();
    waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("Clicked on 'Development Items' section of the test case.");
    int count = 0;
    while (count < 10) {
      this.driverCustom.click("//img[@class='button-img refresh-icon-image'][@alt='Refresh Development Items']");
      waitForSecs(Duration.ofSeconds(5));
      for (WebElement ele : rqmConsPage.getRequirementLinks()) {
        if (ele.getText().trim().startsWith(CCMConstants.TESTCASE_ID + ":") || (ele.getText().trim().contains(testCaseId))) {
          LOGGER.LOG.info("Verified work item is visible in 'Development Items' section of the test case.");
          return true;
        }
      }
      count++;
    }   
    throw new WebAutomationException(
        testCaseId + " not found under link type " + RQMSectionMenus.DEVELOPMENT_PLAN_LINKS.toString());
  }
  /**
   * <p>
   * Open any 'WorkItem'.<br>
   * Navigate to 'Links' section of the work item. <br>
   * Add 'Tested By Test Case','Add Related Test Case' link in Links section. <br>
   * Click on the added link,it will navigate to RQM construction Page. <br>
   * Navigate to 'Development Items'section and click on the wotk item.
   *
   * @param workItemId Id of the work item
   */
  public void clickOnWorkItemFromTestCase(final String workItemId) {
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(10), workItemId);
    Row rowWorkItem =
        this.engine.findElementWithDuration(Criteria.isRow().withText(workItemId), this.timeInSecs).getFirstElement();
    Cell cellWorkItem =
        this.engine.findElementWithDuration(Criteria.isCell().inContainer(rowWorkItem).withIndex(3), this.timeInSecs)
        .getFirstElement();
    Link linkWorkItem =
        this.engine.findFirstElementWithDuration(Criteria.isLink().inContainer(cellWorkItem), this.timeInSecs);
    linkWorkItem.click();
    LOGGER.LOG.info("Clicked on '" + workItemId + "' from the test case.");
  }

  /**
   * <p>
   * Open any 'WorkItem'.<br>
   * Navigate to 'Links' section of the work item. <br>
   * 'Add Related Test Plan' from Links section of the work item. <br>
   * Verify work item is visible in the added test plan.
   *
   * @param workItemId id of the work item.
   * @return true if added work item is visible in the test plan.
   */
  public boolean isWorkItemVisibleInTestArtifact(final String workItemId) {
    for (int i = 1; i <= 5; i++) {
    waitForSecs(5);
    refresh();
    // Click on Left arrow if Related site is collapsed.
    WebElement sideBar = this.driverCustom.getPresenceOfWebElement("//div[@class='show-bar show-bar-main']");
    if (this.driverCustom.isElementPresent("//div[@class='arrow-left']", Duration.ofSeconds(15))) {
      sideBar.click();
    }
    JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
    js.executeScript("window.scrollBy(0,300)");
    LOGGER.LOG.info("Verified " + workItemId + " displayed in the Test Plan.");
    if ((this.driverCustom.isElementVisible("//a[contains(text(),'" + workItemId + "')]/ancestor::li", Duration.ofSeconds(10))) ||
        (this.driverCustom.isElementVisible("//a/strike[contains(text(),'" + workItemId + "')]/ancestor::li", Duration.ofSeconds(10)))) {
      return true;
    }
      for (int j = 1; j <= 10; j++) {
        if (this.driverCustom.isElementClickable(CCMConstants.CCMWORKITEM_NEXT_ENABLE_BUTTON_XPATH, Duration.ofSeconds(20))) {
          this.driverCustom.click(CCMConstants.CCMWORKITEM_NEXT_ENABLE_BUTTON_XPATH);
          if ((this.driverCustom.isElementVisible("//a[contains(text(),'" + workItemId + "')]/ancestor::li", Duration.ofSeconds(10))) ||
              (this.driverCustom.isElementVisible("//a/strike[contains(text(),'" + workItemId + "')]/ancestor::li",
                  Duration.ofSeconds(10)))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * <p>
   * Open any 'WorkItem'.<br>
   * Navigate to 'Links' section of the work item. <br>
   * 'Add Related Test Plan' from Links section of the work item. <br>
   * Click on the Work Item from the Test Plan.
   *
   * @param workItemId work item id with name.
   */
  public void clickOnWorkItemFromTestArtifact(final String workItemId) {
    this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_LINKS_XPATH, Duration.ofSeconds(10), workItemId);
    try {
      this.driverCustom.click("//a[contains(text(),'" + workItemId + "')]/ancestor::li");
      LOGGER.LOG.info("Clicked on '" + workItemId + "'.");
    }
    catch (Exception e) {
      this.driverCustom.click("//a/strike[contains(text(),'" + workItemId + "')]/ancestor::li");
      LOGGER.LOG.info("Clicked on '" + workItemId + "'.");
    }
  }

  /**
   * <p>
   * Open any work item.<br>
   * Navigate to Links section of the work item. <br>
   * 'Add Related Test Case' from Links section <br>
   * Try to add same test case which already added in Links section. <br>
   * Verify 'Save' button is disabled.
   *
   * @return true if the Save button is disabled.
   */
  public boolean isTestCaseDuplicated() {
    waitForPageLoaded();
    LOGGER.LOG.info("Verified 'Save' button is disabled and added test case is duplicated.");
    return this.driverCustom.isElementVisible("//button[@disabled and text()='Save']", Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Open any work item.<br>
   * Navigate to Links section of the work item. <br>
   * Click on 'Add Children' from Links section. <br>
   * Try to add the same work item as Parent work item. <br>
   * Verify trying to add children work item is disabled in 'Select Work Items' widget.
   *
   * @param workItem id with name of the work item.
   * @return true if work item is disabled.
   */
  public boolean isWorkItemDisabled(final String workItem) {
    waitForPageLoaded();
    LOGGER.LOG.info("Verified searched '" + workItem + "' is disabled.");
    return this.driverCustom.isElementVisible("//option[@disabled and text()='" + workItem + "']", Duration.ofSeconds(10));
  }

  /**
   * @param drdLabel the label of the dropdown
   * @return the boolean value
   */
  public boolean isReadOnlyDropdown(final String drdLabel) {
    waitForPageLoaded();
    LOGGER.LOG.info("Verified dropdown with label: '" + drdLabel + "' is disabled.");
    Dropdown drdElement =
        this.engine.findElementWithinDuration(Criteria.isDropdown().withLabel(drdLabel), Duration.ofSeconds(15)).getFirstElement();
    return drdElement.getWebElement().findElement(By.xpath("..")).getAttribute("class").contains("DynamicReadOnly");
  }

  /**
   *
   */
  public void clickOnRefreshButton() {
    waitForPageLoaded();
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getPresenceOfWebElement("//a[@title='Refresh the work item values with its saved state.']");
    this.driverCustom.getWebElement("//a[@title='Refresh the work item values with its saved state.']").click();
    waitForSecs(8);
  }

  /**
   * <p>
   * Open any existing work item. <br>
   * Click on the description field of the work item. <br>
   * Edit the description field. <br>
   * Select all the added Description text and change the font style as Bold,Italic,Underline etc....
   *
   * @param font to be modify in the Description field of the work item with font style Bold,Italic,Underline etc...
   */
  public void modifyDescriptionTextFont(final String font) {
    String descriptionXpath = "//div[@aria-label='Description']";
    this.driverCustom.isElementVisible(descriptionXpath, Duration.ofSeconds(30));
    WebElement description = this.driverCustom.getWebDriver().findElement(By.xpath(descriptionXpath));
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.moveToElement(description).click().perform();
    description.sendKeys(Keys.CONTROL, "A");
    String fontButtonXpath = "//a[@title='DYNAMIC_VAlUE' and @role='button']";
    this.driverCustom.click(fontButtonXpath, font);
  }

  /**
   * <p>
   * Open any existing work item. <br>
   * Add any due date in the work item. <br>
   * Click on 'Delete Date' icon from due date field. <br>
   * Remove the added due date from the work item.
   */
  public void removeDueDate() {
    Text txtTextDueDate = this.engine
        .findElementWithDuration(Criteria.isTextField().hasLabel("Due Date: "), this.timeInSecs).getFirstElement();
    txtTextDueDate.click();
    Button delDt = this.engine.findElement(Criteria.isButton().withAriaLabel("Delete date")).getFirstElement();
    delDt.click();
  }

  /**
   * <p>
   * Open any existing work item. <br>
   * Add any tag to the work item. <br>
   * Click on 'Delete Button' present next to the added tag. <br>
   * Remove the tag from the work item.
   *
   * @param tagName name of the tag to remove.
   */
  public void removeTags(final String tagName) {
    String tagXpath = String.format("//span[@title='%s']/following-sibling::span", tagName);
    this.driverCustom.isElementVisible(tagXpath, Duration.ofSeconds(30));
    WebElement ele = this.driverCustom.getWebDriver().findElement(By.xpath(tagXpath));
    this.driverCustom.clickUsingActions(ele);
  }

  /**
   * <p>
   * Open any existing work item. <br>
   * Add any text to the work item description. <br>
   * Click on 'Print Button' to check description values is same as work item description. <br>
   *
   * @param expectedDescription value in description
   * @return true if description same in print page
   */
  public boolean isDescriptionSameInPrintPage(final String expectedDescription) {
    waitForPageLoaded();
    Button btn = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Print"), Duration.ofSeconds(30));
    btn.click();
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.sendKeys(Keys.ESCAPE);
    switchTowindowTab();
    this.driverCustom.isElementVisible(CCMConstants.WORKITEMEDITORPAGE_DESCRIPTION_TEXTFIELD_XPATH, this.timeInSecs, expectedDescription);
    String actualDescription = this.driverCustom.getWebElement(CCMConstants.WORKITEMEDITORPAGE_DESCRIPTION_TEXTFIELD_XPATH,expectedDescription).getText();
    return actualDescription.contains(expectedDescription);
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         this methos used to check weather ownwr is belongs to team area or not after setting filed against and
   *         owned by. (!) symbol will display
   * @param ownedBy owned By
   * @return {@link Boolean}
   */
  public boolean isWarningSymbolDisplayed(final String ownedBy) {
    this.driverCustom.getPresenceOfWebElement("//td[@class='Column rightColumn' or @class='Column leftColumn']//label[text()='" + ownedBy +
        ": ']//../../..//div[@dojoattachpoint='_statusDiv']");
    LOGGER.LOG.info("Warning Symbol displayed to : " + ownedBy);
    return true;
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         this method used after calling {@link CCMWorkItemEditorPage#isWarningSymbolDisplayed(String)}
   * @param ownedBy owned By
   */
  public void clickOnWarningSymbol(final String ownedBy) {
    waitForSecs(Duration.ofSeconds(5));
    WebElement web = this.driverCustom.getWebElement("//td[@class='Column rightColumn' or @class='Column leftColumn']//label[text()='" + ownedBy +
        ": ']//../../..//div[@dojoattachpoint='_statusDiv']");
    this.driverCustom.getActions().moveToElement(web).click().build().perform();
    waitForSecs(Duration.ofSeconds(5));
  }

  /**
   * @author UUM4KOR
   *         <p>
   *         this method used after using {@link CCMWorkItemEditorPage#clickOnWarningSymbol}
   * @param warningMessage warning mesaage is 'Owner does not belong to Team Area'
   * @return warning mesaage
   */
  public String getWarningMessageOnWorningSymbol(final String warningMessage) {
    waitForSecs(Duration.ofSeconds(5));
    String warMessage =
        this.driverCustom.getPresenceOfWebElement("//div[text()='" + warningMessage + "']").getText().toString();
    LOGGER.LOG.info("Warning message on warning symbol is : " + warMessage);
    return warMessage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    String errorMessage =
        "Work item Editor Page not loaded or The Condition used to check whether page is loaded or not is not general.";
    this.driverCustom.anyMatch(By.xpath("//a[@class = 'tab']"), 2, x -> x.getSize().getHeight() > 0, errorMessage);
  }

  /**
   * Hover on Artifact link in Link tab and verifiy Link details tooltip loaded successfully <br>
   *
   * @author VDY1HC
   * @param linkType - Link type of linked artifact
   * @param artifactLinkText - Text of link displayed
   * @param waitTimeInSecs - Max time to wait until loading finished
   */
  public void hoverOnArtifactLinkInLinkTab(final String linkType, final String artifactLinkText,
      final String waitTimeInSecs) {
    RMArtifactsPage page = new RMArtifactsPage(driverCustom);
    page.waitForLoadingMessage();
    WebElement artifactLink = null;
    try {
      artifactLink =
          this.driverCustom.getPresenceOfWebElement("//div[@dojoattachpoint='_headerNode' and text()='" + linkType +
              "']/following-sibling::div[@dojoattachpoint='_valueNode']//a[@class='jazz-ui-ResourceLink' and text()='" +
              artifactLinkText + "']");
    }
    catch(Exception e) {
      artifactLink =
          this.driverCustom.getPresenceOfWebElement("//div[@dojoattachpoint='_headerNode' and text()='" + linkType +
              "']/following-sibling::div[@dojoattachpoint='_valueNode']//a[@class='jazz-ui-ResourceLink' and contains(text(),'" +
              artifactLinkText + "')]");
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    JavascriptExecutor js = (JavascriptExecutor) this.driverCustom.getWebDriver();
    js.executeScript("window.scrollBy(0,300)");
    Actions act = new Actions(this.driverCustom.getWebDriver());
    act.moveToElement(artifactLink).clickAndHold(artifactLink).build().perform();
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getWebDriver().switchTo().activeElement();
  }

  /**
   * After Open Links tab of Work Item details page. <br>
   * This method will select link action in dropdown and perform select artifact to be add to Work item without click "OK" to close add link dialog <br>
   * @author VDY1HC
   * @param additionalParams - store keys:
   *        linkTypeID - Artifact ID of artifact to be added link <br>
   *        linkActions - link type to be add to work item <br>
   *        linksSection - Section to select dropdown dialog (Process links / Links) <br>
   *        dropdownText - Text of dropdown to select link <br>
   *        rmProjectArea - Project area for RM artifact <br>
   *        rmComponent - Component for RM artifact <br>
   *        expectedMsg - Expected message displayed <br>
   *        searchBy - Search by Folder / Module <br>
   *        moduleID - Module ID <br>
   *        viewName - viewName <br>
   */
  public void addLinkToExistingObjectWithOutClose (final Map<String, String> additionalParams) {
    String linkActions = additionalParams.get(CCMConstants.LINKACTIONS);
    String linkActionsStr = linkActions.replace("_", " ");
    String linkSection = additionalParams.get(CCMConstants.LINKS_SECTION);
    String dropdownText = additionalParams.get(CCMConstants.DROPDOWN_TEXT);
    Panel panel = this.engine.findElementWithDuration(Criteria.isPanel().withTitle(linkSection),this.timeInSecs).getFirstElement();
    Dropdown drdAddWILinks = this.engine.findElementWithDuration
        (Criteria.isDropdown().withText(dropdownText).inContainer(panel),this.timeInSecs).getFirstElement();
    drdAddWILinks.scrollToElement();
    drdAddWILinks.selectOptionWithText(linkActionsStr);
    waitForSecs(Duration.ofSeconds(5));
    switch (linkActions) {
      case "Add Implements Requirement":
      case "Add Affects Requirement":
        addLinkAddImplementsRequirement(additionalParams);
        break;
      default:
        throw new WebAutomationException("Link action: " + linkActions + " is NOT implemented for automation.");
    }
  }

  /**
   * This method calls when running {@link CCMWorkItemEditorPage#addLinkToExistingObject(Map)}
   * With linkActions = Add Tracks
   */
  private void addLinkAddTracks (final Map<String, String> additionalParams) {
    String projectArea = additionalParams.get("ccmProjectArea");
    String workItemID = additionalParams.get(CCMConstants.LINKTYPE_ID);
    Dialog dlgAddLink = null;
    
    try {
      dlgAddLink = this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Add Link:"), this.timeInSecs);
    }
    catch (Exception e) {
      dlgAddLink = this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs);
    }
    
    Text txtSearch = this.engine.findFirstElementWithDuration(Criteria.isTextField().withText("Search...").inContainer(dlgAddLink), Duration.ofSeconds(10));
    txtSearch.setText(projectArea);
    
    Dropdown drdProjectArea = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel("Select either the location of the existing artifact you want to link to or the location of the new artifact you want to create"), this.timeInSecs);
    drdProjectArea.selectOptionWithText(projectArea);
    this.driverCustom.isElementInvisible("//div[@class='messageSummary'][text()='Loading...']", Duration.ofSeconds(10));
    
    // Dialog is refresh and need to find again
    dlgAddLink = this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs);
    Button btnOKOnAddLink = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink), Duration.ofSeconds(10));
    btnOKOnAddLink.click();
   
    Dialog dlgSelectPlanItem = this.engine.findFirstElementWithDuration(Criteria.isDialog().withTitle("Select Plan Item"), Duration.ofSeconds(30));
    Text txtSearchWorkItem = this.engine.findFirstElementWithDuration(Criteria.isTextField().hasLabel(CCMConstants.WORKITEMS_SEARCH).inContainer(dlgSelectPlanItem), Duration.ofSeconds(10));
    txtSearchWorkItem.setText(workItemID);
    try {
      Dropdown drdMatchingWorkItem = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.MATCHING_WORKITEMS).inContainer(dlgSelectPlanItem), Duration.ofSeconds(20));
      drdMatchingWorkItem.selectOptionWithPartText(workItemID);
    }
    catch (Exception e) {
      this.driverCustom.switchToDefaultContent();
      this.waitForSecs(2);
      this.driverCustom.switchToFrame(CCMConstants.CCMWORKITEMEDITORPAGE_FRAME_IFRAME_XPATH);
      this.waitForSecs(2);
      if (isNumeric(workItemID)) {
        WebElement opt = this.driverCustom.getPresenceOfWebElement("//option[@value='" + workItemID + "']");
        opt.click();
      }
      else {
        WebElement opt = this.driverCustom.getPresenceOfWebElement("//option[contains(@title,'" + workItemID + "')]");
        opt.click();
      }
    }
    waitForSecs(3);
    Button btnOkOnPlanItem = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("OK"), Duration.ofSeconds(10));
    btnOkOnPlanItem.click();
  }

  /**
   * This method calls when running {@link CCMWorkItemEditorPage#addLinkToExistingObject(Map)}
   * With linkActions = Add Affected by Defect / Add Affects Plan Item
   */
  private void addLinkAddAffectsPlanItem (final Map<String, String> additionalParams) {
    WebElement element;
    element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
    this.driverCustom.clickOnElementByExactTextMatchUsingKeysControls(element, "OK", Keys.TAB);
    this.driverCustom.switchToFrame(CCMConstants.CCMWORKITEMEDITORPAGE_FRAME_IFRAME_XPATH);
    int breakpoint = 100;
    while (breakpoint > 0) {
      element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
      this.driverCustom.iterateOverElementsUsingKeysControls(element, 1, Keys.TAB);
      if (this.driverCustom.isAttributeContains(element, CCMConstants.CLASS, "QueryInput", Duration.ofSeconds(2))) {
        break;
      }
      breakpoint--;
    }
    element.sendKeys(additionalParams.get(CCMConstants.VALUE));
    element.sendKeys(Keys.TAB);
    element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
    this.driverCustom.textTobePresentInTheElement(element, additionalParams.get(CCMConstants.VALUE));
    element.sendKeys(Keys.DOWN);
    element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
    element.sendKeys(Keys.TAB);
    element = this.driverCustom.getWebElement(this.driverCustom.getWebDriver().switchTo().activeElement());
    element.sendKeys(Keys.ENTER);
  }

  /**
   * This method calls when running {@link CCMWorkItemEditorPage#createLinkToExistingObject(Map)}
   * With linkActions = Add Implements Requirement
   */
  private void createLinkAddImplementsRequirement (final Map<String, String> additionalParams) {
    try {
      Dialog dlgAddLink1 =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
          .getFirstElement();
      LOGGER.LOG.info("'" + CCMConstants.ADD_LINK + "' Dialog opened successfully");
      Text txtSearch1 = this.engine.findFirstElementWithDuration(
          Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink1), this.timeInSecs);
      txtSearch1.setText(additionalParams.get(CCMConstants.RM_PROJECT_AREA));
      LOGGER.LOG
      .info("'" + additionalParams.get(CCMConstants.RM_PROJECT_AREA) + "' Entered in search box successfully");
    }
    catch (Exception e) {
      Dialog dlgAddLink1 = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
      LOGGER.LOG.info("'" + " Add Link " + "' Dialog opened successfully");
      Text txtSearch1 = this.engine.findFirstElementWithDuration(
          Criteria.isTextField().withText(CCMConstants.SEARCH).inContainer(dlgAddLink1), this.timeInSecs);
      txtSearch1.setText(additionalParams.get(CCMConstants.RM_PROJECT_AREA));
      LOGGER.LOG
      .info("'" + additionalParams.get(CCMConstants.RM_PROJECT_AREA) + "' Entered in search box successfully");
    }
    Dropdown drdRmProjectArea =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
            this.timeInSecs).getFirstElement();
    drdRmProjectArea.selectOptionWithText(additionalParams.get(CCMConstants.RM_PROJECT_AREA));
    LOGGER.LOG.info("'" + additionalParams.get(CCMConstants.RM_PROJECT_AREA) + "' Selected successfully");
    waitForSecs(Duration.ofSeconds(5));
    try {
      Dialog dlgAddLink1 =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle(CCMConstants.ADD_LINK), this.timeInSecs)
          .getFirstElement();
      RadioButton rMrdoCreateLink =
          this.engine.findElementWithDuration(Criteria.isRadioButton().inContainer(dlgAddLink1), this.timeInSecs)
          .getElementByIndex(2);
      waitForSecs(Duration.ofSeconds(5));
      rMrdoCreateLink.click();
      LOGGER.LOG.info("'Create New' Radio button selected successfully");
      waitForSecs(Duration.ofSeconds(5));
      Button rMbtnOK = this.engine
          .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink1), this.timeInSecs)
          .getFirstElement();
      waitForSecs(3);
      rMbtnOK.click();
      LOGGER.LOG.info("'OK' button clicked from " + CCMConstants.ADD_LINK + "' Dialog successfully");
      waitForSecs(Duration.ofSeconds(10));
    }
    catch (Exception e) {
      Dialog dlgAddLink1 = this.engine
          .findElementWithDuration(Criteria.isDialog().withTitle("Add Link"), this.timeInSecs).getFirstElement();
      RadioButton rMrdoCreateLink =
          this.engine.findElementWithDuration(Criteria.isRadioButton().inContainer(dlgAddLink1), this.timeInSecs)
          .getElementByIndex(2);
      waitForSecs(Duration.ofSeconds(5));
      rMrdoCreateLink.click();
      LOGGER.LOG.info("'Create New' Radio button selected successfully");
      waitForSecs(Duration.ofSeconds(5));
      Button rMbtnOK = this.engine
          .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddLink1), this.timeInSecs)
          .getFirstElement();
      waitForSecs(3);
      rMbtnOK.click();
      LOGGER.LOG.info("'OK' button clicked from " + CCMConstants.ADD_LINK + "' Dialog successfully");
      waitForSecs(Duration.ofSeconds(10));
    }
    Dialog dlgNewRequirement =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Requirement Creation"), this.timeInSecs)
        .getFirstElement();
    LOGGER.LOG.info("'Requirement Creation' Dialog opened successfully");
    this.driverCustom.switchToFrame(RQMConstants.RQMPROJECT_COLLECTIONSELECTION_IFRAME_XPATH);
    waitForSecs(Duration.ofSeconds(10));
    // Select from Component dropdown if existing
    WebElement drdComponent = null;
    try {
      drdComponent = this.driverCustom.getWebElement("//select[@aria-label='Component:']");
      this.driverCustom.switchToDefaultContent();
    }
    catch (Exception ex) {
      LOGGER.LOG.info("Component value does't exist in 'Requirement Selection' dialog.");
    }
    if (drdComponent != null) {
      Dropdown drdProjectArea1 =
          this.engine.findElementWithDuration(Criteria.isDropdown().withAriaLabel("Component:"), this.timeInSecs)
          .getFirstElement();
      drdProjectArea1.selectOptionWithText(additionalParams.get(CCMConstants.RM_COMPONENT_NAME));
      LOGGER.LOG.info(" Selected '" + additionalParams.get(CCMConstants.RM_COMPONENT_NAME) +
          "' Component from 'Requirement Selection' dialog.");
      waitForSecs(Duration.ofSeconds(5));
    }

    if ((additionalParams.get(CCMConstants.REQUIREMENT_NAME) != null) &&
        (!additionalParams.get(CCMConstants.REQUIREMENT_NAME).isEmpty())) {
      waitForSecs(Duration.ofSeconds(10));
      Text txtRequirementName = this.engine
          .findElementWithDuration(
              Criteria.isTextField().hasLabel("Initial content:").inContainer(dlgNewRequirement), this.timeInSecs)
          .getFirstElement();
      waitForSecs(2);
      txtRequirementName.setText(additionalParams.get(CCMConstants.REQUIREMENT_NAME));
      LOGGER.LOG.info(
          "Name " + additionalParams.get(CCMConstants.REQUIREMENT_NAME) + " entered in text box successfully");
      waitForSecs(3);

      // Select Artifact Format
      Dropdown drdArtifacFormat = this.engine
          .findElementWithDuration(
              Criteria.isDropdown().withLabel("Artifact format:").inContainer(dlgNewRequirement), this.timeInSecs)
          .getFirstElement();
      drdArtifacFormat.selectOptionWithText("Text");
      LOGGER.LOG.info("Artifact Format 'Text' selected from drop dawn");

    }
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    this.driverCustom.switchToFrame(CCMConstants.NEW_TESTCASE_FRAME_XPATH);
    waitForSecs(2);
    Button requirementbtnOK =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    requirementbtnOK.click();
    LOGGER.LOG.info("'OK' button clicked from 'Requirement Creation' Dialog successfully");
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.getWebDriver().switchTo().defaultContent();
    waitForSecs(2);
  }

  /**
   * This method calls when running {@link CCMWorkItemEditorPage#addLinkToExistingObject(Map)}
   * With linkActions = Set Parent
   */
  private void addLinkSetParent (final Map<String, String> additionalParams) {
    Dialog dlgSelectParentWorkItem =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Select Work Item"), this.timeInSecs)
        .getFirstElement();
    Text txtSearchParentWI = this.engine.findElementWithDuration(
        Criteria.isTextField().hasLabel(CCMConstants.WORKITEMS_SEARCH).inContainer(dlgSelectParentWorkItem),
        this.timeInSecs).getFirstElement();
    txtSearchParentWI.setText(additionalParams.get(CCMConstants.LINKTYPE_ID));
    this.driverCustom.isElementInvisible("//div[text()='Running search...']", Duration.ofSeconds(5));
    LOGGER.LOG.info(CCMConstants.SELECTED + additionalParams.get(CCMConstants.LINKTYPE_ID) +
        "' in 'Select Work Items' dialog.");
    Dropdown drdMatchingParentWorkItem = this.engine.findElementWithDuration(
        Criteria.isDropdown().withLabel(CCMConstants.MATCHING_WORKITEMS).inContainer(dlgSelectParentWorkItem),
        this.timeInSecs).getFirstElement();
    drdMatchingParentWorkItem.selectOptionWithPartText(additionalParams.get(CCMConstants.LINKTYPE_ID));
    try {
      //Sometime selectOptionWithPartText will failed - so we need to check option again
      // if option is not selected we will select it
      boolean isOptionSelected = this.driverCustom
          .getFirstVisibleWebElement("//option[@value='DYNAMIC_VAlUE']", additionalParams.get(CCMConstants.LINKTYPE_ID))
          .isSelected();
      if(!isOptionSelected) {
        this.driverCustom.select("//div[@class='formSection']/select[@class='QueryResults']", additionalParams.get(CCMConstants.LINKTYPE_ID), SelectTypeEnum.SELECT_BY_VALUE);
      }
    }
    catch (Exception e) {
    }
    LOGGER.LOG.info(additionalParams.get(CCMConstants.LINKTYPE_ID) + " displayed in Matching Work Items.");
    Button btnOk1 =
        this.engine.findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgSelectParentWorkItem),
            this.timeInSecs).getFirstElement();
    btnOk1.click();
    LOGGER.LOG.info("Clicked on 'OK' button in 'Select Work Items' dialog.");
  }

  /**
   * This method calls when running {@link CCMWorkItemEditorPage#addLinkToExistingObjectWithOutClose(Map)}
   * With linkActions = Add Implements Requirement
   */
  private void addLinkAddImplementsRequirement (final Map<String, String> additionalParams) {
    String rmProjectArea = additionalParams.get(CCMConstants.RM_PROJECT_AREA);
    String rmComponent = additionalParams.get(CCMConstants.RM_COMPONENT_NAME);
    String searchBy = additionalParams.get("searchBy");
    String moduleID = additionalParams.get("moduleID");
    String viewName = additionalParams.get("viewName");
    String artifactID = additionalParams.get(CCMConstants.LINKTYPE_ID);
    Text txbSearch = this.engine.findFirstElementWithDuration(
        Criteria.isTextField().withText(CCMConstants.SEARCH), this.timeInSecs);
    txbSearch.setText(rmProjectArea);
    Dropdown drdRmProjectArea =
        this.engine.findElementWithDuration(Criteria.isDropdown().withLabel(CCMConstants.SELECT_PROJECTAREA_LABEL),
            this.timeInSecs).getFirstElement();
    drdRmProjectArea.selectOptionWithText(rmProjectArea);
    RadioButton rbtnAddExisting =
        this.engine.findElementWithDuration(Criteria.isRadioButton(), this.timeInSecs).getElementByIndex(1);
    rbtnAddExisting.click();
    Button rMbtnOK = this.engine.findElementWithDuration(Criteria.isButton().withText("OK"), this.timeInSecs).getFirstElement();
    rMbtnOK.click();
    waitForSecs(Duration.ofSeconds(5));
    this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='iframe']");
    if(this.driverCustom.isElementVisible("//select[@aria-label='Component:' and (@disabled)]/option",this.timeInSecs)) {
      LOGGER.LOG.info("Component selection Drop down is disabled.Used the default component name.");
    }
    else {
    Dropdown drdComponent = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("Component:"), timeInSecs).getFirstElement();
    if (!drdComponent.getDefaultValue().equalsIgnoreCase(rmComponent)) {
      drdComponent.selectOptionWithText(rmComponent);
    }
    }
    WebElement rBtnSearchBy = this.driverCustom.getPresenceOfWebElement("//fieldset//label[contains(text(),'" + searchBy + "')]");
    rBtnSearchBy.click();
    waitForSecs(5);
    switch (searchBy) {
      case "Module":
        WebElement txbSearchModule = this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_MODULE_SEARCHBOX_XPATH);
        txbSearchModule.click();
        txbSearchModule.sendKeys(moduleID);
        waitForSecs(Duration.ofSeconds(5));
        txbSearchModule.sendKeys(Keys.ENTER);
        this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_LOADED_ARTIFACT_XPATH, this.timeInSecs);
        break;
      case "Folder":
        break;
      default:
        throw new WebAutomationException("Search by with value: " + searchBy + " is not configuared for automation.");
    }
    if (viewName!=null) {
      Text txbSearchView = this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Search Views"), this.timeInSecs).getFirstElement();
      txbSearchView.setText(viewName + Keys.ENTER);
      this.driverCustom.switchToFrame("//iframe[@dojoattachpoint='iframe']");
      WebElement nodeView = this.driverCustom.getPresenceOfWebElement("//span[contains(@class,'public-tag-name list-view') and text()='" + viewName + "']");
      nodeView.click();
      this.driverCustom.isElementInvisible(CCMConstants.CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_LOADED_ARTIFACT_XPATH, this.timeInSecs);
      this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_LOADED_ARTIFACT_XPATH, this.timeInSecs);
    }
    if (artifactID!=null) {
      Text txbSearchArtifact = this.engine.findElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID"), this.timeInSecs).getFirstElement();
      txbSearchArtifact.setText(artifactID + Keys.ENTER);
      this.driverCustom.isElementPresent(CCMConstants.CCMWORKITEMEDITORPAGE_ADDLINKDIALOG_LOADED_ARTIFACT_XPATH, this.timeInSecs);
      Link lnkArtifactID = this.engine.findElementWithDuration(Criteria.isLink().withText(artifactID + ":"), this.timeInSecs).getFirstElement();
      lnkArtifactID.click();
    }
  }

  /**
   * Joining all the value in input map into one completed string <br>
   * Use for generate message with static text and dynamic text
   * @param additionalParams - list of string stored as value (keys can be random)
   *        &nbsp; - use as space
   * @return one joined string
   */
  public String generateExpectedMessageDisplayed (final Map<String, String> additionalParams) {
    Collection<String> collected = additionalParams.values();
    return collected.stream()
        .map(object -> object.replace("&nbsp;", " "))
        .collect(Collectors.joining("") );
  }

  /**
   * <p>
   * Open any existing work item. <br>
   * Add any text to the work item description. <br>
   * Click on 'Print Button' to check summary values is same as work item summary. <br>
   *
   * @param expectedSummary summary of
   * @return true if description same in print page
   */
  public boolean isSummarySameInPrintPage(final String expectedSummary) {
    Button btn = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Print"), Duration.ofSeconds(30));
    btn.click();
    waitForSecs(Duration.ofSeconds(10));
    Actions action = new Actions(this.driverCustom.getWebDriver());
    action.sendKeys(Keys.ESCAPE);
    switchTowindowTab();
    String xpathLinkTextInPrintPage = String.format("//th[text()='Links']/parent::tr/following-sibling::tr//a[contains(text(),'%s')]", expectedSummary);
    
    return this.driverCustom.isElementVisible(xpathLinkTextInPrintPage, Duration.ofSeconds(20));
  }

  /**
   * Delete Link from Links Section by ID. <br>
   * @author KYY1HC
   * 
   * @param workItemId work item id want to delete
   * @return true if the link with Id is removed successfully, otherwise returned false.
   */
  public boolean removeLinksFromLinksSectionById(final String workItemId) {
    String xpathLink = String.format("//span[@aria-label='Process Links']/ancestor::div[@dojoattachpoint='innerBorderDIV']/descendant::a[contains(text(),'%s')]", workItemId);
    if (this.driverCustom.isElementVisible(xpathLink, Duration.ofSeconds(30))) {
      WebElement link = this.driverCustom.getPresenceOfWebElement(xpathLink);
      this.driverCustom.getActions().moveToElement(link).build().perform();
      String xpathRemoveBtn = "./ancestor::div[@class='ValueDiv']/span[@title='Remove']";
        WebElement btnRemove = link.findElement(By.xpath(xpathRemoveBtn));
        btnRemove.click();
        LOGGER.LOG.info("Removed '" + workItemId + "' from the 'Links' section of the work item.");
    }

    LOGGER.LOG.info("Not found '" + workItemId + "' to delete from the 'Links' section of the work item.");
    return this.driverCustom.isElementInvisible(xpathLink, Duration.ofSeconds(30));
  }
  
  /**
   * Check if text is Numberic
   * @author VDY1HC
   * @param str - input string
   * @return true - if text is number
   */
  private boolean isNumeric(String str) { 
    try {  
      Double.parseDouble(str);  
      return true;
    } catch(NumberFormatException e){  
      return false;  
    }  
  }
  /**
   * Check work item can be saved successfully with the newly created dropdown value (like Iteration, Filed Against,etc) 
   * @param params contains
   *        expectedDropdownValue - name of newly created dropdown value
   *        dropdownName - name of field (ex: Planned For, Filed Against, etc)
   * @return true if newly created dropdownValue is available in 'Planned For, Filed Against, Priority, etc' dropdown and false if vice versa.
   * @author NCY3HC
   */
  public boolean verifyWorkItemIsSavedWithNewDropdownValue (final Map<String,String> params) {
    String expectedDropdownValue = params.get("DROPDOWN_VALUE");
    String dropdownName = params.get("DROPDOWN_NAME");
    waitForSecs(Duration.ofSeconds(5));
    String actualDropdownValue = this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDIT_DROPDOWN_VALUE_XPATH, dropdownName).getText();
    if(actualDropdownValue.equalsIgnoreCase(expectedDropdownValue)) {
      return true;
    }
    return false;
  }
  
  /**
   * <p>
   * select project {@link CCMProjectAreaDashboardPage #selectProjectArea(String)},select
   * {@link CCMProjectAreaDashboardPage #openMenu(String)} {@link CCMProjectAreaDashboardPage #openSubMenu(String)}
   * <p>
   * Work item has drop downs to set value for  "Planned for" 
   * @param attributeValue - planned for value
   * @return plan value
   */
  
  public String setPlannedFor(final String attributeValue) {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEM_PLANNED_FOR_VALUE_XPATH).click();
    this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEM_SEARCHBOX_PLANNED_FOR_XPATH, timeInSecs);
    WebElement searchBox = this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEM_SEARCHBOX_PLANNED_FOR_XPATH);
    searchBox.sendKeys(attributeValue);
    waitForSecs(3);
    searchBox.sendKeys(Keys.ENTER);
    return this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEM_PLANNED_FOR_VALUE_XPATH).getText();
  }
  
  /**
   * Open any 'WorkItem'.<br>
   * Then call this method to check text in 'Description' is what style.<br>
   * @author LTU7HC
   * @param font "Bold", "Italic", "Underline", "Strikethrough"
   * @return boolean true|false (true if button "Bold", "Italic", "Underline", "Strikethrough" is selected)
   */
  
  public boolean verifyTextInDescriptionFont(final String font) {
    waitForPageLoaded();
    Text txtTextFiled = null;
    boolean check = false;
    try {
      txtTextFiled = this.engine
          .findElementWithDuration(Criteria.isTextField().hasLabel("Description").isMultiLine(), this.timeInSecs)
          .getFirstElement();
      txtTextFiled.click();
      txtTextFiled.getWebElement().sendKeys(Keys.chord(Keys.CONTROL, "a"));
      String buttonXpath = String.format("//span[@class='cke_toolgroup']/a[@title='%s' and contains(@class,'cke_button_on')]",  font.substring(0, 1).toUpperCase() + font.substring(1));
      String buttonAttribute = this.driverCustom.getAttribute(buttonXpath, "class");
      // If button is selected then class contains 'cke_button_on'
      assertTrue(String.format("Verify that text in Description is '%s' with Font: '%s'",
          txtTextFiled.getText().trim(), font), buttonAttribute.contains("cke_button_on"));
      check = true;
    }
    catch (Exception e) {
      LOGGER.LOG.info("ERROR: " +e);
    }
    return check;
  }
  
  /**
   * After click on button "Move or copy this workitem to another project area"
   * Then "Move or copy this workitem" dialog displayed
   * Select Project area > Select Copy or Move option
   * @author NCY3HC
   * @param projectName : target prject which is selected to ve/copy work item to.
   * @param option : Mover/ Copy
   * @param copy_attachments : checkbox will be display after selected 'Copy' option
   */

  public void moveOrCopyWorkItemToAnotherProject(final String projectName, final String option,
      final String copy_attachments) {
    //Click on element to focus
    this.driverCustom.getWebElement(CCMConstants.CCMWORKITEM_MOVE_OR_COPY_WORKITEM_POPUP).click();
    //Verify element still visible
    boolean isPopupDisplayed = this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEM_MOVE_OR_COPY_WORKITEM_POPUP, timeInSecs); 
    if (isPopupDisplayed) {
      //Select item in dropdown box
      Dialog dlg =
          this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Move or Copy this Work Item"), timeInSecs)
              .getFirstElement();
      Dropdown projectArea = this.engine
          .findElementWithDuration(Criteria.isDropdown().withLabel("Project Area:").inContainer(dlg), timeInSecs)
          .getFirstElement();
      projectArea.click();
      List<WebElement> optionList =
          this.webElement.findElements(By.xpath(CCMConstants.CCMWORKITEM_PROJECTARE_MOVE_OR_COPY_WORKITEM_POPUP));
      for (WebElement opt : optionList) {
        if (opt.getText().equals(projectName)) {
          opt.click();
        }
      }
    //Select Options to "Move" or "Copy"
      switch (option) {
        case "Move":
          this.driverCustom.getWebElement(CCMConstants.CCMWORKITEM_MOVE_BUTTON).click();
          break;
        case "Copy":
          this.driverCustom.getWebElement(CCMConstants.CCMWORKITEM_COPY_BUTTON).click();
          if (copy_attachments.equalsIgnoreCase("true")) {
            this.driverCustom.getWebElement(CCMConstants.CCMWORKITEM_COPY_ATTACHMENTS_CHECKBOX).click();
        }
            break;
      default:
        LOGGER.LOG.info("Option not found");
          }
      }
  }
  
  /**
   * Get header validation message after move/copy work item using method {@link CCMWorkItemEditorPage#moveOrCopyWorkItemToAnotherProject(String, String, String)}
   * @return validation message value
   * @author NCY3HC
   */
  public String getHeaderValidationMessage () {
    RMArtifactsPage page = new RMArtifactsPage(driverCustom);
    page.waitForLoadingMessage();
    WebElement message = this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEM_HEADER_MESSAGE);
    return message.getText();
    
  }

  /**
   * Open an existing Work item, open Links tab inside work item Verify Links/Process Links section
   * 
   * @author NCY3HC
   * @param params : linkSection - Links/Process 
   *                Links linkType - Parent/Implemented By/... 
   *                linkLabel - link text
   * @return true if the link is visible in work item and vice versa.
   */

  public boolean isLinksDisplayedInsideWorkItem(final Map<String, String> params) {
    String linkSection = params.get("LINK_SECTION");
    String linkType = params.get("LINK_TYPE");
    String linkLabel = params.get("LINK_LABEL");
    String[] linkDisplay = { linkSection, linkType, linkLabel };
    this.driverCustom.getFirstVisibleWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_LINKS_PROCESSLINKSORLINKS_XPATH,
        linkSection);
    boolean check = this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_LINKS_LINKSLABEL_XPATH,
        timeInSecs, linkDisplay);
    if (check) {
      return true;
    }
    return false;

  }

  /**
   * Open an existing Work item, open Links tab inside work item Verify Links/Process Links section
   * 
   * @author NCY3HC
   * @param filesName : list of attachments inside work item
   * @return true if attachments are visible in work item and vice versa.
   */

  public boolean isFilesDisplayedInsideWorkItem(final String filesName) {
    List<String> expectedFileList = Arrays.asList(filesName.split(";"));
    for(String ele : expectedFileList) {
      boolean check = this.driverCustom.isElementVisible(CCMConstants.CCMWORKITEMEDITPAGE_ATTACHMENT_XPATH, timeInSecs, ele);
      if(!check) {
        return false;
      }
    }
    return true;

  }

  /**
   * Open an existing Work item, open Links tab inside work item Verify Links/Process Links section
   * 
   * @author NCY3HC
   * @param comment : comment inside workitem
   * @return true if comment is visible in work item and vice versa.
   */

  public boolean isCommentDisplayedInsideWorkItem(final String comment) {
//    boolean checkComment = this.driverCustom
//        .isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_COMMENT_INSIDE_WORKITEM_XPATH, timeInSecs, comment);
    if (this.driverCustom
        .isElementVisible(CCMConstants.CCMWORKITEMEDITORPAGE_COMMENT_INSIDE_WORKITEM_XPATH, timeInSecs, comment)) {
      WebElement wiComment =
          this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITORPAGE_COMMENT_INSIDE_WORKITEM_XPATH, comment);
      JavascriptExecutor je = (JavascriptExecutor) this.driverCustom.getWebDriver();
      je.executeScript("arguments[0].scrollIntoView(true);", wiComment);
      return true;
    }
    return false;

  }

  /**
   * Get value in span tag value by label name
   * @author NCY3HC
   * @param label - field name
   * @return return the value of span tag
   */
  
  public String getTagValueByLabel (String label) {
    String fieldValue = driverCustom.getPresenceOfWebElement(CCMConstants.CCMWORKITEMEDITPAGE_TAG_VALUE_BY_LABEL_XPATH,label).getText();
    return fieldValue;
    
  }
}