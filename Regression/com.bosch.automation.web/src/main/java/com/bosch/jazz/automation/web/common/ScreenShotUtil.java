/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

/**
 * Util class for taking screenshots using Selenium.
 */
public class ScreenShotUtil {

  private ScreenShotUtil() {
    // private constructor due to util class
  }

  /**
   * Makes a screenshot and saves it at the location: [screenshotFolder]\[fileNameNoSuffix]_[random uuid].png. Finally
   * returns the absolute path of the file.
   *
   * @param screenshotFolderLocation the folder where to store the screenshot in, must not be null
   * @param fileNameNoSuffix the name of the screenshot file, without file suffix (since that is predefined to png),
   *          must not be null and must be a valid file name
   * @param screenshotTaker required for making the screenshot, must not be null
   * @return the absolute path of the screenshot file
   */
  public static String makeScreenShot(final String screenshotFolderLocation, final String fileNameNoSuffix,
      final TakesScreenshot screenshotTaker) {
    File scrFile;
    try {
      scrFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
    }
    catch (WebDriverException wde) {
      // in case of huge pages we may get a timeout exception in some browsers, ignore
      return "";
    }
   // String fileName = fileNameNoSuffix + "_" + UUID.randomUUID().toString() + ".png";
    String fileName = fileNameNoSuffix + ".png";
    File screenshotFile = new File(screenshotFolderLocation, fileName);
    try {
      FileUtils.moveFile(scrFile, screenshotFile);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return  screenshotFile.getAbsolutePath();
  }
  public static String makeScreenShotAndReturnFileNameWithUUID(final String screenshotFolderLocation, final String fileNameNoSuffix,
      final TakesScreenshot screenshotTaker) {
    File scrFile;
    try {
      scrFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
    }
    catch (WebDriverException wde) {
      // in case of huge pages we may get a timeout exception in some browsers, ignore
      return "";
    }
   String fileName = fileNameNoSuffix + "_" + UUID.randomUUID().toString() + ".png";
   // String fileName = fileNameNoSuffix + ".png";
    File screenshotFile = new File(screenshotFolderLocation, fileName);
    try {
      FileUtils.moveFile(scrFile, screenshotFile);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return  fileName;
  }
  public static String makeScreenShotAndReturnFileName(final String screenshotFolderLocation, final File scrFile,
      final String tableName) {
   
   String fileName = tableName + "_" + UUID.randomUUID().toString() + ".png";
  //  String fileName = tableName + ".png";
    File screenshotFile = new File(screenshotFolderLocation, fileName);
    try {
      FileUtils.moveFile(scrFile, screenshotFile);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return  fileName;
  }
}

