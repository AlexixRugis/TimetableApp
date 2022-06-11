package com.artech.timetableapp.core;

/**
 * Настройки приложения
 */
public interface ISettings {

    /**
     * Получает количество занятий в день
     * @return Количество занятий в день
     */
    Integer getLessonsPerDay();

    /**
     * Получает количество занятий в неделю
     * @return Количество занятий в неделю
     */
    Integer getLessonsPerWeek();

    /**
     * Получает высоту окна приложения
     * @return Высота окна приложения
     */
    Integer getWindowHeight();

    /**
     * Получает ширину окна приложения
     * @return Ширина окна приложения
     */
    Integer getWindowWidth();
}
