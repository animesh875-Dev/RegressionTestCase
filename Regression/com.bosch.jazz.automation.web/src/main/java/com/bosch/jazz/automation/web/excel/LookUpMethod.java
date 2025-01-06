/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author HCM6KOR This class takes a Class<?> Class and a method name in String and returns the Method method with the
 *         argument array for the method to invoke.
 */
public class LookUpMethod {

  /**
   * This method takes a Class<?> Class and a method name in String and returns the Method method with the argument
   * array for the method to invoke.
   *
   * @param lookupClass Class in which the method to be looked for.
   * @param methodName in String.
   * @param arguments Object array constructed based on the parameters provided in excel file.
   * @param env Environment provider map.
   * @param command ExcelScriptCommand object from the excel reader.
   * @return LookUpMethodValues having Method to be invoked and an argument array to be passed.
   * @throws NoSuchMethodException if the LookUpMethod is not found in the class.
   */
  public LookUpMethodValues lookupMethod(final Class<?> lookupClass, final String methodName, final Object[] arguments,
      final Map<String, String> env, final ExcelScriptCommand command) throws NoSuchMethodException {
    LookUpMethodValues method;
    Method[] methods = lookupClass.getMethods();
    for (Method m : methods) {
      if (m.getName().equals(methodName)) {
        method = getMethodAndAgrument(m, arguments, env);
        if ((method.getMethod() != null) && arguments.length == method.getArguments().length) {
          return method;
        }
      }
    }

    throw new NoSuchMethodException(
        "Method " + methodName + " entered in line number " + command.getLineNumber() + " of sheet " +
            command.getSheetName() + " of the excel file is not available for the set of parameters provided");
  }

  /**
   * This method takes a method and checks if it is a bestMatch based on the number of arguments and type of arguments.
   *
   * @param m method to be checked for if it is the bestMatch.
   * @param arguments Object array constructed based on the parameters provided in excel file.
   * @param env Environment provider map.
   * @return LookUpMethodValues having Method to be invoked and an argument array to be passed.
   */
  private LookUpMethodValues getMethodAndAgrument(final Method m, final Object[] arguments,
      final Map<String, String> env) {
    LookUpMethodValues method = new LookUpMethodValues();
    if ((m.getParameters().length == 0) || (bestMatch(m, arguments))) {
      method.setMethod(m);
      method.setArguments(arguments);
      return method;
    }
    if (arguments.length == 0) {
      throw new IllegalArgumentException("Method " + m.getName() + " was expecting arguments, but none were provided.");
    }
    if (arguments[0] instanceof LinkedHashMap) {
      @SuppressWarnings("unchecked")
      Map<String, String> map = (Map<String, String>) arguments[0];
      if (m.getParameterCount() == map.size()) {
        method = getValuesAsArguments(m, map);
      }
      else {
        method = getValuesFromEnvironment(m, map, env);
      }
    }
    return method;
  }


  /**
   * This method looks for excel parameters and environment map for arguments and checks if the method is a bestMatch.
   * Here we search for argument values in both the input maps using formal parameter name as key.---------------------
   * "Class.method | map {pName1:pVal1}, env {pName2:pVal2}" is "method (String pName1, String pName2)"
   *
   * @param m method to be checked for if it is the bestMatch.
   * @param map taken from the zeroth index of the arguments array.
   * @param arguments Object array constructed based on the parameters provided in excel file.
   * @return LookUpMethodValues having Method to be invoked and an argument array to be passed.
   */
  private LookUpMethodValues getValuesFromEnvironment(final Method m, final Map<String, String> map,
      final Map<String, String> env) {
    LookUpMethodValues method = new LookUpMethodValues();
    Object[] newArguments = new Object[10];
    for (int j = 0; j < m.getParameters().length; j++) {
      if (map.get(m.getParameters()[j].getName()) != null) {
        newArguments[j] = map.get(m.getParameters()[j].getName());
      }
      if ((env.get(m.getParameters()[j].getName()) != null) && (newArguments[j] == null)) {
        newArguments[j] = env.get(m.getParameters()[j].getName());
      }
    }
    newArguments = removeNull(newArguments);
    if (bestMatch(m, newArguments)) {
      method.setMethod(m);
      method.setArguments(newArguments);
    }
    return method;
  }

  /**
   * This method looks for excel parameters for arguments and checks if the method is a bestMatch.---------------------
   * The invoked method takes n number of String parameters. The argument is passed as a map with n number of key value
   * pairs. Here we get the formal parameter name of the first parameter of the invoking method and compare it with the
   * key of the first entry from the map. If it matches, then the value is assigned to the first parameter and so on.
   * "Class.method | paramName1:paramVal1, paramName2:paramVal2" is "method (String paramName1, String paramName2)"
   *
   * @param m method to be checked for if it is the bestMatch.
   * @param map taken from the zeroth index of the arguments array.
   * @param arguments Object array constructed based on the parameters provided in excel file.
   * @return LookUpMethodValues having Method to be invoked and an argument array to be passed.
   */
  private LookUpMethodValues getValuesAsArguments(final Method m, final Map<String, String> map) {
    LookUpMethodValues method = new LookUpMethodValues();
    int i = 0;
    Object[] newArguments = new Object[10];
    for (Map.Entry<String, String> entry : map.entrySet()) {
      if (m.getParameters()[i].getName().equals(entry.getKey())) {
        newArguments[i] = entry.getValue();
      }
      i++;
    }
    newArguments = removeNull(newArguments);
    if (bestMatch(m, newArguments)) {
      method.setMethod(m);
      method.setArguments(newArguments);
    }
    return method;
  }

  /**
   * This method verifies if the method provided by the lookupMethod is the best match based on the arguments list
   * provided by the excel reader.
   *
   * @param Lookup method to look for the best match.
   * @param arguments parameter array from the excel reader.
   * @return true if the lookup method is a best match.
   */
  private boolean bestMatch(final Method m, final Object[] arguments) {
    if (m.getParameters().length == arguments.length) {
      for (int i = 0; i < arguments.length; i++) {
        String str1 = m.getParameters()[i].getType().getName();
        String str2 = arguments[i].getClass().getName();
        if (!(str1.equals(str2) || (mapAgruments(str1, str2)))) {
          return false;
        }
      }
      return true;
    }
    return false;

  }

  /**
   * This method verifies if the type of argument is same in case of map arguments.
   *
   * @param str1 parameter type in String for the lookup method.
   * @param str2 parameter type in String for the excel parameter.
   * @return true if both the Strings represent maps.
   */
  private boolean mapAgruments(final String str1, final String str2) {
    return (str1.equals("java.util.Map") && (str2.equals("java.util.LinkedHashMap")));
  }

  /**
   * This method takes an array with null values and returns an array removing all the null values
   *
   * @param arguments parameter array from the excel reader including null values.
   * @return an array of arguments with no null values
   */
  private Object[] removeNull(final Object[] arguments) {
    ArrayList<Object> removedNull = new ArrayList<>();
    for (Object obj : arguments) {
      if (obj != null) {
        removedNull.add(obj);
      }
    }
    return removedNull.toArray(new Object[0]);
  }
}
