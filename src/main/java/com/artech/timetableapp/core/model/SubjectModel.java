package com.artech.timetableapp.core.model;

/**
 * Модель данных дисциплины
 * @param id Идентификатор
 * @param name Название
 * @param speciality Специальность
 * @param semester Семестр
 */
public record SubjectModel(Integer id, String name, SpecialityModel speciality, Integer semester) implements IModel {
    @Override
    public String toString() {
        return String.format("%s/%s/%s семестр", this.name, this.speciality, this.semester);
    }
}
