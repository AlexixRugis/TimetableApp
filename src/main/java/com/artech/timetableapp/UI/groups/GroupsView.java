package com.artech.timetableapp.UI.groups;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelListView;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;

public class GroupsView extends ModelListView<GroupModel> {

    private final IStorage storage;

    public GroupsView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    protected Controller getController() {
        return new GroupsController(this.storage, this.storage.groupManager());
    }

    @Override
    public String getName() {
        return "Группы";
    }
}
