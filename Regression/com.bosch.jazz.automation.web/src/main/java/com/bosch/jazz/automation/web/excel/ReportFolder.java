/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.CannotProceedException;

/**
 * @author hrt5kor
 */
public class ReportFolder {

  /**
  *
  */
  private static final String RUN = "Run";
  private static String resultsReportDirLocation = null;

  private ReportFolder() {
    // do nothing
  }

  /**
   * @return Returns the result directory path with the Format: .\target\report\datetime stamp\run1
   */
  public static String createReportFolder() {

    String date = null;
    String[] elementsInDateResultFolder = null;
    String run = "Run1";
    try {
      Date now = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      date = dateFormat.format(now);
      String baseRelativePath = "target\\report\\" + date;
      File dateSpecificReportFolder = new File(baseRelativePath);
      if (!dateSpecificReportFolder.exists()) {
        dateSpecificReportFolder.mkdirs();
      }
      elementsInDateResultFolder = dateSpecificReportFolder.list();
      if (elementsInDateResultFolder.length != 0) {
        // "Run"
        File dir = new File(dateSpecificReportFolder.getPath());
        String[] files;
        FilenameFilter filter = (dir1, name) -> name.startsWith(RUN);
        files = dir.list(filter);

        run = RUN + String.format("%d", (files.length + 1));
      }
      File runFolder = new File(baseRelativePath + File.separator + run);

      if (!runFolder.mkdirs()) {
        throw new CannotProceedException("Run folder could not be created: " + runFolder.getPath());
      }
      return runFolder.getAbsolutePath();
    }
    catch (Exception e) {
      throw new IllegalStateException("An exception happened while creating report folder, details: " + e.getMessage(),
          e);
    }
  }

  /**
   * @return the location of the directory where the report file is or shall be created, maybe null if the folder has
   *         not yet been set by {@link #setResultsReportDirLocation(String)}.
   */
  public static String getResultsReportDirLocation() {
    return resultsReportDirLocation;
  }

  /**
   * Sets the location of the directory where the report file is or shall be created, must not be null
   *
   * @param resultsReportDirLocation the location of the report folder, must not be null
   */
  public static void setResultsReportDirLocation(final String resultsReportDirLocation) {
    ReportFolder.resultsReportDirLocation = resultsReportDirLocation;
  }
}
