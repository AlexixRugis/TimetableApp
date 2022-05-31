package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.net.URL;

public abstract class ModelEditView<T extends IModel> extends FXMLView {
    protected final T model;
    protected final IStorage storage;
    protected final IObjectManager<T> manager;

    public ModelEditView(T model, IStorage storage, IObjectManager<T> manager) {
        this.model = model;
        this.storage = storage;
        this.manager = manager;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("model-edit-item.fxml");
    }

    @Override
    public String getName() {
        return this.model.toString();
    }

    @Override
    public Node getContent() {
        Node node = super.getContent();

        Text text = (Text)node.lookup("#text");
        text.setText(model.toString());

        return node;
    }
}
