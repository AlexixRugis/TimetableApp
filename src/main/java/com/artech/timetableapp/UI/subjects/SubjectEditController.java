package com.artech.timetableapp.UI.subjects;

import com.artech.timetableapp.UI.Controllers.ModelEditController;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.storage.IStorage;

public class SubjectEditController extends ModelEditController<SubjectModel> {
    public SubjectEditController(IStorage storage,SubjectModel model) {
        super(storage, model);
    }

    @Override
    protected SubjectModel handleEdit(SubjectModel model) {
        SubjectModel proto = new SubjectEditDialog(this.storage.specialityManager()).ask(model);
        if (proto != null){
            return new SubjectModel(
                    model.id(),
                    proto.name(),
                    proto.speciality(),
                    proto.semester()
                    );
        }

        return null;
    }

    @Override
    protected IObjectManager<SubjectModel> getManager() {
        return this.storage.subjectManager();
    }
}
