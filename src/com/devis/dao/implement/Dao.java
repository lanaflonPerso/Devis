package com.devis.dao.implement;

import com.devis.dao.daoException.DAOException;

public interface Dao<T> {

    public boolean create(T obj) throws DAOException;

    public boolean delete(Long idObj) throws DAOException;

    public boolean update(T obj) throws DAOException;

}