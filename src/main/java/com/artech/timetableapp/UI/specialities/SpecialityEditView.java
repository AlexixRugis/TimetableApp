package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.storage.IStorage;

public class SpecialityEditView extends ModelEditView<SpecialityModel> {
    public SpecialityEditView(SpecialityModel model, IStorage storage, IObjectManager<SpecialityModel> manager) {
        super(model, storage, manager);
    }

    @Override
    protected Controller getController() {
        return new SpecialityEditController(this.storage, this.manager, this.model);
    }
}
