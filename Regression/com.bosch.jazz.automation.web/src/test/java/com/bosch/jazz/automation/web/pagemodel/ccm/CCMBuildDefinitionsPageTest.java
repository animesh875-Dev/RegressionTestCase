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
 * @author UUM4KOR Represents the "Build Definitions" page in CCM application.
 */
public class CCMBuildDefinitionsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads change and configuration management builds page and open the build defination and project area.
   */
  @Test
  public void testOpen() {
    loadPage("ccm/build_definitions_open.html");
    CCMBuildDefinitionsPage build = getJazzPageFactory().getCCMBuildPage();
    assertNotNull(build);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "SYS-TEST-com.bosch.rtc.tmpl.Formal_2017.2.0_IBM_6.0.5";
    Map<String, String> params = new HashMap<String, String>();
    params.put("", "");
    build.open(repositoryURL, projectAreaName, params);
  }

  /**
   * Loads change and configuration management build defination page and open the build defination via menu.
   */
  @Test
  public void testOpenViaMenu() {
    loadPage("ccm/build_definitions_open_via_menu.html");
    CCMBuildDefinitionsPage build = getJazzPageFactory().getCCMBuildPage();
    assertNotNull(build);
    build.openViaMenu();
  }
}
