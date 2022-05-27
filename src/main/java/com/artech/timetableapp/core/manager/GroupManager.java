package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.prototype.GroupPrototype;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class GroupManager extends DbObjectManager<GroupModel, GroupPrototype> {

    public GroupManager(DatabaseHandle handle) throws SQLException {
        super(handle, "teachers");
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS subjects (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "semester INTEGER NOT NULL," +
                "num_study_weeks INTEGER NOT NULL," +
                "speciality INTEGER REFERENCES specialities(id) ON DELETE SET NULL" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected GroupModel build(ResultSet result) {
        try {
            return new GroupModel(
                    result.getInt("id"),
                    result.getString("name"),
                    null, //TODO: loading from manager
                    result.getInt("semester"),
                    result.getInt("num_study_weeks")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(GroupPrototype prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO subjects (name, speciality, semester, num_study_weeks)" +
                    " VALUES (?, ?, ?, ?)");
            statement.setString(1, prototype.name());
            statement.setInt(2, prototype.speciality().id());
            statement.setInt(3, prototype.semester());
            statement.setInt(4, prototype.numberOfStudyWeeks());
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
    public boolean tryUpdate(GroupModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE subjects SET name = ?, speciality = ?, semester = ?, num_study_weeks = ? WHERE id = ?");
            statement.setString(1, model.name());
            statement.setInt(2, model.speciality().id());
            statement.setInt(3, model.semester());
            statement.setInt(4, model.numberOfStudyWeeks());
            statement.setInt(5, model.id());
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
