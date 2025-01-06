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
 * This Page contains RQM Trash Page related data.
 * @author KYY1HC
 */
public class RQMTrashPage extends AbstractRQMPage {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMTrashPage(final WebDriverCustom driverCustom) {
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
    this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMTESTDATAREQUIREMENT_LINK_XPATH, "Trash");
  }

  /**
   * <p>
   * After navigate to the View Trash page, 
   * Select 'Baseline' option in 'View:' label.
   * Select 'Deleted By' option in 'Group By:' label.
   * Search the deleted baseline and verify the deleted baseline is displayed.
   * <p>
   * 
   * @author KYY1HC
   * @param baselineName name of baseline need to delete.
   * @return true if the baseline name is displayed, otherwise return false.
   */
  public boolean isBaselineDisplayed(final String baselineName) {
    TextField searchBaseline = this.engine.findFirstElementWithDuration(Criteria.isTextField().withPlaceHolder("Type filter text and press Enter"), this.timeInSecs);
    searchBaseline.setText(baselineName);
    Button btnFilter = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Filter"), this.timeInSecs);
    btnFilter.click();
    waitForSecs(3);
    
    Row rowBaseline = this.engine.findFirstElementWithDuration(Criteria.isRow().containsText(baselineName), this.timeInSecs);
    return rowBaseline.getWebElement().isDisplayed();
  }

  /**
   * <p>
   * After navigate to the View Trash page, 
   * Select 'Baseline' option in 'View:' label.
   * <p>
   * 
   * @author KYY1HC
   * @param viewOption option of 'View:' dropdown includes: Attachment, Baseline, Build Definition, Buid Record ...
   */
  public void selectOptionInViewDropDown(final String viewOption) {
    Dropdown drdView = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel("View: "), this.timeInSecs);
    drdView.selectOptionWithText(viewOption);
    waitForSecs(3);
  }

  /**
   * <p>
   * After navigate to the View Trash page, 
   * Select 'Baseline' option in 'View:' label.
   * Select 'Deleted By' option in 'Group By:' label.
   * <p>
   * 
   * @author KYY1HC
   * @param groupByOption option of 'Group By:' dropdown includes: Ungrouped and Deleted By.
   */
  public void selectOptionInGroupByDropDown(final String groupByOption) {
    Dropdown drdGroupBy = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel("Group By: "), this.timeInSecs);
    drdGroupBy.selectOptionWithText(groupByOption);
    waitForSecs(3);
  }
  
  /**
   * <p>
   * After navigate to the View Trash page, 
   * Select 'Baseline' option in 'View:' label
   * Select 'Deleted By' option in 'Group By:' label
   * Search the deleted baseline, select checkbox of baseline in table
   * click on 'Actions' dropdown and select 'Restore Baseline' option.
   * <p>
   * 
   * @author KYY1HC
   * @param baselineName name of baseline need to delete
   */
  public void restoreBaseline(final String baselineName) {
    Button btnClearFilterText = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Clear Filter Text"), this.timeInSecs);
    btnClearFilterText.click();
    waitForSecs(3);
    TextField searchBaseline = this.engine.findFirstElementWithDuration(Criteria.isTextField().withPlaceHolder("Type filter text and press Enter"), this.timeInSecs);
    searchBaseline.setText(baselineName);
    Button btnFilter = this.engine.findFirstElementWithDuration(Criteria.isButton().withToolTip("Filter"), this.timeInSecs);
    btnFilter.click();
    waitForSecs(3);
    
    Row rowBaseline = this.engine.findFirstElementWithDuration(Criteria.isRow().containsText(baselineName), this.timeInSecs);
    rowBaseline.hoverOnElement();
    Dropdown drdAction = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withToolTip("Actions").inContainer(rowBaseline), this.timeInSecs);
    drdAction.selectOptionWithText("Restore Baseline");
    waitForSecs(3);
    
    Button btnRestore = this.engine.findFirstElementWithDuration(Criteria.isButton().withText("Restore"), this.timeInSecs);
    btnRestore.click();
  }
}
