/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.GCStreamsPageVerification;

/**
 * @author LTU7HC
 *
 */
public class GCStreamsPageVerificationTest extends AbstractFrameworkUnitTest{
  
  /**
   *  <p>Unit test coverage for {@link GCStreamsPageVerification#verifyFilterAndSelectStream(String, String)}.
   *  
   *  <p>Verify search and select component
   */
  @Test
  public void testVerifySearchAndSelectStream() {
    loadPage("gc/searchAndSelectStream.html");
    GCStreamsPageVerification page = getJazzPageFactory().getGCStreamsPageVerification();
    assertNotNull(page);
    assertEquals("PASSED", page.verifyFilterAndSelectStream("ALM_System_AcceptanceTest_Platform", "true").getState());
    assertEquals("PASSED", page.verifyFilterAndSelectStream("ALM_System_AcceptanceTest_Platforms", "false").getState());
  }

}
