package com.artech.timetableapp.UI.subjects;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class SubjectEditDialogView extends FXMLView {

    private final IObjectManager<SpecialityModel> specialityManager;
    private TextField name;
    private ComboBox<SpecialityModel> speciality;
    private Spinner<Integer> semester;

    public SubjectEditDialogView(IObjectManager<SpecialityModel> specialityManager) {
        this.specialityManager = specialityManager;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("subject-edit-dialog.fxml");
    }

    @Override
    protected Node build() {
        Node build = super.build();

        name = (TextField) build.lookup("#name");

        ObservableList<SpecialityModel> specialities = FXCollections.observableArrayList(this.specialityManager.getAll());
        speciality = (ComboBox<SpecialityModel>)build.lookup("#speciality");
        speciality.setItems(specialities);
        speciality.getSelectionModel().select(0);

        semester = (Spinner<Integer>) build.lookup("#semester");
        semester.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1));


        return build;
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    public String getName() {
        return "Предмет";
    }

    public String getSubjectName() {
        return this.name.getText();
    }

    public void setSubjectName(String name) {
        this.name.setText(name);
    }

    public SpecialityModel getSpeciality() {
        return this.speciality.getSelectionModel().getSelectedItem();
    }

    public void setSpecialityModel(SpecialityModel model) {
        if (model == null) return;

        List<SpecialityModel> models = this.speciality.getItems();
        for (int i = 0; i < models.size(); i++) {
            if (Objects.equals(models.get(i).id(), model.id())) {
                this.speciality.getSelectionModel().select(i);
                break;
            }
        }
    }

    public void setSemester(Integer value) {
        this.semester.getValueFactory().setValue(value);
    }

    public Integer getSemester() {
        return this.semester.getValue();
    }
}
