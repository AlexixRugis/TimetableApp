package com.artech.timetableapp.UI.subjects;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.UI.groups.GroupImportDialog;
import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.UI.specialities.SpecialityEditView;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.util.Collection;

public class SubjectsController extends ModelListController<SubjectModel> implements IManagerUpdateListener {
    public SubjectsController(IStorage storage, IObjectManager<SubjectModel> manager) {
        super(storage, manager);
    }

    @Override
    protected void handleImport() {
        ImportingActionDialog dialog = new SubjectImportDialog(this.storage);
        dialog.ask();
    }

    @FXML
    public void initialize() {
        this.manager.addUpdateListener(this);
        updateData();
    }

    @Override
    protected SubjectModel createModel() {
        return new SubjectEditDialog(this.storage.specialityManager()).ask();
    }

    @Override
    protected Node getEditView(SubjectModel item) {
        return new SubjectEditView(item, this.storage, this.manager).getContent();
    }

    @Override
    public void onUpdate() {
        updateData();
    }

}
