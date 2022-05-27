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
    public static Day get(Integer index) {
        return switch (index) {
            case 0 -> Day.Sunday;
            case 1 -> Day.Monday;
            case 2 -> Day.Tuesday;
            case 3 -> Day.Wednesday;
            case 4 -> Day.Thursday;
            case 5 -> Day.Friday;
            case 6 -> Day.Saturday;
            default -> throw new RuntimeException("Invalid day index!");
        };
    }
}
