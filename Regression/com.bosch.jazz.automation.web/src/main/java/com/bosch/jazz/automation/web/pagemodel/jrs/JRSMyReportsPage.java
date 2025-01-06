/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;

/**
 * Represents the JRS page to build a new JRS report.
 */
public class JRSMyReportsPage extends JRSPage {

  /**
   * See {@link JRSPage#JRSPage(WebDriverCustom)}
   */
  public JRSMyReportsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.openURL(repositoryURL + "/reports#mine");
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page. This method wait for
   * presence of Build link in 'JRSReportBuilder' Page.
   */
  @Override
  public void waitForPageLoaded() {

    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZ_SPANSELECTION_XPATH, "BUILD");

  }
}
