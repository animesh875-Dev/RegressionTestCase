/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.TestProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.AddLinkToArtifact;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ModuleEnums;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;

/**
 * Unit tests for the RMLinksPageTest.
 */
public class RMLinksPageTest extends AbstractFrameworkUnitTest {

  /**
   * The ExpectedException rule allows you to verify that your code throws a specific exception.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Loads requirement management module link page, it will throw exception if you not pass link.
   */
  @Test
  public void testCreateLinkOrChooseExistingImplementedLinkExist() {
    loadPage("dng/create_linkor_choose_existing_implementedby_7.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/create_linkor_choose_existing_implementedby_4.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), "Add Link to Artifact");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(),
        AddLinkToArtifact.IMPLEMENTED_BY.toString());
    additionalParams.put(CCMConstants.WORKITEM_ID, "119508");
    additionalParams.put("ProjectArea", "SYS-TEST-com.bosch.rtc.tmpl.Formal_2016.1.0_IBM_6.0.2");
    rm.chooseExistingItemFromCreateLinkDialog(additionalParams);
  }

  /**
   * Loads requirement management link page, by choosing create new button and create new test case and add it to the
   * artifact
   */
  @Test
  public void testCreateLinkOrChooseExistingValidatedByNew() {
    loadPage("dng/createlink_validatedby.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/createlink_validatedby.html");
    /*
     * clickNumberToPagePath.put(2, null); clickNumberToPagePath.put(3, null); clickNumberToPagePath.put(4, null);
     */
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(TestProperties.CREATE_OR_EXISTING.toString(), "Create New");
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), "Add Link to Artifact");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(),
        AddLinkToArtifact.VALIDATED_BY.toString());
    additionalParams.put("Test case name", "Test Case Sample");
    rm.chooseExistingItemFromCreateLinkDialog(additionalParams);
  }

  /**
   * Loads requirement management link page, by choosing create new button and create new test case and add it to the
   * artifact
   */
  @Test
  public void testCreateLinkOrChooseExistingValidatedByExisting() {
    loadPage("dng/choose_existing_validated_by_link_1.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/choose_existing_validated_by_link_2.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, "dng/choose_existing_validated_by_link_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("ProjectArea", "IBM Enhancements - UBK ALM (QM)");
    additionalParams.put(TestProperties.CREATE_OR_EXISTING.toString(), "Choose Existing");
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), "Add Link to Artifact");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(),
        AddLinkToArtifact.VALIDATED_BY.toString());
    additionalParams.put(TestProperties.ID.toString(), "16754");
    rm.chooseExistingItemFromCreateLinkDialog(additionalParams);
  }

  /**
   * Loads requirement management artifact link page,add satisfied by link to the artifact.
   */
  @Test
  public void testCreateLinkOrChooseExistingConditionLinkTypeOrSatisfied() {
    loadPage("dng/create_linkor_choose_existing_window.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/click_on_ok_button_1.html");
    clickNumberToPagePath.put(3, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "118703");
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), "Add Link to Artifact");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(),
        AddLinkToArtifact.SATISFIED_BY.toString());
    rm.chooseExistingItemFromCreateLinkDialog(additionalParams);
  }

  /**
   * Loads requirement management artifact link page,adding satisfied by /link to link by filtering filter the passed
   * artifact id and clicks on the matching artifact id if the same artifact was already added it will throw warning
   * message and clicks on cancell button
   */
  @Test
  public void testCreateLinkOrChooseExistingLink() {
    loadPage("dng/create_linkor_choose_existing_satisfiedby.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/click_on_ok_button_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "93649");
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), "Add Link to Artifact");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), AddLinkToArtifact.LINK_TO.toString());
    rm.chooseExistingItemFromCreateLinkDialog(additionalParams);
  }

  /**
   * Loads requirement management explore diagrame view page, checks if the Fetching link is not visible then it returen
   * true
   */
  @Test
  public void testDiagramViewOfRelatedLinks() {
    loadPage("dng/diagram_view_of_related_links_module.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/diagram_viewOf_relatedlinks_filter.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/diagram_viewOf_relatedlinks_showlink.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Links Buttons Container", "Links Explorer");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Link To");
    assertTrue(rm.showDiagramForSelectedLinkType(additionalParams));
  }

  /**
   * Loads requirement management explore diagrame view page, checks if the Fetching link is visible then it returen
   * false
   */
  @Test
  public void testDiagramViewOfRelatedLinksFetchLink() {
    loadPage("dng/diagram_view_of_related_links_module.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/diagram_viewOf_relatedlinks_filter.html");
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, "dng/fetching links.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Links Buttons Container", "Links Explorer");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Link To");
    assertFalse(rm.showDiagramForSelectedLinkType(additionalParams));
  }

  /**
   * Loads requirement management module page, checks if the Fetching link is not visible then it returen true
   */
  @Test
  public void testClickOnAddArtifactLinkFromRightSideBar() {
    loadPage("dng/module_links_addlink.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/module_links_addlink.html");
    clickNumberToPagePath.put(2, "dng/module_links_addlink.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), "Module Links");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Link To");
    additionalParams.put(RMConstants.MODULELINK, "Add Link to Artifact");
    rm.clickOnAddArtifactLinkFromRightSideBar(additionalParams);
  }

  /**
   * Loads an requirement management page, click on module links open verify the link.
   */
  @Test
  public void testValidateLinkFromInsideModule() {
    loadPage("dng/validate_link_from_module_0.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/validate_link_from_module.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), ModuleEnums.MODULE_LINKS.toString());
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Link To");
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "62: BRM");
    assertTrue(rm.openLinkByFromTab(additionalParams));
  }

  /**
   * Loads an requirement management page, click on module links open verify the link.
   */
  @Test (expected = Exception.class)
  public void testValidateLinkFromInsideModuleForException() {
    loadPage("dng/validate_link_from_module_0.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/validate_link_from_module.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), ModuleEnums.MODULE_LINKS.toString());
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Link To");
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "62: BRM ");
    // this.thrown.expect(WebAutomationException.class);
    // this.thrown.expectMessage(CoreMatchers.startsWith("Link with id "));
    rm.openLinkByFromTab(additionalParams);
  }

  /**
   * Loads an requirement management page, click on links under the specified coloum and verify the link.
   */
  @Test
  public void testOpenImplementedBy() {
    loadPage("dng/open_implementedBy.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.ID.toString(), "1091351");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Implemented By");
    additionalParams.put(CCMConstants.WORKITEM_ID, "77276");
    rm.openImplementedByLink(additionalParams);
  }

  /**
   * Loads an requirement management page, click on links under the specified coloum and verify the link.
   */
  @Test
  public void testValidateLinkvalidatedBy() {
    loadPage("dng/Validate_Link_Implemented_By.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/Validate_Link_Validated_By_3.html");
    clickNumberToPagePath.put(2, "dng/Validate_Link_Validated_By_3.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.ID.toString(), "19326");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Validated By");
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "520: TCS_1");
    rm.openValidatedByLink(additionalParams);
  }

  /**
   * Unit test for method {@link RMLinksPage#openSatisfiedByLink(Map)}
   * @author KYY1HC
   */
  @Test
  public void testOpenSatisfiedByLink() {
    loadPage("dng/validate_link_Satisfied_By_req.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.ID.toString(), "118704");
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "118703");
    rm.openSatisfiedByLink(additionalParams);
  }

  /**
   * Unit test for method {@link RMLinksPage#openSatisfiesLink(Map)}
   * @author KYY1HC
   */
  @Test
  public void testOpenSatisfiesLink() {
    loadPage("dng/OpenSatisfiesLink_01.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.ID.toString(), "763605");
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "679621");
    rm.openSatisfiesLink(additionalParams);
  }

  /**
   * Loads an requirement management page, ClickOnContextMenu and Open menu.
   */
  @Test
  public void testClickOnContextMenuOrSubMenuForArtifact() {
    loadPage("dng/click_on_context_menu_and_open_submenu.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/click_on_context_menu_and_open_submenu_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "192517");
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(), "Add Link to Artifact");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), AddLinkToArtifact.LINK_TO.toString());
    rm.clickOnContextMenuOfArtifactInModule(additionalParams);
  }

  /**
   *
   */
  @Test
  public void testRemoveLink() {
    loadPage("dng/remove_link_if_exists_1.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/remove_link_if_exists_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), ModuleEnums.LINKS.toString());
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Link From");
    additionalParams.put(ArtifactProperties.ID_NAME.toString(),
        "In Module: Test Module 11_12_2018_14_12_698");
    rm.removeLinkIfExists(additionalParams);
  }

  /**
   *
   */
  @Test
  public void testValidateRequirementLink() {
    loadPage("dng/validate_requirement_link_1.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/validate_requirement_link.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.ID.toString(), "19324");
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "19324: Test_Artf1");
    additionalParams.put(TestProperties.ID.toString(), "520: TCS_1");
    rm.openRequirementLink(additionalParams);
  }

  /**
   *
   */
  @Test
  public void testClickOnContextSubmenu() {
    loadPage("dng/click_on_context_submenu.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    rm.clickOnContextSubmenu("Copy Artifact");
  }

  /**
   *
   */
  @Test
  public void testClickOnOkButton() {
    loadPage("dng/click_on_ok_button.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/click_on_ok_button_1.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.createLinkIfNotAlreadyExists();
  }

  /**
   *
   */
  @Test
  public void testClicksOnOkButton() {
    loadPage("dng/click_on_ok_button_1.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/click_on_ok_button.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.createLinkIfNotAlreadyExists();
  }

  /**
   *
   */
  @Test
  public void testvalidateImplementsLinks() {
    loadPage("dng/validate_implements_links_4.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/validate_implements_links_5.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(CCMConstants.WORKITEM_ID, "274475");
    additionalParams.put(ArtifactProperties.ID.toString(), "1143065");
    rm.openImplementsLinks(additionalParams);
  }

  /**
   *
   */
  @Test
  public void testvalidatesImplementsLinks() {
    loadPage("dng/validate_implements_links_4.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/validate_implements_links_5.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(CCMConstants.WORKITEM_ID, "274476");
    additionalParams.put(ArtifactProperties.ID.toString(), "1143065");
    rm.openImplementsLinks(additionalParams);
  }

  /**
   *
   */
  @Test
  public void testvalidatedImplementsLinks() {
    loadPage("dng/validate_implements_links_4.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/validate_implements_links_5.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(CCMConstants.WORKITEM_ID, "274475");
    additionalParams.put(ArtifactProperties.ID.toString(), "1143065");
    rm.openImplementsLinks(additionalParams);
  }

  /**
   *
   */
  @Test
  public void testGetAllLinksFromSelectedView() {
    loadPage("dng/getLinksOfArtifactFromRightSide.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ModuleEnums.SIDE_BAR_AREA.toString(), "Artifact Links");
    rm.getAllLinksFromSelectedView(additionalParams);

  }

  /**
   * <p>
   * Unit test to verify {@link RMLinksPage#chooseSatisfiedOrLinkTo(Map)}.
   * 
   * @author KYY1HC
   */
  @Test
  public void testChooseLinkTo() {
    loadPage("dng/chooseLinkTo_06.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, "dng/chooseLinkTo_07.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.MODULE_ID.toString(), "676539");
    additionalParams.put(ArtifactProperties.ID.toString(), "676550");
    additionalParams.put(ArtifactProperties.ARTIFACT_COMPONENT.toString(), "rbd_briBk1_sys (BR-Inverter BK1 (RM))");
    rm.chooseSatisfiedOrLinkTo(additionalParams);
  }

  /**
   *
   */
  @Test
  public void testOpenValidatedByLink() {
    loadPage("dng/OpenValidatedByLink_01.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "dng/OpenValidatedByLink_02.html");
    clickNumberToPagePath.put(3, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "891: Software Test Plan plant container BMW");
    additionalParams.put(ArtifactProperties.ID.toString(), "1686995");
    rm.openValidatedByLink(additionalParams);
  }


  /**
   *
   */
  @Test
  public void testOpenSatisfiedByArchitectureElementLink() {
    loadPage("dng/OpenSatisfiedArchitectureElementLink_01.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/OpenSatisfiedArchitectureElementLink_02.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "Project: briBk10_pf_SwArchitecture");
    additionalParams.put(ArtifactProperties.ID.toString(), "1963733");
    rm.openSatisfiedByArchitectureElementLink(additionalParams);
  }

  /**
   * Add Satisfied Architecture Link. {@link RMLinksPage#chooseExistingSatisfiedArchitectureLink} <br>
   * Note: chooseExistingSatisfiedArchitectureLink is private method, call from chooseExistingItemFromCreateLinkDialog
   */
  @Test
  public void testChooseExistingSatisfiedArchitectureLink() {
    loadPage("dng/chooseSatisfiedArchitectureLink_01.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    // T
    clickNumberToPagePath.put(1, "dng/chooseSatisfiedArchitectureLink_01.html");
    clickNumberToPagePath.put(2, "dng/chooseSatisfiedArchitectureLink_02.html");
    clickNumberToPagePath.put(3, "dng/chooseSatisfiedArchitectureLink_03.html");
    clickNumberToPagePath.put(4, "dng/chooseSatisfiedArchitectureLink_04.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new HashMap<>();
    additionalParams.put("Add Link to Artifact", "Satisfied By Architecture Element");
    additionalParams.put("Artifact Container", "BR-Inverter BK1 Sys (DM)");
    additionalParams.put("Diagram Name", "PG_Hirarchy_of_model");
    additionalParams.put("Artifact Component", "");
    rm.chooseExistingItemFromCreateLinkDialog(additionalParams);
  }

  /**
   * Unit test for {@link RMLinksPage#getNumberOfArtifactLinksInSideBarArea}
   *
   * @author LPH1HC
   */
  @Test
  public void testGetNumberOfArtifactLinksInSideBarArea() {
    loadPage("dng/GetArtifactLinks_01.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    String count = rm.getNumberOfArtifactLinksInSideBarArea();
    assertEquals(count, "2");
  }

  /**
   * Unit test for {@link RMLinksPage#isWarningMessageExistsInArtifactLinksSideBarArea(String)}
   * @author LPH1HC
   */
  @Test
  public void testIsWarningMessageExistsInArtifactsLinkSideBarArea() {
    loadPage("dng/GetArtifactLinks_01.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    boolean check = rm.isWarningMessageExistsInArtifactLinksSideBarArea("false");
    assertTrue(check);
  }



  /**
   * Unit test for {@link RMLinksPage#clickOnAttributeLinkFromRightSideBar(Map)}
   * @author LPH1HC
   */
  @Test
  public void testClickOnAttributeLinkFromRightSideBar()  {
    loadPage("dng/clickRightSideBar.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/clickRightSideBar.html");
    clickNumberToPagePath.put(2, "dng/clickRightSideBar.html");
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("_sidebarArea","Module Links");
    rm.clickOnAttributeLinkFromRightSideBar(additionalParams);
  }
  

  /**
   * Unit test for {@link RMLinksPage#selectModuleInCreateLinkDialog(String)}
   * @author PDU6HC
   */
  @Test
  public void testSelectModuleInCreateLinkDialog()  {
    loadPage("dng/selectAndInputModuleCreateLink_01.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/selectAndInputModuleCreateLink_02.html");
    clickNumberToPagePath.put(2, "dng/selectAndInputModuleCreateLink_03.html");
    clickNumberToPagePath.put(3, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    rm.selectModuleInCreateLinkDialog("547554");
  }
  
  /**
   * Unit test for {@link RMLinksPage#isArtifactLinkPresentforSelectedArtifact(String, String)}
   * @author PDU6HC
   */
  @Test
  public void testIsArtifactLinkPresentforSelectedArtifact()  {
    loadPage("dng/testIsArtifactLinkPresentforSelectedArtifact.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    rm.isArtifactLinkPresentforSelectedArtifact("1439718", "Component: DefaultComponent");
  }
  
  
  /**
   * Unit test for {@link RMLinksPage#verifyLinkExistedForMultipleArtifacts(Map)}
   * @author PDU6HC
   */
  @Test
  public void testVerifyLinkExistedForMultipleArtifacts()  {
    loadPage("dng/testVerifyLinkExistedForMultipleArtifacts.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, null);
    clickNumberToPagePath.put(3, null);
    clickNumberToPagePath.put(4, null);
    clickNumberToPagePath.put(5, null);
    clickNumberToPagePath.put(6, null);
    loadNewPageOnActionsCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linkName","Component: DefaultComponent");
    additionalParams.put("id1","2189991");
    additionalParams.put("id2","1439718");
    additionalParams.put("id3","643387");
    additionalParams.put("id4","611264");
    additionalParams.put("id5","6244");
    additionalParams.put("id6","60");
    rm.verifyLinkExistedForMultipleArtifacts(additionalParams);
  }
  
  /**
   * Unit test for {@link RMLinksPage#removeLinkOfArtifactFromTable(Map)}
   * @author LTU7HC
   */
  @Test
  public void testRemoveLinkOfArtifactFromTable()  {
    loadPage("dng/removeLinkOfArtifactFromTable.html");
    RMLinksPage rm = getJazzPageFactory().getRMLinksPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "dng/removeLinkOfArtifactFromTable_Delete_Confirmation.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("id","4295");
    additionalParams.put("Add Link to Artifact","Validated By");
    rm.removeLinkOfArtifactFromTable(additionalParams);
  }
}
