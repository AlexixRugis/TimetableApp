package com.artech.timetableapp.UI.groups;

import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.spreadsheets.OpenDocumentWorker;
import com.artech.timetableapp.spreadsheets.SpreadSheetWorker;

import java.io.IOException;

public class GroupImportDialog extends ImportingActionDialog {
    public GroupImportDialog(IStorage storage) {
        super(storage);
    }

    @Override
    protected void handleImport() {
        try {
            SpreadSheetWorker worker = new OpenDocumentWorker(this.view.getSelectedFile());
            int rowCount = worker.getRowCount();

            if (this.view.getDeleteData()) this.storage.groupManager().clearAllData();

            for (int i = 0; i < rowCount; i++) {
                String name = worker.getValue(0, i).toString();
                Integer semester = (int)(double) worker.getValue(1, i);
                SpecialityModel speciality = this.storage.specialityManager().get((int)(double) worker.getValue(2, i));
                Integer studyWeeks = (int)(double) worker.getValue(3, i);
                this.storage.groupManager().tryCreate(new GroupModel(0, name, speciality, semester, studyWeeks));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
