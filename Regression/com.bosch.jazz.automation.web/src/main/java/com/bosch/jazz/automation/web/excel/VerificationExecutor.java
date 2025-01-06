/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.common.constants.VariableExpander;

/**
 * This class uses java reflection to find and invoke a given lookup method inside a given lookup class. Gives back the
 * return value from the lookup method only if the value is a String.
 */
public class VerificationExecutor {

  WebDriverCustom driverCustom;
  static String dot = ".";
  static String lineNumber = " and line number ";
  static String[] packageName = { "com.bosch.jazz.automation.web.pagemodel.verification.rqm.",
      "com.bosch.jazz.automation.web.pagemodel.verification.",
      "com.bosch.jazz.automation.web.pagemodel.verification.dng.",
      "com.bosch.jazz.automation.web.pagemodel.verification.ccm.",
      "com.bosch.jazz.automation.web.pagemodel.verification.gc.", "com.bosch.jazz.automation.web.excel.",
      "com.bosch.jazz.automation.web.pagemodel.verification.jrs." };

  /**
   * @param driverCustom a public parameterised constructor.
   */
  public VerificationExecutor(final WebDriverCustom driverCustom) {
    this.driverCustom = driverCustom;
  }

  /**
   * Main method for the Command Executor.
   * 
   * @param command ExcelScriptCommand object from the excel reader.
   * @param testDataMap Environment test Data
   * @return returningType TestAcceptanceMessage object containing test validation result.
   * @throws Exception handles reflection exceptions.
   */
  public TestAcceptanceMessage verifyCommand(final ExcelScriptCommand command, Map<String, String> testDataMap)
      throws Exception {

    String className = command.getAction().substring(0, command.getAction().indexOf(CommandExecutor.dot));
    String methodName = command.getAction().substring(command.getAction().indexOf(CommandExecutor.dot) + 1);

    Object[] arguments = getArguments(command, testDataMap);
    Class<?> modelClass = lookupClass(className, command);
    if (modelClass != null) {
      LookUpMethod lookUpMethod = new LookUpMethod();
      LookUpMethodValues methodValue =
          lookUpMethod.lookupMethod(modelClass, methodName, arguments, testDataMap, command);
      Method method = methodValue.getMethod();
      arguments = methodValue.getArguments();

      Object obj = modelClass.getDeclaredConstructor(WebDriverCustom.class).newInstance(this.driverCustom);
      Object returningType = method.invoke(obj, arguments);
      if (returningType instanceof TestAcceptanceMessage) {
        return (TestAcceptanceMessage) returningType;
      }
    }
    return null;
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
    for (int index = 0; index < VerificationExecutor.packageName.length; index++) {
      try {
        if (Class.forName(VerificationExecutor.packageName[index] + className) != null) {
          contextClass = Class.forName(VerificationExecutor.packageName[index] + className);
        }
      }
      catch (ClassNotFoundException e) {
        if ((index == (VerificationExecutor.packageName.length - 1)) && (contextClass == null)) {
          throw new ClassNotFoundException("Class " + className +
              " is not available in any of the packages. Verify for possible typo in line number " +
              command.getLineNumber() + " of sheet " + command.getSheetName());
        }
      }
    }
    return contextClass;
  }

  private List<VerificationConfig> iterateMapAndFetchValidationMethod(String actionName,
      Map<String, List<VerificationConfig>> verificationMap, int rating) {
    List<VerificationConfig> iteratingList = null;
    List<VerificationConfig> finalVerificationList = null;

    for (Map.Entry<String, List<VerificationConfig>> map : verificationMap.entrySet()) {
      if (map.getKey().equals(actionName)) {
        iteratingList = map.getValue();
        break;
      }
    }

    if (iteratingList != null && !iteratingList.isEmpty()) {
      finalVerificationList = new ArrayList<>();
      for (VerificationConfig verificationConfig : iteratingList) {
        if (verificationConfig.getRating() <= rating) {
          finalVerificationList.add(verificationConfig);
        }
      }
    }

    return finalVerificationList;
  }

