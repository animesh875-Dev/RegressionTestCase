/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;


/**
 * @author KDO1KOR
 */
public class ConfigurationManagementEnums {

  @SuppressWarnings("javadoc")
  public enum CurrentConfig {
                             CREATESTREAM("Create Stream..."),
                             CREATEBASELINE("Create Baseline..."),
                             CREATECHANGESET("Create Change Set..."),
                             DELIVERCHANGES("Deliver Changes..."),
                             ACCEPTCHANGES("Accept Changes..."),
                             COMPARECONFIG("Compare Configuration..."),
                             DELIVERCHANGESET("Deliver Change Set...");

    private final String name;

    private CurrentConfig(final String s) {
      this.name = s;
    }

    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }

  }

}
