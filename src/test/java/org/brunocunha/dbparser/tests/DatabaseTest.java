package org.brunocunha.dbparser.tests;

import java.util.Collection;

import static junit.framework.Assert.*;

import org.bruno.dbparser.GetDefaultTables;
import org.bruno.dbparser.vo.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

	private Collection<Table> tables;
	
	@Before
	public void setUp() throws Exception {
		this.tables = GetDefaultTables.listTables();
	}

	@After
	public void tearDown() throws Exception {
		this.tables = null;
	}

	@Test
	public void testSize() {
		assertTrue(tables.size() > 0);
	}

	@Test
	public void testExistencia() {
		Table tabela = null;
		for (Table table : this.tables) {
			if (table.getName().equals("item")) {
				tabela = table;
				break;
			}
		}
	
		assertFalse(tabela == null);
		
		assertFalse(true);
		assertFalse(true);
		assertFalse(true);
		assertFalse(true);
	}
	
	
}
