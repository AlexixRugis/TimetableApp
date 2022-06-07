package com.artech.timetableapp.core.model;

/**
 * День недели
 */
public enum Day {
    Sunday(0),
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6);

    /**
     * Поле, хранящее номер дня недели
     */
    private final Integer index;

    /**
     * Конструктор дня недели
     * @param index номер дня
     */
    Day(Integer index) {
        this.index = index;
    }

    /**
     * Получает номер дня
     * @return Номер дня
     */
    public Integer getIndex() {
        return this.index;
    }

    /**
     * Получает день недели по номеру
     * @param index Номер дня
     * @return День недели
     */
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

    @Override
    public String toString() {
        return switch (this.index) {
            case 0 -> "Воскресенье";
            case 1 -> "Понедельник";
            case 2 -> "Вторник";
            case 3 -> "Среда";
            case 4 -> "Четверг";
            case 5 -> "Пятница";
            case 6 -> "Суббота";
            default -> throw new RuntimeException("Invalid day index!");
        };
    }
}
