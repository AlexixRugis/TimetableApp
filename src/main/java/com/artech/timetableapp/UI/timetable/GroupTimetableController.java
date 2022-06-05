package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.core.manager.IManagerUpdateListener;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collection;

public class GroupTimetableController extends Controller {
    private final GroupModel model;

    @FXML
    private Text infoText;

    @FXML
    private ListView subjectsList;

    @FXML
    private GridPane tableGrid;

    private ArrayList<TimetableLessonHolder> timetable;

    public GroupTimetableController(IStorage storage, GroupModel model) {
        super(storage);
        this.model = model;
    }

    @FXML
    private void initialize() {
        this.storage.teachingLoadManager().addUpdateListener(this::updateSubjectData);
        this.storage.timetableLessonManager().addUpdateListener(this::updateTimetableData);

        this.infoText.setText(this.model.name());
        setupGrid();
        updateSubjectData();
    }


    private void updateSubjectData() {
        setSubjects(this.storage.teachingLoadManager().getByGroup(this.model));
    }
    private void updateTimetableData() {
        Integer allHours = this.storage.timetableLessonManager().getHours(this.model);
        boolean valid = false;
        this.infoText.setText("Заполнено часов: " + allHours + "/36");

        if (allHours <= 36) {
            valid = true;
        }

        this.infoText.setFont(Font.font("Verdana", FontWeight.THIN, FontPosture.REGULAR, 14));
        this.infoText.setStroke(valid ? Color.BLACK : Color.RED);
    }

    private void setSubjects(Collection<TeachingLoadModel> models) {
        ObservableList<Node> loads = FXCollections.observableArrayList();
        for (TeachingLoadModel model : models) {
            DraggableTeachingLoad draggable = new DraggableTeachingLoad(model);
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

    private void setupGrid() {
        timetable = new ArrayList<>();
        Day[] days = {Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday, Day.Friday, Day.Saturday};


        for (int i = 0; i < days.length; i++) {
            Label dayName = new Label(days[i].name());
            dayName.setPadding(new Insets(40));
            tableGrid.add(dayName, i, 0);
            for (int j = 1; j < 7; j++) {
                TimetableLessonHolder holder = new TimetableLessonHolder(this.storage, this.model, days[i], j);
                timetable.add(holder);
                tableGrid.add(holder.getContent(), i, j);
            }
        }
    }
}
