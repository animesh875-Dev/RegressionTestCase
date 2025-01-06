/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.gc.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.gc.BrowseComponentsPageVerification;

/**
 * this page represents BrowseComponents Page Verification.
 */
public class BrowseComponentsPageVerificationTest extends AbstractFrameworkUnitTest {


  /**
   * <p>Unit test coverage for {@link BrowseComponentsPageVerification#verifyClickOnCreateComponentButton(String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyClickOnCreateComponentButton() {
    loadPage("gc/verify_click_on_create_component_button.html");
    BrowseComponentsPageVerification rqm = getJazzPageFactory().getBrowseComponentsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnCreateComponentButton("");
  }

  /**
   * <p>Unit test coverage for {@link BrowseComponentsPageVerification#verifyClickOnCreateComponentButton(String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyInvalidClickOnCreateComponentButton() {
    loadPage("gc/verify_open_action_menu.html");
    BrowseComponentsPageVerification rqm = getJazzPageFactory().getBrowseComponentsPageVerification();
    assertNotNull(rqm);
    rqm.verifyClickOnCreateComponentButton("");
  }

  /**
   * <p>Unit test coverage for {@link BrowseComponentsPageVerification#verifyCreateComponent(String,String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyCreateComponent() {
    loadPage("gc/verify_click_on_create_component_button.html");
    BrowseComponentsPageVerification rqm = getJazzPageFactory().getBrowseComponentsPageVerification();
    assertNotNull(rqm);
    rqm.verifyCreateComponent("Component_Test_15_09_2020_20_09_909", "", "", "");
  }

  /**
   * <p>Unit test coverage for {@link BrowseComponentsPageVerification#verifyCreateComponent(String,String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void tesVerifyInvalidCreateComponent() {
    loadPage("gc/verify_open_action_menu.html");
    BrowseComponentsPageVerification rqm = getJazzPageFactory().getBrowseComponentsPageVerification();
    assertNotNull(rqm);
    rqm.verifyCreateComponent("", "", "", "");
  }
}
