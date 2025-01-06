/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author uum4kor
 */
public class ExcelResultWriterTest extends ExcelResultWriter {


  /**
   * @throws Exception exception
   */
  @Test
  public void testWrite() throws Exception {

    ExcelScriptResult er = new ExcelScriptResult();
    er.setAction("login");
    er.setParameter("user id");
    er.setResult("pass");
    er.setReturnValue("string");
    er.setScreenshotFileName("src\\test\\resources\\Excel_WebTest\\screenshot_file_name.png");
    List<ExcelScriptResult> ers = new LinkedList<>();
    ers.add(er);
    Map<String, List<ExcelScriptResult>> results = new LinkedHashMap<>();
    results.put("Sheet", ers);
    Map<String,String> serverlinkdetails= new LinkedHashMap<>();
    ExcelResultWriter.write(results, "src\\test\\resources\\Excel_WebTest\\screenshot_file_name",serverlinkdetails);
    File tempFile = new File("src\\\\test\\\\resources\\\\Excel_WebTest\\\\screenshot_file_name.xlsx");
    assertTrue(tempFile.exists());
  }
}
