package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;

import java.util.Collection;

public interface ITimetableLessonManager extends IObjectManager<TimetableLessonModel> {
    void setData(TimetableLessonModel model);
    void clearData(GroupModel group, Day day, Integer lesson);
    TimetableLessonModel getData(GroupModel group, Day day, Integer lesson);
    Collection<TimetableLessonModel> getData(TeacherModel teacherModel, Day day, Integer lesson);
}
