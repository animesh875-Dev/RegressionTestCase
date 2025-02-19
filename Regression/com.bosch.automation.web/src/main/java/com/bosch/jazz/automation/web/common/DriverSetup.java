package com.bosch.jazz.automation.web.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.io.Files;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * Sets up the selenium {@link WebDriver} and starts an instance of it
 */
public class DriverSetup {

  /**
   * Properties file path in jar that holds the default configuration properties.<br>
   * Path must be compatible to use with {@link Class#getResourceAsStream(String)}.
   */
  public static final String WEB_AUTOMATION_CONFIG_PROPERTIES_PATH = "/WebAutomationConfig.properties";

  /**
   * Path of properties file that will overwrite the properties defined by
   * {@link #WEB_AUTOMATION_CONFIG_PROPERTIES_PATH}<br>
   * Path must be compatible to use with {@link Class#getResourceAsStream(String)}
   */
  public static final String WEB_AUTOMATION_CONFIG_OVERRIDE_PROPERTIES_PATH = "/WebAutomationConfigOverride.properties";
  private static final String PROPERTY_BROWSER = "browser";
  private static final String PROPERTY_IMPLICIT_WAIT_TIME = "selenium.wait.time.implicit";
  private static final String PROPERTY_EXPLICIT_WAIT_TIME = "selenium.wait.time.explicit";
  private static final String PROPERTY_PAGE_LOAD_WAIT_TIME = "selenium.wait.time.pageload";
  private static final String PROPERTY_SCRIPT_EXECUTION_WAIT_TIME = "selenium.wait.time.script";
  private static final String PROPERTY_IS_HEADLESS_MODE = "isheadlessmode";
  private static final String PROPERTY_CREATE_HAR_FILE = "createHARFile";
  private static final String DOT_ZIP = ".zip";
  private static final String SELENIUM_DRIVER = ".seleniumdriver";
  private static final String DOT_BIN = ".bin";
  private static final String PROPERTY_WEB_AUTOMATION_BROWSER_TYPE = "WEB_AUTOMATION_BROWSER_TYPE";
  private static final String ENV_VAR_WEB_AUTOMATION_BROWSER_TYPE = PROPERTY_WEB_AUTOMATION_BROWSER_TYPE;

  private static final String CHROME_VERSION_60_0_3112_101 = "60.0.3112.101";
  private static final String CHROME_VERSION_65_0_3325_181 = "65.0.3325.181";
  private static final String FIREFOX_VERSION_52_3_0_ESR = "52.3.0_ESR";
  private static final String FIREFOX_VERSION_59_0_2 = "59.0.2";
  private static final String FIREFOX_VERSION_102_0_8 = "102.0.8";
  private static final String IS_SCREENSHORTS_REQUIRED_FOR_ONLY_FAILED_CASE = "isScreenShortsRequiredForOnlyFailedCase";

  private static List<String> processIDs = new LinkedList<>();

  /**
   * Describes which browsers/browser versions can be used with Selenium.
   */
  public static enum Browser {
                              /**
                               * Take the currently installed Chrome browser on the machine
                               */
                              CHROME(null),
                              /**
                               * Take the currently installed Firefox browser on the machine
                               */
                              FIREFOX(null),
                              /**
                               * Take chrome in the specific version
                               */
                              CHROME_60_0_3112_101(CHROME_VERSION_60_0_3112_101),
                              /**
                               * Take chrome in the specific version
                               */
                              CHROME_65_0_3325_181(CHROME_VERSION_65_0_3325_181),
                              /**
                               * Take firefox in the specific version
                               */
                              FIREFOX_52_3_0_ESR(FIREFOX_VERSION_52_3_0_ESR),
                              /**
                               * Take firefox in the specific version
                               */
                              FIREFOX_59_0_2(FIREFOX_VERSION_59_0_2),
                              /**
                               * Take firefox in the specific version
                               */
                              FIREFOX_102_0_8(FIREFOX_VERSION_102_0_8),
                              /**
                               * Take the currently installed IE browser on the machine.
                               * <p>
                               * ATTENTION: Not Supported <br>
                               * XXX TODO REMOVE THIS literal when Chrome and FF are running fine
                               */
                              IE(null);

