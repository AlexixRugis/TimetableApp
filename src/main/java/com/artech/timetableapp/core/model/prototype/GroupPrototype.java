package com.artech.timetableapp.core.model.prototype;

import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.SpecialityModel;

public record GroupPrototype(String name, SpecialityModel speciality, Integer semester, Integer numberOfStudyWeeks) implements IModelPrototype<GroupModel> {}
