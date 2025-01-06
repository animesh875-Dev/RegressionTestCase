/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMLabManagmentPageVerification;

/**
 * Unit tests coverage for the RQMLabManagmentPageVerification.
 */
public class RQMLabManagmentPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RQMLabManagmentPageVerification#verifyRemoveTestEnvironment(String, String, String)}.
   * 
   * <p>Verify test environment is removed.
   */
  @Test
  public void testVerifyRemoveTestEnvironment() {
    loadPage("rqm/remove_test_environment_2.html");
    RQMLabManagmentPageVerification rqmlbmv = getJazzPageFactory().getRQMLabManagmentPageVerification();
    assertNotNull(rqmlbmv);
    rqmlbmv.verifyRemoveTestEnvironment("AMD64", "Yes", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMLabManagmentPageVerification#verifyRemoveTestEnvironment(String, String, String)}.
   * 
   * <p>Verify test environment is not removed.
   */
  @Test
  public void testVerifyRemoveTestEnvironment_two() {
    loadPage("rqm/remove_test_environment.html");
    RQMLabManagmentPageVerification rqmlbmv = getJazzPageFactory().getRQMLabManagmentPageVerification();
    assertNotNull(rqmlbmv);
    rqmlbmv.verifyRemoveTestEnvironment("Alpha", "Yes", "");
  }

}
