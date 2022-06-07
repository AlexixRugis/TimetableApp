package com.artech.timetableapp.ui.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.FXMLView;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.net.URL;

public class TimetableLessonHolder extends FXMLView {
    private final GroupTimetableController timetableController;

    private final Day day;
    private final Integer lesson;

    private Pane pane;
    private Label label;
    private Button button;
    private boolean canDrop;

    public TimetableLessonHolder(GroupTimetableController controller, Day day, Integer lesson) {
        this.timetableController = controller;
        this.day = day;
        this.lesson = lesson;
    }

    @Override
    protected Node build() {
        Node node = super.build();

        pane = (Pane) node.lookup("#pane");
        label = (Label) node.lookup("#label");
        button = (Button) node.lookup("#button");

        button.setOnAction(event -> this.timetableController.getTimetableActions().clearData(this.day, this.lesson));

        pane.setOnDragOver(event -> {
            if (event.getGestureSource() != label &&
                    event.getDragboard().hasString() &&
                    canDrop) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });
        pane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Integer id;
                try {
                    id = Integer.parseInt(db.getString());
                }
                catch (NumberFormatException e) {
                    id = null;
                }
                this.timetableController.getTimetableActions().setData(id, this.day, this.lesson);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        return pane;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("timetable-holder.fxml");
    }

    @Override
    protected Controller getController() {
        return null;
    }

    @Override
    public String getName() {
        return "Предмет";
    }

    public void setDragModel(TeachingLoadModel loadModel) {
        if (loadModel == null) {
            canDrop = false;
            pane.setStyle("-fx-background-color: white;");
            return;
        }

        String red = "-fx-background-color: #f97777;";
        if (loadModel.teacher() == null) {
            canDrop = false;
            pane.setStyle(red);
            return;
        }

        if (this.timetableController.getTimetableActions().hasData(loadModel.teacher(), this.day, this.lesson)) {
            canDrop = false;
            pane.setStyle(red);
            return;
        }

        canDrop = true;
        String green = "-fx-background-color: #78fabf;";
        pane.setStyle(green);
    }

    public Day getDay() {
        return this.day;
    }

    public Integer getLesson() {
        return this.lesson;
    }

    public void setLessonModel(TimetableLessonModel lessonModel) {
        if (lessonModel == null) {
            label.setText("");
            button.setVisible(false);
            return;
        }

        label.setText(String.valueOf(lessonModel));
        button.setVisible(true);
    }
}
