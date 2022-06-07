package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.GroupModel;
import com.artech.timetableapp.core.model.TeacherModel;
import com.artech.timetableapp.core.model.TeachingLoadModel;

import java.util.Collection;

/**
 * Менеджер моделей педагогических нагрузок
 */
public interface ITeachingLoadManager extends IObjectManager<TeachingLoadModel> {
    /**
     * Получает педагогические нагрузки относящиеся к группе
     * @param group Группа, по которой будут отобраны нагрузки
     * @return Коллекция нагрузок с выбранной группой
     */
    Collection<TeachingLoadModel> getByGroup(GroupModel group);
}