    private final String version;

    Browser(final String version) {
      this.version = version;
    }

    /**
     * @return the version of the browser to use, if null it means that any version that is available can be used.
     */
    public String getVersion() {
      return this.version;
    }
  }

  private Properties configProperties;

  private final Class<?> clazz;

  private BrowserMobProxy browserMobProxy;

  private String downloadFolderLocation;


  /**
   * Calls {@link #DriverSetup(Class)} with null as param
   */
  public DriverSetup() {
    this(null);
  }


  /**
   * @param classPathWithOverrideProperties defines the classpath context that can hold a property file
   *          {@value #WEB_AUTOMATION_CONFIG_OVERRIDE_PROPERTIES_PATH} that will override the default properties defined
   *          in {@value #WEB_AUTOMATION_CONFIG_PROPERTIES_PATH}. May be null of no override is required.
   */
  public DriverSetup(final Class<?> classPathWithOverrideProperties) {
    this.clazz = classPathWithOverrideProperties;
    initializeProperties();
    saveProcessIDs();
  }

  /**
   * @return the proxy instance that was potentially created and started during the call to
   *         {@link #initializeWebDriver(File)}. May be null in case the {@link #initializeWebDriver(File)} is not
   *         called before this method or no proxy is required.
   *         <p>
   *         The proxy is only created if during the tests a HAR file shall be created.
   */
  public BrowserMobProxy getBrowserMobProxy() {
    return this.browserMobProxy;
  }


  /**
   * This method initializes WebDriver object to browser specified by WebTestConfig.properties file and returns
   * instantiated driver.<br>
   * HINT: Which browser is being used can be overwritten. For details see method {@link #getBrowserToRun()}
   *
   * @param dlFolder temporary directory for downloads
   * @return driver of browser.(e.g, chrome, i.e, and firefox)
   * @throws Exception in case of problems initializing the driver
   */


