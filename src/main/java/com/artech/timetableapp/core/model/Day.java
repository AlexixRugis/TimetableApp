package com.artech.timetableapp.core.model;

public enum Day {
    Sunday(0),
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6);

    private final Integer index;
    Day(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return this.index;
    }
}
