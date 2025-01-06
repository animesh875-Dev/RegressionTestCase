/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMCreateQueryPage;

/**
 * @author UUM4KOR
 */
public class SystemInfoTest extends AbstractFrameworkUnitTest{


  /**
   * @throws Exception is thrown if file is not found.
   */
  @Test
  public void testLoadSystemInfo() throws Exception {
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String filePath = new File("src\\main\\resources\\excel_webtest\\environment.properties").getAbsolutePath();
    SystemInfo.loadSystemInfo(filePath);
  }

  /**
   * @throws Exception is thrown if file is not found
   */
  @Test
  public void testLoadSystemInfoOne() throws Exception {
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    String filePath = new File("src\\main\\resources\\excel_webtest\\environmentone.properties").getAbsolutePath();
    SystemInfo.loadSystemInfo(filePath);
  }
  /**
   * 
   */
  @Test
  public void testWriteSystemInfo() {
    CCMCreateQueryPage query = getJazzPageFactory().getCCMQueryPage();
    assertNotNull(query);
    Map<String, String> additionalParams = new LinkedHashMap<String, String>();
    additionalParams.put("testArtifactDesignDescriptionValue", "Test Suite Design Description");
    SystemInfo.writeSystemInfo(additionalParams);
  }
}
