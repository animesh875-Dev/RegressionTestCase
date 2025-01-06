/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.time.Duration;
import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.jrs.JRSConstants;
import com.bosch.psec.web.test.finder.DropdownFinder;
import com.bosch.psec.web.test.finder.container.TabFinder;
import com.bosch.psec.web.test.finder.container.cell.RowCellFinder;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.button.JazzButtonFinder;
import finder.container.dialog.JazzDialogFinder;
import finder.container.tab.JazzTabFinder;
import finder.dropdown.JazzDropdownFinder;

/**
 * Class for Jazz Dashboard
 */
public class JazzDashboardPage extends AbstractJazzWebPage {


  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom must not be null
   */
  public JazzDashboardPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new JazzDropdownFinder(), new RowCellFinder(), new JazzTabFinder(),
        new TabFinder(),new JazzDialogFinder(),new DropdownFinder(),new LinkFinder());
  }

  /**
   * Can be called to open a URL in the browser. In order to open a URL, url must be provide to this method.
   *
   * @param dashboardUrl URL which need to be open in the browser
   */
  public void open(final String dashboardUrl) {
    this.driverCustom.openURL(dashboardUrl);
  }

  /**
   * @param i The maximum time to wait in milliseconds
   */
  public void waitForElement(final int i) {
    waitForSecs(Duration.ofSeconds(i));
  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page. This method wait for
   * the presence of alltabs div area in 'JazzDashboardPage'.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(JRSConstants.JAZZDASHBOARDPAGE_ALLTABS_DIV_XPATH);
  }

  /** 
   * {@inheritDoc}
   */
  @Override
  public void open(String repositoryURL, String projectAreaName, Map<String, String> additionalParams) {
    // TODO Auto-generated method stub
    
  }
}
