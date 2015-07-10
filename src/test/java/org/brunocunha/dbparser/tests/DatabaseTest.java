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
package org.brunocunha.dbparser.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.brunocunha.dbparser.GetDefaultTables;
import org.brunocunha.dbparser.vo.Table;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Default tests.
 *
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class DatabaseTest {

    private static Collection<Table> tables;

    @BeforeClass
    public static void setUp() throws Exception {
        tables = GetDefaultTables.listTables();
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
     * Validates if the setUp() loaded successfully a table that always exists
     * in the default DF directory
     */
    @Test
    public void testExistencia() {
        Table tabela = null;
        for (Table table : DatabaseTest.tables) {
            if (table.getName().equals("item")) {
                tabela = table;
                break;
            }
        }

        assertFalse(tabela == null);

        /*assertFalse(true);
         assertFalse(true);
         assertFalse(true);
         assertFalse(true);*/
    }
}
