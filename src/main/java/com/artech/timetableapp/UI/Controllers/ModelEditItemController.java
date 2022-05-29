package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.model.prototype.IModelPrototype;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ModelEditItemController<T extends IModel> extends Controller{

    private final T model;
    private final IObjectManager<T, IModelPrototype<T>> manager;

    public ModelEditItemController(IStorage storage, IObjectManager<T, IModelPrototype<T>> manager, T model) {
        super(storage);
        this.manager = manager;
        this.model = model;
    }

    @FXML
    public void onEdit() {
        //TODO: https://code.makery.ch/blog/javafx-dialogs-official/
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
}
