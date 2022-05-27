package com.artech.timetableapp.core.model.prototype;

import com.artech.timetableapp.core.model.TeacherModel;

public record TeacherPrototype(String firstName, String secondName, String lastName) implements IModelPrototype<TeacherModel> { }
