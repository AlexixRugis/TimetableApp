package com.artech.timetableapp.ui.subjects;

import com.artech.timetableapp.ui.Views.ModelDialog;
import com.artech.timetableapp.ui.Views.View;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.SubjectModel;

public class SubjectEditDialog extends ModelDialog<SubjectModel> {

    private final SubjectEditDialogView view;

    public SubjectEditDialog(IObjectManager<SpecialityModel> specialityManager) {
        this.view = new SubjectEditDialogView(specialityManager);
    }

    @Override
    protected View getView() {
        return this.view;
    }

    @Override
    protected String getHeader() {
        return "Редактирование";
    }

    @Override
    protected void setDialogData(SubjectModel model) {
        this.view.setSubjectName(model.name());
        this.view.setSpecialityModel(model.speciality());
        this.view.setSemester(model.semester());
    }

    @Override
    protected SubjectModel getDialogData() {
        return new SubjectModel(
                0,
                this.view.getSubjectName(),
                this.view.getSpeciality(),
                this.view.getSemester()
        );
    }

    @Override
    protected boolean validate() {
        return !this.view.getSubjectName().isEmpty() && this.view.getSpeciality() != null;
    }
}
