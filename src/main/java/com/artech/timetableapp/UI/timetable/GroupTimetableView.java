package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.FXMLView;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;

import java.net.URL;

public class GroupTimetableView extends FXMLView {

    private final IStorage storage;
    private final GroupModel model;

    public GroupTimetableView(IStorage storage, GroupModel groupModel) {
        this.storage = storage;
        this.model = groupModel;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("group-timetable-view.fxml");
    }

    @Override
    protected Controller getController() {
        return new GroupTimetableController(this.storage, this.model);
    }

    @Override
    public String getName() {
        return "Группа";
    }
}