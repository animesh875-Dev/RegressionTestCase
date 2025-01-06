/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.tests.web.junitrules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.bosch.jazz.automation.web.reporter.Reporter;


/**
 * Rule used to notify {@link Reporter} of a starting and ending test case.
 */
public class ReportStartAndEndTestRule extends TestWatcher {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void starting(final Description description) {
    Reporter.startTest(description.getTestClass().getSimpleName() + " - " + description.getMethodName(), "");
  }
}
