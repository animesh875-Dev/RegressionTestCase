/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.rqm;

import java.time.Duration;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.rqm.RQMLabManagmentPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;

/**
 * @author BBO1KOR
 */
public class RQMLabManagmentPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom set value to instance variable of WebDriverCustom of this class.
   */
  public RQMLabManagmentPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>
   * Verify Test Environment is removed.
   * <p>
   * This method called after {@link RQMLabManagmentPage#removeTestEnvironment(String, String)}.
   *
   * @param testEnvName name of the Test Environment.
   * @param button name of the button.
   * @param lastResult returned value of method which is executed just before the method.
   * @return acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifyRemoveTestEnvironment(final String testEnvName, final String button,
      final String lastResult) {
    if (this.driverCustom.isElementInvisible(String.format("//div[@title='%s']", testEnvName), Duration.ofSeconds(10))) {
      return new TestAcceptanceMessage(true,
          "Verified: Test Environment is removed from Lab Management.\nExpected Result \"" + testEnvName +
              "\" is removed from Test Environments.\nActual Result \"" + testEnvName +
              " should be removed from Test Environments.");
    }
    return new TestAcceptanceMessage(false, "Verified: Test Environment is not removed from Lab Management.");
  }
}
