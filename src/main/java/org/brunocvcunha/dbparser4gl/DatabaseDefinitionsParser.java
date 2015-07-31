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
package org.brunocvcunha.dbparser4gl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.brunocvcunha.dbparser4gl.vo.Database;
import org.brunocvcunha.dbparser4gl.vo.DatabaseTrigger;
import org.brunocvcunha.dbparser4gl.vo.Field;
import org.brunocvcunha.dbparser4gl.vo.Index;
import org.brunocvcunha.dbparser4gl.vo.Table;


/**
 * Class that parses OpenEdge Definitions File (DF) into manageable objects.
 * 
 * @author Bruno Candido Volpato da Cunha
 * 
 */
public class DatabaseDefinitionsParser {

  private static Logger log = Logger.getLogger(DatabaseDefinitionsParser.class);

  private List<Table> tables;

  public DatabaseDefinitionsParser() {
    this.tables = new ArrayList<Table>();
  }

  private String converteBanco(String banco) {
    String convertido = banco;

    if (convertido.startsWith("ems2")) {
      convertido = convertido.replace("ems2", "mg");
    }
    if (convertido.startsWith("mov2")) {
      convertido = convertido.replace("mov2", "mov");
    }
    if (convertido.startsWith("wmov2")) {
      convertido = convertido.replace("wmov2", "wmov");
    }

    return convertido;
  }

  public void parseDefinitions(File df) throws FileNotFoundException {
    parseDefinitions(df, df.getName().split("\\.")[0]);
  }

  public void parseDefinitions(File df, String banco) throws FileNotFoundException {
    this.parseDefinitions(new FileInputStream(df), banco);
  }

  public void parseDefinitions(InputStream is, String banco) {
    String readDf = getFileContent(is).trim();
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
          while (++x < statements.length && !(childrenStatement = statements[x]).contains("ADD TABLE")) {

            if (childrenStatement.contains("cpstream=")) {
              return;
            }

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
          if (keyField.equals("TABLE-TRIGGER")) {
            DatabaseTrigger trigger = new DatabaseTrigger();
            trigger.setType(splitValues[1]);
            trigger.setProcedure(splitValues[3]);

            table.getTriggers().add(trigger);
          }
        } catch (Exception e) {
        }
      }
    }

    return table;
  }

  public Field parseField(String statement) {
    String statementTrim = statement.trim();

    String[] statementSplit = statementTrim.split("\"");
    Field field = new Field();
    field.setName(statementSplit[1]);
    field.setType(statementTrim.split(" ")[6]);

    for (String line : statementTrim.split("\r\n")) {
      line = line.trim();

      String[] keys = line.split(" ");
      String keyField = keys[0];
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
      if (keyField.equals("POSITION")) {
        field.setPosition(Integer.valueOf(keys[1]));
      }
      if (keyField.equals("ORDER")) {
        field.setOrder(Integer.valueOf(keys[1]));
      }
      if (keyField.equals("EXTENT")) {
        field.setExtent(true);
        field.setExtentValue(Integer.valueOf(keys[1]));
      }
      if (keyField.equals("MANDATORY")) {
        field.setMandatory(true);
      }
      if (keyField.equals("HELP")) {
        field.setHelp(splitValues[1]);
      }
      if (keyField.equals("VALEXP")) {
        field.setValidation(splitValues[1]);
      }
      if (keyField.equals("VALMSG")) {
        field.setValidationMessage(splitValues[1]);
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

  public List<Table> getTables() {
    return tables;
  }

  public void setTables(List<Table> tables) {
    this.tables = tables;
  }

  public static Collection<Database> splitTablesIntoDatabases(List<Table> tablesList) {
    Map<String, Database> map = new TreeMap<String, Database>();

    for (Table tabela : tablesList) {
      Database tableBase;

      if (map.containsKey(tabela.getBanco())) {
        tableBase = map.get(tabela.getBanco());
      } else {
        tableBase = new Database();
        tableBase.setName(tabela.getBanco());
        map.put(tabela.getBanco(), tableBase);
      }
      tableBase.getTables().add(tabela);
    }

    return map.values();
  }

  /**
   * Fast file reading
   * 
   * @param in
   * @return
   */
  private String getFileContent(InputStream in) {
    final byte[] readBuffer = new byte[8192];

    StringBuffer sb = new StringBuffer();
    try {
      if (in.available() > 0) {
        int bytesRead = 0;
        while ((bytesRead = in.read(readBuffer)) != -1) {
          sb.append(new String(readBuffer, 0, bytesRead));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }


  public void recursiveParser(File dir) throws FileNotFoundException {
    if (dir.isDirectory()) {
      for (File df : dir.listFiles()) {
        recursiveParser(df);
      }
    } else {
      if (dir.getName().endsWith(".df")) {
        log.info("[+] Parsing " + dir.getAbsolutePath() + "...");
        parseDefinitions(dir, dir.getName().split("\\.")[0]);
      }
    }
  }


}
