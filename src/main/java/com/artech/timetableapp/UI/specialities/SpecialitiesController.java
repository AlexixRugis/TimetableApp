package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class SpecialitiesController extends ModelListController<SpecialityModel> implements IManagerUpdateListener {

    public SpecialitiesController(IStorage storage) {
        super(storage);
    }

    @Override
    protected void handleImport() {
        ImportingActionDialog dialog = new SpecialitiesImportDialog(this.storage);
        dialog.ask();
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
        return new SpecialityEditView(item, this.storage).getContent();
    }

    @Override
    protected IObjectManager<SpecialityModel> getManager() {
        return this.storage.specialityManager();
    }

    @Override
    public void onUpdate() {
        updateData();
    }
}
