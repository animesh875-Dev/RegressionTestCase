/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm.verification;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.ccm.CCMPlanPageVerification;

/**
 * Loads a JazzLogin Page and verifies the methods.
 */
public class CCMPlanPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifySearchBox(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySearchBox() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/click_on_plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySearchBox("New Plan", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifySearchBox(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySearchBox() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/click_on_plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySearchBox("test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyClickOnPlanPhase(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnPlanPhase() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnPlanPhase("New Plan", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyClickOnPlanPhase(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnPlanPhase() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyClickOnPlanPhase("test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyViewAs(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyViewAs() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyViewAs("010 Roadmap", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyViewAs(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyViewAs() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyViewAs("test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyViewAs(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfYViewAs() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/click_on_plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyViewAs("test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyAddWorkItemInsidePlan(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddWorkItemInsidePlan() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyAddWorkItemInsidePlan("test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyAddWorkItemInsidePlan(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAdDWorkItemInsidePlan() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyAddWorkItemInsidePlan("test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyOpenDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyOpenDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/test_setvalue.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyOpenDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu("", "test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyOpenDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyOpenDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyOpenDropdownFromLeftSideActionButtonOfPlanItemAndSelectMenu("", "test", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyIsWorkItemDisplayedUnderPlanItem(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyIsWorkItemDisplayedUnderPlanItem() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/test_setvalue.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemDisplayedUnderPlanItem("test", "true");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyIsWorkItemDisplayedUnderPlanItem(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyIsWorkItemDisplayedUnderPlanItem() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyIsWorkItemDisplayedUnderPlanItem("test", "false");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifySelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_phase.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems("", "", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifySelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems(String,String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfySelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/projectarea_open_mainMenu_by_href.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifySelectWorkItemToCreateFromLeftSideActionButtonOfPlanItems("", "", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyFilterItems(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyFilterItems() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_typeToFilter.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyFilterItems("Work item Story created for Automation to Perform Workflow Transition", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyFilterItems(String,String)}.
   * 
   * <p>Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyFilterItems() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/plan_typeToFilter.html");
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(cqpv);
    cqpv.verifyFilterItems("Work item Story created for Automation to Perform Workflow Transition", "");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyInputDataForNewWorkItemRow(Map, String)}. <br>
   * @author VDY1HC
   */
  @Test
  public void testVerifyInputDataForNewWorkItemRow() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/verifyInputDataForNewWorkItemRow.html");
    assertNotNull(cqpv);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ATTRIBUTE_NAME", "Summary");
    additionalParams.put("ATTRIBUTE_VALUE", "test");
    cqpv.verifyInputDataForNewWorkItemRow(additionalParams, "test");
  }

  /**
   * <p>Unit test coverage for {@link CCMPlanPageVerification#verifyClickOnActionInPlanPhase(String, String, String)}. <br>
   * @author VDY1HC
   */
  @Test
  public void testVerifyClickOnActionInPlanPhase() {
    CCMPlanPageVerification cqpv = getJazzPageFactory().getCCMPlanPageVerification();
    loadPage("ccm/inputDataForNewWorkItemRow.html");
    assertNotNull(cqpv);
    cqpv.verifyClickOnActionInPlanPhase("A Work Item", "Create Work Item > Task", "");
  }
}
