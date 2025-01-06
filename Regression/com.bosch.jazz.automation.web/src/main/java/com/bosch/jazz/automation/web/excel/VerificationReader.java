/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bosch.jazz.automation.web.common.constants.VariableExpander;

/**
 * VerificationReader shall read the verification file and give back the map which contains list of verificationConfig
 * objects.
 */
public class VerificationReader {

  /**
   * @param filePath is the location of the excel file to be read.
   * @param sheetName name of the sheet in the verification excel file.
   * @return Map which contains list of VerificationConfig objects.
   * @throws IOException if the file not found or corrupt.
   */
  public Map<String, List<VerificationConfig>> readVerificationExcelFile(final String filePath, final String sheetName)
      throws IOException {
    Map<String, List<VerificationConfig>> map = new LinkedHashMap<>();
    try (FileInputStream excelfile = new FileInputStream(filePath);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelfile)) {
      if (StringUtils.isNotBlank(sheetName)) {
        
        XSSFSheet excelSheet = excelWorkBook.getSheet(sheetName);
        getExcelVerificationMap(excelSheet, map);
      }
      else {
        for (int index = 0; index < excelWorkBook.getNumberOfSheets(); index++) {
          XSSFSheet excelSheet = excelWorkBook.getSheetAt(index);
          getExcelVerificationMap(excelSheet, map);
        }
      }
    }
    catch (IOException e) {
      throw new IOException("File " + filePath + " was not found or seems to be corrupted");
    }

    return map;
  }

  /**
   * @param excelSheet2 is every excel sheet to be read.
   * @return List of VerificationConfig objects for each excel sheet.
   */
  @SuppressWarnings("unused")
  private List<VerificationConfig> getExcelVerificationList(final XSSFSheet excelSheet2) {
    List<VerificationConfig> verificationList = new ArrayList<>();
    for (int rowNum = 1; rowNum < (excelSheet2.getLastRowNum() + 1); rowNum++) {
      XSSFRow rowContent = excelSheet2.getRow(rowNum);
      if (rowContent == null) {
        throw new InvalidParameterException(
            "Data not present in row number " + (rowNum + 1) + " of sheet name " + excelSheet2.getSheetName());
      }
      VerificationConfig verification = getExcelVerification(rowContent);
      if ((verification.getPageModelMethod() != null) && !(verification.getPageModelMethod().equals(""))) {
        verificationList.add(verification);
      }
    }
    return verificationList;
  }

  /**
   * @param excelSheet2 is every excel sheet to be read.
   */
  private void getExcelVerificationMap(XSSFSheet excelSheet2, Map<String, List<VerificationConfig>> map) {
    List<VerificationConfig> verificationList = null;
    List<VerificationConfig> existingVerificationList = null;
    for (int rowNum = 1; rowNum < (excelSheet2.getLastRowNum() + 1); rowNum++) {
      XSSFRow rowContent = excelSheet2.getRow(rowNum);
      if (rowContent == null) {
        throw new InvalidParameterException(
            "Data not present in row number " + (rowNum + 1) + " of sheet name " + excelSheet2.getSheetName());
      }
      VerificationConfig verification = getExcelVerification(rowContent);
      if ((verification.getPageModelMethod() != null) && !(verification.getPageModelMethod().equals(""))) {
        if (map.containsKey(verification.getPageModelMethod().trim())) {
          existingVerificationList = map.get(verification.getPageModelMethod().trim());
          existingVerificationList.add(verification);
        }
        else {
          verificationList = new ArrayList<>();
          verificationList.add(verification);
          map.put(verification.getPageModelMethod().trim(), verificationList);
        }
      }
    }
  }

  /**
   * @param rowContent is every row in each excel sheet to be read.
   * @return getExcelVerification object for each row.
   */
  private VerificationConfig getExcelVerification(final XSSFRow rowContent) {
    VerificationConfig verification = new VerificationConfig();
    verification.setSheetName(rowContent.getSheet().getSheetName());
    verification.setLineNumber(rowContent.getRowNum() + 1);

    XSSFCell pageModelIMethodCell = rowContent.getCell(0);
    verification.setPageModelMethod(getCellData(pageModelIMethodCell));

    XSSFCell verificationCell = rowContent.getCell(1);
    verification.setVerification(getCellData(verificationCell));

    XSSFCell ratingCell = rowContent.getCell(2);
    verification.setRating(getCellData(ratingCell) != null ? Integer.parseInt(getCellData(ratingCell)) : 0);

    XSSFCell hasParameterCell = rowContent.getCell(3);
    verification.setHasParameter(getCellData(hasParameterCell));
    String hasParamater = getCellData(hasParameterCell);
    if (hasParamater != null && hasParamater.equalsIgnoreCase("true")) {
      XSSFCell paramCell = rowContent.getCell(4);
      String param = getCellData(paramCell);
      if (param == null) {
        verification.setParameter(null);
      }
      else {
        verification.setParameter(new VariableExpander().expandVariables(param));
      }
      verification.setStrParameter(param);
    }

    return verification;
  }

  /**
   * @param cell is every cell in the excel row be read.
   * @return String data present in the cell.
   */
  private String getCellData(final XSSFCell cell) {
    if ((cell == null) || (cell.getCellType() == CellType.BLANK)) {
      return null;
    }
    if (cell.getCellType() == CellType.NUMERIC){
      return NumberToTextConverter.toText(cell.getNumericCellValue());
    }
    else if (cell.getCellType() == CellType.BOOLEAN) {
      return String.valueOf(cell.getBooleanCellValue());
    }
    return cell.getStringCellValue().trim();
  } 

}
