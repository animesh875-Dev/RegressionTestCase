/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;

import java.time.Duration;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMEditWorkItemDialogInPlanPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
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
 * @author UUM4KOR
 */
public class CCMEditWorkItemDialogInPlanPageVerification extends CCMWorkItemEditorPageVerification {

  /**
   * @param driverCustom driver.
   */
  public CCMEditWorkItemDialogInPlanPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzRowFinder(this.driverCustom.getWebDriver()), new JazzTextFieldFinder(),
        new JazzDropdownFinder(), new JazzButtonFinder(), new RowCellFinder(), new JazzTabFinder(),
        new JazzDialogFinder(), new CheckboxFinder(), new RowFinder(), new JazzLabelFinder(), new LinkFinder(),
        new DropdownFinder(), new RadioButtonFinder(), new JazzRadioButtonFinder(), new TextFieldFinder(),
        new PanelFinder(), new RowCellFinder());
  }

  /**
   * <P>
   * This method verifies after set drop down attribute value compare with Actual result with expected drop down value..
   * <p>
   * Verifies the action of {@link CCMEditWorkItemDialogInPlanPage#setDropDownAttributeValue(String, String)}.
   *
   * @param dropDownAttributeName attribute name.
   * @param attributeValue attribute value.
   * @param lastResult not used additional parameter.
   * @return acceptance object which contains verification results.
   */
  @Override
  public TestAcceptanceMessage verifySetDropDownAttributeValue(final String dropDownAttributeName,
      final String attributeValue, final String lastResult) {
    if (Boolean.valueOf(lastResult)) {
      return new TestAcceptanceMessage(true,
          "Expected '" + dropDownAttributeName + "' value is -'" + attributeValue + "' is selected successfuly in '" +
              dropDownAttributeName + "' dropdown. Actual last resut is '" + lastResult + "'.");
    }
    return new TestAcceptanceMessage(false,
        "Expected '" + dropDownAttributeName + "' value is -'" + attributeValue + "' is NOT selected successfuly in '" +
            dropDownAttributeName + "' dropdown. Actual last resut is '" + lastResult + "'.");
  }

  /**
   * <p>
   * Verifies the action of {@link CCMEditWorkItemDialogInPlanPage#clickOnJazzButtons(String)}.
   *
   * @param button name of Jazz button
   * @param lastResult result
   * @return acceptance object which contains verification results.
   */
  @Override
  public TestAcceptanceMessage verifyClickOnJazzButtons(final String button, final String additionalPram,
      final String lastResult) {
    if (!this.driverCustom.isElementClickable(RMConstants.JAZZPAGE_BUTTONS_XPATH, Duration.ofSeconds(5), button)) {
      return new TestAcceptanceMessage(true,
          "Verified: " + button + " button is clicked.\n" + "Actual result '" + button +
              "' button is disabled after clicking.\nExpected result '" + button +
              "' button is disabled and 'Edit New Work Item'  is closed ");
    }
    return new TestAcceptanceMessage(false,
        "Verified a button after clicking." + "Actual result is the '" + button +
            "' button is NOT disabled after clicking and expected is the '" + button +
            "' button not disabled and 'Edit New Work Item'  is not closed \"");
  }


}
