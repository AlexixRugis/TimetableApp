package com.artech.timetableapp.core.model;

public record SubjectModel(Integer id, String name, SpecialityModel speciality, Integer semester) implements IModel {
    @Override
    public String toString() {
        return this.name + " - " + this.speciality + " - " + this.semester + " семестр";
    }
}
