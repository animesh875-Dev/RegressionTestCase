/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs.verification;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.pagemodel.verification.jrs.JRSBuildNewReportPageVerification;

/**
 * @author BBW1KOR
 */
public class JRSBuildNewReportPageVerificationTest extends AbstractFrameworkUnitTest {


  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyEditDataSource(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectOwner() {
    loadPage("jrs/edit_data_source_2.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyEditDataSource("Rational Data Warehouse", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyEditDataSource(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectOwners() {
    loadPage("jrs/edit_data_source_2.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyEditDataSource("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetProjectAreaName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetProjectAreaName() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetProjectAreaName("SYS-TEST-com.bosch.rtc.tmpl.Scrum_2018.1", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetProjectAreaName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifysSetProjectAreaName() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetProjectAreaName("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickContinueButton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickContinueButton() {
    loadPage("jrs/set_project_area.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickContinueButton("Choose a report type", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickContinueButton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifysClickContinueButton() {
    loadPage("jrs/set_project_area.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickContinueButton("Limit the scope", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnJazzSpanButtons() {
    loadPage("jrs/edit_data_source_1.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzSpanButtons("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyClickOnJazzSpanButtons() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzSpanButtons("Add", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfYClickOnJazzSpanButtons() {
    loadPage("jrs/set_graph_type.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzSpanButtons("Add and Close", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyGraphClickOnJazzSpanButtons() {
    loadPage("jrs/set_date_scale_1.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzSpanButtons("Graph", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyRefreshClickOnJazzSpanButtons() {
    loadPage("jrs/set_date_scale_1.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzSpanButtons("Refresh", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzSpanButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerIfyAttributedataitemsClickOnJazzSpanButtons() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzSpanButtons("Attribute data items", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyChooseAnArtifact(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyChooseAnArtifact() {
    loadPage("jrs/add_relationship_1.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyChooseAnArtifact("", "Rational Data Warehouse", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyChooseAnArtifact(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidChooseAnArtifact() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyChooseAnArtifact("", "invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyAddRelationship(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test(expected = Exception.class)
  public void testVerifyAddRelationship() {
    loadPage("jrs/add_relationship_1.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyAddRelationship("", "Rational Data Warehouse", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzImageButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnJazzImageButtons() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzImageButtons("Add condition", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnJazzImageButtons(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyAddconditionClickOnJazzImageButtons() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzImageButtons("invalid button", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySearchCondition(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySearchCondition() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySearchCondition("date", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySearchCondition(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSearchCondition() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySearchCondition("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnRadiobutton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnRadiobutton() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnRadiobutton("Creation Date", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnRadiobutton(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyinvalidClickOnRadiobutton() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnRadiobutton("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionType(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetConditionType() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetConditionType("is", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionType(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSetConditionType() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetConditionType("isnot", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetConditionDate() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetConditionDate("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSetConditionDate() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetConditionDate("26/10/2020", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test(expected = Exception.class)
  public void testVerifySetClickOnfilterRadioButton() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetClickOnfilterRadioButton("Today", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetConditionAttributesOfvalue() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetConditionAttributesOfvalue("QM Test Plan [Type: QM Test Plan]", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSetConditionAttributesOfvalue() {
    loadPage("jrs/set_condition_date.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetConditionAttributesOfvalue("Work Item [Type: Work Item]", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnTab() {
    loadPage("jrs/add_calc_col_limit_person.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnTab("Format results", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidClickOnTab() {
    loadPage("jrs/add_calc_col_limit_person.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnTab("Choose data", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetJrsTextBoxValue() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetJrsTextBoxValue("", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetGroupByAttributeValue() {
    loadPage("jrs/verify_set_jrs_textbox_value.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetGroupByAttributeValue("<None>", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyworkitemSetGroupByAttributeValue() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetGroupByAttributeValue("Work Item", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetDimensionValue() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetDimensionValue("Project", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyinvalidSetDimensionValue() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetDimensionValue("Work Item", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetUnits() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetUnits("Count all results in the category", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyinvalidSetUnits() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetUnits("Work Item", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyValidateHorizontalGraph() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyValidateHorizontalGraph("");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetAddAttributesToTheReport() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetAddAttributesToTheReport("Work Item [Type: Work Item]", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSetAddAttributesToTheReport() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetAddAttributesToTheReport("", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnJazzButtons() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzButtons("Add", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetConditionDate(String,String)}. Loads a
   * JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyaddClickOnJazzButtons() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnJazzButtons("Refresh", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectShowValuesAndShowTotals(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test(expected = Exception.class)
  public void testVerifySelectShowValuesAndShowTotals() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectShowValuesAndShowTotals("");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectShowValuesAndShowTotals(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSelectShowValuesAndShowTotals() {
    loadPage("jrs/verify_Select_show_values_and_show_totals.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectShowValuesAndShowTotals("");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectGraphAndChart(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySelectGraphAndChart() {
    loadPage("jrs/verify_Select_show_values_and_show_totals.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectGraphAndChart("", "Horizontal", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectGraphAndChart(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test(expected = Exception.class)
  public void testVerifyInvalidSelectGraphAndChart() {
    loadPage("jrs/verify_Select_show_values_and_show_totals.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectGraphAndChart("", "vertical", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySearchAttributeCondition(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySearchAttributeCondition() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    getJazzPageFactory().getJRSBuildNewReportPage().searchAttributeCondition("test");
    jrs.verifySearchAttributeCondition("test", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySearchAttributeCondition(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyinvalidSearchAttributeCondition() {
    loadPage("jrs/set_add_attributes_to_the_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    getJazzPageFactory().getJRSBuildNewReportPage().searchAttributeCondition("test");
    jrs.verifySearchAttributeCondition("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnReportTab(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyClickOnReportTab() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnReportTab("Format results", "","");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnReportTab(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidClickOnReportTab() {
    loadPage("jrs/set_graph_values_in_format_result.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnReportTab("Name and share", "","");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetDefaultVisualization(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetDefaultVisualization() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetDefaultVisualization("Table", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetDefaultVisualization(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSetDefaultVisualization() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetDefaultVisualization("invalid", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySaveReport(String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySaveReport() {
    loadPage("jrs/save_report_successfully.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySaveReport("TRUE","");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetJRSReportName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifySetJRSReportName() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetJRSReportName("Report", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySetJRSReportName(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidSetJRSReportName() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySetJRSReportName("Invalidreport", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyDeleteReport(String,String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test(expected = Exception.class)
  public void testVerifyDeleteReport() {
    loadPage("jrs/verify_delete_report.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyDeleteReport("", "","","");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyChooseArtifacts(String,String,String)}. Loads
   * a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyChooseArtifacts() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyChooseArtifacts("Work Item", "", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyChooseArtifacts(String,String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidChooseArtifacts() {
    loadPage("jrs/save_report_name.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyChooseArtifacts("Invalid", "", "");
  }


  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectFolder(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyselectFolder() {
    loadPage("jrs/select_folder_3.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectFolder("SprintwiseData/2018",null);
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectFolder(String,String)}.
   * <p>
   * Loads a JazzLogin Page and verifies the methods.
   */
  @Test
  public void testVerifyInvalidselectFolder() {
    loadPage("jrs/select_folder_3.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectFolder("Invalid",null);
  }
  
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyAddACustomExpressionColumn(Map, String, String)}.
   * <p>
   */
  @Test
  public void testVerifyAddACustomExpressionColumn() {
    loadPage("jrs/addCustomExpression_5.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    Map<String, String> coloumnValue = new HashMap<>();
    coloumnValue.put("ATTRIBUTE_OF", "QM Test Plan [Type: QM Test Plan]");
    coloumnValue.put("ATTRIBUTE_VALUE", "Planning effort");
    coloumnValue.put("CUSTOM_EXPRESSION", "MAX($QM Test Plan:Planning effort$)");
    coloumnValue.put("SHOW_AS", "String (default)");
    jrs.verifyAddACustomExpressionColumn(coloumnValue,"TRUE","The expression is valid. Click Add.!!!!!!");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectMultilePathOption(String, String, String)}.
   * <p>
   */
  @Test
  public void testVerifySelectMultilePathOption() {
    loadPage("jrs/selectMultipleOptions.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectMultilePathOption("Append to...", "Requirement 1","");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectAnArtifactInDialog(String, String, String, String)}.
   * <p>
   */
  @Test
  public void testVerifySelectAnArtifactInDialog() {
    loadPage("jrs/selectMultipleOptions.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectAnArtifactInDialog("Requirement","Requirement","Requirement 1", "");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectOuterGroupType(String, String)}.
   * <p>
   */
  @Test
  public void testVerifySelectOuterGroupType() {
    loadPage("jrs/set_conditions_2.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectOuterGroupType("All must match (AND):", "");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyChooseSelectAllVisibleItems(String)}.
   * Passed case
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyChooseSelectAllVisibleItems() {
    loadPage("jrs/ChooseSelectAllVisibleItems_02.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyChooseSelectAllVisibleItems("");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyChooseSelectAllVisibleItems(String)}.
   * Failed case
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerIfyChooseSelectAllVisibleItems() {
    loadPage("jrs/ChooseSelectAllVisibleItems_01.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyChooseSelectAllVisibleItems("");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyVerifyParameterValueInFilters(String, String)}.
   * Failed case
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerIfyVerifyParameterValueInFilters() {
    loadPage("jrs/VerifyParameterValueInFilters_01.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyVerifyParameterValueInFilters(": Accepted, N/A, New/Changed, Rejected, To clarify","false");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyVerifyParameterValueInFilters(String, String)}.
   * Passed case
   * <p>
   * 
   * @author KYY1HC
   */
  @Test
  public void testVerifyVerifyParameterValueInFilters() {
    loadPage("jrs/VerifyParameterValueInFilters_01.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyVerifyParameterValueInFilters(": 6244","true");
  }
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectAConfigurationAndValidate(String, String)}.
   * Passed case
   */
  @Test
  public void testVerifySelectAConfigurationAndValidate() {
    loadPage("jrs/choose_a_configuration.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectAConfigurationAndValidate("ALM_System_AcceptanceTest_Platform","true");
  }
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifySelectAConfigurationAndValidate(String, String)}.
   * Failed case
   */
  @Test
  public void testverifySelectAConfigurationAndValidate() {
    loadPage("jrs/choose_a_configuration.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifySelectAConfigurationAndValidate("No Error","false");
  }
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnRunButton(String)}.
   * Passed case
   */
  @Test
  public void testVerifyClickOnRunButton() {
    loadPage("jrs/choose_a_configuration.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnRunButton("true");
  }
  
  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickOnRunButton(String)}.
   * Failed case
   */
  @Test
  public void testverifyClickOnRunButton() {
    loadPage("jrs/choose_a_configuration.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickOnRunButton("false");
  }

  /**
   * <p>
   * Unit test coverage for {@link JRSBuildNewReportPageVerification#verifyClickCancelButton(String)}.
   * @author KYY1HC
   */
  @Test
  public void testVerifyClickCancelButton() {
    loadPage("jrs/verifyClickCancelButton_01.html");
    JRSBuildNewReportPageVerification jrs = getJazzPageFactory().getJRSBuildNewReportPageVerification();
    assertNotNull(jrs);
    jrs.verifyClickCancelButton("true");
  }
}
