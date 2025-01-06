/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.ccm;

import java.time.Duration;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWelcomeToWorkItemPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;

/**
 * @author UUM4KOR
 */
public class CCMWelcomeToWorkItemPageVerification extends AbstractWebPageVerification {

  /**
   * @param driverCustom driver.
   */
  public CCMWelcomeToWorkItemPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <P>
   * Verifies name or id of the work item.
   * <P>
   * This method called after {@link CCMWelcomeToWorkItemPage#searchWorkItem(String)}
   *
   * @param wiId of the searched item
   * @param lastResult returned value of method which is executed just before the method.
   * @return Acceptance object which contains verification results.
   */
  public TestAcceptanceMessage verifySearchWorkItem(final String wiId, final String lastResult) {
    if (this.driverCustom.isTitleContains(wiId, Duration.ofSeconds(20))) {
      return new TestAcceptanceMessage(true, "Verified by title of the opened specification.\nActual title is -\"" +
          this.driverCustom.getWebDriver().getTitle() + ".\"\nActual title contains Expected text \"" + wiId + ".\"");
    }
    return new TestAcceptanceMessage(true, "Verified by title of the opened specification.\nActual title is -\"" +
        this.driverCustom.getWebDriver().getTitle() + ".\" \nActual title not contain Expected text \"" + wiId + ".\"");
  }


}
