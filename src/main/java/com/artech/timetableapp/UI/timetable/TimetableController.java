package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.Collection;

public class TimetableController extends Controller {

    @FXML
    private ListView<GroupModel> groupList;

    @FXML
    private AnchorPane tableHolder;

    public TimetableController(IStorage storage) {
        super(storage);
    }

    @FXML
    private void initialize() {
        this.storage.groupManager().addUpdateListener(new IManagerUpdateListener() {
            @Override
            public void onUpdate() {
                updateData();
            }
        });

        updateData();

        groupList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tableHolder.getChildren().clear();

            if (newValue != null) {
                Node node = new GroupTimetableView(this.storage, newValue).getContent();
                AnchorPane.setTopAnchor(node, 0.0);
                AnchorPane.setBottomAnchor(node, 0.0);
                AnchorPane.setLeftAnchor(node, 0.0);
                AnchorPane.setRightAnchor(node, 0.0);
                tableHolder.getChildren().add(node);
            }
        });
    }

    private void updateData() {
        setListData(this.storage.groupManager().getAll());
    }

    private void setListData(Collection<GroupModel> groups) {
        ObservableList<GroupModel> models = FXCollections.observableArrayList(groups);
        groupList.setItems(models);
    }
}
