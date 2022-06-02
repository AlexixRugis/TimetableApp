package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;

public class TeacherEditView extends ModelEditView<TeacherModel> {

    public TeacherEditView(TeacherModel model, IStorage storage, IObjectManager<TeacherModel> manager) {
        super(model, storage, manager);
    }

    @Override
    protected Controller getController() {
        return new TeacherEditController(this.storage, this.manager, this.model);
    }
}
