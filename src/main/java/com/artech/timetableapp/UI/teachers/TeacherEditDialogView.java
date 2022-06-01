package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import javafx.scene.control.TextField;

import java.net.URL;

public class TeacherEditDialogView extends FXMLView {
    private TextField firstName;
    private TextField secondName;
    private TextField lastName;

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("teacher-edit-dialog.fxml");
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    public String getName() {
        return "Преподватель";
    }

    public void setFirstName(String name) {
        lookup("#first_name", TextField.class).setText(name);
    }

    public String getFirstName() {
        return lookup("#first_name", TextField.class).getText();
    }

    public void setSecondName(String name) {
        lookup("#second_name", TextField.class).setText(name);
    }

    public String getSecondName() {
        return lookup("#second_name", TextField.class).getText();
    }

    public void setLastName(String name) {
        lookup("#last_name", TextField.class).setText(name);
    }

    public String getLastName() {
        return lookup("#last_name", TextField.class).getText();
    }
}
