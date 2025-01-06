/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This page represents GCAllProjects Page test.
 *
 */
public class GCAllProjectsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testSelectProjectArea() {
    loadPage("ccm/login_page_verification.html");
    GCAllProjectsPage query = getJazzPageFactory().getGCAllProjectsPage();
    assertNotNull(query);
    query.selectProjectArea("ALM Test (CCM)");
  }


}
