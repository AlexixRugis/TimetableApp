package com.artech.timetableapp.core.timetable;

import com.artech.timetableapp.core.model.Day;
import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.TimetableLessonModel;
import com.artech.timetableapp.core.storage.IStorage;

import java.util.Collection;
import java.util.Objects;

public class TimetableActions {
    private final IStorage storage;
    private GroupModel groupModel;

    public TimetableActions(IStorage storage, GroupModel groupModel) {
        this.storage = storage;
        this.groupModel = groupModel;
    }

    public void setModel(GroupModel model) {
        this.groupModel = model;
    }

    public TimetableLessonModel getLessonModel(Collection<TimetableLessonModel> models, Day day, Integer lesson) {
        for (TimetableLessonModel lessonModel : models) {
            if (lessonModel.day() == day && Objects.equals(lessonModel.lessonNumber(), lesson)) {
                return lessonModel;
            }
        }

        return null;
    }

    public Boolean hasData(TeacherModel teacher, Day day, Integer lesson) {
        Collection<TimetableLessonModel> models = this.storage.timetableLessonManager().getData(teacher, day, lesson);

        boolean has = false;
        for (TimetableLessonModel model : models) {
            if (!Objects.equals(model.group().id(), this.groupModel.id())) {
                has = true;
                break;
            }
        }

        return has;
    }

    public void setData(Integer teachingLoadId, Day day, Integer lesson) {
        TimetableLessonModel model = new TimetableLessonModel(0, day, lesson, this.storage.teachingLoadManager().get(teachingLoadId), this.groupModel);
        this.storage.timetableLessonManager().setData(model);
    }

    public void clearData(Day day, Integer lesson) {
        this.storage.timetableLessonManager().clearData(this.groupModel, day, lesson);
    }
}
