package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.core.model.IModel;

import java.net.URL;

public abstract class ModelListView<T extends IModel> extends FXMLView {
    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("list-view.fxml");
    }
}
