/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;


import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.DateUtil;
import com.bosch.jazz.automation.web.common.DriverSetup;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.JazzPageFactory;

/**
 * @author nbo5kor
 */
public class ServerDetails {

  /** The WebDriver used for testing */
  private static RemoteWebDriver driver;

  private static final String DRIVER_STRING = "driver";
  private static final String RM_SERVER_URL = "RM_SERVER_URL";
  private static final String CCM_SERVER_URL = "CCM_SERVER_URL";
  private static final String QM_SERVER_URL = "QM_SERVER_URL";
  private static final String RM_PROJECT_AREA = "RM_PROJECT_AREA";
  private static final String CCM_FORMAL_PROJECT_AREA = "CCM_FORMAL_PROJECT_AREA";
  private static final String CCM_SCRUM_PROJECT_AREA = "CCM_SCRUM_PROJECT_AREA";
  private static final String RM_CM_PROJECT_AREA = "RM_CM_PROJECT_AREA";
  private static final String QM_PROJECT_AREA = "QM_PROJECT_AREA";
  private static final String QM_CM_PROJECT_AREA = "QM_CM_PROJECT_AREA";
  /**
   * 
   */
  public static final String SERVER_URL = "SERVER_URL";
  private static Map<String, Object> driverMap;

  private ServerDetails() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Initializies the browser.
   *
   * @return drivermap which has both driver and driver set up objects
   * @throws Exception if the browser is not initiated.
   */
  public static Map<String, Object> getDriverSetup() throws Exception {

    driverMap = new HashMap<>();

    DriverSetup driverSetup = new DriverSetup(ServerDetails.class);
    File dlFolder = Files.createTempDirectory("automatedBrowserDownloads").toFile();
    driver = (RemoteWebDriver) driverSetup.initializeWebDriver(dlFolder);
    if (driverSetup.getBrowserMobProxy() != null) {
      driverSetup.getBrowserMobProxy().newHar();
    }
    driverMap.put(DRIVER_STRING, driver);
    driverMap.put("driverSetup", driverSetup);
    return driverMap;
  }


  /**
   * Logs in into alm system and gets information of alm release and ifix version and configuration details of project
   * areas.
   * @param globalEnv name of the global environment.
   * @return Map of Server Details
   * @throws Exception handles Exception.
   */
  public static Map<String, String> getServerDetails(Map<String, String> globalEnv) throws Exception {
    Map<String, String> serverDetailMap = new LinkedHashMap<>();
    try {
      driverMap = getDriverSetup();
      LOGGER.LOG.info("Fetch server environment details from file.");
      WebDriverCustom browser = new WebDriverCustom(driver, (DriverSetup) driverMap.get("driverSetup"));
      JazzPageFactory jazzPageFactory = new JazzPageFactory(browser);
      Capabilities browserVersion = driver.getCapabilities();
      serverDetailMap.put("CREATED_DATE_AND_TIME", DateUtil.getCurrentDateAndTime());
      serverDetailMap.put("BROWSER_NAME", browserVersion.getBrowserName()); // Stores browser name.
      serverDetailMap.put("BROWSER_VERSION", browserVersion.getBrowserVersion()); // Stores browser version.
      serverDetailMap.put("OPERATING_SYSTEM", System.getProperty("os.name")); // Stores operating system information.
      getALMIfixVersion(serverDetailMap, jazzPageFactory, globalEnv);
      getRMServerDetails(serverDetailMap, globalEnv);
    //  getQMServerDetails(serverDetailMap, globalEnv);
      getCCMServerDetails(serverDetailMap, globalEnv);
      LOGGER.LOG.info("Test Environment details are :" + serverDetailMap);
    }
    catch (Exception e1) {
     // driver.close(); // Finally closes the browser.
      LOGGER.LOG.error("Failed to fetch test environment details \n ");
      throw e1;
    }
    finally {
      driver.close(); // Finally closes the browser.
    }

    return serverDetailMap;

  }

  /**
   * @param serverDetailMap store alm relase and ifix version on to the map against property.
   * @param globalEnv contains properties and values of currently running server.
   */
  private static void getCCMServerDetails(Map<String, String> serverDetailMap, Map<String, String> globalEnv) {
    if (globalEnv.get(CCM_SERVER_URL) != null &&
        (globalEnv.get(CCM_SCRUM_PROJECT_AREA) != null || globalEnv.get(CCM_FORMAL_PROJECT_AREA) != null)) {
      driver.get(globalEnv.get(CCM_SERVER_URL));
      serverDetailMap.put(CCM_SERVER_URL, globalEnv.get(CCM_SERVER_URL));
      if (globalEnv.get(CCM_SCRUM_PROJECT_AREA) != null)
        serverDetailMap.put(CCM_SCRUM_PROJECT_AREA, globalEnv.get(CCM_SCRUM_PROJECT_AREA));
      if (globalEnv.get(CCM_FORMAL_PROJECT_AREA) != null)
        serverDetailMap.put(CCM_SCRUM_PROJECT_AREA, globalEnv.get(CCM_FORMAL_PROJECT_AREA));
    }
  }

