package com.artech.timetableapp.UI.importing;

import com.artech.timetableapp.UI.Views.ActionDialog;
import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.control.Alert;

public abstract class ImportingActionDialog extends ActionDialog {
    protected final ImportingActionDialogView view;
    protected final IStorage storage;

    public ImportingActionDialog(IStorage storage) {
        this.storage = storage;
        this.view = new ImportingActionDialogView();
    }

    @Override
    protected View getView() {
        return view;
    }

    @Override
    protected boolean validate() {
        return this.view.getSelectedFile() != null;
    }

    @Override
    protected Boolean performAction() {
        try {
            handleImport();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Данные успешно импортированы!");
            alert.showAndWait();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Во время импорта произошла ошибка!");
            alert.setContentText("Неверное форматирование данных файла");
            alert.showAndWait();
            return false;
        }
    }

    protected abstract void handleImport();
}
