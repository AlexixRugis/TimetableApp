package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.core.model.IModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Collection;

public abstract class ModelListView<T extends IModel> extends FXMLView {
    private ListView listView;
    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("list-view.fxml");
    }

    @Override
    public Node getContent() {
        Node node = super.getContent();
        listView = (ListView) node.lookup("#list");
        return node;
    }


}
