/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bosch.automation.web.log.LOGGER;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * @author ynt2hc
 */
public class ExcelCSVReader {

  private final String filePath;
  private static final String METADATA_KEYWORD = "METADATA";
  private static final String EXPORTED_ARTIFACT_IDS_KEYWORD = "Exported Artifact IDs";
  private static final String CSV_NAME = "CSV file \"";
  private static final String EXCEL_FILE = "Excel file \"";
  private static final String CORRUPT_MSG = "\" was not found or seems to be corrupted";


  /**
   * @param filePath the file location
   */
  public ExcelCSVReader(final String filePath) {
    this.filePath = filePath;
  }

  /**
   * @param linkType the type link between artifacts
   * @param artifactID the ID of artifac having the link
   * @return the url of linked artifact
   * @throws IOException the Exception when reading Excel/CSV file
   */
  public String getUrlofLinkedArtifactFromExcelFile(final String linkType, final String artifactID) throws IOException {
    String url = "";
    int col = -1;
    int rowI = -1;
    String linkValue = "";
    try {
      XSSFCell cel = getCellByValue(linkType);
      if (cel != null) {
        col = cel.getColumnIndex();
      }
      cel = getCellByValue(artifactID);
      if (cel != null) {
        rowI = cel.getRowIndex();
      }

      if (getCellByIndex(rowI, col) != null) {
        linkValue = getCellData(getCellByIndex(rowI, col));
      }

      Pattern p = Pattern.compile("https://(.*?)}$");
      Matcher m = p.matcher(linkValue);
      if (m.find()) {
        url = m.group(0).trim().substring(0, m.group(0).trim().length() - 1);
      }

    }
    catch (IOException e) {
      throw new IOException(EXCEL_FILE + this.filePath + CORRUPT_MSG);
    }
    return url;
  }

  /**
   * @return the number of artifacts exported
   * @throws IOException the Exception when reading Excel/CSV file
   */
  public int getNumberofArtifactsFromExcelFile() throws IOException {
    int num = 0;
    try {
      XSSFCell cel = getCellByValue(METADATA_KEYWORD);
      if (cel != null) {
        // Get next cell value containing "Exported Artifact IDs"
        XSSFCell artifactsCell = getCellByIndex(cel.getRowIndex() + 1, 0);
        Pattern p = Pattern.compile("IDs=(.*?)$");
        Matcher m = p.matcher(artifactsCell.toString());
        if (m.find()) {
          return Pattern.compile("\\|").splitAsStream(m.group(1).trim()).collect(Collectors.toList()).size();
        }
      }
    }
    catch (IOException| NullPointerException e) {
      throw new IOException(EXCEL_FILE + this.filePath + CORRUPT_MSG);
    }
    return num;
  }

  /**
   * @return the number of artifacts exported
   * @throws IOException the Exception when reading Excel/CSV file
   * @throws CsvValidationException the Exception by LineValidation
   */
  public int getNumberofArtifactsFromCSVFile() throws IOException, CsvValidationException {
    int num = 0;
    try {
      List<List<String>> records = readCSVFile();
      for (List<String> row : records) {
        if (row.get(0).contains(EXPORTED_ARTIFACT_IDS_KEYWORD)) {
          Pattern p = Pattern.compile("IDs=(.*?)$");
          Matcher m = p.matcher(row.get(0));
          if (m.find()) {
            return Pattern.compile("\\|").splitAsStream(m.group(1).trim()).collect(Collectors.toList()).size();
          }
        }
      }
    }
    catch (IOException e) {
      throw new IOException(CSV_NAME + this.filePath + CORRUPT_MSG);
    }
    return num;
  }

  /**
   * @param linkType the type link between artifacts
   * @param artifactID the ID of artifac having the link
   * @return the url of linked artifact
   * @throws IOException the Exception when reading Excel/CSV file
   * @throws CsvValidationException the Exception by LineValidation
   */
  public String getUrlofLinkedArtifactFromCSVFile(final String linkType, final String artifactID)
      throws IOException, CsvValidationException {
    String url = "";
    int col = -1;
    int rowI = -1;
    String linkValue = "";
    try {
      List<List<String>> records = readCSVFile();
      col = getColumnIndex(records, linkType);
      rowI = getRowIndex(records, artifactID);

      if ((col >= 0) && (rowI >= 0)) {
        linkValue = records.get(rowI).get(col);
      }

      Pattern p = Pattern.compile("https://(.*?)}$");
      Matcher m = p.matcher(linkValue);
      if (m.find()) {
        url = m.group(0).trim().substring(0, m.group(0).trim().length() - 1);
      }

    }
    catch (IOException e) {
      throw new IOException(CSV_NAME + this.filePath + CORRUPT_MSG);
    }
    return url;
  }

