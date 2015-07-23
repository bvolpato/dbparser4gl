/**
 * Copyright (C) 2015 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.dbparser4gl.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.brunocvcunha.dbparser4gl.helper.DatabaseTablesHelper;
import org.brunocvcunha.dbparser4gl.vo.Table;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Default definition tests.
 *
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class DatabaseDefinitionTest {

  private static Collection<Table> tables;

  @BeforeClass
  public static void setUp() throws Exception {
    tables =
        DatabaseTablesHelper.listTables(DatabaseDefinitionTest.class.getResourceAsStream("/tests.df"), "tests");
  }

  @AfterClass
  public static void tearDown() throws Exception {
    tables = null;
  }

  /**
   * Validates if the setUp() created successfully the table collection
   */
  @Test
  public void testSize() {
    assertTrue(tables.size() > 0);
  }

  /**
   * Validates if the setUp() loaded successfully a table that always exists in the default DF
   * directory
   */
  @Test
  public void testExistence() {
    Table accessLogTable = null;
    for (Table table : DatabaseDefinitionTest.tables) {
      if (table.getName().equals("tests-log-acesso")) {
        accessLogTable = table;
        break;
      }
    }

    System.out.println("Table: " + accessLogTable);
    assertFalse(accessLogTable == null);

  }
}
