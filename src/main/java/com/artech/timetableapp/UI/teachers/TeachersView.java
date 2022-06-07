package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.Controllers.Controller;
import com.artech.timetableapp.UI.Views.ModelListView;
import com.artech.timetableapp.core.storage.IStorage;

/**
 * Представление списка преподавателей
 */
public final class TeachersView extends ModelListView {
    private final IStorage storage;

    /**
     * Конструктор представления списка преподавателей
     * @param storage Хранилище данных
     */
    public TeachersView(IStorage storage) {
        this.storage = storage;
    }

    @Override
    public String getName() {
        return "Преподаватели";
    }

    @Override
    protected Controller getController() {
        return new TeachersController(this.storage);
    }
}
