package com.devis.servlets;

import com.devis.beans.Devis;
import com.devis.dao.DAOFactory;
import com.devis.dao.implement.DevisDao;
import com.devis.dao.implement.FactureDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
//import org.apache.commons.lang3.math.NumberUtils;

@WebServlet(name = "Devis")
public class PageDevis extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE              = "/WEB-INF/jsp/pageDevis.jsp";


    private DevisDao devisDao = null;
    private FactureDao factureDao = null;

    public void init() throws ServletException {

        /* Récupération d'une instance de notre DAO Devis */
        this.devisDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDevisDao();
        /* Récupération d'une instance de notre DAO Facture */
        this.factureDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getFactureDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Devis devis = new Devis();

        /* Récupération des entrées du formulaire */
        /* TODO : Utiliser la lib NumberUtils (ex: toLong) pour les entrées vides "" et les entrées NULL */
        /* TODO : Modifier les types primitifs des Beans pour des types wrapper acceptant les NULL */
        /* TODO : Utiliser méthode getValeurChamp avec private static final CHAMP_X */
        //devis.setIdDevis(Long.parseLong(request.getParameter("q1_nombre"))); Pas utilisé dans DevisDaoImpl
        devis.setNumDevis(request.getParameter("q2_typeA"));
        java.sql.Date date = Date.valueOf ( request.getParameter(  "q3_date[year]") + "-" + request.getParameter(  "q3_date[month]") + "-" + request.getParameter(  "q3_date[day]") );
        devis.setDateDevis( date );
        date = Date.valueOf ( request.getParameter(  "q4_date4[year]") + "-" + request.getParameter(  "q4_date4[month]") + "-" + request.getParameter(  "q4_date4[day]") );
        devis.setDateFinValidite( date );
        devis.setCommentaire(request.getParameter("q5_typeA5"));
        devis.setClientInterlocuteurId(Long.parseLong(request.getParameter("q6_nombre6")));
        devis.setTypeLivraisonId(Long.parseLong(request.getParameter("q7_nombre7")));
        devis.setEntrepriseContactId(Long.parseLong(request.getParameter("q8_nombre8")));
        devis.setEntrepriseId(Long.parseLong(request.getParameter("q9_nombre9")));
        //devis.setFactureId(Long.parseLong(request.getParameter("q10_nombre10"))); Pas utilisé dans DevisDaoImpl

        // Insertion en bdd du devis renseigné par le formulaire
        this.devisDao.create(devis);

        request.setAttribute("listeDevis", this.devisDao.doList());
        request.setAttribute("listeFactures", this.factureDao.doList());

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("listeDevis", this.devisDao.doList());
        request.setAttribute("listeFactures", this.factureDao.doList());

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /*
     * Mé©thode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
