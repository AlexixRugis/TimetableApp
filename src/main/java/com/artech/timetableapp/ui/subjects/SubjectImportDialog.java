package com.artech.timetableapp.ui.subjects;

import com.artech.timetableapp.ui.importing.ImportingActionDialog;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.spreadsheets.OpenDocumentWorker;
import com.artech.timetableapp.spreadsheets.SpreadSheetWorker;

import java.io.IOException;

public class SubjectImportDialog extends ImportingActionDialog {
    public SubjectImportDialog(IStorage storage) {
        super(storage);
    }

    @Override
    protected void handleImport() {
        try {
            SpreadSheetWorker worker = new OpenDocumentWorker(this.view.getSelectedFile());
            int rowCount = worker.getRowCount();

            if (this.view.getDeleteData()) this.storage.subjectManager().clearAllData();

            for (int i = 0; i < rowCount; i++) {
                String name = worker.getValue(0, i).toString();
                Integer semester = (int)(double) worker.getValue(1, i);
                SpecialityModel speciality = this.storage.specialityManager().get((int)(double) worker.getValue(2, i));
                this.storage.subjectManager().tryCreate(new SubjectModel(0, name, speciality, semester));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
