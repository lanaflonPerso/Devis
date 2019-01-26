package com.devis.dao.implement;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.devis.beans.Devis;
import com.devis.dao.DAO;

public class DevisDAOimpl extends DAO<Devis> {
    public DevisDAOimpl(Connection connection) {
        super(connection);
    }

    public boolean create(Devis devis) {
        return false;
    }

    public boolean delete(Devis devis) {
        return false;
    }

    public boolean update(Devis devis) {
        return false;
    }

    public Devis find(int id) { return null; }

    public boolean add(Devis devis) {

        PreparedStatement preparedStatement = null;
        boolean isOk = false;

        try {
            // Créer un enregistrement factice de 'facture' pour permettre l'enreg de 'devis'
            Statement state = this.connection.createStatement();
            String query = "INSERT INTO `facture` (`date_facturation`, `delai_paiement_id`, `date_paiement`,";
            query += "`total_ht`, `taux_tva_100`, `total_ttc`, `statut_facture_id`, `mode_paiement_id`) ";
            query += "VALUES ('1977-06-10', 1, '2011-05-03', null, '20.0', null, 1, 2);";

            state.executeQuery(query);
            //

            preparedStatement = this.connection.prepareStatement("INSERT INTO devis " +
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
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );

            preparedStatement.setString(1, devis.getNumDevis());
            preparedStatement.setDate(2, devis.getDateDevis());
            preparedStatement.setDate(3, devis.getDateDevis());
            preparedStatement.setString(4, devis.getCommentaire());
            preparedStatement.setLong(5, devis.getClientInterlocuteurId());
            preparedStatement.setLong(6, devis.getTypeLivraisonId());
            preparedStatement.setLong(7, devis.getEntrepriseContactId());
            preparedStatement.setLong(8, devis.getEntrepriseId());
            preparedStatement.setLong(9, devis.getFactureId());


            preparedStatement.executeUpdate();

            this.connection.commit();

            isOk = true;

        } catch (SQLException e) { System.out.println(">>>>>>" + e.getCause() );
            try {
                if ( this.connection != null ) this.connection.rollback();
            } catch (SQLException e2) { }
        }
        finally {
            try {
                if (preparedStatement != null) preparedStatement.close();

            } catch (SQLException e) { }
        }

        return isOk;
}

    public List<Devis> doList() {

        List<Devis> listDevis = new ArrayList<Devis>();
        Statement statement = null;
        ResultSet resultat = null;

        try {
            statement = this.connection.createStatement();
            resultat = statement.executeQuery(
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
                            ";");

            while (resultat.next()) {

                Devis devis = new Devis();
                devis.setIdDevis(resultat.getInt("id_devis"));
                devis.setNumDevis(resultat.getString("num_devis"));
                devis.setDateDevis(resultat.getDate("date_devis"));
                devis.setDateFinValidite(resultat.getDate("date_fin_validite"));
                devis.setCommentaire(resultat.getString("commentaire"));
                devis.setClientInterlocuteurId(resultat.getInt("client_interlocuteur_id"));
                devis.setTypeLivraisonId(resultat.getInt("type_livraison_id"));
                devis.setEntrepriseContactId(resultat.getInt("entreprise_contact_id"));
                devis.setEntrepriseId(resultat.getInt("devis.entreprise_id"));
                devis.setFactureId(resultat.getInt("facture_id"));

                listDevis.add(devis);
            }
        } catch (SQLException e) { }
        finally {
            try {

                if ( statement != null )
                    statement.close();

                if ( resultat != null )
                    resultat.close();

            } catch (SQLException ignore) {}
        }
        return listDevis;
    }
}

