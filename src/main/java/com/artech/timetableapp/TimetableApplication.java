package com.artech.timetableapp;

import com.artech.timetableapp.core.query.DatabaseHandle;
import com.artech.timetableapp.core.storage.DbStorage;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public final class TimetableApplication extends Application {

    private final String databasePath;
    private IStorage storage;

    public TimetableApplication() {
        this.databasePath = "sqlite.db";
    }

    public static void main(String[] args) {
        TimetableApplication.launch();
    }

    @Override
    public void start(Stage stage) {
        run(stage);
    }

    private void run(Stage stage) {
        createStorage();
        createUI(stage);
    }

    private void createUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(TimetableApplication.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("hello");
        stage.setScene(scene);
        stage.show();
    }

    private void createStorage() {
        DatabaseHandle handle = connectDatabase();
        try {
            storage = new DbStorage(handle);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error during initializing data storage");
        }

    }

    private DatabaseHandle connectDatabase() {
        try {
            return new DatabaseHandle(databasePath);
        }
        catch (Exception exception) {
            throw new RuntimeException("Error connecting to database");
        }
    }


}
