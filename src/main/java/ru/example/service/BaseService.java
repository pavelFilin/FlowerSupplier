package ru.filin.HavachMVC.service;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();

    T getById(long id);

    void delete(long id);

    void update(T obj);

    Long save(T obj);

}
