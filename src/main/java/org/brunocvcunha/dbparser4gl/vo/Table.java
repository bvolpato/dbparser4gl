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
import java.util.List;

public class Table {

  private String name;
  private String area;
  private String label;
  private String dump;
  private String description;
  private String banco;
  private List<DatabaseTrigger> triggers;
  private List<Field> fields;
  private List<Index> indexes;

  public Table() {
    this.triggers = new ArrayList<DatabaseTrigger>();
    this.fields = new ArrayList<Field>();
    this.indexes = new ArrayList<Index>();
  }

  public String getBanco() {
    return banco;
  }

  public void setBanco(String banco) {
    this.banco = banco;
  }

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

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getDump() {
    return dump;
  }

  public void setDump(String dump) {
    this.dump = dump;
  }

  public List<DatabaseTrigger> getTriggers() {
    return triggers;
  }

  public void setTriggers(List<DatabaseTrigger> triggers) {
    this.triggers = triggers;
  }

  public List<Field> getFields() {
    if (fields == null) {
      fields = new ArrayList<Field>();
    }
    return fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  public List<Index> getIndexes() {
    if (indexes == null) {
      indexes = new ArrayList<Index>();
    }
    return indexes;
  }

  public void setIndexes(List<Index> indexes) {
    this.indexes = indexes;
  }

  public void addField(Field field) {
    this.getFields().add(field);
  }

  public void addIndex(Index index) {
    this.getIndexes().add(index);
  }

  public String getDescription() {
    if (description == null) {
      description = "";
    }

    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DatabaseTrigger getCreateTrigger() {
    return this.getTrigger("Create");
  }

  public DatabaseTrigger getDeleteTrigger() {
    return this.getTrigger("Delete");
  }

  public DatabaseTrigger getWriteTrigger() {
    return this.getTrigger("Write");
  }

  public String getCreateTriggerProcedure() {
    DatabaseTrigger trigger = this.getCreateTrigger();

    if (trigger == null || trigger.getProcedure().equals("")) {
      return "";
    } else {
      return trigger.getProcedure();
    }
  }

  public String getWriteTriggerProcedure() {
    DatabaseTrigger trigger = this.getWriteTrigger();

    if (trigger == null || trigger.getProcedure().equals("")) {
      return "";
    } else {
      return trigger.getProcedure();
    }
  }

  public String getDeleteTriggerProcedure() {
    DatabaseTrigger trigger = this.getDeleteTrigger();

    if (trigger == null || trigger.getProcedure().equals("")) {
      return "";
    } else {
      return trigger.getProcedure();
    }
  }

  public DatabaseTrigger getTrigger(String name) {
    for (DatabaseTrigger trigger : this.getTriggers()) {
      if (trigger.getType().equals(name)) {
        return trigger;
      }
    }

    return null;
  }

  public Index findIndex(String name) {
    for (Index index : getIndexes()) {
      if (name.equalsIgnoreCase(index.getName())) {
        return index;
      }
    }
    return null;
  }

  public Field findField(String name) {
    for (Field field : getFields()) {
      if (name.equalsIgnoreCase(field.getName())) {
        return field;
      }
    }
    return null;
  }

  public boolean containsField(String name) {
    return this.findField(name) != null;
  }

  public List<Index> getFieldIndexes(String name) {
    return this.getFieldIndexes(this.findField(name));
  }

  public List<Index> getFieldIndexes(Field field) {
    List<Index> indexes = new ArrayList<Index>();

    for (Index index : getIndexes()) {
      if (index.getFields().contains(field)) {
        indexes.add(index);
      }
    }
    return indexes;
  }

  @Override
  public String toString() {
    return "Table [name=" + name + ", area=" + area + ", label=" + label + ", dump=" + dump
        + ", description=" + description + ", banco=" + banco + ", triggers=" + triggers
        + ", fields=" + fields + ", indexes=" + indexes + "]";
  }


}
