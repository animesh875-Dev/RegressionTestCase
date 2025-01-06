/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Checkbox;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Cell;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * Most basic page class of RQMBuildsPage.
 */
public class RQMBuildsPage extends AbstractRQMPage {

  private static final String INVALID_PARAMETER_OR_NULL_OR_EMPTY = "Invalid parameter or null or empty.";

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMBuildsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzTextFieldFinder(), new JazzDropdownFinder(), new TextFieldFinder(),
        new JazzRowFinder(driverCustom.getWebDriver()), new RowCellFinder(), new JazzDialogFinder(),
        new JazzButtonFinder());
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build record. <br>
   * Set the record name in 'Enter New Build Record Name' text box.
   *
   * @param buildRecordName to be entered in 'Enter New Build Record Name' text box.
   */
  public void setRecordNameInBuildRecord(final String buildRecordName) {
    waitForPageLoaded();
    if ((buildRecordName != null) && (!buildRecordName.isEmpty())) {
      String buildRecordNames = buildRecordName + DateUtil.getCurrentDateAndTime();
      Text txtName = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Enter New Build Record Name"))
          .getFirstElement();
      txtName.setText(buildRecordNames);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * <p>
   * After providing a build record name using {@link RQMBuildsPage#setRecordNameInBuildRecord(String)} build record
   * name use to return.
   *
   * @return return build record name.
   */
  public String getRecordNameInBuildRecord() {
    return this.driverCustom.getWebElement(RQMConstants.RQM_BUILD_RECORD_NAME_TEXTBOX_XPATH).getAttribute("value");
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build record. <br>
   * Select the Status of the record.
   *
   * @param status for the build records like 'Warning','Error','OK','In Progress' etc.
   */
  public void selectStatus(final String status) {
    Dropdown drdStatus = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Status:"), this.timeInSecs).getFirstElement();
    drdStatus.selectOptionWithText(status);
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build record. <br>
   * Select the State of the record.
   *
   * @param state of the build records like 'Not Started','Incomplete','Canceled','Complete' etc...
   */
  public void selectState(final String state) {
    Dropdown drdState = this.engine.findElementWithDuration(Criteria.isDropdown().withLabel("State:"), this.timeInSecs)
        .getFirstElement();
    drdState.selectOptionWithText(state);
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build record. <br>
   * Set the Description of the record.
   *
   * @param buildRecordDescription to be added in Description text field.
   */
  public void setDescription(final String buildRecordDescription) {
    Text txtDescription = this.engine
        .findElementWithDuration(Criteria.isTextField().isMultiLine().hasLabel("Description:"), this.timeInSecs)
        .getFirstElement();
    txtDescription.setText(buildRecordDescription);
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build record. <br>
   * After adding status, it will verify the status of the build record.
   *
   * @param status of the build record.
   * @return true if added status is available for the build record.
   */
  public boolean isStatusAdded(final String status) {
    Dropdown drpStatusVerify = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("Status:"), this.timeInSecs).getFirstElement();
    return drpStatusVerify.getDefaultValue().equals(status);
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build record. <br>
   * After adding state, it will verify the state of the build record.
   *
   * @param state of the build record.
   * @return true if added state is available for the build record.
   */
  public boolean isStateAdded(final String state) {
    Dropdown drpStateVerify = this.engine
        .findElementWithDuration(Criteria.isDropdown().withLabel("State:"), this.timeInSecs).getFirstElement();
    return drpStateVerify.getDefaultValue().equals(state);
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Browse the build records. <br>
   * Select the searched build record.
   *
   * @param buildRecordName2 name of the build record.
   */
  public void selectBuildRecord(final String buildRecordName2) {
    Row rowBuildRecord = this.engine
        .findElementWithDuration(Criteria.isRow().withText(buildRecordName2), this.timeInSecs).getFirstElement();
    Cell cllCheckbox =
        this.engine.findElement(Criteria.isCell().inContainer(rowBuildRecord).withIndex(1)).getFirstElement();
    Checkbox cbxBuildRecord = this.engine.findElement(Criteria.isCheckbox().inContainer(cllCheckbox)).getFirstElement();
    cbxBuildRecord.click();
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Browse the build records. <br>
   * After selecting the build record click on delete build record button.
   */
  public void clickOnDeleteBuildRecordButton() {
    Button btnDelete =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Delete Build Records"), this.timeInSecs)
            .getFirstElement();
    btnDelete.click();
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Browse the build records. <br>
   * Delete the selected build record.
   */
  public void deleteBuildRecord() {
    Dialog dlgConfirmation = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Confirmation"), this.timeInSecs).getFirstElement();
    Button btnDeleteConfirmation =
        this.engine.findElement(Criteria.isButton().withText("Delete").inContainer(dlgConfirmation)).getFirstElement();
    btnDeleteConfirmation.click();
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Browse the build records. <br>
   * Delete the selected build record.<br>
   * After deleting the build record it will verify build record is deleted.
   *
   * @param msg will display after deleting build record and build definition.
   * @return true if the build record is deleted.
   */
  public boolean isSuccessMessageDisplayed(final String msg) {
    waitForPageLoaded();
    Label lblSuccessMsg = null;
    try {
      lblSuccessMsg =
          this.engine.findElementWithDuration(Criteria.isLabel().containText(msg), this.timeInSecs).getFirstElement();
      LOGGER.LOG.info(msg + " - message is showning properly");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return lblSuccessMsg != null;
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build definition. <br>
   * Set the definition name in 'Enter New Build Definition Name' text box.
   *
   * @param buildDefinitionName to be entered in 'Enter New Build Definition Name' text box.
   */
  public void setBuildDefinitionName(final String buildDefinitionName) {
    waitForPageLoaded();
    if ((buildDefinitionName != null) && (!buildDefinitionName.isEmpty())) {
      String buildDefinitionNames = buildDefinitionName + DateUtil.getCurrentDateAndTime();
      Text txtName = this.engine.findElement(Criteria.isTextField().withPlaceHolder("Enter New Build Definition Name"))
          .getFirstElement();
      txtName.setText(buildDefinitionNames);
      return;
    }
    throw new IllegalArgumentException(INVALID_PARAMETER_OR_NULL_OR_EMPTY);
  }

  /**
   * <p>
   * After providing a build definition name using {@link RQMBuildsPage#setBuildDefinitionName(String)} build definition
   * name use to return.
   *
   * @return return build definition name.
   */
  public String getBuildDefinitionName() {
    return this.driverCustom.getWebElement(RQMConstants.RQM_BUILD_DEFINITION_NAME_TEXTBOX_XPATH).getAttribute("value");
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build definition. <br>
   * Click on 'Add Build Records' button.
   */
  public void clickOnAddBuildRecord() {
    Button btnAddBuildRecord =
        this.engine.findElementWithDuration(Criteria.isButton().withToolTip("Add Build Records"), this.timeInSecs)
            .getFirstElement();
    btnAddBuildRecord.click();
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Create a new build definition. <br>
   * Click on 'Add Build Records' button, 'Add Build Records' pop up will display. <br>
   * Add Build Records in Build Definition.
   *
   * @param buildRecord to be added in build definition.
   */
  public void addBuildRecord(final String buildRecord) {
    Dialog dlgAddBuildRecord = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle("Add Build Records"), this.timeInSecs).getFirstElement();
    Text txtSearchField = this.engine.findElementWithDuration(
        Criteria.isTextField().withPlaceHolder("Type filter text and press Enter").inContainer(dlgAddBuildRecord),
        this.timeInSecs).getFirstElement();
    txtSearchField.setText(buildRecord);
    waitForSecs(2);
    Row rowBuildRecord = this.engine.findElement(Criteria.isRow().withText(buildRecord).inContainer(dlgAddBuildRecord))
        .getFirstElement();
    Cell cllCheckbox =
        this.engine.findElement(Criteria.isCell().inContainer(rowBuildRecord).withIndex(1)).getFirstElement();
    Checkbox cbxBuildRecord = this.engine.findElement(Criteria.isCheckbox().inContainer(cllCheckbox)).getFirstElement();
    cbxBuildRecord.click();
    Button btnOK = this.engine
        .findElementWithDuration(Criteria.isButton().withText("OK").inContainer(dlgAddBuildRecord), this.timeInSecs)
        .getFirstElement();
    btnOK.click();
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Add Build Records in Build Definition. <br>
   * Verify build record is added in build definition.
   *
   * @param buildRecord is added in build definition.
   * @return true if added record is displayed.
   */
  public boolean isBuildRecordDisplayed(final String buildRecord) {
    Row rowBuildRecordVerify = null;
    try {
      rowBuildRecordVerify = this.engine
          .findElementWithDuration(Criteria.isRow().withText(buildRecord), this.timeInSecs).getFirstElement();
      return rowBuildRecordVerify != null;
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return rowBuildRecordVerify != null;
  }

  /**
   * <p>
   * Open 'Builds' menu.<br>
   * Browse the build definitions. <br>
   * After selecting the build record click on delete build definition button.
   */
  public void clickOnDeleteBuildDefinitionButton() {
    Button btnDelete = this.engine
        .findElementWithDuration(Criteria.isButton().withToolTip("Delete Build Definitions"), this.timeInSecs)
        .getFirstElement();
    btnDelete.click();
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH, RQMConstants.BUILDS);

  }

}
