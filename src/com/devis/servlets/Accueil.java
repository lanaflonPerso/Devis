package com.devis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Accueil")
public class Accueil extends HttpServlet {
    public static final String VUE              = "/WEB-INF/jsp/accueil.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
