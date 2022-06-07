package com.artech.timetableapp.ui.teachers;

import com.artech.timetableapp.ui.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.storage.IStorage;

/**
 * Контроллер редактирования преподавателя
 */
public class TeacherEditController extends ModelEditController<TeacherModel> {

    /**
     * Конструктор контроллера редактирования преподавателя
     * @param storage Хранилище данных
     * @param model Модель
     */
    public TeacherEditController(IStorage storage, TeacherModel model) {
        super(storage, model);
    }

    @Override
    protected TeacherModel handleEdit(TeacherModel model) {
        TeacherModel proto = new TeacherEditDialog().ask(model);
        if (proto != null)
            return new TeacherModel(model.id(), proto.firstName(), proto.secondName(), proto.lastName());
        else
            return null;
    }

    @Override
    protected IObjectManager<TeacherModel> getManager() {
        return this.storage.teacherManager();
    }
}
