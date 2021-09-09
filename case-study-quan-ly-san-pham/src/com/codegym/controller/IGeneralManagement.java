package com.codegym.controller;

public interface IGeneralManagement<T> {
    void showAll();

    void addNew(T t);

    void updateById(String id, T t);

    void removeById(String id);

    int findById(String id);
}
