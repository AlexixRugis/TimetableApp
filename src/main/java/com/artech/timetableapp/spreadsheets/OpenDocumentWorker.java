/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artech.timetableapp.spreadsheets;

import com.github.miachm.sods.SpreadSheet;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author TOWARICH_LESHIY
 */
public final class OpenDocumentWorker extends SpreadSheetWorker {
    private final SpreadSheet spreadSheet;
    private int selectedSheet = 0;
    
    public OpenDocumentWorker(File file) throws IOException {
        super(file);
        this.spreadSheet = new SpreadSheet(file);
    }
    
    @Override
    public void selectSheet(int id) {
        assert spreadSheet != null;
        assert id >= 0 && id < spreadSheet.getNumSheets();
        selectedSheet = id;
    }

    @Override
    public int getSheetCount() {
        assert spreadSheet != null;
        return spreadSheet.getNumSheets();
    }

    @Override
    public void setValue(int x, int y, Object value) {
        assert spreadSheet != null;
        spreadSheet.getSheet(selectedSheet).getDataRange().getCell(y, x).setValue(value);
    }

    @Override
    public Object getValue(int x, int y) {
        assert spreadSheet != null;
        
        System.out.println(spreadSheet.getSheet(selectedSheet).getDataRange().getCell(y, x).getValue());
        return spreadSheet.getSheet(selectedSheet).getDataRange().getCell(y, x).getValue();
    }

    @Override
    public int getColumnCount() {
        assert spreadSheet != null;
        return spreadSheet.getSheet(selectedSheet).getMaxColumns();
    }

    @Override
    public int getRowCount() {
        assert spreadSheet != null;
        return spreadSheet.getSheet(selectedSheet).getMaxRows();
    }

    @Override
    public String getSheetName() {
        assert spreadSheet != null;
        return spreadSheet.getSheet(selectedSheet).getName();
    }
    
}
