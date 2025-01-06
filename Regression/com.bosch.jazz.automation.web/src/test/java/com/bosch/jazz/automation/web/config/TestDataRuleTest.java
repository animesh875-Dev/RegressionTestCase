/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.config;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.InvalidKeyException;

import org.junit.Rule;
import org.junit.Test;

import com.bosch.jazz.automation.web.excel.TestDataRule;


/**
 * @author hrt5kor Junit test cases to cover code quality of TestDataRule.
 */
public class TestDataRuleTest {

  /**
   * Rule holding the configuration data required for the test method.
   */
  @Rule
  public TestDataRule testDataRule = new TestDataRule();

  /**
   * @throws InvalidKeyException if the configuration name not found. this method verifies the configuration and
   *           environment properties.
   * @throws IOException environment
   */
  @Test
  public void testGetFilePathBySearchingAllDirectories() throws InvalidKeyException, IOException {
    assertEquals("first value for unit test - Don't change this line", this.testDataRule.getConfigData("TESTVAL1"));
    assertEquals("second value for unit test - Don't change this line", this.testDataRule.getConfigData("TESTVAL2"));
  }
}
