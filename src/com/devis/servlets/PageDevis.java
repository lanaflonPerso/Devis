package com.devis.servlets;

import com.devis.beans.Devis;
import com.devis.dao.DAOFactory;
import com.devis.dao.implement.DevisDao;
import com.devis.dao.implement.FactureDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import org.apache.commons.lang3.math.NumberUtils;

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
        /* TODO : Utiliser la lib Apache Commons Lang NumberUtils (ex: toLong) pour les entrées vides "" et les entrées NULL */
        /* TODO : Modifier les types primitifs des Beans pour des types wrapper acceptant les NULL */
        /* TODO : Utiliser méthode getValeurChamp avec private static final CHAMP_X */
        /* TODO : Utiliser la lib Apache Commons BeanUtils -> BeanUtils.populate(bean, request.getParameterMap()); Pas possible à cause des dates qui sont éclatées (year, month, day)*/
        /* TODO : Utiliser les objets implicites EL (ex:initParam) pour pouvoir utiliser la lib Apache Commons BeanUtils */
        //devis.setIdDevis : absent du formulaire car pas utilisé dans DevisDaoImpl
        devis.setNumDevis(request.getParameter("numDevis"));
        java.sql.Date date = Date.valueOf ( request.getParameter(  "dateDevis[year]") + "-" + request.getParameter(  "dateDevis[month]") + "-" + request.getParameter(  "dateDevis[day]") );
        devis.setDateDevis( date );
        date = Date.valueOf ( request.getParameter(  "dateFinValidite[year]") + "-" + request.getParameter(  "dateFinValidite[month]") + "-" + request.getParameter(  "dateFinValidite[day]") );
        devis.setDateFinValidite( date );
        devis.setCommentaire(request.getParameter("commentaire"));
        devis.setClientInterlocuteurId(Long.parseLong(request.getParameter("clientInterlocuteurId")));
        devis.setTypeLivraisonId(Long.parseLong(request.getParameter("typeLivraisonId")));
        devis.setEntrepriseContactId(Long.parseLong(request.getParameter("entrepriseContactId")));
        devis.setEntrepriseId(Long.parseLong(request.getParameter("entrepriseId")));
        //devis.setFactureId : absent du formulaire car pas utilisé dans DevisDaoImpl

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
