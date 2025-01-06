/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRQMPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.text.Text;

/**
 * this page represents RQMDashBoard Page
 */
public class RQMDashBoardPage extends AbstractRQMPage {

  /**
   * @param driverCustom driver
   */
  public RQMDashBoardPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    try {
      this.driverCustom.getPresenceOfWebElement(RQMConstants.RQMPROJECT_RQMREQUIREMENT_LINK_XPATH,
          RQMConstants.PROJECT_DASHBOARDS);
    }
    catch (Exception er) {
      LOGGER.LOG.error(er.getMessage());
    }
  }
  
  /**
   * Search in quick search text box present on right side top of the 'Quality Management' page.
   * @param name element to be searched.
   * @return element is available or not.
   */
  public boolean quickSearch(final String name) {
      this.driverCustom.isElementPresent(RMConstants.RMARTIFACTSPAGE_QUICKSERACHBOX_XPATH, Duration.ofSeconds((this.timeInSecs.getSeconds() * 5)));
      try {
        String xpathQuickSearch = "//input[@class='SearchInputText']";
        WebElement txtQuickSearch = this.driverCustom.getWebElement(xpathQuickSearch);
        txtQuickSearch.clear();
        this.driverCustom.typeCharByChar(xpathQuickSearch, name, Duration.ofSeconds(1));
        txtQuickSearch.click();
      }
      catch (Exception e) {
        Text txtSearchField = this.engine.findFirstElementWithDuration(Criteria.isTextField().withText("Search Artifacts"), this.timeInSecs);
        txtSearchField.setText(name + Keys.ENTER);
      }
      // Wait for quick search loading disappeared
      this.driverCustom.isElementInvisible(RMConstants.QUICK_SEARCH_BOX_LOADING_XPATH, this.timeInSecs);
      LOGGER.LOG.info(name + " - searched in the quick search text box.");
      return true;
    }
  
}
