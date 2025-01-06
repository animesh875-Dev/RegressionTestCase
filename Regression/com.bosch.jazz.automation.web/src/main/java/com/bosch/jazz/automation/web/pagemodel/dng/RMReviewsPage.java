/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.AbstractRMPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.container.Dialog;
import com.bosch.psec.web.test.element.text.Text;
import com.bosch.psec.web.test.finder.text.TextFinder;

import finder.container.dialog.JazzDialogFinder;
import finder.dropdown.JazzDropdownFinder;
import finder.text.JazzTextFinder;

/**
 * Represents the Requirements Management Reviews Page.
 */
public class RMReviewsPage extends AbstractRMPage {


  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom required for interacting with the browser.
   */
  public RMReviewsPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzDropdownFinder(), new JazzTextFinder(), new JazzDialogFinder(), new TextFinder());
  }

  /**
   * <p>
   * Open Reviews menu {@link RMDashBoardPage#openMenu(String)}, all the reviews is displayed. <br>
   * Click on 'Create' button present left side top of the Reviews page, 'New Review' dialog is displayed.<br>
   * Provide Name, Description for the review and Click on 'OK' button, created review page is displayed.<br>
   * Click on 'Add Participants' button, 'Select User' dialog is displayed.<br>
   * Search Participants Name and Click on 'Add and Close' button.<br>
   * Click on 'Add Artifacts' button, 'Select Artifacts' dialog is displayed.<br>
   * Search and Select the artifact and Click on 'Add and Close' button.<br>
   * Click on 'Save Review' button.
   *
   * @param reviewName name of review
   * @param additionalParams data.
   */
  public void createReview(final String[] reviewName, final Map<String, String> additionalParams) {
    waitForPageLoaded();
    this.driverCustom.click(CCMConstants.JAZZADMIN_SPANSELECTION_XPATH, "Create");
    this.driverCustom.typeText(RMConstants.RMREVIEWSPAGE_ENTERREVIEW_NAME_XPATH, reviewName[0]);
    String parent = this.driverCustom.getWebDriver().getWindowHandle();
    this.driverCustom.click(RMConstants.RMREVIEWSPAGE_CLICKOK_BUTTON_XPATH);
    this.driverCustom.isElementInvisible(RMConstants.RMREVIEWSPAGE_CLICKOK_BUTTON_XPATH, Duration.ofSeconds(15));
    waitForPageLoaded();
    Set<String> allWindowHandles = this.driverCustom.getWebDriver().getWindowHandles();
    Iterator<String> window = allWindowHandles.iterator();
    while (window.hasNext()) {
      String childwindow = window.next();
      if (!parent.equals(childwindow)) {
        this.driverCustom.getWebDriver().switchTo().window(childwindow);
      }
    }
    this.driverCustom.isElementPresent(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, Duration.ofSeconds(10), "Add Participants");
    this.driverCustom.click(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Add Participants");

    this.driverCustom.typeText("//input[@dojoattachpoint='searchText']", additionalParams.get("PARTICIPANTS_NAME"));

    this.driverCustom.getClickableWebElement(this.driverCustom.getPresenceOfWebElement(RMConstants.SELECT_USER));
    this.driverCustom.click(RMConstants.SELECT_USER);

    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.ADD_AND_CLOSE));
    this.driverCustom.click(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.ADD_AND_CLOSE);

    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Add Artifacts"));
    this.driverCustom.click(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Add Artifacts");

    Dialog dlgSelectUser = this.engine.findElement(Criteria.isDialog().withTitle("Select Artifacts")).getFirstElement();
    waitForPageLoaded();

    // Search module
    Text txtSearch = this.engine.findFirstElement(
        Criteria.isTextField().withPlaceHolder("Type to filter artifacts by text or by ID").inContainer(dlgSelectUser));
    txtSearch.setText(additionalParams.get("PROJECT_NAME") + Keys.ENTER);
    waitForSecs(10);

    List<WebElement> artifactList = this.driverCustom.getWebElements(RMConstants.RM_TEMPLATEPAGE_SELECTART_LIST_XPATH);
    if ((artifactList != null)) {
      artifactList.get(5).click();
    }
    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.ADD_AND_CLOSE));
    this.driverCustom.click(RMConstants.JAZZPAGE_BUTTONS_XPATH, RMConstants.ADD_AND_CLOSE);
    waitForPageLoaded();
    this.driverCustom.getClickableWebElement(
        this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Save Review"));
    this.driverCustom.click(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Save Review");
    refresh();
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Open Reviews menu {@link RMDashBoardPage#openMenu(String)}, all the reviews is displayed. <br>
   * Click on any of the review you want to delete, review is opened. <br>
   * Click on 'Delete' icon present right side top of the Review page, 'Confirm Deletion' dialog is displayed.<br>
   * Click on 'Delete Review' button.
   */
  public void deleteReview() {
    waitForPageLoaded();
    this.driverCustom.click(RMConstants.RMREVIEWSPAGE_DELETETHE_REVIEW_XPATH);
    clickOnJazzButtons("Delete Review");
  }

  /**
   * <p>
   * Open Reviews menu {@link RMDashBoardPage#openMenu(String)}, all the reviews is displayed. <br>
   * Click on drop down present for browsing the reviews.<br>
   * Select the review type from the drop down options.
   *
   * @param reviewType is Review type
   */
  public void browseReviews(final String reviewType) {
    waitForPageLoaded();
    this.driverCustom.click(RMConstants.RMREVIEWSPAGE_BROWSERREVIEW_DROPDOWNBUTTON_XPATH);
    WebElement ele = this.driverCustom.getWebElement(RMConstants.RMREVIEWSPAGE_SELECT_TYPE_XPATH, reviewType);
    ((JavascriptExecutor) this.driverCustom.getWebDriver()).executeScript("arguments[0].click()", ele);

  }

  /**
   * WaitForPageLoaded method is use to wait for a element which is common in this current page.
   */
  @Override
  public void waitForPageLoaded() {

    this.driverCustom.getPresenceOfWebElement(RMConstants.RMREVIEWSPAGE_BROWSERREVIEW_DROPDOWNBUTTON_XPATH);


  }

  /**
   * <p>
   * after opening the Reviews menu by using {@link RMDashBoardPage#openMenu(String)}, try to select the target view to
   * open <p
   *
   * @author NVV1HC
   * @param reviewName Name of the review to be selected
   */
  public void selectReview(final String reviewName) {
    waitForPageLoaded();
    try {
      this.driverCustom.click(RMConstants.REVIEWPAGE_REVIEW_XPATH, reviewName);
      waitForSecs(3);
    }
    catch (Exception e) {
      while (this.driverCustom.isElementVisible(RMConstants.REVIEWPAGE_DISABLEDNEXT_BUTTON_XPATH,
          Duration.ofSeconds((this.timeInSecs.getSeconds() / 5)))) {
        this.driverCustom.click(RMConstants.REVIEWPAGE_NEXT_BUTTON_XPATH);
        waitForSecs(3);
        try {
          this.driverCustom
          .click(RMConstants.REVIEWPAGE_REVIEW_XPATH, reviewName);
          waitForSecs(3);
          break;
        }
        catch (Exception e1) {
          LOGGER.LOG.error("This page does not contains the review '" + reviewName + "'");
        }
      }
    }
    try {
      switchTowindowTab();
    }
    catch (Exception e2) {
      fail("The review '" + reviewName + "' is not opened in the new tab!");
    }
  }

  /**
   * <p>
   * Coppy the share link of the review, then we can open the new tab or window then paste the share link the go to the
   * same page with the current review page
   * <p>
   *
   * @author NVV1HC
   * @return the share link of review
   */
  public String copyLinkFromShareLinkToReview() {
    waitForSecs(3);
    this.driverCustom.click(RMConstants.REVIEWPAGE_SHARELINKTOREVIEW_BUTTON_XPATH);
    waitForSecs(2);
    return this.driverCustom.getText(RMConstants.REVIEWPAGE_SHARELINKTOREVIEW_TEXT_XPATH);
  }

  /**
   * After a review is opened by ${@link RMReviewsPage#selectReview(String)} then verify this review is opened
   * successfully or not
   *
   * @param params stores values as: {name:<value>}: name of review {participant:<value>}: name of participant
   *          {participant_type:<value>}: type of participant {artifact_id:<value>}: ID of artifact displayed in the
   *          review
   * @return true if the review is opened successfully or vice versa
   */
  public boolean isReviewDetailsPageOpened(final Map<String, String> params) {
    String reviewName = params.get("name");
    String participant = params.get("participant");
    String participant_type = params.get("participant_type");
    String artifactID = params.get("artifact_id");
    String[] participantArr = { participant, participant_type };

    waitForSecs(3);
    String resourceTitle = "";
    try {
      resourceTitle = this.driverCustom.getText(RMConstants.REVIEWPAGE_RESOURCETITLE_XPATH);
    }
    catch (Exception e) {}
    if (reviewName.equals(resourceTitle.trim())) {
      LOGGER.LOG.info("Review '" + reviewName + "' is opened successfully!");
      boolean result = true;

      if (null != participant) {
        result &= this.driverCustom.isElementVisible(RMConstants.REVIEWPAGE_PARTICIPANTANDTYPE_XPATH, this.timeInSecs,
            participantArr);
        if (!result) {
          LOGGER.LOG.error(
              "Participant '" + participant + "' with type '" + participant_type +
              "' is not displayed as expectation!");
        }
      }
      if (null != artifactID) {
        result &= this.driverCustom.isElementVisible(RMConstants.REVIEWPAGE_ARTIFACTDISPLAYED_XPATH, this.timeInSecs,
            artifactID);
        if (!this.driverCustom.isElementVisible(RMConstants.REVIEWPAGE_ARTIFACTDISPLAYED_XPATH, this.timeInSecs,
            artifactID)) {
          LOGGER.LOG.error("Artifact with ID '" + artifactID + "' is not displayed as expectation!");
        }
      }
      return result;
    }
    fail("Review '" + reviewName + "' is not opened successfully!");
    return false;
  }
}
