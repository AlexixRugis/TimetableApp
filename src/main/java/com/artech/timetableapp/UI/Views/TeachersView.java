package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.teachers.TeachersController;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;

public final class TeachersView extends ModelListView<TeacherModel> {
    private final IStorage storage;

    public TeachersView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    public String getName() {
        return "Преподаватели";
    }

    @Override
    protected Controller getController() {
        return new TeachersController(this.storage);
    }
}
