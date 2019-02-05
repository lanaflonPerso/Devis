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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        // Récupére le nom du Bean passé en paramétre
        String nameBean = request.getParameter("nameBean");

        switch(nameBean) {

            case "devis":
                this.createDevis(request);
                break;
            case "facture":
                break;
            default:
                System.out.println("nameBean Inconnu");
        }

        // Generate Beans tables mapping lists
        this.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupére le nom du Bean.
        String nameBean = request.getParameter("nameBean");

        // TODO : Modifier des ressources dans un GET est contraire au principe MVC
        switch(nameBean) {

            case "devis":
                break;
            case "facture":
                break;
            default:
                System.out.println("nameBean Inconnu");
        }

        // Generate Beans tables mapping lists
        this.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /** Create lists of Beans mapping */
    private void doList( HttpServletRequest request )
    {
        request.setAttribute("listeDevis", this.devisDao.doList());
        request.setAttribute("listeFactures", this.factureDao.doList());
        request.setAttribute("listeTypesLivraison", this.typeLivraisonDao.doList());
        request.setAttribute("listeEntreprises", this.entrepriseDao.doList());
        request.setAttribute("listeEntrepriseContacts", this.entrepriseContactDao.doList());
        request.setAttribute("listeClientInterlocuteurs", this.clientInterlocuteurDao.doList());
    }

    /** Create devis from the inputs of the form */
    private void createDevis( HttpServletRequest request ){

        Devis devis = new Devis();

        /* Récupération des entrées du formulaire */
        /* TODO : Utiliser la lib Apache Commons Lang NumberUtils (ex: toLong) pour les entrées vides "" et les entrées NULL */
        /* TODO : Modifier les types primitifs des Beans pour des types wrapper acceptant les NULL */
        /* TODO : Utiliser méthode getValeurChamp avec private static final CHAMP_X */
        /* TODO : Utiliser la lib Apache Commons BeanUtils -> BeanUtils.populate(bean, request.getParameterMap()); */
        /* TODO : Utiliser les objets implicites EL (ex:initParam) pour pouvoir utiliser la lib Apache Commons BeanUtils */
        Integer NUM_DEVIS_MAX_CHAR = 10;
        String numDevis = request.getParameter("numDevis");
        devis.setNumDevis( numDevis.substring(0, Math.min(NUM_DEVIS_MAX_CHAR, numDevis.length() )));
        devis.setClientInterlocuteurId( Long.parseLong( request.getParameter("clientInterlocuteurId") ) );
        devis.setTypeLivraisonId( Long.parseLong( request.getParameter("typeLivraisonId") ) );
        devis.setCommentaire( request.getParameter("commentaire") );
        devis.setEntrepriseContactId( Long.parseLong( request.getParameter("entrepriseContactId") ) );
        devis.setEntrepriseId(Long.parseLong( request.getParameter("entrepriseId")) );

        /* Récupération des entrées date du formulaire */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(request.getParameter("dateDevis"), formatter);
        devis.setDateDevis(Date.valueOf(date));
        date = LocalDate.parse(request.getParameter("dateFinValidite"), formatter);
        devis.setDateFinValidite(Date.valueOf(date));

        // Insertion en bdd du devis renseigné par le formulaire
        this.devisDao.create(devis);
    }
}
