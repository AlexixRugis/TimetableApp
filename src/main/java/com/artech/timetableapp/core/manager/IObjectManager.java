package com.artech.timetableapp.core.manager;

import com.artech.timetableapp.core.model.IModel;
import com.artech.timetableapp.core.model.prototype.IModelPrototype;

import java.util.Collection;

public interface IObjectManager<T extends IModel, J extends IModelPrototype<T>> {

    void addUpdateListener(IObjectManagerUpdateListener listener);
    void removeUpdateListener(IObjectManagerUpdateListener listener);
    boolean tryCreate(J prototype);
    T get(Integer id);
    Collection<T> getAll();
    boolean tryUpdate(T model);
    boolean tryDelete(T model);
}
