package com.artech.timetableapp.database.manager;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.manager.ITeachingLoadManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.model.prototype.GroupPrototype;
import com.artech.timetableapp.core.model.prototype.SubjectPrototype;
import com.artech.timetableapp.core.model.prototype.TeacherPrototype;
import com.artech.timetableapp.core.model.prototype.TeachingLoadPrototype;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class TeachingLoadManager extends DbObjectManager<TeachingLoadModel, TeachingLoadPrototype> implements ITeachingLoadManager {

    private final IObjectManager<TeacherModel, TeacherPrototype> teacherManager;
    private final IObjectManager<SubjectModel, SubjectPrototype> subjectManager;
    private final IObjectManager<GroupModel, GroupPrototype> groupManager;

    public TeachingLoadManager(DatabaseHandle handle,
                               IObjectManager<TeacherModel, TeacherPrototype> teacherManager,
                               IObjectManager<SubjectModel, SubjectPrototype> subjectManager,
                               IObjectManager<GroupModel, GroupPrototype> groupManager) throws SQLException {
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
                    teacherManager.get(result.getInt("teacher")),
                    subjectManager.get(result.getInt("subject")),
                    groupManager.get(result.getInt("group")),
                    result.getInt("hours_per_week")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(TeachingLoadPrototype prototype) {
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
}
