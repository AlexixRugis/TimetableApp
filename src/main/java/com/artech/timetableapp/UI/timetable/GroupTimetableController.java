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

import java.util.Collection;

public class GroupTimetableController extends Controller {
    private final GroupModel model;

    @FXML
    private Label infoText;

    @FXML
    private ListView subjectsList;

    @FXML
    private GridPane tableGrid;

    public GroupTimetableController(IStorage storage, GroupModel model) {
        super(storage);
        this.model = model;
    }

    @FXML
    private void initialize() {
        this.storage.teachingLoadManager().addUpdateListener(this::updateSubjectData);

        this.infoText.setText(this.model.name());
        setupGrid();
        updateSubjectData();
    }

    private void updateSubjectData() {
        setSubjects(this.storage.teachingLoadManager().getByGroup(this.model));
    }

    private void setSubjects(Collection<TeachingLoadModel> models) {
        ObservableList<Node> loads = FXCollections.observableArrayList();
        for (TeachingLoadModel model : models) loads.add(new DraggableTeachingLoad(model).getContent());
        this.subjectsList.setItems(loads);
    }

    private void setupGrid() {
        Day[] days = {Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday, Day.Friday, Day.Saturday};


        for (int i = 0; i < days.length; i++) {
            Label dayName = new Label(days[i].name());
            dayName.setPadding(new Insets(40));
            tableGrid.add(dayName, i, 0);
            for (int j = 1; j < 6; j++) {
                tableGrid.add(new TimetableLessonHolder(this.storage, days[i], j).getContent(), i, j);
            }
        }
    }
}
