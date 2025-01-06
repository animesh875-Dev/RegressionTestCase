/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.GCAllProjectsPageVerification;

/**
 * this page represents unit test coverage of GCAllProjectsPageVerification.
 */
public class GCAllProjectsPageVerificationTest extends AbstractFrameworkUnitTest {


  /**
   * <p>Unit test coverage for {@link GCAllProjectsPageVerification#verifySelectProjectAreaGC(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifySelectProjectAreaGC() {
    loadPage("gc/Global Configuration Management.html");
    GCAllProjectsPageVerification rqm = getJazzPageFactory().getGCAllProjectsPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectProjectAreaGC("", "");
  }

  /**
   * <p>Unit test coverage for {@link GCAllProjectsPageVerification#verifySelectProjectAreaGC(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerIfySelectProjectAreaGC() {
    loadPage("rqm/testScriptsID.html");
    GCAllProjectsPageVerification rqm = getJazzPageFactory().getGCAllProjectsPageVerification();
    assertNotNull(rqm);
    rqm.verifySelectProjectAreaGC("", "");
  }

}
