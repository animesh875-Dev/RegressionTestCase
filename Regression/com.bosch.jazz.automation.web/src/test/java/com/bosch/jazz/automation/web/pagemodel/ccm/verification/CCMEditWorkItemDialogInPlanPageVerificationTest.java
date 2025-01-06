/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMEditWorkItemDialogInPlanPageVerification;

/**
 * Loads a JazzLogin Page and verifies the methods.
 */
public class CCMEditWorkItemDialogInPlanPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link CCMEditWorkItemDialogInPlanPageVerification#verifySetDropDownAttributeValue(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetDropDownAttributeValue() {
    CCMEditWorkItemDialogInPlanPageVerification cqpv =
        getJazzPageFactory().getCCMEditWorkItemDialogInPlanPageVerification();
    loadPage("ccm/test_setvalue.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetDropDownAttributeValue("Priority", "Low", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMEditWorkItemDialogInPlanPageVerification#verifySetDropDownAttributeValue(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySetDropDownAttributeValue() {
    CCMEditWorkItemDialogInPlanPageVerification cqpv =
        getJazzPageFactory().getCCMEditWorkItemDialogInPlanPageVerification();
    loadPage("ccm/test_setvalue.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySetDropDownAttributeValue("Priority", "test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMEditWorkItemDialogInPlanPageVerification#verifyClickOnJazzButtons(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnJazzButtons() {
    CCMEditWorkItemDialogInPlanPageVerification cqpv =
        getJazzPageFactory().getCCMEditWorkItemDialogInPlanPageVerification();
    loadPage("ccm/test_setvalue.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnJazzButtons("Close", "TRUE", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMEditWorkItemDialogInPlanPageVerification#verifyClickOnJazzButtons(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnJazzButtons() {
    CCMEditWorkItemDialogInPlanPageVerification cqpv =
        getJazzPageFactory().getCCMEditWorkItemDialogInPlanPageVerification();
    loadPage("ccm/test_setvalue.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnJazzButtons("run", "TRUE", "");
  }
}
