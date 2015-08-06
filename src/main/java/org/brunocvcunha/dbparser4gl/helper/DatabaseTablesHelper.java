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
package org.brunocvcunha.dbparser4gl.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.brunocvcunha.dbparser4gl.DatabaseDefinitionsParser;
import org.brunocvcunha.dbparser4gl.vo.Field;
import org.brunocvcunha.dbparser4gl.vo.Table;


/**
 * Parses all Definition Files of an specified directory, returning always a Collection of Tables.
 * 
 * @author Bruno Candido Volpato da Cunha
 * 
 */
public class DatabaseTablesHelper {

  private static List<Table> defaultTablesInstance;

  private static Logger log = Logger.getLogger(DatabaseTablesHelper.class);

  private static final boolean IS_VERBOSE = true;

  private static final String BASE_DIR = "\\\\TOUROS.jv01.local\\DFS";
  private static final String EAI_DEFAULT = BASE_DIR + "\\EAI\\12.1.3";
  private static final String EMS2_DEFAULT = BASE_DIR + "\\EMS2\\12.1.3";
  private static final String EMS5_DEFAULT = BASE_DIR + "\\EMS5\\12.1.3";
  private static final String FND_DEFAULT = BASE_DIR + "\\Foundation\\12.1.3";
  private static final String GP_DEFAULT = BASE_DIR + "\\GP\\12.1.3";
  private static final String HCM_DEFAULT = BASE_DIR + "\\HCM\\12.1.3";
  private static final String METADADOS_DEFAULT = BASE_DIR + "\\CRM\\12.1.3";

  private DatabaseTablesHelper() {}

  public static List<Table> listTables() {
    synchronized (DatabaseTablesHelper.class) {
      if (defaultTablesInstance == null) {
        defaultTablesInstance =
            listTables(new File[] {new File(EAI_DEFAULT), new File(EMS2_DEFAULT),
                new File(EMS5_DEFAULT), new File(FND_DEFAULT), new File(HCM_DEFAULT),
                new File(GP_DEFAULT), new File(METADADOS_DEFAULT)});
      }
    }

    return defaultTablesInstance;
  }

  public static List<Table> listTables(String f) throws FileNotFoundException {
    return listTables(new File(f));
  }

  public static List<Table> listTables(File f) throws FileNotFoundException {
    DatabaseDefinitionsParser parser = new DatabaseDefinitionsParser();
    parser.recursiveParser(f);
    return parser.getTables();
  }

  public static List<Table> listTables(InputStream is, String database) {
    DatabaseDefinitionsParser parser = new DatabaseDefinitionsParser();
    parser.parseDefinitions(is, database);
    return parser.getTables();
  }

  public static List<Table> listTables(File[] a) {
    return listTables(a, true);
  }

  public static List<Table> listTables(File[] a, boolean multiThread) {
    List<Table> tableList = new ArrayList<Table>();

    ExecutorService service = Executors.newFixedThreadPool(multiThread ? 50 : 1);
    List<Future> tasks = new ArrayList<Future>();

    for (File f : a) {
      DatabaseParserThread parserThread = new DatabaseParserThread(f, tableList);
      tasks.add(service.submit(parserThread));
    }

    service.shutdown();

    for (Future task : tasks) {
      try {
        task.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }

    return tableList;
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

  public static Field fieldForTableField(Collection<Table> tables, String expression) {
    if (!expression.contains(".") || expression.split("\\.").length == 1) {
      return null;
    }

    Table table = tableForName(tables, expression.split("\\.")[0]);
    if (table == null) {
      return null;
    }

    return table.findField(expression.split("\\.")[1]);
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


class DatabaseParserThread extends Thread {
  private File file;
  private List<Table> tableList;

  public DatabaseParserThread(File file, List<Table> tableList) {
    super();
    this.file = file;
    this.tableList = tableList;
  }

  public void run() {
    DatabaseDefinitionsParser parser = new DatabaseDefinitionsParser();
    try {
      parser.recursiveParser(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    tableList.addAll(parser.getTables());
  }

}
