package com.bosch.jazz.automation.web.pagemodel;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set; 

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.WebAutomationException;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.jazz.automation.web.pagemodel.dng.RMConstants;
import com.bosch.jazz.utils.misc.encryption.EncryptionUtility;
import com.bosch.jazz.utils.rest.JazzHttpUtils;
import com.bosch.psec.web.test.criteria.Criteria;
import com.bosch.psec.web.test.element.Button;
import com.bosch.psec.web.test.element.text.TextField;

import finder.button.JazzButtonFinder;
import finder.text.textField.JazzLoginTextFieldFinder;

/**
 * This class is used to forcefully login into a jazz repository. By default NOT [jazz repository url]/auth/authrequired
 * is used but instead [jazz installation base url]/jts/auth/authrequired.
 */
public class JazzLoginPage extends AbstractWebPage {

  /**
   * @param driverCustom the non-null custom web driver.
   */
  public JazzLoginPage(final WebDriverCustom driverCustom) {
    super(driverCustom);
    this.engine.addFinders(new JazzButtonFinder(), new JazzLoginTextFieldFinder());
  }


  /**
   * @param repositoryUrl the jazz application repository url without trailing slash
   */
  private String getLoginPageUrl(final String repositoryUrl) {
    String repoUrlWithoutApp = repositoryUrl.substring(0, repositoryUrl.lastIndexOf('/'));
    // In case of SSO: For JRS application the login must be done using jts and cannot be done directly via JRS
    // application.
    if (repositoryUrl.toLowerCase().endsWith("/rs")) {
      return repoUrlWithoutApp.concat("/jts/auth/authrequired");
    }
    return repositoryUrl.concat("/auth/authrequired");
  }


  /**
   * Logs in to the given repository. The given encrypted password is decrypted using the given key file.<br>
   * It is assumed the login window is visible. If not an exception will be throws.
   *
   * @param username Provides username to the method, must not be null
   * @param encryptedPassword Provides password to the method, must not be null
   * @param keyFilePath path to key file to use for decrypting the password, must not be null
   * @param repositoryURL the url of the repo to login to, must not be null
   */
  public void login(final String username, final String encryptedPassword, final String keyFilePath,
      final String repositoryURL) {
    char[] decrypt = decryptPassword(encryptedPassword, keyFilePath);
    loginInternal(username, new String(decrypt), repositoryURL);
  }

  /**
   * Logs into the given reporitory using the mentioned user credentials.<br>
   * It is assumed the login window is visible. If not an exception will be throws.
   *
   * @param username the user name to use when logging in, must not be null
   * @param decryptedPassword the user password in clear text, must not be null
   * @param repositoryURL the repository to log in to, must not be null
   */
  public void loginWithGivenPassword(final String username, final String decryptedPassword,
      final String repositoryURL) {
    loginInternal(username, decryptedPassword, repositoryURL);
  }

  /**
   * Check if Login page is not visible then skip login
   *
   * @param username
   * @param decryptedPassword
   * @param repositoryURL
   */
  public void SSOCheckloginWithGivenPassword(final String username, final String decryptedPassword,
      final String repositoryURL) {
    loginInternalSSOEnabled(username, decryptedPassword, repositoryURL);
  }

  /**
   * @param username
   * @param decryptedPassword
   * @param repositoryURL
   */
  private void loginInternalSSOEnabled(final String username, final String decryptedPassword,
      final String repositoryURL) {
    boolean isExceptionOccured = true;
    String repoUrl = JazzHttpUtils.removeTrailingSlash(repositoryURL);
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    try {
      this.engine.findFirstElement(Criteria.isTextField().hasLabel("User ID:"));
      isExceptionOccured = false;
    }
    catch (NoSuchElementException e) {
      verifyLoginIsFinished();
      LOGGER.LOG.info("SSO Login Enabled" + repoUrl);
      this.driverCustom.waitForSecs(Duration.ofSeconds(40));
      String currUrl = this.driverCustom.getCurrUrl();
      currUrl = JazzHttpUtils.removeTrailingSlash(currUrl);
      if (!currUrl.equals(repoUrl)) {
        this.driverCustom.openURL(repoUrl);
      }
      this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    }
    if (!isExceptionOccured) {
      loginInternal(username, decryptedPassword, repositoryURL);
    }
  }


  /**
   * From the existing login page, logs into again to system the mentioned user credentials.
   *
   * @param username the user name to use when logging in, must not be null
   * @param decryptedPassword the user password in clear text, must not be null
   */
  public void loginWithGivenPassword(final String username, final String decryptedPassword) {
    loginInternal(username, decryptedPassword);
  }

  private char[] decryptPassword(final String encryptedPassword, final String keyFilePath) {
    char[] encryptedPasswordCharArray = encryptedPassword.toCharArray();
    File key = new File(keyFilePath);
    char[] decrypt;
    try {
      decrypt = EncryptionUtility.decryptReadingKeyFromFile(encryptedPasswordCharArray, key);
    }
    catch (GeneralSecurityException | IOException e) {
      throw new WebAutomationException(e);
    }
    return decrypt;
  }

