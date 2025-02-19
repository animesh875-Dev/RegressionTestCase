package com.bosch.jazz.automation.web.common;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.constants.ByType;
import com.bosch.jazz.automation.web.common.constants.SelectTypeEnum;


/**
 * This class represents a low level interface to the browser using the Selenium framework. It is used to find elements
 * in the current browser page, perform actions in the browser, query the browser, ... .
 * <p>
 * The elements in the page are located via textual expressions that are stored in property files that end with
 * ".properties" and are stored in the folder src/main/resources/selenium_locators. In order to locate an element
 * typically the name of the property holding the location information must be provided to the methods of this class.
 *
 * @see #addSeleniumLocatorProperties(InputStream)
 * @see #getSeleniumLocatorProperties()
 * @see #getSeleniumLocatorProperty(String)
 * @see #getSeleniumLocatorProperty(String, String)
 * @see #getWebElement(String)
 * @see #getWebElement(String, String)
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WebDriverCustom {

  /**
   * The text in the seleniumLocatorProperties that shall be replaced by some dynamic value
   */
  public static final String DYNAMIC_VALUE_PLACEHOLDER = "DYNAMIC_VAlUE";

  private Properties seleniumLocatorProperties = null;

  /**
   * Holds some default configuration values for the used selenium web driver like implicit and explicit selenium wait
   * time
   */
  private final DriverSetup driverSetup;
  private final WebDriver driver;

  /**
   * @param driver the driver to use for interacting with the browser via Selenium.
   * @param driverSetup contains some selenium configuration seleniumLocatorProperties to be reused, must not be null
   */
  public WebDriverCustom(final WebDriver driver, final DriverSetup driverSetup) {
    this.driver = driver;
    this.driverSetup = driverSetup;
    this.seleniumLocatorProperties = new Properties();
    loadSeleniumLocatorProperties();
  }

  /**
   * @return Returns the selenium web driver that is used in this class to interact with the browser
   */
  public WebDriver getWebDriver() {
    return this.driver;

  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * clicks on the element.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   */
  public void click(final String seleniumLocatorProperty) {

    getWebElement(seleniumLocatorProperty).click();
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * clicks on the element.<br>
   * It is assumed that the selenium locator property value contains at least once the string
   * {@value #DYNAMIC_VALUE_PLACEHOLDER} that shall be replaced by the given {@code dynamicValue}. The result of the
   * replacement is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String)
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValue the dynamic value to inject into the selenium locator property text, if null no replacement is
   *          done
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if a dynamicValue is given but the property has no dynamic part or vice versa
   */
  public void click(final String seleniumLocatorProperty, final String dynamicValue) {

    getFirstVisibleWebElement(seleniumLocatorProperty, dynamicValue).click();
  }


  /**
   * Clicks on the first found link having the provided text. Then waits for the resulting page to be loaded (by calling
   * {@link #waitForPageLoaded()}.
   *
   * @param linkText the text of the link to click on, must not be null
   * @param isPartialLinkText true if the given text is just a part of the link text, false if it the same
   * @throws WebAutomationException in case the element could not be found
   */
  public void clickOnLink(final String linkText, final boolean isPartialLinkText) {
    By locator;
    if (isPartialLinkText) {
      locator = By.partialLinkText(linkText);
    }
    else {
      locator = By.linkText(linkText);
    }
    /**
     * first wait until an element is found having the text. I am doing it like this since when using firefox driver
     * just searching for elements having the text returned a lot elements that were not clickable and did not even have
     * the required text. Then clicking on the first found element resulted in an exception.
     * <p>
     * Using Chrome and just using the straightforward solution worked. But since we have to stay compatible with at
     * least ff and chrome this implementation here is required.
     */
    final WebElement[] elementHavingTheText = new WebElement[1];
    Boolean isFound = new WebDriverWait(this.driver, this.driverSetup.getConfigurationForExplicitWaitTime())
        .until(new ExpectedCondition<Boolean>() {

          @Override
          public Boolean apply(final WebDriver webDriver) {
            List<WebElement> elements = webDriver.findElements(locator);
            for (WebElement webElement : elements) {
              if (isPartialLinkText) {
                if (webElement.getText().contains(linkText)) {
                  elementHavingTheText[0] = webElement;
                  return true;
                }
              }
              else {
                if (webElement.getText().equals(linkText)) {
                  elementHavingTheText[0] = webElement;
                  return true;
                }
              }
            }
            return false;
          }
        });
    if (isFound) {
      elementHavingTheText[0].click();
    }
    else {
      throw new WebAutomationException(
          "Link having text '" + linkText + "' not found and therefore cannot be clicked!");
    }
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * clicks on the element.<br>
   * It is assumed that the selenium locator property value contains some dynamic parts that are identified by the text
   * {@value #DYNAMIC_VALUE_PLACEHOLDER}1, {@value #DYNAMIC_VALUE_PLACEHOLDER}2,{@value #DYNAMIC_VALUE_PLACEHOLDER}3,
   * ... that shall be replaced by the given {@code dynamicValues} in the order of the array. The result of the
   * replacemenet is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String[])
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValues the dynamic values to inject into the selenium locator property text, if null no replacement
   *          is done
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if there is mismatch between the amount of given dynamic values and the amount of
   *           dynamic parts in the property value
   */
  public void click(final String seleniumLocatorProperty, final String[] dynamicValues) {
    getWebElement(seleniumLocatorProperty, dynamicValues).click();
  }


  /**
   * Clicks twice on the element that can be located using the value of the provided selenium locator property
   *
   * @param seleniumLocatorProperty the property containing the locator information, must not be null and be a valid
   *          property
   * @param dynamicValue replacing existing value with new value.
   */
  public void clickTwice(final String seleniumLocatorProperty, final String dynamicValue) {
    WebElement ele = getWebElement(seleniumLocatorProperty, dynamicValue);
    Actions act = new Actions(this.driver);
    try {
      act.moveToElement(ele).doubleClick().build().perform();
    }
    catch(Exception e) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
      act.moveToElement(ele).doubleClick().build().perform();
    }    
    
  }

  /**
   * Clicks twice on the element that can be located using the value of the provided selenium locator property
   *
   * @param seleniumLocatorProperty the property containing the locator information, must not be null and be a valid
   *          property
   */
  public void clickTwice(final String seleniumLocatorProperty) {
    WebElement ele = getWebElement(seleniumLocatorProperty);
    Actions act = new Actions(this.driver);
    act.moveToElement(ele).doubleClick().build().perform();
  }

  /**
   * Types text into the element specified by the given locator.
   * <p>
   * Hint: For typing e.g. F4 use {@link #pressKey(String, Keys)}
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param textToType the text to type into the element, must not be null
   * @throws WebAutomationException in case of problems finding the web element defined by the selenium locator
   */
  public void typeText(final String seleniumLocatorProperty, final String textToType) {
    WebElement ele = getWebElement(seleniumLocatorProperty);
    ele.click();
    ele.clear();
    ele.sendKeys(textToType);
  }

  /**
   * Types text into the element specified by the given locator information.
   * <p>
   * Hint: For typing e.g. F4 use {@link #pressKey(String, Keys)}
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param textToType the text to type into the element, must not be null
   * @param dynamicLocatorPropertyValue is used to dynamically change the property value for the selenium locator, see
   *          {@link #getSeleniumLocatorProperty(String, String)}
   * @throws WebAutomationException in case of problems finding the web element defined by the selenium locator
   */
  public void typeText(final String seleniumLocatorProperty, final String textToType,
      final String dynamicLocatorPropertyValue) {
    WebElement ele = getWebElement(seleniumLocatorProperty, dynamicLocatorPropertyValue);
    ele.clear();
    ele.sendKeys(textToType);
  }

  /**
   * This method is for type text into the field by char by char
   *
   * @param seleniumLocatorProperty is webelement Locator
   * @param textToType is entering string by char by char
   * @param dynamicLocatorPropertyValue is passing dynamic element
   * @param timeInSecs is time
   */
  public void typeCharByChar(final String seleniumLocatorProperty, final String textToType,
      final String dynamicLocatorPropertyValue, final Duration timeInSecs) {
    WebElement element = getWebElement(seleniumLocatorProperty, dynamicLocatorPropertyValue);
    String val = element.getText();
    for (int i = 0; i < val.length(); i++) {
      char c = val.charAt(i);
      String s = new StringBuilder().append(c).toString();
      waitForSecs(timeInSecs);
      element.sendKeys(s);
    }
  }

  /**
   * This method is for type text into the field by char by char
   *
   * @param seleniumLocatorProperty is webelement Locator
   * @param textToType is entering string by char by char
   * @param timeInSecs is time
   */
  public void typeCharByChar(final String seleniumLocatorProperty, final String textToType, final Duration timeInSecs) {
    WebElement element = getWebElement(seleniumLocatorProperty);
    String val = textToType;
    for (int i = 0; i < val.length(); i++) {
      char c = val.charAt(i);
      String s = new StringBuilder().append(c).toString();
      waitForSecs(timeInSecs);
      element.sendKeys(s);
    }
  }

  /**
   * Types (presses) keys that are no text in the specified web element. For typing text use
   * {@link #typeText(String, String)}.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property webelement for, must not be null
   * @param keyToType the key to press, must not be null
   * @throws WebAutomationException in case of problems finding the web element defined by the selenium locator
   */
  public void pressKey(final String seleniumLocatorProperty, final Keys keyToType) {
    WebElement ele = getWebElement(seleniumLocatorProperty);
    ele.clear();
    ele.sendKeys(keyToType);
  }

  /**
   * This method iterates over the webelement using keys , click the element when the given text matches. Here Break
   * point is given 200, 200 Elements are accesed to check the actual text wiht the web element text. if the element
   * doesn't match with 200 elements then WebAutomationException is thrown. Caution! Doesn't work on tables under
   * current Firefox due to the following bug: https://github.com/mozilla/geckodriver/issues/1228
   *
   * @param element The WebElement.
   * @param textValue Actual text.
   * @param key keys controls like keys.Down, Keys.Tab
   */
  public void clickOnElementByExactTextMatchUsingKeysControls(final WebElement element, final String textValue,
      final Keys key) {
    // TODO Auto-generated method stub
    int breakPoint = 0;
    WebElement ielement = element;
    while (true) {
      if (ielement == null) {
        throw new WebAutomationException("Element is null");
      }
      if (isTextExactMatchWithTheElementText(ielement, textValue, 1)) {
        getClickableWebElement(ielement).click();
        break;
      }
      ielement.sendKeys(key);
      ielement = getWebElement(this.driver.switchTo().activeElement());

      if (breakPoint == 200) {
        throw new WebAutomationException(
            "Element with the given text " + textValue + " not found by iterating over 200 elements.");
      }
      breakPoint++;
    }

  }

  /**
   * @param textValue Actual text.
   */
  public void clickOnTableElementByExactTextMatchUsingMouse(final String textValue) {
    WebElement element = getWebElement(getWebDriver().switchTo().activeElement());

    List<WebElement> childTrElements = element.findElements(By.xpath("../tr"));
    Optional<WebElement> matchingOptional = childTrElements.parallelStream()
        .filter(childElement -> isTextExactMatchWithTheElementText(childElement, textValue, 1)).findFirst();
    if (matchingOptional.isPresent()) {
      WebElement matchingElement = matchingOptional.get();
      // Now, because of Firefox bug https://github.com/mozilla/geckodriver/issues/1228
      // we can not just do matchingElement.click(), but use the following workaround:
      new Actions(getWebDriver()).moveToElement(matchingElement).click().perform();
      return;
    }
    throw new WebAutomationException("Element with the given text '" + textValue + "' not found by iterating over " +
        childTrElements.size() + " elements.");

  }


  /**
   * This method iterates over the numbber of webelemets given webelement using keys control's.
   *
   * @param element The WebElement.
   * @param numberOfElements number of elements
   * @param key keys controls like keys.Down, Keys.Tab
   */
  public void iterateOverElementsUsingKeysControls(final WebElement element, final int numberOfElements,
      final Keys key) {
    int breakPoint = numberOfElements;
    WebElement ielement = element;
    while (breakPoint > 0) {
      if (ielement == null) {
        throw new WebAutomationException("Element is null");
      }
      ielement.sendKeys(key);
      ielement = getWebElement(this.driver.switchTo().activeElement());
      breakPoint--;
    }

  }

  /**
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param selValue value of any type to be selected from dropdown.
   * @param selType Selection type like Select by Index, Select by value and Select by visible text.
   */
  public void select(final String seleniumLocatorProperty, final Object selValue, final SelectTypeEnum selType) {
    FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.SELECT_NOT_PRESENT);
    ExpectedCondition<Boolean> pred = new ExpectedCondition<Boolean>() {

      @SuppressWarnings("hiding")
      @Override
      public Boolean apply(final WebDriver driver) {
        Select sel = new Select(getWebElement(seleniumLocatorProperty));
        if (selType == SelectTypeEnum.SELECT_BY_INDEX) {
          sel.selectByIndex((Integer) selValue);
        }
        else if (selType == SelectTypeEnum.SELECT_BY_VALUE) {
          sel.selectByValue(selValue.toString());
        }
        else {
          sel.selectByVisibleText(selValue.toString().trim());
        }
        return true;
      }
    };
    wait.until(pred);
  }

  /**
   * @param ele WebElement of the DropDown
   * @param selValue value of any type to be selected from dropdown.
   * @param selType Selection type like Select by Index, Select by value and Select by visible text.
   */
  public void select(final WebElement ele, final Object selValue, final SelectTypeEnum selType) {
    FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.SELECT_NOT_PRESENT);
    ExpectedCondition<Boolean> pred = new ExpectedCondition<Boolean>() {

      @SuppressWarnings("hiding")
      @Override
      public Boolean apply(final WebDriver driver) {
        Select sel = new Select(ele);
        if (selType == SelectTypeEnum.SELECT_BY_INDEX) {
          sel.selectByIndex((Integer) selValue);
        }
        else if (selType == SelectTypeEnum.SELECT_BY_VALUE) {
          sel.selectByValue(selValue.toString());
        }
        else {
          sel.selectByVisibleText(selValue.toString().trim());
        }
        return true;
      }
    };
    wait.until(pred);
  }


  /**
   * Loads the default selenium property files that are defined in this project.
   */
  private void loadSeleniumLocatorProperties() {

    // TODO XXX Problem the properties are in a different project

    try {
      PropertyFileUtil.addProperties(this.seleniumLocatorProperties, "ChangeAndConfigurationManagement.properties");
      PropertyFileUtil.addProperties(this.seleniumLocatorProperties, "Login.properties");
      // PropertyFileUtil.addProperties(this.seleniumLocatorProperties, "JRS.properties");
      PropertyFileUtil.addProperties(this.seleniumLocatorProperties, "RequirementsManagement.properties");
      PropertyFileUtil.addProperties(this.seleniumLocatorProperties, "JazzSettings.properties");
      PropertyFileUtil.addProperties(this.seleniumLocatorProperties, "QualityManagement.properties");
    }
    catch (Exception e) {
      //throw new WebAutomationException("Could not load selenium locator property files", e);
    }
  }

  /**
   * Adds the seleniumLocatorProperties from the given property file to the existing list of seleniumLocatorProperties.
   *
   * @param propertyFileInputStream input stream to the property file that adds new selenium locator
   *          seleniumLocatorProperties so that they can be used in this object.
   */
  public void addSeleniumLocatorProperties(final InputStream propertyFileInputStream) {
    try {
      PropertyFileUtil.addProperties(this.seleniumLocatorProperties, propertyFileInputStream);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * Creates and returns a locator from the given property.
   * <p>
   * The type of locator is specified in the property name after the underscore '_'. The value of the property is the
   * locator parameter. Example: Login.Submit.Button_xpath = //button[@type = 'submit']
   *
   * @param seleniumLocatorProperty the name of the property from which to derive the locator, must not be null and the
   *          property must exist
   * @return the locator instance, never null
   * @throws WebAutomationException in case the locator could not be derived from the given property
   */
  public By createLocatorFromProperty(final String seleniumLocatorProperty) {
    return createLocatorFromProperty(seleniumLocatorProperty, (String) null);
  }

  private By createLocatorFromGivenPropertyValue(final String seleniumLocatorProperty, final String propertyValue) {
    String locatorType;
    if (seleniumLocatorProperty.startsWith("/") || seleniumLocatorProperty.startsWith("(")) {
      locatorType = "xpath";
    }
    else if (StringUtils.countMatches(seleniumLocatorProperty, "_") != 1) {
      throw new WebAutomationException(
          "Each property describing a selenium locator must contain exactly one underscore '_' in the property name after which the type of locator is specified");
    }
    else {
      int underscoreIndex = seleniumLocatorProperty.indexOf("_");
      locatorType = seleniumLocatorProperty.substring(underscoreIndex + 1, seleniumLocatorProperty.length());
    }
    return createLocator(propertyValue, locatorType);
  }

  /**
   * Creates and returns a locator with the specified parameter and of the specified type.
   *
   * @param locatorParameterValue the parameter that the locator shall use for finding the element
   * @param locatorType the type of locator to create, must not be null, must be one that can be matched to
   *          {@link ByType} enumeration.
   * @return the locator instance, never null
   * @throws WebAutomationException in case the given locator type is not yet supported
   */
  public By createLocator(final String locatorParameterValue, final String locatorType) {
    ByType byType = ByType.from(locatorType);
    return createLocator(locatorParameterValue, byType);
  }

  /**
   * Creates and returns a locator with the specified parameter and of the specified type.
   *
   * @param locatorParameterValue the parameter that the locator shall use for finding the element
   * @param locatorType the type of locator to create, must not be null
   * @return the locator instance, never null
   * @throws WebAutomationException in case the given locator type is not yet supported
   */
  public By createLocator(final String locatorParameterValue, final ByType locatorType) {
    switch (locatorType) {
      case ID:
        return By.id(locatorParameterValue);
      case XPATH:
        return By.xpath(locatorParameterValue);
      case LINK_TEXT:
        return By.linkText(locatorParameterValue);
      case CLASS_NAME:
        return By.className(locatorParameterValue);
      case CSS_SELECTOR:
        return By.cssSelector(locatorParameterValue);
      case PARTIAL_LINK_TEXT:
        return By.partialLinkText(locatorParameterValue);
      case TAG_NAME:
        return By.tagName(locatorParameterValue);
      case NAME:
        return By.name(locatorParameterValue);
      default:
        throw new WebAutomationException(
            "The required locator type is not (yet) supported, please adapt implementation to support type: " +
                locatorType.toString());
    }
  }

  /**
   * @return Returns current URL of the page.
   */
  public String getCurrUrl() {
    return this.driver.getCurrentUrl();
  }

  /**
   * @return Returns current page Title.
   */
  public String getCurrentPageTitle() {
    return this.driver.getTitle();
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * returns the text of the found element.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return the text of the element, may be null in some cases
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   */
  public String getText(final String seleniumLocatorProperty) {
    return getWebElement(seleniumLocatorProperty).getText();
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * returns the text of the found element.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param attributeName is name of dropdown
   * @return the text of the element, may be null in some cases
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   */
  public String getText(final String seleniumLocatorProperty, final String attributeName) {
    return getWebElement(seleniumLocatorProperty, attributeName).getText();
  }

  /**
   * Waits the maximum amount of time until a page is visible that contains the specified text. If the page with the
   * given title is not available after the maximum time an Exception will be thrown.
   *
   * @param textContainedInPageTitle the text to be part ("contained" in) the page title
   * @throws TimeoutException in case the page is not found with the given title
   */
  public void verifyPageTitleContains(final String textContainedInPageTitle) {
    Duration waitTime = getWebDriverSetup().getConfigurationForExplicitWaitTime();
    verifyPageTitleContains(textContainedInPageTitle, waitTime.getSeconds());
  }

  /**
   *<p> Waits the provided maximum amount of time until a page is visible that contains the specified text. If the page
   * with the given title is not available after the maximum time an Exception will be thrown.
   *
   * @param textContainedInPageTitle the text to be part ("contained" in) the page title
   * @param l the maximum amount of time to wait, must be greater than zero
   * @throws TimeoutException in case the page is not found with the given title
   */
  @SuppressWarnings("deprecation")
  public void verifyPageTitleContains(final String textContainedInPageTitle, final long l) {
    FluentWait wait =
        createWaitWithTimeoutMessage(ErrorMessageConstants.PAGE_TITLE_NOT_FOUND + textContainedInPageTitle);
    wait.withTimeout(Duration.ofSeconds(l));
    wait.until(ExpectedConditions.titleContains(textContainedInPageTitle));
  }

  /**
   *<p> Waits the provided maximum amount of time until a page is visible that contains the specified text. If the page
   * with the given title is not available after the maximum time an Exception will be thrown.
   *
   * @param textContainedInPageTitle the text to be part ("contained" in) the page title
   * @param waitTimeSeconds the maximum amount of time to wait, must be greater than zero
   * @throws TimeoutException in case the page is not found with the given title
   */
  @SuppressWarnings("deprecation")
  public void verifyPageTitleNotContains(final String textContainedInPageTitle, final int waitTimeSeconds) {
    FluentWait wait =
        createWaitWithTimeoutMessage(ErrorMessageConstants.PAGE_TITLE_NOT_FOUND + textContainedInPageTitle);
    wait.withTimeout(Duration.ofSeconds(waitTimeSeconds));
    wait.until(ExpectedConditions.titleContains(textContainedInPageTitle));
  }

  /**
   * @param timeoutMessage the message to show when the timeout happens
   * @return a {@link WebDriverWait} instance that ignores by default {@link NotFoundException}. Can be used to check
   *         for timeout manually and then provide a better error message.
   */
  @SuppressWarnings("deprecation")
  public WebDriverWait createWaitWithTimeoutMessage(final String timeoutMessage) {

    WebDriverWait wait = new WebDriverWait(this.driver, getWebDriverSetup().getConfigurationForExplicitWaitTime());
    wait.pollingEvery(Duration.ofSeconds(1));
    wait.ignoring(NotFoundException.class);
    wait.ignoring(StaleElementReferenceException.class);
    wait.withMessage(timeoutMessage);
    return wait;
  }

  /**
   * <p>Customized method to process web elements of provided locater, and check whether any of the element matched the
   * given condition.
   *
   * @param by locater.
   * @param pollingTime poll to check condition in given time interval.
   * @param pred condition to be checked.
   * @param errorMessages message to be thrown with the exception after time out.
   * @return true if atleast an element matched with the condition.
   */
  @SuppressWarnings("deprecation")
  public boolean anyMatch(final By by, final int pollingTime, final Predicate<WebElement> pred,
      final String errorMessages) {
    WebDriverWait wait = createWaitWithTimeoutMessage(errorMessages);
    wait.pollingEvery(Duration.ofMillis(pollingTime));
    return wait.until(isPredicated(by, pred));
  }

  /**
   * <p>Customised method to process web elements of provided locater, and check whether all  of the elements matched the
   * given condition.
   *
   * @param by locater.
   * @param pollingTime poll to check condition in given time interval.
   * @param pred condition to be checked.
   * @param errorMessages message to be thrown with the exception after time out.
   * @return true if all elements matched with the condition.
   */
  @SuppressWarnings("deprecation")
  public boolean allMatch(final By by, final int pollingTime, final Predicate<WebElement> pred,
      final String errorMessages) {
    WebDriverWait wait = createWaitWithTimeoutMessage(errorMessages);
    wait.pollingEvery(Duration.ofMillis(pollingTime));
    return wait.until(isPredicated(by, pred));
  }

  /**
   * <p>Predicts any matching element for the given condition.
   *
   * @param by locater.
   * @param pred condition to be checked.
   * @return if the predication passed.
   */
  private ExpectedCondition<Boolean> isPredicated(final By by, final Predicate<WebElement> pred) {
    return driver1 -> {
      return driver1.findElements(by).stream().anyMatch(pred);
    };
  }

  /**
   * <p>Waits until the current page DOM is completely loaded and can be used.
   * <p>
   * ATTENTION: Unfortunately this does not mean that the page is completely created since some JS code can alter the
   * DOM after the page was completely loaded.
   * <p>
   * That means if your page has some JS code that creates elements in the DOM than you should use one of the additional
   * waitForPageComplete methods, e.g. {@link #waitForPageLoaded(String)} since these are also waiting for other
   * criterias to happen before the page is assumed to be loaded.
   */
  public void waitForPageLoaded() {

    Duration waitTime = this.driverSetup.getConfigurationForExplicitWaitTime();

    waitForPageLoaded((int) waitTime.getSeconds());
  }

  /**
   *<p> Waits until the given element becomes stale according to Selenium. As a consequence the page holding the stale
   * element has be replaced by a new page.<br>
   * Elements are getting stale if a page is reloaded, a new page is loaded or the page dom (the given element in the
   * dom) is altered through some java script.
   *
   * @param elementOnOldPage the element on the page that must become stale before this method returns true
   * @throws TimeoutException if the timeout expired and the given element is still not stale
   */
  public void waitForPageLoaded(final WebElement elementOnOldPage) {

    new WebDriverWait(this.driver, getWebDriverSetup().getConfigurationForExplicitWaitTime())
    .until(new ExpectedCondition<Boolean>() {

      @Override
      public Boolean apply(final WebDriver thisDriver) {
        try {
          elementOnOldPage.findElement(By.name("Any Name"));
        }
        catch (StaleElementReferenceException e) {
          return true;
        }
        return false;
      }
    });
  }

  /**
   *<p> Waits until the current page DOM is completely loaded and can be used.
   * <p>
   * ATTENTION: Unfortunately this does not mean that the page is completely created since some JS code can alter the
   * DOM after the page was completely loaded.
   * <p>
   * That means if your page has some JS code that creates elements in the DOM than you should use one of the additional
   * waitForPageComplete methods, e.g. {@link #waitForPageLoaded(String)} since these are also waiting for other
   * criterias to happen before the page is assumed to be loaded.
   *
   * @param waitTimeSeconds the maximum amount of time to wait for the page, must be greatedThanZero
   */
  public void waitForPageLoaded(final int waitTimeSeconds) {
    // See https://stackoverflow.com/questions/5868439/wait-for-page-load-in-selenium :
    // "Even still the above does not guarentee that the page is complete - just that the dom is ready. Any dojo/jquery
    // might still be dynamically building elements on the page so you might need first wait for dynamic elements before
    // interacting with them"

    createWaitWithTimeoutMessage(ErrorMessageConstants.DOM_NOT_READY).until(new ExpectedCondition<Boolean>() {

      @Override
      public Boolean apply(final WebDriver thisDriver) {
        return ((JavascriptExecutor) thisDriver).executeScript("return document.readyState").equals("complete");
      }
    });
  }

  /**
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param webElementAttribute name of the attribute.
   * @return Attribute value.
   */
  public String getAttribute(final String seleniumLocatorProperty, final String webElementAttribute) {
    return getWebElement(seleniumLocatorProperty).getAttribute(webElementAttribute);
  }

  /**
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return This method returns the list web elements text.
   */
  public List<String> getWebElementsText(final String seleniumLocatorProperty) {

    return getWebElements(seleniumLocatorProperty).stream().map(WebElement::getText).collect(Collectors.toList());
  }

  /**
   * <p>Searches for the element that can be located by using the value stored under the given property and repeats the
   * search until the element is found or until the predefined timeout is reached. If found returns the enablement state
   * of the element
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return true if element found and is enabled, false if element found but is not enabled
   * @throws TimeoutException if no element could be found
   * @see WebElement#isEnabled()
   */
  public boolean isEnabled(final String seleniumLocatorProperty) {
    return getWebElement(seleniumLocatorProperty).isEnabled();
  }

  /**
   * <p>Searches for the element that can be located by using the value stored under the given property and repeats the
   * search until the element is found or until the predefined timeout is reached. If found returns the enablement state
   * of the element
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValue the value, if null then the original property value is returned
   * @return true if element found and is enabled, false if element found but is not enabled
   * @throws TimeoutException if no element could be found
   * @see WebElement#isEnabled()
   */
  public boolean isEnabled(final String seleniumLocatorProperty, final String dynamicValue) {
    return getWebElement(seleniumLocatorProperty, dynamicValue).isEnabled();
  }

  /**
   * Searches for a web element specified by the parameters and then returns it if found. The search is repeated until
   * the implicit selenium wait time is exceeded.<br>
   *
   * @see WebDriver#findElements(By)
   * @param searchText the text in the searched element, must not be null
   * @param byType the search criteria to search for the element using the given searchText
   * @return the found web element or null if not found
   */
  public WebElement getWebElementNoException(final String searchText, final ByType byType) {
    By locaterValue = createLocator(searchText, byType);
    List<WebElement> elements = this.driver.findElements(locaterValue);
    return elements.isEmpty() ? null : elements.get(0);
  }

  /**
   * Searches for web elements specified by the parameters and then returns all that have been found.<br>
   * If the element is not found it tries again until the Selenium implicit wait time is exceeded, if still not found
   * null is returned.
   *
   * @see WebDriver#findElements(By)
   * @param searchText the text in the searched element, must not be null
   * @param byType the search criteria to search for the element using the given searchText, must not be null
   * @return the found web element or null if not found
   */
  public List<WebElement> getWebElementsNoException(final String searchText, final ByType byType) {
    By locaterValue = createLocator(searchText, byType);
    return this.driver.findElements(locaterValue);
  }

  /**
   * Searches and waits the default maximum amount of time until an element defined by the given search criteria is
   * visible, finally returns it.<b>If the element is not found in time an exception is thrown.
   *
   * @param searchText the search text, must be null
   * @param byType the locator strategy to use, must not be null
   * @return the found element, never null
   * @throws TimeoutException if the element is not found
   */
  public WebElement getWebElement(final String searchText, final ByType byType) {
    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.ELEMENT_NOT_FOUND + searchText + " and locator type: " + byType.toString());
    By locaterValue = createLocator(searchText, byType);

    return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locaterValue));


  }

  /**
   * Opens the url and waits until the page is fully loaded, see {@link #waitForPageLoaded()}
   *
   * @param url the url to open
   */
  public void openURL(final String url) {
    getWebDriver().get(url);
    waitForPageLoaded();
  }

  /**
   * @return all the seleniumLocatorProperties that contain the selenium locator information
   */
  public Properties getSeleniumLocatorProperties() {
    return this.seleniumLocatorProperties;
  }

  /**
   * Returns the value of the selenium locator property.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return the value of the property
   * @throws WebAutomationException in case there is no property with the given name
   */
  public String getSeleniumLocatorProperty(final String seleniumLocatorProperty) {
    return getSeleniumLocatorProperty(seleniumLocatorProperty, (String) null);
  }

  /**
   * Takes the value of the given property and replaces all occurences of {@value #DYNAMIC_VALUE_PLACEHOLDER} in the
   * property value with the given 'dynamicValue'. Finally returns the changed property value.
   * <p>
   * If the original property value has no text to be replaced then simply the original value is returned.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValue the value, if null then the original property value is returned
   * @return the value of the property, with the explained string replacement.
   * @throws WebAutomationException in case there is no property with the given name
   */
  public String getSeleniumLocatorProperty(final String seleniumLocatorProperty, final String dynamicValue) {
    String temp = this.seleniumLocatorProperties.getProperty(seleniumLocatorProperty);
    if (temp == null) {
      if (seleniumLocatorProperty.endsWith("_xpath")) {
        throw new WebAutomationException(ErrorMessageConstants.PROP_VAL_NULL + seleniumLocatorProperty);
      }
      temp = seleniumLocatorProperty;
    }
    if (dynamicValue == null) {
      if (temp.contains(DYNAMIC_VALUE_PLACEHOLDER)) {
        throw new WebAutomationException(ErrorMessageConstants.PRP_DYNAMIC_VALUE + temp);
      }
      return temp;
    }
    return temp.replace(DYNAMIC_VALUE_PLACEHOLDER, dynamicValue);
  }

  /**
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValues the value, if null then the original property value is returned
   * @return selenium locater after replacing with dynamic value.
   */
  public String getSeleniumLocatorProperty(final String seleniumLocatorProperty, final String[] dynamicValues) {
    String temp = this.seleniumLocatorProperties.getProperty(seleniumLocatorProperty);
    if (temp == null) {
      if (seleniumLocatorProperty.endsWith("_xpath")) {
        throw new WebAutomationException(ErrorMessageConstants.PROP_VAL_NULL + seleniumLocatorProperty);
      }
      temp = seleniumLocatorProperty;
    }
    for (int i = 0; i < dynamicValues.length; i++) {
      temp = temp.replaceAll(DYNAMIC_VALUE_PLACEHOLDER + i, dynamicValues[i]);
    }

    return temp;
  }

  /**
   * Searches for the element that can be located by using the value stored under the given property and repeats the
   * search until the element is found or until the predefined timeout is reached. If found returns it.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return found element, never null
   * @throws TimeoutException if no element could be found
   */
  public WebElement getWebElement(final String seleniumLocatorProperty) {
    return getWebElement(seleniumLocatorProperty, null, null);
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * returns the element.<br>
   * It is assumed that the selenium locator property value contains at least once the string
   * {@value #DYNAMIC_VALUE_PLACEHOLDER} that shall be replaced by the given {@code dynamicValue}. The result of the
   * replacement is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String)
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValue the dynamic value to inject into the selenium locator property text, if null no replacement is
   *          done
   * @return the found element, never null
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if a dynamicValue is given but the property has no dynamic part or vice versa
   */
  public WebElement getWebElement(final String seleniumLocatorProperty, final String dynamicValue) {
    return getWebElement(seleniumLocatorProperty, dynamicValue, null);
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * returns the element.<br>
   * It is assumed that the selenium locator property value contains some dynamic parts that are identified by the text
   * {@value #DYNAMIC_VALUE_PLACEHOLDER}1, {@value #DYNAMIC_VALUE_PLACEHOLDER}2,{@value #DYNAMIC_VALUE_PLACEHOLDER}3,
   * ... that shall be replaced by the given {@code dynamicValues} in the order of the array. The result of the
   * replacemenet is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String[])
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValues the dynamic values to inject into the selenium locator property text, if null no replacement
   *          is done
   * @return the found element, never null
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if there is mismatch between the amount of given dynamic values and the amount of
   *           dynamic parts in the property value
   */
  public WebElement getWebElement(final String seleniumLocatorProperty, final String[] dynamicValues) {
    return getWebElement(seleniumLocatorProperty, null, dynamicValues);
  }

  /**
   * An expectation for checking that an element, known to be present on the DOM of a page, is visible. Visibility means
   * that the element is not only displayed but also has a height and width that is greater than 0.
   *
   * @param seleniumLocatorProperty Locater path stored as a string.
   * @param dynamicValue Replace DYNAMIC_VAlUE with the given value.
   * @param dynamicValues Replace list of DYNAMIC_VAlUE with the given values.
   * @return WebElement
   */
  private WebElement getWebElement(final String seleniumLocatorProperty, final String dynamicValue,
      final String[] dynamicValues) {

    WebElement element = null;
    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.ELEMENT_NOT_FOUND + seleniumLocatorProperty + ErrorMessageConstants.ELEMENT_NOT_VISIBLE);
    By locator;
    if (dynamicValues == null) {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
    }
    else {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValues);
    }

    element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return element;
  }

  /**
   * An expectation for checking that an element, known to be present on the DOM of a page, is visible. Visibility means
   * that the element is not only displayed but also has a height and width that is greater than 0.
   *
   * @param element The WebElement.
   * @return the (same) WebElement once it is visible
   */
  public WebElement getWebElement(final WebElement element) {
    FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.WEB_ELEMENT_NOT_VISIBLE);
    return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));

  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * returns the first visible element.<br>
   * It is assumed that the selenium locator property value contains at least once the string
   * {@value #DYNAMIC_VALUE_PLACEHOLDER} that shall be replaced by the given {@code dynamicValue}. The result of the
   * replacement is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String)
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValue the dynamic value to inject into the selenium locator property text, if null no replacement is
   *          done
   * @return the found element, never null
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if a dynamicValue is given but the property has no dynamic part or vice versa
   */
  public WebElement getFirstVisibleWebElement(final String seleniumLocatorProperty, final String dynamicValue) {
    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.ELEMENT_NOT_FOUND + seleniumLocatorProperty + ErrorMessageConstants.ELEMENT_NOT_VISIBLE);
    try {
      wait.until(x -> getWebElements(seleniumLocatorProperty, dynamicValue).stream().anyMatch(WebElement::isDisplayed));
    }
    catch (StaleElementReferenceException e) {
      // Allow one StaleElementReferenceException if it happens more than once,
      // something must be really wrong, so don't catch it again
      wait.until(x -> getWebElements(seleniumLocatorProperty, dynamicValue).stream().anyMatch(WebElement::isDisplayed));
    }
    List<WebElement> foundElements = getWebElements(seleniumLocatorProperty, dynamicValue);
    Optional<WebElement> firstEnabled = foundElements.stream().filter(WebElement::isDisplayed).findFirst();
    if (firstEnabled.isPresent()) {
      return firstEnabled.get();
    }
    // Shall not happen
    throw new WebAutomationException(
        "Internal error: Webelement associated with the property '" + seleniumLocatorProperty + "' not found!");
  }

  /**
   * An expectation for checking an element is visible and enabled such that you can click it
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return The WebElement.
   */
  public WebElement getWebElementClickbale(final String seleniumLocatorProperty) {
    return getWebElementClickbale(seleniumLocatorProperty, null, null);
  }

  /**
   * An expectation for checking an element is visible and enabled such that you can click it. It is assumed that the
   * selenium locator property value contains some dynamic parts that are identified by the text
   * {@value #DYNAMIC_VALUE_PLACEHOLDER}1, {@value #DYNAMIC_VALUE_PLACEHOLDER}2,{@value #DYNAMIC_VALUE_PLACEHOLDER}3,
   * ... that shall be replaced by the given {@code dynamicValues} in the order of the array. The result of the
   * replacemenet is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String[])
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValues the dynamic values to inject into the selenium locator property text, if null no replacement
   *          is done
   * @return the found element, never null
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if there is mismatch between the amount of given dynamic values and the amount of
   *           dynamic parts in the property value
   */
  public WebElement getWebElementClickbale(final String seleniumLocatorProperty, final String[] dynamicValues) {
    return getWebElementClickbale(seleniumLocatorProperty, null, dynamicValues);
  }

  /**
   * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that
   * the element is visible.
   *
   * @param seleniumLocatorProperty Locater path stored as a string.
   * @param dynamicValue Replace DYNAMIC_VAlUE with the given value.
   * @return The WebElement.
   */
  public WebElement getWebElementClikbale(final String seleniumLocatorProperty, final String dynamicValue) {
    return getWebElementClickbale(seleniumLocatorProperty, dynamicValue, null);
  }


  /**
   * An expectation for checking an element is visible and enabled such that you can click it.
   *
   * @param seleniumLocatorProperty Locater path stored as a string.
   * @param dynamicValue Replace DYNAMIC_VAlUE with the given value.
   * @param dynamicValues Replace list of DYNAMIC_VAlUE with the given values.
   * @return The WebElement.
   */
  private WebElement getWebElementClickbale(final String seleniumLocatorProperty, final String dynamicValue,
      final String[] dynamicValues) {

    WebElement element = null;
    FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ELEMENT_NOT_FOUND + seleniumLocatorProperty +
        ErrorMessageConstants.ELEMENT_NOT_CLICKABLE);
    By locator;
    if (dynamicValues == null) {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
    }
    else {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValues);
    }

    element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));

    // don't need this if the element is already found by above call
    // element = this.driver.findElement(locaterValue);

    return element;
  }

  /**
   * An expectation for checking an element is visible and enabled such that you can click it.
   *
   * @param element The WebElement.
   * @return the (same) WebElement once it is clickable (visible and enabled)
   */
  public WebElement getClickableWebElement(final WebElement element) {
    FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.WEB_ELEMENT_NOT_CLICKABLE);
    return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
  }


  /**
   * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that
   * the element is visible.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property.
   * @return The WebElement.
   */
  public WebElement getPresenceOfWebElement(final String seleniumLocatorProperty) {

    return getPresenceOfWebElement(seleniumLocatorProperty, null, null);
  }

  /**
   * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that
   * the element is visible.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValue Replace DYNAMIC_VAlUE with the given value.
   * @return WebElement
   */
  public WebElement getPresenceOfWebElement(final String seleniumLocatorProperty, final String dynamicValue) {
    return getPresenceOfWebElement(seleniumLocatorProperty, dynamicValue, null);
  }

  /**
   ** An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that
   * the element is visible. It is assumed that the selenium locator property value contains some dynamic parts that are
   * identified by the text {@value #DYNAMIC_VALUE_PLACEHOLDER}1,
   * {@value #DYNAMIC_VALUE_PLACEHOLDER}2,{@value #DYNAMIC_VALUE_PLACEHOLDER}3, ... that shall be replaced by the given
   * {@code dynamicValues} in the order of the array. The result of the replacemenet is then used for locating the web
   * element.
   *
   * @see #createLocatorFromProperty(String, String[])
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValues the dynamic values to inject into the selenium locator property text, if null no replacement
   *          is done
   * @return the found element, never null
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if there is mismatch between the amount of given dynamic values and the amount of
   *           dynamic parts in the property value
   */
  public WebElement getPresenceOfWebElement(final String seleniumLocatorProperty, final String[] dynamicValues) {
    return getPresenceOfWebElement(seleniumLocatorProperty, null, dynamicValues);
  }

  /**
   * <p>
   * Test if frameswitching works for dead element error
   */
  public void switchToDefaultContent() {
    this.driver.switchTo().defaultContent();

  }

  /**
   * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that
   * the element is visible.
   *
   * @param seleniumLocatorProperty Locater path stored as a string.
   * @param dynamicValue Replace DYNAMIC_VAlUE with the given value.
   * @param dynamicValues Replace list of DYNAMIC_VAlUE with the given values.
   * @return
   */
  private WebElement getPresenceOfWebElement(final String seleniumLocatorProperty, final String dynamicValue,
      final String[] dynamicValues) {
    WebElement element = null;
    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.ELEMENT_NOT_FOUND + seleniumLocatorProperty + ErrorMessageConstants.ELEMENT_NOT_PRESENT);
    By locator;
    if (dynamicValues == null) {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
    }
    else {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValues);
    }

    element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    return element;
  }

  /**
   * An expectation for checking that there is at least one element present on a web page.
   *
   * @param locater By
   * @return list of WebElement
   */
  @SuppressWarnings("unused")
  private List<WebElement> getWebElements(final By locater) {
    return createWaitWithTimeoutMessage(
        locater.toString() + ErrorMessageConstants.ELEMENTS_NOT_FOUND + ErrorMessageConstants.ELEMENT_NOT_PRESENT)
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locater));
  }

  /**
   * Searches for the elements located by the information stored under the given property and repeats the search until
   * at least one element is found or the predefined timeout is reached. Then returns the found elements.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return the list of found elements, never null and never empty
   * @throws TimeoutException if no element could be found
   */
  public List<WebElement> getWebElements(final String seleniumLocatorProperty) {

    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.ELEMENTS_NOT_FOUND + seleniumLocatorProperty + ErrorMessageConstants.ELEMENT_NOT_PRESENT);
    wait.until(ExpectedConditions.presenceOfElementLocated(createLocatorFromProperty(seleniumLocatorProperty)));
    return this.driver.findElements(createLocatorFromProperty(seleniumLocatorProperty));
  }

  /**
   * Searches for the elements located by the information stored under the given property and repeats the search until
   * at least one element is found or the predefined timeout is reached. Then returns the found elements.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return the list of found elements, never null and never empty
   * @throws TimeoutException if no element could be found
   */
  public List<WebElement> getVisibleWebElements(final String seleniumLocatorProperty) {

    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.ELEMENT_NOT_FOUND + seleniumLocatorProperty + ErrorMessageConstants.ELEMENT_NOT_VISIBLE);
    wait.until(x -> getWebElements(seleniumLocatorProperty).stream().anyMatch(WebElement::isDisplayed));

    Stream<WebElement> foundDisplayed =
        getWebElements(seleniumLocatorProperty).stream().filter(WebElement::isDisplayed);
    return foundDisplayed.collect(Collectors.toList());
  }

  /**
   * An expectation for checking that an element is present on the DOM of a page.
   *
   * @param locater By
   * @return WebElement
   */
  public WebElement getPresenceOfWebElement(final By locater) {
    return createWaitWithTimeoutMessage(locater.toString() + ErrorMessageConstants.WEB_ELEMENT_NOT_PRESENT)
        .until(ExpectedConditions.presenceOfElementLocated(locater));
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * returns the element.<br>
   * It is assumed that the selenium locator property value contains at least once the string
   * {@value #DYNAMIC_VALUE_PLACEHOLDER} that shall be replaced by the given {@code dynamicValue}. The result of the
   * replacement is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String)
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValue the dynamic value to inject into the selenium locator property text, if null no replacement is
   *          done
   * @return the found element, never null
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if a dynamicValue is given but the property has no dynamic part or vice versa
   */
  public List<WebElement> getWebElements(final String seleniumLocatorProperty, final String dynamicValue) {
    return getWebElements(seleniumLocatorProperty, dynamicValue, null);
  }

  /**
   * Searches for an element that can be located by the locator information stored under the given property. If found
   * returns the element.<br>
   * It is assumed that the selenium locator property value contains some dynamic parts that are identified by the text
   * {@value #DYNAMIC_VALUE_PLACEHOLDER}1, {@value #DYNAMIC_VALUE_PLACEHOLDER}2,{@value #DYNAMIC_VALUE_PLACEHOLDER}3,
   * ... that shall be replaced by the given {@code dynamicValues} in the order of the array. The result of the
   * replacemenet is then used for locating the web element.
   *
   * @see #createLocatorFromProperty(String, String[])
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param dynamicValues the dynamic values to inject into the selenium locator property text, if null no replacement
   *          is done
   * @return the found element, never null
   * @throws TimeoutException in case the element could not be found after the predefined maximum amount of time
   * @throws WebAutomationException if there is mismatch between the amount of given dynamic values and the amount of
   *           dynamic parts in the property value
   */
  public List<WebElement> getWebElements(final String seleniumLocatorProperty, final String[] dynamicValues) {
    return getWebElements(seleniumLocatorProperty, null, dynamicValues);
  }

  /**
   * An expectation for checking that an element, known to be present on the DOM of a page, is visible. Visibility means
   * that the element is not only displayed but also has a height and width that is greater than 0.
   *
   * @param seleniumLocatorProperty Locater path stored as a string.
   * @param dynamicValue Replace DYNAMIC_VAlUE with the given value.
   * @param dynamicValues Replace list of DYNAMIC_VAlUE with the given values.
   * @return WebElement
   */
  public List<WebElement> getWebElements(final String seleniumLocatorProperty, final String dynamicValue,
      final String[] dynamicValues) {

    List<WebElement> element = null;


    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.ELEMENTS_NOT_FOUND + seleniumLocatorProperty + ErrorMessageConstants.ELEMENT_NOT_VISIBLE);
    By locator;
    if (dynamicValues == null) {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
    }
    else {
      locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValues);
    }

    element = (List<WebElement>) wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

    // don't need this if the element is already found by above call
    // element = this.driver.findElement(locaterValue);

    return element;
  }

  /**
   * An expectation for checking a element from the given element.
   *
   * @param element The WebElement
   * @param by By
   * @return the (other) WebElement once it is visible.
   */
  public WebElement getChildElement(final WebElement element, final By by) {
    return createWaitWithTimeoutMessage(ErrorMessageConstants.CHILD_ELEMENTS_NOT_VISIBLE)
        .until(new ExpectedCondition<WebElement>() {

          @SuppressWarnings("hiding")
          @Override
          public WebElement apply(final WebDriver driver) {
            return element.findElement(by);
          }
        });
  }

  /**
   * An expectation for checking a element from the given element.
   *
   * @param element The WebElement
   * @param by locter of child element.
   * @return the (other) list of WebElement.
   */
  public List<WebElement> getVisibleChildElements(final WebElement element, final By by) {
    return createWaitWithTimeoutMessage(ErrorMessageConstants.CHILD_ELEMENTS_NOT_VISIBLE + by.toString())
        .until(new ExpectedCondition<List<WebElement>>() {

          @Override
          public List<WebElement> apply(final WebDriver input) {
            return element.findElements(by).parallelStream().filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
          }
        });
  }

  /**
   * An expectation for checking child WebElement as a part of parent element to be visible
   *
   * @param element used as parent element. For example table with locator By.xpath("//table")
   * @param loacter used as parent element. For example table with locator By.xpath("//table")
   * @param timeOutInSeconds maximum amount of time taken to search visibility of the element.
   * @return true if the child element found.
   */
  public boolean isNestedElementVisibile(final WebElement element, final By loacter, final Duration timeOutInSeconds) {

    try {
      WebDriverWait wait = new WebDriverWait(this.driver, timeOutInSeconds);
      return wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, loacter)) != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * An expectation to check if js executable. Useful whenyou know that there should be a Javascript value or something
   * at the stage
   *
   * @param javaScript An expectation to check if js executable. Useful whenyou know that there should be a Javascript
   *          value or something at the stage
   */
  public void executeJavaScript(final String javaScript) {

    if (!new WebDriverWait(this.driver, getWebDriverSetup().getConfigurationForExplicitWaitTime())
        .until(ExpectedConditions.javaScriptThrowsNoExceptions(javaScript))) {
      throw new WebAutomationException("Invalid java script value.");
    }

  }

  /**
   * An expectation for checking whether the given frame is available to switch to. If the frame is available it
   * switches the given driver to the specified webelement.
   *
   * @param index of the frame in the webpage.
   */
  public void switchToFrame(final int index) {
    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.FRAME_WITH_INDEX + index + ErrorMessageConstants.FRAME_NOT_FOUND);
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
  }

  /**
   * An expectation for checking whether the given frame is available to switch to. If the frame is available it
   * switches the given driver to the specified webelement.
   *
   * @param seleniumLocatorProperty the name of the property from which to derive the locator, must not be null and the
   *          property must exist
   */
  public void switchToFrame(final String seleniumLocatorProperty) {
    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.FRAME_WITH_LOCATER + seleniumLocatorProperty + ErrorMessageConstants.FRAME_NOT_FOUND);
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getPresenceOfWebElement(seleniumLocatorProperty)));
  }
  
  /**
   * An expectation for checking whether the given frame is available to switch to. If the frame is available it
   * switches the given driver to the specified webelement.
   *
   * @param seleniumLocatorProperty the name of the property from which to derive the locator, must not be null and the
   *          property must exist
   */
  public void switchToFrame(final String seleniumLocatorProperty,final String dynamicValue) {
    FluentWait wait = createWaitWithTimeoutMessage(
        ErrorMessageConstants.FRAME_WITH_LOCATER + seleniumLocatorProperty + ErrorMessageConstants.FRAME_NOT_FOUND);
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getPresenceOfWebElement(seleniumLocatorProperty, dynamicValue)));
  }

  /**
   * Wait until an element is no longer attached to the DOM.
   *
   * @param element The WebElement.
   * @return true if the element is no longer attached to the DOM.
   */
  public boolean isElementStale(final WebElement element) {
    return new WebDriverWait(this.driver, getWebDriverSetup().getConfigurationForExplicitWaitTime())
        .until(ExpectedConditions.stalenessOf(element));
  }

  /**
   * Searches for the element (whose locator information is stored under the given property) and repeats the search
   * until the element is visible on the current page. If visible return true. If the element cannout be found and the
   * predefined timeout is reached false is returned.
   *
   * @param ccmworkitemeditorpageStateListboxXpath the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param i max time to search for the visisbility of element in DOM.
   * @return true if element is found and visible on the page, false otherwise
   * @see ExpectedConditions#visibilityOfElementLocated(By)
   */
  public boolean isElementVisible(final String ccmworkitemeditorpageStateListboxXpath, final Duration i) {
    By locator = createLocatorFromProperty(ccmworkitemeditorpageStateListboxXpath);
    try {

      WebDriverWait wait = new WebDriverWait(this.driver, i);
      wait.ignoring(Exception.class, TimeoutException.class);
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      return element != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * Seraching for the visibility of the locater.
   *
   * @param locater value of the Element.
   * @param i max time to search for the visisbility of element in DOM.
   * @return true if the locater exists.
   */
  public boolean isLocaterVisible(final By locater, final Duration i) {

    try {

      WebDriverWait wait = new WebDriverWait(this.driver, i);
      wait.ignoring(Exception.class, TimeoutException.class);
      WebElement tempElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
      return tempElement != null;
    }
    catch (Exception e) {
      return false;
    }
  }


  /**
   * An expectation for checking if the given text contains in the specified web element.
   *
   * @param element The WebElement.
   * @param value used as expected text matcher pattern.
   * @return true once the element contains the given text.
   */
  public boolean textTobePresentInTheElement(final WebElement element, final String value) {

    FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ACTUAL_TEXT_MESSAGE);
    return (boolean) wait.until(ExpectedConditions.textToBePresentInElement(element, value));
  }

  /**
   * An expectation for checking if the given text is exact match with the specified element text.
   *
   * @param element The WebElement.
   * @param textValue Actual text value.
   * @param timeInSecs Time in seconds
   * @return true if the text exact match with the element text.
   */
  public Boolean isTextExactMatchWithTheElementText(final WebElement element, final String textValue,
      final int timeInSecs) {
    try {
      FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ATTRIBUTE_VALUE);
      wait.pollingEvery(Duration.ofSeconds(timeInSecs));
      wait.ignoring(TimeoutException.class);
      ExpectedCondition<Boolean> pred = driver1 -> element.getText().equals(textValue);
      return (Boolean) wait.until(pred);
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * An expectation for checking if the given text starts with element text.
   *
   * @param element The WebElement.
   * @param textValue Actual text value.
   * @param timeInSecs Time in secs.
   * @return true if the text starts with the given element text.
   */
  public Boolean isTextStartWithElementText(final WebElement element, final String textValue, final int timeInSecs) {
    try {
      FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ACTUAL_TEXT_MESSAGE);
      wait.pollingEvery(Duration.ofSeconds(timeInSecs));
      wait.ignoring(TimeoutException.class);

      ExpectedCondition<Boolean> pred = driver1 -> element.getText().startsWith(textValue);
      return (boolean) wait.until(pred);
    }
    catch (Exception e) {
      return false;
    }


  }

  /**
   * An expectation for checking WebElement with given locator has attribute which contains specific value
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   * @param attribute Name of the attribue.
   * @param actualValue Value of the attribute.
   * @return true if attribue value matches.
   */
  public boolean isAttributeContains(final String seleniumLocatorProperty, final String attribute,
      final String actualValue) {
    try {
      FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ATTRIBUTE_DOES_NOT_CONTAIN_VALUE);
      wait.ignoring(TimeoutException.class);
      return (boolean) wait
          .until(ExpectedConditions.attributeContains(getWebElement(seleniumLocatorProperty), attribute, actualValue));
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * An expectation for checking if attribue value is exact match with the given value.
   *
   * @param element The WebElement.
   * @param atributeName Name of the attribute.
   * @param text The attribute value.
   * @param duration time in seconds.
   * @return returns true if the attribute value matches exactly.
   */
  public Boolean isAttributeContains(final WebElement element, final String atributeName, final String text,
      final Duration duration) {
    try {
      FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ATTRIBUTE_DOES_NOT_CONTAIN_VALUE);
      wait.ignoring(TimeoutException.class);
      wait.withTimeout(duration);
      ExpectedCondition<Boolean> pred = driver1 -> element.getAttribute(atributeName).contains(text);
      return (boolean) wait.until(pred);
    }
    catch (Exception e) {
      return false;
    }

  }

  /**
   * Searchs for the given attribute present in the element.
   *
   * @param element The WebElement.
   * @param attribute name of the attribute.
   * @param duration seaches the attribute untill the given time.
   * @return true if attribute found in the webelement else false.
   */
  public boolean isAttributeNotNull(final WebElement element, final String attribute, final Duration duration) {
    try {
      FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ATTRIBUTE_NOT_VALUE);
      wait.ignoring(TimeoutException.class);
      wait.withTimeout(duration);
      ExpectedCondition<Boolean> pred = driver1 -> element.getAttribute(attribute) != null;
      return (boolean) wait.until(pred);
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * * Searchs for the given attribute present in the given locater.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property.
   * @param attribute name of the attribute.
   * @param timeInSecs seaches the attribute untill the given time.
   * @return true if attribute found in the locater else false.
   */
  public boolean isAttributeNotNull(final String seleniumLocatorProperty, final String attribute,
      final int timeInSecs) {
    try {
      FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.ATTRIBUTE_NOT_VALUE);
      wait.ignoring(TimeoutException.class);
      wait.withTimeout(Duration.ofSeconds(timeInSecs));
      ExpectedCondition<Boolean> pred = driver1 -> getWebElement(seleniumLocatorProperty) != null;
      return (boolean) wait.until(pred);
    }
    catch (Exception e) {
      return false;
    }

  }

  /**
   * @param result is time to wait
   */
  public void waitForSecs(final Duration result) {
    try {
      new WebDriverWait(this.driver, result)
      .until(ExpectedConditions.visibilityOfElementLocated(By.name("Any Name")));
    }
    catch (Exception e) {
      // ignore exception
    }
  }

  /**
   * @param seleniumLocatorProperty the property holding the selenium locator information (not null and exisitng)
   * @param timeInSecs is time to wait
   */
  public void waitForUnclickabilityOfElement(final String seleniumLocatorProperty, final Duration timeInSecs) {
    try {
      By locator = createLocatorFromProperty(seleniumLocatorProperty);
      new WebDriverWait(this.driver, timeInSecs)
      .until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
    }
    catch (Exception e) {
      // ignore exception
    }
  }

  /**
   * This method searchs whether actual test given is contained in the cuurent url or not with in the given time.
   *
   * @param actualText The text to be contained.
   * @param timeInSecs searchs the text untill the given time.
   * @return true if the actual test is present in the current url.
   */
  public boolean isTextPresentInTheCurrentUrl(final String actualText, final int timeInSecs) {
    try {
      FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.TEXT_NOT_PRESENT);
      wait.ignoring(TimeoutException.class);
      wait.withTimeout(Duration.ofSeconds(timeInSecs));
      ExpectedCondition<Boolean> pred = driver1 -> driver1.getCurrentUrl().contains(actualText);
      return (boolean) wait.until(pred);
    }
    catch (Exception e) {
      return false;
    }

  }

  /**
   * An expectation for checking that the title contains a case-sensitive substring Parameters:title the fragment of
   * title expectedReturns:true when the title matches, false otherwise
   *
   * @param title he fragment of title expectedReturns:true when the title matches, false otherwise
   * @param timeOutInSeconds maximum amount of time to check the title contains.
   * @return true if the page of the title matches.
   */
  public boolean isTitleContains(final String title, final Duration timeOutInSeconds) {
    try {
      return new WebDriverWait(this.driver, timeOutInSeconds).until(ExpectedConditions.titleContains(title));
    }
    catch (TimeoutException e) {
      return false;
    }

  }

  /**
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @return selected options in the list box else null;
   */
  public String getSelectedValueFromDropDown(final String seleniumLocatorProperty) {
    FluentWait wait = createWaitWithTimeoutMessage(ErrorMessageConstants.SELECT_NOT_PRESENT);
    ExpectedCondition<String> pred = new ExpectedCondition<String>() {

      @Override
      public String apply(final WebDriver input) {
        Select sel = new Select(getWebElement(seleniumLocatorProperty));
        return sel.getFirstSelectedOption().getText();
      }
    };

    return (String) wait.until(pred);
  }

  /**
   * Creates and returns a locator from the given property and the given 'dynamicValue' that will replace all occurences
   * of {@value #DYNAMIC_VALUE_PLACEHOLDER} in the property value before being returned to the caller of this method.
   * <p>
   * The type of locator is specified in the property name after the underscore '_'. The value of the property is the
   * locator parameter. Example: Login.Submit.Button_xpath = //button[@type = 'submit']
   *
   * @param seleniumLocatorProperty the name of the property from which to derive the locator, must not be null and the
   *          property must exist
   * @param dynamicValue if the value of the property contains any {@value #DYNAMIC_VALUE_PLACEHOLDER} then it will be
   *          replaced by this text, if null then no replacement will be done and the original property value is
   *          returned.
   * @return the locator instance, never null
   * @throws WebAutomationException in case the locator could not be derived from the given property, or the property
   *           with that name is not valid, ...
   */
  public By createLocatorFromProperty(final String seleniumLocatorProperty, final String dynamicValue) {
    String propertyValue = getSeleniumLocatorProperty(seleniumLocatorProperty, dynamicValue);
    return createLocatorFromGivenPropertyValue(seleniumLocatorProperty, propertyValue);
  }

  /**
   * Creates and returns a locator from the given property and the given 'dynamicValue' that will replace all occurences
   * of {@value #DYNAMIC_VALUE_PLACEHOLDER} in the property value before being returned to the caller of this method.
   * <p>
   * The type of locator is specified in the property name after the underscore '_'. The value of the property is the
   * locator parameter. Example: Login.Submit.Button_xpath = //button[@type = 'submit']
   *
   * @param seleniumLocatorProperty the name of the property from which to derive the locator, must not be null and the
   *          property must exist
   * @param dynamicValues if the value of the property contains any {@value #DYNAMIC_VALUE_PLACEHOLDER} then it will be
   *          replaced by this text, if null then no replacement will be done and the original property value is
   *          returned.
   * @return the locator instance, never null
   * @throws WebAutomationException in case the locator could not be derived from the given property, or the property
   *           with that name is not valid, ...
   */
  public By createLocatorFromProperty(final String seleniumLocatorProperty, final String[] dynamicValues) {
    String propertyValue = getSeleniumLocatorProperty(seleniumLocatorProperty, dynamicValues);
    return createLocatorFromGivenPropertyValue(seleniumLocatorProperty, propertyValue);
  }

  /**
   * Waits the maximum amount of time until a page is loaded and contains the specified text as title. If the page with
   * the given title is not available after the maximum time an exception will be thrown.
   *
   * @param textContainedInPageTitle the text to be part ("contained" in) the page title
   */
  public void waitForPageLoaded(final String textContainedInPageTitle) {
    waitForPageLoaded();
    verifyPageTitleContains(textContainedInPageTitle);
  }

  /**
   * Waits the provided maximum amount of time until a page is loaded and contains the specified text as title. If the
   * page with the given title is not available after the maximum time an exception will be thrown.
   *
   * @param textContainedInPageTitle the text to be part ("contained" in) the page title
   * @param waitTimeSeconds the maximum amount of time to wait until the page should be loaded and having the given
   *          title, must be greater than zero
   */
  public void waitForPageLoaded(final String textContainedInPageTitle, final int waitTimeSeconds) {
    waitForPageLoaded(waitTimeSeconds);
    verifyPageTitleContains(textContainedInPageTitle, waitTimeSeconds);
  }

  /**
   * @return Holds some default configuration values for the used selenium web driver like implicit and explicit
   *         selenium wait time. Is never null.
   */
  public DriverSetup getWebDriverSetup() {
    return this.driverSetup;
  }

  /**
   * @return String download folder path
   * @see DriverSetup#getDownloadFolderLocation()
   */
  public String getDownloadFolderLocation() {
    return this.driverSetup.getDownloadFolderLocation();
  }

  /**
   * Closes all tabs in the current browser window except the one that was active when this method was called
   */
  public void closeAllTabsExceptOne() {
    String originalHandle = this.driver.getWindowHandle();
    for (String handle : this.driver.getWindowHandles()) {
      if (!handle.equals(originalHandle)) {
        this.driver.switchTo().window(handle);
        this.driver.close();
      }
    }
    this.driver.switchTo().window(originalHandle);
  }

  /**
   * An expectation for checking that an element is either invisible or not present on the DOM.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param i wait for given period of time if the element is not visible.
   * @return true if the element is visible.
   */
  public boolean isElementInvisible(final String seleniumLocatorProperty, final Duration i) {

    By locator = createLocatorFromProperty(seleniumLocatorProperty);
    try {
      WebDriverWait wait = new WebDriverWait(this.driver, i);
      wait.ignoring(Exception.class, TimeoutException.class);
      return wait.until(ExpectedConditions.invisibilityOfAllElements(this.driver.findElements(locator)));

    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * An expectation for checking that an element is either invisible or not present on the DOM.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param timeInSecs wait for given period of time if the element is not visible.
   * @param dynamicValue replace the default value.
   * @return true if the element is visible.
   */
  public boolean isElementInvisible(final String seleniumLocatorProperty, final Duration timeInSecs,
      final String dynamicValue) {

    By locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
    try {
      WebDriverWait wait = new WebDriverWait(this.driver, timeInSecs);
      wait.ignoring(Exception.class, TimeoutException.class);
      return wait.until(ExpectedConditions.invisibilityOfAllElements(this.driver.findElements(locator)));

    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * Searches for the element (whose locator information is stored under the given property) and repeats the search
   * until the element is clickable on the current page. If clickable return true. If the element cannout be found and
   * the predefined timeout is reached false is returned.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property
   * @param l maximum time to check the element is clickable or not.
   * @return true if element is found and visible on the page, false otherwise
   * @see ExpectedConditions#elementToBeClickable(By)
   */
  public boolean isElementClickable(final String seleniumLocatorProperty, final Duration l) {

    try {
      By locator = createLocatorFromProperty(seleniumLocatorProperty);
      WebDriverWait wait = new WebDriverWait(this.driver, l);
      wait.ignoring(Exception.class, TimeoutException.class);
      WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
      return element != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * It will check whether the element is present or not.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property.
   * @param timeInSecs seaches for the presence of element untill the given time.
   * @return true if the element is present on the dom.
   */
  public boolean isElementPresent(final String seleniumLocatorProperty, final Duration timeInSecs) {
    WebElement webElement = null;
    try {
      WebDriverWait wait = new WebDriverWait(this.driver, timeInSecs);
      webElement =
          wait.until(ExpectedConditions.presenceOfElementLocated(createLocatorFromProperty(seleniumLocatorProperty)));
      return webElement != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * It will check the dynamic values whether the element is present or not.
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property.
   * @param timeInSecs seaches for the presence of element untill the given time.
   * @param dynamicValue replace the default value.
   * @return true if the element is present on the dom.
   */
  public boolean isElementPresent(final String seleniumLocatorProperty, final Duration timeInSecs,
      final String dynamicValue) {
    WebElement webElement = null;
    try {

      WebDriverWait wait = new WebDriverWait(this.driver, timeInSecs);
      webElement = wait.until(ExpectedConditions
          .presenceOfElementLocated(createLocatorFromProperty(seleniumLocatorProperty, dynamicValue)));
      return webElement != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * It will accept the dynamic values and check the visibility of the element
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property.
   * @param i wait for given period of time if the element is not visible.
   * @param dynamicValue replace the default value.
   * @return true if the element is visible.
   */
  public boolean isElementVisible(final String seleniumLocatorProperty, final Duration i,
      final String dynamicValue) {

    try {
      By locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
      WebDriverWait wait = new WebDriverWait(this.driver, i);
      wait.ignoring(Exception.class, TimeoutException.class);
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      return element != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * It will accept the dynamic values and check the visibility of the element
   *
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property.
   * @param timeInSecs wait for given period of time if the element is not visible.
   * @param dynamicValue replace the default value.
   * @return true if the element is visible.
   */
  public boolean isElementVisible(final String seleniumLocatorProperty, final Duration timeInSecs,
      final String[] dynamicValue) {

    try {
      By locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
      WebDriverWait wait = new WebDriverWait(this.driver, timeInSecs);
      wait.ignoring(Exception.class, TimeoutException.class);
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      return element != null;
    }
    catch (Exception e) {
      return false;
    }
  }


  /**
   *
   */
  public void switchBrowserTabs() {
    ArrayList<String> tabs2 = new ArrayList<>(this.driver.getWindowHandles());
    this.driver.switchTo().window(tabs2.get(1));

  }
  
  /**
  *
  */
 public void switchBrowserTabWithIndex(Integer index) {
   ArrayList<String> tabs = new ArrayList<>(this.driver.getWindowHandles());
   this.driver.switchTo().window(tabs.get(index));

 }

  /**
   * Searches for the element (whose locator information is stored under the given property) and repeats the search
   * until the element is visible on the current page. If visible return true. If the element cannout be found and the
   * predefined timeout is reached false is returned.
   *
   * @param element WebElement
   * @param timeInSecs max time to search for the visisbility of element in DOM.
   * @return true if element is found and visible on the page, false otherwise
   * @see ExpectedConditions#visibilityOfElementLocated(By)
   */
  public boolean isElementVisible(final WebElement element, final Duration timeInSecs) {

    try {

      WebDriverWait wait = new WebDriverWait(this.driver, timeInSecs);
      wait.ignoring(Exception.class, TimeoutException.class);
      WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
      return ele != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * @param seleniumLocatorProperty the property holding the selenium locator information, must not be null and must be
   *          an existing property.
   * @param timeInSecs wait for given period of time if the element is not clickable.
   * @param dynamicValue replace the default value.
   * @return true if the element is Clickable.
   */
  public boolean isElementClickable(final String seleniumLocatorProperty, final Duration timeInSecs,
      final String dynamicValue) {

    try {
      By locator = createLocatorFromProperty(seleniumLocatorProperty, dynamicValue);
      WebDriverWait wait = new WebDriverWait(this.driver, timeInSecs);
      wait.ignoring(TimeoutException.class);
      WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
      return element != null;
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * Switches from current frame to parent frame.
   */
  public void switchToParentFrame() {
    this.driver.switchTo().parentFrame();
  }

  public WebElement getChildElementWithretry(final WebElement element, final By by) 
  {
    final int maxAttempts=12;
    int attempts=0;
    while(attempts<maxAttempts)
    {
      try
      {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)) ;
      
       return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, by));
      }
      catch(StaleElementReferenceException e)
      {
        System.out.println("\n\n Stale Element child");
        attempts++;
        if(attempts==maxAttempts)
        {
          throw e;
        }
      }
    }
    
    return element;
    
  }
  /**
   * @return the alert text if an alert was visible after max wait time of 5 seconds.
   */
  public String acceptAlertAndReturnAlertText() {
    // This will wait for a maximum of 5 seconds, everytime wait is used
    WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(5));
    wait.until(ExpectedConditions.alertIsPresent());
    // Before you try to switch to the so given alert, he needs to be present.

    Alert alert = getWebDriver().switchTo().alert();
    String alertText = alert.getText();
    alert.accept();

    return alertText;
  }

  /**
   * creates object of actions class.
   *
   * @return object of actions aclass
   */
  public Actions getActions() {
    return new Actions(this.driver);
  }

  /**
   * click on element using actions class.
   *
   * @param element web element used to click.
   */
  public void clickUsingActions(final WebElement element) {
    getActions().moveToElement(element).click().build().perform();
  }

  /**
   * Send keys for element using actions class.
   *
   * @param element text field web element.
   * @param text to set vlaue in text field.
   */
  public void typeTextUsingActions(final WebElement element, final String text) {
    getActions().moveToElement(element).sendKeys(text).build().perform();
  }
  
  /**
   * Scroll element into center of view
   * @param element to scroll to
   * @author LTU7HC
   */
  public void scrollIntoCenterOfView(final WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) this.driver;
    String scrollElementIntoMiddle =
        "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" +
            "var elementTop = arguments[0].getBoundingClientRect().top;" +
            "window.scrollBy(0, elementTop-(viewPortHeight/2));";
    try {
      js.executeScript(scrollElementIntoMiddle, element);
    }
    catch (Exception e) {
      LOGGER.LOG.error("Can not scroll element into center of view - ERROR: " + e);
      throw e;
    }
  }
  
  /**
   * Scroll element into center of view
   * @param xpath locator of element
   * @author LTU7HC
   */
  public void scrollIntoCenterOfView(String xpath) {
    isElementVisible(xpath, Duration.ofSeconds(30));
    WebElement element = this.driver.findElement(By.xpath(xpath));
    scrollIntoCenterOfView(element);
  }

  /**
   * Send keys for element using actions class.
   *
   * @param element web element of text filed.
   * @param text value to be entered on text field.
   * @param key key to perform actions.
   */
  public void typeTextUsingActionsAndPressKey(final WebElement element, final String text, final Keys key) {
    getActions().moveToElement(element).sendKeys(text + key).build().perform();
  }
  
  /**
   * Wait until element is invisible or not present on the DOM.
   */
  public void waitUntilElementIsInvisible(final String seleniumLocatorProperty, final Duration i) {

    By locator = createLocatorFromProperty(seleniumLocatorProperty);
    WebDriverWait wait = new WebDriverWait(this.driver, i);
    wait.ignoring(Exception.class, TimeoutException.class);
    wait.until(ExpectedConditions.invisibilityOfAllElements(this.driver.findElements(locator)));   
  }
}
