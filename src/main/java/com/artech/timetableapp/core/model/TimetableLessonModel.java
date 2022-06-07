package com.artech.timetableapp.core.model;

/**
 * Модель данных расписания
 * @param id Идентификатор
 * @param day День недели
 * @param lessonNumber Номер занятия
 * @param load Педагогическая нагрузка
 * @param group Группа
 */
public record TimetableLessonModel(Integer id, Day day, Integer lessonNumber, TeachingLoadModel load, GroupModel group) implements IModel {
    @Override
    public String toString() {
        return String.format("%s\n%s", this.load.subject(), this.load.teacher());
    }
}
