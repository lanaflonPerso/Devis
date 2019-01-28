package com.devis.dao.implement;

import com.devis.beans.Devis;
import com.devis.dao.daoException.DAOException;

import java.util.List;

public interface DevisDao extends Dao<Devis> {

    Devis find(String numDevis) throws DAOException;

    List<Devis> doList() throws DAOException;
}