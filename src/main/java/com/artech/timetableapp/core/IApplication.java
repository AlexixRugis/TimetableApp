package com.artech.timetableapp.core;

import com.artech.timetableapp.core.storage.IStorage;
import javafx.stage.Stage;

public interface IApplication {
    ISettings getSettings();
    IStorage getStorage();
    Stage getPrimaryStage();
}
