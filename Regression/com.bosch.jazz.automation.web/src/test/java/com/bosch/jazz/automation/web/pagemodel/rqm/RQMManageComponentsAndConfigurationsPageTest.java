/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests coverage for the RQMLabManagmentPage.
 */
public class RQMManageComponentsAndConfigurationsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Unit test for method {@link RQMManageComponentsAndConfigurationsPage#deleteBaseline(String)}
   * 
   * @author KYY1HC
   */
  @Test
  public void testDeleteBaseline() {
    loadPage("rqm/DeleteBaselines_01.html");
    RQMManageComponentsAndConfigurationsPage rqm = getJazzPageFactory().getRQMManageComponentsAndConfigurationsPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "rqm/DeleteBaselines_02.html");
    clickToPage.put(3, "rqm/DeleteBaselines_04.html");
    clickToPage.put(4, "rqm/DeleteBaselines_05.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.deleteBaseline("rbd_briBk10_pf_sw BL TEST 20210713");
  }
}
