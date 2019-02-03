package com.devis.dao.implement;

import com.devis.beans.TypeLivraison;
import com.devis.dao.daoException.DAOException;

import java.util.List;

public interface TypeLivraisonDao extends Dao<TypeLivraison> {

    TypeLivraison find(Long idTypeLivraison) throws DAOException;

    List<TypeLivraison> doList() throws DAOException;
}