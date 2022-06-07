package com.artech.timetableapp.ui.groups;

import com.artech.timetableapp.ui.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;

public class GroupEditController extends ModelEditController<GroupModel> {
    public GroupEditController(IStorage storage, GroupModel model) {
        super(storage, model);
    }

    @Override
    protected GroupModel handleEdit(GroupModel model) {
        GroupModel proto = new GroupEditDialog(this.storage.specialityManager()).ask(model);
        if (proto != null) {
            return new GroupModel(model.id(), proto.name(), proto.speciality(), proto.semester(), proto.numberOfStudyWeeks());
        }

        return null;
    }

    @Override
    protected IObjectManager<GroupModel> getManager() {
        return this.storage.groupManager();
    }
}
