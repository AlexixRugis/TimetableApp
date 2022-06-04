package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DraggableTeachingLoad extends View {
    private final TeachingLoadModel teachingLoad;

    private IDragEventHandler<TeachingLoadModel> onBeginDrag;
    private IDragEventHandler<TeachingLoadModel> onEndDrag;

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
            if (onBeginDrag != null) onBeginDrag.handleEvent(teachingLoad);
            event.consume();
        });
        label.setOnDragDone(event -> {
            if (onEndDrag != null) onEndDrag.handleEvent(teachingLoad);
            event.consume();
        });


        return label;
    }

    @Override
    public String getName() {
        return "Нагрузка";
    }

    public void setOnDragBegin(IDragEventHandler<TeachingLoadModel> handler) {
        this.onBeginDrag = handler;
    }

    public void setOnEndDrag(IDragEventHandler<TeachingLoadModel> handler) {
        this.onEndDrag = handler;
    }
}
