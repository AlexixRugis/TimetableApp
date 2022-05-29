package com.artech.timetableapp.UI;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Views.IView;
import com.artech.timetableapp.UI.Views.TeachersView;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public final class MainWindow {

    private final IStorage storage;

    public MainWindow(IStorage storage) {
        this.storage = storage;
    }

    private final String FXML = "main-view.fxml";

    public void run(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(TimetableApplication.class.getResource(FXML));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1600, 900);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("my app");
        stage.setScene(scene);
        stage.show();

        setupTabs(scene);
    }

    private void setupTabs(Scene scene) {
        TabPane pane = (TabPane)scene.lookup("#tab_layout");

        addTab(pane, new TeachersView(this.storage));
    }

    private void addTab(TabPane pane, IView view) {
        Tab tab = new Tab(view.getName());
        tab.setContent(view.getContent());
        pane.getTabs().add(tab);
    }
}