package com.artech.timetableapp.ui.specialities;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.storage.IStorage;

public class SpecialityEditView extends ModelEditView<SpecialityModel> {
    public SpecialityEditView(SpecialityModel model, IStorage storage) {
        super(model, storage);
    }

    @Override
    protected IObjectManager<SpecialityModel> getManager() {
        return this.storage.specialityManager();
    }

    @Override
    protected Controller getController() {
        return new SpecialityEditController(this.storage, this.model);
    }
}
