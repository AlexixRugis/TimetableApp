package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;

public abstract class ModelListController<T extends IObjectManager> extends Controller{
    private final T manager;

    public ModelListController(IStorage storage, T manager) {
        super(storage);
        this.manager = manager;
    }

    @FXML
    public void addNew() {
        this.manager.tryCreate(getPrototype());
    }

    protected abstract Object getPrototype();
}
