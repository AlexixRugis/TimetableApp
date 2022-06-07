package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.net.URL;

/**
 * Представление диалога редактирования данных преподавателя
 */
public class TeacherEditDialogView extends FXMLView {
    /**
     * Поле ввода имени
     */
    private TextField firstName;
    /**
     * Поле ввода отчества
     */
    private TextField secondName;
    /**
     * Поле ввода фамилии
     */
    private TextField lastName;

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("teacher-edit-dialog.fxml");
    }

    @Override
    protected Node build() {
        Node build = super.build();

        firstName = (TextField) build.lookup("#first_name");
        secondName = (TextField) build.lookup("#second_name");
        lastName = (TextField) build.lookup("#last_name");

        return build;
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    public String getName() {
        return "Преподватель";
    }

    /**
     * Устанавливает имя
     * @param name Имя
     */
    public void setFirstName(String name) {
        firstName.setText(name);
    }

    /**
     * Получает имя
     * @return Имя
     */
    public String getFirstName() {
        return firstName.getText();
    }

    /**
     * Устанавливает отчество
     * @param name Отчество
     */
    public void setSecondName(String name) {
        secondName.setText(name);
    }

    /**
     * Получает отчество
     * @return Отчество
     */
    public String getSecondName() {
        return secondName.getText();
    }

    /**
     * Устанавливает фамилию
     * @param name Фамилия
     */
    public void setLastName(String name) {
        lastName.setText(name);
    }

    /**
     * Получает фамилию
     * @return Фамилия
     */
    public String getLastName() {
        return lastName.getText();
    }
}
