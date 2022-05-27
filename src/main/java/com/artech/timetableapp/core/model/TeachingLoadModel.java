package com.artech.timetableapp.core.model;

public record TeachingLoadModel(Integer id, TeacherModel teacherModel, SubjectModel subjectModel, GroupModel group, Integer hoursPerWeek) implements IModel { }
