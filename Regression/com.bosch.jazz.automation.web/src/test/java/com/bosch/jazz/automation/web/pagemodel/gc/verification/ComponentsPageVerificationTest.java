/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.ComponentsPageVerification;

/**
 * @author UUM4KOR
 */
public class ComponentsPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   *  <p>Unit test coverage for {@link ComponentsPageVerification#veifyFilterComponent(String,String)}.
   * 
   *  <p>Verify Filter Component
   */
  @Test
  public void testveifyFilterComponent() {
    loadPage("gc/veifyFilterComponent.html");
    ComponentsPageVerification rqm = getJazzPageFactory().getComponentsPageVerification();
    assertNotNull(rqm);
    rqm.veifyFilterComponent("ALM_System", "");
  }

  /**
   *  <p>Unit test coverage for {@link ComponentsPageVerification#veifyFilterComponent(String,String)}.
   *  
   *  <p>Verify Filter Component One
   */
  @Test
  public void testveifyFilterComponentOne() {
    loadPage("gc/veifyFilterComponent.html");
    ComponentsPageVerification rqm = getJazzPageFactory().getComponentsPageVerification();
    assertNotNull(rqm);
    rqm.veifyFilterComponent("Invalid", "");
  }
  
  /**
   *  <p>Unit test coverage for {@link ComponentsPageVerification#verifyFilterAndSelectComponent(String, String)}.
   *  
   *  <p>Verify search and select component
   */
  @Test
  public void testVerifyFilterAndSelectComponent() {
    loadPage("gc/searchAndSelectComponent.html");
    ComponentsPageVerification componentPageVerification = getJazzPageFactory().getComponentsPageVerification();
    assertNotNull(componentPageVerification);
    assertEquals("PASSED", componentPageVerification.verifyFilterAndSelectComponent("ALM_System", "true").getState());
    assertEquals("FAILED", componentPageVerification.verifyFilterAndSelectComponent("ALM_Systems", "false").getState());
  }
}
