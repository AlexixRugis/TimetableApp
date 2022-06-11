package com.artech.timetableapp.config;

import com.artech.timetableapp.core.ISettings;

public final class Settings implements ISettings {

    private final Integer lessonsPerDay;
    private final Integer lessonsPerWeek;
    private final Integer windowHeight;
    private final Integer windowWidth;

    public Settings(Integer lessonsPerDay, Integer lessonsPerWeek, Integer windowHeight, Integer windowWidth) {
        this.lessonsPerDay = lessonsPerDay;
        this.lessonsPerWeek = lessonsPerWeek;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
    }


    @Override
    public Integer getLessonsPerDay() {
        return this.lessonsPerDay;
    }

    @Override
    public Integer getLessonsPerWeek() {
        return this.lessonsPerWeek;
    }

    @Override
    public Integer getWindowHeight() {
        return this.windowHeight;
    }

    @Override
    public Integer getWindowWidth() {
        return this.windowWidth;
    }
}
