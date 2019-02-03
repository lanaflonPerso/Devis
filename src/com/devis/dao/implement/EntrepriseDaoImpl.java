package com.devis.dao.implement;

import com.devis.beans.Entreprise;
import com.devis.beans.TypeLivraison;
import com.devis.dao.DAOFactory;
import com.devis.dao.daoException.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.devis.dao.DAOUtilitaire.*;


public class EntrepriseDaoImpl implements EntrepriseDao {

    private static final String SQL_SELECT_PAR_IDENTREPRISE =
            "SELECT " +
                    "id_entreprise," +
                    " siret," +
                    " raison_sociale, " +
                    " num_tva," +
                    " tel," +
                    " fax, " +
                    " adresse_entreprise_id " +
                    " FROM entreprise " +
                    " WHERE id_entreprise = ?";


    private static final String SQL_SELECT =
            "SELECT " +
                    "id_entreprise," +
                    " siret," +
                    " raison_sociale, " +
                    " num_tva," +
                    " tel," +
                    " fax, " +
                    " adresse_entreprise_id " +
                    " FROM entreprise " +
                    " ORDER BY id_entreprise ASC";

    private DAOFactory daoFactory;

    public EntrepriseDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean delete(Long idEntreprise) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public boolean update(Entreprise entreprise) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public List<Entreprise> doList()throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Entreprise> entreprises = new ArrayList<Entreprise>();

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

                Entreprise entreprise = new Entreprise();
                entreprise = map( resultSet );
                entreprises.add(entreprise);
            }
        } catch ( SQLNonTransientConnectionException e ) {
            // Exception silencieuse
            System.out.println(e);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return entreprises;
    }

    /* Implémentation de la méthode définie dans l'interface EntrepriseDao */
    @Override
    public boolean create(Entreprise entreprise) throws IllegalArgumentException, DAOException { return false; }

    /* Implémentation de la méthode définie dans l'interface EntrepriseDao */
    @Override
    public Entreprise find( Long idEntreprise ) throws DAOException {
        return find( SQL_SELECT_PAR_IDENTREPRISE, idEntreprise );
    }

    /*
     * Méthode générique utilisée pour retourner une entreprise depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramétres
     * les objets passés en argument.
     */
    private Entreprise find(String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Entreprise entreprise = null;

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
                entreprise = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return entreprise;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des entreprises (un
     * ResultSet) et un bean Entreprise.
     */
    private static Entreprise map( ResultSet resultSet ) throws SQLException {
        Entreprise entreprise = new Entreprise();

        entreprise.setIdEntreprise(resultSet.getLong("id_entreprise"));
        entreprise.setSiret(resultSet.getString("siret"));
        entreprise.setRaisonSociale(resultSet.getString("raison_sociale"));
        entreprise.setNumTva(resultSet.getString("numTva"));
        entreprise.setTel(resultSet.getString("tel"));
        entreprise.setFax(resultSet.getString("fax"));
        entreprise.setAdresseEntrepriseId(resultSet.getLong("adresse_entreprise_id"));

        return entreprise;
    }
}