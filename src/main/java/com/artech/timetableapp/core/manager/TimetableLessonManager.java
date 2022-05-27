package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.model.prototype.SpecialityPrototype;
import com.artech.timetableapp.core.model.prototype.TeachingLoadPrototype;
import com.artech.timetableapp.core.model.prototype.TimetableLessonPrototype;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class TimetableLessonManager extends DbObjectManager<TimetableLessonModel, TimetableLessonPrototype> {

    private final IObjectManager<TeachingLoadModel, TeachingLoadPrototype> teachingLoadManager;

    public TimetableLessonManager(DatabaseHandle handle, IObjectManager<TeachingLoadModel, TeachingLoadPrototype> teachingLoadManager) throws SQLException {
        super(handle, "timetable_lessons");
        this.teachingLoadManager = teachingLoadManager;
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS timetable_lessons (" +
                "id INTEGER PRIMARY KEY," +
                "day_of_week INTEGER NOT NULL," +
                "lesson_number INTEGER NOT NULL," +
                "teaching_load INTEGER REFERENCES teaching_loads(id) ON DELETE SET NULL" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected TimetableLessonModel build(ResultSet result) {
        try {
            return new TimetableLessonModel(
                    result.getInt("id"),
                    Day.get(result.getInt("dayOfWeek")),
                    result.getInt("lesson_number"),
                    teachingLoadManager.get(result.getInt("teaching_load"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(TimetableLessonPrototype prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO timetable_lessons (day_of_week, lesson_number, teaching_load)" +
                    " VALUES (?, ?, ?)");
            statement.setInt(1, prototype.day().getIndex());
            statement.setInt(2, prototype.lessonNumber());
            statement.setInt(3, prototype.load().id());
            statement.executeUpdate();
            statement.close();

            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean tryUpdate(TimetableLessonModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE timetable_lessons SET day_of_week = ?, lesson_number = ?, teaching_load = ? WHERE id = ?");
            statement.setInt(1, model.day().getIndex());
            statement.setInt(2, model.lessonNumber());
            statement.setInt(3, model.load().id());
            statement.setInt(4, model.id());
            statement.executeUpdate();
            statement.close();

            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
