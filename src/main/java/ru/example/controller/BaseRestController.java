package ru.filin.HavachMVC.controller;

import java.util.List;

public interface BaseRestController<T> {
    List<T> getAll();
    T getById(long id);
    void delete(long id);
    void update(T obj);
    Long save(T obj);

}
