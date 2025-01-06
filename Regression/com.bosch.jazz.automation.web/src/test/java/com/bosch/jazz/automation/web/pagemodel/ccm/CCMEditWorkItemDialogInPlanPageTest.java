/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * @author UUM4KOR
 */
public class CCMEditWorkItemDialogInPlanPageTest extends AbstractFrameworkUnitTest {


  /**
   * Loads an Overview page of an work item and checks if setDropDownAttributeValue() gets the proper Attribute Value
   */
  @Test
  public void testSetDropDownAttributeValue() {
    loadPage("ccm/test_setvalue.html");
    CCMEditWorkItemDialogInPlanPage wi = getJazzPageFactory().getCCMEditWorkItemDialogInPlanPage();
    assertNotNull(wi);
    wi.setDropDownAttributeValue("Priority", "Low");

  }

  /**
   * Loads an Overview page of an Epic and checks if setDropDownAttributeValue() gets the proper Attribute Value
   */
  @Test
  public void testSetDropDownAttributeValueOne() {
    loadPage("ccm/test_dropdownvalue_filterable.html");
    CCMEditWorkItemDialogInPlanPage wi = getJazzPageFactory().getCCMEditWorkItemDialogInPlanPage();
    assertNotNull(wi);
    wi.setDropDownAttributeValue("Owned By", "CDG ALM Exchange system-user-CC (CAP-SST/ESM3)");

  }

  /**
   * Loads an Overview page of an Epic and checks if setDropDownAttributeValue() gets the proper Attribute Value
   */
  @Test
  public void testSetDropDownAttributeValueTwo() {
    loadPage("ccm/test_dropdownvalue_filterable.html");
    CCMEditWorkItemDialogInPlanPage wi = getJazzPageFactory().getCCMEditWorkItemDialogInPlanPage();
    assertNotNull(wi);

    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "ccm/test_dropdownvalue_filterable_six.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);

    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    wi.setDropDownAttributeValue("Owned By", "AE-BE-Tool Support (AE-BE/EPT2-St)");
  }


}
