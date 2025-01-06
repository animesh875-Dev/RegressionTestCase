/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.util.Map;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;

/**
 * <p>Represents abstraction of methods to global configuration pages.
 * <br>Acts as super class to all global configuration pages.
 * <br>Declare methods which are common to global configuration pages, 
 * declare abstract methods which are to be overridden by every other pages of global configuration pages.
 * 
 * @author HRT5KOR
 */
public class AbstractGCPage extends AbstractJazzWebPage {

  /**
   * Constructor setting the object of {@link WebDriverCustom} class.
   * @param driverCustom required for interacting with the browser.
   */
  public AbstractGCPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    throw new UnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, "Artifacts");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
  }


}
