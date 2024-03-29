package com.artech.timetableapp.ui.teachers;

import com.artech.timetableapp.ui.Controllers.ModelListController;
import com.artech.timetableapp.ui.importing.ImportingActionDialog;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.Node;

/**
 * Контроллер списка преподавателей
 */
public class TeachersController extends ModelListController<TeacherModel> implements IManagerUpdateListener {

    /**
     * Конструктор контроллер списка преподавателей
     * @param storage Хранилище данных
     */
    public TeachersController(IStorage storage) {
        super(storage);
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
        return new TeacherEditView(item, this.storage).getContent();
    }

    @Override
    protected IObjectManager<TeacherModel> getManager() {
        return this.storage.teacherManager();
    }
}
