package com.artech.timetableapp.core.manager;

import java.util.Collection;

public interface IObjectManager<T> {
    boolean tryCreate(T model);
    T get(Integer id);
    Collection<T> getAll();
    boolean tryUpdate(Integer id, T model);
    boolean tryDelete(Integer id);
}
