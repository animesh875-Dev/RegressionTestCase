/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web;

import static org.mockito.Mockito.reset;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bosch.jazz.automation.web.common.DriverSetup;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.JazzPageFactory;

/**
 * @author taa6si
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(RemoteWebDriver.class)
@PowerMockIgnore({ "org.apache.http.conn.ssl.*", "javax.net.ssl.*", "javax.crypto.*", "javax.management.*" })
public abstract class AbstractFrameworkUnitTest {

  /**
   *
   */
  public static final String SRC_TEST_RESOURCES = "../com.bosch.jazz.automation.web.resources/src/test/resources/";
  /**
   * 
   */
  protected static DriverSetup driverSetup;
  private static JazzPageFactory jazzPageFactory;
  private static File dlFolder;
  /** The WebDriver used for testing */
  protected static RemoteWebDriver driver;

  /**
   * Configures the selenium driver and opens the browser
   *
   * @throws java.lang.Exception in case of problems
   */
  @BeforeClass
  public static void openBrowser() throws Exception {
    
  //  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NUL");
    driverSetup = new DriverSetup(AbstractFrameworkUnitTest.class);
    dlFolder = java.nio.file.Files.createTempDirectory("automatedBrowserDownloads").toFile();
    RemoteWebDriver realDriver = (RemoteWebDriver) driverSetup.initializeWebDriver(dlFolder);
    driver = spy(realDriver);
    
    //FieldSetter.setField(driver, RemoteWebDriver.class.getDeclaredField("converter"),
   //     new JsonToWebElementConverter(driver));
    if (driverSetup.getBrowserMobProxy() != null) {
      driverSetup.getBrowserMobProxy().newHar();
    }
    WebDriverCustom browser = new WebDriverCustom(driver, driverSetup);
    jazzPageFactory = new JazzPageFactory(browser);
  }

  /**
   * Closes the browser
   *
   * @throws java.lang.Exception in case of problems
   */
  @AfterClass
  public static void closeBrowser() throws Exception {
    FileUtils.deleteQuietly(dlFolder); // clean the downloads folder
    if (driver != null) {
      driver.quit();
    }
  }

  /**
   * After each test reset the mockito spy, so that the tests are independent.
   */
  @After
  public void tearDown() {
    reset(driver);
  }

  /**
   * @return the page factory instance used for creating the page objects that represent certain web pages.
   */
  protected final JazzPageFactory getJazzPageFactory() {
    return AbstractFrameworkUnitTest.jazzPageFactory;
  }


  /**
   * @param pagePath path to the page relative to SRC_TEST_RESOURCES
   * @throws IOException 
   */
  protected void loadPage(final String pagePath) {
    
    String resourceDirectoryPath=null;
    try {
      resourceDirectoryPath=new File(SRC_TEST_RESOURCES).getCanonicalPath();
    }
    catch (IOException e) {
      
     e.printStackTrace();
    }
    Path reportFile =Paths.get(resourceDirectoryPath + "ccm/report_get_vadation_message_on_report_resource.html");
   AbstractFrameworkUnitTest.driver.get(reportFile.toUri().toString());
  }


  /**
   * @param pagePath path to the new page relative to SRC_TEST_RESOURCES
   */
  protected void loadNewPageOnFirstDriverClickElementCall(final String pagePath) {
    loadNewPageOnNthDriverClickElementCall(Stream.of(new AbstractMap.SimpleImmutableEntry<>(1, pagePath))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
  }

  /**
   * @param pageForCallNumber mapping the call number to the path to the new page relative to SRC_TEST_RESOURCES
   */
  protected void loadNewPageOnNthDriverClickElementCall(final Map<Integer, String> pageForCallNumber) {
    loadNewPageOnNthCommandCall(DriverCommand.CLICK_ELEMENT, pageForCallNumber);
  }

  /**
   * @param pageForCallNumber mapping the call number to the path to the new page relative to SRC_TEST_RESOURCES
   */
  protected void loadNewPageOnActionsCall(final Map<Integer, String> pageForCallNumber) {
    loadNewPageOnNthCommandCall(DriverCommand.ACTIONS, pageForCallNumber);
  }

  /**
   * @param command DriverCommand to be mocked
   * @param pageForCallNumber mapping the call number to the path to the new page relative to SRC_TEST_RESOURCES
   */
  protected void loadNewPageOnNthCommandCall(final String command, final Map<Integer, String> pageForCallNumber) {
    Answer<Response> answer = new LoadNewPageOnNthAnswer(SRC_TEST_RESOURCES, pageForCallNumber);
    try {
      PowerMockito.doAnswer(answer)
          .when(AbstractFrameworkUnitTest.driver,
              MemberMatcher.method(RemoteWebDriver.class, "execute", String.class, Map.class))
          .withArguments(ArgumentMatchers.eq(command), ArgumentMatchers.anyMap());
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
