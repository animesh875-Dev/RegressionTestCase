/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification.dng;

import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMManageComponentPropertiesPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMReviewsPage;
import com.bosch.jazz.automation.web.pagemodel.verification.AbstractWebPageVerification;
import com.bosch.psec.web.test.finder.text.LinkFinder;

import finder.container.dialog.JazzDialogFinder;

/**
 * This page verifies the methods of {@link RMManageComponentPropertiesPage}
 */
public class RMReviewsPageVerification extends AbstractWebPageVerification {


  /**
   * @param driverCustom WebDriver
   */

  public RMReviewsPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDialogFinder(), new LinkFinder());
  }

  /**
   * <p>
   * Verify the target review is seleceted and opened successfully in the new tab. <br>
   * <p>
   *
   * @author NVV1HC
   * @param reviewName name of review is selected and opened in the new tab
   * @param lastResult result from ${@link RMReviewsPage#selectReview(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifySelectReview(final String reviewName, final String lastResult) {
    String message =
        "Reviews open in a new tab or window, and might open in a different stream or baseline. When you finish with a review, close this tab or window.";
    String actualReviewNameDisplayed = this.driverCustom.getText(RMConstants.REVIEWPAGE_RESOURCETITLE_XPATH);
    String actualMessageDisplayed =
        this.driverCustom.getText(RMConstants.RMARTIFACTPAGE_MODULEARTIFACTMASSAGE_BOX_XPATH);
    if (actualReviewNameDisplayed.trim().equals(reviewName) &
        actualMessageDisplayed.trim().equals(actualMessageDisplayed)) {
      return new TestAcceptanceMessage(true, "Verified review is selected and opened in the new tab: PASSED!");
    }
    return new TestAcceptanceMessage(true,
        "Verified review is selected and opened in the new tab: FAILED!\nExpected review is displayed: '" + reviewName +
        "'.\nActual review is displayed: '" + actualReviewNameDisplayed + "'.\nExpected message is displayed: '" +
        message + "'.\nActual message is displayed: '" + actualMessageDisplayed + "'");
  }

  /**
   * <p>
   * Verify copy the a share link from Review page details is successfully or not after
   * ${@link RMReviewsPage#copyLinkFromShareLinkToReview()}
   * <p>
   *
   * @author NVV1HC
   * @param lastResult result from ${@link RMReviewsPage#copyLinkFromShareLinkToReview()}
   * @param additionalParam_RM_URL URL of RM server
   * @return verification message
   */
  public TestAcceptanceMessage verifyCopyLinkFromShareLinkToReview(final String additionalParam_RM_URL,
      final String lastResult) {
    waitForSecs(3);
    if (lastResult.contains(additionalParam_RM_URL + "reviews/")) {
      return new TestAcceptanceMessage(true, "Verified copy link from Share Link To Review option successfully!");
    }
    return new TestAcceptanceMessage(false,
        "Verified copy link from Share Link To Review option failed!\nExpected share link must contains:'" +
            additionalParam_RM_URL + "reviews/'\nActual share link displayed: '" + lastResult + "'");
  }

  /**
   * <p>
   * Verify open a review successfully or not after ${@link RMReviewsPage#isReviewDetailsPageOpened(Map)}
   * <p>
   *
   * @author NVV1HC
   * @param params stores values as: {name:<value>}: name of review {participant:<value>}: name of participant
   *          {participant_type:<value>}: type of participant {artifact_id:<value>}: ID of artifact displayed in the
   *          review
   * @param lastResult result from ${@link RMReviewsPage#isReviewDetailsPageOpened(Map)}
   * @return Verification message
   */
  public TestAcceptanceMessage verifyIsReviewDetailsPageOpened(final Map<String, String> params,
      final String lastResult) {
    if (lastResult.equalsIgnoreCase("true")) {
      return new TestAcceptanceMessage(true,
          "Review is opened successfully: \nName: '" + params.get("name") + "'\nParticipant: '" +
              params.get("participant") + "'\nParticipant type: '" + params.get("participant_type") +
              "'\nArtifact ID: '" + params.get("artifact_id") + "'");
    }
    return new TestAcceptanceMessage(false,
        "Review should be opened with below information: \nName: '" + params.get("name") + "'\nParticipant: '" +
            params.get("participant") + "'\nParticipant type: '" + params.get("participant_type") +
            "'\nArtifact ID: '" + params.get("artifact_id") + "'.\nBut the opening is failed!");
  }

  /**
   * <p>
   * Verify browse to a review type is successfully after ${@link RMReviewsPage#browseReviews(String)}
   * <p>
   *
   * @author NVV1HC
   * @param reviewType type to filter review: All reviews, My open reviews, All active reviews
   * @param lastResult result from ${@link RMReviewsPage#browseReviews(String)}
   * @return verification message
   */
  public TestAcceptanceMessage verifyBrowseReviews(final String reviewType, final String lastResult) {
    waitForSecs(3);
    String option = this.driverCustom.getText("//div[@class='filterSelect']//span[@role='option']");
    if (reviewType.equals(option.trim())) {
      return new TestAcceptanceMessage(true, "Verified select option '" + reviewType + "' successfully!");
    }
    return new TestAcceptanceMessage(false, "Verified select option '" + reviewType +
        "' failed!\nExpected option is selected: '" + reviewType + "'\nActual option is selected: '" + option + "'");
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_LINKS_XPATH, RMConstants.ARTIFACTS);
  }
}
