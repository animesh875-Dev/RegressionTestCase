/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 */
public class SourceControlEnumsTest {

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
    assertTrue(SourceControlEnums.STREAMS.equalsName("Streams"));
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testSourceControlsEnum() {
    PlansEnum planenum = new PlansEnum();
    assertNotNull(planenum);
    SourceControlEnums.from("Streams");
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testSourcesControlsEnum() {
    PlansEnum planenum = new PlansEnum();
    assertNotNull(planenum);
    thrown.expectMessage("Invalid/unsupported name given: data");
    SourceControlEnums.from("data");
  }
}
