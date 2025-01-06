/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit test for the JRSMyReportsPage.
 *
 * @author gem5kor
 */
public class JRSMyReportsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads the url and waits until the page is fully loaded
   */
  @Test
  public void openTest() {
    loadPage("jrs/open.html");
    JRSMyReportsPage jrsMyReportsPage = getJazzPageFactory().getJRSMyReportsPage();
    assertNotNull(jrsMyReportsPage);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/rs";
    jrsMyReportsPage.open(repositoryURL, null, null);
  }
}
