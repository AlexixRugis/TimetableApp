package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;

public class TeacherEditController extends ModelEditController<TeacherModel> {

    public TeacherEditController(IStorage storage, IObjectManager<TeacherModel> manager, TeacherModel model) {
        super(storage, manager, model);
    }

    @Override
    protected TeacherModel handleEdit(TeacherModel model) {
        TeacherModel proto = new TeacherEditDialog().ask(model);
        if (proto != null)
            return new TeacherModel(model.id(), proto.firstName(), proto.secondName(), proto.lastName());
        else
            return null;
    }
}
