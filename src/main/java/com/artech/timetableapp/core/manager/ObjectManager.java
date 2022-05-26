package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.query.DatabaseHandle;
import com.artech.timetableapp.core.query.SqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ObjectManager<T> {
    protected final DatabaseHandle handle;
    protected final String tableName;

    public ObjectManager(DatabaseHandle handle, String tableName) throws SQLException {
        this.handle = handle;
        this.tableName = tableName;

        initialize();
    }

    protected abstract void initialize() throws SQLException;
    protected abstract T build(ResultSet resultSet);
    public abstract Integer create(T model);
    public abstract void update(Integer pk, T model);

    public T get(Integer pk) {
        try {
            ResultSet result = handle.executeQuery(
                    new SqlQuery("SELECT * FROM "+ this.tableName +" WHERE id = " + pk + " LIMIT 1")
            );
            return build(result);
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return null;
    }

    public Collection<T> getAll() {
        List<T> models = new ArrayList<>();

        try {
            ResultSet result = handle.executeQuery(
                    new SqlQuery("SELECT * FROM " + this.tableName)
            );

            while (result.next()) {
                models.add(build(result));
            }
        }
        catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return models;
    }

    public void delete(Integer pk) {
        try {
            handle.executeUpdate(new SqlQuery("DELETE FROM " + tableName + " WHERE id = " + pk));
        }
        catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
