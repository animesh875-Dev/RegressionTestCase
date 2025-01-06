/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.rqm;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMConstants;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMManageComponentsAndConfigurationsPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;

/**
 * @author KYY1HC
 */
public class RQMManageComponentsAndConfigurationsPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMManageComponentsAndConfigurationsPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * Verify Test Environment is removed.
   * <p>
   * This method called after {@link RQMManageComponentsAndConfigurationsPage#deleteBaseline(String)}.
   * 
   * @param baselineName name of baseline need to delete
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyDeleteBaseline(final String baselineName, final String lastResult) {
    waitForSecs(Duration.ofSeconds(3));
    WebElement messageSuccess = this.driverCustom.getWebElement(RQMConstants.RQMPROJECT_DELETEBASELINESUCCESS_MASSAGE_XPATH);
    if (messageSuccess.getText().trim().equalsIgnoreCase("The baseline is deleted.")) {
      return new TestAcceptanceMessage(true, "Verified: PASSED - Baseline '" + baselineName + "' is deleted successfully.");
    }
    return new TestAcceptanceMessage(false, "Verified: FAILED - Baseline '" + baselineName + "' is NOT deleted.");
  }
}