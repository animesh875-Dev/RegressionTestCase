/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;

/**
 * Represents the Requirements Management All Projects Page.
 */
public class RMAllProjectsPage extends AbstractRMPage {

  /**
   * @param driverCustom required for interacting with the browser.
   */
  public RMAllProjectsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * This method is used to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_TITLE_XPATH, "Requirements Management (/rm)");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }
}
