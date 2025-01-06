/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;

/**
 * This page represents to RQMAllProjects Page.
 */
public class RQMAllProjectsPage extends AbstractRQMPage {

  /**
   * @param driverCustom driver.
   */
  public RQMAllProjectsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMALLPROJECTSPAGE_APPLICATION_TITLE_LINK_XPATH,
          "Quality Management (/qm)");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }
}
