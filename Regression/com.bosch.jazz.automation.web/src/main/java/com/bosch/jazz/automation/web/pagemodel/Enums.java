/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel;


@SuppressWarnings("javadoc")
public class Enums {

  public enum Day {
                   SUNDAY("Sunday"),
                   MONDAY("Monday"),
                   TUESDAY("Tuesday"),
                   WEDNESDAY("Wednesday"),
                   THURSDAY("Thursday"),
                   FRIDAY("Friday"),
                   SATURDAY("Saturday");

    private final String name;

    private Day(String s) {
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


  public enum widgetCategory {
                              ARCHITECTUREMANAGEMENT("am"),
                              CHANGEANDCONFIGURATIONMANAGEMENT("ccm"),
                              DATACOLLECTIONCOMPONENT("dcc"),
                              DESIGNMANAGEMENT("dm"),
                              GLOBALCONFIGURATIONMANAGEMENT("gc"),
                              LIFECYCLEQUERYENGINE("lqe"),
                              LINKINDEXPROVIDER("ldx"),
                              QUALITYMANAGEMENT("qm"),
                              REPORTBUILDER("rs"),
                              REQUIREMENTSMANAGEMENT("rm");

    private final String name;

    private widgetCategory(String s) {
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
}
