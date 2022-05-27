package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.prototype.SpecialityPrototype;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SpecialityManager extends DbObjectManager<SpecialityModel, SpecialityPrototype> {

    public SpecialityManager(DatabaseHandle handle) throws SQLException {
        super(handle, "teachers");
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
    public boolean tryCreate(SpecialityPrototype prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO specialities (name)" +
                    " VALUES (?)");
            statement.setString(1, prototype.name());
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
    public boolean tryUpdate(SpecialityModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE specialities SET name = ? WHERE id = ?");
            statement.setString(1, model.name());
            statement.setInt(2, model.id());
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
