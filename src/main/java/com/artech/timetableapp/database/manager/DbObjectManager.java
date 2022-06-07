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

/**
 * Менеджер коллеций моделей на основе БД
 * @param <T> Класс модели
 */
public abstract class DbObjectManager<T extends IModel> implements IObjectManager<T> {

    /**
     * Слушатели события обновления коллекции
     */
    private final List<IManagerUpdateListener> listeners = new ArrayList<>();

    /**
     * Дескриптор БД
     */
    protected final DatabaseHandle handle;

    /**
     * Название таблицы БД
     */
    protected final String tableName;

    /**
     * Запрос получения модели
     */
    private final String getQuery;

    /**
     * Запрос получения коллекции
     */
    private final String getAllQuery;

    /**
     * Запрос удаления модели из БД
     */
    private final String deleteQuery;

    /**
     * Запрос очистки коллекции
     */
    private final String clearQuery;

    /**
     * Конструктор менеджера
     * @param handle Дескриптор БД
     * @param tableName Название таблицы БД
     */
    public DbObjectManager(DatabaseHandle handle, String tableName) throws SQLException {
        this.handle = handle;
        this.tableName = tableName;
        this.getQuery = "SELECT * FROM " + tableName + " WHERE id = ? LIMIT 1";
        this.getAllQuery = "SELECT * FROM " + tableName;
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
        this.clearQuery = "DELETE FROM " + tableName;

        tryCreateTable();
    }


    /**
     * Создает таблицу в БД, если она отсутствует
     */
    protected abstract void tryCreateTable() throws SQLException;

    /**
     * Создает модель на основе данных из БД
     * @param resultSet Результат выполнения запроса БД
     * @return Модель
     */
    protected abstract T build(ResultSet resultSet);

    @Override
    public T get(Integer id) {
        if (id == null) return null;

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

    @Override
    public void clearAllData() {
        try {
            PreparedStatement statement = handle.buildStatement(clearQuery);
            statement.executeUpdate();
            statement.close();

            handleUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Обновляет состояния слушателей события обновления коллекции
     */
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

    /**
     * Получает Целочисленное значение из столбца БД
     * @param resultSet Результат выполненя запроса БД
     * @param column Название столбца БД
     * @return Целочисленное значение столбца
     */
    protected static Integer getInt(ResultSet resultSet, String column) throws SQLException {
        int value = resultSet.getInt(column);
        return resultSet.wasNull() ? null : value;
    }
}
