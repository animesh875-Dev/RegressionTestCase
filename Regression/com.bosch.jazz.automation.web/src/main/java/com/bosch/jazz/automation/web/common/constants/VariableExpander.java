/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import java.security.InvalidParameterException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hrt5kor This class is to implement the below requirements. The special symbols like "$", "," and "\" have to
 *         be escaped by a preceding backslash: "\$", "\," etc. The colon has to be escaped only in the context of a map
 *         definition (see further below). The keyword parameters are separated by comma: Parameter1,
 *         parameter2,parameter3 Param1, ${PARAM2} Parameter with spaces\, comma and dollar \$, second parameter The
 *         keyword parameter must be one of the following types String This is a single string parameter
 *         Map<String,String> ${key1:value one, key two:value two, key3:value with a comma\, and a colon\:} and Trim the
 *         parameters after splitting.
 */
public class VariableExpander {

  /**
   * An instance variable used to replace when the text encounter with "\\",.
   */
  private String replacabel1 = "_%%%_";
  /**
   * An instance variable used to replace when the text encounter with "\\:".
   */
  private String replacabel2 = "_%%%%_";
  /**
   * An instance variable used to replace when the text encounter with "\\$".
   */
  private String replacabel3 = "_%%%%%_";


  /**
   * Public method used to replace given string when it encounters "\\," , "\\:", , "\\$" with instance variable
   * declared replacabel1, replacabel2, replacabel1.
   *
   * @param text input parameter
   * @return replaced string.
   */
  public String replaceBefore(final String text) {
    String replacedStr = text;
    if (replacedStr.contains("\\,") || replacedStr.contains("\\:") || replacedStr.contains("\\$")) {
      replacedStr = replacedStr.replace("\\,", this.replacabel1);
      replacedStr = replacedStr.replace("\\:", this.replacabel2);
      replacedStr = replacedStr.replace("\\$", this.replacabel3);
    }
    return replacedStr.trim();
  }

  /**
   * Public method used to replace given string when it encounters replacabel1, replacabel2, replacabel3 with instance
   * variable declared "\\," , "\\:", , "\\$".
   *
   * @param str input value
   * @return replaced string.
   */
  public String replaceAfter(final String str) {
    String replacedStr = str;
    if (str.contains(this.replacabel1)) {
      replacedStr = replacedStr.replace(this.replacabel1, ",");
    }
    if (str.contains(this.replacabel2)) {
      replacedStr = replacedStr.replace(this.replacabel2, ":");
    }
    if (str.contains(this.replacabel3)) {
      replacedStr = replacedStr.replace(this.replacabel3, "$");
    }
    return replacedStr;
  }

  /**
   * Public method changes the replacable string value to other replacable value if the text contains.
   *
   * @param text input parameter.
   * @param replacableString output parameter.
   * @return replaced string.
   */
  public String changeReplacableString(final String text, final String replacableString) {
    String replacabel = replacableString;
    while (text.contains(replacabel)) {
      for (int i = 0; i <= 255; i++) {
        if (text.contains(replacabel)) {
          replacabel = new StringBuilder(replacabel).append(Character.toString((char) i)).toString();

        }
        else {
          break;
        }
      }
    }
    return replacabel;
  }

  /**
   * Public method splits the parameter which are in type "param1,param2,param3" and returns list of simple immutable
   * entries.
   *
   * @param textOfMapParms parameter of type text;
   * @return list of AbstractMap.SimpleImmutableEntry<String,String> values.
   */
  private List<AbstractMap.SimpleImmutableEntry<String, String>> getListOfMapOfParamsBySplittingParam(
      final String textOfMapParms) {
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    String text = textOfMapParms;
    text = replaceBefore(text);
    String[] mapParams = text.trim().split(",");
    for (String param : mapParams) {
      list.add(new AbstractMap.SimpleImmutableEntry<>(null, replaceAfter(param)));
    }
    return list;
  }

