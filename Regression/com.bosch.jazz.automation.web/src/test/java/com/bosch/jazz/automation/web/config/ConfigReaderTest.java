/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.config;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bosch.jazz.automation.web.excel.ConfigReader;

/**
 * @author HCM6KOR This is the unit test to cover the methods in ExcelReader class.
 */
public class ConfigReaderTest {

  /**
   * The ExpectedException rule allows you to verify that your code throws a specific exception.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * This method gives the input file and recieves the map from ExcelReader.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testReadExcelFile() throws IOException {
    String filePath = new File("src/test/resources/Excel_WebTest/ConfigReaderInputForDemo.xlsx").getAbsolutePath();

    Map<String, String> map = ConfigReader.readExcelFile(filePath);
    assertEquals("value11", map.get("prop11"));

  }

  /**
   * This method is to test the exception condition when there is an empty row in input file.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testReadExcelFileEmptyRow() throws IOException {
    String filePath = new File("src/test/resources/Excel_WebTest/ConfigReaderInput2.xlsx").getAbsolutePath();
  
    this.thrown.expect(InvalidParameterException.class);
    this.thrown.expectMessage(CoreMatchers.is("Data not present in row number 3 of sheet name Sheet1"));
    ConfigReader.readExcelFile(filePath);
  }

}
