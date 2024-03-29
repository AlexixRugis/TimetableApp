package com.artech.timetableapp.database.manager;

import com.artech.timetableapp.core.manager.IGroupManager;
import com.artech.timetableapp.core.manager.ISubjectManager;
import com.artech.timetableapp.core.manager.ITeacherManager;
import com.artech.timetableapp.core.manager.ITeachingLoadManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Менеджер моделей педагогических нагрузок
 */
public final class TeachingLoadManager extends DbObjectManager<TeachingLoadModel> implements ITeachingLoadManager {

    private final ITeacherManager teacherManager;

    private final ISubjectManager subjectManager;

    private final IGroupManager groupManager;

    /**
     * Конструктор менеджера моделей педагогических нагрузок
     * @param handle Дескриптор БД
     * @param teacherManager Менеджер моделей преподавателей
     * @param subjectManager Менеджер моделей дисциплин
     * @param groupManager Менеджер моделей групп
     */
    public TeachingLoadManager(DatabaseHandle handle,
                               ITeacherManager teacherManager,
                               ISubjectManager subjectManager,
                               IGroupManager groupManager) throws SQLException {
        super(handle, "teaching_loads");
        this.teacherManager = teacherManager;
        this.subjectManager = subjectManager;
        this.groupManager = groupManager;
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS teaching_loads (" +
                "id INTEGER PRIMARY KEY," +
                "teacher INTEGER REFERENCES teachers(id) ON DELETE SET NULL," +
                "subject INTEGER REFERENCES subjects(id) ON DELETE SET NULL," +
                "`group` INTEGER REFERENCES groups(id) ON DELETE SET NULL," +
                "hours_per_week INTEGER NOT NULL" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected TeachingLoadModel build(ResultSet result) {
        try {
            return new TeachingLoadModel(
                    result.getInt("id"),
                    teacherManager.get(getInt(result, "teacher")),
                    subjectManager.get(getInt(result, "subject")),
                    groupManager.get(getInt(result, "group")),
                    result.getInt("hours_per_week")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(TeachingLoadModel prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO teaching_loads (teacher, subject, `group`, hours_per_week)" +
                    " VALUES (?, ?, ?, ?)");
            statement.setInt(1, prototype.teacher().id());
            statement.setInt(2, prototype.subject().id());
            statement.setInt(3, prototype.group().id());
            statement.setInt(4, prototype.hoursPerWeek());
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
    public boolean tryUpdate(TeachingLoadModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE teaching_loads SET teacher = ?, subject = ?, `group` = ?, hours_per_week = ? WHERE id = ?");
            statement.setInt(1, model.teacher().id());
            statement.setInt(2, model.subject().id());
            statement.setInt(3, model.group().id());
            statement.setInt(4, model.hoursPerWeek());
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
    public Collection<TeachingLoadModel> getByGroup(GroupModel group) {
        List<TeachingLoadModel> models = new ArrayList<>();
        if (group == null) return models;

        try {
            PreparedStatement statement = handle.buildStatement("SELECT * FROM teaching_loads WHERE `group` = ?");
            statement.setInt(1, group.id());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                models.add(build(result));
            }

            result.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return models;
    }
}
