/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openqa.selenium.remote.Response;

/**
 * Perform a mockito answer for RemoteWebDriver execute call
 *
 * @author taa6si
 */
public class LoadNewPageOnNthAnswer implements Answer<Response> {

  int callCount;
  private final String resourcePrefix;
  private final Map<Integer, String> pageForCallNumber;

  /**
   * @param resourcePrefix prefix to where the resources are stored
   * @param pageForCallNumber Map mapping the call numbers to the page path relative to the resource prefix
   */
  public LoadNewPageOnNthAnswer(final String resourcePrefix, final Map<Integer, String> pageForCallNumber) {
    this.resourcePrefix = resourcePrefix;
    this.pageForCallNumber = pageForCallNumber;
  }

  @Override
  public Response answer(final InvocationOnMock invocation) throws Throwable {
    this.callCount++;
    if (this.pageForCallNumber.containsKey(this.callCount)) {
      String pagePath = this.pageForCallNumber.get(this.callCount);
      if (pagePath != null) {
        Path nextFile = Paths.get(this.resourcePrefix + pagePath);
        AbstractFrameworkUnitTest.driver.get(nextFile.toUri().toString());
      }
      return new Response();
    }
    return (Response) invocation.callRealMethod();
  }
}
