package com.artech.timetableapp.UI.teachers;

import com.artech.timetableapp.UI.Views.ModelDialog;
import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.model.TeacherModel;

public class TeacherEditDialog extends ModelDialog<TeacherModel> {
    private final TeacherEditDialogView view;

    public TeacherEditDialog() {
        this.view = new TeacherEditDialogView();
    }

    @Override
    protected View getView() {
        return new TeacherEditDialogView();
    }

    @Override
    protected String getHeader() {
        return "Редактирование";
    }

    @Override
    protected void setDialogData(TeacherModel model) {
        this.view.setFirstName(model.firstName());
        this.view.setSecondName(model.secondName());
        this.view.setLastName(model.lastName());
    }

    @Override
    protected TeacherModel getDialogData() {
        return new TeacherModel(
                0,
                this.view.getFirstName(),
                this.view.getSecondName(),
                this.view.getLastName()
        );
    }
}
