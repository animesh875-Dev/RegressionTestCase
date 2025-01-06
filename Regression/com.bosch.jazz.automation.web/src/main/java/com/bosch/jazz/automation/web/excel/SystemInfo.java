/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.bosch.jazz.automation.web.reporter.Reporter;

/**
 * @author nbo5kor
 */
public class SystemInfo {

  private SystemInfo() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * @param map - map to contains all key, values of system info.
   */
  public static void writeSystemInfo(final Map<String, String> map) {

    String fileName = "sysinfo.properties";
    File f = new File(fileName);
    Properties properties = new Properties();
    Set set = map.keySet();
    for (Object key : set) {
      properties.setProperty(key.toString(), map.get(key));
    }

    try {
      if (f.createNewFile()) {
        properties.store(new FileOutputStream(fileName), "Java properties test");
      }
      else {
        properties.store(new FileOutputStream(fileName, false), "Java properties test");
      }
    }
    catch (IOException e) {
      String s = ExceptionUtils.getStackTrace(e);
      Reporter.logFailure(s);
    }
  }

  /**
   * Read the properties file and load all the properties into map as key value pair.
   * @param fileName To get server details
   * @return list of key of value pairs.
   */
  public static Map<String, String> loadSystemInfo(final String fileName) {
    Properties prop = new Properties();

    Map<String, String> propvals = new LinkedHashMap<>();
    try {
      InputStream filename = new FileInputStream(fileName);
      prop.load(filename);
      Set<String> propertyNames = prop.stringPropertyNames();
      for (String Property : propertyNames) {
        propvals.put(Property, prop.getProperty(Property));
      }
    }
    catch (IOException e) {
      String s = ExceptionUtils.getStackTrace(e);
      Reporter.logFailure(s);
    }
    return propvals;

  }
}