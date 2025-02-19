package com.bosch.jazz.automation.web.reporter;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.CannotProceedException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/**
 * Convenience class for creating and handling {@link ExtentReports} in an easier way. The report is finally persisted
 * in html in the folder specified by {@link #getResultsReportDirLocation()} and is having the name
 * {@value #REPORT_FILE_NAME}.
 * <p>
 * The order of method invocations of a Reporter is like this:
 * <ol>
 * <li>{@link #startTestClass(String)}</li>
 * <li>{@link #startTest(String, String)}</li>
 * <li>log....()</li>
 * <li>log....()</li>
 * <li>{@link #startTest(String, String)}</li>
 * <li>log....()</li>
 * <li>{@link #startTestClass(String)}</li>
 * <li>{@link #startTest(String, String)}</li>
 * <li>log....()</li>
 * <li>log....()</li>
 * <li>...</li>
 * </ol>
 */
public class Reporter {

  /**
   *
   */
  private static final String RUN = "Run";
  /**
   * The file name of the report that will be created.
   */
  public static  String REPORT_FILE_NAME = "TestReport.html";
  private static ExtentTest testClassReporter = null;
  private static ExtentTest testCasesReporter = null;
  private static ExtentReports extent = null;
  private static String resultsReportDirLocation = null;
  private static boolean isInitialized = false;

  /**
   * Initializes the reporter and creates the folder where the report shall be finally created when a call to
   * {@link #close()} is done.<br>
   *
   * @param browserName the name of the browser against which the tests will run
   * @see #getResultsReportDirLocation() for getting the location of the report
   */
  public static void initializeReporter(final String browserName) {
    if (isInitialized) {
      return;
    }
    if (getResultsReportDirLocation() == null) {
      setResultsReportDirLocation(createReportFolder());
    }
    extent = new ExtentReports();
    ExtentSparkReporter htmlReporter=new ExtentSparkReporter(getResultsReportDirLocation() + "/" + REPORT_FILE_NAME);
    extent.attachReporter(htmlReporter);
    htmlReporter.config().setDocumentTitle(getDocumentTitle(browserName));
    htmlReporter.config().setReportName(getReportName(browserName));
    htmlReporter.config().setTheme(Theme.DARK);
    // InputStream configFileInputStream =
    // DriverSetup.class.getClassLoader().getResourceAsStream("/config/extent_config.xml");
    // htmlReporter.loadConfig(configFileInputStream);
    // example xml config is here: https://github.com/anshooarora/extentreports-java/blob/master/extent-config.xml
   
    extent.attachReporter(htmlReporter);
    isInitialized = true;
  }

  private static String getDocumentTitle(final String browserName) {
    File runFolder = new File(getResultsReportDirLocation());
    return runFolder.getParentFile().getName() + " - " + " - " + browserName;
  }

  private static String getReportName(final String browserName) {
    File runFolder = new File(getResultsReportDirLocation());
    String buildTag = System.getenv().get("BUILD_TAG"); // coming from Jenkins build
    if (buildTag == null) {
      return runFolder.getParentFile().getName() + " - " + browserName;
    }
    return runFolder.getParentFile().getName() + " - " + browserName + " - " + buildTag;
  }

  /**
   * Must be called once per test class that is about to be executed. The next call must be a
   * {@link #startTest(String, String)} to define that a test case is starting.
   *
   * @param testClassName the name of the test class, must not be null
   */
  public static void startTestClass(final String testClassName) {
    testClassReporter = extent.createTest(testClassName);
  }

  /**
   * Must be called before each single test case in a test class and after a call to
   * {@link #startTestClass(String)}.<br>
   * After a call to this method the steps of a test case can be logged using any of the log... methods.
   *
   * @param tcsName the name of the test, must not be null
   * @param tcsDesc the description of the test, maybe null
   */
  public static void startTest(final String tcsName, final String tcsDesc) {
    if (testClassReporter == null) {
      return;
    }
    testCasesReporter = testClassReporter.createNode(tcsName);
  }

