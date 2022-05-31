package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.IModel;

import java.util.Collection;

public interface IObjectManager<T extends IModel> {

    void addUpdateListener(IManagerUpdateListener listener);
    void removeUpdateListener(IManagerUpdateListener listener);
    boolean tryCreate(T prototype);
    T get(Integer id);
    Collection<T> getAll();
    boolean tryUpdate(T model);
    boolean tryDelete(T model);
}
