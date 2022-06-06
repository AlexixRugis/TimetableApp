package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.spreadsheets.OpenDocumentWorker;
import com.artech.timetableapp.spreadsheets.SpreadSheetWorker;

import java.io.IOException;

public class SpecialitiesImportDialog extends ImportingActionDialog {

    public SpecialitiesImportDialog(IStorage storage) {
        super(storage);
    }

    @Override
    protected void handleImport() {
        try {
            SpreadSheetWorker worker = new OpenDocumentWorker(this.view.getSelectedFile());
            int rowCount = worker.getRowCount();

            if (this.view.getDeleteData()) this.storage.specialityManager().clearAllData();

            for (int i = 0; i < rowCount; i++) {
                String name = worker.getValue(0, i).toString();
                this.storage.specialityManager().tryCreate(new SpecialityModel(0, name));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
