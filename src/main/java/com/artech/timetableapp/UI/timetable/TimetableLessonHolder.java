package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

public class TimetableLessonHolder extends View{
    private final IStorage storage;
    private final Day day;
    private final Integer lesson;

    private TimetableLessonModel timetableLesson;

    public TimetableLessonHolder(IStorage storage, Day day, Integer lesson) {
        this.storage = storage;
        this.day = day;
        this.lesson = lesson;
    }

    @Override
    protected Node build() {
        Label label = new Label(day.name() + " - " + lesson);
        label.setPadding(new Insets(20));

        label.setOnDragOver(event -> {
            if (event.getGestureSource() != label &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });
        label.setOnDragEntered(event -> {
            if (event.getGestureSource() != label &&
                    event.getDragboard().hasString()) {
                label.setTextFill(Color.GREEN);
            }
            event.consume();
        });
        label.setOnDragExited(event -> {
            label.setTextFill(Color.BLACK);
            event.consume();
        });
        label.setOnDragDropped(event -> {
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

                timetableLesson = new TimetableLessonModel(0, this.day, this.lesson, this.storage.teachingLoadManager().get(id));
                label.setText(timetableLesson.toString());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        return label;
    }

    @Override
    public String getName() {
        return "Предмет";
    }
}
