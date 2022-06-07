package com.artech.timetableapp.database.manager;

import com.artech.timetableapp.core.manager.IGroupManager;
import com.artech.timetableapp.core.manager.ITeachingLoadManager;
import com.artech.timetableapp.core.manager.ITimetableLessonManager;
import com.artech.timetableapp.core.model.*;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Менеджер моделей расписания
 */
public final class TimetableLessonManager extends DbObjectManager<TimetableLessonModel> implements ITimetableLessonManager {

    private final ITeachingLoadManager teachingLoadManager;

    private final IGroupManager groupManager;

    /**
     * Конструктор менеджера моделей расписания
     * @param handle Дескриптор БД
     * @param teachingLoadManager Менеджер моделей педагогических нагрузок
     * @param groupManager Менеджер моделей групп
     */
    public TimetableLessonManager(DatabaseHandle handle, ITeachingLoadManager teachingLoadManager, IGroupManager groupManager) throws SQLException {
        super(handle, "timetable_lessons");
        this.teachingLoadManager = teachingLoadManager;
        this.groupManager = groupManager;
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS timetable_lessons (" +
                "id INTEGER PRIMARY KEY," +
                "day_of_week INTEGER NOT NULL," +
                "lesson_number INTEGER NOT NULL," +
                "teaching_load INTEGER REFERENCES teaching_loads(id) ON DELETE CASCADE," +
                "`group` INTEGER REFERENCES groups(id) ON DELETE CASCADE" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected TimetableLessonModel build(ResultSet result) {
        try {
            return new TimetableLessonModel(
                    result.getInt("id"),
                    Day.get(result.getInt("day_of_week")),
                    result.getInt("lesson_number"),
                    teachingLoadManager.get(getInt(result, "teaching_load")),
                    groupManager.get(getInt(result, "group"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(TimetableLessonModel prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO timetable_lessons (day_of_week, lesson_number, teaching_load, `group`)" +
                    " VALUES (?, ?, ?, ?)");
            statement.setInt(1, prototype.day().getIndex());
            statement.setInt(2, prototype.lessonNumber());
            statement.setInt(3, prototype.load().id());
            statement.setInt(4, prototype.group().id());
            statement.executeUpdate();
            statement.close();

            handleUpdate();
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
            PreparedStatement statement = handle.buildStatement("UPDATE timetable_lessons SET day_of_week = ?, lesson_number = ?, teaching_load = ?, `group` = ? WHERE id = ?");
            statement.setInt(1, model.day().getIndex());
            statement.setInt(2, model.lessonNumber());
            statement.setInt(3, model.load().id());
            statement.setInt(4, model.group().id());
            statement.setInt(5, model.id());
            statement.executeUpdate();
            statement.close();

            handleUpdate();
            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public void setData(TimetableLessonModel model) {
        clearData(model.group(), model.day(), model.lessonNumber());
        tryCreate(model);
    }

    @Override
    public void clearData(GroupModel group, Day day, Integer lesson) {
        try {
            PreparedStatement statement = this.handle.buildStatement("DELETE FROM timetable_lessons WHERE day_of_week = ? AND lesson_number = ? AND `group` = ?");
            statement.setInt(1, day.getIndex());
            statement.setInt(2, lesson);
            statement.setInt(3, group.id());
            statement.executeUpdate();
            handleUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TimetableLessonModel getData(GroupModel group, Day day, Integer lesson) {
        try {
            PreparedStatement statement = handle.buildStatement("SELECT * FROM timetable_lessons WHERE day_of_week = ? AND lesson_number = ? AND `group` = ? LIMIT 1");
            statement.setInt(1, day.getIndex());
            statement.setInt(2, lesson);
            statement.setInt(3, group.id());
            ResultSet result = statement.executeQuery();
            TimetableLessonModel model = null;
            if (result.isBeforeFirst()) model = build(result);
            result.close();
            return model;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<TimetableLessonModel> getData(GroupModel group) {
        List<TimetableLessonModel> models = new ArrayList<>();

        try {
            PreparedStatement statement = handle.buildStatement("SELECT * " +
                    "FROM timetable_lessons WHERE `group` = ?");
            statement.setInt(1, group.id());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                models.add(build(result));
            }

            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    @Override
    public Collection<TimetableLessonModel> getData(TeacherModel model, Day day, Integer lesson) {
        List<TimetableLessonModel> models = new ArrayList<>();

        try {
            PreparedStatement statement = handle.buildStatement("SELECT timetable_lessons.id, day_of_week, lesson_number, teaching_load, timetable_lessons.`group` " +
                    "FROM timetable_lessons, teaching_loads WHERE timetable_lessons.teaching_load = teaching_loads.id AND " +
                    "day_of_week = ? AND lesson_number = ? AND teaching_loads.teacher = ?");
            statement.setInt(1, day.getIndex());
            statement.setInt(2, lesson);
            statement.setInt(3, model.id());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                models.add(build(result));
            }

            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    @Override
    public Integer getHours(GroupModel group) {
        try {
            PreparedStatement statement = handle.buildStatement("SELECT COUNT(*) FROM timetable_lessons WHERE `group` = ?");
            statement.setInt(1, group.id());
            ResultSet result = statement.executeQuery();
            Integer count = getInt(result, "COUNT(*)");
            result.close();
            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getHours(TeachingLoadModel teachingLoad) {
        try {
            PreparedStatement statement = handle.buildStatement("SELECT COUNT(*) FROM timetable_lessons WHERE teaching_load = ?");
            statement.setInt(1, teachingLoad.id());
            ResultSet result = statement.executeQuery();
            Integer count = getInt(result, "COUNT(*)");
            result.close();
            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
