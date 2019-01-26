package com.devis.servlets;

import com.devis.beans.Devis;
import com.devis.dao.DAO;
import com.devis.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Devis")
public class PageDevis extends HttpServlet {

    private DAO<Devis> devisDao;

    public void init() throws ServletException {
        this.devisDao = DAOFactory.getDevisDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Devis devis = new Devis();
        devis.setIdDevis(Long.parseLong(request.getParameter("q1_nombre")));
        devis.setNumDevis(request.getParameter("q2_typeA"));


        System.out.print(">>>>>>>>>>"+ request.getParameter(  "q3_date[year]") + "-" + request.getParameter(  "q3_date[month]") + "-" + request.getParameter(  "q3_date[day]") );
        java.sql.Date date = Date.valueOf ( request.getParameter(  "q3_date[year]") + "-" + request.getParameter(  "q3_date[month]") + "-" + request.getParameter(  "q3_date[day]") );
        devis.setDateDevis( date );

        System.out.print(">>>>>>>>>>"+ request.getParameter(  "q4_date[year]") + "-" + request.getParameter(  "q4_date[month]") + "-" + request.getParameter(  "q4_date[day]") );
        //date = Date.valueOf ( request.getParameter(  "q4_date[year]") + "-" + request.getParameter(  "q4_date[month]") + "-" + request.getParameter(  "q4_date[day]") );
        devis.setDateDevis( date );

        devis.setCommentaire(request.getParameter("q5_typeA5"));
        devis.setClientInterlocuteurId(Long.parseLong(request.getParameter("q6_nombre6")));
        devis.setTypeLivraisonId(Long.parseLong(request.getParameter("q7_nombre7")));
        devis.setEntrepriseContactId(Long.parseLong(request.getParameter("q8_nombre8")));
        devis.setEntrepriseId(Long.parseLong(request.getParameter("q9_nombre9")));
        devis.setFactureId(Long.parseLong(request.getParameter("q10_nombre10")));

        boolean isAddOk = false;
        if ( devisDao.add(devis) ) isAddOk = true;

        request.setAttribute("isAddOk", isAddOk );

        request.setAttribute("listeDevis", this.devisDao.doList());

        this.getServletContext().getRequestDispatcher("/WEB-INF/pageDevis.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("listeDevis", this.devisDao.doList());

        this.getServletContext().getRequestDispatcher("/WEB-INF/pageDevis.jsp").forward(request, response);
    }
}
