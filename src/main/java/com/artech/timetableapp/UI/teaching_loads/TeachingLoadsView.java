package com.artech.timetableapp.UI.teaching_loads;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelListView;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.storage.IStorage;

public class TeachingLoadsView extends ModelListView<TeachingLoadModel> {

    private final IStorage storage;

    public TeachingLoadsView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    protected Controller getController() {
        return new TeachingLoadsController(this.storage, this.storage.teachingLoadManager());
    }

    @Override
    public String getName() {
        return "Нагрузка";
    }
}