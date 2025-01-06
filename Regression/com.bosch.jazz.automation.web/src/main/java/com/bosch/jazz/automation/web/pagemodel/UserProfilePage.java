/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.Validate;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.utils.rest.JazzHttpUtils;

/**
 * The user profile page.
 */
public class UserProfilePage extends AbstractJazzWebPage {

  /**
   * {@link #USER_SAVED_SUCCESSFULLY}
   */
  public static final String USER_SAVED_SUCCESSFULLY = "User saved successfully.";
  /**
   * Parameter key for parameter holding USER_ID.
   */
  public static final String USER_ID = "USER_ID";

  /**
   * See {@link AbstractJazzWebPage#AbstractJazzWebPage(WebDriverCustom)}
   */
  public UserProfilePage(final WebDriverCustom driverCustom) {
    super(driverCustom);
  }

  /**
   * Opens the user profile page for the given User.
   * <p>
   * The parameter 'additionalParams' must hold a parameter with key {@link #USER_ID} that holds the user id whose
   * profile page shall be opened.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public void open(final String repositoryURL, final String projectAreaName,
      final Map<String, String> additionalParams) {
    waitForPageLoaded();
    Validate.notNull(additionalParams);
    String userId = additionalParams.get(USER_ID);
    if (StringUtils.isEmpty(userId)) {
      throw new IllegalStateException("Parameter holding user id must be given!");
    }
    String repoUrlNoTrailingSlash = JazzHttpUtils.removeTrailingSlash(repositoryURL);
    if (repoUrlNoTrailingSlash.toLowerCase().endsWith("rs")) {
      throw new IllegalStateException("JRS application does not provide any user profile page!");
    }
    this.driverCustom.openURL(repoUrlNoTrailingSlash.concat("/").concat("users/").concat(userId));
  }

  /**
   * Opens the user profile page for the given User.
   * <p>
   * The parameter 'additionalParams' must hold a parameter with key {@link #USER_ID} that holds the user id whose
   * profile page shall be opened.
   *
   * @param repositoryURL the repo Url, must not be null
   * @param userId the user id, must not be null
   */
  public void open(final String repositoryURL, final String userId) {
    waitForPageLoaded();
    Map<String, String> params = new HashMap<>();
    params.put(USER_ID, userId);
    open(repositoryURL, null, params);
  }

  /**
   * Clicks and opens the "Mail Configuration" tab on the user profile page. If the tab is already opened and this
   * method is invoked again nothing bad will happen.
   */
  public void openMailConfigurationTab() {
    waitForPageLoaded();
    this.driverCustom.clickOnLink("Mail Configuration", false);
  }

  /**
   * Opens the "Mail Configuration" tab and then reads out the setting for the html email formatting.
   *
   * @return true if the html formatting is enabled, false otherwise.
   */
  public boolean isHtmlFormatEnabled() {
    waitForPageLoaded();
    openMailConfigurationTab();
    WebElement emailFormatElement = this.driverCustom.getWebElement("_mailConfigFormatMailsInHtmlCheck", ByType.ID);
    LOGGER.LOG.info("Verified - html formatting is enabled - " + emailFormatElement.isSelected());
    return emailFormatElement.isSelected();
  }

  /**
   * Opens the "Mail Configuration" tab and enables or disables the html formatting of emails according to parameter and
   * then saves the profile.
   * <p>
   * ATTENTION: In order to set this configuration switch for other users the currently logged in user must be
   * JazzAdmin. If not this method will fail eventually since it internally waits for the success message
   * {@link #USER_SAVED_SUCCESSFULLY} to appear.
   *
   * @param enableHtml true to enable html formatted emails, false to use the default format defined by the application,
   *          typically text.
   * @return true if the setting has been changed=is now diffrent than before, false if the requested state was already
   *         available.
   */
  public boolean setEmailFormatToBeHTML(final String enableHtml) {
    boolean b = Boolean.parseBoolean(enableHtml);
    waitForPageLoaded();
    openMailConfigurationTab();
    WebElement emailFormatElement = this.driverCustom.getWebElement("_mailConfigFormatMailsInHtmlCheck", ByType.ID);
    boolean htmlIsEnabled = emailFormatElement.isSelected();
    if (htmlIsEnabled != b) {
      emailFormatElement.click();
      WebElement saveButton = this.driverCustom.getWebElement("//button[text()='Save']", ByType.XPATH);
      saveButton = this.driverCustom.getClickableWebElement(saveButton);
      saveButton.click();
      this.driverCustom.getWebElement("//div[text()='" + USER_SAVED_SUCCESSFULLY + "']", ByType.XPATH);
      LOGGER.LOG.info("Email formatting is set successfully to - " + enableHtml);
      return true;
    }
    return false;
  }

  /**
   * This method verify Username is correct or not
   *
   * @param nameValue of user account
   * @return true if user is correct, false is user is incorrect
   */
  public boolean verifyUserName(final String nameValue) {
    waitForPageLoaded();
    WebElement previewNameElement = null;
    try {
      previewNameElement = this.driverCustom.getWebElement(
          "//span[@dojoattachpoint='previewName' and contains(text(),'" + nameValue + "')]", ByType.XPATH);
      LOGGER.LOG.info(nameValue + " user is verified - showing");
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }
    return previewNameElement != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.JAZZPAGE_SPANBUTTONS_XPATH, "Project");
  }
}
