package com.artech.timetableapp.ui.teaching_loads;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.ModelListView;
import com.artech.timetableapp.core.storage.IStorage;

public class TeachingLoadsView extends ModelListView {

    private final IStorage storage;

    public TeachingLoadsView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    protected Controller getController() {
        return new TeachingLoadsController(this.storage);
    }

    @Override
    public String getName() {
        return "Нагрузка";
    }
}
