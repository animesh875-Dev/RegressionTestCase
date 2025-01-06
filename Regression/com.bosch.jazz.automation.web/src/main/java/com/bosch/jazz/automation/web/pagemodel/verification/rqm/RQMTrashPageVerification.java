/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.rqm;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMTrashPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Dropdown;

/**
 * @author KYY1HC
 */
public class RQMTrashPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMTrashPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * This method called after {@link RQMTrashPage#isBaselineDisplayed(String)}.
   * 
   * @author KYY1HC
   * @param baselineName name of baseline need to delete
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyIsBaselineDisplayed(final String baselineName, final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Baseline '" + baselineName + "' is displayed on the table.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Baseline '" + baselineName + "' is NOT displayed on the table.");
  }

  /**
   * <p>
   * This method called after {@link RQMTrashPage#selectOptionInViewDropDown(String)}.
   * 
   * @author KYY1HC
   * @param viewOption option of 'View:' dropdown includes: Attachment, Baseline, Build Definition, Buid Record ...
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectOptionInViewDropDown(final String viewOption, final String lastResult) {
    Dropdown drdView = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel("View: "), this.timeInSecs);
    if (drdView.getDefaultValue().equalsIgnoreCase(viewOption)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - The option '" + viewOption + "' is selected on the 'View:' Dropdown.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - The option '" + viewOption + "' is NOT selected on the 'View:' Dropdown.");
  }

  /**
   * <p>
   * This method called after {@link RQMTrashPage#selectOptionInGroupByDropDown(String)}.
   * 
   * @author KYY1HC
   * @param groupByOption option of 'Group By:' dropdown includes: Ungrouped and Deleted By.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySelectOptionInGroupByDropDown(final String groupByOption, final String lastResult) {
    Dropdown drdGroupBy = this.engine.findFirstElementWithDuration(Criteria.isDropdown().withLabel("Group By: "), this.timeInSecs);
    if (drdGroupBy.getDefaultValue().equalsIgnoreCase(groupByOption)) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - The option '" + groupByOption + "' is selected on the 'Group By:' Dropdown.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - The option '" + groupByOption + "' is NOT selected on the 'Group By:' Dropdown.");
  }
  
  /**
   * <p>
   * This method called after {@link RQMTrashPage#restoreBaseline(String)}.
   * 
   * @author KYY1HC
   * @param baselineName name of baseline need to delete
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRestoreBaseline(final String baselineName, final String lastResult) {
    waitForSecs(Duration.ofSeconds(3));
    WebElement messageSuccess = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_TESTCASESUCCESS_MASSAGE_XPATH);
    if (messageSuccess.getText().trim().equalsIgnoreCase("The artifacts have been restored")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - The Deleted Baseline '" + baselineName + "' is restored successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - The Deleted Baseline '" + baselineName + "' is NOT restored successfully.");
  }
}
