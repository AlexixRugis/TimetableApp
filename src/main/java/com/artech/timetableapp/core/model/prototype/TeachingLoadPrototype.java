package com.artech.timetableapp.core.model.prototype;

import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.SubjectModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;

public record TeachingLoadPrototype(TeacherModel teacherModel, SubjectModel subjectModel, GroupModel group, Integer hoursPerWeek) implements IModelPrototype<TeachingLoadModel> { }
