/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import java.lang.reflect.Method;

/**
 * @author HCM6KOR POJO class for LookUpMethod.
 */
public class LookUpMethodValues {

  private Method method;
  private Object[] arguments;

  /**
   * @return the method
   */
  public Method getMethod() {
    return this.method;
  }

  /**
   * @param method the method to set
   */
  public void setMethod(final Method method) {
    this.method = method;
  }

  /**
   * @return the arguments
   */
  public Object[] getArguments() {
    return this.arguments;
  }

  /**
   * @param arguments the arguments to set
   */
  public void setArguments(final Object[] arguments) {
    this.arguments = arguments;
  }

}
