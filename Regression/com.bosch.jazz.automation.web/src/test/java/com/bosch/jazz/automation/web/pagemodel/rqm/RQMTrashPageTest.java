/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests coverage for the RQMLabManagmentPage.
 */
public class RQMTrashPageTest extends AbstractFrameworkUnitTest {

  /**
   * Unit test for method {@link RQMTrashPage#isBaselineDisplayed(String)}
   * 
   * @author KYY1HC
   */
  @Test
  public void testIsBaselineDisplayed() {
    loadPage("rqm/IsBaselineDisplayed_01.html");
    RQMTrashPage rqm = getJazzPageFactory().getRQMTrashPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/IsBaselineDisplayed_02.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertTrue(rqm.isBaselineDisplayed("rbd_briBk10_pf_sw BL TEST 20210713"));
  }

  /**
   * Unit test for method {@link RQMTrashPage#restoreBaseline(String)}
   * 
   * @author KYY1HC
   */
  @Test
  public void testRestoreBaseline() {
    loadPage("rqm/RestoreBaseline_01.html");
    RQMTrashPage rqm = getJazzPageFactory().getRQMTrashPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, "rqm/RestoreBaseline_02.html");
    clickToPage.put(4, "rqm/RestoreBaseline_04.html");
    clickToPage.put(5, "rqm/RestoreBaseline_05.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.restoreBaseline("rbd_briBk10_pf_sw BL TEST 20210713");
  }

  /**
   * Unit test for method {@link RQMTrashPage#selectOptionInViewDropDown(String)}
   * 
   * @author KYY1HC
   */
  @Test
  public void testSelectOptionInViewDropDown() {
    loadPage("rqm/SelectOptionInViewDropDown_01.html");
    RQMTrashPage rqm = getJazzPageFactory().getRQMTrashPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/SelectOptionInViewDropDown_02.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.selectOptionInViewDropDown("Baseline");
  }

  /**
   * Unit test for method {@link RQMTrashPage#selectOptionInGroupByDropDown(String)}
   * 
   * @author KYY1HC
   */
  @Test
  public void testSelectOptionInGroupByDropDown() {
    loadPage("rqm/SelectOptionInViewDropDown_02.html");
    RQMTrashPage rqm = getJazzPageFactory().getRQMTrashPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/SelectOptionInGroupByDropDown_01.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.selectOptionInViewDropDown("Deleted By");
  }
}
