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

import java.util.ArrayList;
import java.util.Collection;

public class Index {

  private String name;
  private String area;
  private boolean unique;
  private boolean primary;
  private Collection<Field> fields;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public boolean isUnique() {
    return unique;
  }

  public void setUnique(boolean unique) {
    this.unique = unique;
  }

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

  public Collection<Field> getFields() {
    if (fields == null) {
      fields = new ArrayList<Field>();
    }
    return fields;
  }

  public void setFields(Collection<Field> fields) {
    this.fields = fields;
  }


  public boolean containsField(String name) {
    for (Field field : getFields()) {
      if (name.equalsIgnoreCase(field.getName())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return "Index [name=" + name + ", area=" + area + ", unique=" + unique + ", primary=" + primary
        + ", fields=" + fields + "]";
  }

  
}

