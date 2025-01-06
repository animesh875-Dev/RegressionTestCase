/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.Enums.Day;
import com.bosch.jazz.automation.web.pagemodel.Enums.widgetCategory;

/**
 * @author UUM4KOR passing the applications
 */
public class EnumsTest extends AbstractFrameworkUnitTest {

  /**
   * passing the applications
   */

  @Test
  public void testGetAbbreviation() {
    Enums enu = new Enums();
    assertNotNull(enu);
    widgetCategory.ARCHITECTUREMANAGEMENT.toString();
  }

  /**
   * passing the days
   */
  @Test

  public void testToString() {
    Enums enu = new Enums();
    assertNotNull(enu);
    Day.SATURDAY.toString();
  }
}
