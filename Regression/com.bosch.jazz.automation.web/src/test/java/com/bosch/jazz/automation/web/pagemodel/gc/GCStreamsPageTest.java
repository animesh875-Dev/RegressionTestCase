/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * @author LTU7HC
 *
 */
public class GCStreamsPageTest extends AbstractFrameworkUnitTest{

  /**
   * Unit test for {@link GCStreamsPage#filterAndSelectStream(String)}
   * @author LTU7HC
   */
  @Test
  public void testSearchAndSelectStream() {
    loadPage("gc/searchStream.html");
    GCStreamsPage gcStreamsPage = getJazzPageFactory().getGCStreamsPage();
    assertNotNull(gcStreamsPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    gcStreamsPage.filterAndSelectStream("ALM_System_AcceptanceTest_Platform");
  }
}
