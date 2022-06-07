package com.artech.timetableapp.core.query;

import java.io.File;
import java.sql.*;

/**
 * Дескриптор базы данных
 */
public final class DatabaseHandle {
    /**
     * Путь к базе данных
     */
    private final String path;
    /**
     * Подключение к базе данных
     */
    private Connection connection;

    /**
     * Констурктор дескриптора
     * @param path Путь к БД
     */
    public DatabaseHandle(String path) throws SQLException, ClassNotFoundException {
        this.path = path;
        connect();
    }

    /**
     * Конструктор дескриптора
     * @param file Файл БД
     */
    public DatabaseHandle(File file) throws SQLException, ClassNotFoundException {
        this(file.getPath());
    }

    /**
     * Подключает дескриптор к БД
     */
    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + this.path);
    }

    /**
     * отключает дескриптор от Бд
     */
    public void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    /**
     * Создает запрос БД
     * @param query Запрос
     * @return Prepared-запрос к БД
     */
    public PreparedStatement buildStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
}
