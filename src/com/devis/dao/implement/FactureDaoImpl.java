package com.devis.dao.implement;


import com.devis.beans.Facture;
import com.devis.dao.DAOFactory;
import com.devis.dao.daoException.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.devis.dao.DAOUtilitaire.*;


public class FactureDaoImpl implements FactureDao {

    private static final String SQL_SELECT_PAR_IDFACTURE =
            "SELECT " +
                    "id_facture," +
                    " date_facturation," +
                    " delai_paiement_id," +
                    " date_paiement," +
                    " total_ht," +
                    " taux_tva_100," +
                    " total_ttc," +
                    " statut_facture_id," +
                    " mode_paiement_id" +
                    " FROM facture" +
                    " WHERE id_facture = ?";


    private static final String SQL_SELECT =
            "SELECT " +
            "id_facture," +
            " date_facturation," +
            " delai_paiement_id," +
            " date_paiement," +
            " total_ht," +
            " taux_tva_100," +
            " total_ttc," +
            " statut_facture_id," +
            " mode_paiement_id" +
            " FROM facture " +
            " ORDER BY id_facture ASC;";


    private DAOFactory daoFactory;

    public FactureDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean delete(Facture facture) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public boolean update(Facture facture) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public List<Facture> doList()throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Facture> factures = new ArrayList<Facture>();

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

                Facture facture = new Facture();
                facture = map( resultSet );
                factures.add(facture);
            }
        } catch ( SQLNonTransientConnectionException e ) {
            // Exception silencieuse
            System.out.println(e);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
            //return factures; // Anti-pattern car masque l'exception
        }

        return factures;
    }

    /* Implémentation de la méthode définie dans l'interface FactureDao */
    @Override
    public boolean create(Facture devis) throws IllegalArgumentException, DAOException { return false; }

    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
    @Override
    public Facture find( Integer idFacture ) throws DAOException {
        return find( SQL_SELECT_PAR_IDFACTURE, idFacture );
    }

    /*
     * Méthode générique utilisée pour retourner une facture depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramétres
     * les objets passés en argument.
     */
    private Facture find(String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Facture facture = null;

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
                facture = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return facture;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des factures (un
     * ResultSet) et un bean Facture.
     */
    private static Facture map( ResultSet resultSet ) throws SQLException {
        Facture facture = new Facture();
        facture.setIdFacture(resultSet.getInt("id_facture"));
        facture.setDateFacturation(resultSet.getDate("date_facturation"));
        facture.setDelaiPaiementId(resultSet.getInt("delai_paiement_id"));
        facture.setDatePaiement(resultSet.getDate("date_paiement"));
        facture.setTotalHt(resultSet.getDouble("total_ht"));
        facture.setTauxTva100(resultSet.getDouble("taux_tva_100"));
        facture.setTotalTtc(resultSet.getDouble("total_ttc"));
        facture.setStatutFactureId(resultSet.getInt("statut_facture_id"));
        facture.setModePaiementId(resultSet.getInt("mode_paiement_id"));

        return facture;
    }
}