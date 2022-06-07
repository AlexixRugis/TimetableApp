package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.*;

import java.util.Collection;

/**
 * Менеджер моделей расписания
 */
public interface ITimetableLessonManager extends IObjectManager<TimetableLessonModel> {
    /**
     * Устанавливает данные в расписание
     * @param model Модель данных расписания, которая будет установлена
     */
    void setData(TimetableLessonModel model);

    /**
     * Очищает данные расписания
     * @param group Группа, в которой будут очищены данные
     * @param day День
     * @param lesson Номер занятия
     */
    void clearData(GroupModel group, Day day, Integer lesson);

    /**
     * Получает данные расписания
     * @param group Группа
     * @param day День
     * @param lesson Номер занятия
     * @return Модель данных расписания
     */
    TimetableLessonModel getData(GroupModel group, Day day, Integer lesson);

    /**
     * Получает данные расписания
     * @param group Группа
     * @return Получить данные расписания для выбранной группы
     */
    Collection<TimetableLessonModel> getData(GroupModel group);

    /**
     * Получает данные расписания
     * @param teacherModel Преподаватель
     * @param day День
     * @param lesson Номер занятия
     * @return Коллекция данных расписания по выбранному преподавателю
     */
    Collection<TimetableLessonModel> getData(TeacherModel teacherModel, Day day, Integer lesson);

    /**
     * Получает количество учебных часов
     * @param group Группа
     * @return Количество учебных часов для выбранной группы
     */
    Integer getHours(GroupModel group);

    /**
     * Получает количество учебных часов
     * @param teachingLoad Педагогическая нагрузка
     * @return Количество учебных часов для выбранной нагрузки
     */
    Integer getHours(TeachingLoadModel teachingLoad);
}
