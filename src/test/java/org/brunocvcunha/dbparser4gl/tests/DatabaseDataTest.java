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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.brunocvcunha.dbparser4gl.DatabaseDataParser;
import org.junit.Test;

/**
 * Default data tests.
 *
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class DatabaseDataTest {
  
  
  @Test
  public void test() throws IOException {
    InputStream is = DatabaseDataTest.class.getResourceAsStream("/tests.d");
    
    List<Map<String, Object>> data = DatabaseDataParser.parseDataFile(new String[] {"ID", "Data", "Texto"}, is);
    System.out.println("Returned " + data);
    
    assertEquals(5, data.size());
    assertEquals("1", data.get(0).get("ID").toString());
    assertEquals("13/08/13", data.get(0).get("Data").toString());
    assertEquals("\"Main Reason\"", data.get(0).get("Texto").toString());
    
    assertEquals("2", data.get(1).get("ID").toString());
    assertEquals("Second Reason \"Test\" Second", data.get(1).get("Texto").toString());
    
    assertEquals("3", data.get(2).get("ID").toString());
    
    assertEquals("4", data.get(3).get("ID").toString());
    
    assertEquals("5", data.get(4).get("ID").toString());
    
  }
}
