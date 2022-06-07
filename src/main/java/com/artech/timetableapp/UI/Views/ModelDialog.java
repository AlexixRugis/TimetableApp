package com.artech.timetableapp.UI.Views;

import com.artech.timetableapp.core.model.IModel;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Диалог модели
 * @param <T> Класс модели
 */
public abstract class ModelDialog<T extends IModel> {
    /**
     * Кнопка сохранения диалогового окна
     */
    private final ButtonType saveButtonType = new ButtonType("Сохранить", ButtonBar.ButtonData.OK_DONE);

    /**
     * Получает представления диалога
     * @return Представление диалога
     */
    protected abstract View getView();

    /**
     * Получает заголовка диалога
     * @return Заголовок диалога
     */
    protected abstract String getHeader();

    /**
     * Устанавливает данные модели в диалоговое окно
     * @param model Модель
     */
    protected abstract void setDialogData(T model);

    /**
     * Получает данные модели из диалогового окна
     * @return Модель
     */
    protected abstract T getDialogData();

    /**
     * Получает диалог для данной модели
     * @param model Модель
     * @return Диалог
     */
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

    /**
     * Запускает диалоговое окно
     * @return Модель
     */
    public T ask() {
        return ask(null);
    }

    /**
     * Запускает диалоговое окно
     * @param model Исходная модель
     * @return Модель
     */
    public T ask(T model) {
        Dialog<T> dialog = getDialog(model);
        dialog.showAndWait();
        return dialog.getResult();
    }

    /**
     * Добавляет кнопки в диалог
     * @param dialog Диалог
     */
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

    /**
     * Проверяет правильность введенных данных
     * @return Статус проверки
     */
    protected abstract boolean validate();
}
