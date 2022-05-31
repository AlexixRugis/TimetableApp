package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;

public abstract class ModelListController<T extends IModel> extends Controller{
    private final IObjectManager<T> manager;

    public ModelListController(IStorage storage, IObjectManager<T> manager) {
        super(storage);
        this.manager = manager;
    }

    @FXML
    public void addNew() {
        T model = createModel();
        if (model != null) this.manager.tryCreate(model);
    }

    protected abstract T createModel();
}
