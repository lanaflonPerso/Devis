package com.devis.dao.implement;

import com.devis.beans.TypeLivraison;
import com.devis.dao.DAOFactory;
import com.devis.dao.daoException.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.devis.dao.DAOUtilitaire.*;


public class TypeLivraisonDaoImpl implements TypeLivraisonDao {

    private static final String SQL_SELECT_PAR_IDTYPELIVRAISON =
            "SELECT " +
                    "id_type_livraison," +
                    " libelle" +
                    " FROM type_livraison " +
                    " WHERE id_type_livraison = ?";


    private static final String SQL_SELECT =
            "SELECT " +
                    "id_type_livraison," +
                    " libelle" +
                    " FROM type_livraison " +
                    " ORDER BY id_type_livraison ASC;";


    private DAOFactory daoFactory;

    public TypeLivraisonDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean delete(Long idTypeLivraison) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public boolean update(TypeLivraison typeLivraison) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public List<TypeLivraison> doList()throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TypeLivraison> typesLivraison = new ArrayList<TypeLivraison>();

        try {
            /* Réupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, true );
            resultSet = preparedStatement.executeQuery();
            /* Parcours des lignes de données retournée dans le ResultSet */
            while (resultSet.next()) {

                TypeLivraison typeLivraison = new TypeLivraison();
                typeLivraison = map( resultSet );
                typesLivraison.add(typeLivraison);
            }
        } catch ( SQLNonTransientConnectionException e ) {
            // Exception silencieuse
            System.out.println(e);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return typesLivraison;
    }

    /* Implémentation de la méthode définie dans l'interface TypeLivraisonDao */
    @Override
    public boolean create(TypeLivraison typeLivraison) throws IllegalArgumentException, DAOException { return false; }

    /* Implémentation de la méthode définie dans l'interface TypeLivraisonDao */
    @Override
    public TypeLivraison find( Long idTypeLivraison ) throws DAOException {
        return find( SQL_SELECT_PAR_IDTYPELIVRAISON, idTypeLivraison );
    }

    /*
     * Méthode générique utilisée pour retourner une facture depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramétres
     * les objets passés en argument.
     */
    private TypeLivraison find(String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TypeLivraison typeLivraison = null;

        try {
            /* Réupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
                typeLivraison = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return typeLivraison;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des typeLivraison (un
     * ResultSet) et un bean TypeLivraison.
     */
    private static TypeLivraison map( ResultSet resultSet ) throws SQLException {
        TypeLivraison typeLivraison = new TypeLivraison();

        typeLivraison.setIdTypeLivraison(resultSet.getLong("id_type_livraison"));
        typeLivraison.setLibelle(resultSet.getString("libelle"));

        return typeLivraison;
    }
}