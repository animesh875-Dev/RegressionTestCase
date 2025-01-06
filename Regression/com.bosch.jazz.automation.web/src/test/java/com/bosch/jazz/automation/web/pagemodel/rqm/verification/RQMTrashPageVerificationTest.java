/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.rqm.RQMTrashPageVerification;

/**
 * Unit tests coverage for the RQMLabManagmentPageVerification.
 */
public class RQMTrashPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RQMTrashPageVerification#verifyIsBaselineDisplayed(String, String)}.
   * Passed case
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyIsBaselineDisplayed() {
    loadPage("rqm/IsBaselineDisplayed_02.html");
    RQMTrashPageVerification rqmtpv = getJazzPageFactory().getRQMTrashPageVerification();
    assertNotNull(rqmtpv);
    rqmtpv.verifyIsBaselineDisplayed("", "true");
  }
  
  /**
   * <p>Unit test coverage for {@link RQMTrashPageVerification#verifyIsBaselineDisplayed(String, String)}.
   * Failed case
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerIfyIsBaselineDisplayed() {
    loadPage("rqm/IsBaselineDisplayed_01.html");
    RQMTrashPageVerification rqmtpv = getJazzPageFactory().getRQMTrashPageVerification();
    assertNotNull(rqmtpv);
    rqmtpv.verifyIsBaselineDisplayed("", "false");
  }
  
  /**
   * <p>Unit test coverage for {@link RQMTrashPageVerification#verifyRestoreBaseline(String, String)}.
   *  
   * @author KYY1HC
   */
  @Test
  public void testVerifyRestoreBaseline() {
    loadPage("rqm/RestoreBaseline_05.html");
    RQMTrashPageVerification rqmtpv = getJazzPageFactory().getRQMTrashPageVerification();
    assertNotNull(rqmtpv);
    rqmtpv.verifyRestoreBaseline("rbd_briBk10_pf_sw BL TEST 20210713", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMTrashPageVerification#verifySelectOptionInViewDropDown(String, String)}.
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifySelectOptionInViewDropDown() {
    loadPage("rqm/SelectOptionInViewDropDown_02.html");
    RQMTrashPageVerification rqmtpv = getJazzPageFactory().getRQMTrashPageVerification();
    assertNotNull(rqmtpv);
    rqmtpv.verifySelectOptionInViewDropDown("Baseline", "");
  }

  /**
   * <p>Unit test coverage for {@link RQMTrashPageVerification#verifySelectOptionInGroupByDropDown(String, String)}.
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifySelectOptionInGroupByDropDown() {
    loadPage("rqm/SelectOptionInGroupByDropDown_01.html");
    RQMTrashPageVerification rqmtpv = getJazzPageFactory().getRQMTrashPageVerification();
    assertNotNull(rqmtpv);
    rqmtpv.verifySelectOptionInGroupByDropDown("Deleted By", "");
  }
}