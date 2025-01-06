/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebElement;

import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.excel.TestAcceptanceMessage;
import com.bosch.jazz.automation.web.pagemodel.JazzLoginPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMArtifactsPageVerification;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.text.Link;

/**
 * This page represents JazzLoginPage verification.
 */
public class JazzLoginPageVerification extends RMArtifactsPageVerification {


  /**
   * @param driverCustom the non-null custom web driver.
   */
  public JazzLoginPageVerification(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * <p>After Login clicked on user profile menu and iterates all name and id of user, if user name matched with ID or Name
   * then it will return true.
   * <p>Verifies the action {@link JazzLoginPage#loginWithGivenPassword(String, String, String)}.
   * 
   * @param username Name or ID of User.
   * @param decryptedPassword password.
   * @param repositoryURL url.
   * @param lastResult result.
   * @return if the filter box is available fails the verification because no filtering took place if the filter box is
   *         not available passes verification because filtering took place
   */
  public TestAcceptanceMessage verifyLoginWithGivenPassword(final String username, final String decryptedPassword,
      final String repositoryURL, final String lastResult) {
    String menu = CCMConstants.USER_PROFILE;
    //version 7.0.2
    if (driverCustom.getCurrUrl().contains("/rs/reports")) {
      driverCustom.getPresenceOfWebElement("//span[@id='jazzone-banner-user-name' or @id='userIdPlaceholder']").click();
      List<WebElement>ele=driverCustom.getWebElements("//*[contains(text(),'View Profile')]");
      for (WebElement elem : ele) {
        if (elem.isDisplayed()) {
          elem.click();
          break;
        }
      }
      this.driverCustom.waitForSecs(Duration.ofSeconds(60));
      driverCustom.getPresenceOfWebElement("//table[@class='content']//input");
      List<WebElement> userdetails = this.driverCustom.getWebElements("//table[@class='content']//input");
      for (WebElement details : userdetails) {
        String userName = details.getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
         if (details.getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(username)) {
          driverCustom.getWebDriver().navigate().to(repositoryURL);
          String result = "Verified user id with the user id present in the user profile . \nActual user id is " +
              '"' + userName + '"' + " and Expected user id is  " + username + "\".";
          return new TestAcceptanceMessage(true, result);
        }
      }
    }
    //version 7.0.3
    if (driverCustom.getCurrUrl().contains("/rs/web#/reports")) {
      if(this.driverCustom.isElementVisible("//body[@style='overflow: visible;']", Duration.ofSeconds(30))) {
        this.driverCustom.click("//div[text()='x']");
      }
      driverCustom.getPresenceOfWebElement("//button[@id='user']").click();
      List<WebElement>ele=driverCustom.getWebElements("//*[contains(text(),'Manage profile')]");
      for (WebElement elem : ele) {
        if (elem.isDisplayed()) {
          elem.click();
          break;
        }
      }
      this.driverCustom.waitForSecs(Duration.ofSeconds(60));
      driverCustom.getPresenceOfWebElement("//table[@class='content']//input");
      List<WebElement> userdetails = this.driverCustom.getWebElements("//table[@class='content']//input");
      for (WebElement details : userdetails) {
        String userName = details.getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE);
         if (details.getAttribute(CCMConstants.CCMCREATEQUERYPAGE_QUERY_VALUE).equalsIgnoreCase(username)) {
          driverCustom.getWebDriver().navigate().to(repositoryURL);
          String result = "Verified user id with the user id present in the user profile . \nActual user id is " +
              '"' + userName + '"' + " and Expected user id is  " + username + "\".";
          return new TestAcceptanceMessage(true, result);
        }
      }
    }
    try {
      Link userprofile = this.engine.findElement(Criteria.isLink().withToolTip(menu)).getFirstElement();
      userprofile.click();
    }
    catch (Exception e) {
      Optional<WebElement> matchingOptional =
          this.driverCustom.getWebElements("//a[starts-with(@class , 'jazz-ui-MenuPopup')]").stream()
              .filter(x -> x.getAttribute("title").startsWith(CCMConstants.USER_PROFILE)).findFirst();
      if (matchingOptional.isPresent()) {
        matchingOptional.get().click();
      }
      else {
        throw new WebAutomationException(CCMConstants.USER_PROFILE + "  or locater is invalid.");
      }
    }
    WebElement userID = this.driverCustom.getWebElement("//td[@class='label' and text()='User ID:']/parent::tr//td[2]");
    String actualID = userID.getText().trim();
    if(actualID.equalsIgnoreCase(username)) {
      String result = "Verified user id with the user id present in the user profile menu. \nActual user id is " +
          '"' + actualID + '"' + " and Expected user id is " + username + "\".";
      return new TestAcceptanceMessage(true, result);
    }
    List<WebElement> userdetails =
        this.driverCustom.getWebElements("//table[@class='com-ibm-team-jfs-user-preview']//td");
    for (WebElement details : userdetails) {
      if (details.getText().trim().equalsIgnoreCase(username)) {
        String result = "Verified user id with the user id present in the user profile menu. \nActual user id is " +
            '"' + details.getText() + '"' + " and Expected user id is " + username + "\".";
        return new TestAcceptanceMessage(true, result);
      }
    }
    return new TestAcceptanceMessage(false, "Actual user name not matched with expected user name.");
  }

  /**
   * <p>Verifies the action {@link JazzLoginPage#loginWithGivenPassword(String, String)}.
   * 
   * @param username name of user.
   * @param decryptedPassword password.
   * @param lastResult result.
   * @return if the filter box is available fails the verification because no filtering took place if the filter box is
   *         not available passes verification because filtering took place
   */
  public TestAcceptanceMessage verifyLoginWithGivenPassword(final String username, final String decryptedPassword,
      final String lastResult) {
    String menu = CCMConstants.USER_PROFILE;
    try {
      Link userProfile = this.engine.findElement(Criteria.isLink().withToolTip(menu)).getFirstElement();
      userProfile.click();
    }
    catch (Exception e) {
      Optional<WebElement> matchingOptIonal =
          this.driverCustom.getWebElements("//a[starts-with(@class , 'jazz-ui-MenuPopup')]").stream()
              .filter(x -> x.getAttribute("title").startsWith("User Profile")).findFirst();
      if (matchingOptIonal.isPresent()) {
        matchingOptIonal.get().click();
      }
      else {
        throw new WebAutomationException("User Profile" + "  or locater is invalid.");
      }
    }
    List<WebElement> userDetails =
        this.driverCustom.getWebElements("//table[@class='com-ibm-team-jfs-user-preview']//td");
    for (WebElement details : userDetails) {
      if (details.getText().trim().equalsIgnoreCase(username)) {
        String result = "Verified user id with the user id present in the user profile menu. \nActual user id is " +
            '"' + details.getText() + '"' + " and Expected user id is " + username + "\".";
        return new TestAcceptanceMessage(true, result);
      }
    }
    return new TestAcceptanceMessage(false, "Actual user name not matched with expected user name.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_MULTIPLEVALUE_CHECKBOX_XPATH, "User ID");
  }

}
