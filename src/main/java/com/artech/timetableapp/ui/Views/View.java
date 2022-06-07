package com.artech.timetableapp.ui.Views;

import javafx.scene.Node;

/**
 * Представление
 */
public abstract class View
{
    /**
     * Корневой узел представления
     */
    private Node content;

    /**
     * Собирает представление
     * @return Корневой узел представления
     */
    protected abstract Node build();

    /**
     * Получает название представления
     * @return Название представления
     */
    public abstract String getName();

    /**
     * Получает корневой узел представления
     * @return Получает корневой узел представления
     */
    public Node getContent() {
        if (this.content == null) this.content = build();
        return this.content;
    }

    /**
     * Получает узел представления
     * @param selector Иденитификатор
     * @param type Тип узла
     * @return Найденный узел представления
     * @param <T> Класс узла
     */
    public <T extends Node> T lookup(String selector, Class<T> type) {
        if (this.content == null) this.content = build();
        return type.cast(this.content.lookup(selector));
    }
}
