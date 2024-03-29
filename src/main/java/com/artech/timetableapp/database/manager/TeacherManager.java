package com.artech.timetableapp.database.manager;

import com.artech.timetableapp.core.manager.ITeacherManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Менеджер моделей преподавателей
 */
public final class TeacherManager extends DbObjectManager<TeacherModel> implements ITeacherManager {

    /**
     * Конструктор менеджера моделей специальностей
     * @param handle Дескриптор БД
     */
    public TeacherManager(DatabaseHandle handle) throws SQLException {
        super(handle, "teachers");
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS teachers (" +
                "id INTEGER PRIMARY KEY," +
                "first_name TEXT NOT NULL," +
                "second_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected TeacherModel build(ResultSet result) {
        try {
            return new TeacherModel(
                    result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("second_name"),
                    result.getString("last_name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(TeacherModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO teachers (first_name, second_name, last_name)" +
                    " VALUES (?, ?, ?)");
            statement.setString(1, model.firstName());
            statement.setString(2, model.secondName());
            statement.setString(3, model.lastName());
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
    public boolean tryUpdate(TeacherModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE teachers SET first_name = ?, second_name = ?, last_name = ? WHERE id = ?");
            statement.setString(1, model.firstName());
            statement.setString(2, model.secondName());
            statement.setString(3, model.lastName());
            statement.setInt(4, model.id());
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
