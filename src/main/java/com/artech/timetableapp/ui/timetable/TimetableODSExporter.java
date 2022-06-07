package com.artech.timetableapp.ui.timetable;

import com.artech.timetableapp.ui.exporting.BaseExporter;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.storage.IStorage;
import com.github.miachm.sods.Color;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class TimetableODSExporter extends BaseExporter<GroupModel> {

    public TimetableODSExporter(IStorage storage, GroupModel model) {
        super(storage, model);
    }

    @Override
    protected void export(File file) {


        Sheet sheet = new Sheet("Расписание");

        sheet.appendColumns(Day.values().length);

        sheet.appendRow();
        sheet.getDataRange().getCell(0, 0).setValue(this.model.name());
        sheet.getDataRange().getCell(0, 1).setValue(this.model.speciality());
        sheet.getDataRange().getCell(0, 2).setValue(String.format("%d семестр", this.model.semester()));

        Collection<TimetableLessonModel> lessons = this.storage.timetableLessonManager().getData(this.model);
        int maxLesson = 0;
        for (TimetableLessonModel model : lessons) {
            if (model.lessonNumber() > maxLesson) {
                maxLesson = model.lessonNumber();
            }
        }
        sheet.appendRows(maxLesson + 2);
        final int startRow = 2;
        for (Day day : Day.values()) {
            if (day.getIndex() < 1) continue;
            sheet.getDataRange().getCell(startRow, day.getIndex() - 1).setValue(day.toString());
            sheet.getDataRange().getCell(startRow, day.getIndex() - 1).setBackgroundColor(new Color(180, 180, 180));
        }

        for (TimetableLessonModel model : lessons) {
            sheet.getDataRange().getCell(startRow + model.lessonNumber(), model.day().getIndex() - 1).setValue(String.format("%s\n%s", model.load().subject().name(), model.load().teacher()));
        }

        sheet.setColumnWidths(0, Day.values().length, 70.0);
        sheet.setRowHeights(0, maxLesson + 3, 15.0);

        SpreadSheet spreadSheet = new SpreadSheet();
        spreadSheet.appendSheet(sheet);
        try {
            spreadSheet.save(file);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected String getFileName() {
        return String.format("%s.ods", fixName(this.model.toString()));
    }
}
