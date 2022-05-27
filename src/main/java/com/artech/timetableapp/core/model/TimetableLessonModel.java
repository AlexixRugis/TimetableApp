package com.artech.timetableapp.core.model;

public record TimetableLessonModel(Integer id, Day day, Integer lessonNumber, TeachingLoadModel load) implements IModel { }
