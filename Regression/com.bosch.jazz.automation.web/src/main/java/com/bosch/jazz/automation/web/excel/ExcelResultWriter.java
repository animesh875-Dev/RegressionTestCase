/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author CIK5KOR
 */
public class ExcelResultWriter {

  /**
   * 
   */
  protected ExcelResultWriter() {}

  /**
   * 
   */
  public static final String TESTENVIRONMENTPROPERTIES = "TestEnvironmentProperties";

  private static XSSFWorkbook prepareExcel(final Map<String, List<ExcelScriptResult>> results) throws IOException {
    XSSFWorkbook workbook = new XSSFWorkbook();
    for (Map.Entry<String, List<ExcelScriptResult>> sheetResult : results.entrySet()) {
      XSSFSheet sheet = workbook.createSheet(sheetResult.getKey());
      XSSFCellStyle cs = workbook.createCellStyle();
      cs.setWrapText(true);
      cs.setBorderTop(BorderStyle.MEDIUM);
      cs.setBorderBottom(BorderStyle.MEDIUM);
      cs.setBorderLeft(BorderStyle.MEDIUM);
      cs.setBorderRight(BorderStyle.MEDIUM);
      int rowNum = 0;
      Row headerRow = sheet.createRow(rowNum++);
      int colNum = 0;

      Cell actionCell = headerRow.createCell(colNum++);
      actionCell.setCellValue("Action");
      actionCell.setCellStyle(cs);
      Cell actionTyepCell = headerRow.createCell(colNum++);
      actionTyepCell.setCellValue("Action Type");
      actionTyepCell.setCellStyle(cs);
      Cell description = headerRow.createCell(colNum++);
      description.setCellValue("Description");
      description.setCellStyle(cs);
      Cell parameterCell = headerRow.createCell(colNum++);
      parameterCell.setCellValue("Parameters");
      parameterCell.setCellStyle(cs);
      Cell valuesCell = headerRow.createCell(colNum++);
      valuesCell.setCellValue("Values");
      valuesCell.setCellStyle(cs);
      Cell statusCell = headerRow.createCell(colNum++);
      statusCell.setCellValue("Action Status");
      statusCell.setCellStyle(cs);
      Cell verResCell = headerRow.createCell(colNum++);
      verResCell.setCellValue("Verification Result");
      verResCell.setCellStyle(cs);
      Cell screenshotCell = headerRow.createCell(colNum);
      screenshotCell.setCellValue("Screenshot");
      screenshotCell.setCellStyle(cs);
      for (ExcelScriptResult result : sheetResult.getValue()) {
        Row row = sheet.createRow(rowNum++);
        colNum = 0;
        for (int i = 0; i <= 7; i++) {
          Cell cell = row.createCell(colNum++);
          cell.setCellStyle(cs);
          // set width to n character widths = count characters* 256
          int widthUnits = 60 * 256;
          sheet.setColumnWidth(cell.getColumnIndex(), widthUnits);
          if (i == 0) {
            cell.setCellValue(result.getAction());
          }
          else if (i == 1) {
            cell.setCellValue(result.getActionType());
          }
          else if (i == 2) {
            cell.setCellValue(result.getDescription());
          }
          else if (i == 3) {
            cell.setCellValue(result.getParameter());
          }
          else if (i == 4) {
            cell.setCellValue(result.getValues());
          }
          else if (i == 5) {
            cell.setCellValue(result.getResult());
          }
          else if (i == 6) {
            StringBuilder verificationResult = new StringBuilder();
            if (result.getVerificationResult() == null || result.getVerificationResult().isEmpty()) {

              String actionName = result.getAction();
              if (actionName.startsWith("Assert.")) {
                if (result.getResult().equalsIgnoreCase("Passed"))
                  verificationResult.append(result.getResult() + ":Actual and Expected value matched.");
                else
                  verificationResult.append(result.getResult() + ":Actual  ");
              }
              else if (actionName.equalsIgnoreCase("Set") || actionName.equalsIgnoreCase("ConvertToTimeZone") ||
                  actionName.equalsIgnoreCase("skipExpectedFailure") ||
                  actionName.equalsIgnoreCase("AppendCurrentDateAndTime"))
                verificationResult
                    .append(actionName + " is framework command & it is not a test step so no verification required.");
              else if (!result.getResult().equalsIgnoreCase("Passed"))
                verificationResult.append("Verification can't be done if Action status is Error/Failed. ");
              else
                verificationResult.append("No Verification for this Method implemented.");

            }
            else {
              for (TestAcceptanceMessage tam : result.getVerificationResult()) {
                verificationResult.append(tam.getState());
                if (tam.getMessage() != null)
                  verificationResult.append(": " + tam.getMessage().trim());
                verificationResult.append("\n");
                // String removeFailedIfError = "FAILED: Error:";
                // String resultMessage = verificationResult.toString();
                // if (resultMessage.startsWith(removeFailedIfError)) {
                // resultMessage=resultMessage.replace(removeFailedIfError, "Error:");
                // verificationResult = new StringBuilder(resultMessage);
                // }
              }
            }
            cell.setCellValue(verificationResult.toString());
          }
          else {
            // set height to n points in twips = n * 20
            short heightUnits = 120 * 20;
            cell.getRow().setHeight(heightUnits);
            if (result.getScreenshotFileName() == null || result.getScreenshotFileName().isEmpty()) {
              String actionName = result.getAction();
              if (!(actionName.startsWith("Assert.") || actionName.equalsIgnoreCase("Set") ||
                  actionName.equalsIgnoreCase("ConvertToTimeZone") ||
                  actionName.equalsIgnoreCase("skipExpectedFailure") ||
                  actionName.equalsIgnoreCase("AppendCurrentDateAndTime"))) {
                screenshotCell.setCellValue("Screenshot if action Failed.");
                cell.setCellValue("");
              }
            }
            else
              screenshot(workbook, sheet, cell.getColumnIndex(), cell.getRowIndex(), result.getScreenshotFileName());
          }
        }
      }
    }
    return workbook;

  }

