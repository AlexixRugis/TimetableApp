package com.artech.timetableapp.database.storage;

import com.artech.timetableapp.core.manager.*;
import com.artech.timetableapp.core.query.DatabaseHandle;
import com.artech.timetableapp.core.storage.IStorage;
import com.artech.timetableapp.database.manager.*;

import java.sql.SQLException;

public final class DbStorage implements IStorage {
    private final ITeacherManager teacherManager;
    private final ISpecialityManager specialityManager;
    private final ISubjectManager subjectManager;
    private final IGroupManager groupManager;
    private final ITeachingLoadManager teachingLoadManager;
    private final ITimetableLessonManager timetableLessonManager;

    public DbStorage(DatabaseHandle handle) throws SQLException {

        this.teacherManager = new TeacherManager(handle);
        this.specialityManager = new SpecialityManager(handle);
        this.subjectManager = new SubjectManager(handle, this.specialityManager);
        this.groupManager = new GroupManager(handle, this.specialityManager);
        this.teachingLoadManager = new TeachingLoadManager(handle, this.teacherManager, this.subjectManager, this.groupManager);
        this.timetableLessonManager = new TimetableLessonManager(handle, this.teachingLoadManager);
    }


    @Override
    public ITeacherManager teacherManager() {
        return this.teacherManager;
    }

    @Override
    public ISpecialityManager specialityManager() {
        return this.specialityManager;
    }

    @Override
    public ISubjectManager subjectManager() {
        return this.subjectManager;
    }

    @Override
    public IGroupManager groupManager() {
        return groupManager;
    }

    @Override
    public ITeachingLoadManager teachingLoadManager() {
        return teachingLoadManager;
    }

    @Override
    public ITimetableLessonManager timetableLessonManager() {
        return timetableLessonManager;
    }
}
