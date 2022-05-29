package com.artech.timetableapp.core;

import com.artech.timetableapp.core.storage.IStorage;

public interface IApplication {
    IStorage getStorage();
}
