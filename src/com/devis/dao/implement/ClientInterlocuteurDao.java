package com.devis.dao.implement;

import com.devis.beans.ClientInterlocuteur;
import com.devis.dao.daoException.DAOException;

import java.util.List;

public interface ClientInterlocuteurDao extends Dao<ClientInterlocuteur> {

    ClientInterlocuteur find(Long idClientInterlocuteur) throws DAOException;

    List<ClientInterlocuteur> doList() throws DAOException;
}