  /**
   * @param serverDetailMap store alm relase and ifix version on to the map against property.
   * @param browser
   * @param jazzPageFactory
   * @param globalEnv contains properties and values of currently running server.
   */
  private static void getQMServerDetails(Map<String, String> serverDetailMap, Map<String, String> globalEnv) {
    if (globalEnv.get(QM_SERVER_URL) != null &&
        (globalEnv.get(QM_CM_PROJECT_AREA) != null || globalEnv.get(QM_PROJECT_AREA) != null)) {

      if (globalEnv.get(QM_CM_PROJECT_AREA) != null)
        serverDetailMap.put(globalEnv.get(QM_CM_PROJECT_AREA),
            getConfigurationDetails(globalEnv.get(QM_SERVER_URL), globalEnv.get(QM_CM_PROJECT_AREA)).equalsIgnoreCase("true")?"GC":"Non GC"); // Verifies
                                                                                                       // and
                                                                                                       // stores
      // configuration management
      // of this
      // "QM_CM_PROJECT_AREA."
      driver.get(globalEnv.get(QM_SERVER_URL));
      if (globalEnv.get(QM_PROJECT_AREA) != null)
        serverDetailMap.put(globalEnv.get(QM_PROJECT_AREA),
            getConfigurationDetails(globalEnv.get(QM_SERVER_URL), globalEnv.get(QM_PROJECT_AREA)).equalsIgnoreCase("true")?"GC":"Non GC"); // Verifies
                                                                                                    // and
                                                                                                    // stores
      // configuration management of // this "QM_PROJECT_AREA."
    }
  }

  /**
   * @param serverDetailMap store alm relase and ifix version on to the map against property.
   * @param browser to perform action on the browser.
   * @param jazzPageFactory factory with all page model objects.
   * @param globalEnv contains properties and values of currently running server.
   */
  private static void getRMServerDetails(Map<String, String> serverDetailMap, Map<String, String> globalEnv) {
    if (globalEnv.get(RM_SERVER_URL) != null &&
        (globalEnv.get(RM_CM_PROJECT_AREA) != null || globalEnv.get(RM_PROJECT_AREA) != null)) {

      driver.get(globalEnv.get(RM_SERVER_URL));

      if (globalEnv.get(RM_CM_PROJECT_AREA) != null)
        serverDetailMap.put(globalEnv.get(RM_CM_PROJECT_AREA),
            getConfigurationDetails(globalEnv.get(RM_SERVER_URL), globalEnv.get(RM_CM_PROJECT_AREA)).equalsIgnoreCase("true")?"GC":"Non GC"); // Verifies
                                                                                                       // and
                                                                                                       // stores
      // configuration management
      // of this
      // "RM_CM_PROJECT_AREA

      if (globalEnv.get(RM_PROJECT_AREA) != null)
        serverDetailMap.put(globalEnv.get(RM_PROJECT_AREA),
            getConfigurationDetails(globalEnv.get(RM_SERVER_URL), globalEnv.get(RM_PROJECT_AREA)).equalsIgnoreCase("true")?"GC":"Non GC"); // Verifies
                                                                                                    // and
                                                                                                    // stores
      // configuration management of
      // this "RM_PROJECT_AREA."

    }
  }

