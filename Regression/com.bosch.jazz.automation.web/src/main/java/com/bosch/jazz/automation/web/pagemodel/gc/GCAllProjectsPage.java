/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractGCPage;

/**
 * This page represents GCAllProjects Page.
 */
public class GCAllProjectsPage extends AbstractGCPage {

  /**
   * @param driverCustom driver
   */
  public GCAllProjectsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * Assumes the page is shown where all project areas are listed, then clicks on the given project area in the list.
   *
   * @param projectAreaName the name of the project area, must not be null
   */
  @Override
  public void selectProjectArea(final String projectAreaName) {
    this.driverCustom.clickOnLink(projectAreaName, false);
    LOGGER.LOG.info(projectAreaName + "project area opened successfully.");
  }

}
