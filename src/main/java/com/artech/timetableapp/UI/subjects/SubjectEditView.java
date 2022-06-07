package com.artech.timetableapp.UI.subjects;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.storage.IStorage;

public class SubjectEditView extends ModelEditView<SubjectModel> {
    public SubjectEditView(SubjectModel model, IStorage storage) {
        super(model, storage);
    }

    @Override
    protected IObjectManager<SubjectModel> getManager() {
        return this.storage.subjectManager();
    }

    @Override
    protected Controller getController() {
        return new SubjectEditController(this.storage, this.model);
    }
}
