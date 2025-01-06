/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import static org.junit.Assert.assertNotNull;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit test for the JRSPage.
 *
 * @author gem5kor
 */
public class JRSPageTest extends AbstractFrameworkUnitTest {

  /**
   * The ExpectedException rule allows you to verify that your code throws a specific exception.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Loads the url and waits until the page is fully loaded
   */
  @Test
  public void openTest() {
    loadPage("jrs/open.html");
    JRSPage jrsPage = getJazzPageFactory().getJRSPage();
    assertNotNull(jrsPage);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/rs";
    jrsPage.open(repositoryURL, null, null);
  }

  /**
   * Constructs an UnsupportedOperationException with the specified detail message.
   */
  @Test
  public void getLoggedInUserTest() {
    JRSPage jrsPage = getJazzPageFactory().getJRSPage();
    assertNotNull(jrsPage);
    this.thrown.expect(UnsupportedOperationException.class);
    this.thrown.expectMessage(CoreMatchers.is("Adapt this method to JRS!"));
    jrsPage.getLoggedInUser();
  }
}
