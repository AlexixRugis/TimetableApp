package com.artech.timetableapp.ui.teaching_loads;

import com.artech.timetableapp.ui.Controllers.ModelListController;
import com.artech.timetableapp.ui.importing.ImportingActionDialog;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class TeachingLoadsController extends ModelListController<TeachingLoadModel> implements IManagerUpdateListener {
    public TeachingLoadsController(IStorage storage) {
        super(storage);
    }

    @Override
    protected void handleImport() {
        ImportingActionDialog dialog = new TeachingLoadImportDialog(this.storage);
        dialog.ask();
    }

    @FXML
    public void initialize() {
        this.manager.addUpdateListener(this);
        updateData();
    }

    @Override
    protected TeachingLoadModel createModel() {
        return new TeachingLoadEditDialog(this.storage.teacherManager(), this.storage.groupManager(), this.storage.subjectManager()).ask();
    }

    @Override
    protected Node getEditView(TeachingLoadModel item) {
        return new TeachingLoadEditView(item, this.storage).getContent();
    }

    @Override
    protected IObjectManager<TeachingLoadModel> getManager() {
        return this.storage.teachingLoadManager();
    }

    @Override
    public void onUpdate() {
        updateData();
    }
}
