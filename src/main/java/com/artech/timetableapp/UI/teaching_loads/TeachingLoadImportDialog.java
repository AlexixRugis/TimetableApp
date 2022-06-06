package com.artech.timetableapp.UI.teaching_loads;

import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.core.model.*;
import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.spreadsheets.OpenDocumentWorker;
import com.artech.timetableapp.spreadsheets.SpreadSheetWorker;

import java.io.IOException;

public class TeachingLoadImportDialog extends ImportingActionDialog {
    public TeachingLoadImportDialog(IStorage storage) {
        super(storage);
    }

    @Override
    protected void handleImport() {
        try {
            SpreadSheetWorker worker = new OpenDocumentWorker(this.view.getSelectedFile());
            int rowCount = worker.getRowCount();

            if (this.view.getDeleteData()) this.storage.teachingLoadManager().clearAllData();

            for (int i = 0; i < rowCount; i++) {
                TeacherModel teacher = this.storage.teacherManager().get((int)(double) worker.getValue(0, i));
                GroupModel group = this.storage.groupManager().get((int)(double) worker.getValue(1, i));
                SubjectModel subject = this.storage.subjectManager().get((int)(double) worker.getValue(2, i));
                Integer hours = (int)(double) worker.getValue(3, i);
                this.storage.teachingLoadManager().tryCreate(new TeachingLoadModel(0, teacher, subject, group, hours));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
