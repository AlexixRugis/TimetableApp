package com.artech.timetableapp.UI.Views;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Диалог действие
 */
public abstract class ActionDialog {
    /**
     * Получает представление диалога
     * @return Представление диалога
     */
    protected abstract View getView();

    /**
     * Исполняет действие
     * @return Статус выполнения действия
     */
    protected abstract Boolean performAction();

    /**
     * Проверяет правильность заполнения диалога
     * @return Статус проверки
     */
    protected abstract boolean validate();

    /**
     * Запускает диалоговое окно
     * @return Статус выполнения диалогового окна
     */
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

        if (dialog.showAndWait().isPresent())
            return dialog.showAndWait().get();

        return false;
    }
}
