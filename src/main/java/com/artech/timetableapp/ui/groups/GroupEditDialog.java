package com.artech.timetableapp.ui.groups;

import com.artech.timetableapp.ui.Views.ModelDialog;
import com.artech.timetableapp.ui.Views.View;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.SpecialityModel;

public class GroupEditDialog extends ModelDialog<GroupModel> {

    private final GroupEditDialogView view;

    public GroupEditDialog(IObjectManager<SpecialityModel> specialityManager) {
        this.view = new GroupEditDialogView(specialityManager);
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
    protected void setDialogData(GroupModel model) {
        this.view.setGroupName(model.name());
        this.view.setSpecialityModel(model.speciality());
        this.view.setSemester(model.semester());
        this.view.setNumStudyWeeks(model.numberOfStudyWeeks());
    }

    @Override
    protected GroupModel getDialogData() {
        return new GroupModel(
                0,
                this.view.getGroupName(),
                this.view.getSpeciality(),
                this.view.getSemester(),
                this.view.getNumStudyWeeks()
        );
    }

    @Override
    protected boolean validate() {
        return !this.view.getGroupName().isEmpty() && this.view.getSpeciality() != null;
    }
}
