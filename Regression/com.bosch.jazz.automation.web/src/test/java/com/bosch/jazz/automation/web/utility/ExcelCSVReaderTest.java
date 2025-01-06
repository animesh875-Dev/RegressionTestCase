/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.utility;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.opencsv.exceptions.CsvValidationException;

/**
 * @author HCM6KOR This is the unit test to cover the methods in ExcelReader class.
 */
public class ExcelCSVReaderTest {

  /**
   * This method gives the input file and recieves the value from ExcelUtil.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testGetNumberofArtifactsFromExcelFile() throws IOException {
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    ExcelCSVReader ex = new ExcelCSVReader(filePath);
    assertEquals(4, ex.getNumberofArtifactsFromExcelFile());
  }

  /**
   * This method gives the input file and recieves the value from ExcelUtil.
   *
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testGetUrlofLinkedArtifactFromExcelFile() throws IOException {
    String filePath = new File("src/test/resources/Excel_WebTest/export.xlsx").getAbsolutePath();
    ExcelCSVReader ex = new ExcelCSVReader(filePath);
    String url =
        "https://rb-alm-02-q.de.bosch.com:9443/qm/oslc_qm/contexts/_5RnTU-dJEeKzfc0TjbXmmg/resources/com.ibm.rqm.planning.VersionedTestCase/_oB9FMST5EeeqgMOfmPjf1A";
    assertEquals(url, ex.getUrlofLinkedArtifactFromExcelFile("Link:Validated By", "211565"));
  }

  /**
   * This method gives the input file and recieves the value from ExcelUtil.
   *
   * @throws IOException is thrown if file is not found.
   * @throws CsvValidationException if single line is invalid
   */
  @Test
  public void testGetNumberofArtifactsFromCSVFile() throws IOException, CsvValidationException {
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    ExcelCSVReader ex = new ExcelCSVReader(filePath);
    assertEquals(2, ex.getNumberofArtifactsFromCSVFile());
  }

  /**
   * This method gives the input file and recieves the value from ExcelUtil.
   *
   * @throws IOException is thrown if file is not found.
   * @throws CsvValidationException if single line is invalid
   */
  @Test
  public void testGetUrlofLinkedArtifactFromCSVFile() throws IOException, CsvValidationException {
    String filePath = new File("src/test/resources/Excel_WebTest/export.csv").getAbsolutePath();
    ExcelCSVReader ex = new ExcelCSVReader(filePath);
    String url = "https://rb-alm-02-q.de.bosch.com:9443/ccm/resource/itemName/com.ibm.team.workitem.WorkItem/286141";
    assertEquals(url, ex.getUrlofLinkedArtifactFromCSVFile("Link:Implemented By", "211564"));
  }

  /**
   * Unit test for {@link ExcelCSVReader#readDataFromCellOfXLSX(String,String,String,String)}
   *
   */
  @Test
  public void testReadDataFromCellOfXLSX()  {
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\export.xlsx";
    ExcelCSVReader ex = new ExcelCSVReader(path);
    String result = ex.readDataFromCellOfXLSX(path, "0", "0", "0");
    assertEquals(result, "id");
  }

  /**
   * Unit test for {@link ExcelCSVReader#setDataFromCellOfXLSX(String,String,String,String,String)}
   *
   */
  @Test
  public void testSetDataFromCellOfXLSX()  {
    String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel_WebTest\\setCellData.xlsx";
    ExcelCSVReader ex = new ExcelCSVReader(path);
    String originalValue = ex.readDataFromCellOfXLSX(path, "0", "2", "1");
    String valueUpdated = "test set data from cell of xlsx";
    ex.setDataFromCellOfXLSX(path, "0", "2", "1", valueUpdated);
    assertEquals(ex.readDataFromCellOfXLSX(path, "0", "2", "1"), valueUpdated);
    ex.setDataFromCellOfXLSX(path, "0", "2", "1", originalValue);
    assertEquals(ex.readDataFromCellOfXLSX(path, "0", "2", "1"), originalValue);
  }

  /**
   * <p>
   * This method gives the input file, artifact ID and attribute then recieves the attribute's value from ExcelUtil.
   * <p>
   * 
   * @author NVV1HC
   * @throws IOException is thrown if file is not found.
   */
  @Test
  public void testGetAttributeValueOfArtifactFromXLSFile() throws IOException {
    String filePath = new File("src/test/resources/Excel_WebTest/Artifact_Excel2.xls").getAbsolutePath();
    ExcelCSVReader ex = new ExcelCSVReader(filePath);
    assertEquals("CAN communication interface", ex.getAttributeValueOfArtifactFromXLSFile("Primary Text", "10"));
  }
}