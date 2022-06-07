package com.artech.timetableapp.ui.teachers;

import com.artech.timetableapp.ui.Views.ModelDialog;
import com.artech.timetableapp.ui.Views.View;
import com.artech.timetableapp.core.model.TeacherModel;

/**
 * Диалог редактирования данных преподавателя
 */
public class TeacherEditDialog extends ModelDialog<TeacherModel> {
    private final TeacherEditDialogView view;

    /**
     * Конструктор диалога данных преподавателя
     */
    public TeacherEditDialog() {
        this.view = new TeacherEditDialogView();
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

    @Override
    protected boolean validate() {
        return !this.view.getFirstName().isEmpty() &&
                !this.view.getSecondName().isEmpty() &&
                !this.view.getLastName().isEmpty();
    }
}
