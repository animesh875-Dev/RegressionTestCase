/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bosch.jazz.automation.web.common.constants.VariableExpander;
import com.bosch.jazz.automation.web.reporter.Reporter;

/**
 * @author CIK5KOR ConfigReader shall read the excel file and give back the map which contains list of
 *         ConfigScriptCommand objects.
 */
public class ConfigReader {


  private ConfigReader() {

  }


  /**
   * @param filePath is the excel file to be read.
   * @return Map which contains list of ConfigScriptCommand objects.
   * @throws IOException if the file not found or corrupt.
   */
  public static Map<String, String> readExcelFile(final String filePath) throws IOException {

    Map<String, String> configMap = new HashMap<>();
    Map<String, String> propertySheetMap = new HashMap<>();
    try (FileInputStream excelfile = new FileInputStream(filePath);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelfile)) {

      for (int index = 0; index < excelWorkBook.getNumberOfSheets(); index++) {
        XSSFSheet excelSheet = excelWorkBook.getSheetAt(index);
        List<ConfigScriptCommand> configlist = getConfigurationlist(excelSheet);
        for (ConfigScriptCommand csc : configlist) {
          if (configMap.containsKey(csc.getProperty())) {
            Reporter.logWarning("Property " + csc.getProperty() + " was defined at " +
                propertySheetMap.get(csc.getProperty()) + " already and will be overridden");
          }
          configMap.put(csc.getProperty(), csc.getValue());
          propertySheetMap.put(csc.getProperty(), excelSheet.getSheetName());

        }
      }
    }

    return configMap;
  }

  /**
   * @param excelSheet is every excel sheet to be read.
   * @return List of Config objects for each excel sheet.
   */
  private static List<ConfigScriptCommand> getConfigurationlist(final XSSFSheet excelSheet) {
    List<ConfigScriptCommand> configList = new ArrayList<>();
    for (int rowNum = 1; rowNum < (excelSheet.getLastRowNum() + 1); rowNum++) {
      XSSFRow rowContent = excelSheet.getRow(rowNum);
      if (rowContent == null) {
        throw new InvalidParameterException(
            "Data not present in row number " + (rowNum + 1) + " of sheet name " + excelSheet.getSheetName());
      }
      ConfigScriptCommand config = getConfigurations(rowContent);
      configList.add(config);
    }
    Map<String, String> configMap = new HashMap<>();
    for (ConfigScriptCommand csc : configList) {
      String resolvedValue = VariableExpander.resolveNestedProperties(csc.getValue(), configMap);
      csc.setValue(resolvedValue);
      configMap.put(csc.getProperty(), csc.getValue());
    }

    return configList;
  }


  /**
   * @param rowContent is every row in each excel sheet to be read.
   * @return ConfigScriptCommand object for each row.
   */
  private static ConfigScriptCommand getConfigurations(final XSSFRow rowContent) {
    ConfigScriptCommand configuration = new ConfigScriptCommand();
    configuration.setSheetName(rowContent.getSheet().getSheetName());
    configuration.setLineNumber(rowContent.getRowNum() + 1);
    XSSFCell propertyCell = rowContent.getCell(0);
    configuration.setProperty(getCellData(propertyCell));
    XSSFCell valueCell = rowContent.getCell(1);
    configuration.setValue(getCellData(valueCell));
    if (configuration.getProperty().isEmpty()) {
      throw new InvalidParameterException("Property not defined at row " + rowContent.getRowNum());
    }
    return configuration;
  }


    /**
     * @param cell is every cell in the excel row be read.
     * @return String data present in the cell
     */
    private static String getCellData(final XSSFCell cell) {
      if ((cell == null) || (cell.getCellType() ==CellType.BLANK)) {
        return "";
      }
      if (cell.getCellType() ==CellType.NUMERIC) {
        return NumberToTextConverter.toText(cell.getNumericCellValue());
      }
      else if (cell.getCellType() == CellType.BOOLEAN) {
        return String.valueOf(cell.getBooleanCellValue());
      }
      return cell.getStringCellValue().trim();
    } 
}
