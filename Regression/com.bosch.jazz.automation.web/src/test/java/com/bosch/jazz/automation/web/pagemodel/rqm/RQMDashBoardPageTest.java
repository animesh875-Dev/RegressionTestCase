/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This page represents RQMDashBoard Page unit test cases.
 */
public class RQMDashBoardPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads a requirement management builds page and set build record name.
   */
  @Test
  public void testWaitForPageLoaded() {
    loadPage("rqm/set_build_record_name.html");
    RQMDashBoardPage builds = getJazzPageFactory().getRQMDashBoardPage();
    assertNotNull(builds);
    builds.waitForPageLoaded();
  }

  /**
   * Loads a requirement management builds page and set build record name.
   */
  /*
   * @Test public void testOpen() { loadPage("rqm/set_build_record_name.html"); RQMDashBoardPage builds =
   * getJazzPageFactory().getRQMDashBoardPage(); assertNotNull(builds); Map<String, String> additionalParams = new
   * LinkedHashMap<String, String>(); additionalParams.put("priorityValue", "Medium"); builds.open("", "",
   * additionalParams); }
   */

  /**
   * Loads a requirement management builds page and set build record name.
   */
  @Test
  public void testWaItForPageLoaded() {
    loadPage("dng/loadArtifactsInProjectArea.html");
    RQMDashBoardPage builds = getJazzPageFactory().getRQMDashBoardPage();
    assertNotNull(builds);
    builds.waitForPageLoaded();
  }

  /**
   * Loads a requirement management builds page and set build record name.
   */
  /*
   * @Test public void testOPen() { loadPage("dng/loadArtifactsInProjectArea.html"); RQMDashBoardPage builds =
   * getJazzPageFactory().getRQMDashBoardPage(); assertNotNull(builds); Map<String, String> additionalParams = new
   * LinkedHashMap<String, String>(); additionalParams.put("priorityValue", "Medium"); builds.open("", "",
   * additionalParams); }
   */
  
  /**
   * Unit test to verify {@link RQMDashBoardPage#quickSearch(String)}.
   */
  @Test
  public void testQuickSearch() {
    loadPage("rqm/quick_search_RQM.html");
    RQMDashBoardPage rqm = getJazzPageFactory().getRQMDashBoardPage();
    assertNotNull(rqm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(rqm.quickSearch("91860"));
  }
}
