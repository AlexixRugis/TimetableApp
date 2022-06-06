package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;

public class DraggableTeachingLoad extends FXMLView {
    private final IStorage storage;
    private final TeachingLoadModel teachingLoad;

    private IDragEventHandler<TeachingLoadModel> onBeginDrag;
    private IDragEventHandler<TeachingLoadModel> onEndDrag;

    public DraggableTeachingLoad(IStorage storage, TeachingLoadModel teachingLoad) {
        this.storage = storage;
        this.teachingLoad = teachingLoad;
    }

    @Override
    protected Node build() {
        Node node = super.build();

        AnchorPane pane = (AnchorPane) node.lookup("#pane");

        Text name = (Text) node.lookup("#name");
        name.setText(this.teachingLoad.subject() + "\n" + this.teachingLoad.teacher());

        Label hours = (Label) node.lookup("#hours");
        Integer hoursAmount = this.storage.timetableLessonManager().getHours(this.teachingLoad);
        hours.setText(hoursAmount + "/" + teachingLoad.hoursPerWeek());
        if (Objects.equals(hoursAmount, teachingLoad.hoursPerWeek())) hours.setTextFill(Color.GREEN);
        else if (hoursAmount > teachingLoad.hoursPerWeek()) hours.setTextFill(Color.RED);

        pane.setOnDragDetected(event -> {
            Dragboard db = pane.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(teachingLoad.id()));
            db.setContent(content);
            if (onBeginDrag != null) onBeginDrag.handleEvent(teachingLoad);
            event.consume();
        });
        pane.setOnDragDone(event -> {
            if (onEndDrag != null) onEndDrag.handleEvent(teachingLoad);
            event.consume();
        });


        return node;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("draggable-teaching-load.fxml");
    }

    @Override
    protected Controller getController() {
        return null;
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
