package com.artech.timetableapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class HelloApplication extends Application {
    private Connection connection = null;

    private boolean connect() {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            System.out.println("successfully connected");
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    private String readData(String columnName) {
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder builder = new StringBuilder();
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT " + columnName + " FROM teachers");

            while (resultSet.next())
            {
                builder.append(resultSet.getString(columnName) + "; ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return builder.toString();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        connect();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle(readData("first_name"));
        stage.setScene(scene);
        stage.show();
        connection.close();
    }

    public static void main(String[] args) {
        launch();
    }
}