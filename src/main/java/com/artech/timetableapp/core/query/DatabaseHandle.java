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

    public int executeUpdate(SqlQuery query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query.query());
    }
    public ResultSet executeQuery(SqlQuery query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query.query());
    }
}
