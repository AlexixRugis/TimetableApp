package com.artech.timetableapp.core.model;

public record TeachingLoadModel(TeacherModel teacherModel, SubjectModel subjectModel, GroupModel group, Integer hoursPerWeek) { }
