package com.artech.timetableapp;

import com.artech.timetableapp.UI.MainWindow;
import com.artech.timetableapp.core.IApplication;
import com.artech.timetableapp.core.query.DatabaseHandle;
import com.artech.timetableapp.database.storage.DbStorage;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public final class TimetableApplication extends Application implements IApplication {

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
        MainWindow window = new MainWindow(this.storage);
        window.run(stage);
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


    @Override
    public IStorage getStorage() {
        return this.storage;
    }
}
