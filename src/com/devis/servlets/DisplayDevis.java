package com.devis.servlets;

import com.devis.dao.DAOFactory;
import com.devis.dao.implement.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayDevis")
public class DisplayDevis extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE              = "/WEB-INF/jsp/displayDevis.jsp";

    private DevisDao devisDao = null;
    private FactureDao factureDao = null;
    private TypeLivraisonDao typeLivraisonDao = null;
    private EntrepriseDao entrepriseDao = null;
    private EntrepriseContactDao entrepriseContactDao = null;
    private ClientInterlocuteurDao clientInterlocuteurDao = null;

    public void init() throws ServletException {

        /* Récupération d'une instance de notre DAO Devis */
        // TODO : Duplication de code
        this.devisDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDevisDao();
        /* Récupération d'une instance de notre DAO Facture */
        this.factureDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getFactureDao();
        /* Récupération d'une instance de notre DAO TypeLivraison */
        this.typeLivraisonDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTypeLivraisonDao();
        /* Récupération d'une instance de notre DAO Entreprise */
        this.entrepriseDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getEntrepriseDao();
        /* Récupération d'une instance de notre DAO EntrepriseContact */
        this.entrepriseContactDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getEntrepriseContactDao();
        /* Récupération d'une instance de notre DAO ClientInterlocuteur */
        this.clientInterlocuteurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClientInterlocuteurDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Generate Beans tables mapping lists
        this.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Generate Beans tables mapping lists
        this.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    private void doList( HttpServletRequest request )
    {
        // TODO : Duplication de code
        request.setAttribute("listeDevis", this.devisDao.doList());
        request.setAttribute("listeFactures", this.factureDao.doList());
        request.setAttribute("listeTypesLivraison", this.typeLivraisonDao.doList());
        request.setAttribute("listeEntreprises", this.entrepriseDao.doList());
        request.setAttribute("listeEntrepriseContacts", this.entrepriseContactDao.doList());
        request.setAttribute("listeClientInterlocuteurs", this.clientInterlocuteurDao.doList());
    }

    /* Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.*/
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {

        String valeur = request.getParameter( nomChamp );

        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
