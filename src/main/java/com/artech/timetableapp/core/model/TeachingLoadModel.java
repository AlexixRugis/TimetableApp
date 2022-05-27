package com.artech.timetableapp.core.model;

public record TeachingLoadModel(Integer id, TeacherModel teacher, SubjectModel subject, GroupModel group, Integer hoursPerWeek) implements IModel { }
