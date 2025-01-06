/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author HCM6KOR This is the unit test to cover the methods in ExcelReader class.
 */
public class ExcelReaderTest {

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
    String filePath = new File("src/test/resources/Excel_WebTest/ExcelReaderInput.xlsx").getAbsolutePath();
    ExcelReader er = new ExcelReader();
    Map<String, List<ExcelScriptCommand>> map = er.readExcelFile(filePath, null);
    assertNotNull(map);
    assertEquals(5, map.size());
    assertEquals("Click", map.get("Sheet1").get(0).getAction());
    assertEquals("some_xpath", map.get("Sheet1").get(0).getActionType());
    assertEquals(2, map.get("Sheet1").get(0).getLineNumber());
    assertEquals("Sheet1", map.get("Sheet1").get(0).getSheetName());
  }

  /**
   * This method is to test the exception condition when there is an empty row in input file.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testReadExcelFileEmptyRow() throws IOException {
    String filePath = new File("src/test/resources/Excel_WebTest/ExcelReaderInput3.xlsx").getAbsolutePath();
    ExcelReader er = new ExcelReader();
    assertNotNull(er);
    this.thrown.expect(InvalidParameterException.class);
    this.thrown.expectMessage(CoreMatchers.is("Data not present in row number 3 of sheet name Sheet1"));
    er.readExcelFile(filePath, null);
  }

}
