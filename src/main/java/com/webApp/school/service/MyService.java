package com.webApp.school.service;

import com.webApp.school.model.Course;

import java.util.List;

public interface MyService<T, S> {

    List<T> getAll();

    T save(T t);

    T getById(S s);

    void deleteById(S s);

    void update(T t);

    default void defaultMeth() {
    }
}
