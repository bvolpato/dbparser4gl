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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * Class that parses OpenEdge Data File (D) into maps
 * 
 * Based on http://stackoverflow.com/questions/12360694/java-scanner-delimit-by-spaces-unless-quotation-marks-are-present
 * @author Bruno Candido Volpato da Cunha
 * 
 */
public class DatabaseDataParser {

  private static final String NOT_QUOTE_OR_SPACES = "[^\"\\s]+";
  private static final String QUOTED_STRING = "\"(\\\\.|[^\\\\\"])*\"";
  private static final String DELIMITER = "(" + NOT_QUOTE_OR_SPACES + "|" + QUOTED_STRING + ")";
  private static final Pattern COMPILED_DELIMITER = Pattern.compile(DELIMITER);
  
  private static Logger log = Logger.getLogger(DatabaseDataParser.class);

  
  public static List<Map<String, Object>> parseDataFile(String[] columns, File file) throws FileNotFoundException {
    return parseDataFile(columns, new FileInputStream(file));
  }
  
  public static List<Map<String, Object>> parseDataFile(String[] columns, InputStream is) {
    
    Scanner sc = new Scanner(is);

    List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();

    reading: while (sc.hasNextLine()) {

      log.debug("Line Skipping");
      Map<String, Object> lineMap = new HashMap<String, Object>();

      for (int col = 0; col < columns.length; col++) {
        String found = sc.findWithinHorizon(COMPILED_DELIMITER, 0);
        log.debug("Found -> " + found);
        if (found.equals(".")) {
          break reading;
        }

        lineMap.put(columns[col], found);
      }

      records.add(lineMap);

      log.debug("Next Line --> " + sc.nextLine());
    }


    return records;
  }
}
