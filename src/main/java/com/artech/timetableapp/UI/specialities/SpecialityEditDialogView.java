package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.net.URL;

public class SpecialityEditDialogView extends FXMLView {

    private TextField name;

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("speciality-edit-dialog.fxml");
    }

    @Override
    protected Node build() {
        Node build = super.build();

        name = (TextField) build.lookup("#name");

        return build;
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    public String getName() {
        return "Специальность";
    }

    public void setSpecialityName(String name) {
        this.name.setText(name);
    }

    public String getSpecialityName() {
        return this.name.getText();
    }
}
