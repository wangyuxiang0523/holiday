package com.fh.util.excelUtils;

import java.util.List;

public class ExcelBean {

    private String title;

    private String mkdir;
    private String sheetName;

    private List<String> columns;

    private List<String> columnNames;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMkdir() {
        return mkdir;
    }

    public void setMkdir(String mkdir) {
        this.mkdir = mkdir;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }
}
