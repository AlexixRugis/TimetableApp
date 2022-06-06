package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.UI.importing.ImportingActionDialog;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class TeachersController extends ModelListController<TeacherModel> implements IManagerUpdateListener {

    public TeachersController(IStorage storage) {
        super(storage, storage.teacherManager());
    }

    @FXML
    public void initialize() {
        this.manager.addUpdateListener(this);
        updateData();
    }

    @Override
    public void onUpdate() {
        updateData();
    }

    @Override
    protected void handleImport() {
        ImportingActionDialog dialog = new TeachersImportDialog(this.storage);
        dialog.ask();
    }

    @Override
    protected TeacherModel createModel() {
        return new TeacherEditDialog().ask();
    }

    @Override
    protected Node getEditView(TeacherModel item) {
        return new TeacherEditView(item, this.storage,this.manager).getContent();
    }
}
