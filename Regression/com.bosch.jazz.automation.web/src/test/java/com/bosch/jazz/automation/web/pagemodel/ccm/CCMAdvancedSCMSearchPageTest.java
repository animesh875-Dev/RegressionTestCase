/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.ccm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.remote.DriverCommand;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Represents the advanced scm search page in RTC.
 */
public class CCMAdvancedSCMSearchPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads an change and configuration management advanced search page and gets the artifacts.
   */
  @Test
  public void testGetListOfArtifactss() {
    loadPage("ccm/get_list_of_artifacts.html");
    CCMAdvancedSCMSearchPage search = getJazzPageFactory().getCCMAdvancedSCMSearchPage();
    assertNotNull(search);
    search.getListOfArtifacts();
    List<String> plans = Arrays.asList("StreamTest sample1", "TestStream55236987", "TestStreamBiswajit",
        "TestFifteenComponentsStream_Formal", "TestValidityStream_Formal", "TestStreamReviewFormal_2018.2.0");
    assertEquals(plans, search.getListOfArtifacts());
  }

  /**
   * Loads an change and configuration management advanced search page and gets the artifacts.
   */
  @Test
  public void testGetListOfArtifactssTwo() {
    loadPage("ccm/select_atrifact_type_and_search_anrtifacts.html");
    CCMAdvancedSCMSearchPage search = getJazzPageFactory().getCCMAdvancedSCMSearchPage();
    assertNotNull(search);
    search.getListOfArtifacts();

    System.out.println(search.getListOfArtifacts());
    List<String> plans = Arrays.asList("StreamTest sample1", "TestStream55236987", "TestStreamBiswajit",
        "TestFifteenComponentsStream_Formal", "TestValidityStream_Formal", "TestStreamReviewFormal_2018.2.0");
    assertNotEquals(plans, search.getListOfArtifacts());
  }

  /**
   * Loads an change and configuration management advanced search page and gets select the artifacts type and search
   * artifact.(note )
   */
  @Test
  public void testSelectAtrifactTypeAndSearchArtifact() {
    loadPage("ccm/select_atrifact_type_and_search_anrtifacts.html");
    CCMAdvancedSCMSearchPage search = getJazzPageFactory().getCCMAdvancedSCMSearchPage();
    assertNotNull(search);
    search.selectArtifactTypeAndSearchArtifact("Streams", "test");
  }

  /**
   * Loads an change and configuration management advanced search page and gets select the artifacts type and search
   * artifact.(note )
   */
  @Test
  public void openTest() {
    loadPage("ccm");
    CCMAdvancedSCMSearchPage search = getJazzPageFactory().getCCMAdvancedSCMSearchPage();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    Map<String, String> additionalparam = new LinkedHashMap<>();
    clickToPage.put(1, "ccm/open_advance_search.html");
    assertNotNull(search);
    loadNewPageOnNthCommandCall(DriverCommand.GET, clickToPage);
    search.open(driver.getCurrentUrl(), "auth", additionalparam);
  }
}