  private void loginInternal(final String username, final String decryptedPassword, final String repositoryURL) {
    String repoUrl = JazzHttpUtils.removeTrailingSlash(repositoryURL);
    openLoginPage(repoUrl);
    waitForPageLoaded();
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    LOGGER.LOG.info("Open Login page " + repoUrl);
    TextField txtUsername = this.engine.findFirstElement(Criteria.isTextField().hasLabel("User ID:"));
    txtUsername.setText(username);
    LOGGER.LOG.info(username + "- User name entered Successfully.");
    TextField txtPassword = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Password:"));
    txtPassword.setText(decryptedPassword);
    LOGGER.LOG.info("*****" + "- Password entered Successfully.");
    Button btnLogin = this.engine.findElement(Criteria.isButton().withText("Log In")).getFirstElement();
    btnLogin.click();
    LOGGER.LOG.info("Log-In button clicked Successfully.");
    verifyLoginIsFinished();
    this.driverCustom.waitForSecs(Duration.ofSeconds(40));
    String currUrl = this.driverCustom.getCurrUrl();
    currUrl = JazzHttpUtils.removeTrailingSlash(currUrl);
    if (!currUrl.equals(repoUrl)) {
      this.driverCustom.openURL(repoUrl);
    }
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
  }

  private void loginInternal(final String username, final String decryptedPassword) {
    waitForPageLoaded();
    TextField txtUserId = this.engine.findFirstElement(Criteria.isTextField().hasLabel("User ID:"));
    txtUserId.setText(username);
    LOGGER.LOG.info(username + "- User name entered Successfully.");
    TextField textPassword = this.engine.findFirstElement(Criteria.isTextField().hasLabel("Password:"));
    textPassword.setText(decryptedPassword);
    LOGGER.LOG.info("*****" + "- Password entered Successfully.");
    Button buttonLogin = this.engine.findElement(Criteria.isButton().withText("Log In")).getFirstElement();
    buttonLogin.click();
    LOGGER.LOG.info("Log-In button clicked Successfully.");
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
    String currUrl = this.driverCustom.getCurrUrl();
    currUrl = StringUtils.removeEnd(currUrl, "/auth/");
    this.driverCustom.openURL(currUrl);
    verifyLoginIsFinished();
  }


  /**
   * @param repoUrl the jazz application repository URL without trailing slash
   */
  private String openLoginPage(final String repoUrl) {
    this.driverCustom.waitForSecs(Duration.ofSeconds(8));
    this.driverCustom.getWebDriver().get(getLoginPageUrl(repoUrl));
    this.driverCustom.waitForPageLoaded();
    this.driverCustom.waitForSecs(Duration.ofSeconds(8));
    String currentPageTitle = this.driverCustom.getCurrentPageTitle();
    String pageTitleBefore = currentPageTitle;
    if (!pageTitleBefore.startsWith(this.driverCustom.getSeleniumLocatorProperty("Login.PageTitle.Prefix"))) {
      String failMsg = "Login page is not visible, instead it is a page with title: " + pageTitleBefore + " and URL " +
          this.driverCustom.getWebDriver().getCurrentUrl();
      throw new WebAutomationException(failMsg);
    }
    return pageTitleBefore;
  }

  private void verifyLoginIsFinished() {
    this.driverCustom.waitForSecs(Duration.ofSeconds(4));
    WebDriverWait wait = this.driverCustom.createWaitWithTimeoutMessage("Login was not successfull, wrong password? ");
    // the default timeout is way to big for the login, 3 seconds should be enough to make the login page go away in
    // case of successful log in
    wait.pollingEvery(Duration.ofMillis(100));
    wait.withTimeout(Duration.ofSeconds(9));
    if(this.driverCustom.isElementVisible("//body[@style='overflow: visible;']", Duration.ofSeconds(30))) {
      this.driverCustom.click("//div[text()='x']");
    }
    wait.until(createLoginWasSuccessfullCondition());
    this.driverCustom.waitForPageLoaded();
  }

  private ExpectedCondition<Boolean> createLoginWasSuccessfullCondition() {

    return driver -> {
      boolean returnVal = true;
      String currentUrl = driver.getCurrentUrl();
      if (currentUrl.contains("auth/authfailed")) {
        returnVal = false;
        return returnVal;
      }
      // if the login was succesfull and an automatic redirect to the initially requested resource was done in the
      // browser then there is no "auth" any more in the URL. If the login would have failed the URL would contain the
      // "auth/authfailed" which is already handled above.
      return returnVal;
    };
  }


  /**
   * This method is not implemented for the presence of the element in the JazzLoginPage.
   */
  @Override
  public void waitForPageLoaded() {
    this.driverCustom.getPresenceOfWebElement(RMConstants.MANAGECOMPPROPERTIES_MULTIPLEVALUE_CHECKBOX_XPATH, "User ID");
    String currentWindow = this.driverCustom.getWebDriver().getWindowHandle();
    Set<String> windows = this.driverCustom.getWebDriver().getWindowHandles();

    if (windows.size() > 1) {
      // Now iterate using Iterator
      Iterator<String> window = windows.iterator();
      while (window.hasNext()) {
        String childWindow = window.next();
        if (!currentWindow.equals(childWindow)) {
          // Switch to the SPNEGO window to take screenshot and write to log file.
          this.driverCustom.getWebDriver().switchTo().window(childWindow);
          String childWindowTitle = this.driverCustom.getWebDriver().switchTo().window(childWindow).getTitle();
          LOGGER.LOG.info(" New window : " + childWindowTitle);
          // Handle SPNEGO Authentication Dialog
          if (childWindowTitle.contains("SPNEGO authentication is not supported.")) {
            LOGGER.LOG.info("'" + childWindowTitle + "' --> new window found automatic test script will not compleate");
            throw new WebAutomationException(
                " Unexpected window found with message 'SPNEGO authentication is not supported.' ");
          }
        }
      }
    }
  }
}
