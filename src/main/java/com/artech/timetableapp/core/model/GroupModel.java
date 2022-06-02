package com.artech.timetableapp.core.model;

public record GroupModel(Integer id, String name, SpecialityModel speciality, Integer semester, Integer numberOfStudyWeeks) implements IModel {
    @Override
    public String toString() {
        return this.name;
    }
}
