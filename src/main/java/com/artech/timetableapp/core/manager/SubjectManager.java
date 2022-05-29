package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.model.prototype.SpecialityPrototype;
import com.artech.timetableapp.core.model.prototype.SubjectPrototype;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SubjectManager extends DbObjectManager<SubjectModel, SubjectPrototype> {
    private final IObjectManager<SpecialityModel, SpecialityPrototype> specialityManager;

    public SubjectManager(DatabaseHandle handle, IObjectManager<SpecialityModel, SpecialityPrototype> specialityManager) throws SQLException {
        super(handle, "subjects");
        this.specialityManager = specialityManager;
    }

    @Override
    protected void tryCreateTable() throws SQLException {
        PreparedStatement statement = handle.buildStatement("CREATE TABLE IF NOT EXISTS subjects (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "semester INTEGER NOT NULL," +
                "speciality INTEGER REFERENCES specialities(id) ON DELETE SET NULL" +
                ")");
        statement.executeUpdate();
        statement.close();
    }

    @Override
    protected SubjectModel build(ResultSet result) {
        try {
            return new SubjectModel(
                    result.getInt("id"),
                    result.getString("name"),
                    specialityManager.get(result.getInt("speciality")),
                    result.getInt("semester")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tryCreate(SubjectPrototype prototype) {
        try {
            PreparedStatement statement = handle.buildStatement("INSERT INTO subjects (name, speciality, semester)" +
                    " VALUES (?, ?, ?)");
            statement.setString(1, prototype.name());
            statement.setInt(2, prototype.speciality().id());
            statement.setInt(3, prototype.semester());
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
    public boolean tryUpdate(SubjectModel model) {
        try {
            PreparedStatement statement = handle.buildStatement("UPDATE subjects SET name = ?, speciality = ?, semester = ? WHERE id = ?");
            statement.setString(1, model.name());
            statement.setInt(2, model.speciality().id());
            statement.setInt(3, model.semester());
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
