package org.brunocunha.dbparser;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.brunocunha.dbparser.vo.Field;
import org.brunocunha.dbparser.vo.Table;

/**
 * Parses all Definition Files of an specified directory, returning always a
 * Collection of Tables.
 * 
 * @author Bruno Candido Volpato da Cunha
 * 
 */
public final class GetDefaultTables {

	private static Logger log = Logger.getLogger(GetDefaultTables.class);

	private static final boolean IS_VERBOSE = true;
	private static final String EMS2_DEFAULT = "\\\\vigoreli.jv01.local\\ExpDtsul4\\@byyou\\11.5.4\\database\\progress\\ems2\\dffiles";
	private static final String EMS5_DEFAULT = "\\\\vigoreli.jv01.local\\ExpDtsul4\\@byyou\\11.5.4\\database\\progress\\ems5\\dffiles";
	private static final String HCM_DEFAULT = "\\\\vigoreli.jv01.local\\ExpDtsul4\\@byyou\\11.5.4\\database\\progress\\hcm\\dffiles";
	private static final String FND_DEFAULT = "\\\\vigoreli.jv01.local\\ExpDtsul4\\@byyou\\11.5.4\\database\\progress\\fnd\\dffiles";
	private static final String GP_DEFAULT = "\\\\vigoreli.jv01.local\\ExpDtsul4\\@byyou\\11.5.4\\database\\progress\\gp\\dffiles";

	private GetDefaultTables() {
	}

	public static List<Table> listTables() {
		try {
			return listTables(new File[] { new File(EMS2_DEFAULT), new File(EMS5_DEFAULT), new File(HCM_DEFAULT),
					new File(FND_DEFAULT), new File(GP_DEFAULT) });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Table> listTables(String f) {
		try {
			return listTables(new File(f));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<Table> listTables(File f) {
		try {
			DatabaseParser parser = new DatabaseParser();
			recursiveParser(parser, f);
			return parser.getTables();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
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
				log.warn("[+] Parsing " + dir.getAbsolutePath() + "...");
				parser.parseDefinitions(dir, dir.getName().split("\\.")[0]);
			}
		}

	}

	public static Table tableForName(Collection<Table> tables, String name) {
		for (Table t : tables) {
			if (t.getName().equals(name)) {
				return t;
			}
		}

		return null;
	}

	public static Table tableForDump(Collection<Table> tables, String dump) {
		for (Table t : tables) {
			if (t.getDump().equals(dump)) {
				return t;
			}
		}

		return null;
	}

	public static boolean tableHasField(Table table, String field) {
		for (Field f : table.getFields()) {
			if (f.getName().equals(field)) {
				return true;
			}
		}

		return false;
	}

	public static boolean validField(Collection<Table> tables, String field) {
		for (Table t : tables) {
			if (t.getName().equalsIgnoreCase(field.split("\\.")[0])) {

				for (Field f : t.getFields()) {
					if (f.getName().equalsIgnoreCase(field.split("\\.")[1])) {
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
			if (f.getName().equalsIgnoreCase(field)) {
				return true;
			}
		}

		return false;
	}
}
