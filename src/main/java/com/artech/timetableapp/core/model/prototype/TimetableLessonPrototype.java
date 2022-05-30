package com.artech.timetableapp.core.model.prototype;

import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.TeachingLoadModel;

public record TimetableLessonPrototype(Day day, Integer lessonNumber, TeachingLoadModel load) { }
