/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMReviewsPageVerification;

/**
 * Represents the RM Links Page this is common for Artifacts, Modules and Collections pages.
 */
public class RMReviewsPageVerificationTest extends AbstractFrameworkUnitTest {


  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPageVerification#verifySelectReview(String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifySelectReview() {
    loadPage("dng/verifySelectReview.html");
    RMReviewsPageVerification rm = getJazzPageFactory().getRMReviewsPageVerification();
    assertNotNull(rm);
    rm.verifySelectReview("test", "true");
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPageVerification#verifySelectReview(String, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfySelectReview() {
    loadPage("dng/verifySelectReview.html");
    RMReviewsPageVerification rm = getJazzPageFactory().getRMReviewsPageVerification();
    assertNotNull(rm);
    rm.verifySelectReview("test123", "false");
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPageVerification#verifyCopyLinkFromShareLinkToReview(String, String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyCopyLinkFromShareLinkToReview() {
    loadPage("dng/verifyCopyLinkFromShareLinkToReview.html");
    RMReviewsPageVerification rm = getJazzPageFactory().getRMReviewsPageVerification();
    assertNotNull(rm);
    rm.verifyCopyLinkFromShareLinkToReview("https://rb-alm-23-q.de.bosch.com/rm/",
        "https://rb-alm-23-q.de.bosch.com/rm/reviews/_W9-ykcoLEeuqcKRXXDOFjg");
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPageVerification#verifyCopyLinkFromShareLinkToReview(String, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   * @throws Exception exception
   */
  @Test
  public void testVerIfyCopyLinkFromShareLinkToReview() throws Exception {
    loadPage("dng/verifyCopyLinkFromShareLinkToReview.html");
    RMReviewsPageVerification rm = getJazzPageFactory().getRMReviewsPageVerification();
    assertNotNull(rm);
    rm.verifyCopyLinkFromShareLinkToReview("rb-alm-12-q.de.bosch.com/",
        "https://rb-alm-23-q.de.bosch.com/rm/reviews/_W9-ykcoLEeuqcKRXXDOFjg");
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPageVerification#verifyIsReviewDetailsPageOpened(Map, String)} <br>
   * <p>
   *
   * @author NVV1HC
   * @throws Exception exception
   */
  @Test
  public void testVerifyIsReviewDetailsPageOpened() throws Exception {
    RMReviewsPageVerification rm = getJazzPageFactory().getRMReviewsPageVerification();
    assertNotNull(rm);
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("name", "Review for automatic testing TS_25905");
    params.put("participant", "Phung Trong Van (RBVH/EET23)");
    params.put("participant_type", "Reviewer");
    params.put("artifact_id", "1714843");
    rm.verifyIsReviewDetailsPageOpened(params, "true");
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPageVerification#verifyIsReviewDetailsPageOpened(Map, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   * @throws Exception exception
   */
  @Test
  public void testVerIfyIsReviewDetailsPageOpened() throws Exception {
    RMReviewsPageVerification rm = getJazzPageFactory().getRMReviewsPageVerification();
    assertNotNull(rm);
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("name", "test");
    params.put("participant", "Phung Trong Van (RBVH/EET23)");
    params.put("participant_type", "Reviewer");
    params.put("artifact_id", "1111");
    rm.verifyIsReviewDetailsPageOpened(params, "false");
  }
}
