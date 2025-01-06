/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.util.List;
import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;

/**
 * @author hrt5kor
 */
public class CCMApplicationAdministrationPage extends AbstractCCMPage {


  @SuppressWarnings("javadoc")
  public static final String PROCESS_DESCRIPTION = "Process Description";

  /**
   * Constructor setting the {@link WebDriverCustom}
   * @param driverCustom required for interacting with the browser.
   */
  public CCMApplicationAdministrationPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    this.driverCustom.openURL(repositoryURL);
    this.driverCustom.waitForPageLoaded();
  }

  /**
   * @param tabname name of the tab.
   */
  @Override
  public void clickOnTab(final String tabname) {
    waitForPageLoaded();
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMAPPLICATIONDMINISTRATIONPAGE_TAB_LINK_XPATH, tabname);
    this.driverCustom.click(CCMConstants.CCMAPPLICATIONDMINISTRATIONPAGE_TAB_LINK_XPATH, tabname);
  }

  /**
   * @return list of practices in the process description tab.
   */
  public List<String> getListOfPracticesInProcessDescriptionTab() {
    waitForPageLoaded();
    return this.driverCustom
        .getWebElementsText(CCMConstants.CCMAPPLICATIONADMINISTRATIONPAGE_PROCESSDESCRIPTIONPRACTICES_TEXT_XPATH);
  }

  /*
   * This method wait for the presence of project area link in ApplicationAdministration Page.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(CCMConstants.CCMAPPLICATIONDMINISTRATION_PROJECTAREA_LINK_XPATH);
  }
}
