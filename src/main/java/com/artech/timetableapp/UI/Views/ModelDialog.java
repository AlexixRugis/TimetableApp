package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.core.model.IModel;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public abstract class ModelDialog<T extends IModel> {
    private final ButtonType saveButtonType = new ButtonType("Сохранить", ButtonBar.ButtonData.OK_DONE);

    protected abstract View getView();
    protected abstract String getHeader();
    protected abstract void setDialogData(T model);
    protected abstract T getDialogData();

    protected Dialog<T> getDialog(T model) {
        Dialog<T> dialog = new Dialog<>();

        dialog.setHeaderText(getHeader());
        dialog.getDialogPane().setContent(getView().getContent());
        if (model != null) {
            setDialogData(model);
        }
        dialog.setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                return getDialogData();
            }
            return null;
        });
        addButtons(dialog);

        return dialog;
    }

    public T ask() {
        return ask(null);
    }

    public T ask(T model) {
        Dialog<T> dialog = getDialog(model);
        dialog.showAndWait();
        return dialog.getResult();
    }

    private void addButtons(Dialog<T> dialog) {
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().lookupButton(saveButtonType).addEventFilter(
                ActionEvent.ACTION, event -> {
                    if (!validate()) {
                        event.consume();
                    }
                }
        );
    }

    protected abstract boolean validate();
}
