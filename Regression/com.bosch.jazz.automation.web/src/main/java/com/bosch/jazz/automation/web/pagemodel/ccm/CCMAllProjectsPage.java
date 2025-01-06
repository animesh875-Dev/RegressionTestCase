/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.pagemodel.AbstractCCMPage;

/**
 * Represents "All Projects" page in the CCM application.
 */
public class CCMAllProjectsPage extends AbstractCCMPage {

  /**
   * Constructor setting the {@link WebDriverCustom}
   * 
   * @param driverCustom required for interacting with the browser.
   */
  public CCMAllProjectsPage(final WebDriverCustom driverCustom) {
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
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the list.
   *
   * @param projectAreaName the name of the project area, must not be null
   */
  @Override
  public void selectProjectArea(final String projectAreaName) {
    this.driverCustom.clickOnLink(projectAreaName, false);
    LOGGER.LOG.info(projectAreaName+ "project area opened successfully.");
  }

  /**
   * This method wait for the presence of 'Change and Configuration Management (/ccm)' link in CCM application.
   */
  @Override
  public void waitForPageLoaded() {
    WebDriverWait wait = new WebDriverWait(this.driverCustom.getWebDriver(),
        this.driverCustom.getWebDriverSetup().getConfigurationForExplicitWaitTime());
    By locator =
        this.driverCustom.createLocator("//a[text()='Change and Configuration Management (/ccm)']", ByType.XPATH);
    ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOfElementLocated(locator);
    wait.until(condition);

  }
}