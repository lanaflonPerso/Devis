package com.devis.dao.implement;

import com.devis.beans.Entreprise;
import com.devis.dao.daoException.DAOException;

import java.util.List;

public interface EntrepriseDao extends Dao<Entreprise> {

    Entreprise find(Long idEntreprise) throws DAOException;

    List<Entreprise> doList() throws DAOException;
}