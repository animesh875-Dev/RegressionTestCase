/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This page represents to RQMAllProjects Page.
 */
public class RQMAllProjectsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads a requirement management builds page and set build record name.
   */
  @Test
  public void testWaitForPageLoaded() {
    loadPage("rqm/set_build_record_name.html");
    RQMAllProjectsPage builds = getJazzPageFactory().getRQMAllProjectsPage();
    assertNotNull(builds);
    builds.waitForPageLoaded();
  }

  /**
   * Loads a requirement management builds page and set build record name.
   */
  /*
   * @Test public void testOpen() { loadPage("rqm/set_build_record_name.html"); RQMAllProjectsPage builds =
   * getJazzPageFactory().getRQMAllProjectsPage(); assertNotNull(builds); Map<String, String> additionalParams = new
   * LinkedHashMap<String, String>(); additionalParams.put("priorityValue", "Medium"); builds.open("", "",
   * additionalParams); }
   */

  /**
   * Loads a requirement management builds page and set build record name.
   */
  @Test
  public void testWaItForPageLoaded() {
    loadPage("dng/loadArtifactsInProjectArea.html");
    RQMAllProjectsPage builds = getJazzPageFactory().getRQMAllProjectsPage();
    assertNotNull(builds);
    builds.waitForPageLoaded();
  }

  /**
   * Loads a requirement management builds page and set build record name.
   */
  /*
   * @Test public void testOPen() { loadPage("dng/loadArtifactsInProjectArea.html"); RQMAllProjectsPage builds =
   * getJazzPageFactory().getRQMAllProjectsPage(); assertNotNull(builds); Map<String, String> additionalParams = new
   * LinkedHashMap<String, String>(); additionalParams.put("priorityValue", "Medium"); builds.open("", "",
   * additionalParams); }
   */
}
