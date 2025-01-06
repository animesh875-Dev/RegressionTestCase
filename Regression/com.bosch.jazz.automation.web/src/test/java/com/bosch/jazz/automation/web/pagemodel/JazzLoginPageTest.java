/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests coverage for the JazzLoginPage.
 */
public class JazzLoginPageTest extends AbstractFrameworkUnitTest {

  /**
   * Open the browser and loginWithGivenPassword(final String username, final String decryptedPassword, final String
   * repositoryURL) will login to the mentioned url using user id and password.
   */
  @Test
  public void testLoginWithGivenPassword() {
    loadPage("ccm/login_with_given_password.html");
    JazzLoginPage ccm = getJazzPageFactory().getLoginPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "ccm/login_with_given_password.html");
    clickToPage.put(4, "ccm/login_with_given_password.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    ccm.loginWithGivenPassword("dgs9si", "RCM_test19", Paths.get(".").toUri() + SRC_TEST_RESOURCES + "ccm/");
    assertNotNull(ccm);
  }

  /**
   * Open the browser and loginWithGivenPassword(final String username, final String decryptedPassword, final String
   * repositoryURL) will login to the mentioned url using user id and encrypted password.
   */
  @Test
  public void testLogin() {
    loadPage("ccm/login_with_given_password.html");
    JazzLoginPage ccm = getJazzPageFactory().getLoginPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "ccm/login_with_given_password.html");
    clickToPage.put(4, "ccm/login_with_given_password.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    ccm.login("dgs9si", "CfBfm7N6h0jYEcxSIsa63A==", "src/test/resources/ccm/key.txt",
        Paths.get(".").toUri() + SRC_TEST_RESOURCES + "ccm/");
    assertNotNull(ccm);
  }
}
