/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This class represents AbstractGC Page unit test cases.
 */
public class AbstractGCPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testOpenAndSelectSubMenuInCurrentConfiguration() {
    loadPage("dng/select_project_area_and_global_configuration_current_project_component.html");
    AbstractGCPage rqm = getJazzPageFactory().getAbstractGCPage();
    assertNotNull(rqm);
    rqm.openAndSelectSubMenuInCurrentConfiguration("Create Baseline...");
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testGetCurrentConfiguration() {
    loadPage("dng/select_project_area_and_global_configuration_current_project_component.html");
    AbstractGCPage rqm = getJazzPageFactory().getAbstractGCPage();
    assertNotNull(rqm);
    rqm.getCurrentConfiguration();
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testCreateConfiguration() {
    loadPage("dng/createConfiguration1.html");
    AbstractGCPage rqm = getJazzPageFactory().getAbstractGCPage();
    assertNotNull(rqm);
    rqm.createConfiguration("test", "test");
  }
}
