/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This page represents AbstractCCM Page.
 */
public class AbstractCCMPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testWaitForPageLoaded() {
    loadPage("ccm/plans_tab.html");
    AbstractCCMPage rqm = getJazzPageFactory().getAbstractCCMPage();
    assertNotNull(rqm);
    rqm.waitForPageLoaded();
  }

  /**
   * Loads RQM test case page and executionRecord(final Map<String, String> additionalParams) will run the test case and
   * check the test case passed or not.
   */
  @Test
  public void testOpenAndSelectSubMenuInCurrentConfiguration() {
    loadPage("ccm/plans_tab.html");
    AbstractCCMPage rqm = getJazzPageFactory().getAbstractCCMPage();
    Map<String, String> additionalparam=new HashMap<String, String>();
    assertNotNull(rqm);
    rqm.open("","", additionalparam);
  }
}
