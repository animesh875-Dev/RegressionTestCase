/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.dng.RMModulePage;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMModulePageVerification;

/**
 * Represents the RM Module Page this is common for Artifacts, Modules pages.
 */
public class RMModulePageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyLogout(String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyLogout() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyLogout("");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyLogout(String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerIfyLogout() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyLogout("");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnJazzSpanButtons() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnJazzSpanButtons("OK", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnJazzSpanButtonss() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnJazzSpanButtons("", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyGetDownededPdfFileNameAndPath(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetDownededPdfFileNameAndPath() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetDownededPdfFileNameAndPath("", "ok");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyGetDownededPdfFileNameAndPath(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetDownededPdfFileNameAndPaths() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetDownededPdfFileNameAndPath("", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyVerifyExcelID(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyVerifyExcelID() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyExcelID("", "ok", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyVerifyExcelID(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyVerifyExcelIDs() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyExcelID("", "", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyGetArtifactUrl(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetArtifactUrl() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetArtifactUrl("", "ok");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyGetArtifactUrl(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetArtifactUrls() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetArtifactUrl("", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyNavigateToArtifactURL(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyNavigateToArtifactURL() {
    loadPage("rqm/click_on_testcase_link_from_test_execution_result.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyNavigateToArtifactURL("16754", "","");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyNavigateToArtifactURL(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyNavigateToArtifactURLs() {
    loadPage("rqm/click_on_testcase_link_from_test_execution_result.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyNavigateToArtifactURL("1234", "","");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyGetCSVArtifactURL(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetCSVArtifactURL() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetCSVArtifactURL("", "ok");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyGetCSVArtifactURL(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyGetCSVArtifactURLs() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetCSVArtifactURL("", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyClickOnDropdownOption(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnDropdownOption() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnDropdownOption("OK", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyClickOnDropdownOption(String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyClickOnDropdownOptions() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnDropdownOption("", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyVerifyCSVid(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyVerifyCSVid() {
    loadPage("ccm/login_with_given_password.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyCSVid("test", "", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyVerifyCSVid(String,String,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyVerifyCSVids() {
    loadPage("dng/open_menu.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyCSVid("", "", "");
  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyDuplicateArtifactToFolder(Map,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyDuplicateArtifactToFolder() {
    loadPage("dng/duplicateArtifact_generateSuccess.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("newName:", "COPY_ARTIFACT");
    additionalParams.put("destinationFolder:", "PlatForm");
    additionalParams.put("isCheckCopyLinks:", "true");
    additionalParams.put("isCheckCopyTags:", "true");
    additionalParams.put("duplicationPolicy:", "Duplicate no artifacts (reuse all artifacts)");
    ravt.verifyDuplicateArtifactToFolder(additionalParams, "true");

  }

  /**
   * <p>Unit test coverage for {@link RMModulePageVerification#verifyGetNumberOfArtifactShowInTable(String)}.
   *
   */
  @Test
  public void testVerifyCountNumberArtifactShowing() {
    loadPage("dng/countNumberArtifactShowing_01.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetNumberOfArtifactShowInTable("3159");
  }

  /**
   * <p>
   * Unit test covers for ${@link RMModulePageVerification#verifyGetModuleID(String)}
   * <p>
   */
  @Test
  public void testVerifyGetModuleID() {
    loadPage("dng/verifyGetModuleID.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetModuleID("2003085");
  }

  /**
   * <p>
   * Unit test covers for ${@link RMModulePageVerification#verifyGetModuleID(String)} <br>
   * Covers for the failed case
   * <p>
   */
  @Test
  public void testVerIfyGetModuleID() {
    loadPage("dng/verifyGetModuleID.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetModuleID("abc12");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyCreateANewArtifact(Map, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyCreateANewArtifact() {
    loadPage("dng/verifyCreateANewArtifact.html");
    Map<String, String> additionalParam = new LinkedHashMap<>();
    additionalParam.put("ARTIFACT_NAME", "Test artifact");
    additionalParam.put("ARTIFACT_TYPE", "Heading");
    additionalParam.put("ARTIFACT_FORMAT", "Text");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyCreateANewArtifact(additionalParam, "1026178");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyGetArtifactIDByArtifactContent(String, String)}
   * <p>
   * @author NCY3HC
   */
  @Test
  public void testVerifyGetArtifactIDByArtifactContent() {
    loadPage("dng/getArtifactID.html");
    RMModulePageVerification rmmpv = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(rmmpv);
    rmmpv.verifyGetArtifactIDByArtifactContent("1714831", "What is automation test ?");
    
  }
  /**
   * <p>
   * Unit test covergae for {@link RMModulePageVerification#verifyVerifyInsertExistingArtifactStructureDisplayCorrectly(Map, String)}
   * @author NCY3HC
   */
  @Test
  public void testVerifyVerifyInsertExistingArtifactStructureDisplayCorrectly() {
    loadPage("dng/verifyInsertedArtifact.html");
    RMModulePageVerification rmmpv= getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(rmmpv);
    Map<String, String> additionalParam = new LinkedHashMap<>();
    additionalParam.put("TARGET_ARTIFACT_ID", "1714831");
    additionalParam.put("TYPE_OF_INSERTION", "Before...");
    rmmpv.verifyVerifyInsertExistingArtifactStructureDisplayCorrectly(additionalParam,"true");
       
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyCreateANewArtifact(Map, String)}. <br>
   * Cover for the failled case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyCreateANewArtifact() {
    loadPage("dng/verifyCreateANewArtifact.html");
    Map<String, String> additionalParam = new LinkedHashMap<>();
    additionalParam.put("ARTIFACT_NAME", "Test artifact");
    additionalParam.put("ARTIFACT_TYPE", "Heading");
    additionalParam.put("ARTIFACT_FORMAT", "Text");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyCreateANewArtifact(additionalParam, "10261av");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMModulePageVerification#verifyEditArtifact_insertContent(String, String, String, String, String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyEditArtifact_insertContent() {
    loadPage("dng/verifyEditArtifact_insertContent.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "dng/verifyEditArtifact_insertContent_01.html");
    clickToPage.put(3, "dng/verifyEditArtifact_insertContent_02.html");
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyEditArtifact_insertContent("298843", "Artifact", "1002188",
        "Heading artifact for automation testing TS_25750", "true");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMModulePageVerification#verifyEditArtifact_insertContent(String, String, String, String, String)}. <br>
   * Cover for the failled case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyEditArtifact_insertContent() {
    loadPage("dng/verifyEditArtifact_insertContent.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    clickToPage.put(2, "dng/verifyEditArtifact_insertContent_01.html");
    clickToPage.put(3, "dng/verifyEditArtifact_insertContent_02.html");
    clickToPage.put(4, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyEditArtifact_insertContent("298843", "Artifact", "1002188", "Test", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyInsertNewArtifact(Map,String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyInsertNewArtifact() {
    loadPage("dng/verifyInsertNewArtifact.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    Map<String, String> params = new HashMap<String, String>();
    params.put("targetArtifactID", "298843");
    params.put("typeOfInsertion", "After");
    params.put("artifactContent", "Test");
    params.put("artifactType", "MO_FUNC_REQ");
    ravt.verifyInsertNewArtifact(params, "1026179");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyInsertNewArtifact(Map,String)}. <br>
   * Cover for the failled case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyInsertNewArtifact() {
    loadPage("dng/verifyInsertNewArtifact.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    Map<String, String> params = new HashMap<String, String>();
    params.put("targetArtifactID", "298843");
    params.put("typeOfInsertion", "After");
    params.put("artifactContent", "Test");
    params.put("artifactType", "MO_FUNC_REQ");
    ravt.verifyInsertNewArtifact(params, "134654");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyLookUpTerm(String, String, String, String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyLookUpTerm() {
    loadPage("dng/verifyLookUpTerm.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyLookUpTerm("1026179", "Test 123", "3", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyLookUpTerm(String, String, String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyLookUpTerm() {
    loadPage("dng/verifyLookUpTerm.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyLookUpTerm("1026179", "Test 123", "1", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyEditArtifactContent(Map, String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyEditArtifactContent() {
    loadPage("dng/verifyEditArtifactContent.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<String, String> additionalParam = new HashMap<String, String>();
    additionalParam.put("EDIT_OPTION", "Replace new artifact content");
    additionalParam.put("ARTIFACT_ID", "1026179");
    additionalParam.put("NEW_CONTENT", "Test updated");
    assertNotNull(ravt);
    ravt.verifyEditArtifactContent(additionalParam, "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyEditArtifactContent(Map, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyEditArtifactContent() {
    loadPage("dng/verifyEditArtifactContent.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<String, String> additionalParam = new HashMap<String, String>();
    additionalParam.put("EDIT_OPTION", "Replace new artifact content");
    additionalParam.put("ARTIFACT_ID", "1026179");
    additionalParam.put("NEW_CONTENT", "Test");
    assertNotNull(ravt);
    ravt.verifyEditArtifactContent(additionalParam, "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyDeleteArtifact(String, String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyDeleteArtifact() {
    loadPage("dng/verifyDeleteArtifact_01.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/verifyDeleteArtifact.html");
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyDeleteArtifact("1020196", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyDeleteArtifact(String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyDeleteArtifact() {
    loadPage("dng/verifyDeleteArtifact_01.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/verifyDeleteArtifact_failed.html");
    clickToPage.put(2, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyDeleteArtifact("1020197", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyClickOnEditContentOfArtifact(String, String)}.
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyClickOnEditContentOfArtifact() {
    loadPage("dng/verifyClickOnEditContentOfArtifact.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditContentOfArtifact("1026179", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyClickOnEditContentOfArtifact(String, String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyClickOnEditContentOfArtifact() {
    loadPage("dng/verifyClickOnEditContentOfArtifact.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnEditContentOfArtifact("1026178", "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMModulePageVerification#verifyIsLinkDisplayedInSelectedArtifactTab(String, String,String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyIsLinkDisplayedInSelectedArtifactTab() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyIsLinkDisplayedInSelectedArtifactTab("Satisfied By Architecture Element", "SysML: SystemInterface",
        "false");
  }

  /**
   * <p>
   * Unit test coverage for
   * {@link RMModulePageVerification#verifyIsLinkDisplayedInSelectedArtifactTab(String, String,String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyIsLinkDisplayedInSelectedArtifactTab() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyIsLinkDisplayedInSelectedArtifactTab("Satisfied By Architecture Element", "SysML", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyExpandArtifactSection(String,String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyExpandArtifactSection() {
    loadPage("dng/collapseArtifactSection.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyExpandArtifactSection("1", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyExpandArtifactSection(String,String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyExpandArtifactSection() {
    loadPage("dng/expandArtifactSection.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyExpandArtifactSection("1", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyCollapseArtifactSection(String,String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyCollapseArtifactSection() {
    loadPage("dng/expandArtifactSection.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyCollapseArtifactSection("1", "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyCollapseArtifactSection(String,String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyCollapseArtifactSection() {
    loadPage("dng/collapseArtifactSection.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyCollapseArtifactSection("1", "false");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyExportModuleWithPDFOrWordDocument(Map,String)}. <br>
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyExportModuleWithPDFDocument() {
    loadPage("dng/exportModuleWithPDFDocument_03.html");
    Map<String,String> params = new HashMap<>();
    params.put("includeComments", "false");
    params.put("includeAttributes", "false");
    params.put("includeTitlesForInsertedArtifacts", "true");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyExportModuleWithPDFOrWordDocument(params, "true");
  }

  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyExportModuleWithPDFOrWordDocument(Map,String)}. <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyExportModuleWithPDFDocument() {
    loadPage("dng/exportModuleWithPDFDocument_02.html");
    Map<String, String> params = new HashMap<>();
    params.put("includeComments", "false");
    params.put("includeAttributes", "false");
    params.put("includeTitlesForInsertedArtifacts", "true");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyExportModuleWithPDFOrWordDocument(params, "false");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyInputNameForArtifactContent(String, String)}. <br>
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyInputNameForArtifactContent() {
    loadPage("dng/testInputNameForArtifactContent_01.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyInputNameForArtifactContent("2170019", "2170019");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyClickOnSearchOtherComponents(String)}. <br>
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyClickOnSearchOtherComponents() {
    loadPage("dng/testClickOnSearchOtherComponents_01.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnSearchOtherComponents("true");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyVerifyNumberOfMatchesInTermDialog(String, String)}. <br>
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyVerifyNumberOfMatchesInTermDialog() {
    loadPage("dng/testVerifyNumberOfTermMatches.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyNumberOfMatchesInTermDialog("3", "true");
  }
  
  /**
   * <p>
   * Unit test coverage for 
   * {@link RMModulePageVerification#verifyAddTermOfOtherComponentHyperLinkandSave(String, String)}. <br>
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyAddTermOfOtherComponentHyperLinkandSave() {
    loadPage("dng/test.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyAddTermOfOtherComponentHyperLinkandSave("2", "true");
  }
  
  /**
   * <p>
   * Unit test coverage for 
   * {@link RMModulePageVerification#verifyNavigateToTermHyperLink(String, String, String)}. <br>
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyNavigateToTermHyperLink() {
    loadPage("dng/testNavigateToTermHyperLink.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyNavigateToTermHyperLink("611315", "BRS", "true");
  }
  
  /**
   * <p>
   * Unit test coverage for 
   * {@link RMModulePageVerification#verifyRemoveLinkInsideModule(String, String, String)}. <br>
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyRemoveLinkInsideModule() {
    loadPage("dng/test.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyRemoveLinkInsideModule("Component: DefaultComponent","Traced By Architecture Element ", "true");
  }

  /**
   * Unit test coverage for {@link RMModulePageVerification#verifyGetModuleAttribute(String, String)}. <br>
   * @author VDY1HC
   */
  @Test
  public void testVerifyGetModuleAttribute() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetModuleAttribute("Team Ownership","ALM Test (RM)");
  }
  
  /**
   * Unit test coverage for {@link RMModulePageVerification#verifyEditArtifactTypeHSIFlagDropDownInModule(String, String, String, String)}. <br>
   * @author PDU6HC
   */
  @Test
  public void testVerifyEditArtifactTypeHSIFlagDropDownInModule() {
    loadPage("dng/testVerifyEditArtifactTypeHSIFlagInModule.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyEditArtifactTypeHSIFlagDropDownInModule("2194446", "AEn_ThermoSystems_HSI-FlagSavedToLeanComErrorStruct", "yes", "true");
  }
  
  /**
   * Unit test coverage for {@link RMModulePageVerification#verifyNavigateToModuleFromAuditHistory(String)}. <br>
   * @author PDU6HC
   */
  @Test
  public void testVerifyNavigateToModuleFromAuditHistory() {
    loadPage("dng/testVerifyNavigateToModuleFromAuditHistory.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyNavigateToModuleFromAuditHistory("true");
  }
  
  /**
   * Unit test coverage for {@link RMModulePageVerification#verifyEditMaxLb(String, String, String)}. <br>
   * @author PDU6HC
   */
  @Test
  public void testVerifyEditMaxLb() {
    loadPage("dng/testVerifyEditMaxLb.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyEditMaxLb("1921130", "26.500000", "true");
  }
  /**
   * Unit test to verify ${@link RMModulePageVerification#verifyGetNumberOfArtifactsFromExcelFile(String, String)}.
   *
   */
  @Test
  public void testVerifyGetNumberOfArtifactsFromExcelFile() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    ravt.verifyGetNumberOfArtifactsFromExcelFile(filePath,"30");
  }
  /**
   * Unit test to verify ${@link RMModulePageVerification#verifyGetNumberOfArtifactsFromExcelFile(String, String)}.
   *
   */
  @Test
  public void testVerifyGetNumberOfArtifactsFromExcelFileOne(){
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    ravt.verifyGetNumberOfArtifactsFromExcelFile(filePath,"0");
  }
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyGetURLOfLinkedArtifactFromExcelFile(String, String, String, String)}.
   *
   */
  @Test
  public void testVerifyGetURLOfLinkedArtifactFromExcelFile() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    ravt.verifyGetURLOfLinkedArtifactFromExcelFile(filePath,"Link:Validated By", "211565","true");
  }
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyGetURLOfLinkedArtifactFromExcelFile(String, String, String, String)}.
   *
   */
  @Test
  public void testverifyGetURLOfLinkedArtifactFromExcelFile() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    ravt.verifyGetURLOfLinkedArtifactFromExcelFile(filePath,"Link:Validated By", "211565",null);
  }
  /**
   * Unit test covers for method ${@link RMModulePageVerification#verifyGetSelectedArtifacts(String)}
   */
  @Test
  public void testverifyGetSelectedArtifacts() {
    loadPage("dng/get_selected_artifacts.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetSelectedArtifacts("");
  }
  /**
   * Unit test covers for method ${@link RMModulePageVerification#verifyGetSelectedArtifacts(String)}
   */
  @Test
  public void testVerifyGetSelectedArtifacts() {
    loadPage("dng/get_selected_artifacts.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyGetSelectedArtifacts(null);
  }
  /**
   * Unit test to verify ${@link RMModulePageVerification#verifyGetNumberOfArtifactsFromCSVFile(String, String)}.
   *
   */
  @Test
  public void testVerifyGetNumberOfArtifactsFromCSVFile() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    ravt.verifyGetNumberOfArtifactsFromCSVFile(filePath,"5");
  }
  /**
   * Unit test to verify ${@link RMModulePageVerification#verifyGetNumberOfArtifactsFromCSVFile(String, String)}.
   *
   */
  @Test
  public void testverifyGetNumberOfArtifactsFromCSVFile(){
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    ravt.verifyGetNumberOfArtifactsFromCSVFile(filePath,"0");
  }
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyGetURLOfLinkedArtifactFromCSVFile(String, String, String, String)}.
   *
   */
  @Test
  public void testVerifyGetUrlOfLinkedArtifactFromCSVFile() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    ravt.verifyGetURLOfLinkedArtifactFromCSVFile(filePath,"Link:Validated By", "211565","true");
  }
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyGetURLOfLinkedArtifactFromCSVFile(String, String, String, String)}.
   *
   */
  @Test
  public void testverifyGetUrlOfLinkedArtifactFromCSVFile() {
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    ravt.verifyGetURLOfLinkedArtifactFromCSVFile(filePath,"Link:Validated By", "211565",null);
  }
  
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyRemoveArtifact(String, String)}.
   *
   */
  @Test
  public void testVerifyRemoveArtifact() {
    loadPage("dng/verify_remove_artifact.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyRemoveArtifact("2488486", "true");
  }
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyRemoveArtifact(String, String)}.
   *
   */
  @Test
  public void testVerifyVerifyArtifactNotInModuleUsingModuleGoToFind() {
    loadPage("dng/testArtifactNotInModuleGoToFind.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyArtifactNotInModuleUsingModuleGoToFind("123465", "true");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyAddArtifactToModule(String,String,String)}. <br>
   * <p>
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyAddArtifactToModule() {
    loadPage("dng/testVerifyAddArtifactToModule.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "dng/testVerifyAddArtifactToModule_01.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    assertNotNull(ravt);
    ravt.verifyAddArtifactToModule("824581", "Add and Close", "true");
  }
  
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyRemoveArtifact(String, String)}.
   *
   */
  @Test
  public void testVerifyVerifyModuleStructureDisplayed() {
    loadPage("dng/verifyModuleStructureDisplayed.html");
    RMModulePage rm = getJazzPageFactory().getRMModulePage();
    assertNotNull(rm);
    String text = "1:General;1.1:Document Purpose;1.2:Document Scope;2:Terms and Items;" +
        "2.1:History;2.2:References;2.3:Definitions;2.4:Abbreviations;2.5:Symbols;" +
        "3:Modes;3.1:Example;3.2:ECU Modes;3.3:Vehicle States;3.4:Drive Modes;" +
        "3.5:Accelerator pedal characteristic Modes;3.6:Recuperation Modes (Coasting, One Pedal Mode);" +
        "3.7:Creeping Modes;3.8:Torque distribution Modes;3.9:Steering Modes;" +
        "3.10:Chassis height Modes;3.11:Damping Modes;3.12:Battery cooling Modes;" +
        "3.13:HVAC Modes;3.14:Speed limit Modes;3.15:Valet Mode;3.16:ESC/TCS Modes;3.17:Operational Modes";
    loadPage("dng/testArtifactNotInModuleGoToFind.html");
    RMModulePageVerification ravt = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(ravt);
    ravt.verifyVerifyModuleStructureDisplayedCorrectly(text, "true");
  }
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyInsertExistingArtifact(Map, String)}
   * 
   */
  @Test
  public void testVerifyInsertExistingArtifact() {
    loadPage("dng/insertExistingArtifact_4.html");
    RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("EXISTING_ARTIFACT_ID", "1714828");
    additionalParams.put("TYPE_OF_INSERTION", "Before...");
    additionalParams.put("TARGET_ARTIFACT_ID", "1714831");
    assertNotNull(rmv);
    rmv.verifyInsertExistingArtifact(additionalParams, "true"); 
    }
  
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyPasteArtifact(Map, String)}
   * 
   */
  @Test
  public void testVerifyPasteArtifact() {
    loadPage("dng/pasteArtifact3.html");
    RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("targetArtifactID", "2409481");
    additionalParams.put("typeOfInsertion", "After");
    assertNotNull(rmv);
    rmv.verifyPasteArtifact(additionalParams, "true"); 
    }
  /**
   * Unit test to verify method ${@link RMModulePageVerification#verifyUploadNewArtifact(Map, String)}
   * 
   */
  @Test
  public void testVerifyUploadNewArtifact() {
    loadPage("dng/uploadArtifact2.html");
    RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("FILE_PATH", "testImage.png");
    additionalParams.put("targetArtifactID", "2409500");
    additionalParams.put("typeOfInsertion", "After");
    assertNotNull(rmv);
    rmv.verifyUploadNewArtifact(additionalParams, "true"); 
    }
  /**
   * <p>
   * Unit test for method ${@link RMModulePageVerification#verifyClickOnAddExistingArtifactOption(String)}
   * <p>
   *
   * @author NCY3HC
   */
  @Test
  public void testVerifyClickOnAddExistingArtifactOption() {
    loadPage("dng/addExistingArtifact.html");
    RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(rmv);
    rmv.verifyClickOnAddExistingArtifactOption("true");
  }
  /**
   * Unit test to verify {@link RMModulePageVerification#verifyCreateAndOpenModule(Map, String)}.
   */
  @Test
  public void testverifyCreateAndOpenModule() {
    loadPage("dng/HoverOnLinksByText_01.html");
    RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(rmv);
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("MODULE_NAME", "Text");
    rmv.verifyCreateAndOpenModule(additionalParams,"false");
  }
  /**
   * Unit test to verify {@link RMModulePageVerification#verifyCreateAndOpenModule(Map, String)}.
   */
  @Test
  public void testVerifyCreateAndOpenModule() {
    loadPage("dng/createArtifact.html");
    RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(rmv);
    Map<String, String> additionalParams = new LinkedHashMap<>();
    additionalParams.put("MODULE_NAME", "}");
    rmv.verifyCreateAndOpenModule(additionalParams,"true");
  }
  /**
   * <p>
   * Unit test coverage for {@link RMModulePageVerification#verifyGetArtifactIDByArtifactName(String, String)}
   */
  @Test
  public void testVerifyGetArtifactIDByArtifactName() {
    loadPage("dng/getArtifactID.html");
    RMModulePageVerification rmmpv = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(rmmpv);
    rmmpv.verifyGetArtifactIDByArtifactName("1714831", "What is automation test ?");    
  }
  
  /**
   * <P>
   * Unit test coverage for {@link RMModulePageVerification#verifyGetViewNameApplied(String)}
   */
  @Test
  public void testVerifyGetViewNameApplied() {
    RMModulePageVerification rmModulePageVerification = getJazzPageFactory().getRMModulePageVerification();
    assertNotNull(rmModulePageVerification);
    loadPage("dng/view_applied.html");            
    rmModulePageVerification.verifyGetViewNameApplied("SW_SRS_briBk1_All_Default");
    rmModulePageVerification.verifyGetViewNameApplied("");
    rmModulePageVerification.verifyGetViewNameApplied(null);
   }
  
  /**
   * <P>
   * Unit test coverage for {@link RMModulePageVerification#verifyIsViewApplied(String, String, String)}
   */
  @Test
  public void testVerifyIsViewApplied() {
    RMModulePageVerification rmModulePageVerification = getJazzPageFactory().getRMModulePageVerification();
    String viewName = "SW_SRS_briBk1_All_Default";
    assertNotNull(rmModulePageVerification);
    loadPage("dng/view_applied.html");            
    rmModulePageVerification.verifyIsViewApplied(viewName, "true", "true");
    rmModulePageVerification.verifyIsViewApplied(viewName, "true", "false");
    rmModulePageVerification.verifyIsViewApplied(viewName, "false", "true");
  }
  /**
    *<P>
   * Unit test coverage for {@link RMModulePageVerification#verifyNavigateToModulePage(String, String)}.
   */
  @Test
  public void testVerifyNavigateToModulePage() {
      loadPage("dng/navigate_to_module_page.html");
      RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
      assertNotNull(rmv);
      rmv.verifyNavigateToModulePage("test_artifact_2","true");
    }
  /**
   *<P>
  * Unit test coverage for {@link RMModulePageVerification#verifyNavigateToModulePage(String, String)}.
  */
  @Test
  public void testverifyNavigateToModulePage() {
      loadPage("dng/navigate_to_module_page.html");
      RMModulePageVerification rmv = getJazzPageFactory().getRMModulePageVerification();
      assertNotNull(rmv);
      rmv.verifyNavigateToModulePage("test","false");
    }
}
