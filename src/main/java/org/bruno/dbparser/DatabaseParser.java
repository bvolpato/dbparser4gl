package org.bruno.dbparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bruno.dbparser.vo.Field;
import org.bruno.dbparser.vo.Index;
import org.bruno.dbparser.vo.Table;

import com.googlecode.inutils4j.MyStringUtils;

public class DatabaseParser {

	private Collection<Table> tables;

	public DatabaseParser() {
		this.tables = new ArrayList<Table>();
	}

	private String converteBanco(String banco) {
		if (banco.startsWith("ems2")) {
			banco = banco.replace("ems2", "mg");
		}
		if (banco.startsWith("mov2")) {
			banco = banco.replace("mov2", "mov");
		}
		if (banco.startsWith("wmov2")) {
			banco = banco.replace("wmov2", "wmov");
		}

		return banco;
	}

	public void parseDefinitions(File df, String banco) {
		String readDf = MyStringUtils.getContent(df).trim();
		readDf = readDf.replace("\r\n\r\nADD", " [EOL] \r\n\r\nADD");

		String[] statements = readDf.split("\\[EOL\\]");

		for (int x = 0; x < statements.length; x++) {
			String parse = statements[x].trim();
			parse = parse.replace("  ", " ").trim().replace(" ", " ").trim();

			if (parse.startsWith("ADD")) {

				if (parse.startsWith("ADD TABLE")) {
					Table table = parseTable(parse);
					table.setBanco(converteBanco(banco));
					tables.add(table);

					String childrenStatement;
					while (!(childrenStatement = statements[++x]).contains("ADD TABLE")) {

						if (childrenStatement.contains("cpstream="))
							return;

						if (childrenStatement.contains("ADD FIELD")) {
							String fieldStatement = childrenStatement;

							Field field = parseField(fieldStatement);
							table.addField(field);

						} else if (childrenStatement.contains("ADD INDEX")) {
							String indexStatement = childrenStatement;

							Index index = parseIndex(indexStatement, table);
							table.addIndex(index);

						}
					}

					x--;

				}

			}
		}

	}

	public Table parseTable(String statement) {
		String[] statementSplit = statement.split("\"");
		Table table = new Table();
		table.setName(statementSplit[1]);

		for (String line : statement.split("\r\n")) {
			line = line.trim();

			String keyField = line.split(" ")[0];
			String[] splitValues = line.split("\"");

			if (splitValues.length > 0) {
				try {
					if (keyField.equals("DUMP-NAME")) {
						table.setDump(splitValues[1]);
					}
					if (keyField.equals("LABEL")) {
						table.setLabel(splitValues[1]);
					}
					if (keyField.equals("DESCRIPTION")) {

						table.setDescription(splitValues[1]);

					}
					if (keyField.equals("AREA")) {
						table.setArea(splitValues[1]);
					}
				} catch (Exception e) {
				}
			} else {
			}
		}

		return table;
	}

	public Field parseField(String statement) {
		statement = statement.trim();

		String[] statementSplit = statement.split("\"");
		Field field = new Field();
		field.setName(statementSplit[1]);
		field.setType(statement.split(" ")[6]);

		for (String line : statement.split("\r\n")) {
			line = line.trim();

			String keyField = line.split(" ")[0];
			String[] splitValues = line.split("\"");

			if (keyField.equals("FORMAT") && splitValues.length > 1) {
				field.setFormat(splitValues[1]);
			}
			if (keyField.equals("INITIAL") && splitValues.length > 1) {
				field.setInitial(splitValues[1]);
			}
			if (keyField.equals("LABEL") && splitValues.length > 1) {
				field.setLabel(splitValues[1]);
			}
			if (keyField.equals("COLUMN-LABEL") && splitValues.length > 1) {
				field.setColumnLabel(splitValues[1]);
			}
			if (keyField.equals("POSITION") && splitValues.length > 1) {
				field.setPosition(Integer.valueOf(splitValues[1]));
			}
			if (keyField.equals("MANDATORY")) {
				field.setMandatory(true);
			}
		}

		return field;
	}

	public Index parseIndex(String statement, Table table) {
		String[] statementSplit = statement.split("\"");
		Index index = new Index();
		index.setName(statementSplit[1]);

		if (statement.contains("PRIMARY")) {
			index.setPrimary(true);
		}
		if (statement.contains("UNIQUE")) {
			index.setUnique(true);
		}

		String[] statementLineSplit = statement.split("\r\n");
		List<Field> indexField = new ArrayList<Field>();
		for (String line : statementLineSplit) {
			if (line.contains("INDEX-FIELD")) {

				String field = line.split("\"")[1];

				for (Field f : table.getFields()) {
					if (f.getName().equals(field)) {
						indexField.add(f);
					}
				}
			}
		}

		index.setFields(indexField);

		return index;
	}

	public Collection<Table> getTables() {
		return tables;
	}

	public void setTables(Collection<Table> tables) {
		this.tables = tables;
	}

}
