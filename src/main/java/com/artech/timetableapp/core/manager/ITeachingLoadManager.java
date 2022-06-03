package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;

import java.util.Collection;

public interface ITeachingLoadManager extends IObjectManager<TeachingLoadModel> {
    Collection<TeachingLoadModel> getByGroup(GroupModel group);
}
