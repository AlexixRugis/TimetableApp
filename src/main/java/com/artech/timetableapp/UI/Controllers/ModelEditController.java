package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Контроллер редактирорования модели
 * @param <T> Класс модели
 */
public abstract class ModelEditController<T extends IModel> extends Controller{

    protected final T model;
    private final IObjectManager<T> manager;

    /**
     * Конструктор контроллера редактирования модели
     * @param storage Хранилище данных
     * @param model Модель
     */
    public ModelEditController(IStorage storage, T model) {
        super(storage);
        this.manager = getManager();
        this.model = model;
    }

    /**
     * Обрабатывает нажатие кнопки редактирования
     */
    @FXML
    public void onEdit() {
        T upd = handleEdit(this.model);
        if (upd != null)
            this.manager.tryUpdate(upd);
    }

    /**
     * Обрабатывает нажатие кнопки удаления
     */
    @FXML
    public void onDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вы уверены?");
        alert.setHeaderText("Вы уверены что хотите удалить этот объект?");
        alert.setContentText("Восстановление после удаления невозможно!");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            this.manager.tryDelete(this.model);
        }

        System.out.println("delete " + this.model.id());
    }

    /**
     * Обрабатывает редактирование модели
     * @param model Исходная модель
     * @return Модель после редактирования
     */
    protected abstract T handleEdit(T model);

    /**
     * Получает менеджер моделей
     * @return Менеджер моделей
     */
    protected abstract IObjectManager<T> getManager();
}
