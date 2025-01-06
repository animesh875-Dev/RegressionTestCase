package com.bosch.jazz.tests.web.junitrules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Rule that stores which test class is currently running
 */
public class TestClass extends TestWatcher {

  private Class<?> testClass;

  @Override
  protected void starting(final Description d) {
    this.testClass = d.getTestClass();
  }

  /**
   * @return the name of the currently-running test class
   */
  public String getTestClassName() {
    return getTestClass().getSimpleName();
  }

  /**
   * @return the testClass that will be invoked next
   */
  public Class<?> getTestClass() {
    return this.testClass;
  }
}
