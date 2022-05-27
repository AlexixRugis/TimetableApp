package com.artech.timetableapp.core.query;

import java.io.File;
import java.sql.*;

public final class DatabaseHandle {
    private final String path;
    private Connection connection;

    public DatabaseHandle(String path) throws SQLException, ClassNotFoundException {
        this.path = path;
        connect();
    }

    public DatabaseHandle(File file) throws SQLException, ClassNotFoundException {
        this(file.getPath());
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + this.path);
    }

    public void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public PreparedStatement buildStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
}
