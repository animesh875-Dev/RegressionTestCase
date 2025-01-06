/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;


/**
*
*
*/
@SuppressWarnings("javadoc")
public final class RQMMainMenusEnum {

  public enum RQMMainMenus {
                            PROJECT_DASHBOARDS("Project Dashboards"),
                            REQUIREMENTS("Requirements"),
                            PLANNING("Planning"),
                            CONSTRUCTION("Construction"),
                            LAB_MANAGEMENT("Lab Management"),
                            BUILDS("Builds"),
                            EXECUTION("Execution"),
                            REPORTS("Reports"),
                            CHANGE_REQUESTS("Change Requests");

    private final String name;

    private RQMMainMenus(final String s) {
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


  public enum RQMSectionMenus {
                               ATTACHMENTS("Attachments"),
                               BUSINESS_OBJECTIVES("Business Objectives"),
                               DEVELOPMENT_ITEMS("Development Items"),
                               DEVELOPMENT_PLAN_LINKS("Development Plan Links"),
                               DETAILED_DESCRIPTION("Detailed Description"),
                               ENTRY_CRITERIA("Entry Criteria"),
                               EXIT_CRITERIA("Exit Criteria"),
                               EXECUTION_VARIABLES("Execution Variables"),
                               EXPECTED_RESULTS("Expected Results"),
                               FORMAL_REVIEW("Formal Review"),
                               HISTORY("History"),
                               MANAGE_SECTIONS("Manage Sections"),
                               MANUAL_STEPS("Manual Steps"),
                               MODIFICATION_HISTORY("Modification History"),
                               NORMATIVE_AND_INFORMATIVE_DOCUMENTS("Normative and Informative Documents"),
                               NOTES("Notes"),
                               POST_CONDITION("Post-Condition"),
                               PRE_CONDITION("Pre-Condition"),
                               QULAITY_OBJECTIVES("Quality Objectives"),
                               REQUIREMENTS("Requirements"),
                               REQUIREMENT_COLLECTION_LINKS("Requirement Collection Links"),
                               REQUIREMENT_LINKS("Requirement Links"),
                               RESOURCES("Resources"),
                               RISK_ASSESSMENT("Risk Assessment"),
                               SNAPSHOTS("Snapshots"),
                               SUMMARY("Summary"),
                               TEST_BED("Test Bed"),
                               TEST_CASES("Test Cases"),
                               TEST_CASE_DESIGN("Test Case Design"),
                               TEST_CASE_EXECUTION_RECORDS("Test Case Execution Records"),
                               TEST_DESCRIPTION("Test Description"),
                               TEST_ENVIRONMENTS("Test Environments"),
                               TEST_ESTIMATION("Test Estimation"),
                               TEST_OBJECTIVES("Test Objectives"),
                               TEST_PLAN_SCOPE("Test Plan Scope"),
                               TEST_SCHEDULES("Test Schedules"),
                               TEST_SCRIPTS("Test Scripts"),
                               TEST_STRATEGY("Test Strategy"),
                               TEST_SUITES("Test Suites"),
                               TEST_TEAM("Test Team"),
                               TEST_SUITE_DESIGN("Test Suite Design"),
                               TEST_SUITE_EXECUTION_RECORDS("Test Suite Execution Records"),
                               TEST_SUITE_RESULTS("Test Suite Results"),
                               DEFECTS("Defects");

    private final String name;

    private RQMSectionMenus(final String s) {
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

  public enum TestProperties {
                              CREATE_OR_EXISTING("Create or existing"),
                              CHOOSE_EXISTING("Choose Existing"),
                              CREATE_NEW("Create New"),
                              ID("ID"),
                              TEST_CASE_NAME("Test case name");

    private final String name;

    private TestProperties(final String s) {
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
