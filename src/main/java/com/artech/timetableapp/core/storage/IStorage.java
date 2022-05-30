package com.artech.timetableapp.core.storage;

import com.artech.timetableapp.core.manager.*;

public interface IStorage {
    ITeacherManager teacherManager();
    ISpecialityManager specialityManager();
    ISubjectManager subjectManager();
    IGroupManager groupManager();
    ITeachingLoadManager teachingLoadManager();
    ITimetableLessonManager timetableLessonManager();
}
