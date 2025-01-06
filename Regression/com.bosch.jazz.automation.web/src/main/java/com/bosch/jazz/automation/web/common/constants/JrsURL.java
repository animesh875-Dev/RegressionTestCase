package com.bosch.jazz.automation.web.common.constants;

/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
@SuppressWarnings("javadoc")
public enum JrsURL {

                    JRS_URL("https://rb-alm-02-d.de.bosch.com/rs"),
                    JRS_BUILD_REPORT_URL("https://rb-alm-02-d.de.bosch.com/rs/reports#build/new"),
                    JRS_ALL_REPORTS_URL("https://rb-alm-02-d.de.bosch.com/rs/reports#use"),
                    JRS_MY_REPORTS_URL("https://rb-alm-02-d.de.bosch.com/rs/reports#mine"),
                    CCM_WEB_URL("https://rb-alm-02-d.de.bosch.com/ccm/web"),
                    CCM_URL("https://rb-alm-02-d.de.bosch.com/ccm");


  private final String name;

  private JrsURL(final String s) {
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
