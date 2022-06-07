package com.artech.timetableapp.core.storage;

import com.artech.timetableapp.core.manager.*;

/**
 * Хранилище данных приложения
 */
public interface IStorage {
    /**
     * Получает менеджер моделей преподавателей
     * @return Менеджер моделей преподавателей
     */
    ITeacherManager teacherManager();

    /**
     * Получает менеджер моделей специальностей
     * @return Менеджер моделей специальностей
     */
    ISpecialityManager specialityManager();

    /**
     * Получает менеджер моделей предметов
     * @return Менеджер моделей преметов
     */
    ISubjectManager subjectManager();

    /**
     * Получает менеджер моделей групп
     * @return Менеджер моделей групп
     */
    IGroupManager groupManager();

    /**
     * Получает менеджер моделей педагогической нагрузки
     * @return Менеджер моделей педагогической нагрузки
     */
    ITeachingLoadManager teachingLoadManager();

    /**
     * Получает менеджер моделей расписания
     * @return Менеджер моделей расписания
     */
    ITimetableLessonManager timetableLessonManager();
}
