package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class ModelEditController<T extends IModel> extends Controller{

    protected final T model;
    private final IObjectManager<T> manager;

    public ModelEditController(IStorage storage, IObjectManager<T> manager, T model) {
        super(storage);
        this.manager = manager;
        this.model = model;
    }

    @FXML
    public void onEdit() {
        T upd = handleEdit(this.model);
        if (upd != null)
            this.manager.tryUpdate(upd);
    }

    @FXML
    public void onDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вы уверены?");
        alert.setHeaderText("Вы уверены что хотите удалить этот объект?");
        alert.setContentText("Восстановление после удаления невозможно!");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            this.manager.tryDelete(this.model);
        }

        System.out.println("delete " + this.model.id());
    }

    protected abstract T handleEdit(T model);
}
