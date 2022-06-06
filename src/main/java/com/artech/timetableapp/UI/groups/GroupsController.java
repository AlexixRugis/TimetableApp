package com.artech.timetableapp.UI.groups;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class GroupsController extends ModelListController<GroupModel> implements IManagerUpdateListener {
    public GroupsController(IStorage storage, IObjectManager<GroupModel> manager) {
        super(storage, manager);
    }

    @Override
    protected void handleImport() {
        ImportingActionDialog dialog = new GroupImportDialog(this.storage);
        dialog.ask();
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

    @Override
    protected Node getEditView(GroupModel item) {
        return new GroupEditView(item, this.storage, this.manager).getContent();
    }

    @Override
    public void onUpdate() {
        updateData();
    }
}
