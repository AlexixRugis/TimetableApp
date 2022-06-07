/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artech.timetableapp.spreadsheets;

import java.io.File;

/**
 * Обработчик электронных таблиц
 */
public abstract class SpreadSheetWorker {

    /**
     * Файл таблицы
     */
    protected final File file;

    /**
     * Конструктор обработчика
     * @param file Файл таблицы
     */
    public SpreadSheetWorker(File file) {
        this.file = file;
    }

    /**
     * Получает файл таблицы
     * @return Файл таблицы
     */
    public File getFile() {
        return file;
    }

    /**
     * Получает название выбранного листа
     * @return Название листа
     */
    public abstract String getSheetName();

    /**
     * Выбирает лист
     * @param id Номер листа
     */
    public abstract void selectSheet(int id);

    /**
     * Получает количество листов в документе
     * @return Количество листов
     */
    public abstract int getSheetCount();

    /**
     * Получает количество столбцов листа
     * @return Количество колонок
     */
    public abstract int getColumnCount();

    /**
     * Получает количество строк листа
     * @return Количество строк
     */
    public abstract int getRowCount();

    /**
     * Устанавливает значение ячейки
     * @param x Столбец
     * @param y Строка
     * @param value Значение
     */
    public abstract void setValue(int x, int y, Object value);

    /**
     * Получает значение ячейки
     * @param x Столбец
     * @param y Строка
     * @return Значение
     */
    public abstract Object getValue(int x, int y);
}
