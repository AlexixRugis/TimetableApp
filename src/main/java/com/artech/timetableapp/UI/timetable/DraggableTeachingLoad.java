package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.*;

public class DraggableTeachingLoad extends View {
    public TeachingLoadModel teachingLoad;

    public DraggableTeachingLoad(TeachingLoadModel teachingLoad) {
        this.teachingLoad = teachingLoad;
    }

    @Override
    protected Node build() {
        Label label = new Label(this.teachingLoad.subject() + "\n" + this.teachingLoad.teacher());
        label.setPadding(new Insets(10));

        label.setOnDragDetected(event -> {
            Dragboard db = label.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(teachingLoad.id()));
            db.setContent(content);
            event.consume();
        });
        label.setOnDragDone(event -> event.consume());


        return label;
    }

    @Override
    public String getName() {
        return "Нагрузка";
    }
}
