package com.artech.timetableapp.dataimporting;

import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.spreadsheets.SpreadSheetWorker;

public abstract class SpreadSheetImporter {
    protected final IStorage storage;
    protected final SpreadSheetWorker worker;

    public SpreadSheetImporter(IStorage storage, SpreadSheetWorker worker) {
        this.storage = storage;
        this.worker = worker;
    }

    public abstract void process();
}
