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
 * This page represents Components Page test.
 */
public class ComponentsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM test script page and formalReviewSection(final Map<String, String> additionalParams) will add test script
   * approver.
   */
  @Test
  public void testFormalReviewSection() {
    loadPage("gc/filter_component.html");
    ComponentsPage rqm = getJazzPageFactory().getComponentsPage();
    assertNotNull(rqm);
    rqm.filterComponent("test");
  }
  
  /**
   * Unit test for {@link ComponentsPage#filterAndSelectComponent(String)}
   * @author LTU7HC
   */
  @Test
  public void testFilterAndSelectComponent() {
    loadPage("gc/searchComponent.html");
    ComponentsPage gcComponentsPage = getJazzPageFactory().getComponentsPage();
    assertNotNull(gcComponentsPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    gcComponentsPage.filterAndSelectComponent("ALM_System");
  }
}
