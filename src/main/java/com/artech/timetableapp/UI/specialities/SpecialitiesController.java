package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class SpecialitiesController extends ModelListController<SpecialityModel> implements IManagerUpdateListener {

    public SpecialitiesController(IStorage storage, IObjectManager<SpecialityModel> manager) {
        super(storage, manager);
    }

    @FXML
    public void initialize() {
        this.manager.addUpdateListener(this);
        updateData();
    }

    @Override
    protected SpecialityModel createModel() {
        return new SpecialityEditDialog().ask();
    }

    @Override
    protected Node getEditView(SpecialityModel item) {
        return new SpecialityEditView(item, this.storage, this.manager).getContent();
    }

    @Override
    public void onUpdate() {
        updateData();
    }
}
