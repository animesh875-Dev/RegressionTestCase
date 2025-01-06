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
 * @author HCM6KOR ExcelReader shall read the excel file and give back the map which contains list of ExcelScriptCommand
 *         objects.
 */
public class ExcelReader {

  /**
   * @param filePath is the location of the excel file to be read.
   * @param sheetName name of the sheet in the excel file.
   * @return Map which contains list of ExcelScriptCommand objects.
   * @throws IOException if the file not found or corrupt.
   */
  public Map<String, List<ExcelScriptCommand>> readExcelFile(final String filePath, final String sheetName)
      throws IOException {
    
    Map<String, List<ExcelScriptCommand>> map = new LinkedHashMap<>();
    try (FileInputStream excelfile = new FileInputStream(filePath);
        
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelfile)) {
      
      if (StringUtils.isNotBlank(sheetName)) {
        XSSFSheet excelSheet = excelWorkBook.getSheet(sheetName);
        List<ExcelScriptCommand> commandList = getExcelScriptCommandList(excelSheet);
        map.put(excelSheet.getSheetName(), commandList);
      }
      else {
        for (int index = 0; index < excelWorkBook.getNumberOfSheets(); index++) {
          XSSFSheet excelSheet = excelWorkBook.getSheetAt(index);
          List<ExcelScriptCommand> commandList = getExcelScriptCommandList(excelSheet);
          map.put(excelSheet.getSheetName(), commandList);
        }
      }
    }
    catch (IOException e) {
      throw new IOException("Excel test case file \"" + filePath + "\" was not found or seems to be corrupted");
    }

    return map;
  }

  /**
   * @param excelSheet2 is every excel sheet to be read.
   * @return List of ExcelScriptCommand objects for each excel sheet.
   */
  private List<ExcelScriptCommand> getExcelScriptCommandList(final XSSFSheet excelSheet2) {
    List<ExcelScriptCommand> commandList = new ArrayList<>();
    for (int rowNum = 1; rowNum < (excelSheet2.getLastRowNum() + 1); rowNum++) {
      XSSFRow rowContent = excelSheet2.getRow(rowNum);
      if (rowContent == null) {
        throw new InvalidParameterException(
            "Data not present in row number " + (rowNum + 1) + " of sheet name " + excelSheet2.getSheetName());
      }
      ExcelScriptCommand command = getExcelScriptCommand(rowContent);
      if (command.getAction() != null && !(command.getAction().equals(""))) {
        commandList.add(command);
      }
    }
    return commandList;
  }

  /**
   * @param rowContent is every row in each excel sheet to be read.
   * @return ExcelScriptCommand object for each row.
   */
  private ExcelScriptCommand getExcelScriptCommand(final XSSFRow rowContent) {
    ExcelScriptCommand command = new ExcelScriptCommand();
    VariableExpander vr = new VariableExpander();
    command.setSheetName(rowContent.getSheet().getSheetName());
    command.setLineNumber(rowContent.getRowNum() + 1);
    XSSFCell actionCell = rowContent.getCell(0);
    command.setAction(getCellData(actionCell));
    XSSFCell elementCell = rowContent.getCell(1);
    command.setActionType(getCellData(elementCell));
    XSSFCell paramCell = rowContent.getCell(2);

    String param = getCellData(paramCell);
    if (param == null) {
      command.setParameter(null);
    }
    else {
      command.setParameter(vr.expandVariables(param));
    }
    XSSFCell descriptionCell = rowContent.getCell(3);
    command.setStrParameter(param);
    command.setDescription(getCellData(descriptionCell));

    XSSFCell verificationCell = rowContent.getCell(4);
    String verificationParam = getCellData(verificationCell);
    if (verificationParam == null) {
      command.setVerificationParameter(null);
    }
    else {
      command.setVerificationParameter(vr.expandVariables(verificationParam));
    }

    return command;
  }

  /**
   * @param cell is every cell in the excel row be read.
   * @return String data present in the cell.
   */
  private String getCellData(final XSSFCell cell) {
    if ((cell == null) || (cell.getCellType() == CellType.BLANK)) {
      return null;
    }
    if (cell.getCellType() == CellType.NUMERIC) {
      return NumberToTextConverter.toText(cell.getNumericCellValue());
    }
    else if (cell.getCellType() == CellType.BOOLEAN) {
      return String.valueOf(cell.getBooleanCellValue());
    }
    return cell.getStringCellValue().trim();
  }

  /**
   * @param suiteFileTest path of the test suite
   * @return List of all data from suite file
   * @throws IOException - input, output exception
   */
  public List<String> excelSuiteReader(String suiteFileTest) throws IOException {
    List<String> suites = new ArrayList<>();
    try (FileInputStream excelfile = new FileInputStream(suiteFileTest);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelfile)) {
      XSSFSheet excelSheet = excelWorkBook.getSheetAt(0);

      for (int rowNum = 1; rowNum < (excelSheet.getLastRowNum() + 1); rowNum++) {
        XSSFRow rowContent = excelSheet.getRow(rowNum);
        suites.add(rowContent.getCell(0).toString());
      }
    }

    return suites;
  }
}
