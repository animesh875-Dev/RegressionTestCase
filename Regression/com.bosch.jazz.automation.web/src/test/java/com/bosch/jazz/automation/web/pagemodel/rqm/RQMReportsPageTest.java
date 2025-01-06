/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This Page contains RQM Reports Page Page related data.
 */
public class RQMReportsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testClickOnPropertiesTab() {
    loadPage("rqm/verify_dialogue_box.html");
    RQMReportsPage rqm = getJazzPageFactory().getRQMReportsPage();
    assertNotNull(rqm);
    assertTrue(rqm.verifyDialogueBox("Test Plan"));
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSetReportName() {
    loadPage("rqm/set_report_name.html");
    RQMReportsPage rqm = getJazzPageFactory().getRQMReportsPage();
    assertNotNull(rqm);
    rqm.setReportName("Test Plan");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSetReportDescription() {
    loadPage("rqm/set_report_name.html");
    RQMReportsPage rqm = getJazzPageFactory().getRQMReportsPage();
    assertNotNull(rqm);
    rqm.setReportDescription("Test Plan");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSelectReportFolder() {
    loadPage("rqm/set_report_name.html");
    RQMReportsPage rqm = getJazzPageFactory().getRQMReportsPage();
    assertNotNull(rqm);
    rqm.selectReportFolder("My Reports");
  }
}
