/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.PlansEnum.PlanTypes;

/**
 * @author bbw1kor
 */
public class PlansEnumTest {

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testPlanTypes() {
    PlansEnum planenum = new PlansEnum();
    assertNotNull(planenum);
    PlanTypes.SPRINT_BACKLOG.equals("Sprint Backlog");
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testPlansTypes() {
    PlansEnum planenum = new PlansEnum();
    assertNotNull(planenum);
    PlanTypes.SPRINT_BACKLOG.equalsName("Sprint Backlog");
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testPlansType() {
    PlansEnum planenum = new PlansEnum();
    assertNotNull(planenum);
    assertEquals("Sprint Backlog", PlanTypes.SPRINT_BACKLOG.toString());
  }
}
