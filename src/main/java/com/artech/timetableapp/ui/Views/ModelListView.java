package com.artech.timetableapp.ui.Views;

import com.artech.timetableapp.TimetableApplication;

import java.net.URL;

/**
 * Представление - список моделей
 */
public abstract class ModelListView extends FXMLView {
    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("list-view.fxml");
    }
}
