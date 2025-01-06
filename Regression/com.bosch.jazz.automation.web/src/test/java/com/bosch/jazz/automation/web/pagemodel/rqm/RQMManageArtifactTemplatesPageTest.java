/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This Page contains RQM Manage Artifact template Page related data.
 */
public class RQMManageArtifactTemplatesPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testClickOnPropertiesTab() {
    loadPage("rqm/select_selected_section.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.selectSelectedSection("Copy Template", "Detailed Description");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSelectValueFromCreateTemplate() {
    loadPage("rqm/select_value_from_create_template.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.selectValueFromCreateTemplate("Create Test Plan Template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSetRQMArtifactTemplateName() {
    loadPage("rqm/set_rqmartifact_template_name.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.setRQMArtifactTemplateName("Create Test Plan Template", "Create Test Plan Template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSetRQMArtifactTemplateDescription() {
    loadPage("rqm/set_rqmartifact_template_name.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.setRQMArtifactTemplateDescription("Create Test Plan Template", "Create Test Plan Template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSelectAvaiableSectionsValue() {
    loadPage("rqm/set_rqmartifact_template_name.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.selectAvaiableSectionsValue("Create Test Plan Template", "Attachments");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSectionName() {
    loadPage("rqm/section_name.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.sectionName("Create Test Plan Template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSetSectionDescription() {
    loadPage("rqm/section_name.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.setSectionDescription("Create Test Plan Template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSetSectionDetails() {
    loadPage("rqm/section_name.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.setSectionDetails("Create Test Plan Template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testVerifyTemplate() {
    loadPage("rqm/select_value_from_create_template.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.verifyTemplate("Basic test suite template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testVerifySectionTemplate() {
    loadPage("rqm/select_value_from_create_template.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.verifySectionTemplate("Basic test suite template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testSelectTemplateDropdownValue() {
    loadPage("rqm/select_template_dropdown_value.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.selectTemplateDropdownValue("Basic test suite template", "Copy Template");
  }

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testVerifyDefaultColumn() {
    loadPage("rqm/select_template_dropdown_value.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.verifyDefaultColumn("Basic test suite template");
  }

  /**
   * Load Browse webpage for the artifact templates and search for the artifact templates in filter text box.
   */
  @Test
  public void testSearchRqmArtifactsInFilterTextBox() {
    loadPage("rqm/search_artifact_templates_filters_textbox.html");
    RQMManageArtifactTemplatesPage rqm = getJazzPageFactory().getRQMManageArtifactTemplates();
    assertNotNull(rqm);
    rqm.searchRqmArtifactsInFilterTextBox("Default keyword template");
  }
}
