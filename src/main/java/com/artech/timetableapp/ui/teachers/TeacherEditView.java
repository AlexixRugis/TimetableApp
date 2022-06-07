package com.artech.timetableapp.ui.teachers;

import com.artech.timetableapp.ui.Controllers.Controller;
import com.artech.timetableapp.ui.Views.ModelEditView;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;

/**
 * Представление редактирования данных преподавателя
 */
public class TeacherEditView extends ModelEditView<TeacherModel> {

    /**
     * Конструктор представления редактирования данных преподавателя
     * @param model Модель
     * @param storage Хранилище
     */
    public TeacherEditView(TeacherModel model, IStorage storage) {
        super(model, storage);
    }

    @Override
    protected IObjectManager<TeacherModel> getManager() {
        return this.storage.teacherManager();
    }

    @Override
    protected Controller getController() {
        return new TeacherEditController(this.storage, this.model);
    }
}
