package com.artech.timetableapp.UI.groups;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;

public class GroupEditView extends ModelEditView<GroupModel> {
    public GroupEditView(GroupModel model, IStorage storage, IObjectManager<GroupModel> manager) {
        super(model, storage, manager);
    }

    @Override
    protected Controller getController() {
        return new GroupEditController(this.storage, this.manager, this.model);
    }
}
