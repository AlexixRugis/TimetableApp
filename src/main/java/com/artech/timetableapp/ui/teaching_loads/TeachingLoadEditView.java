package com.artech.timetableapp.ui.teaching_loads;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.storage.IStorage;

public class TeachingLoadEditView extends ModelEditView<TeachingLoadModel> {
    public TeachingLoadEditView(TeachingLoadModel model, IStorage storage) {
        super(model, storage);
    }

    @Override
    protected IObjectManager<TeachingLoadModel> getManager() {
        return this.storage.teachingLoadManager();
    }

    @Override
    protected Controller getController() {
        return new TeachingLoadEditController(this.storage, this.model);
    }
}
