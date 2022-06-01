package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.storage.IStorage;

public class SpecialityEditController extends ModelEditController<SpecialityModel> {
    public SpecialityEditController(IStorage storage, IObjectManager<SpecialityModel> manager, SpecialityModel model) {
        super(storage, manager, model);
    }

    @Override
    protected SpecialityModel handleEdit(SpecialityModel model) {
        SpecialityModel proto = new SpecialityEditDialog().ask(model);
        if (proto != null) {
            return new SpecialityModel(model.id(), proto.name());
        }
        return null;
    }
}
