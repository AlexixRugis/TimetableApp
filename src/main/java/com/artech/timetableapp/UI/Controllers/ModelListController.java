package com.artech.timetableapp.UI.Controllers;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.Collection;

/**
 * Контроллер списка моделей
 * @param <T> Класс моделей
 */
public abstract class ModelListController<T extends IModel> extends Controller{
    protected final IObjectManager<T> manager;

    /**
     * Список моделей
     */
    @FXML
    protected ListView<Node> list;

    /**
     * Кнопка импорта моделей
     */
    @FXML
    protected Button importButton;

    /**
     * Конструктор контроллера списка моделей
     * @param storage Хранилище данных
     */
    public ModelListController(IStorage storage) {
        super(storage);
        this.manager = getManager();
    }

    /**
     * Обрабатывает нажатие кнопки добавления
     */
    @FXML
    public void addNew() {
        T model = createModel();
        if (model != null) this.manager.tryCreate(model);
    }

    /**
     * Обрабатывает нажатие кнопки импорта
     */
    @FXML
    public void importData() {
        handleImport();
    }

    /**
     * Обрабатывает импорт моделей
     */
    protected abstract void handleImport();

    /**
     * Обрабатывает добавление новой модели
     * @return Добавленная модель
     */
    protected abstract T createModel();

    /**
     * Обновляет список моделей
     */
    protected void updateData() {
        Collection<T> models = this.manager.getAll();
        setListData(models);
    }

    /**
     * Устанавливает список моделей
     * @param items Модели
     */
    protected void setListData(Collection<T> items) {
        ObservableList<Node> views = FXCollections.observableArrayList();
        for (T item : items) views.add(getEditView(item));
        list.setItems(views);
    }

    /**
     * Получает представление редактирования модели
     * @param item Модель для редактирования
     * @return Представление редактирования модели
     */
    protected abstract Node getEditView(T item);

    /**
     * Получает менеджер моделей
     * @return Менеджер моделей
     */
    protected abstract IObjectManager<T> getManager();
}
