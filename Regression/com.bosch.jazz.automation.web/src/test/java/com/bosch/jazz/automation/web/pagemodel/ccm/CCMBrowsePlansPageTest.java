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
 * @author NEE2KOR
 */
public class CCMBrowsePlansPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads change and configuration management Browse Plans Page and open the project area with project area.
   */

  @Test
  public void testOpen() {
    loadPage("ccm/browse_plans_page_open.html");
    CCMBrowsePlansPage plan = getJazzPageFactory().getCCMBrowsePlansPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    Map<String, String> additionalParams = new HashMap<String, String>();
    additionalParams.put("URL_SUFFIX", "");
    plan.open(repositoryURL, projectAreaName, additionalParams);
  }

  /**
   * Loads change and configuration management Browse Plans Page and check it shows "All Plans".
   */

  @Test
  public void testOpenAllPlans() {
    loadPage("ccm/browse_plans_page_open_all_plans.html");
    CCMBrowsePlansPage plan = getJazzPageFactory().getCCMBrowsePlansPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    plan.openAllPlans(repositoryURL, projectAreaName);
  }

  /**
   * Loads change and configuration management Browse Plans Page and check it shows "Current Plans".
   */

  @Test
  public void testOpenCurrentPlans() {
    loadPage("ccm/browse_plans_page_open_current_plans.html");
    CCMBrowsePlansPage plan = getJazzPageFactory().getCCMBrowsePlansPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    plan.openCurrentPlans(repositoryURL, projectAreaName);
  }

  /**
   * Loads change and configuration management Browse Plans Page and check it shows "MyCurrent Plans".
   */
  @Test
  public void testOpenMyCurrentPlans() {
    loadPage("ccm/browse_plans_page_open_my_current_plans.html");
    CCMBrowsePlansPage plan = getJazzPageFactory().getCCMBrowsePlansPage();
    assertNotNull(plan);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    plan.openMyCurrentPlans(repositoryURL, projectAreaName);
  }


}
