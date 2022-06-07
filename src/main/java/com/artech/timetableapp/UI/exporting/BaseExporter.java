package com.artech.timetableapp.UI.exporting;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;

import java.io.File;

public abstract class BaseExporter<T extends IModel> {
    protected final IStorage storage;
    protected final T model;

    public BaseExporter(IStorage storage, T model) {
        this.storage = storage;

        this.model = model;
    }

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

    protected File getDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        return chooser.showDialog(TimetableApplication.getInstance().getPrimaryStage());
    }

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

    protected abstract void export(File file);
    protected abstract String getFileName();
}
