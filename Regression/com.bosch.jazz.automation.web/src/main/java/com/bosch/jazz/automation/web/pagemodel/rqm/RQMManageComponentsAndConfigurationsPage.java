/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.Dropdown;
import com.bosch.psec.web.test.element.container.Row;
import com.bosch.psec.web.test.element.text.TextField;
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
 * This Page contains RQM Manage Components and Configurations Page related data.
 * @author KYY1HC
 */
public class RQMManageComponentsAndConfigurationsPage extends AbstractRQMPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMManageComponentsAndConfigurationsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new JazzTextFieldFinder(), new JazzTextEditorFinder(),
        new JazzDropdownFinder(), new JazzRowFinder(this.driverCustom.getWebDriver()), new RowCellFinder(),
        new ColumnFinder(), new LinkFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(), new CheckboxFinder(),
        new DropdownFinder(), new JazzColumnFinder(), new ColumnCellFinder(), new LabelFinder(), new JazzLabelFinder(),
        new JazzTreeNodeFinder());
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, "Manage Components and Configurations");
  }

  /**
   * <p>
   * After navigate to the Manage Components and Configurations page, click on Baselines tabname
   * Then input name of baseline to search, click on Actions dropdown and select 'Delete Baseline' option
   * Delete a Baseline dialog is displayed and click Delete button to confirm.
   * <p>
   * 
   * @author KYY1HC
   * @param baselineName name of baseline need to delete
   */
  public void deleteBaseline(final String baselineName) {
    TextField searchBaseline = this.engine.findFirstElementWithDuration(Criteria.isTextField().withPlaceHolder("Type to search"), this.timeInSecs);
    searchBaseline.setText(baselineName);
    Button btnFilter = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Filter"), this.timeInSecs);
    btnFilter.click();
    waitForSecs(3);
    
    Row rowBaseline = this.engine.findFirstElementWithDuration(Criteria.isRow().containsText(baselineName), this.timeInSecs);
    rowBaseline.hoverOnElement();
    Dropdown drdAction = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withToolTip("Actions").inContainer(rowBaseline), this.timeInSecs);
    drdAction.selectOptionWithText("Delete Baseline");
    waitForSecs(3);
    
    Button btnDelete = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Delete"), this.timeInSecs);
    btnDelete.click();
  }
}
