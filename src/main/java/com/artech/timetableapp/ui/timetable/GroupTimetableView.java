package com.artech.timetableapp.ui.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.FXMLView;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.storage.IStorage;

import java.net.URL;

public class GroupTimetableView extends FXMLView {

    private final GroupTimetableController controller;

    public GroupTimetableView(IStorage storage, GroupModel groupModel) {
        this.controller = new GroupTimetableController(storage, groupModel);
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("group-timetable-view.fxml");
    }

    @Override
    protected Controller getController() {
        return this.controller;
    }

    @Override
    public String getName() {
        return "Группа";
    }

    public void setModel(GroupModel model) {
        this.controller.setModel(model);
    }
}
