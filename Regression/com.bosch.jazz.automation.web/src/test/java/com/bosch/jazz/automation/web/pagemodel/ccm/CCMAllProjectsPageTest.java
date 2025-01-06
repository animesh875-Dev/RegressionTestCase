/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests for CCMAllProjectsPage
 *
 * @author UUM4KOR
 */
public class CCMAllProjectsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads an change and configuration management query page and the project area with project area.
   */

  @Test
  public void testOpen() {
    loadPage("ccm/query_open.html");
    CCMAllProjectsPage projectpage = getJazzPageFactory().getCCMAllProjectsPage();
    assertNotNull(projectpage);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "SYS-TEST-com.bosch.rtc.tmpl.Formal_2017.2.0_IBM_6.0.5";

    Map<String, String> param = new HashMap<String, String>();
    param.put("", "");
    projectpage.open(repositoryURL, projectAreaName, param);
  }

  /**
   * verify Change and Configuration Management Project area page.
   */
  @Test
  public void testwaitForPageLoaded() {
    loadPage("ccm/select_projectarea.html");
    CCMAllProjectsPage projectpage = getJazzPageFactory().getCCMAllProjectsPage();

    assertNotNull(projectpage);


    projectpage.waitForPageLoaded();
  }
}