  /**
   * Closes the report so that it is being persisted to disk in a html file.
   * @throws IOException 
   */
  public static void close() throws IOException {
    System.out
        .println("END: Writing test result report to: " + new File(getResultsReportDirLocation(), REPORT_FILE_NAME));
    extent.flush();
    isInitialized = false;

    try {
      String reportZip = getResultsReportDirLocation() + "/" + REPORT_FILE_NAME + ".zip";
      File reportZipFile = new File(reportZip);
      if (reportZipFile.exists()) {
        boolean fileHasBeenDeleted = reportZipFile.delete();
        if (!fileHasBeenDeleted) {
          System.err
              .println("ERROR: Could not delete already existing report zip file: " + reportZipFile.getAbsolutePath());
        }
      }
      ZipFile zipFile=null;
      try
      {
      zipFile = new ZipFile(reportZip);
      ZipParameters zipParams = new ZipParameters();
      zipParams.setIncludeRootFolder(false);
 
      zipFile.createSplitZipFileFromFolder(new File(getResultsReportDirLocation()), zipParams, false, 0);
      System.out.println("END: Zipped html report and all screenshots: " + reportZipFile.getAbsolutePath());
      }
      finally
      {
        zipFile.close();
      }
    }
    catch (ZipException e) {
      e.printStackTrace();
    }


  }

  @SuppressWarnings("javadoc")
  public static void logError(final String message) {
    System.err.println("ERROR: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.FAIL, MarkupHelper.createCodeBlock(message));
  }

  @SuppressWarnings("javadoc")
  public static void logError(final Throwable throwable) {
    System.err.println("ERROR: " + throwable.getMessage());
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.FAIL, throwable);
  }

