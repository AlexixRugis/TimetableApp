package com.artech.timetableapp.UI.subjects;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.storage.IStorage;

public class SubjectEditView extends ModelEditView<SubjectModel> {
    public SubjectEditView(SubjectModel model, IStorage storage, IObjectManager<SubjectModel> manager) {
        super(model, storage, manager);
    }

    @Override
    protected Controller getController() {
        return new SubjectEditController(this.storage, this.manager, this.model);
    }
}
