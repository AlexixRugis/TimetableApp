package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.Collection;

public abstract class ModelListController<T extends IModel> extends Controller{
    protected final IObjectManager<T> manager;

    @FXML
    protected ListView list;

    @FXML
    protected Button importButton;

    public ModelListController(IStorage storage, IObjectManager<T> manager) {
        super(storage);
        this.manager = manager;
    }

    @FXML
    public void addNew() {
        T model = createModel();
        if (model != null) this.manager.tryCreate(model);
    }

    @FXML
    public void importData() {
        handleImport();
    }
    protected abstract void handleImport();

    protected abstract T createModel();

    protected void updateData() {
        Collection<T> models = this.manager.getAll();
        setListData(models);
    }

    protected void setListData(Collection<T> items) {
        ObservableList<Node> views = FXCollections.observableArrayList();
        for (T item : items) views.add(getEditView(item));
        list.setItems(views);
    }

    protected abstract Node getEditView(T item);
}
