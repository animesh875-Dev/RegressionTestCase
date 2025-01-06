/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * @author BBW1KOR
 */
public class JazzLoginPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link JazzLoginPageVerification#verifyLoginWithGivenPassword(String, String, String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyLoginWithGivenPassword() {
    JazzLoginPageVerification jlv = getJazzPageFactory().getJazzLoginPageVerification();
    loadPage("ccm/login_page_verification.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "ccm/login_page_verification_dropdown.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(jlv);
    jlv.verifyLoginWithGivenPassword("dyc9si", "", "", "");
  }

  /**
   * <p>Unit test coverage for {@link JazzLoginPageVerification#verifyLoginWithGivenPassword(String, String, String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyLoginWithGivenPassword() {
    JazzLoginPageVerification jlv = getJazzPageFactory().getJazzLoginPageVerification();
    loadPage("ccm/login_page_verification.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "ccm/login_page_verification_dropdown.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(jlv);
    jlv.verifyLoginWithGivenPassword("dyc9si", "", "");
  }
}
