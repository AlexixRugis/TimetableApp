package com.artech.timetableapp.core.model;

/**
 * Модель данных группы
 * @param id Идентификатор
 * @param name Название
 * @param speciality Специальность
 * @param semester Семестр
 * @param numberOfStudyWeeks Количество учебных недель
 */
public record GroupModel(Integer id, String name, SpecialityModel speciality, Integer semester, Integer numberOfStudyWeeks) implements IModel {
    @Override
    public String toString() {
        return this.name;
    }
}
