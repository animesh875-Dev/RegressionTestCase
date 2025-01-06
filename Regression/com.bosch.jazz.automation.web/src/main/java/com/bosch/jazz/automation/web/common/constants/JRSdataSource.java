package com.bosch.jazz.automation.web.common.constants;

/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
@SuppressWarnings("javadoc")
public enum JRSdataSource {

                           DWH("Rational Data Warehouse"),
                           LQE("Lifecycle Query Engine"),
                           LQE_CONFIG("Lifecycle Query Engine using Configurations");

  private final String name;

  private JRSdataSource(String s) {
    name = s;
  }

  public boolean equalsName(String otherName) {
    return name.equals(otherName);
  }

  @Override
  public String toString() {
    return this.name;
  }

}
