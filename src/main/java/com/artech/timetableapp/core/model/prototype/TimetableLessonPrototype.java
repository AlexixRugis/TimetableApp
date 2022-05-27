package com.artech.timetableapp.core.model.prototype;

import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.TeachingLoadModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;

public record TimetableLessonPrototype(Day day, Integer lessonNumber, TeachingLoadModel load) implements IModelPrototype<TimetableLessonModel> { }
