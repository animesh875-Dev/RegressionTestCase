/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.text.Label;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.CheckboxFinder;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.ColumnFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LabelFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;
import com.bosch.psec.web.test.finder.text.TextFieldFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.container.row.JazzRowFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.radiobutton.JazzRadioButtonFinder;
import finder.text.JazzTextEditorFinder;
import finder.text.label.JazzLabelFinder;
import finder.text.textField.JazzTextFieldFinder;

/**
 * This Page contains RQM Reports Page related data.
 */
public class RQMReportsPage extends AbstractRQMPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMReportsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new JazzTextFieldFinder(), new JazzTextEditorFinder(),
        new JazzDropdownFinder(), new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(),
        new ColumnFinder(), new LinkFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(), new CheckboxFinder(),
        new DropdownFinder(), new LabelFinder(), new JazzLabelFinder());
  }

  /**
   * <p>
   * Navigate to RQM My report page. <br>
   * Search and open a report. <br>
   * Verify the report contains the dialogs.
   *
   * @param dialogName name of the Dialog present in the report.
   * @return true if dialog exist.
   */
  public boolean verifyDialogueBox(final String dialogName) {
    waitForPageLoaded();
    Dialog dlgIteration = this.engine
        .findElementWithDuration(Criteria.isDialog().withTitle(dialogName), this.timeInSecs).getFirstElement();
    return dlgIteration != null;
  }

  /**
   * <p>
   * Set name of RQM report.
   *
   * @param reportName is the name of report.
   */
  public void setReportName(final String reportName) {
    Text criteriaName = this.engine.findElementWithDuration(Criteria.isTextField().hasLabel("Name:"), this.timeInSecs)
        .getFirstElement();
    criteriaName.setText(reportName + DateUtil.getCurrentDateAndTime());
    LOGGER.LOG.info(reportName + " is entered in Name text field.");
  }

  /**
   * <p>
   * Set description of RQM report.
   *
   * @param reportDescription is the description of report.
   */
  public void setReportDescription(final String reportDescription) {
    Text criteriaName = this.engine
        .findElementWithDuration(Criteria.isTextField().isMultiLine().hasLabel("Description:"), this.timeInSecs)
        .getFirstElement();
    criteriaName.setText(reportDescription);
    LOGGER.LOG.info(reportDescription + " is entered in Description text field.");
  }

  /**
   * <P>
   * Navigate to RQM create report page and select the folder to save the report in that folder.
   *
   * @param folderName name of the folder.
   */
  public void selectReportFolder(final String folderName) {
    waitForPageLoaded();
    Label lblFldr = this.engine.findElement(Criteria.isLabel().withText(folderName)).getFirstElement();
    lblFldr.click();
    LOGGER.LOG.info("Clicked on " + folderName + " folder.");
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH, "Reports");
  }

}
