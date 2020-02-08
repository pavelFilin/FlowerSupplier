package ru.filin.HavachMVC.model;

import java.util.List;

public interface BaseRepository<T> {
    List<T> getAll();
    T getById(long id);
    void delete(long id);
    void update(T obj);
    Long save(T obj);
}
