package com.webApp.school.service;

import java.util.List;

public interface MyService<T, S> {

    List<T> getAll();

    void save(T t);

    T getById(S s);

    void deleteById(S s);

    void update(T t);

    default void defaultMeth() {
    }
}
