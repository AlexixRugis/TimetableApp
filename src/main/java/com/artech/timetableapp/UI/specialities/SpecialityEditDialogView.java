package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import javafx.scene.control.TextField;

import java.net.URL;

public class SpecialityEditDialogView extends FXMLView {
    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("speciality-edit-dialog.fxml");
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
        lookup("#name", TextField.class).setText(name);
    }

    public String getSpecialityName() {
        return lookup("#name", TextField.class).getText();
    }
}
