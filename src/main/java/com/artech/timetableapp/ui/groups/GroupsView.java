package com.artech.timetableapp.ui.groups;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.ModelListView;
import com.artech.timetableapp.core.storage.IStorage;

public class GroupsView extends ModelListView {

    private final IStorage storage;

    public GroupsView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    protected Controller getController() {
        return new GroupsController(this.storage);
    }

    @Override
    public String getName() {
        return "Группы";
    }
}
