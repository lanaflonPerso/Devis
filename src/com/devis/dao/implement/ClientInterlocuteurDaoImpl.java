package com.devis.dao.implement;

import com.devis.beans.ClientInterlocuteur;
import com.devis.beans.Entreprise;
import com.devis.beans.EntrepriseContact;
import com.devis.beans.TypeLivraison;
import com.devis.dao.DAOFactory;
import com.devis.dao.daoException.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.devis.dao.DAOUtilitaire.*;


public class ClientInterlocuteurDaoImpl implements ClientInterlocuteurDao {

    private static final String SQL_SELECT_PAR_IDCLIENTINTERLOCUTEUR =
            "SELECT " +
                    "id_client_interlocuteur," +
                    " civilite," +
                    " nom, " +
                    " prenom," +
                    " tel," +
                    " email, " +
                    " client_id, " +
                    " adresse_livraison_id " +
                    " FROM client_interlocuteur " +
                    " WHERE id_client_interlocuteur = ?";


    private static final String SQL_SELECT =
            "SELECT " +
                    "id_client_interlocuteur," +
                    " civilite," +
                    " nom, " +
                    " prenom," +
                    " tel," +
                    " email, " +
                    " client_id, " +
                    " adresse_livraison_id " +
                    " FROM client_interlocuteur " +
                    " ORDER BY id_client_interlocuteur ASC";

    private DAOFactory daoFactory;

    public ClientInterlocuteurDaoImpl(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean delete(Long idClientInterlocuteur) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public boolean update(ClientInterlocuteur clientInterlocuteur) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public List<ClientInterlocuteur> doList()throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ClientInterlocuteur> clientInterlocuteurs = new ArrayList<ClientInterlocuteur>();

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

                ClientInterlocuteur clientInterlocuteur = new ClientInterlocuteur();
                clientInterlocuteur = map( resultSet );
                clientInterlocuteurs.add(clientInterlocuteur);
            }
        } catch ( SQLNonTransientConnectionException e ) {
            // Exception silencieuse
            System.out.println(e);
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return clientInterlocuteurs;
    }

    /* Implémentation de la méthode définie dans l'interface ClientInterlocuteurDao */
    @Override
    public boolean create(ClientInterlocuteur clientInterlocuteur) throws IllegalArgumentException, DAOException { return false; }

    /* Implémentation de la méthode définie dans l'interface ClientInterlocuteurDao */
    @Override
    public ClientInterlocuteur find( Long idClientInterlocuteur ) throws DAOException {
        return find( SQL_SELECT_PAR_IDCLIENTINTERLOCUTEUR, idClientInterlocuteur );
    }

    /*
     * Méthode générique utilisée pour retourner un clientInterlocuteur depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramétres
     * les objets passés en argument.
     */
    private ClientInterlocuteur find(String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ClientInterlocuteur clientInterlocuteur = null;

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
                clientInterlocuteur = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return clientInterlocuteur;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des clientInterlocuteur (un
     * ResultSet) et un bean ClientInterlocuteur.
     */
    private static ClientInterlocuteur map( ResultSet resultSet ) throws SQLException {
        ClientInterlocuteur clientInterlocuteur = new ClientInterlocuteur();

        clientInterlocuteur.setIdClientInterlocuteur(resultSet.getLong("id_client_interlocuteur"));
        clientInterlocuteur.setCivilite(resultSet.getString("civilite"));
        clientInterlocuteur.setNom(resultSet.getString("nom"));
        clientInterlocuteur.setPrenom(resultSet.getString("prenom"));
        clientInterlocuteur.setTel(resultSet.getString("tel"));
        clientInterlocuteur.setEmail(resultSet.getString("email"));
        clientInterlocuteur.setClientId(resultSet.getLong("client_id"));
        clientInterlocuteur.setAdresseLivraisonId(resultSet.getLong("adresse_livraison_id"));

        return clientInterlocuteur;
    }
}