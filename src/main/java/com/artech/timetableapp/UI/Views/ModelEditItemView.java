package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Controllers.ModelEditItemController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.net.URL;

public class ModelEditItemView<T extends IModel, J> extends FXMLView {
    protected final IModel model;
    protected final IStorage storage;
    protected final IObjectManager<T, J> manager;

    public ModelEditItemView(T model, IStorage storage, IObjectManager<T, J> manager) {
        this.model = model;
        this.storage = storage;
        this.manager = manager;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("model-edit-item.fxml");
    }

    @Override
    protected Controller getController() {
        return new ModelEditItemController(this.storage, this.manager, this.model);
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
