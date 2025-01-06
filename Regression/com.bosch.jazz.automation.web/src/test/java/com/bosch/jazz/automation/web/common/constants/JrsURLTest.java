/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.JrsURL;

/**
 * Unit test for the JrsURL.
 *
 * @author gem5kor
 */
public class JrsURLTest extends AbstractFrameworkUnitTest {

  /**
   * This method is used to test the equalsName of ENUM
   */
  @Test
  public void equalsNameTest() {
    assertTrue(JrsURL.JRS_ALL_REPORTS_URL.equalsName(JrsURL.JRS_ALL_REPORTS_URL.toString()));
  }

  /**
   * This method is used to test the toString of ENUM
   */
  @Test
  public void toStringTest() {
    assertEquals("https://rb-alm-02-d.de.bosch.com/rs", JrsURL.JRS_URL.toString());
  }
}