  @SuppressWarnings("deprecation")
  public WebDriver initializeWebDriver(final File dlFolder) throws Exception {
    if (this.configProperties == null) {
      throw new WebAutomationException("Config properties must be initialized before this method call!");
    }
    this.downloadFolderLocation = dlFolder.getPath();
    Browser browserType = getBrowserToRun();
    String customBrowserExePath = getExplicitBrowserExePath(browserType);
    if (customBrowserExePath == null) {
      customBrowserExePath = installCustomBrowser(browserType);
    }
    installSeleniumDriverForBrowser(browserType);
    if (customBrowserExePath != null) {
      System.out.println("INFO: Running custom browser: " + customBrowserExePath);
    }

    /*
     * use browsermobproxy in case har file shall be generated during the test run:
     * https://techblog.dotdash.com/selenium-browsermob-integration-c35f4713fb59
     */
    boolean createHARFile = Boolean.parseBoolean(this.configProperties.getProperty(PROPERTY_CREATE_HAR_FILE, "false"));
    Proxy seleniumProxy = null;
    if (createHARFile) {
      if (this.browserMobProxy != null) {
        this.browserMobProxy.stop();
      }
      this.browserMobProxy = startNewBrowserMobProxyServer();
      seleniumProxy = createSeleniumProxy(this.browserMobProxy);
    }

    boolean isHeadlessMode =
        Boolean.parseBoolean(this.configProperties.getProperty(PROPERTY_IS_HEADLESS_MODE, "false"));
    if (!dlFolder.exists()) {
      throw new WebAutomationException("Browser download folder was not created: " + getDownloadFolderLocation());
    }
    WebDriver driver;
    File driverExe = getDriverExeLocation(browserType);
    System.out.println("INFO: Running browser driver: " + driverExe.getAbsolutePath());
    DesiredCapabilities capabilities;
    switch (browserType) {
      case CHROME:
      case CHROME_65_0_3325_181:
      case CHROME_60_0_3112_101:
        System.setProperty("webdriver.chrome.driver", driverExe.getAbsolutePath());
        capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        chromeOptions.merge(capabilities);
        if (customBrowserExePath != null) {
          chromeOptions.setBinary(customBrowserExePath);
        }
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", getDownloadFolderLocation());
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("intl.accept_languages", "en,en_US");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        if (isHeadlessMode) {
          chromeOptions.addArguments("--headless");
          chromeOptions.addArguments("--window-size=1920x1080"); // Full HD
        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        if (createHARFile) {
          capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        }
        driver = new ChromeDriver(chromeOptions);
        break;
      case FIREFOX:
      case FIREFOX_59_0_2:
      case FIREFOX_52_3_0_ESR:
      case FIREFOX_102_0_8:
        System.setProperty("webdriver.gecko.driver", driverExe.getAbsolutePath());
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        if (createHARFile) {
          firefoxOptions.setCapability(CapabilityType.PROXY, seleniumProxy);
        }
        // ProfilesIni profile = new ProfilesIni(); // for finding and using existing profile
        // create new profile for not changing existing default profile
        FirefoxProfile ffprofile = new FirefoxProfile();
        ffprofile.setAcceptUntrustedCertificates(true);
        ffprofile.setAssumeUntrustedCertificateIssuer(true);
        ffprofile.setPreference("browser.startup.homepage", "");
        // Download setting
        ffprofile.setPreference("browser.download.folderList", 2);


        String directories = getDownloadFolderLocation();
        ffprofile.setPreference("browser.helperApps.alwaysAsk.force", false);
        ffprofile.setPreference("browser.download.manager.showWhenStarting", false);
        ffprofile.setPreference("browser.download.dir", directories);
        ffprofile.setPreference("browser.download.downloadDir", directories);
        ffprofile.setPreference("browser.download.defaultFolder", directories);
        ffprofile.setPreference("browser.helperApps.neverAsk.saveToDisk",
            "application/octet-stream, application/zip, application/java-archive, application/zip, text/plain, application/vnd.ms-excel, application/msword, application/pdf, text/csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ffprofile.setPreference("pdfjs.disabled", true);
        ffprofile.setPreference("fission.bfcacheInParent", false);
        ffprofile.setPreference("fission.webContentIsolationStrategy", 0);

        ffprofile.setPreference("intl.accept_languages", "en,en_US"); // set english language as default
        ffprofile.setPreference("dom.disable_beforeunload", true);
        ffprofile.setPreference("network.negotiate-auth.trusted-uris", "bosch.com");
       
         firefoxOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
   
        if (customBrowserExePath != null) 
        {
          FirefoxBinary firefoxBinary = new FirefoxBinary(new File(customBrowserExePath));
          if (isHeadlessMode) {
            firefoxBinary.addCommandLineOptions("--headless");
           
          } 
          firefoxOptions.setBinary(firefoxBinary);
        }
        firefoxOptions.setProfile(ffprofile);
        driver = new FirefoxDriver(firefoxOptions);
        
        break;
      case IE:
      default:
        throw new WebAutomationException("The mentioned browser is not supported: " + browserType);
    }

    if (isHeadlessMode) {
      // in headless mode define a default window size
      Dimension d = new Dimension(1920, 1080);
      driver.manage().window().setSize(d);
    }
    else {
      driver.manage().window().maximize();
    }
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(getConfigurationForImplicitWaitTime());
    if (getConfigurationForPageLoadWaitTime().getSeconds() > 0) {
      driver.manage().timeouts().pageLoadTimeout(getConfigurationForPageLoadWaitTime());
    }
    if (getConfigurationForScriptExecutionWaitTime() > 0) {
      driver.manage().timeouts().setScriptTimeout(getConfigurationForScriptExecutionWaitTime(), TimeUnit.SECONDS);
    }

    return driver;
  }


  /**
   * Installs the selenium driver executable on the local machine so that it can be used with the related browser.
   */
  private void installSeleniumDriverForBrowser(final Browser browserType) {
    String seleniumDriverProperty = getSeleniumDriverProperty(browserType);
    String driverExeLocationOnShare = this.configProperties.getProperty(seleniumDriverProperty);
    if (StringUtils.isEmpty(driverExeLocationOnShare)) {
      throw new IllegalStateException("Property for selenium driver must be defined: " + seleniumDriverProperty);
    }
    File driverExe = new File(driverExeLocationOnShare);
    if (!driverExe.exists()) {
      throw new IllegalStateException("Defined Selenium driver location is not correct, check property '" +
          seleniumDriverProperty + "' and driver location '" + driverExeLocationOnShare + "'" + seleniumDriverProperty);
    }
    File tempDirectory = FileUtils.getTempDirectory();
    try {
      File driverExeOnLocalMachine = new File(tempDirectory, driverExe.getName());
      if (!driverExeOnLocalMachine.exists()) {
        Files.copy(driverExe, driverExeOnLocalMachine);
      }
    }
    catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }


  private String getSeleniumDriverProperty(final Browser browserType) {
    return browserType.toString().toLowerCase() + SELENIUM_DRIVER;
  }


  /**
   * Loads the properties specified in {@value #WEB_AUTOMATION_CONFIG_PROPERTIES_PATH}.
   * <p>
   * In case the parameter clazz is given and in its classpath a properties file can be accessed using the path
   * {@value #WEB_AUTOMATION_CONFIG_OVERRIDE_PROPERTIES_PATH} as parameter in a call to
   * {@link Class#getResourceAsStream(String)} then this property file can be used to override some of the default
   * properties.
   */
  private void initializeProperties() {
    try {
      InputStream configPropsStream = getClass().getResourceAsStream(WEB_AUTOMATION_CONFIG_PROPERTIES_PATH);
      Properties props = new Properties();
      props.load(configPropsStream);
      this.configProperties = props;
      System.out.println("Default selenium driver configuration properties");
      System.out.println("================================================");
      this.configProperties.list(System.out);
      System.out.println("================================================");
      System.out.println("");

      configPropsStream = this.clazz.getResourceAsStream(WEB_AUTOMATION_CONFIG_OVERRIDE_PROPERTIES_PATH);
      if (configPropsStream != null) {
        this.configProperties.load(configPropsStream);
        System.out.println("Final (after potential override) selenium driver configuration properties");
        System.out.println("=========================================================================");
        this.configProperties.list(System.out);
        System.out.println("=========================================================================");
        System.out.println("");
      }
    }
    catch (IOException e) {
      throw new WebAutomationException(e);
    }
  }

  /**
   * Returns the browser to finally use for the automation.
   *
   * @return the browser to finally use for the automation, never null
   * @throws WebAutomationException in case the browser could not be identified properly
   */
  public Browser getBrowserToRun() {

    String browserType = System.getenv().get(ENV_VAR_WEB_AUTOMATION_BROWSER_TYPE);
    if (browserType != null) {
      return Browser.valueOf(browserType);
    }
    browserType = System.getProperty(PROPERTY_WEB_AUTOMATION_BROWSER_TYPE);
    if (browserType != null) {
      return Browser.valueOf(browserType);
    }

    String browserPropertyValue = this.configProperties.getProperty(PROPERTY_BROWSER);
    if (browserPropertyValue == null) {
      throw new WebAutomationException("Browser property not specified properly!");
    }
    return Browser.valueOf(browserPropertyValue.toUpperCase());
  }

  /**
   * @param browserType
   * @return the absolute path to the browser executable or null if the configured path to fetch the browser from for
   *         installation is not available.
   * @throws IOException
   */
  private String installCustomBrowser(final Browser browserType) throws IOException {
    // if no explicit version specified for the browser then try to find the browser installed locally on the machine
    // with any version.
    if (browserType.getVersion() == null) {
      return null;
    }
    String zipFileAbsolutePath = getBrowserZipFilePath(browserType);
    File zipFile = new File(zipFileAbsolutePath);
    try {
      if (!zipFile.exists()) {
        String message = "No custom browser zip file found at: " + zipFileAbsolutePath;
        System.out.println(message);
        return null;
      }
    }
    catch (SecurityException e) {
      String message = "No read access available for: " + zipFileAbsolutePath;
      System.out.println(message);
      return null;
    }

    File tempDirectory = FileUtils.getTempDirectory();
    File browserInstallFolder = new File(tempDirectory, browserType.toString());
    String browserInstallFolderPath = browserInstallFolder.getAbsolutePath();
    String exeRelativePath = getBrowserExePathInZip(browserType);
    File browserExeFile = new File(browserInstallFolder, exeRelativePath);
    final File lockFile = new File(browserInstallFolder.getAbsolutePath() + "lock");
    lockFile.deleteOnExit();
    synchronized (DriverSetup.class) {
      if (!browserInstallFolder.exists()) {
        browserInstallFolder.mkdirs();
      }
      try (FileChannel channel = new RandomAccessFile(lockFile, "rw").getChannel()) {
        final FileLock lock = channel.lock();
        if (browserExeFile.exists()) {
          String message = "Custom browser already installed: " + browserInstallFolderPath;
          System.out.println(message);
          lock.close();
          return browserExeFile.getAbsolutePath();
        }
        String message = "Installing (unzipping) " + zipFileAbsolutePath + " to: " + browserInstallFolderPath;
        System.out.println(message);
        unzipToFolder(browserInstallFolderPath, zipFile);
        message = "Portable browser installed to: " + browserInstallFolderPath;
        System.out.println(message);
        lock.close();
      }
    }
    return browserExeFile.getAbsolutePath();
  }

  private String getBrowserExePathInZip(final Browser browserType) {
    return this.configProperties.getProperty(browserType.toString().toLowerCase() + DOT_BIN);
  }

  private String getBrowserZipFilePath(final Browser browserType) {
    return this.configProperties.getProperty(browserType.toString().toLowerCase() + DOT_ZIP);
  }

  /**
   * @return the absolute path of the browser executable on the local machine, or null in case the executable is not
   *         available in exactly that version on the local machine.
   */
  private String getExplicitBrowserExePath(final Browser browserType) {
    // if no version specified then later try to find the browser that is currently installed on the system for running
    // selenium
    if (browserType.getVersion() == null) {
      return null;
    }
    File browserInstallFolder = new File(FileUtils.getTempDirectory(), browserType.toString());
    String relativePathToExe = getBrowserExePathInZip(browserType);
    File browserExeFile = new File(browserInstallFolder, relativePathToExe);
    if (browserExeFile.exists()) {
      return browserExeFile.getAbsolutePath();
    }
    return null;
  }

  private File getDriverExeLocation(final Browser browser) {
    String seleniumDriverProperty = getSeleniumDriverProperty(browser);
    String driverExeLocationOnShare = this.configProperties.getProperty(seleniumDriverProperty);

    File driverExe = new File(driverExeLocationOnShare);
    File tempDirectory = FileUtils.getTempDirectory();
    return new File(tempDirectory, driverExe.getName());
  }

  private void unzipToFolder(final String outputDir, final File fileZip) {
    try {
      ZipFile zipFile = new ZipFile(fileZip);
      zipFile.extractAll(outputDir);
    }
    catch (ZipException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * @return the default explicit selenium wait time to be used when creating explicit Selenium wait condition
   *         statements
   */
  public Duration getConfigurationForExplicitWaitTime() {
    String value = this.configProperties.getProperty(PROPERTY_EXPLICIT_WAIT_TIME);
    return Duration.ofSeconds(Integer.parseInt(value));
  }

  /**
   * @return the explicit selenium wait time to be provided when using explicit Selenium wait condition statements
   */
  public Duration getConfigurationForPageLoadWaitTime() {
    String value = this.configProperties.getProperty(PROPERTY_PAGE_LOAD_WAIT_TIME);
    return Duration.ofSeconds(Integer.parseInt(value));
  }

  /**
   * @return the explicit selenium wait time to be provided when using explicit Selenium wait condition statements
   */
  public int getConfigurationForScriptExecutionWaitTime() {
    String value = this.configProperties.getProperty(PROPERTY_SCRIPT_EXECUTION_WAIT_TIME);
    return Integer.parseInt(value);
  }

  /**
   * @return the implicit selenium wait time that is automatically configured in the selenium web driver
   */
  public Duration getConfigurationForImplicitWaitTime() {
    String value = this.configProperties.getProperty(PROPERTY_IMPLICIT_WAIT_TIME);
    return Duration.ofSeconds(Integer.parseInt(value));
  }

  /**
   * @return the location of the folder that is configured for all the running browsers to be the download folder.
   */
  public String getDownloadFolderLocation() {
    return this.downloadFolderLocation;
  }


  private Proxy createSeleniumProxy(final BrowserMobProxy proxyServer) {
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
    try {
      String hostIp = InetAddress.getLocalHost().getHostAddress();
      seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
      seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
    }
    catch (UnknownHostException e) {
      throw new WebAutomationException(e);
    }
    return seleniumProxy;
  }

  private BrowserMobProxy startNewBrowserMobProxyServer() {
    BrowserMobProxy proxy = new BrowserMobProxyServer();
    proxy.setTrustAllServers(true);
    proxy.start();
    System.out.println("Starting local proxy on port: " + proxy.getPort());
    return proxy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void finalize() throws Throwable {
    if ((this.browserMobProxy != null) && this.browserMobProxy.isStarted()) {
      System.out.println("Stopping proxy on port: " + this.browserMobProxy.getPort());
      this.browserMobProxy.stop();
    }
    super.finalize();
  }


  /**
   * @return the isScreenshortsRequiredForOnlyFailedCase
   */
  public boolean getIsScreenshortsRequiredForOnlyFailedCase() {
    return Boolean
        .parseBoolean(this.configProperties.getProperty(IS_SCREENSHORTS_REQUIRED_FOR_ONLY_FAILED_CASE, "false"));

  }

  /**
   * terminates all tasks related to Firefox and removes the installed Browser
   */
  public void closeBrowserTasks() {
    String browserString = getBrowserToRun().toString();
    String browserName =
        browserString.indexOf('_') == -1 ? browserString : browserString.substring(0, browserString.indexOf('_'));
    String line;
    Process p;
    try {
      p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\tasklist.exe /fo csv /nh");
      java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
      while ((line = input.readLine()) != null) {
        String regexPattern = "\".*?" + browserName + ".*?\",\"(\\d+)\"";
        Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
          if (processIDs.contains(matcher.group(1))) {
            continue;
          }
          Runtime.getRuntime().exec("taskkill /F /PID " + matcher.group(1));
        }
      }
    }
    catch (IOException e) {
      System.out.println("Could not terminate all Browser processes");
    }
  }

  /**
   * Saves the current ProcessIDs, only newly created processes should be terminated
   */
  private void saveProcessIDs() {
    if (!processIDs.isEmpty()) {
      return;
    }
    String browserString = getBrowserToRun().toString();
    String browserName =
        browserString.indexOf('_') == -1 ? browserString : browserString.substring(0, browserString.indexOf('_'));
    String line;
    Process p;
    try {
      p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\tasklist.exe /fo csv /nh");
      java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
      while ((line = input.readLine()) != null) {
        String regexPattern = "\".*?" + browserName + ".*?\",\"(\\d+)\"";
        Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
          processIDs.add(matcher.group(1));
        }
      }
    }
    catch (IOException e) {
      System.out.println("Could not save all Browser processes IDs");
    }
  }
}