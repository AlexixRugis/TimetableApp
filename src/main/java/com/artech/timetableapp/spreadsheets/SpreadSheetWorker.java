/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artech.timetableapp.spreadsheets;

import java.io.File;

/**
 *
 * @author TOWARICH_LESHIY
 */
public abstract class SpreadSheetWorker {
    
    protected final File file;
    
    public SpreadSheetWorker(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return file;
    }
    
    public abstract String getSheetName();
    public abstract void selectSheet(int id);
    public abstract int getSheetCount();
    public abstract int getColumnCount();
    public abstract int getRowCount();
    public abstract void setValue(int x, int y, Object value);
    public abstract Object getValue(int x, int y);
}
