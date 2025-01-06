/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.excel;

import static org.junit.Assert.assertEquals;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.bosch.jazz.automation.web.AbstractFrameworkUnitTest;
import com.bosch.jazz.automation.web.common.WebDriverCustom;

/**
 * unit test for ExcelTestScriptExecutor
 */
public class ExcelTestScriptExecutorTest extends AbstractFrameworkUnitTest  {

  /**
   * This method tests the ExcelTestScriptExecutor with 2 sheets.
   * @throws Exception exception
   */
  @Test
  public void testExecuteScript() throws Exception {
    Map<String, String> env = new LinkedHashMap<>();
    ExcelScriptCommand command1 = new ExcelScriptCommand();
    ExcelScriptCommand command2 = new ExcelScriptCommand();
    ExcelScriptCommand command3 = new ExcelScriptCommand();
    ExcelScriptCommand command4 = new ExcelScriptCommand();
    command1.setAction("CommandExecutorData.testCommandExcecutor");
    command2.setAction("CommandExecutorData.testCommandExcecutor2");
    command3.setAction("CommandExecutorData.testCommandExcecutor3");
    command4.setAction("CommandExecutorData.testCommandExcecutor4");
    SimpleImmutableEntry<String, String> map1 =
        new AbstractMap.SimpleImmutableEntry<String, String>(null, "catframework");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list1 = new LinkedList<>();
    list1.add(map1);
    command1.setParameter(list1);
    SimpleImmutableEntry<String, String> map4 = new AbstractMap.SimpleImmutableEntry<String, String>("name", "Robert");
    SimpleImmutableEntry<String, String> map5 =
        new AbstractMap.SimpleImmutableEntry<String, String>("company", "Bosch");
    SimpleImmutableEntry<String, String> map6 = new AbstractMap.SimpleImmutableEntry<String, String>(null, "JRS");
    SimpleImmutableEntry<String, String> map7 = new AbstractMap.SimpleImmutableEntry<String, String>(null, "Reporting");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list3 = new LinkedList<>();
    list3.add(map4);
    list3.add(map5);
    list3.add(map6);
    list3.add(map7);
    command3.setParameter(list3);
    SimpleImmutableEntry<String, String> map8 = new AbstractMap.SimpleImmutableEntry<String, String>("name", "Robert");
    SimpleImmutableEntry<String, String> map9 =
        new AbstractMap.SimpleImmutableEntry<String, String>("company", "Bosch");
    List<AbstractMap.SimpleImmutableEntry<String, String>> list4 = new LinkedList<>();
    list4.add(map8);
    list4.add(map9);
    command4.setParameter(list4);
    List<ExcelScriptCommand> commandList_SheetOne = new ArrayList<>();
    commandList_SheetOne.add(command1);
    commandList_SheetOne.add(command2);
    commandList_SheetOne.add(command3);
    commandList_SheetOne.add(command4);
    ExcelTestScriptExecutor exe = new ExcelTestScriptExecutor(new WebDriverCustom(driver, driverSetup));
    List<ExcelScriptResult> result = exe.executeScript(env, commandList_SheetOne, null, null);
    assertEquals(4, result.size());
  }
}
