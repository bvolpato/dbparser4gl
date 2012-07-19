package org.brunocunha.dbparser.tests;

import java.util.Collection;

import static junit.framework.Assert.*;

import org.brunocunha.dbparser.GetDefaultTables;
import org.brunocunha.dbparser.vo.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Default tests.
 * @author Bruno Candido Volpato da Cunha
 *
 */
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

	/**
	 * Validates if the setUp() created successfully the table collection
	 */
	@Test
	public void testSize() {
		assertTrue(tables.size() > 0);
	}

	/**
	 * Validates if the setUp() loaded successfully a table that always exists in the default DF directory
	 */
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
		
		/*assertFalse(true);
		assertFalse(true);
		assertFalse(true);
		assertFalse(true);*/
	}
	
	
}
