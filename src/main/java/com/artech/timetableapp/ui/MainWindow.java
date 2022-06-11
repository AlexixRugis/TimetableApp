package com.artech.timetableapp.ui;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.core.ISettings;
import com.artech.timetableapp.ui.Views.View;
import com.artech.timetableapp.ui.groups.GroupsView;
import com.artech.timetableapp.ui.specialities.SpecialitiesView;
import com.artech.timetableapp.ui.subjects.SubjectsView;
import com.artech.timetableapp.ui.teachers.TeachersView;
import com.artech.timetableapp.ui.teaching_loads.TeachingLoadsView;
import com.artech.timetableapp.ui.timetable.TimetableView;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Главное окно приложения
 * @param storage Хранилище данных
 */
public record MainWindow(IStorage storage) {
    /**
     * Запускает приложение
     *
     * @param stage Главный контейнер приложения
     */
    public void run(Stage stage) {
        String FXML = "main-view.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(TimetableApplication.class.getResource(FXML));
        Scene scene;
        try {
            ISettings settings = TimetableApplication.getInstance().getSettings();
            scene = new Scene(fxmlLoader.load(),
                    settings.getWindowWidth(),
                    settings.getWindowHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Редактор расписания");
        stage.setScene(scene);
        stage.show();

        setupTabs(scene);
    }

    /**
     * Настраивает вкладки
     *
     * @param scene Сценв приложения
     */
    private void setupTabs(Scene scene) {
        TabPane pane = (TabPane) scene.lookup("#tab_layout");

        addTab(pane, new TeachersView(this.storage));
        addTab(pane, new SpecialitiesView(this.storage));
        addTab(pane, new GroupsView(this.storage));
        addTab(pane, new SubjectsView(this.storage));
        addTab(pane, new TeachingLoadsView(this.storage));
        addTab(pane, new TimetableView(this.storage));
    }

    /**
     * Добавляет вкладку в окно
     *
     * @param pane Панель вкладок
     * @param view Вкладка
     */
    private void addTab(TabPane pane, View view) {
        Tab tab = new Tab(view.getName());
        tab.setContent(view.getContent());
        pane.getTabs().add(tab);
    }
}
