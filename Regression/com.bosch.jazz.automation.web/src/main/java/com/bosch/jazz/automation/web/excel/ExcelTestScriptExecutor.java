/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.ScreenShotUtil;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.VariableExpander;

import junit.framework.AssertionFailedError;

/**
 * This class is used to run the excel test script corresponding to a sheet in excel test suite.
 *
 * @author CIK5KOR
 */
public class ExcelTestScriptExecutor {

  private static final String ERROR = "Error";
  private static final String LAT_RESULT = "lastResult";
  private static final String PASSED = "Passed";
  private static final String ERROR_SERVER_DOWN =
      "Error - Server is down or network connection of the browser where test case is running is lost or slow.";
  private static final String ERROR_SERVER_SLOW =
      "Error - Server is slow or network connection of the browser where test case is running is lost or slow.";

  WebDriverCustom driverCustom;
  private List<ExcelScriptResult> result = new ArrayList<>();

  /**
   * @param driverCustom a public parameterised constructor.
   */
  public ExcelTestScriptExecutor(final WebDriverCustom driverCustom) {
    this.driverCustom = driverCustom;
  }

  /**
   * @param testDataMap Map of Test Data
   * @param commandList List of commands per sheet
   * @param driver RemoteWebDriver
   * @param excelSuiteRunnerObject ExcelSuiteRunner object to be set to commandExecutor
   * @return List of ExcelScriptResult containing results for a commandList
   * @throws Exception a generic exception to be propagated and mark the test script as failure
   */
  public List<ExcelScriptResult> executeScript(final Map<String, String> testDataMap,
      final List<ExcelScriptCommand> commandList, final RemoteWebDriver driver,
      final ExcelSuiteRunner excelSuiteRunnerObject) throws Exception {
    this.result = new ArrayList<>();
    for (int i = 0; i < commandList.size(); i++) {

      boolean skipFailIfNotExpected = false;
      if (((i + 1) < commandList.size()) &&
          commandList.get(i + 1).getAction().equalsIgnoreCase("skipExpectedFailure")) {
        if ((commandList.get(i + 1).getParameter() == null) && commandList.get(i + 1).getParameter().isEmpty()) {
          throw new InvalidArgumentException("Parameter for action \"" + commandList.get(i + 1).getAction() +
              "\" is null or empty.\n Parameter should be either true or false.");
        }
        String param = VariableExpander.resolveNestedProperties(commandList.get(i + 1).getParameter().get(0).getValue(),
            testDataMap);
        skipFailIfNotExpected = ((param != null) && param.equalsIgnoreCase("false")) ? false : true;
      }


      executeTestScript(testDataMap, commandList.get(i), driver, excelSuiteRunnerObject, skipFailIfNotExpected);
    }
    return this.result;
  }

  /**
   * @param testDataMap Map of Test Data
   * @param command ExcelScriptCommand command for a single action
   * @param driver RemoteWebDriver
   * @param excelSuiteRunnerObject ExcelSuiteRunner object to be set to commandExecutor
   * @throws Exception a generic exception to be propagated
   */
  private void executeTestScript(final Map<String, String> testDataMap, final ExcelScriptCommand command,
      final RemoteWebDriver driver, final ExcelSuiteRunner excelSuiteRunnerObject, final boolean skipFailIfNotExpected)
      throws Exception {
    CommandExecutor commandExecutor = new CommandExecutor(this.driverCustom);
    commandExecutor.setExcelSuiteRunnerObject(excelSuiteRunnerObject);
    ExcelScriptResult scriptCommand = new ExcelScriptResult();

    Object obj = null;
    try {
      LOGGER.LOG.info("********Started [" + command.getAction() + "] action at line number [" +
          command.getLineNumber() + "] in sheet [" + command.getSheetName() + "] **************");
      obj = commandExecutor.executeCommand(testDataMap, command);
      LOGGER.LOG.info("********[" + command.getAction() + "] action stopped **************");
      LOGGER.LOG
          .info("...................................................................................................");
    }
    catch (AssertionError e) {
      createScreenShot(command, scriptCommand, driver);
      scriptCommand.setResult("Failed");
      this.result.add(scriptCommand);
      throw e;
    }
    catch (Exception e) {
      // check server-start
      checkServerAvailable(scriptCommand, testDataMap, e);
      createScreenShot(command, scriptCommand, driver);
      scriptCommand.setResult("Error" + "\n" + e.getCause().getMessage());
      this.result.add(scriptCommand);
      throw e;
    }
    finally {
      // set the values for the scriptCommand
      scriptCommand.setAction(command.getAction());
      scriptCommand.setActionType(command.getActionType());
      scriptCommand.setDescription(command.getDescription());
      try {
        setParameter(command, scriptCommand, testDataMap);
      }
      catch (Exception ex) {
        scriptCommand.setValues(ex.getMessage());
      }
      scriptCommand.setReturnValue(testDataMap.get(LAT_RESULT));
      scriptCommand = setResults(obj, scriptCommand);
    }
    // after command do verification
    if (!command.getAction().equalsIgnoreCase("call")) {
      try {
        executeVerification(scriptCommand, testDataMap, driver, command, skipFailIfNotExpected);
      }
      catch (AssertionError ae) {
        // verification failed
        if (driver != null)
          driver.switchTo().defaultContent();
        createScreenShot(command, scriptCommand, driver);
        this.result.add(scriptCommand);
        throw ae;
      }
      catch (Exception e) {
        // verification failed
        if (driver != null)
          driver.switchTo().defaultContent();
        createScreenShot(command, scriptCommand, driver);
        this.result.add(scriptCommand);
        throw e;
      }
    }
    this.result.add(scriptCommand);
  }

