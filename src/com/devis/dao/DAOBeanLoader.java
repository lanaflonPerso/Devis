package com.devis.dao;

import com.devis.dao.daoException.DAOConfigurationException;
import com.devis.dao.implement.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class DAOBeanLoader {

    private static final String CONF_DAO_FACTORY = "daofactory";

    private static DevisDao devisDao = null;
    private static FactureDao factureDao = null;
    private static TypeLivraisonDao typeLivraisonDao = null;
    private static EntrepriseDao entrepriseDao = null;
    private static EntrepriseContactDao entrepriseContactDao = null;
    private static ClientInterlocuteurDao clientInterlocuteurDao = null;
    private static boolean isInitialized = false;

    public static void init( ServletContext servletContext ) throws DAOConfigurationException {

        if(!isInitialized) {

            /* Récupération d'une instance de notre DAO Devis */
            devisDao = ((DAOFactory) servletContext.getAttribute(CONF_DAO_FACTORY)).getDevisDao();
            /* Récupération d'une instance de notre DAO Facture */
            factureDao = ((DAOFactory) servletContext.getAttribute(CONF_DAO_FACTORY)).getFactureDao();
            /* Récupération d'une instance de notre DAO TypeLivraison */
            typeLivraisonDao = ((DAOFactory) servletContext.getAttribute(CONF_DAO_FACTORY)).getTypeLivraisonDao();
            /* Récupération d'une instance de notre DAO Entreprise */
            entrepriseDao = ((DAOFactory) servletContext.getAttribute(CONF_DAO_FACTORY)).getEntrepriseDao();
            /* Récupération d'une instance de notre DAO EntrepriseContact */
            entrepriseContactDao = ((DAOFactory) servletContext.getAttribute(CONF_DAO_FACTORY)).getEntrepriseContactDao();
            /* Récupération d'une instance de notre DAO ClientInterlocuteur */
            clientInterlocuteurDao = ((DAOFactory) servletContext.getAttribute(CONF_DAO_FACTORY)).getClientInterlocuteurDao();

            isInitialized = true;
        }
    }

    /** Create lists of Beans mapping */
    public static void doList( HttpServletRequest request ) {

        if(isInitialized) {

            request.setAttribute("listeDevis", devisDao.doList());
            request.setAttribute("listeFactures", factureDao.doList());
            request.setAttribute("listeTypesLivraison", typeLivraisonDao.doList());
            request.setAttribute("listeEntreprises", entrepriseDao.doList());
            request.setAttribute("listeEntrepriseContacts", entrepriseContactDao.doList());
            request.setAttribute("listeClientInterlocuteurs", clientInterlocuteurDao.doList());
        }else{
            throw new DAOConfigurationException("Le DAOBeanLoader n'a pas été initialisé");
        }
    }

    public static DevisDao getDevisDao() {
        return devisDao;
    }

    public static FactureDao getFactureDao() {
        return factureDao;
    }

    public static TypeLivraisonDao getTypeLivraisonDao() {
        return typeLivraisonDao;
    }

    public static EntrepriseDao getEntrepriseDao() {
        return entrepriseDao;
    }

    public static EntrepriseContactDao getEntrepriseContactDao() {
        return entrepriseContactDao;
    }

    public static ClientInterlocuteurDao getClientInterlocuteurDao() {
        return clientInterlocuteurDao;
    }
}
