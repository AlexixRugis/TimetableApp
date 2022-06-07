package com.artech.timetableapp.core.model;

/**
 * Модель данных специальности
 * @param id Идентификатор
 * @param name Название
 */
public record SpecialityModel(Integer id, String name) implements IModel {
    @Override
    public String toString() {
        return this.name;
    }
}
