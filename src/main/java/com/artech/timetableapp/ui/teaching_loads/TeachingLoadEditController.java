package com.artech.timetableapp.ui.teaching_loads;

import com.artech.timetableapp.ui.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.storage.IStorage;

public class TeachingLoadEditController extends ModelEditController<TeachingLoadModel> {
    public TeachingLoadEditController(IStorage storage, TeachingLoadModel model) {
        super(storage, model);
    }

    @Override
    protected TeachingLoadModel handleEdit(TeachingLoadModel model) {
        TeachingLoadModel proto = new TeachingLoadEditDialog(this.storage.teacherManager(), this.storage.groupManager(), this.storage.subjectManager())
                .ask(model);

        if (proto != null) {
            return new TeachingLoadModel(
                    model.id(),
                    proto.teacher(),
                    proto.subject(),
                    proto.group(),
                    proto.hoursPerWeek()
            );
        }

        return null;
    }

    @Override
    protected IObjectManager<TeachingLoadModel> getManager() {
        return this.storage.teachingLoadManager();
    }
}
