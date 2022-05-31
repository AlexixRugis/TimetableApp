package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.net.URL;

public class TeacherEditDialogView extends FXMLView {
    private TextField firstName;
    private TextField secondName;
    private TextField lastName;

    @Override
    protected Node build() {
        Node node = super.build();

        firstName = (TextField) node.lookup("#first_name");
        secondName = (TextField) node.lookup("#second_name");
        lastName = (TextField) node.lookup("#last_name");

        return node;
    }

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
        firstName.setText(name);
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public void setSecondName(String name) {
        secondName.setText(name);
    }

    public String getSecondName() {
        return secondName.getText();
    }

    public void setLastName(String name) {
        lastName.setText(name);
    }

    public String getLastName() {
        return lastName.getText();
    }
}
