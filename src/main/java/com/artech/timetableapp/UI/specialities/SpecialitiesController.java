package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.Controllers.ModelListController;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.util.Collection;

public class SpecialitiesController extends ModelListController<SpecialityModel> implements IManagerUpdateListener {

    public SpecialitiesController(IStorage storage, IObjectManager<SpecialityModel> manager) {
        super(storage, manager);
    }

    @FXML
    public void initialize() {
        this.storage.specialityManager().addUpdateListener(this);

        updateData();
    }

    @Override
    protected SpecialityModel createModel() {
        return new SpecialityEditDialog().ask();
    }

    @Override
    public void onUpdate() {
        updateData();
    }

    private void updateData() {
        Collection<SpecialityModel> teacherModels = this.storage.specialityManager().getAll();
        setListData(teacherModels);
    }

    private void setListData(Collection<SpecialityModel> items) {
        ObservableList<Node> views = FXCollections.observableArrayList();
        for (SpecialityModel item : items) views.add(new SpecialityEditView(item, this.storage, this.storage.specialityManager()).getContent());
        list.setItems(views);
    }
}
