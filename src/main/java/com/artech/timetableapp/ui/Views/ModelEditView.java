package com.artech.timetableapp.ui.Views;

import com.artech.timetableapp.TimetableApplication;
import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.storage.IStorage;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.net.URL;

/**
 * Представление редактирования модели
 * @param <T> Класс модели
 */
public abstract class ModelEditView<T extends IModel> extends FXMLView {

    protected final T model;
    protected final IStorage storage;
    protected final IObjectManager<T> manager;

    /**
     * Конструктор представления редактирования модели
     * @param model Модель
     * @param storage Зранилище данных
     */
    public ModelEditView(T model, IStorage storage) {
        this.model = model;
        this.storage = storage;
        this.manager = getManager();
    }

    @Override
    protected URL getFXMLResourceURL() {
        return TimetableApplication.class.getResource("model-edit-item.fxml");
    }

    @Override
    public String getName() {
        return this.model.toString();
    }

    @Override
    public Node getContent() {
        Node node = super.getContent();

        Text text = (Text)node.lookup("#text");
        text.setText(model.toString());

        return node;
    }

    /**
     * Получает менеджер моделей
     * @return Менеджер моделей
     */
    protected abstract IObjectManager<T> getManager();
}
