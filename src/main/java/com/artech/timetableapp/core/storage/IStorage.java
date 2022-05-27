package com.artech.timetableapp.core.storage;

import com.artech.timetableapp.core.manager.IObjectManager;
import com.artech.timetableapp.core.model.*;
import com.artech.timetableapp.core.model.prototype.*;

public interface IStorage {
    IObjectManager<TeacherModel, TeacherPrototype> teacherManager();
    IObjectManager<SpecialityModel, SpecialityPrototype> specialityManager();
    IObjectManager<SubjectModel, SubjectPrototype> subjectManager();
    IObjectManager<GroupModel, GroupPrototype> groupManager();
    IObjectManager<TeachingLoadModel, TeachingLoadPrototype> teachingLoadManager();
    IObjectManager<TimetableLessonModel, TimetableLessonPrototype> timetableLessonManager();
}
