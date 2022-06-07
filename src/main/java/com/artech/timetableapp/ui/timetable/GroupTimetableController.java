package com.artech.timetableapp.ui.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.core.timetable.TimetableActions;
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

public class GroupTimetableController extends Controller {

    @FXML
    private Text infoText;

    @FXML
    private ListView<Node> subjectsList;

    @FXML
    private GridPane tableGrid;

    private final ArrayList<TimetableLessonHolder> timetable = new ArrayList<>();

    private GroupModel groupModel;
    private final TimetableActions timetableActions;
    public GroupTimetableController(IStorage storage, GroupModel model) {
        super(storage);
        this.groupModel = model;
        this.timetableActions = new TimetableActions(storage, model);
    }

    @FXML
    private void initialize() {
        this.storage.teachingLoadManager().addUpdateListener(this::updateSubjectListData);
        this.storage.timetableLessonManager().addUpdateListener(this::updateTimetableData);

        setupGrid();
        updateTimetableData();
    }

    @FXML
    private void exportODS() {
        TimetableODSExporter exporter = new TimetableODSExporter(this.storage, this.groupModel);
        exporter.perform();
    }

    @FXML
    private void exportPDF() {
        TimetablePDFExporter exporter = new TimetablePDFExporter(this.storage, this.groupModel);
        exporter.perform();
    }

    public TimetableActions getTimetableActions() {
        return this.timetableActions;
    }

    public void setModel(GroupModel model) {
        this.groupModel = model;
        this.timetableActions.setModel(model);
        updateTimetableData();
    }

    private void updateTimetableData() {
        updateSubjectListData();
        updateGridData();
        updateHoursStatistics();
    }

    private void updateSubjectListData() {
        setSubjects(this.storage.teachingLoadManager().getByGroup(this.groupModel));
    }

    private void updateGridData() {
        Collection<TimetableLessonModel> lessons = this.storage.timetableLessonManager().getData(this.groupModel);
        for (TimetableLessonHolder holder : timetable) {
            holder.setLessonModel(this.timetableActions.getLessonModel(lessons, holder.getDay(), holder.getLesson()));
        }
    }

    private void updateHoursStatistics() {
        Integer allHours = this.storage.timetableLessonManager().getHours(this.groupModel);
        boolean valid = false;
        Integer hoursPerWeek = TimetableApplication.getInstance().getSettings().getLessonsPerWeek();
        this.infoText.setText("Заполнено часов: " + allHours + "/" + hoursPerWeek);

        if (allHours <= hoursPerWeek) {
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

    private void setupGrid() {
        Day[] days = {Day.Monday, Day.Tuesday, Day.Wednesday, Day.Thursday, Day.Friday, Day.Saturday};

        for (int i = 0; i < days.length; i++) {
            Label dayLabel = new Label(days[i].toString());
            dayLabel.setPadding(new Insets(30));
            tableGrid.add(dayLabel, i, 0);

            for (int j = 1; j <= TimetableApplication.getInstance().getSettings().getLessonsPerDay(); j++) {
                TimetableLessonHolder holder = new TimetableLessonHolder(this, days[i], j);
                timetable.add(holder);
                tableGrid.add(holder.getContent(), i, j);
            }
        }
    }
}
