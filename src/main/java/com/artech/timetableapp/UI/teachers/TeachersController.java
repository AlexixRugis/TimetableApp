package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.util.Collection;

public class TeachersController extends ModelListController<TeacherModel> implements IManagerUpdateListener {

    public TeachersController(IStorage storage) {
        super(storage, storage.teacherManager());
    }

    @FXML
    public void initialize() {
        this.storage.teacherManager().addUpdateListener(this);

        updateData();
    }

    private void updateData() {
        Collection<TeacherModel> teacherModels = this.storage.teacherManager().getAll();
        setListData(teacherModels);
    }

    private void setListData(Collection<TeacherModel> items) {
        ObservableList<Node> views = FXCollections.observableArrayList();
        for (TeacherModel item : items) views.add(new TeacherEditView(item, this.storage, this.storage.teacherManager()).getContent());
        list.setItems(views);
    }

    @Override
    public void onUpdate() {
        updateData();
    }

    @Override
    protected TeacherModel createModel() {
        return new TeacherEditDialog().ask();
    }
}
