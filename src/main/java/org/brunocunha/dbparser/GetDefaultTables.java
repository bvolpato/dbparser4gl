package org.brunocunha.dbparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.brunocunha.dbparser.vo.Field;
import org.brunocunha.dbparser.vo.Table;

/**
 * Parses all Definition Files of an specified directory, returning always a Collection of Tables.
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class GetDefaultTables {

	public static boolean IS_VERBOSE = true;

	public static List<Table> listTables() {
		return listTables(new File[] { 
				new File("\\\\vigoreli.jv01.local\\ExpDtsul4\\@byyou\\11.5.2\\database\\progress\\ems2\\dffiles") 
				
				});
	}

	public static List<Table> listTables(String f) {
		return listTables(new File(f));
	}

	public static List<Table> listTables(File f) {
		DatabaseParser parser = new DatabaseParser();
		recursiveParser(parser, f);
		return parser.getTables();
	}

	public static List<Table> listTables(File[] a) {
		List<Table> tableList = new ArrayList<Table>();

		for (File f : a) {
			DatabaseParser parser = new DatabaseParser();
			recursiveParser(parser, f);
			tableList.addAll(parser.getTables());
		}

		return tableList;
	}

	public static void recursiveParser(DatabaseParser parser, File dir) {
		if (dir.isDirectory()) {
			for (File df : dir.listFiles()) {
				recursiveParser(parser, df);
			}
		} else {
			if (dir.getName().endsWith(".df")) {
				if (IS_VERBOSE) {
					System.out.println("[+] Parsing " + dir.getAbsolutePath() + "...");
				}
				parser.parseDefinitions(dir, dir.getName().split("\\.")[0]);
			}
		}

	}

	public static Table tableForName(Collection<Table> tables, String name) {
		for (Table t : tables) {
			if (t.getName().equals(name))
				return t;
		}

		return null;
	}

	public static Table tableForDump(Collection<Table> tables, String dump) {
		for (Table t : tables) {
			if (t.getDump().equals(dump))
				return t;
		}

		return null;
	}

	public static boolean tableHasField(Table table, String field) {
		for (Field f : table.getFields()) {
			if (f.getName().equals(field))
				return true;
		}

		return false;
	}

	public static boolean validField(Collection<Table> tables, String field) {
		for (Table t : tables) {
			if (t.getName().toLowerCase().equals(field.split("\\.")[0].toLowerCase())) {

				for (Field f : t.getFields()) {
					if (f.getName().toLowerCase().equals(field.split("\\.")[1].toLowerCase())) {
						return true;
					}
				}

				return false;
			}
		}

		return false;
	}

	public static boolean validField(Table t, String field) {

		for (Field f : t.getFields()) {
			if (f.getName().toLowerCase().equals(field.toLowerCase())) {
				return true;
			}
		}

		return false;
	}

}
