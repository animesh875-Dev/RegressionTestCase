/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMWelcomeToWorkItemPageVerification;

/**
 * @author BBW1KOR
 */
public class CCMWelcomeToWorkItemPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link CCMWelcomeToWorkItemPageVerification#verifySearchWorkItem(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectWorkItemFromCreateWorkitemDialogToCreate() {
    CCMWelcomeToWorkItemPageVerification cqpv = getJazzPageFactory().getCCMWelcomeToWorkItemPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySearchWorkItem("Change and Configuration", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMWelcomeToWorkItemPageVerification#verifySearchWorkItem(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySelectWorkItemFromCreateWorkitemDialogToCreate() {
    CCMWelcomeToWorkItemPageVerification cqpv = getJazzPageFactory().getCCMWelcomeToWorkItemPageVerification();
    loadPage("ccm/workitem_editor_attribute_value.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySearchWorkItem("1234", "");
  }
}
