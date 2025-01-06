/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMMainMenus;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.RQMSectionMenus;

/**
 * Unit tests coverage for the RQMMainMenusEnum.
 */
public class RQMMainMenusEnumTest extends AbstractFrameworkUnitTest {

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testRQMMainMenus() {
    RQMMainMenusEnum rqmenum = new RQMMainMenusEnum();
    assertNotNull(rqmenum);
    RQMMainMenus.PROJECT_DASHBOARDS.toString();
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testRQMMainMenu() {
    RQMMainMenusEnum rqmenum = new RQMMainMenusEnum();
    assertNotNull(rqmenum);
    RQMMainMenus.PROJECT_DASHBOARDS.equalsName("Project Dashboards");
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testRQMSectionMenus() {
    RQMMainMenusEnum rqmenum = new RQMMainMenusEnum();
    assertNotNull(rqmenum);
    RQMSectionMenus.ATTACHMENTS.toString();
  }

  /**
   * Rqmmainmenus to cover the enum using tostring().
   */
  @Test
  public void testRQMSectionMenu() {
    RQMMainMenusEnum rqmenum = new RQMMainMenusEnum();
    assertNotNull(rqmenum);
    RQMSectionMenus.ATTACHMENTS.equalsName("Attachments");
  }

}
