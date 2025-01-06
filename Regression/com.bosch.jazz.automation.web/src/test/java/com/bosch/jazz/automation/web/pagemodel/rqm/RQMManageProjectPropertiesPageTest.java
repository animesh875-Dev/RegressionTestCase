/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.rqm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;

/**
 * This Page contains RQM Manage Project Properties Page related data.
 */
public class RQMManageProjectPropertiesPageTest extends AbstractFrameworkUnitTest {

  /**
   * Loads RQM Manage project properties page and click on Properties tab.
   */
  @Test
  public void testClickOnPropertiesTab() {
    loadPage("rqm/click_on_properties_section.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnPropertiesTab("Custom Attributes");
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testCliclOnPropertiesSection() {
    loadPage("rqm/click_on_properties_section.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnPropertiesSection("Keyword Categories");
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testClickOnJazzImageButtons() {
    loadPage("rqm/click_on_jazz_image_buttons.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.clickOnJazzImageButtons("Switch to full view");
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testCreateRootCategoryName() {
    loadPage("rqm/create_root_category_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.createRootCategoryName("Switch to full view");
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testSetCreateValueName() {
    loadPage("rqm/set_create_value_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.setCreateValueName("Switch to full view");
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testSelectOptionFromActionsMenu() {
    loadPage("rqm/select_option_from_actions_menu.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.selectOptionFromActionsMenu("Add Values", "test");
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testAddValues() {
    loadPage("rqm/add_values.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.addValues("Final");
  }

  /**
   * Loads RQM Manage Project Properties page and verify successfull message.
   */
  @Test
  public void testIsPropertiesSaved() {
    loadPage("rqm/is_keyword_categories_saved.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    assertTrue(rqm.isPropertiesSaved());
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testIsKeywordCategoriesVisible() {
    loadPage("rqm/is_keyword_categories_saved.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    assertTrue(rqm.isKeywordCategoriesVisible("Category for Automation testing"));
  }

  /**
   * Loads RQM Manage Project Properties page and select attribute.
   */
  @Test
  public void testSelectAttribue() {
    loadPage("rqm/set_attribute_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.selectAttribute("Custom attribute");
  }

  /**
   * Loads RQM Manage Project Properties page and set the attribute name.
   */
  @Test
  public void testSetAttributeName() {
    loadPage("rqm/set_attribute_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.setAttributeName("test");
  }

  /**
   * Loads RQM Manage Project Properties page and get the attribute name.
   */
  @Test
  public void testGetAttributeName() {
    loadPage("rqm/set_attribute_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    getJazzPageFactory().getRQMManageProjectPropertiesPage().setAttributeName("test_");
    Assert.assertTrue(getJazzPageFactory().getRQMManageProjectPropertiesPage().getAttributeName().contains("test"));
  }

  /**
   * Loads RQM Manage Project Properties page and select the type of the attribute.
   */
  @Test
  public void testSelectType() {
    loadPage("rqm/set_attribute_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    Map<Integer, String> clickToPage = new LinkedHashMap<Integer, String>();
    clickToPage.put(1, "rqm/select_custom_attribute.html");
    loadNewPageOnNthDriverClickElementCall(clickToPage);
    rqm.selectType("Text (Small)");
  }

  /**
   * Loads RQM Manage Project Properties page and Verify attribute is added.
   */
  @Test
  public void testIsAttributeAdded() {
    loadPage("rqm/set_custom_attribute.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    assertTrue(rqm.isAttributeAdded("Test_Attribute"));
  }

  /**
   * Loads RQM Manage Project Properties page and Enable Required check box in custom attribute.
   */
  @Test
  public void testEnableRequiredCheckBox() {
    loadPage("rqm/set_attribute_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.enableRequiredCheckBox();
  }

  /**
   * Loads RQM Manage Project Properties page and input Exteranl URI for the attribute.
   */
  @Test
  public void testSetExternalURI() {
    loadPage("rqm/set_attribute_name.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.setExternalURI("https://www.google.com/");
  }

  /**
   * clicks on RQM Manage project properties page labels.
   */
  @Test
  public void testAddDialogValues() {
    loadPage("rqm/add_value_dialog.html");
    RQMManageProjectPropertiesPage rqm = getJazzPageFactory().getRQMManageProjectPropertiesPage();
    assertNotNull(rqm);
    rqm.addDialogValues("Add Values", "Functional");
  }
}
