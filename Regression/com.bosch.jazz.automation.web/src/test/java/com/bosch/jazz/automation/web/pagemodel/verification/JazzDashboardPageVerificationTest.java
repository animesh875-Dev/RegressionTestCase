/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * @author KYY1HC
 */
public class JazzDashboardPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link JazzDashboardPageVerification#verifyOpen(String, String)}.
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyOpen() {
    JazzDashboardPageVerification jdbpv = getJazzPageFactory().getJazzDashboardPageVerification();
    loadPage("jrs/add_widget_1.html");
    assertNotNull(jdbpv);
    jdbpv.verifyOpen(driver.getCurrentUrl(), "true");
  }
}
