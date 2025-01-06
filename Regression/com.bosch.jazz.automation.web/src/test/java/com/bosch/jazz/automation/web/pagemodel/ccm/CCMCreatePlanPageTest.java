/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests for the CCMCreatePlanPage
 */
public class CCMCreatePlanPageTest extends AbstractFrameworkUnitTest {

  /**
   * Load an overview page of Plan and check if GetValidationMessage() returns error message or not.
   */
  @Test
  public void testGetValidationMessage() {
    loadPage("ccm/save_plan_page.html");
    CCMCreatePlanPage wi = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(wi);
    assertEquals(
        "To complete the 'Save Plan' task, you need these permissions: 'You don't have permission to perform the following actions: Modify Plan (modify/plan)'ID CRJAZ6053E",
        wi.getValidationMessage());
  }

  /**
   * Load an overview page of an Plan and check if isPlanSaved() returns true or not.
   */
  @Test
  public void testIsPlanSaved() {
    loadPage("ccm/saved_plan_page_com.ibm.team.apt.web.ui.plannedItems.html");
    CCMCreatePlanPage wi = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(wi);
    assertTrue(wi.isPlanSaved());
  }

  /**
   * Load an overview page of Plan and check if setPlanType() set null and returns error message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetplanTypeTwo() {
    loadPage("ccm/createplan_set_plantype.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    String s = null;
    plan.setPlanType(s);
  }

  /**
   * Load an overview page of Plan and check if setPlanType() set empty and returns error message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetplanTypeThree() {
    loadPage("ccm/createplan_set_plantype.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    String s = "";
    plan.setPlanType(s);
  }

  /**
   * Load an overview page of Plan and check if setPlanType() sets plan type.
   */

  @Test
  public void testSetplanType() {
    loadPage("ccm/createplan_set_plantype.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    String s = "Phase Plan";
    plan.setPlanType(s);
  }

  /**
   * Load an overview page of Plan and check if setPlanName() set plan name.
   */

  @Test
  public void testSetplanName() {
    loadPage("ccm/createplan_set_plan_name.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    String PLAN_NAME = "PLAN_NAME";
    plan.setPlanName(PLAN_NAME);
  }


  /**
   * Load an overview page of Plan and check if setPlanName() set null and returns error message.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetplanNameTwo() {
    loadPage("ccm/createplan_set_plan_name.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    String s = null;
    plan.setPlanName(s);
  }

  /**
   * Load an overview page of Plan and check if setPlanName() set empty and returns error message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetplanNameThree() {
    loadPage("ccm/createplan_set_plan_name.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    String s = "";
    plan.setPlanName(s);
  }

  /**
   * Load an overview page of an Plan and check if save() save the plan.
   */
  @Test
  public void testSave() {
    loadPage("ccm/createplan_save.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    plan.save();
  }

  /**
   * Load an overview page of an Plan and check if save() save the plan.
   */

  /**
   * Load an overview page of an Plan and check if open() opens the page for creating a new RTC plan.
   */

  @Test
  public void testOpen() {
    loadPage("ccm/createplan_open.html");

    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    Map<String, String> params = new HashMap<String, String>();
    params.put("#action=com.ibm.team.apt.createPlan", "__new_1");
    plan.open(repositoryURL, projectAreaName, params);
  }

  /**
   * Load an overview page of an Plan and check if setOwner() sets owner of the plan.
   */
  @Test
  public void testSetOwner() {
    loadPage("ccm/createplan_browse_button.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/createplan_filtertext.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/createplan_okbutton.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.setOwner("rao8kor_Test_formal_2018.2.0");
  }

  /**
   * Load an overview page of an Plan and check if setOwner() set null and returns error message..
   */

  @Test(expected = IllegalArgumentException.class)
  public void testSetOwnertwo() {
    loadPage("ccm/createplan_browse_button.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/createplan_filtertext.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/createplan_okbutton.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.setOwner(null);
  }

  /**
   * Load an overview page of an Plan and check if setOwner() set empty and returns error message..
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetOwnerThree() {
    loadPage("ccm/createplan_browse_button.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/createplan_filtertext.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/createplan_okbutton.html");
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.setOwner("");
  }


  /**
   * Load an overview page of an Plan and check if setIteration() set empty and returns error message..
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetIterationTwo() {
    loadPage("ccm/createplan_set_iteration_browse.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);

    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/createplan_set_iteration_ok.html");
    clickNumberToPagePath.put(2, "ccm/createplan_set_iteration_search.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.setIteration("");
  }

  /**
   * Load an overview page of an Plan and check if setIteration() set null and returns error message..
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetIterationTherr() {
    loadPage("ccm/createplan_set_iteration_browse.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);

    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/createplan_set_iteration_ok.html");
    clickNumberToPagePath.put(2, "ccm/createplan_set_iteration_search.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.setIteration(null);
  }


  /**
   * Load an overview page of an Plan and check if setIteration() sets Iteration of the plan.
   */
  @Test
  public void testsetIteration() {
    loadPage("ccm/creat_plan_browse_button.html");
    CCMCreatePlanPage plan = getJazzPageFactory().getCCMCreatePlanPage();
    assertNotNull(plan);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/creat_plan_filtertext.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "ccm/creat_plan_ok_button.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    plan.setIteration("Disel Car");
  }
}
