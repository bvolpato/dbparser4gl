package org.brunocunha.dbparser.vo;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private String name;
    private List<Table> tables = new ArrayList<Table>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
