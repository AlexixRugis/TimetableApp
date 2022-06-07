package com.artech.timetableapp.ui.timetable;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;
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
    private GroupTimetableView groupView;

    public TimetableController(IStorage storage) {
        super(storage);
    }

    @FXML
    private void initialize() {
        this.storage.groupManager().addUpdateListener(this::updateGroupListData);

        groupList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectGroupModel(newValue));
        updateGroupListData();
    }

    private void selectGroupModel(GroupModel group) {
        if (group != null) {
            if (this.groupView == null) {
                createGroupTimetable(group);
            }
            else {
                this.groupView.setModel(group);
            }
        }
    }

    private void createGroupTimetable(GroupModel newValue) {
        this.groupView = new GroupTimetableView(this.storage, newValue);
        Node node = this.groupView.getContent();
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        tableHolder.getChildren().clear();
        tableHolder.getChildren().add(node);
    }

    private void updateGroupListData() {
        setListData(this.storage.groupManager().getAll());
    }

    private void setListData(Collection<GroupModel> groups) {
        ObservableList<GroupModel> models = FXCollections.observableArrayList(groups);
        groupList.setItems(models);
    }
}
