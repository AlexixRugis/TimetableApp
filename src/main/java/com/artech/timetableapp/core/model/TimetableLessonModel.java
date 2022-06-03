package com.artech.timetableapp.core.model;

public record TimetableLessonModel(Integer id, Day day, Integer lessonNumber, TeachingLoadModel load) implements IModel {
    @Override
    public String toString() {
        return this.load.subject() + "\n" + this.load.teacher();
    }
}
