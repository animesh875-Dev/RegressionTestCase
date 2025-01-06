/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DriverSetup;
import com.bosch.jazz.automation.web.common.WebDriverCustom;

/**
 * @author CIK5KOR ExcelSuiteRunner
 */
public class ExcelSuiteRunner extends Runner {

  private static final String DEFAULT_EXCEL_SUITE_PATH = "src\\test\\resources\\excel-web-test\\";
  private static final String DRIVER_STRING = "driver";
  @SuppressWarnings("javadoc")
  public static final String SETUP = "SetUp";
  @SuppressWarnings("javadoc")
  public static final String TEARDOWN = "TearDown";
  private static final String RM_SERVER_URL = "RM_SERVER_URL";
  private static final String CCM_SERVER_URL = "CCM_SERVER_URL";
  private static final String QM_SERVER_URL = "QM_SERVER_URL";
  private static final String FILE_PATH_RQM_RESULT = "target\\rqm_result.txt";
  private static final String BLOCKED = "43";
  @SuppressWarnings("unused")
  private static final String PASSED = "0";
  private static final String FAILED = "1";
  private static final String ERROR = "40";
  private static final String INCOMPLETE = "41";
  private File dlFolder;
  /** The WebDriver used for testing */
  protected RemoteWebDriver driver;

  private Map<String, Object> driverMap;
  private Map<String, String> testDataMap;
  private ExcelTestScriptExecutor etse;

  private final Map<String, Description> sheetDescriptionMap = new HashMap<>();
  private Map<String, List<ExcelScriptCommand>> excelScripts = new HashMap<>();
  private Map<String, List<ExcelScriptResult>> suiteResults = new HashMap<>();
  private static final String ERROR_SERVER_DOWN = "Error - Server is down";
  private static final String ERROR_SERVER_SLOW = "Error - Server is slow";
  private String resultStatusCode = BLOCKED;


  private Map<String, String> serverDetails = new HashMap<>();

  /**
   * This constructor is mandatory for a SuiteRunner
   *
   * @param testClass ignored
   */
  public ExcelSuiteRunner(final Class testClass) {

    super();
  }


  @SuppressWarnings("null")
  @Override
  public void run(final RunNotifier notifier) {
    Map<String, String> globalEnv = null;
    try {
      globalEnv = getServerEnvironmentPropertiesAndGenerateBuildInformation();
    }
    catch (Exception e) {
      LOGGER.LOG.error(e.getMessage());
      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
      notifier.fireTestFailure(new Failure(getDescription(), e));
      return;
    }

    String suiteFilePath = System.getProperty("suiteFilePath");// path of the Test Suite
    String suitePath = System.getProperty("suitePath"); // Path to directory of excel test cases.
    String suiteFileName = System.getProperty("suiteName"); // Path of the excel file.
    if (!(suiteFilePath == null && suitePath == null && suiteFileName == null)) {
      // Run excel test cases.
      if (org.apache.commons.lang.StringUtils.isNotBlank(suiteFileName)) {
        try {
          runSuite(suiteFileName, notifier, globalEnv);
          return;
        }
        catch (Exception e) {
          LOGGER.LOG.error(e.getMessage());
          LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));

          return;
        }
      }
      List<String> suites = new LinkedList<>();

      if (StringUtils.isBlank(suiteFilePath))
        try {
          suites = getListOfFilesFromSuitePathAndNestedFolders(suitePath);
        }
        catch (Exception e1) {

          LOGGER.LOG.error("Failed to fetch test cases from the suite path:" + suitePath + "\n" + e1.getMessage());
          LOGGER.LOG.error(ExceptionUtils.getStackTrace(e1));
          notifier.fireTestFailure(new Failure(getDescription(), e1));
        }
      else {
        ExcelReader excelReader = new ExcelReader();
        if (suiteFilePath != null)
          try {
            suites = excelReader.excelSuiteReader(suiteFilePath);
          }
          catch (Exception e) {

            LOGGER.LOG
                .error("Failed to fetch test cases from the suite file path:" + suiteFilePath + "\n" + e.getMessage());
            LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
            notifier.fireTestFailure(new Failure(getDescription(), e));
          }
      }

