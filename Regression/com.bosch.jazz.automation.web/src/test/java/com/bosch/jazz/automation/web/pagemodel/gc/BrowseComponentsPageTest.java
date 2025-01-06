/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This page represents to BrowseComponents Page.
 */
public class BrowseComponentsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads GC page and click on 'Create Component...' button.
   */
  @Test
  public void testClickOnCreateComponentButton() {
    loadPage("gc/click_on_create_component_button.html");
    BrowseComponentsPage rqm = getJazzPageFactory().getBrowseComponentsPage();
    assertNotNull(rqm);
    rqm.clickOnCreateComponentButton();
  }

  /**
   * Loads GC page and create component from 'Create Component...' button.
   */
  @Test
  public void testCreateComponent() {
    loadPage("gc/create_component_1.html");
    BrowseComponentsPage rqm = getJazzPageFactory().getBrowseComponentsPage();
    assertNotNull(rqm);
    rqm.createComponent("test", "Component_Description", "test_tag");
  }
}