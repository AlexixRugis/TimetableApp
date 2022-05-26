package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.query.DatabaseHandle;
import com.artech.timetableapp.core.query.SqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class TeacherManager extends ObjectManager<TeacherModel> {

    public TeacherManager(DatabaseHandle handle) throws SQLException {
        super(handle, "teachers");
    }

    protected void initialize() throws SQLException {
        handle.executeUpdate(
                new SqlQuery("CREATE TABLE IF NOT EXISTS teachers (" +
                        "id INTEGER PRIMARY KEY," +
                        "first_name TEXT NOT NULL," +
                        "second_name TEXT NOT NULL," +
                        "last_name TEXT NOT NULL" +
                        ")")
        );
    }

    protected TeacherModel build(ResultSet result) {
        try {
            return new TeacherModel(
                    result.getString("first_name"),
                    result.getString("second_name"),
                    result.getString("last_name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer create(TeacherModel model) {
        return null;
    }

    @Override
    public void update(Integer pk, TeacherModel model) {
//TODO: implement
    }
}
