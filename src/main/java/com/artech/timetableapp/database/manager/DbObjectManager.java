package com.artech.timetableapp.database.manager;

import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DbObjectManager<T extends IModel> implements IObjectManager<T> {

    private final List<IManagerUpdateListener> listeners = new ArrayList<>();
    protected final DatabaseHandle handle;
    protected final String tableName;

    private final String getQuery;
    private final String getAllQuery;
    private final String deleteQuery;

    public DbObjectManager(DatabaseHandle handle, String tableName) throws SQLException {
        this.handle = handle;
        this.tableName = tableName;
        this.getQuery = "SELECT * FROM " + tableName + " WHERE id = ? LIMIT 1";
        this.getAllQuery = "SELECT * FROM " + tableName;
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";

        tryCreateTable();

    }


    protected abstract void tryCreateTable() throws SQLException;
    protected abstract T build(ResultSet resultSet);

    @Override
    public T get(Integer id) {
        if (id == 0) return null;

        try {
            PreparedStatement statement = handle.buildStatement(getQuery);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            T model = build(result);
            result.close();
            return model;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<T> getAll() {
        List<T> models = new ArrayList<>();

        try {
            PreparedStatement statement = handle.buildStatement(getAllQuery);
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

    @Override
    public boolean tryDelete(T model) {
        try {
            PreparedStatement statement = handle.buildStatement(deleteQuery);
            statement.setInt(1, model.id());
            int result = statement.executeUpdate();
            statement.close();

            handleUpdate();
            return result > 0;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    protected void handleUpdate() {
        for (IManagerUpdateListener listener : listeners) {
            if (listener != null) listener.onUpdate();
        }
    }

    @Override
    public void addUpdateListener(IManagerUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeUpdateListener(IManagerUpdateListener listener) {
        listeners.remove(listener);
    }
}
