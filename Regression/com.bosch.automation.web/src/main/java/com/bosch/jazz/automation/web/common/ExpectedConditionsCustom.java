/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 **/
package com.bosch.jazz.automation.web.common;

import java.time.Duration;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Util class holding some custom conditions that are not available in {@link ExpectedConditions}.
 */
public class ExpectedConditionsCustom {

  private ExpectedConditionsCustom() {
    // util class therefore private constructor
  }

  /**
   * An expectation for checking that the title contains not the given case-sensitive string.
   *
   * @param textNotInTile the text that must not be in the title of the current browser so that the condition is true,
   *          must not be null
   * @return the title not having the text any more or null if the title still has it or there the currently identified
   *         title is null (according to selenium)
   */
  public static ExpectedCondition<String> titleNotEmptyAndContainsNot(final String textNotInTile) {
    return new ExpectedCondition<String>() {

      private String currentTitle = "";

      @Override
      public String apply(final WebDriver driver) {
        this.currentTitle = driver.getTitle();
        if ((this.currentTitle != null) && (this.currentTitle.length() > 0) &&
            !this.currentTitle.contains(textNotInTile)) {
          return this.currentTitle;
        }
        return null;
      }

      @Override
      public String toString() {
        return String.format("Title to not contain \"%s\". Current title: \"%s\"", textNotInTile, this.currentTitle);
      }
    };
  }

  /**
   * handles unexpected behaviour thrown in the browser.
   *
   * @param driver {@link WebDriver}
   * @param timeout searches for the unexpected alert untill timeout.
   */
  public static void handleAlert(final WebDriver driver, final Duration timeout) {
    try {
      WebDriverWait tempWait = new WebDriverWait(driver, timeout);
      tempWait.until(ExpectedConditions.alertIsPresent());
    }
    catch (Exception e) {
      return;
    }

    boolean boolAlert = false;
    int attempts = 0;
    while (!boolAlert && (attempts < 1000)) {
      try {
        driver.switchTo().alert().accept();
        boolAlert = true;
      }
      catch (org.openqa.selenium.UnhandledAlertException e) {
        driver.switchTo().alert().accept();
        boolAlert = true;
      }
      attempts++;
    }
  }
  
  /**
   * handles unexpected behaviour thrown in the browser.
   *
   * @param driver {@link WebDriver}
   * @param timeout searches for the unexpected alert untill timeout.
   * @return the boolean condition alert is present or not
   */
  public static boolean isAlertPresent(final WebDriver driver, final Duration timeout) {
    try {
      WebDriverWait tempWait = new WebDriverWait(driver, timeout);
      tempWait.until(ExpectedConditions.alertIsPresent());
    }
    catch (Exception e) {
    }
    boolean boolAlert = false ;
    int attempts = 0;
    while (!boolAlert && (attempts < 1000)) {
      try{
        driver.switchTo().alert();
        boolAlert =true;
        }catch(NoAlertPresentException ex){
          boolAlert =false;
        }
      attempts++;
    }
    return boolAlert;
  }
  
  
  /**
   * @return true if the login page disappeared due to successfull login, false if the login page is still visible.
   * @throws WebAutomationException in case the login failed due to invalid authentication
   */
  public static ExpectedCondition<Boolean> loginPageDisappeared() {
    return new ExpectedCondition<Boolean>() {

      @Override
      public Boolean apply(final WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("auth/authfailed")) {
          throw new WebAutomationException("Login failed due to invalid credentials!");
        }
        return !currentUrl.contains("/auth");
      }
    };
  }
}
