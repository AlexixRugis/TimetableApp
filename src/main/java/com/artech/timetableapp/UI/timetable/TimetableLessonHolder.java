package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Collection;
import java.util.Objects;

public class TimetableLessonHolder extends FXMLView {
    private final GroupTimetableController timetableController;
    private final TimetableLessonModel lessonModel;
    private final Day day;
    private final Integer lesson;

    private Pane pane;
    private Label label;
    private Button button;
    private boolean canDrop;

    public TimetableLessonHolder(GroupTimetableController controller, TimetableLessonModel lessonModel, Day day, Integer lesson) {
        this.timetableController = controller;
        this.lessonModel = lessonModel;
        this.day = day;
        this.lesson = lesson;
    }

    @Override
    protected Node build() {
        Node node = super.build();

        pane = (Pane) node.lookup("#pane");
        label = (Label) node.lookup("#label");
        button = (Button) node.lookup("#button");

        button.setOnAction(event -> {
            this.timetableController.clearData(this.day, this.lesson);
        });

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
                this.timetableController.setData(id, this.day, this.lesson);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        setModel(this.lessonModel);

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

        if (this.timetableController.hasData(loadModel.teacher(), this.day, this.lesson)) {
            canDrop = false;
            pane.setStyle(red);
            return;
        }

        canDrop = true;
        String green = "-fx-background-color: #78fabf;";
        pane.setStyle(green);
    }

    private void setModel(TimetableLessonModel model) {
        if (model == null) {
            label.setText("");
            button.setVisible(false);
            return;
        }


        label.setText(String.valueOf(model));
        button.setVisible(true);
    }
}
