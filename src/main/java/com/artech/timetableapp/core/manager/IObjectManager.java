package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.IModel;

import java.util.Collection;

/**
 * Менеджер объектов описывает методы работы с коллекциями моделей
 * @param <T> Класс модели
 */
public interface IObjectManager<T extends IModel> {

    /**
     * Добавляет слушатель события обновления объектов
     * @param listener Слушатель события, который должен быть добавлен
     */
    void addUpdateListener(IManagerUpdateListener listener);

    /**
     * Удаляет слушатель события обновлея объектов
     * @param listener Слушатель события, который должен быть удален
     */
    void removeUpdateListener(IManagerUpdateListener listener);

    /**
     * Создает модель в коллекции
     * @param prototype Прототип модели (Используются данные модели, Id устанавливается автоматически)
     * @return Возвращает статус выполнения операции (true - модель добавлена, false - модель не добавлена)
     * @see IModel
     */
    boolean tryCreate(T prototype);

    /**
     * Получает модель из коллекции
     * @param id Идентификатор, по которому будет получена модель
     * @return Полученная из коллекции модель (null, если модели с переданным идентификатором не существует)
     */
    T get(Integer id);

    /**
     * Получает все модели коллекции
     * @return Коллекция моделей
     */
    Collection<T> getAll();

    /**
     * Обновляет модель в коллекции
     * @param model Модель, которая будет обновлена (будет обновлена модель коллекции, имеющая идентификатор переданной модели)
     * @return Возвращает статус выполнения операции (true - модель убновлена, false - модель не обновлена)
     */
    boolean tryUpdate(T model);

    /**
     * Удвляет модель из коллекции
     * @param model Модель, которая будет удалена (будет удалена модель коллекции, имеющая идентификатор переданной модели)
     * @return Возвращает статус выполнения операции (true - модель удалена, false - модель не удалена)
     */
    boolean tryDelete(T model);

    /**
     * Очищает коллекцию моделей
     */
    void clearAllData();
}
