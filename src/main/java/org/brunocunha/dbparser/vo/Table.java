package org.brunocunha.dbparser.vo;

import java.util.ArrayList;
import java.util.Collection;


public class Table {
private String name;
private String area;
private String label;
private String dump;
private String description;
private String banco;
private Collection<DatabaseTrigger> triggers;
private Collection<Field> fields;
private Collection<Index> indexes;

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
public Collection<DatabaseTrigger> getTriggers() {
	return triggers;
}
public void setTriggers(Collection<DatabaseTrigger> triggers) {
	this.triggers = triggers;
}
public Collection<Field> getFields() {
	return fields;
}
public void setFields(Collection<Field> fields) {
	this.fields = fields;
}
public Collection<Index> getIndexes() {
	return indexes;
}
public void setIndexes(Collection<Index> indexes) {
	this.indexes = indexes;
}
	
public void addField(Field field) {
	this.getFields().add(field);
}
public void addIndex(Index index) {
	this.getIndexes().add(index);
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}



}




  