  /**
   * Public method splits the parameter which are in type "key1:value1,key2:value2,key3:value3" and returns list of
   * simple immutable entries.
   *
   * @param textOfMapParms parameter of type text;
   * @return list of AbstractMap.SimpleImmutableEntry<String,String> values.
   */
  private List<AbstractMap.SimpleImmutableEntry<String, String>> getListOfMapOfParamsBySplittingMapOfParams(
      final String textOfMapParms) {
    String text = textOfMapParms;
    List<AbstractMap.SimpleImmutableEntry<String, String>> list = new LinkedList<>();
    SimpleImmutableEntry<String, String> map;
    text = text.substring(2, text.length() - 1);
    text = replaceBefore(text);
    String[] mapParams = text.split(",");
    for (String st : mapParams) {
      if (st.startsWith(":")) {
        throw new InvalidParameterException("Given string is not in map format as key is empty.");
      }
      if (st.endsWith(":")) {
        st = st + ",";
      }
      String[] mapkeyvalues = st.split(":");
      for (int i = 0; i < mapkeyvalues.length; i++) {
        if (mapkeyvalues[i].contains(",")) {
          mapkeyvalues[i] = mapkeyvalues[i].replace(",", "");
        }

      }
      if ((mapkeyvalues.length % 2) != 0) {
        throw new InvalidParameterException(
            "Given string is not in map format i.e every key should have value (key:value)-");
      }
      for (int i = 0; i < (mapkeyvalues.length - 1); i++) {
        map = new AbstractMap.SimpleImmutableEntry<>(replaceAfter(mapkeyvalues[i]).trim(),
            replaceAfter(mapkeyvalues[i + 1]).trim());
        list.add(map);
      }

    }
    return list;

  }

  /**
   * Public method to expand the variables or split into different parameters of the given input using specific
   * condition.
   *
   * @param parameter input given.
   * @return list of AbstractMap.SimpleImmutableEntry<String,String> values.
   */
  public List<AbstractMap.SimpleImmutableEntry<String, String>> expandVariables(final String parameter) {
    String text = parameter;
    this.replacabel1 = changeReplacableString(text, this.replacabel1);
    this.replacabel2 = changeReplacableString(text, this.replacabel2);
    this.replacabel3 = changeReplacableString(text, this.replacabel3);
    if (text.startsWith("${") && text.endsWith("}") && text.contains(":")) {
      return getListOfMapOfParamsBySplittingMapOfParams(text);
    }
    return getListOfMapOfParamsBySplittingParam(text);
  }


  /**
   * @param param
   * @param envMap
   * @return
   */
  @SuppressWarnings("javadoc")
  public static String resolveNestedProperties(final String value, final Map<String, String> envMap) {


    String resolvedValue = "";
    Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");

    Matcher matcher = pattern.matcher(value);
    resolvedValue = value;
    while (matcher.find()) {
      if (envMap.containsKey(matcher.group(1))) {
        resolvedValue = value.replace(matcher.group(0), envMap.get(matcher.group(1)));
      }
      else {
        throw new InvalidParameterException(
            matcher.group(1) + " not found. Please describe property value pairs before they are referenced");
      }
    }
    return resolvedValue;
  }

  /**
   * Replace property with values except credentials.
   * 
   * @param parameter list of simple immutable strings.
   * @param env configuration data.
   * @return string with value replaced by property.
   */
  public static String replacePropertyWithValueExceptCredentials(
      final List<SimpleImmutableEntry<String, String>> parameter, final Map<String, String> env) {
    if (parameter ==null || parameter.isEmpty())
      return null;
    int i = 0;
    Map<String, String> map = new LinkedHashMap<>();
    StringBuilder replacedString = new StringBuilder();
    while (i < parameter.size() && (parameter.get(i).getKey() == null)) {
      String resolvedValue = VariableExpander.resolveNestedProperties(parameter.get(i).getValue(), env);
      map.put(String.valueOf(i), resolvedValue);
      i++;
    }
    for (int k = 0; k < map.size(); k++) {
      replacedString.append(resolvePropertyWithValuesExceptCredentials(parameter.get(k).getValue(), env));
      if (k != map.size() - 1)
        replacedString.append(",");
    }
    if (replacedString.length() != 0)
      return String.valueOf(replacedString);
    i = 0;
    while (i < parameter.size() && (parameter.get(i).getKey() != null)) {
      String resolvedKey = resolvePropertyWithValuesExceptCredentials(parameter.get(i).getKey(), env);
      String resolvedValue = VariableExpander.resolveNestedProperties(parameter.get(i).getValue(), env);
      map.put(resolvedKey, resolvedValue);
      i++;
    }
    replacedString.append("${");
    for (Map.Entry<String, String> entry : map.entrySet())
      replacedString.append(entry.getKey() + ":" + entry.getValue() + ",");
    if (map.size() > 0)
      replacedString.replace(replacedString.length() - 1, replacedString.length(), "}");
    return String.valueOf(replacedString);
  }

  private static String resolvePropertyWithValuesExceptCredentials(final String parameter,
      final Map<String, String> env) {
    String resolvedKey;
    if (parameter.equalsIgnoreCase("${USERNAME}") || parameter.equalsIgnoreCase("${PASSWORD}"))
      resolvedKey = parameter;
    else
      resolvedKey = VariableExpander.resolveNestedProperties(parameter, env);
    return resolvedKey;

  }
}
