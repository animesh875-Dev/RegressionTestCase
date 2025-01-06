/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *  
 */
public class WorkItemEnumsTest {

  /**
   * 
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testSourceControlEnum() {
    assertTrue(WorkItemEnums.WorkItemType.TASK.toString().equals("Task"));
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testSourceControlsEnum() {
    assertTrue(WorkItemEnums.WorkItemType.TASK.equalsName("Task"));
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testWorkItemLinkActions() {
    assertTrue(WorkItemEnums.WorkItemLinkActions.ADD_PREDECESSOR.toString().equals("Add Predecessor"));
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testWorkItemLinksActions() {
    assertTrue(WorkItemEnums.WorkItemLinkActions.ADD_PREDECESSOR.equalsName("Add Predecessor"));
  }
}
