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
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMManageComponentPropertiesPageVerification;


/**
 * @author PDU6HC
 * Unit tests for the RMManageComponentPropertiesPageVerification.
 */
public class RMManageComponentPropertiesPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * <p>Unit test coverage for {@link RMManageComponentPropertiesPageVerification#verifyInputNameForComponentProperties(String, String)}.
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyInputNameForComponentProperties() {
    loadPage("dng/defination_filed.html");
    RMManageComponentPropertiesPageVerification rmmcppv =
        getJazzPageFactory().getRMManageComponentPropertiesPageVerification();
    assertNotNull(rmmcppv);
    rmmcppv.verifyInputNameForComponentProperties("TypeA-System", "TypeA-System");
    assertEquals("PASSED", rmmcppv.verifyInputNameForComponentProperties("TypeA-System", "TypeA-System").getState());
  }
  
  /**
   * <p>Unit test coverage for {@link RMManageComponentPropertiesPageVerification#verifyCreateNewArtifactType(Map, String)}.
   *
   * @author PDU6HC
   */
  @Test
  public void testVerifyCreateNewArtifactType() {
    loadPage("dng/testVerifyCreateNewArtifactType.html");
    RMManageComponentPropertiesPageVerification rmmcppv =
        getJazzPageFactory().getRMManageComponentPropertiesPageVerification();
    assertNotNull(rmmcppv);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("DEFAULT_ARTIFACT_FORMAT", "Text");
    additionalParams.put("URI", "http://www.example.com/type1");
    additionalParams.put("ATTRIBUTE_DATA_TYPE", "");
    additionalParams.put("ARTIFACT_TYPE", "TypeA-System");
//    rmmcppv.verifyCreateNewArtifactType(additionalParams, "true");
    assertEquals("PASSED", rmmcppv.verifyCreateNewArtifactType(additionalParams,"B", "true").getState());
  }

  /**
   * <p>Unit test coverage for {@link RMManageComponentPropertiesPageVerification#verifyDeleteArtifactType(String, String)}.
   * @author PDU6HC
   */
  @Test
  public void testVerifyDeleteArtifactType() {
    loadPage("dng/testDeleteArtifactType04.html");
    RMManageComponentPropertiesPageVerification rmmcppv =
        getJazzPageFactory().getRMManageComponentPropertiesPageVerification();
    assertNotNull(rmmcppv);
    rmmcppv.verifyDeleteArtifactType("B", "true");
    assertEquals("PASSED", rmmcppv.verifyDeleteArtifactType("B", "true").getState());
  }
  
  /**
   * <p>Unit test coverage for {@link RMManageComponentPropertiesPageVerification#verifySelectComponentPropertiesType(String, String)}.
   * @author PDU6HC
   */
  @Test
  public void testVerifySelectComponentPropertiesType() {
    loadPage("dng/testVerifySelectComponentPropertiesType.html");
    RMManageComponentPropertiesPageVerification rmmcppv =
        getJazzPageFactory().getRMManageComponentPropertiesPageVerification();
    assertNotNull(rmmcppv);
    rmmcppv.verifySelectComponentPropertiesType("Artifact Types", "true");
    assertEquals("PASSED", rmmcppv.verifySelectComponentPropertiesType("Artifact Types", "true").getState());
  }
  
  /**
   * <p>Unit test coverage for 
   * {@link RMManageComponentPropertiesPageVerification#verifyClickOnDeleteAnArtifactType(String, String)}.
   * @author PDU6HC
   */
  @Test
  public void testVerifyClickOnDeleteAnArtifactType() {
    loadPage("dng/testDeleteArtifactType03.html");
    RMManageComponentPropertiesPageVerification rmmcppv =
        getJazzPageFactory().getRMManageComponentPropertiesPageVerification();
    assertNotNull(rmmcppv);
    rmmcppv.verifyClickOnDeleteAnArtifactType("B", "true");
    assertEquals("PASSED", rmmcppv.verifySelectComponentPropertiesType("Artifact Types", "true").getState());
  }
  
  /**
   * <p>Unit test coverage for 
   * {@link RMManageComponentPropertiesPageVerification#verifyIsNoticeMessageDisplay(String)}.
   * @author PDU6HC
   */
  @Test
  public void testVerifyIsNoticeMessageDisplay() {
    loadPage("dng/testIsNoticeMessageDisplay.html");
    RMManageComponentPropertiesPageVerification rmmcppv =
        getJazzPageFactory().getRMManageComponentPropertiesPageVerification();
    assertNotNull(rmmcppv);
    rmmcppv.verifyIsNoticeMessageDisplay("true");
    assertEquals("PASSED", rmmcppv.verifyIsNoticeMessageDisplay("true").getState());
  }
  
  /**
   * Unit test coverage for {@link RMManageComponentPropertiesPageVerification#verifyVerifyPropertiesTabsOrder}.<br>
   * @author VDY1HC
   */
  @Test
  public void testVerifyVerifyPropertiesTabsOrder() {
    String listOfTabs = "Artifact Types;Artifact Attributes;Attribute Data Types;" +
        "Link Types;Link Constraints;Link Validity;Module Options;Templates;" +
        "Team Ownership Overview;Configuration Management;ReqIF;Migration;Mapping Contexts";
    loadPage("dng/verifyPropertiesTabsOrder_01.html");
    RMManageComponentPropertiesPageVerification rmmcppv =
        getJazzPageFactory().getRMManageComponentPropertiesPageVerification();
    assertNotNull(rmmcppv);
    assertEquals("PASSED", rmmcppv.verifyVerifyPropertiesTabsOrder(listOfTabs, "true").getState());
  }
}
















