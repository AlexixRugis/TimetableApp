package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.storage.IStorage;

/**
 * Контроллер представления
 */
public abstract class Controller {
    protected final IStorage storage;

    /**
     * Конструктор контроллера
     * @param storage Зранилище данных
     */
    public Controller(IStorage storage) {
        this.storage = storage;
    }
}
