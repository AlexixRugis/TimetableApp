package com.artech.timetableapp.core.model;

/**
 * Модель данных педагогической нагрузки
 * @param id Идентификатор
 * @param teacher Преподаватель
 * @param subject Дисциплина
 * @param group Группа
 * @param hoursPerWeek Количество часов в неделю
 */
public record TeachingLoadModel(Integer id, TeacherModel teacher, SubjectModel subject, GroupModel group, Integer hoursPerWeek) implements IModel {
    @Override
    public String toString() {
        return String.format("%s\n%s/%s", this.teacher, this.group, this.subject);
    }
}