  private XSSFCell getCellByValue(final String value) throws IOException {
    try (FileInputStream excelfile = new FileInputStream(this.filePath);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelfile)) {
      if (excelWorkBook.getNumberOfSheets() > 0) {
        XSSFSheet excelSheet = excelWorkBook.getSheetAt(0);
        for (int rowIndex = 0; rowIndex < excelSheet.getLastRowNum(); rowIndex++) {
          for (int colIndex = 0; colIndex < excelSheet.getRow(rowIndex).getLastCellNum(); colIndex++) {
            XSSFCell foundCell = excelSheet.getRow(rowIndex).getCell(colIndex);
            if ((foundCell != null) && getCellData(foundCell).equalsIgnoreCase(value)) {
              return foundCell;
            }
          }
        }

      }
    }
    catch (IOException e) {
      throw new IOException(EXCEL_FILE + this.filePath + CORRUPT_MSG);
    }
    return null;

  }

  private XSSFCell getCellByIndex(final int rowIndex, final int colIndex) throws IOException {
    if ((rowIndex < 0) || (colIndex < 0)) {
      return null;
    }
    try (FileInputStream excelfile = new FileInputStream(this.filePath);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelfile)) {
      if (excelWorkBook.getNumberOfSheets() > 0) {
        XSSFSheet excelSheet = excelWorkBook.getSheetAt(0);
        return excelSheet.getRow(rowIndex).getCell(colIndex);
      }
    }
    catch (IOException e) {
      throw new IOException(EXCEL_FILE + this.filePath + CORRUPT_MSG);
    }
    return null;
  }



  private String getCellData(final XSSFCell cell) {
    if ((cell == null) || (cell.getCellType() == CellType.BLANK)) {
      return "";
    }
    if (cell.getCellType() == CellType.NUMERIC) {
      return NumberToTextConverter.toText(cell.getNumericCellValue());
    }
    else if (cell.getCellType() == CellType.BOOLEAN) {
      return String.valueOf(cell.getBooleanCellValue());
    }
    return cell.getStringCellValue().trim();
  } 
  
  private String getCellData(final HSSFCell cell) {
    if ((cell == null) || (cell.getCellType() == CellType.BLANK)) {
      return "";
    }
    if (cell.getCellType() == CellType.NUMERIC) {
      return NumberToTextConverter.toText(cell.getNumericCellValue());
    }
    else if (cell.getCellType() == CellType.BOOLEAN) {
      return String.valueOf(cell.getBooleanCellValue());
    }
    return cell.getStringCellValue().trim();
  } 

  private List<List<String>> readCSVFile() throws IOException, CsvValidationException {
    List<List<String>> records = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader(this.filePath));) {
      String[] values = null;
      while ((values = csvReader.readNext()) != null) {
        records.add(Arrays.asList(values));
      }
    }
    catch (IOException e) {
      throw new IOException(CSV_NAME + this.filePath + CORRUPT_MSG);
    }
    return records;
  }

  private int getColumnIndex(final List<List<String>> records, final String value) {
    int index = -1;
    for (int i = 0; i < records.get(0).size(); i++) {
      if (records.get(0).get(i).contains(value)) {
        index = i;
        break;
      }
    }
    return index;
  }

  private int getRowIndex(final List<List<String>> records, final String value) {
    int index = -1;
    for (int i = 0; i < records.size(); i++) {
      if (records.get(i).get(0).contains(value)) {
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * This method will get value from cell which has row as rowIndex and column as columnIndex in XLSX file
   *
   * @param excelPath - path of excel file , type as XLSX
   * @param sheetIndex - sheet index need to read value
   * @param rowIndex - row index need to read value
   * @param columnIndex - column index need to read value
   * @return String data present in the define cell following (path file, sheet index, column index and row index).
   */
  @SuppressWarnings("null")
  public String readDataFromCellOfXLSX(final String excelPath, final String sheetIndex, final String rowIndex,
      final String columnIndex) {

    int rowIndexInt = Integer.parseInt(rowIndex);
    int columnIndexInt = Integer.parseInt(columnIndex);
    int sheetIndexInt = Integer.parseInt(sheetIndex);
    String value = null; // variable for storing the cell value
    XSSFWorkbook wb = null; // initialize Workbook null

    try {
      // reading data from a file in the form of bytes
      FileInputStream fis = new FileInputStream(excelPath);
      // constructs an XSSFWorkbook object, by buffering the whole stream into the memory
      wb = new XSSFWorkbook(fis);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e1) {
      e1.printStackTrace();
    }
    XSSFSheet sheet = wb.getSheetAt(sheetIndexInt); // getting the XSSFSheet object at given index
    XSSFRow row = sheet.getRow(rowIndexInt); // returns the logical row
    XSSFCell cell = row.getCell(columnIndexInt); // getting the cell representing the given column
    value = getCellData(cell);

    return value; // returns the cell value
  }


  /**
   * This method will get value from cell which has row as rowIndex and column as columnIndex in XLSX file
   *
   * @param excelPath - path of excel file , type as XLS
   * @param sheetIndex - sheet index need to read value
   * @param rowIndex - row index need to read value
   * @param columnIndex - column index need to read value
   * @return String data present in the define cell following (path file, sheet index, column index and row index).
   */
  @SuppressWarnings("null")
  public String readIndividualDataFromCellOfXLS(final String excelPath, final String sheetIndex, final String rowIndex,
      final String columnIndex) {

    int rowIndexInt = Integer.parseInt(rowIndex);
    int columnIndexInt = Integer.parseInt(columnIndex);
    int sheetIndexInt = Integer.parseInt(sheetIndex);
    String value = null; // variable for storing the cell value
    HSSFWorkbook wb = null; // initialize Workbook null

    try {
      // reading data from a file in the form of bytes
      FileInputStream fis = new FileInputStream(excelPath);
      // constructs an XSSFWorkbook object, by buffering the whole stream into the memory
      wb = new HSSFWorkbook(fis);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e1) {
      e1.printStackTrace();
    }
    HSSFSheet sheet = wb.getSheetAt(sheetIndexInt); // getting the XSSFSheet object at given index
    HSSFRow row = sheet.getRow(rowIndexInt); // returns the logical row
    HSSFCell cell = row.getCell(columnIndexInt); // getting the cell representing the given column
    value = getCellData(cell);

    return value; // returns the cell value
  }

  
  
  
  
  
  /**
   * @param excelPath
   * @param sheetIndex
   * @param rowIndex
   * @param columnIndex
   * @return
   */
  @SuppressWarnings({ "null", "javadoc" })
  public String readDataFromCellOfXLS(final String excelPath, final String sheetIndex) {

    
    int sheetIndexInt = Integer.parseInt(sheetIndex);
    // variable for storing the cell value
    HSSFWorkbook wb = null; // initialize Workbook null

    try {
      // reading data from a file in the form of bytes
      FileInputStream fis = new FileInputStream(excelPath);
      // constructs an XSSFWorkbook object, by buffering the whole stream into the memory
      wb = new HSSFWorkbook(fis);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e1) {
      e1.printStackTrace();
    }
    HSSFSheet sheet = wb.getSheetAt(sheetIndexInt); // getting the XSSFSheet object at given index
 int rows =   sheet.getPhysicalNumberOfRows();
 String message="";
 for(int i =0; i<rows; i++)
 {
   HSSFRow row = sheet.getRow(i); 
   int col= row.getLastCellNum();
   for(int k=0; k <col;k++)
   {
     message = message+getCellData(row.getCell(k))+" ,, ";
   }
     }
   

    return message; // returns the cell value
  }
  /**
   * This method will set value from cell which has row as rowIndex and column as columnIndex in XLSX file
   *
   * @author KYY1HC
   * @param excelPath - path of excel file , type as XLSX
   * @param sheetIndex - sheet index need to read value, start from 0
   * @param rowIndex - row index need to read value, start from 0
   * @param columnIndex - column index need to read value, start from 0
   * @param updateValue - value to set in cell
   */
  public void setDataFromCellOfXLSX(final String excelPath, final String sheetIndex, final String rowIndex,
      final String columnIndex, final String updateValue) {
    int rowIndexInt = Integer.parseInt(rowIndex);
    int columnIndexInt = Integer.parseInt(columnIndex);
    int sheetIndexInt = Integer.parseInt(sheetIndex);

    try {
      FileInputStream inputFile = new FileInputStream(excelPath); // reading data from a file in the form of bytes
      @SuppressWarnings("resource")
      XSSFWorkbook wb = new XSSFWorkbook(inputFile); // constructs an XSSFWorkbook object, by buffering the whole stream into the memory
      XSSFSheet sheet = wb.getSheetAt(sheetIndexInt); // getting the XSSFSheet object at given index
      XSSFRow row = sheet.getRow(rowIndexInt); // returns the logical row
      XSSFCell cell = row.getCell(columnIndexInt); // getting the cell representing the given column

      cell.setCellValue(updateValue);
      inputFile.close();

      FileOutputStream outputFile =new FileOutputStream(new File(excelPath));
      wb.write(outputFile);
      outputFile.close();
    } catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
    }

    LOGGER.LOG.info("Cell value is updated into \"" + readDataFromCellOfXLSX(excelPath, sheetIndex, rowIndex, columnIndex) + "\"");
  }
  
  
    private String getCellDataXLS(final HSSFCell cell) {
      if ((cell == null) || (cell.getCellType() == CellType.BLANK)) {
        return "";
      }
      if (cell.getCellType() == CellType.NUMERIC) {
        return NumberToTextConverter.toText(cell.getNumericCellValue());
      }
      else if (cell.getCellType() == CellType.BOOLEAN) {
        return String.valueOf(cell.getBooleanCellValue());
      }
      return cell.getStringCellValue().trim();
    } 
   


  private HSSFCell getCellByIndexXLS(final int rowIndex, final int colIndex) throws IOException {
    if ((rowIndex < 0) || (colIndex < 0)) {
      return null;
    }
    try (FileInputStream excelfile = new FileInputStream(this.filePath);
        HSSFWorkbook excelWorkBook = new HSSFWorkbook(excelfile)) {
      if (excelWorkBook.getNumberOfSheets() > 0) {
        HSSFSheet excelSheet = excelWorkBook.getSheetAt(0);
        return excelSheet.getRow(rowIndex).getCell(colIndex);
      }
    }
    catch (IOException e) {
      throw new IOException(EXCEL_FILE + this.filePath + CORRUPT_MSG);
    }
    return null;
  }

  private HSSFCell getCellByValueXLS(final String value) throws IOException {
    try (FileInputStream excelfile = new FileInputStream(this.filePath);
        HSSFWorkbook excelWorkBook = new HSSFWorkbook(excelfile)) {
      if (excelWorkBook.getNumberOfSheets() > 0) {
        HSSFSheet excelSheet = excelWorkBook.getSheetAt(0);
        for (int rowIndex = 0; rowIndex < excelSheet.getLastRowNum(); rowIndex++) {
          for (int colIndex = 0; colIndex < excelSheet.getRow(rowIndex).getLastCellNum(); colIndex++) {
            HSSFCell foundCell = excelSheet.getRow(rowIndex).getCell(colIndex);
            if ((foundCell != null) && getCellDataXLS(foundCell).equalsIgnoreCase(value)) {
              return foundCell;
            }
          }
        }

      }
    }
    catch (IOException e) {
      throw new IOException(EXCEL_FILE + this.filePath + CORRUPT_MSG);
    }
    return null;
  }

  /**
   * <p>
   * <p>
   * 
   * @author NVV1HC
   * @param linkType
   * @param artifactID
   * @return
   * @throws IOException
   */
  @SuppressWarnings("javadoc")
  public String getAttributeValueOfArtifactFromXLSFile(final String linkType, final String artifactID)
      throws IOException {
    int col = -1;
    int rowI = -1;
    String linkValue = "";
    try {
      HSSFCell cel = getCellByValueXLS(linkType);
      if (cel != null) {
        col = cel.getColumnIndex();
      }
      cel = getCellByValueXLS(artifactID);
      if (cel != null) {
        rowI = cel.getRowIndex();
      }

      if (getCellByIndexXLS(rowI, col) != null) {
        linkValue = getCellDataXLS(getCellByIndexXLS(rowI, col));
      }
    }
    catch (IOException e) {
      throw new IOException(EXCEL_FILE + this.filePath + CORRUPT_MSG);
    }
    return linkValue;
  }
}