  private void executeVerification(ExcelScriptResult scriptCommand, Map<String, String> testDataMap,
      RemoteWebDriver driver, ExcelScriptCommand command, boolean skipFailIfNotExpected) throws Exception {
    VerificationExecutor executor = null;
    List<TestAcceptanceMessage> tamObj = null;
    try {
      executor = new VerificationExecutor(this.driverCustom);
      tamObj = executor.executeVerifyCommandList(testDataMap, command);
    }
    catch (Exception e) {
      tamObj = new ArrayList<>();
      tamObj.add(new TestAcceptanceMessage(false, "\nError:\n" + e.getCause().getMessage()));
      scriptCommand.setVerificationResult(tamObj);
      throw e;
    }
    scriptCommand.setVerificationResult(tamObj);

    setScreenshotIfRequired(scriptCommand, command, driver);

    if (skipFailIfNotExpected &&
        scriptCommand.getVerificationResult().stream().anyMatch(x -> x.getState().equalsIgnoreCase("PASSED"))) {
      List<String> verList =
          scriptCommand.getVerificationResult().stream().map(x -> x.getMessage()).collect(Collectors.toList());
      String joining = verList.stream().collect(Collectors.joining());
      throw new AssertionFailedError(joining);
    }
    if (!skipFailIfNotExpected &&
        scriptCommand.getVerificationResult().stream().anyMatch(x -> x.getState().equalsIgnoreCase("FAILED"))) {
      List<String> verList =
          scriptCommand.getVerificationResult().stream().map(x -> x.getMessage()).collect(Collectors.toList());
      String joining = verList.stream().collect(Collectors.joining());
      throw new AssertionFailedError(joining);
    }
  }


  private void setScreenshotIfRequired(ExcelScriptResult scriptCommand, ExcelScriptCommand command,
      RemoteWebDriver driver) {
    if (!this.driverCustom.getWebDriverSetup().getIsScreenshortsRequiredForOnlyFailedCase()) {
      String actionName = scriptCommand.getAction();
      if (!(actionName.startsWith("Assert.") || actionName.equalsIgnoreCase("Set") ||
          actionName.equalsIgnoreCase("ConvertToTimeZone") || actionName.equalsIgnoreCase("skipExpectedFailure") ||
          actionName.equalsIgnoreCase("AppendCurrentDateAndTime") ||actionName.endsWith("switchTowindowTab") || actionName.endsWith("getSummaryOfExportedReqIF"))) {
        createScreenShot(command, scriptCommand, driver);
      }
      else {
        scriptCommand.setScreenshotFileName("");
      }
    }
    else {
      scriptCommand.setScreenshotFileName("");
    }
  }

  private void checkServerAvailable(ExcelScriptResult scriptCommand, Map<String, String> testDataMap, Exception e) {
    try {
      if (isServerAvailable(ExcelSuiteRunner.getServerUrl(testDataMap))) {
        if (e.getCause() != null) {
          scriptCommand.setResult(ERROR + "\n" + e.getCause().getMessage());
        }
        else {
          scriptCommand.setResult(ERROR);
        }
      }
      else {
        scriptCommand.setResult(ERROR_SERVER_DOWN);
      }
    }
    catch (SocketTimeoutException ste) {
      scriptCommand.setResult(ERROR_SERVER_SLOW);
    }
  }

  private void createScreenShot(final ExcelScriptCommand command, final ExcelScriptResult scriptCommand,
      final RemoteWebDriver driver) {
    if (driver != null) {
      String screenShotTitle = command.getAction() + DateUtil.getCurrentDateAndTime();
      String screenshotFileName =
          ScreenShotUtil.makeScreenShot(ReportFolder.getResultsReportDirLocation(), screenShotTitle, driver);
      scriptCommand.setScreenshotFileName(screenshotFileName);

    }
  }

  private void setParameter(final ExcelScriptCommand command, final ExcelScriptResult scriptCommand,
      final Map<String, String> testDataMap) {
    if (command.getStrParameter() != null) {
      scriptCommand.setParameter(command.getStrParameter());
      scriptCommand
          .setValues(VariableExpander.replacePropertyWithValueExceptCredentials(command.getParameter(), testDataMap));
    }
  }

  /**
   * @param obj
   */
  private ExcelScriptResult setResults(final Object obj, ExcelScriptResult esr) {
    if (obj instanceof TestAcceptanceMessage) {
      esr.setResult(((TestAcceptanceMessage) obj).getState());
      esr.setFailMessage(((TestAcceptanceMessage) obj).getMessage());
    }
    else if (esr.getResult() == null) {
      esr.setResult(PASSED);
    }
    return esr;
  }


  /**
   * @return List of ExcelScriptResult for a commandList
   */
  public List<ExcelScriptResult> getResult() {
    return this.result;
  }

  /**
   * @throws SocketTimeoutException - 
   */
  private boolean isServerAvailable(final String url) throws SocketTimeoutException {
    return Monitor.isReachable(12000, url);
  }
}
