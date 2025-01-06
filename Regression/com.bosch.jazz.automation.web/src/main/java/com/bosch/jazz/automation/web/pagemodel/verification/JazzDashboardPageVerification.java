/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.JazzDashboardPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMArtifactsPageVerification;

/**
 * This page represents JazzDashboardPage verification.
 * 
 * @author KYY1HC
 */
public class JazzDashboardPageVerification extends RMArtifactsPageVerification {

  /**
   * @param driverCustom the non-null custom web driver.
   */
  public JazzDashboardPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * Verify method {@link JazzDashboardPage#open(String)}
   *
   * @author KYY1HC
   * @param url URL which need to be open in the browser
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyOpen(final String url, final String lastResult) {
    String currenturl = this.driverCustom.getCurrUrl();
    if (currenturl.contains(url)) {
      return new TestAcceptanceMessage(true, String.format("Verified: Page is navigated to '%s' url.", url));
    }
    return new TestAcceptanceMessage(false, String.format("Verified: Page is not navigated to '%s' url." +
        "\nExpected URL: '%s'\nActual URL: '%s'", url, url, currenturl));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_MULTIPLEVALUE_CHECKBOX_XPATH, "User ID");
  }
}
