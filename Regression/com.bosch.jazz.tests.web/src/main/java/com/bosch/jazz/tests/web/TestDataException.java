package com.bosch.jazz.tests.web;

/**
 * Exception to be used if something with the test data is not valid.
 */
public class TestDataException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Calls the super constructor with the provided parameters
   *
   * @param message the message
   * @param e the exception
   */
  public TestDataException(final String message, final Throwable e) {
    super(message, e);
  }

}
