/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.DateUtil;

/**
 * Unit test coverage for the methods of RMReviewsPage.
 */
public class RMReviewsPageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test to verify {@link RMReviewsPage#browseReviews(String)}.
   */
  @Test
  public void testBrowserviews() {
    loadPage("dng/browse_reviews.html");
    RMReviewsPage rm = getJazzPageFactory().getRMReviewsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/browse_reviews.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.browseReviews("All reviews");
  }

  /**
   * <p>
   * Unit test to verify {@link RMReviewsPage#deleteReview()}.
   */
  @Test
  public void testDeleteReview() {
    loadPage("dng/delete_review.html");
    RMReviewsPage rm = getJazzPageFactory().getRMReviewsPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/delete_review_button.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.deleteReview();
  }

  /**
   * <p>
   * Unit test to verify {@link RMReviewsPage#createReview(String[], Map)}.
   */
  @Test
  public void testCreateReview() {
    loadPage("dng/create_review.html");
    RMReviewsPage rm = getJazzPageFactory().getRMReviewsPage();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("PARTICIPANTS_NAME", "EXTERNAL Muthyalappa Vaddi");
    additionalParams.put("PROJECT_NAME", "Component_11");
    assertNotNull(rm);
    String name[] = { "Test_Review" + DateUtil.getCurrentDateAndTime(), "Reviews" };
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, "dng/create_review_participant.html");
    clickNumberToPagePath.put(4, "dng/create_review_participant_window.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    clickNumberToPagePath.put(7, "dng/create_review_participant.html");
    clickNumberToPagePath.put(8, "dng/addArtifacts_inReview.html");
    clickNumberToPagePath.put(9, null);
    clickNumberToPagePath.put(10, null);
    clickNumberToPagePath.put(11, "dng/create_review_save.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.createReview(name, additionalParams);
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPage#selectReview(String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testSelectReview() {
    loadPage("dng/selectView.html");
    RMReviewsPage rm = getJazzPageFactory().getRMReviewsPage();
    assertNotNull(rm);
    rm.selectReview("test");
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPage#copyLinkFromShareLinkToReview()}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testCopyLinkFromShareLinkToReview() {
    loadPage("dng/verifySelectReview.html");
    RMReviewsPage rm = getJazzPageFactory().getRMReviewsPage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/copyLinkFromShareLinkToReview.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertNotNull(rm);
    rm.copyLinkFromShareLinkToReview();
  }

  /**
   * <p>
   * Unit test cover for ${@link RMReviewsPage#isReviewDetailsPageOpened(Map)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testIsReviewDetailsPageOpened() {
    loadPage("dng/verifySelectReview.html");
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("name", "Review for automatic testing TS_25905");
    params.put("participant", "Phung Trong Van (RBVH/EET23)");
    params.put("participant_type", "Reviewer");
    params.put("artifact_id", "1714843");
    RMReviewsPage rm = getJazzPageFactory().getRMReviewsPage();
    assertNotNull(rm);
    rm.isReviewDetailsPageOpened(params);
  }
}
