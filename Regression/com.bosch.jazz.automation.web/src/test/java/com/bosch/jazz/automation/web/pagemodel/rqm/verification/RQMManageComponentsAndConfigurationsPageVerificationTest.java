/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMManageComponentsAndConfigurationsPageVerification;

/**
 * Unit tests coverage for the RQMLabManagmentPageVerification.
 */
public class RQMManageComponentsAndConfigurationsPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RQMManageComponentsAndConfigurationsPageVerification#verifyDeleteBaseline(String, String)}.
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyDeleteBaseline() {
    loadPage("rqm/DeleteBaselines_05.html");
    RQMManageComponentsAndConfigurationsPageVerification rqmmcacpv = getJazzPageFactory().getRQMManageComponentsAndConfigurationsPageVerification();
    assertNotNull(rqmmcacpv);
    rqmmcacpv.verifyDeleteBaseline("rbd_briBk10_pf_sw BL TEST 20210713", "");
  }
}