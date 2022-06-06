package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.spreadsheets.OpenDocumentWorker;
import com.artech.timetableapp.spreadsheets.SpreadSheetWorker;

import java.io.IOException;

public class TeachersImportDialog extends ImportingActionDialog {
    public TeachersImportDialog(IStorage storage) {
        super(storage);
    }

    @Override
    protected void handleImport() {
        try {
            SpreadSheetWorker worker = new OpenDocumentWorker(this.view.getSelectedFile());
            int rowCount = worker.getRowCount();

            if (this.view.getDeleteData()) this.storage.teacherManager().clearAllData();

            for (int i = 0; i < rowCount; i++) {
                String firstName = worker.getValue(1, i).toString();
                String secondName = worker.getValue(2, i).toString();
                String lastName = worker.getValue(0, i).toString();
                this.storage.teacherManager().tryCreate(new TeacherModel(0, firstName, secondName, lastName));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
