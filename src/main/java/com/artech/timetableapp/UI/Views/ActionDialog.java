package com.artech.timetableapp.UI.Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public abstract class ActionDialog {
    protected abstract View getView();
    protected abstract Boolean performAction();
    protected abstract boolean validate();

    public Boolean ask() {
        Dialog<Boolean> dialog = new Dialog<>();
        View view = getView();
        dialog.setHeaderText(view.getName());
        dialog.getDialogPane().setContent(getView().getContent());
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
            if (!validate()) event.consume();
        });
        dialog.setResultConverter(param -> {
            if (param == ButtonType.OK) {
                return performAction();
            }
            return false;
        });
        return dialog.showAndWait().get();
    }
}
