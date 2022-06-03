package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Collection;

public class TimetableController extends Controller {

    @FXML
    private ListView groupList;

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
    }

    private void updateData() {
        setListData(this.storage.groupManager().getAll());
    }

    private void setListData(Collection<GroupModel> groups) {
        ObservableList<GroupModel> models = FXCollections.observableArrayList(groups);
        groupList.setItems(models);
    }
}
