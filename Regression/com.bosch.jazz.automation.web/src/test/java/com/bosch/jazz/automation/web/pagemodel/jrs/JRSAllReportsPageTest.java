/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.pagemodel.jrs;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.WebAutomationException;

/**
 * Unit test for the JRSAllReportsPage.
 *
 * @author gem5kor
 */
public class JRSAllReportsPageTest extends AbstractFrameworkUnitTest {

  /**
   * The ExpectedException rule allows you to verify that your code throws a specific exception.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * Loads the url and waits until the page is fully loaded
   */
  @Test
  public void openTest() {
    loadPage("jrs/open.html");
    JRSAllReportsPage jrsAllReportsPage = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jrsAllReportsPage);
    String repositoryURL = "https://rb-alm-02-t3.de.bosch.com/rs";
    jrsAllReportsPage.open(repositoryURL, null, null);
  }

  /**
   * Loads All Report page, check for all report page for selected tags.
   *
   * @throws InterruptedException , if any error occur.
   * @throws WebAutomationException , if any error occur.
   */
  @Test
  public void openTagTest() throws WebAutomationException, InterruptedException {
    loadPage("jrs/open_tag_1.html");
    JRSAllReportsPage jrsAllReportsPage = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jrsAllReportsPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/open_tag_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String tagName = "Entwurf";
    jrsAllReportsPage.openTag(tagName);
  }

  /**
   * Loads All Report page, check for all report page for selected tags.
   *
   * @throws InterruptedException , if any error occur.
   * @throws WebAutomationException , if any error occur.
   */
  @Test
  public void openReport_ConditionalTest() throws WebAutomationException, InterruptedException {
    loadPage("jrs/open_report_1.html");
    JRSAllReportsPage jrsAllReportsPage = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jrsAllReportsPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/open_tag_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String tagName = "Entwurf";
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Report not found"));
    jrsAllReportsPage.openReport(tagName);
  }

  /**
   * Loads All Report page, check for all report page for selected tags.
   *
   * @throws InterruptedException , if any error occur.
   * @throws WebAutomationException , if any error occur.
   */
  @Test
  public void openReportTest() throws WebAutomationException, InterruptedException {
    loadPage("jrs/open_report_1.html");
    JRSAllReportsPage jrsAllReportsPage = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jrsAllReportsPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/open_tag_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String tagName = "abc";
    jrsAllReportsPage.openReport(tagName);
  }

  /**
   * Loads run report results page and verify number of records.
   */
  @Test
  public void verifyTotalNoOfRecordsTest() {
    loadPage("jrs/verify_number_of_records_allReports.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    jbnrp.verifyTotalNoOfRecords("3000");
  }

  /**
   * Loads run report results page and verify number of records.
   */
  @Test
  public void verifyTotalNoOfRecordsConditionTwoTest() {
    loadPage("jrs/no_result_table.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    jbnrp.verifyTotalNoOfRecords("0");
  }

  /**
   * Loads run report results page and verify number of records.
   */
  @Test
  public void verifyTotalNoOfRecordsConditionThreeTest() {
    loadPage("jrs/verify_number_of_records_allReports.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("No of records are not correct, total no of records in the reports is" +
        3000 + " and total no recrods expected is " + 305));
    jbnrp.verifyTotalNoOfRecords("305");
  }

  /**
   * Loads run report results page and verify number of records.
   */
  @Test
  public void verifyTotalNoOfRecordsConditionFourTest() {
    loadPage("jrs/verify_number_of_records.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("frame not found"));
    jbnrp.verifyTotalNoOfRecords("305");
  }

  /**
   * Loads reports page with graph, verify the graph result using screen and pattern.
   */
  @Test
  public void verifyGraphResultTest() {
    loadPage("jrs/verify_graph_result_allReportsPage.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Graph is not correct"));
    Screen sr = new Screen();
    Pattern pr = new Pattern();
    Path reportFile = Paths.get(SRC_TEST_RESOURCES + "jrs/verify_graph_result_allReportsPage.html");
    pr.setFilename(reportFile.toUri().toString());
    jbnrp.verifyGraphResult(sr, pr);
  }

  /**
   * Loads change and configuration management dashboard page, click on widget save button.
   */
  @Test
  public void clickRunButton() {
    loadPage("jrs/click_on_run_button.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    // jbnrp.switchFrame();
    jbnrp.clickRunButton();
  }

  /**
   * Loads all reports page, Search to open burndown chart.
   *
   * @throws WebAutomationException if the element is not found.
   * @throws IllegalAccessException if accessing to data which is illegal.
   * @throws InterruptedException if any thread interrupted the current thread.
   */
  @Test
  public void openBurndownReportTest() throws WebAutomationException, IllegalAccessException, InterruptedException {
    loadPage("jrs/open_burn_down_chart.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, null);
    clickNumberToPagePath.put(2, "jrs/open_burn_down_chart_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    this.thrown.expect(IllegalAccessException.class);
    this.thrown.expectMessage(CoreMatchers.is("SnapShot Created"));
    jbnrp.openBurndownReport(" JRS Report");
  }

  /**
   * Loads all reports page, Search to open burndown chart.
   *
   * @throws WebAutomationException if the element is not found.
   * @throws IllegalAccessException if accessing to data which is illegal.
   * @throws InterruptedException if any thread interrupted the current thread.
   */
  @Test
  public void openBurndownReportConditionTestTwo()
      throws WebAutomationException, IllegalAccessException, InterruptedException {
    loadPage("jrs/open_burn_down_chart.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jbnrp);
    this.thrown.expect(IllegalStateException.class);
    this.thrown.expectMessage(CoreMatchers.is("Report not found"));
    jbnrp.openBurndownReport("JRS Report_Not_Valid");
  }

  /**
   * Loads All Report page, check for all report page for selected tags.
   *
   * @throws InterruptedException , if any error occur.
   * @throws WebAutomationException , if any error occur.
   */
  @Test
  public void openTagsTest() throws WebAutomationException, InterruptedException {
    loadPage("jrs/add_relationship_1.html");
    JRSAllReportsPage jrsAllReportsPage = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jrsAllReportsPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/add_relationship_1.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String tagName = "Entwurfsdssds";
    jrsAllReportsPage.openTag(tagName);
  }

  /**
   * Loads All Report page, check for all report page for selected tags.
   *
   * @throws InterruptedException , if any error occur.
   * @throws WebAutomationException , if any error occur.
   */
  @Test
  public void openReportsTest() throws WebAutomationException, InterruptedException {
    loadPage("jrs/pass_filter_values_2.html");
    JRSAllReportsPage jrsAllReportsPage = getJazzPageFactory().getJRSAllReportsPage();
    assertNotNull(jrsAllReportsPage);
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/pass_filter_values_2.html");
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    String tagName = "abc";
    jrsAllReportsPage.openReport(tagName);
  }

  /**
   * Loads report results page and pass the filter values.
   *
   * @throws InterruptedException if any thread interrupted the current thread.
   * @throws WebAutomationException if element is not found.
   */
  @Test
  public void passFiltersValuesTest() throws InterruptedException {
    loadPage("jrs/filters_page_0.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/filters_page_1.html");
    clickNumberToPagePath.put(2, "jrs/filters_page_2.html");
    assertNotNull(jbnrp);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    // jbnrp.passFilterValues("Limit the scope", "All", true);
  }

  /**
   * Loads report results page and pass the filter values.
   *
   * @throws InterruptedException if any thread interrupted the current thread.
   * @throws WebAutomationException if element is not found.
   */
  @Test
  public void passFilteredValuesTest() throws InterruptedException {
    loadPage("jrs/filters_page_0.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/filters_page_3.html");
    clickNumberToPagePath.put(2, "jrs/filters_page_3.html");
    assertNotNull(jbnrp);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    // jbnrp.passFilterValues("Team (Totals trend for Work Item)", "Select all visible items", true);
  }

  /**
   * Loads report results page and pass the filter values.
   *
   * @throws InterruptedException if any thread interrupted the current thread.
   * @throws WebAutomationException if element is not found.
   */
  @Test
  public void passFilteredValues_searchTest() throws InterruptedException {
    loadPage("jrs/filters_page_0.html");
    JRSAllReportsPage jbnrp = getJazzPageFactory().getJRSAllReportsPage();
    Map<Integer, String> clickNumberToPagePath = new HashMap<>();
    clickNumberToPagePath.put(1, "jrs/filters_page_1.html");
    clickNumberToPagePath.put(2, "jrs/filters_page_2.html");
    assertNotNull(jbnrp);
    loadNewPageOnNthDriverClickElementCall(clickNumberToPagePath);
    // jbnrp.passFilterValues("Limit the scope", "SCM_Integration_Test - CCM Project", true);
  }
}