/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.InvalidArgumentException;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.VariableExpander;

/**
 * @author HCM6KOR This class uses java reflection to find and invoke a given lookup method inside a given lookup class.
 *         Gives back the return value from the lookup method only if the value is a String.
 */
public class CommandExecutor {

  private static final String ASSERT_EQUALS = "Assert.assertEquals";
  private static final String ASSERT_NOTEQUALS = "Assert.assertNotEquals";
  private static final String ASSERT_CONTAINS = "Assert.assertContains";
  private static final String ASSERT_CONTAINS_FALSE = "Assert.assertContainsFalse";
  private static final String APPEND_CURRENT_DATE_AND_TIME = "AppendCurrentDateAndTime";
  private static final String SET = "set";
  private static final String CONVERT_TO_TIME_ZONE = "ConvertToTimeZone";
  private static final String SKIP_EXPECTED_FAILURE = "skipExpectedFailure";
  WebDriverCustom driverCustom;
  private ExcelSuiteRunner excelSuiteRunnerObject;
  static String dot = ".";
  static String lineNumber = " and line number ";
  static String lastResult = "lastResult";
  static String[] packageName = { "com.bosch.jazz.automation.web.pagemodel.rqm.",
      "com.bosch.jazz.automation.web.pagemodel.", "com.bosch.jazz.automation.web.pagemodel.dng.",
      "com.bosch.jazz.automation.web.pagemodel.ccm.", "com.bosch.jazz.automation.web.pagemodel.gc.",
      "com.bosch.jazz.automation.web.excel.", "com.bosch.jazz.automation.web.pagemodel.jrs." };

  /**
   * @param driverCustom a public parameterised constructor.
   */
  public CommandExecutor(final WebDriverCustom driverCustom) {
    this.driverCustom = driverCustom;
  }

  /**
   * @return the excelSuiteRunnerObject
   */
  private ExcelSuiteRunner getExcelSuiteRunnerObject() {
    return this.excelSuiteRunnerObject;
  }


  /**
   * @param excelSuiteRunnerObject the excelSuiteRunnerObject to set
   */
  public void setExcelSuiteRunnerObject(ExcelSuiteRunner excelSuiteRunnerObject) {
    this.excelSuiteRunnerObject = excelSuiteRunnerObject;
  }

  /**
   * Main method for the Command Executor.
   *
   * @param env Environment provider map.
   * @param command ExcelScriptCommand object from the excel reader.
   * @return returningType TestAcceptanceMessage object containing test validation result.
   * @throws Exception handles reflection exceptions.
   */
  public TestAcceptanceMessage executeCommand(final Map<String, String> env, final ExcelScriptCommand command)
      throws Exception {
    String returningString = "No Return Value";

    if (!executeFrameworkCommand(env, command)) {
      String className = command.getAction().substring(0, command.getAction().indexOf(CommandExecutor.dot));
      String methodName = command.getAction().substring(command.getAction().indexOf(CommandExecutor.dot) + 1);
      Object[] arguments = getArguments(command, env);

      Class<?> modelClass = lookupClass(className, command);
      if (modelClass != null) {
        LookUpMethod lookUpMethod = new LookUpMethod();
        LookUpMethodValues methodValue = lookUpMethod.lookupMethod(modelClass, methodName, arguments, env, command);
        Method method = methodValue.getMethod();
        arguments = methodValue.getArguments();
        try {
          Object obj = modelClass.getDeclaredConstructor(WebDriverCustom.class).newInstance(this.driverCustom);
          Object returningType = method.invoke(obj, arguments);
          if (returningType instanceof TestAcceptanceMessage) {
            return (TestAcceptanceMessage) returningType;
          }
          else if (returningType != null) {
            returningString = returningType.toString();
          }
        }
        catch (IllegalArgumentException iae) {
          StringWriter sw = new StringWriter();
          PrintWriter pw = new PrintWriter(sw);
          iae.printStackTrace(pw);
          String sStackTrace = sw.toString(); // stack trace as a string
          throw new IllegalArgumentException("The data entered is incorrect in sheet name " + command.getSheetName() +
              lineNumber + command.getLineNumber() + "\n" + sStackTrace);
        }
        catch (Exception e) {
          throw e;
        }
        env.put(lastResult, returningString);
      }
      else {
        throw new ClassNotFoundException("The action key entered " + command.getAction() +
            " is incorrect in sheet name " + command.getSheetName() + lineNumber + command.getLineNumber());
      }
    }
    return null;

  }

