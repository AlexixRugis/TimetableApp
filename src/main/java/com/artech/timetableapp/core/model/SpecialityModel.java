package com.artech.timetableapp.core.model;

public record SpecialityModel(Integer id, String name) implements IModel {
    @Override
    public String toString() {
        return this.name;
    }
}
