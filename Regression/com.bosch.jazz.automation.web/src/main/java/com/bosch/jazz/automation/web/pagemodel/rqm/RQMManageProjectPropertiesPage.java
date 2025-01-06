/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Column;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.container.Tab;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.ColumnFinder;
import com.bosch.psec.web.test.finder.container.cell.ColumnCellFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LabelFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.container.column.JazzColumnFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.container.treeNode.JazzTreeNodeFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextEditorFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * This Page contains RQM Manage Project Properties Page related data.
 */
public class RQMManageProjectPropertiesPage extends AbstractRQMPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMManageProjectPropertiesPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new JazzTextFieldFinder(), new JazzTextEditorFinder(),
        new JazzDropdownFinder(), new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(),
        new ColumnFinder(), new LinkFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(), new CheckboxFinder(),
        new DropdownFinder(), new JazzColumnFinder(), new ColumnCellFinder(), new LabelFinder(), new JazzLabelFinder(),
        new JazzTreeNodeFinder());
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * Click on the tabs present in 'Manage Project Properties' page.
   *
   * @param tab name of the tab which present in Manaage Project Propeties page like Custom Attributes,Properties etc..
   */
  public void clickOnPropertiesTab(final String tab) {
    waitForPageLoaded();
    Tab tabArtifactCategories =
        this.engine.findElementWithDuration(Criteria.isTab().withText(tab), this.timeInSecs).getFirstElement();
    tabArtifactCategories.click();
    LOGGER.LOG.info(tab + " tab is clicked successfully.");

  }

  /**
   * clicks on RQM Manage project properties page labels.
   *
   * @param section name of options available under RQM Manage project properties.
   */
  public void clickOnPropertiesSection(final String section) {
    waitForPageLoaded();
    Label lnkPropertiesSection =
        this.engine.findElementWithDuration(Criteria.isLabel().withText(section), this.timeInSecs).getFirstElement();
    lnkPropertiesSection.click();
    LOGGER.LOG.info(section + " section is selected successfully");
  }

  /**
   * Set name of a category under keyword categories section.
   *
   * @param keyWordCategoryName name of the category.
   * @return New Keyword category name.
   */
  public String createRootCategoryName(final String keyWordCategoryName) {
    waitForPageLoaded();
    Dialog dlgCreateRootCategory =
        this.engine.findElementWithDuration(Criteria.isDialog().withTitle("Create root category"), this.timeInSecs)
            .getFirstElement();
    Column clmName =
        this.engine.findElementWithDuration(Criteria.isColumn().withTitle("Name").inContainer(dlgCreateRootCategory),
            this.timeInSecs).getFirstElement();
    Text txtName =
        this.engine.findElementWithDuration(Criteria.isTextField().isMultiLine().inContainer(clmName), this.timeInSecs)
            .getFirstElement();
    String newkeyWordCategoryName = keyWordCategoryName + DateUtil.getCurrentDateAndTime();
    txtName.setText(newkeyWordCategoryName);
    LOGGER.LOG.info(newkeyWordCategoryName + " category name is entered in Name text field.");
    return newkeyWordCategoryName;
  }

  /**
   * Set name of a value under keyword categories section.
   *
   * @param keywordValueName name of the value.
   * @return New Keyword value name.
   */
  public String setCreateValueName(final String keywordValueName) {
    waitForPageLoaded();
    Dialog dlgCreateValue = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Create Value"), this.timeInSecs).getFirstElement();
    Column clmValueName = this.engine
        .findElementWithDuration(Criteria.isColumn().withTitle("Name").inContainer(dlgCreateValue), this.timeInSecs)
        .getFirstElement();
    Text txtName1 = this.engine
        .findElementWithDuration(Criteria.isTextField().isMultiLine().inContainer(clmValueName), this.timeInSecs)
        .getFirstElement();
    String newkeywordValueName = keywordValueName + DateUtil.getCurrentDateAndTime();
    txtName1.setText(newkeywordValueName);
    LOGGER.LOG.info(newkeywordValueName + " value name is entered in Name text field.");
    this.driverCustom.switchToDefaultContent();
    return newkeywordValueName;
  }

  /**
   * Selects the option from the Action menu drop down.
   *
   * @param option drop down value.
   * @param categoryName name of the category
   */
  public void selectOptionFromActionsMenu(final String option, final String categoryName) {
    waitForPageLoaded();
    WebElement ele = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_DELTESTPLAN_VERIFYMSG_XPATH, categoryName);
    ele.click();
    new Actions(this.driverCustom.getWebDriver()).moveToElement(ele).build().perform();
    WebElement action = this.driverCustom
        .getWebElement("//div[text()='" + categoryName + "']/../..//span[text()='Actions']/preceding-sibling::img");
    new Actions(this.driverCustom.getWebDriver()).moveToElement(action).click().build().perform();
    Cell cel =
        this.engine.findElementWithDuration(Criteria.isCell().withText(option), this.timeInSecs).getFirstElement();
    cel.click();
    LOGGER.LOG.info(option + " is selected successfully from category -" + categoryName);
    waitForSecs(3);
  }

  /**
   * <p>
   * Select checkbox values from the Add Values dialog box.
   *
   * @param value checkbox value.
   */
  public void addValues(final String value) {
    waitForPageLoaded();
    Checkbox cbxFunctional =
        this.engine.findElementWithDuration(Criteria.isCheckbox().withText(value), this.timeInSecs).getFirstElement();
    cbxFunctional.click();
    LOGGER.LOG.info(value + " checkbox is selected successfully.");
  }

  /**
   * <p>
   * Select check box values from the Add Values dialog box.
   *
   * @param dialog name of the dialog.
   * @param value check box value.
   */
  public void addDialogValues(final String dialog, final String value) {
    Dialog dlg = this.engine.findElementWithinDuration(Criteria.isDialog().withTitle(dialog), Duration.ofSeconds(60)).getFirstElement();
    Checkbox cbxFunctional = this.engine
        .findElementWithinDuration(Criteria.isCheckbox().withLabel(value).inContainer(dlg), Duration.ofSeconds(60)).getFirstElement();
    cbxFunctional.click();
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * Do some changes, click on 'Save' button.<br>
   * Verify Successful message.
   *
   * @return true if properties saved.
   */
  public boolean isPropertiesSaved() {
    waitForPageLoaded();
    this.driverCustom.isElementVisible(RQMConstants.RMARTIFACTSPAGE_SELECT_ARTIFACT_FORMAT_VALUE_XPATH, this.timeInSecs,
        "Saved Successfully");
    Label lblSuccessMsg =
        this.engine.findElementWithDuration(Criteria.isLabel().containText("Saved Successfully"), this.timeInSecs)
            .getFirstElement();
    LOGGER.LOG.info("Saved clicked and the page saved successfully");
    return lblSuccessMsg != null;
  }

  /**
   * <p>
   * Save a new Category and validate the created category is visible or not.
   *
   * @param categories name of the category.
   * @return true if categories visible.
   */
  public boolean isKeywordCategoriesVisible(final String categories) {
    waitForPageLoaded();
    Label lblNewKeywordCate =
        this.engine.findElementWithDuration(Criteria.isLabel().withText(categories), this.timeInSecs).getFirstElement();
    LOGGER.LOG.info(categories + "is created successfully");
    return lblNewKeywordCate != null;
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * Select the attribute as per the attribute name.
   *
   * @param customAttributeName name of the attribute.
   */
  public void selectAttribute(final String customAttributeName) {
    waitForPageLoaded();
    Row rowCustomAttribute = this.engine
        .findElementWithDuration(Criteria.isRow().withText(customAttributeName), this.timeInSecs).getFirstElement();
    rowCustomAttribute.click();
    LOGGER.LOG.info(customAttributeName + "is selected from the row");
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * Set the attribute name in 'Custom Attribute' section.
   *
   * @param textAttributeName name of the attribute.
   */
  public void setAttributeName(final String textAttributeName) {
    waitForPageLoaded();
    List<Row> rowCustomAttributeList =
        this.engine.findElementWithDuration(Criteria.isRow().withText(RQMConstants.CUSTOM_ATTRIBUTE), this.timeInSecs)
            .getElementList();
    Row rowCustomAttribute = rowCustomAttributeList.get(rowCustomAttributeList.size() - 1);
    Cell cellAttributeName = this.engine
        .findElementWithDuration(Criteria.isCell().inContainer(rowCustomAttribute).withIndex(2), this.timeInSecs)
        .getFirstElement();
    cellAttributeName.click();
    Text txtAttributeName =
        this.engine.findElement(Criteria.isTextField().inContainer(cellAttributeName)).getFirstElement();
    String newtextAttributeName = textAttributeName + DateUtil.getCurrentDateAndTime();
    LOGGER.LOG.info(newtextAttributeName + " attribute name is entered in Name text field.");
    txtAttributeName.setText(newtextAttributeName);
  }

  /**
   * <p>
   * After providing a attribute name using {@link RQMManageProjectPropertiesPage#setAttributeName(String)} attribute
   * name use to return.
   *
   * @return return attribute name.
   */
  public String getAttributeName() {
    waitForPageLoaded();
    return this.driverCustom
        .getWebElement("(//tr[contains(@id,'AttributeEditor')]/td[@dojoattachpoint='_nameNode']/input)[last()]")
        .getAttribute("value");
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * Set the attribute type in 'Custom Attribute' section.
   *
   * @param attributeType type of the attribute.
   */
  public void selectType(final String attributeType) {
    waitForPageLoaded();
    List<Row> rowCustomAttributeList =
        this.engine.findElementWithDuration(Criteria.isRow().withText(RQMConstants.CUSTOM_ATTRIBUTE), this.timeInSecs)
            .getElementList();
    Row rowCustomAttribute = rowCustomAttributeList.get(rowCustomAttributeList.size() - 1);
    Cell cellAttributeType = this.engine
        .findElementWithDuration(Criteria.isCell().inContainer(rowCustomAttribute).withIndex(3), this.timeInSecs)
        .getFirstElement();
    Dropdown drdType = this.engine.findElement(Criteria.isDropdown().inContainer(cellAttributeType)).getFirstElement();
    drdType.selectOptionWithText(attributeType);
    LOGGER.LOG.info(attributeType + " is selected successfully from Type dropdown.");
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * After adding attribute verify attribute is added.
   *
   * @param customAttributeName name of the attribute.
   * @return true if attribute added.
   */
  public boolean isAttributeAdded(final String customAttributeName) {
    waitForPageLoaded();
    Label lblNewCustomAttribute = null;
    try {
      lblNewCustomAttribute = this.engine
          .findElementWithDuration(Criteria.isLabel().withText(customAttributeName), this.timeInSecs).getFirstElement();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lblNewCustomAttribute != null;
  }

  /**
   *
   */
  public void clickOnCreateButton() {
    waitForPageLoaded();
    Button spanBtn = this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Create..."), this.timeInSecs)
        .getFirstElement();
    spanBtn.click();
    LOGGER.LOG.info("Clicked on 'Create...' button.");
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * Switch to any of the attribute section.<br>
   * Click on 'Add Attribute' icon. <br>
   * Enable Required Check Box in Custom Atribute section.
   */
  public void enableRequiredCheckBox() {
    List<Row> rowCustomAttributeList = this.engine
        .findElementWithDuration(Criteria.isRow().withText("Custom attribute"), this.timeInSecs).getElementList();
    Row rowCustomAttribute = rowCustomAttributeList.get(rowCustomAttributeList.size() - 1);
    Cell cellRequired = this.engine
        .findElementWithDuration(Criteria.isCell().inContainer(rowCustomAttribute).withIndex(4), this.timeInSecs)
        .getFirstElement();
    Checkbox chxRequired = this.engine.findElement(Criteria.isCheckbox().inContainer(cellRequired)).getFirstElement();
    chxRequired.click();
    LOGGER.LOG.info("Required checkbox is selected successfully.");
  }

  /**
   * <p>
   * Click on 'Administration' icon and choose option 'Manage Project Properties'. <br>
   * Switch to any of the attribute section.<br>
   * Click on 'Add Attribute' icon. <br>
   * Input the External URI for the added custom attribute.
   *
   * @param externalURI URI to be added for the attribute.
   */
  public void setExternalURI(final String externalURI) {
    List<Row> rowCustomAttributeList = this.engine
        .findElementWithDuration(Criteria.isRow().withText("Custom attribute"), this.timeInSecs).getElementList();
    Row rowCustomAttribute = rowCustomAttributeList.get(rowCustomAttributeList.size() - 1);
    Cell cellExternalURI = this.engine
        .findElementWithDuration(Criteria.isCell().inContainer(rowCustomAttribute).withIndex(6), this.timeInSecs)
        .getFirstElement();
    Text txtExternalURI =
        this.engine.findElement(Criteria.isTextField().inContainer(cellExternalURI)).getFirstElement();
    txtExternalURI.setText(externalURI);
    LOGGER.LOG.info(externalURI + " url is set in URI text field successfully.");
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH,
        "Manage Project Properties");
  }

}
