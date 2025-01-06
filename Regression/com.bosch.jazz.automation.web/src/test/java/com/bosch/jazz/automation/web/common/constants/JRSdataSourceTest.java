/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.JRSdataSource;

/**
 * Unit test for the JRSdataSource.
 *
 * @author gem5kor
 */
public class JRSdataSourceTest extends AbstractFrameworkUnitTest {

  /**
   * This method is used to test the equalsName of ENUM
   */
  @Test
  public void equalsNameTest() {
    assertTrue(JRSdataSource.DWH.equalsName(JRSdataSource.DWH.toString()));
  }

  /**
   * This method is used to test the toString of ENUM
   */
  @Test
  public void toStringTest() {
    assertEquals("Rational Data Warehouse", JRSdataSource.DWH.toString());
  }
}
