package com.devis.servlets;

import com.devis.beans.Devis;
import com.devis.dao.DAOFactory;
import com.devis.dao.implement.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "Create")
public class Create extends HttpServlet {

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

        Devis devis = new Devis();

        /* Récupération des entrées du formulaire */
        devis.setNumDevis( request.getParameter("numDevis") );
        java.sql.Date date = Date.valueOf( request.getParameter("dateDevis") );
        devis.setDateDevis( date );
        date = Date.valueOf ( request.getParameter("dateFinValidite") );
        devis.setDateFinValidite( date );
        devis.setCommentaire( request.getParameter("commentaire") );
        devis.setClientInterlocuteurId( Long.parseLong( request.getParameter("clientInterlocuteurId") ) );
        devis.setTypeLivraisonId( Long.parseLong( request.getParameter("typeLivraisonId") ) );
        devis.setEntrepriseContactId( Long.parseLong( request.getParameter("entrepriseContactId") ) );
        devis.setEntrepriseId(Long.parseLong( request.getParameter("entrepriseId")) );

        // Insertion en bdd du devis renseigné par le formulaire
        this.devisDao.create(devis);

        this.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nameBean = request.getParameter("nameBean");
        Long idBean = Long.parseLong(request.getParameter("idBean"));

        // TODO : Modifier des ressources dans un GET est contraire au principe MVC
        switch(nameBean) {
            case "devis":
                this.devisDao.delete(idBean);
                System.out.println("Delete Devis");
                break;
            case "facture":
                System.out.println("Delete Facture");
                break;
            default:
                System.out.println("nameBean Inconnu");
        }

        this.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    private void doList( HttpServletRequest request )
    {
        request.setAttribute("listeDevis", this.devisDao.doList());
        request.setAttribute("listeFactures", this.factureDao.doList());
        request.setAttribute("listeTypesLivraison", this.typeLivraisonDao.doList());
        request.setAttribute("listeEntreprises", this.entrepriseDao.doList());
        request.setAttribute("listeEntrepriseContacts", this.entrepriseContactDao.doList());
        request.setAttribute("listeClientInterlocuteurs", this.clientInterlocuteurDao.doList());
    }
}
