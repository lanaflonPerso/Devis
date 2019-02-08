package com.devis.servlets;

import com.devis.dao.DAOBeanLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete")
public class Delete extends HttpServlet {

    public static final String VUE = "/WEB-INF/jsp/displayDevis.jsp";

    @Override
    public void init() throws ServletException {

        DAOBeanLoader.init( getServletContext() );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupére le nom du Bean.
        String nameBean = request.getParameter("nameBean");

        switch(nameBean) {
            case "devis":

                // Multiple deletes
                for( String idDevis : request.getParameterValues("deleteIdDevis") )
                    DAOBeanLoader.getDevisDao().delete( Long.parseLong(idDevis) );

                break;
            case "facture":
                break;
            default:
                System.out.println("nameBean Inconnu");
        }

        // Generate Beans tables mapping lists
        DAOBeanLoader.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupére le nom du Bean et son identifiant.
        String nameBean = request.getParameter("nameBean");
        Long idBean = Long.parseLong(request.getParameter("idBean"));

        // TODO : Modifier des ressources dans un GET est contraire au principe MVC
        switch(nameBean) {
            case "devis":
                DAOBeanLoader.getDevisDao().delete(idBean);
                break;
            case "facture":
                break;
            default:
                System.out.println("nameBean Inconnu");
        }

        // Generate Beans tables mapping lists
        DAOBeanLoader.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
