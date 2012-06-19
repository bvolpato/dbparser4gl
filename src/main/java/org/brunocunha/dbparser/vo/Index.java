package org.brunocunha.dbparser.vo;

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
		return fields;
	}

	public void setFields(Collection<Field> fields) {
		this.fields = fields;
	}

}

/*
 * 
 * 
 * ADD INDEX "pedido" ON "al-pr-oc" AREA "Schema Area" UNIQUE PRIMARY
 * INDEX-FIELD "nome-abrev" ASCENDING INDEX-FIELD "nr-pedcli" ASCENDING
 * INDEX-FIELD "numero-ordem" ASCENDING INDEX-FIELD "parcela" ASCENDING
 */
