package com.artech.timetableapp.ui.subjects;

import com.artech.timetableapp.ui.Controllers.ModelListController;
import com.artech.timetableapp.ui.importing.ImportingActionDialog;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class SubjectsController extends ModelListController<SubjectModel> implements IManagerUpdateListener {
    public SubjectsController(IStorage storage) {
        super(storage);
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
        return new SubjectEditView(item, this.storage).getContent();
    }

    @Override
    protected IObjectManager<SubjectModel> getManager() {
        return this.storage.subjectManager();
    }

    @Override
    public void onUpdate() {
        updateData();
    }

}
