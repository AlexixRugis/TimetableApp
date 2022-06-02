package com.artech.timetableapp.UI.teaching_loads;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.model.TeacherModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class TeachingLoadEditDialogView extends FXMLView {
    private final IObjectManager<TeacherModel> teacherManager;
    private final IObjectManager<GroupModel> groupManager;
    private final IObjectManager<SubjectModel> subjectManager;

    private ComboBox<TeacherModel> teacher;
    private ComboBox<GroupModel> group;
    private ComboBox<SubjectModel> subject;
    private Spinner<Integer> hoursPerWeek;

    public TeachingLoadEditDialogView(IObjectManager<TeacherModel> teacherManager, IObjectManager<GroupModel> groupManager, IObjectManager<SubjectModel> subjectManager) {

        this.teacherManager = teacherManager;
        this.groupManager = groupManager;
        this.subjectManager = subjectManager;
    }

    @Override
    protected Node build() {
        Node build = super.build();

        ObservableList<TeacherModel> teachers = FXCollections.observableArrayList(this.teacherManager.getAll());
        teacher = (ComboBox<TeacherModel>)build.lookup("#teacher");
        teacher.setItems(teachers);
        teacher.getSelectionModel().select(0);

        ObservableList<GroupModel> groups = FXCollections.observableArrayList(this.groupManager.getAll());
        group = (ComboBox<GroupModel>)build.lookup("#group");
        group.setItems(groups);
        group.getSelectionModel().select(0);

        ObservableList<SubjectModel> subjects = FXCollections.observableArrayList(this.subjectManager.getAll());
        subject = (ComboBox<SubjectModel>)build.lookup("#subject");
        subject.setItems(subjects);
        subject.getSelectionModel().select(0);

        hoursPerWeek = (Spinner<Integer>) build.lookup("#hours_per_week");
        hoursPerWeek.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 36, 1));

        return build;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("teaching-load-edit-dialog.fxml");
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    public String getName() {
        return "Нагрузка";
    }

    public TeacherModel getTeacher() {
        return this.teacher.getSelectionModel().getSelectedItem();
    }

    public void setTeacher(TeacherModel model) {
        if (model == null) return;

        List<TeacherModel> models = this.teacher.getItems();
        for (int i = 0; i < models.size(); i++) {
            if (Objects.equals(models.get(i).id(), model.id())) {
                this.teacher.getSelectionModel().select(i);
                break;
            }
        }
    }

    public GroupModel getGroup() {
        return this.group.getSelectionModel().getSelectedItem();
    }

    public void setGroup(GroupModel model) {
        if (model == null) return;

        List<GroupModel> models = this.group.getItems();
        for (int i = 0; i < models.size(); i++) {
            if (Objects.equals(models.get(i).id(), model.id())) {
                this.group.getSelectionModel().select(i);
                break;
            }
        }
    }

    public SubjectModel getSubject() {
        return this.subject.getSelectionModel().getSelectedItem();
    }

    public void setSubject(SubjectModel model) {
        if (model == null) return;

        List<SubjectModel> models = this.subject.getItems();
        for (int i = 0; i < models.size(); i++) {
            if (Objects.equals(models.get(i).id(), model.id())) {
                this.subject.getSelectionModel().select(i);
                break;
            }
        }
    }

    public void setHoursPerWeek(Integer value) {
        this.hoursPerWeek.getValueFactory().setValue(value);
    }

    public Integer getHoursPerWeek() {
        return this.hoursPerWeek.getValue();
    }
}
