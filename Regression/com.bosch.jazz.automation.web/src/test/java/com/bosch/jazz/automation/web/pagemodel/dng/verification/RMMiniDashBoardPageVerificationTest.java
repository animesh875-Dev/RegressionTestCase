/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng.verification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.dng.RMMiniDashBoardPageVerification;

/**
 * Unit tests for the RMDashBoardPage.
 */
public class RMMiniDashBoardPageVerificationTest extends AbstractFrameworkUnitTest {

  /**
   * Unit test for method
   * ${@link RMMiniDashBoardPageVerification#verifyExpandArtifactInModuleExplorer(String, String,String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyExpandArtifactInModuleExplorer() {
    loadPage("dng/verifyExpandArtifactInModuleExplorer.html");
    RMMiniDashBoardPageVerification rm = getJazzPageFactory().getRMMiniDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyExpandArtifactInModuleExplorer("References", "ECU abstraction", "true");
  }

  /**
   * Unit test for method
   * ${@link RMMiniDashBoardPageVerification#verifyExpandArtifactInModuleExplorer(String, String,String)} <br>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyExpandArtifactInModuleExplorer() {
    loadPage("dng/verifyExpandArtifactInModuleExplorer.html");
    RMMiniDashBoardPageVerification rm = getJazzPageFactory().getRMMiniDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyExpandArtifactInModuleExplorer("References", "abc", "true");
  }

  /**
   * Unit test for method
   * ${@link RMMiniDashBoardPageVerification#verifyIsWidgetDisplayedInMiniDashboard(String, String,String)} <br>
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifyIsWidgetDisplayedInMiniDashboard() {
    loadPage("dng/verifyExpandArtifactInModuleExplorer.html");
    RMMiniDashBoardPageVerification rm = getJazzPageFactory().getRMMiniDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyIsWidgetDisplayedInMiniDashboard("Module Explorer", "true", "true");
  }

  /**
   * Unit test for method
   * ${@link RMMiniDashBoardPageVerification#verifyIsWidgetDisplayedInMiniDashboard(String, String,String)} <br>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfyIsWidgetDisplayedInMiniDashboard() {
    loadPage("dng/verifyExpandArtifactInModuleExplorer.html");
    RMMiniDashBoardPageVerification rm = getJazzPageFactory().getRMMiniDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifyIsWidgetDisplayedInMiniDashboard("Project Area", "true", "false");
  }

  /**
   * Unit test for method ${@link RMMiniDashBoardPageVerification#verifySelectArtifactInModuleExplorer(String,String)}
   *
   * @author NVV1HC
   */
  @Test
  public void testVerifySelectArtifactInModuleExplorer() {
    loadPage("dng/selectArtifactInModuleExplorer.html");
    RMMiniDashBoardPageVerification rm = getJazzPageFactory().getRMMiniDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifySelectArtifactInModuleExplorer("References", "6255");
  }

  /**
   * Unit test for method ${@link RMMiniDashBoardPageVerification#verifySelectArtifactInModuleExplorer(String,String)}
   * <br>
   * Cover for the failed case
   *
   * @author NVV1HC
   */
  @Test
  public void testVerIfySelectArtifactInModuleExplorer() {
    loadPage("dng/selectArtifactInModuleExplorer.html");
    RMMiniDashBoardPageVerification rm = getJazzPageFactory().getRMMiniDashBoardPageVerification();
    assertNotNull(rm);
    rm.verifySelectArtifactInModuleExplorer("References", "999");
  }
}
