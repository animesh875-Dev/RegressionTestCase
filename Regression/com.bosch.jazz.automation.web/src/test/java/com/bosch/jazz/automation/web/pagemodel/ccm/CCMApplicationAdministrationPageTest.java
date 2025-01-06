/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests for the CCMApplicationAdministrationPage
 *
 * @author NEE2KOR
 */
public class CCMApplicationAdministrationPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads change and configuration management application administration Page and open the administration page with
   * repositoryURL and project area.
   */
  @Test
  public void testOpen() {
    loadPage("ccm/application_administration_page_open.html");
    CCMApplicationAdministrationPage administration = getJazzPageFactory().getCCMApplicationAdministrationPage();
    assertNotNull(administration);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/ccm";
    String projectAreaName = "rao8kor_Test_formal_2018.2.0";
    Map<String, String> additionalParams = new HashMap<String, String>();
    additionalParams.put("", "");
    administration.open(repositoryURL, projectAreaName, additionalParams);

  }

  /**
   * Loads change and configuration management application administration Page and checks if clickOnTab() gets the
   * proper tabname.
   */
  @Test
  public void testClickOnTab() {
    loadPage("ccm/application_administration_page_clickOn_tab.html");
    CCMApplicationAdministrationPage administration = getJazzPageFactory().getCCMApplicationAdministrationPage();
    assertNotNull(administration);
    String tabname = "Roles";
    administration.clickOnTab(tabname);
  }

  /**
   * Loads change and configuration management application administration Page and checks if
   * getListOfPracticesInProcessDescriptionTab() gets list of practices from process description tab.
   */

  @Test
  public void testGetListOfPracticesInProcessDescriptionTab() {
    loadPage("ccm/application_administration_page_process_description_tab.html");
    CCMApplicationAdministrationPage administration = getJazzPageFactory().getCCMApplicationAdministrationPage();
    assertNotNull(administration);
    List<String> processDescription =
        Arrays.asList("Formal Project Management", "Release Structure", "Roles and Permissions", "Work Item Types",
            "Time Tracking", "Work Item Queries", "Plans", "Rules and Preconditions", "Reports", "Dashboards");
    assertEquals(processDescription, administration.getListOfPracticesInProcessDescriptionTab());
  }


}
