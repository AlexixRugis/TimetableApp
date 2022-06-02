package com.artech.timetableapp.UI.groups;

import com.artech.timetableapp.UI.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;

public class GroupEditController extends ModelEditController<GroupModel> {
    public GroupEditController(IStorage storage, IObjectManager<GroupModel> manager, GroupModel model) {
        super(storage, manager, model);
    }

    @Override
    protected GroupModel handleEdit(GroupModel model) {
        GroupModel proto = new GroupEditDialog(this.storage.specialityManager()).ask(model);
        if (proto != null) {
            return new GroupModel(model.id(), proto.name(), proto.speciality(), proto.semester(), proto.numberOfStudyWeeks());
        }

        return null;
    }
}
