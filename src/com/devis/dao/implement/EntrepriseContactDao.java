package com.devis.dao.implement;

import com.devis.beans.EntrepriseContact;
import com.devis.dao.daoException.DAOException;

import java.util.List;

public interface EntrepriseContactDao extends Dao<EntrepriseContact> {

    EntrepriseContact find(Long idEntrepriseContact) throws DAOException;

    List<EntrepriseContact> doList() throws DAOException;
}