package com.devis.servlets;

import com.devis.dao.DAOBeanLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayDevis")
public class DisplayDevis extends HttpServlet {

    public static final String VUE = "/WEB-INF/jsp/displayDevis.jsp";

    public void init() {

        // TODO : voir s'il est préférable d'instancier un singleton (moins de mémoire que multiples instances de classes statiques)
        // TODO : vérifier qu'il y bien une seule instance dans l'application
        //  (peut-être que chaque servlet instancie une classe static auquel cas les verif isInirialized sont inutiles)
        DAOBeanLoader.init( getServletContext() );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Generate Beans tables mapping lists
        DAOBeanLoader.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Generate Beans tables mapping lists
        DAOBeanLoader.doList(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
