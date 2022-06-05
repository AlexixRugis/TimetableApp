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

    private final IStorage storage;
    private final GroupModel group;
    private final Day day;
    private final Integer lesson;
    private Pane pane;
    private Label label;
    private Button button;
    private boolean canDrop;

    public TimetableLessonHolder(IStorage storage, GroupModel group, Day day, Integer lesson) {
        this.storage = storage;
        this.group = group;
        this.day = day;
        this.lesson = lesson;
    }

    @Override
    protected Node build() {
        Node node = super.build();

        pane = (Pane) node.lookup("#pane");
        label = (Label) node.lookup("#label");
        button = (Button) node.lookup("#button");

        button.setOnAction(event -> setModel(null));

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

                setModel(new TimetableLessonModel(0, this.day, this.lesson, this.storage.teachingLoadManager().get(id), this.group));

                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        setModel(this.storage.timetableLessonManager().getData(this.group, this.day, this.lesson));

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

        Collection<TimetableLessonModel> models = this.storage.timetableLessonManager().getData(loadModel.teacher(), day, lesson);

        for (TimetableLessonModel model : models) {
            if (!Objects.equals(model.group().id(), group.id())) {
                canDrop = false;
                pane.setStyle(red);
                return;
            }
        }

        canDrop = true;
        String green = "-fx-background-color: #78fabf;";
        pane.setStyle(green);
    }

    private void setModel(TimetableLessonModel model) {
        if (model == null) {
            this.storage.timetableLessonManager().clearData(this.group, this.day, this.lesson);

            label.setText("");
            button.setVisible(false);

            return;
        }

        this.storage.timetableLessonManager().setData(model);
        label.setText(String.valueOf(model));
        button.setVisible(true);
    }
}
