package com.artech.timetableapp;

import com.artech.timetableapp.UI.MainWindow;
import com.artech.timetableapp.core.IApplication;
import com.artech.timetableapp.core.ISettings;
import com.artech.timetableapp.core.query.DatabaseHandle;
import com.artech.timetableapp.database.storage.DbStorage;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Приложение
 */
public final class TimetableApplication extends Application implements IApplication {
    private final ISettings settings = new ISettings() {
        @Override
        public Integer getLessonsPerDay() {
            return 6;
        }

        @Override
        public Integer getLessonsPerWeek() {
            return 36;
        }
    };
    private static TimetableApplication instance;
    private final String databasePath;
    private IStorage storage;
    private Stage primaryStage;

    /**
     * Конструктор приложения
     */
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

    /**
     * Запускает приложение
     * @param stage Контейнер приложения
     */
    private void run(Stage stage) {
        createStorage();
        createUI(stage);
    }

    /**
     * Создает пользователский интерфейс
     * @param stage онтейнер приложения
     */
    private void createUI(Stage stage) {
        primaryStage = stage;
        MainWindow window = new MainWindow(this.storage);
        window.run(stage);
    }

    /**
     * Создает хранилище данных
     */
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

    /**
     * Создает дескриптор БД
     * @return Дескриптор БД
     */
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

    @Override
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static TimetableApplication getInstance() {
        if (instance == null) instance = new TimetableApplication();
        return instance;
    }

    @Override
    public ISettings getSettings() {
        return settings;
    }
}