  /**
   * This method handles the framework command.
   *
   * @param env Environment provider map.
   * @param ExcelScriptCommand object from the excel reader.
   * @return true if the command is one of the internal commands
   * @throws Exception
   */
  private boolean executeFrameworkCommand(final Map<String, String> env, final ExcelScriptCommand command)
      throws Exception {
    if (command.getAction().equalsIgnoreCase(SET)) {
      if (command.getParameter() != null) {
        processSetCommand(env, command);
        env.put(lastResult, "Set success");
        return true;
      }
      throw new IllegalArgumentException("Parameter for the set command is invalid at sheet Name " +
          command.getSheetName() + lineNumber + command.getLineNumber());
    }
    if (command.getAction().equalsIgnoreCase(APPEND_CURRENT_DATE_AND_TIME)) {
      if (command.getParameter() != null && command.getParameter().get(0) != null) {
        String propertyKey = command.getParameter().get(0).getValue();
        if (propertyKey.startsWith("${") && propertyKey.endsWith("}"))
          propertyKey = propertyKey.substring(2, propertyKey.length() - 1);
        String value = VariableExpander.resolveNestedProperties(command.getParameter().get(0).getValue(), env);
        String afterDateAndtimeAdding = value + "_" + DateUtil.getCurrentDateAndTime();
        LOGGER.LOG.info("Before appending current date and time :" + value);
        env.put(propertyKey, afterDateAndtimeAdding);
        LOGGER.LOG.info("After appending current date and time " + afterDateAndtimeAdding);
        return true;
      }
      throw new InvalidArgumentException("Parameter is not passed to append current date and time.");
    }
    if (command.getAction().equalsIgnoreCase(ASSERT_EQUALS)) {
      return executeAssertFrameworkCommand(env, command);
    }
    if (command.getAction().equalsIgnoreCase(ASSERT_NOTEQUALS)) {
      return executeAssertFrameworkCommand(env, command);
    }
    
    if (command.getAction().equalsIgnoreCase(ASSERT_CONTAINS)) {
      return executeAssertFrameworkCommand(env, command);
    }
    if (command.getAction().equalsIgnoreCase(ASSERT_CONTAINS_FALSE)) {
      return executeAssertFrameworkCommand(env, command);
    }
    if (command.getAction().equalsIgnoreCase(CONVERT_TO_TIME_ZONE)) {
      env.put(lastResult, convertToTimeZone(env, command));
      return true;

    }
    if (command.getAction().equalsIgnoreCase(SKIP_EXPECTED_FAILURE)) {
      env.put(lastResult, lastResult);
      return true;

    }
    if (command.getAction().equalsIgnoreCase("Call")) {

      Object[] args = getArguments(command, env);
      if (args != null && args.length == 1) {
        getExcelSuiteRunnerObject().call(args[0].toString());
        return true;
      }
      throw new IllegalArgumentException("Call command parameters must be null or empty.");

    }
    return false;
  }

  /**
   * @return converted date.
   */
  private String convertToTimeZone(final Map<String, String> env, final ExcelScriptCommand command) {
    Object[] args = getArguments(command, env);
    if (args != null && args.length == 3) {
      return TimeZoneConverter.changeTimezone(String.valueOf(args[0]), String.valueOf(args[1]),
          Integer.parseInt(String.valueOf(args[2])));

    }
    throw new IllegalArgumentException("convertToTimeZone command parameters must be null or empty.");
  }

  /**
   * @param env
   * @param command
   * @return
   */
  private boolean executeAssertFrameworkCommand(final Map<String, String> env, final ExcelScriptCommand command) {
    if (command.getParameter() != null) {
      processAssertCommand(env, command);
      env.put(lastResult, "Assert success");
      return true;
    }
    throw new IllegalArgumentException("Parameter for the Assert.assertEquals command is invalid at sheet Name " +
        command.getSheetName() + lineNumber + command.getLineNumber());
  }

  /**
   * This method handles the SET command.
   *
   * @param env Environment provider map.
   * @param command ExcelScriptCommand object from the excel reader.
   */
  private static void processSetCommand(final Map<String, String> env, final ExcelScriptCommand command) {
    if (command.getParameter().size() == 2) {
      String propertyKey = command.getParameter().get(0).getValue();
      if (propertyKey.startsWith("${") && propertyKey.endsWith("}"))
        propertyKey = propertyKey.substring(2, propertyKey.length() - 1);
      if (command.getParameter().get(1).getValue().equals(lastResult)) {
        env.put(propertyKey.trim(), env.get(lastResult));
        LOGGER.LOG.info("Set property \"" + propertyKey + "=" + env.get(lastResult) + "\"");

      }
      else {
        String value = VariableExpander.resolveNestedProperties(command.getParameter().get(1).getValue(), env);
        env.put(propertyKey.trim(), value.trim());
        LOGGER.LOG.info("Set property \"" + propertyKey + "=" + command.getParameter().get(1).getValue().trim() + "\"");
      }
    }
    else {
      throw new IllegalArgumentException("Parameter for the set command is invalid at sheet Name " +
          command.getSheetName() + lineNumber + command.getLineNumber());
    }
  }

