/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import com.bosch.jazz.automation.web.pagemodel.ccm.CCMProjectAreaDashboardPage;

/**
 * Defines all top level menus and sub menus in Change and configuration management application.
 */
public final class CCMMenusEnum {

  /**
   * <p>
   * Enumerations serve the purpose of representing a group of named constants.<br>
   * E.g {MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY} are all type of Day.
   * 
   * <p>Change and configuration management top level menu names can be grouped into menu type.
   * Enumerator of type CCMMenu stores all the names of the menus.<br>Values in these enumerators are used as input to  
   * {@link CCMProjectAreaDashboardPage#openSubMenu(String)} methods.
   *
   *
   */
  public enum CCMMenus {
                                /**
                                 * Project Dashboards
                                 */
                                PROJECT_DASHBOARDS("Project Dashboards"),
                                /**
                                 * Work Items
                                 */
                                WORK_ITEMS("Work Items"),
                                /**
                                 * Plans
                                 */
                                PLANS("Plans"),
                                /**
                                 * "Source Control"
                                 */
                                SOURCE_CONTROL("Source Control"),
                                /**
                                 * "Builds"
                                 */
                                BUILDS("Builds"),
                                /**
                                 * "Reports"
                                 */
                                REPORTS("Reports");

    private final String name;

    private CCMMenus(String s) {
      name = s;
    }

    @SuppressWarnings("javadoc")
    public boolean equalsName(String otherName) {
      return name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }


  }
  /**
   * <p>
   * Enumerations serve the purpose of representing a group of named constants.<br>
   * E.g {MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY} are all type of Day.
   * 
   * <p>Change and configuration management top level sub menu names can be grouped into sub menu type.
   * Enumerator of type CCMSubMenus stores all the names of the sub menus. <br>Values in these enumerators are used as input to  
   * methods.
   *  
   * 
   * @author hrt5kor
   */
  public enum CCMSubMenus {
          
                           /**
                           * Welcome to Work Items
                           */
                          WELCOME_TO_WORK_ITEMS("Welcome to Work Items"),
                           /**
                           * My Queries
                           */
                          MY_QUERIES("My Queries"),
                          /**
                           * Shared Queries
                           */
                          SHARED_QUERIES("Shared Queries"),
                           /**
                           * Create Query
                           */
                          CREATE_QUERY("Create Query"),
                           /**
                           * Task
                           */
                          TASK("Task"),
                           /**
                           * Story
                           */
                          STORY("Story"),
                           /**
                           * Epic
                           */
                          EPIC("Epic"),
                           /**
                           * Defect
                           */
                          DEFECT("Defect"),
                           /**
                           * Defect Fix
                           */
                          DEFECT_FIX("Defect Fix"),
                           /**
                           * Review
                           */
                          REVIEW("Review"),
                           /**
                           * Problem
                           */
                          PROBLEM("Problem"),
                           /**
                           * Meeting
                           */
                          MEETING("Meeting"),
                           /**
                           * Delivery
                           */
                          DELIVERY("Delivery"),
                           /**
                           * Release
                           */
                          RELEASE("Release"),
                           /**
                           * Issue
                           */
                          ISSUE("Issue"),
                           /**
                           * Change Request
                           */
                          CHANGE_REQUEST("Change Request"),
                           /**
                           * Need
                           */
                          NEED("Need"),
                           /**
                           * Milestone
                           */
                          MILESTONE("Milestone"),
                           /**
                           * Defect Eval
                           */
                          DEFECT_EVAL("Defect Eval"),
                           /**
                           * Relevant Stream
                           */
                          RELEVANT_STREAM("Relevant Stream"),
                           /**
                           * Adoption Item
                           */
                          ADOPTION_ITEM("Adoption Item"),
                           /**
                           * Impediment
                           */
                          IMPEDIMENT("Impediment"),
                           /**
                           * Track Build Item
                           */
                          TRACK_BUILD_ITEM("Track Build Item"),
                           /**
                           * Retrospective
                           */
                          RETROSPECTIVE("Retrospective"),
                           /**
                           * Create From Template...
                           */
                          CREATE_FROM_TEMPLATE("Create From Template..."),
                           /**
                           * Import From CSV File
                           */
                          IMPORT_FROM_CSV_FILE("Import From CSV File"),
                           /**
                           * Welcome to Plans
                           */
                          WELCOME_TO_PLANS("Welcome to Plans"),
                           /**
                           * Quick Planner
                           */
                          QUICK_PLANNER("Quick Planner"),
                           /**
                           * My Current Plans
                           */
                          MY_CURRENT_PLANS("My Current Plans"),
                           /**
                           * Current Plans
                           */
                          CURRENT_PLANS("Current Plans"),
                           /**
                           *All Plans 
                           */
                          ALL_PLANS("All Plans"),
                           /**
                           * Sprint Backlog
                           */
                          SPRINT_BACKLOG("Sprint Backlog"),
                           /**
                           * "Release Backlog"
                           */
                          RELEASE_BACKLOG("Release Backlog"),
                           /**
                           * Product Backlog
                           */
                          PRODUCT_BACKLOG("Product Backlog"),
                           /**
                           * Cross-Project Plan
                           */
                          CROSS_PROJECT_PLAN("Cross-Project Plan"),
                           /**
                           * Phase Plan
                           */
                          PHASE_PLAN("Phase Plan"),
                           /**
                           * Release Plan
                           */
                          RELEASE_PLAN("Release Plan"),

                           /**
                           * Welcome to Source Control
                           */
                          WELCOME_TO_SOURCE_CONTROL("Welcome to Source Control"),
                           /**
                           * Work with Git
                           */
                          WORK_WITH_GIT("Work with Git"),
                           /**
                           * Streams
                           */
                          STREAMS("Streams"),
                           /**
                           * Repository Workspaces
                           */
                          REPOSITORY_WORKSPACES("Repository Workspaces"),
                          /**
                           * Registered Git Repositories
                           */
                           REGISTERED_GIT_REPOSITORIES("Registered Git Repositories"),
                           /**
                           * Locks
                           */
                          LOCKS("Locks"),
                           /**
                           * Advanced Search
                           */
                          ADVANCED_SEARCH("Advanced Search"),
                           /**
                           * Git Requests
                           */
                          GIT_REQUESTS("Git Requests"),

                           /**
                           * Welcome to Reports
                           */
                          WELCOME_TO_REPORTS("Welcome to Reports"),
                           /**
                           * My Reports
                           */
                          MY_REPORTS("My Reports"),
                           /**
                           * Shared Reports
                           */
                          SHARED_REPORTS("Shared Reports"),
                           /**
                           * Report Resources
                           */
                          REPORT_RESOURCES("Report Resources"),
                           /**
                           * Running Reports
                           */
                          RUNNING_REPORTS("Running Reports"),
                           /**
                           * From Resource
                           */
                          FROM_RESOURCE("From Resource"),
                           /**
                           * Using Report Builder
                           */
                          USING_REPORT_BUILDER("Using Report Builder"),

                           /**
                           * Welcome to Builds
                           */
                          WELCOME_TO_BUILDS("Welcome to Builds"),
                           /**
                           * Build Definitions
                           */
                          BUILD_DEFINITIONS("Build Definitions"),
                           /**
                           * Build Queue
                           */
                          BUILD_QUEUE("Build Queue"),
                           /**
                           * Build Engines
                           */
                          BUILD_ENGINES("Build Engines");

    private final String name;

    private CCMSubMenus(String s) {
      name = s;
    }

    @SuppressWarnings("javadoc")
    public boolean equalsName(String otherName) {
      return name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }

  }

}