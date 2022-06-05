package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.Objects;

public class TimetableLessonHolder extends View {

    private final String red = "-fx-background-color: #f97777;";
    private final String green = "-fx-background-color: #78fabf;";

    private final IStorage storage;
    private final GroupModel group;
    private final Day day;
    private final Integer lesson;

    private TimetableLessonModel timetableLesson;

    private Pane root;
    private boolean canDrop;

    public TimetableLessonHolder(IStorage storage, GroupModel group, Day day, Integer lesson) {
        this.storage = storage;
        this.group = group;
        this.day = day;
        this.lesson = lesson;
    }

    @Override
    protected Node build() {
        root = new Pane();
        Label label = new Label(String.valueOf(this.storage.timetableLessonManager().getData(this.group, this.day, this.lesson)));
        root.getChildren().add(label);
        label.setPadding(new Insets(20));

        root.setOnDragOver(event -> {
            if (event.getGestureSource() != label &&
                    event.getDragboard().hasString() &&
                    canDrop) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });
        root.setOnDragDropped(event -> {
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

                timetableLesson = new TimetableLessonModel(0, this.day, this.lesson, this.storage.teachingLoadManager().get(id), this.group);
                this.storage.timetableLessonManager().setData(timetableLesson);

                label.setText(timetableLesson.toString());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        return root;
    }

    @Override
    public String getName() {
        return "Предмет";
    }

    public void setDragModel(TeachingLoadModel loadModel) {
        if (loadModel == null) {
            canDrop = false;
            root.setStyle("-fx-background-color: white;");
            return;
        }

        if (loadModel.teacher() == null) {
            canDrop = false;
            root.setStyle(red);
            return;
        }

        Collection<TimetableLessonModel> models = this.storage.timetableLessonManager().getData(loadModel.teacher(), day, lesson);

        for (TimetableLessonModel model : models) {
            if (!Objects.equals(model.group().id(), group.id())) {
                canDrop = false;
                root.setStyle(red);
                return;
            }
        }

        canDrop = true;
        root.setStyle(green);
    }
}
