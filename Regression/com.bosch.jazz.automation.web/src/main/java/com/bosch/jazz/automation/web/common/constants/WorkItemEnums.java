/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import java.util.Map;

import com.bosch.jazz.automation.web.pagemodel.ccm.CCMProjectAreaDashboardPage;
import com.bosch.jazz.automation.web.pagemodel.ccm.CCMWorkItemEditorPage;

/**
 * Defines all Work items types, attributes, link types, tabs and etc names in Change and configuration management
 * application.
 */
public final class WorkItemEnums {


  /**
   * <p>
   * Enumerations serve the purpose of representing a group of named constants.<br>
   * E.g {MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY} are all type of Day.
   * <p>
   * Change and configuration management work item types can be grouped into work item type. Enumerator of type
   * WorkItemType stores all the names of the Work item types e.g Task, Defect,Story and etc..<br>
   * Values in these enumerators are used as input to
   * {@link CCMProjectAreaDashboardPage#selectWorkItemFromCreateWorkitemDialogToCreate(String)} methods.
   */
  public enum WorkItemType {
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
                            RETROSPECTIVE("Retrospective");


    private final String name;

    private WorkItemType(final String s) {
      this.name = s;
    }
    @SuppressWarnings("javadoc")
    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }
  }


  /**
   * <p> Enumerations serve the purpose of representing a group of named constants.<br> E.g
   * {MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY} are all type of Day. <p>Change and configuration management work items
   * link sections can be grouped into work item link sections. Enumerator of type WorkItemLinkSection stores all the
   * names of the Work item link sections.<br>Values in these enumerators are used as input to {@link
   * CCMWorkItemEditorPage#addLinkToExistingObject(Map)}
   */
  public enum WorkItemLinkSection {


                                   /**
                                    * Process Links
                                    */
                                   PROCESS_LINKS("Process Links"),
                                   /**
                                    * Links
                                    */
                                   LINKS("Links");

    private final String name;

    private WorkItemLinkSection(final String s) {
      this.name = s;
    }
    @SuppressWarnings("javadoc")
    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }
  }


  /**
   * <p> Enumerations serve the purpose of representing a group of named constants.<br> E.g
   * {MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY} are all type of Day. <p>Change and configuration management work items
   * link types which are there under work item link sections can be grouped into work item link types sections.
   * Enumerator of type WorkItemLinkTypes stores all the names of the Work item links sections.<br>Values in these
   * enumerators are used as input to {@link CCMWorkItemEditorPage#addLinkToExistingObject(Map)}
   */
  public enum WorkItemLinkTypes {


                                 /**
                                  * Children links
                                  */
                                 CHILDREN_LINKS("Children links"),
                                 /**
                                  * Contributes To links
                                  */
                                 CONTRIBUTES_TO_LINKS("Contributes To links"),
                                 /**
                                  * Parent links
                                  */
                                 PARENT_LINKS("Parent links"),
                                 /**
                                  * Related links
                                  */
                                 RELATED_LINKS("Related links"),
                                 /**
                                  * Tracks links
                                  */
                                 TRACKS_LINKS("Tracks links"),
                                 /**
                                  * Implements Requirement
                                  */
                                 IMPLEMENTS_REQUIREMENT("Implements Requirement");

    private final String name;

    private WorkItemLinkTypes(final String s) {
      this.name = s;
    }

    /**
     * @param otherName
     * @return
     */
    @SuppressWarnings("javadoc")
    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }
  }


  /**
   * <p> Enumerations serve the purpose of representing a group of named constants.<br> E.g
   * {MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY} are all type of Day. <p>Change and configuration management work items
   * link types which are there under work item link sections can be grouped into work item link types sections.
   * Enumerator of type WorkItemLinkTypes stores all the names of the Work item links types.<br>Values in these
   * enumerators are used as input to {@link CCMWorkItemEditorPage#addLinkToExistingObject(Map)}
   */
  public enum WorkItemLinkActions {

                                   /**
                                    * Add Predecessor
                                    */
                                   ADD_PREDECESSOR("Add Predecessor"),
                                   /**
                                    * Add Successor
                                    */
                                   ADD_SUCCESSOR("Add Successor"),
                                   /**
                                    * Set Parent
                                    */
                                   SET_PARENT("Set Parent"),
                                   /**
                                    * Add Related
                                    */
                                   ADD_RELATED("Add Related"),
                                   /**
                                    * Add Resolves
                                    */
                                   ADD_RESOLVES("Add Resolves"),
                                   /**
                                    * Add Resolved By
                                    */
                                   ADD_RESOLVED_BY("Add Resolved By"),
                                   /**
                                    * Add Children
                                    */
                                   ADD_CHILDREN("Add Children"),
                                   /**
                                    * Add Duplicated By
                                    */
                                   ADD_DUPLICATED_BY("Add Duplicated By"),
                                   /**
                                    * Set Duplicate Of
                                    */
                                   SET_DUPLICATE_OF("Set Duplicate Of"),
                                   /**
                                    * Add Blocks
                                    */
                                   ADD_BLOCKS("Add Blocks"),
                                   /**
                                    * Add Depends On
                                    */
                                   ADD_DEPENDS_ON("Add Depends On"),
                                   /**
                                    * Add Affects Plan Item
                                    */
                                   ADD_AFFECTS_PLAN_ITEM("Add Affects Plan Item"),
                                   /**
                                    * Add Contributes To
                                    */
                                   ADD_CONTRIBUTES_TO("Add Contributes To"),
                                   /**
                                    * Add Tracks
                                    */
                                   ADD_TRACKS("Add Tracks"),
                                   /**
                                    * Add Affected by Defect
                                    */
                                   ADD_AFFECTED_BY_DEFECT("Add Affected by Defect"),
                                   /**
                                    * Add Related Artifacts
                                    */
                                   ADD_RELATED_ARTIFACTS("Add Related Artifacts"),
                                   /**
                                    * Add SVN Revisions
                                    */
                                   ADD_SVN_REVISIONS("Add SVN Revisions"),
                                   /**
                                    * Add Related Test Execution Record
                                    */
                                   ADD_RELATED_TEST_EXECUTION_RECORD("Add Related Test Execution Record"),
                                   /**
                                    * Add Related Test Script
                                    */
                                   ADD_RELATED_TEST_SCRIPT("Add Related Test Script"),
                                   /**
                                    * Add Related Test Plan
                                    */
                                   ADD_RELATED_TEST_PLAN("Add Related Test Plan"),
                                   /**
                                    * Add Related Test Case
                                    */
                                   ADD_RELATED_TEST_CASE("Add Related Test Case"),
                                   /**
                                    * Add Tracks Requirement
                                    */
                                   ADD_TRACKS_REQUIREMENT("Add Tracks Requirement"),
                                   /**
                                    * Add Affects Test Case Result
                                    */
                                   ADD_AFFECTS_TEST_CASE_RESULT("Add Affects Test Case Result"),
                                   /**
                                    * Add Tested By Test Case
                                    */
                                   ADD_TESTED_BY_TEST_CASE("Add Tested By Test Case"),
                                   /**
                                    * Add Blocks Test Execution
                                    */
                                   ADD_BLOCKS_TEST_EXECUTION("Add Blocks Test Execution"),
                                   /**
                                    * Add Affects Requirement
                                    */
                                   ADD_AFFECTS_REQUIREMENT("Add Affects Requirement"),
                                   /**
                                    * Add Implements Requirement
                                    */
                                   ADD_IMPLEMENTS_REQUIREMENT("Add Implements Requirement");

    private final String name;

    private WorkItemLinkActions(final String s) {
      this.name = s;
    }

    @SuppressWarnings("javadoc")
    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
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
   * <p>
   * Change and configuration management work items attributes such as Summary, Filed against, owned by and etc can be
   * grouped into work item attribute. Enumerator of type WorkItemAttribute stores all the names of the Work item
   * attributes.<br>
   * Values in these enumerators are used as input to
   * {@link CCMWorkItemEditorPage#setAttributeValueInTextBox(String, String)},
   * {@link CCMWorkItemEditorPage#setAttributeValueInTextArea(String, String)}, <br>
   * {@link CCMWorkItemEditorPage#setDropDownAttributeValue(String, String)}
   * {@link CCMWorkItemEditorPage#setConstraintDate(String, String)} and etc.
   */
  public enum WorkItemAttribute {

                                 /**
                                  * Summary
                                  */
                                 SUMMARY("Summary"),
                                 /**
                                  * Type
                                  */
                                 TYPE("Type"),
                                 /**
                                  * Task Type
                                  */
                                 TASK_TYPE("Task Type"),
                                 /**
                                  * Priority
                                  */
                                 PRIORITY("Priority"),
                                 /**
                                  * Project Area
                                  */
                                 PROJECT_AREA("Project Area"),
                                 /**
                                  * Team Area
                                  */
                                 TEAM_AREA("Team Area"),
                                 /**
                                  * Filed Against
                                  */
                                 FILED_AGAINST("Filed Against"),
                                 /**
                                  * Created By
                                  */
                                 CREATED_BY("Created By"),
                                 /**
                                  * Creation Date
                                  */
                                 CREATION_DATE("Creation Date"),
                                 /**
                                  * Corrected Estimate
                                  */
                                 CORRECTION("Corrected Estimate"),
                                 /**
                                  * Tags
                                  */
                                 TAGS("Tags"),
                                 /**
                                  * Owned By
                                  */
                                 OWNED_BY("Owned By"),
                                 /**
                                  * Target Configuration
                                  */
                                 TARGET_CONFIGURATION("Target Configuration"),
                                 /**
                                  * Planned For
                                  */
                                 PLANNED_FOR("Planned For"),
                                 /**
                                  * Due Date
                                  */
                                 DUE_DATE("Due Date"),
                                 /**
                                  * estimate
                                  */
                                 ESTIMATE("estimate"),
                                 /**
                                  * Time Spent
                                  */
                                 TIME_SPENT("Time Spent"),
                                 /**
                                  * Workflow Graph
                                  */
                                 WORKFLOW_GRAPH("Workflow Graph"),
                                 /**
                                  * Description
                                  */
                                 DESCRIPTION("Description"),
                                 /**
                                  * Comments
                                  */
                                 DISCUSSION("Comments"),
                                 /**
                                  * Additional Information in HTML
                                  */
                                 ADDITIONAL_INFORMATION_IN_HTML("Additional Information in HTML"),
                                 /**
                                  * Additional Information in TEXT
                                  */
                                 ADDITIONAL_INFORMATION_IN_TEXT("Additional Information in TEXT"),
                                 /**
                                  * Unit Test
                                  */
                                 UNIT_TEST("Unit Test"),
                                 /**
                                  * Detailed Design
                                  */
                                 DETAILED_DESIGN("Detailed Design"),
                                 /**
                                  * Requirement
                                  */
                                 REQUIREMENT("Requirement"),
                                 /**
                                  * Story Points
                                  */
                                 STORY_POINTS("Story Points"),
                                 /**
                                  * Progress
                                  */
                                 PROGRESS("Progress"),
                                 /**
                                  * Planned Estimate (hrs)
                                  */
                                 PLANNED_ESTIMATE_HRS("Planned Estimate (hrs)"),
                                 /**
                                  * Residual Estimate (hrs)
                                  */
                                 RESIDUAL_ESTIMATE_HRS("Residual Estimate (hrs)"),
                                 /**
                                  * Deviation (hrs)
                                  */
                                 DEVIATION_HRS("Deviation (hrs)"),
                                 /**
                                  * Estimation Sum (hrs)
                                  */
                                 ESTIMATION_SUM_HRS("Estimation Sum (hrs)"),
                                 /**
                                  * PM Interface Element ID
                                  */
                                 PM_INTERFACE_ELEMENT_ID("PM Interface Element ID"),
                                 /**
                                  * Interface Project Status
                                  */
                                 PM_INTERFACE_PROJECT_STATUS("Interface Project Status"),
                                 /**
                                  * PM Interface Change Log
                                  */
                                 PM_INTERFACE_CHANGE_LOG("PM Interface Change Log"),
                                 /**
                                  * PM Interface Resource Group Assignment
                                  */
                                 PM_INTERFACE_RESOURCE_GROUP_ASSIGNMENT("PM Interface Resource Group Assignment"),
                                 /**
                                  * Execution Progress
                                  */
                                 EXECUTION_PROGRESS("Execution Progress"),
                                 /**
                                  * Acceptance Test
                                  */
                                 ACCEPTANCE_TEST("Acceptance Test"),
                                 /**
                                  * Engineering Level
                                  */
                                 ENGINEERING_LEVEL("Engineering Level"),
                                 /**
                                  * Severity
                                  */
                                 SEVERITY("Severity"),
                                 /**
                                  * Detected In
                                  */
                                 DETECTED_IN("Detected In"),
                                 /**
                                  * Found In
                                  */
                                 FOUND_IN("Found In"),
                                 /**
                                  * Detection Phase
                                  */
                                 DETECTION_PHASE("Detection Phase"),
                                 /**
                                  * Detection Method
                                  */
                                 DETECTION_METHOD("Detection Method"),
                                 /**
                                  * Injection Phase
                                  */
                                 INJECTION_PHASE("Injection Phase"),
                                 /**
                                  * Injection Qualifier
                                  */
                                 INJECTION_QUALIFIER("Injection Qualifier"),
                                 /**
                                  * Removed from Related
                                  */
                                 REMOVED_FROM_RELATED("Removed from Related"),
                                 /**
                                  * Mitigation Actions
                                  */
                                 MITIGATION_ACTIONS("Mitigation Actions"),
                                 /**
                                  * Issuer Class
                                  */
                                 ISSUER_CLASS("Issuer Class"),
                                 /**
                                  * Issued By
                                  */
                                 ISSUED_BY("Issued By"),
                                 /**
                                  * External Id
                                  */
                                 EXTERNAL_ID("External Id"),
                                 /**
                                  * Date of Receipt
                                  */
                                 DATE_OF_RECEIPT("Date of Receipt"),
                                 /**
                                  * Responsible Class
                                  */
                                 RESPONSIBLE_CLASS("Responsible Class"),
                                 /**
                                  * Analysis Result
                                  */
                                 ANALYSIS_RESULT("Analysis Result"),
                                 /**
                                  * Meeting Type
                                  */
                                 MEETING_TYPE("Meeting Type"),
                                 /**
                                  * Internal Participants
                                  */
                                 INTERNAL_PARTICIPANTS("Internal Participants"),
                                 /**
                                  * External Participants
                                  */
                                 EXTERNAL_PARTICIPANTS("External Participants"),
                                 /**
                                  * Version
                                  */
                                 VERSION("Version"),
                                 /**
                                  * Maturity Level
                                  */
                                 MATURITY_LEVEL("Maturity Level"),
                                 /**
                                  * Intended Use
                                  */
                                 INTENDED_USE("Intended Use"),
                                 /**
                                  * Base Snapshot | Base Baseline URI
                                  */
                                 BASE_SNAPSHOT_BASE_BASELINE_URI("Base Snapshot | Base Baseline URI"),
                                 /**
                                  * Snapshot | Baseline URI
                                  */
                                 SNAPSHOT_BASELINE_URI("Snapshot | Baseline URI"),
                                 /**
                                  * Part Number
                                  */
                                 PART_NUMBER("Part Number"),
                                 /**
                                  * Contact Persons
                                  */
                                 CONTACT_PERSONS("Contact Persons"),
                                 /**
                                  * Evaluated Subjects
                                  */
                                 EVALUATED_SUBJECTS("Evaluated Subjects"),
                                 /**
                                  * State of automatic evaluation
                                  */
                                 STATE_OF_AUTOMATIC_EVALUATION("State of automatic evaluation"),
                                 /**
                                  * Evaluation result
                                  */
                                 EVALUATION_RESULT("Evaluation result"),
                                 /**
                                  * Stream URI
                                  */
                                 STREAM_URI("Stream URI"),
                                 /**
                                  * Impact
                                  */
                                 IMPACT("Impact"),
                                 /**
                                  * Affected Teams
                                  */
                                 AFFECTED_TEAMS("Affected Teams"),
                                 /**
                                  * Notes
                                  */
                                 NOTES("Notes"),
                                 /**
                                  * Planned Start Date
                                  */
                                 PLANNED_START_DATE("Planned Start Date"),
                                 /**
                                  * Planned End Date
                                  */
                                 PLANNED_END_DATE("Planned End Date"),
                                 /**
                                  * Constraint Type
                                  */
                                 CONSTRAINT_TYPE("Constraint Type"),
                                 /**
                                  * Constraint Date
                                  */
                                 CONSTRAINT_DATE("Constraint Date"),
                                 /**
                                  * Add Approval
                                  */
                                 ADD_APPROVAL("Add Approval"),
                                 /**
                                  * Add Review
                                  */
                                 ADD_REVIEW("Add Review"),
                                 /**
                                  * Add Verification
                                  */
                                 ADD_VERIFICATION("Add Verification"),
                                 /**
                                  * Approval
                                  */
                                 APPROVAL("Approval"),
                                 /**
                                  * Review
                                  */
                                 REVIEW("Review"),
                                 /**
                                  * Verification
                                  */
                                 VERIFICATION("Verification"),
                                 /**
                                  * Duration
                                  */
                                 DURATION("Duration");

    private final String name;

    private WorkItemAttribute(final String s) {
      this.name = s;
    }

    @SuppressWarnings("javadoc")
    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
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
   * <p>
   * Change and configuration management work items tabs such as Overview, Links, Approval and etc can be
   * grouped into work item tabs. Enumerator of type WorkItemTab stores all the names of the Work item
   * WorkItemTab.<br>
   * Values in these enumerators are used as input to {@link CCMWorkItemEditorPage#selectTab(String)}}
   * 
   */
  public enum WorkItemTab {

                           /**
                            * Overview
                            */
                           OVERVIEW("Overview"),
                           /**
                            * Links
                            */
                           LINKS("Links"),
                           /**
                            * Attachments
                            */
                           ATTACHMENTS("Attachments"),
                           /**
                            * Approvals
                            */
                           APPROVALS("Approvals"),
                           /**
                            * Misc
                            */
                           MISC("Misc"),
                           /**
                            * History
                            */
                           HISTORY("History"),
                           /**
                            * Time_Tracking
                            */
                           TIME_TRACKING("Time_Tracking"),
                           /**
                            * Validity
                            */
                           VALIDITY("Validity"),
                           /**
                            * Planning
                            */
                           PLANNING("Planning"),
                           /**
                            * Acceptance
                            */
                           ACCEPTANCE("Acceptance"),
                           /**
                            * Defect Flow
                            */
                           DEFECT_FLOW("Defect Flow"),
                           /**
                            * Errata
                            */
                           ERRATA("Errata"),
                           /**
                            * Errata Formula
                            */
                           ERRATA_FORMULA("Errata Formula"),
                           /**
                            * Requirement Traceability
                            */
                           REQUIREMENT_TRACEABILITY("Requirement Traceability"),
                           /**
                            * Analysis
                            */
                           ANALYSIS("Analysis");

    private final String name;

    private WorkItemTab(final String s) {
      this.name = s;
    }

    @SuppressWarnings("javadoc")
    public boolean equalsName(final String otherName) {
      return this.name.equals(otherName);
    }

    @Override
    public String toString() {
      return this.name;
    }
  }
}