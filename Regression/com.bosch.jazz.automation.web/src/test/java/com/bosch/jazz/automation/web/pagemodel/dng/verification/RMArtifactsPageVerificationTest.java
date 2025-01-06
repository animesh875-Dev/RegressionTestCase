/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.Artifact;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.pagemodel.dng.RMArtifactsPage;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMArtifactsPageVerification;

/**
 * Unit tests for the RMArtifactsPageVerification.
 */
public class RMArtifactsPageVerificationTest extends AbstractFrameworkUnitTest {

  private static final WebDriver WebDriver = null;

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnArtifactTypes(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnArtifactTypes() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyClickOnArtifactTypes("Modules", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnArtifactTypes(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnArtifactTypes() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnArtifactTypes("Collections", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMAttributes(Map,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetRMAttributes() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("Description:", "Demo collections");
    assertNotNull(ravt);
    ravt.verifyGetRMAttributes(clickToPage, "KEY :Description: VALUE :Demo collections");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMAttributes(Map,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetRMAttributesOne() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("Description:", "Demo collections");
    assertNotNull(ravt);
    ravt.verifyGetRMAttributes(clickToPage, "KEY :Description: VALUE :test");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMAttributes(Map,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetRMAttributesTwo() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("Created On", "Apr 8, 2019, 10:32:25 AM");
    assertNotNull(ravt);
    ravt.verifyGetRMAttributes(clickToPage, "KEY :Created On VALUE :Apr 8, 2019, 10:32:25 AM");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMAttributes(Map,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetRMAttributesThree() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("Created On", "test");
    assertNotNull(ravt);
    ravt.verifyGetRMAttributes(clickToPage, "KEY :Created On VALUE :Apr 8, 2019, 10:32:25 AM");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMAttributes(Map,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetRMAttributesFour() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("Created On", "test");
    assertNotNull(ravt);
    ravt.verifyGetRMAttributes(clickToPage, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyFilterArtifactByTextOrId(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyFilterArtifactByTextOrId() {
    loadPage("dng/exportArtifact_DropDownMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyFilterArtifactByTextOrId("130683", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyFilterArtifactByTextOrId(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyFilterArtifactByTextOrId() {
    loadPage("dng/exportArtifact_DropDownMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyFilterArtifactByTextOrId("130683invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyOpenRMSpecification(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenRMSpecification() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenRMSpecification("127667", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyOpenRMSpecification(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenRMSpecification() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenRMSpecification("130683invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMSpecificationsSummary(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetRMSpecificationsSummary() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetRMSpecificationsSummary("127667", "127667");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMSpecificationsSummary(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyGetRMSpecificationsSummary() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetRMSpecificationsSummary("130683invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySearchView(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySearchView() {
    loadPage("dng/verify_search_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchView("Review View", "true", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySearchView(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySearchViewOne() {
    loadPage("dng/verify_search_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchView("Module Content Only", "true", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySearchView(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySearchViewFalse() {
    loadPage("dng/verify_search_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchView("Module Content Only", "false", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySearchView(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySearchViewFalseOne() {
    loadPage("dng/verify_search_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchView("Review View", "false", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySearchView(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySearchViewTwo() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchView("Review View", "true", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectView(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectViewOne() {
    loadPage("dng/module_search_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectView("Test_Personal_View", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectView(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectView() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectView("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectArtifact(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectArtifact() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectArtifact("204963", "");

  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectArtifact(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyDeSelectArtifact() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyDeSelectArtifact("204963", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectArtifact(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectArtifact() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectArtifact("20496323", "");

  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectArtifact(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyDeSelectArtifactOne() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectArtifact("20496323", "");
    ravt.verifyDeSelectArtifact("20496323", "");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String, String, String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifacts() {
    loadPage("dng/edit_Artifact_Attribute2.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Edit Attributes...", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsOne() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Edit Attributes...", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsEditArtifactRow() {
    loadPage("dng/verify_selected_artifacts_edit_artifact_row.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Edit Artifact Row", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsEditArtIfactRow() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Edit Artifact Row", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsSelectTags() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Select Tags", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testerifyOpenContextMenuForSelectedArtifacts() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Select Tags", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsCopy() {
    loadPage("dng/verify_open_context_menu_for_selected_artifacts_copy.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Copy", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsCopyOne() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Copy", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsThree() {
    loadPage("dng/verify_open_context_menu_for_selected_artifacts_copy.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Cut", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenContextMenuForSelectedArtifactsCut() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Cut", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsExport() {
    loadPage("dng/export_csvfile_window.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Export", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsTwo() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Export", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsLinkbyAttribute() {
    loadPage("dng/verify_open_context_menu_for_selected_artifacts_link_by_attribute.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Link by Attribute", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenContextMenuForSelectedArtifactsLinkbyAttribute() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Link by Attribute", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsGenerateReport() {
    loadPage("dng/verify_open_context_menu_for_selected_artifacts_generate_report.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Generate Report", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenContextMenuForSelectedArtifactsGenerateReport() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Generate Report", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenContextMenuForSelectedArtifactsRemove() {
    loadPage("dng/verify_open_context_menu_for_selected_artifacts_remove.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Remove", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenContextMenuForSelectedArtifactsRemove() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Remove", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyOpenContextMenuForSelectedArtifactsRemoved() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "invalid", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyEditArtifactAttribute() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyEditArtifactAttribute("", "", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyEditArtifactAttribute() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyEditArtifactAttribute("", "", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyVerifyArtifactValuesInModuleView(String, String, String, String, String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyArtifactValuesInModuleView() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyArtifactValuesInModuleView("", "", "", "true", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyVerifyEditattributeSuccessMessage(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyEditattributeSuccessMessage() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyEditattributeSuccessMessage("", "true", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyIsColoumAddedToTable(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyIsColoumAddedToTable() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyIsColoumAddedToTable("", "true", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdown() {
    loadPage("dng/edit_shared_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Edit View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownPersonal() {
    loadPage("dng/edit_personal_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Edit View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdowninvalidView() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Edit View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownGenerate() {
    loadPage("dng/verify_open_context_menu_for_selected_artifacts_generate_report.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Generate Report for View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownReport() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Generate Report for View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownExport() {
    loadPage("dng/export_csvfile_window.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Export View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectValueFromViewDropdownExport() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Export View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownEditAttributes() {
    loadPage("dng/edit_Artifact_Attribute2.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Edit Attributes from View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectValueFromViewDropdownEditAttributes() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Edit Attributes from View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownLinkbyAttribute() {
    loadPage("dng/link_by_attribute.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Link by Attribute...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectValueFromViewDropdownLinkbyAttribute() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Link by Attribute...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownDeleteView() {
    loadPage("dng/deleteView_popup.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Delete View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectValueFromViewDropdownDeleteView() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Delete View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectValueFromViewDropdownShareLink() {
    loadPage("dng/share_link_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Share Link to View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectValueFromViewDropdownShareLink() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "Share Link to View...", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectValueFromViewDropdown(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectValueFromViewDropdowninvalid() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectValueFromViewDropdown("", "invalid", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnEditAttributeCheckbox() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Artifact Type", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnEditAttributeCheckbox() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Artifact Type", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnEditAttributeDescription() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Description", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnEditAttributeDescription() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Description", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnEditAttributeCheckboxOne() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Customer ID", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnEditAttributeCustomerID() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Customer ID", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnEditAttributeCustomerComment() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Customer Comment", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnEditAttributeCustomerComment() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Customer Comment", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnEditAttributeHistoryLog() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("History Log", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnEditAttributeHistoryLog() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("History Log", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnEditAttributeTags() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Tags", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnEditAttributeTags() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Tags", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnEditAttributeEditTags() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("Edit Tags", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetAttributeValue(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySetAttributeValue() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetAttributeValue("Description", "test", "test");
  }


  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetAttributeValue(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySetAttributeValueTwo() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPage rm = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rm);
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetAttributeValue("Description", "invalid", "test");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectTag(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySelectTag() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectTag("Automation", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectTag(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySelectTag() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectTag("testing", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClearSelectedTags(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClearSelectedTags() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClearSelectedTags("Automation");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClearSelectedTags(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClearSelectedTags() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClearSelectedTags("testing");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyWaitForHundredPercentLoad(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyWaitForHundredPercentLoad() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyWaitForHundredPercentLoad("true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyWaitForHundredPercentLoad(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyWaitForHundredPercentLoad() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyWaitForHundredPercentLoad("false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyChangeColumnDisplaySettings(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyChangeColumnDisplaySettings() {
    loadPage("dng/clickon_select_tag_text.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "ID");
    assertNotNull(ravt);
    ravt.verifyChangeColumnDisplaySettings("ID", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyChangeColumnDisplaySettings(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyChangeColumnDisplaySettings() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Validated By ");
    assertNotNull(ravt);
    ravt.verifyChangeColumnDisplaySettings("Validated By ", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetColumnDatafromTable(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetColumnDatafromTable() {
    loadPage("dng/view_getListOfContentForEachColumn.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetColumnDatafromTable("", "Hardware Requirement", "Hardware Requirement");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetColumnDatafromTable(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyGetColumnDatafromTable() {
    loadPage("dng/view_getListOfContentForEachColumn.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetColumnDatafromTable("", "Hardware Requirement",
        "  Hardware Requirement,  Hardware Requirement  ,Hardware Requirement  ");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClearAttributeValue(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClearAttributeValue() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClearAttributeValue("ForeignModifiedBy", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClearAttributeValue(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClearAttributeValueOne() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClearAttributeValue("ForeignModifiedBy", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyIsSaveButtonInEditAttributeDialogVisible(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyIsSaveButtonInEditAttributeDialogVisible() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyIsSaveButtonInEditAttributeDialogVisible("true", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyIsSaveButtonInEditAttributeDialogVisible(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyIsSaveButtonInEditAttributeDialogVisibleOne() {
    loadPage("dng/set_attribute_value.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyIsSaveButtonInEditAttributeDialogVisible("true", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyUncheckAttributeInEditAttibutesDialog() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Artifact Type", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyUncheckAttributeInEditAttibutesDialog() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Artifact Type", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyUncheckAttributeInEditAttibutesDialogDescription() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Description", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyUncheckAttributeInEditAttibutesDialogOne() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Description", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyUncheckAttributeInEditAttibutesDialogCustomerID() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Customer ID", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyUncheckAttributeInEditAttibutesDialogCustomerID() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Customer ID", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyUncheckAttributeInEditAttibutesDialogCustomerComment() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Customer Comment", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyUncheckAttributeInEditAttibutesDialogTwo() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Customer Comment", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnEditAttributeCheckbox(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnEditAttributeCheckboxTwo() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditAttributeCheckbox("History Log", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyUncheckAttributeInEditAttibutesDialogHistoryLog() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("History Log", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyUncheckAttributeInEditAttibutesDialogTags() {
    loadPage("dng/verify_click_on_edit_attribute_checkbox.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Tags", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyUncheckAttributeInEditAttibutesDialogThree() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Tags", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyUncheckAttributeInEditAttibutesDialog(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyUncheckAttributeInEditAttibutesDialogEditTags() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyUncheckAttributeInEditAttibutesDialog("Edit Tags", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClearFilter(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClearFilter() {
    loadPage("dng/artifacts_clearFilter.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClearFilter("");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClearFilter(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClearFilter() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClearFilter("");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyAddFilter(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyAddFilter() {
    loadPage("dng/addFilter_newfilter.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddFilter("");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyAddFilter(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyAddFilter() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddFilter("");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyAddFilter(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifySearchAttributeTypeInFilter() {
    loadPage("dng/addFilter_newfilter.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchAttributeInFilter("Artifact Type", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySearchAttributeInFilter(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfySearchAttributeTypeInFilter() {
    loadPage("dng/moveArtifact_TextField.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchAttributeInFilter("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddAndCloseValueForAttributeInFilter(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyChooseArtifactTypeInFilter() {
    loadPage("dng/verify_choose_artifact_type_in_filter.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddAndCloseValueForAttributeInFilter("", "Heading", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddAndCloseValueForAttributeInFilter(String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyChooseArtifactTypeInFilter() {
    loadPage("dng/verify_choose_artifact_type_in_filter.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddAndCloseValueForAttributeInFilter("", "", "Invalid");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnSaveAsNewView(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnSaveAsNewView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnSaveAsNewView("");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnSaveAsNewView(String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyClickOnSaveAsNewView() {
    loadPage("dng/verify_choose_artifact_type_in_filter.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnSaveAsNewView("Invalid");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyCreateNewView(String,String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyCreateNewView() {
    loadPage("dng/verify_create_new_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyCreateNewView("", "", "", "", "test");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyCreateNewView(String,String,String,String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyCreateNewView() {
    loadPage("dng/verify_create_new_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyCreateNewView("", "", "", "", "TestView");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyDeleteView(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyDeleteView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyDeleteView("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyDeleteView(String,String)}.
   * <p>
   * Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyDeleteView() {
    loadPage("dng/verify_search_view.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyDeleteView("Module Content Only", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyOpenContextMenuForSelectedArtifacts(String,String,String,String)}.
   */
  @Test
  public void testVerIfyOpenContextMenuForSelectedArtifacts1() {
    loadPage("dng/selectArtifactAndOpenContextMenu.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenContextMenuForSelectedArtifacts("", "Edit Attributes...", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyIsApplyButtonEnabled(String,String)}.
   */
  @Test
  public void testverifyIsApplyButtonEnabled() {
    loadPage("dng/testRqmenabled.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyIsApplyButtonEnabled("true", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyIsApplyButtonEnabled(String,String)}.
   */
  @Test
  public void testverifyIsApplyButtonEnabledOne() {
    loadPage("dng/testRqmApplybuttonIsEnabled.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyIsApplyButtonEnabled("false", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyCreateNewTag(String,String,String)}.
   */
  @Test
  public void testVerifyCreateNewTag() {
    loadPage("dng/createNewTag2.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyCreateNewTag("", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyCreateNewTag(String,String,String)}.
   */
  @Test
  public void testVerifyCreateNewTagOne() {
    loadPage("dng/createNewTag1.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyCreateNewTag("template", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectMoreActionsForAttribute(String,String)}.
   */
  @Test
  public void testverifySelectMoreActionsForAttribute() {
    loadPage("dng/manageTag2.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectMoreActionsForAttribute("Edit Attributes", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectMoreActionsForAttribute(String,String)}.
   */
  @Test
  public void testverifySelectMoreActionsForAttributeOne() {
    loadPage("dng/manageTag2.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectMoreActionsForAttribute("Edit Attributes", "Edit Attributes");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyManageTag(String,String,String,String)}.
   */
  @Test
  public void testVerifyManageTag() {
    loadPage("dng/manageTag2.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();

    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyManageTag("DefaultTag1", "Delete Tag1", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyManageTag(String,String,String,String)}.
   */
  @Test
  public void testVerifyManageTagOne() {
    loadPage("dng/manageTag2.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, null);
    clickToPage.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyManageTag("DefaultTag", "Delete Tag", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnCreateButtonWithType(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verifies clicked on 'Create' button.
   */
  @Test
  public void testVerifyClickOnCreateButtonWithType() {
    loadPage("dng/createArtifact.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnCreateButtonWithType("Other...", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnCreateButtonWithType(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verifies clicked on 'Create' button with False message.
   */
  @Test
  public void testVerifyClickOnCreateButtonWithTypeTwo() {
    loadPage("dng/createArtifact_ID.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnCreateButtonWithType("test", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyCreateArtifact(Map,String)}.
   * <p>
   * Loads RM Artifacts page and Verifies Artifact is created.
   */
  @Test
  public void testVerifyCreateArtifact() {
    loadPage("dng/createArtifact_ID.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(Artifact.NAME.toString(), "return attributeList;");
    additionalParams.put("Artifact type:", "Assumption");
    additionalParams.put("Artifact format:", "Text");
    // ravt.verifyCreateArtifact(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyCreateArtifact(Map,String)}.
   * <p>
   * Loads RM Artifacts page and Verifies Artifact is created with false acceptance message.
   */
  @Test
  public void testVerifyCreateArtifactTwo() {
    loadPage("dng/createArtifact_ID.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(Artifact.NAME.toString(), "test;");
    additionalParams.put("Artifact type:", "Assumption");
    additionalParams.put("Artifact format:", "Text");
    // ravt.verifyCreateArtifact(additionalParams, "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetArtifactID(String)}.
   * <p>
   * Loads RM Artifacts page and Verifies ID of the artifact.
   */
  @Test
  public void testVerifyGetArtifactID() {
    loadPage("dng/createArtifact_ID.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetArtifactID("129462");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetArtifactID(String)}.
   * <p>
   * Loads RM Artifacts page and Verifies ID of the artifact with false message.
   */
  @Test
  public void testVerifyGetArtifactIDTwo() {
    loadPage("dng/createArtifact_ID.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetArtifactID("123456");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyDeleteArtifactFromSearchedSpecification(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify artifact is deleted from searched specification.
   */
  @Test
  public void testVerifyDeleteArtifactFromSearchedSpecification() {
    loadPage("dng/OpenRMSpecifications.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyDeleteArtifactFromSearchedSpecification("45678", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyDeleteArtifactFromSearchedSpecification(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify artifact is deleted from searched specification with false message.
   */
  @Test
  public void testVerifyDeleteArtifactFromSearchedSpecificationTwo() {
    loadPage("dng/OpenRMSpecifications.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyDeleteArtifactFromSearchedSpecification("129494", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyDeleteArtifactFolder(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and verify the folder is deleted.
   */
  @Test
  public void testVerifyDeleteArtifactFolder() {
    loadPage("dng/test_verifying_delete_artifact_folder.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyDeleteArtifactFolder("Migration Module artifacts", "FALSE", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyImportRequirementsFromACSVFileOrSpreadsheetIntoAModule(String, String, String, String, String)}.
   * <p>
   * Loads RM Artifacts page and verify the CSV or spreadsheet file is imported successfully.
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyImportRequirementsFromACSVFileOrSpreadsheetIntoAModule() {
    loadPage("dng/ImportRequirementsFromACSVFileOrSpreadsheetIntoAModule10.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String fileName = "export_artifact_TS_25902.xlsx";
    String moduleId = "643387";
    String moduleName = "briBk1 Fbl SW RS";
    String importOption = "Update artifacts that match entries, and ignore new entries";
    ravt.verifyImportRequirementsFromACSVFileOrSpreadsheetIntoAModule(fileName, moduleId, moduleName, importOption, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyImportArtifactsWithMigizFileExtension(Map,String)}.
   * <p>
   * Loads RM Artifacts page and verify the migiz file is imported successfully.
   */
  @Test
  public void testVerifyImportArtifactsWithMigizFileExtension() {
    loadPage("dng/test_verifying_delete_artifact_in_folder.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("IMPORTED_FOLDER", "ALM_System");
    additionalParams.put("IMPORTED_MODULE_NAME", "Migration Module");
    ravt.verifyImportArtifactsWithMigizFileExtension(additionalParams, "");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyImportRequirementsFromAMigrationPackageWithError(String, String)}.
   * <p>
   * Loads RM Artifacts page and Verify the displayed error message 'Only files of the following type are allowed: migiz'
   */
  @Test
  public void testVerifyImportRequirementsFromAMigrationPackageWithError() {
    loadPage("dng/import_migration_migiz_error.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    String fileName = "Artifact_Excel1.xlsx";
    assertEquals((rm.verifyImportRequirementsFromAMigrationPackageWithError(fileName , "TRUE").getState()), "PASSED");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetArtifactIdByIndex(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Artifact Id By Index.
   */
  @Test
  public void testVerifyGetArtifactIdByIndex() {
    loadPage("dng/RM_index.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetArtifactIdByIndex("1", "220071");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetArtifactIdByIndex(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Artifact Id By Index.
   */
  @Test
  public void testVerifyGetArtifactIdByIndexOne() {
    loadPage("dng/RM_index.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetArtifactIdByIndex("1", "22007100000");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetArtifactIdByIndex(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get File Data.
   */
  @Test
  public void testVerifyGetFileData() {
    // loadPage("dng/RM_index.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();

    ravt.verifyGetFileData(filePath,
        "Implemented By  (2),270881: Task2 created to automate pdf report,270882: Task3 created to automate pdf report,Satisfied By  (1),1110101: artifact2 created to automate pdf report,Validated By  (1),89281: created for automate dng pdf report,Select Item");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetFileData(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get File Data.
   */
  @Test
  public void testVerifyGetFileDataOne() {
    // loadPage("dng/RM_index.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();

    ravt.verifyGetFileData(filePath, "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyisRmLinksAvailableInPdfFile(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify is Rm Links Available In Pdf File.
   */
  @Test
  public void testVerifyisRmLinksAvailableInPdfFile() {
    // loadPage("dng/RM_index.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();

    ravt.verifyisRmLinksAvailableInPdfFile(filePath, "", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyisRmLinksAvailableInPdfFile(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify is Rm Links Available In Pdf File.
   */
  @Test
  public void testVerifyisRmLinksAvailableInPdfFileOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();

    ravt.verifyisRmLinksAvailableInPdfFile(filePath, "", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyIsDataAvailableInPdfFile(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Is Data Available In Pdf File.
   */
  @Test
  public void testVerifyIsDataAvailableInPdfFile() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();

    ravt.verifyIsDataAvailableInPdfFile(filePath, "270881", "270881");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyIsDataAvailableInPdfFile(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Is Data Available In Pdf File.
   */
  @Test
  public void testVerifyIsDataAvailableInPdfFileOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/dng/1110588- testFile-20200819_100237212.pdf").getAbsolutePath();

    ravt.verifyIsDataAvailableInPdfFile(filePath, "270881", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetDownededPdfFileNameAndPath(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Downeded Pdf File Name And Path.
   */
  @Test
  public void testVerifyGetDownededPdfFileNameAndPath() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetDownededPdfFileNameAndPath("testFile", "testFile");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetDownededPdfFileNameAndPath(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Downeded Pdf File Name And Path.
   */
  @Test
  public void testVerifyGetDownededPdfFileNameAndPathOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetDownededPdfFileNameAndPath("testFile", "270881");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyGetValidationMessageFromReportGenerationMessageArea(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Validation Message From Report Generation Message Area.
   */
  @Test
  public void testVerifyGetValidationMessageFromReportGenerationMessageArea() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetValidationMessageFromReportGenerationMessageArea("Report generation complete",
        "Report generation complete", "Report generation complete");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyGetValidationMessageFromReportGenerationMessageArea(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Validation Message From Report Generation Message Area.
   */
  @Test
  public void testVerifyGetValidationMessageFromReportGenerationMessageAreaOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetValidationMessageFromReportGenerationMessageArea("Report generation complete invalid",
        "Report generation complete", "");
  }


  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetDownloadedFileNameAndPath(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Down loaded File Name And Path.
   */

  @Test
  public void testVerifyGetDownloadedFileNameAndPath() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetDownloadedFileNameAndPath("testFile", "testFile");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetDownloadedFileNameAndPath(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Get Down loaded File Name And Path.
   */

  @Test
  public void testVerifyGetDownloadedFileNameAndPathOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetDownloadedFileNameAndPath("testFile", "270881");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnViewingLink(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Click On Viewing Link.
   */
  @Test
  public void testVerifyClickOnViewingLink() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyClickOnViewingLink("testFile", "testFile");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnViewingLink(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Click On Viewing Link.
   */
  @Test
  public void testVerifyClickOnViewingLinkOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyClickOnViewingLink("testFile", "invalid");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifySelectAddOrRemoveAttribute(String, String, String, String, String)}.
   * <p>
   * Loads RM Artifacts page and Verify Select Add Or Remove Attribute.
   */
  @Test
  public void testVerifySelectAddOrRemoveAttribute() {
    loadPage("dng/test_RMlinksOne.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifySelectAddOrRemoveAttribute("Add", "1234", "name", "false", "lastResult");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifySelectAddOrRemoveAttribute(String, String, String, String, String)}.
   * <p>
   * Loads RM Artifacts page and Verify Select Add Or Remove Attribute.
   */
  @Test
  public void testVerifySelectAddOrRemoveAttributeOne() {
    loadPage("dng/test_RMlinksTwo.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectAddOrRemoveAttribute("Add", "1234", "name", "true", "lastResult");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifySelectAddOrRemoveAttribute(String, String, String, String, String)}.
   * <p>
   * Loads RM Artifacts page and Verify Select Add Or Remove Attribute.
   */
  @Test
  public void testVerifySelectAddOrRemoveAttributeTwo() {
    loadPage("dng/test_RMlinksTwo.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectAddOrRemoveAttribute("Add", "1234", "name", "false", "lastResult");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectMultipleArtifact(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Select Multiple Artifact.
   */
  @Test
  public void testVerifySelectMultipleArtifact() {
    loadPage("dng/test_searchViewTwo.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectMultipleArtifact("1", "809702");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectMultipleArtifact(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Select Multiple Artifact.
   */
  @Test(expected = Exception.class)
  public void testVerifySelectMultipleArtifactOne() {
    loadPage("dng/test_searchViewTwo.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySelectMultipleArtifact("10", "809702");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyHandleAlert(WebDriver, int, String)}.
   * <p>
   * Loads RM Artifacts page and Verify Handle Alert.
   */
  @Test
  public void testVerifyHandleAlert() {
    loadPage("dng/test_searchViewTwo.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyHandleAlert(WebDriver, Duration.ofSeconds(2), "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMAttributeTagValue(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and verify Get RM Attribute Tag Value.
   */
  @Test
  public void testVerifyGetRMAttributeTagValue() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetRMAttributeTagValue("809702", "809702", "809702");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetRMAttributeTagValue(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and verify Get RM Attribute Tag Value.
   */
  @Test
  public void testVerifyGetRMAttributeTagValueOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyGetRMAttributeTagValue("809702", "8097024", "809702");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyClickOnRMAttributeSelectTags(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Click On RM Attribute Select Tags.
   */
  @Test
  public void testVerifyClickOnRMAttributeSelectTags() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyClickOnRMAttributeSelectTags("809702", "TagName", "TagName");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyClickOnRMAttributeSelectTags(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Click On RM Attribute Select Tags.
   */
  @Test
  public void testVerifyClickOnRMAttributeSelectTagsOne() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyClickOnRMAttributeSelectTags("809702", "invalid", "TagName");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnImageButton(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Click On Image Button.
   */
  @Test
  public void testVerifyClickOnImageButton() {
    loadPage("dng/clickOnImage.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnImageButton("Save changes to this view", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnImageButton(String,String)}.
   * <p>
   * Loads RM Artifacts page and Verify Click On Image Button.
   */
  @Test
  public void testVerifyClickOnImageButtonOne() {
    loadPage("dng/clickOnImage.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnImageButton("Save changes to this view1", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySelectAvailableReportType(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify Select Available Report Type.
   */
  @Test
  public void testVerifySelectAvailableReportType() {
    loadPage("dng/clickOnImage.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifySelectAvailableReportType("Holding", "invalid");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddReportInformationWithoutDateandTime(String, String, String, String, String)}.
   * <p>
   * Loads RM Artifacts page and Verify Add Report Information Without Date and Time.
   */
  @Test
  public void testVerifyAddReportInformationWithoutDateandTime() {
    loadPage("dng/clickOnImage.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyAddReportInformationWithoutDateandTime("reportName", "reportType", "authorName", "companyName",
        "reportName");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddReportInformationWithoutDateandTime(String, String, String, String, String)}.
   * <p>
   * Loads RM Artifacts page and Verify Add Report Information Without Date and Time.
   */
  @Test
  public void testVerifyAddReportInformationWithoutDateandTimeOne() {
    loadPage("dng/clickOnImage.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyAddReportInformationWithoutDateandTime("reportName", "", "", "", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddReportInformationWithoutDateandTime(String, String, String, String, String)}.
   * <p>
   * Loads RM Artifacts page and Verify Add Report Information With Date and Time.
   */
  @Test
  public void testVerifyAddReportInformationWithDateandTime() {
    loadPage("dng/testAddReportInformationWithDateandTime.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> params = new HashMap<>();
    params.put("reportName", " Data type - Comparison Repository");
    params.put("reportType", "Print Module Book");
    params.put("appendDateTime", "false");
    params.put("reportFormat", "Adobe PDF");
    ravt.verifyAddReportInformationWithDateandTime(params, "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyClickOnGenerateAreportFromReportPage(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and verify Click On Generate Areport From Report Page.
   */
  @Test
  public void testVerifyClickOnGenerateAreportFromReportPage() {
    // loadPage("dng/clickOnImage.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyClickOnGenerateAreportFromReportPage("Generate a report", "Generate a report", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyClickOnGenerateAreportFromReportPage(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and verify Click On Generate Areport From Report Page.
   */
  @Test
  public void testVerifyClickOnGenerateAreportFromReportPageOne() {
    // loadPage("dng/clickOnImage.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);

    ravt.verifyClickOnGenerateAreportFromReportPage("", "Generate a report invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetViewName(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify view name.
   */
  @Test
  public void testVerifySetViewName() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetViewName("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetViewName(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify view name with false message.
   */
  @Test
  public void testVerifySetViewNameOne() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetViewName("test view", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetViewName(String,String)}.
   * <p>
   * Loads RM Artifacts page and set view type.
   */
  @Test
  public void testVerifySetViewType() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetViewType("Personal", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetViewType(String,String)}.
   * <p>
   * Loads RM Artifacts page and set view type with false message.
   */
  @Test
  public void testVerifySetViewTypeOne() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetViewType("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetWhereToShowThisView(String,String)}.
   * <p>
   * Loads RM Artifacts page and set where to show this view.
   */
  @Test
  public void testVerifySetWhereToShowThisView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetWhereToShowThisView("Just this module", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySetWhereToShowThisView(String,String)}.
   * <p>
   * Loads RM Artifacts page and set where to show this view with false message.
   */
  @Test
  public void testVerifySetWhereToShowThisViewOne() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySetWhereToShowThisView("All Modules", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyMakePreferredView(String)}.
   * <p>
   * Loads RM Artifacts page and verify preferred view.
   */
  @Test
  public void testVerifyMakePreferredView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyMakePreferredView("");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyAddDescriptionInView(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify description is added in the view.
   */
  @Test
  public void testVerifyAddDescriptionInView() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddDescriptionInView("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyAddDescriptionInView(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify description is added in the view with false message.
   */
  @Test
  public void testVerifyAddDescriptionInViewOne() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddDescriptionInView("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyOpenRMSpecification(String,String,String)}.
   * <p>
   * Loads RM Artifacts page and verify rm specification is opened.
   */
  @Test
  public void testverifyOpenRMSpecification() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenRMSpecification("127667", "true", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyChooseRole(String,String)}.
   * <p>
   * Loads RM Artifacts page and verify role is chosen.
   */
  @Test
  public void testVerifyChooseRole() {
    loadPage("dng/saveAsView_newView.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyChooseRole("test_role", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyInputArtifactContent(String,String)}.
   * <p>
   * Loads RM Artifacts Page and verify input artifact content.
   */
  @Test
  public void testVerifyInputArtifactContent() {
    loadPage("dng/verify_input_artifact_content.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyInputArtifactContent("test .................", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyInputArtifactContent(String,String)}.
   * <p>
   * Loads RM Artifacts Page and verify input artifact content with false acceptance message.
   */
  @Test
  public void testverifyInputArtifactContent() {
    loadPage("dng/verify_input_artifact_content.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyInputArtifactContent("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyDeleteArtifact(String,String)}.
   * <p>
   * Loads RM Artifacts Page and verify artifact is deleted.
   */
  @Test
  public void testVerifyDeleteArtifact() {
    loadPage("dng/verify_delete_artifact.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyDeleteArtifact("test", "");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddAndCloseValueForFolderAttributeInFilter(String,String)}.
   * <p>
   * Loads RM Artifacts Page and verify artifact is deleted.
   */
  @Test
  public void testVerifyAddAndCloseValueForFolderAttributeInFilter() {
    loadPage("dng/FilterFolder_filterSuccessfully.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddAndCloseValueForFolderAttributeInFilter("PPP 123 artifacts", "true");
  }


  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifySearchGoToInModuleFind(String,String)}.
   * <p>
   * Search Go To one Artifact and verify this artifact is selected.
   */
  @Test
  public void testVerifySearchGoToInModuleFind() {
    loadPage("dng/searchGoTo_04.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifySearchGoToInModuleFind("1107282", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddItemAndValueForLimitByLifecycleStatusFilter(Map,String)}.
   * <p>
   * Add Limit By Lifecycle Status Filter and verify filter display after add successfully Case 1: All item are selected
   * value
   */
  @Test
  public void testVerifyAddItemAndValueForLimitByLifecycleStatusFilter01() {
    loadPage("dng/LimitByLifecycleStatus05.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("ATTRIBUTE_NAME", "Limit by lifecycle status");
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "Resolved");
    additionalParams.put("TRACKING_VALUE", "Open");
    additionalParams.put("AFFECTS_VALUE", "All");
    additionalParams.put("TEST_VALUE", "Unattempted");
    ravt.verifyAddItemAndValueForLimitByLifecycleStatusFilter(additionalParams, "true");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddItemAndValueForLimitByLifecycleStatusFilter(Map,String)}.
   * <p>
   * Add Limit By Lifecycle Status Filter and verify filter display after add successfully Case 2: One item is selected
   * value. Others are not selected (case for "not select")
   */
  @Test
  public void testVerifyAddItemAndValueForLimitByLifecycleStatusFilter02() {
    loadPage("dng/LimitByLifecycleStatus06.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("ATTRIBUTE_NAME", "Limit by lifecycle status");
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "not select");
    additionalParams.put("TRACKING_VALUE", "not select");
    additionalParams.put("AFFECTS_VALUE", "not select");
    additionalParams.put("TEST_VALUE", "All");
    ravt.verifyAddItemAndValueForLimitByLifecycleStatusFilter(additionalParams, "true");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddAndCloseListValueForAttributeInFilter(String,String,String)}.
   * <p>
   * Add Attribute included value and verify filter display after add successfully
   */
  @Test
  public void testVerifyAddAndCloseListValueForAttributeInFilter() {
    loadPage("dng/verifyAddAndCloseListValueForAttributeInFilter01.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddAndCloseListValueForAttributeInFilter("Artifact Type", "SW Fbl Heading", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddAndCloseListValueForAttributeInFilter(String,String,String)}.
   * <p>
   * Add Attribute included values and verify filter display after add successfully
   */
  @Test
  public void testVerifyAddAndCloseListValueForAttributeInFilterMultipleValue() {
    loadPage("dng/verifyAddAndCloseListValueForAttributeInFilterMultipleValue.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddAndCloseListValueForAttributeInFilter("Artifact Type", "TypeA-System,TypeB-System", "Last Result");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddAndCloseListValueForAttributeInFilter(String,String,String)}.
   * <p>
   * Add Attribute included value for Link Type and verify filter display after add successfully
   */
  @Test
  public void testVerifyAddAndCloseListValueForAttributeInFilterForLinkType() {
    loadPage("dng/verifyAddAndCloseListValueForAttributeInFilterLinkType.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyAddAndCloseListValueForAttributeInFilter("Artifact Type", "TypeA-System,TypeB-System", "Last Result");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyGetAllValuesAtColumnFromTable(String,String,String)}.
   * <p>
   * Case 1: expectedValue is "emptydata"
   */
  @Test
  public void testVerifyGetAllValuesAtColumnFromTable01() {
    loadPage("dng/getValueFromColumn02.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetAllValuesAtColumnFromTable("Validated By", "emptydata", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyGetAllValuesAtColumnFromTable(String,String,String)}.
   * <p>
   * Case 2: expectedValue is single value
   */
  @Test
  public void testVerifyGetAllValuesAtColumnFromTable02() {
    loadPage("dng/getValueFromColumn01.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetAllValuesAtColumnFromTable("Validated By", "96118: TC_SW_QM_PowerMonitoring", "true");
  }


  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyGetAllValuesAtColumnFromTable(String,String,String)}.
   * <p>
   * Case 3: expectedValue is multiple value (ex: "32090: TC_CleanupECU" , "9500: RSU_1162_KL30only" , "emptydata")
   */
  @Test
  public void testVerifyGetAllValuesAtColumnFromTable03() {
    loadPage("dng/getValueFromColumn01.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetAllValuesAtColumnFromTable("Validated By", "32090: TC_CleanupECU,9500: RSU_1162_KL30only,emptydata",
        "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyGetExcelSize(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyGetExcelSize() {
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.csv";
    rm.verifyGetExcelSize(path, "1");

  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnRadioButtonInExportDialog(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyClickOnRadioButtonInExportDialog() {
    loadPage("dng/ClickOnRadioButton02.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnRadioButtonInExportDialog("XLSX", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyGetDataFromCellOfXLSX(String,String,String,String,String)}.
   * <p>
   */
  @Test
  public void testVerifyGetDataFromCellOfXLSX() {

    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    ravt.verifyGetDataFromCellOfXLSX(path, "0", "0", "0", "id");
  }

  /**
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifySetDataFromCellOfXLSX(String, String, String, String, String, String)}.
   * <p>
   */
  @Test
  public void testVerifySetDataFromCellOfXLSX() {
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    ravt.verifySetDataFromCellOfXLSX(path, "0", "0", "0", "id","");
  }


  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyOpenEditFilterDialogOfOneCondition(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyOpenEditFilterDialogOfOneCondition() {
    loadPage("dng/EditFilter02.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenEditFilterDialogOfOneCondition("Limit by lifecycle status", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyOpenEditFilterDialogOfAttributeName(String,String)}.
   * <p>
   * @author PDU6HC
   */
  @Test
  public void testVerifyOpenEditFilterDialogOfAttributeName() {
    loadPage("dng/EditFilterConditionNumber.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyOpenEditFilterDialogOfOneCondition("3", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyEditItemAndValueForLimitByLifecycleStatusFilter(Map,String)}.
   * <p>
   * Case 1: All item are selected value - one item as TRACKING_ITEM is remove selected item by re-select this value
   * again. Other items change value from None to All/Passed
   */
  @Test
  public void testVerifyEditItemAndValueForLimitByLifecycleStatusFilter01() {
    loadPage("dng/EditItemAndValueForLimitByLifecycleStatusFilter06.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "All");
    additionalParams.put("TRACKING_VALUE", "None");
    additionalParams.put("AFFECTS_VALUE", "All");
    additionalParams.put("TEST_VALUE", "Passed");
    ravt.verifyEditItemAndValueForLimitByLifecycleStatusFilter(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyEditItemAndValueForLimitByLifecycleStatusFilter(Map,String)}.
   * <p>
   * Case 2: One item is selected value. Others are not selected (case for "not select")
   */
  @Test
  public void testVerifyEditItemAndValueForLimitByLifecycleStatusFilter02() {
    loadPage("dng/EditItemAndValueForLimitByLifecycleStatusFilter07.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("DEVELOPMENT_ITEM", "Development Items");
    additionalParams.put("TRACKING_ITEM", "Tracking Items");
    additionalParams.put("AFFECTS_ITEM", "Affected Items");
    additionalParams.put("TEST_ITEM", "Test Items");
    additionalParams.put("DEVELOPMENT_VALUE", "not select");
    additionalParams.put("TRACKING_VALUE", "not select");
    additionalParams.put("AFFECTS_VALUE", "not select");
    additionalParams.put("TEST_VALUE", "not select");
    ravt.verifyEditItemAndValueForLimitByLifecycleStatusFilter(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyLoadArtifactsInRootFolder(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyLoadArtifactsInRootFolder() {
    loadPage("dng/LoadArtifactInRootFolder_02.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyLoadArtifactsInRootFolder("rbd_briBk1_sys", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnCreateButton(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyClickOnCreateButton() {
    loadPage("dng/ClickOnCreateArtifactButton01.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnCreateButton("SW SRS Requirement Module", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnCreateButton(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyClickOnCreateButtonWhenChoosingContainType() {
    loadPage("dng/testVerifyClickOnCreateButtonWhenChoosingContainType.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnCreateButton("Heading", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnArtifact(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyClickOnArtifact() {
    loadPage("dng/ClickOnArtifact.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnArtifact("2002272", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyClickOnArtifact(String,String)}. <br>
   * Cover for failed case
   * <p>
   */
  @Test
  public void testVerIfyClickOnArtifact() {
    loadPage("dng/ClickOnArtifact.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnArtifact("123456", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyGetColumnHeaderFromCSVFileByIndex(String,String,String)}.
   * <p>
   */
  @Test
  public void testVerifyGetColumnHeaderFromCSVFileByIndex() {

    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.csv";
    rm.verifyGetColumnHeaderFromCSVFileByIndex(path, "5", "Link:Implemented By");

  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyExpandFilters(String)}.
   * <p>
   */
  @Test
  public void testVerifyExpandFilters() {
    loadPage("dng/EditItemAndValueForLimitByLifecycleStatusFilter07.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyExpandFilters("true");

  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyIsViewSelected(String,String)}.
   * <p>
   */
  @Test
  public void testVerifyIsViewSelected() {
    loadPage("dng/SelectView01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsViewSelected("SW_SRS_briBk1_All_Default", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyIsButtonDisplayed(String,String)}.
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyIsButtonDisplayed() {
    loadPage("dng/buttonDisplay.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsButtonDisplayed("Create", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddArtifactAttributeInToArtifactTable(String,String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyAddArtifactAttributeInToArtifactTable() {
    loadPage("dng/verifyAddArtifactAttributeInToArtifactTable.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyAddArtifactAttributeInToArtifactTable("Validated By", "");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMArtifactsPageVerification#verifyAddArtifactAttributeInToArtifactTable(String,String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyAddArtifactAttributeInToArtifactTable() {
    loadPage("dng/verifyAddArtifactAttributeInToArtifactTable.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyAddArtifactAttributeInToArtifactTable("Name", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyImportArtifactTypes(Map, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyImportArtifactTypes() {
    loadPage("dng/verifyImportArtifactTypes.html");
    Map<String, String> importedMigiz = new LinkedHashMap<>();
    String importFileAbsolutePath =
        getJazzPageFactory().getRMArtifactPage().getAbsoluteFilePathToBeImported("migiz_package_1.migiz");
    importedMigiz.put("FILE_PATH", importFileAbsolutePath);
    importedMigiz.put("IMPORT_TYPE", "Import requirements from a migration package");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyImportArtifactTypes(importedMigiz, "Import completed with errors");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyImportArtifactTypes(Map, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyImportArtifactTypes() {
    loadPage("dng/verifyImportArtifactTypes.html");
    Map<String, String> importedMigiz = new LinkedHashMap<>();
    String importFileAbsolutePath =
        getJazzPageFactory().getRMArtifactPage().getAbsoluteFilePathToBeImported("migiz_package_1.migiz");
    importedMigiz.put("FILE_PATH", importFileAbsolutePath);
    importedMigiz.put("IMPORT_TYPE", "Import requirements from a migration package");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyImportArtifactTypes(importedMigiz, "Test");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyImportArtifactTypes(Map, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyCloneFromAComponent() {
    loadPage("dng/verifyCloneFromAComponent.html");
    Map<String, String> additionalParam = new LinkedHashMap<>();
    additionalParam.put("FOLDER_NAME", "test2132");
    additionalParam.put("PROJECT_AREA", "Control Unit System Development (Requirements)");
    additionalParam.put("COMPONENT_NAME", "MO_EcuBasFct");
    additionalParam.put("STREAMS_DROPDOWN_OPTION", "Streams");
    additionalParam.put("STREAM_NAME", "MO_EcuBasFct Initial Stream");
    additionalParam.put("SELECT_ARTIFACT_TYPE", "Add Module");
    additionalParam.put("ARTIFACT_ID", "1025066");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyCloneFromAComponent(additionalParam, "false", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMArtifactsPageVerification#verifyImportArtifactTypes(Map, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyCloneFromAComponent() {
    loadPage("dng/verifyCloneFromAComponent.html");
    Map<String, String> additionalParam = new LinkedHashMap<>();
    additionalParam.put("FOLDER_NAME", "test2132");
    additionalParam.put("PROJECT_AREA", "Control Unit System Development (Requirements)");
    additionalParam.put("COMPONENT_NAME", "MO_EcuBasFct");
    additionalParam.put("STREAMS_DROPDOWN_OPTION", "Streams");
    additionalParam.put("STREAM_NAME", "MO_EcuBasFct Initial Stream");
    additionalParam.put("SELECT_ARTIFACT_TYPE", "Add Module");
    additionalParam.put("ARTIFACT_ID", "1025066");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyCloneFromAComponent(additionalParam, "true", "false");
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPageVerification#verifySelectConditionWhenAddAndCloseListValueForAttributeInFilter(Map,String)}
   */

  @Test
  public void testVerifySelectConditionWhenAddAndCloseListValueForAttributeInFilter() {
    loadPage("dng/selectCondition_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("ARTIFACT_NAME", "Status SW SRS");
    additionalParams.put("DEFAULT_CONDITION", "is any of");
    additionalParams.put("EXPECT_CONDITION", "does not exist");
    rm.verifySelectConditionWhenAddAndCloseListValueForAttributeInFilter(additionalParams, "true");
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPageVerification#verifySelectConditionWhenAddAndCloseListValueForAttributeInFilter(Map,String)}
   */

  @Test
  public void testVerIfySelectConditionWhenAddAndCloseListValueForAttributeInFilter() {
    loadPage("dng/selectCondition_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("ARTIFACT_NAME", "Status SW SRS");
    additionalParams.put("DEFAULT_CONDITION", "is any of");
    additionalParams.put("EXPECT_CONDITION", "does not exist");
    rm.verifySelectConditionWhenAddAndCloseListValueForAttributeInFilter(additionalParams, "false");
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPageVerification#verifyAddAndCloseListValueByTextFieldForAttributeInFilter(String,String,String)}
   */

  @Test
  public void testVerifyAddAndCloseListValueByTextFieldForAttributeInFilter() {
    loadPage("dng/FilterAddListValue_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyAddAndCloseListValueByTextFieldForAttributeInFilter("Artifact ID", "645625, 611268, 611566", "true");
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPageVerification#verifyAddAndCloseListValueByTextFieldForAttributeInFilter(String,String,String)}
   */

  @Test
  public void testVerIfyAddAndCloseListValueByTextFieldForAttributeInFilter() {
    loadPage("dng/FilterAddListValue_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyAddAndCloseListValueByTextFieldForAttributeInFilter("Artifact ID", "645625, 611268, 611566", "false");
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPageVerification#verifyClickOnArtifactByOpenNewTab(String,String,String)}
   */

  @Test
  public void testVerifyClickOnArtifactByOpenNewTab() {
    loadPage("dng/FilterAddListValue_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    RMArtifactsPage rmt = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rmt);
    rmt.clickOnArtifactByOpenNewTab("611268");
    ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(1));
    loadPage("dng/FilterAddListValue_05.html");
    rm.verifyClickOnArtifactByOpenNewTab("260163", "1", "true");
  }

  /**
   * @author LPH1HC Unit test for
   *         {@link RMArtifactsPageVerification#verifyClickOnArtifactByOpenNewTab(String,String,String)}
   */

  @Test
  public void testVerIfyClickOnArtifactByOpenNewTab() {
    loadPage("dng/FilterAddListValue_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    RMArtifactsPage rmt = getJazzPageFactory().getRMArtifactPage();
    assertNotNull(rmt);
    rmt.clickOnArtifactByOpenNewTab("611268");
    ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs2.get(1));
    loadPage("dng/FilterAddListValue_05.html");
    rm.verifyClickOnArtifactByOpenNewTab("260163", "1", "false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyChooseAllAttributeValuesForSelectedAttribute}
   */
  @Test
  public void testVerifyChooseAllAttributeValuesForSelectedAttribute() {
    loadPage("dng/verifyChooseAllAttributeValuesForSelectedAttribute.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyChooseAllAttributeValuesForSelectedAttribute("Category", "is any of",
        "functional, non functional, non technical, functional safety", "4");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyIsEmptyDataExistingFromArtifactTable(String,String,String)}
   * Case 1: expectResult as true - return true <br>
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyCheckEmptyDataFromArtifactTable01() {
    loadPage("dng/ChecEmptyData01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsEmptyDataExistingFromArtifactTable("Contents", "true", "true");
  }

  /**
   * Unit test for
   * {@link RMArtifactsPageVerification#verifyIsEmptyDataExistingFromArtifactTable(String, String, String)} Case 2:
   * expectResult as false - return true <br>
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyCheckEmptyDataFromArtifactTable02() {
    loadPage("dng/ChecEmptyData02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsEmptyDataExistingFromArtifactTable("Contents", "false", "true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyIsEmptyDataExistingFromArtifactTable(String,String,String)}
   * Case 3: expectResult as true - return false <br>
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyCheckEmptyDataFromArtifactTable03() {
    loadPage("dng/ChecEmptyData02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsEmptyDataExistingFromArtifactTable("Contents", "true", "false");
  }

  /**
   * Unit test for
   * {@link RMArtifactsPageVerification#verifyIsEmptyDataExistingFromArtifactTable(String, String, String)} Case 4:
   * expectResult as false - return false <br>
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyCheckEmptyDataFromArtifactTable04() {
    loadPage("dng/ChecEmptyData01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsEmptyDataExistingFromArtifactTable("Contents", "false", "false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyVerifyDataDisplayedInColumn(String,String,String,String)}
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyVerifyDataDisplayedInColumn() {
    loadPage("dng/IsDataDisplayedInColumn_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyVerifyDataDisplayedInColumn("Artifact Type", "SYS HSI Heading", "true", "true");
    rm.verifyVerifyDataDisplayedInColumn("Artifact Type", "SYS HSI Heading", "true", "false");
    rm.verifyVerifyDataDisplayedInColumn("Artifact Type", "Type not found", "false", "false");
    rm.verifyVerifyDataDisplayedInColumn("Artifact Type", "Type not found", "false", "true");
  }
  
  /**
   * Unit test for
   * {@link RMArtifactsPageVerification#verifyIsExistingIDAndURLInOneColumnFromExcelXLSXExport(String, String, String, String, String)}
   * Case 1: expectResult as true - return true <br>
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyIsExistingIDAndURLInOneColumnFromExcelXLSXExport() {
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    rm.verifyIsExistingIDAndURLInOneColumnFromExcelXLSXExport(path, "0", "5", "true", "true");
  }

  /**
   * Unit test for
   * {@link RMArtifactsPageVerification#verifyIsExistingIDAndURLInOneColumnFromExcelXLSXExport(String, String, String, String, String)}
   * Case 2: expectResult as false - return false <br>
   *
   * @author LPH1HC
   */
  @Test
  public void testVerIfyIsExistingIDAndURLInOneColumnFromExcelXLSXExport() {
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    rm.verifyIsExistingIDAndURLInOneColumnFromExcelXLSXExport(path, "0", "5", "false", "false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyEditArtifactName(String, String, String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyEditArtifactName() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyEditArtifactName("test", "test111111", "test111111");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyEditArtifactName(String, String, String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerIfyEditArtifactName() {
    loadPage("dng/getRmAttributeValues.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyEditArtifactName("test", "test111111", "test111112");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyOpenCreateTermDialogByRightClick(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyOpenCreateTermDialogByRightClick() {
    loadPage("dng/openCreateTermRightClick02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyOpenCreateTermDialogByRightClick("true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyOpenCreateTermDialogByRightClick(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerIfyOpenCreateTermDialogByRightClick() {
    loadPage("dng/openCreateTermRightClick02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyOpenCreateTermDialogByRightClick("false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyCreateNewTerm(Map,String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyCreateNewTerm() {
    loadPage("dng/createTerm04.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("Initial_Content", "123");
    additionalParams.put("Artifact_Type", "Actor (Represents a role that a human, hardware device, or another ...)");
    rm.verifyCreateNewTerm(additionalParams, "true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyCreateNewTerm(Map,String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerIfyCreateNewTerm() {
    loadPage("dng/createTerm04.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("Initial_Content", "123");
    additionalParams.put("Artifact_Type", "Actor (Represents a role that a human, hardware device, or another ...)");
    rm.verifyCreateNewTerm(additionalParams, "false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyEditArtifactIDFilter(String,String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyEditArtifactIDFilter() {
    loadPage("dng/EditArtifactIDFilter04.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyEditArtifactIDFilter("253823", "true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyEditArtifactIDFilter(String,String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerIfyEditArtifactIDFilter() {
    loadPage("dng/EditArtifactIDFilter04.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyEditArtifactIDFilter("253823", "false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyIsTermExistingLookUpTermDialog(String, String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyIsTermExistingLookUpTermDialog() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsTermExistingLookUpTermDialog("test_term_content", "true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyIsTermExistingLookUpTermDialog(String, String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerIfyIsTermExistingLookUpTermDialog() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsTermExistingLookUpTermDialog("123", "false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyOpenLookUpTermDialogByRightClick(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerifyOpenLookUpTermDialogByRightClick() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyOpenLookUpTermDialogByRightClick("true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyOpenLookUpTermDialogByRightClick(String)}
   *
   * @author LPH1HC
   */
  @Test
  public void testVerIfyOpenLookUpTermDialogByRightClick() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyOpenLookUpTermDialogByRightClick("false");
  }

  /**
   * <p>
   * Unit test for {@link RMArtifactsPageVerification#verifyRemoveColumnFromTable(String,String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyRemoveColumnFromTable() {
    loadPage("dng/removeColumnFromTable.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyRemoveColumnFromTable("Validated By", "true");
  }

  /**
   * <p>
   * Unit test for {@link RMArtifactsPageVerification#verifyRemoveColumnFromTable(String,String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyRemoveColumnFromTable() {
    loadPage("dng/removeColumnFromTable.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyRemoveColumnFromTable("ID", "false");
  }

  /**
   * <p>
   * Unit test for {@link RMArtifactsPageVerification#verifyClickSaveChangesToThisView(String)} <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyClickSaveChangesToThisView() {
    loadPage("dng/expandArtifactSection.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyClickSaveChangesToThisView("true");
  }

  /**
   * <p>
   * Unit test for {@link RMArtifactsPageVerification#verifyClickSaveChangesToThisView(String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyClickSaveChangesToThisView() {
    loadPage("dng/clickSaveChangesToThisView.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyClickSaveChangesToThisView("false");
  }

  /**
   * @author LPH1HC <br>
   *         Unit test for
   *         {@link RMArtifactsPageVerification#verifyIsAllTheCountValuesMatchingWithTheOriginalValue(String,String)}
   *         <br>
   */
  @Test
  public void testVerifyIsAllTheCountValuesMatchingWithTheOriginalValue() {
    loadPage("dng/isAllTheCountValuesMatchingWithTheOriginalValue01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsAllTheCountValuesMatchingWithTheOriginalValue("15", "true");
  }

  /**
   * @author LPH1HC <br>
   *         Unit test for
   *         {@link RMArtifactsPageVerification#verifyIsAllTheCountValuesMatchingWithTheOriginalValue(String,String)}
   *         <br>
   */
  @Test
  public void testVerIfyIsAllTheCountValuesMatchingWithTheOriginalValue() {
    loadPage("dng/isAllTheCountValuesMatchingWithTheOriginalValue01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsAllTheCountValuesMatchingWithTheOriginalValue("10", "false");
  }

  /**
   * @author LPH1HC <br>
   *         Unit test for
   *         {@link RMArtifactsPageVerification#verifyIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow(String)}
   *         <br>
   */
  @Test
  public void testVerifyIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow() {
    loadPage("dng/IsPassedStatusOfValidatedByLinkExistingInOneArtifactRow_PASSED.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow("true");
  }

  /**
   * @author LPH1HC <br>
   *         Unit test for
   *         {@link RMArtifactsPageVerification#verifyIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow(String)}
   *         <br>
   */
  @Test
  public void testVerIfyIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow() {
    loadPage("dng/IsPassedStatusOfValidatedByLinkExistingInOneArtifactRow_FAILED.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsPassedStatusOfValidatedByLinkExistingInOneArtifactRow("false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyVerifyNoErrorMessageDisplay(String)}.<br>
   */
  @Test
  public void testVerifyNoErrorMessageDisplay() {
    loadPage("dng/LookUpTerm01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyVerifyNoErrorMessageDisplay("true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyIsConditionSetInFilter(String, String)}.<br>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyIsConditionSetInFilter() {
    loadPage("dng/test_filterCondition.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsConditionSetInFilter("Full Text - - test", "true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyOverrideTheLockedArtifact(String)}.<br>
   * Passed case
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyOverrideTheLockedArtifact() {
    loadPage("dng/ClickToOverrideTheLock_03_UnLockedArtifactSuccessfully.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyOverrideTheLockedArtifact("true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyOverrideTheLockedArtifact(String)}.<br>
   * Failed case
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyOverrideTheLockedArtifact() {
    loadPage("dng/ClickToOverrideTheLock_01_LockedArtifactIcon.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyOverrideTheLockedArtifact("false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyRemoveFilterByCondition(String, String)}.<br>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyRemoveFilterByCondition() {
    loadPage("dng/removeFilterByCondition_03_RemovedFilter.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyRemoveFilterByCondition("Artifact Type - is any of - SW STIL Information, SW STIL Requirement", "");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyIsColumnHeaderDisplayed(String, String)}.<br>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyIsColumnHeaderDisplayed() {
    loadPage("dng/GetColumnHeaderInView_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsColumnHeaderDisplayed("Modified By", "true");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyIsColumnHeaderDisplayed(String, String)}.<br>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyIsColumnHeaderDisplayed() {
    loadPage("dng/GetColumnHeaderInView_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyIsColumnHeaderDisplayed("Validated By", "false");
  }

  /**
   * Unit test for {@link RMArtifactsPageVerification#verifyGetCurrentURL(String)}.<br>
   * @author VDY1HC
   */
  @Test
  public void testVerifyGetCurrentURL() {
    loadPage("dng/GetColumnHeaderInView_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyGetCurrentURL("GetColumnHeaderInView_01.html");
  }

  /**
   * Unit test for method {@link RMArtifactsPageVerification#verifyHoverOnArtifactLink(String, String, String, String, String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyHoverOnArtifactLink() {
    loadPage("dng/HoverOnArtifactLink02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyHoverOnArtifactLink("1132128","Satisfied By","1132127:Module For Automation Testing TS_25933","4","");
  }

  /**
   * Unit test for method {@link RMArtifactsPageVerification#verifyClickOnCloseButtonOnPopup(String)}
   * @author VDY1HC
   */
  @Test
  public void testVerifyClickOnCloseButtonOnPopup() {
    loadPage("dng/HoverOnArtifactLink01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyClickOnCloseButtonOnPopup("");
  }

  /**
   * Unit test for method ${@link RMArtifactsPageVerification#verifyExportView(String, String, String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyExportView() {
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyExportView("test", "XLS", "/export.xls");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyExportView(String, String, String)} <br>
   * <p>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyExportView() {
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyExportView("test", "XLS", "/export.csv");
  }

  /**
   * <p>
   * Unit test for method
   * ${@link RMArtifactsPageVerification#verifyVerifyAttributeValueOfArtifactDisplayedInXLSFile(String,String,String, String, String)}
   * <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyVerifyAttributeValueOfArtifactDisplayedInXLSFile() {
    String fileNameAndPath =
        Paths.get(RMConstants.IMPORT_FILE_LOCATION + "Artifact_Excel2.xls").toAbsolutePath().toString();
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyVerifyAttributeValueOfArtifactDisplayedInXLSFile("CAN communication interface", fileNameAndPath, "10",
        "Primary Text", "true");
  }

  /**
   * <p>
   * Unit test for method
   * ${@link RMArtifactsPageVerification#verifyVerifyAttributeValueOfArtifactDisplayedInXLSFile(String,String,String, String, String)}
   * <br>
   * Cover for the failed case.<br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyVerifyAttributeValueOfArtifactDisplayedInXLSFile() {
    String fileNameAndPath =
        Paths.get(RMConstants.IMPORT_FILE_LOCATION + "Artifact_Excel2.xls").toAbsolutePath().toString();
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyVerifyAttributeValueOfArtifactDisplayedInXLSFile("CAN communication interface", fileNameAndPath, "20",
        "Primary Text", "false");
  }

  /**
   * <p>
   * Unit test for method
   * ${@link RMArtifactsPageVerification#verifyVerifyTestCaseStatusUnderValidateByAttributeOfArtifact(String,String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyVerifyTestCaseStatusUnderValidateByAttributeOfArtifact() {
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyVerifyTestCaseStatusUnderValidateByAttributeOfArtifact("1051958",
        "47863: Test case 2 for automation testing", "Failed", "true");
  }

  /**
   * <p>
   * Unit test for method
   * ${@link RMArtifactsPageVerification#verifyAddFilterForAttributeName(String, String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyAddFilterForAttributeName() {
    loadPage("dng/AddFilterForAttributeName.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyAddFilterForAttributeName("Artifact Type", "TypeC-System", "true");
  }

  /**
   * <p>
   * Unit test for method
   * ${@link RMArtifactsPageVerification#verifyCollapseFilters(String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyCollapseFilters() {
    loadPage("dng/verifyCollapseFilters.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyCollapseFilters("true");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyHoverOnHyperLink(String,String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyVerifyHoverOnHyperLink() {
    loadPage("dng/hoverOnHyperLink2.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyHoverOnHyperLink("661554", "nT100A3655", "474709: Operating point - T100A3655", "true");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyHoverOnHyperLink(String,String, String, String)}
   * <p>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyVerifyHoverOnHyperLink() {
    loadPage("dng/hoverOnHyperLink1.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    rm.verifyHoverOnHyperLink("661554", "nT100A3655", "474709: Operating point - T100A3655", "false");
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyVerifyHyperlinkDisplayed(String, String, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyVerIfyHyperlinkDisplayed() {
    loadPage("dng/hoverOnHyperLink1.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyVerifyHyperlinkDisplayed("661554", "nT100A3655", "true").getState());
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyVerifyHyperlinkDisplayed(String, String, String)}
   * <p>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyVerifyHyperlinkDisplayed() {
    loadPage("dng/hoverOnHyperLink1.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("FAILED", rm.verifyVerifyHyperlinkDisplayed("661554", "nT100A3655", "false").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyClickOnSelectAllModules(String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyClickOnSelectAllModules() {
    loadPage("dng/testVerifyClickOnSelectAllModules.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyClickOnSelectAllModules("6", "true").getState());
  }
  
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyRemoveLinkOfArtifactFromTable(String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyRemoveLinkOfArtifactFromTable() {
    loadPage("dng/test.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyRemoveLinkOfArtifactFromTable("true", "true").getState());
  }
  
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyRemoveLinkOnModuleWithId(String, String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyClickOnRemoveLinkOnModuleWithId() {
    loadPage("dng/test.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyRemoveLinkOnModuleWithId("1439718", "Component: DefaultComponent", "true").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyNavigateToAuditHistory(String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyNavigateToAuditHistory() {
    loadPage("dng/testNavigateToAuditHistory_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyNavigateToAuditHistory("2194457", "true").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyHoverOnLinksByText(String, String)}
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyHoverOnLinksByText() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyHoverOnLinksByText("6281", "").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyHoverOnLinksByText(String, String)}
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyHoverOnLinksByText() {
    loadPage("dng/HoverOnLinksByText_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("FAILED", rm.verifyHoverOnLinksByText("6281", "").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyCheckLinkWithTextOnQuickInfo(String, String)}
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyCheckLinkWithTextOnQuickInfo() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyCheckLinkWithTextOnQuickInfo("678325: For classifying and prioritizing artefacts the attribute <Safety Classification> is used:", "true").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyCheckLinkWithTextOnQuickInfo(String, String)}
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerIfyCheckLinkWithTextOnQuickInfo() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("FAILED", rm.verifyCheckLinkWithTextOnQuickInfo("678325: For classifying and prioritizing artefacts the attribute <Safety Classification> is used:", "false").getState());
  }

  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyIsExistingTextInArtifactContent(String, String)}
   * <p>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyIsExistingTextInArtifactContent() {
    loadPage("dng/IsExistingTextInArtifactContent_01.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyIsExistingTextInArtifactContent("For having optimal memory ressource usage the VLE compiler option must be used for building the application SW.", "true").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyDeleteComment(String, String, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyDeleteComment() {
    loadPage("dng/testVerifyDeleteComment.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    assertEquals("PASSED", rm.verifyDeleteComment("824506", "Test Comment", "true").getState());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyEditArtifactContent(Map, String)}
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyEditArtifactContent() {
    loadPage("dng/testVerifyEditArtifactContent.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put("Artifact ID", "824506");
    clickToPage.put("Select Type Section", "Comments");
    clickToPage.put("Select Item", "Comments");
    clickToPage.put("Sub Item Selection", "Create comment for Artifact...");
    clickToPage.put("User ID", "DYC9SI");
    assertNotNull(ravt);
    assertEquals("PASSED", ravt.verifyEditArtifactContent(clickToPage, "true").getState());
  }

  /**
   * Unit test for method ${@link RMArtifactsPageVerification#verifyClickOnFolder(String, String)}
   * @author KYY1HC
   */
  @Test
  public void testVerifyClickOnFolder() {
    loadPage("dng/ClickOnFolder_02.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    assertEquals("PASSED", ravt.verifyClickOnFolder("AE Doors Development (RM)", "").getState().toString());
    assertEquals("FAILED", ravt.verifyClickOnFolder("AE DC exporter", "").getState().toString());
  }
  
  /**
   * <p>
   * Unit test for method ${@link RMArtifactsPageVerification#verifyVerifyFolderHierarchy}
   * <p>
   *
   * @author LTU7HC
   */
  @Test
  public void testVerifyVerifyFolderHierarchy() {
    loadPage("dng/rm_folder_hirarchy.html");
    RMArtifactsPageVerification rm = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams1 = new HashMap<>();
    additionalParams1.put("rootFolderName", "AE Doors Development (RM)");
    additionalParams1.put("childLv1", "AE_ENP_ALM");
    additionalParams1.put("childLv2", "DNG");
    additionalParams1.put("childLv3",
        "Acceptance Criteria on Requiremt Exchange artifacts," +
        " Copy of Acceptance Criteria on Requiremt Exchange artifacts");
    Map<String, String> additionalParams2 = new HashMap<>();
    additionalParams2.put("rootFolderName", "AE Doors Development (RM)");
    additionalParams2.put("childLv1", "AE_ENP_ALM");
    additionalParams2.put("childLv2", "DNG_");
    additionalParams2.put("childLv3",
        "Acceptance Criteria on Requiremt Exchange artifacts," +
        " Copy of Acceptance Criteria on Requiremt Exchange artifacts");
    assertEquals("PASSED", rm.verifyVerifyFolderHierarchy(additionalParams1, "true").getState().toString());
    assertEquals("FAILED", rm.verifyVerifyFolderHierarchy(additionalParams2, "false").getState().toString());
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyNavigateToArtifactsPage}.
   */
  @Test
  public void testNavigateToArtifactsPage() {
    loadPage("dng/createArtifact.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyNavigateToArtifactsPage("true");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyNavigateToArtifactsPage}.
   */
  @Test
  public void testnavigateToArtifactsPage() {
    loadPage("dng/get_artifact_id_name.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyNavigateToArtifactsPage("false");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyGetArtifactName}.
   */
  @Test
  public void testVerifyGetArtifactName() {
    loadPage("dng/get_artifact_id_name.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetArtifactName("Requirements links");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyGetArtifactName}.
   */
  @Test
  public void testverifyGetArtifactName() {
    loadPage("dng/get_artifact_id_name.html");
    RMArtifactsPageVerification ravt = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(ravt);
    ravt.verifyGetArtifactName("test");
  }
  /**
   * <p>
   * Unit  test to verify method {@link RMArtifactsPageVerification#verifySelectOptionFromCreateDropDown(String, String)}
   */
  @Test
  public void testVerifySelectOptionFromCreateDropDown() {
  loadPage("dng/createNewArtifact.html");
  RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
  assertNotNull(rmav);
  rmav.verifySelectOptionFromCreateDropDown("Module", "true");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyGetArtifactQuickInfo(Map, String)}.
   */
  @Test
  public void testVerifyGetArtifactQuickInfo() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Format", "Text");
    rmav.verifyGetArtifactQuickInfo(additionalParams,"KEY :Format VALUE :Text");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyInsertArtifactNameInArtifactsPage(String,String)}.
   */
  @Test
  public void testVerifyInsertArtifactNameInArtifactsPage() {
    loadPage("dng/insert_artifact_name_in_artifactspage.html");
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    rmav.verifyInsertArtifactNameInArtifactsPage("test","true");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyInsertArtifactNameInArtifactsPage(String,String)}.
   */
  @Test
  public void testverifyInsertArtifactNameInArtifactsPage() {
    loadPage("dng/insert_artifact_name_in_artifactspage.html");
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    rmav.verifyInsertArtifactNameInArtifactsPage("316522","true");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyGetLocationOfAnArtifact(String, String)}.
   */
  @Test
  public void testVerifyGetLocationOfAnArtifact() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    rmav.verifyGetLocationOfAnArtifact("test","true");
  }
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyGetLocationOfAnArtifact(String, String)}.
   */
  @Test
  public void testverifyGetLocationOfAnArtifact() {
    loadPage("dng/HoverOnLinksByText_02.html");
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    rmav.verifyGetLocationOfAnArtifact("true","true");
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyCountNumberOfArtifactDisplayed(String)}.
   */
  @Test
  public void testVerifyCountNumberOfArtifactDisplayed() {
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    assertEquals("PASSED", rmav.verifyCountNumberOfArtifactDisplayed("10").getState().toString());
    assertEquals("FAILED", rmav.verifyCountNumberOfArtifactDisplayed(" ").getState().toString());
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyGetCurrentDisplayedArtifactsNumber(String)}.
   */
  @Test
  public void testVerifyGetCurrentDisplayedArtifactsNumber() {
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    assertEquals("PASSED", rmav.verifyGetCurrentDisplayedArtifactsNumber("10").getState().toString());
    assertEquals("FAILED", rmav.verifyGetCurrentDisplayedArtifactsNumber(" ").getState().toString());
  }
  
  /**
   * <p>
   * Unit test to verify {@link RMArtifactsPageVerification#verifyGetAllArtifactsNumber(String)}.
   */
  @Test
  public void testVerifyGetAllArtifactsNumber() {
    RMArtifactsPageVerification rmav = getJazzPageFactory().getRMArtifactsPageVerification();
    assertNotNull(rmav);
    assertEquals("PASSED", rmav.verifyGetAllArtifactsNumber("100").getState().toString());
    assertEquals("FAILED", rmav.verifyGetAllArtifactsNumber(" ").getState().toString());
  }
}