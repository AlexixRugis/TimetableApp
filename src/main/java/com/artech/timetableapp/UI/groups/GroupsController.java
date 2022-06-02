package com.artech.timetableapp.UI.groups;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.util.Collection;

public class GroupsController extends ModelListController<GroupModel> implements IManagerUpdateListener {
    public GroupsController(IStorage storage, IObjectManager<GroupModel> manager) {
        super(storage, manager);
    }

    @FXML
    public void initialize() {
        this.storage.groupManager().addUpdateListener(this);

        updateData();
    }

    @Override
    protected GroupModel createModel() {
        return new GroupEditDialog(this.storage.specialityManager()).ask();
    }

    private void updateData() {
        Collection<GroupModel> groupModels = this.storage.groupManager().getAll();
        setListData(groupModels);
    }

    private void setListData(Collection<GroupModel> items) {
        ObservableList<Node> views = FXCollections.observableArrayList();
        for (GroupModel item : items) views.add(new GroupEditView(item, this.storage, this.storage.groupManager()).getContent());
        list.setItems(views);
    }

    @Override
    public void onUpdate() {
        updateData();
    }
}
