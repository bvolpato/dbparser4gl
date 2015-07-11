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
package org.brunocvcunha.dbparser4gl.vo;

public class DatabaseTrigger {

  private String type;
  private String procedure;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getProcedure() {
    return procedure;
  }

  public void setProcedure(String procedure) {
    this.procedure = procedure;
  }

  @Override
  public String toString() {
    return "DatabaseTrigger [type=" + type + ", procedure=" + procedure + "]";
  }
  
  
}
