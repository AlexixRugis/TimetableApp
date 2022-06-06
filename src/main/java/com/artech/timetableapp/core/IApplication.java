package com.artech.timetableapp.core;

import com.artech.timetableapp.core.storage.IStorage;
import javafx.stage.Stage;

public interface IApplication {
    IStorage getStorage();
    Stage getPrimaryStage();
}