  /**
   * @param results - excel script results
   * @param reportName - name of report
   * @param serverDetails - details of server
   * @throws IOException - input, output exception
   */
  public static void write(final Map<String, List<ExcelScriptResult>> results, final String reportName,
      final Map<String, String> serverDetails) throws IOException {
    XSSFWorkbook workbook = prepareExcel(results);
    try {
      XSSFSheet sheet1 = workbook.createSheet(TESTENVIRONMENTPROPERTIES);
      XSSFCellStyle cs = workbook.createCellStyle();
      cs.setWrapText(true);
      cs.setBorderTop(BorderStyle.MEDIUM);
      cs.setBorderBottom(BorderStyle.MEDIUM);
      cs.setBorderLeft(BorderStyle.MEDIUM);
      cs.setBorderRight(BorderStyle.MEDIUM);
      Set<String> keyset = serverDetails.keySet();
      int rownum = 0;
      for (String key : keyset) {
        // this creates a new row in the sheet
        Row row = sheet1.createRow(rownum++);
        String value = serverDetails.get(key);
        int cellnum = 0;
        Cell cell0 = row.createCell(cellnum++);
        int widthUnits = 100 * 256;
        sheet1.setColumnWidth(cell0.getColumnIndex(), widthUnits);
        cell0.setCellStyle(cs);
        cell0.setCellValue(key);
        Cell cell1 = row.createCell(cellnum++);
        sheet1.setColumnWidth(cell1.getColumnIndex(), widthUnits);
        cell1.setCellStyle(cs);
        cell1.setCellValue(value);
      }
    }
    finally {
      if (workbook.getSheet(TESTENVIRONMENTPROPERTIES) != null)
        workbook.setSheetOrder(TESTENVIRONMENTPROPERTIES, 0);
      if (workbook.getSheet(ExcelSuiteRunner.SETUP) != null)
        workbook.setSheetOrder(ExcelSuiteRunner.SETUP, 1);
      if (workbook.getSheet(ExcelSuiteRunner.TEARDOWN) != null)
        workbook.setSheetOrder(ExcelSuiteRunner.TEARDOWN, workbook.getNumberOfSheets() - 1);
      ExcelResultWriter.writeToFile(workbook, reportName);
    }
  }

  /**
   * This method writes to a file.
   *
   * @param workbook excel
   * @param reportName filename
   * @throws IOException related to file
   */
  public static void writeToFile(final XSSFWorkbook workbook, final String reportName) throws IOException {
    try (FileOutputStream outputStream = new FileOutputStream(reportName + ".xlsx")) {
      workbook.write(outputStream);
    }
    finally {
      workbook.close();
      String reportFolder = ReportFolder.getResultsReportDirLocation();
      if (reportFolder != null) {
        Arrays.stream(new File(ReportFolder.getResultsReportDirLocation()).listFiles((f, p) -> p.endsWith(".png")));
      }
    }
  }

  static void screenshot(final XSSFWorkbook workbook, final XSSFSheet sheet, final int col, final int row,
      final String screenshotFileName) throws IOException {
    InputStream inputStream = new FileInputStream(screenshotFileName);
    byte[] bytes = IOUtils.toByteArray(inputStream);
    int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
    inputStream.close();
    CreationHelper helper = workbook.getCreationHelper();
    Drawing drawing = sheet.createDrawingPatriarch();
    ClientAnchor anchor = helper.createClientAnchor();
    anchor.setCol1(col);
    anchor.setRow1(row);
    anchor.setCol2(col + 1);
    anchor.setRow2(row + 1);

    drawing.createPicture(anchor, pictureIdx);
  }

}