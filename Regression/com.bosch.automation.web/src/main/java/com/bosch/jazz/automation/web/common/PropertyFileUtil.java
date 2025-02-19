package com.bosch.jazz.automation.web.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * This is a convenience class used for opening, loading and storing properties to a property file.
 */
public class PropertyFileUtil {

  /**
   * Private constructor due to util class
   */
  private PropertyFileUtil() {
    // do nothing
  }

  /**
   * Loads the property file with the given name and adds all properties to the given property object.
   *
   * @param prop the properties object where the properties of the file are stored in. Must not be null
   * @param propFileName file name (without any folder path since all files are to be in the folder with the path
   *          src/main/resources/selenium_locators) to read properties from it. Must not be null.<br>
   *          Hint: The file can be in any project/jar with the given path as long as it is in the classpath of the
   *          running Java automation code.
   * @throws IOException in case the property file could not be loaded or in case the given property file contains a
   *           property that was already loaded before by a different property file.
   */
  public static void addProperties(final Properties prop, final String propFileName) throws IOException {
    InputStream propertyFileInputStream =
        PropertyFileUtil.class.getResourceAsStream("/selenium_locators/" + propFileName);
    addProperties(prop, propertyFileInputStream);
  }

  /**
   * Loads the property file (specified by the given input stream) and adds all properties to the given property object.
   *
   * @param prop the properties object where the properties of the file are stored in. Must not be null
   * @param propertyFileInputStream the input stream used for reading the property file, must not be null.
   * @throws IOException in case the property file could not be loaded or in case the given property file contains a
   *           property that was already loaded before by a different property file and therefore this property is
   *           overwritten with a new value.
   */
  public static void addProperties(final Properties prop, final InputStream propertyFileInputStream)
      throws IOException {
    Properties propTemp = new Properties();
    propTemp.load(propertyFileInputStream);
    Set<Entry<Object, Object>> entrySet = propTemp.entrySet();
    for (Entry<Object, Object> entry : entrySet) {
      Object oldValue = prop.setProperty(entry.getKey().toString(), entry.getValue().toString());
      if (oldValue != null) {
        throw new IOException("Duplicate/overwritten property identified, property: " + entry.getKey().toString() +
            ", oldvalue: " + oldValue.toString() + ", new value: " + entry.getValue().toString());
      }
    }
  }
}
