package com.devis.servlets;

import com.devis.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "About")
public class About extends HttpServlet {

    public static final String VUE  = "/WEB-INF/jsp/about.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO : Récupérer ces éléments de Maven plutôt que depuis les <context-param><param-name> du web.xml
        // Cf. https://openclassrooms.com/fr/courses/4503526-organisez-et-packagez-une-application-java-avec-apache-maven/4609764-packagez-vos-livrables
        request.setAttribute("application_name", getServletContext().getInitParameter("application.name"));
        request.setAttribute("project_version", getServletContext().getInitParameter("project.version"));
        request.setAttribute("maven_build_timestamp", getServletContext().getInitParameter("maven.build.timestamp"));
        request.setAttribute("organization_name", getServletContext().getInitParameter("organization.name"));
        request.setAttribute("organization_url", getServletContext().getInitParameter("organization.url"));
        request.setAttribute("poolProperties", DAOFactory.getPoolProperties());
        request.setAttribute("serverInfo", getServletContext().getServerInfo());

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
