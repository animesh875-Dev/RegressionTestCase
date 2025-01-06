/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.dng;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit test coverage for the methods of RMDashBoardPage.
 */
public class RMMiniDashBoardPageTest extends AbstractFrameworkUnitTest {

  /**
   * <p>
   * Unit test cover for {@link RMMiniDashBoardPage#expandArtifactInModuleExplorer(String)}.
   *
   * @author NVV1HC
   */
  @Test
  public void testExpandArtifactInModuleExplorer() {
    loadPage("dng/expandArtifactInModuleExplorer.html");
    RMMiniDashBoardPage rm = getJazzPageFactory().getRMMiniDashBoardPage();
    assertNotNull(rm);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    rm.expandArtifactInModuleExplorer("References");
  }

  /**
   * <p>
   * Unit test cover for {@link RMMiniDashBoardPage#selectArtifactInModuleExplorer(String)}.
   *
   * @author NVV1HC
   */
  @Test
  public void testSelectArtifactInModuleExplorer() {
    loadPage("dng/selectArtifactInModuleExplorer.html");
    RMMiniDashBoardPage rm = getJazzPageFactory().getRMMiniDashBoardPage();
    assertNotNull(rm);
    rm.selectArtifactInModuleExplorer("References");
  }

  /**
   * <p>
   * Unit test cover for {@link RMMiniDashBoardPage#isWidgetDisplayedInMiniDashboard(String)}.
   *
   * @author NVV1HC
   */
  @Test
  public void testIsWidgetDisplayedInMiniDashboard() {
    loadPage("dng/selectArtifactInModuleExplorer.html");
    RMMiniDashBoardPage rm = getJazzPageFactory().getRMMiniDashBoardPage();
    assertNotNull(rm);
    rm.isWidgetDisplayedInMiniDashboard("Module Explorer");
  }
}
