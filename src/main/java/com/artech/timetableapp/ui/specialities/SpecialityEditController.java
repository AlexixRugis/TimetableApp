package com.artech.timetableapp.ui.specialities;

import com.artech.timetableapp.ui.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.storage.IStorage;

public class SpecialityEditController extends ModelEditController<SpecialityModel> {
    public SpecialityEditController(IStorage storage, SpecialityModel model) {
        super(storage, model);
    }

    @Override
    protected SpecialityModel handleEdit(SpecialityModel model) {
        SpecialityModel proto = new SpecialityEditDialog().ask(model);
        if (proto != null) {
            return new SpecialityModel(model.id(), proto.name());
        }
        return null;
    }

    @Override
    protected IObjectManager<SpecialityModel> getManager() {
        return this.storage.specialityManager();
    }
}
