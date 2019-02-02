package com.devis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "PageAccueil")
public class PageAccueil extends HttpServlet {
    public static final String VUE              = "/WEB-INF/jsp/pageAccueil.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDateTime currentTime = LocalDateTime.now();
        request.setAttribute("currentTime", currentTime);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
