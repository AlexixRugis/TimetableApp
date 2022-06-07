package com.artech.timetableapp.ui.subjects;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.ModelListView;
import com.artech.timetableapp.core.storage.IStorage;

public class SubjectsView extends ModelListView {

    private final IStorage storage;

    public SubjectsView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    protected Controller getController() {
        return new SubjectsController(this.storage);
    }

    @Override
    public String getName() {
        return "Предметы";
    }
}
