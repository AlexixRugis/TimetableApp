package com.artech.timetableapp.database.manager;

import com.artech.timetableapp.core.manager.ISpecialityManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Менеджер моделей специальностей
 */
public final class SpecialityManager extends DbObjectManager<SpecialityModel> implements ISpecialityManager {

    /**
     * Конструктор менеджер моделей специальностей
     * @param handle Дескриптор БД
     */
    public SpecialityManager(DatabaseHandle handle) throws SQLException {
        super(handle, "specialities");
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS specialities (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected SpecialityModel build(ResultSet result) {
        try {
            return new SpecialityModel(
                    result.getInt("id"),
                    result.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(SpecialityModel prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO specialities (name)" +
                    " VALUES (?)");
            statement.setString(1, prototype.name());
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
    public boolean tryUpdate(SpecialityModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE specialities SET name = ? WHERE id = ?");
            statement.setString(1, model.name());
            statement.setInt(2, model.id());
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
