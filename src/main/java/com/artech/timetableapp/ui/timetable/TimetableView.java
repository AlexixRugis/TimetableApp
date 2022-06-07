package com.artech.timetableapp.ui.timetable;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.FXMLView;
import com.artech.timetableapp.core.storage.IStorage;

import java.net.URL;

public class TimetableView extends FXMLView {

    private final IStorage storage;

    public TimetableView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("timetable-view.fxml");
    }

    @Override
    protected Controller getController() {
        return new TimetableController(this.storage);
    }

    @Override
    public String getName() {
        return "Расписание";
    }
}
