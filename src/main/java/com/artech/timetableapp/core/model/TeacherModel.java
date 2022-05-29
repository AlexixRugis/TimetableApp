package com.artech.timetableapp.core.model;

public record TeacherModel(Integer id, String firstName, String secondName, String lastName) implements IModel {
    @Override
    public String toString() {
        return String.format("%s %s %s", firstName(), secondName(), lastName());
    }
}
