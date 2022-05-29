package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.storage.IStorage;

public abstract class Controller {
    protected final IStorage storage;

    public Controller(IStorage storage) {
        this.storage = storage;
    }
}