      if (suites != null && suites.isEmpty()) {
        Exception e = new InvalidParameterException(StringUtils.isNotBlank(suiteFilePath) ? suiteFilePath
            : suitePath + " file/directory has empty excel test cases to run.");
        notifier.fireTestFailure(new Failure(getDescription(), e));
        return;
      }
      for (String suite : suites) {
        String configSuffix = "config.suffix";
        configSuffix = globalEnv.containsKey(configSuffix) ? "-" + globalEnv.get(configSuffix) : ""; // Verify whether
                                                                                                     // test case name
                                                                                                     // does not ends
                                                                                                     // with
                                                                                                     // configuration.
        if (!suite.contains("Config" + configSuffix)) {
          try {
            runSuite(suite, notifier, globalEnv); // suite is an excel file with absolute path.
          }
          catch (Exception e) {
            LOGGER.LOG.error(e.getMessage());
            LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
          }
        }
      }
    }


  }

  /**
   * @return
   * @throws Exception
   */
  private Map<String, String> getServerEnvironmentPropertiesAndGenerateBuildInformation() throws Exception {
    // First assume test result is blocked and store the same value in rqm_result.txt file
    setResultStatus(BLOCKED);
    ReportFolder.setResultsReportDirLocation(ReportFolder.createReportFolder());
    try {
      File serverEnvironmentDir = new File(System.getProperty("environment"));
      if (serverEnvironmentDir.exists()) {
        Map<String, String> globalEnv = new HashMap<>();
        try {
          globalEnv = EnvironmentProvider.loadGlobalEnv();
        }
        catch (IOException e) {
          LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
        }
        LOGGER.LOG.info("Loaded server environment properties from file:" + serverEnvironmentDir.getAbsolutePath());
        // Added monitor here to check if server is up and is not slow
      /*  if (!Monitor.isReachable(12000, getServerUrl(globalEnv)+"/auth/authrequired")) {
          // First assume test result is blocked and store the same value in rqm_result.txt file
          setResultStatus(BLOCKED);
          throw new SocketTimeoutException(getServerUrl(globalEnv) +
              ": Error - Server is down or network connection of the browser  where test case is running is lost or slow.");
          // to indicate rqm command line adapter that server is either down or very slow for the
          // test
          // case to be executed.
        }
        // if server is reachable generate system information.*/
        this.serverDetails = ServerDetails.getServerDetails(globalEnv);
       return globalEnv;
      }
      // First assume test result is blocked and store the same value in rqm_result.txt file
      setResultStatus(BLOCKED);
      throw new FileNotFoundException("Server environment properties file path is invalid." + serverEnvironmentDir);
    }

    catch (NullPointerException e) {
      String environmentValue = System.getProperty("environment");
      LOGGER.LOG.error(
          "Server environment file not passed to load properties:\n" + environmentValue + " server environment path.");
      throw e;
    }

  }


  /**
   * @param suiteFileName name of the suite file.
   * @param notifier
   * @throws FileNotFoundException
   * @throws Exception
   */
  @SuppressWarnings("javadoc")
  public void runSuite(final String suiteFileName, final RunNotifier notifier, final Map<String, String> globalEnv)
      throws FileNotFoundException {
    String suiteName = "";
    suiteResults.clear();
    try {

      suiteName = new File(suiteFileName).getName();
      suiteName = suiteFileName.endsWith(".xlsx") ? suiteName.substring(0, suiteName.length() - 5) : suiteName;

      Map<String, String> suiteConfig = ConfigLoader.loadConfig(globalEnv, suiteName);
      this.testDataMap = EnvironmentProvider.mergeSuiteConfig(globalEnv, suiteConfig);
      LOGGER.LOG.info(suiteName + "-test case execution started.");
      LOGGER.LOG.info("Environment-" + this.testDataMap.get("config.suffix"));

      ExcelReader reader = new ExcelReader();
      this.excelScripts =
          reader.readExcelFile(new File(suiteFileName).getAbsolutePath(), System.getProperty("testCaseName", null)); // All
      suiteResults = new HashMap<>();
      // Execute sheet which does not start with ! and not sheet name does not equals to "SetUp" and "TearDown".
      for (Map.Entry<String, List<ExcelScriptCommand>> esc : this.excelScripts.entrySet()) {
        if (!esc.getKey().startsWith("!") && !esc.getKey().equals(SETUP) && !esc.getKey().equals(TEARDOWN)) {
          // main script.
          executeSheet(esc.getValue(), notifier, this.testDataMap, descriptionKey(suiteName, esc.getKey()));

        }
      }
    }
    catch (IOException e) {
      resultStatusCode = BLOCKED;
      LOGGER.LOG.error(e.getMessage());
      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
      notifier.fireTestFailure(new Failure(getDescription(), e));
      return;
    }

    catch (Exception e) {

      LOGGER.LOG.error(e.getMessage());
      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
    }

    finally {
      setResultStatus(resultStatusCode);
      // Finally write all the results to excel file with the same name of the suite name.
      try {
        writeResultsToExcel(suiteName, suiteResults, this.serverDetails);
      }
      catch (Exception e) {

        LOGGER.LOG.error("Error saving results to file.\n" + ExceptionUtils.getStackTrace(e));
        resultStatusCode = ERROR;
        setResultStatus(resultStatusCode);
        notifier.fireTestFailure(new Failure(getDescription(), e));
      }

    }
  }

  /**
   * @param suiteFileName
   * @param notifier
   * @param globalEnv
   */
  private void executeSheet(final List<ExcelScriptCommand> esc, final RunNotifier notifier,
      final Map<String, String> testDatMap, final String description) {
    Description sheetDescription = this.sheetDescriptionMap.get(description);
    boolean isMainScriptFailed = false;
    boolean isSetUpFailed = true; // Assume set up is failed.
    try {
      this.driverMap = getDriverSetup();
      this.etse = new ExcelTestScriptExecutor(new WebDriverCustom((RemoteWebDriver) this.driverMap.get(DRIVER_STRING),
          (DriverSetup) this.driverMap.get("driverSetup")));
      notifier.fireTestStarted(sheetDescription);// fetch description containing sheet name
      call(SETUP); // Call set up sheet before main script
      isSetUpFailed = false; // Set up method is passed.
      this.etse.executeScript(testDatMap, esc, this.driver, this);// Calling the main script.
      resultStatusCode = "0";// to indicate rqm command line adapter that test cases have executed succesfully.
    }
    catch (AssertionError e) {

      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
      if (isSetUpFailed) {
        resultStatusCode = ERROR;
      }
      else {
        resultStatusCode = FAILED;
        isMainScriptFailed = true;
      }
      notifier.fireTestFailure(new Failure(sheetDescription, e));// fetch description containing
                                                                 // sheet name
    }
    catch (Exception e) {


      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
      resultStatusCode = ERROR;
      isMainScriptFailed = true;

      notifier.fireTestFailure(new Failure(sheetDescription, e));// fetch description containing
                                                                 // sheet name
    }
    finally {
      String result = "";
      if (!isSetUpFailed) {
        suiteResults.put(esc.get(0).getSheetName(), this.etse.getResult()); // Stores result of the main test case.
        result = this.etse.getResult().get(this.etse.getResult().size() - 1).getResult();
      }
      if (result.equals(ERROR_SERVER_DOWN) || result.equals(ERROR_SERVER_SLOW)) {
        resultStatusCode = ERROR;// to indicate rqm command line adapter that server is either down or very slow for the
                                 // test case to be executed.
        notifier.fireTestFinished(sheetDescription);// fetch description containing sheet name

      }
      else {

        try {
          tearDown(isMainScriptFailed, isSetUpFailed, sheetDescription, notifier);
        }
        catch (Exception e) {

          LOGGER.LOG.error(e.getMessage());
          LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
          resultStatusCode=ERROR; 

        }
        finally {
          notifier.fireTestFinished(sheetDescription);// fetch description containing sheet name
          // Remove the Browser Tasks and delete the folder
          ((DriverSetup) this.driverMap.get("driverSetup")).closeBrowserTasks();
        }
      }
    }

  }

  /**
   * Tear down method is called after main sheet commands get executed, this method runs even if the main sheet commands
   * failed.
   * 
   * @param isMainScriptFailed true if the main sheet commands get failed.
   * @param isSetUpFailed flag to know setup failed or not.
   * @param sheetDescription description of the sheet name.
   * @param notifier set notification of test case status to runner.
   */
  public void tearDown(final boolean isMainScriptFailed, final boolean isSetUpFailed, Description sheetDescription,
      final RunNotifier notifier) {
    try {
      call(TEARDOWN); // call tear down method after the main script.
    }
    catch (AssertionError e) {
      LOGGER.LOG.error(e.getMessage());
      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
      resultStatusCode = FAILED;
      notifier.fireTestFailure(new Failure(sheetDescription, e));
    }
    catch (Exception e) {

      LOGGER.LOG.error(e.getMessage());
      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
      if ((!isSetUpFailed) && (!isMainScriptFailed)) {
        resultStatusCode = INCOMPLETE;// to indicate rqm command line adapter that teardown has failed and status is
                                      // incomplete.
      }
      else
        resultStatusCode=ERROR; 

      notifier.fireTestFailure(new Failure(sheetDescription, e));
    }
    finally {
      closeBrowser();
    }
  }

  /**
   * Close the browser by deleting the downloads folder.
   */
  public void closeBrowser() {
    FileUtils.deleteQuietly(this.dlFolder); // clean the downloads folder
    if (this.driver != null) {
      this.driver.quit();
    }
  }

  /**
   * @param suiteName name of the test suite.
   * @param suiteResults1 - results of suite.
   * @param serverDetails1 - details of server.
   * @throws IOException - input, output exception.
   */
  private void writeResultsToExcel(final String suiteName, final Map<String, List<ExcelScriptResult>> suiteResults1,
      final Map<String, String> serverDetails1) throws IOException {
    ExcelResultWriter.write(suiteResults1, ReportFolder.getResultsReportDirLocation() + "\\" + suiteName, serverDetails1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Description getDescription() {
    String suiteFileName = System.getProperty("suiteName");
    String suitePath = System.getProperty("suitePath");
    String suiteFilePath = System.getProperty("suiteFilePath");
    ExcelReader excelReader = new ExcelReader();
    List<String> suiteFiles = new ArrayList<>();
    if (suiteFilePath != null) {
      try {
        suiteFiles = excelReader.excelSuiteReader(suiteFilePath);
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
    }
    if (StringUtils.isBlank(suitePath)) {
      suitePath = DEFAULT_EXCEL_SUITE_PATH;
    }
    if (StringUtils.isNotBlank(suiteFileName)) {
      return createDescriptionForSingleFile(suiteFileName);
    }
    List<String> suiteFilenames = new ArrayList<>();
    if (suiteFilePath == null) {
      try {
        suiteFilenames = getListOfFilesFromSuitePathAndNestedFolders(suitePath);
      }
      catch (Exception e) {
        LOGGER.LOG.error(e.getMessage());
      }
    }
    else {
      suiteFilenames = suiteFiles;
    }
    Description testDescription = Description.createTestDescription("ExcelSuiteRunner", "ExcelSuiteRunner");
    for (String suiteFilename : suiteFilenames) {
      testDescription.addChild(createDescriptionForSingleFile(suiteFilename));
    }
    return testDescription;

  }

  /**
   * @param suitePath directory where all excel test case files exists.
   * @return list of all file paths from suite folder and its nested folders.
   * @throws IOException
   */
  private List<String> getListOfFilesFromSuitePathAndNestedFolders(final String suitePath) throws IOException {
    List<String> suites = new ArrayList<>();
    Stream<Path> walk = null;
    try {
      walk = Files.walk(Paths.get(new File(suitePath).getAbsolutePath()));
      suites = walk.filter(path -> path.toFile().isFile()).map(Path::toString).collect(Collectors.toList());
    }
    finally {
      if (walk != null)
        walk.close();
    }
    return suites;
  }

  /**
   * @param sheets
   * @param testDescription1
   * @return Description containing the scripts (sheets) of a single excel file
   */
  private Description createDescriptionForSingleFile(final String suiteFileName) {
    String suiteName = new File(suiteFileName).getName();
    if (suiteFileName.endsWith(".xlsx")) {
      suiteName = suiteName.substring(0, suiteName.length() - 5);
    }
    Description testDescription1 = Description.createTestDescription(suiteName, suiteName);
    ExcelReader reader = new ExcelReader();
    try {
      this.excelScripts =
          reader.readExcelFile(new File(suiteFileName).getPath(), System.getProperty("testCaseName", null));
      for (Map.Entry<String, List<ExcelScriptCommand>> esc : this.excelScripts.entrySet()) {
        String sheetName = esc.getKey();
        if (!sheetName.startsWith("!") && !sheetName.equalsIgnoreCase(SETUP) && !sheetName.equalsIgnoreCase(TEARDOWN)) {
          String className = suiteFileName.replaceAll("[/\\\\]+([^/\\\\.]+)([.][xX][lL][sS][xX])?$", ".$1");
          Description sheetDescription = Description.createTestDescription(className, sheetName);
          testDescription1.addChild(sheetDescription);
          this.sheetDescriptionMap.put(descriptionKey(suiteName, sheetName), sheetDescription);
        }
      }
    }
    catch (IOException e) {
      LOGGER.LOG.error("Failed to create test case name in Junit Explorer. \n" + e.getMessage());
      LOGGER.LOG.error(ExceptionUtils.getStackTrace(e));
    }
    return testDescription1;
  }

  /**
   * @param suiteName name of the excel file.
   * @param sheetName name of sheet in excel file.
   * @return key for sheetDescriptionMap
   */
  protected String descriptionKey(final String suiteName, final String sheetName) {
    return suiteName + "/" + sheetName;
  }

  /**
   * @return Map<String, Object>
   * @throws Exception
   */
  @SuppressWarnings("javadoc")
  public Map<String, Object> getDriverSetup() throws Exception {

    this.driverMap = new HashMap<>();
    DriverSetup driverSetup = new DriverSetup(ExcelSuiteRunner.class);
    this.dlFolder = Files.createTempDirectory("automatedBrowserDownloads").toFile();
    this.driver = (RemoteWebDriver) driverSetup.initializeWebDriver(this.dlFolder);
    if (driverSetup.getBrowserMobProxy() != null) {
      driverSetup.getBrowserMobProxy().newHar();
    }
    this.driverMap.put(DRIVER_STRING, this.driver);
    this.driverMap.put("driverSetup", driverSetup);
    return this.driverMap;
  }

  /**
   * Executes the excel script commands of sheet which starts with name "!". Stores results the sheet in a map.
   * 
   * @param sheetName name of the sheet.
   * @throws Exception if the test data is not found.
   */
  public void call(final String sheetName) throws Exception {

    if (this.excelScripts.get(sheetName) == null) {
      if (sheetName.equalsIgnoreCase(SETUP) || sheetName.equalsIgnoreCase(TEARDOWN))
        return;
      throw new InvalidParameterException(sheetName + " sheet name is not correct or does not exists.");
    }
    List<ExcelScriptCommand> ese = this.excelScripts.get(sheetName);
    try {
      this.etse.executeScript(this.testDataMap, ese, (RemoteWebDriver) this.driverMap.get(DRIVER_STRING), this);
    }
    catch (Exception e) {
      throw e;
    }
    finally {
      suiteResults.put(sheetName, this.etse.getResult());
    }
  }

  /**
   * @param globalEnv gives the test data from the properties file.
   * @return server url from properties file.
   */
  public static String getServerUrl(final Map<String, String> globalEnv) {
    if (globalEnv.get(RM_SERVER_URL) != null ||
        (globalEnv.get(CCM_SERVER_URL) != null || globalEnv.get(QM_SERVER_URL) != null)) {
      String serverUrl =
          globalEnv.get(RM_SERVER_URL) != null ? globalEnv.get(RM_SERVER_URL) : globalEnv.get(CCM_SERVER_URL);
      if (serverUrl == null)
        serverUrl =
            globalEnv.get(CCM_SERVER_URL) != null ? globalEnv.get(CCM_SERVER_URL) : globalEnv.get(QM_SERVER_URL);
      return serverUrl;
    }
    return null;
  }

  /**
   * @param statusCode to be set to result text document which is read by RQM Adapter batch file.
   * @throws FileNotFoundException if the file is not found in the location.
   */
  public void setResultStatus(String statusCode) throws FileNotFoundException {
    try (PrintWriter out = new PrintWriter(FILE_PATH_RQM_RESULT)) {
      out.println(statusCode);
    }
  }
}