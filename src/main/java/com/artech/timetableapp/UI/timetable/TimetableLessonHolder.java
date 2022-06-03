package com.artech.timetableapp.UI.timetable;

import com.artech.timetableapp.UI.Views.View;
import com.artech.timetableapp.core.model.Day;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class TimetableLessonHolder extends View{

    private final Day day;
    private final Integer lesson;

    public TimetableLessonHolder(Day day, Integer lesson) {
        this.day = day;
        this.lesson = lesson;
    }

    @Override
    protected Node build() {
        return new Label(day.name() + " - " + lesson);
    }

    @Override
    public String getName() {
        return "Предмет";
    }
}