  /**
   * Method used to execute each of the verification methods
   * 
   * @param testDataMap contains rating value from global property file
   * @param command Previously executed Excel Script Command
   * @return List of Verification Results
   * @throws Exception When Excel Configuration is missing or verification does not work
   */
  public List<TestAcceptanceMessage> executeVerifyCommandList(Map<String, String> testDataMap,
      ExcelScriptCommand command) throws Exception {
    List<TestAcceptanceMessage> resultList = new LinkedList<>();
    if (testDataMap.get("RATING") != null) {
      VerificationReader reader = new VerificationReader();
      String filePath =
          new File("src/test/resources/Verification_Config_Test/verification_file.xlsx").getAbsolutePath();
      Map<String, List<VerificationConfig>> verificationMap;
      try {
        verificationMap = reader.readVerificationExcelFile(filePath, "verification");
      }
      catch (IOException e) {
        resultList.add(new TestAcceptanceMessage(false, "\nError:\n" + e.getMessage()));
        return resultList;
      }
      List<VerificationConfig> verificationConfigList = iterateMapAndFetchValidationMethod(command.getAction(),
          verificationMap, Integer.parseInt(testDataMap.get("RATING")));
      if (verificationConfigList != null) {
        for (VerificationConfig verificationConfig : verificationConfigList) {
          ExcelScriptCommand verifyCommand = new ExcelScriptCommand();
          verifyCommand.setAction(verificationConfig.getVerificationMethod());
          verifyCommand.setParameter(command.getParameter());
          verifyCommand.setVerificationParameter(command.getVerificationParameter());
          verifyCommand.setSheetName(command.getSheetName());
          verifyCommand.setLineNumber(command.getLineNumber());
          TestAcceptanceMessage currentResult = null;
          try {
            currentResult = verifyCommand(verifyCommand, testDataMap);
          }
          catch (Exception e) {
            currentResult = new TestAcceptanceMessage(false, "\nError:\n" + e.getMessage());
          }
          finally {
            resultList.add(currentResult);
          }
        }
      }
    }
    return resultList;
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
    Object[] argumentsCommand = new Object[10];
    Object[] argumentsVerification;
    if (command.getParameter() == null && command.getVerificationParameter() == null) {
      argumentsCommand[0] = env.get("lastResult");
      return removeNull(argumentsCommand);
    }
    argumentsCommand = getArgumentsByParameters(command.getParameter(), env);
    argumentsVerification = removeNull(getArgumentsByParameters(command.getVerificationParameter(), env));
    Object[] arguments = new Object[10];
    for (int i = 0; i < argumentsCommand.length; i++) {
      if (argumentsCommand[i] == null) {
        int c = i;
        for (Object verifParam : argumentsVerification) {
          arguments[c++] = verifParam;
        }
        break;
      }
      arguments[i] = argumentsCommand[i];
    }

    // add last returned value from pagemodel method
    for (int j = 0; j < arguments.length; j++) {
      if (arguments[j] == null) {
        arguments[j] = env.get("lastResult");
        break;
      }
    }
    return removeNull(arguments);
  }

  /**
   * @param parameter
   * @param env
   * @return
   */
  private Object[] getArgumentsByParameters(List<SimpleImmutableEntry<String, String>> parameter,
      Map<String, String> env) {
    Object[] arguments = new Object[10];
    if (parameter == null) {
      return arguments;
    }
    Map<String, String> map = new LinkedHashMap<>();
    int i = 0;
    while ((i < parameter.size()) && (parameter.get(i).getKey() != null)) {
      String resolvedKey = VariableExpander.resolveNestedProperties(parameter.get(i).getKey(), env).trim();
      String resolvedValue = VariableExpander.resolveNestedProperties(parameter.get(i).getValue(), env).trim();
      map.put(resolvedKey, resolvedValue);
      i++;
    }
    if (map.size() > 0) {
      arguments[0] = map;
    }
    int k = 0;
    for (int j = map.size(); j < parameter.size(); j++) {
      if (map.size() > 0) {
        arguments[++k] = VariableExpander.resolveNestedProperties(parameter.get(j).getValue(), env).trim();
      }
      else {
        arguments[k++] = VariableExpander.resolveNestedProperties(parameter.get(j).getValue(), env).trim();
      }
    }
    return arguments;
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

}
