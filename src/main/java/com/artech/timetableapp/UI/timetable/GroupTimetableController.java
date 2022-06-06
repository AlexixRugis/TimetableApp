package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.core.model.*;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class GroupTimetableController extends Controller {
    private final GroupModel groupModel;

    @FXML
    private Text infoText;

    @FXML
    private ListView subjectsList;

    @FXML
    private GridPane tableGrid;

    private final ArrayList<TimetableLessonHolder> timetable = new ArrayList<>();

    public GroupTimetableController(IStorage storage, GroupModel model) {
        super(storage);
        this.groupModel = model;
    }

    @FXML
    private void initialize() {
        this.storage.teachingLoadManager().addUpdateListener(this::updateSubjectData);
        this.storage.timetableLessonManager().addUpdateListener(this::updateTimetableData);

        updateTimetableData();
    }


    private void updateSubjectData() {
        setSubjects(this.storage.teachingLoadManager().getByGroup(this.groupModel));
    }
    private void updateTimetableData() {
        updateSubjectData();
        updateGrid();

        Integer allHours = this.storage.timetableLessonManager().getHours(this.groupModel);
        boolean valid = false;
        this.infoText.setText("Заполнено часов: " + allHours + "/36");

        if (allHours <= 36) {
            valid = true;
        }

        this.infoText.setFill(valid ? Color.BLACK : Color.RED);
    }

    private void setSubjects(Collection<TeachingLoadModel> models) {
        ObservableList<Node> loads = FXCollections.observableArrayList();
        for (TeachingLoadModel model : models) {
            DraggableTeachingLoad draggable = new DraggableTeachingLoad(this.storage, model);
            draggable.setOnDragBegin(this::updateDragState);
            draggable.setOnEndDrag((dragModel) -> updateDragState(null));
            loads.add(draggable.getContent());
        }
        this.subjectsList.setItems(loads);
    }

    private void updateDragState(TeachingLoadModel model) {
        for (TimetableLessonHolder holder : timetable) {
            holder.setDragModel(model);
        }
    }

    private void updateGrid() {
        Collection<TimetableLessonModel> lessons = this.storage.timetableLessonManager().getData(this.groupModel);
        Day[] days = {Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday, Day.Friday, Day.Saturday};

        tableGrid.getChildren().clear();
        timetable.clear();
        for (int i = 0; i < days.length; i++) {
            Label dayName = new Label(days[i].name());
            dayName.setPadding(new Insets(40));
            tableGrid.add(dayName, i, 0);
            for (int j = 1; j < 7; j++) {
                TimetableLessonHolder holder = new TimetableLessonHolder(this, getLessonModel(lessons, days[i], j), days[i], j);
                timetable.add(holder);
                tableGrid.add(holder.getContent(), i, j);
            }
        }
    }

    private TimetableLessonModel getLessonModel(Collection<TimetableLessonModel> models, Day day, Integer lesson) {
        for (TimetableLessonModel lessonModel : models) {
            if (lessonModel.day() == day && Objects.equals(lessonModel.lessonNumber(), lesson)) {
                return lessonModel;
            }
        }

        return null;
    }

    public Boolean hasData(TeacherModel teacher, Day day, Integer lesson) {
        Collection<TimetableLessonModel> models = this.storage.timetableLessonManager().getData(teacher, day, lesson);

        boolean has = false;
        for (TimetableLessonModel model : models) {
            if (!Objects.equals(model.group().id(), this.groupModel.id())) {
                has = true;
                break;
            }
        }

        return has;
    }

    public void setData(Integer teachingLoadId, Day day, Integer lesson) {
        TimetableLessonModel model = new TimetableLessonModel(0, day, lesson, this.storage.teachingLoadManager().get(teachingLoadId), this.groupModel);
        this.storage.timetableLessonManager().setData(model);
    }

    public void clearData(Day day, Integer lesson) {
        this.storage.timetableLessonManager().clearData(this.groupModel, day, lesson);
    }
}
