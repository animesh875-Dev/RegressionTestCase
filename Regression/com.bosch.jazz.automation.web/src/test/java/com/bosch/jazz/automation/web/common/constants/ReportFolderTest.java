/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.excel.ReportFolder;

/**
 * @author hrt5kor
 */
public class ReportFolderTest {

  /**
   * Junit test to check whether report is generated or not.
   */
  @Test
  public void testReportFolder() {
    ReportFolder.setResultsReportDirLocation(ReportFolder.createReportFolder());
    assertNotNull(ReportFolder.getResultsReportDirLocation());
  }
}
