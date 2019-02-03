package com.devis.servlets;

import com.devis.dao.DAOFactory;
import com.devis.dao.implement.DevisDao;
import com.devis.dao.implement.FactureDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete")
public class Delete extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE              = "/WEB-INF/jsp/display.jsp";


    private DevisDao devisDao = null;
    private FactureDao factureDao = null;

    public void init() throws ServletException {

        /* Récupération d'une instance de notre DAO Display */
        this.devisDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getDevisDao();
        /* Récupération d'une instance de notre DAO Facture */
        this.factureDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getFactureDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nameBean = request.getParameter("nameBean");
        Long idBean = Long.parseLong(request.getParameter("idBean"));

        switch(nameBean) {
            case "devis":
                this.devisDao.delete(idBean);
                System.out.println("Delete Display");
                break;
            case "facture":
                System.out.println("Delete Facture");
                break;
            default:
                System.out.println("nameBean Inconnu");
        }

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
