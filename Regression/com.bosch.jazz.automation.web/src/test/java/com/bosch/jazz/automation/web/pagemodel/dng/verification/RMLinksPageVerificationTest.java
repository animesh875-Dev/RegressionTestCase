/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.constants.RQMMainMenusEnum.TestProperties;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactContextMenu;
import com.bosch.jazz.automation.web.common.constants.RequirementsManagementEnum.ArtifactProperties;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMConstants;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMLinksPageVerification;

/**
 * Represents the RM Links Page this is common for Artifacts, Modules and Collections pages.
 */
public class RMLinksPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenLinkByFromTab(Map,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenLinkByFromTab() {
    loadPage("dng/open_menu.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(ArtifactProperties.ID_NAME.toString(), "Requirements Management");
    assertNotNull(ravt);
    ravt.verifyOpenLinkByFromTab(clickToPage, "");
  }

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenLinkByFromTab(Map,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenLinkByFromTabOne() {
    loadPage("dng/open_menu.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(ArtifactProperties.ID_NAME.toString(), "test");
    assertNotNull(ravt);
    ravt.verifyOpenLinkByFromTab(clickToPage, "true");
  }

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenLinkByFromTab(Map,String)}.
   *
   * <p>Loads test case page in quality management and checks if getTestcaseID() gets the proper test case id or not.
   */
  @Test
  public void testVerifyOpenLinkByFromTabTwo() {
    loadPage("dng/open_menu.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> clickToPage = new LinkedHashMap<String, String>();
    clickToPage.put(ArtifactProperties.ID_NAME.toString(), "test");
    assertNotNull(ravt);
    ravt.verifyOpenLinkByFromTab(clickToPage, "false");
  }

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenValidatedByLink(Map,String)}.
   *
   * <p>Load Test Case/ Test Script/ Test Plan page (QM page) and check Requirement ID in QM page is correct or not.
   */
  @Test
  public void testVerifyOpenValidatedByLinkOne() {
    loadPage("dng/VerifyOpenValidatedByLink_RequirementCollectionLinks_02.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "1686992");
    assertNotNull(ravt);
    ravt.verifyOpenValidatedByLink(additionalParams,"true");
  }

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenValidatedByLink(Map,String)}.
   *
   * <p>Load Test Case/ Test Script/ Test Plan page (QM page) and check Requirement ID in QM page is correct or not.
   */
  @Test
  public void testVerifyOpenValidatedByLinkTwo() {
    loadPage("dng/VerifyOpenValidatedByLink_RequirementLinks_02.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "1686995");
    assertNotNull(ravt);
    ravt.verifyOpenValidatedByLink(additionalParams, "true");
  }

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenImplementedByLink(Map,String)}.
   *
   * <p>Load CCM Plan page (CCM page) and check Requirement ID in CCM page is correct or not.
   */
  @Test
  public void testVerifyOpenImplementedByLinkOne() {
    loadPage("dng/verifyOpenImplementByLink_02.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "1963377");
    assertNotNull(ravt);
    ravt.verifyOpenValidatedByLink(additionalParams,"true");
  }

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenImplementedByLink(Map,String)}.
   *
   * <p>Load CCM Plan page (CCM page) and check Requirement ID in CCM page is correct or not.
   */
  @Test
  public void testVerifyOpenImplementedByLinkTwo() {
    loadPage("dng/verifyOpenImplementByLink_04.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "991832");
    assertNotNull(ravt);
    ravt.verifyOpenValidatedByLink(additionalParams, "true");
  }

  /**
   * <p>Unit test coverage for {@link RMLinksPageVerification#verifyOpenSatisfiedByArchitectureElementLink(Map,String)}.
   *
   * <p>Load DM page of Component/ Project, access to Links tab and check Requirement ID in DM page is correct or not.
   */
  @Test
  public void testVerifyOpenSatisfiedByArchitectureElementLink() {
    loadPage("dng/OpenSatisfiedArchitectureElementLink_03.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "1963733");
    assertNotNull(ravt);
    ravt.verifyOpenSatisfiedByArchitectureElementLink(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage fro ${@link RMLinksPageVerification#verifyRemoveLinkOfArtifactFromTable(Map, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyRemoveLinkOfArtifactFromTable() {
    loadPage("dng/verifyRemoveLinkOfArtifactFromTable.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "47863");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Validated By");
    assertNotNull(ravt);
    ravt.verifyRemoveLinkOfArtifactFromTable(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage fro ${@link RMLinksPageVerification#verifyRemoveLinkOfArtifactFromTable(Map, String)} Cover for
   * the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyRemoveLinkOfArtifactFromTable() {
    loadPage("dng/verifyRemoveLinkOfArtifactFromTable.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID.toString(), "47863");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(), "Contents");
    assertNotNull(ravt);
    ravt.verifyRemoveLinkOfArtifactFromTable(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage fro ${@link RMLinksPageVerification#verifyClickOnContextSubmenu(String, String)} Cover for the
   * failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyClickOnContextSubmenu() {
    loadPage("dng/verifyClickOnContextSubmenu.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextSubmenu("Validated By", "true");
  }

  /**
   * <p>
   * Unit test coverage for ${@link RMLinksPageVerification#verifyClickOnContextSubmenu(String, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyClickOnContextSubmenu() {
    loadPage("dng/verifyClickOnContextSubmenu.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(ravt);
    ravt.verifyClickOnContextSubmenu("Open Artifact", "true");
  }

  /**
   * <p>
   * Unit test coverage for ${@link RMLinksPageVerification#verifyChooseExistingValidatedLink(Map, String)}
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyChooseExistingValidatedLink() {
    loadPage("dng/verifyChooseExistingValidatedLink.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Create or existing", "Choose Existing");
    additionalParams.put(additionalParams.get(TestProperties.ID.toString()), "47864");
    additionalParams.put(additionalParams.get(RMConstants.PROJECT_AREA), "Control Unit System Development (Tests)");
    assertNotNull(ravt);
    ravt.verifyChooseExistingValidatedLink(additionalParams, "true");
  }

  /**
   * <p>
   * Unit test coverage for ${@link RMLinksPageVerification#verifyChooseExistingValidatedLink(Map, String)} <br>
   * Cover for the failed case
   * <p>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyChooseExistingValidatedLink() {
    loadPage("dng/verifyChooseExistingValidatedLink.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Create or existing", "Choose Existing");
    additionalParams.put(additionalParams.get(TestProperties.ID.toString()), "11111");
    additionalParams.put(additionalParams.get(RMConstants.PROJECT_AREA), "Control Unit System Development (Tests)");
    assertNotNull(ravt);
    ravt.verifyChooseExistingValidatedLink(additionalParams, "true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyGetNumberOfArtifactLinksInSideBarArea(String, String)}
   * 
   * @author LPH1HC
   */
  @Test
  public void testVerifyGetNumberOfArtifactLinksInSideBarArea() {
    loadPage("dng/GetArtifactLinks_01.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    rm.verifyGetNumberOfArtifactLinksInSideBarArea("0","true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyGetNumberOfArtifactLinksInSideBarArea(String, String)}
   * 
   * @author LPH1HC
   */
  @Test
  public void testVerIfyGetNumberOfArtifactLinksInSideBarArea() {
    loadPage("dng/GetArtifactLinks_01.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    rm.verifyGetNumberOfArtifactLinksInSideBarArea("0","false");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyIsWarningMessageExistsInArtifactLinksSideBarArea(String,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerifyIsWarningMessageExistsInArtifactLinksSideBarArea() {
    loadPage("dng/GetArtifactLinks_01.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    rm.verifyIsWarningMessageExistsInArtifactLinksSideBarArea("false","true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyIsWarningMessageExistsInArtifactLinksSideBarArea(String,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerIfyIsWarningMessageExistsInArtifactLinksSideBarArea() {
    loadPage("dng/GetArtifactLinks_01.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    rm.verifyIsWarningMessageExistsInArtifactLinksSideBarArea("false","false");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyChooseExistingImplementedLink(Map,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerifyChooseExistingImplementedLink() {
    loadPage("dng/addImplementedByLink.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(RMConstants.PROJECT_AREA, "47863");
    additionalParams.put(CCMConstants.WORKITEM_ID, "Charging Dummy");
    rm.verifyChooseExistingImplementedLink(additionalParams,"true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyChooseExistingImplementedLink(Map,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerIfyChooseExistingImplementedLink() {
    loadPage("dng/addImplementedByLink.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(RMConstants.PROJECT_AREA, "47863");
    additionalParams.put(CCMConstants.WORKITEM_ID, "Charging Dummy");
    rm.verifyChooseExistingImplementedLink(additionalParams,"false");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyclickOnAddArtifactLinkFromRightSideBar(Map,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerifyclickOnAddArtifactLinkFromRightSideBar() {
    loadPage("dng/clickRighSideBar.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("_sidebarArea", "47863");
    additionalParams.put("Add Link to Artifact", "Validated By");
    rm.verifyclickOnAddArtifactLinkFromRightSideBar(additionalParams,"true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyclickOnAddArtifactLinkFromRightSideBar(Map,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerIfyclickOnAddArtifactLinkFromRightSideBar() {
    loadPage("dng/clickRighSideBar.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("_sidebarArea", "47863");
    additionalParams.put("Add Link to Artifact", "Validated By");
    rm.verifyclickOnAddArtifactLinkFromRightSideBar(additionalParams,"false");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyClickOnAttributeLinkFromRightSideBar(Map,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerifyClickOnAttributeLinkFromRightSideBar() {
    loadPage("dng/clickRightSideBar.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);    
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("_sidebarArea","Module Links");
    rm.verifyClickOnAttributeLinkFromRightSideBar(additionalParams,"true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyClickOnAttributeLinkFromRightSideBar(Map,String)}
   * @author LPH1HC
   */
  @Test
  public void testVerIfyClickOnAttributeLinkFromRightSideBar() {
    loadPage("dng/clickRightSideBar.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);    
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("_sidebarArea","Module Links");
    rm.verifyClickOnAttributeLinkFromRightSideBar(additionalParams,"false");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyClickOnContextMenuOrSubMenuForArtifact(Map,String)}
   * @author KYY1HC
   */
  @Test
  public void testVerifyClickOnContextMenuOrSubMenuForArtifact() {
    loadPage("dng/VerifyClickOnContextMenuOrSubMenuForArtifact_01_CreateLinkDisplayed.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);    
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ARTIFACT_CONTEXT_MENU.toString(),"Add Link to Artifact");
    additionalParams.put(ArtifactContextMenu.ADD_LINK_TO_ARTIFACT.toString(),"Validated By");
    rm.verifyClickOnContextMenuOrSubMenuForArtifact(additionalParams,"true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifySelectModuleInCreateLinkDialog(String,String)}
   * @author PDU6HC
   */
  @Test
  public void testVerifySelectModuleInCreateLinkDialog() {
    loadPage("dng/verifySelectModuleInCreateLinkDialog.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    rm.verifySelectModuleInCreateLinkDialog("547554","true");
  }
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyChooseSatisfiedOrLinkTo(Map,String)}
   * @author PDU6HC
   */
  @Test
  public void testVerifyChooseSatisfiedOrLinkTo() {
    loadPage("dng/verifyChooseSatisfiedOrLinkTo.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("id","547556");
    additionalParams.put("moduleId","547554");
    rm.verifyChooseSatisfiedOrLinkTo(additionalParams, "true");
  }
  
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyChooseExistingSatisfiedArchitectureLink(Map,String)}
   * @author PDU6HC
   */
  @Test
  public void testVerifyChooseExistingSatisfiedArchitectureLink() {
    loadPage("dng/test.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("Add Link to Artifact", "Satisfied By Architecture Element");
    additionalParams.put("Artifact Container", "BR-Inverter BK1 Sys (DM)");
    additionalParams.put("Diagram Name", "PG_Hirarchy_of_model");
    additionalParams.put("Artifact Component", "");
    rm.verifyChooseExistingSatisfiedArchitectureLink(additionalParams, "true");
  }
  
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyIsArtifactLinkPresentforSelectedArtifact(String, String, String, String)}
   * @author PDU6HC
   */
  @Test
  public void testVerifyIsArtifactLinkPresentforSelectedArtifact() {
    loadPage("dng/test.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    rm.verifyIsArtifactLinkPresentforSelectedArtifact("","1439718", "Component: DefaultComponent");
  }
  
  
  /**
   * Unit test for {@link RMLinksPageVerification#verifyVerifyLinkExistedForMultipleArtifacts(Map, String)}
   * @author PDU6HC
   */
  @Test
  public void testVerifyVerifyLinkExistedForMultipleArtifacts() {
    loadPage("dng/test.html");
    RMLinksPageVerification rm = getJazzPageFactory().getRMLinksPageVerification();
    assertNotNull(rm);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("linkName","Component: DefaultComponent");
    additionalParams.put("id1","2189991");
    additionalParams.put("id2","1439718");
    additionalParams.put("id3","643387");
    additionalParams.put("id4","611264");
    additionalParams.put("id5","6244");
    additionalParams.put("id6","60");
    rm.verifyVerifyLinkExistedForMultipleArtifacts(additionalParams, "true");
  }
  
  /**
   * Unit test coverage for {@link RMLinksPageVerification#verifyOpenSatisfiesLink(Map,String)}.</br>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyOpenSatisfiesLink() {
    loadPage("dng/VerifyOpenSatisfiesLink_01.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "679621:Zur");
    assertNotNull(ravt);
    assertEquals("PASSED" ,ravt.verifyOpenSatisfiesLink(additionalParams, "true").getState());
  }

  /**
   * Unit test coverage for {@link RMLinksPageVerification#verifyOpenSatisfiedByLink(Map,String)}.</br>
   *
   * @author KYY1HC
   */
  @Test
  public void testVerifyOpenSatisfiedByLink() {
    loadPage("dng/VerifyOpenSatisfiedByLink_01.html");
    RMLinksPageVerification ravt = getJazzPageFactory().getRMLinksPageVerification();
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put(ArtifactProperties.ID_NAME.toString(), "6593:Range");
    assertNotNull(ravt);
    assertEquals("PASSED" ,ravt.verifyOpenSatisfiedByLink(additionalParams, "true").getState());
  }
}
