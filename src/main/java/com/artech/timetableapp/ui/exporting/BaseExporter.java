package com.artech.timetableapp.ui.exporting;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * Обработчик экспорта модели
 * @param <T> Класс модели
 */
public abstract class BaseExporter<T extends IModel> {

    protected final IStorage storage;
    protected final T model;

    /**
     * Конструктор обработчика экспорта
     * @param storage Хранилище данных
     * @param model Модель
     */
    public BaseExporter(IStorage storage, T model) {
        this.storage = storage;
        this.model = model;
    }

    /**
     * Запускает диалоговое окно экспорта
     */
    public void perform() {
        try {
            File selectedDirectory = getDirectory();

            if (selectedDirectory != null) {
                File file = new File(selectedDirectory, getFileName());
                export(file);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Данные успешно экспортированы!");
                alert.showAndWait();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Во время экспорта произошла ошибка!");
            alert.showAndWait();
        }
    }

    /**
     * Получает директорию экспорта
     * @return Директория
     */
    protected File getDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        return chooser.showDialog(TimetableApplication.getInstance().getPrimaryStage());
    }

    /**
     * Обрабатывает имя файла для использования в системе
     * @param name Исходное имя
     * @return Имя после обработки
     */
    protected static String fixName(String name) {
        name = name.replace("\\", "");
        name = name.replace("/", "");
        name = name.replace(":", "");
        name = name.replace("*", "");
        name = name.replace("?", "");
        name = name.replace("\"", "");
        name = name.replace("<", "");
        name = name.replace(">", "");
        name = name.replace("|", "");
        name = name.replace("+", "");
        name = name.replace(".", "");
        name = name.replace("%", "");
        name = name.replace("!", "");
        name = name.replace("@", "");
        name = name.replace("\n", "");

        return name;
    }

    /**
     * Обрабатывает экспорт
     * @param file Файл экспорта
     */
    protected abstract void export(File file);

    /**
     * Получает имя файла экспорта
     * @return Имя файла экспорта
     */
    protected abstract String getFileName();
}
