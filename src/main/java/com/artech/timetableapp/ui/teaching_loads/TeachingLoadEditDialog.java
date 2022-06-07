package com.artech.timetableapp.ui.teaching_loads;

import com.artech.timetableapp.ui.Views.ModelDialog;
import com.artech.timetableapp.ui.Views.View;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;

public class TeachingLoadEditDialog extends ModelDialog<TeachingLoadModel> {

    private final TeachingLoadEditDialogView view;

    public TeachingLoadEditDialog(IObjectManager<TeacherModel> teacherManager, IObjectManager<GroupModel> groupManager, IObjectManager<SubjectModel> subjectManager) {
        this.view = new TeachingLoadEditDialogView(teacherManager, groupManager, subjectManager);
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
    protected void setDialogData(TeachingLoadModel model) {
        this.view.setTeacher(model.teacher());
        this.view.setGroup(model.group());
        this.view.setSubject(model.subject());
        this.view.setHoursPerWeek(model.hoursPerWeek());
    }

    @Override
    protected TeachingLoadModel getDialogData() {
        return new TeachingLoadModel(
                0,
                this.view.getTeacher(),
                this.view.getSubject(),
                this.view.getGroup(),
                this.view.getHoursPerWeek()
        );
    }

    @Override
    protected boolean validate() {
        return this.view.getTeacher() != null && this.view.getGroup() != null && this.view.getSubject() != null;
    }
}
