package com.bosch.jazz.automation.web.common;

/**
 * Exception describing a problem when using the web client automation framework for e.g. loading property files,
 * finding elements, clicking a button, ...
 */
public class WebAutomationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  @SuppressWarnings("javadoc")
  public WebAutomationException(final String message, final Throwable e) {
    super(message, e);
  }

  @SuppressWarnings("javadoc")
  public WebAutomationException(final String message) {
    super(message);
  }

  @SuppressWarnings("javadoc")
  public WebAutomationException(final Exception e) {
    super(e);
  }
}
