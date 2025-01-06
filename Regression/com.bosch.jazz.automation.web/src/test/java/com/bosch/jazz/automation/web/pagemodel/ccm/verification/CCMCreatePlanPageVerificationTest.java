/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMCreatePlanPageVerification;

/**
 * @author NCY3HC
 *Loads a JazzLogin Page and verifies the methods.
 */
public class CCMCreatePlanPageVerificationTest extends CCMPlanPageVerificationTest {

  
/**
 * Unit test covers for method {@link CCMCreatePlanPageVerification#verifySetPlanName(String, String)}
 */
  @Test
  public void testVerifySetPlanName() {
    CCMCreatePlanPageVerification ccpv = getJazzPageFactory().getCCMCreatePlanPageVerification();
    loadPage("ccm/createPlan.html");
    assertNotNull(ccpv);
    ccpv.verifySetPlanName("New Plan abc", "");
  }
  
  /**
   * Unit test covers for method {@link CCMCreatePlanPageVerification#verifySetOwner(String, String)}
   */
    @Test
    public void testVerifySetOwner() {
      CCMCreatePlanPageVerification ccpv = getJazzPageFactory().getCCMCreatePlanPageVerification();
      loadPage("ccm/createPlan.html");
      assertNotNull(ccpv);
      ccpv.verifySetOwner("PS-XS Automation Test (Change Management)", "");
    }

  /**
   * Unit test covers for method {@link CCMCreatePlanPageVerification#verifySetIteration(String, String)}
   */
  @Test
  public void testVerifySetIteration() {
    CCMCreatePlanPageVerification ccpv = getJazzPageFactory().getCCMCreatePlanPageVerification();
    loadPage("ccm/createPlan.html");
    assertNotNull(ccpv);
    ccpv.verifySetIteration("Iteration created by automation_29_09_2022_14_09_66", "");
  }
}
