package com.artech.timetableapp.core.storage;

import com.artech.timetableapp.core.manager.*;
import com.artech.timetableapp.core.model.*;
import com.artech.timetableapp.core.model.prototype.*;
import com.artech.timetableapp.core.query.DatabaseHandle;

import java.sql.SQLException;

public final class DbStorage implements IStorage {
    private final IObjectManager<TeacherModel, TeacherPrototype> teacherManager;
    private final IObjectManager<SpecialityModel, SpecialityPrototype> specialityManager;
    private final IObjectManager<SubjectModel, SubjectPrototype> subjectManager;
    private final IObjectManager<GroupModel, GroupPrototype> groupManager;
    private final IObjectManager<TeachingLoadModel, TeachingLoadPrototype> teachingLoadManager;
    private final IObjectManager<TimetableLessonModel, TimetableLessonPrototype> timetableLessonManager;

    public DbStorage(DatabaseHandle handle) throws SQLException {

        this.teacherManager = new TeacherManager(handle);
        this.specialityManager = new SpecialityManager(handle);
        this.subjectManager = new SubjectManager(handle, this.specialityManager);
        this.groupManager = new GroupManager(handle, this.specialityManager);
        this.teachingLoadManager = new TeachingLoadManager(handle, this.teacherManager, this.subjectManager, this.groupManager);
        this.timetableLessonManager = new TimetableLessonManager(handle, this.teachingLoadManager);
    }


    @Override
    public IObjectManager<TeacherModel, TeacherPrototype> teacherManager() {
        return this.teacherManager;
    }

    @Override
    public IObjectManager<SpecialityModel, SpecialityPrototype> specialityManager() {
        return this.specialityManager;
    }

    @Override
    public IObjectManager<SubjectModel, SubjectPrototype> subjectManager() {
        return this.subjectManager;
    }

    @Override
    public IObjectManager<GroupModel, GroupPrototype> groupManager() {
        return groupManager;
    }

    @Override
    public IObjectManager<TeachingLoadModel, TeachingLoadPrototype> teachingLoadManager() {
        return teachingLoadManager;
    }

    @Override
    public IObjectManager<TimetableLessonModel, TimetableLessonPrototype> timetableLessonManager() {
        return timetableLessonManager;
    }
}
