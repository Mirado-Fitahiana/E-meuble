/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categorie;
import Model.ConnectionPs;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author P15A-Mirado-159
 */
public class ServletCategorie extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*if (request.getParameter("categorie") != null && !request.getParameter("categorie").isEmpty()) {
            if (request.getParameter("nom") != null) {
                //modification
            } else {
                String idcategorie = request.getParameter("categorie");
                Connection connection = ConnectionPs.connexionPostgreSQL();
                try {
                    Categorie.changeStatCategorie(idcategorie, 0, connection);
                } catch (Exception ex) {
                    Logger.getLogger(ServletListeStyle.class.getName()).log(Level.SEVERE, null, ex);
                }
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            }else { */
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            Categorie[] categories = Categorie.categorie(connection);
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("formCategorie.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {

        }
        /*}*/
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String nom = request.getParameter("nom");
        Categorie catego = new Categorie();
        catego.setNom(nom);
        catego.setEtat(1);

        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            Categorie.insertCategorie(catego, connection);
        } catch (Exception ex) {
            Logger.getLogger(ServletCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
