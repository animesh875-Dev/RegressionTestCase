/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * Unit tests coverage for the RQMBuildsPage.
 */
public class RQMBuildsPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads a requirement management builds page and set build record name.
   */
  @Test
  public void testSetRecordNameInBuildRecord() {
    loadPage("rqm/set_build_record_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.setRecordNameInBuildRecord("Build Record For Automation Testing");
  }

  /**
   * Loads a requirement management builds page and set build record name as null and validating the exception message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetRecordNameInBuildRecordTwo() {
    loadPage("rqm/set_build_record_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    String buildRecordName = null;
    builds.setRecordNameInBuildRecord(buildRecordName);
  }

  /**
   * Loads a requirement management builds page and set build record name and get build record name.
   */
  @Test
  public void testGetRecordNameInBuildRecord() {
    loadPage("rqm/set_description_build_record.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    getJazzPageFactory().getRQMBuildsPage().setRecordNameInBuildRecord("test_");
    System.out.println(getJazzPageFactory().getRQMBuildsPage().getRecordNameInBuildRecord());
    Assert.assertTrue(getJazzPageFactory().getRQMBuildsPage().getRecordNameInBuildRecord().contains("test_"));
  }

  /**
   * Loads a requirement management builds page and select the status for build records.
   */
  @Test
  public void testSelectStatus() {
    loadPage("rqm/set_build_record_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.selectStatus("In Progress");
  }

  /**
   * Loads a requirement management builds page and select the state for build records.
   */
  @Test
  public void testSelectState() {
    loadPage("rqm/set_build_record_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.selectState("Not Started");
  }

  /**
   * Loads a requirement management builds page and set description for build records.
   */
  @Test
  public void testSetDescription() {
    loadPage("rqm/set_description_build_record.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    builds.setDescription("Build Record Description");
  }

  /**
   * Loads a requirement management builds page and verify status of the build record.
   */
  @Test
  public void testIsStatusAdded() {
    loadPage("rqm/set_build_record_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    Assert.assertTrue(builds.isStatusAdded("Info"));
  }

  /**
   * Loads a requirement management builds page and verify state of the build record.
   */
  @Test
  public void testIsStateAdded() {
    loadPage("rqm/set_build_record_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    Assert.assertTrue(builds.isStateAdded("In Progress"));
  }

  /**
   * Loads a requirement management builds page and select the searched build record.
   */
  @Test
  public void testSelectBuildRecord() {
    loadPage("rqm/select_browse_build_record.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, null);
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    builds.selectBuildRecord("build test automation");
  }

  /**
   * Loads a requirement management builds page and click on build record button.
   */
  @Test
  public void testClickOnDeleteBuildRecordButton() {
    loadPage("rqm/select_browse_build_record.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.clickOnDeleteBuildRecordButton();
  }

  /**
   * Loads a requirement management builds page and verify build record is deleted.
   */
  @Test
  public void testIsSuccessMessageDisplayed() {
    loadPage("rqm/verify_deleted_build_record.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.isSuccessMessageDisplayed("1 of 1 build records were deleted.");
  }

  /**
   * Loads a requirement management builds page and set build definition name.
   */
  @Test
  public void testSetDefinitionNameInBuildDefinition() {
    loadPage("rqm/set_build_definition_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.setBuildDefinitionName("Build Definition For Automation Testing");
  }

  /**
   * Loads a requirement management builds page and set build definition name as null and validating the exception
   * message.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetDefinitionNameInBuildDefinitionTwo() {
    loadPage("rqm/set_build_definition_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    String buildDefinitionName = null;
    builds.setBuildDefinitionName(buildDefinitionName);
  }

  /**
   * Loads a requirement management builds page and set build record name and get build definition name.
   */
  @Test
  public void testGetDefinitionNameInBuildDefinition() {
    loadPage("rqm/set_build_definition_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    getJazzPageFactory().getRQMBuildsPage().setBuildDefinitionName("test_");
    System.out.println(getJazzPageFactory().getRQMBuildsPage().getBuildDefinitionName());
    Assert.assertTrue(getJazzPageFactory().getRQMBuildsPage().getBuildDefinitionName().contains("test_"));
  }

  /**
   * Loads a requirement management builds page and click on 'Add Build Record' button.
   */
  @Test
  public void testClickOnAddBuildRecord() {
    loadPage("rqm/set_build_definition_name.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.clickOnAddBuildRecord();
  }

  /**
   * Loads a requirement management builds page and add build record.
   */
  @Test
  public void testAddBuildRecord() {
    loadPage("rqm/add_build_record_in_build_definition.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.addBuildRecord("Build Record For Automation Testing");
  }

  /**
   * Loads a requirement management builds page and verify build record is added.
   */
  @Test
  public void testIsBuildRecordDisplayed() {
    loadPage("rqm/add_build_record_in_build_definition.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    Assert.assertTrue(builds.isBuildRecordDisplayed("Build Record For Automation Testing"));
  }

  /**
   * Loads a requirement management builds page and click on delete build defintion button.
   */
  @Test
  public void testClickOnDeleteBuildDefinitionButton() {
    loadPage("rqm/delete_build_definition.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.clickOnDeleteBuildDefinitionButton();
  }

  /**
   * Loads a requirement management builds page and click on build record button.
   */
  @Test
  public void testDeleteBuildRecord() {
    loadPage("rqm/delete_build_record_conform.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    builds.deleteBuildRecord();
  }
  /**
   * Loads a requirement management builds page and verify build record is added.
   */
  @Test
  public void testIsBuildRecorsdDisplayed() {
    loadPage("rqm/add_build_record_in_build_definition.html");
    RQMBuildsPage builds = getJazzPageFactory().getRQMBuildsPage();
    assertNotNull(builds);
    Assert.assertFalse(builds.isBuildRecordDisplayed("Rest API"));
  }
}