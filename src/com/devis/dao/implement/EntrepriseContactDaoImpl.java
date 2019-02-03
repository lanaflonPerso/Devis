package com.devis.dao.implement;

import com.devis.beans.Entreprise;
import com.devis.beans.EntrepriseContact;
import com.devis.beans.TypeLivraison;
import com.devis.dao.DAOFactory;
import com.devis.dao.daoException.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.devis.dao.DAOUtilitaire.*;


public class EntrepriseContactDaoImpl implements EntrepriseContactDao {

    private static final String SQL_SELECT_PAR_IDENTREPRISECONTACT =
            "SELECT " +
                    "id_entreprise_contact," +
                    " entreprise_id," +
                    " civilite, " +
                    " nom," +
                    " prenom," +
                    " tel, " +
                    " fax, " +
                    " email " +
                    " FROM entreprise_contact " +
                    " WHERE id_entreprise_contact = ?";


    private static final String SQL_SELECT =
            "SELECT " +
                    "id_entreprise_contact," +
                    " entreprise_id," +
                    " civilite, " +
                    " nom," +
                    " prenom," +
                    " tel, " +
                    " fax, " +
                    " email " +
                    " FROM entreprise_contact " +
                    " ORDER BY id_entreprise_contact ASC";

    private DAOFactory daoFactory;

    public EntrepriseContactDaoImpl(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean delete(Long idEntreprise) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public boolean update(EntrepriseContact entrepriseContact) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public List<EntrepriseContact> doList()throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EntrepriseContact> entrepriseContacts = new ArrayList<EntrepriseContact>();

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

                EntrepriseContact entrepriseContact = new EntrepriseContact();
                entrepriseContact = map( resultSet );
                entrepriseContacts.add(entrepriseContact);
            }
        } catch ( SQLNonTransientConnectionException e ) {
            // Exception silencieuse
            System.out.println(e);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return entrepriseContacts;
    }

    /* Implémentation de la méthode définie dans l'interface EntrepriseContactDao */
    @Override
    public boolean create(EntrepriseContact entrepriseContact) throws IllegalArgumentException, DAOException { return false; }

    /* Implémentation de la méthode définie dans l'interface EntrepriseContactDao */
    @Override
    public EntrepriseContact find( Long idEntrepriseContact ) throws DAOException {
        return find( SQL_SELECT_PAR_IDENTREPRISECONTACT, idEntrepriseContact );
    }

    /*
     * Méthode générique utilisée pour retourner un entrepriseContact depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramétres
     * les objets passés en argument.
     */
    private EntrepriseContact find(String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        EntrepriseContact entrepriseContact = null;

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
                entrepriseContact = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return entrepriseContact;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des entrepriseContacts (un
     * ResultSet) et un bean EntrepriseContact.
     */
    private static EntrepriseContact map( ResultSet resultSet ) throws SQLException {
        EntrepriseContact entrepriseContact = new EntrepriseContact();

        entrepriseContact.setIdEntrepriseContact(resultSet.getLong("id_entreprise_contact"));
        entrepriseContact.setEntrepriseId(resultSet.getLong("entreprise_id"));
        entrepriseContact.setCivilite(resultSet.getString("civilite"));
        entrepriseContact.setNom(resultSet.getString("nom"));
        entrepriseContact.setPrenom(resultSet.getString("prenom"));
        entrepriseContact.setTel(resultSet.getString("tel"));
        entrepriseContact.setFax(resultSet.getString("fax"));
        entrepriseContact.setEmail(resultSet.getString("email"));

        return entrepriseContact;
    }
}