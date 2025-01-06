/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bosch.automation.web.log.LOGGER;
import com.bosch.jazz.automation.web.common.ExpectedConditionsCustom;
import com.bosch.jazz.automation.web.common.WebDriverCustom;
import com.bosch.psec.web.test.engine.CommonFinderEngine;

import finder.button.JazzButtonFinder;


/**
 * Most basic page class that only holds an instance of {@link WebDriverCustom}.
 */
public abstract class AbstractWebPage {

  /**
   * PS-EC Finder engine
   */
  protected CommonFinderEngine engine;

  /**
   * Used for low level interacting with the browser
   */
  protected final WebDriverCustom driverCustom;


  /**
   * Constructor setting the {@link WebDriverCustom}
   *
   * @param driverCustom must not be null
   */
  public AbstractWebPage(final WebDriverCustom driverCustom) {
    this.driverCustom = driverCustom;
    this.engine = new CommonFinderEngine(driverCustom.getWebDriver());
    this.engine.addFinder(new JazzButtonFinder());
  }

  /**
   * Waits at most 5 seconds for an alert popup dialog to be present. Then "accepts" the dialog and returns the alert
   * text. If the popup is not visible after 5 seconds an Exception will be thrown.
   *
   * @return the alert text
   */
  public String acceptAlertAndReturnAlertText() {
    return this.driverCustom.acceptAlertAndReturnAlertText();
  }

  /**
   * Is called when some failure is identified and therefore an {@link IllegalStateException} shall be thrown
   *
   * @param message the failure message
   * @throws IllegalStateException whenever this method is called. The given message is given to
   *           {@link IllegalStateException#IllegalStateException(String)}
   */
  public void fail(final String message) {
    throw new IllegalStateException(message);
  }

  /**
   * Waits until the given element becomes stale according to Selenium. As a consequence the page holding the stale
   * element has be replaced by a new page.<br>
   * Elements are getting stale if a page is reloaded, a new page is loaded or the page dom (the given element in the
   * dom) is altered through some java script.
   *
   * @param elementOnOldPage the element on the page that must become stale before this method returns true
   * @throws TimeoutException if the timeout expired and the given element is still not stale
   */
  public void waitForPageLoaded(final WebElement elementOnOldPage) {
    this.driverCustom.waitForPageLoaded(elementOnOldPage);
  }

  /**
   * unimplemented abstract method for waitForPageLoaded.
   */
  public abstract void waitForPageLoaded();

  /**
   * This method is for the command Executor to call the static method ExpectedConditionsCustom.handleAlert.
   */
  public void handleAlert() {
    ExpectedConditionsCustom.handleAlert(this.driverCustom.getWebDriver(), Duration.ofSeconds(5));
  }

  /**
   * <p>
   * Open a new tab in existing window and navigate to the URL
   * <p>
   *
   * @author NVV1HC
   * @param URL url to navigate to
   * @throws AWTException Exception
   */
  public void openNewTabWithURL(final String URL) throws AWTException {
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    String windowHandle = this.driverCustom.getWebDriver().getWindowHandle();
    WebDriver driver = this.driverCustom.getWebDriver();
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_T);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_T);
    this.driverCustom.waitForSecs(Duration.ofSeconds(5));
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    for (int i = 0; i < tabs.size(); i++) {
      try {
        if (tabs.get(i).equals(windowHandle)) {
          driver.switchTo().window(tabs.get(i + 1));
        }
      }
      catch (Exception e) {
        LOGGER.LOG.error("Can not switch to new window with index as " + i);
      }
    }
    driver.navigate().to(URL);
    this.driverCustom.waitForSecs(Duration.ofSeconds(10));
  }

  /**
   * <p>
   * This method is to calculate the total of two numbers
   * <p>
   * 
   * @author NVV1HC
   * @param number1 number 1
   * @param number2 number 2
   * @return total of two number
   */
  public int addTwoNumbers(final String number1, final String number2) {
    return Integer.parseInt(number1) + Integer.parseInt(number2);
  }
}