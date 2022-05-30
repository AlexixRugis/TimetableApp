package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.Controllers.ModelEditItemController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.prototype.TeacherPrototype;
import com.artech.timetableapp.core.storage.IStorage;

public class TeacherEditController extends ModelEditItemController<TeacherModel, TeacherPrototype> {

    public TeacherEditController(IStorage storage, IObjectManager<TeacherModel, TeacherPrototype> manager, TeacherModel model) {
        super(storage, manager, model);
    }

    @Override
    protected TeacherModel handleEdit(TeacherModel model) {
        return new TeacherModel(model.id(), model.firstName(), model.secondName(), "dddf");
    }
}
