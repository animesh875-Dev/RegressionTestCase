/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author HCM6KOR test class for EnvironmentProvider.
 */
public class EnvironmentProviderTest {

  /**
   * Test method for loadGlobalEnv in EnvironmentProvider class.
   *
   * @throws IOException thrown by the called method.
   */
  @Test
  public void testLoadGlobalEnv() throws IOException {
    Map<String, String> globalEnv = EnvironmentProvider.loadGlobalEnv();
    assertNotNull(globalEnv);
    assertEquals("first value for unit test - Don't change this line", globalEnv.get("TESTVAL1"));
    assertEquals("second value for unit test - Don't change this line", globalEnv.get("TESTVAL2"));
  }

  /**
   * Test method for mergeSuiteConfig in EnvironmentProvider class.
   */
  @Test
  public void testMergeSuiteConfig() {
    HashMap<String, String> globalEnv = new HashMap<>();
    globalEnv.put("ServerName", "https://rb-alm-02-d.de.bosch.com/ccm/");
    globalEnv.put("ProjectAreaName", "CAT Test Automation PA(Change Management) Formal");
    HashMap<String, String> suiteConfig = new HashMap<>();
    suiteConfig.put("ServerName", "https://rb-alm-02-d.de.bosch.com/am/");
    suiteConfig.put("UserName", "UnkownUser");
    Map<String, String> env = EnvironmentProvider.mergeSuiteConfig(globalEnv, suiteConfig);
    assertNotNull(env);
    assertEquals("https://rb-alm-02-d.de.bosch.com/am/", env.get("ServerName"));
  }
}
