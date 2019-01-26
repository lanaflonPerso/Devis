package com.devis.dao;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
    protected Connection connection = null;

    protected DAO(Connection connection){
        this.connection = connection;
    }

    public abstract boolean create(T obj);

    public abstract boolean delete(T obj);

    public abstract boolean update(T obj);

    public abstract T find(int id);

    public abstract boolean add(T obj);

    public abstract List<T> doList();
}