  /**
   * Alm release and ifix version is fetched by the specific url appending to server url and stored in the map.
   * 
   * @param serverDetailMap store alm relase and ifix version on to the map against property.
   * @param jazzPageFactory factory with all page model objects.
   * @param globalEnv contains properties and values of currently running server.
   */
  private static void getALMIfixVersion(Map<String, String> serverDetailMap, JazzPageFactory jazzPageFactory,
      Map<String, String> globalEnv) {
    if (globalEnv.get(RM_SERVER_URL) != null ||
        (globalEnv.get(CCM_SERVER_URL) != null || globalEnv.get(QM_SERVER_URL) != null)) {
      String serverUrl =
          globalEnv.get(RM_SERVER_URL) != null ? globalEnv.get(RM_SERVER_URL) : globalEnv.get(CCM_SERVER_URL);
      if (serverUrl == null)
        serverUrl =
            globalEnv.get(CCM_SERVER_URL) != null ? globalEnv.get(CCM_SERVER_URL) : globalEnv.get(QM_SERVER_URL);

      jazzPageFactory.getLoginPage().loginWithGivenPassword(globalEnv.get("USERNAME"), globalEnv.get("PASSWORD"),
          serverUrl);
      String prpductInfoUrl =
          "/service/com.ibm.team.repository.service.internal.IProductRegistryRestService/allProductInfo";
      if (serverUrl.endsWith("/"))
        serverUrl = serverUrl.substring(0, serverUrl.length() - 1);

      driver.get(serverUrl + prpductInfoUrl);

      String htmlSource = driver.getPageSource();
      Document html = Jsoup.parse(htmlSource);
      org.jsoup.select.Elements webElements = html.body().getElementsByTag("patches");

      List<Elements> listOfElements = webElements.last().getAllElements().stream()
          .map(x -> x.getElementsByTag("version")).collect(Collectors.toList());
      if (listOfElements.isEmpty())
        throw new WebAutomationException(
            "Couldn't able to find build version information using url:" + serverUrl + prpductInfoUrl);
      String almrelversion = listOfElements.get(0).text();
      serverDetailMap.put("ALM_RELEASE_VERSION", almrelversion); // Stores alm release version.

      listOfElements = webElements.last().getAllElements().stream().map(x -> x.getElementsByTag("buildId"))
          .collect(Collectors.toList());
      if (listOfElements.isEmpty())
        throw new WebAutomationException(
            "Couldn't able to find build ifix information using url:" + serverUrl + prpductInfoUrl);
      String almIFixVersion = listOfElements.get(0).text();
      serverDetailMap.put("ALM_IFIX_VERSION", almIFixVersion); // Stores alm ifix version.

      serverDetailMap.put(SERVER_URL, getServerUrl(serverUrl));
    }
  }

  /**
   * @return server url by removing module extension.
   */
  private static String getServerUrl(final String url) {
    String serverUrl = url;
    if (serverUrl.contains("/rm")) {
      serverUrl = serverUrl.replace("/rm", "");
    }
    else if (serverUrl.contains("/ccm")) {
      serverUrl = serverUrl.replace("/ccm", "");
    }
    else if (serverUrl.contains("/qm")) {
      serverUrl = serverUrl.replace("/qm", "");
    }
    return serverUrl;
  }

  /**
   * @param jazzPageFactory object contains list of page model objects.
   * @param projectarea project area.
   * @return true if configuration management enabled.
   */
  private static String getConfigurationDetails(final String serverUrl, final String projectarea) {

    String allProjectsInfo = "/process/project-areas";
    String configManagementTag = "jp:configuration-management-enabled";
    String tempServerUrl = serverUrl;

    if (serverUrl.endsWith("/"))
      tempServerUrl = serverUrl.substring(0, serverUrl.length() - 1);
    driver.get(tempServerUrl + allProjectsInfo);
    String htmlSource = driver.getPageSource();
    Document html = Jsoup.parse(htmlSource);
    org.jsoup.select.Elements webElements = html.body().getElementsByTag("jp06:project-area");
    Optional<Element> listOfElements =
        webElements.stream().filter(x -> x.attr("jp06:name").equalsIgnoreCase(projectarea)).findFirst();
    if (!listOfElements.isPresent())
      throw new WebAutomationException(
          "Couldn't able to find project area " + projectarea + " using url:" + tempServerUrl + allProjectsInfo);
    listOfElements = listOfElements.get().children().stream()
        .filter(x -> (!x.getElementsByTag(configManagementTag).isEmpty())).findFirst();
    if (!listOfElements.isPresent())
      throw new WebAutomationException("Couldn't able to find configuration management tag " + configManagementTag +
          " using url:" + serverUrl + allProjectsInfo);
    return listOfElements.get().text();
  }

  /**
   * Compares global properties file with system properties file environment.
   * 
   * @param globalEnv Global Environment File Configuration
   * @param sysinfomap Local temporary System Information
   * @return true if Server are different.
   */
  public static boolean compareServerConfigurations(final Map<String, String> globalEnv,
      final Map<String, String> sysinfomap) {
    return sysinfomap.get(SERVER_URL) != null && globalEnv.get(SERVER_URL) != null &&
        (!globalEnv.get(SERVER_URL).equals(sysinfomap.get(SERVER_URL)));

  }
}
