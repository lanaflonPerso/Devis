package com.devis.dao.implement;

import com.devis.beans.Facture;
import com.devis.dao.daoException.DAOException;

import java.util.List;

public interface FactureDao extends Dao<Facture> {

    Facture find(Long idFacture) throws DAOException;

    List<Facture> doList() throws DAOException;
}