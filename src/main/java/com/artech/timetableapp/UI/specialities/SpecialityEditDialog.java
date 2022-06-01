package com.artech.timetableapp.UI.specialities;

import com.artech.timetableapp.UI.Views.ModelDialog;
import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.model.SpecialityModel;

public class SpecialityEditDialog extends ModelDialog<SpecialityModel> {

    private final SpecialityEditDialogView view;

    public SpecialityEditDialog() {
        this.view = new SpecialityEditDialogView();
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
    protected void setDialogData(SpecialityModel model) {
        this.view.setSpecialityName(model.name());
    }

    @Override
    protected SpecialityModel getDialogData() {
        return new SpecialityModel(0, this.view.getSpecialityName());
    }

    @Override
    protected boolean validate() {
        return !this.view.getSpecialityName().isEmpty();
    }
}
