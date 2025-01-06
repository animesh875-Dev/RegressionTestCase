/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package java_test_scripts.EWM;

import org.junit.Assert;
import org.junit.Test;

import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMMenus;
import com.bosch.jazz.automation.web.common.constants.CCMMenusEnum.CCMSubMenus;
import com.bosch.jazz.automation.web.reporter.Reporter;

import common.AbstractFrameworkTest;

/**
 * @author BBO1KOR
 *
 */
public class TS_17505_queryWithInvalidIDSpecialCharacters extends AbstractFrameworkTest {
  /**
   * This test case check by running Queries with invalid ID and Special Characters and Validate work items are
   * not displayed for any of the scenarios.
   * 
   * @throws Throwable use to handle any kind of exception.
   */
  @Test
  public void tcs_17505_queryWithInvalidIDSpecialCharacters() throws Throwable  {
    String url = this.testDataRule.getConfigData("CCM_SERVER_URL");
    String projectArea = this.testDataRule.getConfigData("CCM_SCRUM_PROJECT_AREA");
    String invalidQuery=this.testDataRule.getConfigData("QUERY_NAME");
    String queryTab="Conditions";
    String id_wildcards=this.testDataRule.getConfigData("ID_WILDCARDS");
    String id_invalid=this.testDataRule.getConfigData("ID_INVALID");
    String summary_spechar =this.testDataRule.getConfigData("SUMMARY_SPECHAR");
    String name_speChar=this.testDataRule.getConfigData("NAME_SPECHAR");
    String id=this.testDataRule.getConfigData("ID");
    
    //Log into alm application with valid user name and password.
    getJazzPageFactory().getLoginPage().loginWithGivenPassword(getUserId(), getUserPassword(), url);
    Reporter.logPass(getUserId() + " user logged in to the " + url + " repository successfully.");
    // Select the project area.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().selectProjectArea(projectArea);
    Reporter.logPass(projectArea + ": project area opened successfully.");
    //Select Create Queries from Work Items drop down.
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openMenu(CCMMenus.WORK_ITEMS.toString());
    getJazzPageFactory().getCCMProjectAreaDashboardPage().openSubMenu(CCMSubMenus.CREATE_QUERY.toString());
    Reporter.logPass(CCMSubMenus.CREATE_QUERY.toString() + " Selected from Work Items drop down.");
    //Set Query name in new query.
    getJazzPageFactory().getCCMQueryPage().setQueryName(invalidQuery);
    invalidQuery = getJazzPageFactory().getCCMQueryPage().getQueryname();
    Reporter.logPass("Query name given as " + invalidQuery);
    
    // Click on Conditions tab.
    getJazzPageFactory().getCCMQueryPage().selectTab(queryTab);
    Reporter.logPass(queryTab + " Tab is selected from new quries");
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("id");
    Reporter.logPass("Id attribute is selected in Add Condition wizard");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Id");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox("Id",id_wildcards);
    Reporter.logPass("ID is set as " + id_wildcards);
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Validate text box for the wildcards.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().validateWildcardsTextBox());
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
    // Validate query is not displayed any work item for the wildcards.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().validateNoWorkItemsFound());
    getJazzPageFactory().getCCMQueryPage().refresh();
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("id");
    Reporter.logPass("Id attribute is selected in Add Condition wizard");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Id");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox("Id",id_invalid);
    Reporter.logPass("ID name given as " + id_invalid);
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
    // Validate query is not displayed any work item for the invalid ids.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().validateNoWorkItemsFound());
    
    getJazzPageFactory().getCCMQueryPage().refresh();
    //Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("Summary");
    Reporter.logPass("Id attribute is selected in Add Condition wizard");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Summary");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox("Summary",summary_spechar);
    Reporter.logPass("ID name given as " + summary_spechar);
   // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
   //Validate query is not displayed any work item for the Summary with special characters.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().validateNoWorkItemsFound());
   
    getJazzPageFactory().getCCMQueryPage().refresh();
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("Created By");
    Reporter.logPass("Id attribute is selected in Add Condition wizard");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Name");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox("Created By > Name",name_speChar);
    Reporter.logPass("Name is set as " + name_speChar);
   // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
    //Validate query is not displayed any work item for the Name with special charcters.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().validateNoWorkItemsFound());
    
    getJazzPageFactory().getCCMQueryPage().refresh();
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("Type");
    Reporter.logPass("Id attribute is selected in Add Condition wizard");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Type");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().clickOnSelectType("Story");
    Reporter.logPass("Type is selected as Story");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute is selected from the list box");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().selectConditionTypeByIndex("Type","is not","2");
    Reporter.logPass("Story Type is selected by Index");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().selectValueFromCondition("Type","Story","2");
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
    //Validate work items not dispalyed in the query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().validateNoWorkItemsFound());
    
    getJazzPageFactory().getCCMQueryPage().refresh();
    // Click on Add Condition button to add a condition.
    getJazzPageFactory().getCCMQueryPage().clickAddCondition();
    Reporter.logPass("Add Condition widget opened to select a condition");
    // Select attribute and specify the value.
    getJazzPageFactory().getCCMQueryPage().filterText("Id");
    Reporter.logPass("Id attribute is selected in Add Condition wizard");
    // Select the attribute from the list box
    getJazzPageFactory().getCCMQueryPage().selectItemInListBox("Id");
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    // Set ID name in the id widget.
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBox("Id",id);
    Reporter.logPass("ID name given as " + id);
    //Click on 'Add Attribute Condition' button.
    getJazzPageFactory().getCCMQueryPage().clickOnAddAttributeCondition();
    Reporter.logPass("Attribute selected from the list box");
    //Set the attribute text box using index.
    getJazzPageFactory().getCCMQueryPage().waitForSecs(5);
    getJazzPageFactory().getCCMQueryPage().setAttributeTextBoxByIndex("Id",id, "2");
    getJazzPageFactory().getCCMQueryPage().selectConditionTypeByIndex("Id", "is not", "2");
    // Click on Run button.
    getJazzPageFactory().getCCMQueryPage().button("Run");
    Reporter.logPass("Query is running");
    //Validate work items are not dispalyed in the query.
    Assert.assertTrue(getJazzPageFactory().getCCMQueryPage().validateNoWorkItemsFound());
    
    
    }

}
