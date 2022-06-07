package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelListView;
import com.artech.timetableapp.core.storage.IStorage;

public class SpecialitiesView extends ModelListView {

    private final IStorage storage;

    public SpecialitiesView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    protected Controller getController() {
        return new SpecialitiesController(this.storage);
    }

    @Override
    public String getName() {
        return "Специальности";
    }
}