  /**
   * This method handles the Assert.assertEquals command.
   *
   * @param env Environment provider map.
   * @param command ExcelScriptCommand object from the excel reader.
   */
  private static void processAssertCommand(final Map<String, String> env, final ExcelScriptCommand command) {
    String expected = null;
    String actual = env.get(lastResult);
    String message = "Assertion failed: The action key at line number " + (command.getLineNumber() - 1) + " of sheet " +
        command.getSheetName() + " is expected to be evaluated as " + actual + " but returned " + expected + " instead";
    if (command.getParameter().size() == 3) {
      message = VariableExpander.resolveNestedProperties(command.getParameter().get(0).getValue(), env);
      expected = VariableExpander.resolveNestedProperties(command.getParameter().get(1).getValue(), env);
      actual = VariableExpander.resolveNestedProperties(command.getParameter().get(2).getValue(), env);

    }
    else if (command.getParameter().size() == 2) {
      expected = VariableExpander.resolveNestedProperties(command.getParameter().get(0).getValue(), env);
      actual = VariableExpander.resolveNestedProperties(command.getParameter().get(1).getValue(), env);
    }
    else if (command.getParameter().size() == 1) {
      expected = VariableExpander.resolveNestedProperties(command.getParameter().get(0).getValue(), env);
    }
    else {
      throw new IllegalArgumentException("Parameter for the Assert.assertEquals command is invalid at sheet Name " +
          command.getSheetName() + lineNumber + command.getLineNumber());
    }
    LOGGER.LOG.info("Expected: " + expected);
    LOGGER.LOG.info("Actual: " + actual);
    if (command.getAction().equals(ASSERT_EQUALS)) {
      if( !actual.equals(expected))
        throw new IllegalArgumentException(message);
      
         LOGGER.LOG.info("Actual and expected are equal.");
      
    }
    if (command.getAction().equals(ASSERT_NOTEQUALS)) {
      assertNotEquals(message, expected, actual);
      LOGGER.LOG.info("Actual and expected are not equal.");

    }
    if (command.getAction().equals(ASSERT_CONTAINS)) {
      assertTrue(message, actual.contains(expected));
      LOGGER.LOG.info("Actual contains expected value.");
    }
    if (command.getAction().equals(ASSERT_CONTAINS_FALSE)) {
      assertFalse(message, actual.contains(expected));
      LOGGER.LOG.info("Actual does n't contains expected value.");
    }

  }

  /**
   * This method constructs an array of arguments which is used to find and invoke the best macthing look up method.
   * This method handles the following parameters. Single String, Multiple Strings, Single Map, Map with Strings
   * combination.
   *
   * @param command ExcelScriptCommand object from the excel reader.
   * @param env Environment provider map.
   * @return an array of objects with all the parameters.
   */
  public Object[] getArguments(final ExcelScriptCommand command, final Map<String, String> env) {
    if (command.getParameter() == null) {
      return new Object[0];
    }
    Object[] arguments = new Object[10];
    Map<String, String> map = new LinkedHashMap<>();
    int i = 0;
    while ((i < command.getParameter().size()) && (command.getParameter().get(i).getKey() != null)) {
      String resolvedKey = VariableExpander.resolveNestedProperties(command.getParameter().get(i).getKey(), env);
      String resolvedValue = VariableExpander.resolveNestedProperties(command.getParameter().get(i).getValue(), env);
      map.put(resolvedKey, resolvedValue);
      i++;
    }
    if (map.size() > 0) {
      arguments[0] = map;
    }
    int k = 0;
    for (int j = map.size(); j < command.getParameter().size(); j++) {
      if (map.size() > 0) {
        arguments[++k] = VariableExpander.resolveNestedProperties(command.getParameter().get(j).getValue(), env);
      }
      else {
        arguments[k++] = VariableExpander.resolveNestedProperties(command.getParameter().get(j).getValue(), env);
      }
    }
    return removeNull(arguments);
  }

  /**
   * This method takes an array with null values and returns an array removing all the null values
   *
   * @param arguments parameter array from the excel reader including null values.
   * @return an array of arguments with no null values
   */
  private static Object[] removeNull(final Object[] arguments) {
    ArrayList<Object> removedNull = new ArrayList<>();
    for (Object obj : arguments) {
      if (obj != null) {
        removedNull.add(obj);
      }
    }
    return removedNull.toArray(new Object[0]);
  }

  /**
   * This method looks for class based on the String className in all the given packages.
   *
   * @param className class name to be looked for.
   * @param command to get the line number and sheet name.
   * @return object of the class to be looked for.
   * @throws ClassNotFoundException if the class is not found in may of the packages.
   */
  private Class<?> lookupClass(final String className, final ExcelScriptCommand command) throws ClassNotFoundException {
    Class<?> contextClass = null;
    for (int index = 0; index < CommandExecutor.packageName.length; index++) {
      try {
        if (Class.forName(CommandExecutor.packageName[index] + className) != null) {
          contextClass = Class.forName(CommandExecutor.packageName[index] + className);
        }
      }
      catch (ClassNotFoundException e) {
        if ((index == (CommandExecutor.packageName.length - 1)) && (contextClass == null)) {
          throw new ClassNotFoundException("Class " + className +
              " is not available in any of the packages. Verify for possible typo in line number " +
              command.getLineNumber() + " of sheet " + command.getSheetName());
        }
      }
    }
    return contextClass;
  }

}
