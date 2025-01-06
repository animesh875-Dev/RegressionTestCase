/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.jazz.automation.web.common.constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.InvalidParameterException;
import java.util.AbstractMap;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author hrt5kor 
 * Unit tests to verify VariableExpander class methods.
 *        
 *         
 */
public class VariableExpanderTest {

  /**
   * The ExpectedException rule allows you to verify that your code throws a specific exception.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  static final String singleParam = "Task\\,type_%%%_";
  static final String multipleParam = "Task,Type,defect,de_livery\\,story\\,delivery\\,";
  static final String mapOfParams = "${name:summary,type:defect,filedAgainst:Realese\\,two\\:next\\$old}";
  static final String mapOfParams_with_repacableValue =
      "${name:summary,type:defect,filedA_%%%_gainst:Realese\\,two\\:next}";
  static final String mapOfParams_neg_4 = "${name,type:defect,filedA_%%%%_gainst:Realese\\,two\\:next}";
  static final String mapOfParams_with_emptyVal = "${name:,type:defect,filedA_%%%%%_gainst:Realese\\,two\\:next}";
  static final String mapOfParams_with_emptyKey = "${name:summary,:defect,filedA_%%%%%_gainst:Realese\\,two\\:next}";
  static final String mapOfParams_WithMapAndVariable = "${name:${summary},type:defect,${filedAgainst}:Realese\\,two\\:next\\$old}";
  static final String mapOfParams_WithMultipleVariable = "${NAME},value,${NAME2},Realese\\\\,two\\\\:next\\\\$old";

  /**
   * Unit test to verify single parameter with "\\," replaced to "," and replacable value remains same.
   */
  @Test
  public void expandVariablesWithSingleParam() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    List<AbstractMap.SimpleImmutableEntry<String,String>> list = ve.expandVariables(singleParam);
    assertNotNull(list);
    assertEquals(1, list.size());
    assertEquals("Task,type_%%%_", list.get(0).getValue());
  }

  /**
   * Unit test to verify multiple parameter with "\\," is replaced with "," or not.
   */
  @Test
  public void expandVariablesWithMultipleParameters() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    List<AbstractMap.SimpleImmutableEntry<String,String>> list = ve.expandVariables(multipleParam);
    assertNotNull(list);
    assertEquals(4, list.size());
    assertEquals("Task", list.get(0).getValue());
    assertEquals("Type", list.get(1).getValue());
    assertEquals("defect", list.get(2).getValue());
    assertEquals("de_livery,story,delivery,", list.get(3).getValue());
    
  }

  /**
   * Unit test to verify map of parameter with "\\," , "\\:" and "\\$" is replaced with "," or not.
   */
  @Test
  public void expandVariablesWithmapOfParams() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    List<AbstractMap.SimpleImmutableEntry<String,String>> list = ve.expandVariables(mapOfParams);
    assertNotNull(list);
    assertEquals(3, list.size());
    assertEquals("name", list.get(0).getKey());
    assertEquals("type", list.get(1).getKey());
    assertEquals("filedAgainst", list.get(2).getKey());
    assertEquals("summary", list.get(0).getValue());
    assertEquals("defect", list.get(1).getValue());
    assertEquals("Realese,two:next$old", list.get(2).getValue());
    
  }
  /**
   * Unit test to verify single parameter with "\\," is replaced with "," or not.
   */
  @Test
  public void expandVariablesWithMapVaribleInsideDollarAndFlowerBraces() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    List<AbstractMap.SimpleImmutableEntry<String,String>> list = ve.expandVariables(mapOfParams_WithMapAndVariable);
    assertEquals(3, list.size());
    assertEquals("name", list.get(0).getKey());
    assertEquals("type", list.get(1).getKey());
    assertEquals("${filedAgainst}", list.get(2).getKey());
    assertEquals("${summary}", list.get(0).getValue());
    assertEquals("defect", list.get(1).getValue());
    assertEquals("Realese,two:next$old", list.get(2).getValue());
  }

  /**
   * Unit test to verify single parameter with "\\," is replaced with "," or not.
   */
  @Test
  public void expandVariablesWithVariableInsideDollarFlowerBraces() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    List<AbstractMap.SimpleImmutableEntry<String,String>> list = ve.expandVariables(mapOfParams_WithMultipleVariable);
    assertNotNull(list);
    assertEquals(4, list.size());
    assertEquals("${NAME}", list.get(0).getValue());
    assertEquals("value", list.get(1).getValue());
    assertEquals("${NAME2}", list.get(2).getValue());
    assertEquals("Realese\\,two\\:next\\$old", list.get(3).getValue());
  }

  /**
   * Unit test to verify whether input parameter contains already replace variable value and when encountered changing
   * the replacable value to other.
   */
  @Test
  public void expandVariablesWithMpaStringWithRepacableValue() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    List<AbstractMap.SimpleImmutableEntry<String,String>> list =  ve.expandVariables(mapOfParams_with_repacableValue);
    assertEquals(3, list.size());
    assertEquals("name", list.get(0).getKey());
    assertEquals("type", list.get(1).getKey());
    assertEquals("filedA_%%%_gainst", list.get(2).getKey());
    assertEquals("summary", list.get(0).getValue());
    assertEquals("defect", list.get(1).getValue());
    assertEquals("Realese,two:next", list.get(2).getValue());
  }

  /**
   * Unit test to verify whether input parameter is in given map format or not. i.e Every key should have value, e.g
   * key:value.
   */
  @Test()
  public void expandVariablesWithMpaStringNeagtiveCase4() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    this.thrown.expect(InvalidParameterException.class);
    this.thrown.expectMessage(
        CoreMatchers.is("Given string is not in map format i.e every key should have value (key:value)-")); 
    ve.expandVariables(mapOfParams_neg_4);
  }

  /**
   * Unit test to verify whether input parameter with key some value and value as empty string.
   */
  @Test
  public void expandVariablesWithMapVariableContaingEmptyValue() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    List<AbstractMap.SimpleImmutableEntry<String,String>> list = ve.expandVariables(mapOfParams_with_emptyVal);
    assertEquals(3, list.size());
    assertEquals("name", list.get(0).getKey());
    assertEquals("type", list.get(1).getKey());
    assertEquals("filedA_%%%%%_gainst", list.get(2).getKey());
    assertEquals("", list.get(0).getValue());
    assertEquals("defect", list.get(1).getValue());
    assertEquals("Realese,two:next", list.get(2).getValue());
  }
  /**
   * Unit test to verify whether input parameter with key some value and value as empty string.
   */
  @Test
  public void expandVariablesWithMapVariableContaingEmptyKey() {
    VariableExpander ve = new VariableExpander();
    assertNotNull(ve);
    this.thrown.expect(InvalidParameterException.class);
    this.thrown.expectMessage(
        CoreMatchers.is("Given string is not in map format as key is empty."));
    ve.expandVariables(mapOfParams_with_emptyKey);
  }
}
