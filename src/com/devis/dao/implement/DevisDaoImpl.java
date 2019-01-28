package com.devis.dao.implement;


import com.devis.beans.Devis;
import com.devis.dao.DAOFactory;
import com.devis.dao.daoException.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.devis.dao.DAOUtilitaire.*;


public class DevisDaoImpl implements DevisDao {

    private static final String SQL_SELECT_PAR_NUMDEVIS =
            "SELECT \n" +
                    "id_devis,\n" +
                    "num_devis,\n" +
                    "date_devis,\n" +
                    "date_fin_validite,\n" +
                    "commentaire,\n" +
                    "client_interlocuteur_id,\n" +
                    "type_livraison_id,\n" +
                    "entreprise_contact_id,\n" +
                    "devis.entreprise_id,\n" +
                    "facture_id  \n" +
                    "FROM devis\n" +
                    "inner join client_interlocuteur\n" +
                    "on client_interlocuteur.id_client_interlocuteur = devis.client_interlocuteur_id\n" +
                    "inner join entreprise_contact\n" +
                    "on entreprise_contact.id_entreprise_contact = devis.entreprise_contact_id\n" +
                    "and entreprise_contact.entreprise_id = devis.entreprise_id\n" +
                    "inner join facture\n" +
                    "on facture.id_facture = devis.facture_id\n" +
                    "inner join type_livraison\n" +
                    "on type_livraison.id_type_livraison = devis.type_livraison_id\n" +
                    "WHERE numDevis = ?";



    private static final String SQL_SELECT =
                        "SELECT \n" +
                                "id_devis,\n" +
                                "num_devis,\n" +
                                "date_devis,\n" +
                                "date_fin_validite,\n" +
                                "commentaire,\n" +
                                "client_interlocuteur_id,\n" +
                                "type_livraison_id,\n" +
                                "entreprise_contact_id,\n" +
                                "devis.entreprise_id,\n" +
                                "facture_id  \n" +
                                "FROM devis\n" +
                                "inner join client_interlocuteur\n" +
                                "on client_interlocuteur.id_client_interlocuteur = devis.client_interlocuteur_id\n" +
                                "inner join entreprise_contact\n" +
                                "on entreprise_contact.id_entreprise_contact = devis.entreprise_contact_id\n" +
                                "and entreprise_contact.entreprise_id = devis.entreprise_id\n" +
                                "inner join facture\n" +
                                "on facture.id_facture = devis.facture_id\n" +
                                "inner join type_livraison\n" +
                                "on type_livraison.id_type_livraison = devis.type_livraison_id\n" +
                                "ORDER BY devis.id_devis ASC;";

    private static final String SQL_INSERT_FAKE_FACTURE = "INSERT INTO facture " +
            "(date_facturation," +
            "delai_paiement_id," +
            "date_paiement," +
            "total_ht," +
            "taux_tva_100," +
            "total_ttc," +
            "statut_facture_id," +
            "mode_paiement_id) " +
            "VALUES ('1977-06-10', 1, '2011-05-03', null, '20.0', null, 1, 2)";

    private static final String SQL_INSERT =
    "INSERT INTO devis " +
            "(" +
            "num_devis,\n" +
            "date_devis,\n" +
            "date_fin_validite,\n" +
            "commentaire,\n" +
            "client_interlocuteur_id,\n" +
            "type_livraison_id,\n" +
            "entreprise_contact_id,\n" +
            "devis.entreprise_id,\n" +
            "facture_id ) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";



    private DAOFactory daoFactory;

    public DevisDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean delete(Devis devis) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public boolean update(Devis devis) throws IllegalArgumentException, DAOException { return false; }

    @Override
    public List<Devis> doList()throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Devis> listDevis = new ArrayList<Devis>();

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

                Devis devis = new Devis();
                devis = map( resultSet );
                listDevis.add(devis);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return listDevis;
    }

    /* Implémentation de la méthode définie dans l'interface DevisDao */
    @Override
    public boolean create(Devis devis) throws IllegalArgumentException, DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        boolean isCreateOk = false;

        try {
            connexion = daoFactory.getConnection();

            connexion.setAutoCommit(false); // Positionné ici car 2 il y 2 insert (Fake insert facture)

            //// Fake insert facture : Créer un enregistrement factice de 'facture' pour permettre l'enreg de 'devis' (FK unique)
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_FAKE_FACTURE, true);

            preparedStatement.executeUpdate();
            // Récupération de la PK de facture à insérer dans devis
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            valeursAutoGenerees.next();
            long idFacturePK = valeursAutoGenerees.getLong( 1 );

            fermetureSilencieuse(preparedStatement);
            ////

            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true
                ,devis.getNumDevis(),
                devis.getDateDevis(),
                devis.getDateFinValidite(),
                devis.getCommentaire(),
                devis.getClientInterlocuteurId(),
                devis.getTypeLivraisonId(),
                devis.getEntrepriseContactId(),
                devis.getEntrepriseId(),
                idFacturePK // Utilisation de la PK de facture
                );

            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du devis, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                devis.setIdDevis( valeursAutoGenerees.getLong( 1 ) );

                connexion.commit(); //Si connexion.setAutoCommit(false);

                isCreateOk = true;
            } else {
                throw new DAOException( "Échec de la création du devis en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );

        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }

        return isCreateOk;
    }

    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
    @Override
    public Devis find( String numDevis ) throws DAOException {
        return find( SQL_SELECT_PAR_NUMDEVIS, numDevis );
    }

    /*
     * Méthode générique utilisée pour retourner un devis depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramétres
     * les objets passés en argument.
     */
    private Devis find(String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Devis devis = null;

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
                devis = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return devis;
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des deviS (un
     * ResultSet) et un bean Devis.
     */
    private static Devis map( ResultSet resultSet ) throws SQLException {
        Devis devis = new Devis();
        devis.setIdDevis(resultSet.getInt("id_devis"));
        devis.setNumDevis(resultSet.getString("num_devis"));
        devis.setDateDevis(resultSet.getDate("date_devis"));
        devis.setDateFinValidite(resultSet.getDate("date_fin_validite"));
        devis.setCommentaire(resultSet.getString("commentaire"));
        devis.setClientInterlocuteurId(resultSet.getInt("client_interlocuteur_id"));
        devis.setTypeLivraisonId(resultSet.getInt("type_livraison_id"));
        devis.setEntrepriseContactId(resultSet.getInt("entreprise_contact_id"));
        devis.setEntrepriseId(resultSet.getInt("devis.entreprise_id"));
        devis.setFactureId(resultSet.getInt("facture_id"));

        return devis;
    }
}