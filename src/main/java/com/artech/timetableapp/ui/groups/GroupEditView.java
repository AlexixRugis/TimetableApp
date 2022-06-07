package com.artech.timetableapp.ui.groups;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;

public class GroupEditView extends ModelEditView<GroupModel> {
    public GroupEditView(GroupModel model, IStorage storage) {
        super(model, storage);
    }

    @Override
    protected IObjectManager<GroupModel> getManager() {
        return this.storage.groupManager();
    }

    @Override
    protected Controller getController() {
        return new GroupEditController(this.storage, this.model);
    }
}
