package com.artech.timetableapp.core.model;

/**
 * Модель данных преподавателя
 * @param id Идентификатор
 * @param firstName Имя
 * @param secondName Отчество
 * @param lastName Фамилия
 */
public record TeacherModel(Integer id, String firstName, String secondName, String lastName) implements IModel {
    @Override
    public String toString() {
        return String.format("%s %s %s", this.firstName, this.secondName, this.lastName);
    }
}
