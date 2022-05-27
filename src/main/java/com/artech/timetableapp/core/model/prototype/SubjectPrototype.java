package com.artech.timetableapp.core.model.prototype;

import com.artech.timetableapp.core.model.SpecialityModel;
import com.artech.timetableapp.core.model.SubjectModel;

public record SubjectPrototype(String name, SpecialityModel speciality, Integer semester) implements IModelPrototype<SubjectModel> { }
