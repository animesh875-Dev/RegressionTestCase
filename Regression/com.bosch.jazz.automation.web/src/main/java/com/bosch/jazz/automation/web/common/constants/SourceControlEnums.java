/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;


/**
 * @author hrt5kor
 */
@SuppressWarnings("javadoc")
public enum SourceControlEnums {
                                STREAMS("Streams"),
                                WORKSPACES("Workspaces"),
                                CONFIGURATION("Configuration"),
                                CONFIG_NAME("ConfigName"),
                                CONFIG_TYPE("ConfigType"),

                                COMPONENTS("Components"),
                                SNAPSHOTS("Snapshots"),
                                BASELINES("Baselines"),
                                CHANGE_SETS("Change Sets"),
                                LOCKS("Locks"),
                                FILES("Files"),;

  private final String name;

  private SourceControlEnums(final String s) {
    this.name = s;
  }

  public boolean equalsName(final String otherName) {
    return this.name.equals(otherName);
  }

  public static String from(final String name) {
    for (SourceControlEnums enm : SourceControlEnums.values()) {
      if (enm.toString().equals(name)) {
        return name;
      }
    }
    throw new IllegalArgumentException("Invalid/unsupported name given: " + name);
  }

  @Override
  public String toString() {
    return this.name;
  }

}