  @SuppressWarnings("javadoc")
  public static void logFailure(final Throwable throwable) {
    System.err.println("FAIL: " + throwable.getMessage());
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.FAIL, throwable);
  }

  /**
   * Logs the given error and adds the given screenshot too.<br>
   * ATTENTION: THis method does not create the screenshot it takes an existing screenshot file.
   *
   * @param message the message to log first before the given screenshot is also added to the report. Must not be null.
   * @param screenshotFileLocation the location where the screenshot is being stored. Must be an existing file and must
   *          not be null.
   * @param screenshotTitle the title to give to the screenshot in the report, may be null
   */
  public static void logErrorWithScreenshot(final String message, final String screenshotFileLocation,
      final String screenshotTitle) throws IOException {
    logError(message);
    testCasesReporter.log(Status.FAIL,
        "Screenshot: " + testCasesReporter.addScreenCaptureFromPath(screenshotFileLocation, screenshotTitle));
  }

  /**
   * Logs the information from the given throwable with ERROR severity and adds the given screenshot too.<br>
   * ATTENTION: This method does not create the screenshot it takes an existing screenshot file.
   *
   * @param throwable the throwable to log, must not be null
   * @param screenshotFileLocation the location where the screenshot is being stored. Must be an existing file and must
   *          not be null.
   */
  public static void logErrorWithScreenshot(final Throwable throwable, final String screenshotFileLocation) throws IOException {
    testCasesReporter.log(Status.FAIL, throwable,
        MediaEntityBuilder.createScreenCaptureFromPath(screenshotFileLocation).build());
  }

  /**
   * Logs the information from the given throwable with FAIL severity and adds the given screenshot too.<br>
   * ATTENTION: This method does not create the screenshot it takes an existing screenshot file.
   *
   * @param throwable the throwable to log, must not be null
   * @param screenshotFileLocation the location where the screenshot is being stored. Must be an existing file and must
   *          not be null.
   */
  public static void logFailureWithScreenshot(final Throwable throwable, final String screenshotFileLocation) throws IOException {
    testCasesReporter.log(Status.FAIL, throwable,
        MediaEntityBuilder.createScreenCaptureFromPath(screenshotFileLocation).build());
  }

  /**
   * Logs the given failure and adds the given screenshot too.<br>
   * ATTENTION: THis method does not create the screenshot it takes an existing screenshot file.
   *
   * @param message the message to log first before the given screenshot is also added to the report. Must not be null.
   * @param screenshotFileLocation the location where the screenshot is being stored. Must be an existing file and must
   *          not be null.
   * @param screenshotTitle the title to give to the screenshot in the report, may be null
   */
  public static void logFailureWithScreenshot(final String message, final String screenshotFileLocation,
      final String screenshotTitle) throws IOException {
    logFailure(message);
    testCasesReporter.log(Status.FAIL, screenshotTitle,
        MediaEntityBuilder.createScreenCaptureFromPath(screenshotFileLocation).build());
  }
  /**
   * Logs the given failure and adds the given screenshot too.<br>
   * ATTENTION: THis method does not create the screenshot it takes an existing screenshot file.
   *
   * @param message the message to log first before the given screenshot is also added to the report. Must not be null.
   * @param screenshotFileLocation the location where the screenshot is being stored. Must be an existing file and must
   *          not be null.
   * @param screenshotTitle the title to give to the screenshot in the report, may be null
   */
  public static void logFailureWithScreenshot(final String message, final String resMessage, final String screenshotFileLocation,
      final String screenshotTitle) throws IOException {
    logFailure(message, resMessage);
    testCasesReporter.log(Status.FAIL, screenshotTitle,
        MediaEntityBuilder.createScreenCaptureFromPath(screenshotFileLocation).build());
  }
  /**
   * Logs the given sucess and adds the given screenshot too.<br>
   * ATTENTION: THis method does not create the screenshot it takes an existing screenshot file.
   *
   * @param message the message to log first before the given screenshot is also added to the report. Must not be null.
   * @param screenshotFileLocation the location where the screenshot is being stored. Must be an existing file and must
   *          not be null.
   * @param screenshotTitle the title to give to the screenshot in the report, may be null
   */
  public static void logPassWithScreenshot(final String message, final String screenshotFileLocation,
      final String screenshotTitle) throws IOException {
    logPass(message);
    String testContext = screenshotTitle.replaceAll("[^\\w.-]", "_"); // make sure the testContext is a valid file name by                                          // replacing certain characr
    testCasesReporter.log(Status.PASS,  testContext,
        MediaEntityBuilder.createScreenCaptureFromPath(screenshotFileLocation).build());
  }
  /**
   * Logs the given sucess and adds the given screenshot too.<br>
   * ATTENTION: THis method does not create the screenshot it takes an existing screenshot file.
   *
   * @param message the message to log first before the given screenshot is also added to the report. Must not be null.
   * @param screenshotFileLocation the location where the screenshot is being stored. Must be an existing file and must
   *          not be null.
   * @param screenshotTitle the title to give to the screenshot in the report, may be null
   */
  public static void logPassWithScreenshot(final String message, final String resultMessage, final String screenshotFileLocation,
      final String screenshotTitle) throws IOException {
    logPass(message, resultMessage);
    String testContext = screenshotTitle.replaceAll("[^\\w.-]", "_"); // make sure the testContext is a valid file name by                                          // replacing certain characr
    testCasesReporter.log(Status.PASS,  testContext,
        MediaEntityBuilder.createScreenCaptureFromPath(screenshotFileLocation).build());
  }
  @SuppressWarnings("javadoc")
  public static void logInfo(final String message) {
    System.out.println("INFO: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.INFO, MarkupHelper.createCodeBlock(message));
  }

  @SuppressWarnings("javadoc")
  public static void logPass(final String message) {
    System.out.println("PASS: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.PASS, MarkupHelper.createCodeBlock(message));

  }
  @SuppressWarnings("javadoc")
  public static void logPass(final String message, final String resultMessage) {
    System.out.println("PASS: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.PASS, MarkupHelper.createCodeBlock(message, resultMessage));

  }
  @SuppressWarnings("javadoc")
  public static void logFailure(final String message, final String resultMessage) {
    System.err.println("FAIL: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.FAIL, MarkupHelper.createCodeBlock(message, resultMessage));
  }

  @SuppressWarnings("javadoc")
  public static void logFailure(final String message) {
    System.err.println("FAIL: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.FAIL, MarkupHelper.createCodeBlock(message));
  }

  @SuppressWarnings("javadoc")
  public static void logWarning(final String message) {
    System.err.println("WARNING: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.WARNING, MarkupHelper.createCodeBlock(message));
  }
  @SuppressWarnings("javadoc")
  public static void logSkip(final String message) {
    System.out.println("SKIP: " + message);
    if (testCasesReporter == null) {
      return;
    }
    testCasesReporter.log(Status.SKIP, MarkupHelper.createCodeBlock(message));
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
    Reporter.resultsReportDirLocation = resultsReportDirLocation;
  }
  
  /**
   * @param name
   */
  public static void setReportName(final String name)
  {
    Reporter.REPORT_FILE_NAME= name+".html";
  }
}
