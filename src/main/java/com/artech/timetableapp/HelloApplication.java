package com.artech.timetableapp;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.manager.TeacherManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.query.DatabaseHandle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        DatabaseHandle handle = new DatabaseHandle("sqlite.db");
        IObjectManager<TeacherModel> manager = new TeacherManager(handle);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        manager.tryCreate(new TeacherModel("abd", "sfd", "aefrewg"));
        manager.tryUpdate(1, new TeacherModel("ad", "sfd", "aefrgregewg"));
        manager.tryDelete(2);

        stage.setTitle(manager.get(1).firstName());
        stage.setScene(scene);
        stage.show();

        handle.closeConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}