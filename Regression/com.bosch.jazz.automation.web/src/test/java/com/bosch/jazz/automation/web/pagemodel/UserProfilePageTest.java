/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * The user profile page.
 */
public class UserProfilePageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads user profile page and checks if openMailConfigurationTab() gets open mail configuration tab. .
   */
  @Test
  public void testopenMailConfigurationTab() {
    loadPage("ccm/user_open_mail_configuration_tab.html");
    UserProfilePage user = getJazzPageFactory().getUserProfilePage();
    assertNotNull(user);
    user.openMailConfigurationTab();
  }

  /**
   * Loads user profile page and checks if isHtmlFormatEnabled() checks html formate enabled. .
   */
  @Test
  public void testIsHtmlFormatEnabled() {
    loadPage("ccm/user_profile_is_html_format_enabled_one.html");
    UserProfilePage user = getJazzPageFactory().getUserProfilePage();
    assertNotNull(user);
    assertTrue(user.isHtmlFormatEnabled());
  }

  /**
   * Loads user profile page and checks if setEmailFormatToBeHTML() set email formate to be html. .
   */
  @Test
  public void testSetEmailFormatToBeHTML() {
    loadPage("ccm/user_profile_is_html_format_enabled_one.html");
    UserProfilePage user = getJazzPageFactory().getUserProfilePage();
    assertNotNull(user);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/user_profile_is_html_format_enabled_two.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertFalse(user.setEmailFormatToBeHTML("true"));
  }

  /**
   * Loads user profile page and checks if setEmailFormatToBeHTML() set email formate to be html.
   */
  @Test
  public void testSetEmailFormatToBeHTMLConditionOne() {
    loadPage("ccm/user_profile_is_html_format_enabled_six.html");
    UserProfilePage user = getJazzPageFactory().getUserProfilePage();
    assertNotNull(user);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/user_profile_is_html_format_enabled_six.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    assertTrue(user.setEmailFormatToBeHTML("true"));
  }

  /**
   * Loads user profile page and checks if open() gets Opens the user profile page.
   */

  @Test
  public void testOpen() {
    loadPage("ccm/user_profile-open.html");
    UserProfilePage profile = getJazzPageFactory().getUserProfilePage();
    assertNotNull(profile);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String userId = "NEE2KOR";
    profile.open(repositoryURL, userId);


  }

  /**
   * Loads user profile page and checks if open() gets Opens the user profile page.
   */

  @Test
  public void testOpenOne() {
    loadPage("ccm/user_profile-open.html");
    UserProfilePage profile = getJazzPageFactory().getUserProfilePage();
    assertNotNull(profile);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    Map<String, String> params = new HashMap<String, String>();
    params.put("USER_ID", "NEE2KOR");
    String projectAreaName = "";
    profile.open(repositoryURL, projectAreaName, params);


  }

  /**
   * Loads user profile page and checks if open() setting the userId as empty and validate the exception message.
   */

  @Test(expected = IllegalStateException.class)
  public void testOpenTwo() {
    loadPage("ccm/user_profile-open.html");
    UserProfilePage profile = getJazzPageFactory().getUserProfilePage();
    assertNotNull(profile);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    Map<String, String> params = new HashMap<String, String>();
    params.put("USER_ID", "");
    String projectAreaName = "";
    profile.open(repositoryURL, projectAreaName, params);


  }
  
  /**
   * Verify displayed user name is correct or not
   */
  @Test
  public void testVerifyUserName() {
    loadPage("rqm/verify_User_Name.html");
    UserProfilePage user = getJazzPageFactory().getUserProfilePage();
    assertTrue(user.verifyUserName("CDG ALM Tester system-user-CC (CAP-SST/ESM3)"));
  }
}
